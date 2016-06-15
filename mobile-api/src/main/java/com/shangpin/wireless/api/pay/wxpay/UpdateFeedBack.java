package com.shangpin.wireless.api.pay.wxpay;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wechat.service.WeChatPublicService;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

public class UpdateFeedBack extends BaseServlet {
	private static final long serialVersionUID = 1068616127096367955L;
	protected final Log log = LogFactory.getLog(UpdateFeedBack.class);
	WeChatPublicService weiXinService = null;

    @Override
    public void init() throws ServletException {
        weiXinService = (WeChatPublicService) getBean(WeChatPublicService.class);
    }
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String openid = request.getParameter("openid");
		String feedbackid = request.getParameter("feedbackid");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		String acessToken;
		if (StringUtils.isNotEmpty(openid) && StringUtils.isNotEmpty(feedbackid)) {
			try {
				acessToken = weiXinService.getToken();
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("openid", openid);
				map.put("feedbackid", feedbackid);
				map.put("access_token", acessToken);
				String data = WebUtil.readContentFromGet(WeixinUtil.WEIXINBASE_URL+"payfeedback/update", map);
//				JSONObject res = null;
//				res = JSONObject.fromObject(data);
				response.getWriter().print(data);
				
			
				JSONObject obj = JSONObject.fromObject(data);
				final String code = obj.getString("errcode");	
				final String msg = obj.getString("errmsg");	
				if(code.equals("0")&&msg.equals("ok")){
					FileUtil.addLog(request,"updateFeedBack", channelNo, 
							"feedbackid", feedbackid, 
							"openid", openid,
							"code", "success");
				}else{
					FileUtil.addLog(request,"updateFeedBack", channelNo, 
							"feedbackid", feedbackid, 
							"openid", openid,
							"code", "fail");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateFeedBack：" + e);
				try {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("errcode", Constants.ERROR_SYSTEM);
					map.put("errmsg", Constants.ERROR_SYSTEM_PROMPT);
					
					String data =JSONObject.fromObject(map).toString();
					response.getWriter().print(data);
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else {
			try {
//				WebUtil.sendErrorInvalidParams(response);
				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("errcode", Constants.ERROR_INVALIDPARAMS);
				map.put("errmsg", Constants.ERROR_INVALIDPARAMS_PROMPT);
				
				String data =JSONObject.fromObject(map).toString();
				response.getWriter().print(data);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	
	}
}
