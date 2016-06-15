(function() {
    window.Login = function() {},
    $.extend(window.Login.prototype, {
        init: function() {},
        loginForm: function() {
            $("#order_address_form").on("submit",
            function(e) {
				var re = /^[\u4e00-\u9fa5]$/,
					mre = /^1[358]\d{9}$/,
					post = /^[1-9][0-9]{5}$/;
				
				//收货人姓名
				if ($.trim($("#J_userName").val()) == "" ){
					return jShare('请填写正确中文名称!',"",""),
					$("#J_userName").addClass("error"),
                	!1;
				}else{
					$("#J_userName").removeClass("error");
				}
				
				//收货人电话
                if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
					return jShare('请输入正确手机号码!',"",""),
					$("#J_mobileNum").addClass("error"),
                	!1;
				}else{
					$("#J_mobileNum").removeClass("error");
				}
				
				//收货人地址
				if ($.trim($("#J_addr").val()) == ""){
					return jShare('请输入详细地址!',"",""),
					$("#J_addr").addClass("error"),
                	!1;
				}else{
					$("#J_addr").removeClass("error");
				}
				
				//收货人邮编
				if ($.trim($("#J_code").val()) == "" || !post.test($("#J_code").val())){
					return jShare('请输入正确邮编!',"",""),
					$("#J_code").addClass("error"),
                	!1;
				}else{
					$("#J_code").removeClass("error");
				}
            });
        }
    });
})(jQuery);

//登录表单验证
if($("#order_address_form").length > 0){
	var Login = new Login();
	Login.loginForm();
}

$(function(){
	
	//选择收货地址
	var addrTxt,thisCon,content,that,obj,title,prev,thatDl;
	$("#select_area").click(function(){
		$(".prev_btn").hide();
		$("#address_area_overlay").height($(document).height());
		$("#address_area_overlay, #address_area_layer").show();
		addrTxt = [];
		return false;
	});
	
	$("#address_area_layer").delegate("a","click",function(){
		var that = $(this),		
		prev = $(".prev_btn");
		obj = $("#address_area_layer dd  a");
		content = $("#address_area_layer dl");
		thisCon = that.closest("dl");
		title = $("#address_area_layer h3");
		obj.removeClass("cur");
		that.addClass("cur");
		thatDl = thisCon;
		
		var dl_id = thisCon.attr("id");
		if(dl_id == "area_province"){
			$("#province").val(that.attr("id"));
			$("#provincename").val(that.text());
		}else if(dl_id == "area_city"){
			$("#city").val(that.attr("id"));
			$("#cityname").val(that.text());
		}else if(dl_id == "area_county"){
			$("#area").val(that.attr("id"));
			$("#areaname").val(that.text());
		}else if(dl_id == "area_street"){
			$("#town").val(that.attr("id"));
			$("#townname").val(that.text());
		}
		//选择结果
		addrTxt.push(that.text());
		setTimeout(function(){
			if(thisCon.next("dl").length > 0){
				prev.show(); // 返回上一级
				content.hide();
				thisCon.next("dl").show();
				title.html(thisCon.next("dl").attr("title"));
				if(thisCon.next("dl").attr("id") == "area_city"){
					$("#area_city").empty();
					$.post("../address/city",{proviceId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#area_city").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}else if(thisCon.next("dl").attr("id") == "area_county"){
					$("#area_county").empty();
					$.post("../address/area",{cityId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#area_county").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}else if(thisCon.next("dl").attr("id") == "area_street"){
					$("#area_street").empty();
					$.post("../address/town",{areaId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#area_street").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}
				}else{
					//返回初始状态
					content.hide();
					$("#address_area_overlay, #address_area_layer").hide();
					$("#address_area_layer dl:first").show();
					title.html($("#address_area_layer dl:first").attr("title"));
					var length = addrTxt.length -1;
					if(addrTxt[length] == '关闭'){
						$("#select_area").text("省市区");
					}else{
						$("#select_area").text(addrTxt.join(" "));
					}
			}
			
		}, 10);

		return false;
	});
	
	//返回上一级	
	$(".prev_btn").click(function(){	
		setTimeout(function(){				
			addrTxt.pop();			
			content.hide();
			thatDl.show();			
			thatDl.find("a").removeClass("cur");
			title.html(thatDl.attr("title"));
			thatDl = thatDl.prev("dl");
			if(title.html() == "省份"){	
				$(".prev_btn").hide();
			}	
		}, 300);
		return false;
	});
	
	//收货地址的弹层关闭
	$(".close_btn, #address_area_overlay").click(function(){
		$("#address_area_layer, #address_area_overlay").hide();
		$("#address_area_layer h3").show().html("省份");
		$("#address_area_layer").find("dl").eq(0).show().siblings("dl").hide();
		$("#address_area_layer dd a").removeClass("cur");
	});
});

