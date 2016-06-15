

$(function(){
	//加载中奖列表
	findWinList();
	var globel_num,globel_times,second_message,second_level;

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


	var loginStatus = 1; //判断登录状态
	var animateing = 1;	//转动时定时器

	$("#turntable_btn").click(function(){
		$.ajax({
			type: "GET",
			url: getRootPath()+"/isLogin?"+new Date(),
			success: function(data){
				/*
				data = 0  未登录
				data = 1  已登录
				*/
				//未登录
				if(data==undefined || data=="" || data == "0" ){
					window.location= getRootPath() + "/meet/redirect/app?id=meet/single/marqueeLuck0708";
					return false;
				}
				//已登录
				if(data == "1"){
					spRotate();
					return false;
				}
				
			}
		});
	});
	

	var spRotate = function(){		
		
		if(animateing===0){return;}

		animateing = 0;
		
		$.ajax({
			type: "GET",
			url: getRootPath()+"/activityLottery?activityId=9&date="+new Date(),
			data: "",
			success: function(data){
				if(data.status == 0 && data.message==0){
					var html = '<h3>很遗憾，你未中奖！</h3><p class="win-prize"><em>明天再来吧</em><p/><p class="remainder-times">今日抽奖机会您已经用完了</p>';
					jShare(html,"","");
					// $('#popup_ok').hide();
					$('div#popup_container').addClass('faid');
					return;
				}
				var turntable = (function(){
					var turntable = {
						range : function(start, end) {  
							var choose = end - start + 1;  
							return Math.floor(Math.random() * choose + start); 
						},
						probability : {
							1: [270,318,270,318],	//一等奖
							2: [45,85,45,85],		//二等奖碎花蕾丝边内裤
							3: [135,170,135,170],	//二等奖夏日花朵发箍
							4: [180,213,180,213],	//二等奖圆形迷你斜挎包
							5: [225,267,225,267],	//二等奖黑白抽象印花丝巾
							6: [315,357,315,357],	//二等奖草编遮阳帽
							7: [90,135,90,135],		//三等奖
							8: [15,35,15,35]		//未中奖
							
						},
						offset : 10,
						result : function(){
							var randnum = this.range(0,1),
								probability = this.probability[findRound(data.message)],
								offset = this.offset;
							return this.range(probability[randnum * 2] + offset,probability[randnum * 2 + 1] - offset);
						}
					}
					return turntable.result();
				})();

				switch(data.message)
				  {
				  case 1:
					  second_message="夏日花朵发箍";
					  break;
				  case 3:
					  second_message="黑白抽象印花丝巾";
					  break;
				  case 5:
					  second_message="碎花蕾丝边内裤";
					  break;
				  case 6:
					  second_message="草编遮阳帽";
					  break;
					case 8:
					  second_message="草编遮阳帽";
					  break;
					case 10:
					  second_message=" 圆形迷你斜挎包";
					  break;
					default:
					  break;
				  }
				
				//判断几等奖显示什么
				function thePrize(prize){
					var prompt= "";
					switch(prize) {
						case 0:
						case 2:
						case 7:
				            prompt = '<h3>很遗憾，你未中奖</h3><p class="win-prize"><em>再试试手气</em><p/><p class="remainder-times">今日你还有'+ data.times +'次机会</p>';
				            break;
				        case 1:
						case 3:
						case 5:
						case 6:
						case 8:
						case 10:
				        	prompt = '<h3>恭喜您获得<strong>二等奖</strong></h3><p class="win-prize" style="margin:0"><em style="font-size:30px;">超美单品</em><br>'+findPrize(prize)+'<p/><p class="win-info">活动结束后<br><strong>客服会联系您为您配送奖品哦！</strong></p><p class="remainder-times">今日你还有'+ data.times +'次机会</p>';
				        	break;
				        case 9:
				        	prompt = '<h3>恭喜您获得<strong>三等奖</strong></h3><p class="win-prize">&yen;<em>30</em>元<p/><p class="win-info">现金券已充入您的账户哦，<br><strong>请前往<a href="#">个人中心</a>查看！</strong></p><p class="remainder-times">今日你还有'+ data.times +'次机会</p>';
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
								var thisMsg = thePrize(parseInt(data.message)); //调用公共方法，返回文字提示
								var html = thisMsg;
								/*setTimeout(
									function(){
										jShare(html,"");
								},2000);*/
								
								jShare(html,"");
								
								animateing = 1;
								
								$('div#popup_container').addClass('success');
								
							}

						
				 });
			}
		});
	}
});
function findWinList(){
	$.ajax({
		type: "GET",
		url: getRootPath()+"/winList?activityId=9",
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
	 if(level == 4 ){
		 return 1;
	 }else if(level == 1||level == 3||level == 5||level == 6||level == 8||level == 10){
		 return 2;
	 }else if(level == 9){
		 return 3;
	 }
}
function findRound(level){
	second_level=8;
	switch(parseInt(level))
	  {
	  case 1:
		  second_level=3;
		  break;
	  case 3:
		  second_level=5;
		  break;
	  case 5:
		  second_level=2;
		  break;
	  case 6:
		  second_level=6;
		  break;
		case 8:
		  second_level=6;
		  break;
		case 10:
		  second_level=4;
		  break;
		 case 9:
			 second_level = 7;
                break;
		default:
			 second_level = 8;
		  break;
	  }
	return second_level;
}
function findPrize(level){
	var prize_message = "";
	if(level==1){
		prize_message="夏日花朵发箍";
	}else if(level==3){
		prize_message="黑白抽象印花丝巾";
	}else if(level==5){
		prize_message="碎花蕾丝边内裤";
	}else if(level==6){
		prize_message="草编遮阳帽";
	}else if(level==8){
		prize_message="草编遮阳帽";
	}else if(level==9){
		prize_message="三等奖现金券";
	}else if(level==10){
		prize_message="圆形迷你斜挎包";
	}
	 return prize_message;
}
