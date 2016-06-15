package com.shangpin.base.service;

public interface PointBuyService {

	/**
	 * 获取当天的时间轴
	 * @return
	 */
	public String pointBuyTimesList();
	/**
	 * 根据某个整点，获取整点下的抢购列表
	 * @param pharseId
	 * @return
	 */
	public String showProductList(String pharseId);
}
