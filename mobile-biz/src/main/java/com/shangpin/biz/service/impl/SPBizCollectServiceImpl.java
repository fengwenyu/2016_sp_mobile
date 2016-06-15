package com.shangpin.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shangpin.biz.bo.Collect;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.service.SPBizCollectService;
import com.shangpin.biz.service.abstraction.AbstractBizCollectService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.StringUtil;

/**
 * @ClassName: SPBizCollectServiceImpl
 * @Description:收藏接口实现类
 * @author qinyingchun
 * @date 2014年11月22日
 * @version 1.0
 */
@Service
public class SPBizCollectServiceImpl extends AbstractBizCollectService implements SPBizCollectService {

	private static Logger logger = LoggerFactory.getLogger(SPBizCollectServiceImpl.class);

	@Override
	public List<Collect> collectList(String userId, String pageIndex, String pageSize, String type) {
		try {
			ResultObjMapList<Collect> obj = beCollect(userId, pageIndex, pageSize, type);
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				return obj.getList("list");
			}
		} catch (Exception e) {
			logger.error("base interface collect list return data occur error!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> collectBrand(String userId, String brandId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ResultBase resultBase = fromCollectBrand(userId, brandId);
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

	@Override
	public Map<String, Object> cancleCollectBrand(String userId, String brandId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ResultBase resultBase = fromCancleCollectBrand(userId, brandId);
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

	@Override
	public Map<String, Object> collectProduct(String shopType, String skuId, String userId, String picw, String pich, String detailPicw, String detailPich) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = fromCollectProduct(shopType, skuId, userId, picw, pich, detailPicw, detailPich);

			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode;
			rootNode = mapper.readTree(json);
			String code = rootNode.path("code").asText();
			if (Constants.SUCCESS.equals(code)) {
				String collectId = rootNode.path("content").path("favoriteproductid").asText();
				map.put("collectId", collectId);
				map.put("code",  Constants.SUCCESS);
			}else{
				map.put("code", Constants.FAILE);
			}

		} catch (Exception e) {
			logger.error("base interface return data error!");
			e.printStackTrace();
		}
		return map;
	}
	@Override
	public Map<String, Object> cancleCollectProduct(String shopType, String collectId, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ResultBase resultBase = fromCancleCollectProduct(shopType,collectId,userId);
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
