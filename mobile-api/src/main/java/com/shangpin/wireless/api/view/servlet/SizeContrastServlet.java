package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.util.Streams;
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
public class SizeContrastServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(TopicServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productNum = request.getHeader("p");
		String ver = request.getHeader("ver");
		String cate = request.getParameter("cate");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtils.isEmpty(productNum))
			productNum = request.getParameter("p");
		if (StringUtils.isEmpty(ver))
			ver = request.getParameter("ver");
		if (StringUtil.isNotEmpty(productNum, ver)) {
			try {
				ServletContext context = getServletContext();
				InputStream in = context.getResourceAsStream("/aolai/size/size_" + cate + ".html");
				ServletOutputStream out = response.getOutputStream();
				Streams.copy(in, out, false);
			} catch (Exception e) {
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "aolaisize", channelNo,
					"productNum", productNum, 
					"ver", ver, 
					"cate", cate);
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
