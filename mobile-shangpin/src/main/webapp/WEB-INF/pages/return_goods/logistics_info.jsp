<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/return/return_logistics.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js?${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js?${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js?${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/UIPickerView.js?${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/return/return_logistics.js?${ver}")
	</script>
</rapid:override>
<rapid:override name="title">
	填写退货物流信息
</rapid:override>
<rapid:override name="page_title">
	填写退货物流信息
</rapid:override>
<rapid:override name="content">
    <form name="" id="">
      <section class="return-form">
      	<h3 class="return-title">退货商品</h3>
      	<c:forEach var="orderdetail" items="${returnContent.orderdetails}">
      		<p class="pord_info">
	            <a href="#" class="clr">
	            <img src="${orderdetail.pic}" width="62" height="70">
	            <ins>
	                <i>${orderdetail.brandNameEN}<br />${orderdetail.productName}</i>
	                <c:if test="${fn:length(orderdetail.attribute) > 0}">
	                	 <c:forEach var="attr" items="${orderdetail.attribute}">
		                	<em>${attr.name}：${attr.value}</em>
		                </c:forEach>
	                </c:if>
	                <br/>
	                <em>价格：&yen;${orderdetail.price}</em>
	                <em>数量：${orderdetail.quantity}</em>
	            </ins>
	            </a>
	        </p>
      	</c:forEach>
        <h3 class="return-title">退货申请信息</h3>
        <ul class="selected user_info clr"> 
            <li>
              <em class="lable">申请单号：</em>
              <span id="applyNo">${returnContent.returnInfo.returnId}</span>
              <time>${returnContent.returnInfo.timestamp}</time>
            </li>
            <li>
              <em class="lable">退货原因：</em>
              <span>${returnContent.returnInfo.returnReason}</span>
            </li>            
        </ul>
      	
      	<h3 class="return-title">退货物流信息<em>请及时补充退货物流信息，否则可能造成无法正常退款</em></h3>
        <ul class="selected user_info"> 
            <li>
              <em class="lable">物流公司：</em>
              <span><a href="javascript:;" class="select_choose" id="select_region" data-provinceIndex="1">请选择物流公司</a></span>
            </li>
            <li>
              <em class="lable">物流单号：</em>
              <span><input id="tNumber" type="text" placeholder="请填写物流单号" required></span>
            </li>            
        </ul>
        
        <div class="payment-submit">
            <a href="#" class="payment-btn">提交信息</a>
            <input type="submit" class="payment-btn" value="提交信息">
        </div>
      </section>
     </form>
</rapid:override>
<rapid:override name="overlay">
	<div class="select-overlay" id="picker-overlay"></div>
    <div class="region-picker-wrapper control" id="region-picker">
	    <div class="header">
        	<div class="bar bar-header">
          		<button class="button" id="selectClose">取消</button>
          		<!--<div class="h3 title">省市区</div>-->
          		<button class="button button-positive" id="selectYes">确定</button>
        	</div>
     	</div>
	    <div class="body">
	        <div class="select-zone top"></div>
	        <div class="select-zone middle"></div>
	        <div class="select-zone bottom"></div>
	        <div class="region-picker" >
	          	<div id="provincePicker"></div>
	        </div>
	    </div>
    </div>
    <!--退货原因弹层end-->
</rapid:override>


<rapid:override name="footer">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/submit_order_common.jsp" %> 