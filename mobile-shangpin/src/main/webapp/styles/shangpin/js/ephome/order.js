$(function(){
	
	//选择支付方式
	$(".paymet_block fieldset p.payment-method span a").click(function(){
		
		var that = $(this),
			obj = $(".paymet_block fieldset p.payment-method span a");
		
		obj.removeClass("cur");	
		that.addClass("cur");
		
	});
	$('.other-payment').click(function(){
		$('.other-payment').hide();
		$('.other-payment-box').show();	
	});
	/*//选择礼品卡支付
	$("p.giftCard a").click(function(){
		$(this).toggleClass("cur");
		return false;
	});
	
	//选择收货时间
	$("#select_time").click(function(){
		$(".select-overlay").height($(document).height());
		$(".select-overlay, #rece_time_layer").show();
		addrTxt = "";
		return false;
	});
	
	$("#rece_time_layer dd  a").click(function(){
		
		var that = $(this),
			obj = $("#rece_time_layer dd  a"),
			timeTxt = that.text();
		
		obj.removeClass("cur");	
		that.addClass("cur");
		
		setTimeout(function(){
			
			$(".select-overlay, #rece_time_layer").hide();
			$(".delivery_mode").text(timeTxt);
			
		}, 300);
		return false;
		
	});
	
	
	//选择配送方式
	$("#dis_modes").click(function(){
		$(".select-overlay").height($(document).height());
		$(".select-overlay, #dis_modes_layer").show();
		addrTxt = "";
		return false;
	});
	
	$("#dis_modes_layer dd  a").click(function(){
		
		var that = $(this),
			obj = $("#dis_modes_layer dd  a"),
			timeTxt = that.text();
		
		obj.removeClass("cur");	
		that.addClass("cur");
		
		setTimeout(function(){
			
			$(".select-overlay, #dis_modes_layer").hide();
			$("#dis_modes").text(timeTxt);
			
		}, 300);
		return false;
		
	});
	
	
	$('#yes').click(function(){
		$('.invoice').show();
		$('#no').removeClass("cur");	
		$(this).addClass("cur");
	});
	
	$('#no').click(function(){
		$('.invoice').hide();
		$('#yes').removeClass("cur");	
		$(this).addClass("cur");
	});
	
	
	
	//收货地址的弹层关闭
	$(".close_btn, .select-overlay").click(function(){
		$("#rece_time_layer,.select-overlay,#dis_modes_layer").hide();
	});
	*/
	
	/*//身份证弹框
	$(".payment_btn").click(function(e){
		e.preventDefault();
		$('#overlay').show();
		$('#popup_box').show();

	});
	*/
/*	$("#popup_cancel,.title_closeBtn").click(function(e){
		e.preventDefault();
		$('#overlay').hide();
		$('#popup_box').hide();

	});
	$("#popup_ok").click(function(e){
		e.preventDefault();
		$('#overlay').hide();
		$('#popup_box').hide();

	});
	
	
	//tab切换
	$(".tab_info").find("li").click(function(e){
		e.preventDefault();
		var $this = $(this);
		var $thisIndex = $this.index();
		$(this).addClass("curr").siblings().removeClass("curr");

		$(".content_info").find(".content_list").eq($thisIndex).show().siblings().hide();
	});
	
	

	$('.cancel-order-btn').click(function(e){
		e.stopImmediatePropagation();
		var $that = $(this);
		$('.overlay').addClass('active');
		$('.modal').animate({"display":"block"},100,function(){
		  $('.modal').addClass('modal-in');
		});		
	}); 
	
	$('.btn-modal').click(function(e){
	  modalHidden($('.modal'));
	  e.stopPropagation();
	});
	
	$('.overlay').click(function(e){
	 if(e.target.classList.contains('overlay')){
		modalHidden($('.modal'));
	  }
	});
	
	function modalHidden($ele) {
	  $ele.removeClass('modal-in');
	  $ele.one('webkitTransitionEnd',function(){
		$ele.css({"display": "none"});
		$('.overlay').removeClass('active');
	  });
	}*/

	
	
	
});