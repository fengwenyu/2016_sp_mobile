<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/order_detail.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/payment.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/topFix.js${ver}")
				.excute();
			
		
	</script>
</rapid:override > 


			
<%-- 浏览器标题 --%>
<rapid:override name="title">
	订单详情
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	订单详情
</rapid:override>
<rapid:override name="downloadAppShowHead">

</rapid:override>

<rapid:override name="content">
<input type="hidden" id="currentTimes" name="currentTimes" value="${currentTimes}">

	<div class="paymet_block">
      <form name="login" id="J_Login"> 
            <fieldset>
	       <%--      <c:if test="${orderDetail.canPay=='1'}">
	            	<legend class="price"><em>应付总额：<i>&nbsp;&nbsp;&yen;${orderDetail.payAmount }</i></em><span>
	          			<c:choose>
		           		<c:when test="${checkWX }">
		           		微信
		           		</c:when>
		           		<c:otherwise>
		           		支付宝
		           		</c:otherwise>
		           		</c:choose>
	            		</span>
	            	</legend>
	            </c:if> --%>
                <legend>订单详情</legend>
                <p class="selected"><a href="#">下单时间：<times id="setTime">${orderDetail.date }</times><br>订单编号：${orderId }
                <c:if test="${orderDetail.orderList[0].detail[0].giftType eq '1'}">
               	 	<span class="e-gift-card">您选购的是电子礼品卡，订单支付成功后，您可在支付成功的页面或"我的尚品-礼品卡"查看购买记录</span>
                </c:if>
                </a></p>
               	<c:if test="${orderDetail.orderList[0].detail[0].giftType!='1'}">
	                <p class="selected"><a href="#"><em>${orderDetail.receive.name }</em><i class="phone">${orderDetail.receive.tel }</i><br>${orderDetail.receive.cardID }<br>${orderDetail.receive.provName }${orderDetail.receive.cityName }${orderDetail.receive.areaName }${orderDetail.receive.townName }${orderDetail.receive.addr}</a></p>
	                <p class="select"><a href="javascript:;" id="select_time">收货时间<span class="delivery_mode">
	                		<c:forEach var="delivery" items="${orderDetail.delivery}">
	                			<c:if test="${delivery.isSelected=='1' }">
	                				${delivery.desc }
	                			</c:if>
	                		</c:forEach>
	                </span></a></p>
                </c:if>
                <legend>商品信息</legend>
                <c:forEach var="orderList" items="${orderDetail.orderList}">
                	<c:forEach var="detail" items="${orderList.detail}">
		                <p class="pord_info">
		                <c:choose>
		                	<c:when test="${detail.isClick eq '1'}">
			                	<c:choose>
				                	<c:when test="${detail.giftType eq '2'}">
				                	<a href="${ctx}/giftCard/cardDetail?productNo=${detail.id}&type=2" class="clr">
				                	</c:when>
				                	<c:when test="${detail.giftType eq '1'}">
				                		<a href="${ctx}/giftCard/cardDetail?productNo=${detail.id}&type=1" class="clr">
				                	</c:when>
				                	<c:otherwise>
				                	<a href="${ctx}/product/detail?productNo=${detail.id}" class="clr">
				                	</c:otherwise>
			                	</c:choose>
		                	</c:when>
		                	<c:otherwise>
		                	<a href="#" class="clr">
		                	</c:otherwise>
		                </c:choose>
		                	
		                    <img src="${fn:substring(detail.pic,0,fn:indexOf(detail.pic,'-'))}-112-134.jpg" width="56" height="67">
		                    
		                    <ins>
		                        <i><span>
		                        <c:if test="${orderDetail.orderType=='2'&&detail.countryPic!=''}">
		                        	<img src="${fn:replace(detail.countryPic,'-10-10','-40-26')}" width="20" height="13">
		                        </c:if></span>
		                        <c:choose>
		                        	<c:when test="${detail.giftType eq '0'}">
		                        	${detail.brandNameEN}<br />${detail.name}</i>
		                        	</c:when>
		                        	<c:otherwise>
		                        	${detail.name}</i>
		                        	</c:otherwise>
		                        </c:choose>
		                        
		                       	<c:forEach var="attribute" items="${detail.attribute}">
		                       	<c:if test="${attribute.value !=null&&attribute.value !=''&&detail.giftType eq '0'}">
			                         <em>${attribute.name }：${attribute.value }</em>		
		                        </c:if>
			                     
		                        </c:forEach>
		                        </br>
		                        <em>${detail.priceTitle}：${detail.price  }</em>
		                        <em>${detail.quantityTitle }：${detail.quantity }</em>
		                    </ins>
		                    </a>
		                </p>
	                </c:forEach>
                </c:forEach>
	              <%--   <legend>配送方式</legend>
	                <p class="select">${orderDetail.noticeInfo }</p> --%>
	                <!--<p class="select"><a href="javascript:;" id="dis_modes">标准配送，预计15-21个工作日送达，免运费</a></p>-->
	                <!--<p>标准配送，预计15-21个工作日送达，免运费</p>
	                <p class="select coupons"><a href="#">优惠券/折扣码 <i>您有 <em>5</em> 张优惠券可用</i></a></p>
	                <legend>礼品卡支付</legend>
	                <p class="giftCard"><a href="#" class="cur">余额 &yen;1000 <i>使用礼品卡为本次支付 &yen;1000</i></a></p>
	                <legend class="tips">提示：提交订单时需输入支付密码</legend>-->
	                <c:choose>
	                <c:when test="${orderDetail.canPay eq'1' }">
	               		<p class="total">
	               </c:when>
	               <c:otherwise>
	               <p class="total" style="border-bottom:none">
	               </c:otherwise>
	               </c:choose>
	               		<c:forEach var="priceShow" items="${orderDetail.priceShow}">
	               		<c:choose>
	               			<c:when test="${priceShow.type=='7' }">
	               				<em>${priceShow.title }：<i>&ensp;&nbsp;&yen;${priceShow.amount }</i><br /></em>
	               			</c:when>
		               		<c:otherwise>
		               			<c:if test="${priceShow.amount*1>0 }">
		               			${priceShow.title }：<i>${priceShow.type eq '2' || priceShow.type eq '6' || priceShow.type eq '3' || priceShow.type eq '4'  ? "&minus;":"&ensp;"}&nbsp;&yen;${priceShow.amount }</i><br />
		               			</c:if>
		               		</c:otherwise>
	               		</c:choose>
	               		</c:forEach>
	                </p>
	                <c:if test="${orderDetail.canPay eq'1' }">
			              <c:if test="${orderDetail.orderType eq'1'}">
			               <a href="${ctx}/order/pay/normal?orderId=${orderDetail.mainOrderId }" class="payment_btn">立即支付 </a>
				         </c:if>
			             <c:if test="${orderDetail.orderType eq'2'}">
			             	<c:choose>
			             		<c:when test="${orderDetail.selectPayment.mainPayCode=='20'||orderDetail.selectPayment.mainPayCode=='19'||orderDetail.selectPayment.mainPayCode=='27'}">
			              			 <a href="${ctx}/order/pay/normal?orderId=${orderDetail.mainOrderId }" class="payment_btn">立即支付 </a>
			             		</c:when>
			             		<c:otherwise>
					                <a href="${ctx}/overseas/pay/continue?orderId=${orderDetail.mainOrderId }" class="payment_btn">立即支付 </a>
			             		</c:otherwise>
			             	</c:choose>
			             </c:if>
		                    
		                <%--  <input type="submit" class="payment_btn" value="支付 <em id='leftTime' leftTime=''></em>"> --%>
			        
			           	<input type="hidden" name="orderId" id="orderType" value="${orderDetail.orderType }"/>
			           	<input type="hidden" name="orderId" id="orderId" value="${orderDetail.mainOrderId }"/>
			            <input type="hidden" name="setTime" id="setTime" value="${orderDetail.date }"/>
          			</c:if>
	            </fieldset>
        </form>
