$(function(){
	
	
	
	//跑马灯开始
	var AutoRoll = function(){
		$(".lottery_list").find("ul:first").animate({
			marginTop:"-28px"
		},500,function(){
			$(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
		});
	};
	
	var startRoll = setInterval(AutoRoll,3000);
	//跑马灯结束
	
	/*$("#turntable_btn").rotate({
		bind:{
			click:function(){
				
			}
		}
	});*/

	

	$("#turntable_btn").click(function(){
		//goodLuck();
		//window.location="http://192.168.7.244:88/html/2014520/coupons.html";
			
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
						window.location= getRootPath() + "/meet/redirect/app?id=goodLuck520";
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
		$.ajax({
			type: "GET",
			url: getRootPath()+"/activityLottery520?"+new Date(),
			data: "",
			success: function(data){
				//var data = $.parseJSON(data);
				//data.status = data.isDraw;//是否抽中
				//一等奖1000元现金券,二等奖200元现金券,三等奖1000元礼券包
				//四等奖500元礼券包,五等奖300元礼券包
				if(data.status =="0"){
					var html = '<h3>明天再来吧！</h3><p>休息,休息！明天再来试试手气!</p>';
					jShare(html,"","");
					// $('#popup_ok').hide();
					$('div#popup_container').addClass('faid');
					return;
				}
				//data.message = data.prizeLevel; //这个值代表几等奖
				//data.times=data.remainCount;//抽奖次数
				var turntable = (function(){
					var turntable = {
						range : function(start, end) {  
							var choose = end - start + 1;  
							return Math.floor(Math.random() * choose + start); 
						},
						probability : {
							1: [330,380,330,380],
							2: [100,150,200,260],
							3: [30,90,150,200,260,330]
							
						},
						offset : 10,
						result : function(){
							var randnum = this.range(0,1),
								probability = this.probability[data.message],
								offset = this.offset;
								
							return this.range(probability[randnum * 2] + offset,probability[randnum * 2 + 1] - offset);
						}
					}
					return turntable.result();
				})();
				
				//判断几等奖显示什么
				function thePrize(prize){
					prize=parseInt(prize);
					var prompt= "";
					switch(prize) {
				        case 1:
				            prompt = "您获得<span>一</span>等奖<span>300元优惠券</span>一张 ,<br>“<a href=\""+getRootPath()+"/coupon/list\" target=\"_blank\">请到您的个人账户中查看！</a>”<br>";
				            break;
				        case 2:
				        	prompt = "您获得<span>二</span>等奖<span>150元优惠券</span>一张 ,<br>“<a href=\""+getRootPath()+"/coupon/list\" target=\"_blank\">请到您的个人账户中查看！</a>”<br>";
				        	break;
				        case 3:
				        	prompt = "您获得<span>三</span>等奖<span>30元现金券</span>一张 ,<br>“<a href=\""+getRootPath()+"/coupon/list\" target=\"_blank\">请到您的个人账户中查看！</a>”<br>";
				        	break;
				    }
				    
				    return prompt;
				}

				$('#turntable_img').rotate({

						duration : 3000,
						angle : 0, 
						animateTo :1440 + turntable - 30,
						easing : $.easing.easeOutSine,
						callback : function(){
							
							
							if(data.status=="1"){
								var thisMsg = thePrize(data.message); //调用公共方法，返回文字提示
								var count=parseInt(data.times);
								if(count==0){
									var html = '<h3>恭喜您中奖了！</h3><p>休息休息，明天再来！<br />'+thisMsg;
								}else{
									var html = '<h3>恭喜您中奖了！</h3><p>您还有<span>'+data.times+'</span>次抽奖机会，再试试手气！<br />'+thisMsg;
								}
								jShare(html,"");
								$('div#popup_container').addClass('success');
								
							}else{
								var html = '<h3>哎呀！不好</h3><p>抽奖系统异常！</p>';
								jShare(html,"","");
								// $('#popup_ok').hide();
								$('div#popup_container').addClass('faid');
								return;
							}
							
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
					url: "/data/active.txt?" + new Date().getTime(),
					//data: $('#verify_text').val(),
					success: function(data){
						if(data == "1"){
							jShare('手机验证通过，立即抽奖吧!',"","");
							setTimeout(function(){
								window.location.href="http://192.168.7.244:88/html/2014520/goodluck.html";
							
							},1000)
							
							
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
$(function(){
	if($("#_iswx").val()){ 
		$('.share-icon').click(function(){
			$('.share-tip').show();
		});
		$('.share-tip').click(function(){
			$('.share-tip').hide();
		})
	}	
});
