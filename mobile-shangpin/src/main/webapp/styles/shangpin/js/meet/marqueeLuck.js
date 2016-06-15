var globel_num,globel_times;

function findWinList(){
	$.ajax({
		type: "GET",
		url: getRootPath()+"/findWinList",
		success: function(data){
			if(data.winList!=undefined&&data.winList!=null){
				var winList = data.winList;
				var strHtml = "";
				for(var i=0;i<winList.length;i++){
					var win = winList[i];
					strHtml = strHtml + "<li>" +
							win.userName +
							"中了<span>" +
							parseInt(findLevel(win.prizeLevel)) +
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

$(function(){
	findWinList();
//跑马灯开始
var AutoRoll = function(){
	$(".lottery_list").animate({
		marginTop:"-28px"
	},500,function(){
		$(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
	});
};

var startRoll = setInterval(AutoRoll,3000);
//跑马灯结束
});

//抽奖效果
var lottery={
	index:-1,	//当前转动到哪个位置，起点位置
	count:10,	//总共有多少个位置
	timer:0,	//setTimeout的ID，用clearTimeout清除
	speed:20,	//初始转动速度
	times:0,	//转动次数
	cycle:50,	//转动基本次数：即至少需要转动多少次再进入抽奖环节
	prize:-1,	//中奖位置
	init:function(id){
		if ($("#"+id).find(".lottery-unit").length>0) {
			$lottery = $("#"+id);
			$units = $lottery.find(".lottery-unit");
			this.obj = $lottery;
			this.count = $units.length;
			$lottery.find(".lottery-unit-"+this.index).addClass("active");
		};
	},
	roll:function(){
		var index = this.index;
		var count = this.count;
		var lottery = this.obj;
		$(lottery).find(".lottery-unit-"+index).removeClass("active");
		index += 1;
		if (index>count-1) {
			index = 0;
		};
		$(lottery).find(".lottery-unit-"+index).addClass("active");
		this.index=index;
		return false;
	},
	stop:function(index){
		this.prize=index;
		return false;
	}
};

function roll(){
	lottery.times += 1;
	lottery.roll();
	
	if (lottery.times > lottery.cycle+10 && lottery.prize==lottery.index) {
		clearTimeout(lottery.timer);
		lottery.prize=-1;
		lottery.times=0;
		lotteryEnd();	//转动结束执行方法
		click=false;
	}else{
		if (lottery.times<lottery.cycle) {
			lottery.speed -= 10;
		}else if(lottery.times==lottery.cycle) {
			//var index = Math.random()*(lottery.count)|0;
			//lottery.prize = index;
			lottery.prize=parseInt(globel_num)-1;
			
		}else{
			if (lottery.times > lottery.cycle+10 && ((lottery.prize==0 && lottery.index==7) || lottery.prize==lottery.index+1)) {
				lottery.speed += 110;
			}else{
				lottery.speed += 20;
			}
		}
		if (lottery.speed<40) {
			lottery.speed=40;
		};
		lottery.timer = setTimeout(roll,lottery.speed);
	}
	
	
	
	return false;
}

var lotteryEnd = function(){
	  switch(globel_num)
	  {
	  case 1:
		second_message="复古风波点蝴蝶结发箍";
		break;
	  case 3:
		second_message="精美印花手包";
		break;
	  case 5:
		second_message="奇趣毛球短袜";
		break;
	  case 6:
		second_message="毛绒钻饰护耳套";
		break;
	  case 8:
		second_message="钉珠绗缝MINI斜挎包";
		break;
	  case 10:
		second_message="鳄鱼纹休闲便鞋";
		break;
	  default:
		break;
	  }
	  var pathName=window.document.location.pathname;
	  var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	  //判断次数
	  if(globel_num === 4 ){
		  var html = '<h3>恭喜您获得<strong>一等奖</strong></h3><p class="win-prize">&yen<em>1000</em>元<p/><p class="win-info">衣橱基金已充入您的账户哦，<br><strong>请前往<a href="'+ projectName +'/user/home">个人中心</a>查看！</strong></p><p class="remainder-times">今日你还有'+ globel_times +'次机会</p>';
		  setTimeout(function(){
			 jShare(html,"","");
			 $('#popup_ok').html('X');
		  },300);
		  
		  return;
	  }else if(globel_num === 1||globel_num === 3||globel_num === 5||globel_num === 6||globel_num === 8||globel_num === 10){
		  var html = '<h3>恭喜您获得<strong>二等奖</strong></h3><p class="win-prize"><em>超美单品</em><br>'+second_message+'<p/><p class="win-info">活动结束后<br><strong>客服会联系您为您配送奖品哦！</strong></p><p class="remainder-times">今日你还有'+ globel_times +'次机会</p>';
		  setTimeout(function(){
			 jShare(html,"","");
			 $('#popup_container').addClass('second');
		     $('#popup_ok').html('X');
		  },300);
		  
		  return;
	  }else if(globel_num === 9){
		  var html = '<h3>恭喜您获得<strong>三等奖</strong></h3><p class="win-prize">&yen<em>30</em>元<p/><p class="win-info">衣橱基金已充入您的账户哦，<br><strong>请前往<a href="'+ projectName +'/user/home">个人中心</a>查看！</strong></p><p class="remainder-times">今日你还有'+ globel_times +'次机会</p>';
		  setTimeout(function(){
			 jShare(html,"","");
			 $('#popup_ok').html('X');
		  },300);
		  
		  
		  return;
	  }else if(globel_times == 0){
		  var html = '<h3>很遗憾，你未中奖！</h3><p class="win-prize"><em>明天再来吧</em><p/><p class="remainder-times">今日抽奖机会您已经用完了</p>';
		  setTimeout(function(){
			 jShare(html,"","");
			 $('#popup_ok').html('X');
		  },300);
		  
		  return;
	  }else{
		  var html = '<h3>很遗憾，你未中奖</h3><p class="win-prize"><em>再试试手气</em><p/><p class="remainder-times">今日你还有'+ globel_times +'次机会</p>';
		  setTimeout(function(){
			 jShare(html,"","");
			 $('#popup_ok').html('X');
		  },300);
		  
		  return;
	  }

}

var loginStatus = false; //判断登录状态
var click=false; 
window.onload=function(){
	//抽奖触发
	lottery.init('lottery');
	$("#lottery .start").bind('touchend',function(){
		if (click) {
			  return false;
		  }else{	
	checkLogin();
	  if(loginStatus){//已登录
		  $.ajax({
			  type: "GET",
			  url: getRootPath()+"/activityLottery9?"+Math.random(),
			  data: "",
			  success: function(data){
				  //second_num = data.message;
				  globel_num = parseInt(data.message);
				  globel_times = data.times;
				  if(data.times >= 0){
					  lottery.speed=300;
					  roll();
					  click=true;
					  return false;
				  }else{
					  var html = '<h3>很遗憾，你未中奖！</h3><p class="win-prize"><em>明天再来吧</em><p/><p class="remainder-times">今日抽奖机会您已经用完了</p>';
					  jShare(html,"","");
					  $('#popup_ok').html('X');
					  return;
				  }
				  
				  
			  }
		  });
	  }
	}
//	  else{
//		  //未登录
//		  window.location.href = "http://login.shangpin.com/register?returnUrl=http%3A%2F%2Fwww.shangpin.com%2F#signIn";
//	  }
	});
		
};

function checkLogin (){
	$.ajax({
		type: "GET",
		url: getRootPath()+"/isLogin?"+Math.random(),
		async: false,
		success: function(data){
			/*
			data = 0  未登录
			data = 1  已登录
			*/
			//未登录
			if(data==undefined || data=="" || data == "0" ){
				window.location= getRootPath() + "/meet/redirect/app?id=marqueeLuck";
			}
			//已登录
			if(data == "1"){
				loginStatus = true;
			}
			
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
function findPrize(level){
	var prize_message = "";
	if(level==1){
		prize_message="复古风波点蝴蝶结发箍";
	}else if(level==3){
		prize_message="精美印花手包";
	}else if(level==4){
		prize_message="1000元衣橱基金";
	}else if(level==5){
		prize_message="欧根纱花边短袜";
	}else if(level==6){
		prize_message="草编遮阳帽";
	}else if(level==8){
		prize_message="鳄鱼纹休闲便鞋";
	}else if(level==9){
		prize_message="30元衣橱基金";
	}else if(level==10){
		prize_message="圆形迷你斜挎包";
	}
	 return prize_message;
}
