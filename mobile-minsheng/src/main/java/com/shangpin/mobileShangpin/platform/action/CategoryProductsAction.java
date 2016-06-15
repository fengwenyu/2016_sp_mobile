package com.shangpin.mobileShangpin.platform.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import com.shangpin.mobileShangpin.common.alipay.util.StringUtil;
import com.shangpin.mobileShangpin.common.page.Page;
import com.shangpin.mobileShangpin.common.page.PageContext;
import com.shangpin.mobileShangpin.common.util.BaseAction;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.MutilSelectUtil;
import com.shangpin.mobileShangpin.common.util.SysContent;

import com.shangpin.mobileShangpin.common.util.WebUtil;

import com.shangpin.mobileShangpin.platform.service.CategoryProductService;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;

import com.shangpin.mobileShangpin.platform.vo.CategoryIndexVO;
import com.shangpin.mobileShangpin.platform.vo.SPCategoryVO;
import com.shangpin.mobileShangpin.platform.vo.SPMerchandiseVO;

/**
 * 分类下商品Action
 * 
 * @Author liling
 * @CreatDate 2013-07-28
 */
@Controller
@ParentPackage("mobileShangpin")
@Scope("prototype")
@Actions({ @Action(value = ("/categoryproductsaction"), results = {//
//
		@Result(name = "spList", location = "/WEB-INF/pages/market/spCategory.jsp"), @Result(name = "getMore", type = "json", params = { "root", "data" }) }) })
public class CategoryProductsAction extends BaseAction {
	private static final long serialVersionUID = 346909355660201917L;
	// 判断是否有加载更多
	private Integer haveMore;
	private List<SPCategoryVO> ppvList;
	private HttpServletRequest request = ServletActionContext.getRequest();
	// 页面头部显示的分类名
	private String categoryname;
	// 页面头部显示的二级分类名
	private String childCategoryname;
	// 页面头部显示的分类名所属的id
	private String categoryid;
	// 页面头部显示的子分类名所属的id
	private String childcategoryid;
	// 加载更多的时候传输对象
	private JSONObject data;
	private String gender;
	@Autowired
	CategoryProductService categoryProductService;

	/**
	 * 进入相应分类商品列表
	 * 
	 * @Author liling
	 * @CreatDate 2013-07-29
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String getCategoryProducts() {
		try {
			String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
			if (StringUtils.isEmpty(channelNo)) {
				channelNo = Constants.SP_WAP_DEFAULT_CHANNELNO;// wap尚品渠道号
				SysContent.getRequest().getSession().removeAttribute(Constants.CHANNEL_PARAM_NAME);
			} else {
				SysContent.getRequest().getSession().setAttribute(Constants.CHANNEL_PARAM_NAME, channelNo);
				ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME, channelNo);
			}
			AccountVO user = WebUtil.getSessionUser();
			String userid = "";
			if (null != user && null != user.getUserid()) {
				userid = user.getUserid();
			}
			gender = request.getParameter("gender");// 性别 0代表女，1代表男
			if (gender == null || StringUtil.isBlank(gender)) {
				gender = "0";
			}
			categoryid = request.getParameter("categoryid");
			childcategoryid = request.getParameter("childcategoryid");// 子分类（包含二级分类和三级分类）
			if (childcategoryid == null || StringUtil.isBlank(childcategoryid)) {
				childcategoryid = categoryid;
			}
			else{
				
			}
			// 如果子分类参数不为空，则查询自子类下的商品列表
			List<SPMerchandiseVO> merchandiseList = categoryProductService.getNewProducts(userid, childcategoryid, gender, 1, Page.DEFAULT_PAGE_SIZE);
			if (null != merchandiseList) {
				if (merchandiseList.size() < Page.DEFAULT_PAGE_SIZE) {
					haveMore = 0;
				} else {
					haveMore = 1;
				}
			} else {
				haveMore = 0;
			}
			ActionContext.getContext().getValueStack().set("merchandiseList", merchandiseList);
			List<CategoryIndexVO> dataList = MutilSelectUtil.getChildCategoryList(categoryid, gender);
			ActionContext.getContext().getValueStack().set("ppvList", dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "spList";
	}

	/**
	 * 加载更多
	 * 
	 * @Author liling
	 * @CreatDate 2013-07-29
	 * @param
	 * @Return
	 */
	public String getmore() {
		try {
			String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
			if (StringUtils.isEmpty(channelNo)) {
				channelNo = Constants.SP_WAP_DEFAULT_CHANNELNO;// wap尚品渠道号
				SysContent.getRequest().getSession().removeAttribute(Constants.CHANNEL_PARAM_NAME);
			} else {
				SysContent.getRequest().getSession().setAttribute(Constants.CHANNEL_PARAM_NAME, channelNo);
				ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME, channelNo);
			}
			AccountVO user = WebUtil.getSessionUser();
			String userid = "";
			if (null != user && null != user.getUserid()) {
				userid = user.getUserid();
			}
			gender = request.getParameter("gender");// 性别 0代表女，1代表男
			if (gender == null || StringUtil.isBlank(gender)) {
				gender = "0";
			}
			categoryid = request.getParameter("categoryid");
			String childcategoryid = request.getParameter("childcategoryid");
//			if (categoryid == null || StringUtil.isBlank(categoryid)) {
//				categoryid = "A01B01";
//			}
			if (childcategoryid == null || StringUtil.isBlank(childcategoryid)) {
				childcategoryid = categoryid;
			}
			List<SPMerchandiseVO> merchandiseList = categoryProductService.getNewProducts(userid, childcategoryid, gender, PageContext.getOffset() == 0 ? 1 : PageContext.getOffset(), Page.DEFAULT_PAGE_SIZE);
		
			data = new JSONObject();
			if (merchandiseList == null) {
				data.put("code", 1);
				data.put("haveMore", "0");// 1:有，0:没有
			} else {
				System.out.println("SIZE  " + merchandiseList.size());
				data.put("merchandiseList", JSONArray.fromObject(merchandiseList));
				data.put("code", 1);
				data.put("haveMore", merchandiseList.size() < Page.DEFAULT_PAGE_SIZE ? "0" : "1");// 1:有，0:没有
				data.put("pageIndex", PageContext.getOffset() == 0 ? 1 : PageContext.getOffset());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "getMore";
	}

	public Integer getHaveMore() {
		return haveMore;
	}

	public void setHaveMore(Integer haveMore) {
		this.haveMore = haveMore;
	}

	public List<SPCategoryVO> getPpvList() {
		return ppvList;
	}

	public void setPpvList(List<SPCategoryVO> ppvList) {
		this.ppvList = ppvList;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getChildCategoryname() {
		return childCategoryname;
	}

	public void setChildCategoryname(String childCategoryname) {
		this.childCategoryname = childCategoryname;
	}

	public String getChildcategoryid() {
		return childcategoryid;
	}

	public void setChildcategoryid(String childcategoryid) {
		this.childcategoryid = childcategoryid;
	}
	
}
