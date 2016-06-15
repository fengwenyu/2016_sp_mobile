package com.shangpin.mobileAolai.common.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

public class SendCoupon {
	
	private static HashMap<String, JSONObject> PromotionCache = new HashMap<String, JSONObject>();
	private static final int PROMOTION_KEEP_TIME = 120 * 60 * 1000; // 120分钟
	private static long PromotionTime;
	
	/**
	 * 根据注册时间是否在配置文件中配置的时间段内，来判断是否送券,如果送返回map添加优惠码
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> isSendCouponAndGetCoupon() {
		long now = System.currentTimeMillis();
		if (now > PromotionTime + PROMOTION_KEEP_TIME) {
			Properties props = PropertiesUtil.getInstance("/resources/config/timebucketsendcoupon/timebucket.properties");
			PromotionCache.clear();
			for (Enumeration e = props.keys(); e.hasMoreElements();) {
				String propkey = (String) e.nextElement();
				JSONObject json = JSONObject.fromObject(props.getProperty(propkey));
				PromotionCache.put(propkey, json);
			}
			PromotionTime = now;
		}
	
		final SimpleDateFormat sdfconfig = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		JSONObject promotioncache = PromotionCache.get("timebucket");
		Map<String, String> map = new HashMap<String, String>();
		if (promotioncache != null) {
			try {
				
				Date starttime = sdfconfig.parse(promotioncache.getString("startDate"));
				Date endtime = sdfconfig.parse(promotioncache.getString("endDate"));
				Date date = new Date(now);
				if (date.after(starttime) && date.before(endtime)) {
					map.put("isSendCoupon", "true");
					map.put("coupon", promotioncache.getString("coupon"));
					map.put("sendcoupondesc", promotioncache.getString("sendcoupondesc"));
				} else {
					map.put("isSendCoupon", "false");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

}
