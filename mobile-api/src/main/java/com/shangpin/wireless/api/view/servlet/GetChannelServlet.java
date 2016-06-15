package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.AoLaiService;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取奥莱频道接口
 * 
 * @CreatDate: 2013-06-24
 */
public class GetChannelServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(GetChannelServlet.class);
	private AoLaiService aoLaiService;
	@Override
	public void init() throws ServletException {
		aoLaiService = (AoLaiService) getBean(AoLaiService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String productNo = request.getHeader("p");
		final String imei = request.getHeader("imei");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(imei, productNo)) {
			try {
				String data = aoLaiService.findChannel();
				JSONObject obj = JSONObject.fromObject(data);
				JSONObject result = new JSONObject();
				JSONArray contentArray = JSONArray.fromObject(obj.getJSONObject("content").getJSONArray("list"));
//				for (int i = 0; i < contentArray.size(); i++) {
//					JSONObject object=(JSONObject) contentArray.get(i);
//					object.put("type", "0");
//					object.put("url", "");
//				}
				JSONObject content =new JSONObject();
				content.put("list", contentArray);
				result.put("code", obj.getString("code"));
				result.put("msg", obj.getString("msg"));
				result.put("content", content);
				response.getWriter().print(result.toString());
			} catch (Exception e) {
				e.printStackTrace();
				log.error("GetChannelServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
//			 记录访问日志
			FileUtil.addLog(request, "channel", channelNo,
					"imei", imei,
					"productNo",productNo);
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
