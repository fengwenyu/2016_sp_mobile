<%@page import="com.shangpin.core.entity.main.Dictionary"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%> 
<%!
	public String tree(Dictionary dictionary, Long dictionaryId) {
		if (dictionary.getChildren().isEmpty()) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("<ul>" + "\n");
		for(Dictionary dic : dictionary.getChildren()) {
			// 不显示自己及子元素
			if (dictionaryId.equals(dic.getId())) {
				break;
			}
			buffer.append("<li><a href=\"javascript:\" onclick=\"$.bringBack({id:'" + dic.getId() + "', name:'" + dic.getName() + "'})\">" + dic.getName() + "</a>" + "\n");
			buffer.append(tree(dic, dictionaryId));
			buffer.append("</li>" + "\n");
		}
		buffer.append("</ul>" + "\n");
		return buffer.toString();
	}
%>
<%
	Dictionary dictionary = (Dictionary)request.getAttribute("dictionary");
	Long dictionaryId = (Long)request.getAttribute("id");
%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="58">
		<ul class="tree expand">
			<li><a href="javascript:"><%=dictionary.getName() %></a>
				<%=tree(dictionary, dictionaryId) %>
			</li>
		</ul>
	</div>
	
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</div>