/**
 * 
 */
package com.shangpin.wechat.constants.enums;

/**
 * @author ZRS
 *
 */
public enum TemplateMsgEnum {
	
	PAY_OK("订单支付成功 ","OCKYrIXu2foeZzV2OOYcIFMJdFE9Kln484PcXCvxAno", "您好，您的订单已支付成功。", "恭喜您，获得{money}元优惠券，点击详情查收。"),

	RECEIVE_RED_ENVELOPE("领取红包通知 ","6HM3qld5TDoFp_QTCYHycj293IHCvw0xiX5qWUE4xoM","恭喜您获得{title}红包。","有效期：{expiry}，红包使用条件：{limit}。"),
	
	ORDER_DELIVER("订单发货通知","86-iH1IxH13Q2qFBkdhEcjx5CWI_BWJI2uy3Yo7RVN4","您的订单已发货。","请根据物流单号查询配送进度。"),
	
	SIGN_FOR_EXPRESS("快递签收通知","U7zyn7rjWpSMuzPPwMqDszeASnMMMtWyrFHm_lLON-A","您的包裹已签收。","如果不是本人取走，请及时联系物流公司进行查询。"),
	
	REFUND_NOTICE("退款通知","w6M2E33j4M36ECphZ1TLV8d4Xl0gR6Cx2hjTHXO4bq0","您的{title}订单款项已退。","了解详情，请点击查看。"),
	
	COUPON_EXPIRY_REMIND("优惠券到期提醒","MEvWeA-P3lXUYGETp0EysB_BPx1sqVVivbxgIz_Hjn4","您的优惠券马上到期了，请在有效期内尽快使用。","查看优惠券"),
	
	COUPON_GET_SUCCESS("优惠券领取成功通知","uSGzKa4__1iyQ65KR3N8QkeqC8rQB0ugVVPXjI_Vfjc","恭喜您成功领取了优惠券。","查看优惠券"),
	
	EXPIRY_REMIND("到期提醒通知","4siTZzNkaF8ad4289RWSOB04odp8sGQihypgFykl0JE","您的优惠券马上到期了，请在有效期内尽快使用。","查看优惠券"),
	
	SUBMIT_ORDER("订单提交成功","xdxm1hgfZ2K3DbFMG4XZTPDM1YF8EWDWKUoJyYYK-aM","您的礼品卡订单已经提交成功，请在30分钟内完成付款。","如有问题请致电400-900-900，小尚将第一时间为您服务！"),

	;

	private final String title;
	private final String id;
	private final String first;
	private final String remark;
	
	private TemplateMsgEnum(String title, String id, String first, String remark){
		this.title = title;
		this.id = id;
		this.first = first;
		this.remark = remark;
	}

	public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
	}

	public String getFirst() {
		return first;
	}

	public String getRemark() {
		return remark;
	}

	
}
