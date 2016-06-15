package com.shangpin.mobileShangpin.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;

public class WebUtil {
	public static final String SESSION_USER_PARAM = "user";
	private static final Log log = LogFactory.getLog(WebUtil.class);

	/**
	 * 获取登录用户
	 * 
	 * @return
	 */
	public static AccountVO getSessionUser() {
		AccountVO tbShopBackUser = (AccountVO) ActionContext.getContext()
				.getSession().get(SESSION_USER_PARAM);
		return tbShopBackUser != null ? tbShopBackUser : null;
	}

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
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(inputStream, outputStream);
		}
		return null;

	}

	private static void closeStream(InputStream inputStream,
			OutputStream outputStream) {
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
	 * 拼接参数
	 * 
	 * @Author:zhouyu
	 * @CreatDate: 2012-09-04
	 * @param inputStream
	 *            输入流
	 */
	// login/?username=zhangsan&password=123
	public static String makeParams(Map<String, String> map, String mode)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		if ("get".equalsIgnoreCase(mode))
			sb.append("?");
		for (Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey());
			sb.append("=");
			if ("get".equalsIgnoreCase(mode)) {
				if (StringUtil.isNotEmpty(entry.getValue())) {
					sb.append(java.net.URLEncoder.encode(entry.getValue(),
							"UTF-8"));
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
			long start = System.currentTimeMillis();
			conn = (HttpURLConnection) new URL(url + makeParams(map, "GET"))
					.openConnection();
			conn.setRequestMethod("GET");
			setConnectionParam(conn);
			final int resCode = conn.getResponseCode();
			String data = null;
			if (resCode == 200) {
				data = read(conn.getInputStream());
			} else {
				data = "http code " + resCode;
				data += "\n" + read(conn.getErrorStream());
			}
			long end = System.currentTimeMillis();
			printInterfaceTime(url, (end - start));
			return data;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return null;

	}

	private static void closeConnection(HttpURLConnection conn) {
		if (conn != null) {
			conn.disconnect();
			conn = null;
		}
	}

	/**
	 * 读取POST请求方式中的数据
	 * 
	 * @param url
	 *            主站接口地址
	 * @param postData
	 *            参数
	 */
	public static String readContentFromPost(String url, String postData) {
		HttpURLConnection conn = null;
		try {
			long start = System.currentTimeMillis();
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("POST");
			setConnectionParam(conn);
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
			long end = System.currentTimeMillis();
			printInterfaceTime(url, (end - start));
			return data;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
			long start = System.currentTimeMillis();
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("POST");
			setConnectionParam(conn);
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(makeParams(map, "POST").getBytes("UTF-8"));
			final int resCode = conn.getResponseCode();
			String data = null;
			outputStream.close();
			if (resCode == 200) {
				data = read(conn.getInputStream());
			} else {
				data = "http code " + resCode;
				data += "\n" + read(conn.getErrorStream());
			}
			long end = System.currentTimeMillis();
			printInterfaceTime(url, (end - start));
			return data;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return null;

	}

	/**
	 * 设置connection的连接参数
	 * 
	 * @param httpURLConnection
	 */
	private static void setConnectionParam(HttpURLConnection httpURLConnection) {
		httpURLConnection.setDoOutput(true); // 指示应用程序要将数据写入URL连接,其值默认为false
		httpURLConnection.setUseCaches(false);
		httpURLConnection.setConnectTimeout(15000); // 30秒连接超时
		httpURLConnection.setReadTimeout(15000); // 30秒读取超时
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
	public static String readHostContentFromPost(String url,
			Map<String, String> map, String host) {
		HttpURLConnection conn = null;
		try {
			long start = System.currentTimeMillis();
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Host", host);
			setConnectionParam(conn);
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(makeParams(map, "POST").getBytes("UTF-8"));
			final int resCode = conn.getResponseCode();
			String data = null;
			outputStream.close();
			if (resCode == 200) {
				data = read(conn.getInputStream());
			} else {
				data = "http code " + resCode;
				data += "\n" + read(conn.getErrorStream());
			}
			long end = System.currentTimeMillis();
			printInterfaceTime(url, (end - start));
			return data;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return null;

	}
	
	private static void printInterfaceTime(String url, long time) {
		log.info("URL:" + url + ",TIME:" + time + "ms");
	}
}
