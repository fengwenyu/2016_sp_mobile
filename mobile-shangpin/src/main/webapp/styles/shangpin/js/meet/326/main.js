$(document).ready(function(e) {
	var couponType = 30;//8一天一次，9多次，30一次
	var coupon="meet_326";
	$(window).scroll(topFixed);//滑动头部导航浮层
	
	setTimeout(function(){window.scroll(0,1);},2000);
	//显示导航浮层方法
	function topFixed(){
		var menuTopHeight;
		if($('.topFix').length>0){
			menuTopHeight = $('.topFix').offset().top;
		}	
		var scrolls = document.body.scrollTop;
		if (scrolls > menuTopHeight) {
			$('.topFix section').css({
				position: "fixed",
				top:"0",
			});
			$('#nav_fixed').css({
				position: "fixed",
				top:"44px"
			});
		}else {
			$('.topFix section').css({
				position: "relative",
				top:"0",
				overflow:'hidden'
			});
			$('#nav_fixed').css({
				position: "relative",
				top:"0"
			});
			   
		}
		/*返回顶部*/
		if(scrolls > 128){
			$(".back_box").fadeIn(300);
		}else{
			$(".back_box").fadeOut(300);
		}
	};
	
	$(".back_top").click(function(){
		$("html,body").animate({"scrollTop":0},"fast");
				return false;	
	});
	$('.share').click(function(){
		$('.share-tip').show();
	});
	$('.share-tip').click(function(){
		$('.share-tip').hide();
	})

	/*优惠劵弹层*/
	var $overlay = $('#overlay');
	function modalHidden($ele) {
		$("html,body").removeClass('onBody');
		$ele.removeClass('modal-in');
		$ele.one('webkitTransitionEnd',function(){
		  $ele.css({"display": "none"});
		  $overlay.removeClass('active');
		});
	}
	$('.coupon').click(function(e){
		$("html,body").addClass("onBody");
		e.preventDefault();
		var $that = $(this);
//		var liNum = $(this).index();
//		if(liNum==0){
//			coupon="meet_3050";
//		}else{
//			coupon="meet_3051";
//		}
					
					// 获取数据
					$.ajax({
						type : "GET",
						url : getRootPath() + "/acivity/check/user",
						data : {
							'mettId' : coupon,
							'couponType' : couponType
						},
						success : function(data,textStatus, XMLHttpRequest) {
							var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
							if(sessionstatus=="timeout"){
								$("html,body").addClass("onBody");
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
								$("html,body").addClass("onBody");
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
						    	$("html,body").removeClass('onBody');
								  $ele.removeClass('couponShow-in');
								  setTimeout(function(){
									  $ele.css({"display": "none"});
									$sw.removeClass('active');
								  },300)
							}
							
							setTimeout(function(){
								$("html,body").addClass('onBody');
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
	$("#passwordGetCode").on("click",function(){
		$(".prompt").html("");
    	var mre = /^1\d{10}$/;
		//手机号码验证
    	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			$(".prompt").html("请输入正确手机号");
    		return;
		}
		if(!isclick) return false;
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
	$(".sure-btn").click(function(){
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
		
		$.ajax({
			type: "GET",
			url: getRootPath()+"/acivity/check",
			data : {
				'phoneNum' : $.trim($("#J_mobileNum").val()),
				'verifyCode' : $.trim($("#J_yzm").val()),
				'mettId':coupon,
				'couponType':couponType
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
			    	$("html,body").removeClass('onBody');
					  $ele.removeClass('couponShow-in');
					  setTimeout(function(){
						  $ele.css({"display": "none"});
						$sw.removeClass('active');
					  },300)
				}
				
				setTimeout(function(){
					$("html,body").addClass('onBody');
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
		return;		
	});		
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