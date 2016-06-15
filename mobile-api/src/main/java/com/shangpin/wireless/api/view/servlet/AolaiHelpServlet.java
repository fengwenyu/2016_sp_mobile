package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 帮助页面
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-09-20
 */
public class AolaiHelpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(TopicServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productNum = request.getHeader("p");
		String ver = request.getHeader("ver");
		String wh = request.getHeader("wh");
		String os = request.getHeader("os");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		String ctx = request.getContextPath();
		
		if (StringUtils.isEmpty(productNum) && StringUtils.isEmpty(ver)) {
			productNum = request.getParameter("p");
			ver = request.getParameter("ver");
		}
		if (StringUtil.isNotEmpty(productNum, ver)) {
			
			if ("1".equals(productNum) || "101".equals(productNum)) { // 奥莱客户端
				request.getRequestDispatcher("/shangpin/help/iphoneindex.html").forward(request, response);
				/*request.forward(ctx+"/aolai/help/iphoneindex.html");*/
			} else if ("2".equals(productNum) || "102".equals(productNum)) { // 尚品客户端
				request.getRequestDispatcher("/shangpin/help/iphoneindex.html").forward(request, response);
				/*response.sendRedirect(ctx+"/shangpin/help/iphoneindex.html");*/
				// iPhone5帮助url
				/*if ("ios".equals(os) && "640x1136".equals(wh)) {
					response.sendRedirect(ctx+"/shangpin/help/iphone5index.html");
				}*/
			}
			
			// 记录访问日志
			FileUtil.addLog(request, "aolaihelp", channelNo,
					"productNum", productNum, 
					"ver", ver);
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
