package com.shangpin.mobileShangpin.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IndexCacheUtil {
	protected final static Log log = LogFactory.getLog(SizeINFOCacheUtil.class);
	private static Cache categoryproduct_cache;
	private static Cache category_cache;
	private static Cache brand_cache;
	private static boolean iscache=false;
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
		categoryproduct_cache = cache.getCache("categoryproduct_cache");
		category_cache = cache.getCache("category_cache");
		brand_cache = cache.getCache("brand_cache");
	}

	public synchronized static void put(Serializable key, List value) {
		Element element = new Element(key, value);
		setIscache(true);
		if (null != categoryproduct_cache&&key.toString().indexOf("P_")!=-1) {
			categoryproduct_cache.put(element);
		} else if(null != category_cache&&key.toString().indexOf("C_")!=-1){
			category_cache.put(element);
		}else if(null != brand_cache&&key.toString().indexOf("B_")!=-1){
			brand_cache.put(element);
		}
		else {
			log.warn("FileuploadTerminalServlet：" + " load EHCache failure!!!");
		}
	}

	public static Object getValue(Serializable key) {
		Element result=null;
		if(categoryproduct_cache!=null&&category_cache!=null&&brand_cache!=null){
			if (null != categoryproduct_cache&&key.toString().indexOf("P_")!=-1) {
				result=categoryproduct_cache.get(key);
			} else if(null != category_cache&&key.toString().indexOf("C_")!=-1){
				result=category_cache.get(key);
			}else if(null != brand_cache&&key.toString().indexOf("B_")!=-1){
				result=brand_cache.get(key);
			}	
		}else{
			log.warn("FileuploadTerminalServlet：" + " load EHCache failure!!!");
			return null;
		}
		if (null != result) {
			Object obj = result.getObjectValue();
			if (null != obj) {
				return obj;
			}
		}
		return null;
	}
	public synchronized void  timerdel(){
			categoryproduct_cache.removeAll();
			category_cache.removeAll();
			brand_cache.removeAll();
			setIscache(false);
	}

	public static boolean getIscache() {
		return iscache;
	}

	public static void setIscache(boolean iscache) {
		IndexCacheUtil.iscache = iscache;
	}
	
	
}
