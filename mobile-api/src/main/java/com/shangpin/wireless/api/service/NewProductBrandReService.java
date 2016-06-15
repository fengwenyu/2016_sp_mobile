package com.shangpin.wireless.api.service;






import com.shangpin.wireless.api.domain.NewProductBrand;



/**
 * 首页新品和品牌推荐
 * 
 * @Author liling
 * @CreatDate 2013-9-2
 */
public interface NewProductBrandReService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.NewProductBrandReServiceImpl";
	/**
	 * 通过categoryId获得品类下推荐位的详细信息
	 * 
	 * @param categoryId品类id
	 * @createDate 2013-09-5
	 */
	public NewProductBrand findByCategoryID(String categoryID,String dbType);


}
