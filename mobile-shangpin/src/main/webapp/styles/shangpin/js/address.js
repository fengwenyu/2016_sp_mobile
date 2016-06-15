(function() {
	
    window.Login = function() {},
    $.extend(window.Login.prototype, {
        init: function() {},
        loginForm: function() {
            $("#J_Login").on("submit",
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
				var cardID=$("#J_cardID").val();
				if (cardID!=undefined && cardID != "" && !checkCard(cardID)) {
					alert("请输入正确的身份证号码！");
					return false;
				}
            });
        }
    });
})(jQuery);

$(function(){
	//读取错误msg
	var msg=$("#msg").val();
	if(msg !=undefined && msg!=""){
		alert(msg);
		$("#msg").val("");
		return;
	}
});
//登录表单验证
if($("#J_Login").length > 0){
	var Login = new Login();
	Login.loginForm();
}

$(function(){
	var path = getRootPath();
	//选择收货地址
	var addrTxt,thisCon,content,that,obj,title,prev,thatDl;
	$("#select_area").click(function(){
		$(".prev_btn").hide();
		$("#area_overlay").height($(document).height());
		$("#area_overlay, #area_layer").show();
		addrTxt = [];
		return false;
	});
	
	$("#area_layer").delegate("a","click",function(){
		var that = $(this),		
		prev = $(".prev_btn");
		obj = $("#area_layer dd  a");
		content = $("#area_layer dl");
		thisCon = that.closest("dl");
		title = $("#area_layer h3");
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
					$.post(path + "/address/city",{proviceId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#area_city").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}else if(thisCon.next("dl").attr("id") == "area_county"){
					$("#area_county").empty();
					$.post(path + "/address/area",{cityId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#area_county").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}else if(thisCon.next("dl").attr("id") == "area_street"){
					$("#area_street").empty();
					$.post(path + "/address/town",{areaId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#area_street").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}
				}else{
					//返回初始状态
					content.hide();
					$("#area_overlay, #area_layer").hide();
					$("#area_layer dl:first").show();
					title.html($("#area_layer dl:first").attr("title"));
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
	$(".close_btn, #area_overlay").click(function(){
		$("#area_layer, #area_overlay").hide();
		$("#area_layer h3").show().html("省份");
		$("#area_layer").find("dl").eq(0).show().siblings("dl").hide();
		$("#area_layer dd a").removeClass("cur");
	});
});

//新增收货地址
function addAddress(n){
	var path = getRootPath();
	if(n == 10){
		alert("你最多可以添加10个收货地址！");
		return;
	}else{
		window.location.href = path + "/address/add";
	}
}
//删除收货地址
function deleteAddr(addressId){
	var path = getRootPath();
	if(confirm("确定要删除此地址吗？")){
		$.get(path + "/address/del?addressId=" + addressId,function(data){
			if(data == "0"){
				//alert("删除成功！");
				window.location.href = path + "/address/list";
			}else{
				alert("删除失败！");
			}
		},"json");
	}
}

function check(){
	var province = $("#province").val();
	if(province == ""){
		alert("请填写收货地址！");
		return false;
	}
	return true;
}

