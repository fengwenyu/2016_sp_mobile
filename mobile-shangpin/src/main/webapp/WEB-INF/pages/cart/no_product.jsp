<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order.css${ver}" rel="stylesheet" />
	<c:if test="${groupList!=null  }">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order_result.css${ver}" rel="stylesheet" />
	</c:if>
	<style>
		body{
			background: #fff; 
		}
	</style>
	<script type="text/javascript" charset="utf-8">
	loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}" )
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	  	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/css3.js${ver}")
      	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/slideLayer.js${ver}")
	  	.excute()
	  	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}" )
      	.excute(function(){
		  //图片轮播
		  $('.scroll').slider({wrap:".bar_list",wrapUl:".slider-wrap",isLoop:true,isPlay:true});
		  
		  //触摸出现边框
		  $('.content_scroll p a').live('touchstart',function(e){
			  $(this).find('img').addClass('img-border');
		  });
		  $('.content_scroll p a').live('touchend',function(e){
			  $(this).find('img').removeClass('img-border');
		  });
		  
		
	  });
</script>
</rapid:override>

<rapid:override name="page_title">
	购物车
</rapid:override>

<rapid:override name="content" >
	  <!--内容区域 start-->
	  <section class="detail_block">
	    <div class="no_result">
	      <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/order/pic_order_new.png" >
	      <span>您的购物车空空如也!</span>
	      		<c:choose>
                		<c:when test="${checkAPP}">
		                	<a href="shangpinapp://phone.shangpin/actiongoindexpage">去逛逛</a>
		                </c:when>
		                <c:otherwise>
		                	    <a href="<c:url value='/index'/>">去逛逛</a>
		                </c:otherwise>
                	</c:choose>
	    </div>
	  </section>
	  			
	  <!--内容区域 end-->
	  <c:if test="${groupList!=null  }">
	  	<section class="scroll clr">
      <h3>流行趋势推荐</h3>
      <div class="bar_list">
        <ul class="slider-wrap clr">
        	<c:forEach var="group" items="${groupList }">
	        	<!--多个list  -->
	          <li>
	          		  <div class="content_scroll clr">
	            	 	 <!-- 每个list有3个 -->	
		            	  <c:forEach var="product" items="${group }">
		            	  		<p class="size158_150">
		            	  		<c:choose>
                		<c:when test="${checkAPP}">
		                	<a href="shangPinApp://phone.shangpin/actiongodetail?productid=${ product.productId}">
		                </c:when>
		                <c:otherwise>
		                	<a href="<c:url value='/product/detail?productNo=${product.productId }'/>">
		                </c:otherwise>
                	</c:choose>
		            	  		
		            	  		<img src="${fn:replace(product.pic,'-10-10','-168-224') }" width="84" height="112" />
                 			 <span>
                   			 	<em>${product.productName }</em>
                    			<b>
                 		  		&yen;
                 		  		<c:choose>
				      				 <c:when test="${product.status == '0001' || product.status == '000100' }">
				      				 ${product.promotionPrice }
				      				 </c:when>
							       <c:otherwise>
								       <c:choose>
								      	  <c:when test="${product.isSupportDiscount==1 }">
								      	  		<c:choose>
								      	  			<c:when test="${sessionScope.mshangpin_user.level=='钻石会员' }">${product.diamondPrice }</c:when>
								      	  			<c:when test="${sessionScope.mshangpin_user.level=='白金会员' }">${product.platinumPrice }</c:when>
								      	  			<c:when test="${sessionScope.mshangpin_user.level=='黄金会员' }">${product.goldPrice }</c:when>
								      	  			<c:otherwise>
								      	  				${product.limitedPrice }
								      	  			</c:otherwise>
								      	  		</c:choose>
								      	  </c:when>
								      	  <c:otherwise>
								      	  	${product.limitedPrice  }
								      	  </c:otherwise>
								       </c:choose>
							       </c:otherwise>
								</c:choose>
		            	  			</b>
                 				 </span>
		            	  		</a>
		            	  		</p>
		            	  </c:forEach>
	            	   	<!-- end -->
	            	   </div>
          		</li>
        	</c:forEach>
        	<!-- end -->  
        </ul>
      </div>
      </section>
	  </c:if>
</rapid:override>
<rapid:override name="footer">
  	
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 