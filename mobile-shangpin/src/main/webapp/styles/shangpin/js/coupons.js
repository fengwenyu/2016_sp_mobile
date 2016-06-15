// JavaScript Document

$(function(){
	
	//tab切换事件
	var tabs = function(nav,content){
		$(nav).find("li").bind("click",function(){
			var index = $(this).index();
			$(this).addClass("cur").siblings().removeClass("cur");
			$(content).eq(index).show().siblings(content).hide();
		});
	}
	
	tabs(".tabs_menu", ".tabs_Cell");
	
	//取消订单
	$(".alOrder_info").delegate(".alOrder_cancelBtn","click",function(){
		
		jConfirm('确认取消该订单吗？','',function(result){
			if(result==true){
				
			}else{
				
			}
		});
		
		return false;
	});
	//取消订单
	
	//选择礼品卡
	$(".select_coupon li").click(function(){
		$(this).siblings("li").removeClass("cur");
		$(this).toggleClass("cur");
		return false;
	});
	
	//优惠码事件
	var codeActive = $(".code_active"),
		saleCode = $("#sale_code"),
		codeResult = $(".code_result"),
		codeActiveBtn = $("#saleCode_btn"),
		codeCancelBtn = $("#saleCacel_btn");
		
	//激活
	codeActiveBtn.click(function(){
		if(saleCode.val() != ""){
			codeActive.hide();
			codeResult.show();
		}
		return false;
	});
	//更改
	codeCancelBtn.click(function(){
		saleCode.val("");
		codeActive.show();
		codeResult.hide();
		return false;
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
			var expirydate = item.expirydate;
			var date1 = expirydate.split("至")[1].split(" ")[0];
			var date2 = expirydate.split("至")[1].split(" ")[1];
			var $html = "";
			if(item.type == "1"){
				$html = "<li><span class='cash'><i>&yen;</i>" + item.amount + "<em>" + item.rule + "</em></span>" + 
					   "<p>过期时间：<br/>" + date1 + "<br/>" + date2 + "</p></li>"
			}else if(item.type == "0"){
				$html = "<li><span class='sale'><i>&yen;</i>" + item.amount + "<em>" + item.rule + "</em></span>" + 
				       "<p>过期时间：<br/>" + date1 + "<br/>" + date2 + "</p></li>"
			}
			$("." + cls).append($html);
		});
		if(!data.hasMore){
			$("#haveMore").remove();
		}
		$("#" + cls).val(data.start);
	},"json");
}