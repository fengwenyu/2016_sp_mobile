package com.shangpin.wireless.api.api2client.domain;

import java.util.Iterator;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

public class BrandAPIResponse extends CommonAPIResponse {

	private String data;
	private TreeMap<String, TreeMap<String, JSONObject>> firstAZMap;
	private TreeMap<String, TreeMap<String, JSONObject>> notAZMap;

	public BrandAPIResponse() {
		firstAZMap = new TreeMap<String, TreeMap<String, JSONObject>>();
		notAZMap = new TreeMap<String, TreeMap<String, JSONObject>>();
	}

	@Override
	public String obj2Json() {
		JSONObject result = new JSONObject();
		JSONObject obj = JSONObject.fromObject(data);
		if (obj != null) {
			String code = obj.getString("code");
			String msg = obj.getString("msg");
			result.put("code", code);
			result.put("msg", msg);
			if (code.equals(Constants.SUCCESS)) {
				JSONObject content = (JSONObject) obj.get("content");
				JSONArray array = content.getJSONArray("list");
				sortBrandArray(array);
				JSONArray reList = new JSONArray();
				reList.addAll(getContentList(firstAZMap));
				reList.addAll(getContentList(notAZMap));
				JSONObject list = new JSONObject();
				list.put("list", reList);
				result.put("content", list);
			} else {
				JSONObject list = new JSONObject();
				list.put("list", new JSONArray());
				result.put("content", list);
			}
		} else {
			result.put("code", "-3");
			result.put("msg", "");
			JSONObject list = new JSONObject();
			list.put("list", new JSONArray());
			result.put("content", list);

		}
		return result.toString();
	}

	/**
	 * 
	 * @param allBrand
	 *            全部品牌
	 * @param commandBrand
	 *            推荐品牌
	 * @return
	 */
	public String obj2Json(String allBrand, String commandBrand) {
		JSONObject result = new JSONObject();
		JSONObject content = new JSONObject();
		JSONObject allBrandObj = new JSONObject();
		JSONArray brandList = new JSONArray();
		JSONArray collectedBrandList = new JSONArray();
		if (allBrand != null && !"".equals(allBrand)) {
			JSONObject obj = JSONObject.fromObject(allBrand);
			result.put("code", obj.getString("code"));
			result.put("msg", obj.getString("code"));
			JSONObject brandContent = obj.getJSONObject("content");
			
			if (brandContent != null && !brandContent.isEmpty()) {
				JSONArray list = brandContent.getJSONArray("brandList");
				if (list != null && !list.isEmpty()) {
					for (int i = 0; i < list.size(); i++) {
						JSONObject brandObj = list.getJSONObject(i);
						JSONObject capitalObj = new JSONObject();
						capitalObj.put("capital", brandObj.getString("capital"));
						JSONArray brandArray = brandObj.getJSONArray("brands");
						JSONArray brands = new JSONArray();
						if (brandArray != null && !brandArray.isEmpty()) {
							for (int j = 0; j < brandArray.size(); j++) {
								JSONObject brand = brandArray.getJSONObject(j);
								brand.put("nameEN", brand.get("nameEN"));
								brand.put("nameCN", brand.get("nameCN"));
								brand.put("name", brand.get("nameCN"));
								brand.put("isFlagship", brand.get("isFlagship"));
								brand.put("pic", brand.get("pic"));
								brand.put("type", "3");
                                brand.put("name", "");
                                brand.put("refContent", brand.get("id"));
								brands.add(brand);
							}
							capitalObj.put("brands", brands);
							brandList.add(capitalObj);
						}
					}

				}
			}
		} 
		allBrandObj.put("brandList", brandList);
		if (commandBrand != null && !"".equals(commandBrand)) {
			JSONObject collectBrand = JSONObject.fromObject(commandBrand);
			try {
			    JSONObject obj = collectBrand.getJSONObject("content");
			    if(obj != null){
    				collectedBrandList = obj.getJSONArray("collectedBrandlist");
    				for (int i = 0; i < brandList.size(); i++) {
    					JSONObject brandJson = brandList.getJSONObject(i);
    					JSONObject brand=new JSONObject();
    					brand.put("nameEN", brandJson.get("nameEN"));
                        brand.put("nameCN", brandJson.get("nameCN"));
                        brand.put("name", brandJson.get("nameCN"));
                        brand.put("isFlagship", brandJson.get("isFlagship"));
                        brand.put("pic", brandJson.get("pic"));
                        brand.put("type", "3");
                        brand.put("refContent", brandJson.get("id"));
    					brandList.element(i, brand);
    				}
                }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		allBrandObj.put("collectedBrandlist", collectedBrandList);
		content.put("allBrand", allBrandObj);
		result.put("content", content);
		return result.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
    private JSONArray getContentList(TreeMap<String, TreeMap<String, JSONObject>> map) {
		JSONArray reList = new JSONArray();
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			JSONObject reObj = new JSONObject();
			String key = (String) iterator.next();
			reObj.put("capital", key);
			TreeMap brandListMap = map.get(key);
			JSONObject[] brandList = (JSONObject[]) brandListMap.values().toArray(new JSONObject[0]);
			reObj.put("brands", brandList);
			reList.add(reObj);
		}
		return reList;
	}

	/**
	 * 给字段排序
	 * 
	 * @param array
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
    private void sortBrandArray(JSONArray array) {
		for (int i = 0; i < array.size(); i++) {
			JSONObject temp = array.getJSONObject(i);
			String capital = temp.getString("capital");
			if (capital != null && !"".equals(capital)) {
				JSONArray brandsArray = temp.getJSONArray("brands");
				TreeMap thirdBrand = new TreeMap();
				if (brandsArray != null && brandsArray.size() > 0) {
					for (int j = 0; j < brandsArray.size(); j++) {
						JSONObject brand = brandsArray.getJSONObject(j);
						String name = brand.getString("name").toLowerCase();
						thirdBrand.put(name, brand);
					}
				}
				char cap = capital.toCharArray()[0];
				// 字母 a-z A-Z
				if ((cap >= 65 && cap <= 90) || (cap >= 97 && cap <= 122)) {
					firstAZMap.put(capital, thirdBrand);
				} else {
					notAZMap.put(capital, thirdBrand);
				}
			}
		}
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
