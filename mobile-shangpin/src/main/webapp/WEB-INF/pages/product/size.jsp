<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/size.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/iscroll.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/size.js${ver}")
		.excute(function(){			
			var size='${fn:length(sizeInfo.productdetailsize.table)}';
			boxW = (parseInt(size)-1)*65+95;


			//li宽度宽度大于320事件
			if(parseInt(boxW) > 320){
				$("#tab").css("width",boxW+"px");
				$("#sizeli").css("width",boxW+"px");
				loaded();
			}else{
				$(".tab_content table td:not(:first-child)").css("width","auto");
			}
			$('#J_m-slider').slider({wrap:".slider-outer",wrapUl:".slider-wrap",wrapStatus:'.slider-status',isLoop:true,isPlay:true});
		});
	</script>
</rapid:override>

<rapid:override name="page_title">
	尺码信息
</rapid:override>

<rapid:override name="content">

		<c:if test="${fn:length(sizeInfo.productindex) > 0}">
			<section class="spSizeInfo">
				<h2>商品指数</h2>
				<c:forEach var="productIndex" items="${sizeInfo.productindex}" varStatus="productIndex_status">
					<dl>
						<dt>${productIndex.title}：</dt>
						<dd>
							<c:forEach var="values" items="${productIndex.values}" varStatus="values_status">
								<c:choose>
									<c:when test="${values_status.index == productIndex_status.index}">
										<em>${values.name}</em>
									</c:when>
									<c:otherwise>
										<span>${values.name}</span>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</dd>
					</dl>
				</c:forEach>
			</section>
		</c:if>
		
		<c:if test="${fn:length(sizeInfo.productdetailsize.table) > 0 || fn:length(sizeInfo.productdetailsize.sizepiclist) > 0 || sizeInfo.productdetailsize.pic != ''}">
			<c:if test="${fn:length(sizeInfo.productdetailsize.table) > 0}">
				<section class="spSizeInfo">
					<h2>详细尺码（测量单位：CM）　<a href="#size">测量说明</a></h2>
					<ul class="tab_content" id="tabContent">
						<li id="sizeli" >
							<table id="tab">
								<c:forEach var="tables" items="${sizeInfo.productdetailsize.table}">
									<tr align="center">
										<c:forEach var="table_value" items="#{tables}">
											<td>${table_value}</td>
										</c:forEach>
									</tr>   
								</c:forEach>		
							</table>
						</li>
					</ul>  
				</section>
			</c:if>
			<c:if test="${fn:length(sizeInfo.productdetailsize.sizepiclist) > 0}">
				<section class="spSizeInfo">
					<h2>详细尺码（测量单位：CM）　</h2>
					<c:forEach var="sizepic" items="${sizeInfo.productdetailsize.sizepiclist}">
						<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"  lazy="${sizepic}" width="320">
					</c:forEach>
				</section> 
			 </c:if>
		
			 <c:if test="${fn:length(sizeInfo.productdetailsize.table) > 0 || sizeInfo.productdetailsize.pic != ''}">
				<section id="pic" class="spSizeInfo">
					<a id="size" name="size"></a>
					<h2>测量说明</h2>
					<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"  lazy="${sizeInfo.productdetailsize.pic}" width="320">
				</section>
			</c:if>
		</c:if> 

</rapid:override>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 