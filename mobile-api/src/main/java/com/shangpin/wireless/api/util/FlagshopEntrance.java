package com.shangpin.wireless.api.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.shangpin.wireless.api.domain.Flagship;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FlagshopEntrance {
	
	private static HashMap<String, JSONArray> VenueCache = new HashMap<String, JSONArray>();
	
	/**
	 * 得到旗舰店内容
	 * 
	 * @return
	 */
	public static List<Flagship> getFlagshopContent() throws ParseException{
		Properties props = PropertiesUtil.getInstance("/flagship/flagshop.properties");
		VenueCache.clear();
		for (Enumeration<?> e = props.keys(); e.hasMoreElements();) {
			String propkey = (String) e.nextElement();
			JSONArray array = new JSONArray();
			array = JSONArray.fromObject(props.getProperty(propkey));
			VenueCache.put(propkey, array);
		}
		List<Flagship> flagshopList = new ArrayList<Flagship>();
		JSONArray promotioncacheArray=VenueCache.get("flagshop");
		if (null != promotioncacheArray && !"".equals(promotioncacheArray))  {
			for (int i = 0; i < promotioncacheArray.size(); i++) {
				JSONObject obj = (JSONObject) promotioncacheArray.get(i);
				if (!obj.isEmpty()) {
					Flagship flagship = new Flagship();
					flagship.setImgUrl(obj.getString("imgUrl"));
					flagship.setTitle(obj.getString("title"));
					flagship.setUrl(obj.getString("url"));
					flagship.setId(obj.getString("id"));
					flagshopList.add(flagship);
				}
			}
			VenueCache.clear();
			return flagshopList;
		}
		VenueCache.clear();
		return null;
		
	}
	
}
