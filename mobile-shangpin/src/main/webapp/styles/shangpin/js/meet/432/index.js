$(document).ready(function(e) {
	if($(".swiper-container").length > 1){
		/*图片轮播*/
		var swiper = new Swiper('#swiper-container1', {
			//loop:false,
			//autoplay: 3000,
			//slidesPerView: 'auto',
			//autoplayDisableOnInteraction : false,
			slidesPerView: 'auto',
			spaceBetween: 2, 
		});
		var swiper = new Swiper('#swiper-container2', {
			//loop:false,
			//autoplay: 3000,
			//slidesPerView: 'auto',
			//autoplayDisableOnInteraction : false,
			slidesPerView: 'auto',
			spaceBetween: 2, 
		});
		var swiper = new Swiper('#swiper-container3', {
			//loop:false,
			//autoplay: 3000,
			//slidesPerView: 'auto',
			//autoplayDisableOnInteraction : false,
			slidesPerView: 'auto',
			spaceBetween: 2, 
		});
		var swiper = new Swiper('#swiper-container4', {
			//loop:false,
			//autoplay: 3000,
			//slidesPerView: 'auto',
			//autoplayDisableOnInteraction : false,
			slidesPerView: 'auto',
			spaceBetween: 2, 
		});
		/*图片轮播end*/
		$('.js-fold-btn').click(function(){
			if($(this).hasClass('fold')){
				$(this).removeClass('fold');
				$('.fold-nav-title').hide();
				$('.fold-nav-box').hide();
				$('.mask').hide();
			}else{
				$(this).addClass('fold');
				$('.fold-nav-title').show();
				$('.fold-nav-box').show();
				$('.mask').show();
			}
		});
		
		$(".fold-nav-box li").click(function(){
				$('.js-fold-btn').removeClass('fold');
				$('.fold-nav-title').hide();
				$('.fold-nav-box').hide();
				$('.mask').hide();
				$(this).find("a").addClass('cur').parent().siblings().find("a").removeClass('cur');
				var mIndex =$(this).index();
				swiper.slideTo(mIndex);
				$("#swiper-container .swiper-slide").eq(mIndex).find('a').addClass('cur').parent().siblings().find("a").removeClass('cur');
						
		});
	}
		//分会场展示层
		$('.j-sub-meet-close').click(function(){
			$('.sub-meet-box').slideUp();
			$('.mask').hide();
		});
		$('.sub-meet-btn').click(function(){
			$('.sub-meet-box').slideDown();
			$('.mask').show();
		});
		
		//分享
		$('.mui-share-sina').click(function(){
			share();
		});
		$('.share-sina-btn').click(function(){
			$('.mask').show();
			$('.mui-share').slideDown();
		});
		$('.mui-share-cancel').click(function(){
			$('.mui-share').slideUp();
			$('.mask').hide();
		});
	  	function share() {
			$('.mask').hide();
			$('.mui-share').slideUp();
			var title="尚品网轻奢购物狂欢节，全球精选欧洲直发，低于海外专柜价.",
				shareUrl="http://m.shangpin.com/meet/390",
				picUrl="http://m.shangpin.com/styles/shangpin/images/share-logo.png";
				
			window.open("http://service.weibo.com/share/share.php?title=" + encodeURIComponent(title) + "&url=" + encodeURIComponent(shareUrl) + "&pic=" + encodeURIComponent(picUrl))
		}
		
		/*优惠劵弹层*/
		var $overlay = $('#overlay');
		function modalHidden($ele) {
			$("body").removeAttr("style");
			touch = 1;		
			$ele.removeClass('modal-in');
			$ele.one('webkitTransitionEnd',function(){
			  $ele.css({"display": "none"});
			  $overlay.removeClass('active');
			});
		}
		$('.get_coupon').click(function(e){
			e.stopPropagation();
			touch = 0;
			$("body").attr("style","overflow:hidden");
			$("body").bind("touchmove",function(e){
				if(touch==0){
					e.preventDefault();
				}
			})
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
		});			
		$overlay.click(function(e){
		  if(e.target.classList.contains('overlay')){
			modalHidden($('.modal'));
		  }
		});
		
		//验证码倒计时
		var isclick = true;
		$("#passwordGetCode").on("click",function(){
			if(!isclick) return false;
			//手机号码验证
        	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
				$(".prompt").html("请输入正确手机号");
				 $(".prompt").css({height:'20px',marginTop:'5px'});
        		return;
			}
			$(".prompt").html("");	
			$(".prompt").css({height:'0',marginTop:'0'});
			var that = $(this),timeId;
			var num = 90;
			var thiscon = $(this).attr("data-waiting");
			that.text(num+thiscon)
			isclick = false;
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
		
		var mre = /^1\d{10}$/;
		//点击领取按钮时验证输入内容是否正确
		$(".sure-btn").click(function(e){			
		    e.preventDefault();
			//手机号码验证
        	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
				$(".prompt").html("请输入正确手机号");
				$(".prompt").css({height:'20px',marginTop:'5px'});
        		return;
			}	
			if ($.trim($("#J_yzm").val()) == ""){
				$(".prompt").html("请输入正确验证码");
				$(".prompt").css({height:'20px',marginTop:'5px'});
        		return;
			}
			modalHidden($('.modal'));
			
			var isS = true; //优惠券是否领取成功
			if(isS){
				var couponShowHtml = '<h3>领取成功</h3><p>优惠券已放入账户<em class="user_number">13703883490</em></p><p>密码短信已发送,登录APP即可使用</p><div class="step-btn"><a href="#" class="close_btn">确认</a></div>';
			}else{
				var couponShowHtml = '<h3>好像没领到啊</h3><p>人太多了！忙不过来了，稍等一会。</p><div class="step-btn"><a href="#" class="close_btn">确认</a></div>';	
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
			},300);
			
			return;		
		});	
		//手机验证

});	