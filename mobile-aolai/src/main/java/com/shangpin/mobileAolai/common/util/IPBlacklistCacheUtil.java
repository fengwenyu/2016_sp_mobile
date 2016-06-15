package com.shangpin.mobileAolai.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IPBlacklistCacheUtil {
	protected final static Log log = LogFactory.getLog(IPBlacklistCacheUtil.class);
	private static Cache ipBlacklistCache;
	static {
		CacheManager cache = null;
		InputStream fis = null;
		try {
			fis = Thread.currentThread().getContextClassLoader().getResourceAsStream("ehcache.xml");
			cache = CacheManager.create(fis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fis = null;
			}
		}
		ipBlacklistCache = cache.getCache("ip_blacklist_cache");
	}

	public static void put(Serializable key, Serializable value) {
		Element element = new Element(key, value);
		if (null != ipBlacklistCache) {
			ipBlacklistCache.put(element);
		} else {
			log.warn("FileuploadTerminalServlet：" + " load EHCache failure!!!");
		}
	}

	public static Object getValue(Serializable key) {
		if (null == ipBlacklistCache) {
			log.warn("FileuploadTerminalServlet：" + " load EHCache failure!!!");
			return null;
		}
		Element result = ipBlacklistCache.get(key);
		if (null != result) {
			Object obj = result.getObjectValue();
			if (null != obj) {
				return obj;
			}
		}
		return null;
	}
}
