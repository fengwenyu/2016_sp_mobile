package com.shangpin.wireless.api.pay.wxpay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.ApplicationContext;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.WeixinPayOrder;
import com.shangpin.wireless.api.pay.alipay.GetAlipayTokenServlet;
import com.shangpin.wireless.api.service.WeixinPayOrderService;
import com.shangpin.wireless.api.util.ApplicationContextUtil;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
/**
 * 微信用户维权
 * @author liling
 * 
 */
public class CustomerPayFeedBack extends HttpServlet {
	
	protected final Log log = LogFactory.getLog(CustomerPayFeedBack.class.getSimpleName());

	private static final long serialVersionUID = -3715354238206178407L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		("维权通知!");
		log.debug("CustomerPayFeedBack start");
		try {
			// 获得通知参数
			String customerData = WebUtil.read(request.getInputStream());
			String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
			if (StringUtil.isNotEmpty(customerData)) {
				HashMap<String, String> map = new HashMap<String, String>();
				HashMap<String, String> mapsign = new HashMap<String, String>();
				Document doc = StringToDocument(customerData);
				Element root = doc.getRootElement();
				Iterator<Element> rootIter = root.elementIterator();
				String appSignaturewx = null;
				while (rootIter.hasNext()) {
					Element rootElement = rootIter.next();
					if(rootElement.getName().equals("PicInfo")){
						String flag="0";
						String picInfo = null;
						for (Iterator i = rootElement.elementIterator("item"); i.hasNext();) {
							// 获取节点元素
							rootElement =(Element)i.next();
							if(flag.equals("0")){
								if(StringUtil.isNotEmpty(rootElement.elementText("PicUrl"))){
									picInfo=rootElement.elementText("PicUrl");
									flag="1";
								}
							}else{
								if(StringUtil.isNotEmpty(rootElement.elementText("PicUrl"))){
									picInfo=picInfo+","+rootElement.elementText("PicUrl");
								}
								
							}
						}
						map.put("PicInfo", picInfo);
					}else{
						map.put(rootElement.getName(), rootElement.getText());
					}
					 
				}
				mapsign.put("openid", map.get("OpenId"));
				mapsign.put("appid", map.get("AppId"));
				mapsign.put("timestamp", map.get("TimeStamp"));
				appSignaturewx = map.get("AppSignature");
				mapsign.put("appkey", WeixinUtil.PAYSIGNKEY);
				String appSignature = SHA1Util.Sha1(WeixinUtil.attachParams(mapsign, true, false));
				if (appSignature.equals(appSignaturewx)) {
					HashMap<String, String> mapcontent = new HashMap<String, String>();
					mapcontent.put("OpenId", map.get("OpenId"));
					mapcontent.put("MsgType", map.get("MsgType"));
					mapcontent.put("FeedBackId", map.get("FeedBackId"));
					String transId=map.get("TransId");
					String reason= map.get("Reason");
					String solution= map.get("Solution");
					String extInfo= map.get("ExtInfo");
					String picInfo= map.get("PicInfo");
					if(StringUtil.isNotEmpty(transId)){
						mapcontent.put("TransId", transId);
					}
					if(StringUtil.isNotEmpty(reason)){
						mapcontent.put("Reason",reason);
					}
					if(StringUtil.isNotEmpty(solution)){
						mapcontent.put("Solution", map.get("Solution"));
					}
					if(StringUtil.isNotEmpty(extInfo)){
						mapcontent.put("ExtInfo", map.get("ExtInfo"));
					}
					if(StringUtil.isNotEmpty(picInfo)){
						mapcontent.put("PicInfo", picInfo);
					}
					ApplicationContext ac = ApplicationContextUtil.get("ac");
					WeixinPayOrderService weixinPayOrderService = (WeixinPayOrderService) ac.getBean(WeixinPayOrderService.SERVICE_NAME);
					if(StringUtil.isNotEmpty(mapcontent.get("TransId"))){
						WeixinPayOrder weixinPayOrder=weixinPayOrderService.findByTransId(mapcontent.get("TransId"));
						if(weixinPayOrder!=null){
							mapcontent.put("OrderNo", weixinPayOrder.getOrderNo());
						}
					}
					JSONObject content = JSONObject.fromObject(mapcontent);
					JSONObject json = new JSONObject();
					json.put("errcode", "0");
					json.put("errmsg", "ok");
					json.put("content", content);
					String postData = json.toString();

					// 先验签如果验证签名通过，则把信息推给css系统
					String resData = WebUtil.readContentFromPost(Constants.CSS_WXFEEDBACK_URL, postData);
					JSONObject obj = JSONObject.fromObject(resData);
					final String code = obj.getString("errcode");	
					final String msg = obj.getString("errmsg");	
					if(code.equals("0")&&msg.equals("ok")){
						FileUtil.addLog(request,"api/weixinpay_customer", channelNo, 
								mapcontent.get("FeedBackId"),
								mapcontent.get("MsgType"),
								"code", "success");
					}else{
						FileUtil.addLog(request,"api/weixinpay_customer", channelNo, 
								mapcontent.get("FeedBackId"),
								mapcontent.get("MsgType"),
								"code", "fail");
					}
					
					
					
				} else {
//					("AppSignature签名验证失败,请检查");
				    log.debug("AppSignature verify fail,Please Check");
				}
			} else {
//					("没有接收到维权通知");
				    log.debug("Not receive customerPayFeedBack");
			}
		} catch (Exception e) {
			try {
				log.error("CustomerPayFeedBack :"+e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		log.debug("CustomerPayFeedBack end");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	}

	public static Document StringToDocument(String text) {
		try {
			return DocumentHelper.parseText(text);
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	}
}
