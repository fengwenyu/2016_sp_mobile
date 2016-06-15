package com.shangpin.biz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.Brand;
import com.shangpin.biz.bo.BrandLists;
import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.bo.CustomBrand;
import com.shangpin.biz.bo.CustomBrandItem;
import com.shangpin.biz.service.ASPBizBrandService;
import com.shangpin.biz.service.abstraction.AbstractBizBrandService;
import com.shangpin.biz.utils.ApiBizData;

@Service
public class ASPBizBrandServiceImpl extends AbstractBizBrandService implements ASPBizBrandService {

	public List<BrandLists> doBrand(String pageIndex, String pageSize) throws Exception {
		return doBase(pageIndex, pageSize);
	}

	@Override
	public String doCustomBrand(String userId, String vuid, String num) throws Exception {
		CustomBrandItem cb = doBaseFavBrandList(userId, vuid, num);
		if (cb == null) {
			return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
		} else {
			return ApiBizData.spliceData(cb, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
		}
	}

	@Override
	public List<CustomBrand> doFavBrands(String userId, String vuId, String num) throws Exception {
		return doBaseFavBrands(userId, vuId, num);
	}

	@Override
	public String doConserveBrand(String brandId, String userId, String vuId) {
		return doBaseCollectFavBrand(brandId, userId, vuId);
	}

	@Override
	public String doNewGoods(String userId) throws Exception {
		String baseData = findBaseNewGoods(userId);
		if (baseData == null) {
			return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
		} else {
			return baseData;
		}
	}

	@Override
	public List<Brand> doFirstNewGoods(String userId) throws Exception {
		return doBizFirstNewGoods(userId);
	}
	
	@Override
	public List<Brand> doHeadNewGoods() throws Exception {
	    return doBizHeadNewGoods();
	}
}
