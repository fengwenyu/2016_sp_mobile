package com.shangpin.mobileShangpin.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.shangpin.mobileShangpin.common.page.Page;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.LogisticeService;
import com.shangpin.mobileShangpin.platform.vo.LogisticeVO;
import com.shangpin.mobileShangpin.platform.vo.MerchandiseVO;


/**
 * 物流逻辑接口，用于获取物流信息相关操作
 * 
 * @author wangfeng
 * @CreatDate 2013-7-29
 */

@Service("logisticeService")
@SuppressWarnings("unchecked")
// @Transactional

public class LogisticeServiceImpl implements LogisticeService {

	@Override
	public LogisticeVO getNewLogistice(String orderId, String userId)
			throws Exception {
		LogisticeVO logisticeVO=null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("flag", "1");
		map.put("orderid", orderId);
		map.put("userid", userId);
		map.put("pich", Constants.SAMLL_PICTURE_HEIGHT);
		map.put("picw", Constants.SAMLL_PICTURE_WIDTH);
		String url = Constants.BASE_SP_URL + "OrderLogistic/";
		String json = null;
		try {
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj && Constants.SUCCESS.equals(jsonObj.get("code"))) {
				logisticeVO=new LogisticeVO();
				if (null != jsonObj.get("content") && !"{}".equals(jsonObj.get("content").toString())) {
					JSONObject contentObj = (JSONObject) jsonObj.get("content");
					logisticeVO.setOrderId(contentObj.getString("orderid"));
					if (null != contentObj.get("list") && !"[]".equals(contentObj.get("list").toString())) {
						JSONArray listArray = (JSONArray) contentObj.get("list");
						for(int i=0;i<listArray.size();i++){

								JSONObject listObj = listArray.getJSONObject(i);
								if (null != listObj.get("logistics") && !"[]".equals(listObj.get("logistics").toString())) {
									JSONArray array = (JSONArray) listObj.get("logistics");
									JSONObject logisticeVoJsonObj = array.getJSONObject(0);
									logisticeVO.setExpress(logisticeVoJsonObj.getString("express"));
									logisticeVO.setTicketNo(logisticeVoJsonObj.getString("ticketno"));
								}

						}
						
					}
				}
			}
		}
		return logisticeVO;
	}

	@Override
	public List<LogisticeVO> getLogisticeList(String orderId, String userId,
			String ticketno) throws Exception {
		LogisticeVO logisticeVO=null;
		List<LogisticeVO> logisticeList=null;
		String express=null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderid", orderId);
		map.put("ticketno", ticketno);
		map.put("userid", userId);
		String url = Constants.BASE_SP_URL + "SPLogisticsDetail/";
		String json = null;
		try {
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj && Constants.SUCCESS.equals(jsonObj.get("code"))) {
				if (null != jsonObj.get("content") && !"{}".equals(jsonObj.get("content").toString())) {
					JSONObject contentObj = (JSONObject) jsonObj.get("content");
					express=contentObj.getString("express");
					if (null != contentObj.get("logistics") && !"[]".equals(contentObj.get("logistics").toString())) {
						JSONArray listArray = (JSONArray) contentObj.get("logistics");
						logisticeList=new ArrayList<LogisticeVO>();
						for(int i=0;i<listArray.size();i++){
									JSONObject logisticeVoJsonObj = listArray.getJSONObject(i);
									logisticeVO=new LogisticeVO();
									logisticeVO.setOrderId(orderId);
									logisticeVO.setExpress(express);
									logisticeVO.setDate(logisticeVoJsonObj.getString("date"));
									logisticeVO.setDesc(logisticeVoJsonObj.getString("desc"));
									logisticeVO.setAddress(logisticeVoJsonObj.getString("address"));
									logisticeList.add(logisticeVO);
								}

						}
						
					}
				}
			}
		return logisticeList;
	}

	@Override
	public List<LogisticeVO> getLogisticeOrderList(String userId,String pageIndex)
			throws Exception {
		LogisticeVO logisticeVO=null;
		List<LogisticeVO> logisticeList=null;
		MerchandiseVO merchandiseVO=null;
		List<MerchandiseVO> merchandiseVOList=null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("flag","1" );
		map.put("pageindex", pageIndex);
		map.put("pagesize", String.valueOf(Page.DEFAULT_PAGE_SIZE));
		map.put("userid", userId);
		map.put("pich", Constants.SAMLL_PICTURE_HEIGHT);
		map.put("picw", Constants.SAMLL_PICTURE_WIDTH);
		map.put("ordertype", "1");
		map.put("isall", "1");

		String url = Constants.BASE_SP_URL + "Logistics/";
		String json = null;
		try {
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj && Constants.SUCCESS.equals(jsonObj.get("code"))) {
				if (null != jsonObj.get("content") && !"{}".equals(jsonObj.get("content").toString())) {
					JSONObject contentObj = (JSONObject) jsonObj.get("content");
					if (null != contentObj.get("list") && !"[]".equals(contentObj.get("list").toString())) {
						logisticeList=new ArrayList<LogisticeVO>();
						JSONArray listArray = (JSONArray) contentObj.get("list");
						for(int i=0;i<listArray.size();i++){
							logisticeVO=new LogisticeVO();
							JSONObject logisticeVoListJsonObj = listArray.getJSONObject(i);
							logisticeVO.setOrderId(logisticeVoListJsonObj.getString("orderid"));
							logisticeVO.setTotalcount(logisticeVoListJsonObj.getString("totalcount"));
							if (null != logisticeVoListJsonObj.get("list") && !"[]".equals(logisticeVoListJsonObj.get("list").toString())) {
								JSONArray logisticeVoAndProductArray = (JSONArray) logisticeVoListJsonObj.get("list");
								for(int j=0;j<logisticeVoAndProductArray.size();j++){
									JSONObject logisticeVoListAndProductJsonObj = logisticeVoAndProductArray.getJSONObject(j);
									if (null != logisticeVoListAndProductJsonObj.get("product") && !"[]".equals(logisticeVoListAndProductJsonObj.get("product").toString())) {
										JSONArray productArray = (JSONArray) logisticeVoListAndProductJsonObj.get("product");
										merchandiseVOList=new ArrayList<MerchandiseVO>();
										for(int z=0;z<productArray.size();z++){
											merchandiseVO=new MerchandiseVO();
											JSONObject productJsonObj = productArray.getJSONObject(z);
											merchandiseVO.setProductname(productJsonObj.getString("productname"));
											merchandiseVO.setFirstpropname(productJsonObj.getString("firstpropname"));
											merchandiseVO.setFirstpropvalue(productJsonObj.getString("firstpropvalue"));
											merchandiseVO.setBuyCount(productJsonObj.getString("count"));
											merchandiseVO.setAmount(productJsonObj.getString("amount"));
											merchandiseVO.setBrand(productJsonObj.getString("brand"));											
											merchandiseVO.setPicurl(productJsonObj.getString("pic"));
											merchandiseVOList.add(merchandiseVO);
										}
										logisticeVO.setMerchandiseVO(merchandiseVOList);
										
									}
									if (null != logisticeVoListAndProductJsonObj.get("logistics") && !"[]".equals(logisticeVoListAndProductJsonObj.get("logistics").toString())) {
										JSONArray logisticeVoArray = (JSONArray) logisticeVoListAndProductJsonObj.get("logistics");
										JSONObject logisticeVoJsonObj = logisticeVoArray.getJSONObject(0);
										logisticeVO.setExpress(logisticeVoJsonObj.getString("express"));
										logisticeVO.setTicketNo(logisticeVoJsonObj.getString("ticketno"));
										logisticeVO.setDesc(logisticeVoJsonObj.getString("desc"));
									}
								}
							}						
								logisticeList.add(logisticeVO);
						}
					}
						
					}
				}
			}
		return logisticeList;
	}

	@Override
	public String getOrderLogistic(String userId, String orderId) {
		String ticketno=null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("flag","1" );
		map.put("userid", userId);
		map.put("orderid", orderId);
		map.put("pich", Constants.SAMLL_PICTURE_HEIGHT);
		map.put("picw", Constants.SAMLL_PICTURE_WIDTH);
		String url = Constants.BASE_SP_URL + "OrderLogistic/";
		String json = null;
		try {
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj && Constants.SUCCESS.equals(jsonObj.get("code"))) {
				if (null != jsonObj.get("content") && !"{}".equals(jsonObj.get("content").toString())) {
					JSONObject contentObj = (JSONObject) jsonObj.get("content");
					if (null != contentObj.get("list") && !"[]".equals(contentObj.get("list").toString())) {
						JSONArray listArray = (JSONArray) contentObj.get("list");
						for(int i=0;i<listArray.size();i++){
							JSONObject listObj = listArray.getJSONObject(i);
							if(null != listObj.get("logistics") && !"[]".equals(listObj.get("logistics").toString())){
								JSONArray logisticsArray = (JSONArray) listObj.get("logistics");
								JSONObject logisticeVoJsonObj = logisticsArray.getJSONObject(0);
								ticketno=logisticeVoJsonObj.getString("ticketno");
							}
							
						}							
					}
				}
			}
		}
		return ticketno;
	}

	

}
