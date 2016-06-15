package com.shangpin.wireless.api.view.spServlet;

import com.shangpin.biz.service.ASPBizBrandShopService;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

/**
 * 品牌店
 * 
 * @author wangfeng
 * 
 */
public class BrandShopServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(BrandShopServlet.class);

	ASPBizBrandShopService aspBizBrandShopService = null;

	@Override
	public void init() throws ServletException {
		aspBizBrandShopService = (ASPBizBrandShopService) getBean(ASPBizBrandShopService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getHeader("userid");
		String imei = request.getHeader("imei");
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String userLv = request.getParameter("userLv");
		String price = request.getParameter("price");
		String size = request.getParameter("size");
		String colorId = request.getParameter("color");
		String tagId = request.getParameter("tagId");
		String categoryId = request.getParameter("categoryId");
		String brandId = request.getParameter("brandId");
		String order = request.getParameter("order");
		String postArea = request.getParameter("postArea");
		String channelNo = request.getHeader("ch");// 获取渠道号
		String filters = request.getParameter("filters");
		final String originalFilters = request.getParameter("originalFilters");
		final String dynamicFilters = request.getParameter("dynamicFilters");
		String version = request.getHeader("ver");

		PrintWriter writer = null;
		if (StringUtil.isNotEmpty(pageIndex, pageSize)) {
			try {
				writer = response.getWriter();
				boolean isPromotion = true;
				if (StringUtil.compareVer(version, "2.9.4") >= 0) {
					isPromotion = false;
				}
				String data = aspBizBrandShopService.queryShopProduct(userId, pageIndex, pageSize, userLv, tagId, order, filters, isPromotion, imei, originalFilters, dynamicFilters, version);
				writer.print(data);
				// 记录访问日志
				FileUtil.addLog(request, "brandShop", channelNo, "brandId", brandId, "pageIndex", pageIndex, "pageSize", pageSize, "userId", userId, "userlv", userLv);
			} catch (Exception e) {
				log.error("BrandShopServlet：" + e);
				e.printStackTrace();
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					log.error("BrandShopServlet：" + e);
					e.printStackTrace();
				}
			}
		}

	}
}
