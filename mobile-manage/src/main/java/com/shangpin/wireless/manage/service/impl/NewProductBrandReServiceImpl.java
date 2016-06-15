package com.shangpin.wireless.manage.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.shangpin.wireless.util.StringUtils;

import com.shangpin.wireless.dao.NewProductBrandDao;
import com.shangpin.wireless.domain.NewProductBrand;
import com.shangpin.wireless.manage.service.NewProductBrandReService;

/**
 * 首页新品和品牌推荐 * @Author liling
 * 
 * @CreatDate 2013-9-2
 */
@Service(NewProductBrandReService.SERVICE_NAME)
public class NewProductBrandReServiceImpl implements NewProductBrandReService {
	protected final Log log = LogFactory.getLog(NewProductBrandReServiceImpl.class);
	@Resource(name = NewProductBrandDao.DAO_NAME)
	private NewProductBrandDao newProductBrandDao;

	/**
	 * 添加或者更新推荐列表
	 * 
	 * @param newProductBrand
	 *            推荐新品和品牌
	 * @createDate 2013-09-5
	 */
	@Override
	public void editAll(NewProductBrand newProductBrand) throws Exception {
		if (newProductBrand.getCategoryId() != null) {
			if (StringUtils.isNotEmpty(newProductBrand.getFirstNewProductId())) {
				newProductBrand.setFirstNewProductId(newProductBrand.getFirstNewProductId().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getSecondNewProductId())) {
				newProductBrand.setSecondNewProductId(newProductBrand.getSecondNewProductId().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getFirstBrandId())) {
				newProductBrand.setFirstBrandId(newProductBrand.getFirstBrandId().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getFirstBrandName())) {
				newProductBrand.setFirstBrandName(newProductBrand.getFirstBrandName().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getFirstBrandProductId())) {
				newProductBrand.setFirstBrandProductId(newProductBrand.getFirstBrandProductId().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getSecondBrandId())) {
				newProductBrand.setSecondBrandId(newProductBrand.getSecondBrandId().trim().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getSecondBrandName())) {
				newProductBrand.setSecondBrandName(newProductBrand.getSecondBrandName().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getThreeBrandId())) {
				newProductBrand.setThreeBrandId(newProductBrand.getThreeBrandId().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getThreeBrandName())) {
				newProductBrand.setThreeBrandName(newProductBrand.getThreeBrandName().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getFourthBrandId())) {
				newProductBrand.setFourthBrandId(newProductBrand.getFourthBrandId().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getFourthBrandName())) {
				newProductBrand.setFourthBrandName(newProductBrand.getFourthBrandName().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getFifthBrandId())) {
				newProductBrand.setFifthBrandId(newProductBrand.getFifthBrandId().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getFifthBrandName())) {
				newProductBrand.setFifthBrandName(newProductBrand.getFifthBrandName().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getSixthBrandId())) {
				newProductBrand.setSixthBrandId(newProductBrand.getSixthBrandId().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getSixthBrandName())) {
				newProductBrand.setSixthBrandName(newProductBrand.getSixthBrandName().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getSeventhBrandId())) {
				newProductBrand.setSeventhBrandId(newProductBrand.getSeventhBrandId().trim());
			}
			if (StringUtils.isNotEmpty(newProductBrand.getSeventhBrandName())) {
				newProductBrand.setSeventhBrandName(newProductBrand.getSeventhBrandName().trim());
			}
			newProductBrand.setCreateTime(new Date());
			if (newProductBrand.getCategoryId() != null) {
				newProductBrandDao.update(newProductBrand);
			} else {
				newProductBrandDao.save(newProductBrand);
			}
//			newProductBrandDao.saveOrUpdate(newProductBrand);
		}
	}

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param sql
	 *            查询语句
	 * @param firstRow
	 *            起始位置
	 * @param maxRow
	 *            每页需要显示的条数
	 * @Return List
	 */
	@Override
	public List executeSqlToMap(String queryListSql, int firstRow, int maxRow) throws Exception {
		return newProductBrandDao.executeSqlToMap(queryListSql, firstRow, maxRow);
	}

	/**
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param sql
	 *            查询语句
	 * @Return Long
	 */
	@Override
	public Integer executeSqlCount(String sql) throws Exception {
		return newProductBrandDao.executeSqlCount(sql);
	}

	
	/**
	 * 通过categoryId获得品类下推荐位的详细信息
	 * 
	 * @param categoryId品类id
	 * @createDate 2013-09-5
	 */
	@Override
	public NewProductBrand findByCategoryID(String categoryId) throws Exception {
		return newProductBrandDao.getById(categoryId);
	}
}
