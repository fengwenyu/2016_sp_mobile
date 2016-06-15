$(function(){
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
	
	
	//四级地址选择
	$("#select_street_layer dd  a").click(function(){
		
		var that = $(this),
			obj = $("#select_street_layer dd  a"),
			timeTxt = that.text();
		
		obj.removeClass("cur");	
		that.addClass("cur");
		
		setTimeout(function(){
			
			$(".select-overlay, #select_street_layer").hide();
			//$(".delivery_mode").text(timeTxt);
			
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
	
	$('.other-payment').click(function(){
		$('.other-payment').hide();
		$('.other-payment-box').show();	
	});
	
	//收货地址的弹层关闭
	$(".close_btn, .select-overlay").click(function(){
		$("#rece_time_layer,.select-overlay,#dis_modes_layer,#select_street_layer").hide();
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
	}
	
	
	var re = /^[\u4e00-\u9fa5]$/,
			mre = /^1\d{10}$/,
			post = /^[0-9][0-9]{5}$/,
			identification = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
	//验证码倒计时
	var isclick = true;
	$("#passwordGetCode").on("click",function(){
		if(!isclick) return false;
		//收货人电话
        if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			return jShare('请输入正确手机号码',"",""),
			$("#J_mobileNum").addClass("error"),
        	!1;
		}else{
			$("#J_mobileNum").removeClass("error");
		}		
		var that = $(this),timeId;
		var num = 90;
		var thiscon = $(this).attr("data-waiting");
		that.text(num+thiscon)
		var path = getRootPath();
		var phoneNum = $.trim($("#J_mobileNum").val());
		//下发短信验证码
		$.get(path + "/common/send/phone/code?timstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum,function(data){
			if(data.code != "0"){
				alert(data.msg);
				return;
			}else{
				isclick = false;
				that.addClass("noblur");
				timeId = setInterval(function(){
					num--;
					that.text(num+thiscon)
					if(num == 0){
						clearInterval(timeId);
						that.text("获取验证码");
						isclick = true;	
						that.removeClass("noblur");				
					}				
				},1000);
			}
		});
		
	});
	
	
	//提交订单
	$(".payment-btn").click(function(){		
		var path = getRootPath();
		//收货人姓名
		if ($.trim($("#J_userName").val()) == ""){
			return jShare('请填写正确中文名称',"",""),
			$("#J_userName").addClass("error"),
        	!1;
		}else{
			$("#J_userName").removeClass("error");
		}
		
		//收货人电话
        if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			return jShare('请输入正确手机号码',"",""),
			$("#J_mobileNum").addClass("error"),
        	!1;
		}else{
			$("#J_mobileNum").removeClass("error");
		}
		
		//验证码
		if ($.trim($("#J_authCode").val()) == ""){
			return jShare('请输入正确验证码',"",""),
			$("#J_authCode").addClass("error"),
        	!1;
		}else{
			$("#J_authCode").removeClass("error");
		}
		
		//省市区
		if ($.trim($("#select_area").html()) == "" || $("#select_area").html()=="省市区"){
			return jShare('请输入省市信息',"",""),
			$("#select_area").addClass("error"),
        	!1;
		}else{
			$("#select_area").removeClass("error");
		}
		
		
		//收货人地址
		if ($.trim($("#J_addr").val()) == ""){
			return jShare('请输入详细地址',"",""),
			$("#J_addr").addClass("error"),
        	!1;
		}else{
			$("#J_addr").removeClass("error");
		}
		
		//收货人邮编
		if ($.trim($("#J_code").val()) == "" || !post.test($("#J_code").val())){
			return jShare('请输入六位邮编',"",""),
			$("#J_code").addClass("error"),
        	!1;
		}else{
			$("#J_code").removeClass("error");
		}
		//提交订单前验证手机号
		var phoneNum =$.trim($("#J_mobileNum").val());
		var orderInfo = $("#J_Login").serialize();
		var verifyCode = $.trim($("#J_authCode").val());
		var mainPay = $("#payTypeId").val();
		var subPay = $("#payTypeChildId").val();
		$.post(path + "/common/check/verify/code",{phoneNum:phoneNum, verifyCode:verifyCode},function(data){
			if(data.code == "0"){
				//提交订单
				$(".payment_btn").text("正在提交订单.......");
				$.post(path + "/order/submit",orderInfo,function(data){
					if(data.code == "0"){
						if(mainPay == "27" && subPay == "58"){
							$(".payment_btn").text("正在跳转微信支付页面.......");
							window.location.href = path + "/pay/weixin/WEIXINPUB?orderId=" + data.orderId+"&quickSubmitFlag=0";
						}else if(mainPay == "27" && subPay == "117"){
							$(".payment_btn").text("正在跳转微信支付页面.......");
							window.location.href = path + "/pay/weixin/WEIXINWAP?orderId=" + data.orderId+"&quickSubmitFlag=0";
						}
					}else{
						alert(data.msg);
					}
					return;
				},"json");
			}else{
				alert(data.msg);
			}
		},"json");
	});
	
	//选择收货地址
	//选择收货地址
	var addrTxt,thisCon,content,that,obj,title,prev,thatDl;
	$("#select_area").click(function(){
		$(".prev_btn").hide();
		$("#area_overlay").height($(document).height());
		$("#area_overlay, #area_layer").show();
		addrTxt = [];
		return false;
	});
	
	$("#area_layer").delegate("a","click",function(){
		var path = getRootPath();
		var that = $(this),		
		prev = $(".prev_btn");
		obj = $("#area_layer dd  a");
		content = $("#area_layer dl");
		thisCon = that.closest("dl");
		title = $("#area_layer h3");
		obj.removeClass("cur");
		that.addClass("cur");
		thatDl = thisCon;
		
		var dl_id = thisCon.attr("id");
		if(dl_id == "area_province"){
			$("#province").val(that.attr("id"));
			$("#provinceName").val(that.text());
		}else if(dl_id == "area_city"){
			$("#city").val(that.attr("id"));
			$("#cityName").val(that.text());
		}else if(dl_id == "area_county"){
			$("#area").val(that.attr("id"));
			$("#areaName").val(that.text());
		}else if(dl_id == "area_street"){
			$("#town").val(that.attr("id"));
			$("#townName").val(that.text());
		}
		//选择结果
		addrTxt.push(that.text());
		setTimeout(function(){
			if(thisCon.next("dl").length > 0){
				prev.show(); // 返回上一级
				content.hide();
				thisCon.next("dl").show();
				title.html(thisCon.next("dl").attr("title"));
				if(thisCon.next("dl").attr("id") == "area_city"){
					
					$("#area_city").empty();
					$.post(path + "/address/city",{proviceId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#area_city").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}else if(thisCon.next("dl").attr("id") == "area_county"){
					$("#area_county").empty();
					$.post(path + "/address/area",{cityId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#area_county").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}else if(thisCon.next("dl").attr("id") == "area_street"){
					$("#area_street").empty();
					$.post(path + "/address/town",{areaId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#area_street").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}
				}else{
					//返回初始状态
					content.hide();
					$("#area_overlay, #area_layer").hide();
					$("#area_layer dl:first").show();
					title.html($("#area_layer dl:first").attr("title"));
					var length = addrTxt.length -1;
					if(addrTxt[length] == '关闭'){
						$("#select_area").text("省市区");
					}else{
						$("#select_area").text(addrTxt.join(" "));
					}
			}
			
		}, 10);

		return false;
	});
	
	//返回上一级	
	$(".prev_btn").click(function(){	
		setTimeout(function(){				
			addrTxt.pop();			
			content.hide();
			thatDl.show();			
			thatDl.find("a").removeClass("cur");
			title.html(thatDl.attr("title"));
			thatDl = thatDl.prev("dl");
			if(title.html() == "省份"){	
				$(".prev_btn").hide();
			}	
		}, 300);
		return false;
	});
	
	//收货地址的弹层关闭
	$(".close_btn, #area_overlay").click(function(){
		$("#area_layer, #area_overlay").hide();
		$("#area_layer h3").show().html("省份");
		$("#area_layer").find("dl").eq(0).show().siblings("dl").hide();
		$("#area_layer dd a").removeClass("cur");
	});
	
	
});