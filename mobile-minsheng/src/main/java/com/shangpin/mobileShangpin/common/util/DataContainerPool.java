package com.shangpin.mobileShangpin.common.util;

/**
 * 数据容器池，初始化数据容器。
 * 
 * @author yumeng
 * @date: 2011-12-5
 */
public class DataContainerPool {
	/** 支付、配送方式容器 */
	public static final DataContainer sysContainer = new DataContainer("syscontainer");
	/** 尚品活动容器，保存topicconfig.properties配置文件中的数据，如：bazaar、ok */
	public static final DataContainer topicConfigContainer = new DataContainer("topicconfigcontainer");
	/** 尚品活动容器首页*/
	public static final DataContainer activityConfigContainer = new DataContainer("activityconfigcontainer");
	/** 尚品品牌容器首页*/
	public static final DataContainer brandConfigContainer = new DataContainer("brandConfigContainer");
	/** 渠道号包括的支付方式*/
	public static final DataContainer paymentConfigContainer = new DataContainer("paymentconfigcontainer");
}
