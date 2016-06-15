package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.shangpin.base.service.AoLaiService;
import com.shangpin.wireless.api.api2client.domain.SubjectResponse;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 奥莱活动列表
 * 
 * @author xupengcheng
 * 
 */
public class QuerySubjectProductListServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(QuerySubjectProductListServlet.class);
	AoLaiService aoLaiService = null;

	@Override
	public void init() throws ServletException {
		aoLaiService = (AoLaiService) getBean(AoLaiService.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String subjectNo = req.getParameter("subjectNo");
		String pageIndex = req.getParameter("pageIndex");
		String pageSize = req.getParameter("pageSize");
		String orderType = req.getParameter("orderType");
		String isHaveStock = req.getParameter("isHaveStock");
		String categoryNo = req.getParameter("categoryNo");
		String sizeNo = req.getParameter("sizeNo");
		String pich = "";
		String picw = "";
		PrintWriter writer = resp.getWriter();
		if (!StringUtil.isNotEmpty(pageSize, pageIndex, subjectNo, isHaveStock)) {
			try {
				WebUtil.sendErrorInvalidParams(resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String data = "";
			if (sizeNo != null && sizeNo.equals("all")) {
				sizeNo = null;
			}
			pich = "111";
			picw = "222";

			if (categoryNo != null && categoryNo.equals("all")) {
				categoryNo = null;
			}
			try {
				data = aoLaiService.querySubjectProductList(sizeNo, pageIndex, pageSize, subjectNo, isHaveStock, pich, picw, orderType, categoryNo);
				SubjectResponse subjectResponse = new SubjectResponse();
				subjectResponse.setUserId(userId);
				subjectResponse.setSubjectNo(subjectNo);
				writer.print(subjectResponse.obj2Json(data).replace("-222-111.", "-{w}-{h}."));
			} catch (Exception e) {
				e.printStackTrace();
				log.error("QuerySubjectProductListServlet：" + e);
				try {
					WebUtil.sendApiException(resp);
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
