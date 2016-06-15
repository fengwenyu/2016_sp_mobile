<%@page import="com.shangpin.mobileAolai.common.util.WebUtil"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%
	Connection con = null;
	Statement stmt = null;
	try{
		String path = request.getContextPath();
		HashMap map = new HashMap();
		map.put("username","sptest001@sina.com");
		String data = WebUtil.readContentFromGet("http://mobile.apiv2.aolai.com/shangpin/checkUsername", map);	
		out.println(data);
		out.print("<br/>");
		out.println("================================getLoginName===========================================");
		out.print("<br/>");
		String url="jdbc:mysql://wirelessapi.spidc1.com:3306/wirelessapi?characterEncoding=utf-8&user=writer&password=wt@sp520";
	    con = DriverManager.getConnection(url);
		stmt = con.createStatement();
		String query = "select loginName from account limit 0,1";
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next()){
			out.println("loginName===============" + rs.getString(1));
		}
	}catch(Exception e){
		throw e;
	}finally{
		if(stmt != null){
			stmt.close();
		}
	
		if(con != null){
			con.close();
		}
	}
%>
