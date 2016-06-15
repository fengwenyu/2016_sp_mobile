package com.shangpin.wireless.api.pay.bestpay.server.impl;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.pay.bestpay.server.service.INoticefyResult;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.RSACipher;
import com.shangpin.wireless.api.util.WebUtil;


public class NoticefyResult implements INoticefyResult {

	protected final Log log = LogFactory.getLog(NoticefyResult.class.getSimpleName());

	private static String RESULT = null;
	private String WEBSVRNAME;
	private String WEBSVRCODE;
	private String APPFROM;
	private String PARTNERORDERID;
	private String TXNAMOUNT;
	private String STAT;
	private String SIG;
	private String KEEP;
	private String finalXML;

	public String dispatchCommand(String in0, String in1) {
		SAXReader saxReader = new SAXReader();
		Document document;
		Element parameters = null;
//		("\n---------------商户扣费通知接口接收报文请求---------------\n");
		log.info("merchant expense notify interface receive message request");
		try 
		{
			document = saxReader.read(new ByteArrayInputStream(in1.getBytes("utf-8")));
			Element root = document.getRootElement();
			Element ctrlInfo = root.element("CTRL-INFO");
			parameters = root.element("PARAMETERS");
			this.WEBSVRNAME = ctrlInfo.attribute("WEBSVRNAME").getText();
			this.WEBSVRCODE = ctrlInfo.attribute("WEBSVRCODE").getText();
			this.APPFROM = ctrlInfo.attribute("APPFROM").getText();
			this.KEEP = ctrlInfo.attribute("KEEP").getText();
			this.PARTNERORDERID=parameters.elementText("PARTNERORDERID");
			this.TXNAMOUNT=parameters.elementText("TXNAMOUNT");
			this.STAT=parameters.elementText("STAT");
			this.SIG=parameters.elementText("SIG");
			//使用PARTNERORDERID、TXNAMOUNT、STAT字段拼接
			//并调用提供的verify进行验签。
			boolean verify=false;
			String plainStr= PARTNERORDERID+TXNAMOUNT+STAT;
			RSACipher.setPublicKey(NoticefyResult.class.getClassLoader().getResource("mepf_publickey.der").getPath());
			verify=RSACipher.verify(plainStr, SIG);//验签
			log.info("verify:"+verify+",STAT:"+STAT);
			if(verify){
				if("S0C".equals(STAT)){
					final String payTypeId = "24";
	            	final String payTypeChildId = "52";            	
//	            	HashMap<String, String> reqmap = new HashMap<String, String>(); //处理订单
//	    			reqmap.put("orderno", PARTNERORDERID);
//	    			reqmap.put("paytypeId", payTypeId);
//	    			reqmap.put("paytypechildId", payTypeChildId);    			
//	    			String url = Constants.BASE_URL + "payconfirmpayment/";
	    			HashMap<String, String> reqmap = new HashMap<String, String>();
	    			reqmap.put("mainorderNo", PARTNERORDERID);
	    			reqmap.put("payTypeId", payTypeId);
	    			reqmap.put("childPayTypeId", payTypeChildId);
//	    			reqmap.put("orderAmount", TXNAMOUNT);
	    			reqmap.put("orderAmount", Constants.PAY_AMOUNT);
	    			String url = Constants.BASE_TRADE_URL + "order/UpdateOrderStatus";
	    			log.info("BestpayNoticefyResult : UpdateOrderStatus " + Thread.currentThread().getName());
	    			String data = WebUtil.readContentFromGet(url, reqmap);
    				JSONObject content = JSONObject.fromObject(data);
    				final String code = content.getString("code");
    				if ("0".equals(code)) {
    					// 记录访问日志
    					FileUtil.addLog(null, "payNoticefy", "", 
    							"orderid", PARTNERORDERID, 
    							"payid", payTypeId, 
    							"paychildid", payTypeChildId, 
    							"code", "success");
    				} else {
    					FileUtil.addLog(null, "payNoticefy", "",
    							"orderid", PARTNERORDERID, 
    							"payid", payTypeId, 
    							"paychildid", payTypeChildId, 
    							"code", "fail", 
    							"msg", content.getString("msg"));
    					log.warn("BestPay failed orderid : " + PARTNERORDERID + " (" + code + ")(" + content.getString("msg") + ")" + Thread.currentThread().getName());
    				}
				}
				return this.returnMessage("10", "000000", "SUCCESS");
			}else{
//				("接收翼支付系统通知验证签名失败，请检查！");
				log.info("NoticefyResultPayVerify fail");
				FileUtil.addLog(null, "payNoticefy", "",
						"orderid", PARTNERORDERID, 
						"code", "fail");
				return this.returnMessage("10", "000096", "验证签名失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.finalXML = this.returnMessage("10", "000096", "扣费同步失败");
			FileUtil.addLog(null, "payNoticefy", "",
					"orderid", PARTNERORDERID, 
					"code", "fail");
			log.error("NoticefyResult : "+e);
			return finalXML;
		}
	}
	
	private String returnMessage(String responsetype, String response,
			String responsecontent) {
		if ("000000".equals(response)) {
			RESULT = "SUCCESS";
		} else {
			RESULT = "FAIL";
		}		
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<PayPlatResponseParameter>\n");
		sb.append("<RESPONSE-INFO ");
		sb.append("REQWEBSVRCODE=\"" + WEBSVRCODE + "\" ");
		sb.append("RESPONSETYPE=\"" + responsetype + "\" ");
		sb.append("KEEP=\"" + KEEP + "\" ");
		sb.append("RESULT=\"" + RESULT + "\" />\n");
		sb.append("<RESPONSECODE>").append(response).append("</RESPONSECODE>\n");
		sb.append("<RESPONSECONTENT>").append(responsecontent).append("</RESPONSECONTENT>\n");
		sb.append("<SIG>").append(responsecontent).append("</SIG>\n");
		sb.append("<RESULTDATESET>\n");
		sb.append("</RESULTDATESET>\n");
		sb.append("</PayPlatResponseParameter>\n");
		return sb.toString();
	}	

}
