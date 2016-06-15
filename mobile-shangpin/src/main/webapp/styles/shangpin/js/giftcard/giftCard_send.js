$(function(){
	
	//显示导航浮层方法
	/*$(window).scroll(topFixed);

	var menuTopHeight = $('.topFix').offset().top;
	function topFixed(){	
		var scrolls = document.body.scrollTop;
		if (scrolls > menuTopHeight) {
			$('.topFix').css({
				position: "fixed"
			});
			$('body .container').css('margin-top',45);    
		
		}else {
			$('body .container').css('margin-top',0); 
			$('.topFix').css({
				position: "static"
			});
			   
		}
	};*/
	var path = getRootPath();
	//文本输入框文字控制
	$(".sendCard_form textarea,.mobile_number").click(function(){
		var $this = $(this),
			value = $this.attr("data-value");
		if($this.val() == value){
			$this.val("").css("color","#333");
		}
		$this.blur(function(){
			if($this.val() == ""){
				$this.val(value).css("color","#d8d8d8");
			}
		});
	});
	
	//选中背景图
	$(".chose_img_list").on("click","li",function(){
		var that = $(this);
		if(that.hasClass("cur")){
			that.removeClass("cur");	
		}else{
			that.addClass("cur").siblings().removeClass("cur");	
		}	
	});
	
	//赠送给小伙伴按钮验证
	var mre = /^1\d{10}$/;
	var $textarea = $(".sendCard_form").find("textarea"),
		textareaVal = $textarea.val();
	var $mbNumber = $("#mbNumber"),
		mbNumberVal = $mbNumber.val();
		
	$("#submit_send").click(function(){
		var $imgCur = $(".chose_img_list").find(".cur");
		if($textarea.val() == textareaVal || $.trim($textarea.val()) == ""){
			show_window_tip("请给您的小伙伴写点祝福信息吧");
			return false;
		}
		if(!$imgCur.length){
			show_window_tip("请选择一张祝福信息的背景图");
			return false;	
		}
		if($mbNumber.val() == mbNumberVal || $.trim($mbNumber.val()) == "" /*|| !mre.test($mbNumber.val())*/){
			show_window_tip("请填写您的小伙伴的手机号码");
			return false;	
		}
		if(!mre.test($mbNumber.val())){
			show_window_tip("请填写正确的手机号码");
			return false;
		}
		var wishPic = $('.cur img').attr('src');
		var _isapp = $("#_isapp").val();
		var _iswx = $("#_iswx").val();
		//调用服务生成链接
		$.ajax({
			type:"get",
			url: path+"/giftCard/sendGiftCard",
            data: {"giftCardId":$('#giftCardId').val(),
            		"giftOrder":$('#giftOrder').val(),
            		"sendPhoneNum":$mbNumber.val(),
            		"wishMsg":$textarea.val(),
            		"wishPic":wishPic,
            		"faceValue":$('#faceValue').val(),
            		"requestHead":$('#requestHead').val(),
            		"userid":$('#userid').val()
            },
            dataType: "json",
            async:false,
            success: function(data){
            	var code = data.code;
            	var msg = data.msg;
            	if("0"!=code){
            		show_window_tip(msg);
            		return;
            	}
            	var user = data.content;
            	var msg2 = user.faceValue;
            	//alert("shangpinapp://phone.shangpin/actiongoshare?title=您的小伙伴"+user.buyerName+"给您送祝福来了！&desc="+user.wishMsg+"&url="+user.sendTime+"/giftCard/skipToVerifyCode?"+msg);
            	 if(_isapp){
            		//如果是app
            		//window.location.href="shangpinapp://phone.shangpin/actiongoshare?title=您的小伙伴"+user.buyerName+"给您送祝福来了！&desc="+user.wishMsg+"&url=http://192.168.20.77"+path+/*user.sendTime*/"/giftCard/skipToVerifyCode?"+msg;
            		window.location.href="shangpinapp://phone.shangpin/actiongoshare?title=您的小伙伴"+user.buyerName+"给您送祝福来了！&desc="+user.wishMsg+"&url="+user.sendTime+"/giftCard/skipToVerifyCode?"+msg;
            		//window.location.href="shangpinapp://phone.shangpin/actiongoshare?title=您的小伙伴"+user.buyerName+"给您送祝福来了！&desc="+user.wishMsg+"&url=http://pre.shangpin.com/giftCard/skipToVerifyCode?"+msg;
            	 }else if(_iswx){
         			//微信分享js
         			$("#_mainDesc").val(user.wishMsg);
         			$("#_mainTitle").val("您的小伙伴"+user.buyerName+"给您送祝福来了！");
         			$("#_shareUrl").val("http://m.shangpin.com/giftCard/skipToVerifyCode?"+msg2);
         			sendToFriend();
         			//弹层
         			$('.js-share-pop').show();	
             	}
            }
		});

	});
	
	//祝福语预览页面js
	if($(".giftCard_preview_box").length> 0){
		
		var sildeTop = 0,startY = 0,endY = 0,isscroll = false;
		
		$(".giftCard_preview_box").height($(window).height());
		var mySwiper = new Swiper ('#swiper-container1', {
			direction : 'vertical',
			mousewheelControl : false,
			preloadImages: false,
			noSwiping : true //使swiper-no-swiping该类slide无法拖动
		});	
		
		//获取滚动盒子的当前top值
		$(".page_box2").scroll(function(){
			sildeTop = $(this).scrollTop();
		});
		
		//touchmove方法
		function upScrollMove(e){
			endY = parseInt(e.targetTouches[0].clientY);
			var y = endY - startY;
			if(sildeTop > 50){isscroll = false;}
			if(sildeTop == 0){			
				if(isscroll && y > 10){				
					mySwiper.slidePrev();
				}else{					
					sildePage2.removeEventListener("touchmove",upScrollMove,false);
					isscroll = true;	
				}			
				return false;		
			}	
		}
		
		var sildePage2 = document.getElementById("page_box2");
		sildePage2.addEventListener("touchstart",function(e){
			startY = parseInt(e.targetTouches[0].clientY);
			sildePage2.addEventListener("touchmove",upScrollMove,false);
		},false);	
	}
	
	/************验证口令*****************/
	//获取验证码
	var isClick = true,code = /^\d{6}$/;
	$("#get_code").click(function(){
		$(".text_tip").css({height:'0'});//隐藏掉对话提示框
		if(!isClick){ return false;}
		isClick = false;
		//后台发送验证码
		$.ajax({
			type:'get',
			url: path+'/giftCard/sendVerifyCodeToFriend',
			dataType:'json',
			success:function(data){
				var code = data.code;
				var msg = data.msg;
				if('0'!=code){
					$("#code_tip").html(msg);
				}
			}
		});
		var that = $(this),num = 90;
		$(".giftCard_verify #code_tip").slideDown();
		
		that.addClass("no_select");
		that.html(num + that.attr("data-waiting"));
		var timeId = setInterval(function(){			
			that.html(--num + that.attr("data-waiting"));
			if(num == 0){
				clearInterval(timeId);
				that.html("发送验证码").removeClass("no_select");
				isClick = true;
				return false;
			}	
		},1000);	
		
	});
	//验证码验证
	$("#verify_btn").click(function(){
		if( $.trim($("#J_yzm").val()) == ""){
			show_window_tip("请输入短信验证码");
			return false;
		}
		if(!code.test($("#J_yzm").val())){//验证码是否正确
			$(".text_tip").css({height:'20px'});
			return false;
		}
		/*alert($("#J_yzm").val()+"=>"+$("#giftCard").val())*/
		//后台校验验证码
	/*	$.get(path + "/giftCard/checkVerifyPassword?timstamp=" + new Date().getTime()+"&smscode="+$("#J_yzm").val(),function(data){
			var code = data.code;
			var msg = data.msg;
			if('0'==code){
				$(".text_tip").css({height:'0'});
				window.location.href=path+"/giftCard/giftCardPreview";
			}else{
				$(".text_tip").html(msg);
				$(".text_tip").css({height:'20px'});
				return false;
			}
		});*/
		
		
		$.ajax({
			type:'get',
			url:path+'/giftCard/checkVerifyPassword?timstamp=' + new Date().getTime(),
			data: {"smscode":$("#J_yzm").val()},
			dataType:'json',
			success:function(data){
				var code = data.code;
				var msg = data.msg;
				if('0'==code){
					$(".text_tip").css({height:'0'});
					window.location.href=path+"/giftCard/giftCardPreview";
				}else{
					$(".text_tip").html(msg);
					$(".text_tip").css({height:'20px'});
					return false;
				}
			}
		});
		//$(".text_tip").css({height:'0'});
		//window.location.href=path+"/giftCard/giftCardPreview";
	});
	
	/**************一键登*******************/
	$("#quick_login").click(function(){
		//后台校验验证码
		
		$.get(path + "/giftCard/checkUser?timstamp=" + new Date().getTime(),function(data){
			var code = data.code;
			var msg = data.msg;
			if('00'==code){
				show_window_tip("尚品网将为您的手机号码"+msg+"注册为尚品网会员，账户名为"+msg+"，密码为手机号码后六位","注册提醒");
				return false;
			}
			if('01'==code){
				show_window_tip("您的手机号码"+msg+"已经是尚品网会员，账户名为您的手机号码。","注册提醒");
				return false;
			}else{
				show_window_tip(msg);
				return false;
			}
		});
		
		
		return false;
	});
	
	//弹出层公共方法
	function show_window_tip(t,h){
		touch = 0;
		$("body").attr("style","overflow:hidden");
		$("body").bind("touchmove",function(e){
			if(touch==0){ e.preventDefault();}
		})
		if(!$(".select-overlay").length){
			var showWindwHtml = '<div class="select-overlay"><div class="show_window_con">';
				showWindwHtml += '<h3 class="title"></h3>';
				showWindwHtml += '<div class="text_con"></div>';
				showWindwHtml += '<p class="close_window">确定</p></div></div>';
		}
		$("body").append(showWindwHtml);
		if(!h){
			$(".select-overlay").find(".title").hide();	
		}else{
			$(".select-overlay").find(".title").show().html(h);
		}
		$(".select-overlay").find(".text_con").html(t);
		$(".select-overlay").addClass("active");
		$("body").on("touchend click",".close_window",function(){
			show_window_down();	
			return false;
		});
	}
	function show_window_down(){
		$("body").removeAttr("style");
		touch = 1;	
		$(".select-overlay").removeClass("active");
	}
	/*$("body").on("touchend click",function(e){
		if(e.target.classList.contains('select-overlay')){
			//show_window_down();
			return false;
		}
	});*/
	
	$("#normal_login").click(function(){
		window.location.href= path+"/login?back="+"/giftCard/oneKeyRecharge";
	});
	
	$("#oneKeySure").on("touchend",function(){
		window.location.href= path+"/giftCard/oneKeyLogin";
	});
	
	$('.js-share-pop').click(function(){
		$('.js-share-pop').hide();	
	});
	
	function sendToFriend(){
		var appId;
		var timestamp;
		var nonceStr;
		var signature;
		var url = window.location.href;
		if(url.indexOf("#") > 0){
			url = window.location.href.split("#")[0];
		}
		var path = getRootPath();
		$.getJSON(path + "/weixin/shareUrl?shareUrl=" + encodeURIComponent(url),function(data){
			appId = data.appId;
			timestamp = data.timestamp;
			nonceStr = data.nonceStr;
			signature = data.signature;
			wx.config({
			    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			    appId: appId, // 必填，公众号的唯一标识
			    timestamp: timestamp, // 必填，生成签名的时间戳
			    nonceStr: nonceStr, // 必填，生成签名的随机串
			    signature: signature,// 必填，签名，见附录1
			    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			wx.ready(function(){
				var title = $("#_mainTitle").val();
				var desc = $("#_mainDesc").val();
				var link = $("#_shareUrl").val();
				var imgUrl = $("#_mainImgUrl").val();
				//分享给朋友
				wx.onMenuShareAppMessage({
				    title: title, // 分享标题
				    desc: desc, // 分享描述
				    link: link, // 分享链接
				    imgUrl: imgUrl, // 分享图标
				    type: 'link', // 分享类型,music、video或link，不填默认为link
				    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
				    success: function () { 
				        // 用户确认分享后执行的回调函数
				    	//alert("分享成功");
				    },
				    cancel: function () { 
				        // 用户取消分享后执行的回调函数
				    },
				    complete: function(){
				    	//接口调用完成时执行的回调函数，无论成功或失败都会执行
				    },
				    fail: function(){
				    	//接口调用失败时执行的回调函数。
				    },
				    trigger: function(){
				    	//监听Menu中的按钮点击时触发的方法，该方法仅支持Menu中的相关接口。
				    }
				});
				
				//分享到朋友圈
				wx.onMenuShareTimeline({
				    title: title, // 分享标题
				    link: link, // 分享链接
				    imgUrl: imgUrl, // 分享图标
				    success: function () { 
				        // 用户确认分享后执行的回调函数
				    	//alert("分享成功");
				    },
				    cancel: function () { 
				        // 用户取消分享后执行的回调函数
				    },
				    complete: function(){
				    	//接口调用完成时执行的回调函数，无论成功或失败都会执行
				    },
				    fail: function(){
				    	//接口调用失败时执行的回调函数。
				    },
				    trigger: function(){
				    	//监听Menu中的按钮点击时触发的方法，该方法仅支持Menu中的相关接口。
				    }
				});
			});
		});
	}

});