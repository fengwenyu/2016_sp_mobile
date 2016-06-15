package com.shangpin.web.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.shangpin.biz.bo.Flagshop;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FlagshopEntrance {
	
	private static HashMap<String, JSONArray> VenueCache = new HashMap<String, JSONArray>();
//	private static final int VENUE_KEEP_TIME = 30 * 60 * 1000; // 30分钟
	private static final int VENUE_KEEP_TIME = 0;
	private static long VenueTime;
	
	/**
	 * 得到旗舰店内容
	 * 
	 * @return
	 */
	public static List<Flagshop> getFlagshopContent() {
		long now = System.currentTimeMillis();
		if (now > VenueTime + VENUE_KEEP_TIME) {
			Properties props = PropertiesUtil.getInstance("/config/flagshop/flagshop.properties");
			VenueCache.clear();
			for (Enumeration<?> e = props.keys(); e.hasMoreElements();) {
				String propkey = (String) e.nextElement();
				JSONArray array = new JSONArray();
				array = JSONArray.fromObject(props.getProperty(propkey));
				VenueCache.put(propkey, array);
			}
			VenueTime = now;
		}
		List<Flagshop> flagshopList = new ArrayList<Flagshop>();
		JSONArray promotioncacheArray=VenueCache.get("flagshop");
		if (null != promotioncacheArray && !promotioncacheArray.isEmpty())  {
			for (int i = 0; i < promotioncacheArray.size(); i++) {
				JSONObject obj = (JSONObject) promotioncacheArray.get(i);
				if (!obj.isEmpty()) {
					Flagshop Flagshop = new Flagshop();
					Flagshop.setImgUrl(obj.getString("imgUrl"));
					try {
						Flagshop.setTitle(URLEncoder.encode(obj.getString("title"), "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					Flagshop.setUrl(obj.getString("url"));
					flagshopList.add(Flagshop);
				}
			}
			return flagshopList;
		}
		return null;
		
	}
}
