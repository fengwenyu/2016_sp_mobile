package com.shangpin.biz.service;
import com.shangpin.biz.bo.CategoryList;
import com.shangpin.biz.bo.GalleryList;
import com.shangpin.biz.bo.IndexHotBrands;
import com.shangpin.biz.bo.Promotions;

/** 
* @ClassName: BizIndexService 
* @Description:商城首页调用接口 
* @author 秦迎春
* @date 2014年10月22日 下午3:33:46 
* @version 1.0 
*/
public interface SPBizIndexService {
	
	/**
	 * 
	* @Title: galleryList 
	* @Description:商城首页轮播图方法
	* @param @param type
	* @param @return
	* @return List<Gallery>
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年10月22日
	 */
	public GalleryList galleryList(String type,String frames);
	
	/**
	 * 
	* @Title: categoryList 
	* @Description:商城首页类别列表
	* @param @return
	* @return List<Category>
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年10月22日
	 */
	public CategoryList categoryList();
	
	/**
	 * 
	* @Title: brandLists 
	* @Description:商城首页品牌列表
	* @param @return
	* @return List<BrandList>
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年10月23日
	 */
	public IndexHotBrands brandLists(String pageIndex, String pageSize);
	
	/**
	 * 
	* @Title: promotions 
	* @Description:商城首页推广位
	* @param @return
	* @return Promotions
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年10月23日
	 */
	public Promotions promotions();

}
