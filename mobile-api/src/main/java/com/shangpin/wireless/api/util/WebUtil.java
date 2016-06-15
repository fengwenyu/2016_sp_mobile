package com.shangpin.wireless.api.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.CommonAPIResponse;
import com.shangpin.wireless.api.domain.Constants;

public class WebUtil {
	
	private static final Log log = LogFactory.getLog(WebUtil.class.getSimpleName());
	/**
	 * 从流中读取数据
	 * 
	 * @Author:zhouyu
	 * @CreatDate: 2012-09-03
	 * @param inputStream
	 *            输入流
	 */
	public static String read(InputStream inputStream) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}

			byte[] data = outputStream.toByteArray();
			String jsonData = new String(data, "UTF-8");

			inputStream.close();
			outputStream.flush();
			outputStream.close();
			return jsonData;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStream(inputStream, outputStream);
		}
		return null;

	}

	/**
	 * 关闭流
	 * 
	 * @param inputStream
	 * @param outputStream
	 */
	private static void closeStream(InputStream inputStream, OutputStream outputStream) {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭http的连接，释放资源
	 * 
	 * @param conn
	 */
	private static void closeConnection(HttpURLConnection conn) {
		if (conn != null) {
			conn.disconnect();
			conn = null;
		}
	}

	/**
	 * 设置connection的连接参数
	 * 
	 * @param httpURLConnection
	 */
	private static void setConnectionParam(HttpURLConnection httpURLConnection) {
		httpURLConnection.setDoOutput(true); // 指示应用程序要将数据写入URL连接,其值默认为false
		httpURLConnection.setUseCaches(false);
		httpURLConnection.setConnectTimeout(240000); // 30秒连接超时
		httpURLConnection.setReadTimeout(240000); // 30秒读取超时
	}

	public static String read(InputStream inputStream, String charset) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}

			byte[] data = outputStream.toByteArray();
			String jsonData = new String(data, (charset == null || charset.length() == 0) ? "UTF-8" : charset);

			inputStream.close();
			outputStream.flush();
			outputStream.close();
			return jsonData;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStream(inputStream, outputStream);
		}
		return null;
	}

	/**
	 * 拼接参数
	 * 
	 * @Author:zhouyu
	 * @CreatDate: 2012-09-04
	 * @param inputStream
	 *            输入流
	 */
	// login/?username=zhangsan&password=123
	public static String makeParams(Map<String, String> map, String mode) throws Exception {
		StringBuilder sb = new StringBuilder();
		if ("get".equalsIgnoreCase(mode))
			sb.append("?");
		for (Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey());
			sb.append("=");
			if ("get".equalsIgnoreCase(mode)) {
				if (null != entry.getValue()) {
					sb.append(java.net.URLEncoder.encode(entry.getValue(), "UTF-8"));
				} else {
					log.debug("makeParams :" + entry.getKey());
				}
			} else {
				sb.append(entry.getValue());
			}
			sb.append("&");
		}
		return sb.substring(0, sb.length() - 1).toString();
	}

	/**
	 * 读取GET请求方式中的数据
	 * 
	 * @Author:zhouyu
	 * @CreatDate: 2012-09-04
	 * @param url
	 *            主站接口地址
	 * @param map
	 *            参数map
	 */
	public static String readContentFromGet(String url, Map<String, String> map) {
		HttpURLConnection conn = null;
		try {
			url = url + makeParams(map, "GET");
			conn = (HttpURLConnection) new URL(url).openConnection();
			long start = new Date().getTime();
			conn.setRequestMethod("GET");
			setConnectionParam(conn);
			final int resCode = conn.getResponseCode();
			String data = null;
			if (resCode == 200) {
				data = read(conn.getInputStream());
			} else {
				data = "http code " + resCode;
			}
			long end = new Date().getTime();
			printInterfaceTime(url, (end - start));
			return data;
		} catch (Exception e) {
			printInterfaceFail(url);
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return null;

	}

	/**
	 * 读取POST请求方式中的数据
	 * 
	 * @Author:zhouyu
	 * @CreatDate: 2012-09-04
	 * @param url
	 *            主站接口地址
	 * @param map
	 *            参数map
	 */
	public static String readContentFromPost(String url, Map<String, String> map) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			long start = new Date().getTime();
			conn.setRequestMethod("POST");
			setConnectionParam(conn);
			// conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(makeParams(map, "POST").getBytes("UTF-8"));
			final int resCode = conn.getResponseCode();
			String data = null;
			outputStream.close();
			if (resCode == 200) {
				data = read(conn.getInputStream());
			} else {
				data = "http code " + resCode;
			}
			long end = new Date().getTime();
			printInterfaceTime(url, (end - start));
			return data;
		} catch (Exception e) {
			printInterfaceFail(url);
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return null;

	}

	public static String readContentFromPost(String url, String postData) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			long start = new Date().getTime();
			conn.setRequestMethod("POST");
			setConnectionParam(conn);
			// conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(postData.getBytes("UTF-8"));
			final int resCode = conn.getResponseCode();
			String data = null;
			outputStream.close();
			if (resCode == 200) {
				data = read(conn.getInputStream());
			} else {
				data = "http code " + resCode;
			}
			long end = new Date().getTime();
			printInterfaceTime(url, (end - start));
			return data;
		} catch (Exception e) {
			printInterfaceFail(url);
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return null;

	}

	public static String readContentFromGet(String url, Map<String, String> params, Map<String, String> header) {
		HttpURLConnection conn = null;
		try {
			url = url + makeParams(params, "GET");
			conn = (HttpURLConnection) new URL(url).openConnection();
			long start = new Date().getTime();
			for (Entry<String, String> entry : header.entrySet()) {
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}
			conn.setRequestMethod("GET");
			setConnectionParam(conn);
			final int resCode = conn.getResponseCode();
			String data = null;
			if (resCode == 200) {
				data = read(conn.getInputStream());
			} else {
				data = "http code " + resCode;
			}
			long end = new Date().getTime();
			printInterfaceTime(url, (end - start));
			return data;
		} catch (Exception e) {
			printInterfaceFail(url);
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return null;

	}

	public static String readContentFromPost(String url, Map<String, String> map, Map<String, String> header) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			long start = new Date().getTime();
			conn.setRequestMethod("POST");
			setConnectionParam(conn);
			// conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(makeParams(map, "POST").getBytes("UTF-8"));
			final int resCode = conn.getResponseCode();
			String data = null;
			outputStream.close();
			if (resCode == 200) {
				data = read(conn.getInputStream());
			} else {
				data = "http code " + resCode;
			}
			long end = new Date().getTime();
			printInterfaceTime(url, (end - start));
			return data;
		} catch (Exception e) {
			printInterfaceFail(url);
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return null;

	}

	public static String readContentFromPost(String url, String postData, Map<String, String> header) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			long start = new Date().getTime();
			for (Entry<String, String> entry : header.entrySet()) {
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}
			conn.setRequestMethod("POST");
			setConnectionParam(conn);
			// conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(postData.getBytes("UTF-8"));
			final int resCode = conn.getResponseCode();
			Map<String, java.util.List<String>> headers = conn.getHeaderFields();
			for (String key : headers.keySet()) {
				log.info(key + " = " + headers.get(key));
			}
			log.debug(conn.getContentType());
			String data = null;
			outputStream.close();
			if (resCode == 200) {
				String charset = findCharset(conn.getContentType());
				data = read(conn.getInputStream(), charset);
			} else {
				data = "http code " + resCode;
			}
			long end = new Date().getTime();
			printInterfaceTime(url, (end - start));
			return data;
		} catch (Exception e) {
			printInterfaceFail(url);
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return null;

	}

	/**
	 * 打印错误日志
	 * @param url
	 */
	private static void printInterfaceFail(String url) {
		log.error("URL:" + url + ",status: fail");
	}

	/**
	 * 打印系统日志
	 * @param url
	 * @param time
	 */
	private static void printInterfaceTime(String url, long time) {
		log.info("URL:" + url + ",TIME:" + time + "ms");
	}

	public static String findCharset(String contentType) {
		String lowercase = contentType.toLowerCase();
		int start = lowercase.indexOf("charset");
		if (start >= 0) {
			int end = lowercase.indexOf(";", start);
			if (end < 0) {
				end = lowercase.length();
			}
			return contentType.substring(start + "charset".length() + 1, end);
		}
		return null;
	}

	/**
	 * 返回API服务器的异常
	 * 
	 * @Author:zhouyu
	 * @CreatDate: 2012-09-04
	 * @param response
	 */
	public static void sendApiException(HttpServletResponse response) throws Exception {
		CommonAPIResponse apiResponse = new CommonAPIResponse();
		apiResponse.setCode(Constants.ERROR_SYSTEM);
		apiResponse.setMsg(Constants.ERROR_SYSTEM_PROMPT);
		response.getWriter().print(apiResponse.obj2Json());
	}

	/**
	 * 返回登录受限提示
	 * 
	 * @Author:zhouyu
	 * @CreatDate: 2012-09-04
	 * @param response
	 */
	public static void sendLimitException(HttpServletResponse response) throws Exception {
		CommonAPIResponse apiResponse = new CommonAPIResponse();
		apiResponse.setCode(Constants.ERROR_LIMIT);
		apiResponse.setMsg(Constants.ERROR_LIMIT_PROMPT);
		response.getWriter().print(apiResponse.obj2Json());
	}

	/**
	 * 返回sessionid不匹配的异常
	 * 
	 * @Author:zhouyu
	 * @CreatDate: 2012-09-04
	 * @param response
	 */
	public static void sendErrorNoSessionid(HttpServletResponse response) throws Exception {
		CommonAPIResponse apiResponse = new CommonAPIResponse();
		apiResponse.setCode(Constants.ERROR_NO_SESSIONID);
		apiResponse.setMsg(Constants.ERROR_NO_SESSIONID_PROMPT);
		response.getWriter().print(apiResponse.obj2Json());
	}

	/**
	 * 请求参数不全或者参数为空
	 * 
	 * @Author:zhouyu
	 * @CreatDate: 2012-09-16
	 * @param response
	 */
	public static void sendErrorInvalidParams(HttpServletResponse response) throws Exception {
		CommonAPIResponse apiResponse = new CommonAPIResponse();
		apiResponse.setCode(Constants.ERROR_INVALIDPARAMS);
		apiResponse.setMsg(Constants.ERROR_INVALIDPARAMS_PROMPT);
		response.getWriter().print(apiResponse.obj2Json());
	}
	
	/**
	 * 返回头像上传失败提示
	 * 
	 * @Author:zhouyu
	 * @CreatDate: 2012-09-04
	 * @param response
	 */
	public static void sendIconException(HttpServletResponse response, String msg) throws Exception {
		CommonAPIResponse apiResponse = new CommonAPIResponse();
		apiResponse.setCode(Constants.ERROR_LIMIT);
		apiResponse.setMsg(Constants.ERROR_LIMIT_PROMPT);
		response.getWriter().print(msg);
	}
	public static void main(String[] args) throws Exception {

		HashMap<String, String> header = new HashMap<String, String>();
		header.put("user-agent", "mozilla/5.0(iPhone;cpu iphone os 6_1_2 like mac os x)applewebkit/536.26(khtml,like gecko)mobile/10b146 micromessenger/4.5");
		header.put("p", "2");
		header.put("ch", "2");
		header.put("ver", "1.0.0");
		header.put("imei", "bf4d1c1bcaf6dc618fe8a0425a918fd2");
		header.put("Accept", "text/xml,text/javascript,text/html");
		header.put("User-Agent", "aop-sdk-java");
		header.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("username", "13983288416");
		map.put("password", "870612");
		map.put("userid", "2D772040F01528115023D1E7AE07EE6B");
		map.put("ordertype", "1");
		map.put("page", "1");
		map.put("pagesize", "2");
		map.put("sessionid", "4QGWGSLVZHIWL2KPOBJ02FCI");

		// String content =
		// readContentFromGet("http://life.qq.com/weixin/r/msg_1glj#wechat_redirect",
		// map, header);
		// String content =
		// readContentFromGet("http://192.168.9.36:8080/api/login", map,
		// header);
		// String content =
		// readContentFromGet("http://mobile.apiv2.shangpin.com/aolai/login",
		// map, header);
		// String content =
		// readContentFromGet("http://192.168.10.220:8080/api/ordermanage", map,
		// header);
		// String postStr =
		// "sign=fOfc%2B0GdqHLG%2FFfbV%2B7PhdBR98roymkaOsXeJUUjOfGJjY6lXAENh%2BU%2B817CoCrMNadohtvdzIck%2F1LyDNJoDDq2eCjvHiCqooFyaRbWF4HxKHtggp%2B0oUeV9rtglHGaoYei360pdL6xHggNIHTBuNBWRsBBDepXxCC3vHkmbIw%3D&timestamp=2013-09-22+10%3A01%3A46&sign_type=RSA&app_id=2013080600000731&method=alipay.user.userinfo.share&format=json&version=1.0";
		// String postStr =
		// "{\"touser\":\"oFHXijhanQsN1n6iZPyepw1fsmZ0\",\"msgtype\":\"text\",\"text\":{\"content\":\"Hello a World\"}}";
		String postStr = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 003}}}";
		// String content =
		// readContentFromPost("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=y_yyhJmDinOV9WvE4cVorWP4VOQFW1nYp3-D5mUmOmqhHvKd37VCaiTYr5GfRmVc8uMZJkWswmt-C4jreQ5kN25mHtTetVq_-TYHcAwLvD0mU8gW2T23WJBtwi4XrwIJl0RDnH5FCwieamTdJ7opWQ",
		// postStr, header);
		String content = readContentFromPost(
				"https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=y_yyhJmDinOV9WvE4cVorWP4VOQFW1nYp3-D5mUmOmqhHvKd37VCaiTYr5GfRmVc8uMZJkWswmt-C4jreQ5kN25mHtTetVq_-TYHcAwLvD0mU8gW2T23WJBtwi4XrwIJl0RDnH5FCwieamTdJ7opWQ",
				postStr, header);
		final String totalfee = "9";
		// final int lastIndex = totalfee.length() - 3;
		double totalFee;
		try {
			totalFee = Double.parseDouble(totalfee);
		} catch (Exception e) {
			totalFee = -1;
		}
		log.debug("totalFee : " + totalFee);
		String fee = String.format("%-6.2f", totalFee);
		log.debug("fee = [" + fee + "]");
		log.debug("fee = [" + fee.trim() + "]");
	}
}
