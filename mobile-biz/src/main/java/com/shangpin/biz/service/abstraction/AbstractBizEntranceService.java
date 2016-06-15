package com.shangpin.biz.service.abstraction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.DynamicObj;
import com.shangpin.biz.bo.Entrance;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizEntranceService {

    public static Logger logger = LoggerFactory.getLogger(AbstractBizEntranceService.class);
    @Autowired
    ShangPinService shangPinService;

    /**
     *首页入口运营位
     *
     * @param   type 1为m站，2为app
     * @param   pageIndex   页码 
     * @param   pageSize    每页数量   
     * @return
     * @author wangfeng
     */
    public List<Entrance> queryEntrance(String type,String pageIndex,String pageSize) {
        try {
            String json = shangPinService.findEntranceIndex(type, pageIndex, pageSize);
            logger.debug("调用base接口返回数据:" + json);
            if (!StringUtils.isEmpty(json)) {                
                ResultObjOne<DynamicObj<Entrance>> resultObjMapList = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<DynamicObj<Entrance>>>(){});
                String code = resultObjMapList.getCode();
                if(!Constants.SUCCESS.equals(code) || null == resultObjMapList){
                    return null;
                }
                List<Entrance> EntranceList = resultObjMapList.getContent().getList();
                return EntranceList;
            }
        } catch (Exception e) {
            logger.error("调用base接口返回数据发生错误！");
            e.printStackTrace();
        }
        return null;
    }
    /**
     *首页入口运营位
     *
     * @param   type 1为m站，2为app
     * @param   pageIndex   页码 
     * @param   pageSize    每页数量   
     * @return
     * @author wangfeng
     */
    public String queryGalleryToResult(String type,String pageIndex,String pageSize) {
        try {
            String json = shangPinService.findEntranceIndex(type, pageIndex, pageSize);
            logger.debug("调用base接口返回数据:" + json);
            if (!StringUtils.isEmpty(json)) {
                return json;
            }
        } catch (Exception e) {
            logger.error("调用base接口返回数据发生错误！");
            e.printStackTrace();
        }
        return null;
    }
    
}
