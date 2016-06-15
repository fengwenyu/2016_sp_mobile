$(document).ready(function(e) {
	var couponType = 8;//8一天一次，9多次，30一次
	var coupon="meet_403";
	$(window).scroll(topFixed);//滑动头部导航浮层
	var activeCode = $('#activeCode').val();
	if(activeCode!=undefined&&activeCode.indexOf(",")>-1){
		activeCode = activeCode.split(",");
	}else{
		activeCode = [activeCode];
	}
	var code;
	//显示导航浮层方法
	function topFixed(){	
				var scrolls = document.body.scrollTop;
				var menuTopHeight;
				if($('.topFix').length>0){
					menuTopHeight = $('.topFix').offset().top;
				}
				if (scrolls > menuTopHeight) {
					$('.topFix section').css({
						position: "fixed",
						top:"0",
						zIndex:"999"
					});
				}else {
					$('.topFix section').css({
						position: "relative",
						top:"0",
						zIndex:"10"
					});
					   
				}
		};
		
		$(window).scroll(function(){
			topFixed();
			bottomFixed();
			positionNav();
		});
		
		/*底部悬浮导航*/
		var swiperFn, isSwiper = true, curr = 0, tCurr = 0,flag=0;
		function swiperFn(){
			swiper = new Swiper('#swiper-container', {
				/*loop:true,
				autoplay: 2000,*/
				slidesPerView: 5,
				spaceBetween: 5,
			});
			/*主会场底部滚动条*/			
		
			//判断左右滑动
			$("#swiper-container .swiper-slide").click(function(){
					flag = 1;
					$(this).find("a").addClass('cur').parent().siblings().find("a").removeClass('cur');
					
					curr = $(this).index();
					tCurr = $("#swiper-container .swiper-slide-active").index();
					
					if(curr>tCurr ){
						swiper.slideNext();
					}else{
						swiper.slidePrev();
					}
					
					//锚点点击滑动页面事件
					/*if(flag == 1){
						var that = $(this).find("a"),
							val = that.attr("href").substr(1);
							pageTop = $("#"+val).offset().top;
							console.log(pageTop);
						$("html,body").animate({"scrollTop":pageTop},300);
					}
					
					return false;
					*/
			});
		}
		
		function bottomFixed(){
			
			if($("a[class][id]").length > 0 && $(".foot-nav").length > 0){	
				var scrolls = document.body.scrollTop;			
				var menuBottomHeight = $("a[class][id]").eq(0).offset().top;
				if (scrolls > menuBottomHeight) {
					$(".foot-nav").fadeIn();
				}else {
					$(".foot-nav").fadeOut();					   
				}
			}
			
			if($("a[class][id]").length > 0 && $("#swiper-container").length > 0){	
				var scrolls = document.body.scrollTop;			
				var menuBottomHeight = $("a[class][id]").eq(0).offset().top-1;
				if (scrolls >= menuBottomHeight) {
					$("#swiper-container").fadeIn();
					if(isSwiper){
						swiperFn();
						isSwiper = false;
					}
				}else {
					$("#swiper-container").fadeOut();					   
				}
			}	
		}
		/*底部悬浮导航end*/
		
		/*底部导航随滚动位置改变class*/
		var n = null;
		function positionNav(){
			var nav_arr = [] , scrollsTop = document.body.scrollTop;
			$(".conbox a[class][id]").each(function(index,ele){
				nav_arr.push($(this).offset().top)
			});	
			var nav_len = nav_arr.length, myIndex;
			
			for(var i=0 ; i<nav_len; i++){
				if(scrollsTop > nav_arr[i]){
					var myIndex = i;
					//$("#swiper-container .swiper-slide").eq(myIndex).trigger("click");						
				}
			}
			
			if(myIndex != n){
				if(flag == 0){
					goNav(myIndex);
				}
			}
			
			function goNav(m){					
				$("#swiper-container .swiper-slide").eq(m).trigger("click");
				n = m;		
			};
			flag = 0;
			
			
		}
		/*底部导航随滚动位置改变class--end*/
		
		 
		/*优惠劵弹层*/
		var $overlay = $('#overlay'),touch = 1;
		function modalHidden($ele) {
			$("body").removeAttr("style");
			touch = 1;			
			$ele.removeClass('modal-in');
			$ele.one('webkitTransitionEnd',function(){
			  $ele.css({"display": "none"});
			  $overlay.removeClass('active');
			});
		}
		$('.coupon-btn').click(function(e){
			touch = 0;
			$("body").attr("style","overflow:hidden");
			$("body").bind("touchmove",function(e){
				if(touch==0){
					e.preventDefault();
				}
			})
			e.preventDefault();			
			var $that = $(this);
			code = activeCode[0];
						// 获取数据
						$.ajax({
							type : "GET",
							url : getRootPath() + "/acivity/check/user",
							data : {
								'mettId' : coupon,
								'couponType' : couponType,
								'code':code
							},
							success : function(data,textStatus, XMLHttpRequest) {
								var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
								if(sessionstatus=="timeout"){
									touch = 0;
									$("body").attr("style","overflow:hidden");
									$overlay.addClass('active');
									$('.modal').css({"display": "block"});
									$('.modal').animate(100,function(){			  
									  $('.modal').addClass('modal-in');
									});
									return;
								}
								var couponShowHtml = '';
								if (data == undefined) {
									couponShowHtml = '<h3></h3><p>领礼包的人太多了，忙不过来了一会在领！</p><div class="step-btn"><a href="#" class="close_btn">确认</a></div>';
								} else if (data.code == '2') {//代表未登陆，需要弹出手机和验证码输入项
									touch = 0;
									$("body").attr("style","overflow:hidden");
									$overlay.addClass('active');
									$('.modal').css({"display": "block"});
									$('.modal').animate(100,function(){			  
									  $('.modal').addClass('modal-in');
									});
									return;
								} else if (data.code == "0") {//代表已登录，并且已经领取成功
									couponShowHtml = '<h3>领取成功</h3><p>礼券包已放入<em class="user_number">'+$("#J_mobileNum").val()+'账户,使用该账户登录APP，礼券包购物享优惠！</em></p><p></p><div class="step-btn"><a href="#" class="close_btn">确认</a></div>';
								}else{
									couponShowHtml = '<h3></h3><p>该手机号已领取过，</p><p>登录APP后在“个人中心-优惠券”中查看</p><div class="step-btn"><a href="#" class="close_btn">确认</a></div>';
								}
								var $sw = $("#sucess_window");
							    function modalHidden2($ele) {
							    	 $("body").removeAttr("style");
									  touch = 1;
									  $ele.removeClass('couponShow-in');
									  setTimeout(function(){
										  $ele.css({"display": "none"});
										$sw.removeClass('active');
									  },300)
								}
								
								setTimeout(function(){
									touch = 0;
									$("body").attr("style","overflow:hidden");
									$sw.addClass('active');
									$('.couponShow').html(couponShowHtml);
									$('.couponShow').css({"display": "block"});
									$('.couponShow').animate(100,function(){			  
										$('.couponShow').addClass('couponShow-in');
									});
									$(".close_btn").click(function(e){
											modalHidden2($(".couponShow"));
											e.stopPropagation();
									});
									$sw.click(function(e){					
									  if(e.target.classList.contains('sucess_window')){
											modalHidden2($(".couponShow"));
									  }
									});
								},300)
									
								return;		
							}
						});
		});		
		$('.close').click(function(e){
		  modalHidden($('.modal'));
		  e.stopPropagation();
		});			
		$overlay.click(function(e){
		  if(e.target.classList.contains('overlay')){
			modalHidden($('.modal'));
		  }
		});
		
		//验证码倒计时
		var isclick = true;
		var mre = /^1\d{10}$/;
		$("#passwordGetCode").on("click",function(){
			if(!isclick) return false;
			$(".prompt").html("");
			//手机号码验证
        	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
				$(".prompt").html("请输入正确手机号");
        		return;
			}
			$(".prompt").html("");	
			var that = $(this),timeId;
			var num = 90;
			var thiscon = $(this).attr("data-waiting");
			that.text(num+thiscon)
			isclick = false;
			obtainCode();
			timeId = setInterval(function(){
				num--;
				that.text(num+thiscon)
				if(num == 0){
					clearInterval(timeId);
					that.text("获取验证码");
					isclick = true;					
				}				
			},1000);
			
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
		$(".sure-btn").click(function(e){			
		    e.preventDefault();
			//手机号码验证
        	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
				$(".prompt").html("请输入正确手机号");
        		return;
			}	
			if ($.trim($("#J_yzm").val()) == ""){
				$(".prompt").html("请输入正确验证码");
        		return;
			}
			$.ajax({
				type: "GET",
				url: getRootPath()+"/acivity/check",
				data : {
					'phoneNum' : $.trim($("#J_mobileNum").val()),
					'verifyCode' : $.trim($("#J_yzm").val()),
					'mettId':coupon,
					'couponType':couponType,
					'code':code
				},
				success: function(data){
					if(data.code!=undefined&&data.code==3){
						$(".prompt").html("请输入正确验证码");
		        		return;
					}
					modalHidden($('.modal'));
					var couponShowHtml = '';
					if(data.code==0){
						//领取成功处理				
						couponShowHtml = '<h3>领取成功</h3><p>礼券包已放入<em class="user_number">'+$("#J_mobileNum").val()+'账户,使用该账户登录APP，礼券包购物享优惠！</em></p><p></p><div class="step-btn"><a href="#" class="close_btn">确认</a></div>';
						
					}else if(data.code==2){
						couponShowHtml = '<h3></h3><p>该手机号已领取过，</p><p>登录APP后在“个人中心-优惠券”中查看</p><div class="step-btn"><a href="#" class="close_btn">确认</a></div>';
					}else{
						couponShowHtml = '<h3></h3><p>该手机号已领取过，</p><p>登录APP后在“个人中心-优惠券”中查看</p><div class="step-btn"><a href="#" class="close_btn">确认</a></div>';
					}
					
					var $sw = $("#sucess_window");
				    function modalHidden2($ele) {			
				    	 $("body").removeAttr("style");
						  touch = 1;
						  $ele.removeClass('couponShow-in');
						  setTimeout(function(){
							  $ele.css({"display": "none"});
							$sw.removeClass('active');
						  },300)
					}
					
					setTimeout(function(){
						touch = 0;
						$("body").attr("style","overflow:hidden");
						$sw.addClass('active');
						$('.couponShow').html(couponShowHtml);
						$('.couponShow').css({"display": "block"});
						$('.couponShow').animate(100,function(){			  
							$('.couponShow').addClass('couponShow-in');
						});
						$(".close_btn").click(function(e){
								modalHidden2($(".couponShow"));
								return false;
						});
						$sw.click(function(e){					
						  if(e.target.classList.contains('sucess_window')){
								modalHidden2($(".couponShow"));
						  }
						});
					},300)
						
					return;		
				}
			});
			return;		
		});	
		//手机验证

});	

function obtainCode(){
	$.ajax({
		type: "GET",
		url: getRootPath()+"/acivity/sendPhoneCode?phoneNum="+$.trim($("#J_mobileNum").val()),
		success: function(data){
			if(data.code!=undefined&&data.code==1){
				$(".prompt").html("获取验证码失败");
        		return;
			}
		}
	});
	
	
}