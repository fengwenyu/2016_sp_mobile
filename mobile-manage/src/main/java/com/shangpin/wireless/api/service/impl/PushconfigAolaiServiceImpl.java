package com.shangpin.wireless.api.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.service.PushconfigAolaiService;
import com.shangpin.wireless.api.service.PushconfigShangpinService;
import com.shangpin.wireless.dao.PushconfigAolaiDao;
import com.shangpin.wireless.domain.PushconfigAolai;
import com.shangpin.wireless.util.HqlHelper;
import com.shangpin.wireless.util.StringUtils;

/**
 * 
 * @Author  zhouyu
 * @CreatDate  2012-07-16
 */
@SuppressWarnings("unchecked")
@Service(PushconfigAolaiService.SERVICE_NAME)
public class PushconfigAolaiServiceImpl implements PushconfigAolaiService {
	@Resource(name = PushconfigAolaiDao.DAO_NAME)
	private PushconfigAolaiDao pushconfigAolaiDao;
	@Resource(name = PushconfigShangpinService.SERVICE_NAME)
	private PushconfigShangpinService pushconfigShangpinService;

	@SuppressWarnings("rawtypes")
	public List executeHql(String hql) throws Exception {
		return pushconfigAolaiDao.executeHql(hql);
	}
	

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
	public void save(PushconfigAolai model) throws Exception {
		pushconfigAolaiDao.save(model);
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
	public PushconfigAolai getById(Long id) throws Exception {
		return pushconfigAolaiDao.getById(id);
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
	public PushconfigAolai getByCondition(HqlHelper hqlHelper) throws Exception {
		return pushconfigAolaiDao.getByCondition(hqlHelper);
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
	public void update(PushconfigAolai model) throws Exception {
		pushconfigAolaiDao.update(model);
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
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigAolai WHERE msgType = 0 AND isOpen = 1 group by token";
			break;
		case 1:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigAolai WHERE msgType = 1 AND isOpen = 1 group by token";
			break;
		case 2:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigAolai WHERE msgType = 2 AND isOpen = 1 group by token";
			break;
		default:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigAolai WHERE isOpen = 1 group by token";
			break;
		}
		return pushconfigAolaiDao.executeHql(hql);
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
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigAolai WHERE (msgType = 0 OR msgType=2) AND isOpen = 1 group by token";
			break;
		case 1:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigAolai WHERE (msgType = 1 OR msgType=2) AND isOpen = 1 group by token";
			break;
		default:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigAolai WHERE isOpen = 1 group by token";
			break;
		}
		return pushconfigAolaiDao.executeHql(hql);
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
	public List<PushconfigAolai> getListByCondition(HqlHelper hqlHelper) throws Exception {
		return pushconfigAolaiDao.getListByCondition(hqlHelper);
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
			if (!StringUtils.isNotEmpty(userId)) {
				// userId为空，查询token是否存在，如果存在则不insert，否则保存。
				hqlHelper.addWhereCondition("p.token=?", token);
				List<PushconfigAolai> modelList = getListByCondition(hqlHelper);
				if (modelList == null || modelList.size() == 0) {
					PushconfigAolai pushconfigAolai = new PushconfigAolai();
					pushconfigAolai.setImei(imei);
					pushconfigAolai.setToken(token);
					pushconfigAolai.setIsOpen(1);
					pushconfigAolai.setMsgType(2);
					pushconfigAolai.setUpdateTime(date);
					pushconfigAolai.setCreateTime(date);
					save(pushconfigAolai);
				}
			} else {
				// userId不为空，查找该userId是否存在，如果存在update token，如果不存在先判断是否存在token，如果token存在补全该token记录，如果token也不存在则save
				hqlHelper = new HqlHelper(PushconfigAolai.class, "p");
				hqlHelper.addWhereCondition("p.userId=?", userId);
				PushconfigAolai model = getByCondition(hqlHelper);
				if (model != null) {
					model.setGender(gender);
					model.setImei(imei);
					model.setToken(token);
					model.setUpdateTime(date);
					update(model);
				} else {
					hqlHelper = new HqlHelper(PushconfigAolai.class, "p");
					hqlHelper.addWhereCondition("p.token=?", token);
					List<PushconfigAolai> model1List = getListByCondition(hqlHelper);
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
						save(pushconfigAolai);
					} else if (model1List != null && model1List.size() > 0) {
						int nullIndex = -1;
						for (int i = model1List.size() - 1; i >= 0; i--) {
							PushconfigAolai aolai = model1List.get(i);
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
							PushconfigAolai pushconfigAolai = new PushconfigAolai();
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
			PushconfigAolai model = getByCondition(hqlHelper);
			if (model != null) {
				model.setImei(imei);
				model.setToken(token);
				model.setIsOpen(isOpen);
				model.setMsgType(msgType);
				model.setGender(gender.trim());
				model.setUpdateTime(date);
				update(model);
			} else {
				hqlHelper = new HqlHelper(PushconfigAolai.class, "p");
				hqlHelper.addWhereCondition("p.token=?", token);
				List<PushconfigAolai> modelTokenList = getListByCondition(hqlHelper);
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
							update(aolai);
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
						save(pushconfigAolai);
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
					save(pushconfigAolai);
				}
			}
		}
	}
	
	@Override
	public List<String> getToken(int flag, int start, int size) throws Exception {
		long startTime = new Date().getTime();
		String hql = "";
		// flag：0接收女士特卖信息1接收男士特卖信息2全部接收
		switch (flag) {
		case 0:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigAolai WHERE (msgType = 0 OR msgType=2) AND isOpen = 1 group by token  order by id";
			break;
		case 1:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigAolai WHERE (msgType = 1 OR msgType=2) AND isOpen = 1 group by token  order by id";
			break;
		default:
			hql = "SELECT token FROM com.shangpin.wireless.domain.PushconfigAolai WHERE isOpen = 1 group by token order by id";
			break;
		}
		List<String> tokens = pushconfigAolaiDao.executeHql(hql, start, size);
		long endTime = new Date().getTime();
        System.out.println("get aolai token end , cost time:" + (endTime - startTime) + "ms");
		return tokens;
	}
	
	public void deleteByCondition(String condition) throws Exception{
		pushconfigAolaiDao.deleteByCondition(condition);
	}


	@Override
	public void sendPushMsg() throws Exception {
		
	}
	
}
