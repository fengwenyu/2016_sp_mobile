<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<div class="wapper1">
 
  <!--内容区域 start-->
  <section class="main"> 
    <div class="red-val" style="position:relative;">
      <!--<div style="position:absolute; right:0; top:0; height:100%; width:24%; background:url(img/fixed_img.png) no-repeat 10px 0; background-size:contain;"></div>-->
      <!--<img class="icon_revice" src="img/icon05.png"  />-->
      <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_510/receive_img01.png">
      <h3>恭喜您，90元礼券已放入</h3>
      <h4>${phoneNum }的尚品账户</h4>
      <h5>如您未修改过密码，密码为手机号后6位。</h5>
      <h4 class="attention">使用折扣码JS15国内商品享受9折优惠</h4>
      <div class="btn-box clr">
        <a href="http://m.shangpin.com/coupon/list">查看我的礼包</a>
        <a href="javascript:;" id="share_btn">呼朋唤友一起领</a>
      </div>
      <a class="href_sp" href="http://m.shangpin.com/">马上去购物</a>
    </div>
    <a href="http://m.shangpin.com/download"> 
    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_510/receive_img02.png" >
    </a>
  </section>
  <!--内容区域 start-->
  <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_510/img03.png" style="position:absolute; bottom:0;" >
</div>
 <div id="popup_overlay"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_510/share.png" style="position: absolute; top:0; right:20px; width:65px; z-index:99999" ></div>