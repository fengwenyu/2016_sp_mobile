<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	尚品网CEO红包,快抢！
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/20151020/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/20151020/ceo_packet.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="page_title">
	尚品网CEO红包,快抢！
</rapid:override>

<rapid:override name="content">
 <input type="hidden" id="amount" value="${amount }">
 <input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/star/index?star=ceo"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="尚品网CEO，1亿红包任性送，人人有份，快来抢现金！"/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="更有尚品网轻奢购物狂欢节，10月22日-28日 精彩不容错过。"/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/20151020/300X300.jpg"/>
	<input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
<div class="wapper1">
  <!--内容区域 start-->
  <section class="main main-box">  
    <div class="img-box bg-box">
    	<i class="right_tag_icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/zly-1021/right_tag_icon.png" ></i>
        <div class="red-val">
          <!--<h3><i><img src="img/packet_icon.png" width="42" ></i>恭喜您获得了</h3>-->
          <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/zly-1021/win_img.png" />
          <c:if test="${amount ne '0'}">
          <h4><em>${amount }</em>元现金和<em>1020</em>元礼包</h4>
          </c:if>
          <c:if test="${amount eq ''|| amount eq '0'}">
              <h4><em>1020</em>元礼包</h4>
          </c:if>
          <a href="${ctx }/red/find?phoneNum=${phoneNum }&amount=${amount }" class="get-btn">立即领取现金</a>
        </div>
    </div>
    
    <!--  明星同款  -->
    <div class="one-list start">
      	<a href="http://m.shangpin.com/product/detail?productNo=30130043&topicId=51013263" class="hat" target="_blank"></a>
        <a href="http://m.shangpin.com/product/detail?productNo=30137010&topicId=51013263" class="coat" target="_blank"></a>
        <a href="http://m.shangpin.com/product/detail?productNo=30150944&topicId=51013263" class="boot" target="_blank"></a>
        <a href="http://m.shangpin.com/product/detail?productNo=30142400&topicId=51013263" class="sweater" target="_blank"></a>
        <a href="http://m.shangpin.com/product/detail?productNo=30137082&topicId=51013263" class="pants" target="_blank"></a>
        <img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/zly-1021/star_product1.jpg" width="100%">
        <img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/zly-1021/star_product2.jpg" width="100%">
    </div>
    
    <div class="one-list" >
      	<a href="http://m.shangpin.com/subject/product/list?topicId=51013263">
        	<img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/zly-1021/meet_img00.jpg" width="100%">
    	</a>
    </div>
    
    <!--  新增M站首页内容  -->
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51018647&amp;postArea=0">
                <img src="http://pic2.shangpin.com/e/s/15/10/21/20151021204121693740-318-156.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=51018650&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/21/20151021204127856448-318-156.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51018646&amp;postArea=0">
                <img src="http://pic2.shangpin.com/e/s/15/10/21/20151021204141334322-318-156.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=51018649&amp;postArea=0">
                <img src="http://pic1.shangpin.com/e/s/15/10/21/20151021204146700486-318-156.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51016525&amp;postArea=0">
                <img src="http://pic5.shangpin.com/e/s/15/10/21/20151021174001423303-316-268.jpg"></a></li>
         <li><a href="http://m.shangpin.com/meet/timeLimit?meetId=368">
                <img src="http://pic6.shangpin.com/e/s/15/10/21/20151021173952187040-316-268.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/390">
            <img alt="" src="http://pic6.shangpin.com/e/s/15/10/21/20151021104801401925-640-268.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/meet/372">
                <img src="http://pic2.shangpin.com/e/s/15/10/22/20151022120604812880-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/meet/373">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020204937383241-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51013265&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020204958209452-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B0797&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020205005728528-316-246.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/357">
            <img alt="" src="http://pic3.shangpin.com/e/s/15/10/20/20151020205025446403-640-140.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <div class="one-list">
          <a href="http://m.shangpin.com/subject/product/list?topicId=51017589&amp;postArea=0">
            <img alt="" src="http://pic3.shangpin.com/e/s/15/10/21/20151021210746597519-640-300.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/368">
            <img alt="" src="http://pic3.shangpin.com/e/s/15/10/21/20151021205835133329-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/meet/376">
                <img src="http://pic5.shangpin.com/e/s/15/10/21/20151021132545719919-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/meet/377">
                <img src="http://pic1.shangpin.com/e/s/15/10/21/20151021132552318299-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/meet/378">
                <img src="http://pic1.shangpin.com/e/s/15/10/21/20151021132604486606-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/meet/379">
                <img src="http://pic4.shangpin.com/e/s/15/10/21/20151021132609759049-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/meet/380">
                <img src="http://pic3.shangpin.com/e/s/15/10/21/20151021132619446772-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/meet/381">
                <img src="http://pic1.shangpin.com/e/s/15/10/21/20151021132626887070-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/meet/383">
                <img src="http://pic3.shangpin.com/e/s/15/10/21/20151021132642269926-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/meet/384">
                <img src="http://pic5.shangpin.com/e/s/15/10/21/20151021205922916987-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/meet/385">
                <img src="http://pic3.shangpin.com/e/s/15/10/21/20151021132658509650-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/meet/382">
                <img src="http://pic6.shangpin.com/e/s/15/10/21/20151021132703657416-316-246.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/375">
            <img alt="" src="http://pic6.shangpin.com/e/s/15/10/20/20151020205603668372-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B0005&amp;postArea=0">
                <img src="http://pic5.shangpin.com/e/s/15/10/20/20151020210439157724-316-400.jpg"></a></li>
         <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B0010&amp;postArea=0">
                <img src="http://pic2.shangpin.com/e/s/15/10/20/20151020210443806204-316-400.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B0109&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020210455366875-316-400.jpg"></a></li>
         <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B0006&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020210459874701-316-400.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/375">
            <img alt="" src="http://pic3.shangpin.com/e/s/15/10/20/20151020210521418304-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B0520&amp;postArea=0">
                <img src="http://pic1.shangpin.com/e/s/15/10/20/20151020210608140737-316-400.jpg"></a></li>
         <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B0514&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020210613850918-316-400.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B0152&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020210624380033-316-400.jpg"></a></li>
         <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B0008&amp;postArea=0">
                <img src="http://pic5.shangpin.com/e/s/15/10/20/20151020210629200586-316-400.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/375">
            <img alt="" src="http://pic4.shangpin.com/e/s/15/10/20/20151020210644691399-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B1829&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/21/20151021112142132998-316-400.jpg"></a></li>
         <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B0036&amp;postArea=0">
                <img src="http://pic5.shangpin.com/e/s/15/10/21/20151021112147998127-316-400.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B02725&amp;postArea=0">
                <img src="http://pic6.shangpin.com/e/s/15/10/21/20151021112200992944-316-400.jpg"></a></li>
         <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B0014&amp;postArea=0">
                <img src="http://pic6.shangpin.com/e/s/15/10/21/20151021112206031959-316-400.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/376">
            <img alt="" src="http://pic3.shangpin.com/e/s/15/10/21/20151021164822890901-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51006625&amp;postArea=0">
                <img src="http://pic2.shangpin.com/e/s/15/10/20/20151020210834203036-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=50929391&amp;postArea=0">
                <img src="http://pic3.shangpin.com/e/s/15/10/20/20151020210841394753-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=50929382&amp;postArea=0">
                <img src="http://pic5.shangpin.com/e/s/15/10/20/20151020210851129554-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=50929386&amp;postArea=0">
                <img src="http://pic1.shangpin.com/e/s/15/10/20/20151020210857150321-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=50929390&amp;postArea=0">
                <img src="http://pic5.shangpin.com/e/s/15/10/20/20151020210912001207-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=50929384&amp;postArea=0">
                <img src="http://pic5.shangpin.com/e/s/15/10/20/20151020210922469888-316-246.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/377">
            <img alt="" src="http://pic6.shangpin.com/e/s/15/10/21/20151021164837116378-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51010017&amp;postArea=0">
                <img src="http://pic1.shangpin.com/e/s/15/10/20/20151020211039205763-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=51010018&amp;postArea=0">
                <img src="http://pic1.shangpin.com/e/s/15/10/20/20151020211048643235-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51010025&amp;postArea=0">
                <img src="http://pic2.shangpin.com/e/s/15/10/20/20151020211124460148-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=51022896&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/22/20151022115732101179-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51012121&amp;postArea=0">
                <img src="http://pic1.shangpin.com/e/s/15/10/20/20151020211146300449-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=51010033&amp;postArea=0">
                <img src="http://pic2.shangpin.com/e/s/15/10/20/20151020211153273364-316-246.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/379">
            <img alt="" src="http://pic4.shangpin.com/e/s/15/10/21/20151021164907255338-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/brand/product/list?brandNo=B0797&amp;postArea=0">
                <img src="http://pic2.shangpin.com/e/s/15/10/20/20151020210536956142-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=50921523&amp;postArea=0">
                <img src="http://pic6.shangpin.com/e/s/15/10/20/20151020210545286856-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=50921519&amp;postArea=0">
                <img src="http://pic5.shangpin.com/e/s/15/10/20/20151020210602353997-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=50921571&amp;postArea=0">
                <img src="http://pic6.shangpin.com/e/s/15/10/20/20151020210610886277-316-246.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/378">
            <img alt="" src="http://pic4.shangpin.com/e/s/15/10/22/20151022091846475929-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51010915&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020210740336288-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=51010924&amp;postArea=0">
                <img src="http://pic2.shangpin.com/e/s/15/10/20/20151020210747949166-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51010971&amp;postArea=0">
                <img src="http://pic1.shangpin.com/e/s/15/10/20/20151020210802083142-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=51010916&amp;postArea=0">
                <img src="http://pic1.shangpin.com/e/s/15/10/20/20151020210811146782-316-246.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/380">
            <img alt="" src="http://pic1.shangpin.com/e/s/15/10/21/20151021164923041448-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=50928202&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020204322309739-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=50928203&amp;postArea=0">
                <img src="http://pic3.shangpin.com/e/s/15/10/19/20151019194257032279-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=50928204&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020204358329921-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=50928205&amp;postArea=0">
                <img src="http://pic2.shangpin.com/e/s/15/10/20/20151020204411980098-316-246.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/382">
            <img alt="" src="http://pic2.shangpin.com/e/s/15/10/21/20151021165011947252-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51012193&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020205914097362-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=51012176&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020205930384413-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51012194&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020210002161496-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=51012192&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/19/20151019202236910225-316-246.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/381">
            <img alt="" src="http://pic4.shangpin.com/e/s/15/10/21/20151021164934772576-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51012151&amp;postArea=0">
                <img src="http://pic6.shangpin.com/e/s/15/10/20/20151020204713300549-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=51012154&amp;postArea=0">
                <img src="http://pic6.shangpin.com/e/s/15/10/20/20151020204724142691-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=51013291&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020205008333024-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=51012156&amp;postArea=0">
                <img src="http://pic3.shangpin.com/e/s/15/10/20/20151020205020532413-316-246.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/384">
            <img alt="" src="http://pic4.shangpin.com/e/s/15/10/21/20151021164949093552-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=50921602&amp;postArea=0">
                <img src="http://pic6.shangpin.com/e/s/15/10/20/20151020205228139152-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=50921624&amp;postArea=0">
                <img src="http://pic3.shangpin.com/e/s/15/10/20/20151020205241976306-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=50921721&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020205525323120-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=50921752&amp;postArea=0">
                <img src="http://pic3.shangpin.com/e/s/15/10/20/20151020205538178965-316-246.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/383">
            <img alt="" src="http://pic5.shangpin.com/e/s/15/10/21/20151021165000278436-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=50929297&amp;postArea=0">
                <img src="http://pic5.shangpin.com/e/s/15/10/20/20151020205643464971-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=50818939&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/20/20151020205658783815-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=50818935&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/19/20151019204321075077-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=50929448&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/19/20151019204356565785-316-246.jpg"></a></li>
         </ul>
    <div class="one-list">
          <a href="http://m.shangpin.com/meet/385">
            <img alt="" src="http://pic3.shangpin.com/e/s/15/10/21/20151021165025160846-640-60.jpg" width="100%" style="opacity: 1;">
        </a>
        </div>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=50930527&amp;postArea=0">
                <img src="http://pic2.shangpin.com/e/s/15/10/20/20151020210304557016-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=50929404&amp;postArea=0">
                <img src="http://pic5.shangpin.com/e/s/15/10/20/20151020210319050017-316-246.jpg"></a></li>
         </ul>
    <ul class="two-list clr">
        <li><a href="http://m.shangpin.com/subject/product/list?topicId=50929410&amp;postArea=0">
                <img src="http://pic2.shangpin.com/e/s/15/10/20/20151020210340672508-316-246.jpg"></a></li>
         <li><a href="http://m.shangpin.com/subject/product/list?topicId=50930528&amp;postArea=0">
                <img src="http://pic4.shangpin.com/e/s/15/10/19/20151019202801738083-316-246.jpg"></a></li>
    </ul>
    <!--  新增M站首页内容  -->
    
    <div class="bg-box1"> 
    <a href="http://m.shangpin.com/download"> 
    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/zly-1021/ad_img03.jpg" > 
    </a>
    </div>
    
    <p class="link_box">
        <!-- <a class="download-btn" href="#">99元活动专区</a>
        <a class="download-btn" href="#">199元活动专区</a> -->
        <span class="clr">
        	<a href="${ctx }/red/coupon/rule">如何使用</a>
            <a href="${ctx }/coupon/list">查看我的券</a>
        </span>
    </p>
    <p class="service-phone">如您在领取现金或红包过程中遇到任何问题<br>请立即致电尚品网客服 <a href="tel:4006-900-900">4006－900－900</a></p>
    <!---->
    
  </section>
  </div>
</rapid:override>

<rapid:override name="static_file">
    <script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/jquery.min.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/ceo_packet.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/star_red_packet_base.jsp" %>