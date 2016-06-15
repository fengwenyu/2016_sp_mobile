<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css" rel="stylesheet" />
	<link rel="stylesheet" href="${ctx}/styles/shangpin/css/page/font-css/font-awesome.min.css"/>	
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/fashion.css" rel="stylesheet" />

	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.floatCalculate.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.appDialogs.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/tabslider.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/fashion.js${ver}");  
				
	</script>
</rapid:override >
<rapid:override name="page_title">
	潮流趋势
</rapid:override>
<rapid:override name="content">
	<div class="alContent">
	 <%-- 分享文案 图片尺寸待定--%> 
		 <c:if test="${fashionDetail.share!=null}">
		 		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${fashionDetail.share.url}"/>
		 		<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="${fashionDetail.share.title}"/>
				 <input type="hidden" name="_mainDesc"  id="_mainDesc"  value=" ${fashionDetail.share.desc}"/>
				 <input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${fn:replace(fashionDetail.share.pic,'-10-10','-600-758') }"/>
		 </c:if>
	   <!--尚潮流详情页--> 
	       <div class="fashion_info clr">
	                <h2>${fashionDetail.name }</h2>
	                <p class="time">${fashionDetail.pubTime } </p>
	                <p class="source">来源：${fashionDetail.source }</p>
	                <section class="fashion_info_txt">
	                  ${fashionDetail.article }
	                </section>
	        </div>
	        <c:choose>
		       	<c:when test="${checkAPP}">
			       	<a  class="more-btn" href="ShangPinApp://phone.shangpin/actiongowebview?title=潮流趋势&url=http://m.shangpin.com/fashion/list?type=1">
	         	</c:when>
	         	<c:otherwise>
	           		<a href="${ctx }/fashion/list?type=1" class="more-btn">
	         	</c:otherwise>
          </c:choose>
          	全部潮流资讯
		  </a>
	     <!--分享按钮--> 
	    
	</div>
<c:if test="${checkAPP}">
  <div class="shang_share"> 
         <a href="shangpinapp://phone.shangpin/actiongoshare?title=${fashionDetail.share.title}&url=${fn:replace(fashionDetail.share.url,'&','8uuuuu8')}&desc=${fashionDetail.share.desc}&imgurl=${fn:replace(fashionDetail.share.pic,'-10-10','-600-758') }"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
   </div>
</c:if>
</rapid:override>

<rapid:override name="statistics">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp" %> 