$(function(){

	/*$('#popup_overlay').fade();
	setTimeout(function(){
		$('#popup_overlay').hide();
	},16000);
	if($('#swiper_item').length){
	  var swiper = new Swiper('#swiper_item', {
		slidesPerView: 'auto',
		spaceBetween: 4,
	  });
	}
	*/
	function MobileErrorTip() {
		$(".prompt").css({height:'20px',marginTop:'10px'});
		$(".prompt").html("请输入正确手机号");
		$("#J_yzm").removeClass('error');
		//$("#J_graphicsCode").removeClass('error');
		$("#J_mobileNum").addClass('error');
	};
	
	function yzmErrorTip() {
	  $(".prompt").html("验证码输入不正确");
	 $(".prompt").css({height:'20px',marginTop:'10px'});
	  $("#J_mobileNum").removeClass('error');
	  //$("#J_graphicsCode").removeClass('error');
	  $("#J_yzm").addClass('error');
	 
	};
	
	/*function graphicsCodeErrorTip() {
	  $(".prompt").html("请输入右侧的数字");
	  $(".prompt").css({height:'20px',marginTop:'10px'});
	  $("#J_mobileNum").removeClass('error');
	  $("#J_yzm").removeClass('error');
	  $("#J_graphicsCode").addClass('error');
	};*/
	
	function removeErrorTip(){
	  $(".prompt").html("");
	  $(".prompt").css({height:'0',marginTop:'0'});
	  $("#J_mobileNum").removeClass('error');
	  $("#J_yzm").removeClass('error');
	  //$("#J_graphicsCode").removeClass('error');
	}
	//点击领取按钮时验证输入内容是否正确
	var isMobile=/^1\d{10}$/; //手机号码验证规则

	removeErrorTip();

	$(".js-start-btn").click(function(){
		if ($.trim($("#J_mobileNum").val()) == "" || !isMobile.test($("#J_mobileNum").val())){
			MobileErrorTip();
			return;
		};	
		if ($.trim($("#J_yzm").val()) == ""){
			yzmErrorTip();
			return;
		};


		var path = getRootPath();
		var phoneNum = $("#J_mobileNum").val();
		var verifyCode = $("#J_yzm").val();

		$.get(path + "/weixin/toCheck?imstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum + "&verifyCode=" + verifyCode ,function(data){
			if(data.code == "4"){//短信验证码输入错误
				yzmErrorTip();
				return;
			}else if(data.code == "0"){
				window.location.href = path + "/weixin/toSuccess";
				return;
			}else if(data.code == "1"){//短信验证码输入错误
				alert(data.msg);
				return;
			}else{
				alert("抢红包人太多了，你没抢到，赶快再去抢！");
				return;
			}
		});


		return;		
	});	
	
	$("body").on("click",".select-overlay",function(e){
		if(e.target.classList.contains('select-overlay')){
			close_window_tip();
		}		
	})
	
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

	//获取验证码
	var flag = 0;
	var isS = 0;//true; //是否领过
	$("#passwordGetCode").click(function() {

		if ($.trim($("#J_mobileNum").val()) == "" || !isMobile.test($("#J_mobileNum").val())){
			MobileErrorTip();
			return;
		}else{
			removeErrorTip();
		}

		if (flag != 0) {
			return false;
		}

		var path = getRootPath();
		var phoneNum = $("#J_mobileNum").val();
		flag = 1;
		var delay = 90, trigger = this, revert = $(trigger).text();

		$("#passwordGetCode").addClass('btn_gradient_gray');
		$(trigger).attr({'disabled': true }).text(delay + $(trigger).attr("data-waiting")).parents("label").addClass("waiting");
		var counter = setInterval(function() {
			$(trigger).text($(trigger).text().replace(delay, --delay));
			if(delay == 0) {
				flag = 0;
				window.clearInterval(counter);
				$(trigger).text(revert).removeAttr("disabled").parents("label").removeClass("waiting");
				$("#passwordGetCode").removeClass('btn_gradient_gray');
				$("#passwordGetCode").html("重新获取");
				$("#passwordGetCode").addClass('recode');
			}
		}, 1000);


		$.get(path + "/weixin/isSendInfo?timstamp=" + new Date().getTime()	+ "&phoneNum=" + phoneNum, function(data) {
			if (data.code == "1" || data.code == "5") {
				alert(data.msg);
			} else if (data.code == "3") {
				popBox();
			}
		});
	});

	
	//领过弹出层
	function popBox(){
		$('#popup_message').html("已经领取");
		//$('#popup_overlay').show();
		$('#popup_container').show();
		setTimeout(function(){
			//$('#popup_overlay').hide();
			$('#popup_container').hide();
			var path = getRootPath();
			window.location.href = path + "/weixin/toSuccess";
		},2000);
	}
	
	//新增点击分享图层
	/*$("#share_btn").click(function(){
		$('#popup_overlay').show();
		return false;
	});*/
	$("#popup_overlay").click(function(){
		$('#popup_overlay').hide();
		return false;
	});
	
	/*视频js*/
	$(".show_video").on("click",function(){
		$(this).hide();
		showVideo()
		return false;
	});
	
	/*$(".mask").on("click",".close_btn",function(){
		closeVideo();
		return false;	
	})*/
	
	function showVideo(val,tvid){
					
		var vId = val,
			//tvId = tvid,
			objStr = null;
			/*src = 'http://static.youku.com/v1.0.0220/v/swf/player.swf?VideoIDS=' + vId + '&winType=adshow&isAutoPlay=false',*/
			
			/*objstr = '<iframe height=182 width=300 src="http://open.iqiyi.com/developer/player_js/coopPlayerIndex.html?vid=' + val + '&tvId=' + tvId + '&accessToken=2.f22860a2479ad60d8da7697274de9346&appKey=3955c3425820435e86d0f4cdfe56f5e7&appId=1368&height=100%&width=100%" frameborder=0 allowfullscreen></iframe>';
			
			objstr = '<iframe height=182 width=300 src="http://v.qq.com/iframe/player.html?vid=' + val + '&tiny=0&auto=0" frameborder=0 allowfullscreen></iframe>';*/

		$(".video_box").show();		
		//$(".mask,.video_box").show();			
	}
	function closeVideo(){
		$(".mask,.video_box").hide();
		$(".video_box").html("");
		touch = 1;	
	}
	
	//领取成功弹出层公共方法
	/*function show_window_tip(){
		touch = 0;
		$("body").attr("style","overflow:hidden");
		$("body").bind("touchmove",function(e){
			if(touch==0){ e.preventDefault();}
		})
		$(".select-overlay").addClass("active");
	}
	function close_window_tip(){
		$("body").removeAttr("style");
		touch = 1;	
		$(".select-overlay").removeClass("active");
	}*/

	
	
});