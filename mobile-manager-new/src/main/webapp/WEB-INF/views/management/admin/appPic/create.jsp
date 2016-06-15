<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<script type="text/javascript">
	function t(obj) {
		var u = $("input[name='shareUrl']");
		var t = $("input[name='shareTitle']");
		var d = $("textarea[name='description']");
		if (obj.value == 'share') {
			u.prop("class", "validate[required,maxSize[200]] required");
			t.prop("class", "validate[required,maxSize[100]] required");
			d.prop("class", "validate[required,maxSize[255]] required");
		} else {
			u.removeAttr('class');
			t.removeAttr('class');
			d.removeAttr('class');
		}
		return validateCallback(obj, nt)
	}
</script>
<style type="text/css" media="screen">
.my-uploadify-button {
	background:none;
	border: none;
	text-shadow: none;
	border-radius:0;
	overflow: auto;
}

.uploadify:hover .my-uploadify-button {
	background:none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 150px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>
<div class="pageContent">
	<form action="${contextPath }/management/admin/appPic/create"
		method="post" class="required-validate pageForm"
		onsubmit="return validateCallback(this,navTabReloadParent)">
		<div class="pageFormContent" layoutH="58">
		
		<div id="preImg"></div>
		<div><p><input id="iu" type="text" name="showUrl" size="56" class="validate[required] required"/>
		<input id="iul" type="hidden" name="imageUrl"/>
		<input id="imgw" type="hidden" name="imageWidth"/>
		<input id="imgh" type="hidden" name="imageHeight"/>
		</p></div>
			<input id="file_upload" type="file"
	uploaderOption="{
        'successTimeout':300,
        'swf':'${contextPath}/styles/uploadify/scripts/uploadify.swf',
        'overrideEvents' : ['onDialogClose'],
        'queueID':'fileQueue',
        'fileObjName':'files',
        'uploader':'${contextPath}/management/admin/appPic/upload;jsessionid=<%=session.getId()%>',
        'fileTypeDesc':'支持的格式：',
        'buttonImage':'${contextPath}/styles/uploadify/img/add-1.jpg',
		'buttonClass':'my-uploadify-button',
        'width':'102',
        'height':'28',
         'fileTypeExts': '*.jpg',
        'fileSizeLimit':'1MB',
        'queueSizeLimit' : 0,
        'onSelectError':function(file, errorCode, errorMsg){
            switch(errorCode) {
                case -100:
                    alert('上传的文件数量已经超出系统限制的'+$('#file_upload').uploadify('settings','queueSizeLimit')+'个文件！');
                    break;
                case -110:
                    alert('文件 ['+file.name+'] 大小超出系统限制的'+$('#file_upload').uploadify('settings','fileSizeLimit')+'大小！');
                    break;
                case -120:
                    alert('文件 ['+file.name+'] 大小异常！');
                    break;
                case -130:
                    alert('文件 ['+file.name+'] 类型不正确！');
                    break;
            }
        },
        'onFallback':function(){
            alert('您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。');
        },
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
        	if (errorCode == 403) {
        		forbidden();
        	}
            alert(file.name + '上传失败: ' + errorMsg + errorString);
        },
        'onUploadSuccess':function(file,data,response){
        	if(!data){
        		alert('上传图片失败!');
       			return ;
       			}
        	//1.解析data值 ，取出服务器图片url地址及宽度 长度
       		var d=jQuery.parseJSON(data);
        	var imgUrl=d.content.PicUrl;
        	var imgWidth=d.imgWidth;
        	var imgHeight=d.imgHeight;
           //2.在图片div中添加一个图片并设置其宽度及长度
           $('#preImg img').remove();
           	var il= imgUrl.replace('{w}',imgWidth);
          	var iul=il.replace('{h}',imgHeight);
           var imgPre=$('<img src='+iul+' width='+imgWidth+' height='+imgHeight+'/>');
           $('#preImg').append(imgPre);
           //3.把图片URL放入input中并防止修改并复制其url值到隐藏域中
             $('#iu').val(iul);
             $('#iu').attr('readonly','readonly');
           //4.设置图片宽度及高度到隐藏域中
           $('#iul').val(imgUrl);
           $('#imgw').val(imgWidth);
           $('#imgh').val(imgHeight);
	        }
	}"/>
			<div id="fileQueue" ></div>
			<div><font color="red">1.上传的.jpg后缀的图片<br/>2.OS类型为Andriod图片尺寸必须是 480*800
	其他OS类型图片尺寸宽度必须为640px</font>
			</div>
	<dl>
		<dt>客户端：</dt>
		<dd>
			<select name="shopType"><option
						value="1" selected>尚品 &nbsp;&nbsp;&nbsp;</option>
					<option value="2">奥莱</option></select>
		</dd>
	</dl>	
	<dl>
		<dt>OS类型：</dt>
		<dd>
			<select name="osType"
					class="validate[required] required"><option value="">请选择&nbsp;&nbsp;</option>
					<option value="iphone4">iphone4</option>
					<option value="iphone5">iphone5</option>
					<option value="andriod">andriod</option>
					<option value="ipad">ipad</option>
				</select>
		</dd>
	</dl>	
	<dl>
		<dt>用途：</dt>
		<dd>
			<select name="uses" onchange="t(this)"><option
						value="start" selected>启动图&nbsp;&nbsp;</option>
					<option value="share">分享图</option></select>
		</dd>
	</dl>	
	<dl>
	<font color="red">用途为：分享图 则需填写分享链接和标题以及文字描述，其他可不填</font>
	</dl>	
	<dl>
		<dt>分享链接：</dt>
		<dd>
			<input type="text" name="shareUrl" size="20"
					maxlength="200" />
		</dd>
	</dl>	
	<dl>
		<dt>分享标题：</dt>
		<dd>
			<input type="text" name="shareTitle" size="20"
					maxlength="100" />
		</dd>
	</dl>	
	<dl>
		<dt>文字描述：</dt>
		<dd>
			<textarea name="description" rows="3" cols="50" maxlength="255"></textarea>
		</dd>
	</dl>	
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="submit">确定</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>
