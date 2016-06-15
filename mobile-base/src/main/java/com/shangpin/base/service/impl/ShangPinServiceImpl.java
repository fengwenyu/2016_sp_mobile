package com.shangpin.base.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.utils.BaseDataUtil;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.base.vo.ActivityOfMain;
import com.shangpin.base.vo.CapitalBrand;
import com.shangpin.base.vo.LatestProduct;
import com.shangpin.base.vo.LatestProductMore;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.base.vo.Look;
import com.shangpin.base.vo.LookForProduct;
import com.shangpin.base.vo.MallBrandList;
import com.shangpin.base.vo.MallCategory;
import com.shangpin.base.vo.Merchandise;
import com.shangpin.base.vo.Picture;
import com.shangpin.base.vo.Promotion;
import com.shangpin.base.vo.ResultObj;
import com.shangpin.base.vo.ResultObjList;
import com.shangpin.base.vo.ResultObjOne;
import com.shangpin.base.vo.SPMerchandise;
import com.shangpin.base.vo.Topic;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JsonUtil;

@SuppressWarnings("deprecation")
@Service
public class ShangPinServiceImpl extends BaseService implements ShangPinService {
	// 尚品专题商品列表请求URL
	private StringBuilder findSPTopicProductsURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("SPTopicProducts");

	// 尚品品类新品商品列表请求URL
	private StringBuilder findSPNewProductsURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("SPNewProducts");

	// 尚品获取品类品牌商品列表请求URL
	private StringBuilder findSPProductsAndBrandURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("SPProductsInCateAndBrand");

	// 尚品商品详情请求URL
	private StringBuilder findSPProductDetailURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("SPProductDetail");

	// 获取CMS尚品专题商品列表
	private StringBuilder findTopicProductsURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("SPNewTopicProducts");

	// 获取尚品男士9宫格活动
	private StringBuilder findMan9GridsURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("man9grids");

	// 批量获取图片链接
	private StringBuilder findPicUrlsURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("getPicUrlsByGet");

	// 根据开始结束时间获取尚品活动列表
	private StringBuilder findSubjectListURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("SubjectList");

	// 获取品牌旗舰店数据
	private StringBuilder findFlagShipURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("flagship");

	// 根据活动名称或者活动编号获取符合条件的尚品活动列表
	private StringBuilder findSearchSubjectURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("searchSubject");

	// 根据性别查询品牌
	private StringBuilder sPBrandsURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("SPBrands");

	// 新品首页列表
	private StringBuilder latestProductListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/latestProductList");

	// 新品更多列表
	private StringBuilder latestProductMoreListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/latestProductMoreList");

	// 新品更多列表筛选
	private StringBuilder latestProductSortListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/latestProductSortList");

	// 商城品类
	private StringBuilder mallCategoryURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("listingcatalog/queryMallCategory");
	// 商城推广位
	private StringBuilder promotionURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("listingcatalog/promotion");
	// 商城品牌
	private StringBuilder mallBrandURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Brand/mallBrandList");
	// 获取轮播图新品，商城
	private StringBuilder galleryURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("AlterPic/gallery");
	// 品牌列表
	private StringBuilder brandListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("brand/brandList");
	// 验证V码是否与活动匹配并且该V码是否和账户绑定
	private StringBuilder checkVcodeURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("VCode/checkVcode");
	// V码绑定到指定用户
	private StringBuilder bindVcodeURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("VCode/bindVcode");
	// 用户是否绑定活动V码接口
	private StringBuilder queryVcodeURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("VCode/queryVcode");
	// 获取look搭配
	private StringBuilder getLooksURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("style/getLooks");
	// 获取style搭配
	private StringBuilder getLookProductsURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("style/getLookProdects");

	// 收藏活动
	private StringBuilder collectActivityURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Subject/collectActivity");

	// 收藏活动列表
	private StringBuilder collectActivityListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Subject/collectedActivityList");

	// 订单物流跟踪
	private StringBuilder orderMoreLogisticURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("orderMoreLogistic");
	// 获取活动头部运营位
	private StringBuilder getTopAdverURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("ActivetyTopAdver");
	// 获取所有即将结束的活动URL
	private StringBuilder selectEndingSubjectListURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("SelectEndingSubjectList");
	// 奥莱按起止时间获取活动展示URL
	private StringBuilder subjectListURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("SubjectList");
	// 奥莱活动商品列表页URL
	private StringBuilder subjectProductListURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("subjectproductlist");

	// 商品品牌商品列表搜索
	private StringBuilder brandProductListURL = new StringBuilder(GlobalConstants.BASE_URL_SEARCH).append("BrandProductList");
	// 获取最新上架列表URL
	private StringBuilder newReleasesURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/newReleases");
	// 获取风格主题列表URL
	private StringBuilder styleThemeURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/styleTheme");
	// 获取首页今日特卖URL
	private StringBuilder homeSaleURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/homeSale");
	// 获取首页更多特卖URL
	private StringBuilder moreSaleURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/moreSale");
	// 获取人气排行榜和今日值得买URL
	private StringBuilder popularityAndWorthURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/popularityAndWorth");

	// 品牌、活动头部URL
	private StringBuilder headInfoURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/headInfo");
	// 品牌、活动优惠券URL
	private StringBuilder couponInfoURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/couponInfo");
	// 品牌、活动推广位URL
	private StringBuilder promotionInfoURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/promotionInfo");
	// 品牌广告位模板
	private StringBuilder modelInfoURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/model");
	// 商品获取模板信息URL
	private StringBuilder productTemplateURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Style/productTemplate");
	// 首页入口运营位URL
	private StringBuilder entranceIndexURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/entranceIndex");
	// 商品详情页接口URL
	private StringBuilder productDetailURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/productDetail");
	// 商品评论列表接口URL
	private StringBuilder commentListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Trade/commentList");

