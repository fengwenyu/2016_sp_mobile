package com.shangpin.biz.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.biz.bo.ProductGroupForTopic;
import com.shangpin.biz.bo.ProductType;
import com.shangpin.biz.bo.ProductsBizTopic;
import com.shangpin.biz.bo.TopicBrand;
import com.shangpin.biz.bo.TopicProduct;
import com.shangpin.biz.service.SPBizSubjectService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.HtmlUtil;
import com.shangpin.biz.utils.ObjectMapperConversionUtil;
import com.shangpin.biz.utils.ParseJsonUtil;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.core.entity.FindManage;
import com.shangpin.core.service.IFindManageService;

@Service
public class SPBizSubjectServiceImpl implements SPBizSubjectService {
	public static Logger logger = LoggerFactory.getLogger(SPBizSubjectServiceImpl.class);

	@Autowired
	private IFindManageService findManageService;
	@Autowired
	private ShangPinService shangPinService;

	@Override
	public List<FindManage> findSubjectList(Date dateTime, String type) {
		return findManageService.findByConditions(dateTime, type);
	}

	@Override
	public Map<String, Object> getNewTopicProducts(String topicId, String gender, String userid, String pageIndex, String pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		ListOfGoods listOfGoodsVO = new ListOfGoods();
		listOfGoodsVO.setUserId(null == userid ? "" : userid);
		listOfGoodsVO.setPageSize(pageSize == null ? "" : pageSize + "");
		listOfGoodsVO.setPageIndex(pageIndex == null ? "" : pageIndex + "");
		listOfGoodsVO.setGender((null == gender || "".equals(gender.trim()) ? "0" : gender));
		listOfGoodsVO.setTopicId(topicId);
		listOfGoodsVO.setPicw(Constants.MERCHANDISE_LIST_PICTURE_WIDTH);
		listOfGoodsVO.setPich(Constants.MERCHANDISE_LIST_PICTURE_HEIGHT);
		listOfGoodsVO.setFilterSold("0");
		String json = shangPinService.findTopicProducts(listOfGoodsVO);
		TopicProduct topicProduct = new TopicProduct();
		if (StringUtil.isNotEmpty(json)) {
			ObjectMapper mapper = new ObjectMapper();
			ObjectMapperConversionUtil.Conversion(mapper);
			JsonNode rootNode;
			try {
				rootNode = mapper.readTree(json);
				String code = rootNode.path("code").asText();
				if (Constants.SUCCESS.equals(code)) {

					JsonNode content = rootNode.path("content");
					if (content != null && !"null".equals(content.asText()) && !"{}".equals(content.asText())) {
						String jsonList = content.path("list").toString();
						if (StringUtil.isNotEmpty(jsonList) && !"[]".equals(jsonList)) {
							JavaType javaType = ParseJsonUtil.getCollectionType(ArrayList.class, ProductGroupForTopic.class);
							List<ProductGroupForTopic> list = mapper.readValue(jsonList, javaType);
							topicProduct.setList(list);
						}
						String jsonBrandList = content.path("brands").toString();
						if (StringUtil.isNotEmpty(jsonList) && !"[]".equals(jsonList)) {
							JavaType javaType = ParseJsonUtil.getCollectionType(ArrayList.class, TopicBrand.class);
							List<TopicBrand> topicBrandlist = mapper.readValue(jsonBrandList, javaType);
							topicProduct.setBrands(topicBrandlist);
						}
						topicProduct.setTopicid(content.path("topicid").asText()); // 专题编号（加入购物袋是可选项，用于统计通过专题销售的商品）
						topicProduct.setShareurl(content.path("shareurl").asText());
						topicProduct.setName(content.path("name").asText());
						topicProduct.setSubtitle(content.path("subtitle").asText());
						topicProduct.setTopicdesc(content.path("topicdesc").asText());
						topicProduct.setStatus(content.path("status").asText());
						topicProduct.setStarttime(content.path("starttime").asText());
						topicProduct.setEndtime(content.path("endtime").asText());
						topicProduct.setType(content.path("type").asText());
						topicProduct.setO1type(content.path("o1type").asText());
						topicProduct.setO1code(content.path("o1code").asText());
						topicProduct.setO1logo(content.path("o1logo").asText());
						topicProduct.setO1url(content.path("o1url").asText());
						topicProduct.setHeadPic(content.path("headPic").asText());
						topicProduct.setPricetag(content.path("pricetag").asText());
						topicProduct.setPricename(content.path("pricename").asText());
						topicProduct.setDatepretime(content.path("datepretime").asText());
						//
						map.put("topicProduct", topicProduct);
						String datepretime = topicProduct.getDatepretime();
						String starttime = topicProduct.getStarttime();
						boolean flag = false;
						boolean priceTag = true;
						if (datepretime != null && !"".equals(datepretime)) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date pretime = sdf.parse(datepretime);
							Date now = new Date();
							flag = pretime.before(now);
							if (flag) {
								Date startpretime = sdf.parse(starttime);
								flag = now.before(startpretime);
							}
						}

						if (topicProduct.getPricename().equals("更多惊喜")) {
							priceTag = false;
						}

						String haveMore = "0";
						if (topicProduct.getList() != null) {
							for (int i = 0; i < topicProduct.getList().size(); i++) {
								List<ProductsBizTopic> list = topicProduct.getList().get(i).getProducts();
								if (list.size() >= Integer.valueOf(pageSize)) {
									haveMore = "1";
								}
							}
						}

						map.put("code", "0");
						map.put("msg", rootNode.path("msg").asText());
						map.put("priceTag", priceTag);
						map.put("flag", flag);
						map.put("haveMore", haveMore);
						map.put("pageIndex", Integer.valueOf(pageIndex) + 1);
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("调用活动商品列表接口返回数据错误 e={}", e);
			}
		}

		return map;
	}

	@Override
	public Map<String, Object> getTopAdver(String topicId, String toLink, ProductType source) {
		Map<String, Object> map = new HashMap<String, Object>();
		String json = shangPinService.getTopAdver(topicId);
		if (StringUtil.isNotEmpty(json)) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode;
			try {
				rootNode = mapper.readTree(json);
				String code = rootNode.path("code").asText();
				if (!Constants.SUCCESS.equals(code)) {
					return null;
				}

				JsonNode content = rootNode.path("content");
				if (content == null) {
					return null;
				}
				JsonNode template = content.path("template");
				if (template == null) {
					return null;
				}
				String html = template.asText();
				if (toLink.equals("0")) {
					html = HtmlUtil.deleteAttribute(html, null, "href");
					map.put("html", html);
					return map;
				}
				if (ProductType.M_AOLAI.equals(source) || ProductType.M_SHANGPIN.equals(source)) {
					map.put("html", html);
					return map;
				}
				html = HtmlUtil.appDetailhref(html);

				map.put("html", html);
				return map;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("调用活动商品列表模板接口返回数据错误 e={}", e);
			}
		}
		return null;
	}

}
