package com.shangpin.biz.service.abstraction;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.AdvertNewTitle;
import com.shangpin.biz.bo.AdvertTitle;
import com.shangpin.biz.bo.EntranceTitle;
import com.shangpin.biz.bo.FashionTitle;
import com.shangpin.biz.bo.GalleryList;
import com.shangpin.biz.bo.ModelTitle;
import com.shangpin.biz.bo.NewGoodsTitle;
import com.shangpin.biz.bo.ReleasesSPActivity;
import com.shangpin.biz.bo.WorthTitle;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JSONUtils;

public abstract class AbstractBizCommonService {

	public static Logger logger = LoggerFactory.getLogger(AbstractBizCommonService.class);

	@Autowired
	ShangPinService shangPinService;

	/** 轮播图 */
	public GalleryList queryGallery(String type, String frames) {
		GalleryList gallery = null;
		try {
			String json = shangPinService.queryGalleryList(type, frames);
			if (StringUtils.isNotEmpty(json)) {
				ResultObjOne<GalleryList> obj = JSONUtils.toGenericsCollection(json, ResultObjOne.class, GalleryList.class);
				if (obj.getObj() != null) {
					gallery = obj.getObj();
				}

			}
		} catch (Exception e) {
			logger.error("轮播图调用base接口返回数据发生错误！" + e);
			e.printStackTrace();
		}
//		if (null == gallery) {
//			gallery = new GalleryList();
//		}
		return gallery;
	}

	/** 获取首页广告对象 */
	public AdvertNewTitle doBaseAdvertNewTitle() throws Exception {
		String baseData = shangPinService.getHeadAdverts();
		AdvertNewTitle advert = null;
		if (StringUtils.isNotEmpty(baseData)) {
			ResultObjOne<AdvertNewTitle> obj = JSONUtils.toGenericsCollection(baseData, ResultObjOne.class, AdvertNewTitle.class);
			if (Constants.SUCCESS.equals(obj.getCode())) {
				advert = obj.getObj();
			}
		}
//		if (null == advert) {
//			advert = new AdvertNewTitle();
//		}

		return advert;
	}

	/** 运营广告 */
	public AdvertTitle doBaseAdvertTitle(String type, String pageIndex, String pageSize) throws Exception {
		String baseData = shangPinService.getOperation(type, pageIndex, pageSize);
		AdvertTitle advertTitle = null;
		if (StringUtils.isNotEmpty(baseData)) {
			ResultObjOne<AdvertTitle> obj = JSONUtils.toGenericsCollection(baseData, ResultObjOne.class, AdvertTitle.class);
			advertTitle = obj.getObj();
		}

//		if (null == advertTitle) {
//			advertTitle = new AdvertTitle();
//		}
		return advertTitle;
	}

	/** 功能入口 */
	public EntranceTitle doBaseEntranceTitle(String type, String pageIndex, String pageSize) throws Exception {
		String baseData = shangPinService.findEntranceIndex(type, pageIndex, pageSize);
		EntranceTitle entranceTitle = null;
		if (StringUtils.isNotEmpty(baseData)) {
			ResultObjOne<EntranceTitle> obj = JSONUtils.toGenericsCollection(baseData, ResultObjOne.class, EntranceTitle.class);
			entranceTitle = obj.getObj();
		}
//		if (null == entranceTitle) {
//			entranceTitle = new EntranceTitle();
//		}
		return entranceTitle;
	}

	/** 值得买 */
	public WorthTitle doBaseWorthTitle(String type, String userId, String pageIndex, String pageSize) throws Exception {
		String baseData = shangPinService.findPopularityAndWorth(type, userId, pageIndex, pageSize);
		WorthTitle worthTitle = null;
		if (StringUtils.isNotEmpty(baseData)) {
			ResultObjOne<WorthTitle> obj = JSONUtils.toGenericsCollection(baseData, ResultObjOne.class, WorthTitle.class);
			if (obj != null) {
				worthTitle = obj.getObj();
			}
		}
//		if (null == worthTitle) {
//			worthTitle = new WorthTitle();
//		}
		return worthTitle;
	}

	/** 主题 */
	public ReleasesSPActivity doBaseReleaseTitle(String pageIndex, String pageSize, String origin) throws Exception {
		String baseData = shangPinService.queryNewReleases(pageIndex, pageSize, origin);
		ReleasesSPActivity releaseTitle = null;
		if (StringUtils.isNotEmpty(baseData)) {
			ResultObjOne<ReleasesSPActivity> obj = JSONUtils.toGenericsCollection(baseData, ResultObjOne.class, ReleasesSPActivity.class);
			if (obj != null) {
				releaseTitle = obj.getObj();
			}
		}
//		if (null == releaseTitle) {
//			releaseTitle = new ReleasesSPActivity();
//		}
		return releaseTitle; 
	}

	/** 潮流 */
	public FashionTitle doBaseFashionTitle(String type, String pageIndex, String pageSize) throws Exception {
		String baseData = shangPinService.findFashionList(type, pageIndex, pageSize);
		FashionTitle fashionTitle = null;
		if (StringUtils.isNotEmpty(baseData)) {
			ResultObjOne<FashionTitle> obj = JSONUtils.toGenericsCollection(baseData, ResultObjOne.class, FashionTitle.class);
			if (obj != null) {
				fashionTitle = obj.getContent();
			}
		}
//		if (null == fashionTitle) {
//			fashionTitle = new FashionTitle();
//		}
		return fashionTitle;
	}

	/** 首页新品 */
	public NewGoodsTitle doBaseNewGoodsTitle() throws Exception {
		String baseData = shangPinService.findHeadNewGoods();
		NewGoodsTitle newGoodsTitle = null;
		if (StringUtils.isNotEmpty(baseData)) {
			ResultObjOne<NewGoodsTitle> obj = JSONUtils.toGenericsCollection(baseData, ResultObjOne.class, NewGoodsTitle.class);
			if (obj != null) {
				newGoodsTitle = obj.getContent();
			}
		}
//		if (null == newGoodsTitle) {
//			newGoodsTitle = new NewGoodsTitle();
//		}
		return newGoodsTitle;
	}
	
	/** 首页模版 */
	public ModelTitle doBaseModelTitle() throws Exception {
		String baseData = shangPinService.findModelInfo("", "1");
		ModelTitle modelTitle = null;
		if (StringUtils.isNotEmpty(baseData)) {
			ResultObjOne<ModelTitle> obj = JSONUtils.toGenericsCollection(baseData, ResultObjOne.class, ModelTitle.class);
			if (obj != null) {
				modelTitle = obj.getContent();
			}
		}
		return modelTitle;
	}

}
