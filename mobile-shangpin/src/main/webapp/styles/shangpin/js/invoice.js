(function() {
    window.Login = function() {},
    $.extend(window.Login.prototype, {
        init: function() {},
        loginForm: function() {
            $("#J_Login").on("submit",
            function(e) {
				var re = /^[\u4e00-\u9fa5]+$/,
					mre = /^1\d{10}$/,
					post = /^[1-9][0-9]{5}$/;
				
			
				//发票内容
                if ($.trim($("#J_invoiceContent").val()) == ""){
					return jShare('请选择发票内容!',"",""),
					$("#J_invoiceContent").addClass("error"),
                	!1;
				}else{
					$("#J_invoiceContent").removeClass("error");
				}
				
				var J_invoiceAddr =  $(".select-address");
				//发票邮寄地址
				if (!J_invoiceAddr.hasClass("cur") && !$('.other-address em').html()){
					return jShare('请填写发票邮寄地址!',"",""),
                	!1;
				}else{
				}
				
				
            });
        }
    });
})(jQuery);

//登录表单验证
if($("#J_Login").length > 0){
	var Login = new Login();
	Login.loginForm();
}

$(function(){
//	$("#order_invoice_name").hide();
//	$("#order_invoice_tel").hide();
//	$("#order_J_addr").hide();
//	$("#order_postcode").hide();
	$("#invoice_list").hide();
	
	//选择地址
	$(".select-address").click(function(){
		$(".select-address").toggleClass("cur");
		if($("#other_address").hasClass("cur")){
			$("#invoice_list").show();
		}else{
			$("#invoice_list").hide();
			$("#invoice_add").hide();
			$("#save_invoice_info").show();
			$("#other_address span").remove();
			$("#other_address").html("<em>其他地址</em>");
		}
	});
	
	//点击发票地址列表
	$("#invoice_list").delegate("p","click",function(){
		var invoice_id = $(this).attr("id");
		var addr = $(this).html();
		$("#other_address em").remove();
		$("#other_address").append(addr);
		$("#other_address span").prepend("<em>其他地址</em>");
		$("#invoice_addr").val(invoice_id);
		$("#invoice_list").hide();
		$("#save_invoice_info").show();
		$(".addr_block").css('height','90px');
	});
	
	
});

