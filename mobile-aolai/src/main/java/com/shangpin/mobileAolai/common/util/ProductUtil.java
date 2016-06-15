package com.shangpin.mobileAolai.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

public class ProductUtil {

	
	/**
	 * 定义不使用优惠劵的商品
	 */
	public static void loadProductOfCoupon(){
		// 获取配置文件路径
		String configFile = ProReader.class.getResource("/resources/config/coupon/coupon.properties").getPath();
		Properties p = new Properties();
		// 读取、加载配置文件
		FileInputStream in = null;
		try {
			in = new FileInputStream(configFile);
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			p.load(bf);
			// 将配置文件内容写入缓存
			JSONArray array = new JSONArray();
			try {
				if (StringUtils.isNotEmpty(p.getProperty("product_coupon"))) {
					array = JSONArray.fromObject(p.getProperty("product_coupon"));
				}
			} catch (Exception e) {
				array = new JSONArray();
				e.printStackTrace();
			}
			DataContainerPool.productCouponContainer.putOrReplace("productCoupon", array);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (in != null) {
				try {
					in.close();
					in = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		 
	}
	
	
	
}
