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
<c:import url="/nav?navId=12"></c:import>
<div class="alContent">
  
  <div class="address_con">
      <h4 class="add_new">修改收货地址</h4>
      <ul class="add_cont">
          <li id="msg" style="padding:0; color:#b51111; text-align:center; line-height:25px;">${msg}</li>
          <form name="addr" id="J_AddrForm" action="update" method="post">
          <li>
              <span>收货人：</span>
              <input type="text" name="name" value="${address.name }" class="ac_txt" id="J_AddrName" maxlength="20">
              <input type="hidden" name="id" value="${address.id}" id="cdid">
          </li>
          <li>
              <span>省份：</span>
              <select name="province" id="J_AddrProvince">
                  <option value="-1">请选择省份</option>
                  <c:forEach var="province" items="${provinceList }" >
                  	<option value="${province.id }" <c:if test="${address.province eq province.id }">selected="selected" </c:if>>${province.name }</option>
                  </c:forEach>
              </select>
          </li>
          <li>
              <span>城市：</span>
              <select name="city" id="J_AddrCity">
                  <option value="-1">请选择城市</option>
                   <c:forEach var="city" items="${cityList }" >
                  	<option value="${city.id }" <c:if test="${address.city eq city.id }">selected="selected" </c:if>>${city.name }</option>
                  </c:forEach>
              </select>
          </li>
          <li>
               <span>地区：</span>
               <select name="area" id="J_AddrArea">
                  <option value="-1">请选择地区</option>
                   <c:forEach var="area" items="${areaList }" >
                  	<option value="${area.id }" <c:if test="${address.area eq area.id }">selected="selected" </c:if>>${area.name }</option>
                  </c:forEach>
              </select>
          </li>
          <li>
               <span>街道：</span>
               <select name="town" id="J_AddrTown">
                  <option value="-1">请选择街道</option>
                   <c:forEach var="town" items="${townList }" >
                  	<option value="${town.id }" <c:if test="${address.town eq town.id }">selected="selected" </c:if>>${town.name }</option>
                  </c:forEach>
              </select>
          </li>
          <li>
              <span>手机号码：</span>
              <input type="text" name="tel" value="${address.tel }" class="ac_txt" id="J_AddrMobile" maxlength="11">
          </li>
          <li>
              <span>邮政编码：</span>
              <input type="text" name="postcode" value="${address.postcode }"  class="ac_txt" id="J_AddrCode" maxlength="6">
          </li>
          <li>
              <span>详细地址：</span>
              <input type="text" name="addr" value="${address.addr }" class="ac_txt long_txt" id="J_AddrText">
          </li>
            
          <li>
              <span>设为默认：</span>
              <input type="checkbox" name="isd" id="J_AddrDefault" <c:if test="${address.isd eq \"1\" }"> checked </c:if> >
          </li>

          <li class="addr_errorMsg"></li>
          <li>
              <p class="txt_center">
                <input type="submit" class="alList_submitBtn" value="确认" onclick="$('#msg').hide();"/>
                <!--a href="#" class="alList_submitBtn">确认</a-->
                <a href="${ctx }/user/address/list" class="alList_moreBtn">取消</a>
              </p>
          </li>
          </form>
      </ul>
  </div>

</div>

</rapid:override>

<%@ include file="/WEB-INF/pages/common/base.jsp" %> 
