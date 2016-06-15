<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/detail.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/product/product_detail.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/css3.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/slideLayer.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/detail.js${ver}")
				
				.excute(function(){
					isHome(false);
					$('#J_m-slider').slider({wrap:".slider-outer",wrapUl:".slider-wrap",wrapStatus:'.slider-status',isLoop:true,prev:".prev",next:".next"});
				});
	</script>
	
</rapid:override>

<rapid:override name="content">
	<%--头部 --%>
	<%--最新特卖 --%>
	<c:if test="${pageType!=null && pageType!='' }">
		<c:if test="${pageType == '1' }">
			<c:import url="/nav?navId=8&parentParams=4_activityId:${activityId},typeFlag:${typeFlag},pageType:${pageType}"></c:import>
		</c:if>
		<%--限时特卖 --%>
		<c:if test="${pageType == '2' }">
			<c:import url="/nav?navId=20&parentParams=18_activityId:${activityId},typeFlag:${typeFlag},pageType:${pageType}"></c:import>
		</c:if>
	</c:if>
	
	<%--BODY --%>
	<div class="alContent">
		<section class="detail_block">
			<header>
				<h3>${merchandise.brand}<em>${merchandise.name}</em></h3>
      			<span>商品编号：${merchandise.goodscode}</span>
	  		</header>
	  		
	  		<%--图片轮播 --%>
	  		<div class="content" id="J_m-slider">
	  			<div class="slider-outer">
	  				<ul class="slider-wrap">
	  					<c:forEach items="${merchandise.pics}" var="pic">
		  					<li>
		  						<img alt="" width="225" height="300" src="${ctx}/styles/shangpin/images/e.gif" lazy="${pic}">
		  					</li>
	  					</c:forEach>
	  				</ul>
	  			</div>
	  			<div class="prev pg-btn"><a>上一页</a></div>
        		<div class="slider-status" ></div>
        		<div class="next pg-btn"><a>下一页</a></div>
	  		</div>
	  		
	  		<div class="alProdInfo">
	  			<dl>
			        <dt>价格：</dt>
			        <dd><strong>&yen;${merchandise.now }</strong>　
			        <c:if test="${merchandise.now*1 < merchandise.past*1  }">
			        	<del>&yen;${merchandise.past }</del>
			        </c:if>
			        
			        </dd>
	    		</dl>
	    		<c:set var="defaultSku" value="" />
	    		<%--判断单品存在的属性 --%>
	    		<!--第一个可变属性：如 颜色 -->
	    		<c:if test="${merchandise.firstpropname != null && merchandise.firstpropname!='' && merchandise.firstprops!=null }">
	    		
	    			<dl>
	    				<dt>${merchandise.firstpropname }：
	    					<c:if test="${merchandise.hassize==1&&(merchandise.secondpropname == null || merchandise.secondpropname =='')}">
		    					<span style="float:right;">
						      		<a href="${ctx}/activity/sizespec?cate=${merchandise.cate}">尺码说明&gt;&gt;</a>
				        		</span>
				        	</c:if>
	    				</dt>
	    				<dd class="fillColor">
    						<c:forEach items="${merchandise.firstprops}" varStatus="status" var="fir">
    							<span <c:if test="${status.index==0}">class="cur"</c:if> data-title="${status.index}" id="firstpro_${status.index}" onclick="changedSecondProp(this)">
	    							<img src="${fir.pics[status.index]}" width="34" height="34" title="${fir.firstprop}"/>
	    							<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" width="12" height="12"/></i>
    							</span>
    							
    							<c:if test="${merchandise.secondpropname == null || merchandise.secondpropname =='' }">
    								<input id="hasSecond" type="hidden" value="false"/>
    								<c:forEach items="${fir.secondprops}" varStatus="second" var="sec">
    									<input id="firstpro_sku_${status.index }" type="hidden" value="${sec.sku }">
    									<input id="firstpro_count_${status.index }" type="hidden" value="${sec.count}">
	    							</c:forEach>
    							</c:if>
    						</c:forEach>
	    				</dd>
	    			</dl>
	    			
	    			
	    		</c:if>
	    		<!--第二个可变属性：如 尺码 -->
	    		<c:if test="${merchandise.secondpropname != null && merchandise.secondpropname !='' && merchandise.firstprops!=null}">
	    			<dl>
	    				<dt>${merchandise.secondpropname}：
		    				<c:if test="${merchandise.hassize==1}">
		    					<span style="float:right;">
						      		<a href="${ctx}/activity/sizespec?cate=${merchandise.cate}">尺码说明&gt;&gt;</a>
				        		</span>
				        	</c:if>
	    				</dt>
	    				<dd class="fillSize">
	    					<c:forEach items="${merchandise.firstprops}" varStatus="first" var="fir">
	    						<c:forEach items="${fir.secondprops}" varStatus="second" var="sec">
	    							<span name="defSku" value="${sec.sku}" <c:if test="${first.index==0&&second.index==0}">class="cur"</c:if> data-title="${first.index }" id="secondpro_${first.index}" sku="${sec.sku }" count="${sec.count}" onclick="setSkuAndCount('${sec.sku }','${sec.count}')">
		    							<a href="#">${sec.secondprop}
		    							<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" width="12" height="12"></i></a>
	    							</span>
	    						</c:forEach>
	    					</c:forEach>
	    				</dd>
	    			</dl>
	    		</c:if>
	    		<%-- --%>
	    		 <dl>
	        <dt>数量：</dt>
	        <dd class="fillNumber">
	          <%--<s:if test="%{merchandise.secondpropname==null||merchandise.secondpropname==''}"> --%>
			      <select name="count" id="count">
			      	<c:forEach items="${merchandise.firstprops }" var="firstprop" varStatus="first">
			      		<c:if test="${first.index==0 }">
			      			<c:forEach var="sec" items="${firstprop.secondprops }" varStatus="second">
				      			 <c:if test="${second.index==0 }">
				      			 <c:set var="counter" value="${sec.count}"/>
				      			 	<c:if test="${sec.count*1>10 }">
				      			 		<c:set var="counter" value="10"/>
				      			 	</c:if>
				      				<c:forEach var="current" begin="1" end="${counter }" >
				      				 <c:if test="${current*1==1 }">
				      				  <option value="${current }" selected="selected">${current }</option>
				      				  </c:if> 
				      				 <c:if test="${current*1 !=1 }"> 
				      					 <option value="${current }">${current }</option>
				      					 </c:if>
				      				</c:forEach>
				      			</c:if>
			      			</c:forEach>
			      		</c:if>
			      	</c:forEach>
		          </select>
	        </dd>
	      </dl>
	    		<%-- --%>
	    		<%-- 表单：提交订单所需信息--%>
	    		<form action="" method="post" id="merchandiseInfo">
	    			<%--隐藏域信息 --%>
	    			<input type="hidden" name="sku" id="sku" value=""/>
			      	<input type="hidden" name="typeFlag" id="typeFlag" value="${typeFlag }"/>
			      	<input type="hidden" name="productno" id="productno" value="${merchandise.goodscode}"/>
			      	<input type="hidden" name="categoryno" id="categoryno" value="${merchandise.categoryno }"/>
			      	<input type="hidden" name="countTemp" id="countTemp"/>
			      	<input type="hidden" name="url" id="url"/>
			      	
			      	
			      	<%--购买商品数量 --%>
			      	<!-- 
			      	<dl>
			      		<dt>数量：</dt>
			      		<dd class="fillNumber">
			      			<select name="count" id="count">
			      				<option value="${merchandise.totalcount}" selected="selected">${merchandise.totalcount}</option>
			      			</select>
			      		</dd>
			      	</dl>
			      	 -->
			      	<dl>
		        		<dt>&nbsp;</dt>
		        		<dd id="alProdInfo" align="center">${merchandise.msg }</dd>
	      			</dl>
			        <dl>
				        <dt>&nbsp;</dt>
				        <dd id="maxcount" align="center">${merchandise.msg }</dd>
			        </dl>    
			        <c:if test="${ merchandise.totalcount == null || merchandise.totalcount*1 > 0 }">     
				        <c:if test="${merchandise.msg =='' || merchandise.msg == null }">
				        	<dl class="alBuy_btn" id="alBuy_btn">
		        				<dd><a href="javascript:addCart();" id="submit">立即购买</a></dd>
		      				</dl>
				        </c:if>
			        </c:if>
			        <c:if test="${merchandise.totalcount == null || merchandise.totalcount*1 <= 0 }">
				        <dl class="alBuy_btn alBuy_overbtn">
			        		<dd><a href="javascript:;">已售罄</a></dd>
			      		</dl>
		      		</c:if>
	    		</form>
	  		</div>
	  		
	 <%--=======================================商品详情/售后服务/品牌介绍===============================================--%>
	  		<section class="alProd_intro">
	  			<%--tab导航 --%>
	  			<nav class="alProd_introMenu">
			        <ul>
			         <li class="cur"><a href="javascript:;">商品详情</a></li>
			         <li><a href="javascript:;">售后服务</a></li>
			         <li><a href="javascript:;">品牌介绍</a></li>
			        </ul>
		        </nav>
		        
		        <%--商品详情 --%>
		        <div class="alProd_introCell">
		        	<c:if test="${merchandise.buyer !='' && merchandise.buyer != null}">
		        		<h1>买手推荐</h1>
						<p>${merchandise.buyer }</p>
		        	</c:if>
		        	<h1>基本信息</h1>
			        <p>
			        	<c:forEach items="${merchandise.info}"  var="i">
			        		${i}<br/>
			    		</c:forEach>
			        </p>
		            <c:if test="${merchandise.specialinfo !='' && merchandise.specialinfo != null}">
	        		   <h1>特殊说明</h1>
			           <p>${merchandise.specialinfo }</p>
                    </c:if>
		        </div>
		        
		        <%--售后服务 --%>
		        <div class="alProd_introCell" style="display:none;">
		        	<s:if test="${merchandise.aftersale !='' && merchandise.aftersale != null}">
				        <h1>售后服务</h1>
				        <p>${merchandise.aftersale}</p>
				    </s:if>		
		        </div>
		        
		        <%--品牌介绍  --%>
		        <div class="alProd_introCell" style="display:none;">
		        	 <s:if test="${merchandise.brandinfo !='' && merchandise.brandinfo != null}">
				        <h1>品牌介绍</h1>
						<p>${merchandise.brandinfo }</p>
				    </s:if>
		        </div>
		        
	  		</section>
	  		
		</section>
	</div>
	<form action="${ctx}/cart/list" id="cartListForm">
		<!-- 购物车跳转到详情页所需参数 -->
      	<input type="hidden" id="typeFlag" value="${typeFlag}" />
      	<input type="hidden" id="pageType" value="${pageType}" />
	</form>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base.jsp" %> 