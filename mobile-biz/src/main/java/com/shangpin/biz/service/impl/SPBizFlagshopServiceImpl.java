package com.shangpin.biz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.Flagshop;
import com.shangpin.biz.service.SPBizFlagshopService;
import com.shangpin.biz.utils.Constants;

@Service
public class SPBizFlagshopServiceImpl implements SPBizFlagshopService {
	private static final Logger logger = LoggerFactory.getLogger(SPBizFlagshopServiceImpl.class);
	@Autowired
	ShangPinService shangpinService;

	@Override
	public Flagshop getFlagshopDetail(String brandNO) {
		String json = null;
		try {
			// 获取会场data格式字符串
			json = shangpinService.findFlagShip(brandNO);
			if (StringUtils.isEmpty(json)) {
				return null;
			}
			Flagshop Flagshop = new Flagshop();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode readTree = mapper.readTree(json);
			String code = readTree.path("code").asText();
			if (Constants.SUCCESS.equals(code)) {
				JsonNode result = readTree.path("content");
				Flagshop.setHtml(result.path("mobiletemplate").asText());
			}
			return Flagshop;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用旗舰店接口返回数据错误" + e);
		}
		return null;

	}

}
