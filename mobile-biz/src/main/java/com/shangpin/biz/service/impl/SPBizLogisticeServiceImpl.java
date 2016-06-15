package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.OrderService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.Logistics;
import com.shangpin.biz.bo.LogisticsStatus;
import com.shangpin.biz.bo.OrderLogistics;
import com.shangpin.biz.bo.Receive;
import com.shangpin.biz.bo.Track;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizLogisticeService;
import com.shangpin.biz.service.abstraction.AbstractBizLogisticeService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.ParseJsonUtil;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.JsonUtil;

@Service
public class SPBizLogisticeServiceImpl extends AbstractBizLogisticeService implements SPBizLogisticeService {
	public static Logger logger = LoggerFactory.getLogger(SPBizLogisticeServiceImpl.class);
	@Autowired
	private ShangPinService shangpinService;
	@Autowired
	private OrderService orderService;

	@Override
	public OrderLogistics getOrderLogisticsInfo(String orderId, String userId,String postArea,String isNew) {
		String json = shangpinService.findOrderMoreLogistic(userId, orderId,postArea,isNew);
		OrderLogistics logistics = new OrderLogistics();
		if (StringUtil.isNotEmpty(json)) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode;
			try {
				rootNode = mapper.readTree(json);
				String code = rootNode.path("code").asText();
				if (Constants.SUCCESS.equals(code)) {
					JsonNode content = rootNode.path("content");
					if (content != null && !"null".equals(content.asText()) && !"{}".equals(content.asText())) {
						String jsonList = content.path("list").toString();
						if (StringUtil.isNotEmpty(jsonList) && !"[]".equals(jsonList)) {
							JavaType javaType = ParseJsonUtil.getCollectionType(ArrayList.class, Logistics.class);
							List<Logistics> list = mapper.readValue(jsonList, javaType);

							logistics.setList(initLogisticsPackage(list));
						}

						logistics.setDate(content.path("date").asText());
						logistics.setOrderId(content.path("orderId").asText());
						logistics.setNoticeInfo(content.path("noticeInfo").asText());
						String jsonReceive = content.path("receive").toString();
						if (StringUtil.isNotEmpty(jsonReceive) && !"{}".equals(jsonReceive)) {
							Receive receive = JsonUtil.fromJson(jsonReceive, Receive.class);
							logistics.setReceive(receive);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("调用订单列表接口返回数据错误 e={}", e);
			}

		}
		return logistics;

	}

	/**
	 * 订单物流信息
	 * 
	 * @param List
	 *            <Logistics> list
	 * @return List<Logistics> list
	 */
	private List<Logistics> initLogisticsPackage(List<Logistics> list) {
		List<Logistics> newList = new ArrayList<Logistics>();

		for (int i = 0; i < list.size(); i++) {
			Logistics newLogistics = new Logistics();
			Logistics singleLogistics = list.get(i);

			List<LogisticsStatus> logisticsStatusList = new ArrayList<LogisticsStatus>();
			newLogistics.setExpress(singleLogistics.getExpress().trim());
			newLogistics.setTicketno(singleLogistics.getTicketno().trim());
			newLogistics.setWarehouse(singleLogistics.getWarehouse());
			newLogistics.setDate(singleLogistics.getDate());
			newLogistics.setWarehouse(singleLogistics.getWarehouse());
			newLogistics.setStatus(singleLogistics.getStatus());
			newLogistics.setStatusdesc(singleLogistics.getStatusdesc());
			newLogistics.setProducts(singleLogistics.getProducts());

			for (int j =0; j < singleLogistics.getLogistics().size(); j++) {
				LogisticsStatus newLogisticsStatus = new LogisticsStatus();
				LogisticsStatus logisticsStatus = singleLogistics.getLogistics().get(j);
				newLogisticsStatus.setAddress(logisticsStatus.getAddress());
				newLogisticsStatus.setDate(logisticsStatus.getDate());
				newLogisticsStatus.setDesc(logisticsStatus.getDesc());
				logisticsStatusList.add(newLogisticsStatus);
			}
			newLogistics.setLogistics(logisticsStatusList);
			newList.add(newLogistics);
		}

		return newList;

	}

	@Override
	public Track getNewLogistice(String orderId, String userId) {
		String track = orderService.getNewLogistice(orderId, userId);
		logger.debug("调用主站查询订单物流信息接口返回数据:" + track);
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode readTree = mapper.readTree(track);
			String code = readTree.path("code").asText();
			if ("0".equals(code)) {
				ResultObjOne<Track> objOne = JsonUtil.fromJson(track, new TypeToken<ResultObjOne<Track>>() {
				});
				return objOne.getObj();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用主站查询订单物流信息接口返回数据错误:" + e);
		}
		return null;
	}

}
