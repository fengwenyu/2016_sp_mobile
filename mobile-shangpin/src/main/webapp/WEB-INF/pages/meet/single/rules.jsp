<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<style>
.main_box img{ width:100%}
</style>
<script type="text/javascript" charset="utf-8">
	loader = SP.core
	.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.easing.min.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/jQueryRotate.2.2.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/xmas.dialogs.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShowMeet.js${ver}")
    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/meet/single/rules.js${ver}")
    .excute(function(){
		isHome(false);
	});
	</script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/20150715Bags.css${ver}" rel="stylesheet" />
</rapid:override>
<rapid:override name="header">
</rapid:override>
<rapid:override name="content">
<body style="background:#ffedce;">
<div class="container">
    
    <!--内容区域 start-->
    <div class="content-box">
        <!--<h2><a href="#"><img src="/images/20150715Bags/img02.jpg"></a></h2>-->
        
        <!--抽奖 start-->
        <div class="draw-info">
            <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150715Bags/rule_title.png"></p>
            <dl class="draw-info_text">
                <dt>· 每个账号每天有3次翻转机会</dt>
                <dt>· 好礼设置</dt>
                <dd>一等奖：1500元购物节现金券<br />
                    二等奖：500元购物节代金券<br />
                    三等奖：50元购物节现金券
                </dd>
                <dt>· 所得礼包券将充入个人账户，请登录查收</dt>
            </dl>
            <div class="sroll-text">
              <div>
              <ul class="lottery_list" id="window_roll">
              
              </ul>
              </div>
          </div>
            
            <p style="margin-top:30px;">活动最终解释权归尚品所有</p>
 		</div>
        <!--抽奖 end-->
       
    </div>
    <!--内容区域 start-->
	
    
</div>
</rapid:override>

 
<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_meet.jsp" %> 
