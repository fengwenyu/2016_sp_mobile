package com.shangpin.wireless.api.view.spServlet;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.biz.service.ASPBizProductInfoService;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

/**
 * 商品详情页
 * 
 * @author wangfeng
 * 
 */
public class ProductDetailServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ProductDetailServlet.class);

	ASPBizProductInfoService aspBizProductInfoService = null;

	@Override
	public void init() throws ServletException {
		aspBizProductInfoService = (ASPBizProductInfoService) getBean(ASPBizProductInfoService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		if(userId==null||"null".equals(userId)){
		    userId="";
		}
		String activityId = request.getParameter("activityId");
		String productId = request.getParameter("productId");
		String type = request.getParameter("type");// 默认为0 0代表普通商品 1代表礼品卡-实物卡
		// 2代表礼品卡-电子卡
		String picNo = request.getParameter("picNo");// 图片编号
		String channelNo = request.getHeader("ch");// 获取渠道号
		String version = request.getHeader("ver")==null?"":request.getHeader("ver");		
		PrintWriter writer = null;
		if (StringUtil.isNotEmpty(productId)) {
			try {
				writer = response.getWriter();
				String data = aspBizProductInfoService.queryProductInfo(activityId, productId, userId, picNo, type,version);	                
				 writer.print(data);				   
				// 记录访问日志
				FileUtil.addLog(request, "productDetail", channelNo, "productId", productId, "activityId", activityId, "userId", userId);
			} catch (Exception e) {
				log.error("ProductDetailServlet:" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					log.error("ProductDetailServlet:" + e);
				}
			}
		}else {
				try {
					WebUtil.sendErrorInvalidParams(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}

}
