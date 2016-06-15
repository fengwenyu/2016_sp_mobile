package com.shangpin.biz.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.AppIndexFirst;
import com.shangpin.biz.bo.BrandLists;
import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.bo.CommonRules;
import com.shangpin.biz.bo.HomeSecond;
import com.shangpin.biz.bo.MallCategory;
import com.shangpin.biz.bo.Recommend;
import com.shangpin.biz.service.ASPBizBrandService;
import com.shangpin.biz.service.ASPBizCategoryService;
import com.shangpin.biz.service.ASPBizEntranceService;
import com.shangpin.biz.service.ASPBizFashionService;
import com.shangpin.biz.service.ASPBizGalleryService;
import com.shangpin.biz.service.ASPBizIndexInfoService;
import com.shangpin.biz.service.ASPBizIndexService;
import com.shangpin.biz.service.ASPBizOperationService;
import com.shangpin.biz.service.ASPBizRecommendService;
import com.shangpin.biz.service.ASPBizReleasesService;
import com.shangpin.biz.service.ASPBizSaleService;
import com.shangpin.biz.service.ASPBizStyleThemeService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.StringUtil;

@Service
public class ASPBizIndexServiceImpl implements ASPBizIndexService {

	private static final Logger logger = LoggerFactory.getLogger(ASPBizIndexServiceImpl.class);

	@Autowired
	ASPBizSaleService aspBizSaleService;
	@Autowired
	ASPBizReleasesService aspBizReleasesService;
	@Autowired
	ASPBizStyleThemeService aspBizStyleThemeService;
	@Autowired
	ASPBizGalleryService aspBizGalleryService;

	@Autowired
	ASPBizCategoryService aspBizCategoryService;

	@Autowired
	ASPBizBrandService aspBizBrandService;

	@Autowired
	ASPBizRecommendService aspBizRecommendService;
	@Autowired
	ASPBizEntranceService aspBizEntranceService;

	@Autowired
	ASPBizFashionService aspBizFashionService;
	
	@Autowired
	ASPBizOperationService aspBizOperationService;
	@Autowired
	ASPBizIndexInfoService aspBizIndexInfoService;

