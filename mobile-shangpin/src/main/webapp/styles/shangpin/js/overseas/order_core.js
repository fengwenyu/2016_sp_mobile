/**
 * @author zhanghongwei
 */
$(function() {
	// 点击收货地址转向收货地址列表 或者新增收货地址
	$("#to_add_address").click(function(e) {
		var hasAddr=$("#address_list_container .paymet_block").has("p").length;
		if($("#addressId").val()=="" &&hasAddr==0){
			backAddressAdd();
		}else{
			backAddress();
		}
	});
	// 点击收货地址转向收货地址列表 或者新增收货地址
	$("#to_list_address").click(function(e) {
		var hasAddr=$("#address_list_container .paymet_block").has("p").length;
		if($("#addressId").val()=="" &&hasAddr==0){
			backAddressAdd();
		}else{
			backAddress();
		}
	});

	// 点击收货地址列表返回按钮返回到主订单页
	$("#address_list_container .backBtn").click(function(e) {
		backMain();
	});

	// 点击新增收货地址按钮进入新增收货地址
	$("#address_to_add").click(function(e) {
		backAddressAdd();
	});

	// 点击新增收货地址返回按钮进入收货地址列表或者主提交订单页
	$("#address_add_container .backBtn").click(function(e) {
		if($("#addressId").val()==""){
			backMain();
		}else{
			backAddress();
		}
	});

	/*// 点击编辑收货地址按钮进入编辑收货地址
	$("#address_list_container .editBtn").click(function(e) {
		backAddressEdit();
	});*/

	// 点击编辑收货地址页返回按钮返回到收货地址列表页
	$("#address_edit_container .backBtn").click(function(e) {
		backAddress();
	});

	// 点击进入发票编辑
	$("#to_invoice").click(function() {
		backInvoice();
	});

	// 点击发票地址的返回按钮回到主提交页面
	$("#invoice_container .backBtn").click(function() {
		backMain();
	});

	// 点击其他地址进入，进入发票地址列表或者新增发票地址
	$("#to_invoice_address").click(function() {
		//取消与收货地址相同样式及值
		$(".select-address").removeClass("cur");
		backInvoiceAddress();
	});

	// 点击发票地址列表返回按钮返回到发票信息页面
	$("#invoice_address_container .backBtn").click(function() {
		backInvoice();
	});

	// 点击新增发票地址进入，新增发票地址
	$("#invoice_address_to_add").click(function() {
		backInvoiceAddressAdd();
	});
	
	// 点击新增发票地址返回按钮进入发票地址列表或者发票信息页
	$("#invoice_address_add_container .backBtn").click(function(e) {
		backInvoiceAddress();
	});

	// 点击编辑收货地址按钮进入编辑收货地址
	$("#invoice_address_container .editBtn").click(function(e) {
		backInvoiceAddressEdit();
	});

	// 点击编辑发票地址页返回按钮返回到发票地址列表页
	$("#invoice_address_edit_container .backBtn").click(function(e) {
		backInvoiceAddress();
	});

	//点击某个收货地址
	$("#address_list_container .addr_block .click_addr").click(function(){
		clickAddr(this);
	});
	//点击某个发票地址
	$("#invoice_address_container .addr_block .click_addr").click(function(){
		clickInvoiceAddr(this);
	});
	//首次加载数据
	//加载用户上次使用的订单信息数据
	//
});
//绑定beforeunload事件
$(window).bind('beforeunload', function() {
	return '您的订单内容尚未提交，确定离开此页面吗？';
});

// order页面中要切换显示或隐藏的DIV id集合
var alldiv = [ "#order_head", "#order_container",
		"#address_list_container", "#address_add_container",
		"#address_edit_container", "#invoice_container",
		"#invoice_address_container", "#invoice_address_add_container",
		"#invoice_address_edit_container" ];
