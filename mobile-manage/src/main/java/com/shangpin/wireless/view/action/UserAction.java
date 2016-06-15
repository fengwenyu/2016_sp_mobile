package com.shangpin.wireless.view.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.wireless.api.service.AccountService;
import com.shangpin.wireless.api.service.PushconfigAolaiService;
import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.AccountStatistics;
import com.shangpin.wireless.domain.Constants;
import com.shangpin.wireless.domain.PageBean;
import com.shangpin.wireless.domain.Privilege;
import com.shangpin.wireless.domain.PushconfigAolai;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.domain.Role;
import com.shangpin.wireless.domain.User;
import com.shangpin.wireless.manage.service.AccountStatisticsService;
import com.shangpin.wireless.util.DataContainerPool;
import com.shangpin.wireless.util.HqlHelper;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.ProReader;
import com.shangpin.wireless.util.StringUtils;
import com.shangpin.wireless.util.WebUtil;

/**
 * 用户管理Action
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-12
 */
@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(UserAction.class);
	private Long[] privilegeIds;
	private Long userId;
	private String oldPassword;
	/** 实体数据转json */
	private JSONObject entityJson;

	/** 添加页面 */
	public String addUI() {
		return "addUI";
	}

	/**
	 * 添加
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-12
	 * @param
	 * @Return
	 */
	public String add() {
		try {
			HqlHelper hqlHelper = new HqlHelper(User.class, "u");
			hqlHelper.addWhereCondition("u.loginName=?", model.getLoginName());
			User user = userService.getByCondition(hqlHelper);
			if (user == null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				model.setCreateTime(sdf.parse(sdf.format(new Date())));
				String passwordMd5 = DigestUtils.md5Hex("1234");// 要使用MD5摘要
				model.setPwd(passwordMd5);
				// 初始化权限
				Role role = new Role();
				String str = ProReader.getInstance().getProperty("default_privileges");
				String[] strArr = str.split(",");
				Long[] privileges = new Long[strArr.length];
				for (int i = 0; i < strArr.length; i++) {
					privileges[i] = Long.parseLong(strArr[i]);
				}
				List<Privilege> privilegeList = privilegeService.getByIds(privileges);
				role.setPrivileges(new HashSet<Privilege>(privilegeList));
				roleService.save(role);// 更新到数据库中
				model.setRole(role);
				userService.save(model);// 保存到数据库
				// 记录操作日志
				operateLogService.saveLog(userLogin, userLogin.getLoginName() + "添加新用户" + model.getLoginName());
			} else {
				ActionContext.getContext().getValueStack().set("checkLoginName", "用户名已经存在");
				return "addUI";
			}
		} catch (Exception e) {
			log.error("UserAction-add() " + e);
		}
		return "toList";
	}

	/**
	 * 用户列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-12
	 * @param
	 * @Return
	 */
	public String list() {
		try {
			// 1，构建查询条件
			HqlHelper hqlHelper = new HqlHelper(User.class, "u");
			hqlHelper.addWhereCondition("u.id!=?", userLogin.getId());
			hqlHelper.addWhereCondition("u.rank!=?", 100);
			hqlHelper.addOrderByProperty(true, "createTime", false);
			// 2，查询并准备分页信息
			PageBean pageBean = userService.getPageBean(pageNum, hqlHelper);
			ActionContext.getContext().getValueStack().push(pageBean);
			// 记录操作日志
			// operateLogService.saveLog(userLogin, userLogin.getLoginName() + "查看用户列表");
		} catch (Exception e) {
			log.error("UserAction-list() " + e);
		}
		return "list";
	}

	/**
	 * 跳转至设置权限页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-12
	 * @param
	 * @Return
	 */
	public String setPrivilegeUI() {
		try {
			User user = userService.getById(userId);
			// 准备role
			Role role = user.getRole();
			ActionContext.getContext().put("user", user);
			// 准备权限列表
			List<Privilege> topPrivilegeList = privilegeService.findTopList();
			ActionContext.getContext().put("topPrivilegeList", topPrivilegeList);
			// 准备回显的数据
			if (role != null) {
				privilegeIds = new Long[role.getPrivileges().size()];
				int index = 0;
				for (Privilege privilege : role.getPrivileges()) {
					privilegeIds[index++] = privilege.getId();
				}
			}
		} catch (Exception e) {
			log.error("UserAction-setPrivilegeUI " + e);
		}
		return "setPrivilegeUI";
	}

	/**
	 * 设置权限
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-12
	 * @param
	 * @Return
	 */
	public String setPrivilege() {
		try {
			User user = userService.getById(userId);
			// 从数据库中获取到原状态的对象
			Role role = user.getRole();
			// 设置要修改的属性
			List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
			if (role == null) {
				role = new Role();
				role.setPrivileges(new HashSet<Privilege>(privilegeList));
				roleService.save(role);// 更新到数据库中
				user.setRole(role);
				userService.save(user);
			} else {
				role.setPrivileges(new HashSet<Privilege>(privilegeList));
				roleService.update(role);
			}
			// 记录操作日志
			operateLogService.saveLog(userLogin, userLogin.getLoginName() + "为" + user.getLoginName() + "设置权限");
		} catch (Exception e) {
			log.error("UserAction-setPrivilege " + e);
		}

		return "toList";
	}

	/**
	 * 跳转至修改密码页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-12
	 * @param
	 * @Return
	 */
	public String editPasswordUI() {
		return "editPasswordUI";
	}

	/**
	 * 修改密码
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-13
	 * @param
	 * @Return
	 */
	public String editPassword() {
		try {
			if (oldPassword != null && !"".equals(oldPassword)) {
				oldPassword = DigestUtils.md5Hex(oldPassword);
				User user = (User) ActionContext.getContext().getSession().get("user");
				if (user != null && oldPassword.equals(user.getPwd())) {
					user.setPwd(DigestUtils.md5Hex(model.getPwd()));
					userService.update(user);
					ActionContext.getContext().getSession().remove("user");
				} else {
					ActionContext.getContext().getValueStack().set("editPassword", "原始密码输入不正确！");
					return "editPasswordUI";
				}
				// 记录操作日志
				operateLogService.saveLog(userLogin, userLogin.getLoginName() + "修改密码");
			}
		} catch (Exception e) {
			log.error("UserAction-editPassword " + e);
		}
		return "loginUI";
	}

	/**
	 * 跳转至登录页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-12
	 * @param
	 * @Return
	 */
	public String loginUI() {
		return "loginUI";
	}

	/**
	 * 登录
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-12
	 * @param
	 * @Return
	 */
	public String login() {
		try {
			if (StringUtils.isEmpty(model.getLoginName()) || StringUtils.isEmpty(model.getPwd())) {
				ActionContext.getContext().getValueStack().set("login", "用户名和密码不能为空！");
				return "loginUI";
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			String saved = request.getParameter("checkNum");
			if (StringUtils.isEmpty(saved)) {
				ActionContext.getContext().getValueStack().set("login", "验证码不能为空");
				return "loginUI";
			}
			boolean flag = isCheckNum(request);
			if (!flag) {
				ActionContext.getContext().getValueStack().set("login", "验证码输入有误,请重新输入！");
				return "loginUI";
			}
			// 根据用户名与密码查询一个用户，如果能查到，就是正确，就登录，如果不正确，就失败。
			User user = userService.findByLoginNameAndPassword(model.getLoginName(), model.getPwd());
			// User user = userService.findByLoginNameAndPassword("admin", "admin");
			if (user == null) {
				ActionContext.getContext().getValueStack().set("login", "用户名或密码不正确！");
				return "loginUI";
			} else {
				// 登录用户
				// if (!"admin".equals(user.getLoginName())) {
				// Hibernate.initialize(user.getRole().getPrivileges());
				// }
				ActionContext.getContext().getSession().put("user", user);
				if ("81dc9bdb52d04dc20036dbd8313ed055".equals(user.getPwd())) {
					return "editPasswordUI";
				}
				// 记录操作日志
				operateLogService.saveLog(user, user.getLoginName() + "登录系统");
				return "toIndex";
			}
		} catch (Exception e) {
			log.error("UserAction-login " + e);
		}
		return "loginUI";
	}

	/**
	 * ajax方式登录校验，并发送手机验证码
	 */
	public String ajaxCheckUserInfo() {
		entityJson = new JSONObject();
		entityJson.put("code", "-1");
		try {
			if (StringUtils.isEmpty(model.getLoginName()) || StringUtils.isEmpty(model.getPwd())) {
				ActionContext.getContext().getValueStack().set("login", "用户名和密码不能为空！");

				entityJson.put("msg", "用户名和密码不能为空！");
				print(entityJson.toString());
				return null;
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			String saved = request.getParameter("checkNum");
			if (StringUtils.isEmpty(saved)) {
				ActionContext.getContext().getValueStack().set("login", "验证码不能为空");
				entityJson.put("msg", "验证码不能为空！");
				print(entityJson.toString());
				return null;
			}
			boolean flag = isCheckNum(request);
			if (!flag) {
				ActionContext.getContext().getValueStack().set("login", "验证码输入有误,请重新输入！");
				entityJson.put("msg", "验证码输入有误,请重新输入！");
				print(entityJson.toString());
				return null;
			}
			// 根据用户名与密码查询一个用户，如果能查到，就是正确，就登录，如果不正确，就失败。
			User user = userService.findByLoginNameAndPassword(model.getLoginName(), model.getPwd());
			// User user = userService.findByLoginNameAndPassword("admin", "admin");
			if (user == null) {
				ActionContext.getContext().getValueStack().set("login", "用户名或密码不正确！");
				entityJson.put("msg", "用户名或密码不正确！");
				print(entityJson.toString());
				return null;
			} else {
				final String phonenum = user.getPhoneNum();
				if (StringUtils.isEmpty(phonenum)) {
					entityJson.put("msg", "未设置手机号码，无法登录系统！");
					print(entityJson.toString());
					return null;
				}

//				String url = Constants.BASE_URL + "sendverifycode/";
//				String _verifycode = null;
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("userid", user.getId().toString());
//				map.put("phonenum", phonenum);
//				map.put("msgtemplate", "无线管理平台提醒您验证码为：{$verifyCode$}，请及时输入验证！");
//				try {
//					String json = WebUtil.readContentFromGet(url, map);
//					log.info("sendverifycode:" + json);
//					if (org.apache.commons.lang.StringUtils.isNotEmpty(json)) {
//						JSONObject jsonObj = JSONObject.fromObject(json);
//						if (null != jsonObj && "0".equals(jsonObj.get("code"))) {
//							JSONObject contentObj = (JSONObject) jsonObj.get("content");
//							_verifycode = contentObj.get("verifycode").toString();
//						} else if (null != jsonObj && !"0".equals(jsonObj.get("code"))) {
//							entityJson.put("code", jsonObj.getString("code"));
//							entityJson.put("msg", jsonObj.getString("msg"));
//							print(entityJson.toString());
//							return null;
//						}
//					}
//					if (org.apache.commons.lang.StringUtils.isNotEmpty(_verifycode)) {
//						DataContainerPool.verifycodeContainer.putOrReplace(model.getLoginName(), _verifycode);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					entityJson.put("msg", "请刷新重新登录！");
//					print(entityJson.toString());
//					return null;
//				}

				DataContainerPool.userInfoContainer.putOrReplace(user.getLoginName() + "", user);
				entityJson.put("code", "0");
				entityJson.put("loginname", model.getLoginName());
				entityJson.put("mobilenum", model.getPhoneNum());
				entityJson.put("msg", "登录验证成功！");
				print(entityJson.toString());
				return null;
			}
		} catch (Exception e) {
			log.error("UserAction-ajaxCheckUserInfo " + e);
		}
		entityJson.put("msg", "请刷新重新登录！");
		print(entityJson.toString());
		return null;
	}

	/**
	 *校验手机验证码
	 */
	public String ajaxCheckMobileNum() {
		entityJson = new JSONObject();
		entityJson.put("code", "-1");
		String verifycode = ServletActionContext.getRequest().getParameter("verifycode");
		// String phonenum = ServletActionContext.getRequest().getParameter("phonenum");
		String loginname = ServletActionContext.getRequest().getParameter("loginname");

		Object obj = DataContainerPool.verifycodeContainer.getAndRemove(loginname);
		if (null != obj) {
			if (obj.toString().equals(verifycode)) {
				entityJson.put("code", "0");
				entityJson.put("loginname", loginname);
				print(entityJson.toString());
				return null;
			}
		}
		entityJson.put("msg", "手机验证码有误，请重试！");
		print(entityJson.toString());
		return null;

		// String url = Constants.BASE_URL + "verifyphoneandcode/";
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("userid", "6ED12A52740E269ACF51840ABA5B91EE");
		// map.put("phonenum", phonenum);
		// map.put("verifycode", verifycode);
		//
		// try {
		// String json = WebUtil.readContentFromGet(url, map);
		// if (null != json && !"".equals(json)) {
		// JSONObject jsonObj = JSONObject.fromObject(json);
		// if (null != jsonObj && "0".equals(jsonObj.get("code"))) {
		// entityJson.put("code", "0");
		// entityJson.put("loginname", loginname);
		// print(entityJson.toString());
		// return null;
		// }
		// }
		//
		// } catch (Exception e) {
		// log.error("UserAction-ajaxCheckMobileNum " + e);
		// }
		// entityJson.put("msg", "手机验证码有误，请重试！");
		// print(entityJson.toString());
		// return null;
	}

	/**
	 * 登录系统
	 * 
	 * @return
	 */
	public String loginSys() {
		try {
			String loginname = ServletActionContext.getRequest().getParameter("loginname");
			User user = (User) DataContainerPool.userInfoContainer.getAndRemove(loginname);
			if (null != user) {
				ActionContext.getContext().getSession().put("user", user);
				if ("81dc9bdb52d04dc20036dbd8313ed055".equals(user.getPwd())) {
					return "editPasswordUI";
				}
				// 记录操作日志
				operateLogService.saveLog(user, user.getLoginName() + "登录系统");
				return "toIndex";
			}
		} catch (Exception e) {
			log.error("UserAction-loginSys " + e);
		}
		return "loginUI";
	}

	/**
	 * 注销
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-12
	 * @param
	 * @Return
	 */
	public String logout() {
		// 记录操作日志
		operateLogService.saveLog(userLogin, userLogin.getLoginName() + "退出系统");
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}

	/**
	 * 删除用户（同时删除用户所拥有的角色）
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-13
	 * @param
	 * @Return
	 */
	public String delete() {
		try {
			User user = userService.getById(model.getId());
			userService.delete(model.getId());
			// 记录操作日志
			operateLogService.saveLog(userLogin, userLogin.getLoginName() + "删除用户" + user.getLoginName());
		} catch (Exception e) {
			log.error("UserAction-delete " + e);
		}
		return "toList";
	}

	/**
	 * 跳转至修改页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-13
	 * @param
	 * @Return
	 */
	public String editUI() {
		try {
			User user = userService.getById(model.getId());
			ActionContext.getContext().getValueStack().push(user);
		} catch (Exception e) {
			log.error("UserAction-editUI " + e);
		}
		return "addUI";
	}

	/**
	 * 修改用户资料
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-13
	 * @param
	 * @Return
	 */
	@Resource(name = AccountService.SERVICE_NAME)
	protected AccountService accountService;
	@Resource(name = AccountStatisticsService.SERVICE_NAME)
	protected AccountStatisticsService accountStatisticsService;
	@Resource(name = PushconfigAolaiService.SERVICE_NAME)
	protected PushconfigAolaiService pushconfigAolaiService;

	public String edit() throws Exception {
		HqlHelper hqlHelper = new HqlHelper(AccountStatistics.class, "a");
		hqlHelper.addOrderByProperty(true, "a.id", true);
		List<AccountStatistics> accountList = accountStatisticsService.executeHql(hqlHelper.getQueryListHql());
		for (AccountStatistics account : accountList) {
			List<PushconfigAolai> pushconfigAolaiList = pushconfigAolaiService.executeHql("FROM PushconfigAolai WHERE userId='" + account.getUserId() + "'");
			if (pushconfigAolaiList.size() > 0) {
				PushconfigAolai pushconfigAolai = pushconfigAolaiList.get(0);
				account.setImei(pushconfigAolai.getImei());
			}
			System.out.print("id:" + account.getId());
			System.out.print(";userid:" + account.getUserId());
			System.out.println(";imei:" + account.getImei());
			accountStatisticsService.update(account);
		}
		// --------------------------------------------------
		try {
			// 1，从数据库中获取原对象
			User user = userService.getById(model.getId());
			// 2，设置要修改的属性
			user.setLoginName(model.getLoginName());
			user.setNickname(model.getNickname());
			user.setPhoneNum(model.getPhoneNum());
			user.setRank(model.getRank());
			// 3，更新到数据库中
			userService.update(user);
			// 记录操作日志
			operateLogService.saveLog(userLogin, userLogin.getLoginName() + "修改用户资料" + user.getLoginName());
		} catch (Exception e) {
			log.error("UserAction-edit " + e);
		}
		return "toList";
	}

	/**
	 * 初始化密码
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-13
	 * @param
	 * @Return
	 */
	public String initPassword() {
		try {
			// 1，从数据库中获取原对象
			User user = userService.getById(model.getId());
			// 2，设置要修改的属性
			user.setPwd(DigestUtils.md5Hex("1234"));
			// 3，更新到数据库中
			userService.update(user);
			// 记录操作日志
			operateLogService.saveLog(userLogin, userLogin.getLoginName() + "为" + user.getLoginName() + "初始化密码");
		} catch (Exception e) {
			log.error("UserAction-initPassword " + e);
		}
		return "toList";
	}

	/**
	 * 检验注册用户名是否可用
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-17
	 * @param
	 * @Return
	 */
	public String checkLoginName() {
		try {
			HqlHelper hqlHelper = new HqlHelper(User.class, "u");
			hqlHelper.addWhereCondition("u.loginName=?", model.getLoginName());
			User user = userService.getByCondition(hqlHelper);
			ReturnObject returnObject = new ReturnObject();
			if (user == null)
				returnObject.setReturnInfo("用户名可用");
			else
				returnObject.setReturnInfo("用户名已经存在");
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("UserAction-list() " + e);
		}
		return null;
	}

	// ---------------------------------------------
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * 处理验证
	 * 
	 * @param request
	 * @return
	 */
	public boolean isCheckNum(HttpServletRequest request) {
		// 获取已经存在的session
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}
		String check_number_key = (String) session.getAttribute("CHECK_NUMBER_KEY");
		if (StringUtils.isEmpty(check_number_key)) {
			return false;
		}
		// 获取jsp页面文本框中输入的?
		// <input name="checkNum" type="text" value="" id="checkNum" style="width: 80">
		String saved = request.getParameter("checkNum");
		if (StringUtils.isEmpty(saved)) {
			return false;
		}
		// 比对session中存放的值和页面文本框输入的验证码的?
		return check_number_key.equalsIgnoreCase(saved);
	}

	public JSONObject getEntityJson() {
		return entityJson;
	}

	public void setEntityJson(JSONObject entityJson) {
		this.entityJson = entityJson;
	}

	private void print(String jsonStr) {
		PrintWriter out = null;
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().setContentType("text/json");
			ServletActionContext.getResponse().setDateHeader("Expires", 0);
			out = ServletActionContext.getResponse().getWriter();
			out.println(jsonStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
	}
}
