<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>新建潮流资讯</title>
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
	
		 var editor = null;  
		    window.onload = function() {  
		    	  editor = CKEDITOR.replace( 'content', {  
		            //  customConfig:"${pageContext.request.contextPath}/script/myconfig.js"  
		    		  skin: "office2003", width:600, height:300 ,
		    		 filebrowserUploadUrl : 'wxFashionInformationAction_execute.action',
		    			  toolbar :   
		    			        [   
		    			             ['Source','-','NewPage','Preview','-','Templates'],
	                         ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print','SpellChecker', 'Scayt'],
	                         ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	                         ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select','Button', 'ImageButton', 'HiddenField'],
	                          '/',
	                         ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	                          ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	                          ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	                          ['Link','Unlink','Anchor'],
	                         ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	                         '/',
	                           ['Styles','Format','Font','FontSize'],
	                          ['TextColor','BGColor']
		    			        ]  
		    		 
		          });  
		    	//  CKFinder.setupCKEditor( editor, '${pageContext.request.contextPath}/ckfinder2.4/' );
		    };  
		    
		    
		    $('#author').focus(function(){
		    	if(this.value == '今日尚品');
		    	{
		    		$(this).val('');
		    	}
		    });
		    $('#author').blur(function(){
		    	if(!this.value)
		    	{
		    		this.value='今日尚品';
		    		}
		    });  
		    
		     
	});

	

    

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
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />&nbsp;新建潮流资讯
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--显示表单内容-->

	


	
<div id=MainArea>
    <s:form id="fashionInformation" action="wxFashionInformationAction_add.action" method="POST" enctype="multipart/form-data" >
    	
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 新建潮流资讯 </div> 
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<input id="id" value="${id }" name="id" type="hidden" />
                   <tr>
                   
							<td width="60px" ><font
								color="red" text-align="right">标题：</font></td>
							<td><input id="title" value="${title }" name="title" type="text" size="40" /><font
								color="black">&nbsp;限制五十个汉字</font>
								   <label><font color="red"><span>${checkTitle }</span></font></label>
								
								</td>
							
					</tr>
					<tr>


							<td><font
								color="red">作者：</font></td>
							<td>
							<s:if test="%{author!=null&&author!=''}">
								<input id="author" value="${author }"  name="author" type="text" />
						
							</s:if>
							 <s:else>
							 	<input id="author" value="今日尚品"  name="author" type="text" />
							</s:else>	
							 <font
								color="black"> &nbsp;限制二十个汉字</font>
								 <label><font color="red"><span>${checkAuthor }</span></font></label>
							</td>
						</tr>
					
					<tr>
						<td><font
								color="red">摘要：</font></td>
						<td><textarea id="digest" name="digest"  type="text" rows="5" cols="50">${digest } </textarea><font
							color="black"> &nbsp;限制一百个汉字</font>
							<label><font color="red"><span>${checkDigest }</span></font></label>
						</td>
					</tr>
					
					<tr>
						<td><font
								color="red">发布时间：</font></td>
								
						<td><input id="releaseDate" name="releaseDate" value="${releaseDate }"
							readonly="readonly"
							onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:00'})"
							style="width: 170px;"><font
							
						</td>
						</tr>
						<c:if test="${prompt!=null }">
						</table>
						<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
						<td><font
								color="red">${prompt}</font></td></tr>
					
						</table>
						<table cellpadding="0" cellspacing="0" class="mainForm">
					</c:if>
						<tr>
						<td><font
								color="red">封面图：</font></td>
						<td>
							<div class="upload1">
								<input type="text"  class="ipt_text"  name="coverImg" id="coverImg" >  
								<input type="button" value="浏览" onclick="coverFile.click()" >  
								<input type="file" id="coverFile"  class="file" name="coverFile" onchange="document.getElementById('coverImg').value=this.value">
						<font
								color="black"> &nbsp;限制600*320jpg,png,gif&lt200k</font>
								<label><font color="red"><span id ="checkChannelNum">${checkCoverImgFile }</span></font></label>
							</div>
							
								
						</td>	
					
					</tr>
					<c:if test="${coverImg!=null }">
						<tr>
							<td><font
									color="red">原封面图：</font></td>
							
							<td><img alt="" src="${coverImg }" style="width: 180px; height:110px; " /></td>
						</tr>
					</c:if>
					</table>
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr><td><font
								color="red">资讯内容：</font></td>
							  <td><textarea id="content" name="content">${content }</textarea>
							  	<label><font color="red"><span id ="checkChannelNum">${checkContent }</span></font></label>
							   </td>   
						</tr>	
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
</body>
</html>