// 过滤div
function filterDiv(excludeDiv) {
	var constans = [];
	constans = $.grep(alldiv, function(val, key) {
		if ($.inArray(val, excludeDiv) != -1) {
			return true;
		}
	}, true);
	return constans;
}
// 页面要显示和隐藏div切换
function switchSH(hideList, showList, order) {
	if (order) {
		if (order == 'show') {
			$("body div").filter(showList).show();
			$("body div").filter(hideList).hide();
			return;
		}
	}
	$("body div").filter(hideList).hide();
	$("body div").filter(showList).show();

}
// 给定显示的div id数组 ,隐藏所有body中其他的div
function swithcSHDef(showDivArr) {
	switchSH(filterDiv(showDivArr).toString(), showDivArr.toString());
}

// 回到主提交订单页
function backMain() {
	//设置收货地址信息内容
	setAddrInfo();
	setInvoiceInfo();
	var showContainer = [ "#order_head", "#order_container" ];
	swithcSHDef(showContainer);
}
// 回到收货地址列表页
function backAddress() {
	var showContainer = [ "#address_list_container" ];
	swithcSHDef(showContainer);
	var curAddress=$("#address_list_container #addrId"+$("#addressId").val());
	curAddress.css("border","1px solid #c62026");
	 $(".addr_edit").hide();
	curAddress.siblings().css("border","1px solid #d8d8d8");
}
// 回到新增收货地址页
function backAddressAdd() {
	//判断是否已经大于10个收货地址
	var count= $("#address_list_container .paymet_block").children("p").length;
	if(count>=10){
		alert("你最多可以添加10个收货地址！");
		return false;
	}
	cleanAddr("address_add_");
	var showContainer = [ "#address_add_container" ];
	swithcSHDef(showContainer);
}
// 回到编辑收货地址页
function backAddressEdit(addrId,flag) {
	getUpdateAddr(addrId,flag);
}

// 回到编辑发票地址页
function backAddressInvoiceEdit(addrId,flag) {
	getUpdateInvoiceAddr(addrId,flag);
}

// 回到发票页面
function backInvoice() {
	setInvoice();
	var showContainer = [ "#invoice_container" ];
	swithcSHDef(showContainer);
}

// 回到发票地址列表页
function backInvoiceAddress() {
	//如果选中了
	var invoiceAddressId=$("#invoiceAddressId").val();
	if(invoiceAddressId!=""){
		var curAddress=$("#invoice_address_container #addrId"+invoiceAddressId);
		curAddress.css("border","1px solid #c62026");
		curAddress.siblings().css("border","1px solid #d8d8d8");
	}
	var showContainer = [ "#invoice_address_container" ];
	swithcSHDef(showContainer);
}
// 回到新增发票地址页
function backInvoiceAddressAdd() {
	//判断是否已经大于10个收货地址
	var count= $("#invoice_address_list_container .paymet_block").children("p").length;
	if(count>=10){
		alert("你最多可以添加10个发票地址！");
		return false;
	}
	cleanAddr("invoice_address_add_");
	var showContainer = [ "#invoice_address_add_container" ];
	swithcSHDef(showContainer);
}
// 回到编辑发票地址页
function backInvoiceAddressEdit() {
	var showContainer = [ "#invoice_address_edit_container" ];
	swithcSHDef(showContainer);
}

//设置收货地址信息内容
function setAddrInfo(){
	var addrId=$("#addressId").val();
	if(addrId==""){
		$("#showAddr").hide();
		$("#showToAddr").show();
	}else{
		var that=$("#address_list_container #addrId"+addrId+" .click_addr");
		var addrName=that.attr("addr_name");
		var addrTel = that.attr("addr_tel");
		var addr= that.attr("addr");
		var cardID=$.trim(that.attr("addr_cardID"));
		$("#showAddr em:first").text(addrName);
		//如果cardID为空不显示
		if(cardID!=""){
			$("#emcardID").text(cardID);
			$("#emcardID").show();
			$("#embr").show();
		}else{
			$("#emcardID").hide();
			$("#embr").hide();
		}
		
		$("#showAddr .phone").text(addrTel);
		$("#showAddr span").text(addr);
		$("#showAddr").show();
		$("#showToAddr").hide();
	}
}

