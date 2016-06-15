package com.shangpin.wireless.api.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.dbay.apns4j.IApnsService;
import com.dbay.apns4j.model.Payload;
import com.shangpin.wireless.api.service.PushManageService;
import com.shangpin.wireless.api.service.PushconfigAolaiService;
import com.shangpin.wireless.api.service.PushconfigShangpinService;
import com.shangpin.wireless.dao.PushManageAndroidDao;
import com.shangpin.wireless.dao.PushManageIosDao;
import com.shangpin.wireless.dao.PushconfigAolaiDao;
import com.shangpin.wireless.dao.PushconfigShangpinDao;
import com.shangpin.wireless.domain.Constants;
import com.shangpin.wireless.domain.PushManageAndroid;
import com.shangpin.wireless.domain.PushManageIos;
import com.shangpin.wireless.util.DataContainerPool;
import com.shangpin.wireless.util.PushHelper;
import com.shangpin.wireless.util.PushUtil;

/**
 * push业务逻辑层接口实现类，提供，android、osi等平台push信息内容
 * 
 * @Author yumeng
 * @CreateDate: 2013-02-22
 */
@Service(PushManageService.SERVICE_NAME)
public class PushManageServiceImpl implements PushManageService {

    private static final Log log = LogFactory.getLog(PushManageServiceImpl.class);
    private final String SP_KEYSTORE = Constants.SP_KEYSTORE;
    private final String AL_KEYSTORE = Constants.AL_KEYSTORE;
    private final int PAGESIZE = 300000;

    @Resource(name = PushManageAndroidDao.DAO_NAME)
    private PushManageAndroidDao pushManageAndroidDao;
    @Resource(name = PushManageIosDao.DAO_NAME)
    private PushManageIosDao pushManageIOSDao;
    @Resource(name = PushconfigShangpinService.SERVICE_NAME)
    PushconfigShangpinService pushconfigShangpinService;
    @Resource(name = PushconfigAolaiService.SERVICE_NAME)
    PushconfigAolaiService pushconfigAolaiService;
    @Resource(name = PushconfigAolaiDao.DAO_NAME)
    private PushconfigAolaiDao pushconfigAolaiDao;
    @Resource(name = PushconfigShangpinDao.DAO_NAME)
    private PushconfigShangpinDao pushconfigShangpinDao;

    /**
     * 将手动录入push信息、系统自动push信息合并成一条json字符串（用逗号分割）
     * 
     * @param buffer
     * @param obj
     * @param objauto
     */
    private void mergeBroadcast(StringBuffer buffer, Object obj, Object objauto) {
        if (null != obj && null != objauto) {
            buffer.append(obj.toString()).append(",").append(objauto.toString());
        } else if (null != obj && null == objauto) {
            buffer.append(obj.toString());
        } else if (null == obj && null != objauto) {
            buffer.append(objauto.toString());
        }
    }

    @Override
    public String updateAndGetAndroidPushInfo(String userId, String gender, String productNum) throws Exception {
        // 组装广播信息
        StringBuffer buffer = new StringBuffer("[");
        Object obj = null;
        Object objauto = null;
        if ("0".equals(gender)) {
            if ("101".equals(productNum)) {
                obj = DataContainerPool.pushManageContainer.get(Constants.PUSH_BROADCAST_ANDROID_AOLAI_0);
                objauto = DataContainerPool.pushManageContainer.get(Constants.PUSH_BROADCAST_ANDROID_AOLAI_AUTO_0);
            } else if ("102".equals(productNum)) {
                obj = DataContainerPool.pushManageContainer.get(Constants.PUSH_BROADCAST_ANDROID_SHANGPIN_0);
                objauto = DataContainerPool.pushManageContainer.get(Constants.PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_0);
            }
            mergeBroadcast(buffer, obj, objauto);
        } else if ("1".equals(gender)) {
            if ("101".equals(productNum)) {
                obj = DataContainerPool.pushManageContainer.get(Constants.PUSH_BROADCAST_ANDROID_AOLAI_1);
                objauto = DataContainerPool.pushManageContainer.get(Constants.PUSH_BROADCAST_ANDROID_AOLAI_AUTO_1);
            } else if ("102".equals(productNum)) {
                obj = DataContainerPool.pushManageContainer.get(Constants.PUSH_BROADCAST_ANDROID_SHANGPIN_1);
                objauto = DataContainerPool.pushManageContainer.get(Constants.PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_1);
            }
            mergeBroadcast(buffer, obj, objauto);
        } else if ("2".equals(gender)) {
            if ("101".equals(productNum)) {
                obj = DataContainerPool.pushManageContainer.get(Constants.PUSH_BROADCAST_ANDROID_AOLAI_2);
                objauto = DataContainerPool.pushManageContainer.get(Constants.PUSH_BROADCAST_ANDROID_AOLAI_AUTO_2);
            } else if ("102".equals(productNum)) {
                obj = DataContainerPool.pushManageContainer.get(Constants.PUSH_BROADCAST_ANDROID_SHANGPIN_2);
                objauto = DataContainerPool.pushManageContainer.get(Constants.PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_2);
            }
            mergeBroadcast(buffer, obj, objauto);
        }
        if (StringUtils.isNotEmpty(userId)) {
            // 获取与个人相关的push信息内容
            List<PushManageAndroid> list = pushManageAndroidDao.findPushManageAndroidByUser(userId, new BigInteger(productNum));
            if (null != list && list.size() > 0) {
                if (buffer.length() > 1) {
                    buffer.append(",");
                }
                for (int i = 0; i < list.size(); i++) {
                    PushManageAndroid pushManageAndroid = list.get(i);
                    buffer.append(null == pushManageAndroid.getPushContent() ? "{}" : pushManageAndroid.getPushContent());
                    if (list.size() - 1 > i) {
                        buffer.append(",");
                    }
                    // 更新push状态为已发送
                    pushManageAndroidDao.updatePushManageAndroidType(pushManageAndroid.getId());
                }
            }
        }
        buffer.append("]");
        return buffer.toString();
    }