	// 尚潮流详情接口URL
	private StringBuilder fashionDetailURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/fashionDetail");
	// 尚潮流列表接口URL
	private StringBuilder fashionListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/fashionList");
	// 立即购买URL
	private StringBuilder buyNowURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/buynow");
	// 分类运营页面URL
	private StringBuilder queryCategoryOperationURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/categoryOperation");
	// 分类运营页面URL
		private StringBuilder queryCategoryOperationsURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/categoryOperations");
	// 商品尺码URL
	private StringBuilder productSizeURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("style/ProductSize");
	// 收藏商品URL
	private StringBuilder collectProductURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("AddProductToCollect");
	// 收藏商品列表
	private StringBuilder collectProductListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/collectedProductList");
	//收藏品牌列表
	private StringBuilder collectBrandListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/collectedBrandlist");
	// 取消收藏商品URL
	private StringBuilder cancleCollectProductURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("delCollect");
	/** 用户定制的品牌URL */
	private StringBuilder customBrandURL = new StringBuilder(GlobalConstants.BASE_URL_CUSTOM).append("FavBrands");
	/** 用户在品牌池选择自定制品牌的数据URL */
	private StringBuilder favBrandListURL = new StringBuilder(GlobalConstants.BASE_URL_CUSTOM).append("FavBrandList");
	/** 保存用户自己定制的品牌URL */
	private StringBuilder collectFavBrandURL = new StringBuilder(GlobalConstants.BASE_URL_CUSTOM).append("CollectFavBrand");
	/** 运营内容URL */
	private StringBuilder operationURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/operation");
	// 礼品卡商品列表
	private StringBuilder giftCardListURL = new StringBuilder(GlobalConstants.BASE_URL_SEARCH).append("GiftCardList");
	// 礼品卡购买
	private StringBuilder giftCardBuyURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/GiftCardBuy");
	// 礼品卡提交订单结算
	private StringBuilder giftCardCommitURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/GiftCardCommit");
	// 礼品卡充值
	private StringBuilder giftCardElectronicRechargeURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/giftCardElectronicRecharge");
	// 礼品卡记录列表
	private StringBuilder giftCardRecordListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/giftCardRecordList");
	// 礼品卡--获取电子卡充值密码
	private StringBuilder giftCardRechargePasswdURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/giftCardRechargePasswd");
	//用户使用礼品卡的状态
	private StringBuilder giftCardStatusURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("queryGiftCardStatus");
	//礼品卡实物卡充值
	private StringBuilder giftCardEntityRchargeURL=new StringBuilder(GlobalConstants.BASE_URL_AOLAI_AOLAI).append("cardRecharge");
	
	private StringBuilder uploadPicURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("SaveMobilePicNew");
	/** 新品到货URL */
	private StringBuilder newGoodsURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/newGoods");
	/** 首页新品到货URL */
	private StringBuilder firstNewGoodsURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/firstNewGoods");
	/** 2.8.2首页新品到货URL */
	private StringBuilder headNewGoodsURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/headNewGoods");
	//首页运营广告位地址
	private StringBuilder headAdvertsURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/headAdverts");
	// 商品详情页接口URL
    private StringBuilder productDetailNewURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/productDetailNew");
    /** 首页标签URL */
	private StringBuilder labelURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/revealLabel");
	//商品库存数
	private StringBuilder productCountURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/ProductStock");
	/** 运营内容URL */
	private StringBuilder shellWindowURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/shellWindow");

	/** 首页底部导航栏图标地址 */
	// 后台地址http://www20.tradeapiliantiao.com/ListingCatalog/bottomNavigate
	private StringBuilder bottomNavigateURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/bottomNavigate");

	// 礼品卡--获取电子卡充值密码
	private StringBuilder giftCardRechargePasswdByCardURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("giftCardRechargePasswdByCard");

	// 礼品卡--保存信息到数据库
	private StringBuilder saveGiftCardToDbURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("account/saveGiftCardToDb");

	private StringBuilder lockSkuURL = new StringBuilder(GlobalConstants.BASE_URL_IOGORDER).append("iog/order/createOrder");

	private StringBuilder GetOrderLockSupplierInfoURL=new StringBuilder(GlobalConstants.BASE_URL_GETLOCKSUPPLIER).append("Api/OrderStock/GetOrderLockSupplierSkuList");

	private StringBuilder StockAbnormalSyncZeroURL=new StringBuilder(GlobalConstants.BASE_URL_GETLOCKSUPPLIER).append("Api/Stock/ScmPurchaseAbnormalSyncZero");
	
	// 礼品卡--更新信息到数据库
	private StringBuilder updateGiftCardToDbURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("account/updateGiftCardToDb");
// 礼品卡--根据卡id获取礼品卡状态
	private StringBuilder getGiftCardStatusByGiftCardIdURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("getGiftCardStatusByOrderEGiftCardId");// 礼品卡状态查询
    private StringBuilder getGiftCardStatusURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("getGiftCardStatus");	
    
    //获取楼层信息
    private StringBuilder SubjectFloorInfoURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/subjectFloorInfo");
    //活动提醒
    private StringBuilder activityStartRemindURL =new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/activityStartRemind");
    
    /** 
     * 全部品牌
     * 
     * @return
     */
    public String queryBrandList() {
        Map<String, String> params = new HashMap<String, String>();
        return HttpClientUtil.doGet(brandListURL.toString(), params);
    }
    
