<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/return_goods.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/page/return_goods.js${ver}")
				.excute();
	</script>
</rapid:override > 

<%-- 页标题 --%>
<rapid:override name="page_title">
	退款详情
</rapid:override>

<rapid:override name="content">
	      <!--内容区域 start-->
	      <section class="return-progress">
	      	<h3 class="return-title">退货进度</h3>
	        <ul class="progress-list">
	          <li class="cur">
	          <c:forEach var="progress" items="${returnProgress}" varStatus="progressStatus">
	          	<c:if test="${!progressStatus.first}">
	          		<li>
	          	</c:if>
	          	<i></i>
	          	${progress.desc}<br />
	          	${progress.timestamp}
	          	<%-- <fmt:formatDate value='${progress.timestamp}' type="both" pattern='yyyy-MM-dd HH:mm:ss'/> --%>
	          	</li>
	          </c:forEach>
	        </ul>
	        <h3 class="return-title">退货信息</h3>
	        <ul class="return_info"> 
	            <li class="clr">
	              <em>申请单号：</em>
	              <span>${returnInfo.returnId}</span>
	              <time>${returnInfo.timestamp}</time>
	            </li>
	            <li class="clr">
	               <em>退货原因：</em>
	              <span>${returnInfo.returnReason}</span>
	            </li>
	            <li class="clr">
	              <em>退款方式：</em>
	              <span>${returnInfo.returnType}</span>
	             <%--  <span>原支付方式返回，返回至<strong>中国上海浦东发展银行</strong></span> --%>
	            </li>
	            <c:if test="${(not empty returnInfo.returnAmount && returnInfo.returnAmount != '0' )}">
	            <li class="clr">
	              <em>退款金额：</em>
	              <span>￥${returnInfo.returnAmount}</span>
	              <%-- <em>退礼品卡：</em>
	              <span>${returnInfo.returnGiftCards}</span>
	              <em>退优惠券：</em>
	              <span>${returnInfo.returnCoupon}</span> --%>
	            </li>
	            </c:if>
	            <li class="clr">
	              <p>
	                  <em>收货人：</em>
	                  <span>${returnInfo.receiver }</span>
	              </p>
	              <p>
	                  <em>收货人电话：</em>
	                  <span>${returnInfo.receiverPhone }</span>
	              </p>
	              <em>商品寄回地址：</em>
	              <span>${returnInfo.returnAddress}</span>
	              <p>
	                  <em>邮政编码：</em>
	                  <span>${returnInfo.zipCode}</span>
	              </p>
	            </li>
	            <c:if test="${(not empty returnInfo.invoiceAddress)}">
	            <li class="clr">
	              <em>发票寄回地址：</em>
	              <span>${returnInfo.invoiceAddress }</span>
	              <p>
	                  <em>邮政编码：</em>
	                  <span>${returnInfo.invoiceCode }</span>
	              </p>
	              <p>
	                  <em>温馨提示：</em>
	                  <span class="tips">直发商品发票与商品要分别寄回</span>
	              </p>
	            </li>
	            </c:if>
	            <c:if test="${(not empty returnInfo.logisticsCpy) ||(not empty returnInfo.logisticsNo) }">
	            <li class="clr">
	              <em>退货物流公司：</em>
	              <span>${returnInfo.logisticsCpy }</span>
	              <p>
	                  <em>物流编号：</em>
	                  <span>${returnInfo.logisticsNo }</span>
	              </p>
	           </li>
	           </c:if>
	            
	        </ul>
	        <h3 class="return-title">退货商品信息</h3>
	        <c:forEach var="orderdetail" items="${orderdetails}">
	        	<div class="pord_info">
		            <a href="#" class="clr">
		            <img src="${orderdetail.pic}" width="56" height="67">
		            <ins>	
		                <i><%-- <span><img src="${orderdetails.pic}" width="20" height="13"></span> --%>${orderdetail.brandNameEN}<br>${orderdetail.brandNameCN}${orderdetail.productName}</i>
		                <c:forEach var="attr" items="${orderdetail.attribute}" varStatus="detailstatus">
		                		<em>${attr.name}：${attr.value}</em>
		                		<c:if test="${detailstatu.count%2==0||detailstatu.last}">
		                			<br>
		                		</c:if>
		                </c:forEach>
		                <em>价格：￥${orderdetail.price}</em>
		                <em>数量：${orderdetail.quantity}</em><br>
		            </ins>
		            </a>
		        </div>
	        </c:forEach>
	      </section>
	      <!--内容区域 start-->
	 
</rapid:override>
 <%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp" %> 
