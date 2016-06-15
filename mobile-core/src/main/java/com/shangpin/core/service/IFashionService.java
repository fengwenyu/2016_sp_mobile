package com.shangpin.core.service;

import java.util.Date;
import java.util.List;

import com.shangpin.core.entity.WxFashionInformation;

public interface IFashionService {
	/**
	 * 根据时间得到正在进行显示潮流信息
	 * 
	 * @param date
	 * @return List<WxFashionInformation>
	 * @author liling
	 */
	public List<WxFashionInformation> findFashionList(Date date);

	/**
	 * 潮流信息内容
	 * 
	 * @param date
	 * @return WxFashionInformation
	 * @author liling
	 */
	public WxFashionInformation findByFashionId(Long id);

}
