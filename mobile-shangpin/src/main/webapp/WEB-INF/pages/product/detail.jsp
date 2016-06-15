<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/detail.css${ver}" rel="stylesheet" />
	<script type="text/javascript" src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js"></script>
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/css3.js${ver}" )
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/slideLayer.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/detail.js${ver}")
		.excute(function(){
			$('#J_m-slider').slider({wrap:".slider-outer",wrapUl:".slider-wrap",wrapStatus:'.slider-status',isLoop:true,isPlay:true});
		});
	</script>
</rapid:override>

<rapid:override name="title">
    	${merchandise.name}
</rapid:override>

<rapid:override name="downloadAppShowBottom">
</rapid:override>

<rapid:override name="page_title">
	 商品详情
</rapid:override>

<rapid:override name="content">
<input id="_iswx" name="_iswx" type="hidden" value="${checkWX }"/>
 <input type="hidden" name="_mainTitle"  id="_mainTitle"  value="${merchandise.name} 尚品网这件宝贝很不错，快来瞧一瞧!"/>
 <input type="hidden" name="_mainDesc"  id="_mainDesc"  value="${merchandise.name} "/>
 	<c:forEach var="pic" items="${merchandise.pics}"  varStatus="status">
		<c:if test="${status.first }">
		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${pic}"/>
		</c:if>
     </c:forEach>
  <section class="detail_block">
  	
    <!-- 图片轮播 Start -->
    <div class="content" id="J_m-slider">
        <div class="slider-outer">
            <ul class="slider-wrap">
            <c:forEach var="pic" items="${merchandise.pics}"  varStatus="status">
