<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<input id="_iswx" name="_iswx" type="hidden" value="${checkWX }"/>
<c:choose>
	<c:when test="${meet.id == '286'}">
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/292"/>
	</c:when>
	<c:when test="${meet.id == '301'}">
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/299"/>
	</c:when>
	<c:when test="${meet.id == '311'}">
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/310"/>
	</c:when>
	<c:when test="${meet.id == '315'}">
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/312"/>
	</c:when>
	<c:when test="${meet.id == '324'}">
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/323"/>
	</c:when>
	<c:when test="${meet.id == '341'}">
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/340"/>
	</c:when>
	<c:when test="${meet.id == '362'}">
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/361"/>
	</c:when>
	<c:when test="${meet.id == '366'}">
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/365"/>
	</c:when>
	<c:when test="${meet.id == '370'}">
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/369"/>
	</c:when>
	<c:when test="${meet.id == '395'}">
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/394"/>
	</c:when>
	<c:when test="${meet.id == '404'}">
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/403"/>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/${meet.id }"/>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${meet.id>=212 && meet.id <=226 }">
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="尚品520,幸运大转盘,现金天天赚。"/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="尚品网5周年店庆，百万潮流单品大促，低至1.52折！每日还有机会抽取惊喜大奖哦！"/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${ctx }/styles/shangpin/images/520share2.jpg"/>
		<c:if test="${checkAPP}">
		  <div class="shang_share" > 
		         <a href="shangpinapp://phone.shangpin/actiongoshare?title=尚品520,幸运大转盘,现金天天赚。&desc=尚品网5周年店庆，百万潮流单品大促，低至1.52折！每日还有机会抽取惊喜大奖哦！&url=http://m.shangpin.com/meet/${meet.id}" style="height:50px;"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
		   </div>
		</c:if>
	</c:when>
	<c:when test="${meet.id == '286' || meet.id == '301' || meet.id == '311' || meet.id == '315' || meet.id == '324'}">
		<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="独家!《女神新装》尹恩惠同款打底衫,仅99元！"/>
		<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="尚品网独揽郭碧婷,尹恩惠中韩女神新装,成最大赢家!"/>
		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/goddes/goddes-5.jpg"/>
	</c:when>
	<c:when test="${meet.id == '341'}">
		<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="只穿明星同款！60款新装齐上阵，总有一款适合你！"/>
		<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="尹恩惠,郭碧婷等四位女神新装系列在尚品网同步发售，快来和女神撞衫吧！"/>
		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/goddes/goddes-6.jpg"/>
	</c:when>
	<c:when test="${meet.id == '362'}">
		<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="尹恩惠同款女神白色百搭衬衫仅售196元！快同女神一起穿刷爆你的颜值！"/>
		<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="尹恩惠邀你共享下午茶！快来与女神同穿闺蜜装，尚品网惊喜发售“闺蜜价”！"/>
		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/goddes/goddes-7.jpg"/>
	</c:when>
	<c:when test="${meet.id == '366'}">
		<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="女神新装》第八期，郭碧婷带你找回青春！"/>
		<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="郭碧婷逆龄装扮尽显青春风，带你重返十八岁！女神同款新衣，尚品网同步发售，点击选购！"/>
		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/goddes/goddes-8.jpg"/>
	</c:when>
	<c:when test="${meet.id == '370'}">
		<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="应采儿同款黑白撞色套装,仅售489元！"/>
		<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="《女神新装》第九期，颖儿&应采儿玩转花样Lady范儿，更多女神同款，点击查看！"/>
		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/goddes/goddes-9.jpg"/>
	</c:when>
	<c:when test="${meet.id == '395'}">
		<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="应采儿同款黑白撞色套装,仅售489元！"/>
		<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="《女神新装》第九期，颖儿&应采儿玩转花样Lady范儿，更多女神同款，点击查看！"/>
		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/goddes/goddes-9.jpg"/>
	</c:when>
	<c:when test="${meet.id == '404'}">
		<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="尹恩惠同款棉服1元包邮！尚品网CEO又任性了！"/>
		<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="尹恩惠同款棉服尚品网1元包邮！限量1000套！"/>
		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/goddes/goddes-11.jpg"/>
	</c:when>
	<c:otherwise>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="${meet.title}"/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="${meet.title}"/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${ctx }/styles/shangpin/images/share-logo.png"/>
	   <c:if test="${checkAPP}">
		  <div class="shang_share" > 
		         <a href="shangpinapp://phone.shangpin/actiongoshare?title=${meet.title }&desc=${meet.title }&url=http://m.shangpin.com/meet/${meet.id}" style="height:50px;"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40" style="width:40px"></a>
		   </div>
		</c:if>
	</c:otherwise>
</c:choose>