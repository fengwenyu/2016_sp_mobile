package com.shangpin.wireless.manage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.AoLaiWapPageViewDao;
import com.shangpin.wireless.domain.AoLaiWapPageView;
import com.shangpin.wireless.manage.service.AoLaiWapPageViewService;

/**
 * PV统计
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
@Service(AoLaiWapPageViewService.SERVICE_NAME)
public class AoLaiWapPageViewServiceImpl implements AoLaiWapPageViewService {
	@Resource(name = AoLaiWapPageViewDao.DAO_NAME)
	private AoLaiWapPageViewDao aoLaiWapPageViewDao;

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	public void save(AoLaiWapPageView entity) throws Exception {
		aoLaiWapPageViewDao.save(entity);
	}
}
