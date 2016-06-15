<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">

<script type="text/javascript">

    function checkForm(){
    	var versionPattern = /^([0-9]+)([.]{1})([0-9]+)([.]{1})([0-9]+)$/;
    	if(!versionPattern.test($("#versionLatest").val())){
    		alertMsg.error('产品版本号格式不正确!');	
			return false;
		}
    	
    	if(!versionPattern.test($("#versionForceMax").val())){
    		alertMsg.error('强制升级最大版本号格式不正确!');	
			return false;
		}
    	
    	var versionLatest= new Array(); 
    	var versionForceMax= new Array(); 
		versionLatest = $('#versionLatest').val().split('.'); 
		versionForceMax = $('#versionForceMax').val().split('.'); 
		for (i = 0; i < 3; i++){    
			if(parseInt(versionLatest[i])>parseInt(versionForceMax[i])){
				break;
			}else{
				alertMsg.error('版本大小顺序不正确!');	
				return false;	
			}
			} 	
		$("#form").submit();
    }
    
</script>
<form id="form" method="post" action="${contextPath }/management/manager/online/create" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<div class="pageFormContent" layoutH="58">
	<dl>
		<dt>产品号：</dt>
		<dd>
		     <select name="productNum" id="productnum">
			   <c:forEach items="${products}" var="prod">
			     <option value="${prod.productNum}">${prod.productName}</option>
			   </c:forEach>
		     </select>
		</dd>
	</dl>
	<dl>
		<dt>产品版本号：</dt>
		<dd>
			<input type="text" id="versionLatest" name="versionLatest" class="validate[required,maxSize[10]" size="30" maxlength="10" alt="格式：1.1.1"/>
		</dd>
	</dl>	
	<dl>
		<dt>强制升级最大版本号：</dt>
		<dd>
			<input type="text" id="versionForceMax" name="versionForceMax" class="validate[required,maxSize[10]" size="30" maxlength="10" alt="格式：1.1.1"/>
		</dd>
	</dl>
	<div>
		<dt>提示信息：</dt>
		<dd>
		   <textarea rows="8" cols="30" name="prompt"  class="validate[required,maxSize[955]"></textarea>
		</dd>
	</div>	
	<div>
		<dl>
		   <dt>渠道编号:</dt>
	   <dd>
	   <input type="text" name="channelNum"  readonly="readonly"/>
	   <a class="btnLook" href="${contextPath }/management/manager/online/find" suggestFields="id,channelNum" lookupGroup="">查找带回</a>	</dd>
	</dl>
	</div>	
	</div>
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="button" onclick="checkForm()">确定</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>