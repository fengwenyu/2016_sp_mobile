package com.shangpin.wireless.api.view.spServlet;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.biz.service.ASPBizIndexService;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

/**
 * app首页第一部分
 * 
 * @author wangfeng
 * 
 */
public class AppIndexFirstServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(AppIndexFirstServlet.class);

	ASPBizIndexService  aspBizIndexService=null;
    @Override
    public void init() throws ServletException {
        aspBizIndexService = (ASPBizIndexService) getBean(ASPBizIndexService.class);
    }



	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String channelNo = request.getHeader("ch");// 获取渠道号
		final String imei = request.getHeader("imei");
		final String p = request.getHeader("p");
        final String version = request.getHeader("ver");

		PrintWriter writer = null;
		if (StringUtil.isNotEmpty(channelNo, imei, p, version)) {
			try {

			    writer=response.getWriter();			    
				String data = aspBizIndexService.queryAppIndexFirst(request);

				writer.print(data);
				// 打印日志
                //FileUtil.addLog(request, "appIndexFirst", channelNo);
			} catch (Exception e) {
				log.error("AppIndexFirstServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					log.error("AppIndexFirstServlet：" + e);
				}
			}
		}

	}	
}
