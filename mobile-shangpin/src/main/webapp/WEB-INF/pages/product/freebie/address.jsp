<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
  
  <!--换领其他款式列表-->
   <input type="hidden" value="${p}" id="pd" />
      <div class="swiper-box">
          <div class="swiper-container" id="swiper-container">
              <div class="swiper-wrapper">
              <c:if test="${tj != null && fn:length(tj)>0}">
              <c:forEach items="${tj }" var="ls">
                  <div class="swiper-slide">
                     <a href="../product/freebieDetail?p=${pd }&spu=${ls.productId }">
                  	  <div class="img_box"><img alt="" src="${ls.pic }" ></div>
                       <div class="text_box">
                    	<h3>${ls.brandNameEN }</h3>
                        <p>${ls.productName }</p>
                        <p class="price">￥${ls.promotionPrice }</p>
                       </div>
                     </a>
                   </div>
                  </c:forEach> 
                 </c:if>
              </div>
          </div>
      </div>
  <!--收货地址四级菜单-->
  
    <!--  <div id="area_overlay"></div>
		<div id="area_layer"> -->
		 <div id="area_overlay"></div>
		<div id="area_layer">
			<a href="javascript:;" class="prev_btn">返回</a>
        	<a href="javascript:;" class="close_selet_btn">关闭</a>
			<h3>省份</h3>
		    <dl id="area_province" title="省份">
		    	<%-- <c:forEach var="provice" items="${provinces}">
		  		<dd><a href="javascript:;" id="${provice.id}">${provice.name}</a></dd>
		  	</c:forEach> --%>
		    </dl>
		    <dl id="area_city" title="城市">
		       
		    </dl>
		    <dl id="area_county" title="地区">
		        
		    </dl>
		    <dl id="area_street" title="街道">
		       
		    </dl>
		</div>
      
       <!--提示结果弹层-->
      <div class="select-overlay" id="select-overlay1">
          <div class="show_window_con" id="show_window_con1">              
              <div class="addr_con">
              
              </div>
              <p class="close_window">
                  <a href="" class="sure_addr_btn">确定</a>
                  <a href="" class="cancel_addr_btn">取消</a>
              </p>
          </div>
      </div>
      
      <!--提示结果弹层-->
      <div class="select-overlay" id="select-overlay2">
          <div class="show_window_con" id="show_window_con2">              
              
          </div>
      </div>