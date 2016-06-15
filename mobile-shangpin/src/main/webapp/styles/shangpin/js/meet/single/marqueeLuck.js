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
		//console.log(lottery.times+'^^^^^^'+lottery.speed+'^^^^^^^'+lottery.prize);
		lottery.timer = setTimeout(roll,lottery.speed);
	}	
	return false;
}

var lotteryEnd = function(){

	  //判断次数
	  if(globel_num === 4 ){ //一等奖
		  var html = '<h3>恭喜您获得<strong>一等奖</strong></h3><p class="win-prize"><strong>&yen;<em>1000</em>元</strong><p/><p class="win-info">衣橱基金已充入您的账户哦，<br><strong>请前往<a href="#">个人中心</a>查看！</strong></p><p class="remainder-times">今日你还有'+ globel_times +'次机会</p>';
		  setTimeout(function(){
			 jShare(html,"","");
			 $('#popup_ok').html('X');
		  },300);		  
		  return;
	  }else if(globel_num === 1||globel_num === 3||globel_num === 5||globel_num === 6||globel_num === 8||globel_num === 10){	//二等奖
		  var html = '<h3>恭喜您获得<strong>二等奖</strong></h3><p class="win-prize"><strong>精美单品</strong><p/><p class="win-info">活动结束后，<br><strong>客服会联系您为您配送奖品哦！</strong></p><p class="remainder-times">今日你还有'+ globel_times +'次机会</p>';
		  setTimeout(function(){
			 jShare(html,"","");			
		     $('#popup_ok').html('X');
		  },300);
		  
		  return;
	  }else if(globel_num === 9){	//三等奖
		  var html = '<h3>恭喜您获得<strong>三等奖</strong></h3><p class="win-prize"><strong>&yen;<em>30</em>元</strong><p/><p class="win-info">现金券已充入您的账户哦，<br><strong>请前往<a href="#">个人中心</a>查看！</strong></p><p class="remainder-times">今日您还有'+ globel_times +'次机会</p>';
		  setTimeout(function(){
			 jShare(html,"","");
			 $('#popup_ok').html('X');
		  },300);
		  
		  
		  return;
	  }else{	//未中奖
		  var html = '<h3>很遗憾，您<strong>未中奖</strong></h3><p class="win-prize"><strong><em>再试试手气</em></<p/><p class="remainder-times">今日您还有'+ globel_times +'次机会</p>';
		  setTimeout(function(){
			 jShare(html,"","");
			 $('#popup_ok').html('X');
		  },300);
		  
		  return;
	  }

}

var click=false; 
window.onload=function(){
	//抽奖触发
	lottery.init('lottery');
	$("#lottery .start").bind('click touchend',function(){
		
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
					window.location= getRootPath() + "/meet/redirect/app?id=meet/single/endLessLove";
					return false;
				}
				//已登录
				if(data == "1"){
					  if (click) {
						  return false;
					  }else{			  
							  $.ajax({
								  type: "GET",
								  url: getRootPath()+"/activityLottery?activityId=9&date="+new Date(),
								  success: function(data){
									  if(data.status == 0 && data.message==0){
										  var html = '<h3>很遗憾，您<strong>未中奖！</strong></h3><p class="win-prize"><strong><em>明天再来吧</em></strong><p/><p class="remainder-times">今日抽奖机会您已经用完了</p>';
										  jShare(html,"","");
										  $('#popup_ok').html('X');
										  return;
										}
									  globel_num = parseInt(data.message);
									  globel_times = data.times;
									  if(globel_times >= 0){
										  lottery.speed=300;
										  roll();
										  click=true;
										  return false;
									  }else{
										  var html = '<h3>很遗憾，您<strong>未中奖！</strong></h3><p class="win-prize"><strong><em>明天再来吧</em></strong><p/><p class="remainder-times">今日抽奖机会您已经用完了</p>';
										  jShare(html,"","");
										  $('#popup_ok').html('X');
										  return;
									  }
								  }
							  });
						}
					return false;
				}
				
			}
		});	  
	});
		
};

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
function findPrize(level){
	var prize_message = "1000元衣橱基金";
	 if(level == 4 ){
		 prize_message =  "";
	 }else if(level == 1||level == 3||level == 5||level == 6||level == 8||level == 10){
		 prize_message =  "精美单品";
	 }else if(level == 9){
		 prize_message =  "30元衣橱基金";
	 }
	 return prize_message;
}

