$(function(){
	
	//$(".select-overlay, #select_street_layer").show(); //四级地址弹层
	
	//选择支付方式
	$(".paymet_block fieldset p.payment-method span a").click(function(){
		
		var that = $(this),
			obj = $(".paymet_block fieldset p.payment-method span a");
		
		obj.removeClass("cur");	
		that.addClass("cur");
        var attr = that.closest("span").attr("class")
        if(attr == "alipay"){
            $("#mainPay").val("20");
            $("#subPay").val("37");
            $("#payDesc").html("支付宝支付")
        }else if(attr == "unionPay"){
            $("#mainPay").val("19");
            $("#subPay").val("49");
            $("#payDesc").html("银联支付")
        }else if(attr == "weixinPay"){
            var isWX = $("#_iswx").val();
            if(isWX){
                $("#mainPay").val("27");
                $("#subPay").val("58");
            }else{
                $("#mainPay").val("27");
                $("#subPay").val("117");
            }
            $("#payDesc").html("微信支付")
        }else{
            $("#mainPay").val("0");
            $("#subPay").val("0");
        }

	});
	
	//选择礼品卡支付
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
	/*$("#dis_modes").click(function(){
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
	*/
	
	$('#yes').click(function(){
		$('.invoice').show();
		$('#no').removeClass("cur");	
		$(this).addClass("cur");
		$("#invoiceFlag").val("1");
	});
	
	$('#no').click(function(){
		$('.invoice').hide();
		$('#yes').removeClass("cur");	
		$(this).addClass("cur");
		$("#invoiceFlag").val("0");
	});
	
	$('.other-payment').click(function(){
		$('.other-payment').hide();
		$('.other-payment-box').show();	
	});
	
	//收货地址的弹层关闭
	$(".close_btn, .select-overlay").click(function(){
		$("#rece_time_layer,.select-overlay,#dis_modes_layer,#select_street_layer").hide();
	});
	
	
	//身份证弹框
	/*$(".payment_btn").click(function(e){
		e.preventDefault();
		$('#overlay').show();
		$('#popup_box').show();

	});
	*/
	$("#popup_cancel,.title_closeBtn").click(function(e){
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
	}

	$('.js-invoice').click(function(){
		$('.js-order-back-btn').show();
		$('.js-order-page').hide();
		document.title="发票详情"; 
		$('.top-title').html('发票详情');
		$('.js-invoice-detail').show();
		var invoiceTitle = $('#invoiceTitle').text();
		var invoiceContent = $('#invoiceContent').text();
		if(invoiceTitle != ''){
			$('#J_invoiceName').val(invoiceTitle);
		}
		if(invoiceContent != ''){
			$('#J_invoiceContent option').each(function(index, item){
				if($(item).text() == invoiceContent){
					$(item).attr("selected","selected");
				}
			});
		}
	});
	
	$('.js-select-address').click(function(){
		document.title="发票地址"; 
		$('.top-title').html('发票地址');
		$('.alUser_icon').hide();
		$('.add-address-btn').show();
		$('.js-invoice-detail').hide();
		$('.js-invoice-address-list').show();
		
	});
	
	$('.js-invoice-address-list').delegate('.js-addr-info','click',function(){
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
		/*if($('.paymet_block').is('hidden')){
		
		}*/
		
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
	
	$('.js-invoice-address-list').delegate('a.js-edit-btn','click',function(){
		var path = getRootPath();
		document.title="编辑地址"; 
		$('.top-title').html('编辑地址');
		var addressId = $(this).closest(".addr_block").attr("id");
		$.post(path + "/invoice/ajax/update",{addressId: addressId},function(data){
			$("#addressId").val(data.id);
			$("#province").val(data.province);
			$("#provincename").val(data.provname);
			$("#city").val(data.city);
			$("#cityname").val(data.cityname);
			$("#area").val(data.area);
			$("#areaname").val(data.areaname);
			$("#town").val(data.town);
			$("#townname").val(data.townname);
			$("#J_userName").val(data.name);
			$("#J_mobileNum").val(data.tel);
			$("#J_code").val(data.postcode);
			$("#J_addr").val(data.addr);
			$("#select_area").text(data.provname + " " + data.cityname + " " + data.areaname + " " + data.townname);
		},"json");
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
	
	$("#area_layer").delegate("a","click",function(){
		var path = getRootPath();
		that = $(this);			
		prev = $(".prev_btn");
		obj = $("#area_layer dd  a");			
		title = $("#area_layer h3")
		content = $("#area_layer dl");
		thisCon = that.closest("dl");
		obj.removeClass("cur");	
		that.addClass("cur");
		thatDl = thisCon
		var dl_id = thisCon.attr("id");
		if(dl_id == "area_province"){
			$("#province").val(that.attr("id"));
			$("#provincename").val(that.text());
		}else if(dl_id == "area_city"){
			$("#city").val(that.attr("id"));
			$("#cityname").val(that.text());
		}else if(dl_id == "area_county"){
			$("#area").val(that.attr("id"));
			$("#areaname").val(that.text());
		}else if(dl_id == "area_street"){
			$("#town").val(that.attr("id"));
			$("#townname").val(that.text());
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
				$(".select-overlay, #area_layer,.prev_btn").hide();
				$("#area_layer dl:first").show();
				title.html($("#area_layer dl:first").attr("title"));
				var length = addrTxt.length -1;
				if(addrTxt[length] == '关闭'){
					$("#select_area").text("省市区");
				}else{
					$("#select_area").text(addrTxt.join(" "));
				}				
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
//		if ($.trim($("#J_identificationNum").val()) == "" || !identification.test($("#J_identificationNum").val())){
//			return jShare('请输入正确的身份证号码',"",""),
//			$("#J_identificationNum").addClass("error"),
//        	!1;
//		}else{
//			$("#J_identificationNum").removeClass("error");
//		}
        var path = getRootPath();
    	var invoices = $("#invoice_addd_login").serialize();
    	var addressId = $("#addressId").val();
    	if(addressId == ''){
    		$.post(path + "/invoice/add",invoices,function(data){
        		if(data.code == 0){
        			$(".js-invoice-address-list p").remove();
        			$.each(data.invoices,function(index, item){
        				var $html = "<p class='addr_block' id='" + item.id + "'>" +
        							"<span id='" + item.id + "' class='js-addr-info'><i>" + item.name + "&nbsp;&nbsp;" + item.tel + "</i>" +
        							item.provname + item.cityname + item.areaname + item.townname + item.addr +	"</span>" +	
        							"<span class='addr_edit'><a href='javascript:;' class='editBtn js-edit-btn'>编辑</a>&nbsp;&nbsp;" + 
        							"<a href='javascript:;' class='deletBtn'>删除</a></span></p>";
        				$(".js-invoice-address-list").append($html);
        				clearForm("invoice_addd_login");
        			});
        			
        			$('.add-address-btn').show();
        			document.title="发票地址"; 
        			$('.top-title').html('发票地址');
        			$('.js-invoice-address-list').show();
        			$('.js-add-invoice-address').hide();
        		}else{
        			alert(data.msg);
        		}
        	},"json");
    	}else{
    		$.post(path + "/invoice/ajax/edit",invoices,function(data){
    			console.log(data);
    			if(data.code == "0"){
    				$(".js-invoice-address-list p").remove();
        			$.each(data.content.list,function(index, item){
        				var $html = "<p class='addr_block' id='" + item.id + "'>" +
        							"<span class='js-addr-info'><i>" + item.name + "&nbsp;&nbsp;" + item.tel + "</i>" +
        							item.provname + item.cityname + item.areaname + item.townname + item.addr +	"</span>" +	
        							"<span class='addr_edit'><a href='javascript:;' class='editBtn js-edit-btn'>编辑</a>&nbsp;&nbsp;" + 
        							"<a href='javascript:;' class='deletBtn'>删除</a></span></p>";
        				$(".js-invoice-address-list").append($html);
        				clearForm("invoice_addd_login");
        			});
        			
        			$('.add-address-btn').show();
        			document.title="发票地址"; 
        			$('.top-title').html('发票地址');
        			$('.js-invoice-address-list').show();
        			$('.js-add-invoice-address').hide();
    			}else{
    				alert(data.msg);
    			}
    		},"json");
    	}
    	
	});
	
	//删除发票地址
	$('.js-invoice-address-list').delegate('a.deletBtn','click',function(){
		var path = getRootPath();
		var $this = $(this);
		$.alerts.dialogClass = "delet-pop";
		jConfirm("确定要删除此地址吗？","",function(result){
			if(result===true){
				var invoiceId = $this.parents(".addr_block").attr("id");
				$.post(path + "/invoice/del",{invoiceId : invoiceId},function(data){
					if(data.code == "0"){
						$this.parents(".addr_block").remove();
					}else{
						alert(data.msg);
					}
				},"json");
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
		$('.js-invoice-detail').hide();
		if($('.js-invoice-detail').find("span.js-addr-info").length > 0){//表示改变发票地址
			var arr = $('.js-invoice-detail').find("span.js-addr-info").find("i").text().split(' ');
			$('#invoiceName').text(arr[0]);
			$('#invoiceTel').text(arr[1]);
			$('#invoiceAddress').text($('.js-invoice-detail').find("span.js-addr-info").find("b").text());
			$("#isInvoiceAddrId").val(1);
			var invoiceAddrId = $('.js-invoice-detail').find("span.js-addr-info").attr("id");
			$("#invoiceAddrId").val(invoiceAddrId);
		}
		var J_invoiceName = $("#J_invoiceName").val();
		var J_invoiceContent = $("#J_invoiceContent option:selected").text();
		var invoiceTitle = $("#invoiceTitle").text();
		var invoiceContent = $("#invoiceContent").text();
		if((invoiceTitle == J_invoiceName) && (J_invoiceContent == invoiceContent)){//表示发票内容未被修改
			$("#isModifyInvoice").val("0");
		}else{
			$("#isModifyInvoice").val("1");
			var invoiceTitle = $("#invoiceTitle").text(J_invoiceName);
			var invoiceContent = $("#invoiceContent").text(J_invoiceContent);
		}
	});
	
	//提交订单结算
	$(".payment_submit .payment-btn").click(function(e){
		e.preventDefault();
		var path = getRootPath();
		var mainPay = $("#mainPay").val();
		var subPay = $("#subPay").val();
		var orderId = $("#mainOrderId").val();
		var invoiceFlag = $("#invoiceFlag").val();
		var isInvoiceAddrId = $("#isInvoiceAddrId").val();
		if(isInvoiceAddrId == "-1"){//表示不修改发票地址
			var invoiceAddrId = "-1";
		}else{
			var invoiceAddrId = $("#invoiceAddrId").val();
		}
		var isModifyInvoice = $("#isModifyInvoice").val();
		var invoiceTitle = $("#invoiceTitle").text();
		var invoiceContent = $("#invoiceContent").text();
		var payType;
		if(mainPay == "20" && subPay == "37"){
			payType = "20_20_37";
		}else if(mainPay == "19" && subPay == "49"){
			payType = "19_19_49";
		}else if(mainPay == "2" && subPay == "41"){
			payType = "4_2_41";
		}else if(mainPay == "27"&& subPay == "58"){
			payType = "21_27_58";
		}else if(mainPay == "15"&& subPay == "115"){
			payType = "21_15_115";
		}else if(mainPay == "27"&& subPay == "117"){
			payType = "21_27_117";
		}else if(mainPay == "15"&& subPay == "118"){
			payType = "21_15_118";
		}else{//表示订单来自其他站点，如果M站没有该支付方式，默认选择支付宝进行支付
			mainPay = "20";
			subPay = "37";
			payType = "20_20_37";
		}
		$.post(path + "/order/modify",{
            orderId : orderId,
            addrId : "-1",
            express : "1",
            invoiceFlag : invoiceFlag,
            invoiceAddrId : invoiceAddrId,
            isModifyInvoice : isModifyInvoice,
            invoiceType : "2",
            invoiceTitle : invoiceTitle,
            invoiceContent : invoiceContent,
        },function(data){
            if(data.code == "0"){
            	$.get(path + "/order/update/type?mainOrderNo=" + orderId + "&payType= " + payType, function(data){
        			//alert(data.code);
        			if(data.code != '0'){
        				alert(data.msg);
        				return;
        			}else{
        				//获取跳转到第三方支付页面url
                        if(mainPay == "20" && subPay == "37"){
                            $(".payment-btn").text("正在跳转支付宝支付页面.......");
                            window.location.href = path + "/pay/alipay/ALIWAP?orderId=" + orderId;
                        }else if(mainPay == "19" && subPay == "49"){
                            $(".payment-btn").text("正在跳转银联支付页面.......");
                            window.location.href = path + "/pay/unpay/UNWAP?orderId=" + orderId;
                            setTimeout(function(){
                                window.location.href = path + "/order/install?orderId=" + orderId;
                            },2000);
                        }else if(mainPay == "27" && subPay == "58"){
                            $(".payment-btn").text("正在跳转微信支付页面.......");
                            window.location.href = path + "/pay/weixin/WEIXINPUB?orderId=" + orderId;
                        }else if(mainPay == "27" && subPay == "117"){
                            $(".payment-btn").text("正在跳转微信支付页面.......");
                            window.location.href = path + "/pay/weixin/WEIXINWAP?orderId=" + orderId;
                        }
        			}
            	});
             
            }else{
            	alert(data.msg);
            }
        },"json");
	});
});

//清空表单
function clearForm(formId){
	$("#" + formId).children(":input").each(function(){
		$(this).val("");
	});
}