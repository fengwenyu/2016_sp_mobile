package com.shangpin.mobileShangpin.platform.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileShangpin.common.util.FileUtil;

/**
 * 下载Action
 * 
 * @CreatDate 2013-06-20
 */
@Controller
@ParentPackage("mobileShangpin")
@Scope("prototype")
@Actions({ @Action(value = ("/appdownloadaction"), results = {//
@Result(name = "default", location = "/mobileapp.html"),//
		@Result(name = "download", type = "stream", params = {"contentLength", "contentLength", "inputName", "inputStream", "contentDisposition", "attachment;filename=\"${downloadFileName}\"", "bufferSize", "1024", "contentType", "application/vnd.android.package-archive" }) }) })
public class APPDownloadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ActionSupport.class);
	private InputStream inputStream;
	private long contentLength;
	private String fileName;

	/**
	 * 下载APP应用
	 */
	public String download() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String channelNum = request.getParameter("ch");
		String productNum = request.getParameter("p");
		String fName = request.getParameter("fileName");
		String regEx = "^([a-zA-Z]+)([_]{1})([a-zA-Z]+)([_v0-9.]?)([.]{1})apk$";
		if (!channelNum.matches("^[0-9]*$") || !productNum.matches("^[0-9]*$") || !fName.matches(regEx)) {
			return "default";
		}
		String filePath = "/home/OnlineManage/product/" + productNum + File.separator + channelNum + File.separator + fName;
		File file = new File(filePath);
		if (file.exists()) {
			setFileName(fName);
			contentLength = file.length();
			inputStream = new FileInputStream(filePath);
			ActionContext.getContext().put("name", fName);
			// 记录访问日志
			FileUtil.addLog(request, "download", "ch", channelNum, "p", productNum, "fileName", fName);
			return "download";
		} else {
			// 记录访问日志
			FileUtil.addLog(request, "download_failure", "ch", channelNum, "p", productNum, "fileName", fName);
			return "default";	
		}
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public String getDownloadFileName() {
		return fileName;
	}

	public void setFileName(String fileName) throws UnsupportedEncodingException {
		String agent = ServletActionContext.getRequest().getHeader("User-agent");
		// 如果浏览器是IE浏览器，就得进行编码转换
		if (agent.contains("MSIE")) {
			this.fileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			this.fileName = new String(fileName.getBytes(), "ISO-8859-1");
		}
	}

	public static void main(String[] args) {
		String str = "shanwgpin_shangpin_v1.0.0.apk";
		String regEx = "^([a-zA-Z]+)([_]{1})([a-zA-Z]+)([_]{1})v([0-9]+)([.]{1})([0-9]+)([.]{1})([0-9]+)([.]{1})apk$";
		System.out.println(str.matches(regEx));
	}

	// 过滤特殊字符
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		System.out.println(m.matches());
		return m.replaceAll("").trim();
	}
}
