package com.shangpin.wireless.api.api2client.domain;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.vo.SearchFacetCategoryItemVO;
import com.shangpin.wireless.api.vo.SearchFacetCategoryVO;
import com.shangpin.wireless.api.vo.SearchFacetColorItemVO;
import com.shangpin.wireless.api.vo.SearchFacetBrandItemVO;
import com.shangpin.wireless.api.vo.SearchFacetBrandVO;
import com.shangpin.wireless.api.vo.SearchFacetColorVO;
import com.shangpin.wireless.api.vo.SearchFacetPriceItemVO;
import com.shangpin.wireless.api.vo.SearchFacetPriceVO;
import com.shangpin.wireless.api.vo.SearchFacetSizeItemVO;
import com.shangpin.wireless.api.vo.SearchFacetSizeVO;
import com.shangpin.wireless.api.vo.SearchFacetsVO;
import com.shangpin.wireless.api.vo.SearchProductVO;

public class SPGoodListAPIResponse extends CommonAPIResponse {

	private String searchCategory;
	private SearchFacetsVO facets;
	private List<SearchProductVO> productList;
//	private String sorttype;
//	private String sortvalue;
	private String sort;
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	
	public SearchFacetsVO getFacets() {
		return facets;
	}

	public void setFacets(SearchFacetsVO facets) {
		this.facets = facets;
	}

	public List<SearchProductVO> getProductList() {
		return productList;
	}

	public void setProductList(List<SearchProductVO> productList) {
		this.productList = productList;
	}

