package com.shangpin.wireless.view.action;

import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.Feedback;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;

/**
 * 信息反馈Action
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-22
 */
@Controller
@Scope("prototype")
public class FeedbackAction extends BaseAction<Feedback> {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(FeedbackAction.class);
	private int page = 1;
	private int rows;
	private String ids;
	private String startDate;
	private String endDate;
	private String productNum;
	private String channelNum;

	/**
	 * 跳转至信息反馈列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-22
	 * @param
	 * @Return
	 */
	public String list() {
		return "list";
	}

	/**
	 * 信息反馈列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-22
	 * @param
	 * @Return
	 */
	public String query() {
		try {
			// 1，构建查询条件
			SqlHelper sqlQueryListHelper = new SqlHelper("feedback", "f");
			SqlHelper sqlQueryCountHelper = new SqlHelper("feedback");
			sqlQueryListHelper.addColumn("f.id");
			sqlQueryListHelper.addColumn("f.userId");
			sqlQueryListHelper.addColumn("f.loginName");
			sqlQueryListHelper.addColumn("f.msg");
			sqlQueryListHelper.addColumn("f.product");
			sqlQueryListHelper.addColumn("f.ver");
			sqlQueryListHelper.addColumn("f.channel");
			sqlQueryListHelper.addColumn("f.createTime");
			sqlQueryListHelper.addColumn("f.platform");
			sqlQueryCountHelper.addColumn("count(*)");
			// ------------------组织查询条件 end-----------------
			if (model.getLoginName() != null && !"".equals(model.getLoginName())) {
				sqlQueryListHelper.addWhereCondition("f.loginName like'%" + model.getLoginName() + "%'");
				sqlQueryCountHelper.addWhereCondition("loginName like'%" + model.getLoginName() + "%'");
			}
			if (model.getPlatform() != null && !"".equals(model.getPlatform())) {
				sqlQueryListHelper.addWhereCondition("f.platform='" + model.getPlatform() + "'");
				sqlQueryCountHelper.addWhereCondition("platform='" + model.getPlatform() + "'");
			}
			if (productNum != null && !"".equals(productNum)) {
				sqlQueryListHelper.addWhereCondition("f.product=" + Long.valueOf(productNum));
				sqlQueryCountHelper.addWhereCondition("product=" + Long.valueOf(productNum));
			}
			if (channelNum != null && !"".equals(channelNum)) {
				sqlQueryListHelper.addWhereCondition("f.channel=" + Long.valueOf(channelNum));
				sqlQueryCountHelper.addWhereCondition("channel=" + Long.valueOf(channelNum));
			}
			if (model.getVer() != null && !"".equals(model.getVer())) {
				sqlQueryListHelper.addWhereCondition("f.ver='" + model.getVer() + "'");
				sqlQueryCountHelper.addWhereCondition("ver='" + model.getVer() + "'");
			}
			if (startDate != null && !"".equals(startDate.trim())) {
				sqlQueryListHelper.addWhereCondition("f.createTime>='" + startDate + " 00:00:00" + "'");
				sqlQueryCountHelper.addWhereCondition("createTime>='" + startDate + " 00:00:00" + "'");
			}
			if (endDate != null && !"".equals(endDate.trim())) {
				sqlQueryListHelper.addWhereCondition("f.createTime<='" + endDate + " 23:59:59" + "'");
				sqlQueryCountHelper.addWhereCondition("createTime<='" + endDate + " 23:59:59" + "'");
			}
			// ------------------查询条件 end-----------------
			sqlQueryListHelper.addOrderByProperty(true, "f.createTime", false);
			// 2，查询并准备分页信息
			List recorList = feedbackService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
			Integer count = feedbackService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recorList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("FeedbackAction-list() " + e);
		}
		return null;
	}

	/**
	 * 信息反馈详细信息
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-22
	 * @param
	 * @Return
	 */
	public String detail() {
		try {
			// 1，从数据库中获取原对象
			Feedback feedback = feedbackService.getById(model.getId());
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "id", "createTime", "reserve0", "reserve1", "reserve2", "reserve3", "reserve4", "reserve5" });
			ServletActionContext.getResponse().getWriter().print(JSONObject.fromObject(feedback, jsonConfig).toString());
		} catch (Exception e) {
			log.error("FeedbackAction-detail " + e);
		}
		return null;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}
}
