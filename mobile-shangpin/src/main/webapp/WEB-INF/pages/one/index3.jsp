<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	女神新装
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/one/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/one/three.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/one/three.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/one/weixin_ready.js${ver}")
			.excute(function(){
			
			})
	</script>
</rapid:override>

<rapid:override name="page_title">
	女神新装
</rapid:override>

<rapid:override name="downloadAppShowHead">

</rapid:override>

<rapid:override name="header">

</rapid:override>


<rapid:override name="content">
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/one/index?nper=3"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="应采儿同款婚纱1元购！尚品网100套女神新装幸福无价！"/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="尚品网100套应采儿婚纱1元包邮+抽奖英国双人游！"/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/goddes/goddes-12.jpg"/>
	<input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
	
	<div class="lottery-box">
    	<p class="tip-text">提示：获奖用户999元情侣基金已放入您尚品网账户，使用您的手机号登录尚品网APP可立即使用。</p>
        <div class="list-scroll-box">
          <h3>11月1日获得999元情侣基金名单：</h3>
          <ul class="lottery_list clr">
            <li>@馨悦嘟不嘟</li>
			<li>@奔三还在卖萌滴真真</li>
			<li>@我叫田小雨</li>
			<li>@超级无敌胡芳微</li>
			<li>@鹏鹏0nly</li>
			<li>@__Yelena__</li>
			<li>@antial0</li>
			<li>@VICKIFX</li>
			<li>@某某某的刘某某</li>
			<li>@Outsider_JingJing</li>
          </ul>
          
          <h3>11月2日获得999元情侣基金名单：</h3>
          <ul class="lottery_list clr">
            <li>@穆维棉</li>
			<li>@塽小張_</li>
			<li>@阿包小公举</li>
			<li>@_Luska</li>
			<li>@喻好棒</li>
			<li>@给你所有正能量</li>
			<li>@NANANANANA_A</li>
			<li>@xuanrujia</li>
			<li>@黄小橘丶</li>
			<li>@Queen倩影</li>
          </ul>
          
          <h3>11月3日获得999元情侣基金名单：</h3>
          <ul class="lottery_list clr">
            <li>@蔻酱酱酱子</li>
			<li>@rebirth静芳 </li>
			<li>@蕉小末</li>
			<li>@木木木木榕 </li>
			<li>@楽欧尼 </li>
			<li>@何励志励志励志</li>
			<li>@katherine高</li>
			<li>@小女人波波糖</li>
			<li>@Serendipity_xhw</li>
			<li>@Miss吴良心</li>
          </ul>
          
          <h3>11月4日获得999元情侣基金名单：</h3>
          <ul class="lottery_list clr">
            <li>@昵称不能用空白</li>
			<li>@WwWHAnn </li>
			<li>@祂幂 </li>
			<li>@没了记忆才好D</li>
			<li>@越努力越幸福000</li>
			<li>@May__瑶</li>
			<li>@1oVEmay-KoNG</li>
			<li>@lulu的小迷妹</li>
			<li>@拜托小姐m</li>
			<li>@小紫韵妹妹</li>
          </ul>
          
          <h3>11月5日获得999元情侣基金名单：</h3>
          <ul class="lottery_list clr">
            <li>@kary_lee</li>
			<li>@如此_12</li>
			<li>@转角-sunshine</li>
			<li>@糖_弧</li>
			<li>@夏咧咧-</li>
			<li>@蜡笔小猫28</li>
			<li>@Vanilla_加糖</li>
			<li>@Mitty_YIN</li>
			<li>@A_Jessie</li>
			<li>@裸奔的小蜗牛1215</li>
          </ul>
          
          <p style="color:#fff">尚品网客服工作人员会通过微博私信的形式跟您确认核实相关信息，并将999元情侣基金发放到您的尚品网账户中</p>
        </div>
        <a href="javascript:;" class="pack-up-btn">收起<i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/arrow_up.png" /></i></a>
    </div>
    <div class="slide-btn"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/winner_list_btn.png" /></div>
    
	<!--内容区域 start-->
    <div class="conbox">
        <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/img01.jpg" /></p>
        <p class="clr">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/img30.jpg" >
        </p>
        
        <p class="clr">
            <a href=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/img29.jpg" ></a>
        </p>
        <p class="clr">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/img05.jpg" >
        </p>
        <p class="clr fixed-high1">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/img06.jpg" >
        </p>
        <p class="clr fixed-high1">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/img07.jpg" >
        </p>
        <p class="clr">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/img08.jpg" >
        </p>
        <p class="clr fixed-high1">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/img31.jpg" >
        </p>
        
        <div class="verify-box">
        <!-- 验证手机-->   
        <div class="modal-bd">
              <input class="tip-input" id="J_mobileNum" type="tel" value="" placeholder="输入手机号" maxlength="11" required />
               <div class="yzm-box">
                      <div class="login_list">                  
                           <input type="tel" id="J_yzm" value="" placeholder="输入验证码" maxlength="6" required>                             
                      </div>
                  <em id="passwordGetCode" class="btn_gradient_gray" data-waiting="秒">获取验证码</em>

              </div> 
          <!--<a href="#" class="close"></a>-->    
          <p class="prompt"></p>  
          <div class="step-btn"><a href="#" class="coupon-btn">参与抽奖</a></div>
          <!--<p class="tip_text">每个手机号限领一张</p>-->
          
        </div>          
        <!-- 验证手机end--> 
        </div>
        
        <p class="clr">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/img10.jpg" >
        </p>
        
        
        <!--两列-->
        <c:choose>
        	<c:when test="${checkAPP}">
        		<ul class="sp_list clr fixed-high2" style="margin-top:-5px;">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30112028"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img01.jpg" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30111704"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img02.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30111978"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img03.jpg" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30112029"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img04.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30111982"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img05.jpg" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30112012"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img06.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30112000"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img07.jpg" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30112015"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img08.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30112011"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img09.jpg" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30112020"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img10.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30111986"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img11.jpg" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30112010"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img12.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30112021"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img13.jpg" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30111978"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img14.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30176657"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img15.jpg" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30176642"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img16.jpg" ></a></li>
		        </ul>
		        
		        <p class="imgList" style="margin:-5px 0;"><a href=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/title01.jpg" ></a></p>
		        <ul class="sp_list clr fixed-high2">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30109168"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img17.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30109171"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img18.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30122510"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img19.jpg" ></a></li>
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30109174"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img20.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30122514"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img21.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30122512"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img22.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30122513"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img23.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30109173"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img24.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30109170"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img25.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30109172"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img26.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30122511"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img27.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30109169"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img28.jpg" ></a></li>
		        
		        </ul>
		        
		        <p class="imgList" style="margin:-5px 0;"><a href=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/title02.jpg" ></a></p>
		        <ul class="sp_list clr fixed-high2">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30120473"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img29.jpg" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30120480"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img30.jpg" ></a></li>
		        	
		        </ul>
		        
		        <p class="imgList" style="margin:-5px 0;"><a href=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/title03.jpg" ></a></p>
		        <ul class="sp_list clr fixed-high2">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30122516"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img31.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30122515"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img32.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30224459"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img33.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30224457"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img34.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30176410"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img35.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30176243"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img36.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30224453"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img37.jpg" ></a></li>
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30224459"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img38.jpg" ></a></li>
		        </ul>
		        
		        <p class="imgList" style="margin:-5px 0;"><a href=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/title04.jpg" ></a></p>
		        <ul class="sp_list clr fixed-high2">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30239182"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img39.jpg" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30239199"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img40.jpg" ></a></li>
		        	
		        </ul>
        	</c:when>
        	<c:otherwise>
        		<ul class="sp_list clr fixed-high2" style="margin-top:-5px;">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30112028"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img01.jpg" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30111704"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img02.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30111978"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img03.jpg" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30112029"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img04.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30111982"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img05.jpg" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30112012"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img06.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30112000"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img07.jpg" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30112015"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img08.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30112011"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img09.jpg" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30112020"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img10.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30111986"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img11.jpg" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30112010"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img12.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30112021"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img13.jpg" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30111978"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img14.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30176657"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img15.jpg" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30176642"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img16.jpg" ></a></li>
		        </ul>
		        
		        <p class="imgList" style="margin:-5px 0;"><a href=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/title01.jpg" ></a></p>
		        <ul class="sp_list clr fixed-high2">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30109168"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img17.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30109171"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img18.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30122510"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img19.jpg" ></a></li>
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30109174"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img20.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30122514"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img21.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30122512"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img22.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30122513"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img23.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30109173"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img24.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30109170"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img25.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30109172"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img26.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30122511"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img27.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30109169"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img28.jpg" ></a></li>
		        
		        </ul>
		        
		        <p class="imgList" style="margin:-5px 0;"><a href=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/title02.jpg" ></a></p>
		        <ul class="sp_list clr fixed-high2">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30120473"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img29.jpg" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30120480"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img30.jpg" ></a></li>
		        	
		        </ul>
		        
		        <p class="imgList" style="margin:-5px 0;"><a href=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/title03.jpg" ></a></p>
		        <ul class="sp_list clr fixed-high2">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30122516"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img31.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30122515"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img32.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30224459"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img33.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30224457"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img34.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30176410"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img35.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30176243"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img36.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30224453"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img37.jpg" ></a></li>
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30224459"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img38.jpg" ></a></li>
		        </ul>
		        
		        <p class="imgList" style="margin:-5px 0;"><a href=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/title04.jpg" ></a></p>
		        <ul class="sp_list clr fixed-high2">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30239182"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img39.jpg" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30239199"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/list_img40.jpg" ></a></li>
		        	
		        </ul>
        	</c:otherwise>
        </c:choose>
        
<%--         <p class="imgList"><a href="http://m.shangpin.com/meet/381"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images3/img28.png" ></a></p> --%>
    </div>
    <!--内容区域 start-->
	
    <!--优惠券领取成功-->
    <div class="sucess_window" id="sucess_window">
    	<section class="couponShow">
            <!--内容-->
        </section>
    </div>
</rapid:override>
<rapid:override name="footer">

</rapid:override>
<rapid:override name="static_file">
	
</rapid:override>

<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp" %> 
