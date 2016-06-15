package com.shangpin.wireless.api.view.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wechat.service.WeChatPublicService;
import com.shangpin.wireless.api.util.ProReader;
import com.shangpin.wireless.api.util.WebUtil;



/**

微信客服上传图片返回数据
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-03-19
 */
public class WeiXinUploadServlet extends BaseServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URL_BASE	= "https://api.weixin.qq.com/cgi-bin/";
	private static final String URL_UPLOAD	= "http://file.api.weixin.qq.com/cgi-bin/";
	private final Log log = LogFactory.getLog(WeiXinUploadServlet.class.getSimpleName());
	WeChatPublicService weiXinService = null;

    @Override
    public void init() throws ServletException {
        weiXinService = (WeChatPublicService) getBean(WeChatPublicService.class);
    }
	public void doGet(HttpServletRequest request, HttpServletResponse response)  {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try {	    
		    InputStream in=request.getInputStream();
			response.getWriter().print(upload(in));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static byte[] readBytes(InputStream in) throws IOException {
		BufferedInputStream bufin = new BufferedInputStream(in);
		int buffSize = 1024;
		ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);
		byte[] temp = new byte[buffSize];
		int size = 0;
		while ((size = bufin.read(temp)) != -1) {
			out.write(temp, 0, size);
		}
		bufin.close();

		byte[] content = out.toByteArray();
		return content;
	}
	
	private String upload(InputStream in) {	
		String accesstoken = weiXinService.getToken();
		String json=null;
		if (accesstoken == null) {
			log.warn("accesstoken is null");
			return "failt";
		}
		String url = URL_UPLOAD + "media/upload?access_token=" + accesstoken+"&type=image";
		try {	
			HttpURLConnection conn = (HttpURLConnection) new URL(url.toString()).openConnection();
			conn.setConnectTimeout(5000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "multipart/form-data");
			OutputStream outputStream = conn.getOutputStream();
		    outputStream.write(readBytes(in));
			outputStream.flush();
			outputStream.close();

			final int resCode = conn.getResponseCode();
			if (resCode == 200) {
				json = WebUtil.read(conn.getInputStream());
			} else {
				json = "http code " + resCode;
				json += "\n" + WebUtil.read(conn.getErrorStream());
			}
			//JSONObject obj = JSONObject.fromObject(json);
			//media_id=parseJsonImage(json);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;

	}
	public static String parseJsonImage (String content) {
		JSONObject jsonObj = JSONObject.fromObject(content);
		String media=null;
		if("image".equals(jsonObj.get("type").toString())){
			media=jsonObj.get("media_id").toString();			
		}
		return media;
	}
	public static void writeLog(String log) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fileName = sdf.format(new Date()) + ".txt";
			String path = ProReader.getInstance().getProperty("weixinOperationLogsPath");
			File filePath = new File(path);
			if (!filePath.exists())
				filePath.mkdirs();
			RandomAccessFile randomFile = new RandomAccessFile(path + fileName, "rw");
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.write((log + "\n").getBytes());
			randomFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
