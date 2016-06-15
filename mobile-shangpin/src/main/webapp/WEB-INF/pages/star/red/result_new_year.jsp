<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<div class="wapper1">
 
  <!--内容区域 start-->
  <section class="main"> 
    <div class="red-val" style="position:relative;">
      <div class="js-share-pop" style="display: none;position: fixed; right: 0px; top: 0px; height: 100%; width: 100%; z-index: 999; background: rgba(0, 0, 0, 0.701961);"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/db_11/share_bg.png"></div>
      <!--<div style="position:absolute; right:0; top:0; height:100%; width:24%; background:url(img/fixed_img.png) no-repeat 10px 0; background-size:contain;"></div>-->
      <img class="icon_revice" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/db_11/icon05.png"  />
      <img class="get-coupon" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/db_11/coupon.png">
      <h3>恭喜您！</h3>
      <h4>尚品网新年礼包已放入${phoneNum }账户</h4>
      <div class="btn-box clr">
        <a href="http://m.shangpin.com/coupon/list">查看我的礼包</a>
        <!-- <a href="#">呼朋唤友一起领</a> -->
        <a href="javascript:;" class="js-share-btn">呼朋唤友一起领</a>
      </div>
    </div>
    <a href="http://m.shangpin.com/meet/551"> 
    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/db_11/title01.png" >
    </a>
    <div class="swiper-container" id="swiper_item">
      <div class="swiper-wrapper">
        <div class="swiper-slide"><a href="http://m.shangpin.com/product/detail?productNo=30151655"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/db_11/item_img01.png" width="100%"></a></div>
        <div class="swiper-slide"><a href="http://m.shangpin.com/product/detail?productNo=30137920"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/db_11/item_img02.png" width="100%"></a></div>
        <div class="swiper-slide"><a href="http://m.shangpin.com/product/detail?productNo=30097170"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/db_11/item_img03.png" width="100%"></a></div>
        <div class="swiper-slide"><a href="http://m.shangpin.com/product/detail?productNo=30240798"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/db_11/item_img04.png" width="100%"></a></div>
      </div>
    </div>
    <a href="http://m.shangpin.com/download"> 
    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/db_11/title03.png" >
    </a>
    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/db_11/title04.png" >
    
  </section>
  <!--内容区域 start-->
  
  </div>