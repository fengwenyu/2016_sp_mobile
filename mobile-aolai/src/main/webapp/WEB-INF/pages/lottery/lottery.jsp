<%@ page language="java" import="java.util.*,com.shangpin.mobileAolai.common.alipay.util.Base64" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ include file="/WEB-INF/pages/common/hm.jsp" %>
<%@ include file="/WEB-INF/pages/common/message.jsp"%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>尚品奥莱触屏版</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/201408lottery/index.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%>/images/logo/loading.png">

<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/jquery.min.js" + ver)
			.using("<%=path%>/js/j.lazyload.js" + ver)
			.using("<%=path%>/js/jquery.easing.min.js" + ver)
			.using("<%=path%>/js/jQueryRotate.2.2.js" + ver)
			.using("<%=path%>/js/xmas.dialogs.js" + ver)
			.excute()
			.using("<%=path%>/js/config.sim.js" + ver)
			.excute()
			.using("<%=path%>/js/lottery/lottery.js" + ver)
			.excute(function(){
				isHome(false);
			});
</script>
</head>

<body id="bodyId">
 <%
		 String ua =request.getHeader("user-agent").toLowerCase();
		 String appstr = "AolaiAndroidApp";
		 String appIOSStr = "shangpinaolaiiosapp";
		 boolean appFlag = false;
		 boolean appIOSFlag = false;
		  if(ua.indexOf(appstr.toLowerCase())>-1){
		      if(ua.indexOf("4.0.0")>-1){
		          appFlag = true;
		      }               
		  }else if (ua.indexOf(appIOSStr.toLowerCase())>-1) {
			  appIOSFlag = true;
		  }
	%>
<div class="alContent">
	<div class="turntable">
    	<div id="turntable_btn"></div>
    	<input type="hidden" id="android_login" value="<%=appFlag%>">
    	<input type="hidden" id="ios_login" value="<%=appIOSFlag%>">
    	<img id="turntable_img" src="<%=path%>/images/e.gif" lazy="<%=path%>/images/201408lottery/turntable_img_03.png" width="225" height="225">
        <img id="turntable_bg" src="<%=path%>/images/e.gif" lazy="<%=path%>/images/201408lottery/turntable_bg.jpg" width="320" >
        <a href="#" class="rule_link">&nbsp;</a>
    </div>
    <div class="con">
        <img id="" src="<%=path%>/images/e.gif" lazy="<%=path%>/images/201408lottery/turntable_bg1.jpg" width="320" height="283">
        <dl>
            <dd class="lottery_list" id="window_roll">
                <ul>
                <s:iterator value="lotteryList" id="lotteryList" >
                    <li><s:date name="#lotteryList.createTime" format="yyyy/MM/dd" />获得拼图 
                    <s:if test='#lotteryList.reserve1.contains("A")'>
                    	A
                    </s:if>
                    <s:elseif test='#lotteryList.reserve2.contains("B")'>
                    	B
                    </s:elseif>
                    <s:elseif test='#lotteryList.reserve3.contains("C")'>
                    	C
                    </s:elseif>
                    <s:elseif test='#lotteryList.reserve4.contains("D")'>
                    	D
                    </s:elseif>
                    </li>
                </s:iterator>
                </ul>
            </dd>
        </dl>
        
        <ul class="pic_game clr">
        		<s:if test='list.contains("A")'>
                    <li class="pic01 ">A</li>
                </s:if>
                <s:else >
                	<li>A</li>
                </s:else>
                <s:if test='list.contains("B")'>
                    <li class="pic02 ">B</li>
                </s:if>
                
                <s:else>
                	<li>B</li>
                </s:else>
                <s:if test='list.contains("C")'>
                    <li class="pic03 ">C</li>
                </s:if>
                <s:else>
                	<li>C</li>
                </s:else>
                <s:if test='list.contains("D")'>
                    <li class="pic04 ">D</li>
                </s:if>
                <s:else>
                	<li>D</li>
                </s:else>
        </ul>
        
        <div class="pic_notice">
       		<p class="nomarl">收集4块图片,即可<br />免费获得该奖品</p>
            <p class="suceess">恭喜您获得该奖品 <a href="about.jsp">&nbsp;</a></p>
            <s:if test='list.size()>"3"'>
	            <style type="text/css">
					.con .pic_notice p.nomarl {display: none}
					.con .pic_notice p.suceess {display: block}
				</style>
        	</s:if>
        </div>
        
    </div>
</div>
  
<div id="popLayer">
    <ul>
    	<h2><img src="<%=path%>/images/201408lottery/title01.png" width="200" height="20"></h2>
        <li>每个账号每天抽奖4次；</li>
        <li>通过抽奖方式集齐4张奖品图片，即可免费获得该奖品；</li>
        <li>关注尚品奥莱官方微信：spaolai，将此活动成功分享至好友或朋友圈后，截图至官方微信即可获赠&yen;10元现金券；</li>
        <li>本活动最终解释权归尚品奥莱所有。</li>
        <a href="#" id="pop_btn">&nbsp;</a>
    </ul>
</div>
<%
_HMT _hmt = new _HMT("2cd596b4dcc6e6078c5e76c70b3b4e27");
_hmt.setDomainName("m.aolai.com");
_hmt.setHttpServletObjects(request, response);
String _hmtPixel = _hmt.trackPageview();
%>
<img src="<%= _hmtPixel %>" width="0" height="0"  />
<!-- 百度统计代码 -->
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F2cd596b4dcc6e6078c5e76c70b3b4e27' type='text/javascript'%3E%3C/script%3E"));
</script>

<!-- 尚品LOG记录 -->
<script type="text/javascript">
var _tongji = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _tongji + "js11.shangpin.com/tongji.js' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>
