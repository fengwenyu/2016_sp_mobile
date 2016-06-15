package com.shangpin.mobileAolai.common.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import com.shangpin.mobileAolai.platform.vo.VenueEntranceVO;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class VenueEntrance {
	
	private static HashMap<String, JSONArray> VenueCache = new HashMap<String, JSONArray>();
	private static final int VENUE_KEEP_TIME = 3 * 60 * 1000; // 30分钟
	private static long VenueTime;
	
	/**
	 * 得到会场入口内容
	 * 
	 * @return
	 */
	public static VenueEntranceVO getVenueEntranceContent(){
		long now = System.currentTimeMillis();
		if (now > VenueTime + VENUE_KEEP_TIME) {
			Properties props = PropertiesUtil.getInstance("/resources/config/venueentrance/venueentrance.properties");
			VenueCache.clear();
			for (Enumeration<?> e = props.keys(); e.hasMoreElements();) {
				String propkey = (String) e.nextElement();
				JSONArray array = new JSONArray();
				array = JSONArray.fromObject(props.getProperty(propkey));
				VenueCache.put(propkey, array);
			}
			VenueTime = now;
		}
		
		final SimpleDateFormat sdfconfig = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		JSONArray promotioncacheArray=VenueCache.get("venueentrance");
		if (null != promotioncacheArray && !"".equals(promotioncacheArray))  {
			for (int i = 0; i < promotioncacheArray.size(); i++) {
				JSONObject obj = (JSONObject) promotioncacheArray.get(i);
				if (!obj.isEmpty()) {
					try {
						Date starttime = sdfconfig.parse(obj.getString("startDate"));
						Date endtime = sdfconfig.parse(obj.getString("endDate"));
						Date date = new Date(now);
						if (date.after(starttime) && date.before(endtime)) {
							VenueEntranceVO venueEntranceVO = new VenueEntranceVO();
							venueEntranceVO.setImgUrl(obj.getString("imgUrl"));
							venueEntranceVO.setTitle(obj.getString("title"));
							venueEntranceVO.setUrl(obj.getString("url"));
							return venueEntranceVO;
							
						} 
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else {
					return null;
				}
			}
		}
		return null;
		
	}
}