	/**
	 * 获取CMS尚品专题商品列表
	 * 
	 * @author liujie
	 */
	@Override
	public String findTopicProducts(ListOfGoods listOfGoodsVO) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", listOfGoodsVO.getUserId());
		params.put("topicid", listOfGoodsVO.getTopicId());
		params.put("gender", listOfGoodsVO.getGender());
		params.put("picw", listOfGoodsVO.getPicw());
		params.put("pich", listOfGoodsVO.getPich());
		params.put("pageindex", listOfGoodsVO.getPageIndex());
		params.put("pagesize", listOfGoodsVO.getPageSize());
		params.put("filtersold", listOfGoodsVO.getFilterSold());
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "SPNewTopicProducts", params, findTopicProductsURL.toString());
	}

	/**
	 * 获取尚品男士9宫格活动
	 * 
	 * @author liujie
	 */
	@Override
	public String findMan9Grids(ListOfGoods listOfGoodsVO) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("gender", listOfGoodsVO.getGender());
		params.put("picw", listOfGoodsVO.getPicw());
		params.put("pich", listOfGoodsVO.getPich());
		String result = HttpClientUtil.doGet(findMan9GridsURL.toString(), params);
		return result;
	}

	/**
	 * 批量获取图片链接
	 * 
	 * @author liujie
	 */
	@Override
	public String findPicUrls(ListOfGoods listOfGoodsVO) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("picw", listOfGoodsVO.getPicw());
		params.put("pich", listOfGoodsVO.getPich());
		params.put("picNos", listOfGoodsVO.getPicNos());
		params.put("picType", listOfGoodsVO.getPicType());
		String result = HttpClientUtil.doPost(findPicUrlsURL.toString(), params);
		return result;
	}

	/**
	 * 根据开始结束时间获取尚品活动列表
	 * 
	 * @author liujie
	 */
	@Override
	public String findSubjectList(String gender, String picw, String pich, String startTime, String endTime) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("gender", gender);
		params.put("pich", picw);
		params.put("picw", pich);
		params.put("starttime", startTime);
		params.put("endtime", endTime);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "SubjectList", params, findSubjectListURL.toString());
	}

	/**
	 * 获取品牌旗舰店数据
	 * 
	 * @author liujie
	 */
	@Override
	public String findFlagShip(String brandNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("brandno", brandNo);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "flagship", params, findFlagShipURL.toString());
	}

	/**
	 * 根据活动名称或者活动编号获取符合条件的尚品活动列表
	 * 
	 * @author liujie
	 */
	@Override
	public String findSearchSubject(String keyWord) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("keyword", keyWord);
		String result = HttpClientUtil.doGet(findSearchSubjectURL.toString(), params);
		return result;
	}

	// 尚品专题商品列表
	@Override
	public String findSPTopicProducts(ListOfGoods listOfGoodsVO) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("gender", listOfGoodsVO.getGender());
		params.put("pageindex", listOfGoodsVO.getPageIndex());
		params.put("pagesize", listOfGoodsVO.getPageSize());
		params.put("userid", listOfGoodsVO.getUserId());
		params.put("topicid", listOfGoodsVO.getTopicId());
		params.put("pich", listOfGoodsVO.getPich());
		params.put("picw", listOfGoodsVO.getPicw());
		params.put("filtersold", listOfGoodsVO.getFilterSold());
		String result = HttpClientUtil.doGet(findSPTopicProductsURL.toString(), params);
		return result;
	}

	// 尚品品类新品商品列表
	@Override
	public String findSPNewProducts(ListOfGoods listOfGoodsVO) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("gender", listOfGoodsVO.getGender());
		params.put("pageindex", listOfGoodsVO.getPageIndex());
		params.put("pagesize", listOfGoodsVO.getPageSize());
		params.put("userid", listOfGoodsVO.getUserId());
		params.put("pich", listOfGoodsVO.getPich());
		params.put("picw", listOfGoodsVO.getPicw());
		params.put("filtersold", listOfGoodsVO.getFilterSold());
		params.put("categoryid", listOfGoodsVO.getCategoryId());
		String result = HttpClientUtil.doGet(findSPNewProductsURL.toString(), params);
		return result;
	}

	// 尚品获取品类品牌商品列表
	@Override
	public String findSPProductsAndBrand(ListOfGoods listOfGoodsVO) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("gender", listOfGoodsVO.getGender());
		params.put("pageindex", listOfGoodsVO.getPageIndex());
		params.put("pagesize", listOfGoodsVO.getPageSize());
		params.put("userid", listOfGoodsVO.getUserId());
		params.put("pich", listOfGoodsVO.getPich());
		params.put("picw", listOfGoodsVO.getPicw());
		params.put("filtersold", listOfGoodsVO.getFilterSold());
		params.put("categoryid", listOfGoodsVO.getCategoryId());// 品类ID
		params.put("brandid", listOfGoodsVO.getBrandId());// 品牌ID
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "SPProductsInCateAndBrand", params, findSPProductsAndBrandURL.toString());
	}

	// 尚品商品详情
	@Override
	public String findSPProductDetail(ListOfGoods listOfGoodsVO) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("productid", listOfGoodsVO.getProductId());
		params.put("topicid", listOfGoodsVO.getTopicId());
		params.put("userid", listOfGoodsVO.getUserId());
		params.put("pich", listOfGoodsVO.getPich());
		params.put("picw", listOfGoodsVO.getPicw());
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "SPProductDetail", params, findSPProductDetailURL.toString());
	}

	// 根据性别查询品牌列表
	@Override
	public String findCapitalBrandList(String gender) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("gender", gender);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "SPBrands", params, sPBrandsURL.toString());
	}

	// 查询以大写字母分类的品牌列表
	@Override
	public List<CapitalBrand> findCapitalBrandListObj(String gender) {
		String result = findCapitalBrandList(gender.toString());
		ResultObj<CapitalBrand> obj = JsonUtil.fromJson(result, new TypeToken<ResultObj<CapitalBrand>>() {
		});
		return obj.getList();
	}

	// 品牌对应的产品列表
	@Override
	public List<Merchandise> findMerchandiseListObj(ListOfGoods paramVo) {
		String result = findSPProductsAndBrand(paramVo);
		ResultObj<Merchandise> obj = JsonUtil.fromJson(result, new TypeToken<ResultObj<Merchandise>>() {
		});
		return obj.getList();
	}

	// 尚品品类新品商品列表
	@Override
	public List<Merchandise> findSPNewProductsObj(ListOfGoods paramVo) {
		String result = findSPNewProducts(paramVo);
		ResultObj<Merchandise> obj = JsonUtil.fromJson(result, new TypeToken<ResultObj<Merchandise>>() {
		});
		return obj.getList();
	}

	/**
	 * 获取CMS尚品专题商品列表
	 * 
	 * @author zhanghongwei
	 */
	@Override
	public Topic findTopicProductsObj(ListOfGoods listOfGoodsVO) {
		String result = this.findTopicProducts(listOfGoodsVO);
		ResultObjOne<Topic> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjOne<Topic>>() {
		});
		return obj.getObj();
	}

	// 尚品一级商品调用主站入口
	@Override
	public List<SPMerchandise> getCategoryByProducts(String categoryId, String gender, String userId) {
		// 拼装传递参数
		ListOfGoods listOfGoods = new ListOfGoods();
		listOfGoods.setGender(gender);
		listOfGoods.setPageIndex("1");// 1
		listOfGoods.setPageSize("3");// 3
		if ("1".equals(gender)) {
			listOfGoods.setPageSize("7");// 7
		}
		listOfGoods.setUserId(userId);
		listOfGoods.setCategoryId(categoryId);
		listOfGoods.setPich(GlobalConstants.MERCHANDISE_LIST_PICTURE_HEIGHT);
		listOfGoods.setPicw(GlobalConstants.MERCHANDISE_LIST_PICTURE_WIDTH);
		listOfGoods.setFilterSold("0");
		listOfGoods.setBrandId("");
		// 尚品获取品类品牌商品列表
		String json = findSPProductsAndBrand(listOfGoods);

		ResultObj<SPMerchandise> list = JsonUtil.fromJson(json, new TypeToken<ResultObj<SPMerchandise>>() {
		});
		return list.getList();
	}

	// 男士首页品牌调用方法
	@Override
	public List<SPMerchandise> getCategoryByBrand(String pageindex, String pagesize, String categoryId, String gender, String userId) {
		// 拼装传递参数
		ListOfGoods listOfGoods = new ListOfGoods();
		listOfGoods.setGender(gender);
		listOfGoods.setPageIndex(pageindex);
		listOfGoods.setPageSize(pagesize);
		listOfGoods.setUserId(userId);
		listOfGoods.setCategoryId(categoryId);
		listOfGoods.setPich(GlobalConstants.MERCHANDISE_LIST_PICTURE_HEIGHT);
		listOfGoods.setPicw(GlobalConstants.MERCHANDISE_LIST_PICTURE_WIDTH);
		listOfGoods.setFilterSold("0");
		listOfGoods.setBrandId("");
		// 尚品获取品类品牌商品列表
		String json = findSPProductsAndBrand(listOfGoods);

		ResultObj<SPMerchandise> list = JsonUtil.fromJson(json, new TypeToken<ResultObj<SPMerchandise>>() {
		});
		return list.getList();
	}

	/**
	 * 商城推广位
	 * 
	 * 
	 */
	@Override
	public String queryMallPromotionList() {
		Map<String, String> params = new HashMap<String, String>();
		String result = HttpClientUtil.doGet(promotionURL.toString(), params);
		return result;
	}

	/**
	 * 商城品类
	 * 
	 * 
	 */
	@Override
	public String queryMallCategoryList() {
		Map<String, String> params = new HashMap<String, String>();
		String result = HttpClientUtil.doGet(mallCategoryURL.toString(), params);
		return result;
	}

	/**
	 * 商城品牌
	 * 
	 * @param pageIndex
	 *            起始页
	 * @param pageSize
	 *            条数
	 * @return
	 */
	@Override
	public String queryBrandList(String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "Brand/mallBrandList", params, mallBrandURL.toString());
	}

	/**
	 * 返回新品首頁列表
	 */
	@Override
	public String findLatestProductList(String userId, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		String result = HttpClientUtil.doPost(latestProductListURL.toString(), params);
		return result;
	}

	/**
	 * 返回新品首頁列表的LatestProduct对象集合
	 */
	@Override
	public List<LatestProduct> findLatestProductListObj(String userId, String pageIndex, String pageSize) {
		String result = findLatestProductList(userId, pageIndex, pageSize);
		ResultObj<LatestProduct> obj = JsonUtil.fromJson(result, new TypeToken<ResultObj<LatestProduct>>() {
		});
		return obj.getLatestProductList();
	}

	/**
	 * 返回新品更多列表
	 */
	@Override
	public String findLatestProductMoreList(String userId, String brandId, String sortId, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("brandId", brandId);
		params.put("sortId", sortId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		String result = HttpClientUtil.doPost(latestProductMoreListURL.toString(), params);
		return result;
	}

	/**
	 * 返回新品更多列表的LatestProductMore对象
	 */
	@Override
	public LatestProductMore findLatestProductMoreObj(String userId, String brandId, String sortId, String pageIndex, String pageSize) {
		String result = findLatestProductMoreList(userId, brandId, sortId, pageIndex, pageSize);
		ResultObjOne<LatestProductMore> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjOne<LatestProductMore>>() {
		});
		return obj.getObj();
	}

	/**
	 * 返回新品品牌筛选列表
	 */
	@Override
	public String findLatestProductSortList(String brandId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("brandId", brandId);
		String result = HttpClientUtil.doPost(latestProductSortListURL.toString(), params);
		return result;
	}

	/**
	 * 
	 * @param type
	 *            1：新品顶部轮播图; 2：商城顶部轮播图
	 * 
	 * @return
	 */
	@Override
	public String queryGalleryList(String type, String frames) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("frames", frames);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "AlterPic/gallery", params, galleryURL.toString());
	}
	
	@Override
	public String queryGalleryList(String type, String frames, String brandId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("frames", frames);
		params.put("brandId", brandId);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "AlterPic/gallery", params, galleryURL.toString());
	}

	@Override
	public List<Picture> findGalleryList(String type, String frames) {
		String result = queryGalleryList(type, frames);
		ResultObj<Picture> obj = JsonUtil.fromJson(result, new TypeToken<ResultObj<Picture>>() {
		});
		return obj.getGallery();
	}
	
	/**
	 * 验证V码是否与活动匹配并且该V码是否和账户绑定
	 * 
	 * @param vcode
	 * @param subjectNo
	 * @param userId
	 * @return
	 * @author wangfeng
	 */
	@Override
	public String checkVcode(String vcode, String subjectNo, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("vcode", vcode);
		params.put("subjectNo", subjectNo);
		return HttpClientUtil.doGet(checkVcodeURL.toString(), params);
	}

	/**
	 * V码绑定到指定用户
	 * 
	 * @param vcode
	 * @param userId
	 * @return
	 * @author wangfeng
	 */
	@Override
	public String bindVcode(String vcode, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("vcode", vcode);
		return HttpClientUtil.doGet(bindVcodeURL.toString(), params);
	}

	/**
	 * 用户是否绑定活动V码接口
	 * 
	 * @param userId
	 * @param subjectNo
	 * @return
	 * @author wangfeng
	 */
	@Override
	public String queryVcode(String userId, String subjectNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("subjectNo", subjectNo);
		return HttpClientUtil.doGet(queryVcodeURL.toString(), params);
	}

	/**
	 * 商城品类对象
	 * 
	 * @return MallCategory
	 * @author liling
	 * 
	 */
	@Override
	public MallCategory findMallCategory() {
		String result = queryMallCategoryList();
		ResultObjOne<MallCategory> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjOne<MallCategory>>() {
		});
		return obj.getObj();
	}

	/**
	 * 商城品牌对象
	 * 
	 * @return MallBrandList
	 * @author wangfeng
	 * 
	 */
	@Override
	public MallBrandList findMallBrandList(String pageIndex, String pageSize) {
		String result = queryBrandList(pageIndex, pageSize);
		ResultObjOne<MallBrandList> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjOne<MallBrandList>>() {
		});
		return obj.getObj();
	}

	/**
	 * 商城推广位列表
	 * 
	 * @return List<Promotion>
	 * @author wangfeng
	 */
	@Override
	public List<Promotion> findMallPromotionList() {
		String result = queryMallPromotionList();
		ResultObj<Promotion> obj = JsonUtil.fromJson(result, new TypeToken<ResultObj<Promotion>>() {
		});
		return obj.getPromotion();
	}

	/**
	 * 获取look列表接口
	 * 
	 * @return List<Look>
	 * @author wangfeng
	 */
	@Override
	public List<Look> getLooks(String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		String result = HttpClientUtil.doGet(getLooksURL.toString(), params);
		ResultObj<Look> obj = JsonUtil.fromJson(result, new TypeToken<ResultObj<Look>>() {
		});
		return obj.getList();
	}

	/**
	 * 获取look商品列表
	 * 
	 * @return List<Promotion>
	 * @author wangfeng
	 */
	@Override
	public LookForProduct getLookProducts(String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		String result = HttpClientUtil.doGet(getLookProductsURL.toString(), params);
		ResultObjOne<LookForProduct> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjOne<LookForProduct>>() {
		});
		return obj.getObj();
	}

	@Override
	public String createCollectActivity(String userId, String activityId, String type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("activityId", activityId);
		params.put("type", type);
		String result = HttpClientUtil.doPost(collectActivityURL.toString(), params);
		return result;
	}

	@Override
	public String findCollectActivityList(String userId, String pageIndex, String pageSize, String type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		params.put("type", type);
		String result = HttpClientUtil.doPost(collectActivityListURL.toString(), params);
		return result;
	}

	/**
	 * 查询订单物流跟踪
	 */
	@Override
	public String findOrderMoreLogistic(String userId, String orderId, String postArea,String isNew) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("orderId", orderId);
		params.put("postArea", postArea);
		params.put("isNew", isNew);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "orderMoreLogistic", params, orderMoreLogisticURL.toString());
	}

	/**
	 * 获取活动头部运营位
	 * 
	 * @return String
	 * @author wangfeng
	 */
	@Override
	public String getTopAdver(String subjectno) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("subjectno", subjectno);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "ActivetyTopAdver", params, getTopAdverURL.toString());
	}

	/**
	 * 尚品获取所有未结束和今天即将开始的活动
	 */
	@Override
	public String findEndingSubjectList(String gender, String picw, String pich) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("gender", gender);
		params.put("picw", picw);
		params.put("pich", pich);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "SelectEndingSubjectList", params, selectEndingSubjectListURL.toString());
	}

	/**
	 * 按起止时间获取活动展示
	 */
	@Override
	public String findSubjectListByPeriod(String gender, String startTime, String endTime, String picw, String pich) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("gender", gender);
		params.put("starttime", startTime);
		params.put("endtime", endTime);
		params.put("picw", picw);
		params.put("pich", pich);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "SubjectList", params, subjectListURL.toString());
	}

	/**
	 * 活动商品列表页
	 */
	@Override
	public String findSubjectProductList(String subjectNo, String picw, String pich, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("subjectNo", subjectNo);
		params.put("picurlw", picw);
		params.put("picurlh", pich);
		params.put("pageindex", pageIndex);
		params.put("pagesize", pageSize);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "subjectproductlist", params, subjectProductListURL.toString());
	}

	/***
	 * 初始化品牌商品列表
	 */
	@Override
	public String findBrandProductList(String start, String end, String brandNo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", start);
		map.put("end", end);
		map.put("brandNO", brandNo);
		return HttpClientUtil.doGet(brandProductListURL.toString(), map);
	}

	/***
	 * 品牌商品搜索列表
	 */
	@Override
	public String findBrandProductList(String start, String num, String brandNo, String categoryNo, String color, String size, String price, String order) {
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isEmpty(start)) {
			map.put("start", start);
		}
		if (!StringUtils.isEmpty(num)) {
			map.put("end", num);
		}
		if (!StringUtils.isEmpty(brandNo)) {
			map.put("brandNO", brandNo);
		}
		if (!StringUtils.isEmpty(categoryNo)) {
			map.put("categoryNO", categoryNo);
		}
		if (!StringUtils.isEmpty(price)) {
			if (price.endsWith("以上")) {
				map.put("price", price.substring(0, price.indexOf("以") - 1) + "~");
			} else {
				map.put("price", price);
			}
		}
		if (!StringUtils.isEmpty(size)) {
			map.put("productSize", size);
		}
		if (!StringUtils.isEmpty(color)) {
			map.put("primaryColorId", color);
		}
		if (!StringUtils.isEmpty(order)) {
			map.put("order", order);
		}
		return HttpClientUtil.doGet(brandProductListURL.toString(), map);
	}

	@Override
	public List<ActivityOfMain> findSearchSubjectList(String keyWord) {
		String result = findSearchSubject(keyWord);
		ResultObjList<ActivityOfMain> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjList<ActivityOfMain>>() {
		});
		return obj.getList();
	}

	@Override
	public List<ActivityOfMain> findSubjectListByTime(String startTime, String endTime) {
		String result = findSubjectList(startTime, endTime);
		ResultObjList<ActivityOfMain> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjList<ActivityOfMain>>() {
		});
		return obj.getList();
	}

	/**
	 * 根据开始结束时间获取尚品活动列表
	 * 
	 * @author liujie
	 */
	@Override
	public String findSubjectList(String startTime, String endTime) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("starttime", startTime);
		params.put("endtime", endTime);
		String result = HttpClientUtil.doGet(findSubjectListURL.toString(), params);
		return result;
	}

	/**
	 * 获取最新上架列表
	 * 
	 * @param pageIndex
	 *            起始页
	 * @param pageSize
	 *            条数
	 * @return
	 * @author wangfeng
	 */
	@Override
	public String queryNewReleases(String pageIndex, String pageSize, String origin) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		params.put("origin", origin);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "ListingCatalog/newReleases", params, newReleasesURL.toString());
	}

	/**
	 * 获取风格主题列表
	 * 
	 * @param pageIndex
	 *            起始页
	 * @param pageSize
	 *            条数
	 * @return
	 * @author wangfeng
	 */
	@Override
	public String queryStyleTheme(String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		String result = HttpClientUtil.doGet(styleThemeURL.toString(), params);
		return result;
	}

	@Override
	public String findHomeSale() {
		Map<String, String> params = new HashMap<String, String>();
		String result = HttpClientUtil.doGet(homeSaleURL.toString(), params);
		return result;
	}

	@Override
	public String findMoreSale(String type, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		String result = BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "moreSale", params, moreSaleURL.toString());
		return result;
	}

	@Override
	public String findPopularityAndWorth(String type, String userId, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("userId", userId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "ListingCatalog/popularityAndWorth", params, popularityAndWorthURL.toString());
	}

	@Override
	public String findHeadInfo(String userid, String id, String type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userid);
		params.put("id", id);
		params.put("type", type);
		String result = HttpClientUtil.doGet(headInfoURL.toString(), params);
		return result;
	}

	@Override
	public String findCouponInfo(String userId, String id, String type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("id", id);
		params.put("type", type);
		String result = HttpClientUtil.doGet(couponInfoURL.toString(), params);
		return result;
	}

	@Override
	public String findPromotionInfo(String id, String type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("type", type);
		String result = HttpClientUtil.doGet(promotionInfoURL.toString(), params);
		return result;
	}

	@Override
	public String findProductTemplate(String type, String productId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("productId", productId);
		String result = HttpClientUtil.doGet(productTemplateURL.toString(), params);
		return result;
	}

	@Override
	public String findEntranceIndex(String type, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "ListingCatalog/entranceIndex", params, entranceIndexURL.toString());
	}

	@Override
	public String findProductDetail(String activityId, String productId, String userId, String picNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("activityId", activityId);
		params.put("productId", productId);
		params.put("userId", userId);
		params.put("picNo", picNo);
		String result = BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "productDetail", params, productDetailURL.toString());
		return result;
	}

	@Override
	public String findCommentList(String productId, String pageIndex, String pageSize,String tagId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("productId", productId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		params.put("tagId", tagId);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "commentList", params, commentListURL.toString());
	}

	@Override
	public String findFashionDetail(String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "ListingCatalog/fashionDetail", params, fashionDetailURL.toString());
	}

	@Override
	public String findFashionList(String type, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "ListingCatalog/fashionList", params, fashionListURL.toString());
	}

	@Override
	public String findBuyNow(String userId, String skuId, String productId, String activityId, String amount, String region) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("skuId", skuId);
		params.put("productId", productId);
		params.put("activityId", activityId);
		params.put("amount", amount);
		params.put("region", region);
		return HttpClientUtil.doPost(buyNowURL.toString(), params);
	}
	
	@Override
	public String findBuyNow(String userId, String skuId, String productId, String activityId, String amount, String region, String type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("skuId", skuId);
		params.put("productId", productId);
		params.put("activityId", activityId);
		params.put("amount", amount);
		params.put("region", region);
		params.put("isPayMentDef", type);
		return HttpClientUtil.doPost(buyNowURL.toString(), params);
	}
	
	@Override
	public String findBuyNow(String userId, String skuId, String productId,String activityId, String amount, String region, String type,String isDefaultUseCoupon) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("skuId", skuId);
		params.put("productId", productId);
		params.put("activityId", activityId);
		params.put("amount", amount);
		params.put("region", region);
		params.put("isPayMentDef", type);
		params.put("isDefaultUseCoupon", isDefaultUseCoupon);
		return HttpClientUtil.doPost(buyNowURL.toString(), params);
	}

