package com.shangpin.wireless.view.action;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.OperateLog;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;

/**
 * 操作日志Action
 * 
 * @Author  zhouyu
 * @CreatDate  2012-07-18
 */
@Controller
@Scope("prototype")
public class OperateLogAction extends BaseAction<OperateLog> {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(OperateLogAction.class);
	private int page = 1;
	private int rows;
	private String ids;
	private String startDate;
	private String endDate;

	/**
	 * 跳转至操作日志列表
	 * 
	 * @Author  zhouyu
	 * @CreatDate  2012-07-18
	 * @param  
	 * @Return 
	 */
	public String listUI() {
		return "toList";
	}

	/**
	 * 操作日志列表
	 * 
	 * @Author  zhouyu
	 * @CreatDate  2012-07-18
	 * @param  
	 * @Return 
	 */
	@SuppressWarnings("unchecked")
	public String list() {
		try {
			// 1，构建查询条件
			SqlHelper sqlQueryListHelper = new SqlHelper("operatelog", "o");
			SqlHelper sqlQueryCountHelper = new SqlHelper("operatelog");
			sqlQueryListHelper.addColumn("o.id");
			sqlQueryListHelper.addColumn("o.content");
			sqlQueryListHelper.addColumn("o.operateTime");
			sqlQueryListHelper.addColumn("o.operateUserName");
			sqlQueryCountHelper.addColumn("count(*)");
			// ------------------组织查询条件 end-----------------
			if (!"".equals(model.getOperateUserName()) && model.getOperateUserName() != null) {
				sqlQueryListHelper.addWhereCondition("o.operateUserName like'%" + model.getOperateUserName() + "'");
				sqlQueryCountHelper.addWhereCondition("operateUserName like'%" + model.getOperateUserName() + "'");
			}
			if (startDate != null && !"".equals(startDate.trim())) {
				sqlQueryListHelper.addWhereCondition("o.operateTime>='" + startDate + " 00:00:00" + "'");
				sqlQueryCountHelper.addWhereCondition("operateTime>='" + startDate + " 00:00:00" + "'");
			}
			if (endDate != null && !"".equals(endDate.trim())) {
				sqlQueryListHelper.addWhereCondition("o.operateTime<='" + endDate + " 23:59:59" + "'");
				sqlQueryCountHelper.addWhereCondition("operateTime<='" + endDate + " 23:59:59" + "'");
			}
			// ------------------查询条件 end-----------------
			sqlQueryListHelper.addOrderByProperty(true, "o.operateTime", false);

			// 2，查询并准备分页信息
			List recorList = operateLogService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
			Integer count = operateLogService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recorList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
			// 记录操作日志
			// operateLogService.saveLog(userLogin, userLogin.getLoginName() + "查看操作日志列表");
		} catch (Exception e) {
			log.error("OperateLogAction-list() " + e);
		}
		return null;
	}

	/**
	 * 删除操作日志
	 * 
	 * @Author  zhouyu
	 * @CreatDate  2012-07-18
	 * @param  
	 * @Return 
	 */
	public String delete() {
		ReturnObject returnObject = new ReturnObject();
		try {
			String[] split = ids.substring(0, ids.length() - 1).split("-");
			for (String id : split) {
				model = operateLogService.getById(Long.parseLong(id));
				operateLogService.delete(Long.parseLong(id));
				// 记录操作日志
				// operateLogService.saveLog(userLogin, userLogin.getLoginName() + "删除操作日志" + "'" + model.getContent() + "'");
			}
			returnObject.setReturnCode("1");// 正确标识符
		} catch (Exception e) {
			returnObject.setReturnCode("0");// 错误标识符
			returnObject.setReturnInfo("OperateLogAction-delete " + e);
			log.error("OperateLogAction-delete " + e);
		}
		try {
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 操作日志详细信息
	 * 
	 * @Author  zhouyu
	 * @CreatDate  2012-07-18
	 * @param  
	 * @Return 
	 */
	public String detail() {
		try {
			// 1，从数据库中获取原对象
			OperateLog operateLog = operateLogService.getById(model.getId());
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "id", "operateTime", "reserve0", "reserve1", "reserve2", "reserve3", "reserve4", "reserve5" });
			ServletActionContext.getResponse().getWriter().print(JSONObject.fromObject(operateLog, jsonConfig).toString());
		} catch (Exception e) {
			log.error("OperateLogAction-detail " + e);
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
}
