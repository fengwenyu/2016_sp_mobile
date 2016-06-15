package com.shangpin.mobileShangpin.common.util;

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

import com.shangpin.mobileShangpin.platform.vo.SearchBrandItemVO;
import com.shangpin.mobileShangpin.platform.vo.SearchCategoryItemVO;
import com.shangpin.mobileShangpin.platform.vo.SearchCategoryVO;
import com.shangpin.mobileShangpin.platform.vo.SearchColorsItemVO;
import com.shangpin.mobileShangpin.platform.vo.SearchFacetBrandVO;
import com.shangpin.mobileShangpin.platform.vo.SearchFacetColorsVO;
import com.shangpin.mobileShangpin.platform.vo.SearchFacetPriceVO;
import com.shangpin.mobileShangpin.platform.vo.SearchFacetSizeVO;
import com.shangpin.mobileShangpin.platform.vo.SearchFacetsVO;
import com.shangpin.mobileShangpin.platform.vo.SearchMerchandiseVO;
import com.shangpin.mobileShangpin.platform.vo.SearchPriceItemVO;
import com.shangpin.mobileShangpin.platform.vo.SearchSizeItemVO;


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
	@SuppressWarnings("unchecked")
	public static SearchMerchandiseVO parseXml(String str, boolean picNoToUrl) {
		SearchMerchandiseVO merchandiseVO = null;
		SAXReader saxReader = new SAXReader();
		Document document = null;
		Element facets = null;
		Element docs=null;
		String facetname=null;
		try 
		{
			document = saxReader.read(new ByteArrayInputStream(str.getBytes("utf-8")));
			Element root = document.getRootElement();			
			Iterator<Element> rootIter = root.elementIterator();
			merchandiseVO = new SearchMerchandiseVO();
			while(rootIter.hasNext()){
				Element rootElement = rootIter.next();
				if("facets".equals(rootElement.getName())){
					facets = root.element("facets");
					Iterator<Element> facetsIter = facets.elementIterator();
					SearchFacetsVO searchFacetVO=new SearchFacetsVO();
					while(facetsIter.hasNext()){
						Element faceElement=facetsIter.next();
						Iterator<Element> facetIter=faceElement.elementIterator();
						facetname=getFirstAttr(faceElement);
						String item=null;
						String[] itemarr=null;
						if("Brand".equals(facetname)){
							 List<SearchBrandItemVO> searchBrandItemVOList=new ArrayList<SearchBrandItemVO>();	
							 SearchFacetBrandVO searchFacetBrandVO=new SearchFacetBrandVO();
							 SearchBrandItemVO searchBrandItemVO=null;
							 searchFacetBrandVO.setName(getFirstAttr(faceElement));	
							 while(facetIter.hasNext()){
								 	searchBrandItemVO=new SearchBrandItemVO();
									Element itemElemen=facetIter.next();	
									item=getFirstAttr(itemElemen);
									itemarr=item.split("\\|");		
									searchBrandItemVO.setBrandId(itemarr[0]);
									searchBrandItemVO.setBrandEnName(itemarr[1]);
									if(itemarr.length==2){
										searchBrandItemVO.setBrandCnName(itemarr[1]);
									}else{
										searchBrandItemVO.setBrandCnName(itemarr[2]);
									}
									searchBrandItemVO.setCount(itemElemen.getText());
									searchBrandItemVOList.add(searchBrandItemVO);						
							}	
							 searchFacetBrandVO.setSearchBrandItemVO(searchBrandItemVOList);
							 searchFacetVO.setSearchFacetBrandVO(searchFacetBrandVO);
						}else if("ProductPrimaryColors".equals(facetname)){
							 List<SearchColorsItemVO> searchColorsItemVOList=new ArrayList<SearchColorsItemVO>();	
							 SearchFacetColorsVO searchFacetColorsVO=new SearchFacetColorsVO();
							 SearchColorsItemVO searchColorsItemVO=null;
							 searchFacetColorsVO.setName(getFirstAttr(faceElement));	
							 while(facetIter.hasNext()){
								 	searchColorsItemVO=new SearchColorsItemVO();
									Element itemElemen=facetIter.next();	
									item=getFirstAttr(itemElemen);
									itemarr=item.split("\\|");		
									searchColorsItemVO.setColorId(itemarr[0]);
									searchColorsItemVO.setColorName(itemarr[1]);
									searchColorsItemVO.setColorPicNo(itemarr[2]);
									searchColorsItemVO.setCount(itemElemen.getText());
									searchColorsItemVOList.add(searchColorsItemVO);						
							}	
							 searchFacetColorsVO.setSearchColorsItemVO(searchColorsItemVOList);
							 searchFacetVO.setSearchFacetColorsVO(searchFacetColorsVO);
						}
						else if("LimitedPrice".equals(facetname)){
							 List<SearchPriceItemVO> searchPriceItemVOList=new ArrayList<SearchPriceItemVO>();	
							 SearchFacetPriceVO searchFacetPriceVO=new SearchFacetPriceVO();
							 SearchPriceItemVO searchPriceItemVO=null;
							 searchFacetPriceVO.setName(getFirstAttr(faceElement));	
							 while(facetIter.hasNext()){
								 	searchPriceItemVO=new SearchPriceItemVO();
									Element itemElemen=facetIter.next();	
									item=getFirstAttr(itemElemen);							
									searchPriceItemVO.setAmong(item);
									searchPriceItemVO.setCount(itemElemen.getText());
									searchPriceItemVO.dealAmong();
									searchPriceItemVOList.add(searchPriceItemVO);						
							}	
							 searchFacetPriceVO.setSearchPriceItemVO(searchPriceItemVOList);
							 searchFacetVO.setSearchPriceVO(searchFacetPriceVO);
						}
						else if("ProductSize".equals(facetname)){
							 List<SearchSizeItemVO> searchSizeItemVOList=new ArrayList<SearchSizeItemVO>();	
							 SearchFacetSizeVO searchFacetSizeVO=new SearchFacetSizeVO();
							 SearchSizeItemVO searchSizeItemVO=null;
							 searchFacetSizeVO.setName(getFirstAttr(faceElement));	
							 while(facetIter.hasNext()){
								 	searchSizeItemVO=new SearchSizeItemVO();
									Element itemElemen=facetIter.next();	
									item=getFirstAttr(itemElemen);								
									searchSizeItemVO.setSizeCode(item);
									searchSizeItemVO.setCount(itemElemen.getText());
									searchSizeItemVOList.add(searchSizeItemVO);						
							}	
							 searchFacetSizeVO.setSearchSizeItemVO(searchSizeItemVOList);
							 searchFacetVO.setSearchFacetSizeVO(searchFacetSizeVO);
						}else if("CLv2".equals(facetname)){//二级分类
							 List<SearchCategoryItemVO> searchClv2ItemVOs=new ArrayList<SearchCategoryItemVO>();	
							 SearchCategoryVO searchClv2VO = new SearchCategoryVO();
							 SearchCategoryItemVO searchClv2ItemVO = null;
							 searchClv2VO.setName(getFirstAttr(faceElement));	
							 while(facetIter.hasNext()){
							    searchClv2ItemVO = new SearchCategoryItemVO();
								Element itemElemen=facetIter.next();	
								item=getFirstAttr(itemElemen);								
								itemarr=item.split("\\|");		
								if(searchClv2ItemVO.parseItemData(itemElemen.getText(), item)){
									searchClv2ItemVOs.add(searchClv2ItemVO);			
								}
							}	
							 searchClv2VO.setSearchCategoryItemVO(searchClv2ItemVOs);
							 searchFacetVO.setSearchClv2VO(searchClv2VO);
						}else if("CLv3".equals(facetname)){//三级分类
							 List<SearchCategoryItemVO> searchClv3ItemVOs=new ArrayList<SearchCategoryItemVO>();	
							 SearchCategoryVO searchClv3VO = new SearchCategoryVO();
							 SearchCategoryItemVO searchClv3ItemVO = null;
							 searchClv3VO.setName(getFirstAttr(faceElement));	
							 while(facetIter.hasNext()){
							    searchClv3ItemVO = new SearchCategoryItemVO();
								Element itemElemen=facetIter.next();	
								item=getFirstAttr(itemElemen);								
								if(searchClv3ItemVO.parseItemData(itemElemen.getText(), item)){
									searchClv3ItemVOs.add(searchClv3ItemVO);			
								}
							}	
							 searchClv3VO.setSearchCategoryItemVO(searchClv3ItemVOs);
							 searchFacetVO.setSearchClv3VO(searchClv3VO);
						}else if("CLv3".equals(facetname)){//四级分类
							 List<SearchCategoryItemVO> searchClv4ItemVOs=new ArrayList<SearchCategoryItemVO>();	
							 SearchCategoryVO searchClv4VO = new SearchCategoryVO();
							 SearchCategoryItemVO searchClv4ItemVO = null;
							 searchClv4VO.setName(getFirstAttr(faceElement));	
							 while(facetIter.hasNext()){
							    searchClv4ItemVO = new SearchCategoryItemVO();
								Element itemElemen=facetIter.next();	
								item=getFirstAttr(itemElemen);								
								if(searchClv4ItemVO.parseItemData(itemElemen.getText(), item)){
									searchClv4ItemVOs.add(searchClv4ItemVO);			
								}
							}	
							 searchClv4VO.setSearchCategoryItemVO(searchClv4ItemVOs);
							 searchFacetVO.setSearchClv4VO(searchClv4VO);
						}
						merchandiseVO.setSearchFacesVO(searchFacetVO);
					}
				}else if("docs".equals(rootElement.getName())){
				}else{	
					if("SID".equals(rootElement.getName())){
						merchandiseVO.setSid(rootElement.getText());
					}else if("Status".equals(rootElement.getName())){
						merchandiseVO.setStatus(rootElement.getText());
					}else if("Discription".equals(rootElement.getName())){
						merchandiseVO.setDiscription(rootElement.getText());
					}else if("Keyword".equals(rootElement.getName())){
						merchandiseVO.setKeyword(rootElement.getText());
					}else if("QTime".equals(rootElement.getName())){
						merchandiseVO.setQtime(rootElement.getText());
					}else if("Total".equals(rootElement.getName())){
						merchandiseVO.setTotal(rootElement.getText()== null || "".equals(rootElement.getText())? 0 : Integer.parseInt(rootElement.getText()));
					}else if("Start".equals(rootElement.getName())){
						merchandiseVO.setStart(rootElement.getText());
					}else if("End".equals(rootElement.getName())){
						merchandiseVO.setEnd(rootElement.getText());
					}
				}								
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	
	
		return merchandiseVO;
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
}
