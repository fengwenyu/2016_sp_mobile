package com.shangpin.wireless.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.vo.SearchFacetCategoryItemVO;
import com.shangpin.wireless.api.vo.SearchFacetCategoryVO;
import com.shangpin.wireless.api.vo.SearchFacetsVO;
import com.shangpin.wireless.api.vo.SearchMerchandiseVO;

public class CategoryCasheUtil {
	protected static final Log log = LogFactory.getLog(CategoryCasheUtil.class.getSimpleName());
	// 将分类的筛选条件放入内存中 应该加上一个半小时更新一次
	private static Hashtable<String, SearchFacetsVO> categorys = new Hashtable<String, SearchFacetsVO>();
	public static void updateCache () {
		log.debug("update ProductListServlet cache start" + categorys.size());
		try{
		for (String key : categorys.keySet()) {
			categorys.put(key, getCategoryInterface(key));
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("update ProductListServlet cache finished " + categorys.size());
	}
	
	public static SearchFacetsVO getCategory (String categoryno, boolean fromCache) {
		SearchFacetsVO data =  categorys.get(categoryno);
		if (data == null || !fromCache) {
			data = getCategoryInterface (categoryno);
			categorys.put(categoryno, data);
		}
		return data;
	}

	
	/**
	 * 查询的是3级 且 3级下面有4级 返回 3级和四级 否则返回2 级和3级 
	 * 查询的是2级 返回2级和3级
	 * @param categoryno
	 * @return
	 */
	private static SearchFacetsVO getCategoryInterface (String categoryno) {
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			SearchFacetsVO searchFacetVO = null;
			map.put("categoryNO", categoryno);
			if(categoryno.length() == 12){
				map.put("categoryNO", categoryno.substring(0, 9));
				searchFacetVO = getSearchMerchandiseVO(categoryno.substring(0, 9));
			}else if(categoryno.length() == 9){//查询3级 结果如果没有4级  用2级重新查询下3级
				searchFacetVO = getSearchMerchandiseVO(categoryno);
				SearchFacetCategoryVO cate4 = searchFacetVO.getSearchFacetCategoryL4VO();
				if(cate4 == null || cate4.getSearchCategoryItems() == null || cate4.getSearchCategoryItems().size() == 0){
					searchFacetVO = getSearchMerchandiseVO(categoryno.substring(0, 6));
					searchFacetVO.setSearchFacetCategoryL4VO(null);
				}else{//
					List<SearchFacetCategoryItemVO> items4 = cate4.getSearchCategoryItems();
					List<SearchFacetCategoryItemVO> itemsTarget = new ArrayList<SearchFacetCategoryItemVO>();
					if(items4 != null && items4.size() > 0){
						for(SearchFacetCategoryItemVO item : items4){
							if(item.getCategoryNo().startsWith(categoryno.substring(0, 9))){
								itemsTarget.add(item);
							}
						}
					}
					if(itemsTarget.size() == 0){
						searchFacetVO = getSearchMerchandiseVO(categoryno.substring(0, 6));
						searchFacetVO.setSearchFacetCategoryL4VO(null);
					}
				}
			}else{
				searchFacetVO = getSearchMerchandiseVO(categoryno);
			}
			return searchFacetVO;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CategoryCasheUtil :"+e);
		}
		return null;
	}
	
	private static SearchFacetsVO getSearchMerchandiseVO(String categoryNo){
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			map.put("categoryNO", categoryNo);
			String filterurl = Constants.BASE_SEARCH_URL + "mobile/CategoryProductList";
			String filterdata = WebUtil.readContentFromGet(filterurl, map);
			if (null != filterdata && filterdata.length() > 0) {
				SearchMerchandiseVO filterlist = ParseXmlUtil.parseSearchResultXml(filterdata, true);
				return  filterlist.getSearchFacetVO();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CategoryCasheUtil :"+e);
		}
		return null;
	}
}
