package com.shangpin.mobileShangpin.platform.service;

import java.util.List;
import java.util.Map;

import com.shangpin.mobileShangpin.platform.vo.SPMerchandiseVO;

/**
 * 尚品分类和分类下的商品业务逻辑接口，用于分类和分类下的商品相关操作
 * 
 * @author liling
 * @date:2013-07-29
 */
public interface CategoryProductService {
	/**
	 * 尚品分类商品列表
	 * 
	 * @param userid
	 *            用户id
	 * @param categoryid
	 *            分类id
	 * @param gender
	 *            性别 0女1男
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页数据量
	 * @Return List<SPMerchandiseVO> 尚品list传输对象
	 */
	public List<SPMerchandiseVO> getNewProducts(String userid, String categoryid, String gender, Integer pageIndex, Integer pageSize);
	/**
	 * 尚品分类列表
	 * 
	 * @param categoryid
	 *            分类id
	 * @param gender
	 *            性别 0女1男
	 * @Return  Map<String, Object> 
	 */
	public Map<String, Object> getChildCategoryMap(String categoryid, String gender);
	
}
