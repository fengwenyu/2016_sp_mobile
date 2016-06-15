package com.shangpin.web.utils;

import java.util.Map;

import redis.clients.jedis.Jedis;

import com.shangpin.biz.bo.ActivityIndex;
import com.shangpin.biz.bo.BrandShop;
import com.shangpin.biz.bo.SearchResult;
import com.shangpin.utils.JedisUtilFactory;
import com.shangpin.utils.JsonUtil;

public class SearchCache {
	private static String key="";
  /**搜索result
   * subject activityIndex|SearchResult
   * brand brandShop |searchResult	
   * @param searchResult
   * @param brandShop
   * @param activityIndex
   */
  public boolean cache(Object object,String key){	
		
	  /**
		 * 定义缓存策略
		 */
	    //  searchResult
		
		try{
			if(null!=object){
				String redisTime ="600";//10分钟
				Jedis jedis = JedisUtilFactory.getDefaultJedisUtil().getJedis();
				String json=JsonUtil.toJson(object);
				jedis.set(key, json);
				jedis.expire(key, Integer.parseInt(redisTime));
			}
			return true;
		}catch(Exception e){
			return false;
		}
		
		/*
		
		//brandShop
		if(null!=brandShop){
			String bredisTime ="600";//10分钟
			Jedis bjedis = JedisUtilFactory.getDefaultJedisUtil().getJedis();
			String bjson=JsonUtil.toJson(brandShop);
			bjedis.set(key, bjson);
			bjedis.expire(key, Integer.parseInt(bredisTime));
		}
		
		//activityIndex
		
		if(null!=activityIndex){
			String aredisTime ="600";//10分钟
			Jedis ajedis = JedisUtilFactory.getDefaultJedisUtil().getJedis();
			String ajson=JsonUtil.toJson(activityIndex);
			ajedis.set(key, ajson);
			ajedis.expire(key, Integer.parseInt(aredisTime));
		}
	*/	
  }
	
}
