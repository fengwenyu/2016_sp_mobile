package com.shangpin.biz.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.service.SPBizAddPayLogService;
import com.shangpin.biz.service.abstraction.AbstractBizAddPayLogService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.StringUtil;
@Service
public class SPBizAddPayLogServiceImpl extends AbstractBizAddPayLogService implements SPBizAddPayLogService {
	private static final Logger logger = LoggerFactory.getLogger(SPBizAddPayLogServiceImpl.class);

	@Override
	public Map<String, Object> addPayLog(String orderId, String payInfo,
			String payType) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ResultBase resultBase = baseAddPayLog(orderId, payInfo,payType);
			if (Constants.SUCCESS.equals(resultBase.getCode())) {
				map.put("code", Constants.SUCCESS);
			} else {
				map.put("code", Constants.FAILE);
				String msg = resultBase.getMsg();
				if (StringUtil.isNotEmpty(msg)) {
					map.put("msg", resultBase.getMsg());
				} 

			}
		} catch (Exception e) {
			logger.error("base interface return data error!");
			e.printStackTrace();
		}
		return map;
	}

}
