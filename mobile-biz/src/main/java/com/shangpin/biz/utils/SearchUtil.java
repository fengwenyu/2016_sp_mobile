package com.shangpin.biz.utils;

import com.shangpin.biz.bo.*;
import com.shangpin.biz.service.impl.SPBizBrandServiceImpl;
import com.shangpin.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

public class SearchUtil {

	private static final Logger logger = LoggerFactory.getLogger(SPBizBrandServiceImpl.class);

	@SuppressWarnings("unchecked")
	public SearchProductResult initProductsList(String xml, String userLv) {
		SAXReader reader = new SAXReader();
		Document document = null;
		SearchProductResult searchProductResult = new SearchProductResult();
		try {
			document = reader.read(new ByteArrayInputStream((xml.getBytes("UTF-8"))));
			Element root = document.getRootElement();
			List<Product> products = null;
			String qtime = root.elementText("QTime");
			String total = root.elementText("Total");
			searchProductResult.setCount(total);
			searchProductResult.setqTime(qtime);
			List<Element> docs = root.elements("docs");
			for (Element doc : docs) {
				products = new ArrayList<Product>();
				List<Element> docElements = doc.elements();
				for (Element docElement : docElements) {
					Product product = new Product();
					List<Element> elements = docElement.elements();
					for (Element element : elements) {
						String name = element.attributeValue("name");
						if ("ProductNo".equals(name)) {
							product.setProductId(element.getText());
						} else if ("ProductName".equals(name)) {
							product.setProductName(element.getText());
						} else if ("ProductName".equals(name)) {
							product.setProductName(element.getText());
						} else if ("MarketPrice".equals(name)) {
							product.setMarketPrice(removeDecimal(element.getText()));
						} else if ("SellPrice".equals(name)) {
							product.setSellPrice(removeDecimal(element.getText()));
						} else if ("PlatinumPrice".equals(name)) {
							product.setPlatinumPrice(removeDecimal(element.getText()));
						} else if ("DiamondPrice".equals(name)) {
							product.setDiamondPrice(removeDecimal(element.getText()));
						} else if ("LimitedPrice".equals(name)) {
							product.setLimitedPrice(removeDecimal(element.getText()));
						} else if ("PromotionPrice".equals(name)) {
							product.setPromotionPrice(removeDecimal(element.getText()));
						} else if ("ProductPicFile".equals(name)) {
							product.setPicNo(PicCdnHash.getPicUrl(element.getText(), "1"));
						} else if ("ProductModelPicFile".equals(name)) {
							product.setProductModelPicFile(element.getText());
						} else if ("IsModelPic".equals(name)) {
							product.setIsModelPic(element.getText());
						} else if ("ProductShowFlag".equals(name)) {
							product.setProductShowFlag(element.getText());
						} else if ("BrandNo".equals(name)) {
							product.setBrandNo(element.getText());
						} else if ("BrandCnName".equals(name)) {
							product.setBrandNameCN(element.getText());
						} else if ("BrandEnName".equals(name)) {
							product.setBrandNameEN(element.getText());
						} else if ("IsSupportDiscount".equals(name)) {
							product.setIsSupportDiscount(element.getText());
						} else if ("AvailableStock".equals(name)) {
							if ("0".equals(element.getText())) {
								product.setProductStatus("1");
							}
							product.setAvailableStock(element.getText());
						} else if ("ErpCategoryNo".equals(name)) {
							product.setErpCategoryNo(element.getText());
						} else if ("PostArea".equals(element.getText())) {
							product.setPostArea(element.getText());
						} else if ("CategoryNo".equals(name)) {
							List<Element> catElements = element.elements();
							List<String> categoryNos = new ArrayList<String>();
							for (Element catElement : catElements) {
								categoryNos.add(catElement.getText());
							}
							product.setCategoryNo(categoryNos);
						}
					}
					products.add(product);
				}
			}
			searchProductResult.setProductList(products);

			
			/*List<Product> productList = searchProductResult.getProductList();
			// 初始化商品图片信息 if (productList != null && productList.size() > 0) {
			for (Product product : productList) {
				if (isModelPic(product)) {
					// 如果是模特图并且erp品类是服装的返回模特图
					product.setProductPicUrl(PicCdnHash.getPicUrl(product.getProductModelPicFile(), "1"));
					product.setPicNo(product.getProductModelPicFile());
				} else {
					product.setProductPicUrl(PicCdnHash.getPicUrl(product.getProductPicFile(), "1"));
					product.setPicNo(product.getProductPicFile());
				}

			}*/
			 

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用initProductsList错误！e={}", e);
		}

		return searchProductResult;
	}

