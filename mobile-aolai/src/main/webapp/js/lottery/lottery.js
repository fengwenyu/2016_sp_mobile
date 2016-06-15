$(function(){
	
	//隐藏细则层
	$("#pop_btn").click(function(){
		$("#popLayer").animate({
			"top":"-743px"
		},500);
		return false;
	});
	
	$(".rule_link").click(function(){
		$("#popLayer").animate({
			"top":"0"
		},500);
		return false;
	});
	
	//跑马灯开始
	var rollLen = $(".lottery_list ul li").length,
		AutoRoll = function(){
		$(".lottery_list").find("ul:first").animate({
			marginTop:"-28px"
		},500,function(){
			$(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
		});
	};
	
	if(rollLen > 7){
		var startRoll = setInterval(AutoRoll,3000);
	}
	//跑马灯结束
	var pathName=window.document.location.pathname;
	var path = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	var loginStatus = 1; //判断登录状态

	$("#turntable_btn").click(function(){
		$.ajax({
			type: "GET",
			url: "accountaction!checkIsBind",
			success: function(data){
				//绑定手机
				if(parseInt(data.num)== 0){
					spRotate();
					return ;					
				}else if(parseInt(data.num)==1){//未绑定手机
					window.location = "accountaction!bindphone";
					return;
				}else if(parseInt(data.num) < 0){
					var enurl=escape(aolaiurl+"accountaction!applogin?loginFrom="+data.loginFrom);
					var android_login=$("#android_login").val();
					var ios_login=$("#ios_login").val();
					if(android_login == 'true'){
						window.location = shangpinurl+"accountaction!loginui?loginFrom="+data.loginFrom+"&callback="+enurl;						
					}else if(ios_login == 'true'){
						window.location = aolaiurl+"accountaction!loginui?loginFrom="+data.loginFrom+"&callback="+enurl;
					}else{
						window.location = "accountaction!loginui?loginFrom="+data.loginFrom+"&callback="+enurl;
					}
					return;
				}
				
			}
		});
	});

	var spRotate = function(){


		$.ajax({
			type: "GET",
			url: "actlotaction!getLottery",
			data: "",
			success: function(data){
				
				if(parseInt(data.num) == -1){
					var html = '<h3>很遗憾, 您没有抽奖机会了!</h3><p>休息休息,明天再来</p>';
					jShare(html,"","");
					$('div#popup_container').addClass('faid');
					return;
				}else if(parseInt(data.num) == -2){
					var html = '<h3>抽奖活动已经结束！</h3>';
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
						probability : {
							1 : [90,110,90,110],
							2 : [355,375,355,375],
							3 : [260,280,260,280],
							4 : [175,195,175,195],
							5 : [40,60,40,60],
							6 : [320,345,125,145],
							7 : [220,240,220,240]
						},
						offset : 10,
						result : function(){
							var randnum = this.range(0,1),
								probability = this.probability[data.num],
								offset = this.offset;
							return this.range(probability[randnum * 2] + offset,probability[randnum * 2 + 1] - offset);
						}
					}
					return turntable.result();
				})();
				
				$('#turntable_img').rotate({

						duration : 3000,
						angle : 0, 
						animateTo :1440 + turntable - 30,
						easing : $.easing.easeOutSine,
						callback : function(){
							var html = "";
							if (data.image !=null) {
								if (parseInt(data.num) ==1) {
									html = '<h3><img src="'+path+'/images/201408lottery/success_bg.png" height="52"><br /><img src="'+path+'/images/201408lottery/title02.png" height="20" style="margin:15px 0;"></h3><p>您获得了<span> A </span>图片，继续加油啊！<br /><br />您还有<span> '+data.times+' </span>次抽奖机会，再试试手气！</p>';
									$(".pic_game li:nth-child(1)").addClass("pic01");
								} else if (parseInt(data.num) ==2) {
									html = '<h3><img src="'+path+'/images/201408lottery/success_bg.png" height="52"><br /><img src="'+path+'/images/201408lottery/title02.png" height="20" style="margin:15px 0;"></h3><p>您获得了<span> B </span>图片，继续加油啊！<br /><br />您还有<span> '+data.times+' </span>次抽奖机会，再试试手气！</p>';
									$(".pic_game li:nth-child(2)").addClass("pic02");
								} else if (parseInt(data.num) ==3) {
									html = '<h3><img src="'+path+'/images/201408lottery/success_bg.png" height="52"><br /><img src="'+path+'/images/201408lottery/title02.png" height="20" style="margin:15px 0;"></h3><p>您获得了<span> C </span>图片，继续加油啊！<br /><br />您还有<span> '+data.times+' </span>次抽奖机会，再试试手气！</p>';
									$(".pic_game li:nth-child(3)").addClass("pic03");
								} else if (parseInt(data.num) ==4) {
									html = '<h3><img src="'+path+'/images/201408lottery/success_bg.png" height="52"><br /><img src="'+path+'/images/201408lottery/title02.png" height="20" style="margin:15px 0;"></h3><p>您获得了<span> D </span>图片，继续加油啊！<br /><br />您还有<span> '+data.times+' </span>次抽奖机会，再试试手气！</p>';
									$(".pic_game li:nth-child(4)").addClass("pic04");
								} else if (parseInt(data.one) ==100) {
									$(".con .pic_notice p.nomarl").style.display='none';
									$(".con .pic_notice p.suceess").style.display='block';
								}
								
							} else if (parseInt(data.num) ==5) {
								html = '<h3><img src="'+path+'/images/201408lottery/success_bg.png" height="52"><br /><img src="'+path+'/images/201408lottery/title02.png" height="20" style="margin:15px 0;"></h3><p>您获得了<span> 30 </span>元现金券，所获现金券无使用限制哟！<br /><br />您还有<span> '+data.times+' </span>次抽奖机会，再试试手气！</p>';
							} else if (parseInt(data.num) ==6) {
								html = '<h3><img src="'+path+'/images/201408lottery/success_bg.png" height="52"><br /><img src="'+path+'/images/201408lottery/title02.png" height="20" style="margin:15px 0;"></h3><p>您获得了<span> 10 </span>元现金券，所获现金券无使用限制哟！<br /><br />您还有<span> '+data.times+' </span>次抽奖机会，再试试手气！</p>';
							}
							
							jShare(html,"");
							$('div#popup_container').addClass('success');
							
						}

						
				 });
			}
		});
	}

	/*验证手机弹出新页面效果*/
	//点击获取验证码
	var phoneed = 0; //标识是否验证过手机
	$('body').delegate('#verify_btn','click',function(){
		
		var $this = $(this);
		if($this.hasClass('countdown_ing')){
			return false;
		}
		
		//判断手机格式
		var phoneVal = $('#verify_text').val();
		
		if (!/^\d{11}$/.test(phoneVal) && !phoneed) {
			
			jShare('您输入的手机号有误，请重新输入!',"","");
			
		}else{
			phoneed = 1; //手机已验证
			
			jShare('验证码获取成功!',"","");
			
			$this.addClass('countdown_ing');
			var time = 60;//倒计时时间60秒
			var countDownTime = setInterval(function(){
				if(time != 1){
					time--;
					//$this.text( time + 's后重新获取验证码');
				}else{
					
					$this.removeClass('countdown_ing');
					clearInterval(countDownTime);
					countDownTime = null;
				}
			},1000);					
		}
	});
	
	//点击领取优惠券
	$('body').delegate('#code_btn','click',function(){
		
		//判断验证码格式
		var codeVal = $('#code_text').val();
		
		if(codeVal == "" || codeVal.length < 6 || codeVal.length > 6){
			
			jShare('请输入正确验证码!',"","");
			
		}else{
			var self = this;
			//手机已验证
			if(phoneed){
				$.ajax({
					type: "GET",
					url: 'accountaction!verifyphonecode',
					//data: $('#verify_text').val(),
					success: function(data){
						if(data == "1"){
							jShare('手机验证通过，立即抽奖吧!',"","");
							setTimeout(function(){
								window.location.href=aolaiurl+"actlotaction!lotteryIndex?activeId=20140805";
							
							},1000);
							
							
						}else{
							jShare('验证码错误!',"","");
						}
					},
					error : function(){
							
					}
				});
			}else{
				jShare('请先获取验证码!',"","");
			}
			return false;
			
		}
		
	});
});