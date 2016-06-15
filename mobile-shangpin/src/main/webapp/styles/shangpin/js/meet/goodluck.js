$(function(){
	var second_message,k=false;

	var animateing = 1;	//转动时定时器

		var $overlay = $('#overlay');
		function modalHidden($ele) {
			$ele.removeClass('modal-in');
			$ele.one('webkitTransitionEnd',function(){
			  $ele.css({"display": "none"});
			  $overlay.removeClass('active');
			});
		}
		$('.start_draw').click(function(e){
			if(animateing===0){return;}

			animateing = 0;
			e.preventDefault();
			var $that = $(this);
			$overlay.addClass('active');
			$('.modal').css({"display": "block"});
			$('.modal').animate(100,function(){			  
			  $('.modal').addClass('modal-in');
			});
		});		
		$('.close').click(function(e){
		  modalHidden($('.modal'));
		  e.stopPropagation();
		  animateing = 1;
		});			
		$overlay.click(function(e){
		  if(e.target.classList.contains('overlay')){
			modalHidden($('.modal'));
		  }
		});		
		
		//点击领取按钮时验证输入内容是否正确
		$(".start_draw_to").click(function(){
			var mre = /^1\d{10}$/;
			//手机号码验证
        	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
				$(".prompt").html("请输入正确手机号");
        		return;
			}	
			if ($.trim($("#J_yzm").val()) == ""){
				$(".prompt").html("请输入正确验证码");
        		return;
			}
			
			setTimeout(spRotate,300);
			return;		
		});		
		

	var spRotate = function(){		
		
		
		
		$.ajax({
			type: "GET",
			url: getRootPath()+"/activityFationRun?"+new Date(),
			data: {
				mobi:$.trim($("#J_mobileNum").val()),
				activityId:10,
				verifyCode:$.trim($("#J_yzm").val())
			},
			success: function(data){
//				var data = $.parseJSON(data);
				
//				data.status = 2;
//
//				data.message = 7; //这个值代表几等奖
				if(data.code!=undefined&&data.code==1){
					$(".prompt").html("请输入正确验证码");
					animateing = 1;
	        		return;
				}
				modalHidden($('.modal'));
				if(data.times < 0){
					var html = '<h3>提示</h3><p class="win-info">不要这样吧，一个手机号只能抽一次！</p>';
					jShare(html,"","");
					$('div#popup_container').addClass('faid');
					animateing = 1;

					return;
				}
				var turntable = (function(){
					var turntable = {
						range : function(start, end) {  
							var choose = end - start + 1;  
							return Math.floor(Math.random() * choose + start); 
						},
						probability : {
							1: [340,350,340,350],		//Tecnica女性跑鞋
							2: [310,320,310,320],		//Tecnica跑步Tshirt
							3: [280,290,280,290],		//X—BIONIC棒球帽
							4: [250,260,250,260],		//Actionfox-Tecnica跑步帽
							5: [220,230,220,230],		//Tecnica跑步头巾
							6: [190,200,190,200],		//未中奖，谢谢参与
							7: [160,170,160,170],	//garmin运动手表
							8: [130,140,130,140],	//美瑞克斯左旋肉碱
							9: [100,110,100,110],	//X—BIONIC运动中筒袜
							10: [70,80,70,80],		//苏泊尔空气翻炸锅
							11: [40,50,40,50],		//未中奖，谢谢参与
							12: [10,20,10,20]		//X—BIONIC能量T恤	
							
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

				switch(parseInt(data.message))
				  {
				  	case 1:
					  second_message="TECICA女性跑鞋";
					  break;
					case 2:
					  second_message="TECNICA跑步TSHIRT";
					  break;
					case 3:
					  second_message="X—BIONIC棒球帽";
					  break;
					case 4:
					  second_message="ACTIONFOX-TECNICA跑步帽";
					  break;
					case 5:
					  second_message="TECNICA跑步头巾";
					  break;
					case 6:
					case 11:
						second_message="谢谢参与";
						break;
					case 7:
						second_message="GARMIN运动手表";
						break;
					case 8:
						second_message="美瑞克斯左旋肉碱";
						break;
					case 9:
						second_message="X—BIONIC运动中筒袜";
						break;
					case 10:
						second_message="苏泊尔空气翻炸锅";
						break;
					case 12:
						second_message="X—BIONIC能量T恤";
						break;
					default:
					  break;
				  }
				
				//判断几等奖显示什么
				function thePrize(prize){
					var prompt= "";
					switch(prize) {
						case 6:
						case 11:
				            prompt = '<h3>未中奖</h3><p class="win-info">很遗憾，手气不太好啊！</p>';
				            break;
						case 1:
				        case 2:
						case 3:
						case 4:
						case 5:
						case 7:
						case 8:
						case 9:
						case 10:
						case 12:
				        	prompt = '<h3>您中奖了</h3><p class="win-prize">恭喜您获得'+second_message+'</p><p class="win-info">3天后FashionRun承办方将与您联系确认信息后，寄送奖品给您，谢谢您参与！</p>';

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
	
	var flag = 0;
    $("#passwordGetCode").click(function() {
    	$(".prompt").html("");
    	var mre = /^1\d{10}$/;
		//手机号码验证
    	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			$(".prompt").html("请输入正确手机号");
    		return;
		}	
    	if(flag!=0){
    		return false;
    	}
    	flag = 1;
        var delay = 59, trigger = this, revert = $(trigger).text();
        obtainCode();
        $('.btn_gradient_gray').css('background','#cecccc')
        $(trigger).attr({'disabled': true }).text(delay + $(trigger).attr("data-waiting")).parents("label").addClass("waiting");
        var counter = setInterval(function() {
            $(trigger).text($(trigger).text().replace(delay, --delay));
            if(delay == 0) {
            	flag = 0;
                window.clearInterval(counter);
                $(trigger).text(revert).removeAttr("disabled").parents("label").removeClass("waiting");
                $('.btn_gradient_gray').css('background','#c62026')
            }
        }, 1000);
    });

	
});
function obtainCode(){
	$.ajax({
		type: "GET",
		url: getRootPath()+"/getsmscode?mobi="+$.trim($("#J_mobileNum").val()),
		success: function(data){
			
		}
	});
}