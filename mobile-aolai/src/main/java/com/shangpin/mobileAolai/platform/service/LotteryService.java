package com.shangpin.mobileAolai.platform.service;

import java.util.List;

import com.shangpin.mobileAolai.base.model.Lottery;
/**
 * 抽奖信息管理业务逻辑接口
 * 
 * @author wangfeng
 * @CreatDate 2013-12-10
 */
public interface LotteryService {
	
	/**
	 * 保存抽奖信息
	 * 
	 * @param lottery
	 *            抽奖信息对象
	 * 
	 * 
	 */
	public void addLottery(Lottery lottery) throws Exception;
	
	
//	/**
//	 * 根据等级查询数量
//	 * 
//	 * @param winningLevel
//	 *           用户等级
//	 * 
//	 * @return list
//	 */
//	public List<Lottery> findLotteryByLevel(String winningLevel);
	
//	/**
//	 * 根据userid数量
//	 * 
//	 * @param userId
//	 *           用户id
//	 * 
//	 * @return lottery
//	 */
//	public List<Lottery> findLotteryByUserid(String userId);

	/**
	 * 获取activeid活动中，userid今天抽奖的记录
	 * 
	 * @param userId
	 *           用户id
	 *           
	 *  * @param activeId
	 *           活动activeid
	 * 
	 * @return lottery
	 */
	public List<Lottery> findLotteryByUidAndAidAndLevel(String level,String userid, String activeid);

	/**
	 * 获取activeid活动中，userid今天抽奖的记录
	 * 
	 * @param userId
	 *           用户id
	 *           
	 *  * @param activeId
	 *           活动activeid
	 * 
	 * @return lottery
	 */
	public List<Lottery> findLotteryByUidAndAid(String userid, String activeid);
	/**
	 * 获取本次活动中level级别奖的个数
	 * 
	 * @param level
	 *           奖品等级
	 *           
	 *  * @param activeId
	 *           活动activeid
	 * 
	 * @return lottery
	 */
	public List<Lottery> findLotteryByLevelAndAcid(String level, String activeid);
	/**
	 * 获取本次活动今天中level级别奖的个数
	 * 
	 * @param level
	 *           奖品等级
	 *           
	 *  * @param activeId
	 *           活动activeid
	 * 
	 * @return lottery
	 */
	public List<Lottery> findLotteryByLevelAndAcidToday(String level, String activeid);

	/**
	 * 根据activeid
	 * 
	 * @param activeid
	 *            活动id
	 *           
	 *  
	 * 
	 * @return lotteryLIST
	 */
	public List<Lottery> findLotteryListByAcid(String activeid);
	
	/**
	 * 根据活动id和用户id查询中奖情况
	 * @param activeid  活动id
	 * @param userId    用户id
	 * @return
	 */
	public List<Lottery> findLotteryListByUserId(String userId, String activeid);
	
	/**
	 * 根据活动id和用户id查询当天中奖情况
	 * @param activeid
	 * @param userId
	 * @return
	 */
	public List<Lottery> findDayLotteryListByUserId(String userId, String activeid);

}
