 <%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
 
 <div class="product_info">
        <div>
          <ul class="tab_info">
            <li class="curr" id="product_tab_1"><span>商品详情</span></li>
            <li id="product_tab_2"><span>图片详情</span></li>
            <c:if test="${productDetail.basic.isSize=='1'}">
           		<li id="product_tab_3"><span>尺码及试穿</span></li>
            </c:if>
            <li id="product_tab_5"><span>保养售后</span></li>
          </ul>
        </div>
        <div class="content_info">
          <div class="content_detail content_list">
            <ul class="info_base">
             <c:forEach var="info" items="${productDetail.basic.info }">
               <c:choose>
               		 <c:when test="${fn:indexOf(info,':')>-1 }">
			             <li><span><strong>${fn:substring(info,0,fn:indexOf(info,":")) }</strong>：</span>${fn:substring(info,fn:indexOf(info,":")+1,fn:length(info)) } &nbsp;</li>
			         </c:when>
			          <c:when test="${fn:indexOf(info,'：')>-1 }">
			              <li><span><strong>${fn:substring(info,0,fn:indexOf(info,"：")) }</strong>：</span>${fn:substring(info,fn:indexOf(info,"：")+1,fn:length(info)) }&nbsp;</li>
			            </c:when>
			          	<c:otherwise>
			          	 <li><span><strong>${fn:substring(info,0,fn:indexOf(info,":")) }</strong>：</span>${fn:substring(info,fn:indexOf(info,":")+1,fn:length(info)) }&nbsp;</li>
			          	</c:otherwise>
		        </c:choose>
           	 </c:forEach>
            <br>
            </ul>
    		</div>
          <div class="content_size content_list" id="tabs_txt1" style="display:none">
             <section>
                <header>${productDetail.basic.recommend }</header>
                <article>
                 <c:forEach var="pic" items="${productDetail.basic.allPics}">
				     <img src="${fn:replace(pic,'-10-10','-600-758')}">
		        </c:forEach>
                </article>
              </section>
          </div>
          <div class="content_size content_list nobottom border_bottom" id="tabs_txt0" style="display:none">
          </div>
          <div class="content_rand content_list nobottom border_bottom" id="tabs_txt3" style="display:none">
         	${productTemplate.html }
          </div>
          <!--保养及售后start-->
          <div class="content_rand content_list"  id="tabs_txt4" style="display:none">
           ${productTemplate.html }
          </div>
        <!--保养及售后end-->       
     </div>
 </div> 
		    
<!--其他服务start-->
<%--  <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/product/pic_service00.jpg" class="service_pic"/> --%>
</div>
