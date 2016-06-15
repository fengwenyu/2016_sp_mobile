/**
 * @author zghw
 */
$(function() {
	//读取错误msg
//	var msg=$("#msg").val();
//	if(msg!=""){
//		alert(msg);
//		$("#msg").val("");
//		return;
//	}
	
	// 选择配送方式
	$("#select_time").click(function() {
		$("#area_overlay1").height($(document).height());
		$("#area_overlay1, #area_layer1").show();
		addrTxt = "";
		return false;
	});

	// 选择配送方式
	$("#area_layer1 dd  a").click(function() {
		var that = $(this), obj = $("#area_layer1 dd  a"), timeTxt = that.text();
		obj.removeClass("cur");
		that.addClass("cur");
		// 设置到提交订单页中的配送方式ID
		$("#express").val(that.attr("timeId"));
		setTimeout(function() {
			$("#area_overlay1, #area_layer1").hide();
			$(".delivery_mode").text(timeTxt);

		}, 300);
		return false;

	});
	// 需要发票
	$('#yes').click(function() {
		$('.invoice').show();
		$('#no').removeClass("cur");
		$(this).addClass("cur");
		$("#invoiceFlag").val("1");
		// 如果发票地址id不存在则设置为收货地址
		if ($("#invoiceAddressId").val() == "") {
			$("#invoiceAddressId").val($("#addressId").val());
		}
	});
	// 不需要发票
	$('#no').click(function() {
		$('.invoice').hide();
		$('#yes').removeClass("cur");
		$(this).addClass("cur");
		$("#invoiceFlag").val("0");
	});

	// 配送方式的弹层关闭
	$(".close_btn, #area_overlay1").click(function() {
		$("#area_layer1, #area_overlay1").hide();
	});

	// 身份证关闭按钮
	$("#popup_cancel,.title_closeBtn").click(function(e) {
		e.preventDefault();
		$('#overlay').hide();
		$('#popup_box').hide();
	});
	
	$("#select-area-overlay, .close_btn").click(function(){
		$("#select-area-overlay, .select-layer").hide();
	});
	//选择支付方式
	$(".paymet_block fieldset p span a").click(function(){
		console.log(123);
		var that = $(this),
			obj = $(".paymet_block fieldset p span a");
		
		obj.removeClass("cur");	
		that.addClass("cur");
		var attr = that.closest("span").attr("class")
		if(attr == "alipayOut"){
			$("#payId").val("30");
			$("#payChildId").val("121");
		}else if(attr == "alipay"){
			$("#payId").val("20");
			$("#payChildId").val("37");
		}else if(attr == "weixinPayOut"){
			$("#payId").val("32");
			$("#payChildId").val("111");
		}else if(attr == "unionPay"){
			$("#payId").val("19");
			$("#payChildId").val("49");
		}else if(attr == "weixinPayPub"){
			$("#payId").val("27");
			$("#payChildId").val("58");
		}else if(attr == "weixinPay"){
				$("#payId").val("27");
				$("#payChildId").val("117");
		}else if(attr == "cashPay" || attr == "postPay"){
			$("#payId").val("2");
			$("#payChildId").val("41");
		}else if(attr == "pufaPay"){
			$("#payId").val("15");
			$("#payChildId").val("115");
		}else{
			$("#payId").val("0");
			$("#payChildId").val("0");
		}
		
	});
	//选择四级地址
	$("#select_street_layer").delegate("a","click",function(){
		$(this).addClass("cur");
		var obj = $("#select_street_layer dd  a");
		var timeTxt = $(this).text();
		console.log(timeTxt);
		obj.removeClass("cur");	
		var townName = $(this).text();
		var townId = $(this).attr("id");
		var addrId = $("#addressId").val();
		$("#fourth_id").val(townId);
		setTimeout(function(){
			$("#select-area-overlay, .select-layer").hide();
			//$(".delivery_mode").text(timeTxt);
			//更新收货地址
			var path = getRootPath();
			$.get(path + "/address/ajax/update?addressId=" + addrId,function(data){
				console.log("update address:" + data);
				$.post(path + "/address/ajax/edit",{
					id : data.id,
					province : data.province,
					city : data.city,
					area : data.area,
					town : townId,
					name : data.name,
					addr : data.addr,
					postcode : data.postcode,
					tel : data.tel,
					cardID: data.cardID
				},function(data){
					if(data.code == "0"){
						$.each(data.content,function(index, item){
							if(addrId == item.id){
								var showAddress = item.provname + item.cityname + item.areaname + item.townname + item.addr;
								$("#showAddr span").text(showAddress);
								return;
							}
						});
					}else{
						alert("四级地址更新失败");
						return;
					}
				},"json");
			},"json");
		}, 300);
		return false;
	});
	
});

