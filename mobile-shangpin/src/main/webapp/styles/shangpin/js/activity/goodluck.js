$(function(){
	var globel_num,globel_times,second_message;


	//var loginStatus = $("#isLogin").val(); //判断登录状态
	var animateing = 1;	//转动时定时器
	var html = "";
	
	$("#turntable_btn").click(function(){
		/*if(loginStatus=="1"){
			spRotate();
		}else{*/
			$.ajax({
				type: "GET",
				url: getRootPath()+"/isLogin?"+new Date(),
				success: function(data){
					//未登录
					if(data==undefined || data=="" || data == "0" ){
						var isApp=$("#_isapp").val();
						if(isApp){
							var path =$("#basePath").val();
							window.location= getRootPath() + "/accountaction!loginui?callback="+path+"/acivity/turntable";
						}else{
							window.location= getRootPath() + "/login?back=/acivity/turntable";
						}
					}
					//已登录
					if(data == "1"){	
						spRotate();
					}				
				}
			});
		
	});
	

	var spRotate = function(){		
		
		if(animateing===0){return;}

		animateing = 0;
		
		$.ajax({
			type: "GET",
			url:  getRootPath()+"/acivity/lottery?date="+Math.random(),
			data: "",
			success: function(data){
                globel_times = parseInt(data.times);//剩余抽奖机会
				globel_num =  parseInt(data.message);//几等奖?
				if(globel_times== -1){
					animateing = 1;
					var  prompt = '<p class="blod">您只有一次抽奖机会!</p>';
					jShare(prompt,"","");
					$("#popup_ok").html("X");
					return;
				}
				var turntable = (function(){
					var turntable = {
						range : function(start, end) {  
							var choose = end - start + 1;  
							return Math.floor(Math.random() * choose + start); 
						},
						probability : {
							/*0: [120,180,120,180],	//未中奖
							1: [300,360,300,360],	//50 topshop
							2: [240,300,240,300],		//100 topshop
							3: [180,240,180,240],	//草莓音乐节票							
							4: [60,120,60,120],	//30元现金券
							5: [0,60,0,60],	//再来一次
*/							
							0: [120,180,120,180],	//未中奖
							1: [180,240,180,240],	//50 topshop
							2: [240,300,240,300],		//100 topshop
							3: [60,120,60,120],	//草莓音乐节票							
							4: [300,360,300,360],	//30元现金券
							5: [120,180,120,180],	//再来一次
							6: [0,60,0,60],	//再来一次
						},
						offset : 15,
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
					case 0:
			            prompt = '<p class="blod">很遗憾没有中奖，谢谢您的参与</p>';
			            break;
			        case 1:
			        	prompt = '<h3>恭喜您</h3><p class="blod">获得草莓音乐节随机日票</p><p>请给在线客服留言，留下您的姓名及手机号，我们会尽快通知您</p>';
			            break;
			        case 2:
			        	prompt = '<h3>恭喜您</h3><p class="blod">获得100元TOPSHOP＆TOPMAN现金券</p><p>请在2016年5月15日前使用</p>';
			            break;
			        case 3:
			        	prompt = '<h3>恭喜您</h3><p class="blod">获得30元TOPSHOP＆TOPMAN现金券</p><p>请在2016年5月15日前使用</p>';
			            break;
			        case 4:
			        	 prompt = '<h3>恭喜您</h3><p class="blod">获得50元TOPSHOP＆TOPMAN优惠券</p><p>请在2016年5月15日前使用</p>';
			        	break;
			        case 5:
			        	 prompt = '<p class="blod">很遗憾没有中奖，谢谢您的参与</p>';
			        	break;
			        case 6:
			        	 prompt = '<p class="blod">您获得了再抽一次的机会哦</p>';	
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
							if(data.status >= 0){
								var thisMsg = thePrize(globel_num); //调用公共方法，返回文字提示
								html = thisMsg;
								/*setTimeout(
									function(){
										jShare(html,"");
								},2000);*/
								
								jShare(html,"");
								$("#popup_ok").html("X");
								
								animateing = 1;
								
								$('div#popup_container').addClass('success');
								
							}
							
						}
				 });
			}
		});
	}


	
});