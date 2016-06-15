package com.shangpin.wireless.api.api2client.domain;

import java.text.ParseException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.shangpin.wireless.api.domain.Flagship;
import com.shangpin.wireless.api.util.FlagshopEntrance;
public class FlagshipListAPIResponse extends CommonAPIResponse{


	/**
	 * 返给客户端的json数据
	 */
	@Override
	public String obj2Json() {
		JSONObject obj = new JSONObject();
		obj.put("code", getCode());
		obj.put("msg", getMsg() == null ? "" : getMsg());
		JSONArray group = new JSONArray();
		JSONObject list = new JSONObject();
		try {
			List<Flagship> flagshopList =FlagshopEntrance.getFlagshopContent();
			for(Flagship flagist : flagshopList){
				 JSONObject flagshipJson1 = new JSONObject();
				 flagshipJson1.put("id", flagist.getId());
				 flagshipJson1.put("name", flagist.getTitle());
				 flagshipJson1.put("flagshipurl", flagist.getUrl());

				 //图片
				 JSONObject iconObj = new JSONObject();
				 iconObj.put("http", flagist.getImgUrl());
				 iconObj.put("w", "640");
				 iconObj.put("h", "404");
				 flagshipJson1.put("icon",iconObj);
				 
				 //top
				 JSONObject topObj = new JSONObject();
				 topObj.put("http", flagist.getImgUrl());
				 topObj.put("w", "640");
				 topObj.put("h", "404");
				 flagshipJson1.put("toppic",topObj);
				 
				 group.add(flagshipJson1);
			}
			list.put("list", group);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		obj.put("content", list);
		return obj.toString();
			


		
	}
}
