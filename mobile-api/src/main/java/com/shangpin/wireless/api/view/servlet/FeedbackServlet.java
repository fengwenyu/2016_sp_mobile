package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.Feedback;
import com.shangpin.wireless.api.service.FeedbackService;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.DBType;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 信息反馈
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-29
 */
public class FeedbackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(FeedbackServlet.class);
	private FeedbackService feedbackService;

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext sc = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		feedbackService = (FeedbackService) ctx.getBean(FeedbackService.SERVICE_NAME);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String msg = request.getParameter("msg");
		String loginName = request.getParameter("loginname");
		String productNum = request.getHeader("p");
		String ver = request.getHeader("ver");
		String channelNum = request.getHeader("ch");
		String platform = request.getHeader("os");
		String phoneType = request.getHeader("mt");
		String phoneModel = request.getHeader("model");
		String phone = request.getParameter("phone");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty( msg, productNum, ver, platform, phoneModel, channelNum)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userId);
			map.put("msg", msg);
			String url = Constants.BASE_URL + "createsubjectconsultations/";
			Feedback feedback = new Feedback();
			feedback.setUserId(userId);
			feedback.setMsg(msg);
			feedback.setLoginName(loginName);
			feedback.setVer(ver);
			feedback.setChannel(Long.valueOf(channelNum.trim()));
			feedback.setPlatform(platform);
			feedback.setPhoneType(phoneType);
			feedback.setPhoneModel(phoneModel);
			feedback.setProduct(Long.valueOf(productNum.trim()));
			feedback.setCreateTime(new Date());
			feedback.setPhone(phone);
			try {
				feedbackService.save(feedback, DBType.dataSourceAPI.toString());
			} catch (Exception e) {
				log.error("FeedbackServlet" + e);
				e.printStackTrace();
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			try {
				String data = WebUtil.readContentFromPost(url, map);
				response.getWriter().print(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 记录访问日志
			FileUtil.addLog(request, "feedback", channelNo,
					"userid", userId, 
					"loginName", loginName, 
					"msg", msg);
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