</div>

<!-- <div class="select-overlay"></div>
<div class="select-layer" id="rece_time_layer">
	<a href="javascript:;" class="close_btn">关闭</a>
	<h3>收货时间</h3>
    <dl class="select-opt">
        <dd><a href="#" class="cur">工作日收货</a></dd>
        <dd><a href="#">工作日、节假日收货</a></dd>
        <dd><a href="#">双休日、节假日收货</a></dd>
    </dl>
</div>

<div class="select-layer" id="dis_modes_layer">
	<a href="javascript:;" class="close_btn">关闭</a>
	<h3>配送方式</h3>
    <dl class="select-opt">
        <dd><a href="#" class="cur">标准配送，预计15-21个工作日送达，免运费</a></dd>
        <dd><a href="#">极速配送，预计3-7个工作日送达，运费¥49</a></dd>
    </dl>
</div> -->

<div id="overlay"></div>
<div id="popup_box" >
    <a href="#" class="title_closeBtn">×</a>
    <h1 id="popup_title" style="display: block;">填写收货人身份证号码</h1>
    <div id="popup_content" class="share">
        <div id="popup_message">
        	<p>根据海关的要求，海外商品需要您填写收货人的身份证号码进行个人物品入境申报。</p>
            <div class="identification-box">
                <label for="identificationNum">身份证号码：</label>
                <input type="text" id="J_identificationNum" name="identificationNum" placeholder="请输入身份证号码" required maxlength="18">
            </div>
        </div>
        <div id="popup_panel">
          <button type="button" id="popup_cancel">&nbsp;取消&nbsp;</button> 
          <button type="button" id="popup_ok">&nbsp;保存&nbsp;</button>
        </div>
    </div>
</div>
</rapid:override> 
<%-- 页面的尾部 --%>      
<rapid:override name="down_page">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 