package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.SearchService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.SearchResult;
import com.shangpin.biz.bo.Brands;
import com.shangpin.biz.bo.CustomBrandItem;
import com.shangpin.biz.bo.NewGoods;
import com.shangpin.biz.bo.Picture;
import com.shangpin.biz.bo.PictureList;
import com.shangpin.biz.bo.SearchConditions;
import com.shangpin.biz.bo.SearchProduct;
import com.shangpin.biz.bo.SearchProductResult;
import com.shangpin.biz.bo.SearchType;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizBrandService;
import com.shangpin.biz.service.abstraction.AbstractBizBrandService;
import com.shangpin.biz.utils.ParseXmlUtil;
import com.shangpin.biz.utils.SearchUtil;
import com.shangpin.core.entity.HotBrands;
import com.shangpin.core.service.IHotBrandsService;
import com.shangpin.utils.JSONUtils;
import com.shangpin.utils.JsonUtil;
  
/**
 * @ClassName: BrandServiceImpl
 * @Description:品牌接口实现类
 * @author qinyingchun
 * @date 2014年10月24日
 * @version 1.0
 */
@Service
public class SPBizBrandServiceImpl extends AbstractBizBrandService implements SPBizBrandService {

	private static final Logger logger = LoggerFactory.getLogger(SPBizBrandServiceImpl.class);

	@Autowired
	private ShangPinService shangpinService;
	@Autowired
	private IHotBrandsService hotBrandService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private SearchService searchService;

	@Override
	public Brands brandList() {
		try {
			String json = shangpinService.queryBrandList();
			// String json =
			// "{'code': '0','msg': '成功','content': {'brandList': [{'capital': 'A','brands': [{'id': 'B0373','nameEN': 'ARMANI JEANS','nameCN': '阿玛尼','desc': '描述摘要','isFlagship': '是否是旗舰店0：不是1：是','pic': 'http://pic14.shangpin.com/e/s/14/08/20/20140820191723534713-10-10.jpg'}]},{'capital': 'B','brands': [{'id':'B0373', 'nameEN':'ARMANI JEANS','nameCN':'阿玛尼','desc': '描述摘要','isFlagship': '是否是旗舰店0：不是1：是', 'pic': 'http://pic14.shangpin.com/e/s/14/08/20/20140820191723534713-10-10.jpg'}]}]}}";
			logger.debug("调用base品牌列表接口返回数据:" + json);
			ResultObjOne<Brands> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<Brands>>() {
			});
			return obj.getObj();
		} catch (Exception e) {
			logger.error("调用base品牌列表接口返回数据错误！");
		}
		return null;
	}

	@Override
	public List<HotBrands> hotBrands() {
		List<HotBrands> hotBrands = null;
		try {
			hotBrands = hotBrandService.findHotBrandsList();
			logger.debug("调用core中热门品牌接口返回数据：" + hotBrands);
		} catch (Exception e) {
			logger.error("调用core中热门品牌接口返回数据错误！");
			hotBrands = new ArrayList<HotBrands>();
			e.printStackTrace();
		}
		return hotBrands;
	}

	@Override
	public SearchProduct initProduct(String start, String end, String brandNo) {
		try {
			String xml = shangpinService.findBrandProductList(start, end, brandNo);
			// File xml = new File("C:/Users/JH/Desktop/product.xml");
			logger.debug("调用base品牌商品列表接口返回数据:" + xml);
			return ParseXmlUtil.initProducts(xml);
		} catch (Exception e) {
			logger.error("调用base品牌商品列表接口返回数据错误!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Picture> getPicURL(String picNo, String width, String height, String type) {
		try {
			String json = commonService.queryPicUrl(picNo, width, height, type);
			logger.debug("调用base获取图片URL:" + json);
			ResultObjOne<PictureList> picture = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<PictureList>>() {
			});
			return picture.getObj().getList();
		} catch (Exception e) {
			logger.error("调用base获取图片URL错误！");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SearchProduct searchProduct(SearchConditions searchConditions) {
		try {
			String xml = shangpinService.findBrandProductList(searchConditions.getStart(), searchConditions.getNum(),
					searchConditions.getBrandNo(), searchConditions.getCategoryNo(), searchConditions.getColor(),
					searchConditions.getSize(), searchConditions.getPrice(), searchConditions.getOrder());
			logger.debug("调用base品牌商品列表接口返回数据:" + xml);
			// File xml = new File("C:/Users/JH/Desktop/product.xml");
			return ParseXmlUtil.initProducts(xml);
		} catch (Exception e) {
			logger.error("调用base品牌商品列表接口返回数据错误!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SearchResult brandProducts(String navId, String pageIndex, String pageSize, String tagId, String brandId,
			String price, String colorId, String size, String categoyId, String order, String userLv, String postArea) {
		SearchResult searchResult = null;
		try {
			searchResult = searchService.searchBrandProductList(navId, pageIndex, pageSize, tagId, brandId, price,
					colorId, size, categoyId, order, userLv, postArea);
		} catch (Exception e) {
			logger.error("base search brand product list interface return data occur error!");
			e.printStackTrace();
		}
		return searchResult;
	}

	@Override
	public SearchProductResult brandProductList(String navId, String pageIndex, String pageSize, String brandId,
			String price, String colorId, String size, String categoyId, String order, String userLv,
			SearchType searchType, String postArea) {
		try {
			String xml = searchService.searchBrandProduct(navId, pageIndex, pageSize, brandId, price, colorId, size,
					categoyId, order, userLv, postArea);
			logger.debug("调用base品牌商品列表接口返回数据:" + xml);
			SearchUtil searchUtil = new SearchUtil();
			return searchUtil.initSearchResult(xml, userLv, searchType);
		} catch (Exception e) {
			logger.error("调用base品牌商品列表接口返回数据错误!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HotBrands findHotBrand(String brandNo) {
		HotBrands hotBrands = hotBrandService.findHotBrands(brandNo);
		return hotBrands;
	}
	@Override
	public CustomBrandItem queryCustomBrand(String userId, String vuId, String num) {
		return fromQueryCustomBrand(userId, vuId, num);
	}

	@Override
	public List<NewGoods> getNewGoods(String userId) throws Exception {
		String json=findBaseNewGoods(userId);
		List<NewGoods> lists = new ArrayList<NewGoods>();
		if (StringUtils.isNotEmpty(json)) {
			ResultObjMapList<NewGoods> obj = JSONUtils.toGenericsCollection(json, ResultObjMapList.class, NewGoods.class);
			lists = obj.getList("list");
		}
		return lists;
	}
	
	
}