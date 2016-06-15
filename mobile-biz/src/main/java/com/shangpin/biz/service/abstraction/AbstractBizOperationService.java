package com.shangpin.biz.service.abstraction;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.vo.ResultObjOne;
import com.shangpin.biz.bo.DynamicObj;
import com.shangpin.biz.bo.Operation;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizOperationService extends AbstractBizCommonService {

	protected List<Operation> doBaseOperation(String type, String pageIndex, String pageSize) throws Exception {
		String json = shangPinService.getOperation(type, pageIndex, pageSize);
		List<Operation> lists = null;
		if (StringUtils.isNotEmpty(json)) {
		    ResultObjOne<DynamicObj<Operation>> resultObjMapList = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<DynamicObj<Operation>>>(){});
			lists = resultObjMapList.getContent().getList();
		}
		return lists;
	}

}
