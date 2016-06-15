package com.shangpin.wireless.api.api2client.domain;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.HotBrands;

public class HotBrandsListAPIResponse extends CommonAPIResponse{

	private List<HotBrands> brandList;
	public List<HotBrands> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<HotBrands> brandList) {
		this.brandList = brandList;
	}
	/**
	 * 返给客户端的json数据
	 */
	@Override
	public String obj2Json() {
		JSONObject obj = new JSONObject();
		obj.put("code", getCode());
		obj.put("msg", getMsg() == null ? "" : getMsg());
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(getCode())) {
			JSONArray list = new JSONArray();
			JSONObject hotBrand = new JSONObject();
			 if(brandList != null && brandList.size() > 0){
				 for(HotBrands brand : brandList){
					 hotBrand.put("id", brand.getBrandId());
					 hotBrand.put("name", brand.getBrandName());
					 
					 //图片
					 JSONObject iconObj = new JSONObject();
					 iconObj.put("url", brand.getImgUrl());
					 iconObj.put("w", brand.getImgWidth());
					 iconObj.put("h", brand.getImgHeight());
					 hotBrand.put("icon", iconObj);
					 
					 //头图
					 JSONObject topocObj = new JSONObject();
					 topocObj.put("url", brand.getTopImgUrl());
					 topocObj.put("w", brand.getTopImgWidth());
					 topocObj.put("h", brand.getTopImgHeight());
					 hotBrand.put("toppic", topocObj);
					 
					 list.add(hotBrand);
				 }
				 content.put("list", list);
			 }
		}
		obj.put("content", content);
		return obj.toString();
	}
}
