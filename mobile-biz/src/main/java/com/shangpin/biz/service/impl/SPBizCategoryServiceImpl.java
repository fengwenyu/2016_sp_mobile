package com.shangpin.biz.service.impl;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.SearchService;
import com.shangpin.base.vo.Category;
import com.shangpin.base.vo.Product;
import com.shangpin.base.vo.SearchResult;
import com.shangpin.biz.bo.CategoryIndex;
import com.shangpin.biz.bo.CategoryItem;
import com.shangpin.biz.bo.CategoryOperationItem;
import com.shangpin.biz.bo.SearchBrand;
import com.shangpin.biz.bo.SearchCategory;
import com.shangpin.biz.bo.SearchColor;
import com.shangpin.biz.bo.SearchConditions;
import com.shangpin.biz.bo.SearchSize;
import com.shangpin.biz.service.SPBizCategoryService;
import com.shangpin.biz.service.abstraction.AbstractBizCategoryService;
import com.shangpin.biz.utils.SearchUtil;
import com.shangpin.utils.CDNHash;

@Service
public class SPBizCategoryServiceImpl extends AbstractBizCategoryService  implements SPBizCategoryService {

	private static final Logger logger = LoggerFactory.getLogger(SPBizCategoryServiceImpl.class);
	@Autowired
	private SearchService searchService;

	@Override
	public SearchResult findCategoryProduct(SearchConditions searchConditions) {
		try {
			return searchService.searchCategoryIndexList(searchConditions.getStart(), searchConditions.getNum(),
					searchConditions.getBrandNo(), searchConditions.getPrice(),
					searchConditions.getColor(), searchConditions.getSize(), searchConditions.getCategoryNo(),
					searchConditions.getOrder(), searchConditions.getUserLv(),searchConditions.getGender(),searchConditions.getPostArea());
		} catch (Exception e) {
			logger.error("调用base品类搜索接口返回数据错误！");
			return null;
		}
	}
	
