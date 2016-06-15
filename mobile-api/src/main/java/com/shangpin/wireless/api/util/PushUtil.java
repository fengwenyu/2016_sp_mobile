package com.shangpin.wireless.api.util;

import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.service.PushManageService;

public class PushUtil {
	private final static Log log = LogFactory.getLog(PushUtil.class);


	public static void sendPush() {
		try {
			// 更新缓存中push信息内容
			savePushManageContainer();
			PushManageService pushManageService = getPushManageService();
			// 发送奥莱用户相关的push信息
			pushManageService.updateAndSendIOSPersonPushInfo(BigInteger.valueOf(1));
			// 发送奥莱女性接受的push广播信息
			pushManageService.updateAndSendIOSPushInfo(0, BigInteger.valueOf(1));
			// 发送奥莱男性接受的push广播信息
			pushManageService.updateAndSendIOSPushInfo(1, BigInteger.valueOf(1));
			// 发送奥莱全部接受的push广播信息
			pushManageService.updateAndSendIOSPushInfo(2, BigInteger.valueOf(1));
			// 发送尚品用户相关的push信息
			pushManageService.updateAndSendIOSPersonPushInfo(BigInteger.valueOf(2));
			// 发送尚品女性接受的push广播信息
			pushManageService.updateAndSendIOSPushInfo(0, BigInteger.valueOf(2));
			// 发送尚品男性接受的push广播信息
			pushManageService.updateAndSendIOSPushInfo(1, BigInteger.valueOf(2));
			// 发送尚品全部接受的push广播信息
			pushManageService.updateAndSendIOSPushInfo(2, BigInteger.valueOf(2));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("PushUtil :"+e);
		}
	}

	/**
	 * 添加、更新缓存中push信息内容（Android平台广播）
	 */
	public static void savePushManageContainer() {
		try {
			// 重新获取数据库中的广播并更新到缓存中
			PushManageService pushManageService = getPushManageService();
			// 尚品女性相关的广播
			DataContainerPool.pushManageContainer.putOrReplace(Constants.PUSH_BROADCAST_ANDROID_SHANGPIN_0, pushManageService.getAndroidPushInfo(0, BigInteger.valueOf(102)));
			// 尚品男性相关的广播
			DataContainerPool.pushManageContainer.putOrReplace(Constants.PUSH_BROADCAST_ANDROID_SHANGPIN_1, pushManageService.getAndroidPushInfo(1, BigInteger.valueOf(102)));
			// 尚品全部广播
			DataContainerPool.pushManageContainer.putOrReplace(Constants.PUSH_BROADCAST_ANDROID_SHANGPIN_2, pushManageService.getAndroidPushInfo(2, BigInteger.valueOf(102)));
			// 尚品奥莱女性相关的广播
			DataContainerPool.pushManageContainer.putOrReplace(Constants.PUSH_BROADCAST_ANDROID_AOLAI_0, pushManageService.getAndroidPushInfo(0, BigInteger.valueOf(101)));
			// 尚品奥莱男性相关的广播
			DataContainerPool.pushManageContainer.putOrReplace(Constants.PUSH_BROADCAST_ANDROID_AOLAI_1, pushManageService.getAndroidPushInfo(1, BigInteger.valueOf(101)));
			// 尚品奥莱全部广播
			DataContainerPool.pushManageContainer.putOrReplace(Constants.PUSH_BROADCAST_ANDROID_AOLAI_2, pushManageService.getAndroidPushInfo(2, BigInteger.valueOf(101)));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("PushUtil :"+e);
		}
	}

	/**
	 * 将系统自动推送的push信息添加到缓存中
	 * 
	 * @param gender
	 *            0：女；1：男；2全部
	 * @param msg
	 *            push信息
	 * @param platform
	 *            AOLAI/SHANGPIN
	 */
	public static void addPushInfo(int gender, String platform, String msg) {
		StringBuffer buffer = new StringBuffer();
		String key = null;
		if (gender == 0) {
			if ("AOLAI".equals(platform)) {
				key = Constants.PUSH_BROADCAST_ANDROID_AOLAI_AUTO_0;
			} else if ("SHANGPIN".equals(platform)) {
				key = Constants.PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_0;
			}
			if (null != key) {
				buffer.append("{\"aps\":{\"sound\":\"default\",\"badge\":\"1\"},\"action\":\"notice\",\"actionarg\":\"").append(msg).append("\"}");
				DataContainerPool.pushManageContainer.putOrReplace(key, buffer.toString());
			}
		} else if (gender == 1) {
			if ("AOLAI".equals(platform)) {
				key = Constants.PUSH_BROADCAST_ANDROID_AOLAI_AUTO_1;
			} else if ("SHANGPIN".equals(platform)) {
				key = Constants.PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_1;
			}
			if (null != key) {
				buffer.append("{\"aps\":{\"sound\":\"default\",\"badge\":\"1\"},\"action\":\"notice\",\"actionarg\":\"").append(msg).append("\"}");
				DataContainerPool.pushManageContainer.putOrReplace(key, buffer.toString());
			}
		} else {
			if ("AOLAI".equals(platform)) {
				key = Constants.PUSH_BROADCAST_ANDROID_AOLAI_AUTO_2;
			} else if ("SHANGPIN".equals(platform)) {
				key = Constants.PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_2;
			}
			if (null != key) {
				buffer.append("{\"aps\":{\"sound\":\"default\",\"badge\":\"1\"},\"action\":\"notice\",\"actionarg\":\"").append(msg).append("\"}");
				DataContainerPool.pushManageContainer.putOrReplace(key, buffer.toString());
			}
		}
	}

	private static PushManageService getPushManageService() {
		ApplicationContext ac = ApplicationContextUtil.get("ac");
		return (PushManageService) ac.getBean(PushManageService.SERVICE_NAME);
	}
}
