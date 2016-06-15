package com.shangpin.mobileShangpin.platform.service;

import java.util.List;

import com.shangpin.mobileShangpin.platform.vo.BrandVo;
import com.shangpin.mobileShangpin.platform.vo.SPBrandVO;
import com.shangpin.mobileShangpin.platform.vo.SPMerchandiseVO;

/**
 * 品牌逻辑接口，用于品牌和品牌下的商品相关操作
 * 
 * @author liling
 * @date:2013-07-29
 */
public interface BrandService {
	/**
	 * 尚品品牌列表
	 * 
	 * @param gender
	 *            性别 0女1男
	 * @Return List<SPBrandVO> 尚品品牌传输对象
	 */
	public List<SPBrandVO> getSPBrands(String gender);

	/**
	 * 尚品分类商品列表
	 * 
	 * @param userid
	 *            用户id
	 * @param brandid
	 *            品牌id
	 * @param gender
	 *            性别 0女1男
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页数据量
	 * @Return List<SPMerchandiseVO> 尚品list传输对象
	 */
	public List<SPMerchandiseVO> SPProductsBrand(String userid, String categoryid,String brandid, String gender, Integer pageIndex, Integer pageSize);
	public List<BrandVo> getBrand(String categoryId,String gender) throws Exception;
	public List<BrandVo> getBrand(String gender) throws Exception;
}
