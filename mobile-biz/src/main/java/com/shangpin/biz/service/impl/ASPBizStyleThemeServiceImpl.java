package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.StyleTheme;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.service.ASPBizStyleThemeService;
import com.shangpin.utils.JSONUtils;

@Service
public class ASPBizStyleThemeServiceImpl implements ASPBizStyleThemeService {
    private static final Logger logger = LoggerFactory.getLogger(ASPBizStyleThemeServiceImpl.class);
    @Autowired
    private ShangPinService shangPinService;

    @Override
    public List<StyleTheme> queryStyleTheme(String pageIndex, String pageSize) {
        List<StyleTheme> styleThemesList = new ArrayList<StyleTheme>();
        try {
            String json = shangPinService.queryStyleTheme(pageIndex, pageSize);
            logger.debug("调用base接口返回数据:" + json);
            if (!StringUtils.isEmpty(json)) {
                ResultObjMapList<StyleTheme> obj = JSONUtils.toGenericsCollection(json, ResultObjMapList.class, StyleTheme.class);
                if(obj!=null&&obj.getList("list")!=null){     
                    styleThemesList = obj.getList("list");
                }
            }
        } catch (Exception e) {
            logger.error("调用base接口返回数据发生错误！");
            e.printStackTrace();
        }
        return styleThemesList;
    }

    @Override
    public String queryStyleThemeToResult(String pageIndex, String pageSize) {
        try {
            String json = shangPinService.queryStyleTheme(pageIndex, pageSize);
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
