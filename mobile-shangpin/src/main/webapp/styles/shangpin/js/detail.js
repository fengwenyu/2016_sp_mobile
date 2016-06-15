$(function(){
	var path = getRootPath();
	if($('#J_m-slider').length > 0){
		$('#J_m-slider').slider({wrap:".slider-outer",wrapUl:".slider-wrap",wrapStatus:'.slider-status',isLoop:true,prev:".prev",next:".next"});
	}
	
	//选择颜色
	$(".fillColor").delegate("span","click",function(){
		$(this).addClass("cur").siblings("span").removeClass("cur");
		$("#colorVal").val($(this).attr("data-title"));
		$('#sku').val($(this).attr('value'));
		return false;
	});
	
	//选择尺码
	$(".fillSize").delegate("span","click",function(){
		$(this).addClass("cur").siblings("span").removeClass("cur");
		$("#sizeVal").val($(this).attr("data-title"));
		$('#sku').val($(this).attr('value'));
		return false;
	});
	//购物车商品数量
	$.get(path + "/cart/count",function(data){
		if(data==0 || data==null){
			$('.cart_box a i').text(null);
		}else if(data == -1){
			$('.cart_box a i').text(null);
		}else{
			$('.cart_box a i').text(data);
		}
	});
	
	$('.fillSize > span').each(function(){
		var s = $(this).attr('id');
		if(s == "secondpro_0"){
			$(this).show();
		}else{
			$(this).hide();
		}
	});
});

//选择颜色改变sku的值
function changeSecondPro(obj){
	var secondId = "secondpro_" + obj.id.split('_')[1];
	$('.fillSize > span').each(function(){
		var s = $(this).attr('id');
		if(secondId == s){
			$(this).show();
			if($(this).attr('class')=='cur'){
				$('#sku').attr('value',$(this).attr('value'));
			}
			
		}else{
			$(this).hide();
		}
	})
}

//加入购物车
function addCart(){
	var path = getRootPath();
	var productNo = $("#productno").val();
	var topicNo = $("#topicno").val();
	var sku = $("#sku").val();
	var count = parseInt($('#totalCount').val());
	if($("#sku").val()==""){
		alert("请选择尺码");
		return;
	}
	if(count <= 0){
		alert("库存不足！");
		return;
	}
	$.post(path + "/cart/add",{"sku" : sku, "productNo" : productNo, "topicNo" : topicNo, "quantity":1},function(data,textStatus, XMLHttpRequest){
		var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
		if(sessionstatus=="timeout"){
			var locationURL = XMLHttpRequest.getResponseHeader("locationURLAll"); // 通过XMLHttpRequest取得响应头，locationURL，
			// 如果超时就处理 ，指定要跳转的页面
			window.location.href = locationURL;
		}
		if(data.code == 2){
			window.location = path + "/login?back=cart/list";
		}else if(data.code == 0){
			$('#totalCount').val(count - 1);
			alert("商品已加入购物车，点击购物车去结算");
			$.get(path + "/cart/count",function(data1){
				if(data1 == 0){
					$(".cart_box a i").text(null);
				}else{
					$(".cart_box a i").text(data1);
				}
			});
		}else if(data.code == 1){
			alert(data.msg);
		}
	},"json");
}
//立即购买
function quickSubmit(){
	var val = $("strong").text();
	var amount = $.trim(val);
	var price = amount.substring(1);
	var path = getRootPath();
	var sku = $("#sku").val();
	var productNo = $("#productno").val();
	if(sku == ''){
		alert("请选择购买的商品的颜色尺码！");
		return;
	}
	window.location.href = path + "/order/now?productNo=" + productNo + "&sku=" + sku + "&amount=" + $.trim(price);
}