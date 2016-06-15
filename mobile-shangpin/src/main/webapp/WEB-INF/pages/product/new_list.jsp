<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/brand.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/filterLayer.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/iscroll.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/brand.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/filterLayer.js${ver}")
				.excute(function(){
					
				});
			
	</script>
</rapid:override >

<rapid:override name="page_title">
	  ${brandName}
</rapid:override>
<rapid:override name="content">
		<form id="search_form" action="${ctx}/product/new/list" method="post">
			<input type="hidden" id="price" name="price" value="${searchConditions.price}"/>
			<input type="hidden" id="size" name="size" value="${searchConditions.size}"/>
			<input type="hidden" id="color" name="color" value="${searchConditions.color}"/>
			<input type="hidden" id="colorName" name="colorName" value="${searchConditions.colorName}"/>
			<input type="hidden" id="categoryNo" name="categoryNo" value="${searchConditions.categoryNo}"/>
			<input type="hidden" id="categoryName" name="categoryName" value="${searchConditions.categoryName}"/>
			<input type="hidden" id="brandNo" name="brandNo" value="${brandNo}"/>
			<input type="hidden" id="brandName" name="brandName" value="${brandName}"/>
			<input type="hidden" id="gender" name="gender" value="${searchConditions.gender}"/>
			<input type="hidden" id="order" name="order" value="${searchConditions.order}"/>
			<input type="hidden" id="start" name="start" value="1"/>
			<input type="hidden" id="pageNo" name="pageNo" value="1"/>
		</form>
	
        
       <c:choose>
		<c:when test="${searchResult.brandList[0].imgurl!=null }">
			<div class="brandBanner">
				<img width="320" height="100" alt=""
					src="${searchResult.brandList[0].imgurl}">
			</div>
			<section class="brandBlock">
				<p class="menu" hasTopImg="yes">
		</c:when>
		<c:otherwise>
			<section class="brandBlock">
				<p class="menu" hasTopImg="no">
		</c:otherwise>
	</c:choose> 
        
        
          <span>
            <a href="#" class="category_nav">品类<i></i></a>
            <a href="#" data-list="brandData">品牌<i></i></a>
            <a href="#" data-list="priceData">排序<i></i></a>
          </span>
          <a href="#" class="fillBtn">筛选</a>
        </p>
        
         <c:if test="${fn:length(searchResult.productList) != 0 && queryCondition !=null && queryCondition != '' }">
        	<p class="filter_txt"><span>${queryCondition}</span></p>
        </c:if>
       
        <ul class="brandList clr" id="brandProductList">
         <c:forEach var="product" items="${searchResult.productList}">
				<li>
					
					<c:choose>
                		<c:when test="${checkAPP}">
		                	<a title="${product.brandNameEN}${product.brandNameCN}" href="shangPinApp://phone.shangpin/actiongodetail?productid=${product.productId}">
		                </c:when>
		                <c:otherwise>
		                	<a title="${product.brandNameEN}${product.brandNameCN}" href="<c:url value='/product/detail?productNo=${product.productId}'/>">
		                </c:otherwise>
                	</c:choose>
					
					<c:if test="${product.availableStock< 1}">
						<i class="saleOut">售罄</i>
					</c:if>
					<img width="159" height="212" alt="${product.brandEnName}" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:substring(product.productPicUrl,0,fn:indexOf(product.productPicUrl,'-'))}-318-422.jpg">
					<div class="brandList_info">
						<p>
						  <em>${product.brandNameEN}</em>
						  <em>${product.productName}</em>
						</p>
						<span><em>&yen;
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
						  </em>
						</span>
					</div>
					</a>
				</li>
				
				
        	</c:forEach>
        	  <c:if test="${fn:length(searchResult.productList) == 0 }">
        	 <li>
              <img src="${ctx }/styles/shangpin/images/e.gif" lazy="${ctx }/styles/shangpin/images/flagshop/noProduct.png" width="159" style="height:auto;" alt="暂无商品">
            </li>
        </c:if>
           
        </ul>
         <c:if test="${hasMore == 1}">
        	<a class="list_moreLink" href="javascript:geNewProductMore(${checkAPP});">点击查看更多</a>
        </c:if>
        
    </section>
    
</div>
<script type="cashe" id="brandData">
		 <c:forEach var="brand" items="${brandFilter.brandList}">
			<dd onClick="searchBrand('${brand.brandId}','${brand.brandEnName}')">${brand.brandEnName}</dd>
		</c:forEach>
