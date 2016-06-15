$(function(){
	//选择颜色
	$(".fillColor").delegate("span","click",function(){
		$(this).addClass("cur").siblings("span").removeClass("cur");
		$("#colorVal").val($(this).attr("data-title"));
		return false;
	});
	//选择尺码
	$(".fillSize").delegate("span","click",function(){
		$(this).addClass("cur").siblings("span").removeClass("cur");
		$("#sizeVal").val($(this).attr("data-title"));
		return false;
	});
});