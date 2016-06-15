var globel_num,globel_times;
$(function(){
	//加载中奖列表
	findWinList();
	//跑马灯开始
	var AutoRoll = function(){
		$(".lottery_list").animate({
			marginTop:"-24px"
		},500,function(){
			$(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
		});
	};
	
	var startRoll = setInterval(AutoRoll,3000);
	//跑马灯结束
	
	var second_message,k=false;

	var animateing = 1;	//转动时定时器

	//点击领取按钮时验证输入内容是否正确
	$("#turntable_btn").click(function(){
		spRotate();
		return;		
	});		
		

	var spRotate = function(){		
		
		if(animateing===0){return;}

		$.ajax({
			type: "GET",
			url: getRootPath()+"/isLogin?"+new Date(),
			success: function(data){
				//未登录
				if(data==undefined || data=="" || data == "0" ){
					window.location= getRootPath() + "/meet/redirect/app?id=meet/single/goodLuck";
					return false;
				}
				//已登录
				if(data == "1"){
					animateing = 0;
					
					$.ajax({
						type: "GET",
						url: getRootPath()+"/activityLottery?activityId=520&date="+Math.random(),
						success: function(data){
							
							 globel_num = parseInt(data.message);
							  globel_times = data.times;
							
							if(globel_times < 0){
								var html = '<h3 style="margin-top:20px;">很遗憾，你未中奖！</h3><p class="win-info">您的抽奖机会已经用完了</p>';
								jShare(html,"","");
								$('div#popup_container').addClass('faid');
								return;
							}
							var turntable = (function(){
								var turntable = {
									range : function(start, end) {  
										var choose = end - start + 1;  
										return Math.floor(Math.random() * choose + start); 
									},
									/*中奖角度算法：
										奖品块数等分360°，奖品分割线左右预留自定义的度数，防止中奖指针指到分割线上。
										逆时针由0-360°递增，算出每个奖品所处位置的角度。
									*/
									probability : {
										1: [325,350,325,350], //一等奖
										2: [280,305,105,125], //三等奖  20元现金券
										3: [235,260,235,260], //  二等奖	
										4: [180,215,180,215], //  未中奖
										0: [180,215,180,215], //  未中奖
										5: [145,170,145,170],// 二等奖	
										6: [55,80,55,80],	 //一等奖
										7: [10,35,10,35]	//一等奖
										
									},
									offset : 10,
									result : function(){
										var randnum = this.range(0,1),
											probability = this.probability[globel_num],
											offset = this.offset;
											
										return this.range(probability[randnum * 2] + offset,probability[randnum * 2 + 1] - offset);
									}
								}
								return turntable.result();
							})();

							//判断几等奖显示什么
							function thePrize(prize){
								var prompt= "";
								switch(prize) {
									case 1:
									case 6:
									case 7:
							        	prompt = '<h3>恭喜您获得<em class="win-prize">一等奖</em></h3><div class="mid-con"><b>限量</b>Q版萌物</div><p class="win-info">衣橱基金已充入您的账户哦，</p><p class="red-t">请前往个人中心查看！</p><div class="div_bottom">您还有<b>'+globel_times+'</b>次抽奖机会</div><div class="lotter-right-img"><img width="100%" src="'+getRootPath()+
							        	'/styles/shangpin/images/20150909GoodLuck/lotter01_img.jpg" /></div>';
							        break;						
									case 3:
									case 5:
							        	prompt = '<h3>恭喜您获得<em class="win-prize">二等奖</em></h3><div class="mid-con f40">神秘礼品</div><p class="win-info">活动结束后，</p><p class="red-t">客服会联系您为您配送奖品哦！</p><div class="div_bottom">您还有<b>'+globel_times+'</b>次抽奖机会</div><div class="lotter-right-img"><img width="100%" src="'+getRootPath()+
							        	'/styles/shangpin/images/20150909GoodLuck/lotter02_img.png" /></div>';
							        break;
									case 2:
							        	prompt = '<h3>恭喜您获得<em class="win-prize">三等奖</em></h3><div class="mid-con f40"><em>￥</em>20<em>元</em></div><p class="win-info">现金劵已充入您的账户哦，</p><p class="red-t">请前往个人中心查看！</p><div class="div_bottom">您还有<b>'+globel_times+'</b>次抽奖机会</div><div class="lotter-right-img"><img width="100%" src="'+getRootPath()+
							        	'/styles/shangpin/images/20150909GoodLuck/lotter03_img.jpg" /></div>';
							        break;
									case 4:
									case 0:
							            prompt = '<h3>很遗憾，您<em class="win-prize">未中奖</em></h3><div class="mid-con f40">再试试手气</div><div class="div_bottom">您还有<b>'+globel_times+'</b>次抽奖机会</div><div class="lotter-right-img"><img width="100%" src="'+getRootPath()+
							        	'/styles/shangpin/images/20150909GoodLuck/lotter04_img.jpg" /></div>';
							        break;
							    }
							    
							    return prompt;
							}

							$('#turntable_img').rotate({

									duration : 3000,
									angle : 0, 
									animateTo :1440 + turntable,
									easing : $.easing.easeOutSine,
									callback : function(){
										
										
										if(globel_times){
											var thisMsg = thePrize(globel_num); //调用公共方法，返回文字提示
											var html = thisMsg;
											/*setTimeout(
												function(){
													jShare(html,"");
											},2000);*/
											
											jShare(html,"");
											
											animateing = 1;
											
											$('div#popup_container').addClass('success');
											
										}
										
									}

									
							 });
						}
					});
				}
			}	
		});

	}
	

	
});

function findWinList(){
	$.ajax({
		type: "GET",
		url: getRootPath()+"/winList?activityId=520",
		success: function(data){
			if(data.winList!=undefined&&data.winList!=null){
				var winList = data.winList;
				var strHtml = "";
				for(var i=0;i<winList.length;i++){
					var win = winList[i];
					strHtml = strHtml + "<li>" +
							win.userName +
							"中了<span>" +
							findLevel(win.prizeLevel) +
							"等奖</span>，领走了<span>" +
							findPrize(win.prizeLevel) +
							"</span>……</li>";
				}
				$('#window_roll').html(strHtml);
			}
			
			/*
			data = 0  未登录<li>XX中了<span>3等奖</span>，领走了<span>30元现金券</span>……</li>
			data = 1  已登录
			*/
		}
	});
}
function findLevel(level){
	 if(level == 1||level == 6||level == 7 ){
		 return 1;
	 }else if(level == 3||level == 5){
		 return 2;
	 }else if(level == 2){
		 return 3;
	 }
}
function findPrize(level){
	var prize_message = "1000元衣橱基金";
	 if(level == 4 ){
		 prize_message =  "";
	 }else if(level == 3||level == 5){
		 prize_message =  "超美单品";
	 }else if(level == 2){
		 prize_message =  "20元现金券";
	 }
	 return prize_message;
}

