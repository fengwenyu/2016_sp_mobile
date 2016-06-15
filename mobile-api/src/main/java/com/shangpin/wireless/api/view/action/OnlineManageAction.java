package com.shangpin.wireless.api.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangpin.wireless.api.base.action.BaseAction;
import com.shangpin.wireless.api.domain.OnlineManage;
import com.shangpin.wireless.api.domain.ReturnObject;
import com.shangpin.wireless.api.util.DBType;
import com.shangpin.wireless.api.util.HqlHelper;
import com.shangpin.wireless.api.util.IPUtil;
import com.shangpin.wireless.api.util.JsonUtil;
import com.shangpin.wireless.api.util.SqlHelper;

/**
 * 上线管理Action
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-07-30
 */
@Controller
@Scope("prototype")
public class OnlineManageAction extends BaseAction<OnlineManage> {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(OnlineManageAction.class);
	private int page = 1;
	private int rows;
	private String ids;
	private File upload;
	private String uploadFileName;
	private String userLoginName;

	/**
	 * 操作日志列表
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-07-30
	 * @param
	 * @Return String
	 */
	public String list() {
		try {
			// 1，构建查询条件
			SqlHelper sqlQueryListHelper = new SqlHelper("onlineManage", "o");
			SqlHelper sqlQueryCountHelper = new SqlHelper("onlineManage");
			sqlQueryListHelper.addColumn("o.id");
			sqlQueryListHelper.addColumn("o.productNum");// 产品编号
			sqlQueryListHelper.addColumn("o.channelNum");// 渠道编号
			sqlQueryListHelper.addColumn("o.versionForceMax");// 强制升级最大版本号
			sqlQueryListHelper.addColumn("o.versionLatest");// 产品版本号
			sqlQueryListHelper.addColumn("o.fileName");// 文件名
			sqlQueryListHelper.addColumn("o.downloadPath");// 下载地址
			sqlQueryListHelper.addColumn("o.prompt");// 提示信息
			sqlQueryListHelper.addColumn("o.createTime");// 创建时间
			sqlQueryCountHelper.addColumn("count(*)");
			sqlQueryListHelper.addOrderByProperty(true, "o.createTime", false);

			// 2，查询并准备分页信息
			List recorList = onlineManageService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows, DBType.dataSourceAPI.toString());
			Integer count = onlineManageService.executeSqlCount(sqlQueryCountHelper.getQueryListSql(), DBType.dataSourceAPI.toString());
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recorList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
			// 记录操作日志
			// operateLogService.saveLog(userLogin, userLogin.getLoginName() + "查看操作日志列表");
		} catch (Exception e) {
			log.error("OnlineManageAction-list():" + e);
		}
		return null;
	}

	/**
	 * 添加产品
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-07-25
	 * @param :
	 * @Return:
	 */
	public String add() {
		try {
			InputStream in = new FileInputStream(upload);
			byte[] dataBin = new byte[in.available()];
			in.read(dataBin);
			// 上传文件的基本路径
			String basePath = "/home/api/";
			File basePathFile = new File(basePath);
			if (!basePathFile.exists())
				basePathFile.mkdirs();
			// String basePath = ServletActionContext.getServletContext().getRealPath("/");
			// 构造查询对象
			HqlHelper hqlHelper = new HqlHelper(OnlineManage.class, "o");
			hqlHelper.addWhereCondition("o.channelNum=?", model.getChannelNum());
			hqlHelper.addWhereCondition("o.productNum=?", model.getProductNum());
			// 进行查询
			OnlineManage onlineManage = onlineManageService.getByCondition(hqlHelper, DBType.dataSourceAPI.toString());
			// 构造下载文件的基本路径
			String loalPath = ServletActionContext.getRequest().getScheme() + "://" + IPUtil.getLocalIP() + ":" + ServletActionContext.getRequest().getServerPort() + ServletActionContext.getRequest().getContextPath();
			String path = loalPath + "/" + "download" + "/" + uploadFileName;
			// 获取上传文件的返回结果
			String uploadResult = saveUploadFile(dataBin, basePath, uploadFileName);
			// 构造格式化时间对象

			if ("success".equals(uploadResult)) {
				if (onlineManage != null) {
					onlineManage.setChannelNum(model.getChannelNum());
					onlineManage.setCreateTime(new Date());
					onlineManage.setDownloadPath(path);
					onlineManage.setFileName(uploadFileName);
					onlineManage.setProductNum(model.getProductNum());
					onlineManage.setPrompt(model.getPrompt());
					onlineManage.setVersionLatest(model.getVersionLatest());
					onlineManage.setVersionForceMax(model.getVersionForceMax());
					onlineManageService.save(onlineManage, DBType.dataSourceAPI.toString());
				} else {
					model.setCreateTime(new Date());
					model.setFileName(uploadFileName);
					model.setDownloadPath(path);
					onlineManageService.save(model, DBType.dataSourceAPI.toString());
				}
			}
			// ********webservice处理时间格式***********
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// model.setCreateTime(DateUtil.convertToXMLGregorianCalendar(sdf.parse(sdf.format(new Date()))));
			// onlineManageService.save(model);
			// ****************************************
			operateLogService.saveLog(userLoginName, "添加了商品" + model.getFileName());
			ServletActionContext.getResponse().sendRedirect(serverPath + "/WirelessManage/onlineManageAction_list.action");
		} catch (Exception e) {
			log.error("OnlineManageAction-add()" + e);
		}
		return null;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
}
