package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.wireless.api.api2client.domain.MallResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 尚品商城
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-08-28
 */
public class MallServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
    ShangPinService shangPinService = null;
    
    @Override
    public void init() throws ServletException {
        shangPinService = (ShangPinService) getBean(ShangPinService.class);
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String pageIndex=request.getParameter("pageIndex");
	    final String pageSize=request.getParameter("pageSize");
	    JSONObject galleryObj=null;
	    JSONObject categoryObj=null;
	    JSONObject brandObj=null;
	    JSONObject promotionObj=null;
	    try {
	        galleryObj=JSONObject.fromObject(shangPinService.queryGalleryList("2","3"));
	        if (Constants.SUCCESS.equals(galleryObj.getString("code"))) {
	            categoryObj=JSONObject.fromObject(shangPinService.queryMallCategoryList());
	            if(Constants.SUCCESS.equals(categoryObj.getString("code"))){
	                brandObj=JSONObject.fromObject(shangPinService.queryBrandList(pageIndex, pageSize));
	                if(Constants.SUCCESS.equals(brandObj.getString("code"))){
	                    promotionObj=JSONObject.fromObject(shangPinService.queryMallPromotionList());	
	                    if(Constants.SUCCESS.equals(promotionObj.getString("code"))){
	                        response.getWriter().print(new MallResponse().obj2Json(galleryObj,categoryObj,brandObj,promotionObj));
	                    }
	                }
	            }
            }else{
                
                WebUtil.sendApiException(response);
            }
        } catch (Exception e) {
            try {
                WebUtil.sendApiException(response);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
	    
	    
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}


  

}
