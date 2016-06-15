<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<%--此页面还有活动促销标未加，大伟说先不加 --%>
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
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/201502brand/lable.js${ver}");
	</script>
</rapid:override >

 <%-- 浏览器标题 --%>
<%--<rapid:override name="title">
	${activityIndex.operat.head.name}
</rapid:override>

页标题
<rapid:override name="page_title">
	${activityIndex.operat.head.name}
</rapid:override> --%>
<rapid:override name="content">
	<form id="search_form" action="${ctx}/lable/product/list?tagId=${searchConditions.tagId}#navLine" method="post">
		<input type="hidden" id="filters" name="filters" value="${searchConditions.filters}"/>
		<input type="hidden" id="type" name="type" value="${searchConditions.type}"/>
		<input type="hidden" id="order" name="order" value="${searchConditions.order}"/>
	
		<input type="hidden" id="start" name="start" value="1"/>
	</form>
		<input type="hidden" id="tagId" name="tagId" value="${searchConditions.tagId}"/>
	<div class="line"><span id="menuLine"></span></div>

	 <!-- 滑块列表 -->  
	<div class="tabSlider" id="navLine"> 
		<div class="menu-nav">
			<div class="menu-nav-left"> 
				<ul class="tabSlider-hd" id="list_menu">
					<li id="defaultOrder"  onClick="orderChange('')"><a>默认</a></li>
					<li id="orderOne"  onClick="orderChange('3')"><a>新品</a></li>
					<%-- <c:if test="${!checkWX}">
						<c:if test="${searchResult.abroad=='1'}">
							<li id="abroadOrder"  onClick="orderChange('9')"><a>海外购</a></li>
						</c:if>
					</c:if> --%>
					<li id="orderTwo" onClick="orderChange('5')"><a>销量</a></li>
					<li id="orderThree" class="price-btn" ><a href="">价格</a></a></li>
				</ul> 
			</div>
<!-- 			<a href="javascript:;" class="fillBtn">筛选</a> -->
		</div>
	
		<div class="list-box">
			<div class="prod_list clr">
				<c:forEach var="product" items="${searchResult.productList}" varStatus="status">
					<div class="list_box">
						
					<c:choose>
						<c:when test="${checkAPP}">
								<a href="shangPinApp://phone.shangpin/actiongodetail?productid=${product.productId}">
						</c:when>
						<c:otherwise>
								<a href="<c:url value='/product/detail?productNo=${product.productId}&picNo=${product.picNo}'/>"  onclick="_smq.push(['custom',${product.productId},'',${product.productName}]);">
						</c:otherwise>
					</c:choose>
						<c:if test="${product.postArea=='2' }">
							<c:if test="${product.countryPic!=null && product.countryPic!='' }">
								<i class="item-country"><img src="${product.countryPic }" style="opacity: 1;"></i> 
							</c:if>	
						</c:if>
						<c:if test="${searchConditions.order=='5' && status.index<10 }">
						<i class="c_tag"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/201502brand/hot_icon0${status.index+1 }.png" style="opacity: 1;"></i>
					</c:if>
						<c:if test="${!(searchConditions.order=='5' && status.index<10) }">
							<c:if test="${product.productStatus=='1' }">
								<i class="item-status">售罄</i>
							</c:if>
							<c:if test="${product.productStatus =='2' }">
								<i class="item-status new-item">新品</i>
							</c:if>
						</c:if>
						<c:if test="${product.promoLogo!=null && product.promoLogo!='' }">
							<i class="item-activity"><img src="${product.promoLogo }" /></i>
						</c:if>
						<c:if test="${product.expressLogo!=null && product.expressLogo!='' }">
							<i class="overseas-symbol"><img src="${product.expressLogo}" style="opacity: 1;"></i>
						</c:if>
							<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"  lazy="${fn:substring(product.pic,0,fn:indexOf(product.pic,'-'))}-320-426.jpg" style="opacity: 1;"/>
						</a>
					
						<div class="li_text">
							<h5>${product.brandNameEN}</h5>
							<p>${product.prefix}${product.productName}${product.suffix}</p>
							<span class="item-detail"> 
								<strong class="refer-price" style="color:${product.priceColor}">
								¥
								<c:choose>
									<c:when test="${product.status == '0001' || product.status == '000100' }">
										${product.promotionPrice }
									</c:when>
									<c:otherwise>
											<c:choose>
												<c:when test="${product.isSupportDiscount==1 }">
													<c:choose>
														<c:when test="${userLv == '0002'}">
														${product.goldPrice}
														</c:when>
														<c:when test="${userLv == '0003'}">
															${product.platinumPrice}
														</c:when>
														<c:when test="${userLv == '0004'}">
															${product.diamondPrice}
														</c:when>
														<c:otherwise>
															${product.limitedPrice}
														</c:otherwise>
													</c:choose>
												</c:when>
											<c:otherwise>
												${product.limitedPrice  }
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								</strong>
								<em class="promotion-price" style="color:${product.descColor}">${product.priceDesc}</em>
								<c:if test="${(product.collections==null ||product.collections=='-1') && (product.comments==null ||product.comments=='-1') }">
									<div class="item-fame">
										<c:if test="${product.collections!=null && product.collections!='-1'}">
											<span class="fame-views"> <i class="fame-views-icon"><img
													src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home/icon13.png"></i> <em class="fame-views-num">${product.collections }</em>
											</span>
										</c:if>	
										<c:if test="${product.comments!=null && product.comments!='-1'}">
										 <span class="fame-comments"> <i class="fame-comments-icon"><img
												src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home/icon12.png"></i> <em
											class="fame-comments-num">${product.comments }</em>
										 </span>
										</c:if>
									</div>
								</c:if>
							</span>
												
						</div>
					</div>
				</c:forEach>
			</div>
		
					<c:if test="${fn:length(searchResult.productList) == 0}">
						<p class="brand_noresults">没有筛选结果，重新筛选下吧</p>
					</c:if>
				
	</div>
	</div>
