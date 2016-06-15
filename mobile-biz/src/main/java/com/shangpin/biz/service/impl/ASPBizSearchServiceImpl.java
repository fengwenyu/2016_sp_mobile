package com.shangpin.biz.service.impl;

import com.google.common.reflect.TypeToken;
import com.shangpin.base.service.CouponsService;
import com.shangpin.base.service.SearchService;
import com.shangpin.biz.bo.*;
import com.shangpin.biz.bo.base.ResultSuggestion;
import com.shangpin.biz.service.ASPBizSerchService;
import com.shangpin.biz.service.abstraction.AbstractBizSearchService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.biz.utils.PicCdnHash;
import com.shangpin.biz.utils.SearchParamUtil;
import com.shangpin.biz.utils.SearchUtil;
import com.shangpin.utils.JsonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class ASPBizSearchServiceImpl extends AbstractBizSearchService implements ASPBizSerchService {
	private static final Logger logger = LoggerFactory.getLogger(ASPBizSearchServiceImpl.class);

	@Autowired
	private SearchService searchService;
	@Autowired
	private CouponsService couponsService;

	@Override
	public SearchResult queryActivityProductList(String subjectNo, String start, String end, String tagId, String brandId, String price, String colorId, String size,
			String categoyId, String order, String gender, String userLv, String postArea, String isPre, ActivityHead activityHead, String imei) {
		SearchResult searchResult = new SearchResult();
		try {
			String xmlStr = searchService.searchActivityProductList(subjectNo, start, end, tagId, brandId, price, colorId, size, categoyId, order, userLv, postArea, isPre, imei);
			logger.debug("调用base接口返回数据:" + xmlStr);
			if (!StringUtils.isEmpty(xmlStr)) {
				searchResult = SearchUtil.converXmlToObj(xmlStr, "1", categoyId, activityHead);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		} catch (DocumentException e) {
			logger.error("调用base接口返回数据发生错误,xml解析错误！");
			e.printStackTrace();
		}
		return searchResult;
	}

	@Override
	public SearchResult queryBrandProductList(String navId, String pageIndex, String pageSize, String tagId, String brandId, String price, String colorId, String size,
			String categoyId, String order, String userLv, String postArea, String imei) {
		SearchResult searchResult = new SearchResult();
		try {
			String xmlStr = searchService.searchBrandShopProductList(navId, pageIndex, pageSize, tagId, brandId, price, colorId, size, categoyId, order, userLv, postArea, imei);
			logger.debug("调用base接口返回数据:" + xmlStr);
			if (!StringUtils.isEmpty(xmlStr)) {
				searchResult = SearchUtil.converXmlToObj(xmlStr, "2", categoyId, null);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		} catch (DocumentException e) {
			logger.error("调用base接口返回数据发生错误,xml解析错误！");
			e.printStackTrace();
		}
		return searchResult;
	}

	@Override
	public String querySuggestion(String keyword, String userID, String userIP, String encode) throws Exception {
		ResultSuggestion resultSuggestion = getSearchResult(keyword, userID, userIP, encode);
		return ApiBizData.spliceData(resultSuggestion, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
	}

	@Override
	public String queryTagProductList(String pageIndex, String pageSize, String userLv, String price, String size, String color, String tagId, String categoryId, String postArea,
			String brandId, String order) throws Exception {
		SearchResult searchResult = new SearchResult();
		String xmlStr = searchService.searchTagProductList(pageIndex, pageSize, userLv, price, size, color, tagId, categoryId, postArea, brandId, order);
		if (!StringUtils.isEmpty(xmlStr)) {
			searchResult = SearchUtil.converXmlToObj(xmlStr, "2", categoryId, null);
		}
		return ApiBizData.spliceData(searchResult, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
	}

	@Override
	public SearchResultApp queryBrandProductListNew(String navId, String pageIndex, String pageSize, String tagId, String brandId, String price, String colorId, String size,
			String categoyId, String order, String userLv, String postArea, String imei) {
		SearchResultApp searchResultApp = new SearchResultApp();
		try {
			String xmlStr = searchService.searchBrandShopProductList(navId, pageIndex, pageSize, tagId, brandId, price, colorId, size, categoyId, order, userLv, postArea, imei);
			logger.debug("调用base接口返回数据:" + xmlStr);
			if (!StringUtils.isEmpty(xmlStr)) {
				searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "2", categoyId, null), "2");
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		} catch (DocumentException e) {
			logger.error("调用base接口返回数据发生错误,xml解析错误！");
			e.printStackTrace();
		}
		return searchResultApp;
	}
	
	@Override
	public SearchResultApp queryBrandProductListNew(String navId, String pageIndex, String pageSize, String tagId, String brandId, String price, String colorId, String size,
			String categoyId, String order, String userLv, String postArea, String originalFilters, String dynamicFilters, String imei, String version) {
		SearchResultApp searchResultApp = new SearchResultApp();
		try {
			String xmlStr = searchService.searchBrandShopProductList(navId, pageIndex, pageSize, tagId, brandId, price, colorId, size, categoyId, order, userLv, postArea, imei);
			logger.debug("调用base接口返回数据:" + xmlStr);
			if (!StringUtils.isEmpty(xmlStr)) {
				if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.7", version) == 1) {
					searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "2", categoyId, null), "2", originalFilters, dynamicFilters);
					if(CollectionUtils.isEmpty(searchResultApp.getProductList())) {
						//排除价格区间和发货地重新查询筛选条件
						xmlStr = searchService.searchBrandShopProductList(navId, pageIndex, pageSize, tagId, brandId, null, colorId, size, categoyId, order, userLv, null, imei);
						searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "2", categoyId, null), "2", originalFilters, dynamicFilters);
						filterUnSelectedAttrs(searchResultApp);
						searchResultApp.setProductList(new ArrayList<Product>());
					}
				} else {
					searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "2", categoyId, null), "2");
				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		} catch (DocumentException e) {
			logger.error("调用base接口返回数据发生错误,xml解析错误！");
			e.printStackTrace();
		}
		return searchResultApp;
	}

	@Override
	public SearchResultApp queryActivityProductListNew(String subjectNo, String start, String end, String tagId, String brandId, String price, String colorId, String size,
			String categoyId, String order, String userLv, String postArea, String isPre, ActivityHead activityHead, String imei) {
		SearchResultApp searchResultApp = new SearchResultApp();
		try {
			String xmlStr = searchService.searchActivityProductList(subjectNo, start, end, tagId, brandId, price, colorId, size, categoyId, order, userLv, postArea, isPre, imei);
			logger.debug("调用base接口返回数据:" + xmlStr);
			if (!StringUtils.isEmpty(xmlStr)) {
				searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "1", categoyId, activityHead), "1");
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		} catch (DocumentException e) {
			logger.error("调用base接口返回数据发生错误,xml解析错误！");
			e.printStackTrace();
		}
		return searchResultApp;
	}
	
	@Override
	public SearchResultApp queryActivityProductListNew(String subjectNo, String start, String end, String tagId, String brandId, String price, String colorId, String size,
			String categoyId, String order, String userLv, String postArea, String isPre, ActivityHead activityHead, String originalFilters, String dynamicFilters, String imei,
			String version) {
		SearchResultApp searchResultApp = new SearchResultApp();
		try {
			String xmlStr = searchService.searchActivityProductList(subjectNo, start, end, tagId, brandId, price, colorId, size, categoyId, order, userLv, postArea, isPre, imei);
			logger.debug("调用base接口返回数据:" + xmlStr);
			if (!StringUtils.isEmpty(xmlStr)) {
				if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.7", version) == 1) {
					searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "1", categoyId, activityHead), "1", originalFilters, dynamicFilters);
					if(CollectionUtils.isEmpty(searchResultApp.getProductList())) {
						//排除价格区间和发货地重新查询过滤条件
						xmlStr = searchService.searchActivityProductList(subjectNo, start, end, tagId, brandId, null, colorId, size, categoyId, order, userLv, null, isPre, imei);
						searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "1", categoyId, activityHead), "1", originalFilters, dynamicFilters);
						filterUnSelectedAttrs(searchResultApp);
						searchResultApp.setProductList(new ArrayList<Product>());
					}
				} else {
					searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "1", categoyId, activityHead), "1");
				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		} catch (DocumentException e) {
			logger.error("调用base接口返回数据发生错误,xml解析错误！");
			e.printStackTrace();
		}
		return searchResultApp;
	}

	@Override
	public String queryTagProductListNew(String pageIndex, String pageSize, String userLv, String tagId, String order, String filters) throws Exception {
		SearchResultApp searchResultApp = new SearchResultApp();
		SearchParam searchParam = SearchParamUtil.parse(filters, "4");
		String xmlStr = searchService.searchTagProductList(pageIndex, pageSize, userLv, searchParam.getPriceId(), searchParam.getSizeId(), searchParam.getColorId(), tagId,
				searchParam.getCategoryId(), searchParam.getPostArea(), searchParam.getBrandId(), order);
		if (!StringUtils.isEmpty(xmlStr)) {
			String json = null;
			try {
				searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "2", searchParam.getCategoryId(), null), "4");
				json = ApiBizData.spliceData(searchResultApp, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
				return json;
			} catch (UnsupportedEncodingException e) {
				logger.error("调用base接口返回数据发生错误！");
				e.printStackTrace();
			} catch (DocumentException e) {
				logger.error("调用base接口返回数据发生错误,xml解析错误！");
				e.printStackTrace();
			} catch (Exception e) {
				logger.error("调用api接口返回数据发生错误！");
				e.printStackTrace();
			}
		}
		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}

	@Override
	public String queryKeyWordProductList(String keywords, String pageIndex, String pageSize, String tagId, String order, String userLv, String filters) throws Exception {
		SearchResultApp searchResultApp = new SearchResultApp();
		SearchParam searchParam = SearchParamUtil.parse(filters, "5");
		String xmlStr = searchService.searchProductListByKeyWord(keywords, pageIndex, pageSize, tagId, searchParam.getBrandId(), searchParam.getPriceId(),
				searchParam.getColorId(), searchParam.getSizeId(), searchParam.getCategoryId(), order, userLv, searchParam.getPostArea());
		if (!StringUtils.isEmpty(xmlStr)) {
			String json = null;
			try {
				searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "5", searchParam.getCategoryId(), null), "5");
				json = ApiBizData.spliceData(searchResultApp, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
				return json;
			} catch (UnsupportedEncodingException e) {
				logger.error("调用base接口返回数据发生错误！");
				e.printStackTrace();
			} catch (DocumentException e) {
				logger.error("调用base接口返回数据发生错误,xml解析错误！");
				e.printStackTrace();
			} catch (Exception e) {
				logger.error("调用api接口返回数据发生错误！");
				e.printStackTrace();
			}
		}
		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}
	
	@Override
	public String queryKeyWordProductList(String keywords, String pageIndex, String pageSize, String tagId, String order, String userLv, String filters, String originalFilters, String dynamicFilters, String version) throws Exception {
		SearchResultApp searchResultApp = new SearchResultApp();
		SearchParam searchParam = new SearchParam();
		if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.7", version) == 1) {
			searchParam = SearchParamUtil.parse(originalFilters, dynamicFilters, "5");
		} else {
			searchParam = SearchParamUtil.parse(filters, "5");
		}
		String xmlStr = searchService.searchProductListByKeyWord(keywords, pageIndex, pageSize, tagId, searchParam.getBrandId(), searchParam.getPriceId(),
				searchParam.getColorId(), searchParam.getSizeId(), searchParam.getCategoryId(), order, userLv, searchParam.getPostArea());
		if (!StringUtils.isEmpty(xmlStr)) {
			String json = null;
			try {
				if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.7", version) == 1) {
					searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "5", searchParam.getCategoryId(), null), "5", originalFilters, dynamicFilters);
					if(CollectionUtils.isEmpty(searchResultApp.getProductList())){
						//排除价格区间和发货地重新获取过滤条件
						xmlStr = searchService.searchProductListByKeyWord(keywords, pageIndex, pageSize, tagId, searchParam.getBrandId(), null,
								searchParam.getColorId(), searchParam.getSizeId(), searchParam.getCategoryId(), order, userLv, null);
						searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "5", searchParam.getCategoryId(), null), "5", originalFilters, dynamicFilters);
						filterUnSelectedAttrs(searchResultApp);
						searchResultApp.setProductList(new ArrayList<Product>());
					}
				} else {
					searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "5", searchParam.getCategoryId(), null), "5");
				}
				json = ApiBizData.spliceData(searchResultApp, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
				return json;
			} catch (UnsupportedEncodingException e) {
				logger.error("调用base接口返回数据发生错误！");
				e.printStackTrace();
			} catch (DocumentException e) {
				logger.error("调用base接口返回数据发生错误,xml解析错误！");
				e.printStackTrace();
			} catch (Exception e) {
				logger.error("调用api接口返回数据发生错误！");
				e.printStackTrace();
			}
		}
		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}

	@Override
	public String queryCategoryProductList(String pageIndex, String pageSize, String userLv, String tagId, String order, String filters, String imei) throws Exception {
		SearchResultApp searchResultApp = new SearchResultApp();
		SearchParam searchParam = SearchParamUtil.parse(filters, "3");

		String xmlStr = searchService.searchCategoryProduct(pageIndex, pageSize, searchParam.getBrandId(), searchParam.getPriceId(), searchParam.getColorId(),
				searchParam.getSizeId(), searchParam.getCategoryId(), order, userLv, "", searchParam.getPostArea(), searchParam.getAttributeId(), imei);
		if (!StringUtils.isEmpty(xmlStr)) {
			String json = null;
			try {
				searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "5", searchParam.getCategoryId(), null), "3");
				json = ApiBizData.spliceData(searchResultApp, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
				return json;
			} catch (UnsupportedEncodingException e) {
				logger.error("调用base接口返回数据发生错误！");
				e.printStackTrace();
			} catch (DocumentException e) {
				logger.error("调用base接口返回数据发生错误,xml解析错误！");
				e.printStackTrace();
			} catch (Exception e) {
				logger.error("调用api接口返回数据发生错误！");
				e.printStackTrace();
			}
		}
		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}
	
	@Override
	public String queryCategoryProductList(String pageIndex, String pageSize, String userLv, String tagId, String order, String filters, String originalFilters, String dynamicFilters, String imei, String version) throws Exception {
		SearchResultApp searchResultApp = new SearchResultApp();
		SearchParam searchParam = new SearchParam();
		if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.7", version) == 1) {
			searchParam = SearchParamUtil.parse(originalFilters, dynamicFilters, "3");
		} else {
			searchParam = SearchParamUtil.parse(filters, "3");
		}
		
		String xmlStr = searchService.searchCategoryProduct(pageIndex, pageSize, searchParam.getBrandId(), searchParam.getPriceId(), searchParam.getColorId(),
				searchParam.getSizeId(), searchParam.getCategoryId(), order, userLv, "", searchParam.getPostArea(), searchParam.getAttributeId(), imei);
		if (!StringUtils.isEmpty(xmlStr)) {
			String json = null;
			try {
				if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.7", version) == 1) {
					searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "5", searchParam.getCategoryId(), null), "3", originalFilters, dynamicFilters);
					if(CollectionUtils.isEmpty(searchResultApp.getProductList())) {
						//去掉价格区间和发货地重新查询结果集
						xmlStr = searchService.searchCategoryProduct(pageIndex, pageSize, searchParam.getBrandId(), null, searchParam.getColorId(),
								searchParam.getSizeId(), searchParam.getCategoryId(), order, userLv, "", null, searchParam.getAttributeId(), imei);
						searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "5", searchParam.getCategoryId(), null), "3", originalFilters, dynamicFilters);
						filterUnSelectedAttrs(searchResultApp);
						//移除选择所有商品结果
						searchResultApp.setProductList(new ArrayList<Product>());
					}
				} else {
					searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "5", searchParam.getCategoryId(), null), "3");
				}
				json = ApiBizData.spliceData(searchResultApp, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
//				System.out.println("json========="+json);
				return json;
			} catch (UnsupportedEncodingException e) {
				logger.error("调用base接口返回数据发生错误！");
				e.printStackTrace();
			} catch (DocumentException e) {
				logger.error("调用base接口返回数据发生错误,xml解析错误！");
				e.printStackTrace();
			} catch (Exception e) {
				logger.error("调用api接口返回数据发生错误！");
				e.printStackTrace();
			}
		}
		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}

	/**
	 * 过滤非选择数据
	 * @param searchResultApp
	 */
	private void filterUnSelectedAttrs(SearchResultApp searchResultApp) {
		searchResultApp.setCount("0");
		List<SearchAttribute> allAttrs = searchResultApp.getAttributes();
		if(CollectionUtils.isNotEmpty(allAttrs)) {
			Iterator<SearchAttribute> attrIt = allAttrs.iterator();
			while(attrIt.hasNext()) {
				if(!hasSelectedValue(attrIt.next())) {
					attrIt.remove();
				}
			}
		}
	}

	private boolean hasSelectedValue(SearchAttribute attr) {
		boolean hasChecked = false;
		List<SearchAttributeGroup> attrGroups = attr.getGroup();
		if(CollectionUtils.isNotEmpty(attrGroups)) {
			for(SearchAttributeGroup group : attrGroups) {
				List<SearchAttributeValue> attrVals = group.getValue();
				if(CollectionUtils.isNotEmpty(attrVals)) {
					for(SearchAttributeValue value : attrVals){
						if("1".equals(value.getIsChecked())){
							hasChecked = true;
							return hasChecked;
						}
					}
				}
				
			}
		}
		return hasChecked;
	}

	@Override
	public String queryLabels(String pageIndex, String pageSize, String userLv, String tagId, String order, String filters, String type, String imei) throws Exception {
		SearchResultApp searchResultApp = new SearchResultApp();
		SearchParam searchParam = SearchParamUtil.parse(filters, "6");
		String area = searchParam.getPostArea();
		area = StringUtils.isEmpty(area) ? "0" : area;
		String xmlStr = searchService.searchCategoryOperations(pageIndex, pageSize, userLv, searchParam.getPriceId(), searchParam.getSizeId(), searchParam.getColorId(), tagId,
				searchParam.getCategoryId(), area, searchParam.getBrandId(), order, type, imei);
		if (!StringUtils.isEmpty(xmlStr)) {
			searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "2", searchParam.getCategoryId(), null), "4");
			searchResultApp.setLabelId(tagId);
			return ApiBizData.spliceData(searchResultApp, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
		}
		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}
	
	@Override
	public String queryLabels(String pageIndex, String pageSize, String userLv, String tagId, String order, String filters, String originalFilters, String dynamicFilters, String type, String imei, String version) throws Exception {

		SearchResultApp searchResultApp = new SearchResultApp();
		SearchParam searchParam = new SearchParam();
		if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.7", version) == 1) {
			searchParam = SearchParamUtil.parse(originalFilters, dynamicFilters, "6");
		} else {
			searchParam = SearchParamUtil.parse(filters, "6");
		}
		String area = searchParam.getPostArea();
		String xmlStr = searchService.searchCategoryOperations(pageIndex, pageSize, userLv, searchParam.getPriceId(), searchParam.getSizeId(), searchParam.getColorId(), tagId,
				searchParam.getCategoryId(), area, searchParam.getBrandId(), order, type, imei);
		if (!StringUtils.isEmpty(xmlStr)) {
			if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.7", version) == 1) {
				searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "2", searchParam.getCategoryId(), null), "4", originalFilters, dynamicFilters);
				if(CollectionUtils.isEmpty(searchResultApp.getProductList())) {
					//排除价格区间和发货地获取筛选条件
					xmlStr = searchService.searchCategoryOperations(pageIndex, pageSize, userLv, null, searchParam.getSizeId(), searchParam.getColorId(), tagId,
							searchParam.getCategoryId(), null, searchParam.getBrandId(), order, type, imei);
					searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "2", searchParam.getCategoryId(), null), "4", originalFilters, dynamicFilters);
					filterUnSelectedAttrs(searchResultApp);
					searchResultApp.setProductList(new ArrayList<Product>());
				}
			} else {
				searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "2", searchParam.getCategoryId(), null), "4");
			}
			
			searchResultApp.setLabelId(tagId);
			return ApiBizData.spliceData(searchResultApp, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
		}
		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}

	@Override
	public RecProductFor searchProductNos(String start, String end, String minPrice,
			String maxPrice, String postArea, String includeBrandNo,
			String excludeBrandNo, String includeCategoryNo,
			String excludeCategoryNo, String includeProductNo,
			String excludeProductNo, String userLv) {
		String jsonStr = couponsService.useCouponProductList(start, end, minPrice, maxPrice, postArea, includeBrandNo, excludeBrandNo, includeCategoryNo, excludeCategoryNo, includeProductNo, excludeProductNo, userLv);
		CouponFilterProductResultModel couponFilterProductResult=JsonUtil.fromJson(jsonStr, CouponFilterProductResultModel.class);
		return  getCouponProductList(couponFilterProductResult);
	}

	private RecProductFor getCouponProductList(CouponFilterProductResultModel couponFilterProductResultModel){
		
		if(couponFilterProductResultModel==null){
			return null;
		}
		CouponFilterProductResult couponFilterProductResult =couponFilterProductResultModel.getCouponFilterProductResultModel();
		if(couponFilterProductResult==null||couponFilterProductResult.getDocs()==null||couponFilterProductResult.getDocs().size()<=0){
			return null;
		}
		List<Product> list=new ArrayList<Product>();
		RecProductFor  recProductFor=new RecProductFor();
		
		recProductFor.setRecommendNum(couponFilterProductResult.getTotal());
		recProductFor.setSystime("");
		for(int i=0;i<couponFilterProductResult.getDocs().size();i++){
			Product productDocs=couponFilterProductResult.getDocs().get(i);
			Product product=new Product();
			//ERP 编号
			product.setErpCategoryNo(productDocs.getErpCategoryNo()==null?"":productDocs.getErpCategoryNo());
			
			product.setPrefix(productDocs.getPrefix()==null?"":productDocs.getPrefix());
			product.setSuffix(productDocs.getSuffix()==null?"":productDocs.getSuffix());
			product.setProductId(productDocs.getProductNo()==null?"":productDocs.getProductNo());
			product.setBrandNameEN(productDocs.getBrandEnName()==null?"":productDocs.getBrandEnName());
			product.setBrandNameCN(productDocs.getBrandCnName()==null?"":productDocs.getBrandCnName());
			product.setProductName(productDocs.getProductName()==null?"":productDocs.getProductName());
			product.setBrandNo(productDocs.getBrandNo()==null?"":productDocs.getBrandNo());
			product.setAdvertWord("");
			product.setName(productDocs.getProductName()==null?"":productDocs.getProductName());
			product.setMarketPrice(productDocs.getMarketPrice()==null?"":productDocs.getMarketPrice());
			product.setLimitedPrice(productDocs.getLimitedPrice()==null?"":productDocs.getLimitedPrice());
			product.setGoldPrice(productDocs.getSellPrice()==null?"":productDocs.getSellPrice());
			product.setPlatinumPrice(productDocs.getPlatinumPrice()==null?"":productDocs.getPlatinumPrice());
			product.setDiamondPrice(productDocs.getDiamondPrice()==null?"":productDocs.getDiamondPrice());
			product.setIsSupportDiscount(productDocs.getIsSupportDiscount()==null?"":productDocs.getIsSupportDiscount());
			product.setLimitedVipPrice("");
			product.setPromotionPrice(productDocs.getPromotionPrice()==null?"":productDocs.getPromotionPrice());
			product.setPromotionDesc("");
			product.setPromotionNotice(productDocs.getPromotionNotice()==null?"":productDocs.getPromotionNotice());
			product.setPostArea(productDocs.getMerchantType()==null?"":productDocs.getMerchantType());
			product.setPostAreaPic(productDocs.getPostAreaPic()==null?"":productDocs.getPostAreaPic());
			product.setCount(productDocs.getAvailableStock()==null?"":productDocs.getAvailableStock());
			product.setComments("");
			product.setCollections("");
			String picFile=productDocs.getProductPicFile();
			if(!StringUtils.isEmpty(picFile)){
				product.setPic(PicCdnHash.getPicUrl(picFile, "1"));
			}
			
			product.setPicNo(picFile==null?"":picFile);
			product.setProductModelPicFile(productDocs.getProductModelPicFile()==null?"":productDocs.getProductModelPicFile());
			product.setCountryPic("");
			List<Tag> tag=new ArrayList<Tag>();
			product.setTag(tag);
			product.setWidth("");
			product.setHeight("");
			product.setStatus("100000");
			list.add(product);
		}
		recProductFor.setList(list);
		return recProductFor;
		
	}
