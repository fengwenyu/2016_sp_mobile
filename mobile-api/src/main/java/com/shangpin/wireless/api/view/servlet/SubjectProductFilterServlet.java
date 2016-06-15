package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.AoLaiService;
import com.shangpin.wireless.api.api2client.domain.SubjectQueryConditionResponse;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 奥莱活动列表筛选条件
 * @author xupengcheng
 *
 */
public class SubjectProductFilterServlet extends BaseServlet {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected final Log log = LogFactory.getLog(SubjectProductFilterServlet.class);
	AoLaiService  aoLaiService=null;
    @Override
    public void init() throws ServletException {
        aoLaiService = (AoLaiService) getBean(AoLaiService.class);
    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String subjectNo = req.getParameter("subjectNo");
		PrintWriter writer = resp.getWriter();
		if(!StringUtil.isNotEmpty(subjectNo)){
			try {
				WebUtil.sendErrorInvalidParams(resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			String data = ""; 
			try {
				data = aoLaiService.queryCategorySizeList(subjectNo);
				SubjectQueryConditionResponse activityQueryConditionResponse = new SubjectQueryConditionResponse();
				data = activityQueryConditionResponse.obj2Json(data);
				writer.print(data);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("ActivityProductFilterServlet：" + e);
				try {
					WebUtil.sendApiException(resp);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

	class ActivityQueryCondition{
		
	}
	
}
