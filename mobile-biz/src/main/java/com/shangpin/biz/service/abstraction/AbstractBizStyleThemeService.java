package com.shangpin.biz.service.abstraction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.StyleTheme;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

 /**
  * 商城首页风格主题抽象类，供API和m站公用
 * @author qinyingchun
 *
 */
public class AbstractBizStyleThemeService {
	private static final Logger logger = LoggerFactory.getLogger(AbstractBizStyleThemeService.class);
	
	@Autowired
	private ShangPinService shangPinService;
	public String queryStyleThemeStr(String pageIndex, String pageSize){
		String json = shangPinService.queryStyleTheme(pageIndex, pageSize);
		return json;
	}
	
	public List<StyleTheme> styleThemes(String pageIndex, String pageSize){
		String json = queryStyleThemeStr(pageIndex, pageSize);
		logger.debug("invoke base interface style theme return data:" + json);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultObjMapList<StyleTheme> resultObjMapList = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<StyleTheme>>(){});
		String code = resultObjMapList.getCode();
		if(!Constants.SUCCESS.equals(code) || null == resultObjMapList){
			return null;
		}
		List<StyleTheme> styleThemes = resultObjMapList.getList("list");
		return styleThemes;
	}

}
