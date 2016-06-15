package com.shangpin.biz.service.abstraction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.ReleasesSPActivity;
import com.shangpin.biz.bo.SPActivity;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.utils.JsonUtil;

/**
 * @author qinyingchun 首页最新上架抽象类，该类中的方法供API和m站公用
 *
 */
public class AbstractBizNewReleaseService extends AbstractBizCommonService {
	private static final Logger logger = LoggerFactory.getLogger(AbstractBizNewReleaseService.class);

	@Autowired
	private ShangPinService shangPinService;

	public String queryReleaseStr(String pageIndex, String pageSize, String origin) {
		String json = shangPinService.queryNewReleases(pageIndex, pageSize, origin);
		return json;
	}

	public ResultObjOne<ReleasesSPActivity> beNewRelease(String pageIndex, String pageSize, String origin){
		String json = queryReleaseStr(pageIndex, pageSize, origin);
		logger.debug("invoke base interface new release return data:" + json);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<ReleasesSPActivity> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ReleasesSPActivity>>() {
			});
			if(result!=null && result.isSuccess()){
				return result;
			}
		}
		return null;
	}
	
	public List<SPActivity> activities(String pageIndex, String pageSize, String origin) {
	    String json = queryReleaseStr(pageIndex, pageSize, origin);
        logger.debug("invoke base interface new release return data:" + json);
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        ResultObjOne<ReleasesSPActivity> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ReleasesSPActivity>>() {
        });
        if (result != null && result.isSuccess() && result.getContent() != null) {
            List<SPActivity> activities = result.getContent().getList();
            return activities;
        }

        return null;
	}
}
