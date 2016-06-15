package com.shangpin.wireless.view.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.HotBrands;
import com.shangpin.wireless.domain.ReturnObject;

import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;

@Controller
@Scope("prototype")
public class HotBrandsAction extends BaseAction<HotBrands> {
	private static final long serialVersionUID = 3853043759988356693L;
	private int page = 1;
	private int rows;

	public String input() {
		return "input";
	}

	public String query() {
		try {
			// 1，构建查询条件
			SqlHelper sqlQueryListHelper = new SqlHelper("hotBrands", "t");
			SqlHelper sqlQueryCountHelper = new SqlHelper("hotBrands");
			sqlQueryListHelper.addColumn("t.brandName");
			sqlQueryListHelper.addColumn("t.id");
			sqlQueryListHelper.addColumn("t.brandId");
			sqlQueryListHelper.addColumn("t.imgUrl");
			sqlQueryListHelper.addColumn("t.imgHeight");
			sqlQueryListHelper.addColumn("t.imgWidth");
			sqlQueryListHelper.addColumn("t.topImgUrl");
			sqlQueryListHelper.addColumn("t.topImgHeight");
			sqlQueryListHelper.addColumn("t.topImgWidth");
			sqlQueryListHelper.addColumn("t.createDate");
			sqlQueryListHelper.addColumn("t.sort");
			sqlQueryCountHelper.addColumn("count(*)");
			sqlQueryListHelper.addOrderByProperty(true, "t.sort", true);
			sqlQueryListHelper.addOrderByProperty(true, "t.createDate", false);
			List recorList = hotBrandsService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
			Integer count = hotBrandsService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recorList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("hotBrandsService-query() " + e);
		}
		return null;
	}

	public String add() {
		try {
			hotBrandsService.add(model);
			ServletActionContext.getResponse().getWriter().write("success");
		} catch (Exception e) {
			log.error("hotBrandsService-add() " + e);
		}
		return null;
	}

	public String info() {
		HotBrands hotBrands = new HotBrands();
		try {
			hotBrands = hotBrandsService.findById(model.getId());
			JSONObject jsonObj = JSONObject.fromObject(hotBrands);
			ServletActionContext.getResponse().getWriter().print(jsonObj);
		} catch (Exception e) {
			log.error("hotBrandsService-info() " + e);
			e.printStackTrace();
		}
		return null;
	}

	public String update() {
		try {
			hotBrandsService.upadte(model);
			ServletActionContext.getResponse().getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String delete() {
		try {
			hotBrandsService.delete(model.getId());
			ServletActionContext.getResponse().getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
}
