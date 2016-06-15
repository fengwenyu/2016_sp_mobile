/**
 * 省市区街道级联选择
 */
$(function(){  
	$("#select_area").click(function(){
		alert(111111);
		$("#area_overlay").height($(document).height());
		$("#area_overlay, #area_layer").show();
		var obj = $("#area_layer dd  a");
		obj.removeClass("cur");
		return false;
	});
});
function getContextPath() {
	var contextPath = document.location.pathname;
	var index = contextPath.substr(1).indexOf("/");
	contextPath = contextPath.substr(0, index + 1);
	delete index;
	return contextPath;
}
var addrTxt = "";
// 级联市
function chooseCity(id) {
	addrTxt="";
	var addr = $("dl[id='area_province'] dd a[id='" + id + "']").text();
	//样式
	$("dl[id='area_province'] dd a[id='" + id + "']").addClass("cur");
	// 将省市信息放入隐藏域
	$("#shengId").val(id);
	$("#shengName").val($.trim(addr));
	var title = $("#area_layer h3");// 省份
	var tit = title.text("城市");// 改变标题为城市
	var city = $("#area_city");
	var province = $("#area_province");
	
	setTimeout(function(){
		province.hide();
		city.show();
	}, 100);
//	var that = $(this);
//	that.addClass("cur");
	addrTxt += "" + addr;
	// alert(addrTxt);
	$.post(getContextPath()+'/accountaction!city', {
		"id" : id
	}, function(data) {
		$.each(data.content,
				function(index, content) {
					city.append("<dd><a id=" + content.id
							+ " onclick='chooseCounty(" + content.id
							+ ")' href='#'>" + content.name + "</a></dd>");
				});
	}, "json");
}
// 级联地区
function chooseCounty(id) {
	var addr = $("dl[id='area_city'] dd a[id='" + id + "']").text();
	$("dl[id='area_city'] dd a[id='" + id + "']").addClass("cur");
	// 将省市信息放入隐藏域
	$("#shiId").val(id);
	$("#shiName").val($.trim(addr));
	var title = $("#area_layer h3");// 市
	var tit = title.text("地区");// 改变标题为地区
	var city = $("#area_city");
	var county = $("#area_county");
	setTimeout(function(){
		city.hide();
		county.show();
	}, 100);
	addrTxt += " " + addr;
	$.post(getContextPath()+'/accountaction!area', {
		"id" : id
	}, function(data) {

		$.each(data.content,
				function(index, content) {
					county.append("<dd><a id=" + content.id
							+ " onclick='chooseStreet(" + content.id
							+ ")' href='#'>" + content.name + "</a></dd>");
				});
	}, "json");
}
// 级联街道
function chooseStreet(id) {
	var addr = $("dl[id='area_county'] dd a[id='" + id + "']").text();
	$("dl[id='area_county'] dd a[id='" + id + "']").addClass("cur");
	// 将省市信息放入隐藏域
	$("#quId").val(id);
	$("#quName").val($.trim(addr));
	var title = $("#area_layer h3");// 地区
	var tit = title.text("街道");// 改变标题为街道
	var street = $("#area_street");
	var county = $("#area_county");
	
	setTimeout(function(){
		county.hide();
		street.show();
	}, 100);
	addrTxt += " " + addr;

	$.post(getContextPath()+'/accountaction!town', {
		"id" : id
	}, function(data) {
		/*
		 * //如果街道信息为空 if(data.content==null || data.content==""){
		 * //alert("街道信息为空"); $("#select_area").text(addrTxt); $("#area_overlay,
		 * #area_layer").hide(); } //alert(data.content);
		 */

		$.each(data.content, function(index, content) {
			street.append("<dd><a id=" + content.id
					+ " href='#' onclick='closes(" + content.id + ")'>"
					+ content.name + "</a></dd>");
		});
	}, "json");

}

function closes(id) {
	var addr = $("dl[id='area_street'] dd a[id='" + id + "']").text();
	$("dl[id='area_street'] dd a[id='" + id + "']").addClass("cur");
	// 将省市信息放入隐藏域
	$("#zhenId").val(id);
	$("#zhenName").val($.trim(addr));
	addrTxt += " " + addr;
	// 将省市区信息放入隐藏域
	$("#ssq").val($.trim(addrTxt));

	$("#select_area").text(addrTxt);
	
	setTimeout(function(){
		$("#area_overlay, #area_layer").hide(); 
	}, 100);
	//将街道框隐藏
	$("#area_street").attr("style","none");
	//显示省级信息
	setTimeout(function(){
		$("#area_layer dl:first").show();
		//将标题还原为初始
		var title = $("#area_layer h3");
		var tit = title.text($("#area_layer dl:first").attr("title"));
	}, 300);
	
	//清除之前dl的关联省市区信息
	$("#area_city").text("");
	$("#area_county").text("");
	$("#area_street").text("");
}
