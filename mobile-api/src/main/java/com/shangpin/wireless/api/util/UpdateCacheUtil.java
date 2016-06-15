package com.shangpin.wireless.api.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
@Deprecated
public class UpdateCacheUtil {
	
	private static final Log log = LogFactory.getLog(UpdateCacheUtil.class.getSimpleName());

	public static final void updatePerMinute () {
		log.debug(Thread.currentThread().getName() + " updatePerMinute: " + System.currentTimeMillis());
		AolaiActivityProductsCacheUtil.update();
		AolaiTopicProductsCacheUtil.update();
	}
	
	public static final void updatePerHour () {

	}
}
