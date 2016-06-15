<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>启动图上传</title>
<%@ include file="/pages/public/common.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor3.6.6.1/ckeditor.js"></script>
<style>
.upload1{position:relative;}
.ipt_text{padding:2px;border:1px solid #aaa;}
.btn{border:1px solid #ccc;background:#fff}
.file{position:absolute;left:0;top:0;opacity:0;filter:alpha(opacity:0);}
</style>
<script type="text/javascript">
	$(function() {
	
		set_window = $('#set-window').window({
			closed : true,
			closable : true,
			modal : false,
			minimizable : false,
			maximizable : false,
			resizable : false
		});

		setForm = set_window.find('form');
		 
		$("#osType option").each(function(){
    		if($(this).val()=="${osType}" ){
    			$(this).attr("SELECTED","SELECTED");
    		}	
    		
    	});
			
		
		
		
	      
		
	});

	
	function showPicture(){
		var path=document.form1.picturePath.value;
		document.getElementById("img").src=path;
	}
    
	function uploadImg(v) {
		alert(v);
			$("#flag").val(v);
			set_window.window('open');
			setForm.form('clear');

		
	}
	function upload(){
		setForm.form('submit', {
			url : 'appUploadPicAction_uploadStartPic.action?flag='+$("#flag").val(),
			cache : false,

			success : function callback(data) {
				data = eval("(" + data + ")");
				if ("success" == data.returnCode) {
					$("#isu").val("1");
					$.messager.alert('操作提示', '操作成功!');
					set_window.window('close');
					location.href = "appUploadPicAction_editStartImg.action";
				} else {

					$.messager.alert('操作提示', data.returnInfo);

					set_window.window('close');

				}

			
			}
		});
	}
	

	
</script>


</head>
<body>
	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />&nbsp;奥莱APP上传图
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--显示表单内容-->

	


	
<div id=MainArea>
    <s:form action="appUploadPicAction_aolaiEdit.action" method="POST" enctype="multipart/form-data" >
    	
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> APP上传图 </div> 
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
            		<c:if test="${prompt!=null }">
					
						<table>
						<tr>
						<td><font
								color="red">${prompt}</font></td></tr>
					
						</table>
						
					</c:if>
           			<table >
					<input id="id" name="id"  type="hidden" value="${id }"
					<tr>
						<td width="50px">图片:</td>
						<td>
							<div class="upload1">
							<input type="text" class="ipt_text"  name="imgUrl" id="imgUrl" >  
							<input type="button" value="浏览" onclick="imgFile.click()" >  
							<input type="file" id="imgFile" name="imgFile"  class="file" onchange="document.getElementById('imgUrl').value=this.value">
							</div>
						   <label><font color="red"><span>${checkImgFile }</span></font></label>
						 </td>
					</tr>
						<c:if test="${imgUrl!=null&&imgUrl!='' }">
						<tr>
							<td><font
									color="red">现有图：</font></td>
							
							<td><img alt="" src="${imgUrl }" style="width: 180px; height:110px; " /></td>
						</tr>
					</c:if>
					<td>类型:</td>		
				<td><select id="osType" name="osType" value="${osType }"
						style="width: 150px;">
						
						
							<option value="-1">---请选择---</option>
							<option value="iphone4Start">iphone4启动图</option>
							<option value="iphone5Start">iphone5,6,6Plus启动图</option>
							<option value="androidStart">安卓启动图</option>
							<option value="ipadStart">ipad启动图</option>
							<!-- <option value="iphoneFindShare">iphone发现分享图</option>
							<option value="androidFindShare">安卓发现分享图</option> -->
						
						
					</select>
					 <label><font color="red"><span>${checkType }</span></font></label>
				</td>
				</table>
					<table>
						<tr>
						<td><font
								color="red">分享图需要填写以下文字描述和分享链接以及title三项，其他类型可不填</font></td></tr>
					
						</table>
				<table >
					
					<tr>
				
						<td>文字描述:</td>
						<td><textarea rows="5" cols="30" id="description"
										name="description"  >${description }</textarea>
										 <label><font color="red"><span>${checkDesc }</span></font></label>
						</td>
					</tr>
					<tr>
						<td>分享链接:</td>
						<td><input id="shareUrl" name="shareUrl" type="text" value="${shareUrl }" size="70"/> 
							 <label><font color="red"><span>${checkShare }</span></font></label>
						</td>
					</tr>
					<tr>
						<td>分享标题:</td>
						<td><input id="shareTitle" size="50" name="shareTitle" type="text" value="${shareTitle }"/> 
							 <label><font color="red"><span>${checkTitle }</span></font></label>
						</td>
					</tr>
				</table>
            </div>
        </div>
         <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
    
    
</div>
</body>
</html>