<%--             	<c:if test="${status.first }"> --%>
<%--             		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${pic}"/> --%>
<%--             	</c:if> --%>
            	<li><img alt="" width="320" height="415" src="${pic}"></li>
            </c:forEach>
            </ul>
        </div>
        <div class="prev pg-btn"><a>上一页</a></div>
        <div class="slider-status" ></div>
        <div class="next pg-btn"><a>下一页</a></div>
    </div>
    <!-- 图片轮播 End -->
    
    <div class="alProdInfo">
      <h3>${merchandise.brand}</h3>
      <h2>${merchandise.name}</h2>
      <!-- 价格信息 -->
      <dl>
       	<dd class="priceInfo">
        <div id="amountDiv">
	        <strong class="">&yen;${strongPrice}</strong>&nbsp;&nbsp;
			<c:if test="${strongPrice < marketPrice}">
				<del>&yen;${marketPrice}</del>
			</c:if>
		</div>
        </dd>
      </dl>
      <!-- 颜色尺码信息 start-->

      <c:if test="${merchandise.firstpropname != null || merchandise.firstpropname != ''}">
      	<dl>
	        <dt>${merchandise.firstpropname}</dt>
	        <dd class="fillColor clr">
	        	<c:if test="${merchandise.secondpropname != null || merchandise.secondpropname != ''}">
	        		<c:forEach var="first" items="${merchandise.firstprops}" varStatus="first_stu">
		        		<c:choose>
		        			<c:when test="${first_stu.index == 0}">
		        				 <span onclick="changeSecondPro(this);" id="firstpro_${first_stu.index}" class="cur" data-title="0"><img src="${first.icon}" width="49" height="66"><i>${first.firstprop}</i></span>
		        			</c:when>
		        			<c:otherwise>
		        				<span onclick="changeSecondPro(this);" id="firstpro_${first_stu.index}" data-title="${first_stu.index}"><img src="${first.icon}" width="49" height="66"><i>${first.firstprop}</i></span>
		        			</c:otherwise>
		        		</c:choose>
		        	</c:forEach>
	        	</c:if>
	        	<c:if test="${merchandise.secondpropname == null && merchandise.secondpropname == ''}">
	        		<c:forEach var="first" items="${merchandise.firstprops}" varStatus="first_stu">
	        			<c:forEach var="second" items="${first.secondprops}" varStatus="second_stu">
	        				<c:choose>
			        			<c:when test="${first_stu.index == 0}">
			        				 <span id="firstpro_${first_stu.index}" class="cur" data-title="0" value="${second.sku}"><img src="${first.icon}" width="49" height="66"><i>${first.firstprop}</i></span>
			        			</c:when>
			        			<c:otherwise>
			        				<span id="firstpro_${first_stu.index}" data-title="${first_stu.index}" value="${second.sku}"><img src="${first.icon}" width="49" height="66"><i>${first.firstprop}</i></span>
			        			</c:otherwise>
			        		</c:choose>
	        			</c:forEach>
		        	</c:forEach>
	        	</c:if>
	        </dd>
	        <input type="hidden" id="colorVal" value="0">
	       </dl>
      </c:if>
      <c:choose>
      
	      <c:when test="${merchandise.secondpropname != null && merchandise.secondpropname != ''}">
	      	<dl>
		        <dt>${merchandise.secondpropname}</dt>
		        <dd class="fillSize clr">
		        	<c:forEach var="first" items="${merchandise.firstprops}" varStatus="first_stu">
		        		<c:forEach var="second" items="${first.secondprops}" varStatus="second_stu">
			        		<c:choose>
			        			<c:when test="${second_stu.index == 0}">
			        				<span id="secondpro_${first_stu.index}" class="cur" data-title="0" value="${second.sku}">${second.secondprop}</span>
			        			</c:when>
			        			<c:otherwise>
			        				<span id="secondpro_${first_stu.index}" data-title="${second_stu.index}" value="${second.sku}">${second.secondprop}</span>
			        			</c:otherwise>
			        		</c:choose>
			        	</c:forEach>
		        	</c:forEach>
		          <c:if test="${fn:length(merchandise.sizeinfoii.productindex)>0 || fn:length(merchandise.sizeinfoii.productdetailsize.table)>0 || fn:length(merchandise.sizeinfoii.productdetailsize.sizepiclist)>0 }">
		          	<a style="margin-left: 10px;" href="<c:url value='/product/sizedesc?productNo=${merchandise.goodscode }'/>">尺码对照表&nbsp;&nbsp;</a>
		          </c:if>
		        </dd>
		        <input type="hidden" id="sizeVal" value="0">
		     </dl>
	      </c:when>
	      <c:otherwise>
	       <c:if test="${fn:length(merchandise.sizeinfoii.productindex)>0 || fn:length(merchandise.sizeinfoii.productdetailsize.table)>0 || fn:length(merchandise.sizeinfoii.productdetailsize.sizepiclist)>0 }">
	      	<dl>
		        <dt></dt>
		        <dd class="fillSize clr">
		         	<a style="margin-left: 10px;" href="<c:url value='/product/sizedesc?productNo=${merchandise.goodscode }'/>">尺码对照表&nbsp;&nbsp;</a>
		        </dd>
		        <input type="hidden" id="sizeVal" value="0">
		     </dl>
		      </c:if>
	      </c:otherwise>
      </c:choose>
      <!-- 颜色尺码信息 end-->
    </div>
   
    <section class="alProd_intro">
    <h4>商品详情</h4>
    <p>
	    <c:forEach var="str" items="${merchandise.info}">
	    	${str}<br/>
	    </c:forEach>
	     <c:if test="${merchandise.returnChangeRemind!=null&&merchandise.returnChangeRemind!=''}">
	    	温馨提示：${merchandise.returnChangeRemind }<br/>
	    </c:if>
    </p>
    </section>
   
    <c:if test="${merchandise.recommend!=null&&merchandise.recommend!=''}">
	    <section>
			${merchandise.recommend }
	    </section>
    </c:if>
    <section class="alProd_intro intro_bg">
        <h3>轻奢第一站，尚品全保障：</h3>
      	<p class="server">
            <span>正品保障</span>100%全正品承诺<br />
            <span>闪电发货</span>顶尖物流闪电直达<br />
            <span>免邮政策</span>订单支付满499及白金以上会员特享<br />
            <span>轻松退货</span>7天退换货放心无忧<br />
            <span>客户服务</span>4006-900-900（8:00~24:00为你守候）
        </p>
    </section>
    
    <ul class="server_notice intro_bg">
    	<li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/detail/server01.png" width="100" height="65"></li>
        <li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/detail/server02.png" width="100" height="65"></li>
        <li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/detail/server03.png" width="100" height="65"></li>
    </ul>
    </section>
    <div class="pageBtm_fixed" style="border-top:1px solid #d8d8d8;">
		<div class="alBuy_btn">
			<c:if test="${merchandise.totalcount > 0}">
				<span class="cart_box"><a href="${ctx}/cart/list"><i></i></a></span>
		        <input type="hidden" name="sku" id="sku" value="${defaultSku}"/>
		        <input type="hidden" name="topicno" id="topicno" value="${merchandise.topicno}"/>
		        <input type="hidden" name="productno" id="productno" value="${merchandise.goodscode}"/>
				<input type="hidden" name="totalCount" id="totalCount" value="${merchandise.totalcount}"/>
				<a href="javascript:;" onclick="addCart();" id="submit">加入购物车</a>
				<%--
				<c:if test="${userId == ''}">
					<a href="javascript:quickSubmit();" class="easyBtn">免注册购物</a>
				</c:if>--%>
			</c:if>
			<c:if test="${merchandise.totalcount == 0}">
				<a href="javascript:;" class="overBtn">已售罄</a>
			</c:if>
	    </div>
    </div>

</rapid:override>
<rapid:override name="down_page">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/common_banner.jsp" %> 
