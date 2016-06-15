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
import com.shangpin.wireless.domain.Channel;
import com.shangpin.wireless.domain.PageBean;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.util.HqlHelper;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;

/**
 * 渠道管理Action
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-16
 */
@Controller
@Scope("prototype")
public class ChannelAction extends BaseAction<Channel> {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ChannelAction.class);
	private int page = 1;
	private int rows;
	private String channelnum;

	/** 添加页面 */
	public String addUI() {
		return "addUI";
	}

	/**
	 * 添加渠道
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-16
	 * @param
	 * @Return
	 */
	public String add() {
		try {
			if (channelnum == null || "".equals(channelnum) || model.getChannelName() == null || "".equals(model.getChannelName()) || !channelnum.matches("^[0-9]*$")) {
				if (channelnum == null || "".equals(channelnum)) {
					ActionContext.getContext().getValueStack().set("checkChannelNum", "渠道号不能为空");
				} else if (!channelnum.matches("^[0-9]*$")) {
					ActionContext.getContext().getValueStack().set("checkChannelNum", "渠道号必须是数字");
				}
				if (model.getChannelName() == null || "".equals(model.getChannelName())) {
					ActionContext.getContext().getValueStack().set("checkChannelName", "渠道名不能为空");
				}
				return "addUI";
			} else {
				HqlHelper hqlHelper = new HqlHelper(Channel.class, "c");
				hqlHelper.addWhereCondition("c.channelName=?", model.getChannelName());
				Channel channel = channelService.getByCondition(hqlHelper);
				HqlHelper hqlHelper1 = new HqlHelper(Channel.class, "c");
				hqlHelper1.addWhereCondition("c.channelNum=?", Long.valueOf(channelnum));
				Channel channel1 = channelService.getByCondition(hqlHelper1);
				if (channel == null && channel1 == null) {
					model.setCreateTime(new Date());
					model.setChannelNum(Long.valueOf(channelnum));
					channelService.save(model);
					// 记录操作日志
					operateLogService.saveLog(userLogin, userLogin.getLoginName() + "添加渠道" + model.getChannelName());
					model.setId(null);
					model.setChannelName(null);
					setChannelnum(null);
					ActionContext.getContext().getValueStack().set("checkChannelName", "插入成功");
				} else {
					if (channel != null)
						ActionContext.getContext().getValueStack().set("checkChannelName", "渠道名已经存在");
					if (channel1 != null)
						ActionContext.getContext().getValueStack().set("checkChannelNum", "渠道号已经存在");
					return "addUI";
				}
			}
		} catch (Exception e) {
			log.error("ChannelAction-add() " + e);
		}
		return "toList";
	}

	/**
	 * 渠道列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-16
	 * @param
	 * @Return
	 */
	public String list() {
		try {
			// 1，构建查询条件
			HqlHelper hqlHelper = new HqlHelper(Channel.class, "c");
			if (!"".equals(model.getChannelName()) && model.getChannelName() != null)
				hqlHelper.addWhereCondition("c.channelName like'%" + model.getChannelName() + "%'");
			if (model.getChannelNum() != null)
				hqlHelper.addWhereCondition("c.channelNum=?", model.getChannelNum());
			hqlHelper.addOrderByProperty(true, "c.createTime", false);
			// 2，查询并准备分页信息
			PageBean pageBean = channelService.getPageBean(pageNum, hqlHelper);
			ActionContext.getContext().getValueStack().push(pageBean);
		} catch (Exception e) {
			log.error("ChannelAction-list() " + e);
		}
		return "list";
	}

	/**
	 * 数据动态绑定-渠道列表
	 * 
	 * @Author zhouyu
	 * @createDate 2012-07-25
	 * @param
	 * @Return
	 */
	public String datagridList() {
		try {
			// 1，构建查询条件
			SqlHelper sqlQueryListHelper = new SqlHelper("channel", "c");
			SqlHelper sqlQueryCountHelper = new SqlHelper("channel");
			sqlQueryListHelper.addColumn("c.channelNum");
			sqlQueryListHelper.addColumn("c.channelName");
			sqlQueryListHelper.addColumn("c.createTime");
			sqlQueryCountHelper.addColumn("count(*)");
			if (!"".equals(model.getChannelName()) && model.getChannelName() != null) {
				sqlQueryListHelper.addWhereCondition("c.channelName like'%" + model.getChannelName() + "%'");
				sqlQueryCountHelper.addWhereCondition("channelName like'%" + model.getChannelName() + "%'");
			}
			sqlQueryListHelper.addOrderByProperty(true, "c.createTime", false);

			// 2，查询并准备分页信息
			List recorList = channelService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
			Integer count = channelService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recorList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("ChannelAction-datagridList() " + e);
		}
		return null;
	}

	/**
	 * 检验渠道名是否可用
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-16
	 * @param
	 * @Return
	 */
	public String checkChannelName() {
		try {
			HqlHelper hqlHelper = new HqlHelper(Channel.class, "c");
			hqlHelper.addWhereCondition("c.channelName=?", model.getChannelName());
			Channel channel = channelService.getByCondition(hqlHelper);
			ReturnObject returnObject = new ReturnObject();
			if (model.getChannelName() == null || "".equals(model.getChannelName())) {
				returnObject.setReturnInfo("渠道名不能为空");
			} else if (channel == null) {
				returnObject.setReturnInfo("渠道名可用");
			} else if (channel != null) {
				returnObject.setReturnInfo("渠道名已经存在");
			}
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("ChannelAction-checkChannelName() " + e);
		}
		return null;
	}

	/**
	 * 检验渠道号是否可用
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-22
	 * @param
	 * @Return
	 */
	public String checkChannelNum() {
		try {
			HqlHelper hqlHelper = new HqlHelper(Channel.class, "c");
			ReturnObject returnObject = new ReturnObject();
			if (channelnum == null || "".equals(channelnum)) {
				returnObject.setReturnInfo("渠道号不能为空");
			} else {
				if (!channelnum.matches("^[0-9]*$")) {
					returnObject.setReturnInfo("渠道号必须是数字");
				} else {
					hqlHelper.addWhereCondition("c.channelNum=?", Long.valueOf(channelnum));
					Channel channel = channelService.getByCondition(hqlHelper);
					if (channel == null) {
						returnObject.setReturnInfo("渠道号可用");
					} else {
						returnObject.setReturnInfo("渠道号已经存在");
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
	 * 删除渠道
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-16
	 * @param
	 * @Return
	 */
	public String delete() {
		try {
			Channel channel = channelService.getById(model.getId());
			channelService.delete(model.getId());
			// 记录操作日志
			operateLogService.saveLog(userLogin, userLogin.getLoginName() + "删除渠道" + channel.getChannelName());
		} catch (Exception e) {
			log.error("ChannelAction-delete " + e);
		}
		return "toList";
	}

	/**
	 * 跳转至修改页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-16
	 * @param
	 * @Return
	 */
	public String editUI() {
		try {
			Channel channel = channelService.getById(model.getId());
			setChannelnum(String.valueOf(channel.getChannelNum()));
			ActionContext.getContext().getValueStack().push(channel);
		} catch (Exception e) {
			log.error("ChannelAction-editUI " + e);
		}
		return "addUI";
	}

	/**
	 * 修改渠道信息
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-16
	 * @param
	 * @Return
	 */
	public String edit() {
		try {
			String temp = null;
			// 1，从数据库中获取原对象
			Channel channel = channelService.getById(model.getId());
			temp = channel.getChannelName();
			// 2，设置属性
			channel.setChannelName(model.getChannelName());
			channel.setChannelNum(Long.valueOf(channelnum));
			channel.setInvitationCode(model.getInvitationCode());
			// 3，更新到数据库中
			channelService.update(channel);
			// 记录操作日志
			operateLogService.saveLog(userLogin, userLogin.getLoginName() + "将渠道名‘" + temp + "’改为'" + model.getChannelName() + "'");
		} catch (Exception e) {
			log.error("ChannelAction-edit " + e);
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

	public String getChannelnum() {
		return channelnum;
	}

	public void setChannelnum(String channelnum) {
		this.channelnum = channelnum;
	}

}
