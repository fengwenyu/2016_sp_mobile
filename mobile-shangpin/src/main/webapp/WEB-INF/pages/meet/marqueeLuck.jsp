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
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/xmas.dialogs.js${ver}")
    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/meet/marqueeLuck.js${ver}")
    .excute();
	</script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/20150605MarqueeLuck.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<%@ include file="/WEB-INF/pages/common/header.jsp"%>
	</c:if>
</rapid:override>
<rapid:override name="content">
<div class="alContent">
	<h1 class="title-top">
    	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520MarqueeLuck/top_title.png">
    </h1>
	<!--------转盘S------>
    <div id="lottery">
        <ul>
         <li class="clr">
            <span class="lottery-unit lottery-unit-0"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150605MarqueeLuck/img01.png" /><em></em></span>
            <span class="lottery-unit lottery-unit-1"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150605MarqueeLuck/thank.png" /><em></em></span>
            <span class="lottery-unit lottery-unit-2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150605MarqueeLuck/img03.png" /><em></em></span>
            <span class="lottery-unit lottery-unit-3"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150605MarqueeLuck/img04.png" /><em></em></span>
          </li>
          <li class="clr">
            <span class="lottery-unit lottery-unit-9"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150605MarqueeLuck/img05.png" /><em></em></span>
            <span class="start">开始抽奖</span>
            <span class="lottery-unit lottery-unit-4"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150605MarqueeLuck/img06.png" /><em></em></span>
          </li>
          <li class="clr">
            <span class="lottery-unit lottery-unit-8"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150605MarqueeLuck/img07.png" /><em></em></span>
            <span class="lottery-unit lottery-unit-7"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150605MarqueeLuck/img08.png" /><em></em></span>
            <span class="lottery-unit lottery-unit-6"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150605MarqueeLuck/thank.png" /><em></em></span>
            <span class="lottery-unit lottery-unit-5"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150605MarqueeLuck/img10.png" /><em></em></span>
          </li>
        </ul>
        <div class="lottery-show"></div>
    </div> 
    <!--------转盘end------>
    <div class="con">
        <ul class="con-text">
            <li>每个用户每天有3次抽奖机会，TOPSHOP任性让你百分百中奖！</li>
            <li><strong>活动时间：</strong>2015.06.08-2015.06.11</li>
            <li><strong>奖项设置：</strong><br>
              <strong>一等奖：</strong>1000元衣橱基金（现金券）<br>
              <strong>二等奖：</strong>超美单品直接送（每周五统一发放）<br>
              <strong>三等奖：</strong>30元现金券<br>
            </li>
            <li>活动最终解释权归尚品网所有。</li>
        </ul>
    </div>
    
    <div class="sroll-text">
    <div>
        <ul class="lottery_list" id="window_roll">
            <c:forEach items="${winList}" var="winList" varStatus="st">
            
            <li>${winList.userName}中了<span>
<c:if test="${winList.prizeLevel}=='4'">1</c:if>
<c:if test="${winList.prizeLevel}=='1' || ${winList.prizeLevel}=='3'  || ${winList.prizeLevel}=='5' || ${winList.prizeLevel}=='6' || ${winList.prizeLevel}=='8' || ${winList.prizeLevel}=='10'">2</c:if>
<c:if test="${winList.prizeLevel}=='9'">3</c:if>
等奖</span>，领走了<span>
<c:if test="${winList.prizeLevel}=='1'">复古风波点蝴蝶结发箍</c:if>
<c:if test="${winList.prizeLevel}=='3'">精美印花手包</c:if>
<c:if test="${winList.prizeLevel}=='4'">1000元衣橱基金</c:if>
<c:if test="${winList.prizeLevel}=='5'">欧根纱花边短袜</c:if>
<c:if test="${winList.prizeLevel}=='6'">草编遮阳帽</c:if>
<c:if test="${winList.prizeLevel}=='8'">鳄鱼纹休闲便鞋</c:if>
<c:if test="${winList.prizeLevel}=='9'">30元衣橱基金</c:if>
<c:if test="${winList.prizeLevel}=='10'">圆形迷你斜挎包</c:if>
</span>……</li>
            </c:forEach>
        </ul>
        </div>
    </div>

    
</div>	
</rapid:override>

 
<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_mall_banner.jsp" %> 
