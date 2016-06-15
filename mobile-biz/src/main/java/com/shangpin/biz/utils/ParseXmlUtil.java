package com.shangpin.biz.utils;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.SearchBrand;
import com.shangpin.biz.bo.SearchCategory;
import com.shangpin.biz.bo.SearchColor;
import com.shangpin.biz.bo.SearchFacet;
import com.shangpin.biz.bo.SearchFacetBrand;
import com.shangpin.biz.bo.SearchFacetCategory;
import com.shangpin.biz.bo.SearchFacetColor;
import com.shangpin.biz.bo.SearchFacetHotWords;
import com.shangpin.biz.bo.SearchFacetPrice;
import com.shangpin.biz.bo.SearchFacetSex;
import com.shangpin.biz.bo.SearchFacetSize;
import com.shangpin.biz.bo.SearchHotWords;
import com.shangpin.biz.bo.SearchPrice;
import com.shangpin.biz.bo.SearchProduct;
import com.shangpin.biz.bo.SearchSize;
import com.shangpin.biz.bo.Sex;

/** 
* @ClassName: ParseXmlUtil 
* @Description: 解析主站返回的商品列表xml
* @author qinyingchun
* @date 2014年10月27日
* @version 1.0
*/
public class ParseXmlUtil {
	private static final Logger logger = LoggerFactory.getLogger(ParseXmlUtil.class);
	
