// JavaScript Document

$(function(){
	//tab 切换
	var tabs = function(nav,content){
		$(nav).find("li").bind("click",function(){
			var index = $(this).index();
			$(this).addClass("cur").siblings().removeClass("cur");
			$(content).eq(index).show().siblings(content).hide();
		});
	}
	
	tabs(".tabs_menu", ".tabs_Cell");
	
	//获取焦点按钮变高亮
	$(".coupons_active input").focus(function(){
		$(this).next().addClass('coupons_submited');
	});
	//失去焦点按钮变高亮
	$(".coupons_active input").blur(function(){
		if(!$(this).val()>0){
			$(this).parent().find('.coupons_submited').removeClass('coupons_submited');
		}
		
	});
	
});

function sendActivation(){
	var path = getRootPath();
	var code = $("#coupons_code").val();
	if(code == ""){
		alert("请输入激活码");
		return
	}else{
		$.post(path + "/coupon/activation",{code : "coupon:" + code},function(data){
			if(data.code == "1"){
				alert(data.msg);
			}else{
				alert("亲，优惠券充值成功");
				window.location.href = path + "/coupon/list";
			}
		},"json");
	}
}

function getMoreList(type,cls){
	var path = getRootPath();
	var start = parseInt($("#" + cls).val());
	$.post(path + "/coupon/more",{
		start : start + 1,
		type : type
	},function(data){
		$.each(data.coupons,function(index,item){
			var $html = "";
			if(item.type == "1"){
			    $html = "<li class='cash'><h4><img src='"+ path +"/styles/shangpin/images/order/cash_coupon_angle.png' width='69' height='145'/></h4>" +
			    		"<input type='hidden' name='couponId' id='couponId' value='" + item.couponno + "'/>" +
			    		"<div class='cash'><i id='coupon_amount'>&yen;" + item.amount + "</i>" +
			    		"<em>" + item.name +"</em>" +
			    		"<span><strong style='overflow:hidden;height:20px;'>" + item.expirydate +"</strong><span style='height:57px;overflow:hidden;font-size: 10px;'>" +
			    		item.rule +" </span></span>"  +
			    		"</div>" +
			    		"<p><img src='" + path + "/styles/shangpin/images/order/cash_coupon.png' width='69' height='145'/></p>" + 
			    		"<b id='coupon_selected' class='coupons_select'></b>" +
			    		"</li>";
			}else if(item.type == "0"){
				$html = "<li class='cash'><h4><img src='"+ path +"/styles/shangpin/images/order/coupon_angle.png' width='69' height='145'/></h4>" +
			    		"<input type='hidden' name='couponId' id='couponId' value='" + item.couponno + "'/>" +
			    		"<div class='cash'><i id='coupon_amount'>&yen;" + item.amount + "</i>" +
			    		"<em>" + item.name +"</em>" +
			    		"<span><strong style='overflow:hidden;height:20px;'>" + item.expirydate +"</strong><span style='height:57px;overflow:hidden;font-size: 10px;'>" +
			    		
			    		item.rule +" </span></span>" +
			    		"</div>" +
			    		"<p><img src='" + path + "/styles/shangpin/images/order/coupon.png' width='69' height='145'/></p>" + 
			    		"<b id='coupon_selected' class='coupons_select'></b>" +
			    		"</li>";
			}
			$("." + cls).append($html);
		});
		if(!data.hasMore){
			$("#haveMore").remove();
		}
		$("#" + cls).val(data.start);
	},"json");
}