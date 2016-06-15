package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.SPGoodListAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.BrandCategoryCacheUtil;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.FilterSearchUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.vo.SearchFacetsVO;
import com.shangpin.wireless.api.vo.SearchMerchandiseVO;

/**
 * 获取尚品品类品牌商品列表
 * 
 * @Author:wangwenguan
 * @CreatDate: 2013-12-29
 */
public class BrandProductListFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(BrandProductListFilterServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String userid = request.getParameter("userid");
		final String userlv = request.getParameter("userlv");	//	用户级别
		final String brandid = request.getParameter("brandid");
		final String navid = request.getParameter("navid");
		final String categoryid = request.getParameter("categoryid");
		final String firstpropid = request.getParameter("firstpropid");	//	颜色
		final String productsize = request.getParameter("productsize");	//	尺码
		final String pricerange = request.getParameter("pricerange");	//	价格区间
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号

		if (StringUtil.isNotEmpty(brandid)) {
			HashMap<String, String> map = FilterSearchUtil.initQueryMap(userlv, userid, brandid, categoryid, navid, firstpropid, pricerange, null, productsize, null, null, null);
			String filterurl = Constants.BASE_SEARCH_URL + "mobile/BrandProductList";
			try {
				SPGoodListAPIResponse apiResponse = new SPGoodListAPIResponse();
				apiResponse.setSearchCategory(categoryid);
				SearchMerchandiseVO filterlist = FilterSearchUtil.searchProductList(map, filterurl, true);
				apiResponse.setCode(filterlist.getStatus());
				apiResponse.setMsg(filterlist.getDiscription());
				SearchFacetsVO searchFacetsVO = filterlist.getSearchFacetVO();
				//	保持相同的分类
				SearchFacetsVO cachedSearchFacetsVO = BrandCategoryCacheUtil.getCategoryInterface(brandid);
				searchFacetsVO.setSearchFacetCategoryL2VO(cachedSearchFacetsVO.getSearchFacetCategoryL2VO());
				searchFacetsVO.setSearchFacetCategoryL3VO(cachedSearchFacetsVO.getSearchFacetCategoryL3VO());
				searchFacetsVO.setSearchFacetCategoryL4VO(cachedSearchFacetsVO.getSearchFacetCategoryL4VO());
				searchFacetsVO.setSearchFacetNavigationVO(cachedSearchFacetsVO.getSearchFacetNavigationVO());
				//筛选条件由品类控制和选择的品牌控制 categoryid 和 navid 不可能同时出现 
				map = FilterSearchUtil.initQueryMap(userlv, userid, brandid, categoryid, navid, null, null, null, null, null, null, null);
				SearchMerchandiseVO filter = FilterSearchUtil.searchProductList(map, filterurl, true);
				searchFacetsVO.setSearchFacetColorVO(filter.getSearchFacetVO().getSearchFacetColorVO());
				searchFacetsVO.setSearchFacetSizeVO(filter.getSearchFacetVO().getSearchFacetSizeVO());
				searchFacetsVO.setSearchFacetPriceVO(filter.getSearchFacetVO().getSearchFacetPriceVO());
				apiResponse.setFacets(searchFacetsVO);
				String result = apiResponse.obj2Json("brand");
				response.getWriter().print(result);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("BrandProductListFilterServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "shangpinbrandproductlistfilter", channelNo,
					"brandid", brandid,
					"categoryid", categoryid,
					"navid", navid,
					"userid", userid,
					"userlv", userlv,
					"firstpropid", firstpropid,
					"productsize", productsize,
					"pricerange", pricerange);
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