</rapid:override> 

<%--筛选弹出层  --%>
<%-- <rapid:override name="filter_layer">
	<div id="filter_layer">
		<div id="filter_box">
			<h2 class="filter_menu"><a href="#" class="close_Btn" id="filter_close"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/201502brand/sx_icon.png" height="20"  /></a><p>筛选</p></h2>
			<div class="filter_content">
			
			<c:forEach var="attributes" items="${searchResultFliter.attributes }" varStatus="status">
			<c:choose>
			<c:when test="${status.index==0}">
			<div id="attributesItemFirst">
				<div class="category-box"  id="attributesItem">
					<h3>${attributes.desc }</h3>
					<ul id="${attributes.name}">
						<c:forEach var="value" items="${attributes.value}" >
								<li id ="${value.id}" onclick="searchFilter('${value.id}','${attributes.name}','0');">${value.name}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			</c:when>
			<c:otherwise>
			<div id="attributesItemNotf">
				<div class="category-box"  id="attributesItem">
						<h3>${attributes.desc }</h3>
						<ul id="${attributes.name}">
							<c:forEach var="value" items="${attributes.value}" >
									<li id ="${value.id}" onclick="searchFilter('${value.id}','${attributes.name}','1');">${value.name}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			 </c:otherwise>
			</c:choose> 
			</c:forEach>
			<div id="filtratesAll">
				<c:forEach var="filtrates" items="${searchResultFliter.filtrates }">
					<div class="category-box"  id="filterItem">
						<h3>${filtrates.desc }</h3>
						<ul id="${filtrates.name}">
							<c:forEach var="value" items="${filtrates.value}" varStatus="status">
									<li id ="${value.id}" onclick="searchFilter('${value.id}','${filtrates.name}','2');">${value.name}</li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
			</div>
				<div class="category-box" id="priceItem">
					<h3>价格区间</h3>
					<ul>
						<li id="0-500"  onclick="searchKey('price','0-500');">0-500</li>
						<li id="500-1000" onclick="searchKey('price','500-1000');">500-1000</li>
						<li id="1000-2000" onclick="searchKey('price','1000-2000');">1000-2000</li>
						<li id="2000-5000" onclick="searchKey('price','2000-5000');">2000-5000</li>
						<li id="5000-10000" onclick="searchKey('price','5000-10000');">5000-10000</li>
						<li id="10000"  onclick="searchKey('price','10000');">>10000</li>
					</ul>
				</div> 
				<a href="javacript:;" id="finishBtn">确定</a>
				<input type="hidden" id="hasPageNum" name="hasPageNum" value="${hasPageNum }"/>
				<input type="hidden" id="checkApp" name="checkApp" value="${checkAPP }"/>	
			</div>
		</div>
		</div>
			<div id="hiddenValue2">
		<input type="hidden" id="oldFilters" name="oldFilters" value="${searchConditions.filters}"/>
		<input type="hidden" id="oldType" name="oldType" value="${searchConditions.type}"/>
		<input type="hidden" id="oldOrder" name="oldOrder" value="${searchConditions.order}"/>
		<input type="hidden" id="oldTagId" name="oldTagId" value="${searchConditions.tagId}"/>
		<input type="hidden" id="oldStart" name="oldStart" value="1"/>
		</div>
		
