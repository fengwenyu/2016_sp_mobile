package com.shangpin.base.service.impl;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.SearchService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.base.vo.Brand;
import com.shangpin.base.vo.Category;
import com.shangpin.base.vo.Color;
import com.shangpin.base.vo.Product;
import com.shangpin.base.vo.SearchHotWords;
import com.shangpin.base.vo.SearchResult;
import com.shangpin.base.vo.Size;
import com.shangpin.utils.CDNHash;
import com.shangpin.utils.HttpClientUtil;

@Service
public class SearchServiceImpl implements SearchService {

	private static CommonService commonService;
	// 品牌商品列表
	private StringBuffer searchBrandProductURL = new StringBuffer(GlobalConstants.BASE_URL_SEARCH).append("BrandProductList");
	// 品类商品列表
	private StringBuffer searchCategoryProductURL = new StringBuffer(GlobalConstants.BASE_URL_SEARCH).append("CategoryProductList");
	// 关键字搜索商品列表
	private StringBuffer searchKeyWordProductURL = new StringBuffer(GlobalConstants.BASE_URL_SEARCH).append("Search");
	// 分类查询，主站数据库获取
	private StringBuffer catrgoryURL = new StringBuffer(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/queryCategory");
	// 分类查询,通过搜索接口获取
	private StringBuffer catrgorySearchURL = new StringBuffer(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/QueryCategorylist");
	// 2.9.0版本以后分类查询,通过搜索接口获取
	private StringBuffer catrgorysURL = new StringBuffer(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/queryCategorys");
	// 搜索新品商品列表
	private StringBuffer searchNewProductURL = new StringBuffer(GlobalConstants.BASE_URL_SEARCH).append("NewestList");
	// 搜索活动商品列表
	private StringBuffer searchActivityProductURL = new StringBuffer(GlobalConstants.BASE_URL_SEARCH).append("SubjectProductList");
	// 搜索关键词智能联想
	private StringBuffer searchSuggestionURL = new StringBuffer(GlobalConstants.BASE_URL_SEARCH).append("Suggest");
	// 标签商品搜索接口
	private StringBuffer searchTagProductListURL = new StringBuffer(GlobalConstants.BASE_URL_SEARCH).append("LabelProductList");
	// 2.9.0首页、分类标签商品搜索接口
	private StringBuffer searchLabelsURL = new StringBuffer(GlobalConstants.BASE_URL_SEARCH).append("RecommendLabelList");
	
	//批量搜索商品
    private StringBuffer subjectProductListURL = new StringBuffer(GlobalConstants.BASE_URL_SEARCH).append("Product");
    //查询某个商品是否在一个活动中
    private StringBuffer searchSpuIsInTopicURL = new StringBuffer(GlobalConstants.BASE_URL_SEARCH).append("SubjectContainProductNo");

	private boolean isEmpty(String str) {
		if (str != null && !"".equals(str.trim()) && !"null".equals(str.trim())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 是否取模特图
	 * 
	 * @param isModelPic
	 *            ,erpCategoryNo
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean isModelPic(String erpCategoryNo) {
		if (erpCategoryNo != null && erpCategoryNo.startsWith("A01")) {
			return true;
		}
		return false;
	}

	/**
	 * 获取图片url
	 * 
	 * @author wangfeng
	 * @createDate 2014-12-22
	 * @param picNo
	 *            ,type 1 商品，2 品牌
	 */
	private String getPicUrl(String picNo, String type) {
		StringBuffer picBuffer = new StringBuffer("");
		picBuffer.append(CDNHash.getUrlHash(picNo));
		if ("1".equals(type)) {
			picBuffer.append("/f/p/");
		} else if ("2".equals(type)) {
			picBuffer.append("/e/s/");
		}
		picBuffer.append(picNo.substring(2, 4));
		picBuffer.append("/");
		picBuffer.append(picNo.substring(4, 6));
		picBuffer.append("/");
		picBuffer.append(picNo.substring(6, 8));
		picBuffer.append("/");
		picBuffer.append(picNo);
		picBuffer.append("-10-10.jpg");
		return picBuffer.toString();
	}

	private HashMap<String, String> initQueryMap(String keyword, String subjectNO, String productLabelNO, String brandNO, String categoryNO, String navigationID,
			String primaryColorId, String price, String productSize, String order, String pageIndex, String pageSize, String gender, String userLv, String postArea, String isPre,
			String attrId) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (!isEmpty(navigationID)) {
			map.put("navigationID", navigationID);
		}
		if (!isEmpty(productSize)) {
			map.put("productSize", productSize);
		}
		if (!isEmpty(primaryColorId)) {
			map.put("primaryColorId", primaryColorId);
		}
		if (!isEmpty(productLabelNO)) {
			map.put("productLabelNO", productLabelNO);
		}
		if (!isEmpty(brandNO)) {
			map.put("brandNO", brandNO);
		}
		if (!isEmpty(categoryNO)) {
			map.put("categoryNO", categoryNO);
		}
		if (!isEmpty(subjectNO)) {
			map.put("subjectNO", subjectNO);
		}
		if (!isEmpty(isPre)) {
			map.put("isPreHot", isPre);
		}
		if (!isEmpty(attrId)) {
			map.put("attrId", attrId);
		}
		if (!isEmpty(pageIndex) && !isEmpty(pageSize)) {
			try {
				int pageStart = (Integer.parseInt(pageIndex) - 1) * Integer.parseInt(pageSize) + 1;
				int pageEnd = pageStart + Integer.parseInt(pageSize) - 1;
				map.put("start", String.valueOf(pageStart));
				map.put("end", String.valueOf(pageEnd));
			} catch (Exception e) {
			}
		}
		if (!isEmpty(userLv)) {
			if (userLv.equals("0002")) {
				map.put("userType", "gold");
			} else if (userLv.equals("0003")) {
				map.put("userType", "platinum");
			} else if (userLv.equals("0004")) {
				map.put("userType", "diamond");
			}
		}
		if (order != null) {
			if (order.equals("3")) {
				map.put("order", "newest desc");
			} else if (order.equals("1")) {
				map.put("order", "price desc");
			} else if (order.equals("2")) {
				map.put("order", "price asc");
			} else if (order.equals("4")) {
				map.put("order", "Hot");
			} else if (order.equals("5")) {
				map.put("order", "sales desc");
			} else if (order.equals("6")) {
				map.put("order", "sales asc");
			} else if (order.equals("7")) {
				map.put("order", "discount desc");
			} else if (order.equals("8")) {
				map.put("order", "discount asc");
			}
		}
		if (!isEmpty(price)) {
			map.put("price", price);
		}
		if (!isEmpty(keyword)) {
			map.put("keyword", keyword);
		}
		if (!isEmpty(gender)) {
			map.put("gender", gender);
		}
		if (!isEmpty(postArea)) {
			map.put("postArea", postArea);
		}
		return map;
	}

	@Override
	public SearchResult searchBrandProductList(String navId, String start, String end, String tagId, String brandId, String price, String colorId, String size, String categoyId,
			String order, String userLv, String postArea) throws Exception {
		Map<String, String> params = initQueryMap(null, null, tagId, brandId, categoyId, navId, colorId, price, size, order, start, end, null, userLv, postArea, null, null);
		String result = HttpClientUtil.doGet(searchBrandProductURL.toString(), params);
		return converXmlToObj(result);
	}

	@Override
	public SearchResult searchCategoryProductList(String start, String end, String tagId, String brandId, String price, String colorId, String size, String categoyId,
			String order, String userLv, String postArea) throws Exception {
		return this.searchCategoryProductList(start, end, tagId, brandId, price, colorId, size, categoyId, order, userLv, null, postArea);
	}

	@Override
	public SearchResult searchCategoryProductList(String start, String end, String tagId, String brandId, String price, String colorId, String size, String categoyId,
			String order, String userLv, String gender, String postArea) throws Exception {
		Map<String, String> params = initQueryMap(null, null, tagId, brandId, categoyId.replace("|", ","), null, colorId, price, size, order, start, end, gender, userLv, postArea,
				null, null);
		String result = HttpClientUtil.doGet(searchCategoryProductURL.toString(), params);
		return converXmlToObj(result);
	}

	@Override
	public SearchResult searchKeyWordProductList(String keyWord, String start, String end, String tagId, String brandId, String price, String colorId, String size,
			String categoyId, String order, String userLv, String postArea) throws Exception {
		Map<String, String> params = initQueryMap(keyWord, null, tagId, brandId, categoyId, null, colorId, price, size, order, start, end, null, userLv, postArea, null, null);
		String result = HttpClientUtil.doGet(searchKeyWordProductURL.toString(), params);
		return converXmlToObj(result);
	}

	@Override
	public SearchResult searchCategoryIndexList(String start, String end, String brandId, String price, String colorId, String size, String categoyId, String order, String userLv,
			String gender, String postArea) throws Exception {
		Map<String, String> params = initQueryMap(null, null, null, brandId, categoyId.replace("|", ","), null, colorId, price, size, order, start, end, gender, userLv, postArea,
				null, null);
		String result = HttpClientUtil.doGet(searchCategoryProductURL.toString(), params);
		return indexConverXmlToObj(result);
	}

	@Override
	public String searchCategoryProduct(String start, String end, String brandId, String price, String colorId, String size, String categoyId, String order, String userLv,
			String gender, String postArea, String attrId, String imei) {
		Map<String, String> params = initQueryMap(null, null, null, brandId, categoyId.replace("|", ","), null, colorId, price, size, order, start, end, gender, userLv, postArea,
				null, attrId);
		params.put("imei", imei);
		String result = HttpClientUtil.doGet(searchCategoryProductURL.toString(), params);
		return result;
	}

	@Override
	public String searchCategoryOperations(String pageIndex, String pageSize, String userLv, String price, String size, String color, String tagId, String categoryId,
			String postArea, String brandId, String order, String type, String imei) {
		Map<String, String> params = initQueryMap(null, null, tagId, brandId, categoryId, null, color, price, size, order, pageIndex, pageSize, null, userLv, postArea, null, null);
		params.put("type", type);
		params.put("imei", imei);
		String result = HttpClientUtil.doGet(searchLabelsURL.toString(), params);
		return result;
	}

	@Override
	public String searchActivityProductList(String subjectNo, String start, String end, String tagId, String brandId, String price, String colorId, String size, String categoyId,
			String order, String userLv, String postArea, String isPre, String imei) throws UnsupportedEncodingException, DocumentException {
		Map<String, String> params = initQueryMap(null, subjectNo, tagId, brandId, categoyId, null, colorId, price, size, order, start, end, "", userLv, postArea, isPre, null);
		params.put("imei", imei);
		String result = HttpClientUtil.doGet(searchActivityProductURL.toString(), params);
		return result;
	}

	@Override
	public String searchActivityProductListNosellOut(String subjectNo, String start, String end, String tagId, String brandId, String price, String colorId, String size, String categoyId, String order, String userLv, String postArea, String isPre, String imei) throws UnsupportedEncodingException, DocumentException {
		Map<String, String> params = initQueryMap(null, subjectNo, tagId, brandId, categoyId, null, colorId, price, size, order, start, end, "", userLv, postArea, isPre, null);
		params.put("imei", imei);
		params.put("hasStock","1"); //是否去除售罄的商品，1：去除，不传或者0：不去除
		String result = HttpClientUtil.doGet(searchActivityProductURL.toString(), params);
		return result;
	}

	@Override
	public String searchBrandShopProductList(String navId, String pageIndex, String pageSize, String tagId, String brandId, String price, String colorId, String size,
			String categoyId, String order, String userLv, String postArea, String imei) {
		Map<String, String> params = initQueryMap(null, null, tagId, brandId, categoyId, navId, colorId, price, size, order, pageIndex, pageSize, null, userLv, postArea, null,
				null);
		// 这个因为王悦的接口参数里面多品类的时候 是这样传的 categoryNo=11&categoryNo=222
		params.put("imei", imei);
		String result = HttpClientUtil.doGet(searchBrandProductURL.toString(), params);
		return result;
	}

	@SuppressWarnings("rawtypes")
	private List<Product> initProductList(List element) {
		List<Product> productList = new ArrayList<Product>();
		if (element != null && element.size() > 0) {
			Product product = new Product();
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
						product.setMarketPrice(value);
					} else if ("LimitedPrice".equals(fieldName) && !isEmpty(value)) {
						product.setLimitedPrice(value);
					} else if ("SellPrice".equals(fieldName) && !isEmpty(value)) {
						product.setGoldPrice(value);
					} else if ("PlatinumPrice".equals(fieldName) && !isEmpty(value)) {
						product.setPlatinumPrice(value);
					} else if ("DiamondPrice".equals(fieldName) && !isEmpty(value)) {
						product.setDiamondPrice(value);
					} else if ("ProductPicFile".equals(fieldName)) {
						product.setPic(getPicUrl(value, "1"));
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
						product.setCount(value);
					} else if ("ErpCategoryNo".equals(fieldName)) {
						product.setErpCategoryNo(value);
					} else if ("PromotionPrice".equals(fieldName)) {
						product.setPromotionPrice(value);
					} else if ("PostArea".equals(fieldName)) {
						product.setPostArea(value);
					} else if ("Prefix".equals(fieldName)) {
						if (value.contains("[")) {
							product.setPrefix(value.replaceAll("\\[", "【").replaceAll("\\]", "】"));
						} else {
							product.setPrefix(value);
						}
					} else if ("Suffix".equals(fieldName)) {
						if (value.contains("[")) {
							product.setSuffix(value.replaceAll("\\[", "【").replaceAll("\\]", "】"));
						} else {
							product.setSuffix(value);
						}
					} else if ("PromotionNotice".equals(fieldName)) {
						product.setPromotionNotice(value);
					}
					product.setStatus("100000");
				}
			}
			/*
			 * if (product.getErpCategoryNo() != null) { String pic = ""; if
			 * (isModelPic(product.getErpCategoryNo())) { pic =
			 * getPicUrl(product.getProductModelPicFile(), "1");
			 * product.setPicNo(product.getProductModelPicFile()); } else { pic
			 * = getPicUrl(product.getProductPicFile(), "1");
			 * product.setPicNo(product.getProductPicFile()); }
			 * product.setPic(pic); }
			 */
			productList.add(product);
		}
		return productList;
	}

	/**
	 * 颜色
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<Color> initColorList(List<Element> element) {
		List<Color> colorList = new ArrayList<Color>();
		if (element != null && element.size() > 0) {
			for (int k = 0; k < element.size(); k++) {
				Color Color = new Color();
				Element element1 = (Element) element.get(k);
				// 数量
				String count = element1.getTextTrim();
				Color.setCount(count);
				List attributes1 = element1.attributes();
				if (attributes1 != null && attributes1.size() > 0) {
					DefaultAttribute attribute1 = (DefaultAttribute) attributes1.get(0);
					// 10|紫色|20120224173228655251
					String value = attribute1.getText();
					String[] itemarr = value.split("\\|");
					Color.setId(itemarr[0]);
					if (itemarr.length > 1) {
						Color.setName(itemarr[1]);
					}
					if (itemarr.length > 2) {
						Color.setRgb(itemarr[2]);
					}

					// Color.setColorPicNo(itemarr[2]);
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
	private List<SearchHotWords> initTagList(List<Element> element) {
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
	 * 尺码
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<Size> initSizeList(List<Element> element) {
		List<Size> sizeList = new ArrayList<Size>();
		if (element != null && element.size() > 0) {
			for (int k = 0; k < element.size(); k++) {
				Size Size = new Size();
				Element element1 = (Element) element.get(k);
				// 数量
				String count = element1.getTextTrim();
				Size.setCount(count);
				List attributes1 = element1.attributes();
				if (attributes1 != null && attributes1.size() > 0) {
					DefaultAttribute attribute1 = (DefaultAttribute) attributes1.get(0);
					// 10|紫色|20120224173228655251
					String value = attribute1.getText();
					if (!isEmpty(value)) {
						Size.setName(value);
						Size.setId(value);
						sizeList.add(Size);
					}
				}
			}
		}

		return sizeList;
	}

	/**
	 * 品类
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<Category> initCategoryList(List<Element> element) {
		List<Category> cateList = new ArrayList<Category>();
		if (element != null && element.size() > 0) {
			for (int k = 0; k < element.size(); k++) {
				Category cateVO = new Category();
				Element element1 = (Element) element.get(k);
				// 数量
				String count = element1.getTextTrim();
				cateVO.setCount(count);
				List attributes1 = element1.attributes();
				if (attributes1 != null && attributes1.size() > 0) {
					DefaultAttribute attribute1 = (DefaultAttribute) attributes1.get(0);
					// 10|紫色|20120224173228655251
					String value = attribute1.getText();
					String[] itemarr = value.split("\\|");
					if (itemarr[3] != null && itemarr[3].equals("1") && itemarr[1] != null && !"".equals(itemarr[1])) {
						cateVO.setId(itemarr[0]);
						cateVO.setName(itemarr[1]);
						cateVO.setParentId(itemarr[2]);
						cateVO.setStatus(itemarr[3]);
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
	private List<Brand> initBrandList(List<Element> element) {
		List<Brand> brandList = new ArrayList<Brand>();
		if (element != null && element.size() > 0) {
			for (int k = 0; k < element.size(); k++) {
				Brand brand = new Brand();
				Element element1 = (Element) element.get(k);
				// 数量
				String count = element1.getTextTrim();
				brand.setCount(count);
				List attributes1 = element1.attributes();
				if (attributes1 != null && attributes1.size() > 0) {
					DefaultAttribute attribute1 = (DefaultAttribute) attributes1.get(0);
					// B0010|BOTTEGA VENETA|宝缇嘉|20101203121053390827
					String value = attribute1.getText();
					String[] itemarr = value.split("\\|");
					brand.setId(itemarr[0]);
					brand.setNameEN(itemarr[1]);
					if (itemarr.length == 2) {
						brand.setNameCN(itemarr[1]);
					} else {
						brand.setNameCN(itemarr[2]);
					}
					String brandPicNo = itemarr[3];
					if (null == commonService) {
						commonService = new CommonServiceImpl();
					}
					/*
					 * String pic = commonService.queryPicUrl(brandPicNo,
					 * "product"); Map<String, String> map = getPicUrl(pic);
					 * String picUrl = map.get(brandPicNo);
					 */
					String picUrl = getPicUrl(brandPicNo, "2");
					brand.setImgurl(picUrl);
					brandList.add(brand);
				}
			}
		}
		return brandList;
	}

	/**
	 * 将主站返回的xml转换为SearchResult
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws DocumentException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private SearchResult converXmlToObj(String data) throws UnsupportedEncodingException, DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new ByteArrayInputStream(data.getBytes("utf-8")));
		Element root = document.getRootElement();
		Iterator<Element> rootIter = root.elementIterator();
		SearchResult search = new SearchResult();
		List<Category> categoryList = new ArrayList<Category>();
		List<Product> productList = new ArrayList<Product>();
		search.setAbroad("0");
		while (rootIter.hasNext()) {
			Element rootElement = rootIter.next();
			String rootName = rootElement.getName();
			String firstEName = rootElement.getName();
			String firstECount = rootElement.getTextTrim();
			if (firstEName.equals("Total")) {
				search.setCount(firstECount);
			}
			if (firstEName.equals("QTime")) {
				search.setqTime(firstECount);
			}
			List elements = rootElement.elements();
			if ("hotwords".equals(rootName)) {
				search.setConditionList((initTagList(elements)));
			} else {
				if (elements != null && elements.size() > 0) {
					for (int i = 0; i < elements.size(); i++) {
						Element element = (Element) elements.get(i);
						String secondEName = element.getName();
						String secondAttrName = null;
						List attributes = element.attributes();
						if (attributes != null && attributes.size() > 0) {
							DefaultAttribute attribute = (DefaultAttribute) attributes.get(0);
							secondAttrName = attribute.getText();
						}
						if (secondEName.equals("facet") && secondAttrName != null) {
							if (secondAttrName.startsWith("CLv")) {// 品类
								// 多个品类层级数据放在一起
								categoryList.addAll(initCategoryList(element.elements()));
								search.setCategoryList(categoryList);
							}
							if (secondAttrName.equals("ProductPrimaryColors")) {// 颜色
								search.setColorList(initColorList(element.elements()));
							}
							if (secondAttrName.equals("Brand")) {// 品牌
								search.setBrandList(initBrandList(element.elements()));
							}
							if (secondAttrName.equals("ProductSize")) {// 尺码
								search.setSizeList(initSizeList(element.elements()));
							}
							/*
							 * if (secondAttrName.equals("PostArea")) {//
							 * 是否海外商品。1国内;2海外 if
							 * ("2".equals(initPostArea(element.elements()))) {
							 * search.setAbroad("1"); } }
							 */
						}
						if (secondEName.equals("doc")) {
							productList.addAll(initProductList(element.elements()));
							search.setProductList(productList);
						}

					}
				}
			}

		}

		// 初始化商品图片信息
		/*
		 * if (productList != null && productList.size() > 0) { if(null ==
		 * commonService){ commonService = new CommonServiceImpl(); }
		 * StringBuilder picBuffer = new StringBuilder(); for (Product product :
		 * productList) { if(isModelPic(product)){ //如果是模特图并且erp品类是服装的返回模特图
		 * picBuffer.append(product.getProductModelPicFile()).append(",");
		 * }else{ picBuffer.append(product.getProductPicFile()).append(","); }
		 * 
		 * } if (picBuffer.toString() != null &&
		 * !"".equals(picBuffer.toString()) && picBuffer.length() > 0) { String
		 * picNos = commonService.queryPicUrl(picBuffer.substring(0,
		 * picBuffer.length() - 1), "product"); Map<String, String> picUrls =
		 * getPicUrl(picNos); for (Product product : productList) { String pic;
		 * if(isModelPic(product)){ pic =
		 * picUrls.get(product.getProductModelPicFile()); }else{ pic =
		 * picUrls.get(product.getProductPicFile()); } product.setPic(pic); //
		 * product
		 * .setProductModelPicUrl(picUrls.get(product.getProductModelPicFile
		 * ())); } } }
		 */
		return search;
	}

	/**
	 * 将主站返回的xml转换为SearchResult
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws DocumentException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private SearchResult indexConverXmlToObj(String data) throws UnsupportedEncodingException, DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new ByteArrayInputStream(data.getBytes("utf-8")));
		Element root = document.getRootElement();
		Iterator<Element> rootIter = root.elementIterator();
		SearchResult search = new SearchResult();
		List<Category> categoryList = new ArrayList<Category>();
		List<Product> productList = new ArrayList<Product>();
		while (rootIter.hasNext()) {

			Element rootElement = rootIter.next();
			String firstEName = rootElement.getName();
			String firstECount = rootElement.getTextTrim();
			if (firstEName.equals("Total")) {
				search.setCount(firstECount);
			}
			if (firstEName.equals("QTime")) {
				search.setqTime(firstECount);
			}
			List elements = rootElement.elements();
			if (elements != null && elements.size() > 0) {
				for (int i = 0; i < elements.size(); i++) {
					Element element = (Element) elements.get(i);
					String secondEName = element.getName();
					String secondAttrName = null;
					List attributes = element.attributes();
					if (attributes != null && attributes.size() > 0) {
						DefaultAttribute attribute = (DefaultAttribute) attributes.get(0);
						secondAttrName = attribute.getText();
					}
					if (secondEName.equals("facet") && secondAttrName != null) {
						if (secondAttrName.equals("CLv2")) {// 父级品类
							List<Category> categories = initCategoryList(element.elements());
							if (categories != null && categories.size() > 0) {
								search.setParentCategory(categories.get(0));
							}
							// search.setParentCategory(initCategoryList(element.elements()).get(0));
						}
						if (secondAttrName.equals("CLv3")) {// 首页展示品类
							// 多个品类层级数据放在一起
							categoryList.addAll(initCategoryList(element.elements()));
							search.setCategoryList(categoryList);
						}
						if (secondAttrName.equals("ProductPrimaryColors")) {// 颜色
							search.setColorList(initColorList(element.elements()));
						}
						if (secondAttrName.equals("Brand")) {// 品牌
							search.setBrandList(initBrandList(element.elements()));
						}
						if (secondAttrName.equals("ProductSize")) {// 尺码
							search.setSizeList(initSizeList(element.elements()));
						}
					}
					if (secondEName.equals("word")) {
						search.setConditionList(initTagList(element.elements()));
					}
					if (secondEName.equals("doc")) {
						productList.addAll(initProductList(element.elements()));
						search.setProductList(productList);
					}

				}
			}
		}
		return search;
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
					if (value != null && "2".equals(value)) {
						abroad = value;
					}
				}
			}
		}

		return abroad;
	}

	/**
	 * 是否取模特图
	 * 
	 * @param product
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean isModelPic(Product product) {
		String isModePic = product.getIsModelPic();
		String erpCategory = product.getErpCategoryNo();
		if ((isModePic == null || (isModePic != null && isModePic.equals("1"))) && erpCategory != null && erpCategory.startsWith("A01")) {
			return true;
		}
		return false;
	}

	/*
	 * @SuppressWarnings("rawtypes") private HashMap<String, String>
	 * getPicUrl(String picNos) { HashMap<String, String> map = new
	 * HashMap<String, String>(); try { if (null != picNos &&
	 * !"".equals(picNos)) { Gson jsonObj = new Gson(); Map dataMap = (Map)
	 * jsonObj.fromJson(picNos, Object.class); if
	 * ("0".equals(dataMap.get("code"))) { Map content = (Map)
	 * dataMap.get("content"); List list = (List) content.get("list"); for (int
	 * i = 0; i < list.size(); i++) { Map dataMap1 = (Map) list.get(i);
	 * map.put(dataMap1.get("picno").toString(),
	 * dataMap1.get("picurl").toString().replace("{w}", "10").replace("{h}",
	 * "10")); } } } } catch (Exception e) { } return map; }
	 */

	@Override
	public String queryCategory(String parentId, String deep) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("parentId", parentId);
		params.put("deep", deep);
		return HttpClientUtil.doGet(catrgoryURL.toString(), params);
	}

	@Override
	public String querySearchCategory(String parentId, String type) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("parentId", parentId);
		params.put("type", type);
		return HttpClientUtil.doGet(catrgorySearchURL.toString(), params);
	}

	@Override
	public String queryCategorys(String parentId, String type) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("parentId", parentId);
		params.put("type", type);
		return HttpClientUtil.doGet(catrgorysURL.toString(), params);
	}

	@Override
	public String querySearchCategory(String parentId, String type, String mark) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("parentId", parentId);
		params.put("type", type);
		params.put("mark", mark);
		return HttpClientUtil.doGet(catrgorySearchURL.toString(), params);
	}

	@Override
	public String searchBrandProduct(String navId, String pageIndex, String pageSize, String brandId, String price, String colorId, String size, String categoyId, String order,
			String userLv, String postArea) {
		Map<String, String> params = initQueryMap(null, null, null, brandId, categoyId, navId, colorId, price, size, order, pageIndex, pageSize, null, userLv, postArea, null, null);
		String result = HttpClientUtil.doGet(searchBrandProductURL.toString(), params);
		return result;
	}

	@Override
	public String searchProductListByKeyWord(String keyWord, String start, String end, String tagId, String brandId, String price, String colorId, String size, String categoyId,
			String order, String userLv, String postArea) {
		Map<String, String> params = initQueryMap(keyWord, null, tagId, brandId, categoyId, null, colorId, price, size, order, start, end, null, userLv, postArea, null, null);
		String result = HttpClientUtil.doGet(searchKeyWordProductURL.toString(), params);
		return result;
	}

	@Override
	public String newProductList(String keyWord, String navId, String start, String end, String brandNo, String price, String colorId, String size, String categoryNo,
			String order, String gender, String userLv, String postArea) {
		Map<String, String> params = initQueryMap(keyWord, null, null, brandNo, categoryNo, navId, colorId, price, size, order, start, end, gender, userLv, postArea, null, null);
		String result = HttpClientUtil.doGet(searchNewProductURL.toString(), params);
		return result;
	}

	@Override
	public String searchSuggestion(String keyword, String userID, String userIP, String encode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("keyword", keyword);
		params.put("userID", userID);
		params.put("userIP", userIP);
		params.put("encode", encode);
		//默认显示大于10个商品的提示词
		params.put("prodsLimit", "10");
		return HttpClientUtil.doGet(searchSuggestionURL.toString(), params);
	}

	@Override
	public String searchTagProductList(String pageIndex, String pageSize, String userLv, String price, String size, String color, String tagId, String categoryId, String postArea,
			String brandId, String order) {
		Map<String, String> params = initQueryMap(null, null, tagId, brandId, categoryId, null, color, price, size, order, pageIndex, pageSize, null, userLv, postArea, null, null);
		return HttpClientUtil.doGet(searchTagProductListURL.toString(), params);
	}

	@Override
	public String searchProductList(List<String> spus) {
		Map<String, String> params = new HashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		for (String spu : spus) {
			sb.append(spu).append(",");
		}
		params.put("productNo", sb.toString());
		params.put("isFilterHasStock", "0");//不屏蔽掉售罄的商品
		return HttpClientUtil.doPost(subjectProductListURL.toString(), params);
	}

	@Override
	public String searchSpuIsInTopic(String topicId, String spu) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("subjectNO", topicId);
		params.put("productNo", spu);
		return HttpClientUtil.doPost(searchSpuIsInTopicURL.toString(),params);
	}


}
