<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
  口令红包
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/share.css${ver}"	rel="stylesheet" />
	<c:choose>
		<c:when test="${star eq '1' }">
			<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/red_26/packet_37.css${ver}"	rel="stylesheet" />
		</c:when>
		<c:otherwise>
			<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/red_26/packet.css${ver}" rel="stylesheet" />
		</c:otherwise>
	</c:choose>
</rapid:override>
<rapid:override name="content">
   <input type="hidden" name="star"  id="star"  value="${star}"/>
 <%-- 加此标签是为了获取会场第一张图片，app分享用的 ，不会-对样式造成影响--%>
 	<c:choose>
		<c:when test="${star eq '1' }">
			<c:if test="${checkAPP}">
			  <div class="shang_share"> 
			         <a href="shangpinapp://phone.shangpin/actiongoshare?title=尚品网女神节现金红包等你抢&url=http://m.shangpin.com/star/red&desc=关注微博@尚品网 ，领取口令抢红包&imgurl='${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_37/37.jpg'"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
			  </div>
			</c:if>
		   <input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/star/red"/>
		   <input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
		   <input type="hidden" name="_mainTitle"  id="_mainTitle"  value="尚品网女神节现金红包等你抢"/>
		   <input type="hidden" name="_mainDesc"  id="_mainDesc"  value="关注微博@尚品网 ，领取口令抢红包"/>
		   <input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_37/37.jpg"/>
		</c:when>
		<c:otherwise>
		   <c:if test="${checkAPP}">
			  <div class="shang_share"> 
			         <a href="shangpinapp://phone.shangpin/actiongoshare?title=AngelaBaby生日红包等你来抢！&url=http://m.shangpin.com/star/red/index&desc=关注微博@赵世诚 领取口令抢红包！&imgurl='${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_26/ab.jpg'"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
			  </div>
		   </c:if>
		   <input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/star/red/index"/>
		   <input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
		   <input type="hidden" name="_mainTitle"  id="_mainTitle"  value="AngelaBaby生日红包等你来抢！"/>
		   <input type="hidden" name="_mainDesc"  id="_mainDesc"  value="关注微博@赵世诚 领取口令抢红包！"/>
		   <input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_26/ab.jpg"/>
		</c:otherwise>
	</c:choose>
<c:choose>
  <c:when test="${star eq '1' }">
      <%@ include file="/WEB-INF/pages/star/226/index_37.jsp"%>
  </c:when>
  <c:otherwise>
       <div class="wapper">
		  <!--内容区域 start-->
		  <section class="main">
		  	 <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_26/img01.jpg" >
		     <div class="packet_box">
		     	<input type="text" value="输口令抢红包" class="passwordInput" id="password">
		        <div class="sure_btn">确定</div>
		     </div>
		     <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_26/img02.jpg" >
		  </section>
		  <!--内容区域 start-->
		
		  
		  <div id="popup_overlay"></div>
		  <div id="popup_container">
		      <div id="popup_message">口令输入有误</div>
		  </div>
		
		  
		</div>
  </c:otherwise>
</c:choose>
</rapid:override>
<rapid:override name="static_file">
    <script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/jquery.min.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/red_26/packet.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/star_red_packet_base.jsp" %> 