$(function(){
	//选择收货地址
	var addrTxt,thisCon,content,that,obj,title,prev,thatDl;
	$(".other-address a").click(function(){
		$(".invoice_prev_btn").hide();
		$("#invoice_area_overlay").height($(document).height());
		$("#invoice_area_overlay, #invoice_area_layer").show();
		addrTxt = [];
		return false;
	});
	
	$("#invoice_area_layer").delegate("a","click",function(){
		var that = $(this);
		prev = $(".invoice_prev_btn");
		obj = $("#invoice_area_layer dd  a");
		content = $("#invoice_area_layer dl");
		thisCon = that.closest("dl");
		title = $("#invoice_area_layer h3");
		obj.removeClass("cur");	
		that.addClass("cur");
		thatDl = thisCon;
		var dl_id = thisCon.attr("id");
		if(dl_id == "invoice_area_province"){
			$("#invoice_province").val(that.attr("id"));
			$("#invoice_provincename").val(that.text());
		}else if(dl_id == "invoice_area_city"){
			$("#invoice_city").val(that.attr("id"));
			$("#invoice_cityname").val(that.text());
		}else if(dl_id == "invoice_area_county"){
			$("#invoice_area").val(that.attr("id"));
			$("#invoice_areaname").val(that.text());
		}else if(dl_id == "invoice_area_street"){
			$("#invoice_town").val(that.attr("id"));
			$("#invoice_townname").val(that.text());
		}
		//选择结果
		addrTxt.push(that.text());
		setTimeout(function(){
			if(thisCon.next("dl").length > 0){
				prev.show(); // 返回上一级
				content.hide();
				thisCon.next("dl").show();
				title.html(thisCon.next("dl").attr("title"));
				if(thisCon.next("dl").attr("id") == "invoice_area_city"){
					$("#invoice_area_city").empty();
					$.post("../address/city",{proviceId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#invoice_area_city").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}else if(thisCon.next("dl").attr("id") == "invoice_area_county"){
					$("#invoice_area_county").empty();
					$.post("../address/area",{cityId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#invoice_area_county").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}else if(thisCon.next("dl").attr("id") == "invoice_area_street"){
					$("#invoice_area_street").empty();
					$.post("../address/town",{areaId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#invoice_area_street").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}
				}else{
					//返回初始状态
					content.hide();
					$("#invoice_area_overlay, #invoice_area_layer").hide();
					$("#invoice_area_layer dl:first").show();
					title.html($("#invoice_area_layer dl:first").attr("title"));
					var length = addrTxt.length -1;
					if(addrTxt[length] == '关闭'){
						$(".other-address a").text("省市区");
					}else{
						$(".other-address a").text(addrTxt.join(" "));
					}
			}
			
		}, 300);
		return false;
	});
	
	//返回上一级	
	$(".invoice_prev_btn").click(function(){	
		setTimeout(function(){				
			addrTxt.pop();			
			content.hide();
			thatDl.show();			
			thatDl.find("a").removeClass("cur");
			title.html(thatDl.attr("title"));
			thatDl = thatDl.prev("dl");
			if(title.html() == "省份"){	
				$(".invoice_prev_btn").hide();
			}	
		}, 300);
		return false;
	});
	
	//收货地址的弹层关闭
	$(".invoice_close_btn, #invoice_area_overlay").click(function(){
		$("#invoice_area_layer, #invoice_area_overlay").hide();
		$("#invoice_area_layer h3").show().html("省份");
		$("#invoice_area_layer").find("dl").eq(0).show().siblings("dl").hide();
		$("#invoice_area_layer dd a").removeClass("cur");
	});
});

//保存发票信息
function saveInvoiceInfo(id, header){
	var re = /^[\u4e00-\u9fa5]+$/,
	mre = /^1\d{10}$/,
	post = /^[1-9][0-9]{5}$/;
	//发票类型
	if ($.trim($("#J_invoiceType").val()) == ""){
		return jShare('请选择发票类型!',"",""),
		$("#J_invoiceType").addClass("error"),
    	!1;
	}else{
		$("#J_invoiceType").removeClass("error");
	}
	//发票抬头
	if ($.trim($("#J_invoiceName").val()) == ""){
		$("#J_invoiceName").val("个人");
	}
	//发票内容
    if ($.trim($("#J_invoiceContent").val()) == ""){
		return jShare('请选择发票内容!',"",""),
		$("#J_invoiceContent").addClass("error"),
    	!1;
	}else{
		$("#J_invoiceContent").removeClass("error");
	}
	var path = getRootPath();
	//与收获地址相同
	if($("#invoice_address_same").hasClass("cur")){
		var addressId = $("#addrid").val();
		$("#invoice_addr").val(addressId);
	}
	
	$("#invoice_type").text($("#J_invoiceType").find("option:selected").text());
	$("#invoice_title").text($("#J_invoiceName").val());
	$("#invoicetitle").val($("#J_invoiceName").val());
	$("#invoiceflag").val("1");
	showPage(id, header);
}

//显示新增发票地址表单
function showAdd(){
	$("#invoice_add").show();
	$("#save_invoice_info").hide();
	$("#invoice_list").hide();
	scroll(0,0);
}

//新增收获地址
function addInvoiceAddress(content,header){
	var re = /^[\u4e00-\u9fa5]+$/,
	mre = /^1\d{10}$/,
	post = /^[1-9][0-9]{5}$/;
	//收货人姓名
	if ($.trim($("#invoice_name").val()) == "" ){
		return jShare('请填写正确中文名称!',"",""),
		$("#invoice_name").addClass("error"),
		!1;
	}else{
		$("#invoice_name").removeClass("error");
	}
	
	//收货人电话
	if ($.trim($("#invoice_tel").val()) == "" || !mre.test($("#invoice_tel").val())){
		return jShare('请输入正确手机号码!',"",""),
		$("#invoice_tel").addClass("error"),
		!1;
	}else{
		$("#invoice_tel").removeClass("error");
	}
	
	//收货人地址
	if ($.trim($("#invoice_addr_order").val()) == "" || $.trim($("#invoice_province").val()) == "" || $.trim($("#invoice_city").val()) == "" || $.trim($("#invoice_area").val()) == "" || $.trim($("#invoice_town").val()) == ""){
		return jShare('请输入详细地址!',"",""),
		$("#invoice_addr_order").addClass("error"),
		!1;
	}else{
		$("#invoice_addr_order").removeClass("error");
	}
	
	//收货人邮编
	if ($.trim($("#invoice_code").val()) == "" || !post.test($("#invoice_code").val())){
		return jShare('请输入正确邮编!',"",""),
		$("#invoice_code").addClass("error"),
		!1;
	}else{
		$("#invoice_code").removeClass("error");
	}
	var path = getRootPath();
	var invoices = $(".inovice_form").serialize();
	$.post(path + "/invoice/add",invoices,function(data){
		if(data.code == 0){
			$("#invoice_list p").remove();
			$.each(data.invoices,function(index, item){
				var $html = "<p style='height: 99px' class='addr_block' id='" + item.id + "'>" +
							"<span><i>" + item.name + "&nbsp;&nbsp;" + item.tel + "</i>" +
							"<em>" + item.provname + item.cityname + item.areaname + item.townname + item.addr + "</em>" +		
							"</span>";
				$("#invoice_list").prepend($html);
			});
			var invoice_id = data.invoices[0].id;
			$("#invoice_addr").val(invoice_id);
			$(".other-address a").text("省市区");
			$("#invoice_name").val("");
			$("#invoice_tel").val("");
			$("#invoice_addr_order").val("");
			$("#invoice_code").val("");
			$("#invoice_add").hide();
			$("#invoice_list").show();
		}else{
			alert(data.msg);
			$("#invoice_add").hide();
			$("#invoice_list").show();
		}
	},"json");
	
}

