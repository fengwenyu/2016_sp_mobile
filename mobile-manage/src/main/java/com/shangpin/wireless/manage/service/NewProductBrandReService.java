package com.shangpin.wireless.manage.service;

import java.util.List;

import com.shangpin.wireless.domain.NewProductBrand;


/**
 * 首页新品和品牌推荐
 * 
 * @Author liling
 * @CreatDate 2013-9-2
 */
public interface NewProductBrandReService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.NewProductBrandReServiceImpl";

	/**
	 * 添加或者更新推荐列表
	 * 
	 * @param newProductBrand
	 *            推荐新品和品牌
	 * @createDate 2013-09-5
	 */
	public void editAll(NewProductBrand newProductBrand) throws Exception;

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @CreatDate 2012-07-11
	 * @param sql
	 *            查询语句
	 * @param firstRow
	 *            起始位置
	 * @param maxRow
	 *            每页需要显示的条数
	 * @Return List
	 */
	public List executeSqlToMap(String queryListSql, int firstRow, int maxRow) throws Exception;

	/**
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param sql
	 *            查询语句
	 * @Return Long
	 */
	public Integer executeSqlCount(String sql) throws Exception;

	/**
	 * 通过categoryId获得品类下推荐位的详细信息
	 * 
	 * @param categoryId品类id
	 * @createDate 2013-09-5
	 */
	public NewProductBrand findByCategoryID(String categoryId) throws Exception;
}
