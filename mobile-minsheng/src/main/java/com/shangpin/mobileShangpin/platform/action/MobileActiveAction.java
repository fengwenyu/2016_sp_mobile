package com.shangpin.mobileShangpin.platform.action;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileShangpin.common.util.HtmlUtil;
import com.shangpin.mobileShangpin.common.util.StringUtil;
import com.shangpin.mobileShangpin.platform.service.MeetPlaceService;



/**
 * 手机端活动页面页面
 * 
 * @author:wangfeng
 * @date:2013-11-06
 */
@Controller
@ParentPackage("mobileShangpin")
@Scope("prototype")
@Actions({ @Action(value = ("/activeaction"), 
		results = { 
			@Result(name = "login", type = "redirect", location = "accountaction!loginui"),//
			@Result(name = "order", location = "/WEB-INF/pages/2014618/order.jsp"),
			@Result(name = "sign", location = "/WEB-INF/pages/2014618/sign.jsp"),
			@Result(name = "mobile", location = "/WEB-INF/pages/2014618/mobile.jsp"),
			@Result(name = "index", location = "/WEB-INF/pages/2014618/index.jsp"),
			@Result(name = "szj", location = "/WEB-INF/pages/2014618/szj.jsp"),
			@Result(name = "xnz", location = "/WEB-INF/pages/2014618/xnz.jsp"),
			@Result(name = "sxh", location = "/WEB-INF/pages/2014618/sxh.jsp"),
			@Result(name = "cxg", location = "/WEB-INF/pages/2014618/cxg.jsp"),
			@Result(name = "ydp", location = "/WEB-INF/pages/2014618/ydp.jsp"),
			@Result(name = "djp", location = "/WEB-INF/pages/2014618/djp.jsp"),
			@Result(name = "xsh", location = "/WEB-INF/pages/2014618/xsh.jsp"),
			@Result(name = "al", location = "/WEB-INF/pages/2014618/al.jsp"),
			@Result(name = "meetpagejson", type = "json", params = { "root", "entityJson" })
		}) })
public class MobileActiveAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private MeetPlaceService meetPlaceService;
	private String id;//会场id
	private String html;//返回html数据
	private String status;
	private String type;
	/** 实体数据转json */
	private JSONObject entityJson;
	private String cmsTitle;
	public String getCmsTitle() {
		return cmsTitle;
	}

	public void setCmsTitle(String cmsTitle) {
		this.cmsTitle = cmsTitle;
	}

	
	/**
	 * 会场首页
	 * 
	 * @return
	 */
	
	public String meetindex() {		
		//判断是正式会场或者预热会场,0代表正式会场
		String isPre=ServletActionContext.getRequest().getParameter("z");
		if(StringUtils.isNotEmpty(isPre)&&isPre.equals("0")){
			String obj;
			try {
				obj = meetPlaceService.getMeetPlaceList(id);
				html=HtmlUtil.changehref(obj.substring(obj.indexOf(":")+1));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "openmeetindex";
		}
		return "meetindex";
		
	}
	
	
	/**
	 * 主场功能跳转
	 * 
	 * @return
	 */
	
	public String meetPage() {		
		if("order".equals(type)){
			return "order";			
		}else if("sign".equals(type)){
			return "sign";
		}else if("mobile".equals(type)){
			return "mobile";
		}else if("index".equals(type)){
			return "activeindex";
		}
		return "meetindex";
	}
	

	
	
	/**
	 * 520会场首页
	 * 
	 * @return
	 */
	public String meetpage() {		
		try {
			String obj = meetPlaceService.getMeetPlaceList(id);
			html = obj.substring(obj.indexOf(":") + 1);
			String[] htmlArray = html.split("###");
			// 会场位置索引
			String p = ServletActionContext.getRequest().getParameter("p");
			if (StringUtil.isNotEmpty(p)) {
				int pindex = Integer.valueOf(ServletActionContext.getRequest().getParameter("p"));
				html = htmlArray[pindex];
			} else {
				html = htmlArray[0];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMeetPlaceService(MeetPlaceService meetPlaceService) {
		this.meetPlaceService = meetPlaceService;
	}

	public JSONObject getEntityJson() {
		return entityJson;
	}

	public void setEntityJson(JSONObject entityJson) {
		this.entityJson = entityJson;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
