package com.shangpin.mobileShangpin.platform.service;

import java.util.List;

import com.shangpin.mobileShangpin.platform.vo.CategoryVo;

/**
 * 二级分类业务逻辑接口，用于首页二级分类相关操作
 * 
 * @author wangfeng
 * @date:2013-7-29
 */
public interface SedCategoryService {

	public List<CategoryVo> getCategory(String firstCatId,String gender) throws Exception;
}
