package com.shangpin.wireless.api.view.servlet;

import com.shangpin.utils.HttpClientUtil;
import com.shangpin.wireless.api.util.WebUtil;
import org.junit.Test;

public class WeXinPlatformServletTest {
	@Test
	public void test0() {
		
//		
		
		String url = "http://192.168.20.81:8083/mapi/weixinapi";
		String s="<xml>" +
//				"<ToUserName><![CDATA[oFHXijvkFXv7ypscJ-jl3rP3O4lY]]></ToUserName>" +
				"<ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName>" +
				"<FromUserName><![CDATA[oFHXijnTyBFsnD4FXBxabUlSPG1Y]]></FromUserName>" +
				"<CreateTime>12345678</CreateTime>" +
				"<MsgType><![CDATA[text]]></MsgType>" +
				"<Content><![CDATA[TOPSFFHOP1]]></Content>" +
				"</xml>";
		try {
//			String result = WebUtil.readContentFromPost(url, s);
			String ee = HttpClientUtil.doPostWithJSON(url, s);
			System.out.println("result="+ee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2(){
		
		String url = "http://wx.ntalker.com/agent/weixin";
		
		String s = "<xml><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName>"
				+ "<FromUserName><![CDATA[oFHXijnTyBFsnD4FXBxabUlSPG1Y]]></FromUserName>"
				+ "<CreateTime>1451959363361</CreateTime>"
				+ "<MsgType><![CDATA[text]]></MsgType>"
				+ "<Content><![CDATA[申请在线客服]]></Content>"
				+ "<MsgId>1451959363361</MsgId></xml>";
		
		try {
			String result = WebUtil.readContentFromPost(url, s);
//			String ee = HttpClientUtil.doPostWithJSON(url, s);
			System.out.println("result ="+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	
	public static void main(String[] args) {

		WeXinPlatformServletTest wx = new WeXinPlatformServletTest();
//		wx.test();
//		String s = "72.35";
//		double d= Double.valueOf(s);
//		String amount = (Double.valueOf(s)*100) + "";
//		BigDecimal bigDecimal = new BigDecimal(s);
//		amount = bigDecimal.multiply(BigDecimal.valueOf(100)).intValue()+"";
//		System.out.println(d);
//		System.out.println(amount);
//		System.out.println("args = [" + args + "]");
//		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//		String[] arr = ac.getBeanDefinitionNames();
//		for (String s : arr) {
//			System.out.println("args = [" + s + "]");
//		}
//
//		WeXinPlatformServiceImpl weXinPlatformServiceImpl = (WeXinPlatformServiceImpl)ac.getBean("com.shangpin.wireless.api.service.impl.WeXinPlatformServiceImpl");
//
//		try {
////			weXinPlatformServiceImpl.findAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}


//		String str = "15812345131s15812345672a15812345133s15812345674a" +
//				"15812345135s15812345676as13312345644";
//
//		String regex = "(1[3458][0-9]{9}).*(1[3458][0-9]{9}).*" +
//				"(1[3458][0-9]{9}).*(1[3458][0-9]{9}).*" +
//				"(1[3458][0-9]{9}).*(1[3458][0-9]{9}).*" +
//				"(1[3458][0-9]{9}).*";
//
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(str);
//		System.out.println("groupCount: " + matcher.groupCount());
//		int count = matcher.groupCount();
//		while (matcher.find()) {
//			for (int i = 1; i <= count; i++) {
//				if (matcher.start(i) > -1) {
//					System.out.println("group " + i + ": " + matcher.group(i));
//				}
//			}
//		}

	}
}
