package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shangpin.base.service.SearchService;
import com.shangpin.base.vo.SearchResult;
import com.shangpin.biz.service.ASPBizBrandShopService;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.wireless.api.api2client.domain.SearchAPIResponse;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 品牌商品搜索接口
 * @author xupengcheng
 *
 */
public class SearchBrandProductServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SearchService searchService;
    ASPBizBrandShopService aspBizBrandShopService = null;

	@Override
	public void init() throws ServletException {
		searchService = (SearchService) getBean(SearchService.class);
		aspBizBrandShopService = (ASPBizBrandShopService) getBean(ASPBizBrandShopService.class);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageIndex = req.getParameter("pageIndex");
		String pageSize = req.getParameter("pageSize");
		String tagId=req.getParameter("tagId");
		String brandId = req.getParameter("brandId");
		String price = req.getParameter("price");
		String colorId = req.getParameter("color");
		String size = req.getParameter("size");
		String categoyId = req.getParameter("categoryId");
		String order = req.getParameter("order");
		String navId = req.getParameter("navId");
		String userLv = req.getParameter("userLv");
		String postArea = req.getParameter("postArea");
		String filters = req.getParameter("filters");
		String version = req.getHeader("ver");
		String userId = req.getHeader("userid");
		String imei = req.getHeader("imei");
		PrintWriter writer = resp.getWriter();
		final String originalFilters = req.getParameter("originalFilters");
		final String dynamicFilters = req.getParameter("dynamicFilters");
		String data = null;
		SearchResult searchResult = new SearchResult();
		try {
            if (StringUtil.compareVer(version, "2.9.0") >= 0) {
                data = aspBizBrandShopService.queryProductList(userId, pageIndex, pageSize, userLv, tagId, order, filters, originalFilters, dynamicFilters, imei, version);                                               
            }else{
                if (categoyId != null) {// 前台配置的品类可能含多个 ，用 | 分割
                    categoyId = categoyId.replace("|", ",").replace(" ", "");
                }
                searchResult = searchService.searchBrandProductList(navId, pageIndex, pageSize, tagId, brandId, price, colorId, size, categoyId, order, userLv, postArea);
                SearchAPIResponse searchAPIResponse = new SearchAPIResponse(searchResult, categoyId);
                searchAPIResponse.getContent().remove("brandList");
                data=searchAPIResponse.obj2Json();                
            }
            writer.print(data);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                WebUtil.sendApiException(resp);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
