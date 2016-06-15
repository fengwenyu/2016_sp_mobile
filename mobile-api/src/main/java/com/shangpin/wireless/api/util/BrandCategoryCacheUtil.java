package com.shangpin.wireless.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.base.service.AoLaiService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.service.impl.ShangPinServiceImpl;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.Flagship;
import com.shangpin.wireless.api.vo.SearchFacetCategoryItemVO;
import com.shangpin.wireless.api.vo.SearchFacetCategoryVO;
import com.shangpin.wireless.api.vo.SearchFacetsVO;
import com.shangpin.wireless.api.vo.SearchMerchandiseVO;

public class BrandCategoryCacheUtil {
	protected static final Log log = LogFactory.getLog(BrandCategoryCacheUtil.class.getSimpleName());

	// 将分类的筛选条件放入内存中
	private static Hashtable<String, SearchFacetsVO> categorys = new Hashtable<String, SearchFacetsVO>();
	public static void updateCache () {
		log.debug("update BrandProductListServlet cache start" + categorys.size());
		try{
		for (String key : categorys.keySet()) {
			categorys.put(key, getCategoryInterface(key));
		}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BrandCategoryCacheUtil :"+e);
		}
		log.debug("update BrandProductListServlet cache finished " + categorys.size());
	}
    public static SearchFacetsVO getCategory (String brandno, boolean fromCache) {
		SearchFacetsVO data = categorys.get(brandno);
		if (data == null || !fromCache) {
			data = getCategoryInterface (brandno);
			categorys.put(brandno, data);
		}
		return data;
	}

	public static SearchFacetsVO getCategoryInterface (String brandno) {
		try{
			List<Flagship> flagshopList = FlagshopEntrance.getFlagshopContent();
			for(Flagship flagist : flagshopList){
				if (brandno.equals(flagist.getId())) {
					
					try {
						String data = ApplicationContextUtil.get("ac").getBean(ShangPinService.class).findFlagShip(brandno);
						JSONObject jsonObj = JSONObject.fromObject(data);
						if (Constants.SUCCESS.equals(jsonObj.get("code"))) {
							JSONObject obj = (JSONObject) jsonObj.get("content");
							JSONArray navs = obj.getJSONArray("navs");
							List<SearchFacetCategoryItemVO> searchCategoryItemVOs = new ArrayList<SearchFacetCategoryItemVO>();
							SearchFacetCategoryVO searchFacetNavigationVO = new SearchFacetCategoryVO();
							if (navs.size() > 0) {
								for (int i=0;i<navs.size();i++) {
									JSONObject nav = navs.getJSONObject(i);
									SearchFacetCategoryItemVO searchNavItemVO = new SearchFacetCategoryItemVO();
									searchNavItemVO.setCategoryNo(nav.getString("id"));
									searchNavItemVO.setCategoryName(nav.getString("name"));
									final String navtype = nav.getString("type");
									if ("1".equals(navtype) || "2".equals(navtype)) {
										searchCategoryItemVOs.add(searchNavItemVO);
									} else {
										JSONArray childnavs = nav.getJSONArray("childnavs");
										for (int k=0;k<childnavs.size();k++) {
											JSONObject childnav = childnavs.getJSONObject(k);
											searchNavItemVO = new SearchFacetCategoryItemVO();
											searchNavItemVO.setCategoryNo(childnav.getString("id"));
											searchNavItemVO.setCategoryName(childnav.getString("name"));
											final String childnavtype = childnav.getString("type");
											if ("1".equals(childnavtype) || "2".equals(childnavtype)) {
												searchCategoryItemVOs.add(searchNavItemVO);
											}
										}
									}
								}
								searchFacetNavigationVO.setSearchCategoryItems(searchCategoryItemVOs);
								
								SearchFacetsVO facets = new SearchFacetsVO();
								facets.setSearchFacetNavigationVO(searchFacetNavigationVO);
								return facets;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("brandNO", brandno);
			String filterurl = null;
			filterurl = Constants.BASE_SEARCH_URL + "mobile/BrandProductList";
			String filterdata = WebUtil.readContentFromGet(filterurl, map);
			if (null != filterdata && filterdata.length() > 0) {
				SearchMerchandiseVO filterlist = null;
				filterlist = ParseXmlUtil.parseSearchResultXml(filterdata, true);
				SearchFacetsVO data = filterlist.getSearchFacetVO();
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
