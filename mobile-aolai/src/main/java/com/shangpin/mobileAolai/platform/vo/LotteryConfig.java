package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LotteryConfig implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 7276082758396168926L;
    private String id;
	private Date startTime;
	private Date endTime;
	private PrizeEntity[] entities;
	private String desc;
	private Map<String,String> viptimesmap;
	
	public static LotteryConfig parse (String configStr) throws Exception {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		LotteryConfig config = new LotteryConfig();
		
		JSONObject obj = JSONObject.fromObject(configStr);
		config.id = obj.getString("id");
		config.startTime = sdf.parse(obj.getString("starttime"));
		config.endTime = sdf.parse(obj.getString("endtime"));
		config.desc=obj.getString("desc");
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
		JSONArray viptimes = obj.getJSONArray("viptimes");
		JSONObject vipobj=null;
		config.viptimesmap=new HashMap<String,String>();
		for (int i=0;i<viptimes.size();i++) {
			vipobj=viptimes.getJSONObject(i);
			config.viptimesmap.put(vipobj.getString("viplv"), vipobj.getString("vipcount"));
		}
		return config;
	}

	public static class PrizeEntity {
		int rangeStart;
		int rangeEnd;
		int probability;
		int daycount;
		int maxcount;
		int usermaxcount;
		String level;
		String levelname;
		Object activatecode;	//	激活码(单个和多个)，如果没有设定激活码那么使用其他方式派奖
		String imageSplit;	//拼接图
		
		
		static PrizeEntity parse (JSONObject obj) {
			PrizeEntity entity = new PrizeEntity();

			entity.probability = obj.getInt("probability");
			entity.daycount = obj.getInt("daycount");
			entity.maxcount = obj.getInt("maxcount");
			entity.usermaxcount = obj.getInt("usermaxcount");
			entity.activatecode = obj.has("activatecode")?obj.get("activatecode"):null;
			entity.level = obj.getString("level");
			entity.levelname = obj.getString("levelname");
			entity.imageSplit = obj.getString("imageSplit");
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

		public String getLevel() {
			return level;
		}

		public String getLevelname() {
			return levelname;
		}

		public int getDaycount() {
			return daycount;
		}

		public int getMaxcount() {
			return maxcount;
		}

		public Object getActivatecode() {
			return activatecode;
		}

		public int getUsermaxcount() {
			return usermaxcount;
		}

		public String getImageSplit() {
			return imageSplit;
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

	public Map<String, String> getViptimesmap() {
		return viptimesmap;
	}

	public String getDesc() {
		return desc;
	}
	
}
