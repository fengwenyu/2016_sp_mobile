<%@ page import="com.shangpin.core.entity.main.Dictionary"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%!
	public String tree(Dictionary dictionary, String basePath) {
		StringBuilder builder = new StringBuilder();
		
		long pid = dictionary.getParent() == null ? 0:dictionary.getParent().getId();
		builder.append("{id:" + dictionary.getId() +  ", pId:" + pid + 
				", name:\"" + dictionary.getName() + "\", url:\"" + basePath + "/management/security/dictionary/list/" + dictionary.getId() + "\", target:\"ajax\"},");
		
		for(Dictionary dic : dictionary.getChildren()) {
			builder.append(tree(dic, basePath));				
		}
		return builder.toString();
	}
%>
<%
	Dictionary dictionary2 = (Dictionary)request.getAttribute("dictionary");
	String dictionaryTree = tree(dictionary2, request.getContextPath());
	dictionaryTree = dictionaryTree.substring(0, dictionaryTree.length() - 1);
%>
<script type="text/javascript">
<!--
var setting = {
	view: {
		//showIcon: false
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	callback: {
		onClick: function(event, treeId, treeNode) {
			var $rel = $("#jbsxBox2dictionaryList");
			$rel.loadUrl(treeNode.url, {}, function(){
				$rel.find("[layoutH]").layoutH();
			});

			event.preventDefault();
		}
	}	
};

var zNodes =[<%=dictionaryTree%>];
     	
$(document).ready(function(){
	var t = $("#dictionaryTree");
	t = $.fn.zTree.init(t, setting, zNodes);
	t.expandAll(true); 
});
//-->
</script>
<ul id="dictionaryTree" class="ztree"></ul>