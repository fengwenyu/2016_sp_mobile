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
	.excute();
	</script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/abroadBuy/20150520AbroadBuy.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<%@ include file="/WEB-INF/pages/common/header.jsp"%>
	</c:if>
</rapid:override>
<rapid:override name="content">
<div class="alContent">
	<h1><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520AbroadBuy/top_banner.jpg" alt="海外购" /></h1>
    <div class="prBox">
    	<!--------海外时尚奢品盛宴-------->
        <section>
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520AbroadBuy/buy_pr_02.jpg" />
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520AbroadBuy/buy_pr_03.jpg" />
        </section>
        <!--------专线物流5至10日送达-------->
        <section>
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520AbroadBuy/buy_pr_04.jpg" />
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520AbroadBuy/buy_pr_05.jpg" />
        </section>
        <!--------全中文一站式购物体验-------->
        <section>
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520AbroadBuy/buy_pr_06.jpg" />
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520AbroadBuy/buy_pr_07.jpg" />
        </section>
        <!--------7天无忧退货服务-------->
        <section>
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520AbroadBuy/buy_pr_08.jpg" />
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520AbroadBuy/buy_pr_09.jpg" />
        </section>
        <!--------扫货指南-------->
        <section>
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520AbroadBuy/buy_pr_10.jpg" />
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520AbroadBuy/buy_pr_11.jpg" />
        </section>
    </div>
    <div class="detail">
        <h3>扫货注意事项：</h3>
        <ol>
            <li>1、尚品网海外购商品暂不支持使用任何优惠码、优惠券；</li>
            <li>2、尚品网海外购商品暂不支持使用礼品卡余额及货到付款支付方式；</li>
            <li>3、订单从海外发货后，一般情况下5-7个工作日可以送到您的手中；</li>
            <li>4、尚品网海外购商品发货时会附上商品清单，暂时无法提供国内消费发票。</li>
        </ol>    
    </div>
    <a class="link" href="${ctx }/meet/215">立即体验海外购</a>
</div>
	
</rapid:override>

 
<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_mall_banner.jsp" %> 