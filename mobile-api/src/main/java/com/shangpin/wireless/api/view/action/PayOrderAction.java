package com.shangpin.wireless.api.view.action;

import com.shangpin.pay.bo.APPPayCommonBo;
import com.shangpin.pay.service.CommonPayService;
import com.shangpin.wireless.api.api2client.domain.BalanceAPIResponse;
import com.shangpin.wireless.api.api2server.domain.BalanceServerResponse;
import com.shangpin.wireless.api.api2server.domain.OrderdetailServerResponse;
import com.shangpin.wireless.api.api2server.domain.SPGoodsOrderServerResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.pay.bestpay.client.bestpay.BestPayClient;
import com.shangpin.wireless.api.pay.lakalapay.LakalaPayModel;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理支付类
 * 
 * @Author: wangfeng
 * @CreatDate: 2014-07-10
 */

public class PayOrderAction {
	public static Logger log = LoggerFactory.getLogger(PayOrderAction.class);
	boolean isCod = false;
	final String codPayTypeId = "2";
	final String codPaytypeChildId = "40";


	public String payInfo(HttpServletRequest request,String orderData, CommonPayService commonPayService, final String productNum, final String ver,
						  final String userid, final String mainpaymode, final String subpaymode, final String mainOrderid, String source, String token, String productno, String sig,
						  ServletContext sc) {
		return this.payInfo( request, orderData, commonPayService, productNum, ver, userid, mainpaymode, subpaymode, mainOrderid, source, token, productno, sig, sc, null);
	}



