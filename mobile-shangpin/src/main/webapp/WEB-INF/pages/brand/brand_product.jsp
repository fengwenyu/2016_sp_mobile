<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/201502brand/brand.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/swiper.min.css" rel="stylesheet" />
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
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/swiper.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/201502brand/filterBrand.js${ver}")
			.excute(function(){
			});
	</script>
</rapid:override >

<%-- 浏览器标题 --%>
<rapid:override name="title">
	${brandShop.operat.head.name }
</rapid:override>
<rapid:override name="page_title">
	${brandShop.operat.head.name }
</rapid:override>

<%--内容体 --%>
<rapid:override name="content">
	<form id="search_form" action="${ctx}/brand/product/list_${searchConditions.brandNo}#navLine" method="post">
		<input type="hidden" id="price" name="price" value="${searchConditions.price}"/>
		<input type="hidden" id="size" name="size" value="${searchConditions.size}"/>
		<input type="hidden" id="color" name="color" value="${searchConditions.color}"/>
		<input type="hidden" id="colorName" name="colorName" value="${searchConditions.colorName}"/>
		<input type="hidden" id="categoryNo" name="categoryNo" value="${searchConditions.categoryNo}"/>
		<input type="hidden" id="categoryName" name="categoryName" value="${searchConditions.categoryName}"/>
	<%--	<input type="hidden" id="brandNo" name="brandNo" value="${searchConditions.brandNo}"/>--%>
		<input type="hidden" id="brandName" name="brandName" value="${searchConditions.brandName}"/>
		
		<input type="hidden" id="gender" name="gender" value="${searchConditions.gender}"/>
		<input type="hidden" id="order" name="order" value="${searchConditions.order}"/>
		<input type="hidden" id="postArea" name="postArea" value="${searchConditions.postArea}"/>
		<input type="hidden" id="start" name="start" value="1"/>
		<input type="hidden" id="pageNo" name="pageNo" value="1"/>
	</form>
	<input type="hidden" id="brandNo" name="brandNo" value="${searchConditions.brandNo}"/>
	 <%-- 头图 --%> 
	<%--  <c:if test="${brandShop.operat.head.logo!=null && brandShop.operat.head.logo!='' }"> --%>
	 <%-- 分享文案 --%> 
	 <c:if test="${brandShop.operat.head.share!=null}">
	 		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${brandShop.operat.head.share.url}"/>
	 		<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="${brandShop.operat.head.share.title} ${brandShop.operat.head.share.desc}"/>
			 <input type="hidden" name="_mainDesc"  id="_mainDesc"  value="${brandShop.operat.head.share.title}"/>
			 <input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${fn:replace(brandShop.operat.head.share.pic,'-10-10','-640-256') }"/>
	 </c:if>
	<%-- 品牌 --%> 
	 <div class="about-brand">
      	<div class="top-img-mask"></div>
      	<c:if test="${brandShop.operat.head.logoNew!=null && brandShop.operat.head.logoNew.pic!='' }">
        <img src="${fn:substring(brandShop.operat.head.logoNew.pic,0,fn:indexOf(brandShop.operat.head.logoNew.pic,'-'))}-640-314.jpg" width="100%">
        </c:if>
        <div class="top-img-text">
           <h1>${brandShop.operat.head.name }</h1>
           <!--<h2>旗舰店</h2>-->
        </div>
        <div class="about-brand-btn">
          <c:choose>
				<c:when test="${brandShop.operat.head.isCollected=='1' }">
					<a href="javascript:;" dataId="${brandShop.operat.head.id }" class="follow_btn followed_btn"> √  已关注</a>
				</c:when>
				<c:otherwise>
					<a href="javascript:;"  dataId="${brandShop.operat.head.id }" class="follow_btn">  关注</a>
				</c:otherwise>
			</c:choose>
					<a href="${ctx }/brandStory/index?id=${brandShop.operat.head.id }&type=0" class="">品牌故事</a>
        </div>
      </div>
        <c:if test="${(brandShop.operat.head.fansCount>0) && (brandShop.operat.head.goodsCount >0)}">
      <ul class="brand-store-num">
        <li>
          <em>粉丝数</em>
          <span>${brandShop.operat.head.fansCount }</span>
        </li>
        <li>
          <em>商品数</em>
          <span>${brandShop.operat.head.goodsCount }</span>
        </li>
      </ul>
      </c:if>
      
       <!-- 焦点图 Start -->
      <%@ include file="/WEB-INF/pages/brand/focus.jsp"%>
      <!-- 焦点图 End -->
      
 <%-- 	 </c:if> --%>
	<%--优惠券 --%>
	<c:if test="${fn:length(brandShop.operat.coupon) > 0}">
		<ul class="coupon-list clr">
			<c:forEach var="coupon" items="${brandShop.operat.coupon}">
				<c:if test="${coupon.isValid=='0'}">
					<li id="${coupon.code}"><img src="${fn:substring(coupon.pic,0,fn:indexOf(coupon.pic,'-'))}-200-95.jpg" /></li>
				</c:if>
			</c:forEach>
		</ul>
		<div class="line"></div>
	</c:if>
	
 <%--模板广告 --%>
	<c:if test="${fn:length(brandShop.operat.modelOne.list) > 0}">
		<c:forEach var="modelOne" items="${brandShop.operat.modelOne.list}"
			varStatus="status">
			<c:if test="${fn:length(modelOne.type) > 0}">
				<c:choose>
					<c:when test="${modelOne.type eq '1'}">
						<div class="one-list">
							<%@ include file="/WEB-INF/pages/brand/model_list.jsp"%>
						</div>
					</c:when>
					<c:when test="${modelOne.type eq '2'}">
						<ul class="two-list clr">
							<%@ include file="/WEB-INF/pages/brand/model_list.jsp"%>
						</ul>
					</c:when>
					<c:when test="${modelOne.type eq '3'}">
						<ul class="three-list clr">
							<%@ include file="/WEB-INF/pages/brand/model_list.jsp"%>
						</ul>
					</c:when>
					<c:when test="${modelOne.type eq '4'}">
						<ul class="four-list clr">
							<%@ include file="/WEB-INF/pages/brand/model_list.jsp"%>
						</ul>
					</c:when>
					<c:when test="${modelOne.type eq '5'}">
						<ul class="five-list clr">
							<%@ include file="/WEB-INF/pages/brand/model_list.jsp"%>
						</ul>
					</c:when>
					<c:when test="${modelOne.type eq '100'}">
						<div class="one-two-list">
							<%@ include file="/WEB-INF/pages/brand/model_list.jsp"%>
						</div>
					</c:when>
					<c:when test="${modelOne.type eq '101'}">
						<div class="two-one-list">
							<%@ include file="/WEB-INF/pages/brand/model_list.jsp"%>
						</div>
					</c:when>
				</c:choose>
			</c:if>
		</c:forEach>
	</c:if>
	<div class="line"><span id="menuLine"></span></div>
   <!-- 滑块列表 -->  
	<div class="tabSlider" id="navLine"> 
		<div class="menu-nav">
				<ul class="tabSlider-hd" id="list_menu">
					<li id="defaultOrder"  onClick="orderChange('')"><a>默认</a></li>
					<li id="orderOne"  onClick="orderChange('3')"><a>新品</a></li>
				
					<%-- <c:if test="${searchResult.abroad=='1'}">
						<li id="abroadOrder"  onClick="orderChange('9')"><a>海外购</a></li>
					</c:if> --%>
					<li id="orderTwo" onClick="orderChange('5')"><a>销量</a></li>
					<li id="orderThree"class="price-btn" ><a href="">价格</a></li>
				</ul> 
			
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
						<c:forEach var="category" items="${brandShop.result.categoryList}" varStatus="status">
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
						<li id="5000-10000" onclick="searchKey('price','5000-10000');">5000-10000</li>
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
			<input type="hidden" id="oldPrice" name="oldPrice" cname="price" value="${searchConditions.price}"/>
			<input type="hidden" id="oldSize" name="oldSize"  cname="size" value="${searchConditions.size}"/>
			<input type="hidden" id="oldColor" name="oldColor" cname="color" value="${searchConditions.color}"/>
			<input type="hidden" id="oldColorName" name="oldColorName" cname="colorName" value="${searchConditions.colorName}"/>
			<input type="hidden" id="oldCategoryNo" name="oldCategoryNo" cname="categoryName" value="${searchConditions.categoryNo}"/>
			<input type="hidden" id="oldCategoryName" name="oldCategoryName" cname="categoryName" value="${searchConditions.categoryName}"/>
			<input type="hidden" id="oldBrandNo" name="oldBrandNo"  cname="brandNo"  value="${searchConditions.brandNo}"/>
			<input type="hidden" id="oldBrandName" name="oldBrandName" cname="brandName"  value="${searchConditions.brandName}"/>
			<input type="hidden" id="oldGender" name="oldGender" cname="gender" value="${searchConditions.gender}"/>
			<input type="hidden" id="oldOrder" name="oldOrder" cname="order" value="${searchConditions.order}"/>
			<input type="hidden" id="oldPostArea" name="oldPostArea" cname="postArea" value="${searchConditions.postArea}"/>
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
    
</div>

<%@ include file="/WEB-INF/pages/common/common_mall_banner.jsp" %> 

