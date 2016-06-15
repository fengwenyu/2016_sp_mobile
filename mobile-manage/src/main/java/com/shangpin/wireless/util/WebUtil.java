package com.shangpin.wireless.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.shangpin.wireless.domain.Constants;


public class WebUtil {
	/**
	 * 从流中读取数据
	 * 
	 * @Author:zhouyu
	 * @CreatDate: 2012-09-03
	 * @param inputStream
	 *            输入流
	 */
	public static String read(InputStream inputStream) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}
		inputStream.close();
		byte[] data = outputStream.toByteArray();
		outputStream.close();
		String jsonData = new String(data, "UTF-8");
		return jsonData;
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
				sb.append(java.net.URLEncoder.encode(entry.getValue(), "UTF-8"));
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
	public static String readContentFromGet(String url, Map<String, String> map) throws Exception {
		HttpURLConnection conn = (HttpURLConnection) new URL(url + makeParams(map, "GET")).openConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// TODO readContentFromGet日志时间
		// System.out.println(sdf.format(new Date()));
		 System.out.println(url + makeParams(map, "GET"));
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		final int resCode = conn.getResponseCode();
		String data = null;
		if (resCode == 200) {
			data = read(conn.getInputStream());
		} else {
			data = "http code " + resCode;
			data += "\n" + read(conn.getErrorStream());
			System.out.println(data);
		}
		return data;
	}

	public static String readContentFromGet(String url, Map<String, String> params, Map<String, String> header) throws Exception {
		url = url + makeParams(params, "GET");
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		for (Entry<String, String> entry : header.entrySet()) {
			conn.setRequestProperty(entry.getKey(), entry.getValue());
		}
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("GET");
		final int resCode = conn.getResponseCode();
		String data = null;
		if (resCode == 200) {
			data = read(conn.getInputStream());
		} else {
			data = "http code " + resCode;
		}
		return data;
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
	public static String readContentFromPost(String url, Map<String, String> map) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// TODO readContentFromPost日志时间
		System.out.println(sdf.format(new Date()));
		System.out.print(url);
		System.out.println("?" + makeParams(map, "POST"));
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setConnectTimeout(5000);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
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
			data += "\n" + read(conn.getErrorStream());
		}
		System.out.println(data);
		return data;
	}
	
	/**
	 * 获取图片扩展名
	 * 
	 * @param file
	 * @return
	 */
	public static String getExt(String fileName)
	{
		return fileName.substring(fileName.lastIndexOf('.') + 1);
	}
	public static String uploadPic(File file, String filename, String extension) {
		int size = (int) file.length();
		String contentlength = String.valueOf(size);
		StringBuffer url = new StringBuffer(Constants.BASE_URL_SP_SP+"SaveMobilePic/?extension=");
		url.append(extension);
		url.append("&picturetype=system&contentlength=");
		url.append(contentlength);
		url.append("&filename=");
		url.append(filename);
		String json = null;
		try {
			System.out.println(url);
			HttpURLConnection conn = (HttpURLConnection) new URL(url.toString()).openConnection();
			conn.setConnectTimeout(5000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "multipart/form-data");
			conn.connect();
			byte[] data = new byte[size];
			FileInputStream fis = new FileInputStream(file);
			fis.read(data, 0, size);
			int len = 0;
			while (fis.read() != -1) { // 当没有读取完时，继续读取
				fis.read(data, len, 5);
				len++;
			}
			OutputStream output = conn.getOutputStream();
			// 写入图片流
			output.write(data);
			output.flush();
			output.close();
			fis.close();
			final int resCode = conn.getResponseCode();
			if (resCode == 200) {
				json = WebUtil.read(conn.getInputStream());
			} else {
				json = "http code " + resCode;
				json += "\n" + WebUtil.read(conn.getErrorStream());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
