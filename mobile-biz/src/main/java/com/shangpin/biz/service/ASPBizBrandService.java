package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.Brand;
import com.shangpin.biz.bo.BrandLists;
import com.shangpin.biz.bo.CustomBrand;

/**
 * 客户端品牌相关接口
 * 
 * @author huangxiaoliang
 *
 */
public interface ASPBizBrandService {

	/**
	 * 获得热门品牌、大牌的map
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<BrandLists> doBrand(String pageIndex, String pageSize) throws Exception;

	/**
	 * 用于展示用户自己的定制的品牌
	 * 
	 * @param userId
	 * @param vuId
	 *            app传imei、pc传quark_uv
	 * @param num
	 *            品牌数量
	 * @return
	 * @throws Exception
	 */
	List<CustomBrand> doFavBrands(String userId, String vuId, String num) throws Exception;

	/**
	 * 显示用户自己关注的以及品牌池的品牌
	 * 
	 * @param userId
	 * @param vuid
	 *            app传imei、pc传quark_uv
	 * @return
	 */
	String doCustomBrand(String userId, String vuid, String num) throws Exception;

	/**
	 * 用于获取用户保存自己定制的品牌是否成功接口
	 * 
	 * @param brandId
	 *            多个品牌按“,”分割
	 * @param userId
	 * @param vuId
	 * @return
	 */
	String doConserveBrand(String brandId, String userId, String vuId);
	
	/**
	 * 新品到货
	 * 
	 * @param userId
	 * @return
	 */
	String doNewGoods(String userId) throws Exception;
	
	/**
	 * 首页新品到货
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<Brand> doFirstNewGoods(String userId) throws Exception;
	
	/**
	 * 2.8.2首页新品到货
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Brand> doHeadNewGoods() throws Exception;

}
