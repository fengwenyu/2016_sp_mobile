package com.shangpin.base.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shangpin.base.service.ActivityService;
import com.shangpin.base.utils.BaseDataUtil;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.utils.HttpClientUtil;

@Service
public class ActivityServiceImpl implements ActivityService {
	/** 限时购--520 */
	private StringBuilder timeLimitBuyURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("timeLimitBuy");
	/** 抽奖 */
	private StringBuilder activityLotteryURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("activityLottery");
	/** FationRun抽奖 */
	private StringBuilder activityFationRunURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("activityFationRun");
	/**中奖人名单*/
	private StringBuilder winnersURL=new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("winners");
	/**抽奖910*/
    private StringBuilder activityfollowerURL=new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("activityfollowerLottery");
    
	@Override
	public String findTimeLimitBuy(String meetId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("meetingId", meetId);
		String result = BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "findTimeLimitBuy", params, timeLimitBuyURL.toString());
		// String result = HttpClientUtil.doGet(timeLimitBuyURL.toString(), params);
		return result;
	}

	@Override
	public String findActivityLottery(String activityId, String userId,String verifyCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("activityId", activityId);
		params.put("userId", userId);
		String result = "";
		if(verifyCode!=null&&!"".equals(verifyCode)){
			params.put("verifyCode", verifyCode);
			result = HttpClientUtil.doGet(activityFationRunURL.toString(), params);
		}else{
			result = HttpClientUtil.doGet(activityLotteryURL.toString(), params);
		}
		return result;
	}

	@Override
	public String findWinners(String activityId,String pageIndex,String pageSize) {
		Map<String,String> params=new HashMap<String,String>();
		params.put("activityId", activityId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		String result = HttpClientUtil.doGet(winnersURL.toString(), params);
		return result;
	}

    @Override
    public String findFollowerLottery(String userId, String activityId) {
        Map<String,String> params=new HashMap<String,String>();
        params.put("activityId", activityId);
        params.put("userId", userId); 
        String result = HttpClientUtil.doGet(activityfollowerURL.toString(), params);
        return result;
    }

}
