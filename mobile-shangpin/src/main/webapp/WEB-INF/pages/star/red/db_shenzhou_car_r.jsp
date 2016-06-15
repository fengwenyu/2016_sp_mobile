<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<div class="wapper">
  <!--内容区域 start-->
  <section class="main"> 
    <div class="red-val" style="position:relative;">
      <img class="get-coupon" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_32/coupon.png">
      <h3>恭喜您！</h3>
      <h4>尚品网新年礼包已放入${phoneNum }账户</h4>
      <div class="btn-box clr">
        <a href="http://m.shangpin.com/coupon/list">查看我的礼包</a>
        <a href="#" id="share_btn">呼朋唤友一起领</a>
      </div>
    </div>
    <a href="http://m.shangpin.com"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_32/title01.png" ></a>
    <div class="swiper-container" id="swiper_item">
      <div class="swiper-wrapper">
        <div class="swiper-slide"><a href="http://m.shangpin.com/product/detail?productNo=30297925"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_32/item_img01.png" width="100%"></a></div>
        <div class="swiper-slide"><a href="http://m.shangpin.com/product/detail?productNo=03462148"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_32/item_img02.png" width="100%"></a></div>
        <div class="swiper-slide"><a href="http://m.shangpin.com/product/detail?productNo=30128878"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_32/item_img03.png" width="100%"></a></div>
        <div class="swiper-slide"><a href="http://m.shangpin.com/product/detail?productNo=30196096"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_32/item_img04.png" width="100%"></a></div>
      </div>
    </div>
    <a href="http://m.shangpin.com/download"> 
    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_32/title02.png" >
    </a>
  </section>
  <!--内容区域 start-->
  
  <div id="popup_overlay"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_32/share.png" style="position: absolute; top:0; right:20px; width:65px; z-index:99999" ></div>
    
</div>

