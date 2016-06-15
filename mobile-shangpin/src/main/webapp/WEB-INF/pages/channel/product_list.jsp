<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/201502brand/brand.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/201502brand/iscroll.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/tabslider.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/brandDownloadAppShow.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/201502brand/filterChannel.js${ver}")
			.excute(function(){
			});
	</script>
</rapid:override >

<%-- 浏览器标题 --%>
 <rapid:override name="title">
	${channelConditions.channelCategoryName}
</rapid:override>
<rapid:override name="page_title">
	${channelConditions.channelCategoryName}
</rapid:override>

<%--内容体 --%>
<rapid:override name="content">
	<form id="search_form" action="${ctx}/channel/product/list?channelCategoryNo=${channelConditions.channelCategoryNo}#navLine" method="post">
		<input type="hidden" id="price" name="price" value="${channelConditions.price}"/>
		<input type="hidden" id="size" name="size" value="${channelConditions.size}"/>
		<input type="hidden" id="color" name="color" value="${channelConditions.color}"/>
		<input type="hidden" id="categoryNo" name="categoryNo" value="${channelConditions.categoryNo}"/>
		<input type="hidden" id="brandNo" name="brandNo" value="${channelConditions.brandNo}"/>
		
		<input type="hidden" id="gender" name="gender" value="${channelConditions.gender}"/>
		<input type="hidden" id="order" name="order" value="${channelConditions.order}"/>
		<input type="hidden" id="postArea" name="postArea" value="${channelConditions.postArea}"/>
		<input type="hidden" id="start" name="start" value="1"/>
		<input type="hidden" id="pageNo" name="pageNo" value="1"/>
	</form>
	<input type="hidden" id="brandNo" name="brandNo" value="${channelConditions.brandNo}"/>
	<input type="hidden" id="hasMore" name="hasMore" value="1"/>
	<input type="hidden" id="channelCategoryNo" name="channelCategoryNo" value="${channelConditions.channelCategoryNo}"/>
	 <%-- 头图 --%> 
	<%--  <c:if test="${brandShop.operat.head.logo!=null && brandShop.operat.head.logo!='' }"> --%>
	 <%-- 分享文案 --%> 
<div class="line"><span id="menuLine"></span></div>
   <!-- 滑块列表 -->  
	<div class="tabSlider" id="navLine"> 
		<div class="menu-nav">
				<ul class="tabSlider-hd" id="list_menu">
					<li id="defaultOrder"  onClick="orderChange('')"><a>默认</a></li>
					<li id="orderOne"  onClick="orderChange('3')"><a>新品</a></li>
				
					<c:if test="${channelResult.abroad=='1'}">
					<!-- 	<li style="display: none;" id="abroadOrder"  onClick="orderChange('9')"><a>海外购</a></li> -->
					</c:if>
					<li id="orderTwo" onClick="orderChange('5')"><a>销量</a></li>
					<li id="orderThree"class="price-btn" ><a href="">价格</a></li>
				</ul> 
<!-- 			<a href="javascript:;" class="fillBtn">筛选</a> -->
		</div>
	
		<div class="list-box">
			<div class="prod_list clr">
				<c:forEach var="product" items="${channelResult.productList}" varStatus="status">
					<div class="list_box">
						<c:choose>
							<c:when test="${checkAPP}">
								<a href="shangPinApp://phone.shangpin/actiongodetail?productid=${product.productId}">
							</c:when>
							<c:otherwise>
								<a href="<c:url value='/product/detail?productNo=${product.productId}&picNo='/>" onclick="_smq.push(['custom',${product.productId},,${product.productName}]);">
							</c:otherwise>
						</c:choose>
						<c:if test="${product.count<=0 }">
							<i class="saleOut">售罄</i>
						</c:if>
						<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"  lazy="${fn:substring(product.pic,0,fn:indexOf(product.pic,'-'))}-304-404.jpg"/></a>
						<div class="li_text">
						<h5>${product.brandNameEN}</h5>
						<p>${product.productName}</p>
						 <span class="item-detail"> 
						<c:choose>
							<c:when test="${product.status == '0001' || product.status == '000100' }">
								<strong class="red">&yen;	${product.promotionPrice }</strong>
							<%-- 	<em>
									<c:if test="${product.promotionPrice*1<product.marketPrice }">
									&yen;${product.marketPrice}
									</c:if>
								</em> --%>
							</c:when>
						<c:otherwise>
						<c:choose>
							<c:when test="${product.isSupportDiscount==1 }">
							<c:choose>
								<c:when test="${userLv == '0002'}">
									<strong class="refer-price">&yen;${product.goldPrice}</strong>
									<%-- <em>
										<c:if test="${product.goldPrice*1<product.marketPrice }">
											&yen;${product.marketPrice}
										</c:if>
									</em> --%>
								</c:when>
								<c:when test="${userLv == '0003'}">
									<strong class="refer-price">&yen;${product.platinumPrice}</strong>
									<%-- <em>
										<c:if test="${product.platinumPrice*1<product.marketPrice }">
											&yen;${product.marketPrice}
										</c:if>
									</em> --%>
								</c:when>
								<c:when test="${userLv == '0004'}">
									<strong class="refer-price">&yen;${product.diamondPrice}</strong>
									<%-- <em>
										<c:if test="${product.diamondPrice*1<product.marketPrice }">
											&yen;${product.marketPrice}
										</c:if>
									</em> --%>
								</c:when>
								<c:otherwise>
									<strong class="refer-price">&yen;${product.limitedPrice}</strong>
									<%-- <em>
										<c:if test="${product.limitedPrice*1<product.marketPrice }">
											&yen;${product.marketPrice}
										</c:if>
									</em> --%>
								</c:otherwise>
						</c:choose>
						</c:when>
						<c:otherwise>
							<strong class="refer-price">&yen; ${product.limitedPrice  }</strong>
							<%-- <em>
								<c:if test="${product.limitedPrice*1<product.marketPrice }">
									&yen;${product.marketPrice}
								</c:if>
							</em> --%>
						</c:otherwise>
						
						</c:choose>
						</c:otherwise>
						</c:choose>
						</span>
						</div>
						</a>
					</div>
				</c:forEach>
				<c:choose>
					<c:when test="${fn:length(channelResult.productList) == 0 && channelConditions.postArea == '2'}">
						<p class="brand_noresults">暂无海外购商品</p>
					</c:when>
					<c:when test="${fn:length(channelResult.productList) == 0 && channelConditions.postArea != '2'}">
						<p class="brand_noresults">没有筛选结果，重新筛选下吧</p>
					</c:when>
					<c:otherwise>
						
					</c:otherwise>
				</c:choose>
			</div>
			</div>
		</div>
	</div>
