<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>免费送出同款撞衫</title>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/slot/base.css${ver}" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/slot/style.css${ver}" rel="stylesheet" />
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/activity/jquery.min.js${ver}" type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/slot/jquery.slotmachine.js${ver}" type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/slot/xmas.dialogs.js${ver}" type="text/javascript" charset="utf-8"></script>
</head>	
<body>
<div class="line">
 <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/product/freebie/prod-title.png" />
 <div class="topImg">
   	<img src="${fn:replace(productDetail.basic.allPics[0],'-10-10','-600-758')}" />
   	<div class="item-info">
        <h5 class="item-name">${productDetail.basic.brand.nameEN }</h5>
        <p class="item-desc">${productDetail.basic.prefix }${productDetail.basic.productName } ${productDetail.basic.suffix }</p>
        <div class="item-detail">
          <div class="item-price">
                <span class="refer-price">
			     <c:set var="firstP" value="${productDetail.basic.defaultIndex.firstPropIndex}"/>
			     <c:set var="secondP" value="${productDetail.basic.defaultIndex.secondPropIndex}"/>
				 <c:choose>
					<c:when test="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion=='0'}">
							<c:choose>
								<c:when test="${productDetail.basic.firstProps[firstP].secondProps[secondP].isSupportDiscount==1 }">
									<c:choose>
										<c:when test="${userLv == '0002'}">
											&yen;${productDetail.basic.firstProps[firstP].secondProps[secondP].goldPrice }
										</c:when>
										<c:when test="${userLv == '0003'}">
											&yen;${productDetail.basic.firstProps[firstP].secondProps[secondP].platinumPrice }
										</c:when>
										<c:when test="${userLv == '0004'}">
											&yen;${productDetail.basic.firstProps[firstP].secondProps[secondP].diamondPrice }
										</c:when>
										<c:otherwise>
											&yen;${productDetail.basic.firstProps[firstP].secondProps[secondP].limitedPrice }
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									&yen;${productDetail.basic.firstProps[firstP].secondProps[secondP].limitedPrice }
								</c:otherwise>
							</c:choose>
					</c:when>
					<c:otherwise>
						 &yen;${productDetail.basic.firstProps[firstP].secondProps[secondP].promotionPrice }
					</c:otherwise>
				</c:choose>
              </span>
          </div>
        </div>
    </div>
</div>
<div class="line">
    <div class="content"> 
        <div class="machine-box">
            <div id="machine1" class="slotMachine">
                <div class="slot slot1"></div>
                <div class="slot slot2"></div>
                <div class="slot slot3"></div>
                <div class="slot slot4"></div>
            </div>
            <div id="machine2" class="slotMachine">
                <div class="slot slot1"></div>
                <div class="slot slot2"></div>
                <div class="slot slot3"></div>
                <div class="slot slot4"></div>
            </div>
            <div id="machine3" class="slotMachine">
                <div class="slot slot1"></div>
                <div class="slot slot2"></div>
                <div class="slot slot3"></div>
                <div class="slot slot4"></div>
            </div>
            <input type="hidden" value="${p}" id="p"/>
            <div id="slotMachineButton1" class="slotMachineButton"></div>

        </div>

    </div>
    <div class="click-btn"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/slot/click-btn.png" /></div>
    <div class="rule-text">
       <h3 class="rule-title"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/slot/rule-title.png" ></h3>     
      1. 点击“抢”按钮参与游戏，同时获得3颗桃心组合，
      就能免费领取好友的同款撞衫<br>
      2. 若获得其他符号组合，将免费领取100元优惠券满500元可用
      （TOPSHOP、TOPMAN、Miss Selfridge通用，特殊商品除外。
      使用截止日期：2015年5月26日）<br>
      3. 活动时间从5月20日0点至5月26日24点，每个ID仅限参与一次<br>
      4. 本活动为尚品网APP专享活动，解释权在法律规定的范围内归尚品网所有<br>
    </div>
</div>
</div>
<script>
$(document).ready(function(){
	var stopNum1,stopNum2,stopNum3;
	var isClick = true;;
	
	
	function onComplete($el, active){
		switch($el[0].id){
			case 'machine1':
				$("#machine1Result").text("Index: "+active.index);
				break;
			case 'machine2':
				$("#machine2Result").text("Index: "+active.index);
				break;
			case 'machine3':
				$("#machine3Result").text("Index: "+active.index);
				break;
		}
	}
	
	$("#slotMachineButton1").click(function(){
		if(isClick){
				$.ajax({
					type: "GET",
					url: "../acivity/slot?"+new Date()+"&p="+$("#p").val(),
					success: function(data){
					stopNum1 = data.stopNum1;
					stopNum2 = data.stopNum2;
					stopNum3 = data.stopNum3;
				if(data.state == 10){
					var html = '<p>活动已结束!</p>';
					jShare(html,"",function(){
					});
					$("#popup_ok").html("关闭").css({'background':'#FEB343','font-size':'13px','border-radius':'5px'});
				}
				if(data.state == 3){
					var html = '<p>只能抢一次哟!不可以太贪心咯!</p>';
					jShare(html,"",function(){
					 });
					$("#popup_ok").html("关闭").css({'background':'#FEB343','font-size':'13px','border-radius':'5px'});
				}
				var machine1 = $("#machine1").slotMachine({
					active	: 0,
					delay	: 1000,
					stopIndex: stopNum1 //这句是新加的参数，要指定停止在哪个位置，修改数值就可以了。
				});
				
				var machine2 = $("#machine2").slotMachine({
					active	: 0,
					delay	: 1000,
					stopIndex: stopNum2
				});
				
				var machine3 = $("#machine3").slotMachine({
					active	: 0,
					delay	: 1000,
					stopIndex: stopNum3
				});
				
				if(data.state == 1){
					machine1.shuffle(1, onComplete);
					setTimeout(function(){
						machine2.shuffle(1, onComplete);
					}, 1000);						
					setTimeout(function(){
						machine3.shuffle(1, onComplete);									
					}, 2000);
					setTimeout(function(){
						if((machine1.active().index==machine2.active().index==machine3.active().index==0)){
							 var html = '<p>恭喜您！获得同款撞衫</p>';
							 jShare(html,"",function(){
							 	window.location.href="../product/freebieDetail?p="+$("#p").val();
							 });
						}else{
							var html = '<p>恭喜您！<br>获得100元优惠券</p>';
							jShare(html,"",function(){
							 	window.location.href="../acivity/receive";
							 });
						}
						isClick=false;
					},3500);
					
				}
			}
		});
		
		}

	})
});
</script>
</body>
