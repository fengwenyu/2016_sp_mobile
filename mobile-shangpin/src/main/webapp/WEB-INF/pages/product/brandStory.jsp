<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<c:if test="${productDetail.basic.brand.about!=null&&productDetail.basic.brand.about!='' }">
	 <div class="product_introduction">
	        <a href="<c:url value='/brand/product/list?brandNo=${productDetail.basic.brand.id }&brandName=${productDetail.basic.brand.nameEN }'/>">
	          <span>${productDetail.basic.brand.nameEN } 品牌店</span>
	        </a>
	        <p class="product_flag">
	       		${productDetail.basic.brand.about }
	        </p>
	 </div>
 </c:if>