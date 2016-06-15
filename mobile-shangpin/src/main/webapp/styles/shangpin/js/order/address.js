//显示新增地址页	
function preAddress() {
	var d = [ "#addAddr", "#addAddrTop" ];
	swithcSHDef(d);
}
// 如果没有收货地址则进入新增否则进入列表
function showAddress() {
	if ($("#confirmAddrId").val() == "") {
		preAddress();
	} else {
		$("#listAddr .paymet_block .addr_block").removeClass("curr");
		// 取得当前的ID值
		var selectAddrid = "addr" + $("#confirmAddrId").val();
		// 拿当前的ID值作为id查询并赋予css cur
		eval('$("#' + selectAddrid + '").addClass("curr")');
		var d = [ "#listAddr", "#listAddrTop" ];
		swithcSHDef(d);
	}
}
function subAddr(addr,selected){
	var other=$(selected).attr("addrName")+" , "+$(selected).attr("addrTel")+"<br/>";
	return (other+addr);
}
// 点击一个地址 返回
function confirmAddr(o) {
	// 取得数据给前方
	$("#showAddr").html(subAddr($(o).attr("addr"),o));
	var addressId=$(o).attr("id").substring(4);
	// 设置被标记选中的ID
	$("#confirmAddrId").val(addressId);
	//是否支持货到付款
	var cod=$(o).attr("cod");
	//设置到订单form元素中
	$("#addressId").val(addressId);
	$("#isPayDelivery").val(cod);
	isPayOnDelivery();
	// 显示订单
	back();
}
// 编辑收货人地址
function addAddress() {
	var validate = new Order().addrValidate();
	if (undefined == validate) {
		$
				.ajax({
					url : 'accountaction!ajaxsaveaddress',
					data : {
						"consigneeAddressVO.name" : encodeURI($("#J_AddrName")
								.val()),
						"consigneeAddressVO.province" : $("#J_AddrProvince")
								.val(),
						"consigneeAddressVO.city" : $("#J_AddrCity").val(),
						"consigneeAddressVO.area" : $("#J_AddrArea").val(),
						"consigneeAddressVO.town" : $("#J_AddrTown").val(),
						"consigneeAddressVO.tel" : $("#J_Mobile").val(),
						"consigneeAddressVO.postcode" : $("#J_AddrCode").val(),
						"consigneeAddressVO.addr" : encodeURI($("#J_AddrText")
								.val()),
						"consigneeAddressVO.isd" : $("#J_AddrDefault").is(
								":checked") ? "on" : ""
					},
					dataType : 'json',
					timeout : 30000,
					error : function(xmlHttpRequest, error) {
						if (error == "parsererror") {
							alert("用户帐号异常，请重新登录");
							var enurl = escape(shangpinurl+"accountaction!applogin");
							window.location.href = "<%=path%>/mobileshangpin/accountaction!loginui?callback="
									+ enurl;
						} else {
							alert("您的网络异常");
						}
						return;
					},
					success : function(data) {
						if (data.code == 0) {
							var obj = data.content.list[0];

							var appCon = '<p class="addr_block" id="addr'
									+ obj.id
									+ '" onclick="confirmAddr(this)" addrId="'
									+ obj.id + '" addrName="'+ obj.name+ '" addrTel="'+ obj.tel+ '" cod="'+obj.cod + '" addr="' + obj.provname
									+ obj.cityname + obj.areaname + obj.addr
									+ '">' + '<span class="markCount"><i>'
									+ obj.name + ' &nbsp;&nbsp; ' + obj.tel
									+ '</i>' + obj.provname + obj.cityname
									+ obj.areaname + obj.addr + '</span></p>';
							// 追加内容
							var listp = $("#listAddr>.paymet_block").children();
							listp.first().before(appCon);
							// 切换试图
							confirmAddr($(appCon));
							// 地址只能添加10个
							if ($("#listAddr>.paymet_block").children("p")
									.size() >= 10) {
								$("#addAddrButton").hide();
							}
							//清空地址表单
							cleanAddr("J");
							jShare("新增收货成功!", "", "");
						} else {
							jShare(data.msg, "", "");
						}
					}
				});
	} else {
		return validate;
	}
}
function cleanAddr(falg){
	$("#"+falg+"_AddrName").val("");
	$("#"+falg+"_Mobile").val("");
	$("#"+falg+"_AddrText").val("");
	$("#"+falg+"_AddrCode").val("");
	$("#"+falg+"_select_area").text("省市区");
	$("#"+falg+"_AddrProvince").val("");
	$("#"+falg+"_AddrCity").val("");
	$("#"+falg+"_AddrArea").val("");
	$("#"+falg+"_AddrTown").val("");
	if(falg=='J'){
		$("#"+falg+"_AddrDefault").removeAttr("checked");
	}
}
$(function(){
	//选择配送方式
	$("#select_time").click(function(){
		$("#area_overlay").height($(document).height());
		$("#area_overlay, #area_layer").show();
		addrTxt = "";
		return false;
	});
	
	$("#area_layer dd  a").click(function(){
		
		var that = $(this),
			obj = $("#area_layer dd  a"),
			timeTxt = that.text();
		
		obj.removeClass("cur");	
		that.addClass("cur");
		
		setTimeout(function(){
			$("#area_overlay, #area_layer").hide();
			$("#select_time").text(timeTxt);
			
			//设置到订单form中
			$("#deliveryId").val(that.attr("id"));
		}, 300);
		return false;
		
	});
	
});