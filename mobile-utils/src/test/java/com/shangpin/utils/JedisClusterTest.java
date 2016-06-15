package com.shangpin.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import com.shangpin.utils.CouponJedisUtil.Strings;

public class JedisClusterTest {

	@Test
	public void testJedisUtil(){
		JedisUtil jedisUtil = JedisUtilFactory.getDefaultJedisUtil();
		jedisUtil.set("yanxiaobin", "小宾在哪里？？？？");
		String value = jedisUtil.get("上帝");
		long decr = jedisUtil.decr("fffds");
		System.out.println(decr);
		System.out.println(value);
	}
	@Test
	public void testCouponJedisUtil(){
		CouponJedisUtil jedisUtil = JedisUtilFactory.getCouponJedisUtil();
		jedisUtil.set("chenxiaofeng", "小峰在哪里？？？？");
		String value = jedisUtil.get("chenxiaofeng");
		System.out.println(value);
		Strings strings = jedisUtil.new Strings();
		 
	}
	@Test
	public void testServiceJedisUtil(){
		ServiceJedisUtil jedisUtil = JedisUtilFactory.getServiceJedisUtil();
//		jedisUtil.set("上帝", "上帝在哪里？？？？？");
//		String value = jedisUtil.get("上帝");
//		System.out.println("======="+value+"=============");
//		boolean b = jedisUtil.exists("上帝");
//		System.out.println(b);
//		long num = jedisUtil.decr("number");
//		System.out.println(num);
//		jedisUtil.expire("yanxiaobin");
//		jedisUtil.expire("yan", 60*60);
//		byte[] bs = jedisUtil.get("yanxiaobin".getBytes());
//		System.out.println(new String(bs));
		jedisUtil.set("XXX", "XXX".getBytes());
		byte[] bs = jedisUtil.get("XXX".getBytes());
		System.out.println(new String(bs));
		while (true) {
			Long num = jedisUtil.incr("number");
			System.out.println(num);
		}
	}
	
	@Test
	public void run() throws Throwable{
		
		// TODO 自动生成方法存根 
		URL url = new URL("http://localhost:8083/shopunion/alluserlogin");//你要登陆网站的登陆界面的地址. 
		HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
		con.setDoOutput(true); // POST方式 

		con.setRequestMethod("POST"); 
		OutputStream os = con.getOutputStream(); // 输出流，写数据 
		os.write("username=kaixinmoming&password=860523".getBytes());//提交用户名和密码.当然你要知道这个网站用户名和密码的变量名称向里面传值 
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream())); // 读取结果 
		String line; 
		while ((line = reader.readLine()) != null) { 
		System.out.println(line);//输出返回信息,这是一个字符传 你需要分析字符串来判断是否登陆成功. 
		} 		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
