<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/freebie/all_share_present.css${ver}" rel="stylesheet" />
	<script type="text/javascript">window.bd && bd._qdc && bd._qdc.init({app_id: '75feae525068fb2bec34e48e'});</script>
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/fixed_public.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/520freebie/shareList.js${ver}")
				.excute(function(){
				$(window).scroll(function(){
					topFixed('.topFix');
				});	
			});
	</script>
</rapid:override >
<rapid:override name="title">
    送出同款撞衫
</rapid:override>
<rapid:override name="downloadAppShowBottom">
</rapid:override>
<rapid:override name="page_title">
	送出同款撞衫
</rapid:override>
<rapid:override name="appLayer">
</rapid:override>
<rapid:override name="content">
<div class="alContent">
<div class="share" style="display: none">
	    	 <input id ="shareTitle" type="hidden" readonly="readonly" value="${shareTitle}"> 
	    	 <input id ="shareDesc" type="hidden" readonly="readonly" value="${shareDesc}"> 
	    	 <input id ="sharePic" type="hidden" readonly="readonly" value="${sharePic}"> 
	    
	    	 <input id ="checkAPP" type="hidden" readonly="readonly" value="${checkAPP}"> 
    	</div>
    	<div class="present_box">  
    	<c:forEach var="order" items="${orderList }" > 
        	<div class="present_list">
            	<div class="show_img">
                	<img src="${order.pic }" >
                </div>
                <div class="show_text">
                    <h3> ${order.productName}</h3>
                    <input class ="shareUrl" type="hidden" readonly="readonly" value="${order.refContent}"> 
                    <c:choose>
                    	<c:when test="${order.refContent != null}">
		                    <a href="javascript:" class="share_link" onclick="share(this)">送出同款撞衫</a>
                    	</c:when>
                    	<c:otherwise>
                    		<a href="javascript:" class="share_link disabel">已领取</a>
                    	</c:otherwise>
                    </c:choose>
                </div> 
        	</div> 
           </c:forEach> 
        </div>
    </div>

</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_meet.jsp"%>