package com.shangpin.biz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shangpin.biz.bo.TimeBucketSendCoupon;

public class SendCoupon {

	private static HashMap<String, TimeBucketSendCoupon> SendCouponCache = new HashMap<String, TimeBucketSendCoupon>();
	private static final int SENDCOUPON_KEEP_TIME = 120 * 60 * 1000; // 120分钟
	private static long SendCouponTime;

	/**
	 * 根据注册时间是否在配置文件中配置的时间段内，来判断是否送券,
	 * 
	 * @return
	 */

	public static Map<String, String> isSendCouponAndGetCoupon() {
		long now = System.currentTimeMillis();
		if (now > SendCouponTime + SENDCOUPON_KEEP_TIME) {
			Properties props = PropertiesUtil.getInstance("/timebucketsendcoupon/timebucket.properties");
			SendCouponCache.clear();
			Gson gson = new Gson();
			TimeBucketSendCoupon timeBucketSendCoupon = new TimeBucketSendCoupon();
			for (Enumeration<?> e = props.keys(); e.hasMoreElements();) {
				String propkey = (String) e.nextElement();
				timeBucketSendCoupon = gson.fromJson(props.getProperty(propkey), new TypeToken<TimeBucketSendCoupon>() {
				}.getType());
				SendCouponCache.put(propkey, timeBucketSendCoupon);
			}

			SendCouponTime = now;
		}

		final SimpleDateFormat sdfconfig = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		TimeBucketSendCoupon promotioncache = SendCouponCache.get("timebucket");
		Map<String, String> map = new HashMap<String, String>();
		if (promotioncache != null) {
			try {

				Date starttime = sdfconfig.parse(promotioncache.getStartDate());
				Date endtime = sdfconfig.parse(promotioncache.getEndDate());
				Date date = new Date(now);
				if (date.after(starttime) && date.before(endtime)) {
					map.put("isSendCoupon", "true");
					map.put("coupon", promotioncache.getCoupon());
					map.put("sendcoupondesc", promotioncache.getDesc());
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
