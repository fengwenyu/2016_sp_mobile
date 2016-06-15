/*
  $(function(){

	//$(".select-overlay, #select_street_layer").show(); //四级地址弹层

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
	
	
	//选择配送方式
	/!*$("#dis_modes").click(function(){
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
	*!/
	




	//身份证弹框
	/!*$(".payment_btn").click(function(e){
		e.preventDefault();
		$('#overlay').show();
		$('#popup_box').show();

	});
	*!/
	$("#popup_cancel_id,.title_closeBtn").click(function(e){
		e.preventDefault();
		$('#overlay').hide();
		$('#popup_box').hide();

	});
	$("#popup_ok_id").click(function(e){
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
	}

	$('.js-invoice').click(function(){
		$('.js-order-back-btn').show();
		$('.js-order-page').hide();
		document.title="发票详情"; 
		$('.top-title').html('发票详情');
		$('.js-invoice-detail').show();
	});
	
	$('.js-select-address').click(function(){
		document.title="发票地址"; 
		$('.top-title').html('发票地址');
		$('.alUser_icon').hide();
		$('.add-address-btn').show();
		$('.js-invoice-detail').hide();
		$('.js-invoice-address-list').show();
		
	});
	
	$('.js-addr-info').click(function(){
		document.title="发票详情"; 
		$('.top-title').html('发票详情');
		$('.alUser_icon').show();
		$('.add-address-btn').hide();
		$('.js-invoice-address-list').hide();
		$('.js-select-address').parent().find('.js-addr-info').remove();
		var selectAddress = $(this).clone();
		$('.js-select-address').after(selectAddress);
		$('.js-invoice-detail').show();
	});
	
	$('.js-order-back-btn').click(function(){
		/!*if($('.paymet_block').is('hidden')){
		
		}*!/
		
		$(".paymet_block").each(function(index, element){
		   if($(this).css("display")=="block"){
			   var index = $(this).index();
			   $('.add-address-btn').hide();
			   if(index == 3){
			   	 	$('.add-address-btn').show();
					document.title="发票地址"; 
					$('.top-title').html('发票地址');
			   }
			   if(index == 2){
					document.title="发票详情"; 
					$('.top-title').html('发票详情');
			   }
			   if(index<1){
			   	 $('.js-order-back-btn').hide();
				 
			   }else{
				 $(this).closest('.container').find('.paymet_block').eq(index).hide();
				 $(this).closest('.container').find('.paymet_block').eq(index-1).show();
				 if((index-1)<1){
			   	 	$('.js-order-back-btn').hide();
					document.title="提交订单"; 
				 	$('.top-title').html('提交订单');
				 }
				 
				
			   }
		   }
	   });
		
		
	});
	
	
	$('.add-address-btn').click(function(){
		document.title="新增地址"; 
		$('.top-title').html('新增地址');
		$('.add-address-btn').hide();
		$('.js-invoice-address-list').hide();
		$('.js-add-invoice-address').show();
	});
	
	$('.js-edit-btn').click(function(){
		document.title="编辑地址"; 
		$('.top-title').html('编辑地址');
		$('.add-address-btn').hide();
		$('.js-invoice-address-list').hide();
		$('.js-add-invoice-address').show();
	});
	
	
	//选择收货地址
	var addrTxt,thisCon,content,that,obj,title,prev,thatDl;
	$("#select_area").click(function(){
		$(".select-overlay").height($(document).height());
		$(".select-overlay, #area_layer").show();
		addrTxt = [];
		return false;
	});
	
	$("#area_layer dd a").click(function(){
		
		that = $(this);			
		prev = $(".prev_btn");
		obj = $("#area_layer dd  a");			
		title = $("#area_layer h3")
		content = $("#area_layer dl");
		thisCon = that.closest("dl");
		
		obj.removeClass("cur");	
		that.addClass("cur");
		thatDl = thisCon
			
		//选择结果
		addrTxt.push(that.text());
		setTimeout(function(){
			
			if(thisCon.next("dl").length > 0){
				prev.show(); // 返回上一级
				content.hide();
				thisCon.next("dl").show();
				title.html(thisCon.next("dl").attr("title"));
				
			}else{
				//返回初始状态
				content.hide();
				$(".select-overlay, #area_layer,.prev_btn").hide();
				$("#area_layer dl:first").show();
				title.html($("#area_layer dl:first").attr("title"));
								
				$("#select_area").text(addrTxt.join(" "));				
			}
			
		}, 300);
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
	$(".close_btn, .select-overlay").click(function(){
		$("#area_layer, .select-overlay").hide();
		$("#area_layer h3").show().html("省份");
		$("#area_layer").find("dl").eq(0).show().siblings("dl").hide();
		$("#area_layer dd a").removeClass("cur");

	})
	
	//jShare('请输入正确手机号码',"","");
	//点击保存按钮时验证输入内容是否正确
	$(".js-add-invoice-address .payment_btn").click(function(e){
		e.preventDefault();

		var re = /^[\u4e00-\u9fa5]$/,
			mre = /^1\d{10}$/,
			post = /^[1-9][0-9]{5}$/,
			identification = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
		
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
			return jShare('请输入正确邮编',"",""),
			$("#J_code").addClass("error"),
        	!1;
		}else{
			$("#J_code").removeClass("error");
		}

		//收货人姓名
		//if ($.trim($("#J_userName").val()) == "" || !re.test($("#J_userName").val())){
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

		//身份证号码
		if ($.trim($("#J_identificationNum").val()) == "" || !identification.test($("#J_identificationNum").val())){
			return jShare('请输入正确的身份证号码',"",""),
			$("#J_identificationNum").addClass("error"),
        	!1;
		}else{
			$("#J_identificationNum").removeClass("error");
		}
		
		$('.add-address-btn').show();
		document.title="发票地址"; 
		$('.top-title').html('发票地址');
		$('.js-invoice-address-list').show();
		$('.js-add-invoice-address').hide();

		
	});
	
	//删除商品
	$(".deletBtn").click(function(){
		var $this = $(this);
		//$this.parents(".addr_block").remove();
		$.alerts.dialogClass = "delet-pop";
		jConfirm("确定要删除此地址吗？","",function(result){

			if(result===true){
				$this.parents(".addr_block").remove();
				
			}else{
				
			}
			$.alerts.dialogClass = "";
		});
		return false;
		
	});
	
	$(".js-invoice-detail .payment_btn").click(function(e){
		e.preventDefault();
		if ($.trim($("#J_invoiceName").val()) == ""){
			return jShare('请输入发票抬头',"",""),
			$("#J_addr").addClass("error"),
        	!1;
		}
		$('.add-address-btn').hide();
		document.title="提交订单"; 
		$('.top-title').html('提交订单');
		$('.js-order-page').show();
		$('.js-invoice-address-list').hide();
	});
	

	
});
*/
