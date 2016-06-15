package com.shangpin.wireless.api.api2client.domain;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.FilterSearchUtil;
import com.shangpin.wireless.api.vo.SearchFacetCategoryItemVO;
import com.shangpin.wireless.api.vo.SearchFacetCategoryVO;
import com.shangpin.wireless.api.vo.SearchMerchandiseVO;

public class SpCategoryHomeAPIResponse extends CommonAPIResponse {

//	private final static String SECOND_CATEGORY_URL= Constants.SP_BASE_URL_OLD + "SPSearchChildCategory";
	private final static String SECOND_CATEGORY_URL= Constants.BASE_SEARCH_URL + "mobile/CategoryProductList";
	private final static String IMG_URL = Constants.BASE_M_SHANGPIN_URL +"styles/shangpin/images/category/";
	//性别 0 女 1 男
	private String gender;
	@Override
	public String obj2Json() {
		JSONObject clientData = new JSONObject();
		clientData.put("code", getCode());
		clientData.put("msg", getMsg() == null ? "" : getMsg());
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(getCode())) {
			if(gender.equals("0")){
				initWomenData(content);
			}else if(gender.equals("1")){
				initMenData(content);
			}
		}
		clientData.put("content", content);
		return clientData.toString();
	}
	
	/**
	 * 取得一级分类下的二级分类
	 * @param categoryId
	 * @return
	 */
	private JSONArray getChildCategoryList(String categoryId, String name, String enname, String url){
		HashMap<String, String> filterMap = FilterSearchUtil.initQueryMap(null, null, null, categoryId, null, null, null, null, null, null, null, null);
		SearchMerchandiseVO cateData = FilterSearchUtil.searchProductList(filterMap, SECOND_CATEGORY_URL, true);
		SearchFacetCategoryVO cateL3VO = cateData.getSearchFacetVO().getSearchFacetCategoryL3VO();
		List<SearchFacetCategoryItemVO> items = cateL3VO.getSearchCategoryItems();
		JSONArray array = new JSONArray();
		if(items != null && items.size() > 0){
			JSONArray temp = new JSONArray();
			JSONObject all = new JSONObject();
			all.put("id", categoryId);
			all.put("name", "全部" + name);
			all.put("enname", enname);
			all.put("icon",url);
			temp.add(all);
			for(int i =0 ;i < items.size(); i++){
				JSONObject childCate = new JSONObject();
				childCate.put("id", items.get(i).getCategoryNo());
				childCate.put("name", items.get(i).getCategoryName());
				//childCate.put("icon",url);
				temp.add(childCate);
			}
			array = temp;
		}else{
			array = new JSONArray();
			JSONObject all = new JSONObject();
			all.put("id", categoryId);
			all.put("name", "全部" +name);
			all.put("enname", enname);
			all.put("icon",url);
			array.add(all);
		}
		return array;
	}
	
	private JSONObject getChildCategoryObj(String categoryId, String name, String enname, String url){
		JSONObject obj = new JSONObject();
		obj.put("id", categoryId);
		obj.put("name", name);
		obj.put("enname", enname);
		obj.put("icon", url);
		obj.put("list", getChildCategoryList(categoryId, name, enname, url));
		return obj;
	}
	
	/**
	 * 初始化男士数据
	 * "config/config/categoryindex/men-categories.properties"
	 * @param menObj
	 */
	private void initMenData(JSONObject menObj) {
		menObj.put("newproductname", "新品");
		menObj.put("newproducticon", IMG_URL + "manNew.png");
		JSONArray menArray = new JSONArray();
		menArray.add(getChildCategoryObj("A02B01", "箱包", "BAGS", IMG_URL + "men_bags_img.jpg"));
		menArray.add(getChildCategoryObj("A02B02", "服饰", "CLOTHING", IMG_URL + "men_clothing_img.jpg"));
		menArray.add(getChildCategoryObj("A02B03", "鞋靴", "SHOES", IMG_URL + "men_shoes_img.jpg"));
		menArray.add(getChildCategoryObj("A02B04", "配饰", "ACCESSORIES", IMG_URL + "men_accessories_img.jpg"));
		menArray.add(getChildCategoryObj("A02B06", "腕表眼镜", "WATCHES&GLASSES", IMG_URL + "men_watchesglasses_img.jpg"));
		menArray.add(getChildCategoryObj("A02B07", "美妆", "BEAUTY", IMG_URL + "men_beauty_img.jpg"));
		menArray.add(getChildCategoryObj("A02B05", "家居", "HOME", IMG_URL + "men_home.jpg"));
		menObj.put("list", menArray);
	}
	/**
	 * 初始化女士数据
	 * "config/config/categoryindex/women-categories.properties"
	 * @param womenObj
	 */
	private void initWomenData(JSONObject womenObj) {
		womenObj.put("newproductname", "新品");
		womenObj.put("newproducticon", IMG_URL + "womanNew.png");
		JSONArray womenArray = new JSONArray();
		womenArray.add(getChildCategoryObj("A01B01", "箱包", "BAGS", IMG_URL + "women_bags_img.jpg"));
		womenArray.add(getChildCategoryObj("A01B02", "服饰", "CLOTHING", IMG_URL + "women_clothing_img.jpg"));
		womenArray.add(getChildCategoryObj("A01B03", "鞋靴", "SHOES", IMG_URL + "women_shoes_img.jpg"));
		womenArray.add(getChildCategoryObj("A01B04", "配饰", "ACCESSORIES", IMG_URL + "women_accessories_img.jpg"));
		womenArray.add(getChildCategoryObj("A01B06", "腕表眼镜", "WATCHES&GLASSES", IMG_URL + "women_watchesglasses_img.jpg"));
		womenArray.add(getChildCategoryObj("A01B07", "美妆", "BEAUTY", IMG_URL + "women_beauty_img.jpg"));
		womenArray.add(getChildCategoryObj("A01B05", "家居", "HOME", IMG_URL + "women_home.jpg"));
		womenObj.put("list", womenArray);
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}


