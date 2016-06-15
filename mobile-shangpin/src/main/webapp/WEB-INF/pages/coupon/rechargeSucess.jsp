<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftCard_send3.css${ver}" rel="stylesheet" />
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
</rapid:override>
<rapid:override name="page_title">
 <c:choose>
	<c:when test="${type eq '0'}">
	  现金卷   
	</c:when> 
	<c:otherwise>
	 优惠劵
	</c:otherwise>
 </c:choose>
</rapid:override>
<rapid:override name="content">
	<div class="results-box coupon-box">
    	<i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/giftcard/recharge_success_icon.png" /></i>
        <h2>
          <c:choose>
			<c:when test="${type eq '0'}">
			  现金卷已经成功充值到您的账户中
			</c:when> 
			<c:otherwise>
			 优惠劵已经成功充值到您的账户中
			</c:otherwise>
		 </c:choose>
        </h2>
        <div class="btn-icon clr">
        	<a href="${ctx }/coupon/list">查看优惠券</a>
            <a href="${ctx }/index" class="bg_btn">立即购物</a>            
        </div>  
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