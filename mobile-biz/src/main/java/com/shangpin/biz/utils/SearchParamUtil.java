package com.shangpin.biz.utils;

import org.apache.commons.lang.StringUtils;

import com.shangpin.biz.bo.SearchParam;

public class SearchParamUtil {

	/**
	 * 解析筛选参数
	 * 
	 * @param filters
	 *            筛选参数
	 * @param source
	 *            来源 1:活动;2:品牌;3:品类;4:标签;5:关键字;6首页标签
	 */
	public static SearchParam parse(String filters, String source) {
		SearchParam searchParam = new SearchParam();
		if (filters != null && !"".equals(filters)) {
			String[] filtersArray = filters.split("\\|");
			String filter = null;
			String[] filterValueArray = null;
			for (int i = 0; i < filtersArray.length; i++) {
				filter = filtersArray[i];
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
				} else if ("attribute".equals(filterValueArray[0])) {
					searchParam.setAttributeId(filterValueArray[1]);
				} else if ("postArea".equals(filterValueArray[0])) {
					searchParam.setPostArea(filterValueArray[1]);
				}
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

		}
		return searchParam;
	}

	/**
	 * 2.9.8后解析筛选参数
	 * 
	 * @param originalFilters
	 * @param dynamicFilters
	 * @param source
	 *            来源 1:活动;2:品牌;3:品类;4:标签;5:关键字;6首页标签
	 * @return
	 */
	public static SearchParam parse(String originalFilters, String dynamicFilters, String source) {
		SearchParam searchParam = new SearchParam();
		if (StringUtils.isNotBlank(originalFilters)) {
			String[] originalfilters = originalFilters.split("\\|");
			for (int i = 0; i < originalfilters.length; i++) {
				String filter = originalfilters[i];
				String[] filterValueArray = filter.split("_");
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
				}
				// else if ("attribute".equals(filterValueArray[0])) {
				// searchParam.setAttributeId(filterValueArray[1]);
				// }
				else if ("postArea".equals(filterValueArray[0])) {
					searchParam.setPostArea(filterValueArray[1]);
				} else if (filterValueArray[0].indexOf("dynamic") > -1) {
					searchParam.setDynamic(filterValueArray[1]);
				}
			}
		}
		if (StringUtils.isNotBlank(dynamicFilters)) {
			StringBuffer dynamic = new StringBuffer();
			String[] dynamicfilters = dynamicFilters.split("\\|");
			for (int j = 0; j < dynamicfilters.length; j++) {
				String dynamicFilter = dynamicfilters[j];
				String[] dynamicFilterValue = dynamicFilter.split("_");
				if ("category".equals(dynamicFilterValue[0])) {
					searchParam.setCategoryId(dynamicFilterValue[1]);
				} else if ("brand".equals(dynamicFilterValue[0])) {
					searchParam.setBrandId(dynamicFilterValue[1]);
				} else if ("size".equals(dynamicFilterValue[0])) {
					searchParam.setSizeId(dynamicFilterValue[1]);
				} else if ("color".equals(dynamicFilterValue[0])) {
					searchParam.setColorId(dynamicFilterValue[1]);
				} else if ("price".equals(dynamicFilterValue[0])) {
					String price = dynamicFilterValue[1];
					if(StringUtil.isNotEmpty(price))
						price=price.replace("10000", "");
					searchParam.setPriceId(price);
				}
				// else if ("attribute".equals(dynamicFilterValue[0])) {
				// searchParam.setAttributeId(dynamicFilterValue[1]);
				// }
				else if ("postArea".equals(dynamicFilterValue[0])) {
					searchParam.setPostArea(dynamicFilterValue[1]);
				} else if (dynamicFilterValue[0].indexOf("dynamic") > -1) {
					dynamic = dynamic.append(dynamicFilterValue[1]).append(",");
				}
			}
			String attributeId = dynamic.toString();
			if (StringUtils.isNotBlank(attributeId)) {
				attributeId = attributeId.substring(0, attributeId.length() - 1);
				searchParam.setAttributeId(attributeId);
			}
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

		return searchParam;
	}
}
