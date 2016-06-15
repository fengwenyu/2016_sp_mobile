package com.shangpin.wireless.manage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.PageViewDao;
import com.shangpin.wireless.domain.PageView;
import com.shangpin.wireless.manage.service.PageViewService;

/**
 * PV统计
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
@Service(PageViewService.SERVICE_NAME)
public class PageViewServiceImpl implements PageViewService {
	@Resource(name = PageViewDao.DAO_NAME)
	private PageViewDao pageViewDao;

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	public void save(PageView entity) throws Exception {
		pageViewDao.save(entity);
	}
}
