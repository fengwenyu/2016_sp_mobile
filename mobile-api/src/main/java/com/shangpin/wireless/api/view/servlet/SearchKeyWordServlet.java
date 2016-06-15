package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shangpin.biz.service.ASPBizSerchService;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 关键字搜索接口
 * 
 * @author xupengcheng
 *
 */
public class SearchKeyWordServlet extends BaseServlet {

	private static final long serialVersionUID = -3222084538876924010L;
	ASPBizSerchService aspBizSearchService;

	@Override
	public void init() throws ServletException {
		aspBizSearchService = (ASPBizSerchService) getBean(ASPBizSerchService.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageIndex = req.getParameter("pageIndex");
		String pageSize = req.getParameter("pageSize");
		String tagId = req.getParameter("tagId");
//		String brandId = req.getParameter("brandId");
//		String price = req.getParameter("price");
		String keywords = req.getParameter("keywords");
//		String colorId = req.getParameter("color");
//		String size = req.getParameter("size");
//		String categoyId = req.getParameter("categoryId");
		String order = req.getParameter("order");
		String userLv = req.getParameter("userLv");
//		String postArea = req.getParameter("postArea");
		String version = req.getHeader("ver");
		String filters = req.getParameter("filters");
		final String originalFilters = req.getParameter("originalFilters");
		final String dynamicFilters = req.getParameter("dynamicFilters");
		if (StringUtil.isNotEmpty(keywords)) {
			try {
				String data = aspBizSearchService.queryKeyWordProductList(keywords, pageIndex, pageSize, tagId, order, userLv, filters, originalFilters, dynamicFilters, version);
				resp.getWriter().print(data);
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
