package com.shangpin.mobileShangpin.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.mobileShangpin.platform.vo.AccountVO;
import com.shangpin.mobileShangpin.platform.vo.BrandVo;
import com.shangpin.mobileShangpin.platform.vo.CategoryIndexVO;
import com.shangpin.mobileShangpin.platform.vo.CategoryVo;
import com.shangpin.mobileShangpin.platform.vo.SearchBrandItemVO;
import com.shangpin.mobileShangpin.platform.vo.SearchCategoryItemVO;
import com.shangpin.mobileShangpin.platform.vo.SearchCategoryVO;
import com.shangpin.mobileShangpin.platform.vo.SearchFacetBrandVO;
import com.shangpin.mobileShangpin.platform.vo.SearchFacetsVO;
import com.shangpin.mobileShangpin.platform.vo.SearchMerchandiseVO;

/**
 * 筛选工具类
 * @author xupengcheng
 * @createDate 2014-1-22 下午03:25:07
 *
 */
public class MutilSelectUtil {
	private final static String SECOND_CATEGORY_URL= Constants.SEARCH_BASE_URL + "mobile/CategoryProductList";
	private static long lastModifyTime ;
	private static Map<String, List<SearchCategoryItemVO>> map;
	/**
	 * 取得导航和品类的关系
	 * @author xupengcheng
	 * @createDate 2014-2-12 下午12:07:19
	 * @param brandNO
	 * @return
	 */
	public static List<SearchCategoryItemVO> getNavCategory(String brandNO){
		boolean flag = false;
		List<SearchCategoryItemVO> list = null;
		if(map == null){
			 map = Collections.synchronizedMap(new HashMap<String, List<SearchCategoryItemVO>>());
			 flag = true;
		}else{
			if(map.containsKey(brandNO)){
				list = map.get(brandNO);
			}else{
				flag = true;
			}
		}
		Long now = System.currentTimeMillis();
		if (now - lastModifyTime > 1800000 || flag ) { // 半小时更新一次
			list = new ArrayList<SearchCategoryItemVO>();
			HashMap filterMap = new HashMap();
			filterMap.put("brandNO", brandNO);
			String data = null;
			try {
				// 获取活动xml格式字符串
				data = WebUtil.readContentFromGet(Constants.BASE_SP_URL +"flagship", filterMap);
			} catch (Exception e) {
				e.printStackTrace();
			}    
			JSONObject obj = JSONObject.fromObject(data);
			if(obj.get("content") != null && Constants.SUCCESS.equals(obj.get("code"))){
				JSONObject content = (JSONObject) obj.get("content");
				if(content != null && !"[]".equals(content.toString())){
					JSONArray navs = (JSONArray) content.get("navs");
					for(int i =0;i < navs.size(); i++){
						JSONObject navObj = (JSONObject) navs.get(i);
						SearchCategoryItemVO categoryItemVO = new SearchCategoryItemVO();
						categoryItemVO.setCode(navObj.getString("id"));
						categoryItemVO.setName(navObj.getString("name"));
						list.add(categoryItemVO);
					}
					map.put(brandNO, list);
				}
			}
			if(!flag){
				lastModifyTime = System.currentTimeMillis();
			}
		}
		return list;
	}
	
	/**
	 * 初始化查询参数
	 * @author xupengcheng
	 * @createDate 2014-1-6 下午02:46:08
	 * @param brandNO
	 * @param categoryNO
	 * @param primaryColorId
	 * @param price
	 * @param productSize
	 * @param order
	 * @param start
	 * @param end
	 * @return
	 */
	public static HashMap<String, String> initQueryMap(String brandNO, String categoryNO, String navigationID, String primaryColorId, String price,String gender,
			String productSize, String order, Integer start, Integer end) {
		HashMap<String, String> map = new HashMap<String, String>();
		if(price != null && !"".equals(price) && price.indexOf("以上") > -1){
			price = price.substring(0, price.indexOf("以上")) + "~";
		}
		AccountVO user = WebUtil.getSessionUser();
		String userid = "";
		if (null != user && null != user.getUserid()) {
			userid = user.getUserid();
			map.put("userID", userid);
		}
		
		if(navigationID != null && !"".equals(navigationID)){
			map.put("navigationID", navigationID);
		}
		
		if(categoryNO != null && !"".equals(categoryNO)){
			map.put("categoryNO", categoryNO);
		}
		if(brandNO != null && !"".equals(brandNO)){
			map.put("brandNO", brandNO);
		}
		if(primaryColorId != null && !"".equals(primaryColorId)){
			map.put("primaryColorId", primaryColorId);
		}
		if(productSize != null && !"".equals(productSize)){
			map.put("productSize", productSize);
		}
		if(start != null){
			map.put("start", String.valueOf(start));
		}
		if(end != null){
			map.put("end", String.valueOf(end));
		}
		
		if(gender != null && !"".equals(gender)){
			map.put("gender", gender);
		}
		
		String orderField = "";
		String orerType = "";
		if(order != null && !"".equals(order.trim())){
			String[] orderType = order.split(" ");
			if(orderType != null){
				if(orderType.length > 1){
					orerType = orderType[1];
				}
				orderField = orderType[0]; 
			}
		}
		return map;
	}
	
