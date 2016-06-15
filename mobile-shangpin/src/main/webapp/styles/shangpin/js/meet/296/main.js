$(document).ready(function(e) {
	
	$(window).scroll(topFixed);//滑动头部导航浮层
	
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
		$ele.removeClass('modal-in');
		$ele.one('webkitTransitionEnd',function(){
		  $ele.css({"display": "none"});
		  $overlay.removeClass('active');
		});
	}
	$('.coupon-btn li').click(function(e){
		e.preventDefault();
		var $that = $(this);
		var coupon="meet_296";
		// 获取数据
		$.ajax({
			type : "GET",
			url : getRootPath() + "/meet/coupon/activation",
			data : {
				'coupon' : coupon
			},
			success : function(data) {
				if (data == undefined) {
					alert("领取失败！");
					return;
				} else if (data.code == '2') {
					location.href = getRootPath() + "/meet/redirect/app?id=296";
					return;
				} else if (data.code == "0") {
					$overlay.addClass('active');
					$('.modal').css({
						"display" : "block"
					});
					$('.modal').animate(100, function() {
						$('.modal').addClass('modal-in');
					});
				}else{
					$overlay.addClass('active');
					$('.modal').css({
						"display" : "block"
					});
					$(".modal-hd").html("不能重复领取或现金券已发完！");
					$('.modal').animate(100, function() {
						$('.modal').addClass('modal-in');
					});
				}
			}
		});
	});
	$('.btn-modal').click(function(e){
	  modalHidden($('.modal'));
	  e.stopPropagation();
	});
		
	$overlay.click(function(e){
	  if(e.target.classList.contains('overlay')){
		modalHidden($('.modal'));
	  }
	});
	
	
});	