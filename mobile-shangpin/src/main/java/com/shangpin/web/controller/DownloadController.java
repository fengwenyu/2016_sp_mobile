package com.shangpin.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.CookieUtil;

/**
 * @ClassName: DownloadController
 * @Description: 下载APP应用
 * @author liling
 * @date 2014年12月10日
 * @version 1.0
 */
@Controller
public class DownloadController extends BaseController {
	private static final String INDEX = "download/download";
	private static final String REDIRECT = "download/redirect";
	private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);
	private long contentLength;
	private String fileName;
	/** iphone下载地址 */
	public static final String ISO_DOWNLOAD_HREF = "https://itunes.apple.com/cn/app/id598127498?mt=8";
	
	/**Android下载地址*/
	public static final String ANDROID_DOWNLOAD_HREF="/goDownload?p=102&ch=4&fileName=shangpin_4.apk";
	/**
	 * 判断是ios手机访问
	 * @param uaString
	 * @return
	 * @author zghw
	 */
	private boolean isIOS(String uaString){
		boolean isIphone=uaString.toLowerCase().indexOf("iphone")>-1;
		boolean isIpod = uaString.toLowerCase().indexOf("ipod")>-1;
		boolean isIpad= uaString.toLowerCase().indexOf("ipad")>-1;
		if (isIphone||isIpod||isIpad) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断是否android手机访问
	 * @param uaString
	 * @return
	 * @author zghw
	 */
	private boolean isAndroid(String uaString){
		boolean isAndroid=uaString.toLowerCase().indexOf("android")>-1;
		return isAndroid;
	}
	/**
	 * 默认统计点击量
	 * 
	 * @param favor
	 * @return
	 * @author zghw
	 */
	@RequestMapping("/countDownload")
	public String countDownload(String favor, HttpServletRequest request, HttpServletResponse response) {
		String ua = request.getHeader("User-Agent").toLowerCase();
		// 如果是微信 直接转到下载页面
		if (ClientUtil.CheckMircro(ua)) {
			return index();
		} else {
			try {
				if (isIOS(ua)) {
					response.sendRedirect(ISO_DOWNLOAD_HREF);
				} else if (isAndroid(ua)) {
					return "redirect:"+ANDROID_DOWNLOAD_HREF;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: index
	 * @Description:跳到下载页
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月10日
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String index() {
		return INDEX;
	}

	/**
	 * 
	 * @Title: goDownload
	 * @Description:下载App应用
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月10日
	 */
	@RequestMapping(value = "/goDownload", method = RequestMethod.GET)
	public ModelAndView goDownload(HttpServletRequest request, HttpServletResponse response, @RequestParam String ch,
			@RequestParam String p, String fileName) {
		String referer = request.getHeader("referer");
		String channelNum = ch;
		String cookieChannel = null;
		String defaultChannel = Constants.SP_WAP_DEFAULT_CHANNELNO;
		if (CookieUtil.getCookieByName(request, Constants.CHANNEL_PARAM_NAME) != null) {
			cookieChannel = CookieUtil.getCookieByName(request, Constants.CHANNEL_PARAM_NAME).getValue();
		}
		logger.debug("download :channelNum={}, cookieChannel={},defaultChannel={},", channelNum, cookieChannel,
				defaultChannel);
		String productNum = request.getParameter("p");
		String fName = fileName;
		// String regEx =
		// "^([a-zA-Z]+)([_]{1})([a-zA-Z]+)([_v0-9.]?)([.]{1})apk$";
		String regEx = "^([a-zA-Z]+)([_]{1})([0-9]+)([.]{1})(apk|ipa)$";
		if (!channelNum.matches("^[0-9]*$") || !productNum.matches("^[0-9]*$") || !fName.matches(regEx)) {
			return null;
		}
		File dir = new File("/home/OnlineManage/product/" + productNum + File.separator + cookieChannel);
		if (!dir.exists()) {
			dir = new File("/home/OnlineManage/product/" + productNum + File.separator + channelNum);
		}
		if (!dir.exists()) {
			dir = new File("/home/OnlineManage/product/" + productNum + File.separator + defaultChannel);
		}
		logger.debug("download : dir ={} ,dirExists={}", dir.getAbsolutePath(), dir.exists());
		if (!dir.exists()) {
			// 记录访问日志
			logger.debug("download_failure:channelNum={},p={},referer={},fileName={}", channelNum, productNum, referer,
					fName);
			return null;
		}
		String[] filenames = dir.list();
		for (int i = 0; i < filenames.length; i++) {
			String filename = filenames[i];
			if (filename.matches("^([a-zA-Z]+)([_]{1})([0-9]+)([.]{1})(apk|ipa)$")) {
				String filePath = dir.getAbsolutePath() + File.separator + filename;
				File file = new File(filePath);
				logger.debug("download : filePath ={}, fileExists={}", file.getAbsolutePath(), file.exists());
				if (file.exists()) {
					setFileName(filename);
					// 记录访问日志
					logger.debug("download:channelNum={},p={},referer={},fileName={}", channelNum, productNum, referer,
							fName);
					download(request, response, filePath, filename);
				}
			}
		}
		logger.debug("download_failure:channelNum={},p={},referer={},fileName={}", channelNum, productNum, referer,
				fName);
		return null;
	}

	// 文件下载 主要方法
	private void download(HttpServletRequest request, HttpServletResponse response, String downLoadPath, String fileName) {

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream os = null;

		try {
			request.setCharacterEncoding("UTF-8");

			// 获取文件的长度
			long fileLength = new File(downLoadPath).length();
			// 设置文件输出类型
			response.setContentType("application/vnd.android.package-archive");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName);
			// 设置输出长度
			response.setHeader("Content-Length", String.valueOf(fileLength));
			// 获取输入流
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			// 输出流
			os = response.getOutputStream();
			bos = new BufferedOutputStream(os);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			os.flush();
			bos.flush();
		} catch (Exception e) {
			// TODO
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO
				}

			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO
				}
			}
		}

	}

	public void setFileName(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
		String agent = request.getHeader("User-agent");
		// 如果浏览器是IE浏览器，就得进行编码转换
		if (agent.contains("MSIE")) {
			this.fileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			this.fileName = new String(fileName.getBytes(), "ISO-8859-1");
		}
	}

	// 过滤特殊字符
	public static String stringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 *
	 * @Title: saveToken
	 * @Description:app下载前保存token
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月10日
	 */
	@RequestMapping(value = "/saveToken", method = RequestMethod.GET)
	public void saveToken(HttpServletRequest request,HttpServletResponse response) {
		String data = request.getParameter("data");
		String uuid = UUID.randomUUID().toString();
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("token_ios_share")){
				cookie.setMaxAge(0);
				break;
			}
		}
		Cookie cookie = new Cookie("token_ios_share",uuid);
		cookie.setPath("/");
		response.addCookie(cookie);
		System.out.println("token_ios_share"+data);
		logger.info("ios_share:="+uuid);
		//return "success";
	}

	@RequestMapping(value = "/getToken")
	public String getToken(HttpServletRequest request, HttpServletResponse response, Model model) {

		Cookie[] cookies = request.getCookies();
		String token=null;
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("token_ios_share")){
				token = cookie.getValue();
				logger.info("============>"+token);
				break;
			}
		}
		System.out.println(token);
		if(token!=null){
			model.addAttribute("token",token);
			logger.info("REDIRECT============>true");
		}
		return REDIRECT;
		//loggerger.info("ios_share:="+uuid);
		//return "success";
	}

}
