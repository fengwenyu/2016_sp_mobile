package com.shangpin.wireless.api.view.spServlet;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.shangpin.biz.service.ASPBizStyleThemeService;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

/**
 * 更多主题
 * 
 * @author wangfeng
 * 
 */
public class MoreThemesServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(MoreThemesServlet.class);

	ASPBizStyleThemeService  aspBizStyleThemeService=null;
    @Override
    public void init() throws ServletException {
        aspBizStyleThemeService = (ASPBizStyleThemeService) getBean(ASPBizStyleThemeService.class);
    }



	public void doGet(HttpServletRequest request, HttpServletResponse response) {
	    String pageIndex=request.getParameter("pageIndex");
	    String pageSize=request.getParameter("pageSize");
		PrintWriter writer = null;
		if (StringUtil.isNotEmpty(pageIndex,pageSize)) {
			try {
			    writer=response.getWriter();
				String data = aspBizStyleThemeService.queryStyleThemeToResult(pageIndex, pageSize);
				writer.print(data);
				// 打印日志
                //FileUtil.addLog(request, "appIndexFirst", channelNo);
			} catch (Exception e) {
				log.error("MoreThemesServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					log.error("MoreThemesServlet：" + e);
				}
			}
		}

	}	
}
