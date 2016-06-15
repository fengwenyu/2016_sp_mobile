package com.shangpin.biz.service.abstraction;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ResultObjOne;
import com.shangpin.biz.bo.Advert;
import com.shangpin.biz.bo.CommonRules;
import com.shangpin.biz.bo.DynamicObj;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

/**
 * 首页相关抽象接口
 * 
 * @author huangxiaoliang
 *
 */
public abstract class AbstractBizIndexInfoService extends AbstractBizCommonService {

	@Autowired
	private ShangPinService shangPinService;

	/** 获取首页广告 */
	public String getBaseAdvert() {
		return shangPinService.getHeadAdverts();

	}

	/** 获取首页广告集合 */
	public List<Advert> doBaseAdvert() throws Exception {
		String json = getBaseAdvert();
		List<Advert> adverts = null;
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<DynamicObj<Advert>> resultObjMapList = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<DynamicObj<Advert>>>() {
			});
			if (Constants.SUCCESS.equals(resultObjMapList.getCode())) {
				adverts = resultObjMapList.getContent().getList();
			}
		} else {
			adverts = new ArrayList<Advert>();
		}

		return adverts;
	}

	/** 获取首页标签 */
	public String getBaseLabel(String pageIndex, String pageSize) {
		return shangPinService.getLabel(pageIndex, pageSize);

	}

	/** 获取新版首页标签 */
	public String getBaseLabel(String pageIndex, String pageSize, String type) {
		return shangPinService.getLabel(pageIndex, pageSize, type);

	}

	/** 首页弹窗 */
	public String doBaseShellWindow(String pageIndex, String pageSize) {
		return shangPinService.getShellWindow(pageIndex, pageSize);
		
	}
	
	/** 更多潮流资讯 */
	public CommonRules getFashion() {
		CommonRules moreFashion = new CommonRules();
		moreFashion.setName("潮流资讯");
		moreFashion.setType("5");
		moreFashion.setRefContent(Constants.FASHION_WAP);
		return moreFashion;
	}
}
