package com.shangpin.wireless.api.pay.unionpay;

import java.security.SignatureException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.pay.unionpay.UnionUtil.Entity;
import com.shangpin.wireless.api.util.WebUtil;

public class UnionPayModelV2 {
	
	protected final Log log = LogFactory.getLog(UnionPayModelV2.class.getSimpleName());
	
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
	public UnionPayModelV2(String orderid, String ordertime, String expiretime, String totalFee) throws SignatureException, Exception {
//		signature=md5(key1=value1&key2=value2&...&keyn=valuen&md5(secret_key))
//		1.	被签名字符串中的关键信息需要按照key值做升序排列
		
//		报文发送前，需将各接口定义的字段信息进行URL编码后再进行如下形式的拼接。
//		key1=value1&key2=value2&charset=UTF-8&...&keyn=valuen&signMethod=MD5&signature=3280082966dccfcc62d0234c9253e746
//		需要对key=value中的value进行URL编码

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

		long now = System.currentTimeMillis();
		Entity entity = UnionUtil.OrderQueue.get(orderid);
		
		if (entity == null) {
			entity = new Entity();
			entity.orderid = orderid;
			entity.payamount = totalFee;
			entity.firstTime = now;
		}
		entity.ordertime = ordertime;
		data = entity.tn;
		if (entity.tn == null || !totalFee.equals(entity.payamount)) {
			String content = WebUtil.readContentFromPost(UnionUtil.URL_BUY, postData);

			HashMap<String, String> resp = new HashMap<String, String>();
			boolean valid = UnionUtil.verifyResponse(content, UnionUtil.SECRET, resp);
			
			if (valid) {
				respCode = resp.get(UnionUtil.KEY_CODE);
				if ("00".equals(respCode)) {
					data = entity.tn = resp.get(UnionUtil.KEY_TN);
					entity.payamount = totalFee;
				} else {
					respMsg = resp.get(UnionUtil.KEY_MESSAGE);
				}
			} else {
				respCode = "04";
				respMsg = "签名验证失败";
				data = null;
				
//				("UnionPayModelV2:: 签名验证失败");
				log.debug("UnionPayModelV2 : verify fail");
			}
		} else if (entity.isPaid) {
			respCode = "03";
			respMsg = "订单已经完成支付";
			data = null;
		}
		log.debug("respCode = " + respCode + "; respMsg = " + respMsg + "; data = " + data);
		
		UnionUtil.OrderQueue.put(orderid, entity);
	}

	public static void main (String[] args) {
//		try {
//			UnionPayModelV2 model = new UnionPayModelV2("2013062410768", "20130627151145", "20130627161145", "331500", true);
//		} catch (SignatureException e) {
//		 Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//	 Auto-generated catch block
//			e.printStackTrace();
//		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		Date now = new Date();
//		String expiretime = null, resttime = null;	//	单位second
//		try {
//			Date ordertime = sdf.parse("20130628171556");
//			long endtime = ordertime.getTime() + 60*1000 * 60;
////			expiretime = String.valueOf((now.getTime() - ordertime.getTime())/(1000*60));
//			expiretime = sdf.format(new Date(endtime));
//			resttime = String.valueOf((endtime - now.getTime())/(1000));
//			System.out.println(sdf.format(new Date(ordertime.getTime())));
//			System.out.println(expiretime);
//			System.out.println(resttime);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("version", "1.0.0");
		map.put(UnionUtil.KEY_CHARSET, UnionUtil.CHARSET);
		map.put("transType", "01");	//	01 消费; 02 预授权
		map.put("merId", UnionUtil.MERCHANT);
		map.put("backEndUrl", UnionUtil.URL_NOTIFY);
		map.put("frontEndUrl", UnionUtil.URL_NOTIFY);
		map.put("orderTime", "20140307040126");
		map.put("orderTimeout", "20140307044916");
		map.put(UnionUtil.KEY_ORDER_ID, "20140307684375gift557");
		map.put("orderAmount", "61000");
		map.put("orderCurrency", "156");
//		map.put("orderDescription", "莫国强");
//		map.put("merReserved", "abc");	//	商户保留域
		
//		Map<String, String> resp = new HashMap<String, String>();
//		boolean validResp = UpmpService.trade(map, resp);
		
		String signature = UnionUtil.buildSignature(map, UnionUtil.SECRET);
	}
}
