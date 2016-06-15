$(function(){

	//领过弹出层
	function popBox(){
		//$('#popup_message').html("领取失败，请稍后重试");
		//$('#popup_overlay').show();
		$('#popup_container').show();
		$(".submit_btn").html("领取成功 去买买买").addClass("curr");
		setTimeout(function(){
			//$('#popup_overlay').hide();
			$('#popup_container').hide();
		},2000);
	}

	//领光
	function popBoxGuang(){
		$('#popup_message').html("优惠券已领光");
		//$('#popup_overlay').show();
		$('#popup_container').show();
		//$(".submit_btn").html("领取成功 去买买买").addClass("curr");
		setTimeout(function(){
			//$('#popup_overlay').hide();
			$('#popup_container').hide();
		},2000);
	}

	var url = window.location.href;
	//截取参数
	var _s = url.lastIndexOf("/");
	var flag = url.substr(_s+1, 1);

	if(flag == "6") {
		$(".submit_btn").html("领取150元现金红包");
	}else{

	}



	var isSucess = false;	//是否领取成功
	//点击按钮
	$(".submit_btn").click(function(){

		if(isSucess){
			if(flag == "6") {
				window.location = "http://m.shangpin.com/brand/product/list?brandNo=B1885&postArea=0&WWWWWWWWW";
			}else{
				window.location = "http://m.shangpin.com/meet/699";
			}
			return;
		}

		$.ajax({
			type: "GET",
			url: getRootPath()+"/isLogin?"+new Date(),
			success: function(data){
				//未登录
				if(data==undefined || data=="" || data == "0" ){
					var isApp=$("#_isapp").val();
					//var isApp = "6" != flag;
					var urlEncode = encodeURIComponent(url);
					if(isApp){
						window.location= getRootPath() + "/accountaction!loginui?callback=" + urlEncode;
					}else{
						window.location= getRootPath() + "/login?back=" + urlEncode;

					}
				}
				//已登录
				if(data == "1"){

					//去领券
					$.ajax({
						type: "GET",
						url: getRootPath() + "/command/get/"+flag,
						success: function (data) {

							if("0" == data.code){

								isSucess = true;

								if(flag == "6") {
									window.location = "http://m.shangpin.com/brand/product/list?brandNo=B1885&postArea=0&WWWWWWWWW";
								}else{
									$(".submit_btn").html("领取成功 去买买买").addClass("curr");
								}


							}else{

								//返回1 就认为已经领过了   （这里是有问题的！赶时间）

								//在改 再改 再改
								if(/*"抱歉，该优惠券充值码已充值过，不能重复充值！" == data.msg ||*/ "充值次数过多，请一天后再试。" == data.msg ){
									isSucess = true;
									popBox();
								}

								else{
									popBoxGuang();

								}

							}

						}
					});

				}
			}
		});










	});
	
});