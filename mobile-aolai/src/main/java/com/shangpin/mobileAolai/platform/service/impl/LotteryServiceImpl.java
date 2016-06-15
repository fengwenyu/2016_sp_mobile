package com.shangpin.mobileAolai.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.mobileAolai.base.model.Lottery;
import com.shangpin.mobileAolai.platform.dao.LotteryDAO;
import com.shangpin.mobileAolai.platform.service.LotteryService;

/**
 * 抽奖信息管理实现类
 * 
 * @author wangfeng
 * @CreatDate 2013-12-10
 */
@Service("lotteryService")
// @Transactional
@SuppressWarnings({ "unchecked" })
public class LotteryServiceImpl implements LotteryService {
	@Autowired
	private LotteryDAO lotteryDAO;

	@Override
	public void addLottery(Lottery lottery) throws Exception {
		lotteryDAO.saveOrUpdate(lottery);
	}

//	/**
//	 * 根据等级查询数量
//	 * 
//	 * @param winningLevel
//	 *           用户等级
//	 * 
//	 * @return 数量
//	 */
//	@Override
//	public List<Lottery> findLotteryByLevel(String winningLevel) {
//		List<Lottery> lotteryList = lotteryDAO.find("FROM Lottery WHERE winninglevel ='" + winningLevel + "'");		
//		return lotteryList;
//	}
	
//	/**
//	 * 根据userid数量
//	 * 
//	 * @param userId
//	 *           用户id
//	 * 
//	 * @return lottery
//	 */
//	@Override
//	public List<Lottery> findLotteryByUserid(String userId) {
//		List<Lottery> lotteryList = lotteryDAO.find("FROM Lottery WHERE DATE_FORMAT(createTime,'%Y-%m-%d')=DATE_FORMAT(NOW(),'%Y-%m-%d') and userid ='" + userId + "'");		
//		return lotteryList;
//	}
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
	@Override
	public List<Lottery> findLotteryByUidAndAid(String userid, String activeid) {
		List<Lottery> lotteryList = lotteryDAO.find("FROM Lottery WHERE DATE_FORMAT(createTime,'%Y-%m-%d')=DATE_FORMAT(NOW(),'%Y-%m-%d') and userid ='" + userid + "' and activeid='"+activeid+"'");		
		return lotteryList;
	}
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
	@Override
	public List<Lottery> findLotteryByLevelAndAcid(String level, String activeid) {
		List<Lottery> lotteryList = lotteryDAO.find("FROM Lottery WHERE winninglevel ='" + level + "' and activeid='"+activeid+"' ");		
		return lotteryList;
	}
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
	@Override
	public List<Lottery> findLotteryByLevelAndAcidToday(String level, String activeid){
		List<Lottery> lotteryList = lotteryDAO.find("FROM Lottery WHERE DATE_FORMAT(createTime,'%Y-%m-%d')=DATE_FORMAT(NOW(),'%Y-%m-%d') and winninglevel ='" + level + "' and activeid='"+activeid+"' ");		
		return lotteryList;
	}

	@Override
	public List<Lottery> findLotteryListByAcid(String activeid) {
		List<Lottery> lotteryList = lotteryDAO.find("FROM Lottery WHERE winningname !='未中奖' and activeid='"+activeid+"' order by  createtime desc limit 1,100");		
		return lotteryList;
	}

	@Override
	public List<Lottery> findLotteryByUidAndAidAndLevel(String level,
			String userid, String activeid) {
		List<Lottery> lotteryList = lotteryDAO.find("FROM Lottery WHERE winninglevel ='" + level + "' and activeid='"+activeid+"' and userid='"+userid+"'");		
		return lotteryList;
	}

	@Override
	public List<Lottery> findLotteryListByUserId(String userId, String activeid) {
		List<Lottery> lotteryList = lotteryDAO.find("FROM Lottery WHERE userId ='"+userId+"' and activeid='"+activeid+"' and (winningLevel='1' or winningLevel='2' or winningLevel='3' or winningLevel='4') order by  createTime desc");		
		return lotteryList;
	}

	@Override
	public List<Lottery> findDayLotteryListByUserId(String userId, String activeid) {
		String sql = "from Lottery where userId ='"+userId+"' and activeId='"+activeid+"' and (winningLevel='1' or winningLevel='2' or winningLevel='3' or winningLevel='4') group by reserve1,reserve2,reserve3,reserve4";
		
		List<Lottery> lotteryList = lotteryDAO.find(sql);		
		return lotteryList;
	}
}