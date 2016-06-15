package com.shangpin.biz.service.abstraction;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ResultObjOne;
import com.shangpin.biz.bo.Brand;
import com.shangpin.biz.bo.BrandLists;
import com.shangpin.biz.bo.CustomBrand;
import com.shangpin.biz.bo.CustomBrandItem;
import com.shangpin.biz.bo.DynamicObj;
import com.shangpin.biz.bo.NewGoods;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.PicCdnHash;
import com.shangpin.utils.JSONUtils;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizBrandService {
	public static Logger logger = LoggerFactory.getLogger(AbstractBizBrandService.class);
	@Autowired
	ShangPinService shangPinService;

	/**
	 * 获得主站新品到货
	 * 
	 * @param userId
	 * @return
	 */
	protected String findBaseNewGoods(String userId) {
		try {
			return shangPinService.findNewGoods(userId);
		} catch (Exception e) {
			logger.error("find the base 'findNewGoods' interface error !" + e);
		}
		return null;
	}
	
	protected String findBaseFirstNewGoods(String userId) {
		try {
			return shangPinService.findFirstNewGoods(userId);
		} catch (Exception e) {
			logger.error("find the base 'findFirstNewGoods' interface error !" + e);
		}
		return null;
	}
	
	protected String findBaseHeadNewGoods() {
		try {
			return shangPinService.findHeadNewGoods();
		} catch (Exception e) {
			logger.error("find the base 'findHeadNewGoods' interface error !" + e);
		}
		return null;
	}

	protected List<BrandLists> doBase(String pageIndex, String pageSize) throws Exception {
		String baseData = shangPinService.queryBrandList(pageIndex, pageSize);
		List<BrandLists> lists = new ArrayList<BrandLists>();
		if (StringUtils.isNotEmpty(baseData)) {
			ResultObjMapList<BrandLists> obj = JSONUtils.toGenericsCollection(baseData, ResultObjMapList.class, BrandLists.class);
			lists = obj.getList("brandList");
		}
		return lists;
	}

	protected List<CustomBrand> doBaseFavBrands(String userId, String vuId, String num) throws Exception {
		String baseData = shangPinService.getRecBrand(userId, vuId, num);
		return ruleBrand(baseData);
	}

	protected CustomBrandItem doBaseFavBrandList(String userId, String vuId, String num) throws Exception {
		String baseData = shangPinService.getFavBrandList(userId, vuId, num);
		return ruleBrandRool(baseData);
	}

	protected String doBaseCollectFavBrand(String brandId, String userId, String vuId) {
		return shangPinService.getCollectFavBrand(brandId, userId, vuId);
	}

	/**
	 * 处理用户关注的品牌
	 * 
	 * @param baseData
	 * @return
	 * @throws Exception
	 */
	private List<CustomBrand> ruleBrand(String baseData) throws Exception {
		if (StringUtils.isEmpty(baseData)) {
			return null;
		}

		ResultObjMapList<CustomBrand> obj = JSONUtils.toGenericsCollection(baseData, ResultObjMapList.class, CustomBrand.class);
		List<CustomBrand> favBrands = obj.getList("favList");
		List<CustomBrand> lists = new ArrayList<CustomBrand>();
		if (favBrands != null && !favBrands.isEmpty()) {
			for (CustomBrand cb : favBrands) {
				cb.setIsFlagship("0");
				cb.setNameCN(cb.getBrandCnName());
				cb.setNameEN(cb.getBrandEnName());
				cb.setType("3");
				cb.setName(cb.getBrandEnName());
				cb.setPic(PicCdnHash.getPicUrl(cb.getBrandLogo()));
				cb.setRefContent(cb.getBrandNo());
				lists.add(cb);
			}
		}
		return lists;
	}

	/**
	 * 处理用户关注的品牌、及品牌池
	 * 
	 * @param baseData
	 * @return
	 * @throws Exception
	 */
	private CustomBrandItem ruleBrandRool(String baseData) throws Exception {
		if (StringUtils.isEmpty(baseData)) {
			return null;
		}

		ResultObjMapList<CustomBrand> obj = JSONUtils.toGenericsCollection(baseData, ResultObjMapList.class, CustomBrand.class);
		List<CustomBrand> favBrands = obj.getList("favList");
		CustomBrandItem cbi = new CustomBrandItem();
		List<CustomBrand> fbs = new ArrayList<CustomBrand>();
		if (favBrands != null && !favBrands.isEmpty()) {
			for (CustomBrand fb : favBrands) {
				fb.setIsFlagship("0");
				fb.setNameCN(fb.getBrandCnName());
				fb.setNameEN(fb.getBrandEnName());
				fb.setType("3");
				fb.setName(fb.getBrandEnName());
				fb.setPic(PicCdnHash.getPicUrl(fb.getBrandLogo()));
				fb.setRefContent(fb.getBrandNo());
				fbs.add(fb);
			}
		}
		cbi.setFavList(fbs);
		List<CustomBrand> roolBrands = obj.getList("list");
		List<CustomBrand> rbs = new ArrayList<CustomBrand>();
		if (roolBrands != null && !roolBrands.isEmpty()) {
			for (CustomBrand rb : roolBrands) {
				rb.setIsFlagship("0");
				rb.setNameCN(rb.getBrandCnName());
				rb.setNameEN(rb.getBrandEnName());
				rb.setType("3");
				rb.setName(rb.getBrandEnName());
				rb.setPic(PicCdnHash.getPicUrl(rb.getBrandLogo()));
				rb.setRefContent(rb.getBrandNo());
				rbs.add(rb);
			}
		}
		cbi.setList(rbs);
		return cbi;
	}
	/**
	 * 用于展示用户自己的定制的品牌
	 * 
	 * @param userId
	 * @param vuId
	 *            app传imei、pc传quark_uv
	 * @param num
	 *            品牌数量
	 * @return
	 * @author liling
	 */
	public CustomBrandItem fromQueryCustomBrand(String userId, String vuId, String num) {
		try {
			String json = shangPinService.getRecBrand(userId, vuId, num);
			logger.debug("调用base接口返回数据:" + json);
			if (!StringUtils.isEmpty(json)) {
				ResultObjOne<CustomBrandItem> obj = JSONUtils.toGenericsCollection(json, ResultObjOne.class, CustomBrandItem.class);
				String code = obj.getCode();
				if (!Constants.SUCCESS.equals(code) || null == obj) {
					return null;
				}
				CustomBrandItem customBrandItem =obj.getObj();
				List<CustomBrand> favList=customBrandItem.getFavList();
				if(favList!=null){
					customBrandItem.setFavList(ruleBrand(json));
				}
				return customBrandItem;
			}
		} catch (Exception e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 获得新品到货集合
	 * 
	 * @param userId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	protected List<NewGoods> doBizNewGoods(String userId) throws Exception {
		String baseData = findBaseNewGoods(userId);
		List<NewGoods> lists = new ArrayList<NewGoods>();
		if (StringUtils.isNotEmpty(baseData)) {
			ResultObjMapList<NewGoods> obj = JSONUtils.toGenericsCollection(baseData, ResultObjMapList.class, NewGoods.class);
			lists = obj.getList("list");
		}
		return lists;
	}
	
	protected List<Brand> doBizFirstNewGoods(String userId) throws Exception {
		String baseData = findBaseFirstNewGoods(userId);
		List<Brand> lists = new ArrayList<Brand>();
		if (StringUtils.isNotEmpty(baseData)) {
			ResultObjMapList<Brand> obj = JSONUtils.toGenericsCollection(baseData, ResultObjMapList.class, Brand.class);
			lists = obj.getList("list");
		}
		return lists;
	}
	
	protected List<Brand> doBizHeadNewGoods() throws Exception {
		String json = findBaseHeadNewGoods();
		List<Brand> lists = new ArrayList<Brand>();
		if (StringUtils.isNotEmpty(json)) {
		    ResultObjOne<DynamicObj<Brand>> resultObjMapList = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<DynamicObj<Brand>>>(){});
			lists = resultObjMapList.getContent().getList();
		}
		return lists;
	}
}
