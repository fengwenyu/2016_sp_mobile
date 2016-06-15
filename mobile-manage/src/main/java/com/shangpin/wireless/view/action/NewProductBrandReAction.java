package com.shangpin.wireless.view.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.NewProductBrand;
import com.shangpin.wireless.domain.ReturnObject;

import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;
import com.shangpin.wireless.util.StringUtils;

/**
 * 首页新品和品牌推荐Action
 * 
 * @Author liling
 * @CreatDate 2013-09-01
 */
@Controller
@Scope("prototype")
public class NewProductBrandReAction extends BaseAction<NewProductBrand> {
	private static final long serialVersionUID = 3609751469926445844L;
	private int page = 1;
	private int rows;

	public String addNewProduct() {
		return "addNewProduct";
	}

	/**
	 * 添加或者更新推荐列表
	 * 
	 * @Author liling
	 * @createDate 2013-09-5
	 */
	public String editAll() {
		if (model.getCategoryId() != null) {
			try {
				newProductBrandReService.editAll(model);
				ServletActionContext.getResponse().getWriter().write("success");
				String logstr=getLogStr(model);
				operateLogService.saveLog(userLogin, userLogin.getLoginName() + logstr);
			} catch (Exception e) {
				log.error("NewProductBrandReAction-editAll() " + e);
			}
		}
		return null;
	}
	

	/**
	 * 数据动态绑定-推荐列表
	 * 
	 * @Author liling
	 * @createDate 2013-09-5
	 */
	public String datagridList() {
		try {
			// 1，构建查询条件
			SqlHelper sqlQueryListHelper = new SqlHelper("newProductBrand", "t");
			SqlHelper sqlQueryCountHelper = new SqlHelper("newProductBrand");
			sqlQueryListHelper.addColumn("t.categoryId");
			sqlQueryListHelper.addColumn("t.firstNewProductId");
			sqlQueryListHelper.addColumn("t.secondNewProductId");
			sqlQueryListHelper.addColumn("t.firstBrandProductId");
			sqlQueryListHelper.addColumn("t.firstBrandName");
			sqlQueryListHelper.addColumn("t.secondBrandName");
			sqlQueryListHelper.addColumn("t.threeBrandName");
			sqlQueryListHelper.addColumn("t.fourthBrandName");
			sqlQueryListHelper.addColumn("t.fifthBrandName");
			sqlQueryListHelper.addColumn("t.sixthBrandName");
			sqlQueryListHelper.addColumn("t.seventhBrandName");
			sqlQueryListHelper.addColumn("t.firstBrandId");
			sqlQueryListHelper.addColumn("t.secondBrandId");
			sqlQueryListHelper.addColumn("t.threeBrandId");
			sqlQueryListHelper.addColumn("t.fourthBrandId");
			sqlQueryListHelper.addColumn("t.fifthBrandId");
			sqlQueryListHelper.addColumn("t.sixthBrandId");
			sqlQueryListHelper.addColumn("t.seventhBrandId");
			sqlQueryListHelper.addColumn("t.createTime");
			sqlQueryCountHelper.addColumn("count(*)");
			if (StringUtils.isNotEmpty(model.getCategoryId()) && !model.getCategoryId().equals("-1")) {
				sqlQueryListHelper.addWhereCondition("t.categoryId=" + "'" + model.getCategoryId() + "'");
				 sqlQueryCountHelper.addWhereCondition("categoryId=" + "'" + model.getCategoryId() + "'");
			}
			sqlQueryListHelper.addOrderByProperty(true, "t.createTime", false);
			// 2，查询并准备分页信息
			List recorList = newProductBrandReService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
			Integer count = newProductBrandReService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recorList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("NewProductBrandReAction-datagridList() " + e);
		}
		return null;
	}

	/**
	 * 通过categoryId获得品类下推荐位的详细信息
	 * 
	 * @Author liling
	 * @createDate 2013-09-5
	 */
	public String info() {
		NewProductBrand newProductBrand = new NewProductBrand();
		try {
			newProductBrand = newProductBrandReService.findByCategoryID(model.getCategoryId());
			JSONObject jsonObj = JSONObject.fromObject(newProductBrand);
			ServletActionContext.getResponse().getWriter().print(jsonObj);
		} catch (Exception e) {
			log.error("NewProductBrandReAction-info() " + e);
			e.printStackTrace();
		}
		return null;
	}

	private static String getLogStr(NewProductBrand model) {
		StringBuffer logstr = new StringBuffer("增加或修改了");
		// 记录操作日志
		if (model.getCategoryId().endsWith("A01B02")) {
			logstr.append("女士服装频道首页推荐位,修改内容为：");
		}
		if (model.getCategoryId().endsWith("A01B03")) {
			logstr.append("女士鞋靴频道首页推荐位,修改内容为：");
		}
		if (model.getCategoryId().endsWith("A01B01")) {
			logstr.append("女士箱包频道首页推荐位,修改内容为：");
		}
		if (model.getCategoryId().endsWith("A01B04")) {
			logstr.append("女士配饰频道首页推荐位,修改内容为：");
		}
		if (model.getCategoryId().endsWith("A02B02")) {
			logstr.append("男士服装频道首页推荐位,修改内容为：");
		}
		if (model.getCategoryId().endsWith("A02B03")) {
			logstr.append("男士鞋靴频道首页推荐位,修改内容为：");
		}
		if (model.getCategoryId().endsWith("A02B01")) {
			logstr.append("男士箱包频道首页推荐位,修改内容为：");
		}
		if (model.getCategoryId().endsWith("A02B04")) {
			logstr.append("男士配饰频道首页推荐位,修改内容为：");
		}
		logstr.append("新品推荐: ");
		logstr.append("位置1: " + model.getFirstNewProductId());
		logstr.append(",位置2: " + model.getSecondNewProductId());
		logstr.append(", 品牌推荐: ");
		logstr.append("位置1ID: " + model.getFirstBrandId() + ",位置1名称: " + model.getFirstBrandName() + ",位置1商品编号: " + model.getFirstBrandProductId());
		logstr.append(",位置2ID: " + model.getSecondBrandId() + ",位置2名称: " + model.getSecondBrandName());
		logstr.append(",位置3ID: " + model.getThreeBrandId() + "位置3名称: " + model.getThreeBrandName());
		logstr.append(",位置4ID：" + model.getFourthBrandId() + ",位置4名称: " + model.getFourthBrandName());
		logstr.append(",位置5ID：" + model.getFifthBrandId() + ",位置5名称: " + model.getFifthBrandName());
		logstr.append(",位置6ID：" + model.getSixthBrandId() + ",位置6名称: " + model.getSixthBrandName());
		logstr.append(",位置7ID: " + model.getSeventhBrandId() + ",位置7名称: " + model.getSeventhBrandName());
		return logstr.toString();
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
