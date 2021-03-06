<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>新建活动页</title>
<style>
.upload1{position:relative;}
.ipt_text{padding:2px;border:1px solid #aaa;}
.btn{border:1px solid #ccc;background:#fff}
.file{position:absolute;left:0;top:0;opacity:0;filter:alpha(opacity:0);}
</style>

<%@ include file="/pages/public/common.jspf"%>
  
<script type="text/javascript">

function saveImgText() {
	var imgUrl= $('#imgUrl').val();
	var imgWidth= $('#imgWidth').val();
	var imgHeight= $('#imgHeight').val();
	var description=$('#description').val();
	var showStartDate=$('#showStartDate').val();
	var showEndDate=$('#showEndDate').val();
	var title=$('#title').val();
	var getUrl=$('#getUrl').val();


	
	if (Trim(getUrl) == '') {
		$.messager.alert('操作提示','跳转链接不能为空！');
		$('#getUrl').focus();
		return false;
	}
	
	if (Trim(showStartDate) == '') {
		$.messager.alert('操作提示', '开始时间不能为空！');
		$('#showStartDate').focus();
		return false;
	}
	if (Trim(showEndDate) == '') {
		$.messager.alert('操作提示','结束时间不能为空！');
		$('#showEndDate').focus();
		return false;
	}
	if (Trim(imgUrl) == '') {
		
		$.messager.alert('操作提示', '图片不能为空！');
		$('#imgUrl').focus();
		return false;
	}
	
	if(Trim(imgWidth) == ''){
		$.messager.alert('操作提示', '图片宽度不能为空！');
		$('#imgWidth').focus();
		return false;
	}
	if(Trim(imgHeight) == ''){
		$.messager.alert('操作提示', '图片高度不能为空！');
		$('#imgHeight').focus();
		return false;
	}
	if (Trim(title) == '') {
		$.messager.alert('操作提示','标题不能为空！');
		$('#title').focus();
		return false;
	}
	
	
	
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
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />&nbsp;新建活动页
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--显示表单内容-->
<div id=MainArea>
   	<s:form action="findManageAction_editeStaticAtc.action" id="editeStaticAtc" method="POST" enctype="multipart/form-data" onsubmit="return saveImgText();" > 
   	 <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 新建活动页 </div> 
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
            <table>
					<tr>
						<td width="85px">活动静态页链接</td>
						<td><input id="getUrl" name="getUrl" type="text" size="70"/><font
							color="red"> *</font>
							<label><font color="red"><span>${checkGetUrl }</span></font></label>
							
							 </td>
					</tr>
				</table>
				<table>
					<tr>
						<td width="85px">开始时间</td>
						<td><input id="showStartDate" name="showStartDate"
							readonly="readonly"
							onFocus="var endDate=$dp.$('showEndDate');WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){showEndDate.focus();},maxDate:'#F{$dp.$D(\'showEndDate\')}'})"
							style="width: 170px;"> <font
							color="red"> *</font>
							<label><font color="red"><span>${checkShowStartDate }</span></font></label>
						</td>
					</tr>
					<tr>
						<td>结束时间</td>
						<td><input id="showEndDate"
								name="showEndDate"
								onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'showStartDate\')}'})"
								readonly="readonly" style="width: 170px;"> <font
							color="red"> *</font>
							<label><font color="red"><span>${checkShowEndDate }</span></font></label>
						</td>
					</tr>
					<tr>
						<td>是否显示</td>
						<td><select id="display" name="display"
						style="width: 150px;">
							<option value="YES">是</option>
							<option value="NO">否</option>
						
						</select>
						</td>
					</tr>
				</table>
					<table >
						<tr>
						<td><font
								color="red">为兼容老版本APP，请填写以下项</font></td></tr>
					
						</table>
					
            	<table>
				
					<tr>
						<td width="80px">图片</td>
						<td>
							<div class="upload1">
								<input type="text" class="ipt_text"  name="imgUrl"  id="imgUrl"/>
								<input type="button" class="btn" value="浏 览" />
								<input type="file"  id="imgUrlFile" name="imgUrlFile" class="file"  onchange="document.getElementById('imgUrl').value=this.value" />
							<font
							color="red"> *</font>
							<label><font color="red"><span>${checkImgUrl }</span></font></label>
							</div>
						 </td>
					</tr>
				</table>
				<table>
					<tr>
						<td width="80px">图片宽度</td>
						<td><input id="imgWidth" name="imgWidth" type="text" /> <font
							color="red"> *</font>
							<label><font color="red"><span>${checkImgWidth}</span></font></label></td>
					</tr>
					<tr>
						<td>图片高度</td>
						<td><input id="imgHeight" name="imgHeight" type="text" /> <font
							color="red"> *</font>
							<label><font color="red"><span>${checkImgHeight}</span></font></label> </td>
					<tr>
				
						<td>文字描述</td>
						<td><textarea rows="5" cols="30" id="description"
										name="description"></textarea>
										
						</td>
					</tr>
					<tr>
						<td>标题</td>
						<td><input id="title" name="title" type="text" /><font
							color="red"> *</font>
							<label><font color="red"><span>${checkTitle}</span></font></label> </td>
					</tr>
					
				</table>
            
          
					<table >
						<tr>
						<td><font
								color="red">${checkPrompt}</font></td></tr>
					
						</table>
					
            	</div>
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
