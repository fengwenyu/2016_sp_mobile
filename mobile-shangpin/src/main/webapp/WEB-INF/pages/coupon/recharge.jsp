<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftCard_send3.css${ver}" rel="stylesheet" />
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/giftcard/recharge.js${ver}"  type="text/javascript" charset="utf-8"></script>
</rapid:override>
<rapid:override name="page_title">
 <c:choose>
	<c:when test="${coupon.type eq '0'}">
	  现金卷	   
	</c:when> 
	<c:otherwise>
	优惠劵 
	</c:otherwise>
 </c:choose>
</rapid:override>
<rapid:override name="headApp">

</rapid:override>
<rapid:override name="content">
	<div class="results-box coupon-box" style="padding-top:14px;">
    	<!--<p class="coupon-list-box">
        	<img src="/images/giftcard/detail/coupon_img01.jpg" />
        </p>-->
        
        <ul class="coupon_list select_coupon">
           <c:if test="${coupon.type == '0' }">
            <li class="cash">
                <h4><img src="${ctx}/styles/shangpin/images/order/cash_coupon_angle.png" width="69" height="121" /></h4>
                <div class="cash">
                    <i>&yen;${coupon.amount}</i>
                    <em>${coupon.name }</em>
                    <span>
                      <strong>${coupon.expiredate }</strong>
                     ${coupon.desc }
                    </span>
                </div>
                <p><img src="${ctx}/styles/shangpin/images/order/cash_coupon.png" width="69" height="121" /></p>
            </li>
            </c:if>
            <c:if test="${coupon.type == '1' }">
            <li class="sale">
                <h4><img src="${ctx}/styles/shangpin/images/order/coupon_angle.png" width="69" height="121" /></h4>
                <div class="cash">
                    <i>&yen;${coupon.amount}</i>
                    <em>${coupon.name}</em>
                    <span>
                      <strong>${coupon.expiredate }</strong>
                       ${coupon.desc }
                    </span>
                </div>
                <p><img src="${ctx}/styles/shangpin/images/order/coupon.png" width="69" height="121" /></p>
            </li>
            </c:if>
        </ul>
        
        <div class="btn-icon clr">
        	<a href="javascript:;"class="bg_btn"  style="width:120px;float:none;margin:auto" id="recharge">充值</a> 
            <form id="coupon" action="${ctx }/coupon/recharge" method="post">
              <input type="hidden" name="cardNo" id="cardNo" value="${value}" />  
              <input type="hidden" name="bId" id="bId" value="${bId}" /> 
              <input type="hidden" name="type" id="type" value="${coupon.type}" />    
            </form>   
        </div>  
         <input type="hidden" name="msg" id="msg" value="${msg}" />    
        <span class="title_tip">优惠券使用说明：</span> 
        <p>1.优惠券是尚品网发放的现金抵用券；<br />
            2.优惠券不记名、不挂失、不可兑换现金，请妥善保管；<br />
            3.优惠券必须充值到尚品网的会员账户中方可使用，充值成功后，您可在“我的尚品-优惠券”列表中进行查看；<br />
            4.您在尚品网购物，在“提交订单”页面选中该现金券，可直接进行抵现，现金券不找零。
        </p>    
    </div>
</rapid:override>
<rapid:override name="down_page">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/common_mall_home_banner.jsp" %> 