package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.shangpin.wireless.api.api2client.domain.FlagshipListAPIResponse;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 旗舰店信息
 * 
 * @Author:zhouyu
 * @CreatDate: 2013-1-10
 */
public class FlagshipServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(FlagshipServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FlagshipListAPIResponse flagshipListAPIResponse = new FlagshipListAPIResponse();
			flagshipListAPIResponse.setCode("0");
			String result = flagshipListAPIResponse.obj2Json();
			response.getWriter().print(result);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("FlagshipServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	}
	
	
}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
