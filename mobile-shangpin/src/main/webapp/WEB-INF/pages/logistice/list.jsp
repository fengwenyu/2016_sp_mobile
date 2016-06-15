<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/swiper.min.css${ver}" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${cdn:css(pageContext.request)}/styles/shangpin/css/scrollbar.css${ver}">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/track_order.css${ver}" rel="stylesheet" />
	<script type="text/javascript" src="${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js?"></script>
	<script type="text/javascript" src="${cdn:js(pageContext.request)}/styles/shangpin/js/swiper.min.js"></script>
	<script type="text/javascript" src="${cdn:js(pageContext.request)}/styles/shangpin/js/iscroll.js"></script>

	<script type="text/javascript" src="${cdn:js(pageContext.request)}/styles/shangpin/js/order_tracking.js"></script>
	
	<!-- <script type="text/javascript" charset="utf-8">
			loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/iscroll.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order_tracking.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.floatCalculate.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.appDialogs.js${ver}")
			.excute();
				
	</script> -->
</rapid:override >   

<%-- 浏览器标题 --%>
<rapid:override name="title">
	订单跟踪
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	订单跟踪
</rapid:override>
<rapid:override name="downloadAppShowHead">
</rapid:override>
<rapid:override name="content">
<c:choose>
	<c:when test="${checkAPP}">
		<div id="wrapper" style="top:0px;" >
	</c:when>
	<c:otherwise>
		<div id="wrapper" style="top:45px;" >
	</c:otherwise>
</c:choose>
 
  <div>
  
    
		<div class="alContent" >
		  <section class="order_block">
		    <h2>
		      <span>
		                      订单编号：<b>${orderLogistics.orderId }</b>
		      </span>
		      ${orderLogistics.date }
		    </h2>
		    <c:if test="${orderLogistics.noticeInfo!=null&&orderLogistics.noticeInfo!='' }">
		      <div class="order-prompt"><i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/prompt_img.png" width="26" height="26"></i><span>${orderLogistics.noticeInfo }</span></div>
		 	</c:if>
		   <c:if test="${orderLogistics.list!= null && fn:length(orderLogistics.list) != 0}">
		   <nav>
      		<div class="swiper-container" id="swiper-container1">
              <div class="swiper-wrapper">
             	 <c:forEach var="orderLogistic" items="${orderLogistics.list}" varStatus="st">
		 			<c:choose>  
					   <c:when test="${st.index==orderLogistics.index }">  
					<div class="swiper-slide">	<a href="${ctx }/logistice/list?ticketNo=${orderLogistic.ticketno }&orderId=${orderId }&index=${st.index}&postArea=${postArea}" target="_self" class="cur">包裹${st.index+1 }</a> </div>
					   </c:when>  
					   <c:otherwise>  
					    <div class="swiper-slide"> <a href="${ctx }/logistice/list?ticketNo=${orderLogistic.ticketno }&orderId=${orderId }&index=${st.index}&postArea=${postArea}" target="_self">包裹${st.index+1 }</a></div>
					   </c:otherwise>  
					</c:choose>  
       			 </c:forEach>
              </div>
            </div>
      		 <div class="swiper-container" id="swiper-container2">
              <div class="swiper-wrapper">
       			  <c:forEach var="product" items="${orderLogistics.logistics.products}">
		       	<%-- 	<c:choose>
						<c:when test="${checkAPP}">
						<div class="swiper-slide"><a  href="shangPinApp://phone.shangpin/actiongodetail?productid=${product.productId}"  target="_blank">
						</c:when>
						<c:otherwise>
						<div class="swiper-slide"><a   href="<c:url value='/product/detail?productNo=${product.productId}'/>">
						</c:otherwise>
					</c:choose> --%>
					<div class="swiper-slide"><a  href="javascript:;" >
		         		<img src="${product.pic }" width="56" height="67" />
		         		<span>¥<fmt:formatNumber type="number" value="${product.price}" maxFractionDigits="0"/> x ${product.count } </span>
		    	    	</a> 
		         	</div>
		    	  </c:forEach>
              </div>
            </div>
   				 
   				 
    		</nav>
   			</c:if>
    <c:if test="${orderLogistics.logistics.ticketno != null && orderLogistics.logistics.ticketno != '' && orderLogistics.logistics.express!= null &&orderLogistics.logistics.express != ''}">
   		<div class="address">
   		 <c:if test="${orderLogistics.logistics.logistics!= null && fn:length(orderLogistics.logistics.logistics) != 0}">
            <h4>由尚品网<span>${orderLogistics.logistics.warehouse }</span>发货</h4>
			<dl class="clr">
			<!-- 
				<dt>
					<a href="#" target="_blank"><img src="/images/track_order/logo_sf.jpg" width="28" height="28" /></a>
				</dt>
			 -->
				<dd>
					<p><span>物流公司：</span>${orderLogistics.logistics.express }</p>
					<p><span>物流单号：</span>${orderLogistics.logistics.ticketno }
						<c:if test="${postArea =='1'&&!fn:contains(orderLogistics.logistics.express, '尚品物流') && (fn:contains(orderLogistics.logistics.logistics[0].desc, '已发货') || fn:contains(orderLogistics.logistics.logistics[0].desc, '已支付完成'))}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="ShangPinApp://phone.shangpin/actiongowebview?title=物流查询&url=http://m.kuaidi100.com/"><font color="blue">&nbsp;&nbsp;&nbsp;去查询>></font></a>
								</c:when>
								<c:otherwise>
									<a href="http://m.kuaidi100.com/"><font color="blue">&nbsp;&nbsp;&nbsp;去查询>></font></a>
								</c:otherwise>
							</c:choose>
						</c:if>
					</p>
				</dd>
			</dl>
   		 </c:if>
		 </div>   
	</c:if>
	
	    <ul class="address_list">
	    <c:choose>  
			   <c:when test="${orderLogistics.logistics.logistics!= null && fn:length(orderLogistics.logistics.logistics) != 0}"> 
			   	<c:forEach var="logistics" items="${orderLogistics.logistics.logistics}" varStatus="status"> 
			   
			    	<c:if test="${status.index==0 }">
			     	 <li class="cur"><i></i> ${logistics.address }&nbsp;&nbsp;${logistics.desc}<br />${logistics.date}</li>
			    	</c:if>
			    	<c:if test="${status.index!=0 }">
			    	 <li>
				        <i></i>
				      ${logistics.address }&nbsp;&nbsp;${logistics.desc }<br/>${logistics.date}
				      </li>
			    	</c:if>
			     </c:forEach>
			   
			   </c:when>  
			   <c:otherwise>  
			    <li class="cur"><i></i>暂无物流信息</li>
			   </c:otherwise>  
		</c:choose>  
	    </ul>
	    
	        <div class="address"> 
            <div class="add_list">
		        <p class="clr">
		          <span class="left">收&nbsp;&nbsp;货&nbsp;&nbsp;人：</span>
		          <span class="right">${ orderLogistics.receive.name}</span>
		        </p>
		        <p class="clr">
		          <span class="left">收货地址：</span>
		          <span class="right">${orderLogistics.receive.provName}${orderLogistics.receive.cityName}${orderLogistics.receive.area}${orderLogistics.receive.townName}${orderLogistics.receive.addr}</span>
		        </p>
		        <p class="clr">
		          <span class="left">联系电话：</span>
		          <span class="right">${orderLogistics.receive.tel }</span>
		        </p>
		    </div>
          </div>
  		</section>

      </div>
  </div>
</rapid:override> 
  <rapid:override name="footer">
  </rapid:override>
<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp" %> 

