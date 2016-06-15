package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.util.DataContainerPool;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.HotBrandsUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取尚品热门品牌商品列表
 * @author xupengcheng
 * @createDate 2013-12-31 下午06:58:14
 *
 */
public class HotBrandsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(HotBrandsServlet.class);
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		try {
			Object object = DataContainerPool.brandContainer.get("HOT_BRAND_LIST");
			String result = null;
			if(object != null &&!"".equals(object)){
				result = (String) object;
			}else{
				HotBrandsUtil.refreshBrandsList();
				Object o = DataContainerPool.brandContainer.get("HOT_BRAND_LIST");
				if(o != null && !o.equals("")){
					result = (String) o;
				}else{
					WebUtil.sendApiException(response);
				}
			}
			if(result!= null){
				response.getWriter().print(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("HotBrandsServlet：" + e);
			try {
				WebUtil.sendApiException(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
}