</script>
<script type="cashe" id="priceData">

	<dd onClick="orderChange('1')">价格从高到低</dd>
	<dd onClick="orderChange('2')">价格从低到高</dd>
</script>     

<!-- 筛选弹出层 -->

<div class="nav_menu">
  <div class="pageContent" id="navContent">
    <div class="pageBox">
    	<dl id="nav_list"></dl>
    </div>
  </div>
  <div class="nav_bg">&nbsp;</div>
</div>

<div class="brand_menu">
  <ul class="filter_select">
       <c:if test="${fn:length(categoryFilter.sexs) > 1 }">
      <li class="first">
      <ul class="brand_tabsMenu">
        <li class="cur">女士</li>
        <li>男士</li>
      </ul>
      </li>
      </c:if>
      
      
          <li>
      <div class="pageContent" id="barndlayer">
        <div class="pageBox">
     
	       <c:if test="${fn:length(categoryFilter.womanFacetCategory) > 0 }"> 
					<dl class="brand_tabsCell"> 
					<dd id="womanCategoryAll" onclick="searchCategory(0,'A01','')">全部</dd> 
					<c:forEach var="womanCategory" items="${categoryFilter.womanFacetCategory}"> 
						<dd id="${womanCategory.id}" onclick="searchCategory(0,'${womanCategory.id}','${womanCategory.name}');">${womanCategory.name}</dd> 
					</c:forEach> 
					</dl> 
			</c:if> 
	        <c:if test="${fn:length(categoryFilter.manFacetCategory) > 0 }"> 
					<dl class="brand_tabsCell"> 
					<dd id="manCategoryAll"  onclick="searchCategory(1,'A02','')">全部</dd> 
					<c:forEach var="manCategory" items="${filterCondition.manFacetCategory}"> 
						<dd id="${manCategory.id}" onclick="searchCategory(1,'${manCategory.id}','${manCategory.name}');">${manCategory.name}</dd> 
					</c:forEach> 
					</dl> 
			</c:if> 
        </div>
      </div>
      </li>
  </ul>
</div>

<div id="filter_layer">
  <div id="filter_box">
    <div class="filter_content">
      
      <a href="#" class="close_Btn" id="filter_close"></a>
      <h2><a href="#" id="clearBtn">清除</a> <a href="#" id="finishBtn">完成</a></h2>
      <ul class="filter_select">
          <li class="first">
          <ul class="layer_tabsMenu">
        						 <c:if test="${fn:length(otherFilter.priceList) > 0 }">
									<li id="priceItem">价格</li>
								</c:if>
     							
								<c:if test="${fn:length(otherFilter.sizeList) > 0 }">
									<li id="sizeItem">尺寸</li>
								</c:if>
								<c:if test="${fn:length(otherFilter.colorList) > 0 }">
									<li id="colorItem">颜色</li>
								</c:if>
          </ul>
          </li>
          <li>
          <div class="pageContent" id="layerContent">
            <div class="pageBox">
            
            <dl class="layer_tabsCell">
               <dd id="priceAll">全部</dd>
										
				<c:forEach var="price" items="${otherFilter. priceList}">
					<dd id="${price.among}" onclick="searchKey('price','${price.among}');">${price.among}</dd>
				</c:forEach>
              </dl>
             <dl class="layer_tabsCell">
               <dd id="sizeAll">全部</dd>
										<c:forEach var="size" items="${otherFilter.sizeList}">
											<dd id="${size.sizeCode}" onclick="searchKey('size','${size.sizeCode}');">${size.sizeCode}</dd>
										</c:forEach>
              </dl>
               <dl class="layer_tabsCell">
              										<dd id="colorAll">全部</dd>
										<c:forEach var="color" items="${otherFilter.colorList}">
											<dd id="${color.id}"
												onclick="searchKey('color','${color.id}-${color.name}');">${color.name}</dd>
										</c:forEach>
              </dl>
            </div>
          </div>
          </li>
      </ul>
      
    </div>
  </div>
</div>
<!-- 筛选弹出层 end -->
<c:choose>
<c:when test="${checkWx }">
	<input type="hidden" id="isWeixin" name="isWeixin" value="0"/>
</c:when>
<c:otherwise>
	<input type="hidden" id="isWeixin" name="isWeixin" value="1"/>
</c:otherwise>
</c:choose>
</rapid:override>
<rapid:override name="footer">
  	
 </rapid:override>
 <rapid:override name="down_page">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/weixin_common.jsp" %> 