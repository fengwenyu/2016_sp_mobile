package com.shangpin.mobileAolai.common.util;





/**
 * 数据容器池，初始化数据容器。
 * 
 * @author yumeng
 * @date: 2011-12-5
 */
public class DataContainerPool {

	/** 支付、配送方式容器 */
	public static final DataContainer sysContainer = new DataContainer(
			"syscontainer");
	/** 活动容器，保存topicconfig.properties配置文件中的数据，如：bazaar、ok */
	public static final DataContainer topicConfigContainer = new DataContainer("topicconfigcontainer");
	/** 渠道号包括的支付方式*/
	public static final DataContainer paymentConfigContainer = new DataContainer("paymentconfigcontainer");
	/**不使用优惠劵的商品*/
    public static final DataContainer productCouponContainer = new DataContainer("productCouponContainer");

}
