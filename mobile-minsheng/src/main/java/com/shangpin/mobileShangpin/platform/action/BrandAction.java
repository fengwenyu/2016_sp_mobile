package com.shangpin.mobileShangpin.platform.action;

import java.util.List;

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
import com.shangpin.mobileShangpin.common.util.SysContent;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.BrandService;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;
import com.shangpin.mobileShangpin.platform.vo.SPBrandVO;
import com.shangpin.mobileShangpin.platform.vo.SPMerchandiseVO;


/**
 * 品牌Action
 * 
 * @Author liling
 * @CreatDate 2013-07-28
 */
@Controller
@ParentPackage("mobileShangpin")
@Scope("prototype")
@Actions({ @Action(value = ("/brandaction"), results = {//
@Result(name = "brandlist", location = "/WEB-INF/pages/market/brandList.jsp"),//
		@Result(name = "getMore", type = "json", params = { "root", "data" }), @Result(name = "spproducts", location = "/WEB-INF/pages/market/spProductsBrandList.jsp") }) })
public class BrandAction extends BaseAction {
	private static final long serialVersionUID = 2833408077637562315L;
	private HttpServletRequest request = ServletActionContext.getRequest();
	@Autowired
	BrandService brandService;
	private String gender;
	// 判断是否有加载更多
	private Integer haveMore;
	// 加载更多的时候传输对象
	private JSONObject data;
	//页面导航品牌名字
	private String brandName;
	//分类
	private String categoryid;

	/**
	 * 跳转到男士或女士品牌列表页
	 * 
	 * @Author liling
	 * @CreatDate 2013-07-29
	 */
	public String getSPBrands() {
		try {
			String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
			if (StringUtils.isEmpty(channelNo)) {
				channelNo = Constants.SP_WAP_DEFAULT_CHANNELNO;// wap尚品渠道号
				SysContent.getRequest().getSession().removeAttribute(Constants.CHANNEL_PARAM_NAME);
			} else {
				SysContent.getRequest().getSession().setAttribute(Constants.CHANNEL_PARAM_NAME, channelNo);
				ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME, channelNo);
			}
			gender = request.getParameter("gender");// 性别 0代表女，1代表男
			if (gender == null || StringUtil.isBlank(gender)) {
				gender = "0";
			}
			List<SPBrandVO> spBrandList = brandService.getSPBrands(gender);
			if (null != spBrandList) {
				ActionContext.getContext().getValueStack().set("spBrandList", spBrandList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "brandlist";
	}

	/**
	 * 跳转到品牌商品列表页
	 * 
	 * @Author liling
	 * @CreatDate 2013-07-29
	 */
	public String getSPProductsBrand() {
		try {
			String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
			if (StringUtils.isEmpty(channelNo)) {
				channelNo = Constants.SP_WAP_DEFAULT_CHANNELNO;// wap尚品渠道号
				SysContent.getRequest().getSession().removeAttribute(Constants.CHANNEL_PARAM_NAME);
			} else {
				SysContent.getRequest().getSession().setAttribute(Constants.CHANNEL_PARAM_NAME, channelNo);
				ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME, channelNo);
			}
			String brandid = request.getParameter("brandid");
			ActionContext.getContext().getValueStack().set("bid",brandid);//站内导航图
			AccountVO user = WebUtil.getSessionUser();
			String userid = "";
			if (null != user && null != user.getUserid()) {
				userid = user.getUserid();
			}
			gender = request.getParameter("gender");// 性别 0代表女，1代表男
			if (gender == null || StringUtil.isBlank(gender)) {
				gender = "0";
			}
			if(categoryid==null||"".equals(categoryid)){
				if("0".equals(gender)){
					categoryid="A01";
				}else{
					categoryid="A02";
				}
			}
			List<SPMerchandiseVO> merchandiseList = brandService.SPProductsBrand(userid,categoryid, brandid, gender, 1, Page.DEFAULT_PAGE_SIZE);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "spproducts";
	}

	/**
	 * 加载更多
	 * 
	 * @Author liling
	 * @CreatDate 2013-07-29
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
			if(categoryid==null||"".equals(categoryid)){
				if("0".equals(gender)){
					categoryid="A01";
				}else{
					categoryid="A02";
				}
			}
			String brandid = request.getParameter("brandid");
			List<SPMerchandiseVO> merchandiseList = brandService.SPProductsBrand(userid, categoryid,brandid, gender, PageContext.getOffset() == 0 ? 1 : PageContext.getOffset(), Page.DEFAULT_PAGE_SIZE);
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getHaveMore() {
		return haveMore;
	}

	public void setHaveMore(Integer haveMore) {
		this.haveMore = haveMore;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	
	
}