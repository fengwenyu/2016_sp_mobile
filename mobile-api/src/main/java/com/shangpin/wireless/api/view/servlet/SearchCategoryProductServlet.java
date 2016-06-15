package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shangpin.base.service.SearchService;
import com.shangpin.base.vo.SearchResult;
import com.shangpin.biz.service.ASPBizSerchService;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.wireless.api.api2client.domain.SearchAPIResponse;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 品类商品搜索接口
 * 
 * @author xupengcheng
 *
 */
public class SearchCategoryProductServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SearchService searchService;
	ASPBizSerchService aspBizSearchService;

	@Override
	public void init() throws ServletException {
		searchService = (SearchService) getBean(SearchService.class);
		aspBizSearchService = (ASPBizSerchService) getBean(ASPBizSerchService.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageIndex = req.getParameter("pageIndex");
		String pageSize = req.getParameter("pageSize");
		String tagId = req.getParameter("tagId");
		String brandId = req.getParameter("brandId");
		String price = req.getParameter("price");
		String colorId = req.getParameter("color");
		String size = req.getParameter("size");
		String categoyId = req.getParameter("categoryId");
		String order = req.getParameter("order");
		String userLv = req.getParameter("userLv");
		String postArea = req.getParameter("postArea");
		String filters = req.getParameter("filters");
		String version = req.getHeader("ver");
		String imei = req.getHeader("imei");
		final String originalFilters = req.getParameter("originalFilters");
		final String dynamicFilters = req.getParameter("dynamicFilters");
		String data = null;
		if (StringUtil.isNotEmpty(version) && StringUtil.compareVer(version, "2.9.0") >= 0) {
			try {
				data = aspBizSearchService.queryCategoryProductList(pageIndex, pageSize, userLv, tagId, order, filters, originalFilters, dynamicFilters, imei, version);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (StringUtil.isNotEmpty(categoyId)) {
				SearchResult searchResult = new SearchResult();
				try {
					if (categoyId != null && categoyId.indexOf("|") >= 0) {
						String[] categorys = categoyId.split("\\|");
						for (String cate : categorys) {
							if (cate.startsWith("A01")) {
								categoyId = cate;
								break;
							}
						}
					}
					searchResult = searchService.searchCategoryProductList(pageIndex, pageSize, tagId, brandId, price, colorId, size, categoyId, order, userLv, postArea);
					SearchAPIResponse searchAPIResponse = new SearchAPIResponse(searchResult, categoyId);
					data = searchAPIResponse.obj2Json();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						WebUtil.sendApiException(resp);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				try {
					WebUtil.sendErrorInvalidParams(resp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		resp.getWriter().print(data);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
