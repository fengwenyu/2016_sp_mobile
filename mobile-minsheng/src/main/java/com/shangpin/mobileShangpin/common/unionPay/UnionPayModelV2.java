package com.shangpin.mobileShangpin.common.unionPay;


import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.shangpin.mobileShangpin.common.unionPay.UnionUtil.Entity;

import com.shangpin.mobileShangpin.common.util.WebUtil;



public class UnionPayModelV2 {
	
	private String data;
	private String respCode;
	private String respMsg;

	public String getData () {return data;}
	public String getResponseCode () {return respCode;}
	public String getResponseMessage () {return respMsg;}
	/**
	版本号	version	M	1.0.0
	字符编码	charset	M	全大写
	签名方法	signMethod	M	可参考 6.4签名方法
	签名信息	signature	M	
	交易类型	transType	M	例如 01 消费； 02 预授权
	商户代码	merId	M	定长15位数字
	通知URL	backEndUrl	M	交易成功后，异步通知商户后台的URL
	前台通知URL	frontEndUrl	O	交易成功后，客户端前台通知商户的URL
	收单机构代码	acqCode	C	当商户直接与银联移动支付系统相连时，该域可不出现。当商户通过其他系统间接与银联移动支付系统相连时，该域必须出现
	交易开始日期时间	orderTime	M	yyyyMMddHHmmss, 交易发生时的时间日期
	订单超时时间	orderTimeout	O	yyyyMMddHHmmss,订单超时时间
	商户订单号	orderNumber	M	最大40个字母、数字
	交易金额	orderAmount	M	
	交易币种	orderCurrency	O	
	订单描述	orderDescription	O	
	商户保留域	merReserved	O	
	请求方保留域	reqReserved	O	
	系统保留域	sysReserved	O
	*/	

	/**
	 * 银联支付 根据原始数据，获取银联加密后的字符串，数据格式有要求
	 * 
	 * @param orderNumber
	 *            订单编号 8-40位，字母加数字
	 * @param orderTime
	 *            订单时间 14位，yyyyMMddHHmmss
	 * @param orderAmount
	 *            订单金额 最长12位，以分为单位
	 * @param orderDescription
	 *            订单描述信息 最长32字节
	 * @param merReserved
	 *            商户保留域 最长2048字符
	 * @param test
	 *            是否测试环境
	 * @return
	 */
	public Map<String, String> unionPayModel(String orderid, String ordertime, String expiretime, String totalFee, boolean test) throws SignatureException, Exception {
//		signature=md5(key1=value1&key2=value2&...&keyn=valuen&md5(secret_key))
//		1.	被签名字符串中的关键信息需要按照key值做升序排列
		
//		报文发送前，需将各接口定义的字段信息进行URL编码后再进行如下形式的拼接。
//		key1=value1&key2=value2&charset=UTF-8&...&keyn=valuen&signMethod=MD5&signature=3280082966dccfcc62d0234c9253e746
//		需要对key=value中的value进行URL编码
		HashMap<String, String> returnMap = new HashMap<String, String>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("version", "1.0.0");
		map.put(UnionUtil.KEY_CHARSET, UnionUtil.CHARSET);
		map.put("transType", "01");	//	01 消费; 02 预授权
		map.put("merId", UnionUtil.MERCHANT);
		map.put("backEndUrl", UnionUtil.URL_NOTIFY);
		map.put("frontEndUrl", UnionUtil.URL_NOTIFY);
		map.put("orderTime", ordertime);
		map.put("orderTimeout", expiretime);
		map.put(UnionUtil.KEY_ORDER_ID, orderid);
		map.put("orderAmount", totalFee);
		map.put("orderCurrency", "156");
//		map.put("orderDescription", "莫国强");
//		map.put("merReserved", "abc");	//	商户保留域
		
//		Map<String, String> resp = new HashMap<String, String>();
//		boolean validResp = UpmpService.trade(map, resp);
		
		String signature = UnionUtil.buildSignature(map, UnionUtil.SECRET);

		map.put(UnionUtil.KEY_SIGNATURE_METHOD, UnionUtil.SIGNATURE_METHOD);
		map.put(UnionUtil.KEY_SIGNATURE, signature);
		final String postData = UnionUtil.attachParams(map, false, true);
		System.out.println("postData = " + postData);

		long now = System.currentTimeMillis();
		Entity entity = UnionUtil.OrderQueue.get(orderid);
		
		if (entity == null) {
			entity = new Entity();
			entity.orderid = orderid;
			entity.firstTime = now;
		}
		entity.ordertime = ordertime;
		entity.tn = null;
//		if (entity.tn == null) {
			String content = WebUtil.readContentFromPost(UnionUtil.URL_BUY, postData);

			HashMap<String, String> resp = new HashMap<String, String>();
			boolean valid = UnionUtil.verifyResponse(content, UnionUtil.SECRET, resp);
			
			if (valid) {
				respCode = resp.get(UnionUtil.KEY_CODE);
				if ("00".equals(respCode)) {
					entity.tn = resp.get(UnionUtil.KEY_TN);
				} else {
					respMsg = resp.get(UnionUtil.KEY_MESSAGE);
				}
			} else {
				System.out.println("UnionPayModelV2:: 签名验证失败");
			}
//		}
		data = entity.tn;
		UnionUtil.OrderQueue.put(orderid, entity);
		returnMap.put("signature", signature);
		returnMap.put("tn", data);
		return returnMap;
	}



	
	
