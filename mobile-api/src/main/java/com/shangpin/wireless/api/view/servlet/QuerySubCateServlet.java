package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.SearchService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.wireless.api.api2client.domain.BrandAPIResponse;
import com.shangpin.wireless.api.api2client.domain.CommonAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 用于尚品客户端查询全部品牌或者一级品类下的子品类列表内容
 * 
 * @author xupengcheng
 * 
 */
public class QuerySubCateServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SearchService searchService;
	private CommonService commonService;
	private ShangPinService shangPinService;

	@Override
	public void init() throws ServletException {
		searchService = (SearchService) getBean(SearchService.class);
		commonService = (CommonService) getBean(CommonService.class);
		shangPinService = (ShangPinService) getBean(ShangPinService.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cateId = req.getParameter("cateId");// 品类或者全部品牌ID，全部品牌默认值：0
		String userId = req.getParameter("userId");
		final String p = req.getHeader("p");
        final String version = req.getHeader("ver");
		// 默认返回9个
		String pageIndex = "1";
		String pageSize = "12";
		CommonAPIResponse apiResponse = new CommonAPIResponse();
		try {
			if (cateId != null && cateId.equals("0")) {// 查询全部品牌
			    String allBrand = shangPinService.queryBrandList();
			    String collectBrand = null;
			    if (!("2".equals(p) && com.shangpin.utils.StringUtil.compareVersion("2.8.0", "2.8.5", version) == 0) || ("102".equals(p) && com.shangpin.utils.StringUtil.compareVersion("2.8.2", "2.8.5", version) == 0)) {
			    	collectBrand = commonService.collectedBrandlistObj(userId, pageIndex, pageSize);
                }
				BrandAPIResponse apiResponse2 = new BrandAPIResponse();
				resp.getWriter().print(apiResponse2.obj2Json(allBrand, collectBrand).toString());
			} else {// 查询品类
				JSONObject cateObj = new JSONObject();
				JSONObject cateContent = new JSONObject();
				String categoryList = searchService.querySearchCategory(cateId, "");
				if (categoryList != null) {
					JSONObject obj = JSONObject.fromObject(categoryList);
					apiResponse.setCode(obj.getString("code"));
					apiResponse.setMsg(obj.getString("msg"));
					JSONArray list = new JSONArray();
					try {
						JSONObject content = obj.getJSONObject("content");
						if (content.getJSONArray("categoryList") != null && content.getJSONArray("categoryList").size() > 0) {
							list = content.getJSONArray("categoryList");//
						}
					} catch (Exception e) {
					}
					JSONObject allCategory = new JSONObject();
					allCategory.put("id", cateId);
					allCategory.put("name", "全部");
					String pic = Constants.BASE_API + "images/category/" + cateId.replace("|", ",") + ".png";
					allCategory.put("pic", pic);
					list.add(0, allCategory);
					cateObj.put("categoryList", list);
				}

				String commendBrand = commonService.categoryCommendBrandList(cateId, pageIndex, pageSize);
				if (commendBrand != null) {
					JSONObject obj = JSONObject.fromObject(commendBrand);
					apiResponse.setCode(obj.getString("code"));
					apiResponse.setMsg(obj.getString("msg"));
					JSONArray list = new JSONArray();
					try {
						JSONObject content = obj.getJSONObject("content");
						JSONArray list1 = content.getJSONArray("commendBrandList");//
						if (list1 != null && list1.size() > 0) {
							for (int i = 0; i < list1.size(); i++) {
								JSONObject brand = list1.getJSONObject(i);								
								brand.put("nameEN", brand.get("nameEN"));
                                brand.put("nameCN", brand.get("nameCN"));
                                brand.put("isFlagship", brand.get("isFlagship"));
                                brand.put("pic", brand.get("pic"));
                                brand.put("type", "3");
                                brand.put("name", "");
                                brand.put("refContent", brand.get("id"));
								list.add(brand);
							}
						}
					} catch (Exception e) {
					}
					cateObj.put("commendBrandList", list);
				}
				cateContent.put("allCategory", cateObj);
				apiResponse.setContent(cateContent);
				resp.getWriter().print(apiResponse.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				WebUtil.sendApiException(resp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
