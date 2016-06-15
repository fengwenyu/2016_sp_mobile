package com.shangpin.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qinyingchun 
 * 读写web应用中的输入输出数据流
 *
 */
public class WebContentUtil {

	private static final Logger logger = LoggerFactory.getLogger(WebContentUtil.class);

	public static String readContentFromPost(String url, String postData) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			long start = new Date().getTime();
			conn.setRequestMethod("POST");
			setConnectionParam(conn);
			// conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
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
	
	/**
	 *  模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
	 * @param url 请求地址 form表单url地址
	 * @param filePath 文件在服务器保存路径
	 * @return
	 * @throws IOException 
	 */
	public static String readContentFromFile(String url, String filePath) throws IOException{

	    File file = new File(filePath);

	  	return readContentFromFile(url, file);
	}
	/**
	 *  模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
	 * @param url 请求地址 form表单url地址
	 * @param file 文件
	 * @return
	 * @throws IOException
	 */
	public static String readContentFromFile(String url, File file) throws IOException{

		if(!file.exists() || !file.isFile()){
			throw new IOException("文件不存在");
		}

		String result = null;
	    /**
	    * 第一部分
	    */
	    URL urlObj = new URL(url);
	    // 连接
	    HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
	    /**
	    * 设置关键值
	    */
	    con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
	    con.setDoInput(true);
	    con.setDoOutput(true);
	    con.setUseCaches(false); // post方式不能使用缓存
	    // 设置请求头信息
	    con.setRequestProperty("Connection", "Keep-Alive");
	    con.setRequestProperty("Charset", "UTF-8");
	    // 设置边界
	    String BOUNDARY = "----------" + System.currentTimeMillis();
	    con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);
	    // 请求正文信息
	    // 第一部分：
	    StringBuilder sb = new StringBuilder();
	    sb.append("--"); // 必须多两道线
	    sb.append(BOUNDARY);
	    sb.append("\r\n");
	    sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
	    sb.append("Content-Type:application/octet-stream\r\n\r\n");
	    byte[] head = sb.toString().getBytes("utf-8");
	    // 获得输出流
	    OutputStream out = new DataOutputStream(con.getOutputStream());
	    // 输出表头
	    out.write(head);
	    // 文件正文部分
	    // 把文件已流文件的方式 推入到url中
	    DataInputStream in = new DataInputStream(new FileInputStream(file));
	    int bytes = 0;
	    byte[] bufferOut = new byte[1024];
	    while ((bytes = in.read(bufferOut)) != -1) {
	    	out.write(bufferOut, 0, bytes);
	    }
	    in.close();
	    // 结尾部分
	    byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
	    out.write(foot);
	    out.flush();
	    out.close();
	    StringBuffer buffer = new StringBuffer();
	    BufferedReader reader = null;
	    try {
		    // 定义BufferedReader输入流来读取URL的响应
		    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	buffer.append(line);
		    }
		    if(result==null){
		    	result = buffer.toString();
		    }
	    } catch (IOException e) {
		   logger.debug("发送POST请求出现异常！" + e);
		    e.printStackTrace();
		    throw new IOException("数据读取异常");
	    } finally {
		    if(reader!=null){
		    reader.close();
		    }
	    }
	    return result;
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

	/**
	 * 从流中读取数据
	 * 
	 * @param inputStream
	 * @return
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
	 * 打印系统日志
	 * 
	 * @param url
	 * @param time
	 */
	private static void printInterfaceTime(String url, long time) {
		logger.info("URL:" + url + ",TIME:" + time + "ms");
	}

	/**
	 * 打印错误日志
	 * 
	 * @param url
	 */
	private static void printInterfaceFail(String url) {
		logger.error("URL:" + url + ",status: fail");
	}
	
	public static void main(String[] args) {
		String params = "<xml><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName><FromUserName><![CDATA[oFHXijs8DAnA2OfSwOUH7rZtuv4U]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event></xml>";
		WebContentUtil.readContentFromPost("http://api.m.shangpin.com/api/weixinapi", params);
	}
}
