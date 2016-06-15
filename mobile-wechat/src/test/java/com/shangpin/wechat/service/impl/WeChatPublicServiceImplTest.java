package com.shangpin.wechat.service.impl;

import com.shangpin.wechat.bo.base.*;
import com.shangpin.wechat.service.WeChatPublicService;
import org.junit.Test;
import org.springframework.util.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeChatPublicServiceImplTest{
	
	@Test
	public void testSendMsg(){
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String access_token = service.getToken();
		String msg = "{\"touser\":\"oFHXijvkFXv7ypscJ-jl3rP3O256\",\"msgtype\":\"text\",\"text\":{\"content\":\"Hello World\"}}";
		String result = service.sendMsg(access_token, msg);
		Assert.notNull(result);
		System.out.println(result);
	}

	@Test
	public void testGetToken() {
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String result = service.getToken();
		Assert.notNull(result);
		System.out.println(result);
	}

	@Test
	public void testGetAccessToken() {
//		WeChatPublicService service= new WeChatPublicServiceImpl();
//		String result = service.getAccessToken(service.getToken());
//		Assert.assertNotNull(result);
//		System.out.println(result);
	}

	@Test
	public void testCreateCard() {
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String accessToken = service.getToken();
//		String postJson = "{\"card\":{\"card_type\":\"GROUPON\",\"groupon\":{\"base_info\":{\"logo_url\":\"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0\",\"brand_name\":\"海底捞\",\"code_type\":\"CODE_TYPE_TEXT\",\"title\":\"132元双人火锅套餐\",\"sub_title\":\"周末狂欢必备\",\"color\":\"Color010\",\"notice\":\"使用时向服务员出示此券\",\"service_phone\":\"020-88888888\",\"description\":\"不可与其他优惠同享\\n如需团购券发票，请在消费时向商户提出\\n店内均可使用，仅限堂食\","
//				+ "\"date_info\":{\"type\":\"DATE_TYPE_FIX_TIME_RANGE\",\"begin_timestamp\":"+System.currentTimeMillis()/1000+",\"end_timestamp\":"+(System.currentTimeMillis()/1000 +24*60*60)+"},\"sku\":{\"quantity\":500},\"get_limit\":3,\"use_custom_code\":false,\"bind_openid\":false,\"can_share\":true,\"can_give_friend\":true,\"location_id_list\":[123,12321,345345],\"custom_url_name\":\"立即使用\",\"custom_url\":\"http://www.qq.com\",\"custom_url_sub_title\":\"6个汉字tips\",\"promotion_url_name\":\"更多优惠\",\"promotion_url\":\"http://www.qq.com\",\"source\":\"大众点评\"},\"deal_detail\":\"以下锅底2选1（有菌王锅、麻辣锅、大骨锅、番茄锅、清补凉锅、酸菜鱼锅可选）：\\n大锅1份 12元\\n小锅2份 16元 \"}}}";
//		String postJson = "{\"card\":{\"card_type\":\"CASH\",\"cash\":{\"base_info\":{\"logo_url\":\"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0\",\"brand_name\":\"海底捞\",\"code_type\":\"CODE_TYPE_TEXT\",\"title\":\"132元双人火锅套餐\",\"sub_title\":\"周末狂欢必备\",\"color\":\"Color010\",\"notice\":\"使用时向服务员出示此券\",\"service_phone\":\"020-88888888\",\"description\":\"不可与其他优惠同享\\n如需团购券发票，请在消费时向商户提出\\n店内均可使用，仅限堂食\","
//				+ "\"date_info\":{\"type\":\"DATE_TYPE_FIX_TIME_RANGE\",\"begin_timestamp\":"+System.currentTimeMillis()/1000+",\"end_timestamp\":"+(System.currentTimeMillis()/1000 +24*60*60)+"},\"sku\":{\"quantity\":500},\"get_limit\":3,\"use_custom_code\":false,\"bind_openid\":false,\"can_share\":true,\"can_give_friend\":true,\"location_id_list\":[123,12321,345345],\"custom_url_name\":\"立即使用\",\"custom_url\":\"http://www.qq.com\",\"custom_url_sub_title\":\"6个汉字tips\",\"promotion_url_name\":\"更多优惠\",\"promotion_url\":\"http://www.qq.com\",\"source\":\"大众点评\"},\"least_cost\":10000,\"reduce_cost\":10000}}}";
//		String postJson = "{\"card\":{\"card_type\":\"GROUPON\",\"groupon\":{\"base_info\":{\"logo_url\":\"http://mmbiz.qpic.cn/mmbiz/uSsXTbwzqVffNkozSBFsmz7BgcWByiblgUKkYNgcvwgemYf24Xsn7QUyDCsLquyhZfqRZa1YsjZ0ZIiaHZ2TgYag/0\",\"code_type\":\"CODE_TYPE_BARCODE\",\"brand_name\":\"尚品\",\"title\":\"尚品团\",\"sub_title\":\"尚品券\",\"color\":\"Color100\",\"notice\":\"有效期内\",\"description\":\"有效\",\"sku\":{\"quantity\":\"100\"},\"date_info\":{\"type\":\"DATE_TYPE_FIX_TERM\",\"fixed_term\":\"10\",\"fixed_begin_term\":\"0\"},\"use_custom_code\":\"false\",\"bind_openid\":\"false\",\"get_limit\":\"3\",\"can_share\":\"true\",\"can_give_friend\":\"true\"},\"deal_detail\":\"9.9活动\"}}}";
		String postJson = "{\"card\":{\"card_type\":\"GROUPON\",\"groupon\":{\"base_info\":{\"logo_url\":\"http://mmbiz.qpic.cn/mmbiz/uSsXTbwzqVffNkozSBFsmz7BgcWByiblgUKkYNgcvwgemYf24Xsn7QUyDCsLquyhZfqRZa1YsjZ0ZIiaHZ2TgYag/0\",\"code_type\":\"CODE_TYPE_TEXT\",\"brand_name\":\"尚品\",\"title\":\"尚品团\",\"sub_title\":\"尚品券\",\"color\":\"Color010\",\"notice\":\"有效期内\",\"description\":\"有效\",\"sku\":{\"quantity\":\"1000\"},\"date_info\":{\"type\":\"DATE_TYPE_FIX_TERM\",\"fixed_term\":\"10\",\"fixed_begin_term\":\"0\"},\"use_custom_code\":false,\"bind_openid\":false,\"get_limit\":\"5\",\"can_share\":true,\"can_give_friend\":true},\"deal_detail\":\"9.9活动\"}}}";
				
		String result = service.createCard(accessToken, postJson);
		System.out.println(result);
		Assert.notNull(result);
	}

	@Test
	public void testQueryCardCode() {
		WeChatPublicService service= new WeChatPublicServiceImpl();
//		http://localhost:8080/mobile-apiv2/wechat/consume.action?accessToken=%22k8XAahvq7MPxciBdZwgb2S141Ta6nxNm8QaNDWCoghC4yqqSXFMkmfDY027p7IsKGxS5CEzYA_r__Dx6dITbXEaQZpOA9OlgwmSkStGeVWY%22&code=%22%22
		String accessToken = service.getToken();
//		String code ="826475038113";
		String code ="128019710528";
		String cardId = null;
		QueryCardCodeResult queryCardCodeResult = service.queryCardCode(accessToken, code, cardId);
		System.out.println(queryCardCodeResult.toString());
		Assert.notNull(queryCardCodeResult);
	}

	@SuppressWarnings("unused")
    @Test
	public void testConsumeCardCodeStringString() {
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String accessToken = service.getToken();
	}

	@Test
	public void testConsumeCardCodeStringStringString() {
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String accessToken = service.getToken();
		String code = "826475038113";
		String cardId = null;
		Map result =  service.consumeCardCode(accessToken, code, cardId);
		System.out.println(result.toString());
		Assert.notNull(result);
	}
	
	@Test
	public void testUploadLogo() {
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String accessToken = service.getToken();
		File logo = new File("D:\\132.jpg");
		System.out.println(logo.length());
		String logoUrl = service.uploadLogo(accessToken, logo);
		System.out.println(logoUrl);
		Assert.notNull(logoUrl);
	}

	@Test
	public void testQueryPoiList() {
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String accessToken = service.getToken();		
		String result = service.queryPoiListWithJson(accessToken, 1, 10);
		System.out.println(result);
		Assert.notNull(result);
	}
	
	@Test
	public void testQueryUserCardStringString() {
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String accessToken = service.getToken();	
		String openid = "46516adfadf";
		QueryUserCardResult result = service.queryUserCard(accessToken, openid );
		System.out.println(result);
		Assert.notNull(result);
	}

	@Test
	public void testQueryUserCardStringStringString() {

	}

	@Test
	public void testQueryBatchCardStringIntInt() {
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String accessToken = service.getToken();	
	
		QueryBatchCardResult result = service.queryBatchCard(accessToken, 0, 10);
		System.out.println(result);
		Assert.notNull(result);
	}

	@Test
	public void testQueryBatchCardStringIntIntStringArray() {
		
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String accessToken = service.getToken();	
		List<String> arr = new ArrayList<String>();
//		arr.add("CARD_STATUS_VERIFY_FAIL");
		
		//接口问题:貌似这种状态没有起作用		
		arr.add("CARD_STATUS_USER_DISPATCH");
	
		QueryBatchCardResult result = service.queryBatchCard(accessToken, 0, 10, arr);
		System.out.println(result);
		Assert.notNull(result);
	}
	
    @Test
	public void testQueryCardDetailByCardIdStringString(){
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String accessToken = service.getToken();	
		
//		String cardId = "pFHXijl_P_sdK48JeYwl3jXK64oA";
		String cardId = "pFHXijgOl0jTWY6SEMrjARy4mN3s";
//		String cardId = "pFHXijl_P_sdK48JeYwl3jXK64oA";
//		String cardId = "pFHXijl_P_sdK48JeYwl3jXK64oA";
	
		QueryCardDetailResult result = service.queryCardDetailByCardId(accessToken, cardId);
		System.out.println(result);
		Assert.notNull(result);
	}
	
	@Test
	public void testSendTemplateMsg(){
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String access_token = service.getToken();
//		String tmp_id = "qYf8-4dsRobeUtUpvm5t0xb9yX-HmiyFIFKulgzMbz8";
		String tmp_id = "qYf8-4dsRobeUtUpvm5t0xb9yX-HmiyFIFKulgzMbz8";//购买成功
//		String tmp_id = "X7fpxxfDUUl3k_BFhUNOU_ZesMDgEF724uO4PEugxm8";//提交成功
		String msg = "{\"touser\":\"oFHXijnTyBFsnD4FXBxabUlSPG1Y\",\"template_id\":\""+tmp_id+"\",\"url\":\"http://weixin.qq.com/download\",\"data\":{\"first\":{\"value\":\"恭喜你购买成功！\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"巧克力\",\"color\":\"#173177\"},\"keyword2\":{\"value\":\"39.8元\",\"color\":\"#173177\"},\"keyword3\":{\"value\":\"2014年9月22日\",\"color\":\"#173177\"},\"remark\":{\"value\":\"欢迎再次购买！\",\"color\":\"#173177\"}}}";
		TemplateMsgResult result = service.sendTemplateMsg(access_token, msg);
		Assert.notNull(result);
		System.out.println(result);
	}
	
	/**
	 * Test method for {@link com.shangpin.wechat.service.impl.WeChatPublicServiceImpl#sendTemplateMsg4PayOk(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSendTemplateMsg4PayOk() {
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String access_token = service.getToken();
		System.out.println(access_token);
		String toUser = "oFHXijnTyBFsnD4FXBxabUlSPG1Y";
		TemplateMsgResult result = service.sendTemplateMsg4PayOk(null,toUser, "1", "2", "3", "2500", "5");
		Assert.notNull(result);
		System.out.println(result);
		
	}
	@Test
	public void sendTemplateMsg4SubmitOrder() {
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String access_token = service.getToken();
		System.out.println(access_token);
		String toUser = "oFHXijnTyBFsnD4FXBxabUlSPG1Y";
		TemplateMsgResult result = service.sendTemplateMsg4SubmitOrder(toUser, "1", "2", "3", "4", "5:","6","7");
		Assert.notNull(result);
		System.out.println(result);
		
	}
	@Test
	public void sendTemplateMsgStringStringMAP() {
		WeChatPublicService service= new WeChatPublicServiceImpl();
		String access_token = service.getToken();
		System.out.println(access_token);
		String toUser = "oFHXijnTyBFsnD4FXBxabUlSPG1Y";
		
		Map<String,String> map=new HashMap<>();
		map.put("first", "欢迎");
		map.put("remark", "结束");
		map.put("refund", "退款");
		map.put("reason", "原因");
		
		TemplateMsgResult result = service.sendTemplateMsg(toUser, 
				"w6M2E33j4M36ECphZ1TLV8d4Xl0gR6Cx2hjTHXO4bq0", 
				"https://www.baidu.com", 
				map);
		Assert.notNull(result);
		System.out.println(result);
		
	}

@Test
public void test(){
//	WeChatMerchantServiceImpl wm = new WeChatMerchantServiceImpl();
//
//	String re_openid="oFHXijnTyBFsnD4FXBxabUlSPG1Y";
//	String total_amount="20000";
//	String total_num="1";
//	String wishing="奖励红包";
//	String client_ip="192.168.3.64";
//	String act_name="111";
//	String remark ="222";
//	
//	
//	Object s =  wm.cashBonus(re_openid, total_amount, total_num, wishing, client_ip, act_name, remark);
//	
//	System.out.println(s);
	
}

@Test
public void testcashCoupon(){
	
	WeChatMerchantServiceImpl wm = new WeChatMerchantServiceImpl();

	String openid="oFHXijnTyBFsnD4FXBxabUlSPG1Y";
//	String openid="oFHXijlAkVduH9MGKV8r7kRZJNNM";
//	String openid="oFHXijsSnol93uI6ch_2nyhW0Aoc";
//	String coupon_stock_id="232153";
//	String coupon_stock_id="232557";
//	String coupon_stock_id="232566";
//	String coupon_stock_id="229744";
//	String coupon_stock_id="241054";
	String coupon_stock_id="238667";
	
	Object s =  wm.cashCoupon(coupon_stock_id, openid,"打印日志的userId");
	System.out.println(s);
	
}


}