// 保存身份证号码
function saveCardID() {
	var cardID = $("#carIDNum").val().toUpperCase();
	var addressId = $("#addressId").val();
	// 验证身份证号码
	if (cardID == "") {
		alert("请输入身份证号码！");
		return false;
	}
	if (!checkCard(cardID)) {
		alert("您填写的身份证号码无效！");
		return false;
	} else {
		// ajax请求修改省份证号码
		$.ajax({
			url : getRootPath() + '/address/ajax/addCardID',
			data : {
				"addressId" : addressId,
				"cardID" : cardID
			},
			type : "get",
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					var code = data.code;
					var msg = data.msg;
					if (code == "1") {
						alert(msg);
					} else {
						// 关闭弹层
						$('#overlay').hide();
						$('#popup_box').hide();
						alert("添加身份证信息成功!");
					}
				}
			}
		});

	}
}
// 重复提交标识
var checkSubmitFlg = false;
function submitOrder() {
	var path = getRootPath();
	if (!checkSubmitFlg) {
		// 验证表单
		var validOk = validOrder();
		if (!validOk) {
			return false;
		}
		//判断收货地址中是否有四级地址
		var fourth_id = $("#fourth_id").val();
		if(fourth_id == '' || fourth_id == '0'){
			$("#select-area-overlay, #select_street_layer").show();
			var area = $("#third_id").val();
			$.post(path + "/address/town",{areaId : area},function(data){
				$("#orgin_area_street").empty();
				$.each(data,function(index,item){
					$("#orgin_area_street").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
				});
			},"json");
			return;
		}
		
		// 如果收货地址中的身份证号码不存在则弹层提示
		$.ajax({
			type : "get",
			url : getRootPath() + "/address/ajax/hasCardID?"+new Date(),
			data : {
				"addressId" : $("#addressId").val()
			},
			async : false,
			dataType : "json",
			success : function(data) {
				if (!data) {
					// 收货地址不存在身份证号
					$('#overlay').show();
					$('#popup_box').show();
					return false;
				} else {
					// 解除绑定事件
					$(window).unbind('beforeunload');
					// 提交订单
					checkSubmitFlg = true;
					$("#so").html('正在提交...');
					$("#orderForm").submit();
				}
			}
		});
	}
}
// 验证提交表单
function validOrder() {
	var addressId = $("#addressId").val();
	var invoiceFlag = $("#invoiceFlag").val();
	// var invoiceType=$("#invoiceType").val();
	var invoiceTitle = $("#invoiceTitle").val();
	var invoiceContent = $("#invoiceContent").val();
	var invoiceAddressId = $("#invoiceAddressId").val();
	var express = $("#express").val();
	var payId = $("#payId").val();
	var payChildId = $("#payChildId").val();
	if (addressId == "") {
		alert("请填写收货地址信息!");
		return false;
	}
	// 开发票验证
	if (invoiceFlag == 1) {
		if (invoiceTitle == "") {
			alert("请填写发票抬头!");
			return false;
		}
		if (invoiceContent == "") {
			alert("请填写发票内容！");
			return false;
		}
		if (invoiceAddressId == "") {
			alert("请填写发票地址！");
			return false;
		}
	}
	if (express == "") {
		alert("请选择配送方式！");
		return false;
	}
	if (payId == "" || payChildId == "") {
		alert("请选择支付方式！");
		return false;
	}
	return true;
}
