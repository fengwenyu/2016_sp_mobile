package com.shangpin.mobileShangpin.platform.action;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.mobileShangpin.base.model.Account;
import com.shangpin.mobileShangpin.common.util.BaseAction;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.FileUtil;
import com.shangpin.mobileShangpin.common.util.GetRandomNum;
import com.shangpin.mobileShangpin.common.util.ParseLogUtil;
import com.shangpin.mobileShangpin.common.util.StringUtil;
import com.shangpin.mobileShangpin.common.util.WebUtil;

import com.shangpin.mobileShangpin.common.util.SysContent;

import com.shangpin.mobileShangpin.platform.service.AccountService;
import com.shangpin.mobileShangpin.platform.service.CouponService;

import com.shangpin.mobileShangpin.platform.service.MinshengBindService;

import com.shangpin.mobileShangpin.platform.vo.AccountVO;

/**
 * 民生银行绑定Action
 * 
 * @Author wangfeng
 * @CreatDate 2013-09-13
 */
@Controller
@ParentPackage("mobileShangpin")
@Scope("prototype")
@Actions({ @Action(value = ("/minshengbindaction"), results = {//
		@Result(name = "loginUI", location = "/WEB-INF/pages/account/minshengbind_login.jsp"),//
		@Result(name = "index", location = "/WEB-INF/pages/cmbc_women.jsp"),//
		@Result(name = "login", type = "redirect", location = "spindex!index?ch=${ch}"),//
		@Result(name = "showCart", type = "redirect", location = "allcartaction!listCart?msg=${errorMsg}"), //
		@Result(name = "toInfo", type = "redirect", location = "accountaction!info?ch=${ch}"), //
		//@Result(name = "toInfo",  location = "/WEB-INF/pages/account/minshenglogin_declaration.jsp") ,//
		@Result(name = "toOrderList", type = "redirect", location = "orderaction!orderlist?ch=${ch}") //
		 }) })