@Override
	public String findBuyNowThrid(String userId, String skuId, String productId,String activityId, String amount, String region, String chanelNo,String chanelId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("skuId", skuId);
		params.put("productId", productId);
		params.put("activityId", activityId);
		params.put("amount", amount);
		params.put("region", region);
		params.put("channelNo", chanelNo);
		params.put("channelId", chanelId);
		return HttpClientUtil.doPost(buyNowURL.toString(), params);
	}
	@Override
	public String findProductSize(String productId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("productId", productId);
		String result = HttpClientUtil.doGet(productSizeURL.toString(), params);
		return result;
	}

	@Override
	public String queryCategoryOperation(String id, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("userId", userId);
		String result = HttpClientUtil.doGet(queryCategoryOperationURL.toString(), params);
		return result;
	}
	
	@Override
	public String queryCategoryOperations(String id, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("userId", userId);
		return HttpClientUtil.doGet(queryCategoryOperationsURL.toString(), params);
	}
	
	@Override
	public String queryCategoryOperation(String id, String userId, String mark) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("userId", userId);
		params.put("mark", mark);
		return HttpClientUtil.doGet(queryCategoryOperationURL.toString(), params);
	}

	@Override
	public String collectProduct(String shopType, String skuId, String userId, String picw, String pich, String detailPicw, String detailpich) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("shopType", shopType);
		params.put("sku", skuId);
		params.put("userid", userId);
		params.put("picw", picw);
		params.put("pich", pich);
		params.put("detailpicw", detailPicw);
		params.put("detailpich", detailpich);
		String result = HttpClientUtil.doGet(collectProductURL.toString(), params);
		return result;
	}

	@Override
	public String cancleCollectProduct(String shopType, String collectId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("favoriteproductid", collectId);
		params.put("shoptyp", shopType);
		String result = HttpClientUtil.doGet(cancleCollectProductURL.toString(), params);
		return result;
	}

	@Override
	public String findCollectProductList(String userId, String pageIndex, String pageSize, String type, String postArea) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		params.put("shopType", type);
		params.put("postArea", postArea);
		String result = HttpClientUtil.doGet(collectProductListURL.toString(), params);
		return result;
	}

	@Override
	public String getRecBrand(String userId, String vuId, String num) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", userId);
		params.put("vuid", vuId);
		params.put("num", num);
		return HttpClientUtil.doGet(customBrandURL.toString(), params);
	}
	@Override
	public String getFavBrandList(String userId, String vuId, String num) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", userId);
		params.put("vuid", vuId);
		params.put("num", num);
		return HttpClientUtil.doGet(favBrandListURL.toString(), params);
	}

	@Override
	public String getCollectFavBrand(String brandId, String userId, String vuId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("brandNo", brandId);
		params.put("uid", userId);
		params.put("vuid", vuId);
		return HttpClientUtil.doPost(collectFavBrandURL.toString(), params);
	}

	@Override
	public String getOperation(String type, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "ListingCatalog/operation", params, operationURL.toString());
	}

	@Override
	public String giftCardList(String categoryNO, String productNo, String start, String end, String userID, String userIP, String encode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("categoryNO", categoryNO);
		params.put("productNO", productNo);
		params.put("start", start);
		params.put("end", end);
		params.put("userID", userID);
		params.put("userIP", userIP);
		params.put("encode", encode);
		String result = HttpClientUtil.doGet(giftCardListURL.toString(), params);
		return result;
	}

	@Override
	public String giftCardRecordList(String userId, String recordType, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("recordType", recordType);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		String result = HttpClientUtil.doGet(giftCardRecordListURL.toString(), params);
		return result;
	}

	@Override
	public String giftCardElectronicRecharge(String userId, String orderId, String cardPasswd) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("cardPasswd", cardPasswd);
		params.put("orderId", orderId);
		String result = HttpClientUtil.doGet(giftCardElectronicRechargeURL.toString(), params);
		return result;
	}

	@Override
	public String giftCardBuy(String userId, String skuId, String productId, String quantity, String categoryNo, String type, String eGiftCardAmount) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("skuId", skuId);
		params.put("productId", productId);
		params.put("quantity", quantity);
		params.put("categoryNo", categoryNo);
		params.put("type", type);
		params.put("eGiftCardAmount", eGiftCardAmount);
		String result = HttpClientUtil.doGet(giftCardBuyURL.toString(), params);
		return result;
	}

	@Override
	public String giftCardCommit(String userId, String addressId, String invoiceFlag, String invoiceAddressId, String invoiceType, String invoiceTitle, String invoiceContent,
			String express, String orderFrom, String buysIds, String payTypeId, String payTypeChildId, String orderType, String type,
								 String invoiceEmail,String invoiceTel) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("addrid", addressId);
		params.put("invoiceflag", invoiceFlag);
		params.put("invoiceaddrid", invoiceAddressId);
		params.put("invoicetype", invoiceType);
		params.put("invoicetitle", invoiceTitle);
		params.put("invoicecontent", invoiceContent);
		params.put("express", express);
		params.put("orderfrom", orderFrom);
		params.put("buysIds", buysIds);
		params.put("paytypeid", payTypeId);
		params.put("paytypechildid", payTypeChildId);
		params.put("ordertype", orderType);
		params.put("type", type);

		//2.9.12增加start
		params.put("invoiceEmail",invoiceEmail);
		params.put("invoiceTel",invoiceTel);
		//2.9.12增加end

		String result = HttpClientUtil.doGet(giftCardCommitURL.toString(), params);
		return result;
	}

	@Override
	public String giftCardRechargePasswd(String userId, String orderId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("orderId", orderId);
		String result = HttpClientUtil.doGet(giftCardRechargePasswdURL.toString(), params);
		return result;
	}
	
	@Override
	public String giftCardStatus(String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		String result = HttpClientUtil.doGet(giftCardStatusURL.toString(), params);
		return result;
	}
	@Override
	public String giftCardEntityRecharge(String userId,String cardNo,String cardPwd){
		Map<String,String> params = new HashMap<String,String>();
		params.put("userId", userId);
		params.put("cardNo", cardNo);
		params.put("cardPwd", cardPwd);
		String result = HttpClientUtil.doGet(giftCardEntityRchargeURL.toString(), params);
		return result;
	}
	@Override
	public String collectBrandList(String userId, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		return HttpClientUtil.doGet(collectBrandListURL.toString(), params);
	}
	@Override
	public String uploadPic(String extension, String picturetype, String picFileContent) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("extension", extension);
		params.put("picturetype", picturetype);
		params.put("picFileContent", picFileContent);
		return HttpClientUtil.doPostFile(uploadPicURL.toString(), params);
	}

	@Override
	public String findNewGoods(String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "ListingCatalog/newGoods", params, newGoodsURL.toString());
	}

	@Override
	public String findFirstNewGoods(String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "ListingCatalog/firstNewGoods", params, firstNewGoodsURL.toString());
	}

    @Override
    public String getHeadAdverts(String pageIndex, String pageSize) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        return HttpClientUtil.doGet(headAdvertsURL.toString(), params);
    }
    
    @Override
	public String findHeadNewGoods() {
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "ListingCatalog/headNewGoods", null, headNewGoodsURL.toString());
	}

	@Override
	public String getHeadAdverts() {
        return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "ListingCatalog/headAdverts", null, headAdvertsURL.toString());
	}
	
	@Override
    public String findProductDetailNew(String activityId, String productId, String userId, String picNo) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("activityId", activityId);
        params.put("productId", productId);
        params.put("userId", userId);
        params.put("picNo", picNo);
        return HttpClientUtil.doGet(productDetailNewURL.toString(), params);
    }
	
	@Override
    public String findProductDetailNew(String activityId, String productId, String userId, String picNo, String isNew) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("activityId", activityId);
        params.put("productId", productId);
        params.put("userId", userId);
        params.put("picNo", picNo);
        params.put("isNew", isNew);
        return HttpClientUtil.doGet(productDetailNewURL.toString(), params);
    }

	@Override
	public String getLabel(String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "ListingCatalog/revealLabel", params, labelURL.toString());
	}
	
	@Override
	public String getLabel(String pageIndex, String pageSize, String type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        params.put("isNew", type);
        return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "ListingCatalog/revealLabel", params, labelURL.toString());
	}
	
	@Override
	public String productCount(String productNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("productId", productNo);
		return HttpClientUtil.doGet(productCountURL.toString(), params);
	}
	
	@Override
	public String findModelInfo(String id, String type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("brandId", id);
		params.put("type", type);
		String result = HttpClientUtil.doGet(modelInfoURL.toString(), params);
		return result;
	}
	
	@Override
	public String getShellWindow(String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        return HttpClientUtil.doGet(shellWindowURL.toString(), params);
	}
	
	@Override
	//获取底部导航栏图标
	public String getBottomNavigatePic(String plateForm) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("isIOS",plateForm);
		return HttpClientUtil.doGet(bottomNavigateURL.toString(),params);
	}

	@Override
	public String LockSku(String supplierNo, String orderNo,
			String orderItemList) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("supplierNo", supplierNo);
        params.put("orderNo", orderNo);
        params.put("orderItemList", orderItemList);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
				.create();
        String jsonStr = gson.toJson(params);
        int timeout=1000;
		return HttpClientUtil.doPostWithJSON(lockSkuURL.toString(),jsonStr,timeout);
	}

	@Override
	public String getOrderLockSupplierInfo(String orderNo) throws Exception {
        return HttpClientUtil.doPostWithJSON(GetOrderLockSupplierInfoURL.toString(), orderNo);
	}

	@Override
	public String giftCardRechargePasswdByCardId(String userId,	String giftCardId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
        params.put("giftCardId", giftCardId);
		return HttpClientUtil.doGet(giftCardRechargePasswdByCardURL.toString(),params);
	}

	@Override
	public String saveGiftCardToDb(String userId, String giftOrder,String giftCardId, String buyerName, String sendPhoneNum,
			String sendTime, String wishMsg, String wishPic) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("giftOrder", giftOrder);
		params.put("giftCardId", giftCardId);
		params.put("buyerName", buyerName);
		params.put("sendPhoneNum", sendPhoneNum);
		params.put("sendTime", sendTime);
		params.put("wishMsg", wishMsg);
		params.put("wishPic", wishPic);
		return HttpClientUtil.doGet(saveGiftCardToDbURL.toString(),params);
	}

	@Override
	public String updateGiftCardToDb(String userId, String giftCardId,String recgargeTime) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
        params.put("giftCardId", giftCardId);
        params.put("recgargeTime", recgargeTime);
		return HttpClientUtil.doGet(updateGiftCardToDbURL.toString(),params);
	}
	
	/*public static void main(String[] args) {
		ShangPinService service = new ShangPinServiceImpl();
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(new File("c:\\Users\\qinyingchun\\Desktop\\未标题-2.png")));
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ImageOutputStream imageOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(image, "jpg", imageOut);
			byte[] bt = bs.toByteArray();
			Base64 base64=new Base64();  
			String str = new String(base64.encode(bt));
			String json = service.uploadPic(".jpg", "system", str);
			System.out.println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	@Override
	public String stockAbnormalSyncZero(String warehouseNo, String supplierNo,
			String formNo, String operateUser, String skuDetailDtos) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("WarehouseNo", warehouseNo);
		params.put("SupplierNo", supplierNo);
		params.put("FormNo", formNo);
		params.put("OperateUser", operateUser );
		
	
		List<Map<String, String>> list =new ArrayList<Map<String, String>>();
		
		String[] skuArray =skuDetailDtos.split("[,]");
		for(int i=0;i<skuArray.length;i++){
			Map<String, String> skuMap = new HashMap<String, String>();
			skuMap.put("SkuNo", skuArray[i].split(":")[0]);
			skuMap.put("TargetQuantity", skuArray[i].split(":")[1]);
			list.add(skuMap);
		}
		params.put("DetailDtos", list );
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
				.create();
		
		 String jsonStr = gson.toJson(params);
		 return HttpClientUtil.doPostWithJSONFORM(StockAbnormalSyncZeroURL.toString(),"="+jsonStr);
	}

	@Override
	public String getGiftCardStatusByGiftCardId(String giftCardId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("orderGiftCardId", giftCardId);
		return HttpClientUtil.doGet(getGiftCardStatusByGiftCardIdURL.toString(), params);
	}    
	@Override
    public String cardStatus(String cardNo) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("cardNo", cardNo);
        return HttpClientUtil.doGet(getGiftCardStatusURL.toString(),params);
    }

	@Override
	public String subjectFloorInfo(String topicId) {
		Map<String, String> params = new HashMap<String, String>();
        params.put("topicId", topicId);
        return HttpClientUtil.doGet(SubjectFloorInfoURL.toString(),params);
	}

    @Override
    public String activityStartRemind(String phoneNum, String actityId, String time) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", phoneNum);
        params.put("actityId", actityId);
        params.put("time", time);
        return HttpClientUtil.doGet(activityStartRemindURL.toString(),params);
    }
    
    public static void main(String[] args) throws Exception{
    	Base64 base64 = new Base64();
        InputStream input = new FileInputStream(new File("F:/liusisi.jpg"));
        BufferedImage image = ImageIO.read(input);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imageOut = ImageIO
                .createImageOutputStream(bs);
        ImageIO.write(image, "jpg", imageOut);
        byte[] bt = bs.toByteArray();
        String str = new String(base64.encode(bt));
        System.out.println(str);
        Map<String, String> params = new HashMap<String, String>();
		params.put("extension", ".jpg");
		params.put("picturetype", "user");
		params.put("picFileContent", str);
		String url="http://qa.mobilev2.shangpin.com/shangpin/SaveMobilePicNew";
        String rs=HttpClientUtil.doPost(url, params);
        System.out.println(rs);
        
	}
}