	public String payInfo(HttpServletRequest request,String orderData, CommonPayService commonPayService, final String productNum, final String ver,
			final String userid, final String mainpaymode, final String subpaymode, final String mainOrderid, String source, String token, String productno, String sig,
			ServletContext sc,String internalAmount) {
		String code = null;
		String msg = null;
		String orderonlinepayamount = null;
		String orderstatus = null;
		String orderstatusdesc = null;
		int count = 0;// 购买商品数量
		String goodsname = null;// 商品名称
		String ext=null;
		BalanceAPIResponse apiResponse = new BalanceAPIResponse();
		if (StringUtil.isNotEmpty(productNum, ver, userid, mainpaymode, subpaymode, mainOrderid)) {
			log.debug("PayOrderAction:: p = " + productNum + "; ver = " + ver + "; orderid = " + mainOrderid + "; (" + mainpaymode + "," + subpaymode + ") userid = " + userid);
			try {
				// 支付宝 20、 翼支付 24、 微信27、 海外支付宝30、海外微信32
				if ("20".equals(mainpaymode) || "24".equals(mainpaymode) || "27".equals(mainpaymode) || "30".equals(mainpaymode) || "32".equals(mainpaymode)|| "19".equals(mainpaymode)) { // 翼支付单独调用订单详情
					PayOrderData payOrderData = new PayOrderData(ver, productNum, mainOrderid, userid);
					if(StringUtils.isEmpty(orderData)){
						orderData = payOrderData.getContentOfOrderDetail();
					}
					
					OrderdetailServerResponse orderdetailServerResponse = new OrderdetailServerResponse();
					orderdetailServerResponse = OrderdetailServerResponse.json2BeanHigh4(orderData);
					
					BeanUtils.copyProperties(apiResponse, orderdetailServerResponse);
					code = orderdetailServerResponse.getCode();
					msg = orderdetailServerResponse.getMsg();
					if (Constants.SUCCESS.equals(orderdetailServerResponse.getCode())) {
						if("30".equals(mainpaymode) || "32".equals(mainpaymode)){
							BigDecimal goodFees = new BigDecimal(0);
							BigDecimal transportFee = new BigDecimal(0);
							BigDecimal duty = new BigDecimal(0);
							for (SPGoodsOrderServerResponse spgood : orderdetailServerResponse.getSpGoods()) {
								goodFees=goodFees.add(new BigDecimal(spgood.getGoodFees()));
								transportFee=transportFee.add(new BigDecimal(spgood.getTransportFee()));
								duty=duty.add(new BigDecimal(spgood.getDuty()));
								count += Integer.parseInt(spgood.getCount());
							}
							ext=getExt(goodFees.setScale(2,BigDecimal.ROUND_HALF_UP),transportFee.setScale(2,BigDecimal.ROUND_HALF_UP),duty.setScale(2,BigDecimal.ROUND_HALF_UP));

							//分账支付
							if("120".equals(subpaymode) && !StringUtils.isBlank(internalAmount)){
								ext = ext + ",splitAmount:" + internalAmount;
							}

						}else{
						
							for (SPGoodsOrderServerResponse spgood : orderdetailServerResponse.getSpGoods()) {
								count += Integer.parseInt(spgood.getCount());
							}
						}
						
						if (orderdetailServerResponse.getSpGoods().size() > 0) {
							goodsname = orderdetailServerResponse.getSpGoods().get(0).getProductname();
						}

						if (goodsname.length() > 10) {
							goodsname = goodsname.substring(0, 10) + "...";
						} else if (orderdetailServerResponse.getSpGoods().size() > 1) {
							goodsname = goodsname + "等";
						}
						
						orderonlinepayamount = orderdetailServerResponse.getOnlinepayamount();
						orderstatus = orderdetailServerResponse.getStatus();
						orderstatusdesc = orderdetailServerResponse.getStatusdesc();
					}
				} else {
					// 获取订单信息：订单编号，订单时间yyyyMMDDHHmmss，订单金额100.00，订单状态，订单状态描述
					PayOrderData payOrderData = new PayOrderData(ver, productNum, mainOrderid, userid);
					if(StringUtils.isEmpty(orderData)){
						orderData = payOrderData.getContentOfOrder();
					}
					
					BalanceServerResponse balanceServerResponse = new BalanceServerResponse();
					balanceServerResponse.json2BeanHigh4(orderData);
					BeanUtils.copyProperties(apiResponse, balanceServerResponse);
					code = balanceServerResponse.getCode();
					msg = balanceServerResponse.getMsg();
					if (Constants.SUCCESS.equals(balanceServerResponse.getCode())) {
						orderonlinepayamount = balanceServerResponse.getOnlinepayamount();
						orderstatus = balanceServerResponse.getStatus();
						orderstatusdesc = balanceServerResponse.getStatusdesc();
					}

				}
				/*
				 * 根据不同的支付方式，返回不同的内容： 货到付款：无额外逻辑 银联支付：需要把订单数据加密后的xml返回给客户端 主支付方式
				 * 子支付方式 货到付款（2） 货到付款（10） 手机货到付款 （40） Wap货到付款 （41） 无线银联支付（19）
				 * 无线银联支付（36） 无线支付宝（20） WAP支付（37） 安全支付（38） 浏览器安全支付（39）
				 * 钱包快捷支付（51） 无线拉卡拉（21） 无线拉卡拉（43） 翼支付 (24) 安全支付 (52)
				 */
				if (Constants.SUCCESS.equals(code)) {
					if (!"10".equals(orderstatus) && !"11".equals(orderstatus) && !"13".equals(orderstatus)) {
						apiResponse.setCode(Constants.ERROR_CANPAY);
						apiResponse.setCod(Constants.ERROR_CANPAY);
						apiResponse.setMsg("订单状态已经为" + orderstatusdesc + "，无法支付");
					} else {
						// 检查订单的金额格式是否正确，必须为"123.00"元
						final String totalfee = orderonlinepayamount;
						double totalFee;
						try {
							totalFee = Double.parseDouble(totalfee);
						} catch (Exception e) {
							totalFee = -1;
						}
						// totalFee=0.01;//测试价格切换为一分
						apiResponse.setMainpaymode(mainpaymode);
						apiResponse.setSubpaymode(subpaymode);
						if (totalFee < 0) {
							apiResponse.setCode(Constants.ERROR_FEE_FORMAT);
							apiResponse.setCod(Constants.ERROR_FEE_FORMAT);
							apiResponse.setMsg("订单金额格式非法");
							log.error("PayOrderServlet：订单金额格式非法(" + totalFee + ")");
							return apiResponse.obj2Json();
						} else {
							if ("19".equals(mainpaymode)) { // 银联
								APPPayCommonBo appPayCommonBo=new APPPayCommonBo("UNAPP", mainOrderid,goodsname, goodsname,Double.toString(totalFee), null,null);
								Map<String,Object> map =commonPayService.appPay(appPayCommonBo);
								if(map!=null&&map.get("jsonStr")!=null){
									apiResponse.setData(map.get("jsonStr").toString());
								}
								
							} else if ("20".equals(mainpaymode)) { // 支付宝
								APPPayCommonBo appPayCommonBo=new APPPayCommonBo("ALIAPP", mainOrderid,goodsname, goodsname,Double.toString(totalFee), null,null);
								Map<String,Object> map =commonPayService.appPay(appPayCommonBo);
								if(map!=null&&map.get("jsonStr")!=null){
									apiResponse.setData(map.get("jsonStr").toString());
								}
								
							} else if ("21".equals(mainpaymode)) { // 拉卡拉
																	// amount:交易金额.单位为元，小数点后两位（例100.20）
								LakalaPayModel model = new LakalaPayModel(mainOrderid, ((int) (totalFee * 100)));
								apiResponse.setData(model.getData());
								apiResponse.setPayArg1(model.getMerchant());
								apiResponse.setPayArg2(model.getMinCode());
								apiResponse.setPayArg3(model.getRandomNum());
							} else if ("24".equals(mainpaymode)) { // 翼支付
								StringBuilder totalFeeBuff = new StringBuilder();
								totalFeeBuff.append((int) (totalFee * 100));
								BestPayClient bestPay = new BestPayClient();
								String reqjson = bestPay.getOrderBestPay(ver, mainOrderid, String.valueOf(count), productno, totalFeeBuff.toString(), goodsname, sig, sc);
								apiResponse.setCode(code);
								apiResponse.setCod(code);
								apiResponse.setMsg(msg);
								apiResponse.setData(reqjson);
							} else if ("2".equals(mainpaymode)) { // 货到付款
								isCod = true;
								try {
									log.debug("PayOrderServlet::updateOrderStatus " + Thread.currentThread().getName());
									PayOrderData payOrderData = new PayOrderData(ver, productNum, mainOrderid, userid, mainpaymode, subpaymode);
									if (StringUtil.compareVer(ver, "2.7.0") < 0) {
										payOrderData.setMainPayMode(codPayTypeId);
										payOrderData.setSubPayMode(codPaytypeChildId);
									}
									String data = payOrderData.getContentOfModelType();
									JSONObject content = JSONObject.fromObject(data);
									final String payconfirmcode = content.getString("code");
									if ("0".equals(payconfirmcode)) {
										log.debug("COD success orderid = " + mainOrderid + "; " + Thread.currentThread().getName());
									} else {
										apiResponse.setCode(payconfirmcode);
										apiResponse.setCod(payconfirmcode);
										apiResponse.setMsg(content.getString("msg"));
										log.warn("COD failed orderid = " + mainOrderid + " (" + payconfirmcode + ")(" + content.getString("msg") + ")"
												+ Thread.currentThread().getName());
									}
								} catch (Exception e) {
									e.printStackTrace();
									log.error("CODNotify：" + e);
								}
							} else if ("27".equals(mainpaymode) ) {
								APPPayCommonBo appPayCommonBo=new APPPayCommonBo("WEIXINAPP", mainOrderid,goodsname, goodsname,Double.toString(totalFee), getIpAddr(request),null);
								Map<String,Object> map =commonPayService.appPay(appPayCommonBo);
								Map<String,String> payMap =getWechatMap(map);
								if(map!=null&&payMap!=null){
									apiResponse.setWechat(payMap);
								}
								
							} else if("32".equals(mainpaymode)){//32海外微信
								APPPayCommonBo appPayCommonBo=new APPPayCommonBo("WEIXINAPPSEA", mainOrderid,goodsname, goodsname,Double.toString(totalFee), getIpAddr(request),ext,userid);
								Map<String,Object> map =commonPayService.appPay(appPayCommonBo);
								Map<String,String> payMap =getWechatSeaMap(map);
								if(map!=null&&payMap!=null){
									apiResponse.setWechat(getWechatSeaMap(map));
								}
								
							} else if ("30".equals(mainpaymode)) {
//								APPPayCommonBo appPayCommonBo=new APPPayCommonBo(GetPayGatewayUtil.getGateway(), mainOrderid,goodsname, goodsname,Double.toString(totalFee), null,ext);

								//海外支付宝  分账支付宝 都走分账
								APPPayCommonBo appPayCommonBo=new APPPayCommonBo("ALIFZAPP", mainOrderid,goodsname, goodsname,Double.toString(totalFee), null,ext);
								Map<String,Object> map =commonPayService.appPay(appPayCommonBo);
								if(map!=null&&map.get("jsonStr")!=null){
									apiResponse.setData(map.get("jsonStr").toString());
								}
								
							} else {
								if (StringUtil.compareVer(ver, "2.7.0") >= 0) {
									apiResponse.setCode(Constants.SUCCESS);
									apiResponse.setCod(Constants.SUCCESS);
									apiResponse.setMsg("");
								} else {
									apiResponse.setCode(Constants.ERROR_NO_PAYMODE);
									apiResponse.setCod(Constants.ERROR_NO_PAYMODE);
									apiResponse.setMsg("不支持的支付方式");
									log.error("PayOrderServlet：不支持的支付方式(" + mainpaymode + ")");
								}
								return apiResponse.obj2Json();

							}
							if (!"2".equals(mainpaymode)) { // 不是货到付款
								new Thread(new Runnable() {
									@Override
									public void run() {
										try {
											PayOrderData payOrderData = new PayOrderData(ver, productNum, mainOrderid, userid);
											payOrderData.setMainPayMode(mainpaymode);
											payOrderData.setSubPayMode(subpaymode);
											String data = payOrderData.getContentOfModelType();
											JSONObject content = JSONObject.fromObject(data);
											final String code = content.getString("code");
											if (!"0".equals(code)) {
												log.warn("chagePayMode failed orderid = " + mainOrderid + " (" + code + ")(" + content.getString("msg") + ")");
											}
										} catch (Exception e) {
											e.printStackTrace();
											log.error("CODNotify：" + e);
										}
									}
								}).start();
							}
						}
					} // else

					return apiResponse.obj2Json();
				} else {
					apiResponse.setCod(code);
					apiResponse.setCode(code);
					apiResponse.setMsg(msg);
					return apiResponse.obj2Json();
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				apiResponse.setCode(Constants.ERROR_SYSTEM);
				apiResponse.setCod(Constants.ERROR_SYSTEM);
				apiResponse.setMsg("订单支付异常");
				return apiResponse.obj2Json();
			}
		}
		return apiResponse.obj2Json();

	}


	@SuppressWarnings("unchecked")
	private Map<String, String> getWechatSeaMap(Map<String, Object> map) {
		
		try{
			Map<String,String> wechatMap=new HashMap<String,String>();
			Map<String,String> valMap=new HashMap<String,String>();
			valMap=(Map<String, String>) map.get("requestParams");
			wechatMap.put("payCode", "0");
			wechatMap.put("payMsg", "OK");
			wechatMap.put("appId", valMap.get("appid"));
			wechatMap.put("partnerId", valMap.get("partnerid"));
			wechatMap.put("prepayId", valMap.get("prepayid"));
			wechatMap.put("nonceStr", valMap.get("noncestr"));
			wechatMap.put("timestamp", valMap.get("timestamp"));
			wechatMap.put("sign", valMap.get("sign"));
			wechatMap.put("dataPackage", valMap.get("package"));
			return wechatMap;
		}catch(Exception e){
				return null;
		}
	}


	private String getExt(BigDecimal goodFees, BigDecimal transportFee,
			BigDecimal duty) {
		StringBuffer str=new StringBuffer("duty:");
		str.append(duty).append(",").append("goodFees:").append(goodFees).append(",").append("transportFee:").append(transportFee);
		return str.toString();
	}


	@SuppressWarnings("unchecked")
	private Map<String, String> getWechatMap(Map<String, Object> map) {
		try{
			Map<String,String> wechatMap=new HashMap<String,String>();
			Map<String,String> valMap=new HashMap<String,String>();
			valMap=(Map<String, String>) map.get("requestParams");
			wechatMap.put("payCode", "0");
			wechatMap.put("payMsg", "OK");
			wechatMap.put("appId", valMap.get("appid"));
			wechatMap.put("partnerId", valMap.get("partnerid"));
			wechatMap.put("prepayId", valMap.get("prepayid"));
			wechatMap.put("nonceStr", valMap.get("noncestr"));
			wechatMap.put("timestamp", valMap.get("timestamp"));
			wechatMap.put("sign", valMap.get("sign"));
			wechatMap.put("dataPackage", valMap.get("package"));
			return wechatMap;
		}catch(Exception e){
			return null;
		}
		
	}


	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if(StringUtil.isNotEmpty(ip)){
			if(ip.indexOf(",")>-1){
				ip=ip.split("[,]")[0];
			}
		}
		return ip;
	}



}

