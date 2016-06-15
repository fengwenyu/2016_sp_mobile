package com.shangpin.mobileAolai.platform.action;






import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.HtmlUtil;
import com.shangpin.mobileAolai.common.util.SysContent;
import com.shangpin.mobileAolai.platform.service.MeetPlaceService;



/**
 * 手机端活动页面页面
 * 
 * @author:wangfeng
 * @date:2013-11-06
 */
@Controller
@ParentPackage("mobileAolai")
@Scope("prototype")
@Actions({ @Action(value = ("/meet"), 
		results = { 
//			@Result(name = "giftCard", location = "/WEB-INF/pages/2014ifc/rule_giftCard.jsp"),
//			@Result(name = "ruleAolai", location = "/WEB-INF/pages/2014ifc/rule_mobile_aolai.jsp"),
//			@Result(name = "mobile", location = "/WEB-INF/pages/2014ifc/rule_mobile.jsp"),
//			@Result(name = "order", location = "/WEB-INF/pages/2014ifc/rule_order.jsp"),
//			@Result(name = "sign", location = "/WEB-INF/pages/2014ifc/rule_sign.jsp"),
//			@Result(name = "weibo", location = "/WEB-INF/pages/2014ifc/rule_weibo.jsp"),
			
			@Result(name = "index", location = "/WEB-INF/pages/20150520/index.jsp"),
			@Result(name = "formal", location = "/WEB-INF/pages/20150520/aolaiFormal.jsp"),
			@Result(name = "newIndex", location = "/WEB-INF/pages/20141113/index.jsp"),
			@Result(name = "acrossYear", location = "/WEB-INF/pages/2014AcrossYear/index.jsp"),
			@Result(name = "share", location = "/WEB-INF/pages/20150520/share.jsp"),
			@Result(name = "gift", location = "/WEB-INF/pages/20150520/gift.jsp"),
//			@Result(name = "aolai", location = "/WEB-INF/pages/2014ifc/aolai.jsp"),
//			@Result(name = "bag", location = "/WEB-INF/pages/2014ifc/bag.jsp"),
//			@Result(name = "beauty", location = "/WEB-INF/pages/2014ifc/beauty.jsp"),
//			@Result(name = "ccessories", location = "/WEB-INF/pages/2014ifc/ccessories.jsp"),
//			@Result(name = "home", location = "/WEB-INF/pages/2014ifc/home.jsp"),
//			@Result(name = "man", location = "/WEB-INF/pages/2014ifc/man.jsp"),
//			@Result(name = "woman", location = "/WEB-INF/pages/2014ifc/woman.jsp"),
//			@Result(name = "shoe", location = "/WEB-INF/pages/2014ifc/shoe.jsp"),
//			@Result(name = "watch", location = "/WEB-INF/pages/2014ifc/watch.jsp"),
		}) })
public class MeetAction extends ActionSupport {
	

	private static final long serialVersionUID = 6546268453706457777L;
	private String type;
	private String html;//返回html数据
	private String title;
	private String status;
	private String isChange;//是否切换正式会场 0预热，1强制切换正式会场
	private String id;
	@Autowired
	MeetPlaceService meetPlaceService;
//	private HttpServletResponse response = SysContent.getResponse();
	/**
	 * 首页
	 * 
	 * @return
	 */
	public String index() {
		if (StringUtils.isEmpty(type) && StringUtils.isNotEmpty(id)) {
			type = "index";
		}
		/*else if ("acrossYear".equals(type)) {
			id = "158";
		}
		else if(type.equals("index")){
//			id="420";
			type="index";
			id="115";
		}
		else if ("newIndex".equals(type)) {
			id = "121";
		}*/
//		if(type.equals("bag")){
////			id="424";
//			id="107";
//		}
//		if(type.equals("beauty")){
////			id="426";
//			id="105";
//		}
//		if(type.equals("ccessories")){
////			id="425";
//			id="106";
//		}
//		if(type.equals("home")){
////			id="427";
//			id="104";
//		}
//		if(type.equals("man")){
////			id="422";
//			id="109";
//		}
//		if(type.equals("woman")){
////			id="421";
//			id="110";
//			
//		}
//		if(type.equals("shoe")){
////			id="423";
//			id="108";
//		}
//		
	
		
		try {
			// 当前时间开始时间 格式2015,05,12,10,01,00
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
			String nowSys = sdf.format(now);
			SysContent.getRequest().getSession().setAttribute("nowSys", nowSys);
			String obj;
	        JSONObject resultObject;
	        JSONObject contentObject;	
	       	if(isChange==null){
	       		isChange="0";
	       	}
            obj = meetPlaceService.getMeetPlaceList(id,isChange,type);
            resultObject = JSONObject.fromObject(obj);
            if (Constants.SUCCESS.equals(resultObject.getString("code"))) {
                contentObject = JSONObject.fromObject(resultObject.getString("content"));
                if (contentObject != null) {
                    html = contentObject.getString("html");
                    title = contentObject.getString("title");
                    html=HtmlUtil.addprefixShangPinhref(html);
                    html = changeHtml(html);
                    status=contentObject.getString("status");
                    if("1".equals(status)){
                    	type="formal";
                    }
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return type;
		
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getIsChange() {
		return isChange;
	}


	public void setIsChange(String isChange) {
		this.isChange = isChange;
	}


	/**
	 * 拼接页面html的href方法
	 * @return
	 */
	public String changeHtml(String htmlString){
		String resultString = null;
		//判断app版本信息
		String ua = SysContent.getRequest().getHeader("User-Agent").toLowerCase();
		String[] appArray = {"AolaiIOSApp","AolaiAndroidApp"};
		boolean flag =false;
		 for(int i=0;i<appArray.length;i++){
			 if(ua.indexOf(appArray[i].toLowerCase())>-1){
//				 String str = ua.substring(ua.indexOf(appArray[i].toLowerCase()), ua.length());
//				 if(str.length()>=appArray[i].length()+1&&str.indexOf(";")>-1){
//					 String ver = str.substring(appArray[i].length()+1, str.indexOf(";"));
//						if (StringUtil.compareVer(ver, "4.0.0") == 1||StringUtil.compareVer(ver, "4.0.0") ==0){
							flag = true;
//						}
							
//					}
				 }
			
		 }
		 if(flag){
			 resultString=HtmlUtil.addprefixhref(htmlString);
		 }else{
			 resultString=htmlString;
		 }
		return resultString;
	}
	/**
	 * 乐分享，礼券包
	 * @return
	 */
	public String active(){
		if (StringUtils.isEmpty(type)&&"share".equals(type)) {
			type = "share";
		}else if(StringUtils.isEmpty(type)&&"gift".equals(type)){
			type="gift";
		}		
		return type;
	}
}
