package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.AoLaiService;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 活动列表接口
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-27
 */
public class ActivitylistServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(TopicServlet.class);

	private AoLaiService aoLaiService;

	@Override
	public void init() throws ServletException {
		aoLaiService = (AoLaiService) getBean(AoLaiService.class);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		final String actid = request.getParameter("actid");
		final String picquality = request.getParameter("picquality");
		String picw = request.getParameter("picw");
		String pich = request.getParameter("pich");
		if (!StringUtil.isNotEmpty(picw) || !StringUtil.isNotEmpty(pich)) {
			if ("low".equals(picquality)) {
				picw = "141";
				pich = "188";
			} else {
				picw = "282";
				pich = "376";
			}
		}
		if (StringUtil.isNotEmpty(actid)) {
			try {
				String data = aoLaiService.findSubjectProductList(actid, picw, pich, "1", "500");
				response.getWriter().print(data);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("ActivitylistServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "activitylist", channelNo,
					"actid", actid,
					"code","success");
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
