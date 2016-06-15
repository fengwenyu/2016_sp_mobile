package com.shangpin.biz.utils;

import java.text.ParseException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.shangpin.biz.bo.Flagship;

public class FlagshopEntrance {
	
	private static HashMap<String, List<Flagship>> VenueCache = new HashMap<String, List<Flagship>>();
	
	/**
	 * 得到旗舰店内容
	 * 
	 * @return
	 */
	@SuppressWarnings("serial")
    public static List<Flagship> getFlagshopContent() throws ParseException{
		Properties props = PropertiesUtil.getInstance("/flagshop.properties");
		VenueCache.clear();
		Gson gson = new Gson();
		List<Flagship> flagshipList=null;
		for (Enumeration<?> e = props.keys(); e.hasMoreElements();) {
			String propkey = (String) e.nextElement();			
			flagshipList=gson.fromJson(props.getProperty(propkey),new TypeToken<List<Flagship>>(){}.getType());
			VenueCache.put(propkey, flagshipList);
		}
		VenueCache.clear();
		return flagshipList;		
	}

}
