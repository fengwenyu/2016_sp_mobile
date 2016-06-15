///**
// * 
// */
//package com.shangpin.wechat.utils.common;
//
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//import com.shangpin.wechat.service.impl.WeChatMerchantServiceImpl;
//
///**
// * @author ZRS
// *
// */
//public class CertUtil {
//	
//	private static Map<String,InputStream> map = new HashMap<String,InputStream>();
//	
//	static{
//		InputStream instream = WeChatMerchantServiceImpl.class.getClassLoader().getResourceAsStream("wxcash_apiclient_cert.p12");
////		InputStream instream = WeChatMerchantServiceImpl.class.getClassLoader().getResourceAsStream("wxcash_apiclient_cert.pem");
//		map.put("WX_CASH", instream);
//	}
//	
//	public static InputStream getValue(String key){
//		return map.get(key);
//	}
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
