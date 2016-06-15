package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**

 */
public class BrowserTestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(BrowserTestServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuilder sbuff = new StringBuilder();
		sbuff.append("<!doctype html>\n").append("<html><head>");
		sbuff.append("<meta content-type=\"text/html\" charset=\"utf-8\">\n");
		sbuff.append("</head>");
		sbuff.append("<script type=\"text/javascript\">").append("\r\n");
		sbuff.append("document.write(\"<p>Header\");");
		sbuff.append("document.write(\"<br/>\");");
		for (Enumeration e = request.getHeaderNames();e.hasMoreElements();) {
			String key = (String)e.nextElement();
			String value = request.getHeader(key);
			sbuff.append("document.write(\"").append(key).append("\" + \" = \" + \"").append(value).append("\");");
			sbuff.append("document.write(\"<br/>\");");
		}
		sbuff.append("document.write(\"<p>Browser\");");
		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("document.write(\"cookie: \" + document.cookie);");
		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("document.write(\"title: \" + document.title);");
		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("document.write(\"URL: \" + document.URL);");
		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("document.write(\"history: \" + history.length);");
		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("document.write(\"protocol: \" + location.protocol);");
		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("document.write(\"appCodeName: \" + navigator.appCodeName);");
		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("document.write(\"appName: \" + navigator.appName);");
		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("document.write(\"appVersion: \" + navigator.appVersion);");
		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("document.write(\"language: \" + navigator.language);");
		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("document.write(\"cookieEnabled: \" + navigator.cookieEnabled);");
		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("document.write(\"platform: \" + navigator.platform);");
		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("document.write(\"userAgent: \" + navigator.userAgent);");
		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("var len = navigator.plugins.length;");
		sbuff.append("document.write (\"你的浏览器共支持\" + len + \"种plug-in：<BR>\");");
		sbuff.append("document.write (\"<TABLE BORDER>\");");
		sbuff.append("document.write (\"<CAPTION>PLUG-IN 清单</CAPTION>\");");
		sbuff.append("document.write (\"<TR><TH> <TH>名称<TH>描述<TH>文件名\");");
		sbuff.append("for (var i=0;i<len;i++){");
			sbuff.append("document.write(\"<TR><TD>\" + i + " +
	            "\"<TD>\" + navigator.plugins[i].name + " +
	            "\"<TD>\" + navigator.plugins[i].description + " +
	            "\"<TD>\" + navigator.plugins[i].filename);");
			sbuff.append("document.write(\"</TR>\");");
		sbuff.append("}");

		sbuff.append("document.write(\"<br/>\");");
		sbuff.append("var len = navigator.mimeTypes.length;");
		sbuff.append("document.write (\"你的浏览器共支持\" + len + \"种MIME类型：\");");
		sbuff.append("document.write (\"<TABLE BORDER>\");");
		sbuff.append("document.write (\"<CAPTION>MIME type 清单</CAPTION>\");");
		sbuff.append("document.write (\"<TR><TH> <TH>名称<TH>描述<TH>扩展名<TH>附注\");");
		sbuff.append("for (var i=0; i<len; i++) {");
			sbuff.append("document.write(\"<TR><TD>\" + i + " +
	           "\"<TD>\" + navigator.mimeTypes[i].type + " +
	           "\"<TD>\" + navigator.mimeTypes[i].description + " +
	           "\"<TD>\" + navigator.mimeTypes[i].suffixes + " +
	           "\"<TD>\" + navigator.mimeTypes[i].enabledPlugin.name);");
			sbuff.append("document.write(\"</TR>\");");
		sbuff.append("}");
		sbuff.append("document.write(\"<br/>\");");
		
		sbuff.append("\r\n").append("</script>").append("\r\n");
		sbuff.append("<body>");
		sbuff.append("</body>");
		sbuff.append("</html>");
		
		response.getWriter().print(sbuff.toString());
		
		log.debug("BrowserTestServlet: " + sbuff.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