	public static void main (String[] args) {
		UnionPayModelV2 model = new UnionPayModelV2();
		String data="丁赛";

//		  byte[] data1=DigestUtils.md5(data);
//		  byte[] data2=DigestUtils.md5(data);
		  String data1= DigestUtils.md5Hex(data);
		  String data2= DigestUtils.md5Hex(data);

		  System.out.println("one   "+ data1);
			System.out.println( "two   "+data2);
	String str="asdfghjklpasdfghjklpasdfghjklppl|logisticeaction!logisticeDetail?orderid=2013020508053&ticketno=120684667147";
	System.out.println("one   "+ str.toString().split("[|]")[0]);
	System.out.println( "two   "+str.toString().split("[|]")[1]);
//	responseUrl=weixinParams.toString().split("[|]")[1];
//		String totalFee="2100.45678";
//		BigDecimal totalFee2=new BigDecimal(totalFee);
//		BigDecimal totalFee4=new BigDecimal(100);
//		//BigDecimal totalFee3=100;
////		double i=Double.parseDouble(totalFee);
////		//System.out.println("数字"+totalFee2.multiply(totalFee4).toString().split("[.]")[0]);
//////		try {
//////			model.unionPayModel("2013070911260", "20130709190645", "20130709200645", "2100", true);
//////		} catch (SignatureException e) {
//////			// TODO Auto-generated catch block
//////			e.printStackTrace();
//////		} catch (Exception e) {
//////			// TODO Auto-generated catch block
//////			e.printStackTrace();
//////		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		String now = sdf.format(new Date());
//		System.out.println(now);
//		String expiretime = null, resttime = null;	//	单位second
//		try {
//			Date ordertime = sdf.parse("20130628171556");
//	System.out.println("时间"+ordertime);
//			String beforeBase64="";
//			String tn="20130628171556";
//			String resultUrl="http://m.aolai.com?argName=";
//			beforeBase64=beforeBase64+"tn="+tn+",resultUrl="+resultUrl+",usetestmode="+"true";
//			System.out.println(beforeBase64   );
//			long endtime = ordertime.getTime() + 60*1000 * 60;
////			expiretime = String.valueOf((now.getTime() - ordertime.getTime())/(1000*60));
//			expiretime = sdf.format(new Date(endtime));
//			//resttime = String.valueOf((endtime - now.getTime())/(1000));
//		//	System.out.println(sdf.format(new Date(ordertime.getTime())));
//			System.out.println("此刻时间"+expiretime);
//			System.out.println(resttime);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		String paydata="dG49MjAxMzA3MTcxNDU4NTUwMDk3OTMyLHJlc3VsdFVybD1odHRwJTNBJTJGJTJGMTkyLjE2OC43%0D%0ALjIzNCUzQTgwODAlMkZ1bmlvblBheVRyYWRlQWN0aW9uJTIxdW5pb25QYXlSZXN1bHQlM0ZhcmdO%0D%0AYW1lJTNELHVzZXRlc3Rtb2RlPXRydWU%3D";
//		try {
//			paydata=URLDecoder.decode(paydata, "UTF-8");
//			System.out.println("第一次解码"+paydata);
//			try {
//				String s=	new String(Base64.getBASE624(paydata));
//				System.out.println(s);
//			System.out.println("第二次解码"+	new String(Base64.getBASE624(paydata)));
//			
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		String s=	"resultUrl=http%3A%2F%2F192.168.7.234%3A8080%2FunionPayTradeAction%21unionPayResult%3FargName%3D";
//	System.out.println("而刺激金额"+URLDecoder.decode(s, "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	
	}
}
