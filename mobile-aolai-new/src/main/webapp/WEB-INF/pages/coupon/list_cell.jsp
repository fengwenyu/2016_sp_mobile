<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
  <div class="alOrder_couponList order_list" id="alOrder_couponList">
  <input type="hidden" id="statusType" value="${couponType }"/>
  <input type="hidden" id="pageNo" value="${pageNo }"/>
      <%@ include file="list_cell_coupon.jsp" %> 
  </div>

    <div class="alOrder_listCell order_null" >
      <div class="alOrder_none" <c:if test="${haveCoupon}">style="display:none"</c:if>>
	      <div class="alOrder_none">
	        <h2>暂无此类优惠券</h2>
	        <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/none_prod.png" width="130" height="130"></p>
	      </div>
	   </div>
    </div>
    
  <!-- 优惠券列表结束 -->
 <a id="addMore" onclick="addMore(this)" class="alList_moreBtn moreButton"<c:if test="${!isHaveMore}">style="display:none"</c:if> href="javascript:void(0);">加载更多</a>
