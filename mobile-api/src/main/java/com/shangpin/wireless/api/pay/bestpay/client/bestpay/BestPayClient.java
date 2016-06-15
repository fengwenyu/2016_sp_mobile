package com.shangpin.wireless.api.pay.bestpay.client.bestpay;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangpin.wireless.api.domain.BestpayorderShangpin;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.pay.bestpay.client.DealProcessorService;
import com.shangpin.wireless.api.pay.bestpay.client.DealProcessorServiceImplServiceClient;
import com.shangpin.wireless.api.service.BestpayByOrderService;
import com.shangpin.wireless.api.util.DBType;
import com.shangpin.wireless.api.util.HqlHelper;

public class BestPayClient {
	protected final Log log = LogFactory.getLog(BestPayClient.class);
	private static final String MEPF_ADDRESS;
	private static final String PARTNERID;
	static {
		
		MEPF_ADDRESS = Constants.BESTPAY_MEPFADDRESS_URL +"services/MPProcessor";
		PARTNERID = Constants.BESTPAY_PARTNERID_VARIATE;
		
	}
	private BestpayByOrderService bestpayByOrderService;
	BestpayorderShangpin bestpayorderShangpin=null;
	
	
	
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 * @throws DocumentException 
	 */
	public String getOrderBestPay(String ver,String orderid,String count,String phonecode,String amount,String goodsname,String sig,ServletContext sc) throws UnsupportedEncodingException, DocumentException{
		String keep = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()).substring(2);
		String arg0 ="001001|440000-payeasySP-1-121.33.197.198|1|121.33.197.198|";
		String arg1 ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
					  	"<PayPlatRequestParameter>"+
						"<CTRL-INFO WEBSVRNAME=\"订单生成\" WEBSVRCODE=\"001001\" APPFROM=\"001440060510005-TISSON001-"+ver+"|121.33.197.198\" KEEP=\""+keep+"\" />"+
						"<PARAMETERS>"+
						"<PARTNERID>"+PARTNERID+"</PARTNERID>"+
						"<PRODUCTNO>"+phonecode+"</PRODUCTNO>"+
						"<PARTNERORDERID>"+orderid+"</PARTNERORDERID>"+
						"<TXNAMOUNT>"+amount+"</TXNAMOUNT>"+
						"<BUSINESSTYPE></BUSINESSTYPE>"+
						"<GOODSID></GOODSID>"+
						"<GOODSNAME>"+goodsname+"</GOODSNAME>"+
						"<GOODSCOUNT>"+count+"</GOODSCOUNT>"+
						"<SIG></SIG>"+
						"</PARAMETERS>"+
						"</PayPlatRequestParameter>";
		