</rapid:override> --%>

<rapid:override name="filter_layer">
	<div id="filter_layer">
		<div id="filter_box">
			<h2 class="filter_menu"><a href="#" class="close_Btn" id="filter_close"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/201502brand/sx_icon.png" height="20"  /></a><p>筛选</p></h2>
			<div class="filter_content">
			<div id="attributesItemFirst">
				<div class="category-box"  id="categoryItem">
					<h3>品类</h3>
					<ul id="category">
						<c:forEach var="category" items="${searchResultFliter.categoryList}" varStatus="status">
							<c:choose>
								<c:when test="${status.index==0}">
									<li id ="${category.id}" class="first_yes" firstFlag="yes" onclick="searchFilter('${category.id}','category','0');" idFlag="${category.id}" nameFlag="${category.name}">${category.name}</li>
								</c:when>
								<c:otherwise>
									<li id ="${category.id}" firstFlag="no" onclick="searchFilter('${category.id}','category','0');">${category.name}</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div id="attributesItemNotf">	
				<div class="category-box"  id="brandItem">
					<h3>品牌</h3>
					<ul id="brand">
					</ul>
				</div>
			</div>	
			<div id="filtratesAll">
				<div class="category-box"  id="sizeItem">
					<h3>尺码</h3>
					<ul id="size">
					</ul>
				</div>
				
				<div class="color-box" id="colorItem">
					<h3>颜色</h3>
					<ul id="color">
					</ul>
				</div>
				
				<div class="category-box" id="priceItem">
					<h3>价格区间</h3>
					<ul id="price">
						<li id="0-500"  onclick="searchFilter('0-500','price','2');">0-500</li>
						<li id="500-1000" onclick="searchFilter('500-1000','price','2');">500-1000</li>
						<li id="1000-2000" onclick="searchFilter('1000-2000','price','2');">1000-2000</li>
						<li id="2000-5000" onclick="searchFilter('2000-5000','price','2');">2000-5000</li>
						<li id="5000-10000" onclick="searchFilter('5000-10000','price','2');">5000-10000</li>
						<li id="10000"  onclick="searchFilter('10000','price','2');">>10000</li>
					</ul>
				</div>
				<div class="category-box" id="postAreaItem">
					<h3>价格区间</h3>
					<ul id="postArea">
					
					</ul>
				</div>
			</div>
				<a href="javacript:;" id="finishBtn">确定</a>
				<input type="hidden" id="hasPageNum" name="hasPageNum" value="${hasPageNum }"/>
				<input type="hidden" id="checkApp" name="checkApp" value="${checkAPP }"/>	
			</div>
		</div>
		</div>
		<div id="hiddenValue2">
		<input type="hidden" id="oldFilters" name="oldFilters" value="${searchConditions.filters}"/>
		<input type="hidden" id="oldType" name="oldType" value="${searchConditions.type}"/>
		<input type="hidden" id="oldOrder" name="oldOrder" value="${searchConditions.order}"/>
		<input type="hidden" id="oldTagId" name="oldTagId" value="${searchConditions.tagId}"/>
		<input type="hidden" id="oldStart" name="oldStart" value="1"/>
		</div>
	</rapid:override>
<%@ include file="/WEB-INF/pages/common/common_mall_banner.jsp" %> 

