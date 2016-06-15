package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.base.vo.SearchResult;
import com.shangpin.biz.bo.CategoryIndex;
import com.shangpin.biz.bo.CategoryItem;
import com.shangpin.biz.bo.CategoryOperationItem;
import com.shangpin.biz.bo.SearchConditions;

/** 
* @ClassName: SPBizCategoryService 
* @Description:品类接口 
* @author qinyingchun
* @date 2014年11月4日
* @version 1.0 
*/
public interface SPBizCategoryService {
	/***
	 * 
	* @Title: findCategoryProduct 
	* @Description:品类商品搜索
	* @param searchConditions
	* @param @return
	* @return SearchResult
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年11月4日
	 */
	public SearchResult findCategoryProduct(SearchConditions searchConditions);

	/***
	 * 
	 * @Title: findCategoryProduct 
	 * @Description:品类商品搜索
	 * @param searchConditions
	 * @param @return
	 * @return SearchResult
	 * @throws 
	 * @Create By qinyingchun
	 * @Create Date 2014年11月4日
	 */
	public com.shangpin.biz.bo.SearchResult searchCategoryProduct(SearchConditions searchConditions);
	/***
	 * 
	 * @Title: getManCateList
	 * @Description:品类首页男士品类
	 * @param gender
	 *            性别
	 * @param @return
	 * @return List<CategoryIndex>
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月18日
	 */
	public List<CategoryIndex> getManCateList(String gender,String userLv);

	/***
	 * 
	 * @Title: getWomanCateList
	 * @Description:品类首页女士品类
	 * @param gender
	 *            性别
	 * @param @return
	 * @return List<CategoryIndex>
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月18日
	 */
	public List<CategoryIndex> getWomanCateList(String gender,String userLv);
	/**
	 * 得到父品类下的品类列表
	 * @param categoryId 品类ID
	 * @param gender 性别
	 * @param userLv 用户级别
	 * @return
	 * @author zghw
	 */
	public com.shangpin.biz.bo.SearchResult getCategoryChildList(SearchConditions searchConditions);

	/**
	 * 获取品类运营位相关的一级品类
	 * 
	 * @param parentId
	 * @param type
	 * @return
	 * @author liling
	 */
	public CategoryItem queryCategory(String parentId, String type);

	/**
	 * 获取品类的一级品类相关运营位
	 * 
	 * @param id
	 * @param userId
	 * @return
	 * @author liling
	 */
	public CategoryOperationItem queryCategoryOperation(String id, String userId);
}
