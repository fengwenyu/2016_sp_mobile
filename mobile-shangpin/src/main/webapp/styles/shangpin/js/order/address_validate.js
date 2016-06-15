(function() {
			window.Order = function() {
			},
			$.extend(
					window.Order.prototype,
					{
						init : function() {
						},
						addrValidate : function() {
							var re = /^[\u4e00-\u9fa5]$/, mre = /^1[358]\d{9}$/, post = /^[0-9][0-9]{5}$/;
							// 收货人姓名
							if ($.trim($("#J_AddrName").val()) == ""
									|| $("#J_AddrName").val().length > 20) {
								return alert('请填写正确的姓名!'), $(
										"#J_AddrName")
										.addClass("error"), !1;
							} else {
								$("#J_AddrName").removeClass("error");
							}

							// 收货人电话
							if ($.trim($("#J_Mobile").val()) == ""
									|| !mre.test($("#J_Mobile").val())) {
								return alert('请输入正确手机号码!'), $(
										"#J_Mobile").addClass("error"),
										!1;
							} else {
								$("#J_Mobile").removeClass("error");
							}
							// 省市县区
							if ($.trim($("#J_select_area").text()) == ""
									|| $("#J_select_area").text() == "省市区") {
								return alert('请选择收货地址!'), $(
										"#J_select_area").addClass(
										"error"), !1;
							} else {
								$("#J_select_area").removeClass("error");
							}
							// 收货人地址
							if ($.trim($("#J_AddrText").val()) == "" ) {
								return alert('请输入详细地址!'), $(
								"#J_AddrText")
								.addClass("error"), !1;	
							}else if($("#J_AddrText").val().length < 5){
								return alert('详细地址长度小于5!'), $(
								"#J_AddrText")
								.addClass("error"), !1;	
							} else {
								$("#J_AddrText").removeClass("error");
							}

							// 收货人邮编
							if ($.trim($("#J_AddrCode").val()) == ""
									|| !post.test($("#J_AddrCode")
											.val())) {
								return alert('请输入正确邮编!'), $(
										"#J_AddrCode")
										.addClass("error"), !1;
							} else {
								$("#J_AddrCode").removeClass("error");
							}
						},
						addrInvoiceValidate : function() {
							var re = /^[\u4e00-\u9fa5]$/, mre = /^1[358]\d{9}$/, post = /^[0-9][0-9]{5}$/;
							// 收货人姓名
							if ($.trim($("#I_AddrName").val()) == ""
								|| $("#I_AddrName").val().length > 20) {
								return alert('请填写正确的姓名!'), $(
								"#I_AddrName")
								.addClass("error"), !1;
							} else {
								$("#I_AddrName").removeClass("error");
							}
							
							// 收货人电话
							if ($.trim($("#I_Mobile").val()) == ""
								|| !mre.test($("#I_Mobile").val())) {
								return alert('请输入正确手机号码!'), $(
								"#I_Mobile").addClass("error"),
								!1;
							} else {
								$("#I_Mobile").removeClass("error");
							}
							// 省市县区
							if ($.trim($("#I_select_area").text()) == ""
								|| $("#I_select_area").text() == "省市区") {
								return alert('请选择收货地址!'), $(
								"#I_select_area").addClass(
								"error"), !1;
							} else {
								$("#I_select_area").removeClass("error");
							}
							// 收货人地址
							if ($.trim($("#I_AddrText").val()) == "" ) {
								return alert('请输入详细地址!'), $(
								"#I_AddrText")
								.addClass("error"), !1;
							}else if($("#I_AddrText").val().length < 5){
								return alert('详细地址长度小于5!'), $(
								"#I_AddrText")
								.addClass("error"), !1;
							} else {
								$("#I_AddrText").removeClass("error");
							}
							
							// 收货人邮编
							if ($.trim($("#I_AddrCode").val()) == ""
								|| !post.test($("#I_AddrCode")
										.val())) {
								return alert('请输入正确邮编!'), $(
								"#I_AddrCode")
								.addClass("error"), !1;
							} else {
								$("#I_AddrCode").removeClass("error");
							}
						}
					});
})(jQuery);
//选择收货地址
var addrTxt;
//加锁
var block = true;

function selectArea(addrFlag) {
	block = true;
	$("#area_overlay_addr").height($(document).height());
	$("#area_overlay_addr, #area_layer_addr").show();
	addrTxt = "";
	$("#addrFlag").val("#"+addrFlag);
	return false;
}

function choiseArea(o) {
	var addrFlag=$("#addrFlag").val();
	var province=addrFlag+"_AddrProvince",
		city=addrFlag+"_AddrCity",
		area=addrFlag+"_AddrArea",
		town=addrFlag+"_AddrTown",
		selectTxt=addrFlag+"_select_area";
	
	if (!block) {
		return;
	}
	block = false;
	var that = $(o), obj = $("#area_layer_addr dd  a"),

	content = $("#area_layer_addr dl"), thisCon = that.closest("dl"), title = $("#area_layer_addr h3"), nextDl = thisCon
			.next("dl");
	obj.removeClass("cur");
	that.addClass("cur");
	// 选择结果
	addrTxt += " " + that.text();

	setTimeout(function() {

		var did = that.attr("id");
		var conId = thisCon.attr("id");
		var typeId = nextDl.attr("id");
		var url = getContextPath();
		if (thisCon.next("dl").length > 0) {
			content.hide();
			nextDl.children("dd").remove();
			if (typeId == 'area_city') {
				url += "/accountaction!city";
				$(province).val(did);
			}
			if (typeId == 'area_county') {
				url += "/accountaction!area";
				$(city).val(did);
			}
			if (typeId == 'area_street') {
				url += "/accountaction!town";
				$(area).val(did);
			}
			$.post(url, {
				"id" : did
			}, function(data) {
				// 迭代返回的json数据
				$.each(data.content, function(index, content) {
					// 动态装载市级数据
					nextDl.append("<dd><a href='javascript:;' id='"
							+ content.id + "' onclick='choiseArea(this)' >"
							+ content.name + "</a></dd>");
				});
				block = true;
			}, "json");
			nextDl.show();
			title.html(thisCon.next("dl").attr("title"));
		} else {
			if (conId == 'area_street') {
				$(town).val(did);
			}
			// 返回初始状态
			content.hide();
			$("#area_overlay_addr, #area_layer_addr").hide();
			$("#area_layer_addr dl:first").show();
			title.html($("#area_layer_addr dl:first").attr("title"));

			$(selectTxt).text(addrTxt);
			block = true;
		}

	}, 300);

	return false;
}
/** 获取项目上下文路径 */
function getContextPath() {
	var contextPath = document.location.pathname;
	var index = contextPath.substr(1).indexOf("/");
	contextPath = contextPath.substr(0, index + 1);
	delete index;
	return contextPath;
}