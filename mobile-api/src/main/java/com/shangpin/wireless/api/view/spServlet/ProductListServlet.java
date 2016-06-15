package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.SPGoodListAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.CategoryCasheUtil;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.FilterSearchUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.vo.SearchFacetBrandItemVO;
import com.shangpin.wireless.api.vo.SearchFacetsVO;
import com.shangpin.wireless.api.vo.SearchMerchandiseVO;

/**
 * 获取尚品品类品牌商品列表
 * 
 * @Author:wangwenguan
 * @CreatDate: 2013-12-28
 */
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ProductListServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		final String userId = request.getParameter("userid");
		final String userlv = request.getParameter("userlv");	//	用户级别
		final String categoryid = request.getParameter("categoryid");
		final String brandid = request.getParameter("brandid");
		final String firstpropid = request.getParameter("firstpropid");	//	颜色
		final String productsize = request.getParameter("productsize");	//	尺码
		final String pricerange = request.getParameter("pricerange");	//	价格区间
		final String sort = request.getParameter("sort");	//	排序
		final String pageindex = request.getParameter("pageindex");
		final String pagesize = request.getParameter("pagesize");
		final String update = request.getParameter("update");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		String filterurl = Constants.BASE_SEARCH_URL + "mobile/CategoryProductList";

		if (update != null) {
			CategoryCasheUtil.updateCache();
		}
		if (StringUtil.isNotEmpty(pageindex, pagesize, categoryid)) {
			try {
				int pageStart = (Integer.parseInt(pageindex) - 1) * Integer.parseInt(pagesize) + 1;
				int pageEnd = pageStart + Integer.parseInt(pagesize) - 1;
				//数据map
				HashMap<String, String> productListMap = FilterSearchUtil.initQueryMap(userlv, userId, brandid, categoryid, null, firstpropid, pricerange, null, productsize, sort, pageStart, pageEnd);
				//品牌
				HashMap<String, String> brandMap = FilterSearchUtil.initQueryMap(userlv, userId, null, categoryid, null, null, null, null, null, null, null, null);
				//筛选
				HashMap<String, String> filterMap = FilterSearchUtil.initQueryMap(userlv, userId, brandid, categoryid, null, null, null, null, null, null, null, null);
				
				SearchMerchandiseVO productListVO = FilterSearchUtil.searchProductList(productListMap, filterurl, false);
				SearchFacetsVO searchFacetsVO = productListVO.getSearchFacetVO();
				SPGoodListAPIResponse apiResponse = new SPGoodListAPIResponse();
				apiResponse.setSearchCategory(categoryid);
				apiResponse.setCode(productListVO.getStatus());
				apiResponse.setSort(sort);
				apiResponse.setMsg(productListVO.getDiscription());
				apiResponse.setProductList(productListVO.getSearchProductVO());
				searchFacetsVO = CategoryCasheUtil.getCategory(categoryid, true);
				//xupengcheng 品牌用品类控制
				SearchMerchandiseVO brandVO = FilterSearchUtil.searchProductList(brandMap, filterurl, true);
				List<SearchFacetBrandItemVO> brandList = brandVO.getSearchFacetVO().getSearchFacetBrandVO().getSearchFacetBrandItems();
				Collections.sort(brandList);
				searchFacetsVO.getSearchFacetBrandVO().setSearchFacetBrandItems(brandList);
				//xupengcheng 筛选条件由 品类 品牌一起控制
				SearchMerchandiseVO filter = FilterSearchUtil.searchProductList(filterMap, filterurl, true);
				searchFacetsVO.setSearchFacetColorVO(filter.getSearchFacetVO().getSearchFacetColorVO());
				searchFacetsVO.setSearchFacetSizeVO(filter.getSearchFacetVO().getSearchFacetSizeVO());
				searchFacetsVO.setSearchFacetPriceVO(filter.getSearchFacetVO().getSearchFacetPriceVO());
				apiResponse.setFacets(searchFacetsVO);
				String result = apiResponse.obj2Json("category");
				response.getWriter().print(result);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("ProductListServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "shangpinproductlist", channelNo,
									"categoryid", categoryid, 
									"pageindex", pageindex, 
									"pagesize", pagesize,
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
	
}
