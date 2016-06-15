package com.shangpin.wireless.util;

import com.shangpin.wireless.util.DataContainer;

/**
 * 数据容器池，初始化数据容器。
 * 
 * @author yumeng
 */
public class DataContainerPool {

	/** ajax登录成功后将user暂时保存到此容器中，手机验证后将user保存到session中，并将此user从容器中删除 */
	public static final DataContainer userInfoContainer = new DataContainer("userinfocontainer");
	/** 手机验证码容器，key=用户名，value=手机验证码 */
	public static final DataContainer verifycodeContainer = new DataContainer("verifycodeContainer");
	/** 存放push信息 */
	public static final DataContainer pushManageContainer = new DataContainer("pushManageContainer");
}
