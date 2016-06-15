<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="title">
	集资金，换大礼
</rapid:override>

<rapid:override name="custum">
    <link href="${cdn:css(pageContext.request) }/styles/shangpin/css/base.css" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/wechat-red/wechatRed.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
	var ver = Math.random();
		loader = SP.core
		    .install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
			.excute(function(){
				var code;//按钮的ID
				$('.exchange-btn').click(function(e){
					e.stopImmediatePropagation();
					var $that = $(this);
				    code=$(this).attr("id");
				    var totalPacketMoney=${account.balance}
				    if(totalPacketMoney <code){
					   	$('#overlay').addClass('active');
				   	    $('#title').html("对不起,您募集的资金余额不足!");
				   	    $('#content').html("<a class='enter-active' href=''>确认</a>");
						$('.modal').animate({"display":"block"},100,function(){
						  $('.modal').addClass('modal-in');
						});
					}else{
			        	$('#overlay').addClass('active');
					    $('.modal').animate({"display":"block"},100,function(){
					    	$('.modal').addClass('modal-in'); 
					    });	 
					}
				}); 
				
				$('.btn-modal').click(function(e){
				  modalHidden($('.modal'));
				  e.stopPropagation();
				  history.go(0) //刷新页面
				});
			//点确定
			$('.enter-active').click(function(e){
					var path = getRootPath();
					var amount = code;
					var actiCode;
					if(code == 10){
						actiCode = '10';//这是传入激活现金券额激活码，到时间需要产品提供
					}else if(code == 30){
						actiCode = '30';
					}else if(code == 50){
						actiCode = '50';
					}
// 					$.get(path + "/packet/saveCoupon", {activCode : actiCode, openId : "${account.openId}", accountName: "${account.loginName}", couponValue : code, identify: "二维码"
// 					},function(data){
// 						if(data == "0"){
// 							$.get(path + "/packet/send/msg?openId=${account.openId}&amount=" + amount,function(data){});
// 							window.location.href=path + "/packet/exchange?openId=${account.openId}";
// 						}else{
// 							window.location.href=path + "/packet/exchange?openId=${account.openId}";
// 						}
						
// 					},"json");
					$.ajax({
						url:path + "/packet/saveCoupon",
						type:"post",
						data:{activCode : actiCode, openId : "${account.openId}", accountName: "${account.loginName}", couponValue : code, identify: "二维码"},
						dataType:"json",
						async:false,
						success:function(data){
							if(data=="0"){
								$.get(path + "/packet/send/msg?openId=${account.openId}&amount=" + amount,function(data){
									window.location.href=path + "/packet/exchange?openId=${account.openId}";
								});
							}else{
								window.location.href=path + "/packet/exchange?openId=${account.openId}";
							}
						}
					});
				});
		
		  	//有下面的代码后,点击黑背景会隐藏弹窗		
		     /*  	$('#overlay').click(function(e){
				 if(e.target.classList.contains('overlay')){
					modalHidden($('.modal'));
				  }
				}); 
				 */
				function modalHidden($ele) {
				  $ele.removeClass('modal-in');
				  $ele.one('webkitTransitionEnd',function(){
					$ele.css({"display": "none"});
					$('#overlay').removeClass('active');
				  });
				}
	     });
</script>
</rapid:override>

<rapid:override name="content">
	<div class="exchange_block">
        <div class="exchange-tip">
            <h3>可兑换红包金额</h3>
            <h2><em>${account.balance}</em>元</h2>
            <p>您共有${amount}元红包，已兑换${totalCoupon}元</p>
        </div>
        <ul class="exchange-list">
        	<!--<li>
            	<a href=""><img src="/images/wechatRed/coupon_100.png" /></a>     
            </li>-->
             <p>选择要兑换的现金券，每种现金券每天限兑换一次！</p>
            <li>
            	  <c:set  var="tennum"  value="${tennumber}"/>
            	  <c:set  var="ten"  value="${ten}"/>
    		    <c:choose>
       				<c:when test="${cashone.cashValue==ten}">
       					<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/logo.png" />
                 			<span id="54321">${cashone.cashValue}元现金券</span> 
               			<p>有效期至：${cashone.validTime}<br>每天限量${cashone.threshold}张</p>
               			<em  class="exchanged-btn">已兑换</em>    
       				</c:when>
       				<c:otherwise>
       				 	<c:if test="${tennum>=cashone.threshold}">
           					<i class="exchanged"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/exchanged.png" /></i>
		   				</c:if>
       					<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/logo.png" />
                 			<span>${cashone.cashValue}元现金券</span> 
               			<p>有效期至：${cashone.validTime}<br>每天限量${cashone.threshold}张</p>
               			<em   id="${cashone.cashValue}"  class="exchange-btn">兑换</em>  
       				</c:otherwise>
        		</c:choose>  
            </li>
            <li>
            		<c:set  var="thirtynum"  value="${thirtynumber}"/>
            	  	<c:set  var="thirty"  value="${thirty}"/>
            	<c:choose>
       				<c:when test="${thirty==cashtwo.cashValue}">
       					<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/logo.png" />
                 			<span>${cashtwo.cashValue}元现金券</span> 
               			<p>有效期至：${cashtwo.validTime}<br>每天限量${cashtwo.threshold}张</p>
               			<em  class="exchanged-btn">已兑换</em>    
       				</c:when>
       				<c:otherwise>
       				 	 <c:if test="${thirtynum>=cashtwo.threshold }">
           					<i class="exchanged"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/exchanged.png" /></i>
		   				</c:if>
       					<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/logo.png" />
                 			<span>${cashtwo.cashValue}元现金券</span> 
               			<p>有效期至：${cashtwo.validTime}<br>每天限量${cashtwo.threshold}张</p>
               			<em   id="${cashtwo.cashValue}"  class="exchange-btn">兑换</em>  
       				</c:otherwise>
        		</c:choose>     
            </li>
            <li>
        		  <c:set  var="fiftynum"  value="${fiftynumber}"/>
            	  <c:set  var="fifty"  value="${fifty}"/>
        		 <c:choose>
       				<c:when test="${fifty==cashthree.cashValue}">
       					<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/logo.png" />
                 			<span>${cashthree.cashValue}元现金券</span> 
               			<p>有效期至：${cashthree.validTime}<br>每天限量${cashthree.threshold}张</p>
               			<em  class="exchanged-btn">已兑换</em>    
       				</c:when>
       				<c:otherwise>
       				 	 <c:if test="${fiftynum>=cashthree.threshold}">
           					<i class="exchanged"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/exchanged.png" /></i>
		   				</c:if>
       					<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/logo.png" />
                 			<span>${cashthree.cashValue}元现金券</span> 
               			<p>有效期至：${cashthree.validTime}<br>每天限量${cashthree.threshold}张</p>
               			<em  id="${cashthree.cashValue}"  class="exchange-btn">兑换</em>  
       				</c:otherwise>
        		</c:choose>  
            </li>
        </ul>
    </div>
    <!-- 优惠劵弹层 --> 
      <div class="overlay" id="overlay">
        <section class="modal modal-test">
          <div class="modal-hd" id="title">是否兑换此优惠劵？</div>
          <div class="modal-bd"></div>
          <div class="modal-ft" id="content">
            <span class="btn-modal">取消</span>
     	 <span  id="content" ><a class="enter-active" href="javascript:void(0)">确认</a></span>
          </div>
          
        </section>		
      </div> 
 
</rapid:override>

<%@ include file="/WEB-INF/pages/common/weixin_packet_base.jsp" %> 