	@Override
	public String queryAppIndexFirst(HttpServletRequest request) throws Exception {
		AppIndexFirst appIndexFirst = new AppIndexFirst();
		appIndexFirst.setSysTime(String.valueOf(System.currentTimeMillis()));

		final String imei = request.getHeader("imei");
		final String p = request.getHeader("p");
		final String version = request.getHeader("ver");
		final String userId = request.getHeader("userid");

		if ("102".equals(p) || "2".equals(p)) {
			if (com.shangpin.utils.StringUtil.compareVersion("2.8.5", "2.9.0", version) == 0) {
				CommonRules moreFashion = getFashion();
				appIndexFirst.setGallery(aspBizGalleryService.queryGalleryList("3", "6"));
				appIndexFirst.setAdvert(aspBizOperationService.doOperation("2","1", "3"));
				appIndexFirst.setAdvertNew(aspBizIndexInfoService.doBaseAdvert());
				appIndexFirst.setEntrance(aspBizEntranceService.queryEntranceList("2", "1", "10"));
				appIndexFirst.setReleases(aspBizReleasesService.queryNewReleases("1", "3", ""));
				appIndexFirst.setWorth(aspBizRecommendService.doRecProduct("2", userId, "1", "6"));
				appIndexFirst.setFashion(aspBizFashionService.doFashion("0", "1", "12"));
				appIndexFirst.setMoreFashion(moreFashion);
				appIndexFirst.setNewGoods(aspBizBrandService.doHeadNewGoods());
				appIndexFirst.setOperation(aspBizOperationService.doOperation("1","1", "1"));
			} else if (("2".equals(p) && com.shangpin.utils.StringUtil.compareVersion("2.8.0", "2.8.5", version) == 0) || ("102".equals(p) && com.shangpin.utils.StringUtil.compareVersion("2.8.2", "2.8.5", version) == 0)) {
				CommonRules moreFashion = getFashion();
				appIndexFirst.setGallery(aspBizGalleryService.queryGalleryList("3", "6"));
				appIndexFirst.setAdvert(aspBizOperationService.doOperation("2","1", "3"));
				appIndexFirst.setEntrance(aspBizEntranceService.queryEntranceList("2", "1", "10"));
				appIndexFirst.setReleases(aspBizReleasesService.queryNewReleases("1", "3", ""));
				appIndexFirst.setFashion(aspBizFashionService.doFashion("0", "1", "12"));
				appIndexFirst.setMoreFashion(moreFashion);
				appIndexFirst.setNewGoods(aspBizBrandService.doHeadNewGoods());
				appIndexFirst.setWorth(aspBizRecommendService.doRecProduct("2", userId, "1", "6"));
				appIndexFirst.setOperation(aspBizOperationService.doOperation("1","1", "1"));
			} else if (com.shangpin.utils.StringUtil.compareVersion("2.6.5", "2.7.0", version) == 0) {
				appIndexFirst.setEntrance(aspBizEntranceService.queryEntranceList("2", "1", "10"));
				appIndexFirst.setGallery(aspBizGalleryService.queryGalleryList("3", "6"));
				appIndexFirst.setReleases(aspBizReleasesService.queryNewReleases("1", "3", ""));
				appIndexFirst.setThemes(aspBizStyleThemeService.queryStyleTheme("1", "3"));
				appIndexFirst.setSale(aspBizSaleService.queryHomeSale());
			} else if (com.shangpin.utils.StringUtil.compareVersion("2.7.0", "2.8.0", version) == 0) {
				appIndexFirst.setGallery(aspBizGalleryService.queryGalleryList("3", "6"));
				appIndexFirst.setEntrance(aspBizEntranceService.queryEntranceList("2", "1", "10"));
				appIndexFirst.setReleases(aspBizReleasesService.queryNewReleases("1", "3", ""));
				appIndexFirst.setFashion(aspBizFashionService.doFashion("0", "1", "12"));
				appIndexFirst.setBrand(aspBizBrandService.doFavBrands(userId, imei, "8"));
			} else if ("102".equals(p) && com.shangpin.utils.StringUtil.compareVersion("2.8.0", "2.8.2", version) == 0) {
				CommonRules moreFashion = getFashion();
				appIndexFirst.setGallery(aspBizGalleryService.queryGalleryList("3", "6"));
				appIndexFirst.setEntrance(aspBizEntranceService.queryEntranceList("2", "1", "10"));
				appIndexFirst.setSale(aspBizSaleService.getHotSale("4", "1", "6"));
				appIndexFirst.setFashion(aspBizFashionService.doFashion("0", "1", "12"));
				appIndexFirst.setMoreFashion(moreFashion);
				appIndexFirst.setNewGoods(aspBizBrandService.doFirstNewGoods(userId));
				appIndexFirst.setWorth(aspBizRecommendService.doRecProduct("2", userId, "1", "6"));
				appIndexFirst.setOperation(aspBizOperationService.doOperation("1","1", "1"));
			} else {
				appIndexFirst.setEntrance(aspBizEntranceService.queryEntranceList("2", "1", "8"));
				appIndexFirst.setGallery(aspBizGalleryService.queryGalleryList("3", "3"));
				appIndexFirst.setReleases(aspBizReleasesService.queryNewReleases("1", "3", ""));
				appIndexFirst.setThemes(aspBizStyleThemeService.queryStyleTheme("1", "3"));
				appIndexFirst.setSale(aspBizSaleService.queryHomeSale());
			}

		}

		if (appIndexFirst != null) {
			String json;
			try {
				json = ApiBizData.spliceData(appIndexFirst, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
				logger.debug("调用api接口返回数据:" + json);
				return json;
			} catch (Exception e) {
				logger.error("调用api接口返回数据发生错误！");
				e.printStackTrace();
			}

		}
		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}

	private CommonRules getFashion() {
		CommonRules moreFashion = new CommonRules();
		moreFashion.setName("潮流资讯");
		moreFashion.setType("5");
		moreFashion.setRefContent(Constants.FASHION_WAP);
		return moreFashion;
	}

	@Override
	public String getHomeSecond(String userId) throws Exception {
		String data = null;
		HomeSecond hs = new HomeSecond();
		MallCategory category = aspBizCategoryService.doCategory();
		List<Recommend> recommends = aspBizRecommendService.doRecommendList(userId);
		List<BrandLists> brands = aspBizBrandService.doBrand("1", "20");
		hs.setProductList(recommends);
		hs.setCategory(category);
		hs.setBrandList(brands);
		if (brands.isEmpty() && null == category && recommends.isEmpty()) {
			data = ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
		} else {
			data = ApiBizData.spliceData(hs, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
		}
		return data;
	}

}
