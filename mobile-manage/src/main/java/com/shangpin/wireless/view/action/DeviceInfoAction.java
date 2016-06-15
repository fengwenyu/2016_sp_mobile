package com.shangpin.wireless.view.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.DeviceInfo;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;

/**
 * 操作日志Action
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-18
 */
@Controller
@Scope("prototype")
public class DeviceInfoAction extends BaseAction<DeviceInfo> {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(DeviceInfoAction.class);
	private int page = 1;
	private int rows;

	/**
	 * 跳转至操作日志列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-18
	 * @param
	 * @Return
	 */
	public String list() {
		return "list";
	}

	/**
	 * 操作日志列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-18
	 * @param
	 * @Return
	 */
	@SuppressWarnings("unchecked")
	public String query() {
		try {
			// 1，构建查询条件
			SqlHelper sqlQueryListHelper = new SqlHelper("deviceinfo", "o");
			sqlQueryListHelper.addColumn("o.id");
			sqlQueryListHelper.addColumn("o.platform");
			sqlQueryListHelper.addColumn("o.osv");
			sqlQueryListHelper.addColumn("o.imei");
			sqlQueryListHelper.addColumn("o.apn");
			sqlQueryListHelper.addColumn("o.resolution");
			sqlQueryListHelper.addColumn("o.phoneType");
			sqlQueryListHelper.addColumn("o.phoneModel");
			sqlQueryListHelper.addColumn("o.operator");
			sqlQueryListHelper.addColumn("o.ua");
			sqlQueryListHelper.addColumn("o.browser");
			sqlQueryListHelper.addColumn("o.browserInfo");
			sqlQueryListHelper.addColumn("o.ip");
			sqlQueryListHelper.addColumn("o.city");
			sqlQueryListHelper.addColumn("o.createTime");
//			sqlQueryListHelper.addGroupByProperty("o.imei");
			sqlQueryListHelper.addOrderByProperty(true, "o.createTime", false);
			SqlHelper sqlQueryCountHelper = new SqlHelper("deviceinfo");
			sqlQueryCountHelper.addColumn("count(*)");
//			sqlQueryCountHelper.addGroupByProperty("imei");

			// 2，查询并准备分页信息
			List recorList = deviceInfoService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
			Integer count = deviceInfoService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recorList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
			// 记录操作日志
			// operateLogService.saveLog(userLogin, userLogin.getLoginName() + "查看操作日志列表");
		} catch (Exception e) {
			log.error("DeviceInfoAction-query() " + e);
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
}