		DealProcessorServiceImplServiceClient dpsis = new DealProcessorServiceImplServiceClient();
		DealProcessorService dps = dpsis.getDealProcessorServiceImplPort(MEPF_ADDRESS);
		String result = dps.dispatchCommand(arg0, arg1);	
		Map<String, String> map = new HashMap<String, String>();
		SAXReader saxReader = new SAXReader();
		Document document;
		document = saxReader.read(new ByteArrayInputStream(result.getBytes("utf-8")));
		Element root = document.getRootElement();
		JSONObject obj = new JSONObject();
		JSONObject content = new JSONObject();
		BestpayByOrderService bestpayByOrderService = null;
		try {
			bestpayByOrderService = getBean(sc);
		} catch (ServletException e1) {
			e1.printStackTrace();
			log.error("BestPayClient ："+e1);
		}
		if("000000".equals(root.element("RESPONSECODE").getText())){
			obj.put("code", 0);
			obj.put("msg", "");
			content.put("PARTNERID", root.element("PARTNERID").getText());
			content.put("PARTNERNAME", root.element("PARTNERNAME").getText());
			content.put("SUPPLYORGCODE1", root.element("PARTNERID").getText());
			content.put("SUPPLYORGCODE2", root.element("PARTNERID").getText());
			content.put("SUPPLYORGCODE3", root.element("PARTNERID").getText());
			content.put("PRODUCTNO", phonecode);
			content.put("PARTNERORDERID", root.element("PARTNERORDERID").getText());
			content.put("ORDERID",  root.element("ORDERID").getText());
			content.put("TXNAMOUNT", amount);
			content.put("RATING", root.element("RATING").getText());
			content.put("GOODSNAME", root.element("GOODSNAME").getText());
			content.put("GOODSCOUNT", count);
			content.put("SIG", root.element("SIG").getText());	
			obj.put("content", content);				
			bestpayorderShangpin=saveBean(root);
			try {
				bestpayByOrderService.save(bestpayorderShangpin, DBType.dataSourceAPI.toString());
			} catch (Exception e) {
				e.printStackTrace();
				log.error("BestPayClient :"+e);
			}
		}else if("010006".equals(root.element("RESPONSECODE").getText())){
			HqlHelper hqlHelper=findHql(orderid);
			try {
				bestpayorderShangpin=bestpayByOrderService.getByCondition(hqlHelper, DBType.dataSourceAPI.toString());
				obj.put("code", 0);
				obj.put("msg", "");
				content.put("PARTNERID", bestpayorderShangpin.getPartnerid());
				content.put("PARTNERNAME", bestpayorderShangpin.getPartnername());
				content.put("SUPPLYORGCODE1",bestpayorderShangpin.getPartnerid());
				content.put("SUPPLYORGCODE2", bestpayorderShangpin.getPartnerid());
				content.put("SUPPLYORGCODE3", bestpayorderShangpin.getPartnerid());
				content.put("PRODUCTNO", bestpayorderShangpin.getProductno());
				content.put("PARTNERORDERID", bestpayorderShangpin.getPartnerorderid());
				content.put("ORDERID",  bestpayorderShangpin.getOrderid());
				content.put("TXNAMOUNT", bestpayorderShangpin.getTxnamount());
				content.put("RATING", bestpayorderShangpin.getRating());
				content.put("GOODSNAME", bestpayorderShangpin.getGoodsname());
				content.put("GOODSCOUNT", bestpayorderShangpin.getGoodscount());
				content.put("SIG", bestpayorderShangpin.getSig());	
				obj.put("content", content);	
			} catch (Exception e) {
				e.printStackTrace();
				log.error("BestPayClient :"+e);
			}
		}
		else{
			obj.put("code", 1);
			obj.put("msg", "生成订单失败");
			obj.put("content", "");
		}
		return obj.toString();	
	}
	public static void main(String[] args) throws DocumentException 
	{
		String orderid = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()).substring(2);
		try {
			new BestPayClient().getOrderBestPay("",orderid, "1", "13315953167", "100", "箱包","",null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
	}
	
	public BestpayByOrderService  getBean(ServletContext sc)throws ServletException {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		bestpayByOrderService = (BestpayByOrderService) ctx.getBean(BestpayByOrderService.SERVICE_NAME);
			//bestpayByOrderService.save(bestpayorderShangpin, DBType.dataSourceAPI.toString());		
		return bestpayByOrderService;
	}

	public BestpayorderShangpin saveBean(Element root){
		bestpayorderShangpin=new BestpayorderShangpin();
		bestpayorderShangpin.setPartnerid(root.element("PARTNERID").getText());
		bestpayorderShangpin.setPartnername(root.element("PARTNERNAME").getText());
		bestpayorderShangpin.setSupplyorgcode1(root.element("PARTNERID").getText());
		bestpayorderShangpin.setSupplyorgcode2(root.element("PARTNERID").getText());
		bestpayorderShangpin.setSupplyorgcode3(root.element("PARTNERID").getText());
		bestpayorderShangpin.setProductno(root.element("PRODUCTNO").getText());
		bestpayorderShangpin.setPartnerorderid(root.element("PARTNERORDERID").getText());
		bestpayorderShangpin.setOrderid(root.element("ORDERID").getText());
		bestpayorderShangpin.setTxnamount(root.element("TXNAMOUNT").getText());
		bestpayorderShangpin.setRating(root.element("RATING").getText());
		bestpayorderShangpin.setGoodsname(root.element("GOODSNAME").getText());
		bestpayorderShangpin.setGoodscount(root.element("GOODSCOUNT").getText());
		bestpayorderShangpin.setSig(root.element("SIG").getText());	
		return bestpayorderShangpin;
	}
	public HqlHelper findHql(String orderid){
		HqlHelper hqlHelper= new HqlHelper(BestpayorderShangpin.class, "b");
		hqlHelper.addWhereCondition("b.partnerorderid=?", orderid);
		return hqlHelper;
	}

}
