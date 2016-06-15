package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.utils.StringUtil;
import com.shangpin.wireless.api.api2client.domain.SPGoodListAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.BrandCategoryCacheUtil;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.FilterSearchUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.vo.SearchFacetsVO;
import com.shangpin.wireless.api.vo.SearchMerchandiseVO;

/**
 * 获取尚品品类品牌商品列表
 * 
 * @Author:wangwenguan
 * @CreatDate: 2013-12-29
 */
public class BrandProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(BrandProductListServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		final String userid = request.getParameter("userid");
		final String userLv = request.getParameter("userlv");	//	用户级别
		final String brandid = request.getParameter("brandid");
		final String categoryid = request.getParameter("categoryid");
		final String navid = request.getParameter("navid");
		final String primaryColorId = request.getParameter("firstpropid");	//	颜色
		final String productSize = request.getParameter("productsize");	//	尺码
		final String price = request.getParameter("pricerange");	//	价格区间
		final String sort = request.getParameter("sort");	//	排序
		final String pageindex = request.getParameter("pageindex");
		final String pagesize = request.getParameter("pagesize");
		final String update = request.getParameter("update");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(pageindex, pagesize, brandid)) {
			if(StringUtil.isInteger(pageindex)&&StringUtil.isInteger(pagesize)){
				int pageStart = (Integer.parseInt(pageindex) - 1) * Integer.parseInt(pagesize) + 1;
				int pageEnd = pageStart + Integer.parseInt(pagesize) - 1;
				String[] sortparams = sort == null ? null : sort.split(":");
				String listUrl = Constants.BASE_SEARCH_URL + "mobile/BrandProductList";
				HashMap productListMap = FilterSearchUtil.initQueryMap(userLv, userid, brandid, categoryid, navid, primaryColorId, price, null, productSize, sort, pageStart, pageEnd);
				HashMap filterMap = FilterSearchUtil.initQueryMap(userLv, userid, brandid, categoryid, navid, null, null, null, null, null, null, null);
				try {
					SPGoodListAPIResponse apiResponse = new SPGoodListAPIResponse();
					apiResponse.setSearchCategory(categoryid);
					apiResponse.setSort(sort);
					SearchMerchandiseVO productlist = FilterSearchUtil.searchProductList(productListMap, listUrl, false);
					apiResponse.setCode(productlist.getStatus());
					apiResponse.setMsg(productlist.getDiscription());
					apiResponse.setProductList(productlist.getSearchProductVO());
					//筛选数据 由品类和品牌一起决定
					SearchMerchandiseVO filterData = FilterSearchUtil.searchProductList(filterMap, listUrl, true);
					//筛选数据
					SearchFacetsVO searchFacetsVO = filterData.getSearchFacetVO();
					//保持相同的分类
					SearchFacetsVO cachedSearchFacetsVO = BrandCategoryCacheUtil.getCategoryInterface(brandid);
					searchFacetsVO.setSearchFacetCategoryL2VO(cachedSearchFacetsVO.getSearchFacetCategoryL2VO());
					searchFacetsVO.setSearchFacetCategoryL3VO(cachedSearchFacetsVO.getSearchFacetCategoryL3VO());
					searchFacetsVO.setSearchFacetCategoryL4VO(cachedSearchFacetsVO.getSearchFacetCategoryL4VO());
					searchFacetsVO.setSearchFacetNavigationVO(cachedSearchFacetsVO.getSearchFacetNavigationVO());
					apiResponse.setFacets(searchFacetsVO);
					String result = apiResponse.obj2Json("brand");
					response.getWriter().print(result);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("BrandProductListServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				// 记录访问日志
				FileUtil.addLog(request, "shangpinbrandproductlist", channelNo,
										"brandid", brandid, 
										"pageindex", pageindex, 
										"pagesize", pagesize,
										"categoryid", categoryid,
										"navid", navid,
										"userid", userid,
										"userlv", userLv,
										"firstpropid", primaryColorId,
										"productsize", productSize,
										"pricerange", price,
										"sort", sort);
			}else{
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		
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
