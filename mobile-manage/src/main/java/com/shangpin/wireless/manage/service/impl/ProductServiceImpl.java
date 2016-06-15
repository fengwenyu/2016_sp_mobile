package com.shangpin.wireless.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.ProductDao;
import com.shangpin.wireless.domain.Product;
import com.shangpin.wireless.manage.service.ProductService;
import com.shangpin.wireless.util.HqlHelper;

/**
 * 产品管理
 * 
 * @Author zhouyu
 * @CreatDate 2012-08-03
 */
@Service(ProductService.SERVICE_NAME)
public class ProductServiceImpl implements ProductService {
	@Resource(name = ProductDao.DAO_NAME)
	private ProductDao productDao;

	/**
	 * 根据组合条件获取实体，如果条件为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-17
	 * @param hqlHelper
	 * @Return Product
	 */
	public Product getByCondition(HqlHelper hqlHelper) throws Exception {
		return productDao.getByCondition(hqlHelper);
	}

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void save(Product entity) throws Exception {
		productDao.save(entity);
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
	public List executeSqlToMap(String sql, int firstRow, int maxRow) throws Exception {
		return productDao.executeSqlToMap(sql, firstRow, maxRow);
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
	public Integer executeSqlCount(String sql) throws Exception {
		return productDao.executeSqlCount(sql);
	}

	/**
	 * 根据id获取实体，如果id为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param id
	 * @Return Product
	 */
	public Product getById(Long id) throws Exception {
		return productDao.getById(id);
	}

	/**
	 * 删除实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param id
	 * @Return
	 */
	public void delete(Long id) throws Exception {
		productDao.delete(id);
	}

	/**
	 * 更新实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void update(Product entity) throws Exception {
		productDao.update(entity);
	}

	/**
	 * 获取所有实体集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param
	 * @Return List
	 */
	public List<Product> findAll() throws Exception {
		return productDao.findAll();
	}
}
