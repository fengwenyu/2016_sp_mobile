package com.shangpin.wireless.api.util;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.shangpin.utils.CDNHash;
import com.shangpin.wireless.api.vo.SearchFacetBrandItemVO;
import com.shangpin.wireless.api.vo.SearchFacetBrandVO;
import com.shangpin.wireless.api.vo.SearchFacetCategoryItemVO;
import com.shangpin.wireless.api.vo.SearchFacetCategoryVO;
import com.shangpin.wireless.api.vo.SearchFacetColorItemVO;
import com.shangpin.wireless.api.vo.SearchFacetColorVO;
import com.shangpin.wireless.api.vo.SearchFacetPriceItemVO;
import com.shangpin.wireless.api.vo.SearchFacetPriceVO;
import com.shangpin.wireless.api.vo.SearchFacetSizeItemVO;
import com.shangpin.wireless.api.vo.SearchFacetSizeVO;
import com.shangpin.wireless.api.vo.SearchFacetsVO;
import com.shangpin.wireless.api.vo.SearchMerchandiseVO;
import com.shangpin.wireless.api.vo.SearchProductVO;


public class ParseXmlUtil {
	/**
	 * 解析xml格式
	 * 
	 * @Author wangfeng
	 * @CreateDate 2013-11-15
	 * @param str
	 *            xml格式字符串
	 * @Return
	 */
	public static SearchMerchandiseVO parseSearchResultXml(String xml, boolean justfacet) {
		SAXReader saxReader = new SAXReader();
		SearchMerchandiseVO searchMerchandiseVO = null;
		Document document = null;
		Element facets = null;
		Element docs=null;
		try {
			document = saxReader.read(new ByteArrayInputStream(xml.getBytes("utf-8")));
			Element root = document.getRootElement();			
			Iterator<Element> rootIter = root.elementIterator();
			searchMerchandiseVO = new SearchMerchandiseVO();
			while(rootIter.hasNext()){
				Element rootElement = rootIter.next();
				if("facets".equals(rootElement.getName())){
					facets = root.element("facets");
					Iterator<Element> facetsIter = facets.elementIterator();
					SearchFacetsVO searchFacetVO = new SearchFacetsVO();
					while (facetsIter.hasNext()) {
						Element faceElement = facetsIter.next();
						Iterator<Element> facetIter = faceElement.elementIterator();
						String facetname = getFirstAttr(faceElement);
						String item = null;
						String[] itemarr = null;
						if ("Brand".equals(facetname)) {
							List<SearchFacetBrandItemVO> searchBrandItemVOList = new ArrayList<SearchFacetBrandItemVO>();
							SearchFacetBrandVO searchFacetBrandVO = new SearchFacetBrandVO();
							SearchFacetBrandItemVO searchBrandItemVO = null;
							searchFacetBrandVO.setName("brandNO");
							while (facetIter.hasNext()) {
								searchBrandItemVO = new SearchFacetBrandItemVO();
								Element itemElemen = facetIter.next();
								searchBrandItemVO.parse(getFirstAttr(itemElemen), itemElemen.getText());
								searchBrandItemVOList.add(searchBrandItemVO);
							}
							searchFacetBrandVO.setSearchFacetBrandItems(searchBrandItemVOList);
							searchFacetVO.setSearchFacetBrandVO(searchFacetBrandVO);
						} else if ("ProductPrimaryColors".equals(facetname)) {
							List<SearchFacetColorItemVO> searchColorsItemVOList = new ArrayList<SearchFacetColorItemVO>();
							SearchFacetColorVO searchFacetColorVO = new SearchFacetColorVO();
							SearchFacetColorItemVO searchColorsItemVO = null;
							searchFacetColorVO.setName("primaryColorId");
							while (facetIter.hasNext()) {
								searchColorsItemVO = new SearchFacetColorItemVO();
								Element itemElemen = facetIter.next();
								item = getFirstAttr(itemElemen);
								itemarr = item.split("\\|");
								searchColorsItemVO.setColorId(itemarr[0]);
								searchColorsItemVO.setColorName(itemarr[1]);
								if(item!=null&&item.length()>2){									
									searchColorsItemVO.setColorPicNo(itemarr[2]);
								}								
								searchColorsItemVO.setCount(itemElemen.getText());
								searchColorsItemVOList.add(searchColorsItemVO);
							}
							searchFacetColorVO.setSearchFacetColorItems(searchColorsItemVOList);
							searchFacetVO.setSearchFacetColorVO(searchFacetColorVO);
						} else if ("LimitedPrice".equals(facetname)) {
							List<SearchFacetPriceItemVO> searchPriceItemVOList = new ArrayList<SearchFacetPriceItemVO>();
							SearchFacetPriceVO searchFacetPriceVO = new SearchFacetPriceVO();
							SearchFacetPriceItemVO searchPriceItemVO = null;
							searchFacetPriceVO.setName("limitedPrice");
							while (facetIter.hasNext()) {
								searchPriceItemVO = new SearchFacetPriceItemVO();
								Element itemElemen = facetIter.next();
								item = getFirstAttr(itemElemen);
								searchPriceItemVO.setAmong(item);
								searchPriceItemVO.setCount(itemElemen.getText());
								searchPriceItemVOList.add(searchPriceItemVO);
							}
							searchFacetPriceVO.setSearchFacetPriceItems(searchPriceItemVOList);
							searchFacetVO.setSearchFacetPriceVO(searchFacetPriceVO);
						} else if ("ProductSize".equals(facetname)) {
							List<SearchFacetSizeItemVO> searchSizeItemVOList = new ArrayList<SearchFacetSizeItemVO>();
							SearchFacetSizeVO searchFacetSizeVO = new SearchFacetSizeVO();
							SearchFacetSizeItemVO searchSizeItemVO = null;
							searchFacetSizeVO.setName("productSize");
							while (facetIter.hasNext()) {
								searchSizeItemVO = new SearchFacetSizeItemVO();
								Element itemElemen = facetIter.next();
								item = getFirstAttr(itemElemen);
								searchSizeItemVO.setSizeCode(item);
								searchSizeItemVO.setCount(itemElemen.getText());
								searchSizeItemVOList.add(searchSizeItemVO);
							}
							searchFacetSizeVO.setSearchFacetSizeItems(searchSizeItemVOList);
							searchFacetVO.setSearchFacetSizeVO(searchFacetSizeVO);
						} else if ("CLv2".equals(facetname)) {// 二级分类
							List<SearchFacetCategoryItemVO> searchCategoryItemVOs = new ArrayList<SearchFacetCategoryItemVO>();
							SearchFacetCategoryVO searchCategoryVO = new SearchFacetCategoryVO();
							SearchFacetCategoryItemVO searchCategoryItemVO = null;
							searchCategoryVO.setName("categoryNO");
							searchCategoryVO.setLevel("CLv2");
							while (facetIter.hasNext()) {
								searchCategoryItemVO = new SearchFacetCategoryItemVO();
								Element itemElemen = facetIter.next();
								if(searchCategoryItemVO.parse(getFirstAttr(itemElemen), itemElemen.getText())){
									searchCategoryItemVOs.add(searchCategoryItemVO);
								}
							}
							searchCategoryVO.setSearchCategoryItems(searchCategoryItemVOs);
							searchFacetVO.setSearchFacetCategoryL2VO(searchCategoryVO);
						} else if ("CLv3".equals(facetname)) {// 三级分类
							List<SearchFacetCategoryItemVO> searchCategoryItemVOs = new ArrayList<SearchFacetCategoryItemVO>();
							SearchFacetCategoryVO searchCategoryVO = new SearchFacetCategoryVO();
							SearchFacetCategoryItemVO searchCategoryItemVO = null;
							searchCategoryVO.setName("categoryNO");
							searchCategoryVO.setLevel("CLv3");
							while (facetIter.hasNext()) {
								searchCategoryItemVO = new SearchFacetCategoryItemVO();
								Element itemElemen = facetIter.next();
								if(searchCategoryItemVO.parse(getFirstAttr(itemElemen), itemElemen.getText())){
									searchCategoryItemVOs.add(searchCategoryItemVO);
								}
							}
							searchCategoryVO.setSearchCategoryItems(searchCategoryItemVOs);
							searchFacetVO.setSearchFacetCategoryL3VO(searchCategoryVO);
						} else if ("CLv4".equals(facetname)) {// 四级分类
							List<SearchFacetCategoryItemVO> searchCategoryItemVOs = new ArrayList<SearchFacetCategoryItemVO>();
							SearchFacetCategoryVO searchCategoryVO = new SearchFacetCategoryVO();
							SearchFacetCategoryItemVO searchCategoryItemVO = null;
							searchCategoryVO.setName("categoryNO");
							searchCategoryVO.setLevel("CLv4");
							while (facetIter.hasNext()) {
								searchCategoryItemVO = new SearchFacetCategoryItemVO();
								Element itemElemen = facetIter.next();
								if(searchCategoryItemVO.parse(getFirstAttr(itemElemen), itemElemen.getText())){
									searchCategoryItemVOs.add(searchCategoryItemVO);
								}
							}
							searchCategoryVO.setSearchCategoryItems(searchCategoryItemVOs);
							searchFacetVO.setSearchFacetCategoryL4VO(searchCategoryVO);
						}
						searchMerchandiseVO.setSearchFacetVO(searchFacetVO);
					}
				} else if(!justfacet && "docs".equals(rootElement.getName())){
					docs = root.element("docs");
					Iterator<Element> docsIter = docs.elementIterator();
					List<SearchProductVO> searchProductVOList=new ArrayList<SearchProductVO>();
					while(docsIter.hasNext()){
						Element docElement=docsIter.next();
						Iterator<Element> docIter=docElement.elementIterator();
						SearchProductVO searchProductVO=new SearchProductVO();
						while(docIter.hasNext()){
							Element fieldElemen = docIter.next();
							final String fieldname = getFirstAttr(fieldElemen);
							final String fieldtext = fieldElemen.getText();
							if("ProductNo".equals(fieldname)){
								searchProductVO.setProductNo(fieldtext);
							}else if("ProductName".equals(fieldname)){
								searchProductVO.setProductName(fieldtext.trim());
							}else if("MarketPrice".equals(fieldname)){
								searchProductVO.setMarketPrice(fieldtext);
							}else if("LimitedPrice".equals(fieldname)){
								searchProductVO.setLimitedPrice(fieldtext);
							}else if("SellPrice".equals(fieldname)){
								searchProductVO.setSellPrice(fieldtext);
							}else if("PlatinumPrice".equals(fieldname)){
								searchProductVO.setPlatinumPrice(fieldtext);
							}else if("DiamondPrice".equals(fieldname)){
								searchProductVO.setDiamondPrice(fieldtext);
							}else if("LimitedVipPrice".equals(fieldname)){
								searchProductVO.setLimitedVipPrice(fieldtext);
							}else if("ProductPicFile".equals(fieldname)){
								searchProductVO.setProductPicFile(fieldtext);
							}else if("ProductModelPicFile".equals(fieldname)){
								searchProductVO.setProductModelPicFile(fieldtext);
							}else if("BrandNo".equals(fieldname)){
								searchProductVO.setBrandNo(fieldtext);
							}else if("BrandEnName".equals(fieldname)){
								searchProductVO.setBrandEnName(fieldtext);
							}else if("BrandCnName".equals(fieldname)){
								searchProductVO.setBrandCnName(fieldtext);
							}else if("IsSupportDiscount".equals(fieldname)){
							    //ep兼容 设置全都享用会员权益
								searchProductVO.setIsSupportDiscount("1");
							}else if("AvailableStock".equals(fieldname)){
								searchProductVO.setAvailableStock(fieldtext);	
							}else if ("IsModelPic".equals(fieldname)) {
                                searchProductVO.setIsModelPic(fieldElemen.getText());
                            } else if ("ErpCategoryNo".equals(fieldname)) {
                                searchProductVO.setErpCategoryNo(fieldElemen.getText());
                            }else if (searchProductVO.getIsModelPic()!=null&&searchProductVO.getErpCategoryNo()!=null) {
                                String pic="";
                                if (isModelPic(searchProductVO.getIsModelPic(),searchProductVO.getErpCategoryNo())) {
                                    pic = getPicUrl(searchProductVO.getProductModelPicFile());
                                } else {
                                    pic = getPicUrl(searchProductVO.getProductPicFile());
                                }
                                searchProductVO.setProductPicUrl(pic);
                            }
						}
						searchProductVOList.add(searchProductVO);
											
					}
					searchMerchandiseVO.setSearchProductVO(searchProductVOList);
					
				} else {	
					if("SID".equals(rootElement.getName())){
						searchMerchandiseVO.setSid(rootElement.getText());
					}else if("Status".equals(rootElement.getName())){
						searchMerchandiseVO.setStatus(rootElement.getText());
					}else if("Discription".equals(rootElement.getName())){
						searchMerchandiseVO.setDiscription(rootElement.getText());
					}else if("Keyword".equals(rootElement.getName())){
						searchMerchandiseVO.setKeyword(rootElement.getText());
					}else if("QTime".equals(rootElement.getName())){
						searchMerchandiseVO.setQtime(rootElement.getText());
					}else if("Total".equals(rootElement.getName())){
						searchMerchandiseVO.setTotal(rootElement.getText());
					}else if("Start".equals(rootElement.getName())){
						searchMerchandiseVO.setStart(rootElement.getText());
					}else if("End".equals(rootElement.getName())){
						searchMerchandiseVO.setEnd(rootElement.getText());
					}
				}								
			}

			/*if (!justfacet) {
				StringBuilder picBuffer = new StringBuilder();
				for(SearchProductVO searchProductVO:searchMerchandiseVO.getSearchProductVO()){
					picBuffer.append(searchProductVO.getProductPicFile()).append(",");
					picBuffer.append(searchProductVO.getProductModelPicFile()).append(",");
				}
				if(picBuffer.toString() != null && !"".equals(picBuffer.toString()) && picBuffer.length() > 0){
					Map<String,String> picUrls = getPictureUrl(picBuffer.substring(0, picBuffer.length()-1), "product");
					for(int i=0;i<searchMerchandiseVO.getSearchProductVO().size();i++){
						SearchProductVO product = searchMerchandiseVO.getSearchProductVO().get(i);
						product.setProductPicUrl(picUrls.get(product.getProductPicFile()));
						product.setProductModelPicUrl(picUrls.get(product.getProductModelPicFile()));
					}
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return searchMerchandiseVO;
	}
	@SuppressWarnings("unchecked")
	public static Map<String,String> getAttrs(Element e){
		Map<String,String> attMap=new HashMap<String,String>();
		List<Attribute> elementAttrs=null;
		elementAttrs=e.attributes();
		for (int i = 0; i < elementAttrs.size(); ++i) {
			if("name".equals(elementAttrs.get(i).getName())){				
				attMap.put(elementAttrs.get(i).getValue(), e.getText());				
			}
		}
		return attMap;
	}
	@SuppressWarnings("unchecked")
	public static String getFirstAttr(Element e){
		String result=null;
		List<Attribute> elementAttrs=null;
		elementAttrs=e.attributes();
		for (int i = 0; i < elementAttrs.size(); ++i) {
			if("name".equals(elementAttrs.get(i).getName())){				
				result=elementAttrs.get(i).getValue();				
			}
		}
		return result;
	}
	/*public static Map<String, String> getPictureUrl(String picnos, String pictype){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("picnos", picnos);
		map.put("picw","{w}");
		map.put("pich", "{h}");
		map.put("pictype", pictype);
		String url = Constants.SP_BASE_URL + "GetPicUrlsByGet";
		String data = null;
		try {
			// 获取活动xml格式字符串
			data = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		if (null != data && !"".equals(data)) {
			JSONObject jsonObj = JSONObject.fromObject(data);
			if (Constants.SUCCESS.equals(jsonObj.get("code"))) {
				JSONObject obj = (JSONObject) jsonObj.get("content");
				JSONArray array = obj.getJSONArray("list");

				JSONObject picObj = null;
				for (int i = 0; i < array.size(); i++) {
					picObj = (JSONObject) array.get(i);
					map.put(picObj.getString("picno"), picObj.getString("picurl"));
				}
			}
		}  
		return map;
	}*/
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String str="<result>"+
							 "<SID id=\"aaa\">10c2994b-8ee4-4e64-a926-d5d55c7b80c3</SID>"+
							"<Status>0</Status>"+
							"<Discription>查询成功</Discription>"+
							"<Keyword>女士</Keyword>"+
							"<QTime>179</QTime>"+
							"<Total>2,088</Total>"+
							"<Start>1</Start>"+
							"<End>10</End>"+
							"<facets>"+
							"<facet name=\"Brand\">"+
							"<item name=\"B0003|GUCCI|古琦\" display=\"true\">405</item>"+
							"</facet>"+
							"<facet name=\"ProductPrimaryColors\">"+
							"<item name=\"10|紫色|2012022417322\">6</item>"+
							"<item name=\"2|白色|20120224170727\">25</item>"+
							"</facet>"+
							"<facet name=\"CLv2\">"+
							"<item name=\"A01B01|箱包|A01|1|1\">150</item>"+
							"<item name=\"A01B02|服装|A01|1|2\">1</item>"+
							"<item name=\"A02B02|服装|A02|1|2\">16</item>"+
							"<item name=\"A02B01|箱包|A02|1|1\">38</item>"+
							"</facet>"+
							"</facets>"+
							"<docs>"+
							"<doc>"+
							"<field name=\"ProductNo\">0575236</field>"+
							"<field name=\"ProductName\"> 限量款艺术花色图案女士绢毛披肩</field>"+
							"</doc>"+
							"</docs>"+
					"</result>";
		//Map<String, List<XmlElementsObject>> tmap = new HashMap<String, List<XmlElementsObject>>();
	}
	 /**
     * 是否取模特图
     * 
     * @param isModelPic,erpCategoryNo
     * @return
     */
    private static boolean isModelPic(String isModelPic,String erpCategoryNo) {
        if (isModelPic != null && isModelPic.equals("1") && erpCategoryNo != null && erpCategoryNo.startsWith("A01")) {
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
     */
    private static String getPicUrl(String picNo) {
        StringBuffer picBuffer = new StringBuffer("");
        picBuffer.append(CDNHash.getUrlHash(picNo));
        picBuffer.append("/f/p/");
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
}
