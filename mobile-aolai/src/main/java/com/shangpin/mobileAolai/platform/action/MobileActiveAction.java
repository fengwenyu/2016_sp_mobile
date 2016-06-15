package com.shangpin.mobileAolai.platform.action;

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
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.HtmlUtil;
import com.shangpin.mobileAolai.common.util.StringUtil;
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
@Actions({ @Action(value = ("/activeaction"), 
		results = { 
			@Result(name = "scroll", location = "/WEB-INF/pages/active/scroll.jsp"),
			@Result(name = "login", type = "redirect", location = "accountaction!loginui"),//
			@Result(name = "activehomeone", location = "/WEB-INF/pages/active/activehomeone.jsp"),
			@Result(name = "activehometwo", location = "/WEB-INF/pages/active/activehometwo.jsp"),
			@Result(name = "activeindex", location = "/WEB-INF/pages/active/activeindex.jsp"),
			@Result(name = "activeindextwo", location = "/WEB-INF/pages/active/activeindextwo.jsp"),
			@Result(name = "meetindex", location = "/WEB-INF/pages/2014818/index.jsp"),
			@Result(name = "man", location = "/WEB-INF/pages/2014801/man.jsp"),
			@Result(name = "women", location = "/WEB-INF/pages/2014801/women.jsp"),
			@Result(name = "shoe", location = "/WEB-INF/pages/2014801/shoe.jsp"),
			@Result(name = "bag", location = "/WEB-INF/pages/2014801/bag.jsp"),
			@Result(name = "acc", location = "/WEB-INF/pages/2014801/acc.jsp"),
			@Result(name = "watch", location = "/WEB-INF/pages/2014801/watch.jsp"),
			@Result(name = "yj", location = "/WEB-INF/pages/2014801/yj.jsp"),
			@Result(name = "mz", location = "/WEB-INF/pages/2014801/mz.jsp"),
			@Result(name = "home", location = "/WEB-INF/pages/2014801/home.jsp"),
			@Result(name = "meetpagejson", type = "json", params = { "root", "entityJson" })
		}) })
public class MobileActiveAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private MeetPlaceService meetPlaceService;

	private String id;//会场id
	private String html;//返回html数据
	private String status;
	private String num;
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
	
	public String activeList(){
		
		return "activeindextwo";
	}
	/**
	 * 会场首页
	 * 
	 * @return
	 */
	
	public String meetindex() {		
		String obj;
		JSONObject resultObject;
		JSONObject contentObject;
		try {
			obj = meetPlaceService.getMeetPlaceList(id,"0",type);
			resultObject=JSONObject.fromObject(obj);
			if (Constants.SUCCESS.equals(resultObject.getString("code"))) {
		        contentObject=JSONObject.fromObject(resultObject.getString("content"));
		        if(contentObject!=null){
	                html=contentObject.getString("html");
	                html=changeHtml(html);
		        }			   
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(StringUtils.isNotEmpty(type)){
			return type;
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
		}
		return "meetindex";
	}
	/**
	 * 520会场首页
	 * 
	 * @return
	 */
	public String meetpage() {		
			String obj;
			JSONObject resultObject;
	        JSONObject contentObject;
	        try {
	           obj = meetPlaceService.getMeetPlaceList(id,"0",type);
	            resultObject=JSONObject.fromObject(obj);
	            if (Constants.SUCCESS.equals(resultObject.getString("code"))) {
	                contentObject=JSONObject.fromObject(resultObject.getString("content"));
	                if(contentObject!=null){
	                    html=entityJson.getString("html");
	                    html=changeHtml(html);
	                    String[] htmlArray = html.split("###");
	                    // 会场位置索引
	                    String p = ServletActionContext.getRequest().getParameter("p");
	                    if (StringUtil.isNotEmpty(p)) {
	                        int pindex = Integer.valueOf(ServletActionContext.getRequest().getParameter("p"));
	                        html = changeHtml(htmlArray[pindex]);
	                        html = htmlArray[pindex];
	                    } else {
	                        html = changeHtml(htmlArray[0]);
	                        html = htmlArray[0];
	                    }
	                }              
	            }			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return type;
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
	 *  秒杀抢购
	 * 
	 * @return
	 */
	
	/*public List<SkillGroupVO> skillProducts(String id) {	
		List<SkillGroupVO> products=new ArrayList<SkillGroupVO>();
		try {
			products=meetPlaceService.getSkillProductList(id);
			if(products!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			boolean flag=false;
			for(int i=0;i<products.size();i++){
				Date startime=sdf.parse(products.get(i).getStarttime());
				Date now=new Date();
				flag=startime.before(now);		
				for(int j=0;j<products.get(i).getSkillProductList().size();j++){
					if(flag){
						products.get(i).getSkillProductList().get(j).setProductno("http://m.shangpin.com/merchandiseaction!spdetail?productid="+products.get(i).getSkillProductList().get(j).getProductno());						
					}else{
						products.get(i).getSkillProductList().get(j).setProductno("javascript:();");
					}
				}
			}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return products;
		
	}
*/
	/**
	 *  活动故事
	 * 
	 * @return
	 */
	/*public String scroll(){		
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		storyVO=meetPlaceService.getStory(user.getUserid());
		return "scroll";
		
	}*/
	/**
	 *  活动（不属于会场类型的）
	 * 
	 * @return
	 */
	public String activehome(){		
		if(num!=null&&!"".equals(num)){
			return "activehometwo";				
		}
		return "activehomeone";
		
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
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
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