class PayOrderData {

	private String ver; // 版本号
	private String productNum;
	private String orderId;
	private String userId;
	private String payTypeId;
	private String childPayTypeId;
	private String mainPayMode;
	private String subPayMode;
	

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getPayTypeId() {
		return payTypeId;
	}

	public void setPayTypeId(String payTypeId) {
		this.payTypeId = payTypeId;
	}

	public String getChildPayTypeId() {
		return childPayTypeId;
	}

	public void setChildPayTypeId(String childPayTypeId) {
		this.childPayTypeId = childPayTypeId;
	}

	public PayOrderData(String ver, String productNum, String orderId, String userId) {
		this.ver = ver;
		this.productNum = productNum;
		this.orderId = orderId;
		this.userId = userId;
	}
	
	public PayOrderData(String ver, String productNum, String orderId, String userId, String mainPayMode, String subPayMode) {
		this.ver = ver;
		this.productNum = productNum;
		this.orderId = orderId;
		this.userId = userId;
		this.mainPayMode = mainPayMode;
		this.subPayMode = subPayMode;
	}

	public String getContentOfOrderDetail() {
		HashMap<String, String> map = new HashMap<String, String>();
		String url = Constants.BASE_TRADE_URL + "order/getorder";
		map.put("userid", userId);
		map.put("mainorderNo", orderId);
		String data = null;
		try {
			data = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
		}
		return data;
	}

