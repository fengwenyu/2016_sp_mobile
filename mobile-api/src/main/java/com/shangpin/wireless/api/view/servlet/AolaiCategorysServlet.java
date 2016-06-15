package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取奥莱分类列表接口
 * 
 */
public class AolaiCategorysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(AolaiCategorysServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// :未确定参数
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		String url = Constants.SP_BASE_URL + "/";
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{\"code\":\"0\",\"msg\":\"\",\"content\":{");
			buffer.append("\"list\":");
			buffer.append("[{\"id\":\"1234\",\"name\":\"服饰\",\"createdate\":\"1366336800000\",\"updatedate\":\"1376336800000\"},");
			buffer.append("{\"id\":\"1235\",\"name\":\"饰品\",\"createdate\":\"1366336800000\",\"updatedate\":\"1376336800000\"},");
			buffer.append("{\"id\":\"1236\",\"name\":\"鞋靴\",\"createdate\":\"1366336800000\",\"updatedate\":\"1376336800000\"},");
			buffer.append("{\"id\":\"1237\",\"name\":\"箱包\",\"createdate\":\"1366336800000\",\"updatedate\":\"1376336800000\"}]");
			buffer.append("}}");
			// String data = WebUtil.readContentFromGet(url, map);
			response.getWriter().print(buffer);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("AolaiCategorysServlet：" + e);
			try {
				WebUtil.sendApiException(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		/*
		 */
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