	public static SearchMerchandiseVO searchProductList(Map<String, String> map, String url){
		String data = null;
		try {
			// 获取活动xml格式字符串
			data = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
		}                                                         
		SearchMerchandiseVO searchMerchandiseVO = ParseXmlUtil.parseXml(data, true);
		return searchMerchandiseVO;
	}
	
	
	/**
	 * 过滤不是同一父类的品类 且为显示的
	 * @author xupengcheng
	 * @createDate 2014-1-22 下午01:51:32
	 * @param list
	 * @param parentCode
	 * @return
	 */
	public static List<SearchCategoryItemVO> getSameCategoryItemVO(List<SearchCategoryItemVO> list, String parentCode){
		List<SearchCategoryItemVO> destCategoryItemList = new ArrayList<SearchCategoryItemVO>();
		String firstCode = "";
		if(parentCode == null || "".equals(parentCode)){
			return destCategoryItemList;
		}
		if(parentCode.length() > 6){
			firstCode = parentCode.substring(0, 6);
		}else{
			firstCode = parentCode;
		}
		for(int i = 0; i < list.size(); i++){
			SearchCategoryItemVO categoryItemVO = list.get(i);
			String itemCode = categoryItemVO.getCode() == null ? "" : categoryItemVO.getCode();
			String status = categoryItemVO.getStatus() == null ? "" : categoryItemVO.getStatus();
			if(itemCode.startsWith(firstCode) && !"".equals(status) && "1".equals(status)){
				destCategoryItemList.add(categoryItemVO); 
			}
		}
		return destCategoryItemList;
	}
	
	/**
	 * 取的品类子类
	 * @param categoryId
	 * @param gender
	 * @return
	 */
	public static List<CategoryIndexVO> getChildCategoryList(String categoryId, String gender){
		HashMap<String, String> filterMap = MutilSelectUtil.initQueryMap(null, categoryId, null, null, null, gender, null, null, null, null);
		SearchMerchandiseVO cateData = MutilSelectUtil.searchProductList(filterMap, SECOND_CATEGORY_URL);
	    SearchCategoryVO cateL3VO = cateData.getSearchFacesVO().getSearchClv3VO();
		List<SearchCategoryItemVO> items = cateL3VO.getSearchCategoryItemVO();
		List<CategoryIndexVO> dataList = new ArrayList<CategoryIndexVO>();
		if(items != null && items.size() > 0){
			for(int i = 0; i < items.size() ; i++){
				SearchCategoryItemVO cateObj = items.get(i);
				CategoryIndexVO categoryIndexVO = new CategoryIndexVO();
				categoryIndexVO.setCode(cateObj.getCode());
				categoryIndexVO.setName(cateObj.getName());
				dataList.add(categoryIndexVO);
			}
		}
		return dataList;
	}
	
	/**
	 * 首页品类子类
	 * @param categoryId
	 * @param gender
	 * @return
	 */
	public static List<CategoryVo> getIndexChildCategoryList(String categoryId, String gender){
		HashMap<String, String> filterMap = MutilSelectUtil.initQueryMap(null, categoryId, null, null, null, gender, null, null, null, null);
		SearchMerchandiseVO cateData = MutilSelectUtil.searchProductList(filterMap, SECOND_CATEGORY_URL);
	    SearchCategoryVO cateL3VO = cateData.getSearchFacesVO().getSearchClv3VO();
		List<SearchCategoryItemVO> items = cateL3VO.getSearchCategoryItemVO();
		List<CategoryVo> dataList = new ArrayList<CategoryVo>();
		if(items != null && items.size() > 0){
			for(int i = 0; i < items.size() ; i++){
				SearchCategoryItemVO cateObj = items.get(i);
				CategoryVo categoryIndexVO = new CategoryVo();
				categoryIndexVO.setId(cateObj.getCode());
				categoryIndexVO.setName(cateObj.getName());
				dataList.add(categoryIndexVO);
			}
		}
		return dataList;
	}
	
}
