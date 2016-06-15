package com.shangpin.mobileShangpin.platform.service.impl;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.CookieUtil;
import com.shangpin.mobileShangpin.common.util.FileUtil;
import com.shangpin.mobileShangpin.common.util.SysContent;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.CouponCodeService;
@Service("couponCodeService")
public class CouponCodeServiceImpl implements CouponCodeService{
	protected final Log log = LogFactory.getLog(CouponCodeServiceImpl.class.getSimpleName());
	
	@Override
	public Map<String, Object> updateConfirmOrderInfo(String userid, String coupon, String couponflag, String buysids,String addrid) {
		Map<String, Object> res = new HashMap<String, Object>();
		String url = Constants.BASE_TRADE_URL + "Trade/updateConfirmOrderInfo/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);// 用户ID
		map.put("coupon", coupon);
		map.put("couponflag", couponflag);
		map.put("buysids", buysids);
		map.put("addrid", addrid);
		String channelNo=null;
		if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.CHANNEL_PARAM_NAME) != null) {
			channelNo = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.CHANNEL_PARAM_NAME).getValue();
		} else {
			channelNo = StringUtils.isEmpty(channelNo) ? Constants.SP_WAP_DEFAULT_CHANNELNO : channelNo;
		}
		try {
			String data =  WebUtil.readContentFromGet(url, map);
			JSONObject content = JSONObject.fromObject(data);
			final String code = content.getString("code");
			// 记录是否更新支付方式成功
		
			if ("0".equals(code)) {
				JSONObject contentObj = content.getJSONObject("content");
				JSONObject couponcodeObj=contentObj.getJSONObject("couponcode");
				res.put("msgcode", "success");
				res.put("discountamount", contentObj.getString("discountamount"));
				res.put("payamount", contentObj.getString("payamount"));
				res.put("carriage", contentObj.getString("carriage"));
				if (null != couponcodeObj && !"".equals(couponcodeObj)&&!"1".equals(couponflag)) {
					res.put("couponcode", couponcodeObj.getString("couponcode"));
					}
				// 记录访问日志
				FileUtil.addLog(SysContent.getRequest(), "/orderaction!updateConfirmOrderInfo", channelNo, "userid", userid, "coupon", coupon, "buysids", buysids, "success", "1");
			} else {
				
				res.put("msgcode", "fail");
				
				res.put("msg", content.getString("msg"));
				FileUtil.addLog(SysContent.getRequest(), "/orderaction!updateConfirmOrderInfo", channelNo,  "userid", userid, "coupon", coupon, "buysids", buysids, "success", "0");
				log.warn("updateConfirmOrderInfo failed userid = " + userid + " (" + code + ")(" + content.getString("msg") + ")");
			}
			System.out.println("orderaction code : " + code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String str =
		// "{\"code\":\"0\",\"msg\":\"\",\"content\":{\"list\":[{\"orderid\":\"2013031908439\",\"date\":\"2013/3/19 11:06:56\",\"amount\":\"8999\",\"status\":\"0\",\"statusdesc\":\"已取消\",\"cancancel\":\"0\",\"canpay\":\"0\",\"canconfirm\":\"0\",\"cod\":\"1\",\"codmsg\":\"\",\"detail\":{\"list\":[{\"sku\":\"03219928001\",\"brand\":\"PRADA\",\"productname\":\"专利SAFFIANO斜纹牛皮手拎斜挎两用包\",\"cate\":\"\",\"firstpropname\":\"颜色\",\"firstpropvalue\":\"黑色\",\"secondpropname\":\"\",\"secondpropvalue\":\"\",\"count\":\"1\",\"amount\":\"8999\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/12/27/20121227173137502376-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"sadfdsf\",\"province\":\"上海\",\"city\":\"上海\",\"area\":\"普陀区\",\"addr\":\"司法所飞洒\",\"postcode\":\"111111\",\"tel\":\"13401000111\"},\"express\":\"工作日收货\",\"paymode\":\"无线支付宝\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013020708066\",\"date\":\"2013/2/7 17:56:45\",\"amount\":\"9799\",\"status\":\"0\",\"statusdesc\":\"已取消\",\"cancancel\":\"0\",\"canpay\":\"0\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"03219928001\",\"brand\":\"PRADA\",\"productname\":\"专利SAFFIANO斜纹牛皮手拎斜挎两用包\",\"cate\":\"\",\"firstpropname\":\"颜色\",\"firstpropvalue\":\"黑色\",\"secondpropname\":\"\",\"secondpropvalue\":\"\",\"count\":\"1\",\"amount\":\"8999\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/12/27/20121227173137502376-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"0537890001\",\"brand\":\"FOLLI FOLLIE\",\"productname\":\"手镯\",\"cate\":\"\",\"firstpropname\":\"主石/配石\",\"firstpropvalue\":\"银\",\"secondpropname\":\"珠宝尺码\",\"secondpropvalue\":\"无\",\"count\":\"1\",\"amount\":\"800\",\"pic\":\"http://pic02.shangpincdn.com/f/p/11/05/12/20110512181238000082-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"斯蒂芬\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"撒旦法撒法是否萨芬萨芬\",\"postcode\":\"122222\",\"tel\":\"13333333323\"},\"express\":\"工作日收货\",\"paymode\":\"无线支付宝\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013013007868\",\"date\":\"2013/1/30 14:05:07\",\"amount\":\"599\",\"status\":\"10\",\"statusdesc\":\"等待客服确认\",\"cancancel\":\"1\",\"canpay\":\"1\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236077003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"棕色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"L(国际码 L)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209094317305357-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"sadfdsf\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"司法所飞洒\",\"postcode\":\"111111\",\"tel\":\"13401000111\"},\"express\":\"工作日收货\",\"paymode\":\"货到付款\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012907778\",\"date\":\"2013/1/29 15:48:49\",\"amount\":\"599\",\"status\":\"0\",\"statusdesc\":\"已取消\",\"cancancel\":\"0\",\"canpay\":\"0\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236077003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"棕色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"L(国际码 L)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209094317305357-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"sadfdsf\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"司法所飞洒\",\"postcode\":\"111111\",\"tel\":\"13401000111\"},\"express\":\"工作日收货\",\"paymode\":\"无线支付宝\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012907776\",\"date\":\"2013/1/29 15:45:49\",\"amount\":\"599\",\"status\":\"10\",\"statusdesc\":\"等待客服确认\",\"cancancel\":\"1\",\"canpay\":\"1\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"待客服确认状态订单不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236077003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"棕色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"L(国际码 L)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209094317305357-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"roonery123\",\"province\":\"山西\",\"city\":\"大同市\",\"area\":\"大同县\",\"addr\":\"郎家园6号-Vintage郎园2号楼A座5层\",\"postcode\":\"100009\",\"tel\":\"12313123121\"},\"express\":\"所有日期均可收货\",\"paymode\":\"货到付款\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012807688\",\"date\":\"2013/1/28 16:51:07\",\"amount\":\"599\",\"status\":\"10\",\"statusdesc\":\"等待客服确认\",\"cancancel\":\"1\",\"canpay\":\"1\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236079002\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"红色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"M(国际码 M)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103405570341-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"sadfdsf\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"司法所飞洒\",\"postcode\":\"111111\",\"tel\":\"13401000111\"},\"express\":\"工作日收货\",\"paymode\":\"货到付款\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012807687\",\"date\":\"2013/1/28 16:40:57\",\"amount\":\"599\",\"status\":\"10\",\"statusdesc\":\"等待客服确认\",\"cancancel\":\"1\",\"canpay\":\"1\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236077003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"棕色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"L(国际码 L)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209094317305357-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"sadfdsf\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"司法所飞洒\",\"postcode\":\"111111\",\"tel\":\"13401000111\"},\"express\":\"工作日收货\",\"paymode\":\"货到付款\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012807682\",\"date\":\"2013/1/28 16:17:39\",\"amount\":\"2596\",\"status\":\"0\",\"statusdesc\":\"已取消\",\"cancancel\":\"0\",\"canpay\":\"0\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236077003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"棕色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"L(国际码 L)\",\"count\":\"2\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209094317305357-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236079002\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"红色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"M(国际码 M)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103405570341-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236081003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士加绒拉链帽衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"米色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"XL(国际码 XL)\",\"count\":\"1\",\"amount\":\"799\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103303648562-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"sadfdsf\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"司法所飞洒\",\"postcode\":\"111111\",\"tel\":\"13401000111\"},\"express\":\"工作日收货\",\"paymode\":\"无线支付宝\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012307515\",\"date\":\"2013/1/23 20:49:35\",\"amount\":\"6191\",\"status\":\"0\",\"statusdesc\":\"已取消\",\"cancancel\":\"0\",\"canpay\":\"0\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236079002\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"红色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"M(国际码 M)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103405570341-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236079005\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"红色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"XXL(国际码 XXL)\",\"count\":\"3\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103405570341-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236081002\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士加绒拉链帽衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"米色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"L(国际码 L)\",\"count\":\"3\",\"amount\":\"799\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103303648562-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236081003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士加绒拉链帽衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"米色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"XL(国际码 XL)\",\"count\":\"1\",\"amount\":\"799\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103303648562-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236085002\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"白色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"M(国际码 M)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209102917867993-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"斯蒂芬\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"撒旦法撒法是否萨芬萨芬\",\"postcode\":\"122222\",\"tel\":\"13333333323\"},\"express\":\"工作日收货\",\"paymode\":\"无线支付宝\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012307508\",\"date\":\"2013/1/23 20:19:46\",\"amount\":\"2895\",\"status\":\"0\",\"statusdesc\":\"已取消\",\"cancancel\":\"0\",\"canpay\":\"0\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236079004\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"红色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"XL(国际码 XL)\",\"count\":\"4\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103405570341-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236093001\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士短袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"红色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"M(国际码 M)\",\"count\":\"1\",\"amount\":\"499\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/16/20120216163851488917-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"斯蒂芬\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"撒旦法撒法是否萨芬萨芬\",\"postcode\":\"122222\",\"tel\":\"13333333323\"},\"express\":\"工作日收货\",\"paymode\":\"无线支付宝\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"}]}}";
		// res = new JSONObject().fromObject(str);
		return res;
	}

	}