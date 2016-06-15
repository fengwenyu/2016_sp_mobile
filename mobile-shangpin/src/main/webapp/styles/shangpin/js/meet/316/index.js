var loginStatus = false;
var isLogin=false;
//clcik
;(function($){
	$.fn.dbRotateClick2D=function(options){
		var opt={
			rotateSpeed:100       //��ת�ٶ�
		}
		$.extend(opt,options);
		 var flipBg;
		return this.each(function(){
			var $this=$(this);
			var $img=$this.find('img');
			var imgWidth=$img.width();
			var imgHeight=$img.height();
		  

			var mOver=false;
			init();

			function init(){
				setCss();
				setMouseEvent();
			}
			
			function setCss(){	
				$this.css({'width':imgWidth,'height':imgHeight});
				$img.data({'out':$img.attr('src'),'over':$img.attr('alt')});
			}
			
			
			var prize_num,remain_times,isWin;
			function setMouseEvent(){
				$this.bind('click',function(){
				checkLogin();
					//接受数据
				if(loginStatus){//已登录
					if(mOver==false){
					$.ajax({
						  type: "GET",
						  url: getRootPath()+"/goddes/activityFollower?date="+new Date().getTime(),
						  data: "",
						  success: function(data){
							  
							  if(data.code =="1"){
								 $('.login-box').fadeOut();
								 var html = '<h3> 亲爱的~</h3><p class="win-prize">优惠劵领取失败<p/>';
								 
								 jShare(html,"","");
								 $('#popup_container').height(150);
								 $('#popup_ok').html('X');
								return;
							  }else{
								  // "0未抽到 1抽到",
								  isWin = data.status;     //是否已中过奖
								  prize_num = data.message; //目前返回的是等级奖品数
								  remain_times = data.remainCount; //剩余次数
								  console.log(data.status,data.message,data.remainCount);
								  flipAnimate();
								  mOver=true;
							  }
						  }
					  });
					}
				   }else{
					  //未登录
						$('.login-box').fadeIn();
						$("#login_box1 .sure-btn").click(function(){
						  var mre = /^1\d{10}$/;
						  //手机号码验证
						  if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
							  $(".prompt").html("请输入正确手机号");
							  return;
						  }	
						  if ($.trim($("#J_yzm").val()) == ""){
							  $(".prompt").html("请输入正确验证码");
							  return;
						  };
						  $(".prompt").html(" ");
						  var verifycode = $("#J_yzm").val();
						  var phoneNum = $("#J_mobileNum").val();
						  if(mOver==false){
						  $.post(getRootPath()+ "/goddes/checkActivity",{"phoneNum" : phoneNum, "verifycode": verifycode},function(data){
								if(data.code =="2"){
									$(".prompt").html(data.msg);
									return;
								}
								loginStatus =1;
								if(data.code !="1"){
									 $('.login-box').fadeOut();
									   setTimeout(function(){
									   isWin = data.status;     //是否已中过奖
									   prize_num = data.message; //目前返回的是等级奖品数
									   remain_times = data.remainCount; //剩余次
									   console.log(data.status,data.message,data.remainCount);
									   flipAnimate();
									   mOver=true;
									   },800);
								}else{
									 $('.login-box').fadeOut();
									 var html = '<h3> 亲爱的~</h3><p class="win-prize">优惠劵领取失败<p/>';
									 jShare(html,"","");
									 $('#popup_container').height(150);
									 $('#popup_ok').html('X');
									return;
								}
						},"json");
						  }  
					  });
						
				   }

				});
			}
			
			function flipAnimate(){
				            var path=getRootPath();
							if(remain_times >= 0){
								if(isWin==0){
									flipBg =  path+"/styles/shangpin/images/flipDraw/flip_bg1.jpg"; 
									$this.find('img').attr('alt',flipBg);
									if(mOver==false){
										mOver=true;
										setAnimation();
									}
								}else{
									
									var flipBgArr = [
									  //"img/flip_bg1.jpg",
									  path+"/styles/shangpin/images/flipDraw/flip_bg2.jpg",
									  path+"/styles/shangpin/images/flipDraw/flip_bg3.jpg",
									  path+"/styles/shangpin/images/flipDraw/flip_bg4.jpg",
									  path+"/styles/shangpin/images/flipDraw/flip_bg5.jpg",
									  path+"/styles/shangpin/images/flipDraw/flip_bg6.jpg",
									  path+"/styles/shangpin/images/flipDraw/flip_bg7.jpg"
									]; 
									flipBg = flipBgArr[prize_num-1];
									$this.find('img').attr('alt',flipBg);
								 
									  if(mOver==false){
										  mOver=true;
										  setAnimation();										  
										  winResults();										 
										  
									  }
									  return false;
								 }
							
							  }else{
								 var html = "<h3> 亲爱的~</h3><p class=\"win-prize\">三次邂逅已消费完毕，<br>明天继续等你呦!<p/><p class=\"tip-info\"><a href=\""+getRootPath()+"/coupon/list\">我的券儿</a></p>";
								  jShare(html,"","");
								  $('#popup_ok').html('X');
							  }
			}
			function setAnimation(){
				if(mOver==true){
					$img.stop()
						.animate({'left':imgWidth/2,'width':0},opt.rotateSpeed,function(){
							$(this).attr({'src':$(this).attr('alt')});
						})
						.animate({'left':0,'width':imgWidth},opt.rotateSpeed);
					
				}else{
					$img.stop()
						.animate({'left':imgWidth/2,'width':0},opt.rotateSpeed,function(){						
							$(this).attr({'src':$(this).data('out')});
						})
						.animate({'left':0,'width':imgWidth},opt.rotateSpeed);
				}
			}
			
			var winResults = function(){
				  //判断得奖名次
				  if(prize_num == 1 ){
					  var html = "<h3> 撒花~</h3><p class=\"win-prize\">&yen;<em>5</em>元现金券<p/><p class=\"win-info\">已妥妥被您抱回家~</p><p class=\"remainder-times\">今日你还有"+ remain_times +"次机会</p><p class=\"tip-info\"><a href=\""+getRootPath()+"/coupon/list\">我的券儿</a></p>";
					  setTimeout(function(){ 
						  jShare(html,"","");
						  $('#popup_ok').html('X');
					  },300);
					  
					  return;
				  }else if(prize_num == 2 ){
					 var html = "<h3> 撒花~</h3><p class=\"win-prize\">&yen;<em>10</em>元现金券<p/><p class=\"win-info\">已妥妥被您抱回家~</p><p class=\"remainder-times\">今日你还有"+ remain_times +"次机会</p><p class=\"tip-info\"><a href=\""+getRootPath()+"/coupon/list\">我的券儿</a></p>";
					  setTimeout(function(){
						 jShare(html,"","");
						 $('#popup_ok').html('X');
					  },300);
					  
					  return;
				  }else if(prize_num == 3 ){
					  var html = "<h3> 撒花~</h3><p class=\"win-prize\">&yen;<em>15</em>元现金券<p/><p class=\"win-info\">已妥妥被您抱回家~</p><p class=\"remainder-times\">今日你还有"+ remain_times +"次机会</p><p class=\"tip-info\"><a href=\""+getRootPath()+"/coupon/list\">我的券儿</a></p>";
					  setTimeout(function(){
						  jShare(html,"","");
						  $('#popup_ok').html('X');
					  },300);
					  
					  return;
				  }else if(prize_num == 4 ){
					  var html = "<h3> 撒花~</h3><p class=\"win-prize\">&yen;<em>20</em>优惠券<p/><p class=\"win-info\">已妥妥被您抱回家~</p><p class=\"remainder-times\">今日你还有"+ remain_times +"次机会</p><p class=\"tip-info\"><a href=\""+getRootPath()+"/coupon/list\">我的券儿</a></p>";
					  setTimeout(function(){
						  jShare(html,"","");
						  $('#popup_ok').html('X');
					  },300);
					  
					  return;
				  }else if(prize_num == 5 ){
					  var html = "<h3> 撒花~</h3><p class=\"win-prize\">&yen;<em>50</em>元优惠券<p/><p class=\"win-info\">已妥妥被您抱回家~</p><p class=\"remainder-times\">今日你还有"+ remain_times +"次机会</p><p class=\"tip-info\"><a href=\""+getRootPath()+"/coupon/list\">我的券儿</a></p>";
					  setTimeout(function(){
						  jShare(html,"","");
						  $('#popup_ok').html('X');
					  },300);
					  
					  return;
				  }else if(prize_num == 6 ){
					  var html = "<h3> 撒花~</h3><p class=\"win-prize\">&yen;<em>100</em>元优惠券<p/><p class=\"win-info\">已妥妥被您抱回家~</p><p class=\"remainder-times\">今日你还有"+ remain_times +"次机会</p><p class=\"tip-info\"><a href=\""+getRootPath()+"/coupon/list\">我的券儿</a></p>";
					  setTimeout(function(){
						  jShare(html,"","");
						  $('#popup_ok').html('X');
					  },300);
					  
					  return;
				  }else{
					  
					  return;
				  }
			}
	
		})			
				
	}			
})(jQuery);

	


