package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

public class StaticCacheServlet extends BaseServlet {

	private static final long serialVersionUID = 6682490335263155099L;

	protected final Log log = LogFactory.getLog(StaticCacheServlet.class);
	
	private static final String JS = Constants.STATICCACHE_JS;
	private static final String CSS = Constants.STATICCACHE_CSS;
	private static final String PIC = Constants.STATICCACHE_PIC;
	
//	CommonService commonService;
	
	@Override
	public void init() throws ServletException {
//		commonService = (CommonService) getBean(CommonService.class);
	}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	final String productNo =request.getHeader("p");
        final String imei = request.getHeader("imei");
        final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
        if (StringUtil.isNotEmpty(imei, productNo)) {
        	StringBuffer strBuffer = new StringBuffer();
			try {

				String cssStr = "[\""+CSS+"/shangpin_new/css/base.css?2014123101\",\""+CSS+"/shangpin_new/css/page/order.css?2014123101\",\""+CSS+"/shangpin_new/css/page/order_result.css?2014123101\",\""+CSS+"/app/css/base.css\",\""+CSS+"/app/css/page/detail.new.css\"]";
				String jsStr = "[\""+JS+"/shangpin_new/js/core.js?2014123101\",\""+JS+"/shangpin_new/js/jquery.min.js?2014123101\",\""+JS+"/shangpin_new/js/lazyload.js?2014123101\",\""+JS+"/shangpin_new/js/config.sim.js?2014123101\",\""+JS+"/shangpin_new/js/j.floatCalculate.js?2014123101\",\""+JS+"/shangpin_new/js/j.appDialogs.js?2014123101\",\""+JS+"/js/weixin/order.js?2014123101\",\""+JS+"/js/lazyload.js?2014123101\",\""+JS+"/js/config.sim.js?2014123101\",\""+JS+"/js/css3.js?2014123101\",\""+JS+"/js/slideLayer.js?2014123101\",\""+JS+"/js/comm.js?2014123101\",\""+JS+"/js/zepto.min.js?2014123101\",\""+JS+"/js/jquery.min.js?2014123101\",\""+JS+"/js/core.js?2014123101\"]";
				String picStr = "[\""+PIC+"/images/touch-icon-iphone.png\",\""+PIC+"/images/touch-icon-ipad.png\",\""+PIC+"/images/touch-icon-iphone4.png\",\""+PIC+"/images/touch-icon-newipad.png\",\""+PIC+"/images/logo/loading.png\",\""+PIC+"/images/e.gif\",\""+PIC+"/images/order/pic_order.png\",\"http://pic11.shangpin.com/shangpin/images/logo/favicon.ico\",\""+PIC+"/shangpin_new/images/detail/server01.png\",\""+PIC+"/shangpin_new/images/detail/server02.png\",\""+PIC+"/shangpin_new/images/detail/server03.png\",\""+PIC+"/shangpin_new/images/order/close.png\",\""+PIC+"/shangpin_new/images/order/pic_fail.png\"]";
				strBuffer.append("{\"code\":\"0\",\"msg\":\"\",\"content\":{");
				strBuffer.append("\"css\":" + cssStr);
				strBuffer.append(",\"js\":" + jsStr);
				strBuffer.append(",\"pic\":" + picStr);
				strBuffer.append("}}");

				response.getWriter().print(strBuffer);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("StaticCacheServlet:" + e);
				returnError(response, "api");
			}
			// 记录访问日志
			FileUtil.addLog(request, "StaticCacheServlet", channelNo, "imei", imei, "productNo", productNo);
        } else {
        	returnError(response,"params");
        }
    }

    /**
     * 返回错误信息
     * @param response
     * @param errorType
     * 			params:请求参数不能为空
     * 			api:服务器的错误信息
     */
	private void returnError(HttpServletResponse response, String errorType) {
		try {
			if ("params".equals(errorType)) {
				WebUtil.sendErrorInvalidParams(response);
			} else {
				WebUtil.sendApiException(response);
			}
		    
		} catch (Exception e1) {
		    e1.printStackTrace();
		}
	}
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
