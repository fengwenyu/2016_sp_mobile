package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.base.service.SearchService;
import com.shangpin.biz.service.ASPBizBrandService;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.wireless.api.api2client.domain.CommonAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 品类搜索接口,第一个元素为全部品牌
 * @author xupengcheng
 *
 */
public class QueryCategoryServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SearchService searchService;
	
	ASPBizBrandService aspBizBrandService;

	@Override
	public void init() throws ServletException {
		searchService = (SearchService) getBean(SearchService.class);
		aspBizBrandService = (ASPBizBrandService) getBean(ASPBizBrandService.class);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String type = req.getParameter("type");//type=1，运营分类
	    final String version = req.getHeader("ver");
        final String product = req.getHeader("p");
//      final String imei = req.getHeader("imei");
//		final String userId = req.getHeader("userid");
		try {
			String data = null;
			if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.0", version) == 1) {
				data = searchService.queryCategorys("0", type);
			} else if (com.shangpin.utils.StringUtil.compareVersion("2.8.0", "2.9.0", version) == 0) {
				data = searchService.querySearchCategory("0", type, "1");
			} else {
				data = searchService.querySearchCategory("0", type);
			}
			
			CommonAPIResponse apiResponse = new CommonAPIResponse();
			if(data != null){
				try{
					JSONObject obj = JSONObject.fromObject(data);
					apiResponse.setCode(obj.getString("code"));
					apiResponse.setMsg(obj.getString("msg"));
					JSONObject content = obj.getJSONObject("content");
					JSONArray list = content.getJSONArray("categoryList");
					JSONObject contentNew = new JSONObject();
					if (StringUtil.compareVer(version, "2.7.0") >= 0) {
						contentNew.put("list",list);
						if (StringUtil.compareVer(version, "2.8.0") == -1) {
							contentNew.put("brand",aspBizBrandService.doFavBrands("", "", "16"));
						}
						apiResponse.setContent(contentNew);
					} else {
						for(int i =0;i<list.size();i++){
							JSONObject category = list.getJSONObject(i);
							String id = category.getString("id").replace("|", ",");
							String pic = Constants.BASE_API + "images/category/" + id +".png";
							category.put("pic", pic);
							list.set(i, category);
						}
						content.remove("categoryList");
						JSONObject allBrand = new JSONObject();
						allBrand.put("id", "0");
						allBrand.put("name", "品牌");
						allBrand.put("nameEN", "Brand");
						if("2".equals(product)||"102".equals(product)){//兼容ios品牌位置写死
						    if (StringUtil.compareVer(version, "2.6.5") >= 0) {
						        list.add(allBrand);
						    } else {
						        list.add(0, allBrand);
						    }
	                    }
						content.put("list",list);
						apiResponse.setContent(content);
					}
					
					resp.getWriter().print(apiResponse.toString());
				}catch(Exception ee){
					ee.printStackTrace();
					try {
						WebUtil.sendApiException(resp);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}else{
				try {
					WebUtil.sendApiException(resp);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
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


