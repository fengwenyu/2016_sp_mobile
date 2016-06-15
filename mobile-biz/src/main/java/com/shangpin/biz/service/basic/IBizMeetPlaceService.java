package com.shangpin.biz.service.basic;

import com.shangpin.biz.bo.ActivityLottery;
import com.shangpin.biz.bo.Meet;
import com.shangpin.biz.bo.TimeLimitBuy;
import com.shangpin.biz.bo.Winners;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;

/**
 * 会场接口
 * 
 * @author zghw
 *
 */
public interface IBizMeetPlaceService {
	/**
	 * 获取会场内容数据
	 * 
	 * @param Id
	 *            会场ID：不传的话，默认主会场
	 * @param isChange
	 *            1.强制切换到主会场;0.当前会场
	 * @return
	 * @author zghw
	 */
	public String fromFindHallContent(String id, String isChange,String staticType);

	/**
	 * 获取会场内容数据
	 * 
	 * @param Id
	 *            会场ID：不传的话，默认主会场
	 * @param isChange
	 *            1.强制切换到主会场;0.当前会场
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<Meet> beFindHallContent(String id, String isChange,String staticType);

	/**
	 * 限时购520会场
	 * 
	 * @return
	 * @author zghw
	 */
	public String fromTimeLimitBuy(String meetId);

	/**
	 * 限时购520会场
	 * 
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<TimeLimitBuy> beTimeLimitBuy(String meetId);

	/**
	 * 活动抽奖接口
	 * 
	 * @param activityId活动ID
	 * @param userId用户名ID
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<ActivityLottery> beActivityLottery(String activityId, String userId);
	/**
	 * 
	 * @param activityId
	 * @param userId
	 * @param verifyCode 验证码
	 * @return
	 */
	public ResultObjOne<ActivityLottery> beActivityLottery(String activityId, String userId,String verifyCode);

	/**
	 * 中奖名单
	 * 
	 * @param activityId
	 *            活动ID
	 * @param num
	 *            显示中奖人数量
	 * @return
	 * @author zghw
	 */
	public String fromWinners(String activityId,String pageIndex,String pageSize);

	/**
	 * 中奖名单
	 * 
	 * @param activityId
	 *            活动ID
	 * @param num
	 *            显示中奖人数量
	 * @return
	 * @author zghw
	 */
	public ResultObjMapList<Winners> beWinners(String activityId, String pageIndex,String pageSize);
}
