package com.shangpin.wireless.view.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.AolaiActivity;
import com.shangpin.wireless.domain.DisplayEnum;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;

/**
 * 奥莱活动管理
 * 
 * @author liling
 */
@Controller
@Scope("prototype")
public class AolaiActivityAction extends BaseAction<AolaiActivity> {
	private static final long serialVersionUID = 4998495104863508859L;
	private int page = 1;
	private int rows;
	private String typeStr;

	public String input() {
		return "input";
	}

	/**
	 * 数据动态绑定-推荐列表
	 * 
	 * @Author liling
	 * @createDate 2014-06-8
	 */
	public String datagridList() {
		try {
			// 1，构建查询条件
			SqlHelper sqlQueryListHelper = new SqlHelper("aolai_activity", "t");
			SqlHelper sqlQueryCountHelper = new SqlHelper("aolai_activity");
			sqlQueryListHelper.addColumn("t.id");
			sqlQueryListHelper.addColumn("t.startTime");
			sqlQueryListHelper.addColumn("t.endTime");
			sqlQueryListHelper.addColumn("t.getUrl");
			sqlQueryListHelper.addColumn("t.createDate");
			sqlQueryListHelper.addColumn("t.display");
			sqlQueryCountHelper.addColumn("count(*)");
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sqlQueryListHelper.addWhereCondition("t.endTime>=" + "'" + sdf.format(new Date()) + "'");
			sqlQueryCountHelper.addWhereCondition("endTime>=" + "'" + sdf.format(new Date()) + "'");
			sqlQueryListHelper.addOrderByProperty(true, "createDate", false);
			// 2，查询并准备分页信息
			List recorList = aolaiActivityService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
			Integer count = aolaiActivityService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recorList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("AolaiActivityAction-datagridList() " + e);
		}
		return null;
	}

	/**
	 * 添加或者更新奥莱静态活动
	 * 
	 * @Author liling
	 * @createDate 2014-06-8
	 */
	public String edit() {
		try {
			ReturnObject returnObject = new ReturnObject();
			if (model.getId() == null) {
				if (model.getDisplay() == DisplayEnum.YES) {
					Map<String, Object> map = new HashMap<String, Object>();
					map = aolaiActivityService.isExist(model.getStartTime(), model.getEndTime());
					if (map.get("isExist").equals("true")) {
						returnObject.setReturnCode("fail");
					
						returnObject.setReturnInfo("当前时间段内已存在开启的静态页");
						ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
						return null;
					}
				}
			}
			if (model.getId() != null) {
				if (model.getDisplay() == DisplayEnum.YES) {
					Map<String, Object> map = new HashMap<String, Object>();
					map = aolaiActivityService.isExist(model.getStartTime(), model.getEndTime(),model.getId());
					if (map.get("isExist").equals("true")) {
						returnObject.setReturnCode("fail");
						returnObject.setReturnInfo("当前时间段内已存在开启的静态页");
						ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
						return null;
					}
				}
			}
			model.setCreateDate(new Date());
			aolaiActivityService.edit(model);
			returnObject.setReturnCode("success");
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
			operateLogService.saveLog(userLogin, userLogin.getLoginName() + "新增或者更新了奥莱静态活动");
		} catch (Exception e) {
			log.error("AolaiActivityAction-edit() " + e);
		}
		return null;
	}

	public String delete() {
		try {
			aolaiActivityService.delete(model.getId());
			ServletActionContext.getResponse().getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String info() {
		AolaiActivity aolaiActivity = new AolaiActivity();
		try {
			aolaiActivity = aolaiActivityService.findById(model.getId());
			JSONObject jsonObj = JSONObject.fromObject(JsonUtil.getJsonString4JavaPOJO(aolaiActivity,"yyyy-MM-dd HH:mm:ss"));
			ServletActionContext.getResponse().getWriter().print(jsonObj);
		} catch (Exception e) {
			log.error("aolaiActivityService-info() " + e);
			e.printStackTrace();
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

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
}
