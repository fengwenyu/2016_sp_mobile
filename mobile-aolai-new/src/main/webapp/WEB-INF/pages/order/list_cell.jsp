<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
  <div class="alOrder_listCell order_list">
  <input type="hidden" id="statusType" value="${statusType }"/>
  <input type="hidden" id="pageNo" value="${pageNo }"/>
      <%@ include file="list_cell_order.jsp" %> 
  </div>

    <div class="alOrder_listCell order_null" id="alOrder_listCell">
      <div class="alOrder_none" <c:if test="${haveOrder}">style="display:none"</c:if>>
        <h2>暂无此类订单</h2>
        <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/none_prod.png" width="130" height="130"></p>
        <a href="${ctx }/" class="alOrder_buyBtn">去 购 物</a>
      </div>
    </div>
    
  <!-- 订单列表结束 -->
 <a id="addMore" onclick="addMore(this)" class="alList_moreBtn moreButton"<c:if test="${!isHaveMore}">style="display:none"</c:if> href="javascript:void(0);">加载更多</a>
