package com.shangpin.biz.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ActivityService;
import com.shangpin.base.service.CommonService;
import com.shangpin.biz.bo.ActivityLottery;
import com.shangpin.biz.bo.Meet;
import com.shangpin.biz.bo.SiteType;
import com.shangpin.biz.bo.TimeLimitBuy;
import com.shangpin.biz.bo.Winners;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizMeetPlaceService;
import com.shangpin.biz.utils.HtmlUtil;
import com.shangpin.utils.JsonUtil;

@Service
public class SPBizMeetPlaceServiceImpl implements SPBizMeetPlaceService {

	private static final Logger logger = LoggerFactory.getLogger(SPBizMeetPlaceServiceImpl.class);
	@Autowired
	private CommonService commonService;
	@Autowired
	private ActivityService activityService;

	@Override
	public Meet getMeetPlaceList(String id, SiteType type, String isChange) throws Exception {
		Meet meet = new Meet();
		try {
			ResultObjOne<Meet> obj = beFindHallContent(id, isChange,null);
			String osFlag = "1";
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				meet = obj.getObj();
				if (meet != null) {
					String html = meet.getHtml();
					if (!StringUtils.isEmpty(html)) {
						if (type.equals(SiteType.IOS_AOLAI) || type.equals(SiteType.IOS_SHANGPIN)) {
							osFlag = "3";
						}
						if (type.equals(SiteType.ANDRIOD_AOLAI) || type.equals(SiteType.ANDRIOD_SHANGPIN)) {
							osFlag = "2";
						}
					}
					html = HtmlUtil.modifyHrefAoLai(html, osFlag);
					meet.setHtml(html);
				}
			}
		} catch (Exception e) {
			logger.error("调用会场接口返回数据错误 e={}", e);
			return null;
		}
		return meet;
	}
	
	@Override
    public Meet getMeetPlaceList(String id, SiteType type, boolean flag, String isChange,String staticType) throws Exception {
	    Meet meet = new Meet();
        try {
            ResultObjOne<Meet> obj = beFindHallContent(id, isChange, staticType);
            String osFlag = "1";
            if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
                meet = obj.getObj();
                if (meet != null) {
                    String html = meet.getHtml();
                    if (!StringUtils.isEmpty(html)) {
                        if (type.equals(SiteType.IOS_AOLAI) || type.equals(SiteType.IOS_SHANGPIN)) {
                            osFlag = "3";
                        }
                        if (type.equals(SiteType.ANDRIOD_AOLAI) || type.equals(SiteType.ANDRIOD_SHANGPIN)) {
                            osFlag = "2";
                        }
                    }
                    if(flag){
                        html = HtmlUtil.modifyHref(html, osFlag); // 290之后的版本
                    }else{
                        html = HtmlUtil.modifyOldHref(html, osFlag); // 290之前的版本
                    }
                    meet.setHtml(html);
                }
            }
        } catch (Exception e) {
            logger.error("调用会场接口返回数据错误 e={}", e);
            return null;
        }
        return meet;
    }

	@Override
	public String fromFindHallContent(String id, String isChange,String staticType) {
		String json = commonService.findHallContent(id, isChange,staticType);
		return json;
	}

	@Override
	public ResultObjOne<Meet> beFindHallContent(String id, String isChange,String staticType) {
		String json = fromFindHallContent(id, isChange,staticType);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<Meet> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<Meet>>() {
			});
			return result;
		}
		return null;
	}

    /**
	 * 限时购520会场
	 * 
	 * @return
	 * @author zghw
	 */
	public String fromTimeLimitBuy(String meetId) {
		String json = activityService.findTimeLimitBuy(meetId);
		return json;
	}

	/**
	 * 限时购520会场
	 * 
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<TimeLimitBuy> beTimeLimitBuy(String meetId) {
		String json = fromTimeLimitBuy(meetId);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<TimeLimitBuy> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<TimeLimitBuy>>() {
			});
			return result;
		}
		return null;
	}

	/**
	 * 活动抽奖接口
	 * 
	 * @param activityId活动ID
	 * @param userId用户名ID
	 * @return
	 * @author zghw
	 */
	public String fromActivityLottery(String activityId, String userId,String verifyCode) {
		String json = activityService.findActivityLottery(activityId, userId,verifyCode);
		return json;
	}

	/**
	 * 活动抽奖接口
	 * 
	 * @param activityId活动ID
	 * @param userId用户名ID
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<ActivityLottery> beActivityLottery(String activityId, String userId) {
		String json = fromActivityLottery(activityId, userId,"");
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<ActivityLottery> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ActivityLottery>>() {
			});
			return result;
		}
		return null;
	}

	@Override
	public String fromWinners(String activityId, String pageIndex,String pageSize) {
		String json = activityService.findWinners(activityId, pageIndex,pageSize);
		return json;
	}

	@Override
	public ResultObjMapList<Winners> beWinners(String activityId, String pageIndex,String pageSize) {
		String json = fromWinners(activityId, pageIndex,pageSize);
		if (!StringUtils.isEmpty(json)) {
			ResultObjMapList<Winners> result = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Winners>>() {
			});
			return result;
		}
		return null;
	}

	@Override
	public List<Winners> doWinners(String activityId, String pageIndex,String pageSize) {
		ResultObjMapList<Winners> result=beWinners(activityId,pageIndex,pageSize);
		if(result!=null){
			return result.getList("winners");
		}
		return null;
	}

	@Override
	public ResultObjOne<ActivityLottery> beActivityLottery(String activityId,
			String userId, String verifyCode) {
		String json = fromActivityLottery(activityId, userId,verifyCode);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<ActivityLottery> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ActivityLottery>>() {
			});
			return result;
		}
		return null;
	}

    @Override
    public ResultObjOne<ActivityLottery> beFollowerLottery(String userId, String activityId) {
        String json = activityService.findFollowerLottery(userId,activityId);
        if (!StringUtils.isEmpty(json)) {
            ResultObjOne<ActivityLottery> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ActivityLottery>>() {
            });
            return result;
        }
        return null;
    }
}
