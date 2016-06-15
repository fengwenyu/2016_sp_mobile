package com.shangpin.biz.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shangpin.base.service.AoLaiService;
import com.shangpin.biz.bo.ActivityLv2;
import com.shangpin.biz.bo.Merchandise;
import com.shangpin.biz.service.ALBizProductService;
import com.shangpin.biz.service.abstraction.AbstractBizProductService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.ParseJsonUtil;
@Service
public class ALBizProductServiceImpl extends AbstractBizProductService implements ALBizProductService {
	
	public static final Logger logger = LoggerFactory.getLogger(ALBizProductServiceImpl.class);
	
	@Autowired
	private AoLaiService aoLaiService;

	/**
	 * 二级活动商品列表
	 * 
	 */
	@Override
	public ActivityLv2 getActivityLv2List(String activityId, String typeFlag,
			Integer pageIndex, Integer pageSize) {
		ActivityLv2 activityLv2 = new ActivityLv2();
		String picw = Constants.GOODS_LIST_PICTURE_WIDTH;
		String pich = Constants.GOODS_LIST_PICTURE_HEIGHT;
		String index = String.valueOf(pageIndex);
		String size = String.valueOf(pageSize);
		String json = "";
		
		if(Constants.TYPE_FLAG_ACTIVITY.equals(typeFlag)){
			//测试数据
			//activityId = "40507292";
			json = aoLaiService.findSubjectProductList(activityId, picw, pich, index, size);
			logger.debug("ActivityLv2List's json is:"+json);
		}else if(Constants.TYPE_FLAG_TOPIC.equals(typeFlag)){
			//预留
		}
		
		if(!StringUtils.isEmpty(json)){
			ObjectMapper  mapper = new ObjectMapper();
			JsonNode rootNode;
			try {
				rootNode = mapper.readTree(json);
				String code = rootNode.path("code").asText();
				String name = rootNode.path("content").path("name").asText();
				String startTime = rootNode.path("content").path("starttime").asText();
				String endTime = rootNode.path("content").path("endtime").asText();
				String openFlag = "";//标记活动是否开启
				activityLv2.setName(name);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//主站处理成功
				if(Constants.SUCCESS.equals(code)){
					String jsonList = rootNode.path("content").path("list").toString();
					JavaType javaType = ParseJsonUtil.getCollectionType(ArrayList.class,Merchandise.class);
					List<Merchandise> goodsList = mapper.readValue(jsonList, javaType);
					activityLv2.setMerchandiseList(goodsList);
					
					if(Constants.TYPE_FLAG_ACTIVITY.equals(typeFlag)){
						activityLv2.setStartTime(sdf.format(new Date(Long.valueOf(startTime))));
						activityLv2.setEndTime(sdf.format(new Date(Long.valueOf(endTime))));
						if(System.currentTimeMillis() >= Long.valueOf(endTime)){
							//活动已结束
							openFlag = "3";
						} else if (System.currentTimeMillis() >= Long.valueOf(startTime)) {
							//活动开启
							openFlag = "1";
						} else {
							//活动未开启
							openFlag = "0";
						}
					}else if(Constants.TYPE_FLAG_TOPIC.equals(typeFlag)){
						openFlag = "1";
					}
					activityLv2.setOpenFlag(openFlag);
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return activityLv2;
	}
	
	/**
	 * 商品详情
	 */
	@Override
	public Merchandise getDetail(String categoryNo, String goodsId,
			String typeFlag) {
		Merchandise merchandise = new Merchandise();
		String picw = Constants.GOODS_DETAIL_PICTURE_WIDTH;
		String pich = Constants.GOODS_DETAIL_PICTURE_HEIGHT;
		
		String json = aoLaiService.findProductDetail(goodsId, typeFlag, categoryNo, picw, pich);
		logger.debug("ProductDetail's json is:"+json);
		if(!StringUtils.isEmpty(json)){
			ObjectMapper  mapper = new ObjectMapper();
			JsonNode rootNode;
			try {
				rootNode = mapper.readTree(json);
				String code = rootNode.path("code").asText();
				if(Constants.SUCCESS.equals(code)){
					String jsonStr = rootNode.path("content").toString();
					merchandise = mapper.readValue(jsonStr, Merchandise.class);
				}
			}catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return merchandise;
	}

}
