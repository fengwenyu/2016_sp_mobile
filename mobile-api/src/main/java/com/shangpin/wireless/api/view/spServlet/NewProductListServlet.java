package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.SPGoodListAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.FilterSearchUtil;
import com.shangpin.wireless.api.util.ParseXmlUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.vo.SearchFacetBrandItemVO;
import com.shangpin.wireless.api.vo.SearchFacetCategoryItemVO;
import com.shangpin.wireless.api.vo.SearchFacetCategoryVO;
import com.shangpin.wireless.api.vo.SearchFacetsVO;
import com.shangpin.wireless.api.vo.SearchMerchandiseVO;

/**
 * 获取尚品品类品牌商品列表
 * 
 * @Author:wangwenguan
 * @CreatDate: 2013-12-28
 */
public class NewProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final static Log log = LogFactory.getLog(NewProductListServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		final String userId = request.getParameter("userid");
		final String userlv = request.getParameter("userlv");	//	用户级别
		final String gender = request.getParameter("gender");
		final String categoryid = request.getParameter("categoryid");
		final String brandid = request.getParameter("brandid");
		final String firstpropid = request.getParameter("firstpropid");	//	颜色
		final String productsize = request.getParameter("productsize");	//	尺码
		final String pricerange = request.getParameter("pricerange");	//	价格区间
		final String sort = request.getParameter("sort");	//	排序
		final String pageindex = request.getParameter("pageindex");
		final String pagesize = request.getParameter("pagesize");
		final String update = request.getParameter("update");

		if (update != null) {
			updateCache();
		}
		String listUrl = Constants.BASE_SEARCH_URL + "mobile/NewestList";
		if (StringUtil.isNotEmpty(pageindex, pagesize, gender)) {
			int pageStart = (Integer.parseInt(pageindex) - 1) * Integer.parseInt(pagesize) + 1;
			int pageEnd = pageStart + Integer.parseInt(pagesize) - 1;
			String listurl = null;
			String filterurl = null;
			//商品data
			HashMap<String, String> productMap = FilterSearchUtil.initQueryMap(userlv, userId, brandid, categoryid, null, firstpropid, pricerange, gender, productsize, sort, pageStart, pageEnd);
			//品类决定品牌
			HashMap<String, String> brandMap = FilterSearchUtil.initQueryMap(userlv, userId, null, categoryid, null, null, null, gender, null, null, null, null);
			//筛选 品牌 品类决定
			HashMap<String, String> filterMap = FilterSearchUtil.initQueryMap(userlv, userId, brandid, categoryid, null, null, null, gender, null, null, null, null);
			try {
				SPGoodListAPIResponse apiResponse = new SPGoodListAPIResponse();
				apiResponse.setSort(sort);
				apiResponse.setSearchCategory(categoryid);
				SearchMerchandiseVO productListVO = FilterSearchUtil.searchProductList(productMap, listUrl, false);
				apiResponse.setCode(productListVO.getStatus());
				apiResponse.setMsg(productListVO.getDiscription());
				apiResponse.setProductList(productListVO.getSearchProductVO());
				//筛选条件
				SearchMerchandiseVO filterListVO = FilterSearchUtil.searchProductList(filterMap, listUrl, true);
				SearchFacetsVO searchFacetsVO = filterListVO.getSearchFacetVO();
				
				//	保持相同的二级类
				SearchFacetCategoryVO category = getCategory(gender, true);
				searchFacetsVO.setSearchFacetCategoryL2VO(category);
				
				SearchMerchandiseVO brandListVO = FilterSearchUtil.searchProductList(brandMap, listUrl, true);
				List<SearchFacetBrandItemVO> brandList = brandListVO.getSearchFacetVO().getSearchFacetBrandVO().getSearchFacetBrandItems();
				Collections.sort(brandList);
				searchFacetsVO.getSearchFacetBrandVO().setSearchFacetBrandItems(brandList);
				
				apiResponse.setFacets(searchFacetsVO);
				String result = apiResponse.obj2Json("new");
				response.getWriter().print(result);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("NewProductListServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "shangpinnewproductlist", "gender", gender, "pageindex", pageindex, "pagesize", pagesize,
									"categoryid", categoryid,				
									"brandid", brandid,
									"userid", userId,
									"userlv", userlv,
									"firstpropid", firstpropid,
									"productsize", productsize,
									"pricerange", pricerange,
									"sort", sort);
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// 将分类的筛选条件放入内存中
	private static Hashtable<String, SearchFacetCategoryVO> Categorys = new Hashtable<String, SearchFacetCategoryVO>();
	private static void updateCache () {
		log.debug("update NewProductListServlet cache " + Categorys.size());
		Hashtable<String, SearchFacetCategoryVO> categorys = new Hashtable<String, SearchFacetCategoryVO>();
		try{
		for (String key : Categorys.keySet()) {
			categorys.put(key, getCategoryInterface(key));
		}
		Categorys = categorys;
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("update NewProductListServlet cache finished " + Categorys.size());
	}
	
	/*private */static SearchFacetCategoryVO getCategory (String gender, boolean fromCache) {
		SearchFacetCategoryVO data = Categorys.get(gender);
		if (data == null || !fromCache) {
			data = getCategoryInterface (gender);
			Categorys.put(gender, data);
		}
		return data;
	}

	private static SearchFacetCategoryVO getCategoryInterface (String gender) {
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			map.put("gender", gender);
			String filterurl = null;
			filterurl = Constants.BASE_SEARCH_URL + "mobile/NewestList";
			String filterdata = WebUtil.readContentFromGet(filterurl, map);
			SearchFacetCategoryVO category2 = null;
			if (null != filterdata && filterdata.length() > 0) {
				SearchMerchandiseVO filterlist = ParseXmlUtil.parseSearchResultXml(filterdata, true);
				SearchFacetsVO searchFacetsVO = filterlist.getSearchFacetVO();
				category2 = searchFacetsVO.getSearchFacetCategoryL2VO();
				List<SearchFacetCategoryItemVO> list = category2.getSearchCategoryItems();
				if(list != null && list.size() > 0){
					List<SearchFacetCategoryItemVO> temp = new ArrayList<SearchFacetCategoryItemVO>();
					for(int i = 0; i < list.size(); i++){
						SearchFacetCategoryItemVO cateItemVo = list.get(i);
						String cateNo = cateItemVo.getCategoryNo();
						if(gender.equals("1") && cateNo.startsWith("A02")){
							temp.add(cateItemVo);
						}else if(gender.equals("0") && cateNo.startsWith("A01")){
							temp.add(cateItemVo);
						}
					}
					category2.setSearchCategoryItems(temp);
				}else{
					category2.setSearchCategoryItems(null);
				}
				return category2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
