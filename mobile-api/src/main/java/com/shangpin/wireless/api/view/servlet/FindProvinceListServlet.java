package com.shangpin.wireless.api.view.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.shangpin.biz.service.ASPBizDistrictService;

/**
 * 获取省级行政区接口
 * 
 * @author cuibinqiang
 *
 */
public class FindProvinceListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(FindProvinceListServlet.class);
	
	@Autowired 
	ASPBizDistrictService districtService;
	
	@Override
	public void init() throws ServletException {
		districtService = (ASPBizDistrictService) getBean(ASPBizDistrictService.class);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			String data = districtService.provinceList();
			JSONObject obj = JSONObject.fromObject(data);
            response.getWriter().print(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("FindProvinceListServlet:"+e);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		doGet(request,response);
	}

}