	@SuppressWarnings("unchecked")
	public SearchProductResult initAllForFilter(String xml, String userLv) {
		SearchProductResult searchBrandResult = new SearchProductResult();

		SAXReader reader = new SAXReader();
		Document document = null;

		try {
			document = reader.read(new ByteArrayInputStream((xml.getBytes("UTF-8"))));
			Element root = document.getRootElement();
			Element facetsElement = root.element("facets");
			List<Element> facets = facetsElement.elements();
			for (Element facet : facets) {
				String name = facet.attribute("name").getValue();
				if ("Brand".equals(name)) {
					SearchFacetBrand facetBrand = new SearchFacetBrand();
					List<SearchBrand> brands = new ArrayList<SearchBrand>();
					facetBrand.setName("Brand");
					List<Element> brandItems = facet.elements();
					for (Element brandItem : brandItems) {
						SearchBrand brand = new SearchBrand();
						brand.setCount(brandItem.getText());
						String str = brandItem.attribute("name").getValue();
						String[] brandStr = str.split("\\|");

						brand.setId(brandStr[0]);
						if (brandStr.length > 1) {
							brand.setNameEN(brandStr[1]);
						}
						if (brandStr.length > 2) {
							brand.setNameCN(brandStr[2]);
						}
						if (brandStr.length > 3) {
							brand.setPicNo(brandStr[3]);
						}

						brands.add(brand);
					}

					searchBrandResult.setBrandList(brands);
				} else if ("ProductPrimaryColors".equals(name)) {
					List<SearchColor> searchColors = new ArrayList<SearchColor>();
					List<Element> colorItems = facet.elements();
					for (Element colorItem : colorItems) {
						SearchColor color = new SearchColor();
						color.setCount(colorItem.getText());
						String str = colorItem.attributeValue("name");
						String[] colorStr = str.split("\\|");
						color.setId(colorStr[0]);
						if (colorStr.length > 1) {
							color.setName(colorStr[1]);
						}
						if (colorStr.length > 2) {
							color.setRgb(colorStr[2]);
						}

						searchColors.add(color);
					}
					searchBrandResult.setColorList(searchColors);
				} else if ("ProductSize".equals(name)) {
					List<SearchSize> searchSizes = new ArrayList<SearchSize>();
					List<Element> sizeItems = facet.elements();
					for (Element sizeItem : sizeItems) {
						SearchSize searchSize = new SearchSize();
						searchSize.setCount(sizeItem.getText());
						String str = sizeItem.attributeValue("name");
						searchSize.setSizeCode(str);
						searchSizes.add(searchSize);
					}
					searchBrandResult.setSizeList(searchSizes);

				} else if ("Price".equals(name)) {
					List<SearchPrice> searchPrices = new ArrayList<SearchPrice>();
					List<Element> priceItems = facet.elements();
					for (Element priceItem : priceItems) {
						SearchPrice price = new SearchPrice();
						price.setCount(priceItem.getText());
						String str = priceItem.attributeValue("name");
						price.setAmong(str);
						searchPrices.add(price);
					}
					searchBrandResult.setPriceList(searchPrices);
				} else if ("CLv2".equals(name)) {
					List<SearchCategory> categories = new ArrayList<SearchCategory>();
					List<Element> categoryItems = facet.elements();
					for (Element categoryItem : categoryItems) {
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
					searchBrandResult.setCategoryLv2List(categories);
				} else if ("CLv3".equals(name)) {
					Map<String, Sex> map = new HashMap<String, Sex>();
					List<Sex> sexs = new ArrayList<Sex>();
					List<SearchCategory> categories = new ArrayList<SearchCategory>();
					List<SearchCategory> womanCategories = new ArrayList<SearchCategory>();
					List<SearchCategory> manCategories = new ArrayList<SearchCategory>();
					List<Element> categoryItems = facet.elements();
					for (Element categoryItem : categoryItems) {
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
						if (code.startsWith("A01")) {
							womanCategories.add(category);
							if (!map.containsKey("0")) {
								Sex sex = new Sex();
								sex.setCode("0");
								sex.setName("女士");
								map.put("0", sex);
								sexs.add(sex);
							}
						} else if (code.startsWith("A02")) {
							manCategories.add(category);
							if (!map.containsKey("1")) {
								Sex sex = new Sex();
								sex.setCode("1");
								sex.setName("男士");
								map.put("1", sex);
								sexs.add(sex);
							}
						}
					}
					searchBrandResult.setCategoryLv3List(categories);
					searchBrandResult.setWomanFacetCategory(womanCategories);
					searchBrandResult.setManFacetCategory(manCategories);
					searchBrandResult.setSexs(sexs);
				} else if ("CLv4".equals(name)) {
					List<SearchCategory> categories = new ArrayList<SearchCategory>();
					List<Element> categoryItems = facet.elements();
					for (Element categoryItem : categoryItems) {
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
					searchBrandResult.setCategoryLv4List(categories);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用initAllForFilter错误！e={}", e);
		}
		return searchBrandResult;

	}

	@SuppressWarnings("unchecked")
	public SearchProductResult initCategoryForFilter(String xml, String userLv) {
		SearchProductResult searchProductResult = new SearchProductResult();

		SAXReader reader = new SAXReader();
		Document document = null;

		try {
			document = reader.read(new ByteArrayInputStream((xml.getBytes("UTF-8"))));
			Element root = document.getRootElement();

			Element facetsElement = root.element("facets");
			List<Element> facets = facetsElement.elements();
			for (Element facet : facets) {
				String name = facet.attribute("name").getValue();
				if ("CLv2".equals(name)) {
					List<SearchCategory> categories = new ArrayList<SearchCategory>();
					List<Element> categoryItems = facet.elements();
					for (Element categoryItem : categoryItems) {
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
					searchProductResult.setCategoryLv2List(categories);
				} else if ("CLv3".equals(name)) {
					Map<String, Sex> map = new HashMap<String, Sex>();
					List<Sex> sexs = new ArrayList<Sex>();
					List<SearchCategory> categories = new ArrayList<SearchCategory>();
					List<SearchCategory> womanCategories = new ArrayList<SearchCategory>();
					List<SearchCategory> manCategories = new ArrayList<SearchCategory>();
					List<Element> categoryItems = facet.elements();
					for (Element categoryItem : categoryItems) {
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
						if (code.startsWith("A01")) {
							womanCategories.add(category);
							if (!map.containsKey("0")) {
								Sex sex = new Sex();
								sex.setCode("0");
								sex.setName("女士");
								map.put("0", sex);
								sexs.add(sex);
							}
						} else if (code.startsWith("A02")) {
							manCategories.add(category);
							if (!map.containsKey("1")) {
								Sex sex = new Sex();
								sex.setCode("1");
								sex.setName("男士");
								map.put("1", sex);
								sexs.add(sex);
							}
						}
					}
					searchProductResult.setCategoryLv3List(categories);
					searchProductResult.setWomanFacetCategory(womanCategories);
					searchProductResult.setManFacetCategory(manCategories);
					searchProductResult.setSexs(sexs);
				} else if ("CLv4".equals(name)) {
					List<SearchCategory> categories = new ArrayList<SearchCategory>();
					List<Element> categoryItems = facet.elements();
					for (Element categoryItem : categoryItems) {
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
					searchProductResult.setCategoryLv4List(categories);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用initCategoryForFilter错误！e={}", e);
		}
		return searchProductResult;

	}

	@SuppressWarnings("unchecked")
	private SearchProductResult initOtherForFilter(String xml, String userLv) {
		SearchProductResult searchProductResult = new SearchProductResult();

		SAXReader reader = new SAXReader();
		Document document = null;

		try {
			document = reader.read(new ByteArrayInputStream((xml.getBytes("UTF-8"))));
			Element root = document.getRootElement();
			String priceKey = "LimitedPrice_Sale";
			if (userLv != null) {
				if (userLv.equals("0001")) {
					priceKey = "LimitedPrice_Sale";
				} else if (userLv.equals("0002")) {
					priceKey = "SellPrice_Sale";

				} else if (userLv.equals("0003")) {
					priceKey = "PlatinumPrice_Sale";

				} else if (userLv.equals("0004")) {
					priceKey = "DiamondPrice_Sale";
				}
			}

			Element facetsElement = root.element("facets");
			List<Element> facets = facetsElement.elements();
			for (Element facet : facets) {
				String name = facet.attribute("name").getValue();
				if ("ProductPrimaryColors".equals(name)) {
					List<SearchColor> searchColors = new ArrayList<SearchColor>();
					List<Element> colorItems = facet.elements();
					for (Element colorItem : colorItems) {
						SearchColor color = new SearchColor();
						color.setCount(colorItem.getText());
						String str = colorItem.attributeValue("name");
						String[] colorStr = str.split("\\|");
						color.setId(colorStr[0]);
						color.setName(colorStr[1]);
						color.setRgb(colorStr[2]);

						searchColors.add(color);
					}
					searchProductResult.setColorList(searchColors);
				} else if ("ProductSize".equals(name)) {
					List<SearchSize> searchSizes = new ArrayList<SearchSize>();
					List<Element> sizeItems = facet.elements();
					for (Element sizeItem : sizeItems) {
						SearchSize searchSize = new SearchSize();
						searchSize.setCount(sizeItem.getText());
						String str = sizeItem.attributeValue("name");
						searchSize.setSizeCode(str);
						searchSizes.add(searchSize);
					}
					searchProductResult.setSizeList(searchSizes);

				} else if (priceKey.equals(name)) {
					List<SearchPrice> searchPrices = new ArrayList<SearchPrice>();
					List<Element> priceItems = facet.elements();
					for (Element priceItem : priceItems) {
						SearchPrice price = new SearchPrice();
						price.setCount(priceItem.getText());
						String str = priceItem.attributeValue("name");
						price.setAmong(str);
						searchPrices.add(price);
					}
					searchProductResult.setPriceList(searchPrices);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用initOtherForFilter错误！e={}", e);
		}
		return searchProductResult;
	}

	@SuppressWarnings("unchecked")
	private SearchProductResult initBrandForFilter(String xml, String userLv) {
		SearchProductResult searchProductResult = new SearchProductResult();

		SAXReader reader = new SAXReader();
		Document document = null;

		try {
			document = reader.read(new ByteArrayInputStream((xml.getBytes("UTF-8"))));
			Element root = document.getRootElement();

			Element facetsElement = root.element("facets");
			List<Element> facets = facetsElement.elements();
			for (Element facet : facets) {
				String name = facet.attribute("name").getValue();
				if ("Brand".equals(name)) {
					SearchFacetBrand facetBrand = new SearchFacetBrand();
					List<SearchBrand> brands = new ArrayList<SearchBrand>();
					facetBrand.setName("Brand");
					List<Element> brandItems = facet.elements();
					for (Element brandItem : brandItems) {
						SearchBrand brand = new SearchBrand();
						brand.setCount(brandItem.getText());
						String str = brandItem.attribute("name").getValue();
						String[] brandStr = str.split("\\|");

						brand.setId(brandStr[0]);
						if (brandStr.length > 2) {
							brand.setNameEN(brandStr[1]);
						}
						if (brandStr.length > 3) {
							brand.setNameCN(brandStr[2]);
						}
						if (brandStr.length > 4) {
							brand.setPicNo(brandStr[3]);
						}

						brands.add(brand);
					}

					searchProductResult.setBrandList(brands);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用initBrandProductsForFilter错误！e={}", e);
		}
		return searchProductResult;
	}

	/**
	 * 是否取模特图
	 * 
	 * @param product
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean isModelPic(Product product) {
		String erpCategory = product.getErpCategoryNo();
		if (erpCategory != null && erpCategory.startsWith("A01")) {
			return true;
		}
		return false;
	}

	public SearchProductResult initSearchResult(String xml, String userLv, SearchType searchType) {
		if (SearchType.ALL_FILTER.equals(searchType)) {// 解析页面中展示的查询筛选条件
			return initAllForFilter(xml, userLv);
		} else if (SearchType.PRODUCT.equals(searchType)) {// 解析商品列表
			return initProductsList(xml, userLv);
		} else if (SearchType.CATEGORY_FILTER.equals(searchType)) {
			return initCategoryForFilter(xml, userLv);
		} else if (SearchType.BRAND_FILTER.equals(searchType)) {
			return initBrandForFilter(xml, userLv);
		} else if (SearchType.OTHER_FILTER.equals(searchType)) {
			return initOtherForFilter(xml, userLv);
		}
		return null;
	}

	/**
	 * 颜色
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static List<SearchColor> initColorList(List<Element> element) {
		List<SearchColor> colorList = new ArrayList<SearchColor>();
		if (element != null && element.size() > 0) {
			for (int k = 0; k < element.size(); k++) {
				SearchColor Color = new SearchColor();
				Element element1 = (Element) element.get(k);
				// 数量
				String count = element1.getTextTrim();
				Color.setCount(count);
				List attributes1 = element1.attributes();
				if (attributes1 != null && attributes1.size() > 0) {
					DefaultAttribute attribute1 = (DefaultAttribute) attributes1.get(0);
					String value = attribute1.getText();
					String[] itemarr = value.split("\\|");
					Color.setId(itemarr[0]);
					Color.setName(itemarr[1]);
					if (itemarr.length > 2) {
						Color.setRgb(itemarr[2]);
					} else {
						Color.setRgb("");
					}
					colorList.add(Color);
				}
			}
		}
		return colorList;
	}

	/**
	 * 热词
	 * 
	 * @param element
	 * @return
	 */
	private static List<SearchHotWords> initTagList(List<Element> element) {
		List<SearchHotWords> tagList = new ArrayList<SearchHotWords>();
		if (element != null && element.size() > 0) {
			for (int i = 0; i < element.size(); i++) {
				String textVal = element.get(i).getTextTrim();
				if (textVal != null && !"".equals(textVal)) {
					SearchHotWords hotwords = new SearchHotWords();
					String[] itemarr = textVal.split("\\|");
					hotwords.setId(itemarr[0]);
					hotwords.setName(itemarr[1]);
					hotwords.setType(itemarr[2]);
					tagList.add(hotwords);
				}
			}
		}
		return tagList;
	}

	/**
	 * 动态属性模板
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static SearchDynamicAttr initThemeList(List<Element> element, String secondAttrName, String typeAttrNam) {
		SearchDynamicAttr searchDynamicAttr = null;
		List<SearchDynamicValue> searchDynamicValueList = new ArrayList<SearchDynamicValue>();
		if (element != null && element.size() > 0) {
			String dynamicId = "";
			for (int k = 0; k < element.size(); k++) {
				SearchDynamicValue searchTheme = new SearchDynamicValue();
				Element element1 = (Element) element.get(k);
				// 数量
				List attributes1 = element1.attributes();
				if (attributes1 != null && attributes1.size() > 0) {
					DefaultAttribute attribute1 = null;
					for (int i = 0; i < attributes1.size(); i++) {
						attribute1 = (DefaultAttribute) attributes1.get(i);
						if ("id".equals(attribute1.getName())) {
							searchTheme.setId(attribute1.getValue());
							dynamicId = attribute1.getValue();
						}
						if ("name".equals(attribute1.getName())) {
							searchTheme.setName(attribute1.getValue());
						}
					}
					searchDynamicValueList.add(searchTheme);
				}
			}
			searchDynamicAttr = new SearchDynamicAttr();
			searchDynamicAttr.setName(secondAttrName);
			if ("attribute".equals(typeAttrNam)) {
				searchDynamicAttr.setType("attrId");
				searchDynamicAttr.setDynamicType("dynamic" + dynamicId);
			} else if ("ProductSize".equals(typeAttrNam)) {
				searchDynamicAttr.setType("size");
			}
			searchDynamicAttr.setSearchDynamicValue(searchDynamicValueList);
		}
		return searchDynamicAttr;
	}

	/**
	 * 是否海外商品，1:国内;2:海外
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private static String initPostArea(List<Element> element) {
		String abroad = "";
		if (element != null && element.size() > 0) {
			for (int k = 0; k < element.size(); k++) {
				Element element1 = (Element) element.get(k);
				String count = element1.getTextTrim();
				List attributes1 = element1.attributes();
				if (attributes1 != null && attributes1.size() > 0) {
					DefaultAttribute attribute1 = (DefaultAttribute) attributes1.get(0);
					String value = attribute1.getText();
					if (StringUtil.isNotEmpty(value) && "2".equals(value)) {
						abroad = value;
					}
				}
			}
		}

		return abroad;
	}

	/**
	 * 品类
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static List<SearchCategory> initCategoryList(List<Element> element) {
		List<SearchCategory> cateList = new ArrayList<SearchCategory>();
		if (element != null && element.size() > 0) {
			for (int k = 0; k < element.size(); k++) {
				SearchCategory cateVO = new SearchCategory();
				Element element1 = (Element) element.get(k);
				// 数量
				String count = element1.getTextTrim();
				cateVO.setCount(count);
				List attributes1 = element1.attributes();
				if (attributes1 != null && attributes1.size() > 0) {
					DefaultAttribute attribute1 = (DefaultAttribute) attributes1.get(0);
					String value = attribute1.getText();
					String[] itemarr = value.split("\\|");
					if (itemarr[3] != null && itemarr[1] != null && !"".equals(itemarr[1])) {
						cateVO.setId(itemarr[0]);
						cateVO.setName(itemarr[1]);
						cateVO.setParentId(itemarr[2]);
						cateVO.setStatus(itemarr[3]);
						cateVO.setSortType(itemarr[4]);
						if("0".equals(cateVO.getStatus()))
							continue;
						cateList.add(cateVO);
					}
				}
			}
		}

		return cateList;
	}

	/**
	 * 初始化品牌数据
	 * 
	 * @param element
	 */
	@SuppressWarnings("rawtypes")
	private static List<SearchBrand> initBrandList(List<Element> element) {
		List<SearchBrand> brandList = new ArrayList<SearchBrand>();
		if (element != null && element.size() > 0) {
			for (int k = 0; k < element.size(); k++) {
				SearchBrand brand = new SearchBrand();
				Element element1 = (Element) element.get(k);
				// 数量
				String count = element1.getTextTrim();
				brand.setCount(count);
				List attributes1 = element1.attributes();
				if (attributes1 != null && attributes1.size() > 0) {
					DefaultAttribute attribute1 = (DefaultAttribute) attributes1.get(0);
					String value = attribute1.getText();
					String[] itemarr = value.split("\\|");
					brand.setId(itemarr[0]);
					brand.setNameEN(itemarr[1]);
					brand.setName(itemarr[1]);
					if (itemarr.length == 2) {
						brand.setNameCN(itemarr[1]);
					} else {
						brand.setNameCN(itemarr[2]);
					}
					String brandPicNo = itemarr[3];
					String picUrl = PicCdnHash.getPicUrl(brandPicNo, "2");
					brand.setImgurl(picUrl);
					brandList.add(brand);
				}
			}
		}
		return brandList;
	}

	@SuppressWarnings("rawtypes")
	private static List<Product> initProductList(List element, String fromType, ActivityHead activityHead) {
		List<Product> productList = new ArrayList<Product>();
		if (element != null && element.size() > 0) {
			Product product = new Product();
			product.setProductStatus("0");
			int stock = 0;
			for (int k = 0; k < element.size(); k++) {
				Element element1 = (Element) element.get(k);
				List attributes1 = element1.attributes();
				if (attributes1 != null && attributes1.size() > 0) {
					DefaultAttribute attribute1 = (DefaultAttribute) attributes1.get(0);

					// <field name="ProductNo">01227957</field>
					String fieldName = attribute1.getText();
					// 取值
					String value = element1.getTextTrim();

					if ("ProductNo".equals(fieldName)) {
						product.setProductId(value);
					} else if ("ProductName".equals(fieldName)) {
						product.setProductName(value);
					} else if ("MarketPrice".equals(fieldName)) {
						product.setMarketPrice(removeDecimal(value));
					} else if ("LimitedPrice".equals(fieldName) && StringUtil.isNotEmpty(value)) {
						product.setLimitedPrice(removeDecimal(value));
					} else if ("SellPrice".equals(fieldName) && StringUtil.isNotEmpty(value)) {
						product.setGoldPrice(removeDecimal(value));
					} else if ("PlatinumPrice".equals(fieldName) && StringUtil.isNotEmpty(value)) {
						product.setPlatinumPrice(removeDecimal(value));
					} else if ("DiamondPrice".equals(fieldName) && StringUtil.isNotEmpty(value)) {
						product.setDiamondPrice(removeDecimal(value));
					} else if ("ProductPicFile".equals(fieldName)) {
						product.setPic(PicCdnHash.getPicUrl(value, "1"));
						product.setPicNo(value);
					} else if ("ProductModelPicFile".equals(fieldName)) {
						product.setProductModelPicFile(value);
					} else if ("BrandNo".equals(fieldName)) {
						product.setBrandNo(value);
					} else if ("BrandEnName".equals(fieldName)) {
						product.setBrandNameEN(value);
					} else if ("BrandCnName".equals(fieldName)) {
						product.setBrandNameCN(value);
					} else if ("IsSupportDiscount".equals(fieldName)) {
						// 这个值以后不用了 写死为1
						product.setIsSupportDiscount(value);
					} else if ("AvailableStock".equals(fieldName)) {
						stock = Integer.parseInt(value);
						if (stock == 0) {
							product.setProductStatus("1");
						}
						product.setCount(value);
					} else if ("ErpCategoryNo".equals(fieldName)) {
						product.setErpCategoryNo(value);
					} else if ("PromotionPrice".equals(fieldName) && StringUtil.isNotEmpty(value)) {
						product.setPromotionPrice(removeDecimal(value));
					} else if ("PostArea".equals(fieldName)) {
						product.setPostArea(value);
					} else if ("Prefix".equals(fieldName)) {
						product.setPrefix(value);
						;
					} else if ("Suffix".equals(fieldName)) {
						product.setSuffix(value);
					} else if ("PromotionNotice".equals(fieldName)) {
						product.setPromotionNotice(value);
					} else if ("PostAreaPic".equals(fieldName)) {
						if (!("".equals(value))) {
							product.setCountryPic(Constants.COUNTRYPIC_URL + value);
						} else {
							product.setCountryPic("");
						}
					} else if ("IsNewSeasonal".equals(fieldName)) {
						if (stock != 0) {
							if ("1".equals(value)) {
								product.setProductStatus("2");
							}
						}

					} else if ("IsPromotion".equals(fieldName)) {
						product.setIsPromotion(value);
					} else if ("NewLimitedPrice".equals(fieldName) && StringUtil.isNotEmpty(value)) {
						product.setNewLimitedPrice(removeDecimal(value));
					} else if ("NewSellPrice".equals(fieldName) && StringUtil.isNotEmpty(value)) {
						product.setNewSellPrice(removeDecimal(value));
					} else if ("NewPlatinumPrice".equals(fieldName) && StringUtil.isNotEmpty(value)) {
						product.setNewPlatinumPrice(removeDecimal(value));
					} else if ("NewDiamondPrice".equals(fieldName) && StringUtil.isNotEmpty(value)) {
						product.setNewDiamondPrice(removeDecimal(value));
					}

					// 2015.11.24-实时变价，促销失效
					if (StringUtil.compareDate(Constants.ACTIVITY_SALES_START, Constants.ACTIVITY_SALES_END, DateUtils.dateToStr(new Date())) == 0) {
						if ("NewLimitedPrice".equals(fieldName) && StringUtil.isNotEmpty(value)) {
							product.setLimitedPrice(removeDecimal(value));
						} else if ("NewSellPrice".equals(fieldName) && StringUtil.isNotEmpty(value)) {
							product.setSellPrice(removeDecimal(value));
						} else if ("NewPlatinumPrice".equals(fieldName) && StringUtil.isNotEmpty(value)) {
							product.setPlatinumPrice(removeDecimal(value));
						} else if ("NewDiamondPrice".equals(fieldName) && StringUtil.isNotEmpty(value)) {
							product.setDiamondPrice(removeDecimal(value));
						}
						product.setIsPromotion("0");
					}

					// 这个值以后不用了 写死为100000 普通
					product.setStatus("100000");
					product.setComments("-1");
					product.setCollections("-1");
					// product.setPromoLogo(Constants.DOUBLE_ELE_URL);

				}
			}
			product = getPromionProduct(product, fromType, activityHead);
			productList.add(product);
		}
		return productList;
	}

	/**
	 * 将主站返回的xml转换为SearchResult
	 * 
	 * @param fromType
	 *            来源 1:活动;2:品牌;3:品类;4:标签;5:关键字
	 * @param categoryId
	 *            当前所选分类
	 * @param activityHead
	 *            当fromType为1时，传值，其他传null
	 * @throws UnsupportedEncodingException
	 * @throws DocumentException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static SearchResult converXmlToObj(String data, String fromType, String categoryId, ActivityHead activityHead) throws UnsupportedEncodingException, DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new ByteArrayInputStream(data.getBytes("utf-8")));
		Element root = document.getRootElement();
		Iterator<Element> rootIter = root.elementIterator();
		SearchResult search = new SearchResult();
		List<SearchCategory> categoryList = new ArrayList<SearchCategory>();
		List<SearchCategory> categoryList2 = new ArrayList<SearchCategory>();
		List<SearchCategory> categoryList3 = new ArrayList<SearchCategory>();
		List<Product> productList = new ArrayList<Product>();
		List<SearchDynamicAttr> searchThemeList = new ArrayList<SearchDynamicAttr>();
		String qTime = root.elementText("QTime");
		String total = root.elementText("Total");
		String suggest = root.elementText("Suggest");
		search.setqTime(qTime);
		search.setCount(total);
		search.setAbroad("0");
		search.setSuggest(suggest);
		while (rootIter.hasNext()) {
			Element rootElement = rootIter.next();
			String rootName = rootElement.getName();
			List elements = rootElement.elements();
			if ("hotwords".equals(rootName)) {
				search.setConditionList((initTagList(elements)));
			} else {
				if (elements != null && elements.size() > 0) {
					for (int i = 0; i < elements.size(); i++) {
						Element element = (Element) elements.get(i);
						String secondEName = element.getName();
						String secondAttrName = null;
						String typeAttrName = null;
						List attributes = element.attributes();
						if (attributes != null && attributes.size() > 0) {
							DefaultAttribute attribute = null;
							attribute = (DefaultAttribute) attributes.get(0);
							secondAttrName = attribute.getText();
							if (attributes.size() == 2) {
								attribute = (DefaultAttribute) attributes.get(1);
								typeAttrName = attribute.getText();
							}
						}
						if (secondEName.equals("facet") && secondAttrName != null) {
							if (secondAttrName.startsWith("CLv4")) {// 品类
								// 多个品类层级数据放在一起
								categoryList.addAll(initCategoryList(element.elements()));
								search.setCategoryList(categoryList);
							} else if (secondAttrName.equals("CLv3")) {
								categoryList3.addAll(initCategoryList(element.elements()));
								search.setCategoryList3(categoryList3);
							} else if (secondAttrName.equals("CLv2")) {
								categoryList2.addAll(initCategoryList(element.elements()));
								search.setCategoryList2(categoryList2);
							} else if (secondAttrName.equals("ProductPrimaryColors")) {// 颜色
								search.setColorList(initColorList(element.elements()));
							} else if (secondAttrName.equals("Brand")) {// 品牌
								search.setBrandList(initBrandList(element.elements()));
							}
							/*
							 * if (secondAttrName.equals("PostArea")) {//
							 * 是否海外商品。1国内;2海外 if
							 * ("2".equals(initPostArea(element.elements()))) {
							 * search.setAbroad("1"); } }
							 */
						} else if (secondEName.equals("filter") && secondAttrName != null) {
							if (categoryId != null) {
								if (categoryId.length() == 12 || categoryId.indexOf("A01B03") > -1 || categoryId.indexOf("A02B03") > -1) {
									SearchDynamicAttr searchDynamicAttr = initThemeList(element.elements(), secondAttrName, typeAttrName);
									if (searchDynamicAttr != null) {
										searchThemeList.add(searchDynamicAttr);
										search.setAttributes(searchThemeList);
									}
								}
							}

						} else if (secondEName.equals("doc")) {
							productList.addAll(initProductList(element.elements(), fromType, activityHead));
							search.setProductList(productList);
						}

					}
				}

			}

		}
		return search;
	}

	/**
	 * 去掉小数点
	 * 
	 * @Author xupengcheng
	 * @CreatDate 2013-12-29 下午12:32:14
	 * 
	 */
	private static String removeDecimal(String data) {
		if (data != null && !"".equals(data) && data.indexOf(".") > -1) {
			data = data.substring(0, data.indexOf("."));
		}
		return data;
	}

	/**
	 * 搜索对象转换成客户端对象
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws DocumentException
	 */
	public static SearchResultApp searchByApp(SearchResult searchResult, String from) {
		SearchResultApp searchResultApp = new SearchResultApp();
		SearchCorrect searchCorrect = new SearchCorrect();
		String suggest = searchResult.getSuggest();
		if (suggest != null && !"".equals(suggest)) {
			searchCorrect.setWord(suggest);
			searchCorrect.setDesc("找不到您要的结果，为您推荐了" + suggest + "以下相关商品~");
		}
		searchResultApp.setCorrect(searchCorrect);
		searchResultApp.setCount(searchResult.getCount());
		searchResultApp.setConditionList(searchResult.getConditionList());
		searchResultApp.setAttributes(searchByAttribute(searchResult, from));
		searchResultApp.setFiltrates(searchByfiltrates(searchResult));
		searchResultApp.setProductList(searchResult.getProductList());
		return searchResultApp;
	}
	
	public static SearchResultApp searchByApp(SearchResult searchResult, String from, String originalFilters, String dynamicFilters) {
		SearchResultApp searchResultApp = new SearchResultApp();
		SearchCorrect searchCorrect = new SearchCorrect();
		String suggest = searchResult.getSuggest();
		if (suggest != null && !"".equals(suggest)) {
			searchCorrect.setWord(suggest);
			searchCorrect.setDesc("找不到您要的结果，为您推荐了" + suggest + "以下相关商品~");
		}
		searchResultApp.setCorrect(searchCorrect);
		searchResultApp.setCount(searchResult.getCount());
		searchResultApp.setConditionList(searchResult.getConditionList());
		searchResultApp.setAttributes(searchByAttribute(searchResult, from, originalFilters, dynamicFilters));
		searchResultApp.setFiltrates(searchByfiltrates(searchResult, from, originalFilters, dynamicFilters));
		searchResultApp.setProductList(searchResult.getProductList());
		return searchResultApp;
	}

	/**
	 * 搜索属性对象转换客户端格式
	 * 
	 * @param source
	 *            来源 1:活动;2:品牌;3:品类;4:标签;5:关键字
	 * @param categoryId
	 *            当前所选分类
	 * @throws UnsupportedEncodingException
	 * @throws DocumentException
	 */
	private static List<SearchAttribute> searchByAttribute(SearchResult searchResult, String source) {
		List<SearchAttribute> attributes = new ArrayList<SearchAttribute>();
		SearchAttribute searchAttribute = null;
		/* 以下是品类的封装 */
		List<SearchCategory> categoryList = searchResult.getCategoryList();
		List<SearchCategory> categoryList2 = searchResult.getCategoryList2();
		List<SearchCategory> categoryList3 = searchResult.getCategoryList3();
		if (categoryList != null && categoryList.size() > 0) {
			searchAttribute = new SearchAttribute();
			searchAttribute.setName("category");
			searchAttribute.setIsMultiSelect("0");
			searchAttribute.setDesc("品类");
			List<SearchAttributeGroup> searchAttributeGroups = new ArrayList<SearchAttributeGroup>();
			if (!categoryList.isEmpty() && !categoryList2.isEmpty() && !categoryList3.isEmpty()) {
				Collections.sort(categoryList, new Comparator<SearchCategory>() {

					@Override
					public int compare(SearchCategory o1, SearchCategory o2) {
						return new BigDecimal(o1.getSortType()).compareTo(new BigDecimal(o2.getSortType()));
					}

				});
				
				Collections.sort(categoryList3, new Comparator<SearchCategory>() {

					@Override
					public int compare(SearchCategory o1, SearchCategory o2) {
						return new BigDecimal(o1.getSortType()).compareTo(new BigDecimal(o2.getSortType()));
					}

				});

				SearchCategory category = new SearchCategory();
				Map<String, List<SearchCategory>> resultMap = new HashMap<String, List<SearchCategory>>();
				for (int i = 0; i < categoryList.size(); i++) {
					category = categoryList.get(i);
					if (resultMap.containsKey(category.getParentId())) {
						resultMap.get(category.getParentId()).add(category);
					} else {
						List<SearchCategory> list = new ArrayList<SearchCategory>();
						list.add(category);
						resultMap.put(category.getParentId(), list);
					}
				}

				for (int j = 0; j < categoryList3.size(); j++) {
					SearchCategory category3 = categoryList3.get(j);
					SearchAttributeGroup group3 = new SearchAttributeGroup();
					List<SearchAttributeValue>  searchAttributeValueList = new ArrayList<SearchAttributeValue>();
					List<SearchCategory> categorys = resultMap.get(category3.getId());
					if(CollectionUtils.isNotEmpty(categorys)) {
						for (int m = 0; m < categorys.size(); m++) {
							SearchCategory obj = categorys.get(m);
							SearchAttributeValue searchAttributeValue = new SearchAttributeValue();
							searchAttributeValue.setId(obj.getId());
							searchAttributeValue.setName(obj.getName());
							searchAttributeValue.setExt(new ArrayList<SearchAttributeExt>());
							searchAttributeValueList.add(searchAttributeValue);
						}
						group3.setValue(searchAttributeValueList);
						group3.setParentId(category3.getParentId());
						group3.setName(category3.getName());
						searchAttributeGroups.add(group3);
					}
				}
				
				if (categoryList2.size() > 1) {
					Map<String, SearchCategory> map = new HashMap<String, SearchCategory>();
					List<SearchAttributeGroup> searchGroups = new ArrayList<SearchAttributeGroup>();
					for (SearchCategory searchCategory : categoryList2) {
						map.put(searchCategory.getId(), searchCategory);
					}
					for (int z = 0; z < searchAttributeGroups.size(); z++) {
						SearchAttributeGroup searchGroup = searchAttributeGroups.get(z);
						SearchAttributeGroup resultGroup = new SearchAttributeGroup();
						resultGroup.setParentSortType(map.get(searchGroup.getParentId()).getSortType());
						resultGroup.setName(map.get(searchGroup.getParentId()).getName() + "-" + searchGroup.getName());
						resultGroup.setValue(searchGroup.getValue());
						searchGroups.add(resultGroup);
					}
					Collections.sort(searchGroups, new Comparator<SearchAttributeGroup>() {

						@Override
						public int compare(SearchAttributeGroup o1, SearchAttributeGroup o2) {
							return new BigDecimal(o1.getParentSortType()).compareTo(new BigDecimal(o2.getParentSortType()));
						}

					});
					searchAttribute.setGroup(searchGroups);
				} else {
					searchAttribute.setGroup(searchAttributeGroups);
				}

			}
			// 2.9.5以前的版本要用
			List<SearchAttributeValue> searchList = new ArrayList<SearchAttributeValue>();
			for (int x = 0; x < categoryList.size(); x++) {
				SearchCategory sc = categoryList.get(x);
				SearchAttributeValue searchValue = new SearchAttributeValue();
				searchValue.setId(sc.getId());
				searchValue.setName(sc.getName());
				searchValue.setExt(new ArrayList<SearchAttributeExt>());
				searchList.add(searchValue);
			}

			searchAttribute.setValue(searchList);
			attributes.add(searchAttribute);
		}
		/* 以下是品牌的封装 */
		List<SearchBrand> brandList = searchResult.getBrandList();
		if (!"2".equals(source) && brandList != null && brandList.size() > 0) {
			List<SearchAttributeGroup> searchAttributeGroups1 = new ArrayList<SearchAttributeGroup>();
			searchAttribute = new SearchAttribute();
			searchAttribute.setName("brand");
			searchAttribute.setIsMultiSelect("0");
			searchAttribute.setDesc("品牌");
			List<SearchAttributeValue> searchAttributeValueList = new ArrayList<SearchAttributeValue>();
			SearchAttributeExt searchAttributeExt = null;
			for (int i = 0; i < brandList.size(); i++) {
				SearchBrand searchBrand = brandList.get(i);
				SearchAttributeValue searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId(searchBrand.getId());
				searchAttributeValue.setName(searchBrand.getName());
				List<SearchAttributeExt> searchAttributeExtList = new ArrayList<SearchAttributeExt>();
				searchAttributeExt = new SearchAttributeExt();
				searchAttributeExt.setName("nameEN");
				searchAttributeExt.setValue(searchBrand.getNameEN());
				searchAttributeExtList.add(searchAttributeExt);
				searchAttributeExt = new SearchAttributeExt();
				searchAttributeExt.setName("nameCN");
				searchAttributeExt.setValue(searchBrand.getNameCN());
				searchAttributeExtList.add(searchAttributeExt);
				searchAttributeValue.setExt(searchAttributeExtList);
				searchAttributeValueList.add(searchAttributeValue);

			}
			//全部品牌选择条件
			SearchAttributeGroup searchAttributeGroup1 = new SearchAttributeGroup();
			searchAttributeGroup1.setName("全部品牌");
			searchAttributeGroup1.setValue(searchAttributeValueList);
			searchAttributeGroups1.add(searchAttributeGroup1);

			searchAttribute.setValue(searchAttributeValueList);
			searchAttribute.setGroup(searchAttributeGroups1);
			attributes.add(searchAttribute);
		}

		/* 以下是动态属性的封装 */
		List<SearchDynamicAttr> attrList = searchResult.getAttributes();
		if (attrList != null && attrList.size() > 0) {
			for (int i = 0; i < attrList.size(); i++) {
				SearchDynamicAttr searchDynamicAttr = attrList.get(i);
				if (searchDynamicAttr != null) {
					searchAttribute = new SearchAttribute();
					SearchAttributeGroup searchAttributeGroup2 = new SearchAttributeGroup();
					List<SearchAttributeGroup> searchAttributeGroups2 = new ArrayList<SearchAttributeGroup>();
					String type = searchDynamicAttr.getType();
					if ("attrId".equals(type)) {
						searchAttribute.setName("attribute");
						searchAttribute.setIsMultiSelect("0");
						searchAttribute.setDesc(searchDynamicAttr.getName());
					} else {
						searchAttribute.setName("size");
						searchAttribute.setIsMultiSelect("0");
						searchAttribute.setDesc(searchDynamicAttr.getName());
					}
					List<SearchDynamicValue> searchDynamicValueList = searchDynamicAttr.getSearchDynamicValue();
					List<SearchAttributeValue> SearchAttributeValueList = new ArrayList<SearchAttributeValue>();
					if (searchDynamicValueList != null) {
						for (int j = 0; j < searchDynamicValueList.size(); j++) {
							SearchDynamicValue searchDynamicValue = searchDynamicValueList.get(j);
							SearchAttributeValue searchAttributeValue = new SearchAttributeValue();
							if ("attrId".equals(type)) {
								searchAttributeValue.setId(searchDynamicValue.getId());
							} else {
								searchAttributeValue.setId(searchDynamicValue.getName());
							}
							searchAttributeValue.setName(searchDynamicValue.getName());
							searchAttributeValue.setExt(new ArrayList<SearchAttributeExt>());
							SearchAttributeValueList.add(searchAttributeValue);
						}
						searchAttributeGroup2.setName("");
						searchAttributeGroup2.setValue(SearchAttributeValueList);
						searchAttributeGroups2.add(searchAttributeGroup2);
						searchAttribute.setValue(SearchAttributeValueList);
						searchAttribute.setGroup(searchAttributeGroups2);
					}
					attributes.add(searchAttribute);
				}

			}
		}
		return attributes;
	}
	
	private static List<SearchAttribute> searchByAttribute(SearchResult searchResult, String source, String originalFilters, String dynamicFilters) {
		List<SearchAttribute> attributes = new ArrayList<SearchAttribute>();
		SearchAttribute searchAttribute = null;
		// 处理客户端请求逻辑
		SearchParam searchParam = new SearchParam();
		SearchParam originalParam = new SearchParam();
		StringBuffer dynamic = new StringBuffer();
		String filter = null;
		String[] filterValueArray = null;
		if (StringUtils.isNotBlank(originalFilters)) {
			String[] originalfilters = originalFilters.split("\\|");
			for (int i = 0; i < originalfilters.length; i++) {
				filter = originalfilters[i];
				filterValueArray = filter.split("_");
				if ("category".equals(filterValueArray[0])) {
					searchParam.setCategoryId(filterValueArray[1]);
					originalParam.setCategoryId(filterValueArray[1]);
				} else if ("brand".equals(filterValueArray[0])) {
					searchParam.setBrandId(filterValueArray[1]);
					originalParam.setBrandId(filterValueArray[1]);
				} else if ("size".equals(filterValueArray[0])) {
					searchParam.setSizeId(filterValueArray[1]);
				} else if ("color".equals(filterValueArray[0])) {
					searchParam.setColorId(filterValueArray[1]);
				} else if ("price".equals(filterValueArray[0])) {
					String price = filterValueArray[1];
					if(StringUtil.isNotEmpty(price)) 
						price=price.replace("10000", "");
					searchParam.setPriceId(price);
				} else if ("postArea".equals(filterValueArray[0])) {
					searchParam.setPostArea(filterValueArray[1]);
				} else if (filterValueArray[0].indexOf("dynamic") > -1) {
					dynamic = dynamic.append(filterValueArray[1]).append(",");
				}
			}
		}
		if (StringUtils.isNotBlank(dynamicFilters)) {
			String[] dynamicfilters = dynamicFilters.split("\\|");
			for (int j = 0; j < dynamicfilters.length; j++) {
				filter = dynamicfilters[j];
				filterValueArray = filter.split("_");
				if ("category".equals(filterValueArray[0])) {
					searchParam.setCategoryId(filterValueArray[1]);
				} else if ("brand".equals(filterValueArray[0])) {
					searchParam.setBrandId(filterValueArray[1]);
				} else if ("size".equals(filterValueArray[0])) {
					searchParam.setSizeId(filterValueArray[1]);
				} else if ("color".equals(filterValueArray[0])) {
					searchParam.setColorId(filterValueArray[1]);
				} else if ("price".equals(filterValueArray[0])) {
					String price = filterValueArray[1];
					if(StringUtil.isNotEmpty(price)) 
						price=price.replace("10000", "");
					searchParam.setPriceId(price);
				} else if ("postArea".equals(filterValueArray[0])) {
					searchParam.setPostArea(filterValueArray[1]);
				} else if (filterValueArray[0].indexOf("dynamic") > -1) {
					dynamic = dynamic.append(filterValueArray[1]).append(",");
				}
			}
			
		}
		String attributeId = dynamic.toString();
		if (StringUtils.isNotBlank(attributeId)) {
			attributeId = attributeId.substring(0, attributeId.length() - 1);
			searchParam.setAttributeId(attributeId);
		}
		// 分类末级才筛选属性
		if ("3".equals(source)) {
			if (searchParam.getCategoryId() != null) {
				if (searchParam.getCategoryId().indexOf("A01B03") == -1 && searchParam.getCategoryId().indexOf("A02B03") == -1) {
					if (searchParam.getCategoryId().length() != 12) {
						searchParam.setAttributeId("");
						searchParam.setSizeId("");
					}
				}
			}
		} else {
			if (searchParam.getCategoryId() == null || "".equals(searchParam.getCategoryId())) {
				searchParam.setAttributeId("");
				searchParam.setSizeId("");
			}
		}

		/* 以下是品类的封装 */
		List<SearchCategory> categoryList = searchResult.getCategoryList();
		List<SearchCategory> categoryList2 = searchResult.getCategoryList2();
		List<SearchCategory> categoryList3 = searchResult.getCategoryList3();
		if (categoryList != null && categoryList.size() > 0) {
			/**
			 * categoryList分类末级为1时，该不该展示， 情况一：originalParam原始参数和searchParam动态参数都为空时，即走搜索时，不展示
			 * 情况二：originalParam原始参数和searchParam动态参数相等时，即走分类时，不展示
			 * 情况三：originalParam原始参数里分类和品牌都存在时，即走通用规则时，不展示
			 */
			if (!((categoryList.size() == 1) && 
					((StringUtils.isBlank(originalParam.getCategoryId()) && StringUtils.isBlank(searchParam.getCategoryId())) 
							|| (StringUtils.isNotBlank(originalParam.getCategoryId()) && (originalParam.getCategoryId()).equals(searchParam.getCategoryId())) 
							|| (StringUtils.isNotBlank(originalParam.getCategoryId()) && StringUtils.isNotBlank(originalParam.getBrandId()))))) {
				searchAttribute = new SearchAttribute();
				searchAttribute.setName("category");
				searchAttribute.setDynamicName("category");
				searchAttribute.setIsMultiSelect("0");
				searchAttribute.setDesc("品类");
				List<SearchAttributeGroup> searchAttributeGroups = new ArrayList<SearchAttributeGroup>();
				if (!categoryList.isEmpty() && !categoryList2.isEmpty() && !categoryList3.isEmpty()) {
					Collections.sort(categoryList, new Comparator<SearchCategory>() {

						@Override
						public int compare(SearchCategory o1, SearchCategory o2) {
							return new BigDecimal(o1.getSortType()).compareTo(new BigDecimal(o2.getSortType()));
						}

					});

					Collections.sort(categoryList3, new Comparator<SearchCategory>() {

						@Override
						public int compare(SearchCategory o1, SearchCategory o2) {
							return new BigDecimal(o1.getSortType()).compareTo(new BigDecimal(o2.getSortType()));
						}

					});

					SearchCategory category = new SearchCategory();
					Map<String, List<SearchCategory>> resultMap = new HashMap<String, List<SearchCategory>>();
					for (int i = 0; i < categoryList.size(); i++) {
						category = categoryList.get(i);
						if (resultMap.containsKey(category.getParentId())) {
							resultMap.get(category.getParentId()).add(category);
						} else {
							List<SearchCategory> list = new ArrayList<SearchCategory>();
							list.add(category);
							resultMap.put(category.getParentId(), list);
						}
					}

					for (int j = 0; j < categoryList3.size(); j++) {
						SearchCategory category3 = categoryList3.get(j);
						SearchAttributeGroup group3 = new SearchAttributeGroup();
						List<SearchCategory> categorys = resultMap.get(category3.getId());
						if(CollectionUtils.isNotEmpty(categorys)) {
							List<SearchAttributeValue> searchAttributeValueList = new ArrayList<SearchAttributeValue>();
							for (int m = 0; m < categorys.size(); m++) {
								SearchCategory obj = categorys.get(m);
								SearchAttributeValue searchAttributeValue = new SearchAttributeValue();
								searchAttributeValue.setId(obj.getId());
								searchAttributeValue.setName(obj.getName());
								if (obj.getId().equals(searchParam.getCategoryId())) {
									searchAttributeValue.setIsChecked("1");
								} else {
									searchAttributeValue.setIsChecked("0");
								}
								searchAttributeValue.setExt(new ArrayList<SearchAttributeExt>());
								searchAttributeValueList.add(searchAttributeValue);
							}
							group3.setValue(searchAttributeValueList);
							group3.setParentId(category3.getParentId());
							group3.setName(category3.getName());
							searchAttributeGroups.add(group3);
						}
					}

					if (categoryList2.size() > 1) {
						Map<String, SearchCategory> map = new HashMap<String, SearchCategory>();
						List<SearchAttributeGroup> searchGroups = new ArrayList<SearchAttributeGroup>();
						for (SearchCategory searchCategory : categoryList2) {
							map.put(searchCategory.getId(), searchCategory);
						}
						for (int z = 0; z < searchAttributeGroups.size(); z++) {
							SearchAttributeGroup searchGroup = searchAttributeGroups.get(z);
							SearchAttributeGroup resultGroup = new SearchAttributeGroup();
							resultGroup.setParentSortType(map.get(searchGroup.getParentId()).getSortType());
							resultGroup.setName(map.get(searchGroup.getParentId()).getName() + "-" + searchGroup.getName());
							resultGroup.setValue(searchGroup.getValue());
							searchGroups.add(resultGroup);
						}
						Collections.sort(searchGroups, new Comparator<SearchAttributeGroup>() {

							@Override
							public int compare(SearchAttributeGroup o1, SearchAttributeGroup o2) {
								return new BigDecimal(o1.getParentSortType()).compareTo(new BigDecimal(o2.getParentSortType()));
							}

						});
						searchAttribute.setGroup(searchGroups);
					} else {
						searchAttribute.setGroup(searchAttributeGroups);
					}

				}
				// 2.9.5以前的版本要用
				List<SearchAttributeValue> searchList = new ArrayList<SearchAttributeValue>();
				for (int x = 0; x < categoryList.size(); x++) {
					SearchCategory sc = categoryList.get(x);
					SearchAttributeValue searchValue = new SearchAttributeValue();
					searchValue.setId(sc.getId());
					searchValue.setName(sc.getName());
					searchValue.setExt(new ArrayList<SearchAttributeExt>());
					searchList.add(searchValue);
				}

				searchAttribute.setValue(searchList);
				attributes.add(searchAttribute);
			}

		}
		/* 以下是品牌的封装 */
		List<SearchBrand> brandList = searchResult.getBrandList();
		if (!"2".equals(source) && brandList != null && brandList.size() > 0) {
			/**
			 * brandList为1时，该不该展示， 情况一：originalParam原始参数和searchParam动态参数都为空时，即走搜索时，不展示
			 * 情况二：originalParam原始参数和searchParam动态参数相等时，即走品牌时，不展示
			 * 情况三：originalParam原始参数里分类和品牌都存在时，即走通用规则时，不展示
			 */
			if (!((brandList.size() == 1) && 
					((StringUtils.isBlank(originalParam.getBrandId()) 
							&& StringUtils.isBlank(searchParam.getBrandId())) 
					|| (StringUtils.isNotBlank(originalParam.getBrandId()) 
							&& (originalParam.getBrandId()).equals(searchParam.getBrandId())) 
					|| (StringUtils.isNotBlank(originalParam.getBrandId()) && StringUtils.isNotBlank(originalParam.getCategoryId()))
					))) {
				SearchAttributeGroup searchAttributeGroup1 = new SearchAttributeGroup();
				List<SearchAttributeGroup> searchAttributeGroups1 = new ArrayList<SearchAttributeGroup>();
				searchAttribute = new SearchAttribute();
				searchAttribute.setName("brand");
				searchAttribute.setDynamicName("brand");
				searchAttribute.setIsMultiSelect("0");
				searchAttribute.setDesc("品牌");
				List<SearchAttributeValue> searchAttributeValueList = new ArrayList<SearchAttributeValue>();
				SearchAttributeExt searchAttributeExt = null;
				for (int i = 0; i < brandList.size(); i++) {
					SearchBrand searchBrand = brandList.get(i);
					SearchAttributeValue searchAttributeValue = new SearchAttributeValue();
					searchAttributeValue.setId(searchBrand.getId());
					searchAttributeValue.setName(searchBrand.getName());
					if (searchBrand.getId().equals(searchParam.getBrandId())) {
						searchAttributeValue.setIsChecked("1");
					} else {
						searchAttributeValue.setIsChecked("0");
					}
					List<SearchAttributeExt> searchAttributeExtList = new ArrayList<SearchAttributeExt>();
					searchAttributeExt = new SearchAttributeExt();
					searchAttributeExt.setName("nameEN");
					searchAttributeExt.setValue(searchBrand.getNameEN());
					searchAttributeExtList.add(searchAttributeExt);
					searchAttributeExt = new SearchAttributeExt();
					searchAttributeExt.setName("nameCN");
					searchAttributeExt.setValue(searchBrand.getNameCN());
					searchAttributeExtList.add(searchAttributeExt);
					searchAttributeValue.setExt(searchAttributeExtList);
					searchAttributeValueList.add(searchAttributeValue);

				}
				// SearchAttributeGroup searchAttributeGroup2 = new
				// SearchAttributeGroup();
				// searchAttributeGroup2.setName("热门品牌");
				// searchAttributeGroup2.setValue(searchAttributeValueList);
				// searchAttributeGroups1.add(searchAttributeGroup2);

				searchAttributeGroup1.setName("全部品牌");
				searchAttributeGroup1.setValue(searchAttributeValueList);
				searchAttributeGroups1.add(searchAttributeGroup1);

				searchAttribute.setValue(searchAttributeValueList);
				searchAttribute.setGroup(searchAttributeGroups1);
				attributes.add(searchAttribute);
			}
		}

		/* 以下是动态属性的封装 */
		List<SearchDynamicAttr> attrList = searchResult.getAttributes();
		if (attrList != null && attrList.size() > 0) {
			for (int i = 0; i < attrList.size(); i++) {
				SearchDynamicAttr searchDynamicAttr = attrList.get(i);
				if (searchDynamicAttr != null) {
					String type = searchDynamicAttr.getType();
					String dynamicType = searchDynamicAttr.getDynamicType();
					List<SearchDynamicValue> searchDynamicValueList = searchDynamicAttr.getSearchDynamicValue();
					if (searchDynamicValueList != null && searchDynamicValueList.size() > 0) {
						searchAttribute = new SearchAttribute();
						List<SearchAttributeValue> searchAttributeValueList = new ArrayList<SearchAttributeValue>();
						for (int j = 0; j < searchDynamicValueList.size(); j++) {
							SearchDynamicValue searchDynamicValue = searchDynamicValueList.get(j);
							SearchAttributeValue searchAttributeValue = new SearchAttributeValue();
							if ("attrId".equals(type) && (StringUtils.isNotBlank(dynamicType) && (dynamicType.indexOf("dynamic") > -1))) {
								searchAttributeValue.setId(searchDynamicValue.getId());
							} else {
								searchAttributeValue.setId(searchDynamicValue.getName());
							}
							if ("attrId".equals(searchDynamicAttr.getType()) && StringUtils.isNotBlank(searchParam.getAttributeId()) && (searchParam.getAttributeId().indexOf(searchDynamicValue.getId()) > -1)) {
								searchAttributeValue.setIsChecked("1");
							} else if ("size".equals(searchDynamicAttr.getType()) && StringUtils.isNotBlank(searchParam.getSizeId()) && (searchParam.getSizeId()).equals(searchDynamicValue.getName())) {
								searchAttributeValue.setIsChecked("1");
							} else {
								searchAttributeValue.setIsChecked("0");
							}
							searchAttributeValue.setName(searchDynamicValue.getName());
							searchAttributeValue.setExt(new ArrayList<SearchAttributeExt>());
							searchAttributeValueList.add(searchAttributeValue);
						}
						//名称
						if ("attrId".equals(type) && (StringUtils.isNotBlank(dynamicType) && (dynamicType.indexOf("dynamic") > -1))) {
							searchAttribute.setName("attribute");
							searchAttribute.setDynamicName(dynamicType);
							searchAttribute.setIsMultiSelect("0");
							searchAttribute.setDesc(searchDynamicAttr.getName());
						} else {
							searchAttribute.setName("size");
							searchAttribute.setDynamicName("size");
							searchAttribute.setIsMultiSelect("0");
							searchAttribute.setDesc(searchDynamicAttr.getName());
						}

						List<SearchAttributeValue> selecteds = exitsSearchParams(searchAttributeValueList,searchParam,searchDynamicAttr.getType());
						if(!selecteds.isEmpty()){
							//重新设置单个的选中值
							searchAttributeValueList = new ArrayList<SearchAttributeValue>();
							searchAttributeValueList.addAll(selecteds);
							
							SearchAttributeGroup searchAttributeGroup2 = new SearchAttributeGroup();
							List<SearchAttributeGroup> searchAttributeGroups2 = new ArrayList<SearchAttributeGroup>();
							searchAttributeGroup2.setName("");
							searchAttributeGroup2.setValue(searchAttributeValueList);
							searchAttributeGroups2.add(searchAttributeGroup2);
							//选择值
							searchAttribute.setValue(searchAttributeValueList);
							searchAttribute.setGroup(searchAttributeGroups2);
							attributes.add(searchAttribute);
						} else if(searchAttributeValueList.size() > 1) {
							SearchAttributeGroup searchAttributeGroup2 = new SearchAttributeGroup();
							List<SearchAttributeGroup> searchAttributeGroups2 = new ArrayList<SearchAttributeGroup>();
							searchAttributeGroup2.setName("");
							searchAttributeGroup2.setValue(searchAttributeValueList);//所有选则值
							searchAttributeGroups2.add(searchAttributeGroup2);
							//选择值
							searchAttribute.setValue(searchAttributeValueList);
							searchAttribute.setGroup(searchAttributeGroups2);
							attributes.add(searchAttribute);
						}
						
					}
				}
			}
		}
		/*List<SearchDynamicAttr> attrList = searchResult.getAttributes();
		if (attrList != null && attrList.size() > 0) {
			for (int i = 0; i < attrList.size(); i++) {
				SearchDynamicAttr searchDynamicAttr = attrList.get(i);
				if (searchDynamicAttr != null) {
					String type = searchDynamicAttr.getType();
					String dynamicType = searchDynamicAttr.getDynamicType();
					List<SearchDynamicValue> searchDynamicValueList = searchDynamicAttr.getSearchDynamicValue();
					if (searchDynamicValueList != null && searchDynamicValueList.size() > 0) {
						searchAttribute = new SearchAttribute();
						SearchAttributeGroup searchAttributeGroup2 = new SearchAttributeGroup();
						List<SearchAttributeGroup> searchAttributeGroups2 = new ArrayList<SearchAttributeGroup>();

						List<SearchAttributeValue> SearchAttributeValueList = new ArrayList<SearchAttributeValue>();
						for (int j = 0; j < searchDynamicValueList.size(); j++) {
							SearchDynamicValue searchDynamicValue = searchDynamicValueList.get(j);
							SearchAttributeValue searchAttributeValue = new SearchAttributeValue();
							if ("attrId".equals(type) && (StringUtils.isNotBlank(dynamicType) && (dynamicType.indexOf("dynamic") > -1))) {
								searchAttributeValue.setId(searchDynamicValue.getId());
							} else {
								searchAttributeValue.setId(searchDynamicValue.getName());
							}
							if (StringUtils.isNotBlank(searchParam.getAttributeId()) && (searchParam.getAttributeId().indexOf(searchDynamicValue.getId()) > -1)) {
								searchAttributeValue.setIsChecked("1");
							} else if (StringUtils.isNotBlank(searchParam.getSizeId()) && (searchParam.getSizeId()).equals(searchDynamicValue.getName())) {
								searchAttributeValue.setIsChecked("1");
							} else {
								searchAttributeValue.setIsChecked("0");
							}
							searchAttributeValue.setName(searchDynamicValue.getName());
							searchAttributeValue.setExt(new ArrayList<SearchAttributeExt>());
							SearchAttributeValueList.add(searchAttributeValue);
						}
						if (!SearchAttributeValueList.isEmpty()) {
							if ("attrId".equals(type) && (StringUtils.isNotBlank(dynamicType) && (dynamicType.indexOf("dynamic") > -1))) {
								searchAttribute.setName("attribute");
								searchAttribute.setDynamicName(dynamicType);
								searchAttribute.setIsMultiSelect("0");
								searchAttribute.setDesc(searchDynamicAttr.getName());
							} else {
								searchAttribute.setName("size");
								searchAttribute.setDynamicName("size");
								searchAttribute.setIsMultiSelect("0");
								searchAttribute.setDesc(searchDynamicAttr.getName());
							}
							searchAttributeGroup2.setName("");
							searchAttributeGroup2.setValue(SearchAttributeValueList);
							searchAttributeGroups2.add(searchAttributeGroup2);
							searchAttribute.setValue(SearchAttributeValueList);
							searchAttribute.setGroup(searchAttributeGroups2);
						}

						attributes.add(searchAttribute);
					}
				}
			}
		}*/
		return attributes;
	}
	
	/**
	 * 判断属性是否选择
	 * @param SearchAttributeValueList 属性值
	 * @param searchParam 查询参数
	 * @param attrType 参数类型
	 * @return
	 */
	private static List<SearchAttributeValue> exitsSearchParams(List<SearchAttributeValue> SearchAttributeValueList,
			SearchParam searchParam,String attrType) {
		List<SearchAttributeValue> values = new ArrayList<SearchAttributeValue>();
		for (SearchAttributeValue sav : SearchAttributeValueList) {
			if("attrId".equals(attrType) && StringUtils.isNotBlank(searchParam.getAttributeId())) {
				List<String> ids = Arrays.asList(searchParam.getAttributeId().split(","));
				for(String id: ids) {
					List<String> multIds = Arrays.asList(id.split("-"));
					if(id.equals(sav.getId()) || multIds.contains(sav.getId())){
						values.add(sav);
					}
				}
			} 
			if("size".equals(attrType) && StringUtils.isNotBlank(searchParam.getSizeId())) {
				List<String> ids = Arrays.asList(searchParam.getSizeId().split(","));
				for(String id: ids) {
					List<String> multIds = Arrays.asList(id.split("-"));
					if(id.equals(sav.getId()) || multIds.contains(sav.getId())){
						values.add(sav);
					}
				}
			}
		}
		return values;
	}
	
	/**
	 * 搜索属性对象转换客户端格式筛选页
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws DocumentException
	 */
	private static List<SearchAttribute> searchByfiltrates(SearchResult searchResult) {
		List<SearchAttribute> attributes = new ArrayList<SearchAttribute>();
		SearchAttribute searchAttribute = null;
		if (!"0".equals(searchResult.getCount())) {
			/* 以下是价格的封装 */
			if (searchResult.getProductList() != null) {
				searchAttribute = new SearchAttribute();
				searchAttribute.setName("price");
				searchAttribute.setIsMultiSelect("0");
				searchAttribute.setDesc("价格区间");
				List<SearchAttributeValue> searchAttributeValueList = new ArrayList<SearchAttributeValue>();
				SearchAttributeValue searchAttributeValue = null;
				List<SearchAttributeExt> defaultExtList = new ArrayList<SearchAttributeExt>();
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("0");
				searchAttributeValue.setName("0");
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("500");
				searchAttributeValue.setName("500");
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("1000");
				searchAttributeValue.setName("1000");
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("2000");
				searchAttributeValue.setName("2000");
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("5000");
				searchAttributeValue.setName("5000");
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("10000");
				searchAttributeValue.setName("10000");
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("10000");
				searchAttributeValue.setName(">10000");
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttribute.setValue(searchAttributeValueList);
				attributes.add(searchAttribute);
			}
			/* 以下是颜色的封装 */
			List<SearchColor> colorList = searchResult.getColorList();
			if (colorList != null && colorList.size() > 0) {
				searchAttribute = new SearchAttribute();
				searchAttribute.setName("color");
				searchAttribute.setIsMultiSelect("1");
				searchAttribute.setDesc("颜色");
				List<SearchAttributeValue> searchAttributeValueList = new ArrayList<SearchAttributeValue>();
				SearchAttributeExt searchAttributeExt = null;
				for (int i = 0; i < colorList.size(); i++) {
					SearchColor searchColor = colorList.get(i);
					SearchAttributeValue searchAttributeValue = new SearchAttributeValue();
					searchAttributeValue.setId(searchColor.getId());
					searchAttributeValue.setName(searchColor.getName());
					List<SearchAttributeExt> searchAttributeExtList = new ArrayList<SearchAttributeExt>();
					searchAttributeExt = new SearchAttributeExt();
					searchAttributeExt.setName("rgb");
					searchAttributeExt.setValue(searchColor.getRgb());
					searchAttributeExtList.add(searchAttributeExt);
					searchAttributeValue.setExt(searchAttributeExtList);
					searchAttributeValueList.add(searchAttributeValue);
				}
				searchAttribute.setValue(searchAttributeValueList);
				attributes.add(searchAttribute);
			}
			/* 以下是发货地的封装 */
			String aBroad = searchResult.getAbroad();
			SearchAttributeValue searchAttributeValue = null;
			List<SearchAttributeExt> searchAttributeExtList = null;
			if (aBroad != null) {
				searchAttribute = new SearchAttribute();
				searchAttribute.setName("postArea");
				searchAttribute.setIsMultiSelect("0");
				searchAttribute.setDesc("发货地");
				List<SearchAttributeValue> searchAttributeValueList = new ArrayList<SearchAttributeValue>();
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("1");
				searchAttributeValue.setName("国内");
				searchAttributeExtList = new ArrayList<SearchAttributeExt>();
				searchAttributeValue.setExt(searchAttributeExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("2");
				searchAttributeValue.setName("海外");
				searchAttributeExtList = new ArrayList<SearchAttributeExt>();
				searchAttributeValue.setExt(searchAttributeExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttribute.setValue(searchAttributeValueList);
				attributes.add(searchAttribute);
			}
		}
		return attributes;
	}
	
	private static List<SearchAttribute> searchByfiltrates(SearchResult searchResult, String source, String originalFilters, String dynamicFilters) {
		List<SearchAttribute> attributes = new ArrayList<SearchAttribute>();
		SearchAttribute searchAttribute = null;
		if (!"0".equals(searchResult.getCount())) {
			// 处理客户端请求逻辑
			SearchParam searchParam = new SearchParam();
			StringBuffer dynamic = new StringBuffer();
			String filter = null;
			String[] filterValueArray = null;
			if (StringUtils.isNotBlank(originalFilters)) {
				String[] originalfilters = originalFilters.split("\\|");
				for (int i = 0; i < originalfilters.length; i++) {
					filter = originalfilters[i];
					filterValueArray = filter.split("_");
					if ("category".equals(filterValueArray[0])) {
						searchParam.setCategoryId(filterValueArray[1]);
					} else if ("brand".equals(filterValueArray[0])) {
						searchParam.setBrandId(filterValueArray[1]);
					} else if ("size".equals(filterValueArray[0])) {
						searchParam.setSizeId(filterValueArray[1]);
					} else if ("color".equals(filterValueArray[0])) {
						searchParam.setColorId(filterValueArray[1]);
					} else if ("price".equals(filterValueArray[0])) {
						String price = filterValueArray[1];
						if(StringUtil.isNotEmpty(price)) 
							price=price.replace("10000", "");
						searchParam.setPriceId(price);
					} else if ("postArea".equals(filterValueArray[0])) {
						searchParam.setPostArea(filterValueArray[1]);
					} else if (filterValueArray[0].indexOf("dynamic") > -1) {
						dynamic = dynamic.append(filterValueArray[1]).append(",");
					}
				}
			}
			if (StringUtils.isNotBlank(dynamicFilters)) {
				String[] dynamicfilters = dynamicFilters.split("\\|");
				for (int j = 0; j < dynamicfilters.length; j++) {
					filter = dynamicfilters[j];
					filterValueArray = filter.split("_");
					if ("category".equals(filterValueArray[0])) {
						searchParam.setCategoryId(filterValueArray[1]);
					} else if ("brand".equals(filterValueArray[0])) {
						searchParam.setBrandId(filterValueArray[1]);
					} else if ("size".equals(filterValueArray[0])) {
						searchParam.setSizeId(filterValueArray[1]);
					} else if ("color".equals(filterValueArray[0])) {
						searchParam.setColorId(filterValueArray[1]);
					} else if ("price".equals(filterValueArray[0])) {
						String price = filterValueArray[1];
						if(StringUtil.isNotEmpty(price)) 
							price=price.replace("10000", "");
						searchParam.setPriceId(price);
					} else if ("postArea".equals(filterValueArray[0])) {
						searchParam.setPostArea(filterValueArray[1]);
					} else if (filterValueArray[0].indexOf("dynamic") > -1) {
						dynamic = dynamic.append(filterValueArray[1]).append(",");
					}
				}
				
			}
			String attributeId = dynamic.toString();
			if (StringUtils.isNotBlank(attributeId)) {
				attributeId = attributeId.substring(0, attributeId.length() - 1);
				searchParam.setAttributeId(attributeId);
			}
			// 分类末级才筛选属性
			if ("3".equals(source)) {
				if (searchParam.getCategoryId() != null) {
					if (searchParam.getCategoryId().indexOf("A01B03") == -1 && searchParam.getCategoryId().indexOf("A02B03") == -1) {
						if (searchParam.getCategoryId().length() != 12) {
							searchParam.setAttributeId("");
							searchParam.setSizeId("");
						}
					}
				}
			} else {
				if (searchParam.getCategoryId() == null || "".equals(searchParam.getCategoryId())) {
					searchParam.setAttributeId("");
					searchParam.setSizeId("");
				}
			}

			/* 以下是价格的封装 */
			if (searchResult.getProductList() != null) {
				searchAttribute = new SearchAttribute();
				searchAttribute.setName("price");
				searchAttribute.setIsMultiSelect("0");
				searchAttribute.setDesc("价格区间");
				List<SearchAttributeValue> searchAttributeValueList = new ArrayList<SearchAttributeValue>();
				SearchAttributeValue searchAttributeValue = null;
				List<SearchAttributeExt> defaultExtList = new ArrayList<SearchAttributeExt>();
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("0");
				searchAttributeValue.setName("0");
				String priceId = searchParam.getPriceId();
				String lowPrice = "";
				String highPrice = "";
				if(!org.springframework.util.StringUtils.isEmpty(priceId)){
					String[] priceArr = priceId.split("\\~");
					if(priceArr.length == 2){
						lowPrice = priceArr[0];
						highPrice = priceArr[1];
					}else{
						lowPrice = priceArr[0];
						highPrice = lowPrice;
					}
				}
				
				if ("0".equals(lowPrice)) {
					searchAttributeValue.setIsChecked("1");
				} else {
					searchAttributeValue.setIsChecked("0");
				}
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("500");
				searchAttributeValue.setName("500");
				if ("500".equals(lowPrice) || "500".equals(highPrice)) {
					searchAttributeValue.setIsChecked("1");
				} else {
					searchAttributeValue.setIsChecked("0");
				}
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("1000");
				searchAttributeValue.setName("1000");
				if ("1000".equals(lowPrice) || "1000".equals(highPrice)) {
					searchAttributeValue.setIsChecked("1");
				} else {
					searchAttributeValue.setIsChecked("0");
				}
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("2000");
				searchAttributeValue.setName("2000");
				if ("2000".equals(lowPrice) || "2000".equals(highPrice)) {
					searchAttributeValue.setIsChecked("1");
				} else {
					searchAttributeValue.setIsChecked("0");
				}
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("5000");
				searchAttributeValue.setName("5000");
				if ("5000".equals(lowPrice) || "5000".equals(highPrice)) {
					searchAttributeValue.setIsChecked("1");
				} else {
					searchAttributeValue.setIsChecked("0");
				}
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("10000");
				searchAttributeValue.setName("10000");
				if ("10000".equals(lowPrice) || "10000".equals(highPrice)) {
					searchAttributeValue.setIsChecked("1");
				} else {
					searchAttributeValue.setIsChecked("0");
				}
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("10000");
				searchAttributeValue.setName(">10000");
				if ("0".equals(lowPrice)) {
					searchAttributeValue.setIsChecked("1");
				} else {
					searchAttributeValue.setIsChecked("0");
				}
				searchAttributeValue.setExt(defaultExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttribute.setValue(searchAttributeValueList);
				attributes.add(searchAttribute);
			}
			/* 以下是颜色的封装 */
			List<SearchColor> colorList = searchResult.getColorList();
			if (colorList != null && colorList.size() > 0) {
				searchAttribute = new SearchAttribute();
				searchAttribute.setName("color");
				searchAttribute.setIsMultiSelect("1");
				searchAttribute.setDesc("颜色");
				List<SearchAttributeValue> searchAttributeValueList = new ArrayList<SearchAttributeValue>();
				SearchAttributeExt searchAttributeExt = null;
				//颜色多选处理
				String colorId = searchParam.getColorId();
				List<String> selectedIds = new ArrayList<String>();
				if(StringUtil.isNotEmpty(colorId)) {
					selectedIds = Arrays.asList(colorId.split(","));
				}
				for (int i = 0; i < colorList.size(); i++) {
					SearchColor searchColor = colorList.get(i);
					SearchAttributeValue searchAttributeValue = new SearchAttributeValue();
					searchAttributeValue.setId(searchColor.getId());
					searchAttributeValue.setName(searchColor.getName());
					if (selectedIds.contains(searchColor.getId())) {
						searchAttributeValue.setIsChecked("1");
					} else {
						searchAttributeValue.setIsChecked("0");
					}
					List<SearchAttributeExt> searchAttributeExtList = new ArrayList<SearchAttributeExt>();
					searchAttributeExt = new SearchAttributeExt();
					searchAttributeExt.setName("rgb");
					searchAttributeExt.setValue(searchColor.getRgb());
					searchAttributeExtList.add(searchAttributeExt);
					searchAttributeValue.setExt(searchAttributeExtList);
					searchAttributeValueList.add(searchAttributeValue);
				}
				searchAttribute.setValue(searchAttributeValueList);
				attributes.add(searchAttribute);
			}
			/* 以下是发货地的封装 */
			String aBroad = searchResult.getAbroad();
			SearchAttributeValue searchAttributeValue = null;
			List<SearchAttributeExt> searchAttributeExtList = null;
			if (aBroad != null) {
				searchAttribute = new SearchAttribute();
				searchAttribute.setName("postArea");
				searchAttribute.setIsMultiSelect("0");
				searchAttribute.setDesc("发货地");
				List<SearchAttributeValue> searchAttributeValueList = new ArrayList<SearchAttributeValue>();
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("1");
				searchAttributeValue.setName("国内");
				if ("1".equals(searchParam.getPostArea())) {
					searchAttributeValue.setIsChecked("1");
				} else {
					searchAttributeValue.setIsChecked("0");
				}
				searchAttributeExtList = new ArrayList<SearchAttributeExt>();
				searchAttributeValue.setExt(searchAttributeExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttributeValue = new SearchAttributeValue();
				searchAttributeValue.setId("2");
				searchAttributeValue.setName("海外");
				if ("2".equals(searchParam.getPostArea())) {
					searchAttributeValue.setIsChecked("1");
				} else {
					searchAttributeValue.setIsChecked("0");
				}
				searchAttributeExtList = new ArrayList<SearchAttributeExt>();
				searchAttributeValue.setExt(searchAttributeExtList);
				searchAttributeValueList.add(searchAttributeValue);
				searchAttribute.setValue(searchAttributeValueList);
				attributes.add(searchAttribute);
			}
		}
		return attributes;
	}
	
	/**
	 * 将搜索返回的json列表转换为product列表
	 * @param productList 搜索的product集合列表
	 * @param fromType 来源 1:活动;2:品牌;3:品类;4:标签;5:关键字
	 * @param activityHead 当fromType为1时，传值，其他传null
	 * @return
	 */
	public static List<Product> initJsonProductList(List<ProductSreach> sreachList, String fromType, ActivityHead activityHead) {
		List<Product> productList = new ArrayList<Product>();
		if (sreachList != null && sreachList.size() > 0) {
			for (ProductSreach search : sreachList) {
				Product product = new Product();
				product.setProductStatus("0");
				int stock = 0;
				product.setProductId(search.getProductNo());
				product.setProductName(search.getProductName());
				product.setMarketPrice(StringUtil.isNotEmpty(search.getMarketPrice())?removeDecimal(search.getMarketPrice()):"");
				product.setLimitedPrice(StringUtil.isNotEmpty(search.getLimitedPrice())?removeDecimal(search.getLimitedPrice()):"");
				product.setGoldPrice(StringUtil.isNotEmpty(search.getSellPrice())?removeDecimal(search.getSellPrice()):"");
				product.setPlatinumPrice(StringUtil.isNotEmpty(search.getPlatinumPrice())?removeDecimal(search.getPlatinumPrice()):"");
				product.setDiamondPrice(StringUtil.isNotEmpty(search.getDiamondPrice())?removeDecimal(search.getDiamondPrice()):"");
				product.setPic(StringUtil.isNotEmpty(search.getProductPicFile())?PicCdnHash.getPicUrl(search.getProductPicFile(), "1"):"");
				product.setPicNo(search.getProductPicFile());
				product.setProductModelPicFile(search.getProductModelPicFile());
				product.setBrandNo(search.getBrandNo());
				product.setBrandNameEN(search.getBrandEnName());
				product.setBrandNameCN(search.getBrandCnName());
				product.setCategoryNo(search.getCategoryNo());
				// 这个值以后不用了 写死为1
				product.setIsSupportDiscount(search.getIsSupportDiscount());
					
					stock = Integer.parseInt(search.getAvailableStock());//有效库存数
					if (stock == 0) {
						product.setProductStatus("1");
					}
					product.setCount(search.getAvailableStock());
					product.setErpCategoryNo(search.getErpCategoryNo());
					product.setPromotionPrice(StringUtil.isNotEmpty(search.getPromotionPrice())?removeDecimal(search.getPromotionPrice()):"");
					product.setPostArea(search.getPostArea());
					product.setPrefix(search.getPrefix());
					product.setSuffix(search.getSuffix());
					product.setPromotionNotice(search.getPromotionNotice());
					
					if (!("".equals(search.getPostAreaPic()))) {
						product.setCountryPic(Constants.COUNTRYPIC_URL + search.getPostAreaPic());
					} else {
						product.setCountryPic("");
					}
					if (stock != 0) {
						if ("1".equals(search.getIsNewSeasonal())) {
							product.setProductStatus("2");
						}
					}
					product.setIsPromotion(search.getIsPromotion());
					product.setNewLimitedPrice(StringUtil.isNotEmpty(search.getNewLimitedPrice())?removeDecimal(search.getNewLimitedPrice()):"");
					product.setNewSellPrice(StringUtil.isNotEmpty(search.getNewSellPrice())?removeDecimal(search.getNewSellPrice()):"");
					product.setNewPlatinumPrice(StringUtil.isNotEmpty(search.getNewPlatinumPrice())?removeDecimal(search.getNewPlatinumPrice()):"");
					product.setNewDiamondPrice(StringUtil.isNotEmpty(search.getNewDiamondPrice())?removeDecimal(search.getNewDiamondPrice()):"");

				// 2015.11.24-实时变价，促销失效
				if (StringUtil.compareDate(Constants.ACTIVITY_SALES_START, Constants.ACTIVITY_SALES_END, DateUtils.dateToStr(new Date())) == 0) {
					product.setLimitedPrice(StringUtil.isNotEmpty(search.getNewLimitedPrice())?removeDecimal(search.getNewLimitedPrice()):"");
					product.setSellPrice(StringUtil.isNotEmpty(search.getNewSellPrice())?removeDecimal(search.getNewSellPrice()):"");
					product.setPlatinumPrice(StringUtil.isNotEmpty(search.getNewPlatinumPrice())?removeDecimal(search.getNewPlatinumPrice()):"");
					product.setDiamondPrice(StringUtil.isNotEmpty(search.getNewDiamondPrice())?removeDecimal(search.getNewDiamondPrice()):"");
					product.setIsPromotion("0");
				}

				// 这个值以后不用了 写死为100000 普通
				product.setStatus("100000");
				//推荐去掉这两个值
			/*	product.setComments("-1");
				product.setCollections("-1");*/
				// product.setPromoLogo(Constants.DOUBLE_ELE_URL);
				product = getPromionProduct(product, fromType, activityHead);
				productList.add(product);
				}
			}
		return productList;
	}
	
	
	
	public static Product getPromionProduct(Product product,String fromType,ActivityHead activityHead){
		String priceTitle = "";// 价格title
		String priceColor = "#2d2d2d";// 价格颜色
		String descColor = "";// 描述颜色
		String priceDesc = ""; // 价格描述
		String expressLogo = "";// 直发图片
		if (product != null) {
			StringBuffer descBuffer = null;
			String promotionPrice = product.getPromotionPrice();
			String diamondPrice = product.getDiamondPrice();
			String newLimitedPrice = product.getNewLimitedPrice();
			// 2015.11.24-实时变价，预热阶段
			if (StringUtil.compareDate(Constants.ACTIVITY_READY_START, Constants.ACTIVITY_READY_END, DateUtils.dateToStr(new Date())) == 0) {
				if ("1".equals(product.getIsPromotion())) {
					priceColor = "#c62026";
				} else {
					if (StringUtil.isNotEmpty(promotionPrice) && (Integer.parseInt(promotionPrice) < Integer.parseInt(diamondPrice))) {
						descColor = "#c62026";
						descBuffer = new StringBuffer();
						descBuffer.append(Constants.PRICE_ACTIVITY);
						descBuffer.append("¥" + promotionPrice);
						priceDesc = descBuffer.toString();
					}
				}
			}
			// 2015.11.24-实时变价，活动阶段
			else if (StringUtil.compareDate(Constants.ACTIVITY_OPEN_START, Constants.ACTIVITY_OPEN_END, DateUtils.dateToStr(new Date())) == 0) {
				if ("1".equals(product.getIsPromotion())) {
					priceTitle = Constants.PRICE_ACTIVITY;
					priceColor = "#c62026";
					if (StringUtil.isNotEmpty(promotionPrice) && (Integer.parseInt(promotionPrice) < Integer.parseInt(newLimitedPrice))) {
						descColor = "#888888";
						descBuffer = new StringBuffer();
						descBuffer.append(Constants.PRICE_COMPARE);
						descBuffer.append("¥" + newLimitedPrice);
						priceDesc = descBuffer.toString();
					}
					product.setPromoLogo(Constants.IFC_KHJ_URL);
				}
			}
			// 原逻辑
			else {
				if ("2".equals(product.getPostArea())) {
					// #2d2d2d 黑色
					// #888888 灰色
					// #c62026 红色
					descColor = "#888888";
					int cheapPrice = 0;
					cheapPrice = Integer.parseInt(product.getMarketPrice()) - Integer.parseInt(product.getGoldPrice());
					String countryPic = product.getCountryPic();
					StringBuffer stringBuffer = new StringBuffer();
					if (countryPic != null) {
						if (cheapPrice > 0) {
							stringBuffer.append("省¥" + cheapPrice);
						}							
					}
					if ("1".equals(product.getIsPromotion())) {
                        priceColor = "#c62026";
                    }
				} else {
					if ("1".equals(product.getIsPromotion())) {
						priceColor = "#c62026";
						descColor = "#888888";
						// priceTitle="促销价:";//上线不显示
						descBuffer = new StringBuffer();
						if (Integer.parseInt(product.getLimitedPrice()) < Integer.parseInt(product.getMarketPrice())) {
							descBuffer.append("市场价:");
							descBuffer.append("¥" + product.getMarketPrice());
							// priceDesc=descBuffer.toString();
						}
					}
				}
				// 活动有预热单独处理
				if ("1".equals(fromType)) {
					if (activityHead != null) {
						if ("1".equals(activityHead.getIsPre())) {
							if (!"1".equals(product.getIsPromotion())) {
								priceColor = "#888888";
							}
							descColor = "#c62026";
							descBuffer = new StringBuffer();
							if ("1".equals(activityHead.getPriceTag())) {
								descBuffer.append(activityHead.getPriceDesc());
								descBuffer.append(":¥" + promotionPrice);
								priceDesc = descBuffer.toString();
							} else if ("2".equals(activityHead.getPriceTag())) {
								descBuffer.append(activityHead.getPriceDesc());
								priceDesc = descBuffer.toString();
							}
						}
					}
				}

			}
			if ("2".equals(product.getPostArea())) {
				String countryPic = product.getCountryPic();
				if (countryPic != null) {
					if (countryPic.indexOf("yidali") > -1 || countryPic.indexOf("yingguo") > -1) {
						expressLogo = Constants.OZ_URL;
					} else if (countryPic.indexOf("xinjiapo") > -1) {
						expressLogo = Constants.IFC_XJP_URL;
					} else if (countryPic.indexOf("meiguo") > -1) {
						expressLogo = Constants.MG_URL;
					} else if (countryPic.indexOf("hanguo") > -1) {
						expressLogo = Constants.HG_URL;
					} else if (countryPic.indexOf("xianggang") > -1) {
						expressLogo = Constants.XG_URL;
					}
				}
			}
		}
		product.setCountryPic("");// 占时去掉国旗标
		product.setPriceColor(priceColor);
		product.setDescColor(descColor);
		product.setExpressLogo(expressLogo);
		product.setPriceTitle(priceTitle);
		product.setPriceDesc(priceDesc);
		
		return product;
	}

	
}