    @Override
    public String getAndroidPushInfo(int gender, BigInteger productNum) throws Exception {
        List<PushManageAndroid> list = pushManageAndroidDao.findPushManageAndroid(gender, productNum);
        if (null != list && list.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                PushManageAndroid pushManageAndroid = list.get(i);
                buffer.append(pushManageAndroid.getPushContent());
                if (list.size() - 1 > i) {
                    buffer.append(",");
                }
            }
            return buffer.toString();
        } else {
            return null;
        }
    }

    /**
     * 取得keystore
     * 
     * @param productNum
     * @return
     */
    @SuppressWarnings("unused")
    private String getKeyStore(BigInteger productNum) {
        String keyStore = null;
        if (null != productNum && productNum.intValue() == 1) {
            keyStore = AL_KEYSTORE;
        } else if (null != productNum && productNum.intValue() == 2) {
            keyStore = SP_KEYSTORE;
        }
        return keyStore;
    }

    /**
     * 取得token
     * 
     * @param productNum
     * @return
     * @throws Exception
     */
    private List<String> getTokens(int gender, int start, BigInteger productNum) throws Exception {
        List<String> token = null;
        if (null != productNum && productNum.intValue() == 1) {
            token = pushconfigAolaiService.getToken(gender, start, PAGESIZE);
        } else if (null != productNum && productNum.intValue() == 2) {
            token = pushconfigShangpinService.getToken(gender, start, PAGESIZE);
        }
        return token;
    }