	/**
	 * 订单信息
	 * 
	 * @return
	 */
	public String getContentOfOrder() {
		HashMap<String, String> map = new HashMap<String, String>();
		String url = Constants.BASE_TRADE_URL + "order/getorder";
		map.put("userid", userId);
		map.put("mainorderNo", orderId);
		String data = null;
		try {
			data = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {

		}
		return data;
	}

	/**
	 * 修改支付方式
	 * 
	 * @return
	 */
	public String getContentOfModelType() {
		HashMap<String, String> map = new HashMap<String, String>();
		String url = Constants.BASE_TRADE_URL + "order/UpdatePayType";
		map.put("userid", userId);
		map.put("mainorderno", orderId);
		if ("2".equals(mainPayMode)) {
			map.put("paytype", "4_" + mainPayMode + "_" + subPayMode);
		} else {
			map.put("paytype", "0_" + mainPayMode + "_" + subPayMode);
		}
		String data = null;
		try {
			data = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {

		}
		return data;
	}

	/**
	 * 订单回调
	 * 
	 * @return
	 */
	public String getContentOfOrderStatus() {
		HashMap<String, String> map = new HashMap<String, String>();
		String url = Constants.BASE_TRADE_URL + "order/UpdateOrderStatus";
		map.put("userid", userId);
		map.put("mainorderNo", orderId);
		map.put("payTypeId", payTypeId);
		map.put("childPayTypeId", childPayTypeId);
		map.put("orderAmount", Constants.PAY_AMOUNT);
		String data = null;
		try {
			data = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
		}
		return data;
	}


	public String getMainPayMode() {
		return mainPayMode;
	}

	public void setMainPayMode(String mainPayMode) {
		this.mainPayMode = mainPayMode;
	}

	public String getSubPayMode() {
		return subPayMode;
	}

	public void setSubPayMode(String subPayMode) {
		this.subPayMode = subPayMode;
	}
	

}
