package com.shangpin.wireless.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.dbay.apns4j.IApnsService;
import com.dbay.apns4j.impl.ApnsServiceImpl;
import com.dbay.apns4j.model.ApnsConfig;
import com.dbay.apns4j.model.Feedback;
import com.dbay.apns4j.model.Payload;
import com.shangpin.wireless.api.service.PushManageService;
import com.shangpin.wireless.domain.Constants;
import com.shangpin.wireless.domain.PushManageIos;

public class PushUtil {
	private final static Log log = LogFactory.getLog(PushUtil.class);

	//奥莱service
	private static IApnsService aoLaiApnsService;
	//商品service
	private static IApnsService spApnsService;

	public static IApnsService getAoLaiApnsService() {
		if (aoLaiApnsService == null) {
			ApnsConfig config = new ApnsConfig();
			InputStream is = null;
			try {
				is = new BufferedInputStream(new FileInputStream(new File(Constants.AL_KEYSTORE)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			config.setKeyStore(is);
			config.setDevEnv(Constants.PUSH_TEST);
			config.setPassword("1");
			config.setPoolSize(4);
			config.setName("aolai");
			aoLaiApnsService = ApnsServiceImpl.createInstance(config);
		}
		return aoLaiApnsService;
	}

	public static IApnsService getSpApnsService() {
		if (spApnsService == null) {
			ApnsConfig config = new ApnsConfig();
			InputStream is = null;
			try {
				is = new BufferedInputStream(new FileInputStream(new File(Constants.SP_KEYSTORE)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			config.setKeyStore(is);
			config.setDevEnv(Constants.PUSH_TEST);
			config.setPassword("1");
			config.setPoolSize(12);
			config.setName("shangpin");
			spApnsService = ApnsServiceImpl.createInstance(config);
		}
		return spApnsService;
	}
	
	public static void sendPush() {
		PushUtil pp = new PushUtil();
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
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			log.info(df.format(new Date()) + "开始执行尚品发送");
			getPushManageService().updateAndSendIOSPushInfo(2, BigInteger.valueOf(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送全部奥莱push
	 */
	public static void sendAllAoLaiPush() {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			log.info(df.format(new Date()) + "开始执行奥莱发送");
			getPushManageService().updateAndSendIOSPushInfo(2, BigInteger.valueOf(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 获取apnservice
	 * @param productNum
	 * @return
	 */
	public static IApnsService getIApnService(BigInteger productNum){
		IApnsService service = null;
		if (null != productNum && productNum.intValue() == 1) {
			service = PushUtil.getAoLaiApnsService();
		} else if (null != productNum && productNum.intValue() == 2) {
			service = PushUtil.getSpApnsService();
		}
		return service;
	
	}
	
	public static Payload getPayloadOfManagerInfo(PushManageIos pushManageIos){
		Payload payload = new Payload();
		payload.setAlert(pushManageIos.getNotice());
		payload.setBadge(1);
		payload.setSound("default");
		payload.addParam("id", String.valueOf(pushManageIos.getId()));
		payload.addParam("title", pushManageIos.getTitle() == null ? "" : pushManageIos.getTitle());
		payload.addParam("action", pushManageIos.getAction() == null ? "":pushManageIos.getAction());
		String actionArg = pushManageIos.getActionarg();
		if(actionArg != null && !"".equals(actionArg)){
			payload.addParam("actionarg", actionArg);
		}
		String actionobj = pushManageIos.getActionobj();
		if(actionobj != null && !"".equals(actionobj)){
			payload.addParam("actionobj", actionobj);
		}
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
		IApnsService service = null;
		HashSet<String> invlidTokenSet = new HashSet<String>();
		try {
			service = getIApnService(productNum);
			List<Feedback> list = service.getFeedbacks();
			if (list != null && list.size() > 0) {
				for (Feedback feedback : list) {
					invlidTokenSet.add(feedback.getToken());
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
}
