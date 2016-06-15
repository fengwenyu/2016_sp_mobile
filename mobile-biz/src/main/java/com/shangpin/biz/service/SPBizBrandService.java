package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.base.vo.SearchResult;
import com.shangpin.biz.bo.Brands;
import com.shangpin.biz.bo.CustomBrandItem;
import com.shangpin.biz.bo.NewGoods;
import com.shangpin.biz.bo.Picture;
import com.shangpin.biz.bo.SearchProductResult;
import com.shangpin.biz.bo.SearchConditions;
import com.shangpin.biz.bo.SearchProduct;
import com.shangpin.biz.bo.SearchType;
import com.shangpin.core.entity.HotBrands;

/**
 * @ClassName: BrandService
 * @Description:品牌接口方法
 * @author qinyingchun
 * @date 2014年10月24日
 * @version 1.0
 */
public interface SPBizBrandService {

	/**
	 * 
	 * @Title: brandList
	 * @Description:尚品M站获取品牌列表
	 * @param @return
	 * @return List<BrandCapitalList>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年10月24日
	 */
	public Brands brandList();

	/**
	 * 
	 * @Title: hotBrands
	 * @Description: M站获取热门品牌列表
	 * @param @return
	 * @return List<HotBrands>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年10月25日
	 */
	public List<HotBrands> hotBrands();

	/**
	 * 
	 * @Title: initProduct
	 * @Description:品牌商品列表页初始化商品
	 * @param @param start
	 * @param @param end
	 * @param @param brandNo
	 * @param @return
	 * @return List<Product>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年10月27日
	 */
	public SearchProduct initProduct(String start, String end, String brandNo);

	public SearchProduct searchProduct(SearchConditions searchConditions);

	/**
	 * 
	 * @Title: getPicURL
	 * @Description:获取图片url
	 * @param @param picNo
	 * @param @param type
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年10月28日
	 */
	public List<Picture> getPicURL(String picNo, String width, String height, String type);

	/**
	 * 
	 * @Title: brandProducts
	 * @Description:商品品牌商品类表搜索
	 * @param
	 * @return SearchResult
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月17日
	 */
	public SearchResult brandProducts(String navId, String pageIndex, String pageSize, String tagId, String brandId,
			String price, String colorId, String size, String categoyId, String order, String userLv, String postArea);

	/**
	 * 
	 * @Title: brandProductList
	 * @Description:商品品牌商品类表搜索
	 * @param
	 * @return SearchBrandResult
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月23日
	 */
	public SearchProductResult brandProductList(String navId, String start, String productListEnd, String brandNo,
			String price, String color, String size, String categoryNo, String order, String userLv,
			SearchType searchType, String postArea);

	/**
	 * 根据品牌编号得到热门品牌
	 * 
	 * @param brandNo
	 * @return
	 * @author zghw
	 */
	public HotBrands findHotBrand(String brandNo);
	/**
	 * 用户推荐品牌
	 * 
	 * @param userId
	 * @param vuId app传imei、pc传quark_uv
	 * @return
	 * @author liling
	 */
	public CustomBrandItem queryCustomBrand(String userId, String vuId, String num);
	/**
	 * 获得新品到货集合
	 * 
	 * @param userId
	 * @param type
	 * @return
	 * @author liling
	 * @throws Exception
	 */
	public List<NewGoods> getNewGoods(String userId) throws Exception;
}
