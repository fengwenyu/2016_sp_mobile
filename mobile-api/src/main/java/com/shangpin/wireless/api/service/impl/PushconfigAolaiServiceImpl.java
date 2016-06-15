package com.shangpin.wireless.api.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.PushconfigAolaiDao;
import com.shangpin.wireless.api.domain.PushconfigAolai;
import com.shangpin.wireless.api.service.PushconfigAolaiService;
import com.shangpin.wireless.api.service.PushconfigShangpinService;
import com.shangpin.wireless.api.util.DBType;
import com.shangpin.wireless.api.util.HqlHelper;
import com.shangpin.wireless.api.util.StringUtil;

/**
 * push设置
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-09-14
 */
@Service(PushconfigAolaiService.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class PushconfigAolaiServiceImpl implements PushconfigAolaiService {
	@Resource(name = PushconfigAolaiDao.DAO_NAME)
	private PushconfigAolaiDao pushconfigAolaiDao;
	@Resource(name = PushconfigShangpinService.SERVICE_NAME)
	private PushconfigShangpinService pushconfigShangpinService;

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
	public void save(PushconfigAolai model, String dbType) throws Exception {
		pushconfigAolaiDao.save(model, dbType);
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
	public PushconfigAolai getById(Long id, String dbType) throws Exception {
		return pushconfigAolaiDao.getById(id, dbType);
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
	public PushconfigAolai getByCondition(HqlHelper hqlHelper, String dbType) throws Exception {
		return pushconfigAolaiDao.getByCondition(hqlHelper, dbType);
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
	public void update(PushconfigAolai model, String dbType) throws Exception {
		pushconfigAolaiDao.update(model, dbType);
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
			hql = "SELECT token FROM PushconfigAolai WHERE msgType = 0 AND isOpen = 1 group by token";
			break;
		case 1:
			hql = "SELECT token FROM PushconfigAolai WHERE msgType = 1 AND isOpen = 1 group by token";
			break;
		case 2:
			hql = "SELECT token FROM PushconfigAolai WHERE msgType = 2 AND isOpen = 1 group by token";
			break;
		default:
			hql = "SELECT token FROM PushconfigAolai WHERE isOpen = 1 group by token";
			break;
		}
		return pushconfigAolaiDao.executeHql(hql, DBType.dataSourceAPI.toString());
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
			hql = "SELECT token FROM PushconfigAolai WHERE (msgType = 0 OR msgType=2) AND isOpen = 1 group by token";
			break;
		case 1:
			hql = "SELECT token FROM PushconfigAolai WHERE (msgType = 1 OR msgType=2) AND isOpen = 1 group by token";
			break;
		default:
			hql = "SELECT token FROM PushconfigAolai WHERE isOpen = 1 group by token";
			break;
		}
		return pushconfigAolaiDao.executeHql(hql, DBType.dataSourceAPI.toString());
	}

	public void sendPushMsg() throws Exception {
//		// push 密钥库
//		String devKeyStore = "/home/api/apple_Development_push_services.p12";
//		String proKeyStore = "/home/api/apple_Production_push_services.p12";
//		// 密钥库密码
//		String password = "1";
//		// 生产or测试，true为生产
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		long small = sdf.parse(DateUtil.getAfterAmountDay(new Date(), 0, "yyyy-MM-dd")).getTime();
//		long big = sdf.parse(DateUtil.getAfterAmountDay(new Date(), 1, "yyyy-MM-dd")).getTime();
//
//		String activityWoman1 = "";
//		String activityMan1 = "";
//		PushNotificationPayload payLoad = new PushNotificationPayload();
//		String activityUrl = Constants.BASE_URL + "TopicList/";
//		Map<String, String> activityMap = new HashMap<String, String>();
//		activityMap.put("picw", "640");
//		activityMap.put("pich", "640");
//		// -----------------推送女士活动----------------
//		activityMap.put("gender", "0");
//		String activityWoman = WebUtil.readContentFromGet(activityUrl, activityMap);
//		TopicAndActivityServerResponse topicAndActivityServerResponse = new TopicAndActivityServerResponse();
//		topicAndActivityServerResponse.json2Obj(activityWoman);
//		JSONArray daysWoman = topicAndActivityServerResponse.getContent();
//		if (daysWoman != null && daysWoman.size() > 0) {
//			// 随机取专题描述信息，超出专题列表总数量的2倍，还没取到不为空的描述信息，将不执行push
//			for (int i = daysWoman.size() * 3; i > 0; i--) {
//				final int index = (int) (Math.random() * daysWoman.size());
//				JSONObject activitieWoman = (JSONObject) daysWoman.get(index);
//				String str = activitieWoman.getString("desc").trim();
//				String startDate = activitieWoman.getString("starttime");
//				long starttime = Long.valueOf(startDate);
//				if (StringUtil.isNotEmpty(str) && (small <= starttime) && (starttime < big)) {
//					activityWoman1 = str;
//					break;
//				} else {
//					daysWoman.remove(index);
//					if (daysWoman.size() == 0) {
//						break;
//					}
//				}
//			}
//			if (StringUtil.isNotEmpty(activityWoman1)) {
//				payLoad.addBadge(1);// 应用图标上小红圈上的数值
//				payLoad.addSound("default");// 铃音
//				String pushmsg = activityWoman1 = "☆" + activityWoman1;
////				pushconfigShangpinService.saveTopicPushInfo(pushmsg, 101L, 0);
////				List<String> woman = executeHql(0);
////				if (woman != null && woman.size() > 0) {
////					payLoad.addAlert(pushmsg);// push的内容
////					System.out.println(pushmsg);
////					PushUtil.payload(payLoad, Constants.PRODUCTION ? proKeyStore : devKeyStore, password, Constants.PRODUCTION, 3, woman);
////				}
//			}
//		}
//		// -----------------推送男士活动----------------
//		activityMap.put("gender", "1");
//		String activityMan = WebUtil.readContentFromGet(activityUrl, activityMap);
//		topicAndActivityServerResponse = new TopicAndActivityServerResponse();
//		topicAndActivityServerResponse.json2Obj(activityMan);
//		JSONArray daysMan = topicAndActivityServerResponse.getContent();
//		if (daysMan != null && daysMan.size() > 0) {
//			for (int i = daysMan.size() * 3; i > 0; i--) {
//				final int index = (int) (Math.random() * daysMan.size());
//				JSONObject activitieMan = (JSONObject) daysMan.get(index);
//				String str = activitieMan.getString("desc").trim();
//				String startDate = activitieMan.getString("starttime");
//				long starttime = Long.valueOf(startDate);
//				if (StringUtil.isNotEmpty(str) && (small <= starttime) && (starttime < big) && !str.equals(activityWoman1)) {
//					activityMan1 = str;
//					break;
//				} else {
//					daysMan.remove(index);
//					if (daysMan.size() == 0) {
//						break;
//					}
//				}
//			}
//			if (StringUtil.isNotEmpty(activityMan1)) {
//				payLoad.addBadge(1);// 应用图标上小红圈上的数值
//				payLoad.addSound("default");// 铃音
//				String pushmsg = activityMan1 = "☆" + activityMan1;
////				pushconfigShangpinService.saveTopicPushInfo(pushmsg, 101L, 1);
////				List<String> man = executeHql(1);
////				if (man != null && man.size() > 0) {
////					payLoad.addAlert(pushmsg);// push的内容
////					System.out.println(pushmsg);
////					PushUtil.payload(payLoad, Constants.PRODUCTION ? proKeyStore : devKeyStore, password, Constants.PRODUCTION, 3, man);
////				}
//			}
//		}
//		// -----------------推送全部活动----------------
//		String pushmsg = null;
//		if (!"".equals(activityWoman1)) {
//			if (!"".equals(activityMan1)) {
//				pushmsg = activityWoman1 + "\n" + activityMan1;
//			} else {
//				pushmsg = activityWoman1;
//			}
//		} else if (!"".equals(activityMan1)) {
//			pushmsg = activityMan1;
//		}
//		if (StringUtils.isNotEmpty(pushmsg)) {
////			pushconfigShangpinService.saveTopicPushInfo(pushmsg, 101L, 2);
//			// :参数3为发送所有设置接受push的用户
////			List<String> all = executeHql(3);
////			if (all != null && all.size() > 0) {
////				payLoad.addAlert(pushmsg);// push的内容
////				payLoad.addBadge(1);// 应用图标上小红圈上的数值
////				payLoad.addSound("default");// 铃音
////				PushUtil.payload(payLoad, Constants.PRODUCTION ? proKeyStore : devKeyStore, password, Constants.PRODUCTION, 4, all);
////				System.out.println("all aolai " + activityWoman1 + "；" + activityMan1);
////			}
//		}
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
	public List<PushconfigAolai> getListByCondition(HqlHelper hqlHelper, String dbType) throws Exception {
		return pushconfigAolaiDao.getListByCondition(hqlHelper, dbType);
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
			HqlHelper hqlHelper = new HqlHelper(PushconfigAolai.class, "p");
			if (!StringUtil.isNotEmpty(userId)) {
				// userId为空，查询token是否存在，如果存在则不insert，否则保存。
				hqlHelper.addWhereCondition("p.token=?", token);
				List<PushconfigAolai> modelList = getListByCondition(hqlHelper, DBType.dataSourceAPI.toString());
				if (modelList == null || modelList.size() == 0) {
					PushconfigAolai pushconfigAolai = new PushconfigAolai();
					pushconfigAolai.setImei(imei);
					pushconfigAolai.setToken(token);
					pushconfigAolai.setIsOpen(1);
					pushconfigAolai.setMsgType(2);
					pushconfigAolai.setUpdateTime(date);
					pushconfigAolai.setCreateTime(date);
					save(pushconfigAolai, DBType.dataSourceAPI.toString());
				}
			} else {
				// userId不为空，查找该userId是否存在，如果存在update token，如果不存在先判断是否存在token，如果token存在补全该token记录，如果token也不存在则save
				hqlHelper = new HqlHelper(PushconfigAolai.class, "p");
				hqlHelper.addWhereCondition("p.userId=?", userId);
				PushconfigAolai model = getByCondition(hqlHelper, DBType.dataSourceAPI.toString());
				if (model != null) {
					model.setGender(gender);
					model.setImei(imei);
					model.setToken(token);
					model.setUpdateTime(date);
					update(model, DBType.dataSourceAPI.toString());
				} else {
					hqlHelper = new HqlHelper(PushconfigAolai.class, "p");
					hqlHelper.addWhereCondition("p.token=?", token);
					List<PushconfigAolai> model1List = getListByCondition(hqlHelper, DBType.dataSourceAPI.toString());
					if (model1List == null || model1List.size() == 0) {
						PushconfigAolai pushconfigAolai = new PushconfigAolai();
						pushconfigAolai.setGender(gender);
						pushconfigAolai.setUserId(userId);
						pushconfigAolai.setImei(imei);
						pushconfigAolai.setToken(token);
						pushconfigAolai.setIsOpen(1);
						pushconfigAolai.setMsgType(2);
						pushconfigAolai.setUpdateTime(date);
						pushconfigAolai.setCreateTime(date);
						save(pushconfigAolai, DBType.dataSourceAPI.toString());
					} else if (model1List != null && model1List.size() > 0) {
						int nullIndex = -1;
						for (int i = model1List.size() - 1; i >= 0; i--) {
							PushconfigAolai aolai = model1List.get(i);
							if (aolai.getUserId() == null) {
								nullIndex = i;
								aolai.setUserId(userId);
								aolai.setGender(gender);
								aolai.setUpdateTime(date);
								update(aolai, DBType.dataSourceAPI.toString());
								break;
							}
						}
						if (nullIndex == -1) {
							PushconfigAolai pushconfigAolai = new PushconfigAolai();
							pushconfigAolai.setGender(gender);
							pushconfigAolai.setUserId(userId);
							pushconfigAolai.setImei(imei);
							pushconfigAolai.setToken(token);
							pushconfigAolai.setIsOpen(1);
							pushconfigAolai.setMsgType(2);
							pushconfigAolai.setUpdateTime(date);
							pushconfigAolai.setCreateTime(date);
							save(pushconfigAolai, DBType.dataSourceAPI.toString());
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
			HqlHelper hqlHelper = new HqlHelper(PushconfigAolai.class, "p");
			hqlHelper.addWhereCondition("p.userId=?", userId);
			PushconfigAolai model = getByCondition(hqlHelper, DBType.dataSourceAPI.toString());
			if (StringUtils.isNotEmpty(userId) && model != null) {
				model.setImei(imei);
				model.setToken(token);
				model.setIsOpen(isOpen);
				model.setMsgType(msgType);
				model.setGender(gender.trim());
				model.setUpdateTime(date);
				update(model, DBType.dataSourceAPI.toString());
			} else {
				hqlHelper = new HqlHelper(PushconfigAolai.class, "p");
				hqlHelper.addWhereCondition("p.token=?", token);
				List<PushconfigAolai> modelTokenList = getListByCondition(hqlHelper, DBType.dataSourceAPI.toString());
				if (modelTokenList != null && modelTokenList.size() > 0) {
					int nullIndex = -1;
					for (int i = modelTokenList.size() - 1; i >= 0; i--) {
						PushconfigAolai aolai = modelTokenList.get(i);
						if (aolai.getUserId() == null) {
							nullIndex = i;
							aolai.setUserId(userId);
							aolai.setGender(gender);
							aolai.setIsOpen(isOpen);
							aolai.setMsgType(msgType);
							aolai.setUpdateTime(date);
							update(aolai, DBType.dataSourceAPI.toString());
							break;
						}
					}
					if (nullIndex == -1) {
						PushconfigAolai pushconfigAolai = new PushconfigAolai();
						pushconfigAolai.setGender(gender);
						pushconfigAolai.setUserId(userId);
						pushconfigAolai.setImei(imei);
						pushconfigAolai.setToken(token);
						pushconfigAolai.setIsOpen(1);
						pushconfigAolai.setMsgType(2);
						pushconfigAolai.setUpdateTime(date);
						pushconfigAolai.setCreateTime(date);
						save(pushconfigAolai, DBType.dataSourceAPI.toString());
					}
				} else {
					PushconfigAolai pushconfigAolai = new PushconfigAolai();
					pushconfigAolai.setUserId(userId);
					pushconfigAolai.setImei(imei);
					pushconfigAolai.setToken(token);
					pushconfigAolai.setIsOpen(isOpen);
					pushconfigAolai.setMsgType(msgType);
					pushconfigAolai.setGender(gender.trim());
					pushconfigAolai.setCreateTime(date);
					pushconfigAolai.setUpdateTime(date);
					save(pushconfigAolai, DBType.dataSourceAPI.toString());
				}
			}
		}
	}
}
