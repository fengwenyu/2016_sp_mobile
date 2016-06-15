package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.Operation;

public interface ASPBizOperationService {

	/**
	 * 获取运营信息
	 * 
	 * @param type
	 * 			1:首页;非必须
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	List<Operation> doOperation(String type, String pageIndex, String pageSize) throws Exception;

}
