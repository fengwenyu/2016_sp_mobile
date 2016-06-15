<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<script type="text/javascript" charset="utf-8">
    loader = SP.core
      .install("${cdn:css(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")  //jquery库文件
        .using("${cdn:css(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")    //图片懒加载
        .excute()
        .using("${cdn:css(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
        .using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}")
        .excute();
      
      
</script>
<style>
body{background-color:#000; color: #fafafa}

.container {min-width:320px; max-width:640px;	margin:0 auto; font-size:12px;}
body .page_box{margin-top:45px;}
.title_img{/*padding:10px 70px;*/}
.title_img1{padding:10px 30px;}

.page_info{margin:20px 0 0;}
.page_info dl{margin-top:10px; padding:0 5px;}
.page_info dt, .page_info dd{float:left; font-size:14px; line-height:20px;}
.page_info dt{ width:16%; text-align:right;}
.page_info dd{ width:84%;}
.page_info dd img{border:1px solid #d8d8d8;}
.footerBtm {min-width: 320px; max-width: 640px;	position: fixed; bottom: 0px; z-index: 10; width: 100%;}
.footerBtmFixed{position:relative;	line-height:50px; height:50px; background:rgba(255,255,255,.8); padding:5px 12px; text-align:center; border-top:1px solid #ccc;}

.footerBtmFixed a{
	width:140px;
	height:38px;
	line-height:38px;
	border:1px solid #c62026;
	border-radius:5px;
	font-size:16px;
	display: inline-block;
	text-align:center;
}
.footerBtmFixed a.buy_btn{
	color:#fff;
	background:#c62026;
}
.btn-box { position: relative; margin: 20px 5% 0; }
.submit-btn { background-color: #fb0c60; border: 1px solid #c62026; border-radius:5px; height: 40px; line-height: 40px; color: #fff; text-align: center; font-size: 16px; display: inline-block; box-sizing: border-box; width:41%; margin:0 2%; }
.big-btn{width:51%;}
.center_text{ text-align:center; line-height: 20px; color: #fb0c60; margin-bottom: 20px;}
.center_text a{color: #fb0c60; text-decoration:underline;}
.disabled{background-color:#d8d8d8; border:1px solid #666; color:#555555;}
</style>
</rapid:override >
     

			
<%-- 浏览器标题 --%>
<rapid:override name="title">
	FashionRun 时尚美女跑者招募
</rapid:override>

<%-- 页标题 --%>
 <rapid:override name="page_title">
	FashionRun 时尚美女跑者招募
</rapid:override> 
 <rapid:override name="appLayer">
</rapid:override> 
<rapid:override name="downloadAppShowHead">
		
</rapid:override>
<rapid:override name="content">
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${basePath }/fashionrun/index"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${basePath }/styles/shangpin/images/FashionRun/fashion-run.jpg"/>
  <c:choose>
	<c:when test="${!checkWX&&!checkAPP}">
				<div class="alContent page_box">
	</c:when>
	<c:otherwise>
		<div class="alContent page_box" style="margin-top: 0;">
	</c:otherwise>
</c:choose>
    	
        <p class="title_img"><img src="${ctx }/styles/shangpin/images/FashionRun/form_banner.png" alt=""></p>
        <p class="title_img1"><img src="${ctx }/styles/shangpin/images/FashionRun/index_map.png" alt=""></p>
        
        <section class="page_info">
        	<dl class="clr">
                <dt>时间：</dt>
                <dd>2015年8月1日 7:00-8:00签到 , 8:30开跑</dd>
            </dl>
            <dl class="clr">
                <dt>地点：</dt>
                <dd>奥林匹克森林公园南门</dd>
            </dl>
            <dl class="clr">
                <dt>里程：</dt>
                <dd>5公里</dd>
            </dl>
            <dl class="clr">
                <dt>人员：</dt>
                <dd>时尚美女</dd>
            </dl>
            <dl class="clr">
                <dt>费用：</dt>
                <dd>168元/人</dd>
            </dl>
            <dl class="clr">
                <dt>奖励：</dt>
                <dd>1、所有完赛选手将获得FashionRun专属</br>&#12288;&nbsp;&nbsp;奖牌一枚</br>2、完赛后将设置丰厚时尚装备抽奖环节;<br/>
3、完赛后将通过网络评选“个性着装”、<br/>&#12288;&nbsp;&nbsp;“动感女郎”、“最具人气”等奖项</br>
</dd>
            </dl>
             <dl class="clr">
                <dt>公益：</dt>
                 <dd>赛事收益将捐赠中国服装设计师协会,扶持中国原创时尚设计</dd>
            </dl>
            <dl class="clr">
                <dt>流程：</dt>
                
            </dl>
            </dl>

            <p class="title_img1" style="padding:10px"><img src="${ctx }/styles/shangpin/images/FashionRun/index_step.png" alt=""></p>
        </section>
        
     
          <div class="btn-box" style="margin-bottom:20px">
            <a href="javascript:;" class="submit-btn disabled">报名已结束</a><a href="${ctx }/fashionrun/gift" class="submit-btn big-btn">已报名,领装备</a>
        </div>
       <%--  <p class="center_text"><a href="${ctx }/fashionrun/gift" style="color:#fff;font-size:16px">已报名,领时尚装备</a></p> --%>
        <p class="center_text" ><a href="mailto:info@fashionrun.org?subject=&body=" style="color:#fff;text-decoration: none;">  联系方式：info@fashionrun.org</a></p>

        
    </div>
      <!--页面底部end-->	
<%-- <c:if test="${checkAPP}">
  <div class="shang_share"> 
         <a href="shangpinapp://phone.shangpin/actiongoshare?title=FashionRun北京站美女招募,8月1日奥森公园不美不跑.&url=${basePath }/fashionrun/gift&desc=FashionRun北京站美女招募,8月1日奥森公园不美不跑."><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
   </div>
</c:if> --%>
</rapid:override>
 <rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common_no_content_no_banner.jsp" %> 
