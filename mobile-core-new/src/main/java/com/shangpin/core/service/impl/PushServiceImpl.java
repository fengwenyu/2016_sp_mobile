package com.shangpin.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import javapns.devices.Device;
import javapns.devices.Devices;
import javapns.json.JSONException;
import javapns.notification.AppleNotificationServer;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;
import javapns.notification.transmission.NotificationProgressListener;
import javapns.notification.transmission.NotificationThread;
import javapns.notification.transmission.NotificationThreads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.shangpin.core.dao.PushConfigDao;
import com.shangpin.core.dao.PushDao;
import com.shangpin.core.entity.main.Push;
import com.shangpin.core.entity.main.PushConfig;
import com.shangpin.core.service.PushService;
import com.shangpin.core.util.ManagerConstants;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

@Service("pushService")
@Transactional
public class PushServiceImpl implements PushService {

    private static final Logger logger = LoggerFactory.getLogger(PushServiceImpl.class);

    @Autowired
    private PushDao pushDao;
    @Autowired
    private PushConfigDao pushConfigDao;

    @Override
    public List<Push> findAll(Page page) {
        page.setOrderField("createTime");
        page.setOrderDirection(Page.ORDER_DIRECTION_DESC);
        org.springframework.data.domain.Page<Push> springDatePage = pushDao.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDatePage.getTotalElements());
        return springDatePage.getContent();
    }

    @Override
    public void save(Push push) {
        pushDao.save(push);

    }

    @Override
    public List<Push> findByExample(Specification<Push> specification, Page page) {
        page.setOrderField("createTime");
        page.setOrderDirection(Page.ORDER_DIRECTION_DESC);
        org.springframework.data.domain.Page<Push> springDatePage = pushDao.findAll(specification, PageUtils.createPageable(page));
        page.setTotalCount(springDatePage.getTotalElements());
        return springDatePage.getContent();
    }

    @Override
    public Push get(Long id) {
        return pushDao.findOne(id);
    }

    @Override
    public void update(Push push) {
        pushDao.save(push);
    }

    @Override
    public void delete(Long id) {
        pushDao.delete(id);
    }
    
    @Override
    public List<String> tokens(final int productCode, int gender, int pageNum, int pageSize){
        List<String> tokens = new ArrayList<String>();
        List<PushConfig> pushConfigs = pushConfigDao.findAll(new Specification<PushConfig>() {
            @Override
            public Predicate toPredicate(Root<PushConfig> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Integer> type = root.get("type");
                Path<Integer> gender = root.get("gender");
                Path<Integer> isopen = root.get("isopen");
                query.where(cb.equal(type, productCode), cb.equal(gender, gender), cb.equal(isopen, 1));
                return null;
            }
            
        },new PageRequest(pageNum - 1, pageSize)).getContent();
        
        for(PushConfig pushConfig : pushConfigs){
            tokens.add(pushConfig.getToken());
        }
        return tokens;
    }

    /**
     * 针对某个具体用户的单个设备推送
     */
    @Override
    public void updateAndSendIOSPersonPushInfo(int productCode) {
        List<Push> pushs = pushDao.findSendPushs(productCode, ManagerConstants.IOS, ManagerConstants.UNSEND);
        // push 密钥库
        String keystore = "";
        // 密钥库密码
        String password = "1";
        // 生产or测试，true为生产
        if(pushs != null && pushs.size() > 0){
            if (productCode == 1) {
                keystore = "/home/pushcertificate/aolaipush.p12";
                password = "1";
            } else if (productCode == 2) {
                keystore = "/home/pushcertificate/shangpinpush.p12";
                password = "1";
            }
        }
        for (Push push : pushs) {
            // showtime字段为空或小于当前时间时发送push信息
            if (push.getStartTime() == null || push.getStartTime().getTime() <= new Date().getTime()) {
                PushConfig pushConfig = pushConfigDao.findByTypeAndUserid(productCode, push.getUserId());
                String token = pushConfig.getToken();
                logger.info("PushServiceImpl::updateAndSendIOSPersonPushInfo token is " + token + "; productCode = " + productCode);
                if (!StringUtils.isEmpty(token)) {
                    PushNotificationPayload payLoad;
                    try {
                        payLoad = new PushNotificationPayload(push.getContent());
                        javapns.Push.payload(payLoad, keystore, password, true, 1, token);
                        push.setStatus(1);
                        pushDao.save(push);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * 多个设备的推送
     */
    @Override
    public void updateAndSendIOSPushInfo(int productCode, int gender) {
        try {
            List<Push> pushs = pushDao.findSendPushs(productCode, ManagerConstants.IOS, 2, ManagerConstants.UNSEND);
                // push 密钥库
               String keystore = "";
               // 密钥库密码
               String password = "1";
               int start = 1;//分页
               int size = 10000;
               int threadNums = 20;
               // 生产or测试，true为生产
               if (productCode == 1) {
                   keystore = "/home/pushcertificate/aolaipush.p12";
               } else if (productCode == 2) {
                   keystore = "/home/pushcertificate/shangpinpush.p12";
               }
               if (null != pushs && pushs.size() > 0) {
                   List<String> tokens = null;
                   List<Device> invlidToken = null;//无效的设备，一个token对应一个设备
                   for (Push push : pushs) {
                       // showtime字段为空或小于当前时间时发送push信息
                       if(push.getStartTime() == null || push.getStartTime().getTime() <= new Date().getTime()){
                           if(invlidToken == null){
                               invlidToken = javapns.Push.feedback(keystore, password, true);
                           }
                           
                          tokens = tokens(productCode, gender, start, size);
                          while(null != tokens && tokens.size() > 0){
                              if (invlidToken != null && invlidToken.size() > 0) {
                                  for (int i = 0; i < invlidToken.size(); i++) {
                                      String temp = invlidToken.get(i).getToken();
                                      tokens.remove(temp);
                                      if(productCode == 1){
                                          logger.info("aolai unuse device ==" + temp);
                                      }else{
                                          logger.info("shangpin unuse device ==" + temp);
                                      }
                                  }
                              }
                              PushNotificationPayload payLoad = new PushNotificationPayload(push.getContent());
                              pushSimplePayloadUsingThreads(payLoad, keystore, password, tokens, threadNums);
                              start++;
                              Thread.sleep(60000);
                              tokens = tokens(productCode, gender, start, size);
                          }
                       }
                   }
               }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
            
    }

    private void pushSimplePayloadUsingThreads(PushNotificationPayload payLoad, String keystore, String password, List<String> tokens, int threads) {
        try {
            logger.info("Creating PushNotificationManager and AppleNotificationServer");
            AppleNotificationServer server = new AppleNotificationServerBasicImpl(keystore, password, true);
            logger.info("Generating " + tokens.size() + " fake devices");
            List<Device> deviceList = Devices.asDevices(tokens);
            logger.info("Creating " + threads + " notification threads");
            NotificationThreads work = new NotificationThreads(server, payLoad, deviceList, threads);
//          work.setMaxNotificationsPerConnection(1000);//每条线程发送1000
            logger.info("Linking notification work debugging listener");
            work.setListener(DEBUGGING_PROGRESS_LISTENER);
            logger.info("Starting all threads...");
            long timestamp1 = System.currentTimeMillis();
            work.start();
            logger.info("All threads started, waiting for them...");
            work.waitForAllThreads();
            long timestamp2 = System.currentTimeMillis();
            logger.info("All threads finished in " + (timestamp2 - timestamp1) + " milliseconds");
            printPushedNotifications(work.getPushedNotifications(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void printPushedNotifications(PushedNotifications notifications) {
        try{
            List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
            List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
            int failed = failedNotifications.size();
            int successful = successfulNotifications.size();
            if (successful > 0 && failed == 0) {
                printPushedNotifications("All notifications pushed successfully (" + successfulNotifications.size() + "):", successfulNotifications);
            } else if (successful == 0 && failed > 0) {
                printPushedNotifications("All notifications failed (" + failedNotifications.size() + "):", failedNotifications);
            } else if (successful == 0 && failed == 0) {
                logger.info("No notifications could be sent, probably because of a critical error");
            } else {
                printPushedNotifications("Some notifications failed (" + failedNotifications.size() + "):", failedNotifications);
                printPushedNotifications("Others succeeded (" + successfulNotifications.size() + "):", successfulNotifications);
            }
        }catch (Exception e) {
        }
        
    }

    private void printPushedNotifications(String description, List<PushedNotification> notifications) {
        logger.info(description);
        for (PushedNotification notification : notifications) {
            try {
                //打印其中一条
                logger.info("  " + notification.toString());
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    /**
     * A NotificationProgressListener you can use to debug NotificationThreads.
     */
    public final static NotificationProgressListener DEBUGGING_PROGRESS_LISTENER = new NotificationProgressListener() {
        public void eventThreadStarted(NotificationThread notificationThread) {
            logger.info("   [EVENT]: thread #" + notificationThread.getThreadNumber() + " started with " + notificationThread.getDevices().size() + " devices beginning at message id #" + notificationThread.getFirstMessageIdentifier());
        }

        public void eventThreadFinished(NotificationThread thread) {
            logger.info("   [EVENT]: thread #" + thread.getThreadNumber() + " finished: pushed messages #" + thread.getFirstMessageIdentifier() + " to " + thread.getLastMessageIdentifier() + " toward " + thread.getDevices().size() + " devices");
        }

        public void eventConnectionRestarted(NotificationThread thread) {
            logger.info("   [EVENT]: connection restarted in thread #" + thread.getThreadNumber() + " because it reached " + thread.getMaxNotificationsPerConnection() + " notifications per connection");
        }

        public void eventAllThreadsStarted(NotificationThreads notificationThreads) {
            logger.info("   [EVENT]: all threads started: " + notificationThreads.getThreads().size());
        }

        public void eventAllThreadsFinished(NotificationThreads notificationThreads) {
            logger.info("   [EVENT]: all threads finished: " + notificationThreads.getThreads().size());
        }

        public void eventCriticalException(NotificationThread notificationThread, Exception exception) {
            logger.info("   [EVENT]: critical exception occurred: " + exception);
        }
    };

    @Override
    public List<Push> findSendPushs(int productCode, int platformType,int status) {
        return pushDao.findSendPushs(productCode, platformType, status);
    }

    @Override
    public List<Push> findSendPushs(int productCode, int platformType, int gender, int status) {
        return pushDao.findSendPushs(productCode, platformType, gender, status);
    }

}
