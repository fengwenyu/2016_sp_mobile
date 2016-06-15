package com.shangpin.wireless.api.weixinbean;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LotteryConfig {

	private String id;
	private Date startTime;
	private Date endTime;
	private PrizeEntity[] entities;
	
	public static LotteryConfig parse (String configStr) throws Exception {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		LotteryConfig config = new LotteryConfig();
		
		JSONObject obj = JSONObject.fromObject(configStr);
		config.id = obj.getString("id");
		config.startTime = sdf.parse(obj.getString("starttime"));
		config.endTime = sdf.parse(obj.getString("endtime"));
		JSONArray list = obj.getJSONArray("list");
		config.entities = new PrizeEntity[list.size()];
		int probability = 0;
		for (int i=0;i<list.size();i++) {
			PrizeEntity entity = PrizeEntity.parse(list.getJSONObject(i));
			entity.rangeStart = probability;
			probability += entity.probability;
			entity.rangeEnd = probability;
			
			config.entities[i] = entity;
		}
		
		return config;
	}

	public static class PrizeEntity {
		int rangeStart;
		int rangeEnd;
		int probability;
		int count;
		Object activatecode;	//	激活码(单个和多个)
		String desc;
		
		static PrizeEntity parse (JSONObject obj) {
			PrizeEntity entity = new PrizeEntity();

			entity.probability = obj.getInt("probability");
			entity.count = obj.getInt("count");
			entity.desc = obj.getString("desc");
			entity.activatecode = obj.get("activatecode");
			
			return entity;
		}

		public int getRangeStart() {
			return rangeStart;
		}

		public int getRangeEnd() {
			return rangeEnd;
		}

		public int getProbability() {
			return probability;
		}

		public int getCount() {
			return count;
		}

		public Object getActivatecode() {
			return activatecode;
		}

		public String getDesc() {
			return desc;
		}
	}

	public String getId() {
		return id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public PrizeEntity[] getEntity() {
		return entities;
	}
	
}
