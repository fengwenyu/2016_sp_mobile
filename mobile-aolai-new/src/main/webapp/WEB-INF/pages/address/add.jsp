<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/account.css" rel="stylesheet" />
	<script src="${cdn:css(pageContext.request)}/styles/shangpin/js/public.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/account.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/address/add.js${ver}")
		.excute();
	</script>
</rapid:override > 

<rapid:override name="content">
<div class="alContent">
  <c:import url="/nav?navId=11"></c:import>
  <div class="address_con">
      <h4 class="add_new">新增收货地址</h4>
      <ul class="add_cont">
          <li id="msg" style="padding:0; color:#b51111; text-align:center; line-height:25px;">${msg}</li>
          <form name="addr" id="J_AddrForm" action="save" method="post">
          <li>
              <span>收货人：</span>
              <input type="text" name="name" class="ac_txt" id="J_AddrName" maxlength="20">
          </li>
          <li>
              <span>省份：</span>
              <select name="province" id="J_AddrProvince">
                  <option value="-1">请选择省份</option>
                  <c:forEach var="province" items="${provinceList }" >
                  	<option value="${province.id }">${province.name }</option>
                  </c:forEach>
              </select>
          </li>
          <li>
              <span>城市：</span>
              <select name="city" id="J_AddrCity">
                  <option value="-1">请选择城市</option>
              </select>
          </li>
          <li>
               <span>地区：</span>
               <select name="area" id="J_AddrArea">
                  <option value="-1">请选择地区</option>
              </select>
          </li>
           <li>
               <span>街道：</span>
               <select name="town" id="J_AddrTown">
                  <option value="-1">请选择街道</option>
              </select>
          </li>
          <li>
              <span>手机号码：</span>
              <input type="text" name="tel" class="ac_txt" id="J_AddrMobile" maxlength="11">
          </li>
          <li>
              <span>邮政编码：</span>
              <input type="text" name="postcode" class="ac_txt" id="J_AddrCode" maxlength="6">
          </li>
          <li>
              <span>详细地址：</span>
              <input type="text" name="addr" class="ac_txt long_txt" id="J_AddrText">
          </li>
            
          <li>
              <span>设为默认：</span>
              <input type="checkbox" name="isd" id="J_AddrDefault" >
          </li>

          <li class="addr_errorMsg"></li>
          <li>
              <p class="txt_center">
                <input type="submit" class="alList_submitBtn" value="确认" onclick="$('#msg').hide();"/>
                <a href="${ctx }/user/address/list" class="alList_moreBtn">取消</a>
              </p>
          </li>
          </form>
      </ul>
  </div>

</div>

</rapid:override>

<%@ include file="/WEB-INF/pages/common/base.jsp" %> 
