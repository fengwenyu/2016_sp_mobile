<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link  href="${cdn:css(pageContext.request)}/styles/shangpin/css/font-css/font-awesome.min.css${ver}" rel="stylesheet">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/FashionRun/form_new.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/pop_small.css" rel="stylesheet">
	<script type="text/javascript" charset="utf-8">
      	loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}")
		.using("${cdn:css(pageContext.request)}/styles/shangpin/js/FashionRun/bigGlass_new.js${ver}")
		.using("${cdn:css(pageContext.request)}/styles/shangpin/js/FashionRun/form_new.js${ver}")
      
</script>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	领取时尚装备
</rapid:override>

<%-- 页标题 --%>
 <rapid:override name="page_title">
	领取时尚装备
</rapid:override> 
 <rapid:override name="appLayer">
</rapid:override> 
<rapid:override name="downloadAppShowHead">
		
</rapid:override>
<rapid:override name="content">
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${basePath }/fashionrun/gift"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${basePath }/styles/shangpin/images/FashionRun/fashion-run.jpg"/>
	 <c:choose>
	<c:when test="${!checkWX&&!checkAPP}">
				<div class="alContent form-content">
	</c:when>
	<c:otherwise>
		<div class="alContent form-content" style="margin-top: 0;">
	</c:otherwise>
</c:choose> 

	<!--填写表单 start-->
    <div class="form-box">
    	 <img src="${ctx }/styles/shangpin/images/FashionRun/form_banner.png" alt="">
        <h2 class="title">时尚装备</h2>
  
    	<div class="row-box">
    	   <img src="${ctx }/styles/shangpin/images/FashionRun/11111.jpg" />
    	<img src="${ctx }/styles/shangpin/images/FashionRun/22222.jpg" style="margin-top:10px"/>
    	</div>
    	 <form id="apply_form"  method="post">
        <div class="row-box">
            <label for="mobileNum">您的手机号</label>
            <input type="text"  pattern="[0-9]*" id="J_mobileNum" name="phone" placeholder="请输入您报名时的手机号码" value="" required maxlength="11" />
        	<div><button type="button"></button></div>
        </div>
        
        <div class="row-box">
            <label for="area">收货地址</label>
            <a id="J_area" class="select-area" name="area">省/市/区</a>
        </div>
        
        <div class="row-box" style="margin-top:5px;">
            <input type="text" id="J_addr" name="addr" placeholder="您的详细地址" value="" required />
        	<div><button type="button"></button></div>
        </div>
        <input type="hidden" id="size" name="size" value=""/>
        <input type="hidden" id="city" name="city" value=""/>
	    <input type="hidden" id="area" name="area" value=""/>
	    <input type="hidden" id="town" name="town" value=""/>
	      <input type="hidden" id="province" name="province" value=""/>
	      
        <div class="row-box size-box clr">
            <em>选择<br>BRA尺码</em>
            <ul class="bar-size js-bar-size">
            <c:forEach var="ext" items="${packExtList }">
            	<li >
            	<c:choose>
	            	<c:when test="${ext.stock*1 >0}">
	            		<a  id="${ext.size }">${ext.size }</a>
	            	</c:when>
	            	<c:otherwise>
	            		<a id="${ext.size }" class="saleOut">${ext.size }</a>
	            	</c:otherwise>
            	</c:choose>
            	
            	</li>
            </c:forEach>
            
            </ul>
        </div>
        <!-- <div class="row-box size-box clr">
            <em>选择<br>BRA尺码</em>
            <ul class="bar-size js-bar-size">
                <li><a>S</a><span>剩余1000件</span></li>
                <li><a>M</a><span>已领完</span></li>
            </ul>
        </div> -->
        
	</form>
        
        <div class="btn-box" style="margin-bottom:20px">
            <a href="javascript:;" class="submit-btn js-get-btn">领取时尚装备</a>
        </div>
        
    </div>
    <!--填写表单 end-->

  <%--   <div class="select-overlay" id="area_overlay"></div>
    <div class="area-layer" id="area_layer">
        <a href="javascript:;" class="close_btn">关闭</a>
        <h3>省份</h3>
        <dl id="area_province" title="省份">
        	<c:forEach var="provice" items="${provinces}">
		  		<dd><a href="#" id="${provice.id}">${provice.name}</a></dd>
		  	</c:forEach>
          
        </dl>
        <dl id="area_city" title="城市">
          
        </dl>
        <dl id="area_county" title="地区">
          
        </dl>
        <dl id="area_street" title="街道">
           
        </dl>
    </div> --%>
    <div class="select-overlay" id="area_overlay"></div>
    <div class="area-layer" id="area_layer">
        <a href="javascript:;" class="close_btn">关闭</a>
        <h3>省份</h3>
        <dl id="area_province" title="省份">
       	<c:forEach var="provice" items="${provinces}">
		  		<dd><a href="#" id="${provice.id}">${provice.name}</a></dd>
		  	</c:forEach>
          
        </dl>
        <dl id="area_city" title="城市">
        </dl>
        <dl id="area_county" title="地区">
        </dl>
        <dl id="area_street" title="街道">
        </dl>
    </div>
    <!-- 点击结算提醒弹层 -->
    <div class="overlay" id="overlay">
        <section class="modal modal-test">
          <div class="modal-bd" style="padding: 30px 45px 25px; font-size:14px">
            <p>您还没报名呢，先去报名吧！</p>
          </div>
          <div class="modal-ft">
            <span class="btn-modal sure"><a href="${ctx }/fashionrun/sign">我要报名</a></span>
          </div>
          
        </section>      
    </div>
<%-- <c:if test="${checkAPP}">
  <div class="shang_share"> 
         <a href="shangpinapp://phone.shangpin/actiongoshare?title=FashionRun北京站美女招募,8月1日奥森公园不美不跑.&url=${basePath }/fashionrun/gift&desc=FashionRun北京站美女招募,8月1日奥森公园不美不跑."><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
   </div>
</c:if> --%>
</rapid:override>
<rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp" %> 