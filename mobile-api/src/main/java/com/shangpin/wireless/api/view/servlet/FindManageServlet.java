package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangpin.wireless.api.api2client.domain.FindManageListAPIResponse;
import com.shangpin.wireless.api.domain.DisplayEnum;
import com.shangpin.wireless.api.domain.FindManage;
import com.shangpin.wireless.api.domain.TypeEnum;
import com.shangpin.wireless.api.service.FindManageService;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取发现列表
 * @author wanghaibo
 * @createDate 2013-1-9
 *
 */
public class FindManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(FindManageServlet.class);
	private FindManageService findManageService;
	
	private static final int FINDMANAGE_KEEP_TIME	=  30 * 1000;	//	30分钟
	private static HashMap<String, JSONObject> FindManageJson = new HashMap<String, JSONObject>();
	private static long FindManageTime;
	
	private static final int OLD_FINDMANAGE_KEEP_TIME	=  30 * 1000;	//	30分钟
	private static HashMap<String, JSONObject> OldFindManageJson = new HashMap<String, JSONObject>();
	private static long OldFindManageTime;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			final String product = request.getHeader("p");
			final String ver = request.getHeader("ver");
//			 String product = "2";
//			 String ver = "2.0.1";
			String verType = "1";//1代表新版本，0代表老版本
			long now = System.currentTimeMillis();
			if (StringUtils.isNotEmpty(product) && StringUtils.isNotEmpty(ver)) {
				if ((product.equals("2") && StringUtil.compareVer(ver, "2.0.5") == -1) || (product.equals("102") && StringUtil.compareVer(ver, "2.0.5") == -1)) {
					verType = "0";
				}
			} 
			if(verType.equals("1")){
				if (now > FindManageTime + FINDMANAGE_KEEP_TIME) {
					FindManageJson.clear();
					List<FindManage> findManageList =new ArrayList<FindManage>();
					findManageList = findManageService.findAll();
					FindManage staticActivity= findManageService.findStaticActivity();
					FindManageListAPIResponse findManageListAPIResponse = new FindManageListAPIResponse();
					findManageListAPIResponse.setCode("0");
					
					if(findManageList != null && findManageList.size() > 0){
						findManageListAPIResponse.setFindManageList(findManageList);
					}
					if(staticActivity != null){
						findManageListAPIResponse.setStaticActivity(staticActivity);
					}
					JSONObject result = findManageListAPIResponse.obj2Json("1");
					FindManageJson.put("result", result);
					FindManageTime = now;
//					
				}
				JSONObject findManageJson = FindManageJson.get("result");
				response.getWriter().print(findManageJson.toString());
			}else{
				if (now > OldFindManageTime + OLD_FINDMANAGE_KEEP_TIME) {
					OldFindManageJson.clear();
					
					List<FindManage> oldFindManageList = new ArrayList<FindManage>();
					oldFindManageList = findManageService.findActManageByVer(verType);
					if (oldFindManageList != null && oldFindManageList.size() > 0) {
						for (int i = 0; i < oldFindManageList.size(); i++) {
							if (TypeEnum.STATICATC.name().equals(oldFindManageList.get(i).getType()) && oldFindManageList.get(i).getDisplay() == DisplayEnum.NO) {
								oldFindManageList.remove(i);
							}
						}
					}
					FindManageListAPIResponse findManageListAPIResponse = new FindManageListAPIResponse();
					findManageListAPIResponse.setCode("0");
					if (oldFindManageList != null && oldFindManageList.size() > 0) {
						findManageListAPIResponse.setOldFindManageList(oldFindManageList);
					}
					JSONObject result = findManageListAPIResponse.obj2Json( verType);
					OldFindManageJson.put("result", result);
					OldFindManageTime = now;
				}
				JSONObject oldfindManageJson = OldFindManageJson.get("result");
				response.getWriter().print(oldfindManageJson.toString());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("FindManageServlet：" + e);
			try {
				WebUtil.sendApiException(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext sc = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		findManageService = (FindManageService) ctx.getBean(FindManageService.SERVICE_NAME);
	}
	
	
}
