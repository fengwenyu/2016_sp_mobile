package com.shangpin.biz.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shangpin.base.service.SearchService;
import com.shangpin.biz.bo.SearchParam;
import com.shangpin.biz.bo.SearchProductResult;
import com.shangpin.biz.bo.SearchResult;
import com.shangpin.biz.bo.SearchType;
import com.shangpin.biz.service.SPBizSearchService;
import com.shangpin.biz.utils.SearchParamUtil;
import com.shangpin.biz.utils.SearchUtil;

@Service
public class SPBizSearchServiceImpl implements SPBizSearchService {
	private static final Logger logger = LoggerFactory.getLogger(SPBizSearchServiceImpl.class);
	@Autowired
	SearchService searchService;

	@Override
	public SearchProductResult searchProductList(String keyword,String navId, String start, String end,String tagId, String brandNo, String price, String color, String size, String categoryNo, String order, String userLv, SearchType searchType, String postArea) {
		try {
			String xml = searchService.searchProductListByKeyWord(keyword, start, end,tagId, brandNo, price, color, size, categoryNo, order, userLv, postArea);
			SearchUtil searchUtil = new SearchUtil();
			return searchUtil.initSearchResult(xml, userLv,searchType);

		} catch (Exception e) {
			logger.error("调用base搜索商品列表接口返回数据错误!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SearchProductResult newProductList(String keyword,String navId, String start, String end, String brandNo, String price, String color, String size, String categoryNo, String order, String gender, String userLv, SearchType searchType, String postArea) {
		String xml = searchService.newProductList(keyword,navId, start, end, brandNo, price, color, size, categoryNo, order,gender, userLv, postArea);
		SearchUtil searchUtil = new SearchUtil();
		return searchUtil.initSearchResult(xml, userLv,searchType);
	}

	@Override
	public SearchResult queryLabels(String pageIndex, String pageSize,
			String userLv, String tagId, String order, String filters,
			String type) throws Exception {
		SearchParam searchParam = SearchParamUtil.parse(filters,"6");
		String area = searchParam.getPostArea();
		area = StringUtils.isEmpty(area) ? "0" : area;
		String xmlStr = searchService.searchCategoryOperations(pageIndex, pageSize, userLv, searchParam.getPriceId(), searchParam.getSizeId(), searchParam.getColorId(), tagId,
				searchParam.getCategoryId(), area, searchParam.getBrandId(), order, type, "");
		if (!StringUtils.isEmpty(xmlStr)) {
			SearchResult searchResult=SearchUtil.converXmlToObj(xmlStr, "2",searchParam.getCategoryId(),null);
			return searchResult;
		}
		return null;
	}


	/*@Override
	public Map<String,Object> queryLabels(String pageIndex, String pageSize,
			String userLv, String tagId, String order, String filters,
			String type) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		SearchResultApp searchResultApp = new SearchResultApp();
		SearchParam searchParam = SearchParamUtil.parse(filters,"6");
		String area = searchParam.getPostArea();
		area = StringUtils.isEmpty(area) ? "0" : area;
		String xmlStr = searchService.searchCategoryOperations(pageIndex, pageSize, userLv, searchParam.getPriceId(), searchParam.getSizeId(), searchParam.getColorId(), tagId,
				searchParam.getCategoryId(), area, searchParam.getBrandId(), order, type);
		if (!StringUtils.isEmpty(xmlStr)) {
				searchResultApp = SearchUtil.searchByApp(SearchUtil.converXmlToObj(xmlStr, "2",searchParam.getCategoryId()),"4");
				searchResultApp.setLabelId(tagId);
		}
		map.put("searchResultApp",searchResultApp);
		map.put("searchParam",searchParam);
		return map;
	}*/

}