	/**
	 * 返给客户端的json数据
	 * 
	 * @Author: wangwenguan
	 * @CreatDate: 2013-12-28
	 * @param type: category,new,brand,flagship,subject
	 * @Return
	 */
	public String obj2Json(String type) {
		JSONObject obj = new JSONObject();
		obj.put("code", getCode());
		obj.put("msg", getMsg());
		JSONObject content = new JSONObject();
		
		if (Constants.SUCCESS.equals(getCode())) {
			if(sort != null && !"".equals(sort)){
				String[] orderType = sort.split(":");
				if(orderType != null){
					if(orderType.length > 1){
						content.put("sortvalue", orderType[1]);
					}else{
						content.put("sortvalue", "");
					}
					content.put("sorttype", orderType[0]);
				}
			}
			
			if(facets != null){
				JSONArray list = new JSONArray();
				JSONObject facet = new JSONObject();
				JSONArray facetitems = new JSONArray();
				JSONObject facetitem = new JSONObject();
				if ("category".equals(type)) {
					if(searchCategory != null){
						if(searchCategory.length() == 9){
							SearchFacetCategoryVO cate4 = facets.getSearchFacetCategoryL4VO();
							if(cate4 != null){
								List<SearchFacetCategoryItemVO> item4 = cate4.getSearchCategoryItems();
								if(item4 != null && item4.size() > 0){
									//4级不为空 返回 3级和四级
									facetitems = getCategory3And4Array();
								}else{
									facetitems = getCategory2And3Array();
								}
							}else{
								facetitems = getCategory2And3Array();
							}
						}else if(searchCategory.length() == 12){//查询的是4级的 返回 3级和四级
							facetitems = getCategory3And4Array();
						}else if(searchCategory.length() == 6){//查询的是2级的  返回2级和3级 
							//没有四级的话 返回  3级  和  2级
							facetitems = getCategory2And3Array();
						
						}
					}
					facet.put("key", "categoryid");
					facet.put("name", "品类");
					facet.put("value", facetitems);
					list.add(facet);
				} else if ("new".equals(type)) {
					SearchFacetCategoryVO category = facets.getSearchFacetCategoryL2VO();
					facet.put("key", category.getFilterKey());
					facet.put("name", "品类");
					String filtercategory = (searchCategory!=null && searchCategory.length()>=3) ? searchCategory.substring(0, 3) : "";
					List<SearchFacetCategoryItemVO> categoryitems = category.getSearchCategoryItems();
					for (int i=0;i<categoryitems.size();i++) {
						SearchFacetCategoryItemVO item = categoryitems.get(i);
						if ("1".equals(item.getStatus()) && item.getCategoryNo().startsWith(filtercategory)) {
							facetitem.put("id", item.getCategoryNo());
							facetitem.put("name", item.getCategoryName());
							facetitems.add(facetitem);
						}
					}
					if (facetitems.size() > 0) {
						facetitem.put("id", filtercategory);
						facetitem.put("name", "全部");
						facetitems.add(0, facetitem);
						facet.put("value", facetitems);
						list.add(facet);
					}else{
						facet.put("value", new JSONArray());
						list.add(facet);
					}
				} else if ("brand".equals(type)) {
					SearchFacetCategoryVO navigation = facets.getSearchFacetNavigationVO();
					if (navigation != null) {	//	导航
						facet.put("key", "navid");
						facet.put("name", "分类");
						
						List<SearchFacetCategoryItemVO> categoryitems = navigation.getSearchCategoryItems();
						for (int i=0;i<categoryitems.size();i++) {
							SearchFacetCategoryItemVO item = categoryitems.get(i);

							facetitem.put("id", item.getCategoryNo());
							facetitem.put("name", item.getCategoryName());
							facetitems.add(facetitem);
						}
						if (facetitems.size() > 0) {
							facetitem.put("id", "");
							facetitem.put("name", "全部");
							facetitems.add(0, facetitem);
							facet.put("value", facetitems);
							list.add(facet);
						}else{
							facet.put("value", new JSONArray());
							list.add(facet);
						}
					} else {	//	分类
						//JSONArray facetL2Items = new JSONArray();
						//JSONObject facetL2Item = new JSONObject();
						//	二级分类
						SearchFacetCategoryVO categoryL2 = facets.getSearchFacetCategoryL2VO();
						facet.put("key", categoryL2.getFilterKey());
						facet.put("name", "品类");
						List<SearchFacetCategoryItemVO> categoryitems = categoryL2.getSearchCategoryItems();
						for (int i=0;i<categoryitems.size();i++) {
							SearchFacetCategoryItemVO item = categoryitems.get(i);
							if ("1".equals(item.getStatus())) {
								String categoryNoL2 = item.getCategoryNo();
								String categoryNameL2 = item.getCategoryName();
								facetitem.put("pid", item.getParentNo());
								facetitem.put("id", categoryNoL2);
								facetitem.put("name", categoryNameL2);

								JSONArray facetL3Items = new JSONArray();
								JSONObject facetL3Item = new JSONObject();
								//	三级分类
								SearchFacetCategoryVO categoryL3 = facets.getSearchFacetCategoryL3VO();
								facet.put("key", categoryL3.getFilterKey());
								facet.put("name", "品类");
								List<SearchFacetCategoryItemVO> categoryL3Items = categoryL3.getSearchCategoryItems();
								for (int j=0;j<categoryL3Items.size();j++) {
									SearchFacetCategoryItemVO l3item = categoryL3Items.get(j);
									if ("1".equals(l3item.getStatus()) && l3item.getCategoryNo().startsWith(categoryNoL2)) {
										facetL3Item.put("id", l3item.getCategoryNo());
										facetL3Item.put("name", l3item.getCategoryName());
										facetL3Items.add(facetL3Item);
									}
								}
								if (facetL3Items.size() > 0) {
									facetL3Item.put("id", categoryNoL2);
									facetL3Item.put("name", "全部" + categoryNameL2);
									facetL3Items.add(0, facetL3Item);
									facetitem.put("value", facetL3Items);
								}
								facetitems.add(facetitem);
								//facetL2Item.put("id", categoryNoL2);
								//facetL2Item.put("name", categoryNameL2);
								//facetL2Items.add(facetL2Item);
							}
						}
						if (facetitems.size() > 0) {
							facet.put("value", facetitems);
							list.add(facet);
						}else{
							facet.put("value", new JSONArray());
							list.add(facet);
						}
					}
				}
				
				if (!"brand".equals(type)) {
					facet = new JSONObject();
					facetitems = new JSONArray();
					facetitem = new JSONObject();
					SearchFacetBrandVO brand = facets.getSearchFacetBrandVO();
					facet.put("key", brand.getFilterKey());
					facet.put("name", "品牌");
					List<SearchFacetBrandItemVO> branditems = brand.getSearchFacetBrandItems();
					for (int i=0;i<branditems.size();i++) {
						SearchFacetBrandItemVO item = branditems.get(i);
						facetitem.put("id", item.getBrandId());
						facetitem.put("name", item.getBrandEnName());
						facetitems.add(facetitem);
					}
					if (facetitems.size() > 0) {
						facetitem.put("id", "");
						facetitem.put("name", "全部品牌");
						facetitems.add(0, facetitem);
						facet.put("value", facetitems);
						list.add(facet);
					}else{
						facet.put("value", new JSONArray());
						list.add(facet);
					}
				}
				
				facet = new JSONObject();
				facetitems = new JSONArray();
				facetitem = new JSONObject();
				SearchFacetPriceVO price = facets.getSearchFacetPriceVO();
				facet.put("key", price.getFilterKey());
				facet.put("name", "价格区间");
				List<SearchFacetPriceItemVO> priceitems = price.getSearchFacetPriceItems();
				for (int i=0;i<priceitems.size();i++) {
					SearchFacetPriceItemVO item = priceitems.get(i);
					facetitem.put("id", item.getAmong());
					facetitem.put("name", (item.getAmong().indexOf("~")>0)?(item.getAmong() + "元"):(item.getAmong() + "元以上"));
					facetitems.add(facetitem);
				}
				if (facetitems.size() > 0) {
					facetitem.put("id", "");
					facetitem.put("name", "全部");
					facetitems.add(0, facetitem);
					facet.put("value", facetitems);
					list.add(facet);
				}
				
				facet = new JSONObject();
				facetitems = new JSONArray();
				facetitem = new JSONObject();
				SearchFacetSizeVO size = facets.getSearchFacetSizeVO();
				facet.put("key", size.getFilterKey());
				facet.put("name", "尺码");
				List<SearchFacetSizeItemVO> sizeitems = size.getSearchFacetSizeItems();
				for (int i=0;i<sizeitems.size();i++) {
					SearchFacetSizeItemVO item = sizeitems.get(i);
					facetitem.put("id", item.getSizeCode());
					facetitem.put("name", item.getSizeCode());
					facetitems.add(facetitem);
				}
				if (facetitems.size() > 0) {
					facetitem.put("id", "");
					facetitem.put("name", "全部尺码");
					facetitems.add(0, facetitem);
					facet.put("value", facetitems);
					list.add(facet);
				}
				
				facet = new JSONObject();
				facetitems = new JSONArray();
				facetitem = new JSONObject();
				SearchFacetColorVO color = facets.getSearchFacetColorVO();
				facet.put("key", color.getFilterKey());
				facet.put("name", "颜色");
				List<SearchFacetColorItemVO> coloritems = color.getSearchFacetColorItems();
				for (int i=0;i<coloritems.size();i++) {
					SearchFacetColorItemVO item = coloritems.get(i);
					facetitem.put("id", item.getColorId());
					facetitem.put("name", item.getColorName());
					facetitems.add(facetitem);
				}
				if (facetitems.size() > 0) {
					facetitem.put("id", "");
					facetitem.put("name", "全部颜色");
					facetitems.add(0, facetitem);
					facet.put("value", facetitems);
					list.add(facet);
				}
				
				content.put("filterlist", list);
			}
			if(productList!=null && productList.size()>0){
				JSONArray list = new JSONArray();
				JSONArray pics = null;
				JSONArray prices = null;
				JSONObject product = null;
				JSONArray specialprice = null;
				for(int i=0;i<productList.size();i++){
					SearchProductVO searchProduct = productList.get(i);
					product = new JSONObject();
					pics = new JSONArray();
					pics.add(searchProduct.getProductPicUrl());
					pics.add(searchProduct.getProductModelPicUrl());
					product.put("pics", pics);
					product.put("count",  searchProduct.getAvailableStock());
					product.put("productid", searchProduct.getProductNo());
					product.put("productname", searchProduct.getProductName());
					prices = new JSONArray();
					prices.add(searchProduct.getLimitedPrice().substring(0, searchProduct.getLimitedPrice().length()-3));
					prices.add(searchProduct.getSellPrice().substring(0, searchProduct.getSellPrice().length()-3));
					prices.add(searchProduct.getPlatinumPrice().substring(0, searchProduct.getPlatinumPrice().length()-3));
					prices.add(searchProduct.getDiamondPrice().substring(0, searchProduct.getDiamondPrice().length()-3));
					prices.add(searchProduct.getMarketPrice().substring(0, searchProduct.getMarketPrice().length()-3));
					product.put("prices", prices);
					//这个值以后不用了 写死为100000 普通
//					product.put("status", searchProduct.getProductShowFlag());
					product.put("status", "100000");
					product.put("brandname", searchProduct.getBrandEnName());
					product.put("brandid", searchProduct.getBrandNo());
					product.put("issupportmember", searchProduct.getIsSupportDiscount());
					specialprice = new JSONArray();
//					specialprice.add(searchProduct.getPromotionPrice().substring(0, searchProduct.getPromotionPrice().length()-3));
					product.put("specialprice", specialprice);
					list.add(product);
				}
				content.put("productlist", list);
			}else{
				content.put("productlist", new JSONArray());
			}
		}
		obj.put("content", content);
		return obj.toString();
	}
	/**
	 * 返回3级和4级品类
	 * @return
	 */
	private JSONArray getCategory3And4Array() {
		JSONArray facetitems = new JSONArray();
		SearchFacetCategoryVO cate4 = facets.getSearchFacetCategoryL4VO();
		if(cate4 != null){
			List<SearchFacetCategoryItemVO> item4 = cate4.getSearchCategoryItems();
			String target = searchCategory.substring(0, 9);
			if(item4 != null && item4.size() > 0){
				//返回3级和四级
				SearchFacetCategoryVO cate3 = facets.getSearchFacetCategoryL3VO();
				if(cate3 != null && cate3.getSearchCategoryItems() != null && cate3.getSearchCategoryItems().size() > 0){
					List<SearchFacetCategoryItemVO> item3 = cate3.getSearchCategoryItems();
					for(int i = 0; i < item3.size(); i++){
						if(target.equals(item3.get(i).getCategoryNo())){
							JSONObject facetitem = new JSONObject();
							facetitem.put("id", item3.get(i).getCategoryNo());
							facetitem.put("name", "全部" + item3.get(i).getCategoryName());
							facetitems.add(facetitem);
							break;
						}
					}
				}
				for(SearchFacetCategoryItemVO item : item4){
					String cateNO = item.getCategoryNo();
					if(cateNO.startsWith(target)){
						JSONObject facetitem = new JSONObject();
						facetitem.put("id", item.getCategoryNo());
						facetitem.put("name", item.getCategoryName());
						facetitems.add(facetitem);
					}
				}
			}
		}
		return facetitems;
	}

