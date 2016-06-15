package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.Operation;
import com.shangpin.biz.service.ASPBizOperationService;
import com.shangpin.biz.service.abstraction.AbstractBizOperationService;

@Service
public class ASPBizOperationServiceImpl extends AbstractBizOperationService implements ASPBizOperationService {

	@Override
	public List<Operation> doOperation(String type, String pageIndex, String pageSize) throws Exception {
		List<Operation> operations = doBaseOperation(type, pageIndex, pageSize);
		if (null == operations) {
			operations = new ArrayList<Operation>();
		}
		return operations;
	}

}
