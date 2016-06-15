package com.shangpin.wireless.api.api2client.domain;

import java.util.HashMap;
import java.util.HashSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 活动列表查询条件
 * @author xupengcheng
 *
 */
public class SubjectQueryConditionResponse extends CommonAPIResponse{
	private JSONArray allSizeList = new JSONArray();//全部尺码
	@SuppressWarnings("rawtypes")
    private HashSet allKey = new HashSet();
	@SuppressWarnings("rawtypes")
    @Override
	public String obj2Json(String data) {
		JSONObject result = new JSONObject();
		JSONObject dataContent = new JSONObject();
		JSONObject obj = JSONObject.fromObject(data);
		if(obj != null){
			String code = obj.getString("code");
			String msg = obj.getString("msg");
			JSONObject content = obj.getJSONObject("content");
			result.put("code", code);
			result.put("msg", msg);
			Object size1 = content.get("size");
			HashMap map = new HashMap();
			if(size1 != null){
				if(size1 instanceof JSONObject){
					
				}else if(size1 instanceof JSONArray){
					JSONArray size = content.getJSONArray("size");
					map = initCategoryMap(size);
				}
			}
			Object categoryObj = content.get("category");
			JSONArray sizeArray = new JSONArray();
			JSONArray category = null;
			if(categoryObj != null && categoryObj instanceof JSONArray){
				category = (JSONArray) categoryObj;
			}
			if(category != null && category.size() > 0){
				for(int i =0; i < category.size(); i++){
					JSONObject cateSizeObj = new JSONObject();
					JSONObject target = category.getJSONObject(i);
					cateSizeObj.put("key", "categoryNo");
					cateSizeObj.put("value", target.getString("categoryNo"));
					cateSizeObj.put("name", target.getString("categoryName"));
					cateSizeObj.put("list", map.get(target.getString("categoryNo")) == null? getSizeAll() : map.get(target.getString("categoryNo")));
					sizeArray.add(cateSizeObj);
				}
			}
			//给分类添加默认的全部，其中尺码的为所有尺码去重的总和
			JSONObject all = new JSONObject();
			all.put("key", "categoryNo");
			all.put("value", "all");
			all.put("name", "全部");
			JSONObject obj0 = new JSONObject();
			obj0.put("id", "all");
			obj0.put("name", "全部");
			allSizeList.add(0, obj0);
			all.put("list", allSizeList);
			sizeArray.add(0, all);
			dataContent.put("list", sizeArray);
			result.put("content", dataContent);
		}
		return result.toString();
	}
	private JSONArray getSizeAll(){
		JSONArray list = new JSONArray();
		JSONObject obj0 = new JSONObject();
		obj0.put("id", "all");
		obj0.put("name", "全部");
		list.add(obj0);
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    private HashMap initCategoryMap(JSONArray category){
		HashMap map = new HashMap();
		if(category != null && category.size() > 0){
			for(int i =0; i < category.size(); i++){
				JSONObject obj = category.getJSONObject(i);
				String categoryNo = obj.getString("categoryNo");
				JSONArray list = new JSONArray();
				JSONArray sizeList = obj.getJSONArray("data");
				if(sizeList != null && sizeList.size() > 0){
					JSONObject obj0 = new JSONObject();
					obj0.put("id", "all");
					obj0.put("name", "全部");
					list.add(obj0);
					for(int j =0; j < sizeList.size(); j++){
						JSONObject obj1 = new JSONObject();
						obj1.put("id", sizeList.getString(j));
						obj1.put("name", sizeList.getString(j));
						list.add(obj1);
						if(!allKey.contains(sizeList.getString(j))){
							allKey.add(sizeList.getString(j));
							allSizeList.add(obj1);
						}
					}
				}
				map.put(categoryNo, list);
			}
		}
		return map;
	}
}
