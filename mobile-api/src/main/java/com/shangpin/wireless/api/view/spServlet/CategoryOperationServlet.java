package com.shangpin.wireless.api.view.spServlet;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.biz.service.ASPBizCategoryOperationService;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

/**
 * 品类运营内容
 * 
 * @author wangfeng
 * 
 */
public class CategoryOperationServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(CategoryOperationServlet.class);

	ASPBizCategoryOperationService aspBizCategoryOperationService = null;

	@Override
	public void init() throws ServletException {
		aspBizCategoryOperationService = (ASPBizCategoryOperationService) getBean(ASPBizCategoryOperationService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String userId = request.getParameter("userId");
		final String version = request.getHeader("ver");
		String channelNo = request.getHeader("ch");// 获取渠道号
		PrintWriter writer = null;
		if (StringUtil.isNotEmpty(id)) {
			try {
				String data = "";
				writer = response.getWriter();
				if (StringUtil.compareVer(version, "2.9.4") == 1) {
					data = aspBizCategoryOperationService.getCategoryOperations(id, userId);
				} else if (com.shangpin.utils.StringUtil.compareVersion("2.8.0", "2.9.0", version) == 0) {
					data = aspBizCategoryOperationService.queryCategoryOperationToResult(id, userId, "1");
				} else {
					data = aspBizCategoryOperationService.queryCategoryOperationToResult(id, userId);
				}
				writer.print(data);
				// 记录访问日志
				FileUtil.addLog(request, "categoryOperation", channelNo, "id", id);
			} catch (Exception e) {
				log.error("CategoryOperationServlet:" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					log.error("CategoryOperationServlet:" + e);
				}
			}
		}

	}
}
