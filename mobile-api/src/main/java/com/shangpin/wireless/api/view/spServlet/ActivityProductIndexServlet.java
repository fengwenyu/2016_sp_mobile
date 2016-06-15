package com.shangpin.wireless.api.view.spServlet;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.biz.service.ASPBizActivityService;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

/**
 * 活动
 * 
 * @author wangfeng
 * 
 */
public class ActivityProductIndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ActivityProductIndexServlet.class);

	ASPBizActivityService  aspBizActivityService=null;
    @Override
    public void init() throws ServletException {
        aspBizActivityService = (ASPBizActivityService) getBean(ASPBizActivityService.class);
    }



	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String imei=request.getHeader("imei");
	    String userId=request.getHeader("userid");
	    String pageIndex=request.getParameter("pageIndex");
	    String pageSize=request.getParameter("pageSize");
	    String userLv=request.getParameter("userLv");
	    String price=request.getParameter("price");
	    String size=request.getParameter("size");
	    String activityId=request.getParameter("activityId");
	    String colorId=request.getParameter("color");
	    String tagId=request.getParameter("tagId");
	    String categoryId=request.getParameter("categoryId");
	    String brandId=request.getParameter("brandId");
	    String order=request.getParameter("order");
		String channelNo = request.getHeader("ch");// 获取渠道号
		String product = request.getHeader("p");
		String ver = request.getHeader("ver");
		String filters=request.getParameter("filters");
		final String originalFilters = request.getParameter("originalFilters");
		final String dynamicFilters = request.getParameter("dynamicFilters");
		PrintWriter writer = null;
		if (StringUtil.isNotEmpty(product)&&StringUtil.isNotEmpty(ver)) {
			try {
			    writer=response.getWriter();
			    String data = aspBizActivityService.queryActivityIndexNew(userId, activityId, pageIndex, pageSize, tagId, order, userLv,filters,  originalFilters, dynamicFilters, imei, ver);
				writer.print(data);
			    // 记录访问日志
	            FileUtil.addLog(request, "activityProduct", channelNo,
	                                    "brandId", brandId, 
	                                    "pageIndex", pageIndex, 
	                                    "pageSize", pageSize,
	                                    "categoryId", categoryId,
	                                    "subjectNo", activityId,
	                                    "userId", userId,
	                                    "userlv", userLv,
	                                    "colorId", colorId,
	                                    "size", size,
	                                    "price", price,
	                                    "order", order);
			} catch (Exception e) {
				log.error("ActivityProductIndexServlet：" + e);				
			}
		}else{
		    try {
                WebUtil.sendApiException(response);
            } catch (Exception e) {
                log.error("ActivityProductIndexServlet：" + e);
            }
		}

	}	
}
