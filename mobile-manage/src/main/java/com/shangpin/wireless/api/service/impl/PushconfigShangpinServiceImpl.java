package com.shangpin.wireless.api.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.service.PushconfigShangpinService;
import com.shangpin.wireless.dao.PushManageAndroidDao;
import com.shangpin.wireless.dao.PushconfigShangpinDao;
import com.shangpin.wireless.domain.PushManageAndroid;
import com.shangpin.wireless.domain.PushconfigShangpin;
import com.shangpin.wireless.util.HqlHelper;
import com.shangpin.wireless.util.StringUtils;

/**
 * push设置
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-09-14
 */
@Service(PushconfigShangpinService.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class PushconfigShangpinServiceImpl implements PushconfigShangpinService {
	private final Log log = LogFactory.getLog(PushconfigShangpinServiceImpl.class);
	@Resource(name = PushconfigShangpinDao.DAO_NAME)
	private PushconfigShangpinDao pushconfigShangpinDao;
	@Resource(name = PushManageAndroidDao.DAO_NAME)
	private PushManageAndroidDao pushManageAndroidDao;

	/**
	 * 保存实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-14
	 * @param entity
	 * @param dbType
	 *            数据库类型
	 * @throws Exception
	 * @Return:
	 */
	public void save(PushconfigShangpin model) throws Exception {
		pushconfigShangpinDao.save(model);
	}

	/**
	 * 根据ID获取实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-14
	 * @param id
	 * @param dbType
	 *            数据库类型
	 * @throws Exception
	 * @Return:
	 */
	public PushconfigShangpin getById(Long id) throws Exception {
		return pushconfigShangpinDao.getById(id);
	}

	/**
	 * 根据userId查询pushconf_aolai
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-14
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	public PushconfigShangpin getByCondition(HqlHelper hqlHelper) throws Exception {
		return pushconfigShangpinDao.getByCondition(hqlHelper);
	}

	/**
	 * 更新实体
	 * 
	 * @Author: zhouyu
	 * 
	 * @CreateDate: 2012-09-16
	 * @param entity
	 *            实体
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	public void update(PushconfigShangpin model) throws Exception {
		pushconfigShangpinDao.update(model);
	}

	/**
	 * 根据HQL语句获取数据集合
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-17
	 * @param flag
	 *            push消息类型标识
	 * @Return List
	 */
	public List<String> executeHql(int flag) throws Exception {
		String hql = "";
		// flag：0接收女士特卖信息1接收男士特卖信息2全部接收
		switch (flag) {
		case 0:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigShangpin WHERE msgType = 0 AND isOpen = 1 group by token";
			break;
		case 1:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigShangpin WHERE msgType = 1 AND isOpen = 1 group by token";
			break;
		case 2:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigShangpin WHERE msgType = 2 AND isOpen = 1 group by token";
			break;
		default:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigShangpin WHERE isOpen = 1 group by token";
			break;
		}
		return pushconfigShangpinDao.executeHql(hql);
	}

	/**
	 * 根据HQL语句获取token数据集合
	 * 
	 * @param flag
	 *            push消息类型标识
	 * @Return List
	 */
	public List<String> getToken(int flag) throws Exception {
		String hql = "";
		// flag：0接收女士特卖信息1接收男士特卖信息2全部接收
		switch (flag) {
		case 0:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigShangpin WHERE (msgType = 0 OR msgType = 2) AND isOpen = 1 group by token";
			break;
		case 1:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigShangpin WHERE (msgType = 1 OR msgType = 2) AND isOpen = 1 group by token";
			break;
		default:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigShangpin WHERE isOpen = 1 group by token";
			break;
		}
		return pushconfigShangpinDao.executeHql(hql);
	}

	public void sendPushMsg() throws Exception {
//		System.out.println("---- PushconfigShangpinServiceImpl sendPushMsg ----");
//		// push 密钥库
//		String keystore = "/home/pushcertificate/shangpinpush.p12";
//		// 密钥库密码
//		String password = "1";
//		// 生产or测试，true为生产
//		// long startTime = 0L;
//		// long endTime = 0L;
//		String activityWoman1 = "";
//		String activityMan1 = "";
//		PushNotificationPayload payLoad = new PushNotificationPayload();
//		String activityUrl = Constants.BASE_URL_SP_SP + "SPTopicList/";
//		Map<String, String> activityMap = new HashMap<String, String>();
//		activityMap.put("picw", "640");
//		activityMap.put("pich", "640");
//		// -----------------推送女士活动----------------
//		activityMap.put("gender", "0");
//		String activityWoman = WebUtil.readContentFromGet(activityUrl, activityMap);
//		CommonServerResponse topicAndActivityServerResponse = new CommonServerResponse();
//		topicAndActivityServerResponse.json2Obj(activityWoman);
//		JSONObject content = topicAndActivityServerResponse.getContent();
//		JSONArray daysWoman = content.getJSONArray("list");
//		if (daysWoman != null && daysWoman.size() > 0) {
//			// 随机取专题描述信息，超出专题列表总数量的2倍，还没取到不为空的描述信息，将不执行push
//			for (int i = 0; i < daysWoman.size() * 2; i++) {
//				JSONObject activitieWoman = (JSONObject) daysWoman.get((int) (Math.random() * daysWoman.size()));
//				String str = activitieWoman.getString("desc");
//				if (StringUtils.isNotEmpty(str)) {
//					activityWoman1 = str;
//					break;
//				}
//			}
//			if (StringUtils.isNotEmpty(activityWoman1) && !"。".equals(activityWoman1)) {
////				List<String> woman = executeHql(0);
////				if (woman != null && woman.size() > 0) {
//					String pushmsg = "☆" + activityWoman1;
//					saveTopicPushInfo(pushmsg, 102L, 0);
//					System.out.println("shangpin Woman " + pushmsg);
////					payLoad.addBadge(1);// 应用图标上小红圈上的数值
////					payLoad.addSound("default");// 铃音
////					payLoad.addAlert(pushmsg);// push的内容
////					PushUtil.payload(payLoad, keystore, password, !Constants.PUSH_TEST, 3, woman);
////				}
//			}
//		}
//		// -----------------推送男士活动----------------
//		activityMap.put("gender", "1");
//		String activityMan = WebUtil.readContentFromGet(activityUrl, activityMap);
//		topicAndActivityServerResponse = new CommonServerResponse();
//		topicAndActivityServerResponse.json2Obj(activityMan);
//		JSONObject manContent = topicAndActivityServerResponse.getContent();
//		JSONArray daysMan = manContent.getJSONArray("list");
//		if (daysMan != null && daysMan.size() > 0) {
//			for (int i = 0; i < daysMan.size() * 2; i++) {
//				JSONObject activitieMan = (JSONObject) daysMan.get((int) (Math.random() * daysMan.size()));
//				String str = activitieMan.getString("desc");
//				if (StringUtils.isNotEmpty(str)) {
//					activityMan1 = str;
//					break;
//				}
//			}
//			if (StringUtils.isNotEmpty(activityMan1) && !"。".equals(activityMan1)) {
////				List<String> man = executeHql(1);
////				if (man != null && man.size() > 0) {
//					String pushmsg = "☆" + activityMan1;
//					saveTopicPushInfo(pushmsg, 102L, 1);
//					System.out.println("shangpin Man " + pushmsg);
////					payLoad = new PushNotificationPayload();
////					payLoad.addBadge(1);// 应用图标上小红圈上的数值
////					payLoad.addSound("default");// 铃音
////					payLoad.addAlert(pushmsg);// push的内容
////					PushUtil.payload(payLoad, keystore, password, !Constants.PUSH_TEST, 3, man);
////				}
//			}
//		}
//		// -----------------推送全部活动----------------
//		String pushmsg = "";
//		if (!"".equals(activityWoman1) && !"".equals(activityMan1) && !"。".equals(activityWoman1) && !"。".equals(activityMan1) && !activityWoman1.equals(activityMan1)) {
//			pushmsg = "☆" + activityWoman1 + "；☆" + activityMan1;
//		} else if (!"".equals(activityWoman1) && !"。".equals(activityWoman1)) {
//			pushmsg = "☆" + activityWoman1;
//		} else if (!"".equals(activityMan1) && !"。".equals(activityMan1)) {
//			pushmsg = "☆" + activityMan1;
//		}
//		if (StringUtils.isNotEmpty(pushmsg)) {
//			saveTopicPushInfo(pushmsg, 102L, 2);
//		}
//		// TODO:参数3为发送所有设置接受push的用户
//		List<String> all = executeHql(3);
//		if (all != null && all.size() > 0) {
//			if (StringUtils.isNotEmpty(pushmsg)) {
//				payLoad.addAlert(pushmsg);// push的内容
//				payLoad.addBadge(1);// 应用图标上小红圈上的数值
//				payLoad.addSound("default");// 铃音
//				Push.payload(payLoad, keystore, password, !Constants.PUSH_TEST, 4, all);
//				System.out.println("all shangpin☆" + activityWoman1 + "；☆" + activityMan1);
//			}
//		}
//		System.out.println("---- PushconfigShangpinServiceImpl sendPushMsg finished ----");
	}

	/**
	 * 根据符合条件查询pushconf_aolai结果集
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-14
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	public List<PushconfigShangpin> getListByCondition(HqlHelper hqlHelper) throws Exception {
		return pushconfigShangpinDao.getListByCondition(hqlHelper);
	}

	/**
	 * 保存Token
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2013-01-16
	 * @param token
	 *            iPhone token
	 * @param userId
	 *            用户id
	 * @param imei
	 *            手机imei
	 * @param gender
	 *            性别
	 * @Return:
	 */
	public void saveToken(String token, String userId, String imei, String gender) throws Exception {
		synchronized (token.intern()) {
			Date date = new Date();
			HqlHelper hqlHelper = new HqlHelper(PushconfigShangpin.class, "p");
			if (!StringUtils.isNotEmpty(userId)) {
				// userId为空，查询token是否存在，如果存在则不insert，否则保存。
				hqlHelper.addWhereCondition("p.token=?", token);
				List<PushconfigShangpin> modelList = getListByCondition(hqlHelper);
				if (modelList == null || modelList.size() == 0) {
					PushconfigShangpin pushconfigShangpin = new PushconfigShangpin();
					pushconfigShangpin.setImei(imei);
					pushconfigShangpin.setToken(token);
					pushconfigShangpin.setIsOpen(1);
					pushconfigShangpin.setMsgType(2);
					pushconfigShangpin.setUpdateTime(date);
					pushconfigShangpin.setCreateTime(date);
					save(pushconfigShangpin);
				}
			} else {
				// userId不为空，查找该userId是否存在，如果存在update token，如果不存在先判断是否存在token，如果token存在补全该token记录，如果token也不存在则save
				hqlHelper = new HqlHelper(PushconfigShangpin.class, "p");
				hqlHelper.addWhereCondition("p.userId=?", userId);
				PushconfigShangpin model = getByCondition(hqlHelper);
				if (model != null) {
					model.setGender(gender);
					model.setImei(imei);
					model.setToken(token);
					model.setUpdateTime(date);
					update(model);
				} else {
					hqlHelper = new HqlHelper(PushconfigShangpin.class, "p");
					hqlHelper.addWhereCondition("p.token=?", token);
					List<PushconfigShangpin> model1List = getListByCondition(hqlHelper);
					if (model1List == null || model1List.size() == 0) {
						PushconfigShangpin pushconfigShangpin = new PushconfigShangpin();
						pushconfigShangpin.setGender(gender);
						pushconfigShangpin.setUserId(userId);
						pushconfigShangpin.setImei(imei);
						pushconfigShangpin.setToken(token);
						pushconfigShangpin.setIsOpen(1);
						pushconfigShangpin.setMsgType(2);
						pushconfigShangpin.setUpdateTime(date);
						pushconfigShangpin.setCreateTime(date);
						save(pushconfigShangpin);
					} else if (model1List != null && model1List.size() > 0) {
						int nullIndex = -1;
						for (int i = model1List.size() - 1; i >= 0; i--) {
							PushconfigShangpin aolai = model1List.get(i);
							if (aolai.getUserId() == null) {
								nullIndex = i;
								aolai.setUserId(userId);
								aolai.setGender(gender);
								aolai.setUpdateTime(date);
								update(aolai);
								break;
							}
						}
						if (nullIndex == -1) {
							PushconfigShangpin pushconfigShangpin = new PushconfigShangpin();
							pushconfigShangpin.setGender(gender);
							pushconfigShangpin.setUserId(userId);
							pushconfigShangpin.setImei(imei);
							pushconfigShangpin.setToken(token);
							pushconfigShangpin.setIsOpen(1);
							pushconfigShangpin.setMsgType(2);
							pushconfigShangpin.setUpdateTime(date);
							pushconfigShangpin.setCreateTime(date);
							save(pushconfigShangpin);
						}
					}
				}
			}
		}
	}

	/**
	 * 保存push设置
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2013-01-16
	 * @param userId
	 *            用户id
	 * @param gender
	 *            性别
	 * @param token
	 *            iPhone token
	 * @param imei
	 *            手机imei
	 * @param isOpen
	 *            是否接收push信息
	 * @param msgType
	 *            接收push的消息类型
	 * @Return:
	 */
	public void savePushConfig(String userId, String gender, String token, String imei, int isOpen, int msgType) throws Exception {
		synchronized (token.intern()) {
			Date date = new Date();
			HqlHelper hqlHelper = new HqlHelper(PushconfigShangpin.class, "p");
			hqlHelper.addWhereCondition("p.userId=?", userId);
			PushconfigShangpin model = getByCondition(hqlHelper);
			if (model != null) {
				model.setImei(imei);
				model.setToken(token);
				model.setIsOpen(isOpen);
				model.setMsgType(msgType);
				model.setGender(gender.trim());
				model.setUpdateTime(date);
				update(model);
			} else {
				hqlHelper = new HqlHelper(PushconfigShangpin.class, "p");
				hqlHelper.addWhereCondition("p.token=?", token);
				List<PushconfigShangpin> modelTokenList = getListByCondition(hqlHelper);
				if (modelTokenList != null && modelTokenList.size() > 0) {
					int nullIndex = -1;
					for (int i = modelTokenList.size() - 1; i >= 0; i--) {
						PushconfigShangpin aolai = modelTokenList.get(i);
						if (aolai.getUserId() == null) {
							nullIndex = i;
							aolai.setUserId(userId);
							aolai.setGender(gender);
							aolai.setIsOpen(isOpen);
							aolai.setMsgType(msgType);
							aolai.setUpdateTime(date);
							update(aolai);
							break;
						}
					}
					if (nullIndex == -1) {
						PushconfigShangpin pushconfigAolai = new PushconfigShangpin();
						pushconfigAolai.setGender(gender);
						pushconfigAolai.setUserId(userId);
						pushconfigAolai.setImei(imei);
						pushconfigAolai.setToken(token);
						pushconfigAolai.setIsOpen(1);
						pushconfigAolai.setMsgType(2);
						pushconfigAolai.setUpdateTime(date);
						pushconfigAolai.setCreateTime(date);
						save(pushconfigAolai);
					}
				} else {
					PushconfigShangpin pushconfigAolai = new PushconfigShangpin();
					pushconfigAolai.setUserId(userId);
					pushconfigAolai.setImei(imei);
					pushconfigAolai.setToken(token);
					pushconfigAolai.setIsOpen(isOpen);
					pushconfigAolai.setMsgType(msgType);
					pushconfigAolai.setGender(gender.trim());
					pushconfigAolai.setCreateTime(date);
					pushconfigAolai.setUpdateTime(date);
					save(pushconfigAolai);
				}
			}
		}
	}

	public void saveTopicPushInfo(String pushmsg, Long num, int msgType) throws Exception {
		PushManageAndroid entity = new PushManageAndroid();
		entity.setPlatform("android");
		entity.setProductNum(num);
		entity.setNotice(pushmsg);
		entity.setMsgType(msgType);
		entity.setPushType(0);
		entity.setUsername("");
		entity.setCreateTime(new Date());
		pushManageAndroidDao.save(entity);
		String str = "{\"id\":\"" + entity.getId() + "\",\"aps\":{\"sound\":\"default\",\"alert\":\"" + pushmsg + "\",\"badge\":\"1\"},\"action\":\"\",\"showtime\":\"\"}";
		entity.setPushContent(str);
		pushManageAndroidDao.update(entity);
		log.info("saveTopicPushInfo--msgType=" + msgType + ";productNum=" + num + ";pushmsg=" + pushmsg);
	}

	@Override
	public List<String> getToken(int flag, int start, int size) throws Exception {
		long startTime = new Date().getTime();
		String hql = "";
		// flag：0接收女士特卖信息1接收男士特卖信息2全部接收
		switch (flag) {
		case 0:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigShangpin WHERE (msgType = 0 OR msgType = 2) AND isOpen = 1 group by token  order by id";
			break;
		case 1:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigShangpin WHERE (msgType = 1 OR msgType = 2) AND isOpen = 1 group by token  order by id";
			break;
		default:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigShangpin WHERE isOpen = 1 group by token  order by id";
			break;
		}
		List<String> tokens = pushconfigShangpinDao.executeHql(hql, start, size);
		long endTime = new Date().getTime();
		System.out.println("get shangpin token end , size is " + tokens.size() + " , cost time:" + (endTime - startTime) + "ms");
		return tokens;
	}

	@Override
	public void deleteByCondition(String condition) throws Exception {
		pushconfigShangpinDao.deleteByCondition(condition);
	}
}
