package com.shangpin.wireless.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.wireless.util.FileUtil;

/**
 *下载Action
 * 
 * @Author zhouyu
 * @CreatDate 2013-01-10
 */
@Controller
@Scope("prototype")
public class Download extends ActionSupport {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(Download.class);
	private InputStream inputStream; // 下载用的
	private long contentLength;

	/**
	 * 下载文件
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-10
	 * @param
	 * @throws FileNotFoundException
	 * @Return
	 */
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String channelNum = request.getParameter("ch");
		String productNum = request.getParameter("p");
		String fileName = request.getParameter("fileName");
		// if (fileName.indexOf("./") > 0 || fileName.indexOf("../") > 0 || //
		// channelNum.indexOf("./") > 0 || channelNum.indexOf("../") > 0 || //
		// productNum.indexOf("./") > 0 || productNum.indexOf("../") > 0) {
		// return "privilegeError";
		// }
		String regEx = "^([a-zA-Z]+)([_]{1})([a-zA-Z]+)([_]{1})v([0-9]+)([.]{1})([0-9]+)([.]{1})([0-9]+)([.]{1})apk$";
		if (!channelNum.matches("^[0-9]*$") || !productNum.matches("^[0-9]*$") || !fileName.matches(regEx)) {
			return "privilegeError";
		}
		String filePath = "/home/OnlineManage/product/" + productNum + File.separator + channelNum + File.separator + fileName;
		File file = new File(filePath);
		if (file.exists()) {
			contentLength = file.length();
			inputStream = new FileInputStream(filePath);
			ActionContext.getContext().put("name", fileName);
			// 记录访问日志
			FileUtil.addLog(request, "download", "ch", channelNum, "p", productNum, "fileName", fileName);
			return "download";
		} else {
			// 记录访问日志
			FileUtil.addLog(request, "download", "ch", channelNum, "p", productNum, "fileName", fileName);
			return "privilegeError";
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

	/*@Test
	public void testStringFilter() throws PatternSyntaxException {
		String str = "*adCVs*34_a _09_b5*[/435^*&城池()^$$&*).{}+.|.)%%*(*.中国}34{45[]12.fd'*&999下面是中文的字符￥……{}【】。，；’“‘”？";
		System.out.println(str);
		System.out.println(StringFilter(str));
	}*/
}
