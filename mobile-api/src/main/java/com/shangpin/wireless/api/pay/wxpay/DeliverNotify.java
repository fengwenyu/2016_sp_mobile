package com.shangpin.wireless.api.pay.wxpay;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.shangpin.wechat.service.WeChatPublicService;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.WeixinPayOrder;
import com.shangpin.wireless.api.service.WeixinPayOrderService;
import com.shangpin.wireless.api.util.ApplicationContextUtil;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

public class DeliverNotify extends BaseServlet {
	/**
	 * 微信发货通知接口。商户发货后需通知微信
	 */
	private static final long serialVersionUID = 7472432582727507447L;
	
	protected final Log log = LogFactory.getLog(DeliverNotify.class.getSimpleName());
	WeChatPublicService weiXinService = null;

    @Override
    public void init() throws ServletException {
        weiXinService = (WeChatPublicService) getBean(WeChatPublicService.class);
    }
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		("发货通知!");
		log.debug("DeliverNotify start");
		try {
			String notifyData = WebUtil.read(request.getInputStream());
			final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
			if (StringUtil.isNotEmpty(notifyData)) {
				HashMap<String, String> map = new HashMap<String, String>();
				JSONObject content = JSONObject.fromObject(notifyData);
				if (null != content) {
					ApplicationContext ac = ApplicationContextUtil.get("ac");
					WeixinPayOrderService weixinPayOrderService = (WeixinPayOrderService) ac.getBean(WeixinPayOrderService.SERVICE_NAME);
					WeixinPayOrder weixinPayOrder = weixinPayOrderService.findByOrderId(content.getString("orderid"));
					if(weixinPayOrder!=null){
						WxPayHelper wxPayHelper = new WxPayHelper();
						String openid=weixinPayOrder.getOpenId();
						String transid=weixinPayOrder.getTransId();
						String orderid= content.getString("orderid");
						String deliverstatus=content.getString("deliverstatus");
						String delivermsg=content.getString("delivermsg");
						map.put("appid", WeixinUtil.APPID);
						map.put("openid", openid);
						map.put("transid", transid);
						map.put("out_trade_no", orderid);
						map.put("deliver_timestamp", Long.toString(new Date().getTime() / 1000));
						map.put("deliver_status", deliverstatus);
						map.put("deliver_msg", delivermsg);
						map.put("appkey", WeixinUtil.PAYSIGNKEY);
						String appsignature = wxPayHelper.GetDeliverSign(map);
						map.put("app_signature", appsignature);
						map.put("sign_method", "sha1");
						final String postData = JSONObject.fromObject(map).toString();
						String acessToken;
						acessToken = weiXinService.getToken();
						String resData = WebUtil.readContentFromPost(WeixinUtil.WEIXINBASE_URL + "pay/delivernotify?access_token=" + acessToken, postData);
//						HashMap<String, String> map2 = new HashMap<String, String>();
//						map2.put("errcode","0");
//						map2.put("errmsg","ok");
//						String data = JSONObject.fromObject(map2).toString();
//						response.getWriter().print(data);
						response.getWriter().print(resData);
						JSONObject obj = JSONObject.fromObject(resData);
						final String code = obj.getString("errcode");
						final String msg = obj.getString("errmsg");
						if (code.equals("0") && msg.equals("ok")) {
							FileUtil.addLog(request, "DeliverNotify", channelNo, 
									"openid", openid, 
									"transid", transid, 
									"out_trade_no",orderid, 
									"deliver_status", deliverstatus, 
									"deliver_msg", delivermsg, 
									"code", "success");
						} else {
							FileUtil.addLog(request, "DeliverNotify", channelNo, 
									"openid",openid, "transid",transid, 
									"out_trade_no", orderid, 
									"deliver_status", deliverstatus, 
									"deliver_msg", delivermsg, 
									"code", "fail");
						}
					}else{
						HashMap<String, String> maperror = new HashMap<String, String>();
						maperror.put("errcode","-1");
						maperror.put("errmsg", "根据订单号查询微信支付表出错");
						String data = JSONObject.fromObject(maperror).toString();
						response.getWriter().print(data);
					}
				
				}
			} else {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("errcode", Constants.ERROR_INVALIDPARAMS);
				map.put("errmsg", Constants.ERROR_INVALIDPARAMS_PROMPT);
				String data = JSONObject.fromObject(map).toString();
				response.getWriter().print(data);
			}
		} catch (Exception e) {
			try {
				log.error("DeliverNotify :"+e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		log.debug("DeliverNotify end");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	}
}
