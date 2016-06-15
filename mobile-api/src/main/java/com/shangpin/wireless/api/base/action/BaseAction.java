package com.shangpin.wireless.api.base.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shangpin.wireless.api.service.ErrorLogService;
import com.shangpin.wireless.api.service.OnlineManageService;
import com.shangpin.wireless.manage.service.OperateLogService;

/**
 * @Author zhouyu
 * @Create Date: 2012-07-30
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    private static final long serialVersionUID = 1670469313294525370L;
    
    protected final Log log = LogFactory.getLog(BaseAction.class);
	// **************************
	@Resource(name = OnlineManageService.SERVICE_NAME)
	protected OnlineManageService onlineManageService;
	@Resource(name = ErrorLogService.SERVICE_NAME)
	protected ErrorLogService errorLogService;
	@Resource(name = OperateLogService.SERVICE_NAME)
	protected OperateLogService operateLogService;
	// **************************
	protected T model;
	private Class<T> modelClass;
	protected int pageNum = 1;
	protected String serverPath = ServletActionContext.getRequest().getScheme() + "://" + ServletActionContext.getRequest().getServerName() + ":" + ServletActionContext.getRequest().getServerPort();

	@SuppressWarnings("unchecked")
    public BaseAction() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.modelClass = (Class<T>) pt.getActualTypeArguments()[0];

	}

	public T getModel() {
		if (model == null) {
			try {
				model = (T) modelClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return model;
	}

	/**
	 *保存上传的文件，并返回文件存储的路径
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-07-23
	 * @param inData
	 *            上传的字节
	 * @param path
	 *            文件保存路径
	 * @throws IOException
	 * @Return:String
	 */
	public String saveUploadFile(byte[] inData, String basePath, String fileName) {
		try {
			File dir = new File(basePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String path = basePath + File.separator + fileName;
			OutputStream out = new FileOutputStream(path);
			out.write(inData);
			out.close();
			return "success";
		} catch (Exception e) {
			log.error("BaseAction-saveUploadFile()" + e);
			return "error";
		}
	}

	/**
	 *下载文件
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-07-30
	 * @param path
	 *            文件保存路径
	 * @Return:
	 */
	public void downloadFile(String downloadPath, InputStream in, OutputStream out) {
		try {
			out = ServletActionContext.getResponse().getOutputStream();
			Streams.copy(in, out, true);
		} catch (Exception e) {
			log.error("UploadAndDownload-downloadFile()" + e);
		}
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
