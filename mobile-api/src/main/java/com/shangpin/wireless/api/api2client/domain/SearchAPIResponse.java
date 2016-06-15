package com.shangpin.wireless.api.api2client.domain;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.base.vo.Brand;
import com.shangpin.base.vo.Category;
import com.shangpin.base.vo.Color;
import com.shangpin.base.vo.Product;
import com.shangpin.base.vo.SearchHotWords;
import com.shangpin.base.vo.SearchResult;
import com.shangpin.base.vo.Size;

public class SearchAPIResponse extends CommonAPIResponse {

	private SearchResult data;
	private String searchCategory;
	private JSONArray cateJSONList = new JSONArray();

	public SearchAPIResponse(SearchResult data, String searchCategory) {
		this.data = data;
		this.searchCategory = searchCategory;
		initAPIResponse();
	}

	private void initAPIResponse() {
		if (data != null) {
			String count = data.getCount();
			if (count != null) {
				setCode("0");
				setMsg("查询成功！");
				JSONObject content = new JSONObject();
				content.put("abroad", data.getAbroad());
				content.put("categoryList", getCategoryList());
				content.put("brandList", getBrandList());
				content.put("sizeList", getSizeList());
				content.put("colorList", getColorList());
				content.put("productList", getProductList());
				content.put("conditionList", getTagList());
				this.setContent(content);
			} else {
				setCode("1");
				setMsg("查询失败！");
			}
		}
	}

	private JSONArray getProductList() {
		JSONArray productList = new JSONArray();
		List<Product> list = data.getProductList();
		if (list != null && list.size() > 0) {
			productList.addAll(list);
		}
		return productList;
		
	}

	private JSONArray getColorList() {
		JSONArray colorList = new JSONArray();
		List<Color> list = data.getColorList();
		if (list != null && !list.isEmpty()) {
			for (Color color : list) {
				String id = color.getId();
				String name = color.getName();
				String rgb=color.getRgb();
				if (name != null && !"".equals(name)) {
					JSONObject colorObj = new JSONObject();
					colorObj.put("id", id);
					colorObj.put("name", name);
					colorObj.put("rgb", rgb);
					colorList.add(colorObj);
				}
			}
		}
		return colorList;

	}

	private JSONArray getSizeList() {
		JSONArray sizeList = new JSONArray();
		List<Size> list = data.getSizeList();
		if (list != null && !list.isEmpty()) {
			for (Size size : list) {
				String id = size.getId();
				String name = size.getName();
				if (name != null && !"".equals(name)) {
					JSONObject sizeObj = new JSONObject();
					sizeObj.put("id", id);
					sizeObj.put("name", name);
					sizeList.add(sizeObj);
				}
			}
		}
		return sizeList;

	}
	private JSONArray getTagList() {
        JSONArray tagList = new JSONArray();
        List<SearchHotWords> list = data.getConditionList();
        if (list != null && !list.isEmpty()) {
            for (SearchHotWords tag : list) {
                String id = tag.getId();
                String name = tag.getName();
                String type=tag.getType();
                if (id != null && !"".equals(id)) {
                    JSONObject tagObj = new JSONObject();
                    tagObj.put("id", id);
                    tagObj.put("name", name);
                    tagObj.put("type", type);
                    tagList.add(tagObj);
                }
            }
        }
        return tagList;

    }
	private JSONArray getBrandList() {
		JSONArray brandList = new JSONArray();
		List<Brand> list = data.getBrandList();
		if (list != null && !list.isEmpty()) {
			for (Brand brand : list) {
				String id = brand.getId();
//				String name = brand.getNameCN();产品需要英文名
				String nameEN = brand.getNameEN();
				if (nameEN != null && !nameEN.isEmpty()) {
					JSONObject brandObj = new JSONObject();
					brandObj.put("id", id);
					brandObj.put("name", nameEN);
					brandObj.put("nameEN", nameEN);
					brandList.add(brandObj);
				}
			
			}
		}
		return brandList;
	}

	private void initCategoryList(int targetLength, String cate) {
		List<Category> categoryList = data.getCategoryList();
		if (categoryList != null && categoryList.size() > 0) {
			// 先查下有没有品类下的子类
			boolean flag = false;
			for (Category category : categoryList) {
				String id = category.getId();
				if (cate == null || (id.length() == targetLength && id.startsWith(cate))) {
					flag = true;
					break;
				}
			}
			for (Category category : categoryList) {
				String id = category.getId();
				if (flag) {// 有显示子类
					if (id.length() == targetLength) {
						if (cate != null && id.startsWith(cate)) {
							try {
								JSONObject obj = new JSONObject();
								String name = category.getName();
								if (name != null && !"".equals(name)) {
									obj.put("id", category.getId());
									obj.put("name", category.getName());
									cateJSONList.add(obj);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else if (cate == null) {
							try {
								JSONObject obj = new JSONObject();
								String name = category.getName();
								if (name != null && !"".equals(name)) {
									obj.put("id", category.getId());
									obj.put("name", category.getName());
									cateJSONList.add(obj);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				} else {// 否则显示自己
					if (id.equals(cate)) {
						try {
							JSONObject obj = new JSONObject();
							obj.put("id", category.getId());
							obj.put("name", category.getName());
							cateJSONList.add(obj);
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
		}

	}

	private JSONArray getCategoryList() {
		/*if (searchCategory != null && !"".equals(searchCategory)) {
			if (searchCategory.indexOf(",") >= 0) {
				String[] cates = searchCategory.split(",");
				for (String cate : cates) {
					// 显示查询品类的下一级，没有下一级显示自己
					int targetLength = cate.length() + 3;
					initCategoryList(targetLength, cate);
				}
			} else {
				int targetLength = searchCategory.length() + 3;
				initCategoryList(targetLength, searchCategory);
			}
		} else {
			// 没传品类的时候默认显示3级品类
			int targetLength = 9;
			initCategoryList(targetLength, searchCategory);
		}*/
		initCategoryList(12, searchCategory);
		return cateJSONList;
	}

}
