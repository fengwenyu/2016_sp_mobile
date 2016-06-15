<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<script type="text/javascript" charset="utf-8">
  var ver = Math.random();
    loader = SP.core
      .install("${cdn:css(pageContext.request)}/styles/shangpin/js/jquery.min.js?" + ver)  //jquery库文件
        .using("${cdn:css(pageContext.request)}/styles/shangpin/js/lazyload.js?" + ver)    //图片懒加载
        .excute()
        .using("${cdn:css(pageContext.request)}/styles/shangpin/js/config.sim.js?" + ver)
        .excute()
        .using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}")
      
</script>
<style>
body{background:#0e0e0e; color: #ffffff}
.container {min-width:320px; max-width:640px;	margin:0 auto; font-size:12px;}
body .page_box{margin-top:45px;}
.title_img{ width:100%;}
.page_box h3,.page_box h2{ padding:0 10px;}
.page_box h2{ margin-top:25px; font-size:16px;}

.p_text{ padding:0 10px; font-size:12px; line-height:1.5; margin-top:15px;}

.page_info{margin:10px 0 0;}
.page_info dl{margin-top:5px; padding:0 10px; position:relative;}
.page_info dt, .page_info dd{float:left; font-size:12px; line-height:20px;}
.page_info dt{ width:60px; text-align:left; position:absolute;}
.page_info dd{ width:auto; margin-left:60px; }
.page_info dd img{border:1px solid #d8d8d8;}

.sponsor_bar{ background:#444444; margin-top:30px;}
.sponsor_bar h4{ background:#333333; font-size:14px; padding:10px;}
.sponsor_bar p{ padding:11px 0;}
.sponsor_bar p.text{ padding:10px;}

.page_box .h_logo{ margin-top:10px; position:relative;}
.page_box .h_logo i{ width:31px; height:35px; position:absolute; top:-35px; left:10px;}

.page_info dl.new_dl dd{ margin:0;}

footer{ text-align:center; width:100%; padding:32px 0; border:none;}
footer a{ color:#fff;}
.shang_share{position: fixed;right:15px;bottom:68px;z-index:999;}
</style>
</rapid:override>

<%-- 浏览器标题 --%>
<rapid:override name="title">
	FashionRun
</rapid:override>

<%-- 页标题 --%>
 <rapid:override name="page_title">
	FashionRun
</rapid:override> 
 <rapid:override name="appLayer">
</rapid:override> 
<rapid:override name="downloadAppShowHead">
		
</rapid:override>
<rapid:override name="content">
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${basePath }/fashionrun/share"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${basePath }/styles/shangpin/images/FashionRun/fashion-run.jpg"/>
  <div class="alContent page_box">
		<p class="title_img"><img src="../styles/shangpin/images/FashionRunShare/header_banner.jpg" alt=""></p>
        
        <p class="p_text">8月1日，这个星球上最时尚的跑步赛事Fashion Run将要落地北京奥林匹克森林公园啦！</p>
		<p class="p_text">赛事报名即日启动。该赛事由2015年北京国际田联世界田径锦标赛组委会主办，是2015年北京国际田联世界田径竞标赛官方宣传推广活动之一，中国最大的时尚轻奢跨境电商尚品网为首席赞助商。</p>
		<p class="p_text">Fashion Run以“不美不跑”为宗旨，是北京首个只面向时尚女性的大型跑步赛事，传递个性、时尚、健康的生活态度，倡导全北京的女性朋友跑起来。</p>
		<p class="p_text">Fashion Run让时尚女性跑起来，让女性跑者时尚起来。</p>
        
        <h3><img src="../styles/shangpin/images/FashionRunShare/bg_01.jpg" alt=""></h3>
        <p class="p_text">Fashion Run将有300多来自各行各业最时尚的女性领跑者带你跑步！奥运冠军、服装设计师、医生、记者……由这些美丽的榜样女性领跑，Fashion Run将会是年度最有影响力、专注倡导女性跑步、关注女性健康与时尚的盛会!</p>
        <p class="p_text">Fashion Run将为你提供史上最时尚、最超值的的惊喜参赛包！跨界定制的时尚运动Bra、荧光抽绳背包、玩味十足潮跑Ｔ恤、糖果多彩发带、限量炫彩手机防水袋、时尚专属奖牌……更有英伦顶级时尚品牌TOPSHOP提供的大礼包！此外，首席赞助商尚品网将会把参赛装备为你免费快递到家，无需现场领取！</p>
        <p class="p_text">Fashion Run有最时尚、最酷炫的赛前美妆环节，让追求时尚的你分分钟过足时尚大瘾，跑起来也美美哒！</p>
        <p class="p_text">Fashion Run有最时尚、最健硕的鲜肉男为你领跑、为你加油呐喊。最后，他们还将在终点组成人墙等你冲破，给你十足的动力跑完五公里！</p>
        <p class="p_text">Fashion Run有最时尚、最有趣的途中互动环节，让你轻松玩转跑步！水枪大战、泡泡秀等，将为你冲淡夏日的炎热，增添跑步的乐趣，让你性感地跑起来！</p>
        <p class="p_text">Fashion Run有最时尚的专业摄影师准备了长枪大炮在现场等候着你，全程捕捉你最精彩的瞬间，记录下每一个最美丽、最性感的你！</p>
        <p class="p_text">Fashion Run有最时尚的Ladies Only女款顶级跑步装备专属展示区，赛后现场展示最前沿、最专业、最时尚的跑步装备，让你放松随意逛，还能成为一名时尚跑步运动达人！</p>
        <p class="p_text">Fashion Run准备了最时尚的UBER　Black豪车接你到比赛现场，为你解除交通的烦恼。此外，Fashion Run还准备了20个一票难求的2015北京国际马拉松参赛名额等你来抢！</p>
        
        <h3><img src="../styles/shangpin/images/FashionRunShare/bg_02.jpg" alt=""></h3>
        <p class="p_text">Fashion Run只需要你跑得更加个性，更加美丽！ 怎么时尚就怎么跑！我们的比赛更有乐趣！沿途迎接你的可能是长枪短炮的镜头阵，也有可能是猛男手里的水枪哦！我们的比赛更加激情！终点处我们设猛男肉墙，到达终点冲破男模人墙完成比赛就能获得专属奖牌！除了在5公里的距离里尽情展示最时尚的自己，你更可以在活动后期参与网络评选，做最具人气的跑者！时尚奖品多多，惊喜环节多多！</p>
        <h3><img src="../styles/shangpin/images/FashionRunShare/bg_04.jpg" alt=""></h3>
        <h2 class="h_logo">赛事装备<i><img src="../styles/shangpin/images/FashionRunShare/h_bg.png" alt=""></i></h2>
        <p class="p_text">我们为你准备了史上最时尚、最超值的跑步装备包！</p>
        <p class="p_text">跨界定制时尚运动BRA、悦跑荧光抽绳背包、玩味十足潮跑白Ｔ恤、限量炫彩手机防水袋、潮跑必备糖果多彩发带时尚领跑者专属奖牌。</p>
        <p class="p_text">参赛包内更有英国高街品牌TOPSHOP惊喜礼包哦！</p>
		<h2>时尚装备快递到家</h2>
        <p class="p_text">为了省去你在线下领取装备包的奔波劳碌和麻烦， 我们特别联系了活动首席赞助商尚品网，把装备和礼包免费寄送到你家中，让你轻松感受Fashion Run的魅力。</p>
        
		<h3><img src="../styles/shangpin/images/FashionRunShare/bg_03.jpg" alt=""></h3>
        
        <section class="page_info">
        	<dl class="clr">
                <dt>活动时间：</dt>
                <dd>2015年8月1日 7:00-8:00签到，8:30开跑</dd>
            </dl>
            <dl class="clr">
                <dt>集合地点：</dt>
                <dd>奥林匹克森林公园南门</dd>
            </dl>
            <dl class="clr">
                <dt>赛事里程：</dt>
                <dd>5公里</dd>
            </dl>
            <dl class="clr">
                <dt>参赛人员：</dt>
                <dd>时尚美女</dd>
            </dl>
            <dl class="clr">
                <dt>报名费用：</dt>
                <dd>168元/人</dd>
            </dl>
            
            <dl class="new_dl clr">
                <dt>完赛奖励：</dt>
                <dd><br />1、所有完赛选手将获得Fashion Run专属奖牌一枚；<br />2、完赛后将设置丰厚时尚装备抽奖环节；<br />3、完赛后将通过网络评选“个性着装”“动感女郎”“最具人气”等奖项。</dd>
            </dl>
        </section>
        <p class="p_text">公益捐赠：赛事收益将捐赠中国服装设计师协会扶持中国原创时尚设计。</p>
        
        <h2>赛事地点</h2>
        <p class="p_text">Fashion Run城市将陆续在以下城市进行北京，哈尔滨，上海，南京，杭州，西安，成都，重庆，长沙，厦门，深圳……</p>
        <p class="p_text">Fashion Run首站设在中国北京奥林匹克森林公园</p>
        
        <div class="sponsor_bar">
        	<h4>首席赞助</h4>
            <p><img src="../styles/shangpin/images/FashionRunShare/img01.png" alt=""></p>
            <h4>联合赞助</h4>
            <p><img src="../styles/shangpin/images/FashionRunShare/img02.png" alt=""></p>
            <h4>支持单位</h4>
            <p><img src="../styles/shangpin/images/FashionRunShare/img03.png" alt=""></p>
            <h4>赞助商</h4>
            <p><img src="../styles/shangpin/images/FashionRunShare/img04.png" alt=""></p>
            <h4>主办方</h4>
            <p class="text">2015年北京国际田联世界田径锦标赛组委会</p>
            <h4>承办方</h4>
            <p class="text">森博公关集团</p>
        </div>
		
        <footer>
        	联系我们　<a href="mailto:info@fashionrun.org">info@fashionrun.org</a>
        </footer>
    </div>
<%-- <c:if test="${checkAPP}">
  <div class="shang_share"> 
         <a href="shangpinapp://phone.shangpin/actiongoshare?title=FashionRun北京站美女招募,8月1日奥森公园不美不跑.&url=${basePath }/fashionrun/share&desc=FashionRun北京站美女招募,8月1日奥森公园不美不跑."><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
   </div>
</c:if> --%>
</rapid:override>
<rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common_no_content_no_banner.jsp" %>