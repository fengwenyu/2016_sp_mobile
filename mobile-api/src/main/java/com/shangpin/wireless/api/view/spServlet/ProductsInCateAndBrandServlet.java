package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

/**
 * 获取尚品品类品牌商品列表
 * 
 * @Author:yumeng
 * @CreatDate: 2012-12-25
 */
public class ProductsInCateAndBrandServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ProductsInCateAndBrandServlet.class);
	private ShangPinService shangPinService;

	@Override
	public void init() throws ServletException {
		shangPinService = (ShangPinService) getBean(ShangPinService.class);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String pagesize = request.getParameter("pagesize");
		String pageindex = request.getParameter("pageindex");
		String gender = request.getParameter("gender");
		String categoryid = request.getParameter("categoryid");
		String brandid = request.getParameter("brandid");
		String productNo = request.getHeader("p");// 产品号
		String picw = request.getParameter("picw");
		String pich = request.getParameter("pich");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		// iphone
		if (StringUtils.isNotEmpty(productNo) && Constants.SP_IPHONE_PRODUCTNO.equals(productNo)) {
			pich = Constants.SP_PRODUCTS_PICH;
			picw = Constants.SP_PRODUCTS_PICW;
		}
		if (StringUtil.isNotEmpty(pagesize, categoryid)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", null == userid ? "" : userid);
			map.put("pagesize", pagesize);
			map.put("pageindex", pageindex);
			map.put("gender", (null == gender || "".equals(gender.trim()) ? "0" : gender));
			map.put("categoryid", null == categoryid ? "" : categoryid);
			map.put("brandid", null == brandid ? "" : brandid);
			map.put("picw", picw);
			map.put("pich", pich);
			try {
				ListOfGoods goods =new ListOfGoods();
				goods.setGender(map.get("gender"));
				goods.setPageIndex(map.get("pageIndex"));
				goods.setPageSize(map.get("pagesize"));
				goods.setUserId(map.get("userid"));
				goods.setPich(map.get("pich"));
				goods.setPicw(map.get("picw"));
				goods.setFilterSold(map.get("filtersold"));
				goods.setCategoryId(map.get("categoryid"));
				goods.setBrandId(map.get("brandid"));
				String data = shangPinService.findSPProductsAndBrand(goods);
				response.getWriter().print(data);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("ProductsInCateAndBrandServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "shangpinproductsincateandbrand", channelNo,
					"userid", userid, 
					"pagesize", pagesize, 
					"pageindex", pageindex, 
					"gender", gender, 
					"categoryid", categoryid, 
					"brandid", brandid);
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
