<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	女神新装
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/one/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/one/two.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/one/two.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/one/weixin_ready.js${ver}")
			.excute(function(){
			
			})
	</script>
</rapid:override>

<rapid:override name="page_title">
	女神新装
</rapid:override>

<rapid:override name="content">
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/one/index?nper=2"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="尹恩惠同款棉服1元包邮！尚品网CEO又任性了！"/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="尹恩惠同款棉服尚品网1元包邮！限量1000套！"/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/goddes/goddes-11.jpg"/>
	<input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
	<!--内容区域 start-->
    <div class="conbox">
        <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img01.jpg${ver}" /></p>
        
        <p class="clr">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img30.jpg${ver}" >
        </p>
        
        <!--两列-->
        <c:choose>
        	<c:when test="${checkAPP}">
        		<ul class="sp_list list-nom clr">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30224457"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img7.jpg${ver}" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30224459"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img8.jpg${ver}" ></a></li>
		        </ul>
        	</c:when>
        	<c:otherwise>
		        <ul class="sp_list list-nom clr">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30224457"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img7.jpg${ver}" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30224459"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img8.jpg${ver}" ></a></li>
		        </ul>
        	</c:otherwise>
        </c:choose>
        
        <p class="clr">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img02.jpg${ver}" >
        </p>
        <p class="clr">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img03.jpg${ver}" >
        </p>
        <div class="verify-box">
        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img29.png${ver}" >
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
          <div class="step-btn"><a href="#" class="coupon-btn">立即领取</a></div>
          <!--<p class="tip_text">每个手机号限领一张</p>-->
          
        </div>          
        <!-- 验证手机end--> 
        </div>
        <p class="clr">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img5.jpg${ver}" >
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img6.jpg${ver}" >
        </p>
         <!-- <c:if test="${productCount.totalCount == 0}">
        	<p class="out_text">1000件尹恩惠同款分分钟秒杀售罄</p>
        </c:if> 
        <!--两列-->
         <!--<ul class="sp_list list-nom clr">
        	<c:choose>
        		<c:when test="${productCount.totalCount > 0}">
        			<li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img05.jpg" ></li>	
            		<li><a href="${ctx}/product/detail?productNo=30224465"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img06.jpg" ></a></li>
        		</c:when>
        		<c:otherwise>
        			<li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img05_sellout.jpg" ></li>	
            		<li><a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img06_sellout.jpg" ></a></li>
        		</c:otherwise>
        	</c:choose>
        </ul>
       -->
        <!--两列-->
        <c:choose>
        	<c:when test="${checkAPP}">
        		<ul class="sp_list list-nom clr">
		        	<li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img09.jpg${ver}" ></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30224453"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img10.jpg${ver}" ></a></li>
		        </ul>
        	</c:when>
        	<c:otherwise>
        		<ul class="sp_list list-nom clr">
		        	<li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img09.jpg${ver}" ></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30224453"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img10.jpg${ver}" ></a></li>
		        </ul>
        	</c:otherwise>
        </c:choose>
        
        <h3 class="nom"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img11.jpg${ver}" ></h3>
        <!--两列-->
        <c:choose>
        	<c:when test="${checkAPP}">
        		<ul class="sp_list clr">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30111978"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img12.jpg${ver}" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30112029"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img13.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30111704"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img14.jpg${ver}" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30112028"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img15.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30109168"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img16.jpg${ver}" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30109174"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img17.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30109169"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img18.jpg${ver}" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30109171"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img19.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30122513"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img20.jpg${ver}" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30122511"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img21.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30122512"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img22.jpg${ver}" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30122510"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img23.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30122515"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img24.jpg${ver}" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30120473"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img25.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30122516"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img26.jpg${ver}" ></a></li>	
		            <li><a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30120480"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img27.jpg${ver}" ></a></li>
		        </ul>
        	</c:when>
        	<c:otherwise>
        		<ul class="sp_list clr">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30111978"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img12.jpg${ver}" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30112029"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img13.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30111704"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img14.jpg${ver}" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30112028"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img15.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30109168"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img16.jpg${ver}" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30109174"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img17.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30109169"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img18.jpg${ver}" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30109171"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img19.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30122513"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img20.jpg${ver}" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30122511"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img21.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30122512"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img22.jpg${ver}" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30122510"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img23.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30122515"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img24.jpg${ver}" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30120473"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img25.jpg${ver}" ></a></li>
		        </ul>
		        <ul class="sp_list clr">
		        	<li><a href="http://m.shangpin.com/product/detail?productNo=30122516"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img26.jpg${ver}" ></a></li>	
		            <li><a href="http://m.shangpin.com/product/detail?productNo=30120480"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img27.jpg${ver}" ></a></li>
		        </ul>
        	</c:otherwise>
        </c:choose>
          <p class="imgList"><a href="http://m.shangpin.com/meet/381"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images2/img28.jpg" ></a></p>
    </div>
</rapid:override>
<rapid:override name="footer">

</rapid:override>
<rapid:override name="static_file">
	
</rapid:override>

<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp" %> 
