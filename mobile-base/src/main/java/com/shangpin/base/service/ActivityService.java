package com.shangpin.base.service;

/**
 * 活动接口
 * 
 * @author zghw
 *
 */
public interface ActivityService {
	/**
	 * 限时购520会场
	 * 
	 * @return
	 * @author zghw
	 */
	public String findTimeLimitBuy(String meetId);
	/**
	 * 
	 * @param activityId
	 * @param userId
	 * @param verifyCode 验证码
	 * @return
	 */
	public String findActivityLottery(String activityId, String userId,String verifyCode);
	/**
	 * 中奖名单
	 * @param activityId 活动ID
	 * @return
	 * @author zghw
	 */
	public String findWinners(String activityId,String pageIndex,String pageSize);
	
	/**
	 * 根据用户id抽奖
	 * @param userId
	 * @param activityId
	 * @return String
	 */
    public String findFollowerLottery(String userId, String activityId);
}
