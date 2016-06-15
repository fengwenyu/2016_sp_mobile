package com.shangpin.wireless.view.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.manage.service.UserBehavioralStatisticsService;
import com.shangpin.wireless.util.ApplicationContextUtil;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.StringUtils;

/**
 * 用户app行为统计Action
 * 
 * @Author liling
 * @CreatDate 2013-12-06
 */
@Controller
@Scope("prototype")
public class UserBehavioralStatisticsAction extends ActionSupport {
	private static final long serialVersionUID = 1690695553155161657L;
	private String endDate;
	private String startDate;
	private String productNum;
	private int page = 1;
	private int rows;
	protected final Log log = LogFactory.getLog(UserBehavioralStatisticsAction.class);

	public String input() {
		return "input";
	}

	/**
	 * 查询统计列表
	 * 
	 * @Author liling
	 * @createDate 2013-12-6
	 */
	@SuppressWarnings("rawtypes")
	public String query() {
		ApplicationContext ac = ApplicationContextUtil.get();
		UserBehavioralStatisticsService userBehavioralStatisticsService = (UserBehavioralStatisticsService) ac.getBean(UserBehavioralStatisticsService.SERVICE_NAME);
		if (productNum != null) {
			try {
				StringBuffer res = new StringBuffer("SELECT SUM(COUNT)times,COUNT(*)imeiNum,behaviorName FROM( SELECT COUNT(*)COUNT,behaviorName,imei,date FROM ");
				if (productNum.equals("1")) {
					res.append("userBehavioralAolaiIphone u ");
				}
				if (productNum.equals("2")) {
					res.append("userBehavioralSPIphone u ");
				}
				if (productNum.equals("101")) {
					res.append("userBehavioralAolaiAndriod u ");
				}
				if (productNum.equals("102")) {
					res.append("userBehavioralSPAndriod u ");
				}
				if (StringUtils.isNotEmpty(startDate)) {
					res.append("where u.date>'");
					res.append(startDate + " 00:00:00' ");
				}
				if (StringUtils.isNotEmpty(endDate)) {
					if (StringUtils.isEmpty(startDate)) {
						res.append("where u.date<'");
					} else {
						res.append("and u.date<'");
					}
					res.append(endDate + "  23:59:59'");
				}
				res.append("GROUP BY behaviorName,imei,DATE )  t GROUP BY t.behaviorName ");
				StringBuffer sqlCount = new StringBuffer();
				sqlCount.append("select count(*) from(");
				sqlCount.append(res.toString());
				sqlCount.append(")s");
				// 2，查询并准备分页信息
				List recorList = userBehavioralStatisticsService.executeSqlToMap(res.toString(), page, rows);
				Integer count = userBehavioralStatisticsService.executeSqlCount(sqlCount.toString());
				ReturnObject returnObject = new ReturnObject();
				returnObject.setTotal(count);
				returnObject.setRows(recorList);
				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
			} catch (Exception e) {
				log.error("UserBehavioralStatisticsAction-query() " + e);
			}
			return null;
		}
		return null;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}


}
