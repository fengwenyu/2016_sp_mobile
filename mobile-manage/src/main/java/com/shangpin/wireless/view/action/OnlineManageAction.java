package com.shangpin.wireless.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.Channel;
import com.shangpin.wireless.domain.OnlineManage;
import com.shangpin.wireless.domain.Product;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.util.HqlHelper;
import com.shangpin.wireless.util.IPUtil;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;

/**
 *上线管理Action
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-25
 */
@Controller
@Scope("prototype")
public class OnlineManageAction extends BaseAction<OnlineManage> {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(OnlineManageAction.class);
	private int page = 1;
	private int rows;
	private String ids;
//	private File upload;
//	private String uploadFileName;

	/**
	 * 跳转至添加页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-25
	 * @param
	 * @Return
	 */
	public String addUI() {
		try {
			List<Product> productList = productService.findAll();
			ActionContext.getContext().put("productList", productList);
		} catch (Exception e) {
			log.error("OnlineManageAction-addUI()" + e);
		}
		return "addUI";
	}

	/**
	 * 产品列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-27
	 * @param
	 * @Return
	 */
	public String list() {
		return "list";
	}

	/**
	 * 操作日志列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-30
	 * @param
	 * @Return String
	 */
	public String query() {
		try {
			// 1，构建查询条件
			SqlHelper sqlQueryListHelper = new SqlHelper("onlineManage", "o");
			sqlQueryListHelper.addColumn("o.id");
			sqlQueryListHelper.addColumn("o.productNum");// 产品编号
			sqlQueryListHelper.addColumn("o.channelNum");// 渠道编号
			sqlQueryListHelper.addColumn("o.versionForceMax");// 强制升级最大版本号
			sqlQueryListHelper.addColumn("o.versionLatest");// 产品版本号
			sqlQueryListHelper.addColumn("o.fileName");// 文件名
			sqlQueryListHelper.addColumn("o.downloadPath");// 下载地址
			sqlQueryListHelper.addColumn("o.prompt");// 提示信息
			sqlQueryListHelper.addColumn("o.createTime");// 创建时间
			sqlQueryListHelper.addOrderByProperty(true, "o.createTime", false);
			sqlQueryListHelper.addWhereCondition("o.inuse=1");

			SqlHelper sqlQueryCountHelper = new SqlHelper("onlineManage");
			sqlQueryCountHelper.addColumn("count(*)");
			sqlQueryCountHelper.addWhereCondition("inuse=1");

			// 2，查询并准备分页信息
			List<HashMap> queryList = onlineManageService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
			List<HashMap> recordList = new ArrayList<HashMap>(queryList.size());
			for (int i = 0; i < queryList.size(); i++) {
				HashMap record = new HashMap();
				HashMap log = queryList.get(i);
				record.put("id", log.get("id"));
				HqlHelper hqlHelperProduct = new HqlHelper(Product.class, "p");
				hqlHelperProduct.addWhereCondition("p.productNum=?", Long.valueOf(log.get("productNum").toString()));
				Product product = productService.getByCondition(hqlHelperProduct);
				HqlHelper hqlHelperChannel = new HqlHelper(Channel.class, "c");
				hqlHelperChannel.addWhereCondition("c.channelNum=?", Long.valueOf(log.get("channelNum").toString()));
				Channel channel = channelService.getByCondition(hqlHelperChannel);
				record.put("summary", new StringBuilder().append("产品：").append(product.getProductName() + "(" + log.get("productNum") + ")").append("<br/>").append("渠道：").append(channel.getChannelName() + "(" + log.get("channelNum") + ")").append("<br/>").append("强制升级最大版本号：").append(log.get("versionForceMax")).append("<br/>").append("产品版本号：").append(log.get("versionLatest")).append("<br/>").append("文件名：").append(log.get("fileName")).append("<br/>").toString());
				record.put("downloadPath", log.get("downloadPath"));
				record.put("prompt", log.get("prompt"));
				record.put("createTime", log.get("createTime"));
				recordList.add(record);
			}
			Integer count = onlineManageService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recordList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("OnlineManageAction-list() " + e);
		}
		return null;
	}

