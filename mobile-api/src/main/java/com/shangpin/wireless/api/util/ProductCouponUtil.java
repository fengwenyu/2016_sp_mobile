package com.shangpin.wireless.api.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductCouponUtil {

	private static List<String> config = Collections.synchronizedList(new ArrayList<String>());
	protected static final Log log = LogFactory.getLog(ProductCouponUtil.class.getSimpleName());
	/**
	 * 定义不使用优惠劵的商品
	 * @throws UnsupportedEncodingException 
	 */
	public static void loadProductOfCoupon(){
		// 获取配置文件路径
		Properties props = PropertiesUtil.getInstance("/coupon/coupon.properties");
		String coupons;
		try {
			coupons = new String(props.getProperty("product_coupon").getBytes("ISO-8859-1"),"UTF-8");
			JSONArray couponList = JSONArray.fromObject(coupons);
			config.clear();
			if(couponList != null && couponList.size() > 0){
				for(int i = 0 ;i < couponList.size(); i++){
				    String id="";
					if("".equals(id)){
					    id=((JSONObject)couponList.get(i)).getString("brandName");
					}else{
					    id = ((JSONObject)couponList.get(i)).getString("id"); 
					}
					if(StringUtil.isNotEmpty(id)){
						config.add(id);
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error("ProductCouponUtil:"+e);
		}
		 
	}
	
	public static boolean checkIsExist(String productId){
		boolean flag = false;
		if(config != null && config.size() > 0){
			for(String key : config){
				if(StringUtil.isNotEmpty(productId) && key.equals(productId)){
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	public static boolean checkBrandIsExist(String brandName){
        boolean flag = false;
        if(config != null && config.size() > 0){
            for(String key : config){
                if(StringUtil.isNotEmpty(brandName) && key.equals(brandName)){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

}
