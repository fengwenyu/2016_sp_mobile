<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%> 

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/new_member.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/homeScroll.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.excute()
	</script>
</rapid:override > 

<%-- 页标题 --%>
<rapid:override name="page_title">
	我的尚品
</rapid:override>

<rapid:override name="content">
	<div class="member_box">
		<c:choose>
			<c:when test="${sessionScope.mshangpin_user.lv == '0002'}">
				<header>	     			
					<img src="${cdn:js(pageContext.request)}/styles/shangpin/images/member/user_gold.png" title="用户头像" />
			</c:when>
			<c:when test="${sessionScope.mshangpin_user.lv == '0003'}">
				<header>
	     			<img src="${cdn:js(pageContext.request)}/styles/shangpin/images/member/user_platinum.png" title="用户头像" />
			</c:when>
			<c:when test="${sessionScope.mshangpin_user.lv == '0004'}">
				<header>
	     			<img src="${cdn:js(pageContext.request)}/styles/shangpin/images/member/user_diamond.png" title="用户头像" />
			</c:when>
			<c:otherwise>
				<header>
	     			<img src="${cdn:js(pageContext.request)}/styles/shangpin/images/member/user_normal.png" title="用户头像" />
			</c:otherwise>
		</c:choose>
	        <h5>${sessionScope.mshangpin_user.name}</h5>
	        <p>
	        	<c:choose>
	        		<c:when test="${sessionScope.mshangpin_user.lv == '0002'}">
	        			黄金会员
	        		</c:when>
	        		<c:when test="${sessionScope.mshangpin_user.lv == '0003'}">
	        			白金会员
	        		</c:when>
	        		<c:when test="${sessionScope.mshangpin_user.lv == '0004'}">
	        			钻石会员
	        		</c:when>
	        		<c:otherwise>
	        			 普通会员
	        		</c:otherwise>
	        	</c:choose>
	        </p>
	    </header>
	    
	    <nav>
	    	<a href="${ctx}/order/list?statusType=2"><i><img src="${cdn:js(pageContext.request)}/styles/shangpin/images/member/member01.png" title="待支付${buyIfo.waitpaycount}" /></i><c:if test="${buyIfo.waitpaycount != '0'&&buyIfo.waitpaycount !=''}"><b>${buyIfo.waitpaycount}</b></c:if>待支付</a>
	        <a href="${ctx}/order/list?statusType=4"><i><img src="${cdn:js(pageContext.request)}/styles/shangpin/images/member/member02.png" title="待发货${buyIfo.preparegoodscount}" /></i><c:if test="${buyIfo.preparegoodscount != '0'&&buyIfo.preparegoodscount !=''}"><b>${buyIfo.preparegoodscount}</b></c:if>待发货</a>
	        <a href="${ctx}/order/list?statusType=3"><i><img src="${cdn:js(pageContext.request)}/styles/shangpin/images/member/member03.png" title="待收货${buyIfo.delivergoodscount }" /></i><c:if test="${buyIfo.delivergoodscount != '0'&&buyIfo.delivergoodscount !=''}"><b>${buyIfo.delivergoodscount}</b></c:if>待收货</a>
	    </nav>
	    
	    <ul class="menu">
	    	<li><a href="${ctx}/order/list?statusType=1" class="order">全部订单</a></li>
	        <li class="line"><a href="${ctx}/coupon/list" class="coupon">优惠券</a></li>
	        <li class="line"><a href="${ctx}/giftCard/recordList" class="giftcard">礼品卡</a></li>
	        <li class="line"><a href="${ctx}/collect/brand/list?pageIndex=1&pageSize=20" class="favorBrand">我喜欢的品牌</a></li>
	        <li><a href="${ctx}/collect/product/list?pageIndex=1&pageSize=20&shopType=1"" class="favorPorduct">我的愿望清单</a></li>
	        <li class="line"><a href="${ctx}/address/list" class="addr">我的收货地址</a></li>
	        <li class="line"><a href="${ctx}/findpwd" class="change">修改密码</a></li>
	        <li><a href="${ctx}/help/index.html" class="help">帮助</a></li>
	    </ul>
	</div>
	
	<rapid:override name="downloadAppShowBottom">
	</rapid:override>
</rapid:override > 

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %>  