	@SuppressWarnings("unchecked")
	public static SearchProduct initProducts(String xml){
		SAXReader reader = new SAXReader();
		Document document = null;
		SearchProduct searchProduct = null;
		SearchFacet searchFacet = null;
		List<Product> products = null;
		try {
			document = reader.read(new ByteArrayInputStream((xml.getBytes("UTF-8"))));//
			Element root = document.getRootElement();
			logger.info("根节点为：" + root.getName());
			searchProduct = new SearchProduct();
			String sid = root.element("SID").getText();
			String status = root.elementText("Status");
			String discription = root.elementText("Discription");
			String qtime = root.elementText("QTime");
			String total = root.elementText("Total");
			String start = root.elementText("Start");
			String end = root.elementText("End");
			searchProduct.setSid(sid);
			searchProduct.setStatus(status);
			searchProduct.setDiscription(discription);
			searchProduct.setQtime(qtime);
			searchProduct.setTotal(Integer.parseInt(total));
			searchProduct.setStart(start);
			searchProduct.setEnd(end);
			logger.debug("开始解析查询条件....................................");
			Element facetsElement = root.element("facets");
			List<Element> facets = facetsElement.elements();
			searchFacet = new SearchFacet();
			for(Element facet : facets){
				String name = facet.attribute("name").getValue();
				System.out.println("name:" + name);
				if("Brand".equals(name)){
					SearchFacetBrand facetBrand = new SearchFacetBrand();
					List<SearchBrand> brands = new ArrayList<SearchBrand>();
					facetBrand.setName("Brand");
					List<Element> brandItems = facet.elements();
					for(Element brandItem : brandItems){
						SearchBrand brand = new SearchBrand();
						brand.setCount(brandItem.getText());
						String str = brandItem.attribute("name").getValue();
						String[] brandStr = str.split("\\|");
						brand.setId(brandStr[0]);
						brand.setNameEN(brandStr[1]);
						brand.setNameCN(brandStr[2]);
						brand.setPicNo(brandStr[3]);
						brands.add(brand);
					}
					facetBrand.setSearchBrands(brands);
					searchFacet.setSearchFacetBrand(facetBrand);
				}else if ("hotwords".equals(name)) {
                    SearchFacetHotWords facetHotWords= new SearchFacetHotWords();
                    List<SearchHotWords> searchHotWords = new ArrayList<SearchHotWords>();
                    facetHotWords.setName("hotwords");
                    List<Element> hotwordsItems = facet.elements();
                    for(Element hotwordsItem : hotwordsItems){
                        SearchHotWords hotWords = new SearchHotWords();
                        String str = hotwordsItem.attributeValue("word");
                        String[] hotWordsStr = str.split("\\|");                        
                        hotWords.setId(hotWordsStr[0]);
                        hotWords.setName(hotWordsStr[1]);
                        hotWords.setType(hotWordsStr[2]);
                        searchHotWords.add(hotWords);
                    }
                    facetHotWords.setSearchHotWords(searchHotWords);
                    searchFacet.setSearchFacetHotWords(facetHotWords);
                }else if ("ProductPrimaryColors".equals(name)) {
					SearchFacetColor facetColor = new SearchFacetColor();
					List<SearchColor> searchColors = new ArrayList<SearchColor>();
					facetColor.setName("ProductPrimaryColors");
					List<Element> colorItems = facet.elements();
					for(Element colorItem : colorItems){
						SearchColor color = new SearchColor();
						color.setCount(colorItem.getText());
						String str = colorItem.attributeValue("name");
						String[] colorStr = str.split("\\|");
						color.setId(colorStr[0]);
						color.setName(colorStr[1]);
						color.setRgb(colorStr[2]);
						searchColors.add(color);
					}
					facetColor.setSearchColors(searchColors);
					searchFacet.setSearchFacetColor(facetColor);
				}else if ("ProductSize".equals(name)) {
					SearchFacetSize facetSize = new SearchFacetSize();
					List<SearchSize> searchSizes = new ArrayList<SearchSize>();
					facetSize.setName("ProductSize");
					List<Element> sizeItems = facet.elements();
					for(Element sizeItem : sizeItems){
						SearchSize searchSize = new SearchSize();
						searchSize.setCount(sizeItem.getText());
						String str = sizeItem.attributeValue("name");
						searchSize.setSizeCode(str);
						searchSizes.add(searchSize);
					}
					facetSize.setSearchSizes(searchSizes);
					searchFacet.setSearchFacetSize(facetSize);
				}else if ("LimitedPrice".equals(name)) {
					SearchFacetPrice facetPrice = new SearchFacetPrice();
					List<SearchPrice> searchPrices = new ArrayList<SearchPrice>();
					facetPrice.setName("LimitedPrice");
					List<Element> priceItems = facet.elements();
					for(Element priceItem : priceItems){
						SearchPrice price = new SearchPrice();
						price.setCount(priceItem.getText());
						String str = priceItem.attributeValue("name");
						price.setAmong(str);
						searchPrices.add(price);
					}
					facetPrice.setSearchPrices(searchPrices);
					searchFacet.setSearchFacetPrice(facetPrice);
				}else if("CLv2".equals(name)){
					SearchFacetCategory facetCategory = new SearchFacetCategory();
					List<SearchCategory> categories = new ArrayList<SearchCategory>();
					facetCategory.setName("CLv2");
					List<Element> categoryItems = facet.elements();
					for(Element categoryItem : categoryItems){
						SearchCategory category = new SearchCategory();
						category.setCount(categoryItem.getText());
						String str = categoryItem.attributeValue("name");
						String[] categoryStr = str.split("\\|");
						category.setId(categoryStr[0]);
						category.setName(categoryStr[1]);
						category.setParentCode(categoryStr[2]);
						category.setStatus(categoryStr[3]);
						category.setSortType(categoryStr[4]);
						categories.add(category);
					}
					facetCategory.setSearchCategories(categories);
					searchFacet.setSearchFacet2Category(facetCategory);
				}else if("CLv3".equals(name)){
					Map<String, Sex> map = new HashMap<String, Sex>();
					List<Sex> sexs = new ArrayList<Sex>();
					SearchFacetSex facetSex = new SearchFacetSex();
					SearchFacetCategory facetCategory = new SearchFacetCategory();
					SearchFacetCategory womCategory = new SearchFacetCategory();
					SearchFacetCategory manCategory = new SearchFacetCategory();
					List<SearchCategory> categories = new ArrayList<SearchCategory>();
					List<SearchCategory> womanCategories = new ArrayList<SearchCategory>();
					List<SearchCategory> manCategories = new ArrayList<SearchCategory>();
					facetCategory.setName("CLv3");
					List<Element> categoryItems = facet.elements();
					for(Element categoryItem : categoryItems){
						SearchCategory category = new SearchCategory();
						category.setCount(categoryItem.getText());
						String str = categoryItem.attributeValue("name");
						String[] categoryStr = str.split("\\|");
						String code = categoryStr[0];
						category.setId(code);
						category.setName(categoryStr[1]);
						category.setParentCode(categoryStr[2]);
						category.setStatus(categoryStr[3]);
						category.setSortType(categoryStr[4]);
						categories.add(category);
						if(code.startsWith("A01")){
							womanCategories.add(category);
							if(!map.containsKey("0")){
								Sex sex = new Sex();
								sex.setCode("0");
								sex.setName("女士");
								map.put("0", sex);
								sexs.add(sex);
							}
						}else if (code.startsWith("A02")) {
							manCategories.add(category);
							if(!map.containsKey("1")){
								Sex sex = new Sex();
								sex.setCode("1");
								sex.setName("男士");
								map.put("1", sex);
								sexs.add(sex);
							}
						}
					}
					facetSex.setSexs(sexs);
					facetCategory.setSearchCategories(categories);
					manCategory.setSearchCategories(manCategories);
					womCategory.setSearchCategories(womanCategories);
					searchFacet.setSearchFacet3Category(facetCategory);
					searchFacet.setWomanFacetCategory(womCategory);
					searchFacet.setManFacetCategory(manCategory);
					searchFacet.setSearchFacetSex(facetSex);
				}else if("CLv4".equals(name)){
					SearchFacetCategory facetCategory = new SearchFacetCategory();
					List<SearchCategory> categories = new ArrayList<SearchCategory>();
					facetCategory.setName("CLv4");
					List<Element> categoryItems = facet.elements();
					for(Element categoryItem : categoryItems){
						SearchCategory category = new SearchCategory();
						category.setCount(categoryItem.getText());
						String str = categoryItem.attributeValue("name");
						String[] categoryStr = str.split("\\|");
						category.setId(categoryStr[0]);
						category.setName(categoryStr[1]);
						category.setParentCode(categoryStr[2]);
						category.setStatus(categoryStr[3]);
						category.setSortType(categoryStr[4]);
						categories.add(category);
					}
					facetCategory.setSearchCategories(categories);
					searchFacet.setSearchFacet4Category(facetCategory);
				}
			}
			searchProduct.setSearchFacet(searchFacet);
			
			List<Element> docs = root.elements("docs");
			for(Element doc : docs){
				products = new ArrayList<Product>();
				List<Element> docElements = doc.elements();
				for(Element docElement : docElements){
					Product product = new Product();
					List<Element> elements = docElement.elements();
					for(Element element : elements){
						String name = element.attributeValue("name");
						if("ProductNo".equals(name)){
							product.setProductId(element.getText());
						}else if ("ProductName".equals(name)) {
							product.setProductName(element.getText());
						}else if ("ProductName".equals(name)) {
							product.setProductName(element.getText());
						}else if ("MarketPrice".equals(name)) {
							product.setMarketPrice(element.getText());
						}else if ("SellPrice".equals(name)) {
							product.setSellPrice(element.getText());
						}else if ("PlatinumPrice".equals(name)) {
							product.setPlatinumPrice(element.getText());
						}else if ("DiamondPrice".equals(name)) {
							product.setDiamondPrice(element.getText());
						}else if ("LimitedPrice".equals(name)) {
							product.setLimitedPrice(element.getText());
						}else if ("PromotionPrice".equals(name)) {
							product.setPromotionPrice(element.getText());
						}else if ("ProductPicFile".equals(name)) {
							product.setProductPicFile(element.getText());
						}else if ("ProductModelPicFile".equals(name)) {
							product.setProductModelPicFile(element.getText());
						}else if ("IsModelPic".equals(name)) {
							product.setIsModelPic(element.getText());
						}else if ("ProductShowFlag".equals(name)) {
							product.setProductShowFlag(element.getText());
						}else if ("BrandNo".equals(name)) {
							product.setBrandNo(element.getText());
						}else if ("BrandCnName".equals(name)) {
							product.setBrandNameCN(element.getText());
						}else if ("BrandEnName".equals(name)) {
							product.setBrandNameEN(element.getText());
						}else if ("IsSupportDiscount".equals(name)) {
							product.setIsSupportDiscount(element.getText());
						}else if ("AvailableStock".equals(name)) {
							product.setAvailableStock(element.getText());
						}else if ("CategoryNo".equals(name)) {
							List<Element> catElements = element.elements();
							List<String> categoryNos = new ArrayList<String>();
							for(Element catElement : catElements){
								categoryNos.add(catElement.getText());
							}
							product.setCategoryNo(categoryNos);
						}
					}
					products.add(product);
				}
			}
			searchProduct.setProducts(products);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return searchProduct;
	}
	
	
	public static void main(String[] args) {
		//ParseXmlUtil.initProducts(new File("C:/Users/JH/Desktop/product.xml"));
	}
	
}
