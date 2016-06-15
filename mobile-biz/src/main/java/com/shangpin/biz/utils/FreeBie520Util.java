package com.shangpin.biz.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.biz.service.impl.FreebieService;
import com.shangpin.utils.AESUtil;

/** 
 * 520买赠活动的util
 * Date:     2016年5月12日 <br/> 
 * @author   fengwenyu
 * @since    JDK 7
 */
public class FreeBie520Util {
	
	private static final String SPLIT_CHAR = "`";
	private static final String freebieKey="key_shangpin_fbe";
	
	private static final Logger logger = LoggerFactory.getLogger(FreeBie520Util.class);
	
	/*** 由于加密后的串会存在‘+’ 导致 http将该字符当做拼接符，所以先替换为8uuuu8，使用时，再替换回来 */
	private static final String SOURCE_CHAR="+";
	private static final String TARGER_CHAR="9uuuuu9";

	/**
	 * 加密分享url上面的参数 
	 * @param userId 
	 * @param orderId
	 * @param spu
	 * @param skuId
	 * @param sortNo
	 * @return
	 */
	public static String encodeFreebieUrlParam(String userId,String orderId,String spu,String skuId, String sortNo){
		StringBuffer orgin= new StringBuffer();
		orgin.append(userId);
		orgin.append(SPLIT_CHAR+orderId);
		orgin.append(SPLIT_CHAR+spu);
		orgin.append(SPLIT_CHAR+skuId);
		orgin.append(SPLIT_CHAR+sortNo);
		try {
			String result = AESUtil.encrypt(orgin.toString(), freebieKey);
			return result.replace(SOURCE_CHAR, TARGER_CHAR);
		} catch (Exception e) {
			logger.error("买赠参数加密失误，原始串：{}",orgin);
		}
		return "";
	}
	
	
	
	/**
	 * 将分享的url上的加密参数解密<br/>
	 * @param freebieParam 加密的参数
	 * @return 解密后的参数键值对,键有userId,orderId,skuId
	 */
	public static Map<String,String> decodeFreebieParam(String freebieParam){
		try {
			String fb=AESUtil.decrypt(freebieParam.replace(TARGER_CHAR,SOURCE_CHAR), freebieKey);
			String[] params=fb.split(SPLIT_CHAR);
			Map<String,String> map = new HashMap<>(params.length);
			map.put("userId",params[0]);
			map.put("orderId",params[1]);
			if(params.length>2){
				map.put("spu", params[2]);
			}
			if(params.length>3){
				map.put("sku", params[3]);
			}
			if(params.length>4){
				map.put("sortNo", params[4]);
			}
			return map;
		} catch (Exception e) {
		}
		return null;
	}
	
	public static void main(String[] args) {
		Map<String, String> param = decodeFreebieParam("YGcu&t/ZCKIZIw6hHMIoLT0hGmd6D3X35ThhH2XU1dzRIBGTtKkNSjeBD53VNEJYEnhu/E6XQfQeINytNXBJtA==");
		System.out.println(param);
	}
	
}
