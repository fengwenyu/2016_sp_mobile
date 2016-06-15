package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.SpCategoryHomeAPIResponse;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 取得男士/女士品类首页数据
 * @author xupengcheng
 * @createDate 2014-1-8 下午07:34:40
 *
 */
public class SpCategoryHomeServlet extends HttpServlet {
	protected final Log log = LogFactory.getLog(SpCategoryHomeServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String gender = null;
		try{
			gender = req.getParameter("gender");
			SpCategoryHomeAPIResponse apiResponse = new SpCategoryHomeAPIResponse();
			if(gender == null || "".equals(gender)){
				apiResponse.setMsg("必须存在参数gender,而且取值只能为1或者0");
				apiResponse.setCode("-7");
			}else{
				apiResponse.setCode("0");
				apiResponse.setGender(gender);
			}
			String result = apiResponse.obj2Json();
			resp.getWriter().print(result);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("SpCategoryHomeServlet：" + e);
			try {
				WebUtil.sendApiException(resp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
