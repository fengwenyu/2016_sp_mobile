package com.shangpin.wireless.view.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.AppNavigation;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.domain.ShowTypeEnum;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;
/**
 * 奥莱导航管理
 * @author liling
 */
@Controller
@Scope("prototype")
public class AppNavigationAction  extends BaseAction<AppNavigation>{

	
	private static final long serialVersionUID = -5179795532094984731L;
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
			SqlHelper sqlQueryListHelper = new SqlHelper("app_navigation", "t");
			SqlHelper sqlQueryCountHelper = new SqlHelper("app_navigation");
			sqlQueryListHelper.addColumn("t.id");
			sqlQueryListHelper.addColumn("t.showType");
			sqlQueryListHelper.addColumn("t.name");
			sqlQueryListHelper.addColumn("t.link");
			sqlQueryListHelper.addColumn("t.createDate");
			sqlQueryListHelper.addColumn("t.startDate");
			sqlQueryListHelper.addColumn("t.endDate");
			sqlQueryListHelper.addColumn("t.tabId");
			sqlQueryListHelper.addColumn("t.sort");
			sqlQueryCountHelper.addColumn("count(*)");
		
			sqlQueryListHelper.addOrderByProperty(true, "ABS(t.sort)", true);
			sqlQueryListHelper.addOrderByProperty(true, "createDate", false);
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 2，查询并准备分页信息
			List<HashMap> recorList = appNavigationService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
			for(int i=0;i<recorList.size();i++){
				HashMap list = recorList.get(i);
				if(list.get("showType").toString().equals("2")){
					if(list.get("endDate") != null){
						if(sdf.parse(list.get("endDate").toString()).before(new Date())){
							recorList.remove(i);
						}
					}
				}
			}
			Integer count = recorList.size();
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recorList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("AppNavigationAction-datagridList() " + e);
		}
		return null;
	}
	/**
	 * 添加或者更新导航
	 * 
	 * @Author liling
	 * @createDate 2014-06-8
	 */
	public String edit() {
			try {
				ReturnObject returnObject = new ReturnObject();
				String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
			
				Pattern p = Pattern.compile(regEx);
				Matcher m = p.matcher(model.getName());
				while (m.find()) {
					returnObject.setReturnCode("fail");
					returnObject.setReturnInfo("名称中不能有特殊字符！");
					ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
					return null;
				}
				int len = model.getName().length();
				int i = 0;
				while (++i < len) {
					if (model.getName().charAt(i) == ' ') {
						returnObject.setReturnCode("fail");
						returnObject.setReturnInfo("名称中不能有空格！");
						ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
						return null;
					}
				}
				if(len>8){
					returnObject.setReturnCode("fail");
					returnObject.setReturnInfo("名称的长度不能大于8位！");
					ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
					return null;
				}
				if(!ShowTypeEnum.getValue(typeStr).equals(ShowTypeEnum.HTML)){
					int cont=Integer.valueOf(appNavigationService.getSameTabId(model));
					if(cont>0){
					
						returnObject.setReturnCode("fail");
						returnObject.setReturnInfo("tabId不能重复！");
						ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
						return null;
					}
				}
				model.setCreateDate(new Date());
				model.setShowType(ShowTypeEnum.getValue(typeStr));
				
				appNavigationService.edit(model);
				returnObject.setReturnCode("success");
				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
				
				operateLogService.saveLog(userLogin, userLogin.getLoginName() + "新增或者更新了奥莱客户端导航");
			} catch (Exception e) {
				log.error("AppNavigationAction-edit() " + e);
			}
		return null;
	}
	public String delete() {
		try {
			appNavigationService.delete(model.getId());
			ServletActionContext.getResponse().getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String info() {
		AppNavigation appNavigation = new AppNavigation();
		try {
			appNavigation = appNavigationService.findById(model.getId());
			JSONObject jsonObj = JSONObject.fromObject(JsonUtil.getJsonString4JavaPOJO(appNavigation,"yyyy-MM-dd HH:mm:ss"));
			ServletActionContext.getResponse().getWriter().print(jsonObj);
		} catch (Exception e) {
			log.error("appNavigationService-info() " + e);
			e.printStackTrace();
		}
		return null;
	}
	public String setSort() {
		AppNavigation appNavigation = new AppNavigation();
		try {
			appNavigation = appNavigationService.findById(model.getId());
			appNavigation.setSort(model.getSort());
			appNavigationService.edit(appNavigation);
	
		} catch (Exception e) {
			log.error("appNavigationAction-setSort() " + e);
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
