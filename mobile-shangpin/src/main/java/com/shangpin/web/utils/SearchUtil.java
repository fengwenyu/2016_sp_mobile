package com.shangpin.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.shangpin.biz.bo.SearchConditions;

public class SearchUtil {
	private static final Logger logger = LoggerFactory.getLogger(SearchUtil.class);
	public static String initQueryConditions(SearchConditions searchConditions){
		StringBuffer conditions = new StringBuffer();
		String gender = searchConditions.getGender();
		String categoryName = searchConditions.getCategoryName();
		String brandName = searchConditions.getBrandName();
		String colorName = searchConditions.getColorName();
		String size = searchConditions.getSize();
		String price = searchConditions.getPrice();
		String order = searchConditions.getOrder();
//		if(!StringUtils.isEmpty(gender)){
//			conditions.append(sexName(gender)).append("-");
//		}
		if(!StringUtils.isEmpty(categoryName)){
			conditions.append(categoryName).append("-");
		}
		if(!StringUtils.isEmpty(brandName)){
			conditions.append(brandName).append("-");
		}
		if(!StringUtils.isEmpty(colorName)){
			conditions.append(colorName).append("-");
		}
		if(!StringUtils.isEmpty(size)){
			conditions.append(size).append("-");
		}
		if(!StringUtils.isEmpty(price)){
			conditions.append(price).append("-");
		}
		if(!StringUtils.isEmpty(order)){
			conditions.append(orderDsc(order)).append("-");
		}
//		return conditions.toString();
		
		
		if(conditions.length()>0){
			if(!StringUtils.isEmpty(gender)){
				conditions.insert(0, (sexName(gender)));
			}
				return conditions.substring(0, conditions.lastIndexOf("-"));
		}
		return null;
		
	
	}
	
	public static String sexName(String gender){
		String genderName;
		if(gender.equals(Constants.WOMAN)){
			genderName = "女士-";
		}else if(gender.equals(Constants.MAN)){
			genderName = "男士-";
		}else{
			genderName = "";
		}
		return genderName;
	}
	
	public static String orderDsc(String order){
		String orderDsc;
		if(order.equals("2")){
			orderDsc = "价格从低到高";
		}else if (order.equals("1")) {
			orderDsc = "价格从高到低";
		}else if (order.equals("3")) {
			orderDsc = "新品";
		}else {
			orderDsc = "";
		}
		return orderDsc;
	}
	
	public static int hasMore(int total, int size){
		int totalPage = total % size == 0 ? total/size : total/size + 1;
		int hasMore = 0;
		if(totalPage > 1){
			hasMore = 1;
		}
		return hasMore;
	}
	public static int hasMore(int total,int index, int size){
		int totalPage = total % (size *index) == 0 ? total/(size *index) : total/(size *index) + 1;
		int hasMore = 0;
		if(totalPage > 1){
			hasMore = 1;
		}
		return hasMore;
	}
	public static int hasPageNum(int total, int size){
		int hasPageNum = total/ size;
		int num=total% size;
		if(num>0){
			hasPageNum=hasPageNum+1;
		}
		return hasPageNum;
	}
	public static int totalPage(int total, int size){
		return total % size == 0 ? total/size : total/size + 1;
	}
	public static  void SystemConditions(SearchConditions s) {
		logger.info("brandName: "+s.getBrandName());
		logger.info("brandNo: "+s.getBrandNo());
		logger.info("categoryName: "+s.getCategoryName());
		logger.info("categoryNo: "+s.getCategoryNo());
		logger.info("color: "+s.getColor());
		logger.info("colorName: "+s.getColorName());
		logger.info("gender: "+s.getGender());
		logger.info("keyword: "+s.getKeyword());
		logger.info("num: "+s.getNum());
		logger.info("order: "+s.getOrder());
		logger.info("pageNo: "+s.getPageNo());
		logger.info("postArea: "+s.getPostArea());
		logger.info("price: "+s.getPrice());
		logger.info("size: "+s.getSize());
		logger.info("start: "+s.getStart());
		logger.info("tagId: "+s.getTagId());
		logger.info("topicId: "+s.getTopicId());
		logger.info("userLv"+s.getUserLv());
	}
}