public class MinshengBindAction extends BaseAction {
	private final Log log = LogFactory.getLog(MinshengBindAction.class);
	private static final long serialVersionUID = -3550458496888919742L;
	private String loginFrom;
	@Autowired
	private AccountService accountService;
	@Autowired
	MinshengBindService minshengBindService;
	@Autowired
	CouponService couponService;
	/** 登录名 */
	private String loginName;
	/** 密码 */
	private String password;
	private String msg;
	/** 性别 */
	private String gender;
	/**手机号*/
	private String phoneNumber;
	/** 实体数据转json */
	private JSONObject entityJson;
	private String islogin;
	/**
	 * 民生银行入口登录
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public String minshengLogin() {
		String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		try {
			AccountVO user=null;
			
			phoneNumber=SysContent.getRequest().getParameter("phoneNumber");
			islogin=SysContent.getRequest().getParameter("islogin");
			SysContent.getRequest().getSession().setAttribute("islogin", islogin);
			user=accountService.findAccountByPhone(phoneNumber);
			if (user != null) {
				// 自动登录成功后返回到responseUrl指定跳转页
				minshengBindService.autologin(user);	
				loginName=user.getName();
			} else {
				loginName=phoneNumber+"@shangpin.com";
				password=GetRandomNum.genRandomNum(6);
				gender="0";
				boolean flag=accountService.register(loginName, password, gender, channelNo);
				if(flag){
						user = WebUtil.getSessionUser();
						couponService.sendCoupon(user.getUserid(), "1","bind:"+phoneNumber);
//						String msgtemplate="尊敬的民生客户您好，您已成功注册尚品网，您可以在电脑端通过手机号码登录尚品网，密码"+password+"为了保护您的账户安全请及时修改密码";
//						entityJson = accountService.sendPhoneCode(user.getUserid(), phoneNumber,msgtemplate);	
						System.out.print("minshengbangding");
						return "loginUI";
				}
			} 
			String ua = SysContent.getRequest().getHeader("User-Agent");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			 synchronized (user.getUserid().intern()) {
				Account account = accountService.getAccountByParams(loginName);
				Map<String, String> uaMap = ParseLogUtil.parseUA(ua);
				System.out.println("login:" + loginName + "; " + (account != null?(account.getId() + "," + sdf.format(date)):"null"));
				// 登录需要记录到account表的信息
				if (account != null && account.getId() > 0) {
					if (uaMap != null) {
						account.setPlatform(uaMap.get("platform"));
						account.setPhoneModel(uaMap.get("phoneModel"));
					} else {
						log.info("login : " + ua);
					}
					account.setLoginTime(date);
					account.setReserve0("shangpin login modify account");
					accountService.modifyAccount(account);
				} else {
					account = new Account();
					account.setChannel(StringUtils.isEmpty(channelNo)?BigInteger.valueOf(4):BigInteger.valueOf(Integer.valueOf(channelNo)));
					account.setProduct(BigInteger.valueOf(20000));
					account.setGender(user.getGender());
					account.setLoginName(user.getEmail());
					account.setCreateTime(date);
					account.setLoginTime(date);
					account.setRegTime(sdf.parse(user.getRegTime()));
					account.setRegOrigin(user.getRegOrigin());
					account.setUserId(user.getUserid());
					if (uaMap != null) {
						account.setPlatform(uaMap.get("platform"));
						account.setPhoneModel(uaMap.get("phoneModel"));
					} else {
						log.info("login : " + ua);
					}
					account.setReserve0("shangpin login add account");
					accountService.addAccount(account);
				}
			}
			// 记录访问日志
			FileUtil.addLog(SysContent.getRequest(), "accountaction!login",//
					channelNo,//
					"username", loginName,//
					"code", user.getCode(),//
					"userId", user.getUserid(),//
					"regOrigin", user.getRegOrigin(),//
					"gender", user.getGender(),//
					"regTime", user.getRegTime(),//
					"createTime", sdf.format(date),//
					"loginTime", sdf.format(date));
			System.out.println("loginFrom"+loginFrom);
		if ("1".equals(loginFrom)) {
			Object obj = ServletActionContext.getRequest().getSession().getAttribute("map");	
			if (obj != null) {
                Map<String, String> map = (Map<String, String>) obj;
                map.put("userid", user.getUserid());
                String url = Constants.BASE_TRADE_URL + "trade/addproducttocart/";
                String data = WebUtil.readContentFromPost(url, map);
                JSONObject jsonObj = JSONObject.fromObject(data);
                String code = jsonObj.getString("code");
                ServletActionContext.getRequest().getSession().removeAttribute("map");
                if (!"0".equals(code)) {
                    ActionContext.getContext().getValueStack().set("errorMsg", URLEncoder.encode(jsonObj.getString("msg"), "UTF-8"));
                }
                return "showCart";
            }
		} else if ("2".equals(loginFrom)) {
			return "toOrderList";
		} else if ("3".equals(loginFrom)) {
			return "toInfo";
		} else if ("4".equals(loginFrom)) {
			return "showCart";
		}else if("5".equals(loginFrom))	{
			return "index";
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toInfo";
	}
	 /**  
     * 追加文件：使用FileOutputStream，在构造FileOutputStream时，把第二个参数设为true  
     *   
     * @param fileName  
     * @param content  
     */   
    public   static   void  writerText(String file, String conent) {  
        BufferedWriter out = null ;  
        try  {  
            out = new  BufferedWriter( new  OutputStreamWriter(  
                    new  FileOutputStream(file,  true )));  
            out.write(conent);  
        } catch  (Exception e) {  
            e.printStackTrace();  
        } finally  {  
            try  {  
                out.close();  
            } catch  (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
	public static void main(String[] args){
		 System.out.println("start" );  
		 writerText("d:/input.txt" ,  "追加到文件的末尾\n" );  
	        System.out.println("end" );  
	}
	public AccountService getAccountService() {
		return accountService;
	}
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	public MinshengBindService getMinshengBindService() {
		return minshengBindService;
	}
	public void setMinshengBindService(MinshengBindService minshengBindService) {
		this.minshengBindService = minshengBindService;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setCouponService(CouponService couponService) {
		this.couponService = couponService;
	}
	public String getLoginFrom() {
		return loginFrom;
	}
	public void setLoginFrom(String loginFrom) {
		this.loginFrom = loginFrom;
	}
	public JSONObject getEntityJson() {
		return entityJson;
	}
	public void setEntityJson(JSONObject entityJson) {
		this.entityJson = entityJson;
	}
	public String getIslogin() {
		return islogin;
	}
	public void setIslogin(String islogin) {
		this.islogin = islogin;
	}
	
	
}
