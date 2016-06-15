<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/201502brand/brand.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js?${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/201502brand/iscroll.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/tabslider.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/201502brand/brand.js${ver}");
	</script>
</rapid:override >
<rapid:override name="appLayer">
	
</rapid:override>
<rapid:override name="page_title">
	<c:choose>
		<c:when test="${categoryName == null || categoryName == ''}">
			${parentCategoryName }
		</c:when>
		<c:otherwise>
			${categoryName }
		</c:otherwise>
	</c:choose>
</rapid:override>
<rapid:override name="search_form">
	
</rapid:override>
<rapid:override name="content">
	<form id="search_form" action="${ctx}/category/product/list" method="post">
		<input type="hidden" id="price" name="price" value="${searchConditions.price}"/>
		<input type="hidden" id="size" name="size" value="${searchConditions.size}"/>
		<input type="hidden" id="color" name="color" value="${searchConditions.color}"/>
		<input type="hidden" id="colorName" name="colorName" value="${searchConditions.colorName}"/>
		<input type="hidden" id="categoryNo" name="categoryNo" value="${categoryNo}"/>
		<input type="hidden" id="categoryName" name="categoryName" value="${categoryName}"/>
		<input type="hidden" id="brandNo" name="brandNo" value="${searchConditions.brandNo}"/>
		<input type="hidden" id="brandName" name="brandName" value="${searchConditions.brandName}"/>
		<input type="hidden" id="gender" name="gender" value="${gender }"/>
		<input type="hidden" id="order" name="order" value="${searchConditions.order}"/>
		<input type="hidden" id="postArea" name="postArea" value="${searchConditions.postArea}"/>
		<input type="hidden" id="start" name="start" value="1"/>
		<input type="hidden" id="pageNo" name="pageNo" value="1"/>
		<input type="hidden" id="parentCategoryNo" name="parentCategoryNo" value="${parentCategoryNo }"/>
		<input type="hidden" id="parentCategoryName" name="parentCategoryName" value="${parentCategoryName }"/>
	</form>
	<input type="hidden" id="totalPage" name="totalPage" value="${totalPage}"/>
	<input type="hidden" id="checkApp" name="checkApp" value="${checkAPP}"/>
	<input type="hidden" id="defaultCategoryNo" name="defaultCategoryNo" value="${defaultCategoryNo}"/>
	<input type="hidden" id="defaultCategoryName" name="defaultCategoryName" value="${defaultCategoryName}"/>
	
	<!-- 滑块列表 -->  
	<div id="" class="tabSlider"> 
		<div class="menu-nav">
				<ul class="tabSlider-hd" id="list_menu">
					<li id="defaultOrder" class="curr"><a>默认</a></li>
					<li id="normalOrder"><a>新品</a></li>
				<%-- 	<c:if test="${searchResult.abroad=='1'}">
						<li id="abroadOrder"><a>海外购</a></li>
					</c:if> --%>
					<li id="hotOrder"><a>销量</a></li>
					<li class="price-btn" id="treeOrder"><a>价格</a></li>
				</ul> 
		</div>
	
		<div class="list-box">
			<div class="prod_list clr" id="prodList">
			
				<c:forEach var="product" items="${searchResult.productList}" varStatus="status">
					<div class="list_box">
						<c:choose>
							<c:when test="${checkAPP}">
								<a href="shangPinApp://phone.shangpin/actiongodetail?productid=${product.productId}">
							</c:when>
						<c:otherwise>
							<a href="<c:url value='/product/detail?productNo=${product.productId}&picNo=${product.picNo}'/>" onclick="_smq.push(['custom',${product.productId},${searchConditions.categoryName},${product.productName}]);">
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
							<p>${product.prefix}${product.productName}${product.suffix }</p>
							
							<span class="item-detail"> 
								<strong class="refer-price" style="color:${product.priceColor}">
								<b>${product.priceTitle }</b>
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
								<c:if test="${(product.collections!=null && product.collections!=-1) || (product.comments!=null && product.comments!=-1) }">
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
							<!--<label class="active_price">店庆价<em>&yen;1490</em></label>-->
						</div>
						
					</div>
				</c:forEach>
				<c:choose>
					<c:when test="${fn:length(searchResult.productList) == 0 && searchConditions.postArea == '2'}">
						<p class="brand_noresults">暂无海外购商品</p>
					</c:when>
					<c:when test="${fn:length(searchResult.productList) == 0 && searchConditions.postArea != '2'}">
						<p class="brand_noresults">没有筛选结果，重新筛选下吧</p>
					</c:when>
					<c:otherwise>
						
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</rapid:override>
<rapid:override name="filter_layer">
	<!-- 筛选弹出层  -->
	<div id="filter_layer">
		<div id="filter_box">
			<h2 class="filter_menu"><a href="#" class="close_Btn" id="filter_close"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/201502brand/sx_icon.png" height="20"  /></a><p>筛选</p></h2>
				<div class="filter_content">
					<c:if test="${fn:length(searchFacet.category) > 0}">
						<div class="category-box">
							<h3>品类</h3>
							<ul>
								<c:forEach var="category" items="${searchFacet.category}" varStatus="category_stu">
									<c:choose>
										<c:when test="${category_stu.index == 0 }">
											<li class="cur" onclick="selectCategory('${category.id}','${category.name}',this)" id="${category.id}">${category.name}</li>
										</c:when>
										<c:otherwise>
											<li onclick="selectCategory('${category.id}','${category.name}',this)" id="${category.id}">${category.name}</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					<input type="hidden" id="brandList"/>
					<c:if test="${fn:length(searchResult.brandList) > 0}">
						<div class="category-box brandList">
							<h3>品牌</h3>
							<ul>
								<c:forEach var="brand" items="${searchResult.brandList}">
									<li onclick="selectBrand('${brand.id}')" id="${brand.id}">${brand.nameEN}</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					<input type="hidden" id="sizeList"/>
					<c:if test="${fn:length(searchResult.sizeList) > 0}">
						<div class="category-box sizeList">
				    		<h3>尺码</h3>
							<ul>
								<c:forEach var="size" items="${searchResult.sizeList}">
									<li onclick="selectSize('${size.id}');" id="${size.id}">${size.id}</li>
								</c:forEach>
							</ul>
					    </div>
					</c:if>
				    <input type="hidden" id="colorList"/>
				    <c:if test="${fn:length(searchResult.colorList) > 0}">
				    	<div class="color-box colorList">
					    	<h3>颜色</h3>
					        <ul>
				                <c:forEach var="color" items="${searchResult.colorList}">
									 <li onclick="selectColor('${color.id}','${color.name}')" id="${color.id}">
							          	<i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/201502brand/color_cur.png" /></i>
							          	<c:choose>
							          		<c:when test="${color.rgb == '#ffffff' || color.rgb == '#FFFFFF'}">
							          			<a href="javascript:;" class="white-color" style="background: ${color.rgb };"></a>
							          		</c:when>
							          		<c:when test="${color.rgb == null || color.rgb == '' }">
							          			<a href="javascript:;" class="all-color"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/201502brand/other_color.jpg" /></a>
							          		</c:when>
							          		<c:otherwise>
							          			<a href="javascript:;" class="black-color" style="background: ${color.rgb };"></a>
							          		</c:otherwise>
							          	</c:choose>
							          	
							            <span id="${color.id}">${color.name}</span>
							         </li>
								</c:forEach>
					        </ul>
					    </div>
				    </c:if>
				    <div class="category-box" id="priceItem">
				    	<h3>价格区间</h3>
				        <ul>
				          <li onclick="selectPrice('0-500')" id="0-500">0-500</li>
				          <li onclick="selectPrice('500-1000')" id="500-1000">500-1000</li>
				          <li onclick="selectPrice('1000-2000')" id="1000-2000">1000-2000</li>
				          <li onclick="selectPrice('2000-5000')" id="2000-5000">2000-5000</li>
				          <li onclick="searchKey('price','5000-10000');" id="5000-10000">5000-10000</li>
				          <li onclick="selectPrice('10000-')" id="10000-">>10000</li>
				        </ul>
				    </div>
				    <a href="javacript:;" id="finishBtn">确定</a>
				</div>
		</div>
	</div>
		<div id="hiddenValue2">
			<input type="hidden" id="oldPrice" name="oldPrice" cname="price" value="${searchConditions.price}"/>
			<input type="hidden" id="oldSize" name="oldSize"  cname="size" value="${searchConditions.size}"/>
			<input type="hidden" id="oldColor" name="oldColor" cname="color" value="${searchConditions.color}"/>
			<input type="hidden" id="oldColorName" name="oldColorName" cname="colorName" value="${searchConditions.colorName}"/>
			<input type="hidden" id="oldCategoryNo" name="oldCategoryNo" cname="categoryName" value="${categoryNo}"/>
			<input type="hidden" id="oldCategoryName" name="oldCategoryName" cname="categoryName" value="${categoryName}"/>
			<input type="hidden" id="oldBrandNo" name="oldBrandNo"  cname="brandNo"  value="${searchConditions.brandNo}"/>
			<input type="hidden" id="oldBrandName" name="oldBrandName" cname="brandName"  value="${searchConditions.brandName}"/>
			<input type="hidden" id="oldGender" name="oldGender" cname="gender" value="${searchConditions.gender}"/>
			<input type="hidden" id="oldOrder" name="oldOrder" cname="order" value="${searchConditions.order}"/>
			<input type="hidden" id="oldPostArea" name="oldPostArea" cname="postArea" value="${searchConditions.postArea}"/>
			<input type="hidden" id="oldStart" name="oldStart" cname="start" value="1"/>
			<input type="hidden" id="oldPageNo" name="oldPageNo" cname="pageNo"  value="1"/>
		</div>
		
	
</rapid:override>


<%@ include file="/WEB-INF/pages/common/common_mall_banner.jsp" %> 