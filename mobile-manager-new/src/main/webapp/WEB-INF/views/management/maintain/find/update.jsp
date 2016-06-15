<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<script type="text/javascript">



</script>
<div class="pageContent">
<form method="post" action="${contextPath }/management/maintain/find/update" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<div class="pageFormContent" layoutH="58">  
	<input id="id" name="id" value="${find.id}"  type="hidden" />
		<c:if test="${ !empty find.imgUrl}">
			<p>
			<dl style="height: 120px">
				<dt>原封面图：</dt>
			 	<dd>
			 		<input type="hidden" name="imgUrl" value="${find.imgUrl}" id="imgUrlInput"/>
					<img id="" src="${find.imgUrl}" width="120px" height="120px" />
				</dd>
			</dl>
			</p>
			<p>
			<font color="red">注意：如果上传新的图片将覆盖原有图片！</font>
		</c:if>
			<p>
			
			<dl style="height: 120px">
				<dt>图片：</dt>
				<dd>
					<input type="hidden" name="imgUrl" value="" id="imgUrlInput"/>
					<img id="headImg" src="${imgUrl}" width="120px" height="120px" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>上传图片：</dt>
				<dd>
					<input id="file_upload" type="file" name="image"
						uploaderOption="{
		        'auto':true,
		        'successTimeout':300,
		        'swf':'${contextPath}/styles/uploadify/scripts/uploadify.swf',
		        'overrideEvents' : ['onDialogClose'],
		        'queueID':'fileQueue',
		        'fileObjName':'file',
		        'uploader':'${contextPath}/management/maintain/find/upload;jsessionid=<%=session.getId()%>',
		        'buttonText':'请选择文件',
		        'fileTypeDesc':'Image File',
		        'fileTypeExts':'*.jpg;*.jpeg;*.gif;*.png;',
		        'fileSizeLimit':'1MB',
		        'queueSizeLimit' : 1,
		        'onSelectError':function(file, errorCode, errorMsg){
		            switch(errorCode) {
		                case -100:
		                	alertMsg.error('上传的文件数量已经超出系统限制的'+$('#file_upload').uploadify('settings','queueSizeLimit')+'个文件！');
		                    break;
		                case -110:
		                	alertMsg.error('文件 ['+file.name+'] 大小超出系统限制的'+$('#file_upload').uploadify('settings','fileSizeLimit')+'大小！');
		                    break;
		                case -120:
		                	alertMsg.error('文件 ['+file.name+'] 大小异常！');
		                    break;
		                case -130:
		                	alertMsg.error('文件 ['+file.name+'] 类型不正确！');
		                    break;
		            }
		        },
		        'onFallback':function(){
		        	alertMsg.error('您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。');
		        },
		        'onUploadSuccess':function(file, data, response){
		        console.log(data);
		        var json = $.parseJSON(data);
		        var src = json.message;
		        var code = json.statusCode;
		        console.log(src);
		        	$('#headImg').attr('src',src);
		        	$('#imgUrlInput').val(src);
		        	$('#headImageUrl').attr('value', data);
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	if (errorCode == 400) {
		        		forbidden();
		        	}
		        	if (errorCode == 403) {
		        		forbidden();
		        	}
		        	alertMsg.error(file.name + '上传失败: ' + errorMsg + errorString);
		        }
			}" />
					<font color="red">只允许上传1M内jpg,jpeg,gif,png格式图片</font>
				</dd>
			</dl>
		</p>
		<c:if test="${find.type=='IMAGETEXT'}">
			<p>
				<label>图片宽度</label>
				<input id="imgWidth" name="imgWidth" value="${find.imgWidth}" type="text"  class="validate[required,minSize[1],maxSize[100]] required"/> 
			</p>
			<p>
				<label>图片宽度</label>
				<input  id="imgHeight" name="imgHeight" value="${find.imgHeight}" class="validate[required,minSize[1],maxSize[100]] required"/>
							
			</p>
		</c:if>
		<c:if test="${find.type=='ACTIVITY'}">
			<p>
				<label>活动id</label>
				<input  id="activityId" name="activityId"  value="${find.activityId}" type="text" class="validate[required,minSize[1],maxSize[100]] required"/>
							
			</p>
		</c:if>
		
		<p>
			<label>开始时间：</label>
			<input type="text" name="showStartTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" value="${find.showStartTime}" readonly="true"  />
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
		<p>
			<label>结束时间：</label>
			<input type="text" name="showEndTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" value="${find.showEndTime}" readonly="true" />
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
	
		<c:if test="${find.type=='IMAGETEXT'}">
			<p>
				<label>URL跳转链接：</label>
				<input type="text" name="getUrl"  value="${find.getUrl}"  class="validate[required,minSize[1],maxSize[200]] required"/>
				<font color="red"><span>以http://开头</span></font>
			</p>
		</c:if>
		<p>
			<label>标题</label>
			<input id="title" name="title" type="text" value="${find.title}"  class="validate[required,minSize[1],maxSize[100]] required"/> 
		</p>
		<p>
			<label>文字描述</label>
			<textarea  id="description" name="description"  class="validate[required,minSize[1],maxSize[100]] required">${find.description}</textarea>
						
		</p>
		<c:if test="${find.type=='ACTIVITY'}">
			<p>
				<label>专题副标题</label>
				<textarea  id="subTitle" name="subTitle"  class="validate[required,minSize[1],maxSize[100]] required">${find.subTitle}</textarea>
							
			</p>
		</c:if>
		<input id="type" name="type" type="hidden" value="${find.type}"/> 
		<input id="mobilePic" name="mobilePic" type="hidden" value="${find.mobilePic}"/> 
		<input id="iphonePic" name="iphonePic" type="hidden" value="${find.iphonePic}"/> 
		<input id="shareUrl" name="shareUrl" type="hidden" value="${find.shareUrl}"/> 
		<input id="startTime" name="startTime" type="hidden" value="${find.startTime}"/> 
		<input id="endTime" name="endTime" type="hidden" value="${find.endTime}"/> 
		<input id="sortBy" name="sortBy" type="hidden" value="${find.sortBy}"/>
		</div>		
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>