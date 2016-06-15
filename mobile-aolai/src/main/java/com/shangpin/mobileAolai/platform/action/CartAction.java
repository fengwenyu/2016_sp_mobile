package com.shangpin.mobileAolai.platform.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.CookieUtil;
import com.shangpin.mobileAolai.common.util.StringUtil;
import com.shangpin.mobileAolai.common.util.SysContent;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.service.CartService;
import com.shangpin.mobileAolai.platform.vo.AccountVO;
import com.shangpin.mobileAolai.platform.vo.ConsigneeAddressVO;
import com.shangpin.mobileAolai.platform.vo.MerchandiseVO;
/**
 * 购物袋管理Action
 * 
 * @Author yumeng
 * @CreatDate 2012-11-2
 */
@Controller
@ParentPackage("mobileAolai")
@Scope("prototype")
@Actions({ @Action(value = ("/cartaction"), results = {//
		@Result(name = "deleteCart", type = "json", params = { "root","entityJson" }),
		@Result(name = "showCart", location = "/WEB-INF/pages/order/cart.jsp"),
		@Result(name = "index", type = "redirect", location = "aolaiindex!index"),
		@Result(name = "login", type = "redirect", location = "accountaction!loginui?loginFrom=4&ch=${ch}"),
		@Result(name = "back", type = "json", params = { "root","entityJson" }),
		@Result(name = "detail", location = "/WEB-INF/pages/market/merchandiseDetail.jsp")//,
		//@Result(name = "toCartList", type = "redirect", location = "cartaction!showcart?errorMsg=${errorMsg}")
		}) })
public class CartAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6241749617103279391L;
	@Autowired
	private CartService cartService;

	private List<MerchandiseVO> merchandiseList;
	/** 实体数据转json */
	private JSONObject entityJson;
	private String skuid;
	private ConsigneeAddressVO consigneeAddressVO;
	private String msg;
	private String ch;
	@SuppressWarnings("unused")
	private String platform;
	/** 记住用户当前的URL */
//	private String url;

	/**
	 * 删除购物袋中商品
	 * 
	 * @return
	 */
	public String deletecart() {
		String skuid=ServletActionContext.getRequest().getParameter("skuid");
		String gid = ServletActionContext.getRequest().getParameter("gid");
		String shoppingcartdetailid = ServletActionContext.getRequest().getParameter("shoppingcartdetailid");
		String groupno = ServletActionContext.getRequest().getParameter("groupno");
		String count = ServletActionContext.getRequest().getParameter("count");
		
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "index";
		}
		String res = cartService.removeCartOfMerchandise(user.getUserid(), skuid, gid, groupno, shoppingcartdetailid, count, "2", "1");
		entityJson = new JSONObject();
		if (null != res && Constants.SUCCESS.equals(res)) {
			entityJson.put("success", true);
		} else {
			entityJson.put("success", false);
			entityJson.put("msg", "删除失败！");
		}
		return "deleteCart";
	}

	/**
	 * 跳转到购物袋首页面
	 * 
	 * @return
	 */
	public String showcart() {
		AccountVO user = WebUtil.getSessionUser();
//		String ch = (String) ServletActionContext.getRequest().getSession().getAttribute(Constants.CHANNEL_PARAM_NAME);
//		if (ch == null) {
//			ch = "";
//		}
//		ActionContext.getContext().getValueStack().set("ch", ch);
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		
		merchandiseList = cartService
				.getCartOfMerchandiseList(user.getUserid());
		
//		//String errorMsg = ServletActionContext.getRequest().getParameter("errorMsg");
//		//ServletActionContext.getRequest().setAttribute("errorMsg", errorMsg);
		return "showCart";
	}

	/**
	 * 加入购物袋
	 * 
	 * @return
	 */
	public String addcart() {
		String sku = ServletActionContext.getRequest().getParameter("sku");
		String typeFlag = ServletActionContext.getRequest().getParameter("typeFlag");
		String count = ServletActionContext.getRequest().getParameter("count");
		String categoryno = ServletActionContext.getRequest().getParameter("categoryno");
		AccountVO user = WebUtil.getSessionUser();
		String url = Constants.BASE_URL + "AddProductToShopping/";
		Map<String, String> map = new HashMap<String, String>();
		map.put("sku", sku);
		map.put("count", count);
		map.put("categoryno", categoryno);
		map.put("type", typeFlag);
		map.put("detailpicw", "210");
		map.put("detailpich", "278");
		map.put("picw", "60");
		map.put("pich", "80");
		map.put("flag", "1");
		entityJson = new JSONObject();
		if (null == user || !StringUtil.isNotEmpty(user.getUserid())) {
			ServletActionContext.getRequest().getSession().setAttribute("map", map);
			entityJson.put("code", "1");
			entityJson.put("loginFrom", "1");
			return "back";
		}
		map.put("userid", user.getUserid());
		try {
			String data = WebUtil.readContentFromGet(url, map);
			JSONObject obj = JSONObject.fromObject(data);
			String code = obj.getString("code");
			if (!"0".equals(code)) {
				if("6137612f".equals(code))
					entityJson.put("code", "6137612f");
				else
					entityJson.put("code", "2");
				entityJson.put("msg", obj.getString("msg"));
			} else {
				entityJson.put("code", "3");//跳转至购物车列表页面
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back";
	}

	public JSONObject getEntityJson() {
		return entityJson;
	}

	public void setEntityJson(JSONObject entityJson) {
		this.entityJson = entityJson;
	}

	public String getSkuid() {
		return skuid;
	}

	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}

	public ConsigneeAddressVO getConsigneeAddressVO() {
		return consigneeAddressVO;
	}

	public void setConsigneeAddressVO(ConsigneeAddressVO consigneeAddressVO) {
		this.consigneeAddressVO = consigneeAddressVO;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<MerchandiseVO> getMerchandiseList() {
		return merchandiseList;
	}

	public void setMerchandiseList(List<MerchandiseVO> merchandiseList) {
		this.merchandiseList = merchandiseList;
	}

	public String getCh() {
		ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			}else{
				ch=StringUtils.isEmpty(ch)?Constants.AOLAI_WAP_DEFAULT_CHANNELNO:ch;
			}
			
		}
		return ch;
	}

	public void setCh(String ch) {
		ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			}else{
				ch=StringUtils.isEmpty(ch)?Constants.AOLAI_WAP_DEFAULT_CHANNELNO:ch;
			}
			
		}
		this.ch = ch;
	}

	public String getPlatform() {
		Object platform = SysContent.getSession().getAttribute("platform");
		if(platform != null) {
			return (String) platform;
		}
		return "";
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}

}
