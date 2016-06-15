package com.shangpin.wireless.api.util;

import java.util.HashMap;
import java.util.Map;

import com.shangpin.wireless.api.vo.SearchMerchandiseVO;

public class FilterSearchUtil {
	/**
	 * 初始化查询参数
	 * @author xupengcheng
	 * @createDate 2014-1-6 下午02:46:08
	 * @param brandNO
	 * @param categoryNO
	 * @param primaryColorId
	 * @param price
	 * @param productSize
	 * @param order
	 * @param start
	 * @param end
	 * @return
	 */
	public static HashMap<String, String> initQueryMap(String userLv, String userId, String brandNO, String categoryNO, String navigationID, String primaryColorId, String price,String gender,
			String productSize, String order, Integer start, Integer end) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		if(price != null){
			try{
				Integer price1 = Integer.parseInt(price);
				if(price1 != null){
					price = price + "~";
				}
			}catch(Exception e){
			}
		}
		
		if(userId != null && !"".equals(userId)){
			map.put("userID", userId);
		}
		
		if(navigationID != null && !"".equals(navigationID)){
			map.put("navigationID", navigationID);
		}
		
		if(categoryNO != null && !"".equals(categoryNO)){
			map.put("categoryNO", categoryNO);
		}
		if(brandNO != null && !"".equals(brandNO)){
			map.put("brandNO", brandNO);
		}
		if(primaryColorId != null && !"".equals(primaryColorId)){
			map.put("primaryColorId", primaryColorId);
		}
		if(productSize != null && !"".equals(productSize)){
			map.put("productSize", productSize);
		}
		if(start != null){
			map.put("start", String.valueOf(start));
		}
		if(end != null){
			map.put("end", String.valueOf(end));
		}
		
		if(gender != null && !"".equals(gender)){
			map.put("gender", gender);
		}
		
		String orderField = "";
		String orerType = "";
		if(order != null && !"".equals(order.trim())){
			String[] orderType = order.split(":");
			if(orderType != null){
				if(orderType.length > 1){
					orerType = orderType[1];
				}else{
					orerType = "";
				}
				orderField = orderType[0]; 
			}
		}
		if(userLv != null && !"".equals(userLv)){
			if(userLv.equals("0001") || userLv.equals("0000")){
				if(price != null && !"".equals(price)){
					map.put("price", "LimitedPrice_Sale:" + price);
				}
				if(orderField.equalsIgnoreCase("price")){
					map.put("order", "LimitedPrice_Sale " + orerType);
				}else if(orderField.equalsIgnoreCase("productNewFlag")){
					map.put("order", "ProductNewFlag " + orerType);
				}
			}else if(userLv.equals("0002")){
				if(price != null && !"".equals(price)){
					map.put("price", "SellPrice_Sale:" + price);
				}
				if(orderField.equalsIgnoreCase("price")){
					map.put("order", "SellPrice_Sale " + orerType);
				}else if(orderField.equalsIgnoreCase("ProductNewFlag")){
					map.put("order", "ProductNewFlag "+ orerType);
				}
			}else if(userLv.equals("0003")){
				if(price != null && !"".equals(price)){
					map.put("price", "PlatinumPrice_Sale:" + price);
				}
				if(orderField.equalsIgnoreCase("price")){
					map.put("order", "PlatinumPrice_Sale " + orerType);
				}else if(orderField.equalsIgnoreCase("ProductNewFlag")){
					map.put("order", "ProductNewFlag "+ orerType);
				}
			}else if(userLv.equals("0004")){
				if(price != null && !"".equals(price)){
					map.put("price", "DiamondPrice_Sale:" + price);
				}
				if(orderField.equalsIgnoreCase("price")){
					map.put("order", "DiamondPrice_Sale " + orerType);
				}else if(orderField.equalsIgnoreCase("ProductNewFlag")){
					map.put("order", "ProductNewFlag "+ orerType);
				}
			}
		}else{
			if(price != null && !"".equals(price)){
				map.put("price", "LimitedPrice_Sale:" + price);
			}
			if(orderField.equalsIgnoreCase("price")){
				map.put("order", "LimitedPrice_Sale " + orerType);
			}else if(orderField.equalsIgnoreCase("ProductNewFlag")){
				map.put("order", "ProductNewFlag "+ orerType);
			}
		}
		return map;
	}
	
	public static SearchMerchandiseVO searchProductList(Map<String, String> map, String url, boolean flag){
		String data = null;
		try {
			// 获取活动xml格式字符串
			data = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
		}                                                         
		SearchMerchandiseVO searchMerchandiseVO = ParseXmlUtil.parseSearchResultXml(data, flag);
		return searchMerchandiseVO;
	}
}
