package com.shangpin.wireless.api.view.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.util.DataContainerPool;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.Header;
import com.shangpin.wireless.api.util.MD5Util;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 上传终端日志接口
 * 
 * @Author yumeng
 * @CreateDate: 2013-03-13
 */
@SuppressWarnings("unchecked")
public class FileuploadTerminalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(FileuploadTerminalServlet.class);

	@Override
	public void init() throws ServletException {
		// ServletContext sc = this.getServletContext();
		// WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imei = request.getHeader("imei");
		String os = request.getHeader("os");
		String osv = request.getHeader("osv");
		String productNum = request.getHeader("p");
		String channelNum = request.getHeader("ch");
		String ver = request.getHeader("ver");
		String apn = request.getHeader("apn");
		String wh = request.getHeader("wh");
		String mt = request.getHeader("mt");
		String model = request.getHeader("model");
		String operator = request.getHeader("operator");
		String ua = request.getHeader("ua");
		String md5Key = request.getHeader("mk");
		if (StringUtil.isNotEmpty(imei, productNum, channelNum, md5Key)) {
			String md5Digest = "";
			String str = imei + productNum;
			try {
				md5Digest = MD5Util.md5Digest(str.toLowerCase());
			} catch (Exception e1) {
				//  Auto-generated catch block
				e1.printStackTrace();
				md5Digest = "";
			}
			if (!md5Key.equals(md5Digest)) {
				response.getWriter().print("{\"code\":\"-1\",\"msg\":\"MK error\",\"content\":{}}");
				return;
			}
			final Header header = new Header(imei, os, osv, productNum, channelNum, ver, apn, wh, mt, model, operator, ua);
			// 写入header到/home/terminalLog/xxxx-xx-xx/imei
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat _sdf = new SimpleDateFormat("HHmmssSSS");
			FileOutputStream out = null;
			try {
				// 生成header信息文件
				final String path = File.separatorChar + "home" + File.separatorChar + "terminalLog" + File.separatorChar + sdf.format(new Date()) + File.separatorChar + imei + File.separatorChar;
				// 临时文件目录
				final String tempPath = File.separatorChar + "home" + File.separatorChar + "buffer";
				FileUtil.createDir(tempPath);
				FileUtil.createDir(path);
				if (FileUtil.CreateFile(path + "header.log")) {
					out = new FileOutputStream(path + "header.log");
					out.write(header.toString().getBytes());
					out.close();
				}
				// 判断提交过来的表单是否为文件上传菜单
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				String printinfo = "{\"code\":\"-1\",\"msg\":\"not uploaded files\",\"content\":{}}";
				if (isMultipart) {
					final long MAX_SIZE = 2 * 1024 * 1024;// 设置上传文件最大为 2M
					final int Buffer = 2 * 1024 * 1024;// 设置上传文件最大为 10kb
					// 构造一个文件上传处理对象
					DiskFileItemFactory factory = new DiskFileItemFactory();
					// 设置缓冲区大小
					factory.setSizeThreshold(Buffer);
					// 设置缓冲区目录
					factory.setRepository(new File(tempPath));
					ServletFileUpload upload = new ServletFileUpload(factory);
					// 设置上传文件的最大尺寸
					upload.setSizeMax(MAX_SIZE);
					Iterator items;
					// 解析表单中提交的所有文件内容
					items = upload.parseRequest(request).iterator();
					while (items.hasNext()) {
						FileItem item = (FileItem) items.next();
						// 判断是否为文件并且是允许上传的文件类型
						if (!item.isFormField() && validateType(item.getInputStream())) {
							// 取出上传文件的文件名称
							String name = item.getName();
							// 取得上传文件以后的存储路径
							String fileName = name.substring(name.lastIndexOf('\\') + 1, name.length());
							StringBuffer fileNameBuffer = new StringBuffer(fileName);
							// 获取文件名后缀位数
							int index = fileNameBuffer.lastIndexOf(".");
							// 重名，将时分秒毫秒数作为文件名的一部分
							if (index < 0) {
								fileNameBuffer.append(_sdf.format(new Date()));
							} else {
								fileNameBuffer.insert(index, _sdf.format(new Date()));
							}
							// 上传文件以后的存储路径
							String logPath = path + fileNameBuffer.toString();
							// 上传文件
							File uploaderFile = new File(logPath);
							item.write(uploaderFile);
							printinfo = "{\"code\":\"0\",\"msg\":\"\",\"content\":{}}";
						}
					}
				}
				response.getWriter().print(printinfo);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("FileuploadTerminalServlet：" + e);
			} finally {
				if (null != out) {
					out.close();
					out = null;
				}
			}
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 通过文件流获取文件真实类型，并验证是否为允许上传的文件类型
	 * 
	 * @param fis
	 *            文件输入流
	 * 
	 * @return 是否为允许上传的文件类型，true为可上传的文件类型
	 */
	private static boolean validateType(InputStream fis) {
		String fisType = FileUtil.getTypeByStream(fis);
		Object obj = DataContainerPool.fileTypeContainer.get(fisType);
		boolean res = false;
		if (null != obj) {
			res = true;
		}
		return res;
	}
}