$(function(){
	var path = getRootPath();
	var windowWidth=$('.container').width();
	$('.flip-list li img').css({'width':windowWidth/2,'height':windowWidth/2});
	//抽奖判断判断登录状态
	loginStatus = parseInt($("#isLogin").val());
	$('.flip-list li').dbRotateClick2D({});
	
    //跑马灯开始
    var AutoRoll = function(){
	  $(".lottery_list").animate({
		  marginTop:"-24px"
	  },500,function(){
		  $(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
	  });
    };
	var startRoll = setInterval(AutoRoll,3000);
	

	
	/*规则弹层*/
	var $overlay = $('#overlay');
	/**/function modalHidden($ele) {
		$ele.removeClass('modal-in');
		$ele.one('webkitTransitionEnd',function(){
		  $ele.css({"display": "none"});
		  $('#overlay').removeClass('active');
		});
	}
	//领取优惠劵判断登录状态;
	isLogin = parseInt($("#isLogin").val());
	
	$('.coupon-list li').click(function(e){
		e.preventDefault();
		var couponName = $(this).attr('data-name');
		var couponNum = $(this).attr('data-num');
		$('.coupon-name span').html(couponName);
		$('.coupon-num').html(couponNum);
		var code = $(this).attr("id");
		var dataId;
		var pop_pic = $(this).attr('data-url');
		$('.modal-bd>p>img').attr('src',pop_pic);
		checkLogin();
		if(isLogin){
			$.ajax({
				    url : path + "/coupon/ajax/getCoupon?timestmp=" + new Date().getTime(),
					data : {
						"couponCode" : code,
						"couponType": 8
					},
					dataType : "json",
					success : function(data) {
						if ("0" == data.code) {
						   $('.modal-hd').css('marginBottom','0');
						   $('#overlay').addClass('active');
						   $('.modal').css({"display": "block"});
						   $('.modal').animate(100,function(){
						      $('.modal').addClass('modal-in');
						   });
						   return;
						} else if ("1" == data.code) {
							 $('#overlay').addClass('active');
							 $('.modal').css({"display": "block"});
							 $('.modal').animate(100,function(){
							      $('.modal').addClass('modal-in');
							   });
							 $('.modal-hd').html('亲，您今天领过啦，明天再来吧').css('marginBottom','40px');
							 $('.modal-bd p').hide();
							 $('.modal-bd .click-look').html("查看优惠劵");
								return;
						} else if ("2" == data.code) {
							var isApp=$("#_isapp").val();
		                	if(isApp){
		                		window.location.href=path+"/coupon/app/getCoupon?back=/subject/product/list_"+$("#topicId").val();
		                	}else{
		                		window.location.href=path+"/login?back=/subject/product/list_"+$("#topicId").val();
		                		 
		                	}
							return;
						} else {
						   $('#overlay').addClass('active');
						   $('.modal').css({"display": "block"});
						   $('.modal').animate(100,function(){
						      $('.modal').addClass('modal-in');
						   });
						   $('.modal-hd').html('优惠劵领取失败').css('marginBottom','40px');
						   $('.modal-bd').hide();
							return;
						}

					}
			  });
		}else{
			 $('.login-box').fadeIn();
			 dataId = $('#login_box2').attr('data-id',code);
		}
	});
	
	$('.btn-modal').click(function(e){
	  modalHidden($('.modal'));
	  e.stopPropagation();
	});
		
	$('#overlay').click(function(e){
	  if(e.target.classList.contains('overlay')){
		modalHidden($('.modal'));
	  }
	});
	
		//判断手机输入框内容长度
		$("#J_mobileNum").on("keyup", function(){
			var len = $(this).val().length;
			if(len == 11){
				$(this).blur();
			}
		});
		$("#J_yzm").on("keyup", function(){
			var len = $(this).val().length;
			if(len == 6){
				$(this).blur();
			}
		});		
		
		//点击领取按钮时验证输入内容是否正确
		$("#login_box2 .sure-btn").click(function(){
		    var mre = /^1\d{10}$/;
			//手机号码验证
        	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
				$(".prompt").html("请输入正确手机号");
        		return;
			}	
			if ($.trim($("#J_yzm").val()) == ""){
				$(".prompt").html("请输入正确验证码");
        		return;
			};
			$(".prompt").html(" ");
			//优惠劵提交领取
			var ctx = $("#ctx").val();
			var verifycode = $("#J_yzm").val();
			var phoneNum = $("#J_mobileNum").val();
			var couponCode = $(this).parent().parent().attr('data-id');
			$.post(ctx + "/goddes/check",{"phoneNum" : phoneNum, "verifycode":verifycode, "couponType": 8,
				"couponCode":couponCode},function(data){
				if(data.msg=="手机验证码输入错误！"){
					$(".prompt").html(data.msg);
					return;
				}
				isLogin =1;
				if ("0" == data.code) {
					$('.modal-hd').css('marginBottom','0');
					$('.login-box').fadeOut();
					$('#overlay').addClass('active');
					$('.modal').css({"display": "block"});
					$('.modal').animate(100,function(){
						$('.modal').addClass('modal-in');
					});
				}else if(data.code == "1"){
					$('#overlay').addClass('active');
					 $('.modal').css({"display": "block"});
					 $('.login-box').fadeOut();
				     $('.modal').animate(100,function(){
				      $('.modal').addClass('modal-in');
				     });
					 $('.modal-hd').html('亲，您今天领过啦，明天再来吧').css('marginBottom','40px');
					 $('.modal-bd p').hide();
					 $('.modal-bd .click-look').html("查看优惠劵");
					return;
				}else{
					 $('#overlay').addClass('active');
					 $('.modal').css({"display": "block"});
					 $('.login-box').fadeOut();
				     $('.modal').animate(100,function(){
				      $('.modal').addClass('modal-in');
				     });
					 $('.modal-hd').html('优惠劵领取失败').css('marginBottom','40px');
					 $('.modal-bd').hide();
					return;
				}
			},"json");
			 
		});	
		
		
		
		//验证码倒计时
		var isclick = true;
		$("#passwordGetCode").on("click",function(){
			var mre = /^1\d{10}$/;
			if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
				$(".prompt").html("请输入正确手机号");
				return;
			}else{
				$(".prompt").html(" ");
				
			}
			
			if(!isclick) return false;
			//请求发送优惠码
			var ctx = $("#ctx").val();
			var phoneNum = $("#J_mobileNum").val();
			$.post(ctx + "/goddes/sendPhoneCode",{"phoneNum" : phoneNum},function(data){
				if(data.code == "1"){
					$(".prompt").html("手机验证码下发失败");
				}
			},"json");
			var that = $(this),timeId;
			var num = 60;
			var thiscon = $(this).attr("data-waiting");
			that.text(num+thiscon)
			$("#passwordGetCode").addClass('btn_gradient_gray');
			isclick = false;
			timeId = setInterval(function(){
				num--;
				that.text(num+thiscon)
				if(num == 0){
					clearInterval(timeId);
					that.text("获取验证码");
					$("#passwordGetCode").removeClass('btn_gradient_gray');
					$("#passwordGetCode").html("重新获取验证码");
					$("#passwordGetCode").addClass('recode');
					isclick = true;					
				}				
			},1000);
			
		});
	
		$('.close-btn').click(function(e){
			e.stopPropagation();
			$('.login-box').fadeOut();
		});
		
});

function checkLogin (){
	$.ajax({
		type: "GET",
		url: getRootPath()+"/checkIsLogin?"+Math.random(),
		async: false,
		success: function(data){
			//已登录
			if(data == "1"){
				isLogin=true;
				loginStatus = true;
			}
		}
	});
}