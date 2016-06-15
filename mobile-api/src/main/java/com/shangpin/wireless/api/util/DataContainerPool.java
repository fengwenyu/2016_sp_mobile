package com.shangpin.wireless.api.util;

/**
 * 数据容器池，初始化数据容器。
 * 
 * @author yumeng
 */
public class DataContainerPool {
	
	/** 存放push信息 */
	public static final DataContainer pushManageContainer = new DataContainer("pushManageContainer");
	/** 存放品类首页推荐数据 */
	public static final DataContainer categoryIndexContainer = new DataContainer("categoryIndexContainer");
	/** 存放app下载链接 */
	public static final DataContainer appAddressContainer = new DataContainer("appAddressContainer");
	/** 存放文件类型 */
	public static final DataContainer fileTypeContainer = new DataContainer("fileTypeContainer");
	/**发现活动和商品关系*/
	public static final DataContainer discoverContainer = new DataContainer("discoverContainer");
	/** 奥莱专题编号*/
	public static final DataContainer topicNosContainer = new DataContainer("topicNos");
	/** 存放更多品牌容器，如：服饰、鞋靴、箱包、配饰（男、女）等 */
	public static final DataContainer brandContainer = new DataContainer("brandcontainer");
}
