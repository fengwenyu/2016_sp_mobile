package com.shangpin.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.CMSservice;
import com.shangpin.base.service.CommonService;
import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.service.ASPBizCMSService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.product.model.cbwfs.CommonRules;
import com.shangpin.product.model.common.ContentBuilder;

@Service
public class ASPBizCMSServiceImpl implements ASPBizCMSService {
	@Autowired
	private CMSservice cmsService;
	
	@Autowired
	private CommonService commonService;

	@SuppressWarnings("unchecked")
	@Override
	public Object querySearchKeyword() throws Exception {
		ContentBuilder<Map<Object, List<CommonRules>>> obj = (ContentBuilder<Map<Object, List<CommonRules>>>) cmsService.getSearchKeyword();
		ContentBuilder<Map<Object, Object>> builder = new ContentBuilder<Map<Object, Object>>();
		if (obj != null) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("title", "热门搜索");
			map.put("list", obj.getContent().get("list"));
			return builder.buildDefSuccess(map);
		}
		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}

	@Override
	public String getSearchKeyword() throws Exception {
		return commonService.querySearchKeyword();
	}

}
