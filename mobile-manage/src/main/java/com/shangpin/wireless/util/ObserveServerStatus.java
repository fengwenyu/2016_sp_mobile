package com.shangpin.wireless.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.domain.Constants;

/**
 * 监视服务器运行状态，提供了访问api server方法，在定时任务中配置observeServer方法
 * 
 */
public class ObserveServerStatus {

	private final Log log = LogFactory.getLog(ObserveServerStatus.class);
	private static Random random = new Random();
	private static final int MAX_NUMBER = 100000;

	/**
	 * 随机在0到100000间取数
	 * 
	 * @return
	 */
	private int getRandomData() {
		return random.nextInt(MAX_NUMBER);
	}

	private void observeAPIServer() {
		HashMap<String, String> header = new HashMap<String, String>();
		header.put("user-agent", "mozilla/5.0(iPhone;cpu iphone os 6_1_2 like mac os x)applewebkit/536.26(khtml,like gecko)mobile/10b146 micromessenger/4.5");
		header.put("p", "-1");
		header.put("ch", "-1");
		header.put("imei", "bf4d1c1bcaf6dc618fe8a0425a918fd2");

		String url = "http://api.m.shangpin.com/api/login";
		// url = "http://192.168.9.36:8080/api/login";
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "shangpin001@qq.com");
		map.put("password", "123123");
		String json = null;
		try {
			json = WebUtil.readContentFromGet(url, map, header);
		} catch (Exception e) {
			sendNotify("API", "API接口服务器无法链接！(login)" + e.getMessage());
			e.printStackTrace();
			return;
		}

		url = "http://mobile.apiv2.shangpin.com/shangpin/SPProductDetail/?productid=02346338&pich=%7Bh%7D&picw=%7Bw%7D";
		map.clear();
		try {
			json = WebUtil.readContentFromGet(url, map, header);
		} catch (Exception e) {
			sendNotify("API", "主站接口服务器无法链接！(SPProductDetail)" + e.getMessage());
			e.printStackTrace();
			return;
		}

		try {
			JSONObject.fromObject(json);
		} catch (Exception e) {
			sendNotify("API", "主站接口服务器数据异常。" + e.getMessage());
			e.printStackTrace();
			return;
		}

	}

	public static void main(String[] args) {
		new ObserveServerStatus().observeAPIServer();
	}

	private void sendNotify(String serverName, String msg) {
		String url = Constants.BASE_URL_AL_AL + "sendverifycode/";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", "WirelessManage" + getRandomData());
		map.put("phonenum", "15010246732");//15010246732
		map.put("msgtemplate", "无线管理平台提醒您：" + serverName + "服务器出错了！" + msg);
		try {
			String json = WebUtil.readContentFromGet(url, map);
			log.info("sendNotify：" + json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void observeServer() {
		observeAPIServer();
	}

}
