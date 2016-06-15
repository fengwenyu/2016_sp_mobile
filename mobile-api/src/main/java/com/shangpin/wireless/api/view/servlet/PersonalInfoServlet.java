package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.CommonService;
import com.shangpin.wireless.api.domain.PushconfigAolai;
import com.shangpin.wireless.api.domain.PushconfigShangpin;
import com.shangpin.wireless.api.service.PushconfigAolaiService;
import com.shangpin.wireless.api.service.PushconfigShangpinService;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.DBType;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.HqlHelper;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 个人信息接口
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-29
 */
public class PersonalInfoServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(PersonalInfoServlet.class);
	private PushconfigAolaiService pushconfigAolaiService;
	private PushconfigShangpinService pushconfigShangpinService;
	private CommonService commService;

	@Override
	public void init() throws ServletException {
		pushconfigAolaiService = (PushconfigAolaiService) getBean(PushconfigAolaiService.class);
		pushconfigShangpinService = (PushconfigShangpinService) getBean(PushconfigShangpinService.class);
		commService = (CommonService) getBean(CommonService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String sessionid = request.getParameter("sessionid");
		String imei = request.getHeader("imei");
		String productNo = request.getHeader("p");// 产品号
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userId, imei, sessionid)) {
			if (SessionUtil.validate(userId, imei, sessionid)) {
				try {
					String data = commService.findUserInfo(userId);
					JSONObject obj = JSONObject.fromObject(data);
					JSONObject content = JSONObject.fromObject(obj.getString("content"));
					HqlHelper hqlHelper = null;
					if ("1".equals(productNo)) {
						hqlHelper = new HqlHelper(PushconfigAolai.class, "p");
						hqlHelper.addWhereCondition("p.userId=?", userId);
						PushconfigAolai model = pushconfigAolaiService.getByCondition(hqlHelper, DBType.dataSourceAPI.toString());
						if (model != null) {
							content.put("isopen", model.getIsOpen() + "");
							content.put("msgtype", model.getMsgType() + "");
						} else {
							content.put("isopen", "1");
							content.put("msgtype", "2");
						}
					} else if ("2".equals(productNo)) {
						hqlHelper = new HqlHelper(PushconfigShangpin.class, "p");
						hqlHelper.addWhereCondition("p.userId=?", userId);
						PushconfigShangpin model = pushconfigShangpinService.getByCondition(hqlHelper, DBType.dataSourceAPI.toString());
						if (model != null) {
							content.put("isopen", model.getIsOpen() + "");
							content.put("msgtype", model.getMsgType() + "");
						} else {
							content.put("isopen", "1");
							content.put("msgtype", "2");
						}
					}
					obj.put("content", content.toString());
					response.getWriter().print(obj.toString());
				} catch (Exception e) {
					e.printStackTrace();
					log.error("PersonalInfoServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				try {
					WebUtil.sendErrorNoSessionid(response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "personalinfo", channelNo,
					"userid", userId);
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
