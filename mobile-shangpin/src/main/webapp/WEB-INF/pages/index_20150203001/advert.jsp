<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<c:if test="${releasesSPActivity != null&&fn:length(releasesSPActivity.list)>0&&fn:length(worthTitle.list)>0&&worthTitle != null}">

<div class="theme-box clr">
  <a href="${ctx }/index/release/list?pageIndex=1&pageSize=20&origin=1">
	<div class="theme-left">
      <h3>${releasesSPActivity.title }</h3>
      <img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:replace(releasesSPActivity.list[0].pic,'-10-10','-400-186') }" width="100%"></div>
   </a>


 <a href="${ctx }/index/worth">
    <div class="theme-right">
      <h3 style="font-size:12px">${worthTitle.title}</h3>
      <img alt="" src="${fn:replace(worthTitle.list[0].pic,'-10-10','-200-203') }" width="100%">
      <h4>${worthTitle.list[0].brandNameEN }</h4>
      <p>
    	<c:choose>
			<c:when test="${worthTitle.list[0].status == '0001' || worthTitle.list[0].status == '000100' }">
				&yen;	${worthTitle.list[0].promotionPrice }
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${worthTitle.list[0].isSupportDiscount==1 }">
						<c:choose>
							<c:when test="${userLv == '0002'}">
									<span>&yen;${worthTitle.list[0].goldPrice}  </span>
									<c:if test="${worthTitle.list[0].goldPrice*1< worthTitle.list[0].marketPrice}">
										<em>&yen;${worthTitle.list[0].marketPrice} </em>
									</c:if>
							</c:when>
							<c:when test="${userLv == '0003'}">
									<span>&yen;${worthTitle.list[0].platinumPrice}</span>
										<c:if test="${worthTitle.list[0].platinumPrice*1< worthTitle.list[0].marketPrice}">
										<em>&yen;${worthTitle.list[0].marketPrice} </em>
									</c:if>
							</c:when>
							<c:when test="${userLv == '0004'}">
									<span>&yen;${worthTitle.list[0].diamondPrice}</span>
									<c:if test="${worthTitle.list[0].diamondPrice*1< worthTitle.list[0].marketPrice}">
										<em>&yen;${worthTitle.list[0].marketPrice} </em>
									</c:if>
							</c:when>
							<c:otherwise>
									<span>&yen;${worthTitle.list[0].limitedPrice}</span>
										<c:if test="${worthTitle.list[0].limitedPrice*1< worthTitle.list[0].marketPrice}">
										<em>&yen;${worthTitle.list[0].marketPrice} </em>
									</c:if>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
							<span>&yen; ${worthTitle.list[0].limitedPrice  }</span>
								<c:if test="${worthTitle.list[0].limitedPrice*1< worthTitle.list[0].marketPrice}">
										<em>&yen;${worthTitle.list[0].marketPrice} </em>
									</c:if>
					</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
    </p>
    </div>
  </a>   

  
</div>
</c:if>




<%--广告模板 --%>
 <c:if test="${modelOne != null}">
	<c:forEach var="list" items="${modelOne.list}">
		<c:choose>
			<c:when test="${list.type=='1'}">
				<div class="one-list" >
				    <%@ include file="/WEB-INF/pages/index_20150203001/advert_link.jsp"%>
				</div>
			</c:when>
			<c:when test="${list.type=='2'}">
				<ul class="two-list clr">
				 	<%@ include file="/WEB-INF/pages/index_20150203001/advert_link.jsp"%>
				</ul>
			</c:when>
			<c:when test="${list.type=='3'}">
				<ul class="three-list clr">
					<%@ include file="/WEB-INF/pages/index_20150203001/advert_link.jsp"%>
				</ul>
			</c:when>
			<%-- <c:when test="${fn:length(list.model)==4}">
				<ul class="three-list clr">
					<%@ include file="/WEB-INF/pages/brand/advert_link.jsp"%>
				</ul>
			</c:when>
			<c:when test="${fn:length(list.model)==5}">
				<ul class="three-list clr">
					<%@ include file="/WEB-INF/pages/brand/advert_link.jsp"%>
				</ul>
			</c:when> --%>
			<c:when test="${list.type=='100'}">
			   <div class="one-two-list">
				    <%@ include file="/WEB-INF/pages/index_20150203001/advert_link.jsp"%>
				</div>
			</c:when>
			<c:when test="${list.type=='101'}">
			  <div class="two-one-list">
					<%@ include file="/WEB-INF/pages/index_20150203001/advert_link.jsp"%>
			  </div>
			</c:when>
		</c:choose>
	</c:forEach>
</c:if>

<c:if test="${newGoodsTitle != null}">
	<h2 class="title">
		${newGoodsTitle.title }
		<a href="${ctx }/new/goods" class="title-more">更多<i class="icon-caret-right"></i></a>
	</h2>
	<div class="swiper-container" id="swiper-container3">
	    <div class="swiper-wrapper">
		    <c:forEach var="list" items="${newGoodsTitle.list}">
		        <div class="swiper-slide">
			        <c:choose>
			        <c:when test="${checkAPP}">
						<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&filters=brand_${list.refContent}&brandid=${list.refContent}"  >
					</c:when>
					<c:otherwise>
						<a href="${ctx }/brand/product/list?brandNo=${list.refContent}&postArea=0" >
					</c:otherwise>
					</c:choose>
			        <img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:replace(list.productList[0].pic,'-10-10','-200-266') }" width="100%">
			        <span class="new-brand-logo"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:replace(list.pic,'-10-10','-100-46') }"/><em class="model-num">
			        <strong>${list.count }</strong>款</em></span></a>
		        
		        </div>
		     </c:forEach>
	    </div>
	</div>
</c:if>



<c:if test="${operation != null&&fn:length(operation.list)>0}">
  <c:forEach var="list" items="${operation.list}">
	<div class="one-list">
   				<c:choose>
	    			<c:when test="${list.type == '1'}">
	    				<a href="${ctx}/subject/product/list?topicId=${list.refContent}&postArea=0">${list.name }</a>
	    			</c:when>
	    			<c:when test="${list.type == '2'}">
	    				<a href="${ctx}/category/product/list?categoryNo=${list.refContent}&postArea=0">${list.name }</a>
	    			</c:when>
	    			<c:when test="${list.type == '3'}">
	    				<a href="${ctx}/brand/product/list?brandNo=${list.refContent}&postArea=0">${list.name }</a>
	    			</c:when>
	    			<c:when test="${list.type == '4'}">
	    				<a href="${ctx}/product/detail?productNo=${list.refContent}">${list.name }</a>
	    			</c:when>
	    			<c:when test="${list.type == '5'}">
	    				<a href="${list.refContent}">${list.name }
	    			</c:when>
		    		<c:otherwise>
		    				<a href="#">${list.name }
		    		</c:otherwise>
	   		 </c:choose>
			<img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:replace(list.pic,'-10-10','-640-190') }" width="100%">
		</a>
	</div>
</c:forEach>
</c:if>
 

