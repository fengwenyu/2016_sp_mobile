<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/swiper.min.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/freebie/topshop_detail.css${ver}" rel="stylesheet" />
	<script type="text/javascript">window.bd && bd._qdc && bd._qdc.init({app_id: '75feae525068fb2bec34e48e'});</script>
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/swiper.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
				/* .using("${cdn:js(pageContext.request)}/styles/shangpin/js/order_address.js${ver}") */
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/product/freebie/topshop_productDetail.js${ver}")
	</script>
</rapid:override >
<rapid:override name="title">
	${productDetail.basic.productName }
</rapid:override>
<rapid:override name="page_title">
	${productDetail.basic.productName }
</rapid:override>
<rapid:override name="appLayer">
</rapid:override>
<rapid:override name="content">
<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${productDetail.share.url}"/>
<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="${productDetail.share.title}${productDetail.share.desc}"/>
<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="${productDetail.share.title}"/>
<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${fn:replace(productDetail.share.pic,'-10-10','-160-218') }"/>
<input type="hidden"  id="proShelves"  value="${productDetail eq null ? 0 :1 }"/>
<input type="hidden"  id="pd"  value="${pd}"/>
<input type="hidden"  id="msg"  value="${msg}"/>
<input type="hidden"  id="codeSt"  value="${codeSt}"/>
<c:import url="basic.jsp"/> 
<c:import url="comment.jsp"/>  
<c:import url="info.jsp"/>
<c:import url="buy.jsp"/>
<c:import url="address.jsp"/>

</rapid:override>

<%@ include file="/WEB-INF/pages/common/base_meet.jsp"%>