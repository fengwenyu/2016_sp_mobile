package com.shangpin.wireless.api.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.shangpin.wireless.api.api2client.domain.HotBrandsListAPIResponse;
import com.shangpin.wireless.api.domain.HotBrands;
import com.shangpin.wireless.api.service.HotBrandsService;

/**
 * 热门品牌刷新工具类
 * @author Administrator
 *
 */
public class HotBrandsUtil {

	/**
	 * 刷新热门品牌
	 */
	public static void refreshBrandsList(){
		ApplicationContext ac = ApplicationContextUtil.get("ac");
		HotBrandsService brandsService = (HotBrandsService) ac.getBean(HotBrandsService.SERVICE_NAME);
		List<HotBrands> brandList;
		try {
			brandList = brandsService.findAll();
			HotBrandsListAPIResponse brandsListAPIResponse = new HotBrandsListAPIResponse();
			brandsListAPIResponse.setCode("0");
			if(brandList != null && brandList.size() > 0){
				brandsListAPIResponse.setBrandList(brandList);
			}
			String result = brandsListAPIResponse.obj2Json();
			DataContainerPool.brandContainer.putOrReplace("HOT_BRAND_LIST", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
