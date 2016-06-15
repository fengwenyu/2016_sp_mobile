package com.shangpin.wireless.api.pay.bestpay.client.bestpay;

import com.shangpin.wireless.api.pay.bestpay.client.DealProcessorService;
import com.shangpin.wireless.api.pay.bestpay.client.DealProcessorServiceImplServiceClient;

public class Test {

	private final static String CS_ADDRESS = "http://192.168.7.238:8080/api/services/bestpay_notify"; 
	private final static String sig="85014514116D929F2A201A8512946DD1675EC2FC544EDAAC5D26F25DB1EDD08EA2A462CAF41399A16BB6B2767182F337359AD329F45D68BBCE3D51F758F4951EE041A923CA6395833C821F84A2F56EE4D3E0651B61B4CACED69678D2CCCFC34FE5CA69B59E4D8C8413B251A1D838091170181C2C53F659634566E9720C94F816";//签名
	private final static String stat="SOC";//soc 成功 sox 失败
	private  final static String orderid="201309101325";
	public static void main(String[] args) 
	{
		String arg0 ="001001|440000-payeasySP-1-121.33.197.198|1|121.33.197.198|";
		String arg1 ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
					  	"<PayPlatRequestParameter>"+
						"<CTRL-INFO WEBSVRNAME=\"扣费通知\" WEBSVRCODE=\"002001\" APPFROM=\"001440060510005-TISSON001-1|121.33.197.198\" KEEP=\"20110513015738\" />"+
						"<PARAMETERS>"+
						"<PARTNERORDERID>"+orderid+"</PARTNERORDERID>"+
						"<TXNAMOUNT>100</TXNAMOUNT>"+
						"<STAT>"+stat+"</STAT>"+
						"<SIG>"+sig+"</SIG>"+
						"</PARAMETERS>"+
						"</PayPlatRequestParameter>";
		
		
		DealProcessorServiceImplServiceClient dpsis = new DealProcessorServiceImplServiceClient();
		DealProcessorService dps = dpsis.getDealProcessorServiceImplPort(CS_ADDRESS);
		String result = dps.dispatchCommand(arg0, arg1);
		
//		System.out.println("result:"+result);
	
	}
}
