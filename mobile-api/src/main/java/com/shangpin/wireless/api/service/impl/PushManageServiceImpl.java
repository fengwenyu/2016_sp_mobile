package com.shangpin.wireless.api.service.impl;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.PushManageAndroidDao;
import com.shangpin.wireless.api.dao.PushManageIOSDao;
import com.shangpin.wireless.api.dao.PushconfigAolaiDao;
import com.shangpin.wireless.api.dao.PushconfigShangpinDao;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.PushManageAndroid;
import com.shangpin.wireless.api.service.PushManageService;
import com.shangpin.wireless.api.service.PushconfigAolaiService;
import com.shangpin.wireless.api.service.PushconfigShangpinService;
import com.shangpin.wireless.api.util.DBType;
import com.shangpin.wireless.api.util.DataContainerPool;

/**
 * push业务逻辑层接口实现类，提供，android、osi等平台push信息内容
 * 
 * @Author yumeng
 * @CreateDate: 2013-02-22
 */
@Service(PushManageService.SERVICE_NAME)
public class PushManageServiceImpl implements PushManageService {
	@Resource(name = PushManageAndroidDao.DAO_NAME)
	private PushManageAndroidDao pushManageAndroidDao;
	@Resource(name = PushManageIOSDao.DAO_NAME)
	private PushManageIOSDao pushManageIOSDao;
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

	@Override
	public void updateAndSendIOSPushInfo(int gender, BigInteger productNum) throws Exception {
//		List<PushManageIos> list = pushManageIOSDao.findPushManageIOS(gender, productNum);
//		// push 密钥库
//		String keystore = "";
//		// keystore = "E:\\apple push servces.p12";
//		// 密钥库密码
//		String password = "1";
//		// 生产or测试，true为生产
//		if (null != list && list.size() > 0) {
//			List<String> token = null;
//			if (null != productNum && productNum.intValue() == 1) {
//				token = pushconfigAolaiService.getToken(gender);
//				if(Constants.PRODUCTION) {
//					keystore = "/home/api/apple_Production_push_services.p12";
//				} else {
//					keystore = "/home/api/apple_Development_push_services.p12";
//				}
//				password = "1";
//			} else if (null != productNum && productNum.intValue() == 2) {
//				token = pushconfigShangpinService.getToken(gender);
//				keystore = "/home/api/shangpinapp push.p12";
//				password = "1";
//			}
//			if (null != token && token.size() > 0) {
//				for (PushManageIos pushManageIos : list) {
//					// showtime字段为空或小于当前时间时发送push信息
//					if (pushManageIos.getShowTime() == null || pushManageIos.getShowTime().getTime() <= new Date().getTime()) {
////						PushNotificationPayload payLoad = new PushNotificationPayload(pushManageIos.getPushContent());
//						// payLoad.addBadge(1);// 应用图标上小红圈上的数值
//						// payLoad.addSound("default");// 铃音
//						// payLoad.addAlert(pushManageIos.getPushContent());
////						PushUtil.payload(payLoad, keystore, password, Constants.PRODUCTION, 5, token);
////						pushManageIOSDao.updatePushManageIOSType(pushManageIos.getId());
//						System.out.println("======>" + keystore + "===msg===>" + pushManageIos.getPushContent());
//					}
//				}
//			}
//		}
	}

	@Override
	public void updateAndSendIOSPersonPushInfo(BigInteger productNum) throws Exception {
//		List<PushManageIos> list = pushManageIOSDao.findPushManageIOSByUser(productNum);
//		// push 密钥库
//		String keystore = "";
//		// keystore = "E:\\apple push servces.p12";
//		// 密钥库密码
//		String password = "1";
//		// 生产or测试，true为生产
//		if (null != list && list.size() > 0) {
//			if (null != productNum && productNum.intValue() == 1) {
//				if(Constants.PRODUCTION) {
//					keystore = "/home/api/apple_Production_push_services.p12";
//				} else {
//					keystore = "/home/api/apple_Development_push_services.p12";
//				}
//				password = "1";
//			} else if (null != productNum && productNum.intValue() == 2) {
//				keystore = "/home/api/shangpinapp push.p12";
//				password = "1";
//			}
//			for (PushManageIos pushManageIos : list) {
//				// showtime字段为空或小于当前时间时发送push信息
//				if (null != pushManageIos && StringUtils.isNotEmpty(pushManageIos.getUserId()) && (pushManageIos.getShowTime() == null || pushManageIos.getShowTime().getTime() <= new Date().getTime())) {
//					String token = executeHql(productNum, pushManageIos.getUserId());
//					if (StringUtils.isNotEmpty(token)) {
////						PushNotificationPayload payLoad = new PushNotificationPayload(pushManageIos.getPushContent());
//						// payLoad.addBadge(1);// 应用图标上小红圈上的数值
//						// payLoad.addSound("default");// 铃音
////						PushUtil.payload(payLoad, keystore, password, Constants.PRODUCTION, 1, token);
////						pushManageIOSDao.updatePushManageIOSType(pushManageIos.getId());
//						System.out.println(list.size() + "push person info======>" + keystore + "===token===>" + token + "===userId===>" + pushManageIos.getUserId() + "===msg===>" + pushManageIos.getPushContent());
//					}
//				}
//			}
//		}
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
			List<String> list = pushconfigAolaiDao.executeHql(hql, DBType.dataSourceAPI.toString());
			return (null != list && list.size() > 0) ? list.get(0) : null;
		} else if (productNum.intValue() == 2) {
			hql = "SELECT token FROM PushconfigShangpin WHERE userId = '" + userId + "'";
			List<String> list = pushconfigShangpinDao.executeHql(hql, DBType.dataSourceAPI.toString());
			return (null != list && list.size() > 0) ? list.get(0) : null;
		} else {
			return null;
		}
	}
}
