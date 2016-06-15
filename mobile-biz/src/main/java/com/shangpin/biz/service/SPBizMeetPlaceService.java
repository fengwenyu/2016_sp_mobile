package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.ActivityLottery;
import com.shangpin.biz.bo.Meet;
import com.shangpin.biz.bo.SiteType;
import com.shangpin.biz.bo.Winners;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.basic.IBizMeetPlaceService;

public interface SPBizMeetPlaceService extends IBizMeetPlaceService {
    /**
     * 得到会场信息
     * 
     * @param id
     *            会场ID
     * @param type
     *            来源M站或者客户端其他
     * @param isChange
     *            是否切换到主场 0是预热 1是主场
     * @return
     * @throws Exception
     * 
     */
    public Meet getMeetPlaceList(String id, SiteType type, String isChange) throws Exception;

    /**
     * 得到会场信息
     * 
     * @param id
     *            会场ID
     * @param type
     *            来源M站或者客户端其他
     * @param flag
     *            是否调用新的替换方法 true:调用新的，false:调用老的
     * @param isChange
     *            是否切换到主场 0是预热 1是主场
     * @return
     * @throws Exception
     * 
     */
    public Meet getMeetPlaceList(String id, SiteType type, boolean flag, String isChange, String staticType) throws Exception;

    /**
     * 中奖名单
     * 
     * @param activityId
     *            活动ID
     * @param num
     *            中奖名单数量
     * @return
     * @author zghw
     */
    public List<Winners> doWinners(String activityId, String pageIndex, String pageSize);

    /***
     * 抽奖接口
     * 
     * @param userId
     * @param activityId
     * @return ResultObjOne<ActivityLottery>
     */
    public ResultObjOne<ActivityLottery> beFollowerLottery(String userId, String activityId);

}