	/**
	 * 返回2级和3级品类
	 * @return
	 */
	private JSONArray getCategory2And3Array(){
		SearchFacetCategoryVO cate2 = facets.getSearchFacetCategoryL2VO();
		JSONArray facetitems = new JSONArray();
		if(cate2 != null){
			String startTarget = searchCategory.substring(0, 6);
			List<SearchFacetCategoryItemVO> item2 = cate2.getSearchCategoryItems();
			if(item2 != null && item2.size() > 0){
				for(int i =0 ;i < item2.size(); i++){
					SearchFacetCategoryItemVO obj = item2.get(i);
					String cateNo = obj.getCategoryNo();
					if(cateNo.startsWith(startTarget)){
						JSONObject facetitem = new JSONObject();
						facetitem.put("id", cateNo);
						facetitem.put("name", "全部" + obj.getCategoryName());
						facetitems.add(facetitem);
						break;
					}
				}
			}
			//返回3级
			SearchFacetCategoryVO cate3 = facets.getSearchFacetCategoryL3VO();
			if(cate3 != null){
				List<SearchFacetCategoryItemVO> item3 = cate3.getSearchCategoryItems();
				if(item3 != null && item3.size() > 0){
					for(SearchFacetCategoryItemVO item : item3){
						String cateNo = item.getCategoryNo();
						if(cateNo.startsWith(startTarget)){
							JSONObject facetitem = new JSONObject();
							facetitem.put("id", cateNo);
							facetitem.put("name", item.getCategoryName());
							facetitems.add(facetitem);
						}
					}
				}
			}
		}
		return facetitems;
	}
}