	@Override
	public com.shangpin.biz.bo.SearchResult searchCategoryProduct(SearchConditions searchConditions) {
		try {
			String xml = searchService.searchCategoryProduct(searchConditions.getStart(), searchConditions.getNum(),
					searchConditions.getBrandNo(), searchConditions.getPrice(),
					searchConditions.getColor(), searchConditions.getSize(), searchConditions.getCategoryNo(),
					searchConditions.getOrder(), searchConditions.getUserLv(),searchConditions.getGender(),searchConditions.getPostArea(),null, "");
			com.shangpin.biz.bo.SearchResult sr =SearchUtil.converXmlToObj(xml, "3",searchConditions.getCategoryNo(),null);
			return sr;
		} catch (Exception e) {
			logger.error("调用base品类搜索接口返回数据错误！");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CategoryIndex> getManCateList(String gender, String userLv) {
		return getManList(gender, userLv);
	}

	@Override
	public List<CategoryIndex> getWomanCateList(String gender, String userLv) {
		return getWomanList(gender, userLv);
	}

	private List<CategoryIndex> getManList(String gender, String userLv) {
		List<CategoryIndex> dataList = new ArrayList<CategoryIndex>();

		CategoryIndex bagCateVO = new CategoryIndex();
		bagCateVO.setCode("A02B01");
		bagCateVO.setEnname("BAGS");
		bagCateVO.setName("箱包");
		bagCateVO.setIcon("/images/category/men_bags_img.jpg");
		bagCateVO.setChildList(getChildCategoryList("A02B01", gender, userLv));
		dataList.add(bagCateVO);

		CategoryIndex clothObj = new CategoryIndex();
		clothObj.setCode("A02B02");
		clothObj.setName("服饰");
		clothObj.setEnname("CLOTHING");
		clothObj.setIcon("/images/category/men_clothing_img.jpg");
		clothObj.setChildList(getChildCategoryList("A02B02", gender, userLv));
		dataList.add(clothObj);

		CategoryIndex shoesObj = new CategoryIndex();
		shoesObj.setCode("A02B03");
		shoesObj.setName("鞋靴");
		shoesObj.setEnname("SHOES");
		shoesObj.setIcon("/images/category/men_shoes_img.jpg");
		shoesObj.setChildList(getChildCategoryList("A02B03", gender, userLv));
		dataList.add(shoesObj);

		CategoryIndex peisObj = new CategoryIndex();
		peisObj.setCode("A02B04");
		peisObj.setName("配饰");
		peisObj.setEnname("ACCESSORIES");
		peisObj.setIcon("/images/category/men_accessories_img.jpg");
		peisObj.setChildList(getChildCategoryList("A02B04", gender, userLv));
		dataList.add(peisObj);

		CategoryIndex watchObj = new CategoryIndex();
		watchObj.setCode("A02B06");
		watchObj.setName("腕表眼镜");
		watchObj.setEnname("WATCHES&GLASSES");
		watchObj.setIcon("/images/category/men_watchesglasses_img.jpg");
		watchObj.setChildList(getChildCategoryList("A02B06", gender, userLv));
		dataList.add(watchObj);

		CategoryIndex meizObj = new CategoryIndex();
		meizObj.setCode("A02B07");
		meizObj.setName("美妆");
		meizObj.setEnname("BEAUTY");
		meizObj.setIcon("/images/category/men_beauty_img.jpg");
		meizObj.setChildList(getChildCategoryList("A02B07", gender, userLv));
		dataList.add(meizObj);

		CategoryIndex homeObj = new CategoryIndex();
		homeObj.setCode("A02B05");
		homeObj.setName("家居");
		homeObj.setEnname("HOME");
		homeObj.setIcon("/images/category/men_home.jpg");
		homeObj.setChildList(getChildCategoryList("A02B05", gender, userLv));
		dataList.add(homeObj);
		return dataList;
	}

	private List<CategoryIndex> getWomanList(String gender, String userLv) {
		List<CategoryIndex> dataList = new ArrayList<CategoryIndex>();

		CategoryIndex bagObj = new CategoryIndex();
		bagObj.setCode("A01B01");
		bagObj.setName("箱包");
		bagObj.setEnname("BAGS");
		bagObj.setIcon("/images/category/women_bags_img.jpg");
		bagObj.setChildList(getChildCategoryList("A01B01", gender, userLv));
		dataList.add(bagObj);

		CategoryIndex clothObj = new CategoryIndex();
		clothObj.setCode("A01B02");
		clothObj.setName("服饰");
		clothObj.setEnname("CLOTHING");
		clothObj.setIcon("/images/category/women_clothing_img.jpg");
		clothObj.setChildList(getChildCategoryList("A01B02", gender, userLv));
		dataList.add(clothObj);

		CategoryIndex shoesObj = new CategoryIndex();
		shoesObj.setCode("A01B03");
		shoesObj.setName("鞋靴");
		shoesObj.setEnname("SHOES");
		shoesObj.setIcon("/images/category/women_shoes_img.jpg");
		shoesObj.setChildList(getChildCategoryList("A01B03", gender, userLv));
		dataList.add(shoesObj);

		CategoryIndex peisObj = new CategoryIndex();
		peisObj.setCode("A01B04");
		peisObj.setName("配饰");
		peisObj.setEnname("ACCESSORIES");
		peisObj.setIcon("/images/category/women_accessories_img.jpg");
		peisObj.setChildList(getChildCategoryList("A01B04", gender, userLv));
		dataList.add(peisObj);

		CategoryIndex watchObj = new CategoryIndex();
		watchObj.setCode("A01B06");
		watchObj.setName("腕表眼镜");
		watchObj.setEnname("WATCHES&GLASSES");
		watchObj.setIcon("/images/category/women_watchesglasses_img.jpg");
		watchObj.setChildList(getChildCategoryList("A01B06", gender, userLv));
		dataList.add(watchObj);

		CategoryIndex meizObj = new CategoryIndex();
		meizObj.setCode("A01B07");
		meizObj.setName("美妆");
		meizObj.setEnname("BEAUTY");
		meizObj.setIcon("/images/category/women_beauty_img.jpg");
		meizObj.setChildList(getChildCategoryList("A01B07", gender, userLv));
		dataList.add(meizObj);

		CategoryIndex homeObj = new CategoryIndex();
		homeObj.setCode("A01B05");
		homeObj.setName("家居");
		homeObj.setEnname("HOME");
		homeObj.setIcon("/images/category/women_home.jpg");
		homeObj.setChildList(getChildCategoryList("A01B05", gender, userLv));
		dataList.add(homeObj);
		return dataList;
	}

	/**
	 * 取的品类子类
	 * 
	 * @param categoryId
	 * @param gender
	 * @return
	 */
	private List<CategoryIndex> getChildCategoryList(String categoryId, String gender, String userLv) {
		try {
			List<Category> categoryList = searchService.searchCategoryIndexList(null, null, null, null, null, null,
					categoryId, null, userLv,gender, null).getCategoryList();
			List<CategoryIndex> dataList = new ArrayList<CategoryIndex>();
			if (categoryList != null && categoryList.size() > 0) {
				for (int i = 0; i < categoryList.size(); i++) {
					Category cateObj = categoryList.get(i);
					CategoryIndex categoryIndex = new CategoryIndex();
					categoryIndex.setCode(cateObj.getId());
					categoryIndex.setName(cateObj.getName());
					dataList.add(categoryIndex);
				}
				return dataList;
			}

		} catch (Exception e) {
			logger.error("调用base品类搜索接口返回数据错误！");
		}
		return null;

	}

	@Override
	public com.shangpin.biz.bo.SearchResult getCategoryChildList(SearchConditions searchConditions) {

		try {
			String xml = searchService.searchCategoryProduct(null, null,
					null,null,
					null, null, searchConditions.getCategoryNo(),
					null,searchConditions.getUserLv(),searchConditions.getGender(),searchConditions.getPostArea(),null, "");
			com.shangpin.biz.bo.SearchResult sr =SearchUtil.converXmlToObj(xml, "3",searchConditions.getCategoryNo(),null);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * 将主站返回的xml转换为SearchResult
     * 
     * @throws UnsupportedEncodingException
     * @throws DocumentException
     */
    @SuppressWarnings({ "unused", "unchecked", "rawtypes" })
    private com.shangpin.biz.bo.SearchResult indexConverXmlToObj(String data) throws UnsupportedEncodingException, DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new ByteArrayInputStream(data.getBytes("utf-8")));
        Element root = document.getRootElement();
        Iterator<Element> rootIter = root.elementIterator();
        com.shangpin.biz.bo.SearchResult search = new com.shangpin.biz.bo.SearchResult();
        List<SearchCategory> categoryList = new ArrayList<SearchCategory>();
        List<com.shangpin.biz.bo.Product> productList = new ArrayList<com.shangpin.biz.bo.Product>();
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
                    	if(secondAttrName.equals("CLv2")){//父级品类
                    		List<SearchCategory> categories = initCategoryList(element.elements());
                    		if(categories != null && categories.size() > 0){
                    			search.setParentCategory(categories.get(0));
                    		}
                    		//search.setParentCategory(initCategoryList(element.elements()).get(0));
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
    
    @SuppressWarnings("rawtypes")
    private List<com.shangpin.biz.bo.Product> initProductList(List element) {
        List<com.shangpin.biz.bo.Product> productList = new ArrayList<com.shangpin.biz.bo.Product>();
        if (element != null && element.size() > 0) {
            com.shangpin.biz.bo.Product product = new com.shangpin.biz.bo.Product();
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
                    } else if ("LimitedPrice".equals(fieldName)&&!isEmpty(value)) {
                        product.setLimitedPrice(value);
                    } else if ("SellPrice".equals(fieldName)&&!isEmpty(value)) {
                        product.setGoldPrice(value);
                    } else if ("PlatinumPrice".equals(fieldName)&&!isEmpty(value)) {
                        product.setPlatinumPrice(value);
                    } else if ("DiamondPrice".equals(fieldName)&&!isEmpty(value)) {
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
                    }else if ("ErpCategoryNo".equals(fieldName)) {
                        product.setErpCategoryNo(value);
                    }else if("PromotionPrice".equals(fieldName)){
                        product.setPromotionPrice(value);
                    }
                    product.setStatus("100000");
                }
            }
          /*  if (product.getErpCategoryNo() != null) {
                String pic = "";
                String picNo="";
                if (isModelPic(product.getErpCategoryNo())) {
                    pic = getPicUrl(product.getProductModelPicFile(), "1");
                    picNo=product.getProductModelPicFile();
                } else {
                    pic = getPicUrl(product.getProductPicFile(), "1");
                    picNo=product.getProductPicFile();
                }
                product.setPic(pic);
                product.setPicNo(picNo);
            }*/
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
    private List<SearchColor> initColorList(List<Element> element) {
        List<SearchColor> colorList = new ArrayList<SearchColor>();
        if (element != null && element.size() > 0) {
            for (int k = 0; k < element.size(); k++) {
            	SearchColor color = new SearchColor();
                Element element1 = (Element) element.get(k);
                // 数量
                String count = element1.getTextTrim();
                color.setCount(count);
                List attributes1 = element1.attributes();
                if (attributes1 != null && attributes1.size() > 0) {
                    DefaultAttribute attribute1 = (DefaultAttribute) attributes1.get(0);
                    // 10|紫色|20120224173228655251
                    String value = attribute1.getText();
                    String[] itemarr = value.split("\\|");
                    color.setId(itemarr[0]);
                    if(itemarr.length>1){
                    	 color.setName(itemarr[1]);
                    }
                    if(itemarr.length>2){
                    	 color.setRgb(itemarr[2]);
                   }
                   
                    // Color.setColorPicNo(itemarr[2]);
                    colorList.add(color);
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
    private List<com.shangpin.biz.bo.SearchHotWords> initTagList(List<Element> element) {
        List<com.shangpin.biz.bo.SearchHotWords> tagList = new ArrayList<com.shangpin.biz.bo.SearchHotWords>();
        if (element != null && element.size() > 0) {
            for (int i = 0; i < element.size(); i++) {
                String textVal = element.get(i).getTextTrim();
                if (textVal != null && !"".equals(textVal)) {
                	com.shangpin.biz.bo.SearchHotWords hotwords = new com.shangpin.biz.bo.SearchHotWords();
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
    private List<SearchSize> initSizeList(List<Element> element) {
        List<SearchSize> sizeList = new ArrayList<SearchSize>();
        if (element != null && element.size() > 0) {
            for (int k = 0; k < element.size(); k++) {
            	SearchSize size = new SearchSize();
                Element element1 = (Element) element.get(k);
                // 数量
                String count = element1.getTextTrim();
                size.setCount(count);
                List attributes1 = element1.attributes();
                if (attributes1 != null && attributes1.size() > 0) {
                    DefaultAttribute attribute1 = (DefaultAttribute) attributes1.get(0);
                    // 10|紫色|20120224173228655251
                    String value = attribute1.getText();
                    if (!isEmpty(value)) {
                        size.setName(value);
                        size.setId(value);
                        sizeList.add(size);
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
    private List<SearchCategory> initCategoryList(List<Element> element) {
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
    private List<SearchBrand> initBrandList(List<Element> element) {
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
                    String picUrl = getPicUrl(brandPicNo, "2");
                    brand.setImgurl(picUrl);
                    brandList.add(brand);
                }
            }
        }
        return brandList;
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

	@Override
	public CategoryItem queryCategory(String parentId, String type) {
		CategoryItem categoryItem=fromQueryCategorys(parentId,type);
		if(categoryItem!=null){
			return categoryItem;
		}
		return null;
	}

	@Override
	public CategoryOperationItem queryCategoryOperation(String id, String userId) {
		CategoryOperationItem categoryOperationItem=fromQueryCategoryOperations(id,userId);
		if(categoryOperationItem!=null){
			return categoryOperationItem;
		}
		return null;
	}
}
