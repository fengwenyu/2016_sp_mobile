var path=getRootPath();
function findByPhone(){
	$(".content_size").hide();
	$("#fashionBtn").hide();
	$("#sign_form").hide();
	$.ajax({
		url : path + "/fashionrun/findByPhone",
		data : {
			phone:$("#_phone").val()
		},
		dataType : "json",
		type:"POST",
		success : function(data) {
			
			if(data.code=="0"){
				$("#orderId").val(data.fashionOrder.orderId);
				$(".content_size").show();
				$("#name").text(data.fashionOrder.name);
				$("#phone").text(data.fashionOrder.phone);
				$("#pid").text(data.fashionOrder.pid);
				var status;
				var _status=data.fashionOrder.status;
				if(_status=="002"){
					
					$("#fashionBtn").show();
					status="已支付";
				}
				if(_status=="003"){
					status="已申请领取";
				}
				if(_status=="004"){
					status="已确认";
				}
				if(_status=="005"){
					status="已发货";
				}
				$("#status").text(status);
				if(data.fashionOrder.size!=null){
					$("#size").text(data.fashionOrder.size);
				}
				if(data.fashionOrder.addr!=null){
					$("#addr").text(data.fashionOrder.addr);
				}
				if(data.fashionOrder.province!=null||data.fashionOrder.city!=null||data.fashionOrder.area!=null||data.fashionOrder.town!=null){
					$("#place").text(data.fashionOrder.province+data.fashionOrder.city+data.fashionOrder.area+data.fashionOrder.town);
				}
			
			
			}
			if(data.code=="-1"){
				$("#sign_form").show();
				return jShare('未查询到报名信息！',"",""),
	        	!1;
			}
		
		}
	});
}

function findByPid(){
	$(".content_size").hide();
	$("#fashionBtn").hide();
	$("#sign_form").hide();
	$.ajax({
		url : path + "/fashionrun/findByPid",
		data : {
			pid:$("#_pid").val()
		},
		dataType : "json",
		type:"POST",
		success : function(data) {
			
			if(data.code=="0"){
				$("#orderId").val(data.fashionOrder.orderId);
				$(".content_size").show();
				$("#name").text(data.fashionOrder.name);
				$("#phone").text(data.fashionOrder.phone);
				$("#pid").text(data.fashionOrder.pid);
				var status;
				var _status=data.fashionOrder.status;
				if(_status=="002"){
					
					$("#fashionBtn").show();
					status="已支付";
				}
				if(_status=="003"){
					status="已申请领取";
				}
				if(_status=="004"){
					status="已确认";
				}
				if(_status=="005"){
					status="已发货";
				}
				$("#status").text(status);
				if(data.fashionOrder.size!=null){
					$("#size").text(data.fashionOrder.size);
				}
				if(data.fashionOrder.addr!=null){
					$("#addr").text(data.fashionOrder.addr);
				}
				if(data.fashionOrder.province!=null||data.fashionOrder.city!=null||data.fashionOrder.area!=null||data.fashionOrder.town!=null){
					$("#place").text(data.fashionOrder.province+data.fashionOrder.city+data.fashionOrder.area+data.fashionOrder.town);
				}
			
			
			}
			if(data.code=="-1"){
				$("#sign_form").show();
				return jShare('未查询到报名信息！',"",""),
	        	!1;
			}
		
		}
	});
}

function sendPhoneCode(){
	var phone = $('#_phone').val();
	if(phone == "" || phone == null){
		return jShare('请输入手机号！',"",""),
    	!1;
	}
	$.post(path+"/fashionrun/sendPhoneCode",{phone:phone},function(data){
		console.log(data);
		if(data.code=="0"){
			$("#phoneCode").text(data.verifycode)
		}
	},"json");
}

function confirm(){
	var orderId = $('#orderId').val();
	$.post(path+"/fashionrun/deliver",{orderId:orderId},function(data){
		console.log(data);
		if(data.code=="0"){
			return jShare('领取成功！',"",""),
        	!1;
		}
		if(data.code=="2"){
			return jShare('已经领取成功！',"",""),
        	!1;
		}
	},"json");
}
function load(){
	window.location.reload();
}