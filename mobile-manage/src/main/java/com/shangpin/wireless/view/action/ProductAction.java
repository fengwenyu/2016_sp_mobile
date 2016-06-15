package com.shangpin.wireless.view.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.PageBean;
import com.shangpin.wireless.domain.Product;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.util.HqlHelper;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;

/**
 * 产品管理Action
 * 
 * @Author zhouyu
 * @CreatDate 2012-08-03
 */
@Controller
@Scope("prototype")
public class ProductAction extends BaseAction<Product> {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ProductAction.class);
	private int page = 1;
	private int rows;
	private String productnum;

	/** 添加页面 */
	public String addUI() {
		return "addUI";
	}

	/**
	 * 添加产品
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-03
	 * @param
	 * @Return
	 */
	public String add() {
		try {
			if (productnum == null || "".equals(productnum) || model.getProductName() == null || "".equals(model.getProductName()) || !productnum.matches("^[0-9]*$")) {
				if (productnum == null || "".equals(productnum)) {
					ActionContext.getContext().getValueStack().set("checkProductNum", "产品号不能为空");
				} else if (!productnum.matches("^[0-9]*$")) {
					ActionContext.getContext().getValueStack().set("checkProductNum", "产品号必须是数字");
				}
				if (model.getProductName() == null || "".equals(model.getProductName())) {
					ActionContext.getContext().getValueStack().set("checkProductName", "产品名不能为空");
				}
				return "addUI";
			} else {
				HqlHelper hqlHelper = new HqlHelper(Product.class, "p");
				hqlHelper.addWhereCondition("p.productName=?", model.getProductName());
				Product product = productService.getByCondition(hqlHelper);
				HqlHelper hqlHelper1 = new HqlHelper(Product.class, "p");
				hqlHelper1.addWhereCondition("p.productNum=?", Long.valueOf(productnum));
				Product product1 = productService.getByCondition(hqlHelper1);
				if (product == null && product1 == null) {
					model.setCreateTime(new Date());
					model.setProductNum(Long.valueOf(productnum));
					productService.save(model);
					// 记录操作日志
					operateLogService.saveLog(userLogin, userLogin.getLoginName() + "添加产品" + model.getProductName());
					model.setId(null);
					model.setProductName(null);
					setProductnum(null);
					ActionContext.getContext().getValueStack().set("checkProductName", "插入成功");
				} else {
					if (product != null)
						ActionContext.getContext().getValueStack().set("checkProductName", "产品名已经存在");
					if (product1 != null)
						ActionContext.getContext().getValueStack().set("checkProductNum", "产品号已经存在");
					return "addUI";
				}
			}
		} catch (Exception e) {
			log.error("ProductAction-add() " + e);
		}
		return "toList";
	}

	/**
	 * 产品列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-03
	 * @param
	 * @Return
	 */
	public String list() {
		try {
			// 1，构建查询条件
			HqlHelper hqlHelper = new HqlHelper(Product.class, "p");
			if (!"".equals(model.getProductName()) && model.getProductName() != null)
				hqlHelper.addWhereCondition("p.productName like'%" + model.getProductName() + "%'");
			if (model.getProductNum() != null)
				hqlHelper.addWhereCondition("p.productNum=?", model.getProductNum());
			hqlHelper.addOrderByProperty(true, "p.createTime", false);
			// 2，查询并准备分页信息
			PageBean pageBean = userService.getPageBean(pageNum, hqlHelper);
			ActionContext.getContext().getValueStack().push(pageBean);
			// 记录操作日志
			// operateLogService.saveLog(userLogin, userLogin.getLoginName() + "查看产品列表");
		} catch (Exception e) {
			log.error("ProductAction-list() " + e);
		}
		return "list";
	}

	/**
	 * 数据动态绑定-产品列表
	 * 
	 * @Author zhouyu
	 * @createDate 2012-08-03
	 * @param
	 * @Return
	 */
	public String datagridList() {
		try {
			// 1，构建查询条件
			SqlHelper sqlQueryListHelper = new SqlHelper("product", "p");
			SqlHelper sqlQueryCountHelper = new SqlHelper("product");
			sqlQueryListHelper.addColumn("p.id");
			sqlQueryListHelper.addColumn("p.productName");
			sqlQueryListHelper.addColumn("p.productNum");
			sqlQueryListHelper.addColumn("p.createTime");
			sqlQueryCountHelper.addColumn("count(*)");
			if (!"".equals(model.getProductName()) && model.getProductName() != null) {
				sqlQueryListHelper.addWhereCondition("p.productName like%'" + model.getProductName() + "%'");
				sqlQueryCountHelper.addWhereCondition("productName like%'" + model.getProductName() + "%'");
			}
			sqlQueryListHelper.addOrderByProperty(true, "p.createTime", false);

			// 2，查询并准备分页信息
			List recorList = productService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
			Integer count = productService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recorList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("ProductAction-datagridList() " + e);
		}
		return null;
	}

	/**
	 * 检验产品名是否可用
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-03
	 * @param
	 * @Return
	 */
	public String checkProductName() {
		// try {
		// HqlHelper hqlHelper = new HqlHelper(Product.class, "p");
		// hqlHelper.addWhereCondition("p.productName=?", model.getProductName());
		// Product product = productService.getByCondition(hqlHelper);
		// ReturnObject returnObject = new ReturnObject();
		// if (product == null)
		// returnObject.setReturnInfo("产品名可用");
		// else
		// returnObject.setReturnInfo("产品名已经存在");
		// ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		// } catch (Exception e) {
		// log.error("ProductAction-checkProductName() " + e);
		// }
		// return null;
		try {
			HqlHelper hqlHelper = new HqlHelper(Product.class, "p");
			hqlHelper.addWhereCondition("p.productName=?", model.getProductName());
			Product product = productService.getByCondition(hqlHelper);
			ReturnObject returnObject = new ReturnObject();
			if (model.getProductName() == null || "".equals(model.getProductName())) {
				returnObject.setReturnInfo("产品名不能为空");
			} else if (product == null) {
				returnObject.setReturnInfo("产品名可用");
			} else if (product != null) {
				returnObject.setReturnInfo("产品名已经存在");
			}
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("ChannelAction-checkChannelName() " + e);
		}
		return null;
	}

	/**
	 * 检验产品号是否可用
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-22
	 * @param
	 * @Return
	 */
	public String checkProductNum() {
		// try {
		// HqlHelper hqlHelper = new HqlHelper(Product.class, "p");
		// hqlHelper.addWhereCondition("p.productNum=?", model.getProductName());
		// Product product = productService.getByCondition(hqlHelper);
		// ReturnObject returnObject = new ReturnObject();
		// if (product == null)
		// returnObject.setReturnInfo("产品号可用");
		// else
		// returnObject.setReturnInfo("产品号已经存在");
		// ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		// } catch (Exception e) {
		// log.error("ProductAction-checkProductNum() " + e);
		// }
		// return null;
		try {
			HqlHelper hqlHelper = new HqlHelper(Product.class, "p");
			ReturnObject returnObject = new ReturnObject();
			if (productnum == null || "".equals(productnum)) {
				returnObject.setReturnInfo("产品号不能为空");
			} else {
				if (!productnum.matches("^[0-9]*$")) {
					returnObject.setReturnInfo("产品号必须是数字");
				} else {
					hqlHelper.addWhereCondition("p.productNum=?", Long.valueOf(productnum));
					Product product = productService.getByCondition(hqlHelper);
					if (product == null) {
						returnObject.setReturnInfo("产品号可用");
					} else {
						returnObject.setReturnInfo("产品号已经存在");
					}
				}
			}
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("ChannelAction-checkChannelNum() " + e);
		}
		return null;
	}

	/**
	 * 删除产品
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-03
	 * @param
	 * @Return
	 */
	public String delete() {
		try {
			Product product = productService.getById(model.getId());
			productService.delete(model.getId());
			// 记录操作日志
			operateLogService.saveLog(userLogin, userLogin.getLoginName() + "删除产品" + product.getProductName());
		} catch (Exception e) {
			log.error("ProductAction-delete " + e);
		}
		return "toList";
	}

	/**
	 * 跳转至修改页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-03
	 * @param
	 * @Return
	 */
	public String editUI() {
		try {
			Product product = productService.getById(model.getId());
			setProductnum(String.valueOf(product.getProductNum()));
			ActionContext.getContext().getValueStack().push(product);
		} catch (Exception e) {
			log.error("ProductAction-editUI " + e);
		}
		return "addUI";
	}

	/**
	 * 修改产品信息
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-03
	 * @param
	 * @Return
	 */
	public String edit() {
		try {
			String temp = null;
			// 1，从数据库中获取原对象
			Product product = productService.getById(model.getId());
			temp = product.getProductName();
			// 2，设置属性
			product.setProductName(model.getProductName());
			product.setProductNum(Long.valueOf(productnum));
			product.setPlatform(model.getPlatform());
			// 3，更新到数据库中
			productService.update(product);
			// 记录操作日志
			operateLogService.saveLog(userLogin, userLogin.getLoginName() + "将产品名‘" + temp + "’改为'" + model.getProductName() + "'");
		} catch (Exception e) {
			log.error("ProductAction-edit " + e);
		}
		return "toList";
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

	public String getProductnum() {
		return productnum;
	}

	public void setProductnum(String productnum) {
		this.productnum = productnum;
	}
}
