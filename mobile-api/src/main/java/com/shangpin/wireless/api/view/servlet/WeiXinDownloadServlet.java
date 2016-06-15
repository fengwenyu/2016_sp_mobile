package com.shangpin.wireless.api.view.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shangpin.wechat.service.WeChatPublicService;
import com.shangpin.wireless.api.util.ProReader;


/**

微信客服下载声音返回数据
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-03-19
 */
public class WeiXinDownloadServlet extends BaseServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String URL_DOWNLOAD	= "http://file.api.weixin.qq.com/cgi-bin/media/get";
	
	WeChatPublicService weiXinService = null;

    @Override
    public void init() throws ServletException {
        weiXinService = (WeChatPublicService) getBean(WeChatPublicService.class);
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response)  {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response){
		HttpURLConnection conn=null;
		String conlen=null;
		String contype=null;
		String condis=null;
		InputStream in=null;
		try {	    
			String media_id=request.getParameter("media_id");
			if(!"".equals(media_id)&&media_id!=null){	
				conn=download(media_id);
				conlen=conn.getHeaderField("Content-Length");
				contype=conn.getHeaderField("Content-Type");
				condis=conn.getHeaderField("Content-disposition");
				response.setHeader("Content-Length", conlen);
				response.setHeader("Content-Type", contype);
				response.setHeader("Content-disposition", condis);				
				in=conn.getInputStream();
				 //  循环取出流中的数据 
		         byte [] b  =   new   byte [ 100 ];
		         int  len;
		         try  {
		             while  ((len  =  in.read(b))  >   0 )
		                response.getOutputStream().write(b,  0 , len);
		            in.close();
		        }  catch  (IOException e) {
		            e.printStackTrace();
		        }							
			}
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
	
	private HttpURLConnection download(String media_id) {	
		InputStream in=null;
		String accesstoken = weiXinService.getToken();
		if (accesstoken == null) {
			return null;
		}
		String url=URL_DOWNLOAD+"?access_token="+accesstoken+"&media_id="+media_id;
		HttpURLConnection conn = null;
		try {	
			

			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			final int resCode = conn.getResponseCode();
			String data = null;
			if (resCode == 200) {
			} else {
				data = "http code " + resCode;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

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
	public static void main(String []args){
		String url="http://192.168.9.111:8080/api/weixindownload?media_id=obUJMephKmQhQClz0D8a7UYlZEmMC7ROyeRJN8zmVSPwp6x-KM9MTybR95oNPxRZ";
		HttpURLConnection conn = null;
		InputStream in=null;
		try {	
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setConnectTimeout(50000);
			conn.setRequestMethod("GET");
			final int resCode = conn.getResponseCode();
			String data = null;
			if (resCode == 200) {
				in = conn.getInputStream();
				String disposition = conn.getHeaderField("Content-disposition");
				String dd = conn.getHeaderField("Content-Type");
			
					
				
						disposition = disposition.replaceAll("\"", "");
						String realFileName = URLDecoder.decode(disposition.substring(disposition.indexOf("filename")+"filename".length()+1),"UTF-8"); //disposition.length()-1  
				
			

					

				String srcLocalFilePath = "e:/"+realFileName;
								
				InputStream inputStream = conn.getInputStream();
				FileOutputStream fs = null;
				fs = new FileOutputStream(srcLocalFilePath);

				byte[] buffer = new byte[1204];
				int byteread = 0;
				while ((byteread = inputStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}


			} else {
				data = "http code " + resCode;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}


}
