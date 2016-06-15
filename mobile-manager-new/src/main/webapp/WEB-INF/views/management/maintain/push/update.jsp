<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<script type="text/javascript">
(function(){
	var value = "${push.actionType}";
	$(".combox option[value = " + value + "]").attr("selected",true);
	switch(value){
	case 'link':
		$('#url').show();
		$('#topicid').hide();
		$('#actid').hide();
		$('#productid').hide();
		$('#productSource').hide();
		$('#categoryid').hide();
		$('#openurl').hide();
		$('#otherurl').hide();
		break;
	case 'topic':
		$('#topicid').show();
		$('#url').hide();
		$('#actid').hide();
		$('#productid').hide();
		$('#productSource').hide();
		$('#categoryid').hide();
		$('#openurl').hide();
		$('#otherurl').hide();
		break;
	case 'activity':
		$('#topicid').hide();
		$('#url').hide();
		$('#actid').show();
		$('#productid').hide();
		$('#productSource').hide();
		$('#categoryid').hide();
		$('#openurl').hide();
		$('#otherurl').hide();
		break;
	case 'detail':
		$('#topicid').hide();
		$('#url').hide();
		$('#actid').hide();
		$('#productid').show();
		$('#productSource').show();
		$('#categoryid').show();
		$('#openurl').hide();
		$('#otherurl').hide();
		break;
	case 'openapp':
		$('#topicid').hide();
		$('#url').hide();
		$('#actid').hide();
		$('#productid').hide();
		$('#productSource').hide();
		$('#categoryid').hide();
		$('#openurl').show();
		$('#otherurl').show();
		break;
	default:
		$('#topicid').hide();
		$('#url').hide();
		$('#actid').hide();
		$('#productid').hide();
		$('#productSource').hide();
		$('#categoryid').hide();
		$('#openurl').hide();
		$('#otherurl').hide();
	}	
})(jQuery);
</script>
<div class="pageContent">
<form method="post" action="${contextPath }/management/maintain/push/update" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<input type="hidden" name="id" value="${push.id}"/>
	<div class="pageFormContent" layoutH="58">
		<p>
			<label>产品编号：</label>
			<input type="text" name="productCode" value="${push.productCode}" class="validate[required] required"/>
			<a class="btnLook" href="${contextPath }/management/security/user/lookup2org" lookupGroup="organization" title="关联组织" width="400">查找带回</a>
		</p>
		<p>
			<label>渠道编号：</label>
			<input type="text" name="channelCode" value="${push.productCode}" class="validate[required] required"/>
			<a class="btnLook" href="${contextPath }/management/security/user/lookup2org" lookupGroup="organization" title="关联组织" width="400">查找带回</a>
		</p>		
		<p>
			<label>性别：</label>
			<c:choose>
				<c:when test="${push.sex == 0}">
					<input type="radio" name="sex" value="0" checked="checked"/>男
					<input type="radio" name="sex" value="1"/>女
				</c:when>
				<c:otherwise>
					<input type="radio" name="sex" value="0"/>男
					<input type="radio" name="sex" value="1" checked="checked"/>女
				</c:otherwise>
			</c:choose>
		</p>
		<p id="title">
			<label>标题：</label>
			<input type="text" name="title" value="${push.title}" class="validate[required] required"/>
		</p>
		<p>
			<label>用户名：</label>
			<input type="text" name="username" value="${push.username}"/>
		</p>
		<p>
			<label>开始时间：</label>
			<input type="text" name="startTime" value="${push.startTime}" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true"/>
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
		<p>
			<label>结束时间：</label>
			<input type="text" name="endTime" value="${push.endTime}" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true"/>
			<a class="inputDateButton" href="javascript:;">选择</a>
		</p>
		<p>
			<label>处理事件：</label>
			<select class="combox" name="actionType" ref="w_combox_type">
				<option value="">请选择</option>
				<option value=link>用浏览器打开链接</option>
				<option value="topic">直接进入专题</option>
				<option value="activity">直接进入活动</option>
				<option value="detail">直接进入详情页</option>
				<option value="openapp">打开其他应用</option>
			</select>
		</p>		
		<p id="url">
			<label>URL超链接：</label>
			<input type="text" name="pushContent.url" value="${url}" class="validate[required,custom[url]] required"/>
			<font color="red"><span>以http://开头</span></font>
		</p>
		<p id="topicid">
			<label>专题编号：</label>
			<input type="text" name="pushContent.topicid" value="${topic}"/>
		</p>
		<p id="actid">
			<label>活动编号：</label>
			<input type="text" name="pushContent.actid" value="${activity}" class="validate[required] required"/>
		</p>
		<p id="productid">
			<label>产品编号：</label>
			<input type="text" name="pushContent.productid" value="${goodsid}" class="validate[required] required"/>
		</p>
		<p id="productSource">
			<label>商品来源：</label>
			<input type="text" name="pushContent.productSource" value="${type}" class="validate[required] required"/>
		</p>
		<p id="categoryid">
			<label>子类编号：</label>
			<input type="text" name="pushContent.categoryid" value="${categoryno}" class="validate[required] required"/>
		</p>
		<p id="openurl">
			<label>应用打开地址：</label>
			<input type="text" name="pushContent.openurl" value="${openurl}" class="validate[required] required"/>
		</p>
		<p id="otherurl">
			<label>其他应用下载地址：</label>
			<input type="text" name="pushContent.otherurl value="${otherurl}" class="validate[required] required"/>
		</p>
		<p>
			<label>公告内容：</label>
			<textarea name="notice" rows="8" cols="30">${push.notice}</textarea>
		</p>
	</div>
			
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>