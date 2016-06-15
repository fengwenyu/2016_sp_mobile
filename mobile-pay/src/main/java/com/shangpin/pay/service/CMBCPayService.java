package com.shangpin.pay.service;

import java.util.Map;

import com.shangpin.pay.bo.CommonPayBo;
import com.shangpin.pay.exception.ServiceException;

public interface CMBCPayService {
	Map<String,Object>  cmbcWapPay(CommonPayBo CommonPayBo) throws ServiceException;
}