//点击收货地址
function clickAddr(o){
	var that = $(o);
	//得到数据进行前方显示
	var addrId=that.attr("addr_id");
	$("#addressId").val(addrId);
	var area = that.parent(".addr_block").attr("area");
	var town = that.parent(".addr_block").attr("town");
	$("#third_id").val(area);
	$("#fourth_id").val(town);
	backMain();
}
//点击发票地址
function clickInvoiceAddr(o){
	var that = $(o);
	//得到数据进行前方显示
	var addrId=that.attr("addr_id");
	$("#invoiceAddressId").val(addrId);
	backInvoice();
}
//通过收货地址ID查询地址数据填充到更新数据中
function getUpdateAddr(addrId,flag){
	var url = getRootPath()+"/address/ajax/update";
	$.getJSON(url,{"addressId":addrId},function(data){
		if(data!=null){
			setEditContent(data,flag);
			var showContainer = [ "#address_edit_container" ];
			swithcSHDef(showContainer);
		}
	});
}
//通过收货地址ID查询地址数据填充到更新数据中
function getUpdateInvoiceAddr(addrId,flag){
	var url = getRootPath()+"/invoice/ajax/update";
	$.getJSON(url,{"addressId":addrId},function(data){
		if(data!=null){
			setEditContent(data,flag);
			var showContainer = [ "#invoice_address_edit_container" ];
			swithcSHDef(showContainer);
		}
	});
}

 //设置编辑内容
function setEditContent(data,flag){
	$("#" + flag + "id").val(data.id);
	$("#" + flag + "select_area").text(data.provname+" "+data.cityname+" "+data.areaname+" "+data.townname);
	$("#" + flag + "province").val(data.province);
	$("#" + flag + "city").val(data.city);
	$("#" + flag + "area").val(data.area);
	$("#" + flag + "town").val(data.town);
	$("#" + flag + "addr").val(data.addr);
	$("#" + flag + "postcode").val(data.postcode);
	$("#" + flag + "name").val(data.name);
	$("#" + flag + "tel").val(data.tel);
	$("#" + flag + "cardID").val(data.cardID);
}

//清空数据
function cleanAddr(flag){
	$("#" + flag + "id").val("");
	$("#" + flag + "select_area").text("省市区");
	$("#" + flag + "province").val("");
	$("#" + flag + "city").val("");
	$("#" + flag + "area").val("");
	$("#" + flag + "town").val("");
	$("#" + flag + "addr").val("");
	$("#" + flag + "postcode").val("");
	$("#" + flag + "name").val("");
	$("#" + flag + "tel").val("");
	$("#" + flag + "cardID").val("");
}

//设置发票地址信息
function setInvoiceInfo(){
	$("#invoiceTitle").val($("#J_invoiceName").val());
	$("#invoiceContent").val($("#J_invoiceContent").val());
	$("#to_invoice em:eq(1)").html($("#invoiceTitle").val());
	$("#to_invoice em:eq(2)").html($("#invoiceContent").val());
}

//设置发票信息
function setInvoice(){
	var invoiceAddressId=$("#invoiceAddressId").val();
	var addressId=$("#addressId").val();
	if(invoiceAddressId==""){
		//设置为与收货地址相同
		$("#invoiceAddressId").val(addressId);
		//取消与收货地址相同样式及值
		$(".select-address").removeClass("cur");
		$(".select-address").addClass("cur");
	}else if(invoiceAddressId==addressId){
		//收货地址与发票地址相同 
		$(".select-address").removeClass("cur");
		$(".select-address").addClass("cur");
	}
}