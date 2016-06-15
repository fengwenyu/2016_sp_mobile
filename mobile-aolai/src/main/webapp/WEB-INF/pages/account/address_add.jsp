<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%String path = request.getContextPath();%>
<!doctype html>
<html lang="zh-CN">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>尚品奥莱-触屏版</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/account.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%>/images/logo/loading.png">

<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?" + Math.random();
		loader = SP.core
			.install("<%=path%>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/comm.js" + ver)
			.using("<%=path%>/js/account.js" + ver)
			.excute(function(){
				isHome(false);
				//保存收货人地址成功后，清空表单内容
				if('${msg}'=='保存收货人地址成功'){
					$(':input','#J_AddrForm')
					.not(':button, :submit, :reset, :hidden')  
					.val('')
					//.removeAttr('checked')  
					.removeAttr('selected');
				}
				if($("#cdid").val()!=""){
					$(".add_new").html("修改收货地址");
				}else{
					$(".add_new").html("新增收货地址");
				}
				var ch=getCookie("ch");
				<s:if test="platform=='android'||ch=='100001'">
					JSCallback.onPageLoaded();
				</s:if>
			});
		 $(document).ready(function(){
			cascadeStage($("#J_AddrProvince"),$("#J_AddrCity"),$("#J_AddrArea"),$("#J_AddrTown"));
		 });
</script>

</head>

<body>

<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/">首页</a></li>
    <li><a href="<%=path%>/accountaction!info" title="">我的账户</a></li>
    <li>地址管理</li>
  </ul>
</nav>

<div class="alContent">
  
  <div class="address_con">
      <h4 class="add_new">新增收货地址</h4>
      
      <ul class="add_cont">
          <li id="msg" style="padding:0; color:#b51111; text-align:center; line-height:25px;">${msg}</li>
          <form name="addr" id="J_AddrForm" action="<%=path%>/accountaction!saveaddress" method="post">
          <li>
              <span>收货人：</span>
              <input type="text" name="consigneeAddressVO.name" value="${consigneeAddressVO.name}" class="ac_txt" id="J_AddrName" maxlength="20">
              <input type="hidden" name="consigneeAddressVO.id" value="${consigneeAddressVO.id}" id="cdid">
          </li>
          <li>
              <span>省份：</span>
              <select name="consigneeAddressVO.province" id="J_AddrProvince">
                  <option value="-1">请选择省份</option>
                  <s:iterator value="provinceList" id="provinceVO">
                    <option value="${provinceVO.id }" <s:if test="#provinceVO.id == consigneeAddressVO.province">selected="selected"</s:if> >${provinceVO.name }</option>
                  </s:iterator>
              </select>
          </li>
          <li>
              <span>城市：</span>
              <select name="consigneeAddressVO.city" id="J_AddrCity">
                  <option value="-1">请选择城市</option>
                  <s:iterator value="cityList" id="cityVO">
                    <option value="${cityVO.id }" <s:if test="#cityVO.id == consigneeAddressVO.city">selected="selected"</s:if> >${cityVO.name }</option>
                  </s:iterator>
              </select>
          </li>
         
          <li>
               <span>地区：</span>
               <select name="consigneeAddressVO.area" id="J_AddrArea">
                  <option value="-1">请选择地区</option>
				  <s:iterator value="areaList" id="areaVO">
                    <option value="${areaVO.id }" <s:if test="#areaVO.id == consigneeAddressVO.area">selected="selected"</s:if> >${areaVO.name }</option>
                  </s:iterator>
              </select>
          </li>
            <li>
               <span>街道：</span>
               <select name="consigneeAddressVO.town" id="J_AddrTown">
                  <option value="-1">请选择地区</option>
				  <s:iterator value="townList" id="townVO">
                    <option value="${townVO.id }"  <s:if test="#townVO.id == consigneeAddressVO.town">selected="selected"</s:if> >${townVO.name }</option>
                  </s:iterator>
              </select>
          </li>
          <li>
              <span>手机号码：</span>
              <input type="tel" name="consigneeAddressVO.tel" value="${consigneeAddressVO.tel}" class="ac_txt" id="J_AddrMobile" maxlength="11">
          </li>
          <li>
              <span>邮政编码：</span>
              <input type="tel" name="consigneeAddressVO.postcode" value="${consigneeAddressVO.postcode}" class="ac_txt" id="J_AddrCode" maxlength="6">
          </li>
          <li>
              <span>详细地址：</span>
              <input type="text" name="consigneeAddressVO.addr" value="${consigneeAddressVO.addr}" class="ac_txt long_txt" id="J_AddrText">
          </li>
            
          <li>
              <span>设为默认：</span>
              <input type="checkbox" name="consigneeAddressVO.isd" id="J_AddrDefault" <s:if test='consigneeAddressVO.isd == "1"'>checked</s:if> >
          </li>

          <li class="addr_errorMsg"></li>
          <li>
              <p class="txt_center">
                <input type="submit" class="alList_submitBtn" value="确认" onclick="$('#msg').hide();"/>
                <a href="<%=path%>/accountaction!addresslist" class="alList_moreBtn">取消</a>
              </p>
          </li>
          </form>
      </ul>
  </div>

</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
