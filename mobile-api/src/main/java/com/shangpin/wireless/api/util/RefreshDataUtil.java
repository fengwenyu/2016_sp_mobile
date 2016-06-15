package com.shangpin.wireless.api.util;



/**
 * 半个小时更新一次数据辅助类
 * @author Administrator
 *
 */
public class RefreshDataUtil {

	
	
	
	/**
	 * 半个小时更新一次
	 */
	public static void refreshDataPerHalfHour(){
		HotBrandsUtil.refreshBrandsList();
		CategoryCasheUtil.updateCache();
		ProductCouponUtil.loadProductOfCoupon();
	}
}
