package com.shangpin.wireless.api.view.spServlet;

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
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取一级品类下的所有子品类
 * 
 * @Author:yumeng
 * @CreatDate: 2012-12-25
 */
public class ChildCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ChildCategoryServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gender = request.getParameter("gender");
		String categoryid = request.getParameter("categoryid");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(gender, categoryid)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("gender", (null == gender || "".equals(gender.trim()) ? "0" : gender));
			map.put("categoryid", null == categoryid ? "" : categoryid);
			String url = Constants.SP_BASE_URL + "SPChildCategory/";
			try {
				String data = WebUtil.readContentFromGet(url, map);
				response.getWriter().print(data);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("ChildCategoryServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "shangpinchildcategory", channelNo,
					"gender", gender, 
					"categoryid", categoryid);
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