	/**
	 * 添加产品
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-25
	 * @param
	 * @Return
	 */
	public String add() {
		try {
//			InputStream in = new FileInputStream(upload);
//			byte[] dataBin = new byte[in.available()];
//			in.read(dataBin);
			// 上传文件的基本路径
			String basePath = "/home/OnlineManage/product/" + model.getProductNum() + "/" + model.getChannelNum() + "/";
			File basePathFile = new File(basePath);
			if (!basePathFile.exists())
				basePathFile.mkdirs();
			// String basePath = ServletActionContext.getServletContext().getRealPath("/");
			// 构造查询对象
			HqlHelper hqlHelper = new HqlHelper(OnlineManage.class, "o");
			hqlHelper.addWhereCondition("o.channelNum=?", model.getChannelNum());
			hqlHelper.addWhereCondition("o.productNum=?", model.getProductNum());
			hqlHelper.addWhereCondition("o.inuse=?", 1);
			// 进行查询
			OnlineManage onlineManage = onlineManageService.getByCondition(hqlHelper);
			// 构造下载文件的基本路径
			String loalPath = "";
			String localIP = IPUtil.getLocalIP();
			if ("172.20.10.253".equals(localIP))
				loalPath = "http://moss.shangpin.com";
			else
				loalPath = ServletActionContext.getRequest().getScheme() + "://" + localIP + ":" + ServletActionContext.getRequest().getServerPort() + ServletActionContext.getRequest().getContextPath();
			String path = loalPath + "/" + "download.action" + "?p=" + model.getProductNum() + "&ch=" + model.getChannelNum() + "&fileName=";// + uploadFileName;
			// 获取上传文件的返回结果
//			String uploadResult = saveUploadFile(dataBin, basePath, uploadFileName);
			// 构造格式化时间对象
//			if ("success".equals(uploadResult)) {
				// if (onlineManage != null) {
				// onlineManage.setChannelNum(model.getChannelNum());
				// onlineManage.setCreateTime(new Date());
				// if ("1".equals(model.getProductNum())) { // 奥莱iPhone客户端
				// onlineManage.setDownloadPath("http://itunes.apple.com/cn/app/id432489082?mt=8&ign-mpt=uo%3D4");
				// } else {
				// onlineManage.setDownloadPath(path);
				// }
				// onlineManage.setFileName(uploadFileName);
				// onlineManage.setProductNum(model.getProductNum());
				// onlineManage.setPrompt(model.getPrompt());
				// onlineManage.setVersionLatest(model.getVersionLatest());
				// onlineManage.setVersionForceMax(model.getVersionForceMax());
				// onlineManage.setDel(1);
				// onlineManageService.save(onlineManage);
				// } else {
				// model.setDel(1);
				// model.setCreateTime(new Date());
				// model.setFileName(uploadFileName);
				// model.setDownloadPath(path);
				// onlineManageService.save(model);
				// }
				if (onlineManage != null) {
					onlineManage.setInuse(0);
					onlineManageService.update(onlineManage);
				}
				if (1 == model.getProductNum() && 2 == model.getChannelNum()) { // 奥莱iPhone客户端
					model.setDownloadPath("http://itunes.apple.com/cn/app/id432489082?mt=8&ign-mpt=uo%3D4");
				} else if (2 == model.getProductNum() && 2 == model.getChannelNum()) {
					model.setDownloadPath("https://itunes.apple.com/us/app/shang-pin-gao-duan-shi-shang/id598127498?ls=1&mt=8");// 尚品iPhone客户端
				} else {
					model.setDownloadPath(path);
				}
				model.setInuse(1);
				model.setCreateTime(new Date());
//				model.setFileName(uploadFileName);
				onlineManageService.save(model);
//			}
			// ********webservice处理时间格式***********
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// model.setCreateTime(DateUtil.convertToXMLGregorianCalendar(new Date()));
			// onlineManageService.save(model);
			// ****************************************
//			if (uploadFileName != null && !"".equals(uploadFileName))
//				operateLogService.saveLog(userLogin, "添加了产品" + uploadFileName);
		} catch (Exception e) {
			log.error("OnlineManageAction-add()" + e);
		}
		return "list";
	}

	/**
	 * 删除
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-10
	 * @param
	 * @Return
	 */
	public String delete() {
		ReturnObject returnObject = new ReturnObject();
		try {
			model = onlineManageService.getById(model.getId());
			model.setInuse(0);
			onlineManageService.update(model);
			returnObject.setReturnCode("1");// 正确标识符
		} catch (Exception e) {
			returnObject.setReturnCode("0");// 错误标识符
			returnObject.setReturnInfo("OnlineManageAction-delete " + e);
			log.error("OnlineManageAction-delete " + e);
		}
		try {
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

//	public File getUpload() {
//		return upload;
//	}
//
//	public void setUpload(File upload) {
//		this.upload = upload;
//	}
//
//	public String getUploadFileName() {
//		return uploadFileName;
//	}
//
//	public void setUploadFileName(String uploadFileName) {
//		this.uploadFileName = uploadFileName;
//	}

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
}