/*
	@Override
	public List<SearchSubjectFloor> queryActivityProductListFloor(String topicId, String postArea, String userLv,ActivityHead activityHead) {
		List<SearchSubjectFloor> productList =null;
		final String REDISKEY = "Activity_ProductList_Floor:"+topicId; 
		final int REDISTIME = 600;
		JedisUtil jedisUtil = JedisUtilFactory.getDefaultJedisUtil();
		String redisVal = jedisUtil.get(REDISKEY);
		String data;
		try {
			if(redisVal==null){
				data = searchService.searchActivityProductListFloor(topicId,postArea,userLv,activityHead.getIsPre()) ;
				logger.debug("调用base接口返回数据:" + data);
			}else{
				data =redisVal;
			}
			if (!StringUtils.isEmpty(data)) {
				JSONObject obj = JSONObject.fromObject(data);
				String result  = obj.getString("result");
				if(obj!=null){
					SearchProductJson<SearchSubjectFloor> productJson = JsonUtil.fromJson(result, new TypeToken<SearchProductJson<SearchSubjectFloor>>(){}.getType());
					if(productJson!=null){
						logger.debug("searchProductListFloor   result:{description:"+productJson.getDiscription()+", qtime:"+productJson.getQtime()+", status:"+productJson.getStatus()+" ,total:"+productJson.getTotal()+" ,sid:"+productJson.getSid()+" ,dosCount:"+productJson.getDocs().size());
						//查询结果大于0
						productList= productJson.getDocs();
						if(productJson.getTotal()>0&&productList!=null && productList.size()>0){
							//将搜索的数据放入缓存
							jedisUtil.set(REDISKEY, data);
							jedisUtil.expire(REDISKEY, REDISTIME);
							
							for (SearchSubjectFloor searchSubject : productList) {
								List<ProductSreach> productSreachList = searchSubject.getSearchList();
								if(productSreachList!=null && productSreachList.size()>0){
									List<Product> initJsonProductList = SearchUtil.initJsonProductList(productSreachList, "1", activityHead);
									searchSubject.setProductList(initJsonProductList);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("解析搜索Floor数据发生错误！");
			e.printStackTrace();
		}
		return productList;
	}*/

	@Override
	public List<Product> getProductListFromProductNo(List<String> spus, Map<String, String> productAttMap,ActivityHead activityHead) {
		List<List<String>> spuList = new ArrayList<>();
		List<String> spuSearch = new ArrayList<>();
		int count =1;
		for (int i = 0; i < spus.size(); i++) {
			spuSearch.add(spus.get(i));
			if(count==20||i==spus.size()-1){
				spuList.add(spuSearch);
				count=1;
				spuSearch = new ArrayList<>();
			}
			count++;
		}
		List<String> results = new ArrayList<>();
		for (List<String> list : spuList) {
			String data = searchService.searchProductList(list);
			results.add(data);
		}
		List<ProductSreach> searchList = new ArrayList<>();
		for (String data : results) {
			if (org.apache.commons.lang3.StringUtils.isNotBlank(data)) {
					SearchProductJson<ProductSreach> productJson = JsonUtil.fromJson(data, new TypeToken<SearchProductJson<ProductSreach>>(){}.getType());
					if(productJson!=null){
						logger.debug("searchProductListFloor   result:{description:"+productJson.getDiscription()+", qtime:"+productJson.getQTime()+", status:"+productJson.getStatus()+" ,total:"+productJson.getTotal()+" ,sid:"+productJson.getSid()+" ,dosCount:"+productJson.getDocs().size());
						//查询结果大于0
						List<ProductSreach> searchListDoc = productJson.getDocs();
						if(productJson.getTotal()>0 && searchListDoc!=null && searchListDoc.size()>0){
							for (ProductSreach productSreach : searchListDoc) {
								productSreach.setSort(productAttMap.get(productSreach.getProductNo()));
							}
							searchList.addAll(searchListDoc);
						}
					}
			}
		}
		Collections.sort(searchList, new Comparator<ProductSreach>() {
			@Override
			public int compare(ProductSreach ps1, ProductSreach ps2) {
				if(ps1.getSort()==null){
					return -1;
				}else if(ps2.getSort() ==null){
					return 1;
				}else{
					return Integer.parseInt(ps1.getSort())-Integer.parseInt(ps2.getSort());
				}
			}
		});
		List<Product> productList = SearchUtil.initJsonProductList(searchList, "1", activityHead);
		return productList;
	}

}
