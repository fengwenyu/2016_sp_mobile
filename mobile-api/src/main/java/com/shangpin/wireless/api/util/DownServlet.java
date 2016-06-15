package com.shangpin.wireless.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 产品下载
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-07-30
 */
public class DownServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Log log = LogFactory.getLog(DownServlet.class);
		try {
			request.setCharacterEncoding("utf-8");
			String fileName = request.getParameter("fileName");
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName.split("_")[1], "utf-8"));
			String basePath = "/home/api/";
			File basePathFile = new File(basePath);
			if (!basePathFile.exists())
				basePathFile.mkdirs();
			File uploadedFile = new File(basePath + fileName);
			OutputStream out = response.getOutputStream();
			InputStream in = new FileInputStream(uploadedFile);
			Streams.copy(in, out, false);
		} catch (Exception e) {
			log.error("DownServlet:" + e.getMessage());
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
