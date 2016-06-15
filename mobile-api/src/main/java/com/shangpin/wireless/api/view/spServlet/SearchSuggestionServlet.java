package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.biz.service.ASPBizSerchService;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

public class SearchSuggestionServlet extends BaseServlet {

	private static final long serialVersionUID = 3479475926001047012L;

	protected final Log log = LogFactory.getLog(SearchSuggestionServlet.class);

	ASPBizSerchService aspBizSearchService;

	@Override
	public void init() throws ServletException {
		aspBizSearchService = (ASPBizSerchService) getBean(ASPBizSerchService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String productNo = request.getHeader("p");
		final String imei = request.getHeader("imei");
		final String keywords = request.getParameter("keywords");

		if (StringUtil.isNotEmpty(imei, productNo, keywords)) {
			try {
				String bizData = aspBizSearchService.querySuggestion(keywords.toLowerCase(), "", "", "UTF-8");
				sendMessage(response, bizData);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("SearchSuggestionServlet:" + e);
				responseError(response, "api");
			}
		} else {
			responseError(response, "params");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
