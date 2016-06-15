package com.shangpin.wireless.util;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.PayloadBuilder;
import com.shangpin.wireless.api.service.PushManageService;
import com.shangpin.wireless.domain.Constants;
import com.shangpin.wireless.domain.PushManageIos;

public class PushHelper {
    private final static Log log = LogFactory.getLog(PushHelper.class);

    private static final String PASSWORD = "1";

    private static final int MAX_POOL_SIZE = 8;

    public static ApnsService getAoLaiApnsService(boolean env) {
    	ApnsService aoLaiApnsService = APNS.newService().withCert(Constants.AL_KEYSTORE, PASSWORD).withAppleDestination(env).asPool(MAX_POOL_SIZE).build();
        return aoLaiApnsService;
    }

    public static ApnsService getSpApnsService(boolean env) {
        ApnsService spApnsService = APNS.newService().withCert(Constants.SP_KEYSTORE, PASSWORD).withAppleDestination(env).asPool(MAX_POOL_SIZE).build();
        return spApnsService;
    }

    public static void sendPush() {
        PushHelper pp = new PushHelper();
        pp.new PushTask("1").start();
        pp.new PushTask("2").start();
        pp.new PushTask("3").start();
        pp.new PushTask("4").start();
    }

    /**
     * 删除作废的tokens
     */
    public static void deleteAllInvalidTokens() {
        try {
            getPushManageService().deleteAllInvalidTokens();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送全部尚品push
     */
    public static void sendAllShangpinPush() {
        // 发送尚品全部接受的push广播信息
        try {
            //long startTime = new Date().getTime();
            log.info("shangpin push start!");
            getPushManageService().updateAndSendIOSPushInfo(2, BigInteger.valueOf(2));
            //long endTime = new Date().getTime();
            //log.info("shangpin push end! time:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送全部奥莱push
     */
    public static void sendAllAoLaiPush() {
        try {
            //long startTime = new Date().getTime();
            log.info("aolai push start!");
            getPushManageService().updateAndSendIOSPushInfo(2, BigInteger.valueOf(1));
            //long endTime = new Date().getTime();
            //log.info("aolai push end! time:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取apnservice
     * 
     * @param productNum
     * @return
     */
    public static ApnsService getApnService(BigInteger productNum) {
        ApnsService service = null;
        if (null != productNum && productNum.intValue() == 1) {
            service = getAoLaiApnsService(true);
        } else if (null != productNum && productNum.intValue() == 2) {
            service = getSpApnsService(true);
        }
        return service;

    }

    public static String getPayloadOfManagerInfo(PushManageIos pushManageIos) {
        PayloadBuilder builder = APNS.newPayload();
        builder.alertTitle(pushManageIos.getTitle() == null ? "" : pushManageIos.getTitle());
        builder.alertBody(pushManageIos.getNotice());
        builder.alertAction(pushManageIos.getAction() == null ? "" : pushManageIos.getAction());
        builder.sound("default");
        builder.badge(1);

        String actionArg = pushManageIos.getActionarg();
        if (actionArg != null && !"".equals(actionArg)) {
            builder.customField("actionarg", actionArg);
        }
        String actionobj = pushManageIos.getActionobj();
        if (actionobj != null && !"".equals(actionobj)) {
            builder.customField("actionobj", actionobj);
        }

        String payload = builder.build();
        return payload;
    }

    /**
     * 取得没用的token
     * 
     * @param keystore
     * @param password
     * @param flag
     * @return
     */
    public static HashSet<String> feedBackToken(BigInteger productNum) {
        ApnsService service = null;
        HashSet<String> invlidTokenSet = new HashSet<String>();
        try {
            service = getApnService(productNum);
            Map<String, Date> inactiveDevices = service.getInactiveDevices();
			if (inactiveDevices.size() > 0) {
				log.info("inactiveDevices size=" + inactiveDevices.size() + " productNum=" + productNum);
				for (String deviceToken : inactiveDevices.keySet()) {
					invlidTokenSet.add(deviceToken);
				}
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invlidTokenSet;
    }

    private static PushManageService getPushManageService() {
        ApplicationContext ac = ApplicationContextUtil.get();
        return (PushManageService) ac.getBean(PushManageService.SERVICE_NAME);
    }

    class PushTask extends Thread {
        private String sendType;
        private PushManageService pushManageService = getPushManageService();

        public PushTask(String sendType) {
            this.sendType = sendType;
        }

        @Override
        public void run() {
            try {
                if (sendType != null) {
                    if (sendType.equals("1")) {
                        // 发送尚品用户相关的push信息
                        pushManageService.updateAndSendIOSPersonPushInfo(BigInteger.valueOf(2));
                    } else if (sendType.equals("2")) {
                        // 发送尚品全部接受的push广播信息
                        pushManageService.updateAndSendIOSPushInfo(2, BigInteger.valueOf(2));
                    } else if (sendType.equals("3")) {
                        // 发送奥莱用户相关的push信息
                        pushManageService.updateAndSendIOSPersonPushInfo(BigInteger.valueOf(1));
                    } else if (sendType.equals("4")) {
                        // 发送奥莱全部接受的push广播信息
                        pushManageService.updateAndSendIOSPushInfo(2, BigInteger.valueOf(1));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getSpApnsService(true));
    }
}
