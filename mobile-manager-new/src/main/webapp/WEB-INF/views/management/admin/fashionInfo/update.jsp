<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<script type="text/javascript">
function forbidden() {
	dialogAjaxDone('{"statusCode":"403", "message":"用户权限不足。", "navTabId":"", "forwardUrl":"", "callbackType":"closeCurrent"}');
} 

	$(function() {
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

		//表单提交前操作
		function validateCallback(form, callback, confirmMsg) {
			var $form = $(form);
			if (!$form.validationEngine('validate')) {
				return false;
			}
			
			$form.append('<input type="hidden" id="content" name="content" value="" />');
			var content = CKEDITOR.instances.ckeditor.getData();
			$('#content').attr('value', content);
			
			var _submitFn = function(){
				$.ajax({
					type: form.method || 'POST',
					url:$form.attr("action"),
					data:$form.serializeArray(),
					dataType:"json",
					cache: false,
					success: callback || DWZ.ajaxDone,
					error: DWZ.ajaxError
				});
			}
			
			if (confirmMsg) {
				alertMsg.confirm(confirmMsg, {okCall: _submitFn});
			} else {
				_submitFn();
			}
			
			return false;
		}
</script>
<div class="pageContent">
<form method="post" action="${contextPath}/management/admin/fashionInfo/update" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<input type="hidden" name="id" value="${apiFashionInfo.id}"/>
	<div class="pageFormContent" layoutH="58">
		<p>
			<label>标题：</label>
			<input type="text" value="${apiFashionInfo.title}" name="title" class="validate[required,minSize[1],maxSize[50]] required" size="30" maxlength="51"/>
			<!-- 
			<span class="info">限制50汉字</span>
			 -->
		</p>		
		<p>
			<label>作者：</label>
			<input type="text" value="${apiFashionInfo.author}" name="author" class="validate[maxSize[20]]" size="20" maxlength="21" id="author"/>
		</p>
		<p>
			<label>摘要：</label>
			<div>
				<textarea rows="5" cols="50" name="digest" class="validate[maxSize[100]]" maxlength="101" style="text-align: left;">${apiFashionInfo.digest}</textarea>
			</div>
		</p>

		<p>
			<label>发布时间：</label>
			<input type="text" value="${apiFashionInfo.releaseTime}" name="releaseTime" class="date" readonly="readonly" style="float:left;"/>
			<a class="inputDateButton" href="javascript:;" style="float:left;">选择</a>
		</p>
		<c:if test="${ !empty apiFashionInfo.imgUrl}">
			<p>
			<dl style="height: 120px">
				<dt>原封面图：</dt>
			 	<dd>
			 		<input type="hidden" name="imgUrl" value="${apiFashionInfo.imgUrl}" id="imgUrlInput"/>
					<img id="" src="${apiFashionInfo.imgUrl}" width="120px" height="120px" />
				</dd>
			</dl>
			</p>
			<p>
			<font color="red">注意：如果上传新的图片将覆盖原有图片！</font>
		</c:if>
		<dl style="height: 120px">
				<dt>封面图：</dt>
				<dd>
					<input type="hidden" name="imgUrl" value="" id="imgUrlInput2"/>
					<img id="headImg" src="" width="120px" height="120px"/>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>上传logo：</dt>
				<dd>
					<input id="file_upload" type="file" name="image"
						uploaderOption="{
		        'auto':true,
		        'successTimeout':300,
		        'swf':'${contextPath}/styles/uploadify/scripts/uploadify.swf',
		        'overrideEvents' : ['onDialogClose'],
		        'queueID':'fileQueue',
		        'fileObjName':'file',
		        'uploader':'${contextPath}/management/admin/fashionInfo/upload;jsessionid=<%=session.getId()%>',
		        'buttonText':'图片浏览...',
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
		        	$('#imgUrlInput2').val(src);
		        	$('input').remove('#imgUrlInput');
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
		<dl class="nowrap">
			<dt>资讯内容：</dt>
			<dd>
				<textarea class="textInput" cols="55" rows="5" name="ckeditor" >${apiFashionInfo.content}</textarea>
				<script type="text/javascript">

					CKEDITOR.replace('ckeditor');
				</script>
			</dd>
		</dl>
	</div>
			
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>