//    @Override
//    public void updateAndSendIOSPushInfo(int gender, BigInteger productNum) {
//        ApnsService service = PushHelper.getApnService(productNum);
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        cal.add(Calendar.MINUTE, 240);
//        Date expire = cal.getTime();
//        
//        try {
//            int start = 1;
//            List<PushManageIos> list = pushManageIOSDao.findPushManageIOS(gender, productNum);
//            List<String> tokens = null;
//            for (PushManageIos pushManageIos : list) {
//                // showtime字段为空或小于当前时间时发送push信息
//                if (!(pushManageIos.getShowTime() == null || pushManageIos.getShowTime().getTime() <= new Date().getTime())) {
//                    continue;
//                } else {
//                }
//                start = 1;
//                tokens = getTokens(gender, start, productNum);
//                pushManageIOSDao.updatePushManageIOSType(pushManageIos.getId());
//                String payload = PushHelper.getPayloadOfManagerInfo(pushManageIos);
//                while (null != tokens && tokens.size() > 0) {
//					service.push(tokens, payload, expire);
//                    start++;
//                    tokens = getTokens(gender, start, productNum);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally{
//        	service.stop();
//        }
//    }

	/*@Override
	public void updateAndSendIOSPushInfo(int gender, BigInteger productNum) {
		try {
			int start = 1;
			//IApnsService service = PushUtil.getIApnService(productNum);
			List<PushManageIos> list = pushManageIOSDao.findPushManageIOS(gender, productNum);

			List<String> tokens = null;
			for (PushManageIos pushManageIos : list) {
				// showtime字段为空或小于当前时间时发送push信息
				if (!(pushManageIos.getShowTime() == null || pushManageIos.getShowTime().getTime() <= new Date().getTime())) {
					continue;
				} else {
				}
				start = 1;
				tokens = getTokens(gender, start, productNum);
				pushManageIOSDao.updatePushManageIOSType(pushManageIos.getId());
				Payload payload = PushUtil.getPayloadOfManagerInfo(pushManageIos);
				while (null != tokens && tokens.size() > 0) {
					for (String token : tokens) {
						service.sendNotification(token, payload);
					}
					PushIosService pushService = new PushIosService(productNum, tokens, payload);
					resultList.add(executorService.submit(pushService));
					start++;
					tokens = getTokens(gender, start, productNum);
				}
				threadTerminate(resultList);
				executorService.shutdown();
		        return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
    
    @Override
	public void updateAndSendIOSPushInfo(int gender, BigInteger productNum) {
		try {
			int start = 1;
			IApnsService service = PushUtil.getIApnService(productNum);
			List<PushManageIos> list = pushManageIOSDao.findPushManageIOS(gender, productNum);

			List<String> tokens = null;
			for (PushManageIos pushManageIos : list) {
				// showtime字段为空或小于当前时间时发送push信息
				if (!(pushManageIos.getShowTime() == null || pushManageIos.getShowTime().getTime() <= new Date().getTime())) {
					continue;
				} else {
				}
				start = 1;
				tokens = getTokens(gender, start, productNum);
				pushManageIOSDao.updatePushManageIOSType(pushManageIos.getId());
				Payload payload = PushUtil.getPayloadOfManagerInfo(pushManageIos);
				while (null != tokens && tokens.size() > 0) {
					for (String token : tokens) {
						service.sendNotification(token, payload);
					}
					start++;
					tokens = getTokens(gender, start, productNum);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    @Override
    public void updateAndSendIOSPersonPushInfo(BigInteger productNum) throws Exception {
        List<PushManageIos> list = pushManageIOSDao.findPushManageIOSByUser(productNum);
        // push 密钥库
        // String keystore = getKeyStore(productNum);
        // 密钥库密码
        // 生产or测试，true为生产
        if (null != list && list.size() > 0) {
            for (PushManageIos pushManageIos : list) {
                // showtime字段为空或小于当前时间时发送push信息
                if (null != pushManageIos && StringUtils.isNotEmpty(pushManageIos.getUserId())
                        && (pushManageIos.getShowTime() == null || pushManageIos.getShowTime().getTime() <= new Date().getTime())) {
                    String token = executeHql(productNum, pushManageIos.getUserId());
                    log.info("PushManageServiceImpl::updateAndSendIOSPersonPushInfo token is " + token + "; productNum = " + productNum);
                    if (StringUtils.isNotEmpty(token)) {
                        String payload = PushHelper.getPayloadOfManagerInfo(pushManageIos);
                        PushHelper.getApnService(productNum).push(token, payload);
                        pushManageIOSDao.updatePushManageIOSType(pushManageIos.getId());
                    }
                }
            }
        }
    }

    /**
     * 根据HQL语句获取数据集合
     * 
     * @param productNum
     *            产品号：1为奥莱；2为尚品
     * @param userId
     *            用户id
     * @Return token
     */
    @SuppressWarnings("unchecked")
    public String executeHql(BigInteger productNum, String userId) throws Exception {
        if (null == productNum) {
            return null;
        }
        String hql = "";
        if (productNum.intValue() == 1) {
            hql = "SELECT token FROM PushconfigAolai WHERE userId = '" + userId + "'";
            List<String> list = pushconfigAolaiDao.executeHql(hql);
            return (null != list && list.size() > 0) ? list.get(0) : null;
        } else if (productNum.intValue() == 2) {
            hql = "SELECT token FROM PushconfigShangpin WHERE userId = '" + userId + "'";
            List<String> list = pushconfigShangpinDao.executeHql(hql);
            return (null != list && list.size() > 0) ? list.get(0) : null;
        } else {
            return null;
        }
    }

    private String getTokens(HashSet<String> tokens) {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("token in (");
            String[] keys = tokens.toArray(new String[0]);
            for (int i = 0; i < keys.length; i++) {
                String token = keys[i].toLowerCase();
                if (i == keys.length - 1) {
                    sb.append("'").append(token).append("'");
                } else {
                    sb.append("'").append(token).append("'").append(",");
                }
            }
            sb.append(")");
            return sb.toString();
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void deleteAllInvalidTokens() throws Exception {
        try {
            HashSet<String> alTokens = PushHelper.feedBackToken(new BigInteger("1"));
            HashSet<String> spTokens = PushHelper.feedBackToken(new BigInteger("2"));
            if (alTokens != null && alTokens.size() > 0) {
                pushconfigAolaiDao.deleteByCondition(getTokens(alTokens));
            }
            if (spTokens != null && spTokens.size() > 0) {
                pushconfigShangpinDao.deleteByCondition(getTokens(spTokens));
            }
        } catch (Exception e) {
        }
    }
    
	@SuppressWarnings("unused")
	private void threadTerminate(List<Future<String>> futureList) {
		for (Future<String> future : futureList) {
			try {
				// 如果计算未完成则阻塞
				log.info(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}