</rapid:override> 

<%--筛选弹出层  --%>
<rapid:override name="filter_layer">
	<div id="filter_layer">
		<div id="filter_box">
			<h2 class="filter_menu"><a href="#" class="close_Btn" id="filter_close"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/201502brand/sx_icon.png" height="20"  /></a><p>筛选</p></h2>
			 <%--<div id="wrapper"> --%>
			<div class="filter_content">
				<div class="category-box"  id="categoryItem">
					<h3>品类</h3>
					<ul>
						<c:forEach var="category" items="${filterCondition.categoryList}" varStatus="status">
							<c:choose>
								<c:when test="${status.index==0}">
									<li id ="categoryFirst" onclick="searchCategory('categoryFirst','${category.id}','${category.name}');" idFlag="${category.id}" nameFlag="${category.name}" >${category.name}</li>
								</c:when>
								<c:otherwise>
									<li id ="${category.id}" onclick="searchCategory('${category.id}','${category.id}','${category.name}');">${category.name}</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
				</div>
				
				<div class="category-box"  id="sizeItem">
					<h3>尺码</h3>
					<ul>
					</ul>
				</div>
				
				<div class="color-box" id="colorItem">
					<h3>颜色</h3>
					<ul>
					</ul>
				</div>
				
				<div class="category-box" id="priceItem">
					<h3>价格区间</h3>
					<ul>
						<li id="0-500"  onclick="searchKey('price','0-500');">0-500</li>
						<li id="500-1000" onclick="searchKey('price','500-1000');">500-1000</li>
						<li id="1000-2000" onclick="searchKey('price','1000-2000');">1000-2000</li>
						<li id="2000-5000" onclick="searchKey('price','2000-5000');">2000-5000</li>
						<li id="10000"  onclick="searchKey('price','10000');">>10000</li>
					</ul>
				</div>
				
				<a href="javacript:;" id="finishBtn">确定</a>
				<input type="hidden" id="hasPageNum" name="hasPageNum" value="${hasPageNum }"/>
				<input type="hidden" id="checkApp" name="checkApp" value="${checkAPP }"/>
			</div>
			<%--</div> --%>
		</div>
	</div>
		<div id="hiddenValue">
			<input type="hidden" id="oldPrice" name="oldPrice" cname="price" value="${channelConditions.price}"/>
			<input type="hidden" id="oldSize" name="oldSize"  cname="size" value="${channelConditions.size}"/>
			<input type="hidden" id="oldColor" name="oldColor" cname="color" value="${channelConditions.color}"/>
			<input type="hidden" id="oldCategoryNo" name="oldCategoryNo" cname="categoryName" value="${channelConditions.categoryNo}"/>
			<input type="hidden" id="oldBrandNo" name="oldBrandNo"  cname="brandNo"  value="${channelConditions.brandNo}"/>
			<input type="hidden" id="oldGender" name="oldGender" cname="gender" value="${channelConditions.gender}"/>
			<input type="hidden" id="oldOrder" name="oldOrder" cname="order" value="${channelConditions.order}"/>
			<input type="hidden" id="oldPostArea" name="oldPostArea" cname="postArea" value="${channelConditions.postArea}"/>
			<input type="hidden" id="oldStart" name="oldStart" cname="start" value="1"/>
			<input type="hidden" id="oldPageNo" name="oldPageNo" cname="pageNo"  value="1"/>
		</div>
		
	
</rapid:override>
<%--优惠劵弹层  --%>
<rapid:override name="popup_layer">
	<div class="overlay" id="overlay">
		<section class="modal modal-test">
			<div class="modal-hd">成功领取优惠券</div>
			<div class="modal-bd">
				<p>领取成功<br/>
				   您可以在<strong>“我的”</strong>的页面<strong>“优惠券”</strong>中查看
				</p>
			</div>
			<div class="modal-ft">
				<span class="btn-modal">知道了</span>
			</div>
		</section>		
	</div>
</rapid:override>
    


<%@ include file="/WEB-INF/pages/common/common_mall_banner.jsp" %> 

