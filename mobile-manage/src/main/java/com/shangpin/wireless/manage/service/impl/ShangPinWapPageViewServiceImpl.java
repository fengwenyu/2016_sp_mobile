package com.shangpin.wireless.manage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.ShangPinWapPageViewDao;
import com.shangpin.wireless.domain.ShangPinWapPageView;
import com.shangpin.wireless.manage.service.ShangPinWapPageViewService;

/**
 * PV统计
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
@Service(ShangPinWapPageViewService.SERVICE_NAME)
public class ShangPinWapPageViewServiceImpl implements ShangPinWapPageViewService {
	@Resource(name = ShangPinWapPageViewDao.DAO_NAME)
	private ShangPinWapPageViewDao shangPinWapPageViewDao;

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	public void save(ShangPinWapPageView entity) throws Exception {
		shangPinWapPageViewDao.save(entity);
	}
}
