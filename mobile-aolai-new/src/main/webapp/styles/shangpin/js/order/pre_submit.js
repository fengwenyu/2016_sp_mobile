/**
 * 显示编辑收货人信息区域
 */
function showAddress(){
	
	//隐藏其他信息
	$(".myCom").hide();
	
	//显示编辑区域
	$(".address_con").show();
	//如果收货地址有10条，则不显示新增区域
	if($("#CON_Addr_size").val()>=10){
		$("#addMyAddr").hide();
	}
	
}

/**
 * 编辑收货人区域，选择已有的地址
 */
function setAddress(v){
	$("#caname").html($(v).attr("uname"));
    $("#caaddress").html($(v).attr("addr"));
    $("#catel").html($(v).attr("tel"));
    //将收货地址ID放入隐藏域
    $("#addrid").val($(v).attr("id"));
    
    //删除选择默认地址标签
    $(".noDefault").remove();
    //显示其他信息
	$(".myCom").show();
	
	//隐藏编辑区域
	$(".address_con").hide();
}

//初始化省市区级联信息
$(function(){
	cascade($("#J_AddrProvince"),$("#J_AddrCity"),$("#J_AddrArea"),$("#J_AddrTown"));
	cascadeInv($("#INV_AddrProvince"),$("#INV_AddrCity"),$("#INV_AddrArea"),$("#INV_AddrTown"));
});

function cascade(province, city, area,town){
	//省级事件
	province.change(function() {
		if ($(this).val() == -1){
			return;
		}
		var id = $(this).val();
		//alert("省份id："+id);
		$.ajax({
			url:'city',
			data:{"provinceId":id},
			dataType:'json',
		    timeout: 30000,
		    error: function (xmlHttpRequest, error) {			            	
	            alert("您的网络异常");
	            return;
	        },
	        success:function(data){
	        	console.log(data);
	        	var obj = $.parseJSON(data),
	        	//cityList = data.cityList;
	        	city = $("#J_AddrCity");
	        	//清除之前的市级信息
	        	city.html("<option value='-1'>请选择城市</option>");
	        	$.each(data.cityList,function(index,cityList){
	        		city.append("<option value="+cityList.id+" onclick='chooseAreaAddr("+cityList.id+")'>"+cityList.name+"</option>");
	        	});
	        }
		});
			
	});
	
	// 市级事件
	city.change(function() {
		if ($(this).val() == -1){
			return;
		}
		var id = $(this).val();
		$.ajax({
			url:'area',
			data:{"cityId":id},
			dataType:'json',
		    timeout: 30000,
		    error: function (xmlHttpRequest, error) {			            	
	            alert("您的网络异常");
	            return;
	        },
	        success:function(data){
	        	console.log(data);
	        	var obj = $.parseJSON(data),
	        	//cityList = data.cityList;
	        	area = $("#J_AddrArea");
	        	//清除之前的区级信息
	        	area.html("<option value='-1'>请选择地区</option>");
	        	$.each(data.areaList,function(index,areaList){
	        		area.append("<option value="+areaList.id+" onclick='chooseTownAddr("+areaList.id+")'>"+areaList.name+"</option>");
	        	});
	        }
		});
	});
	
	// 地区事件
	area.change(function() {
		if ($(this).val() == -1){
			return;
		}
		var id = $(this).val();
		$.ajax({
			url:'town',
			data:{"areaId":id},
			dataType:'json',
		    timeout: 30000,
		    error: function (xmlHttpRequest, error) {			            	
	            alert("您的网络异常");
	        return;
	        },
	        success:function(data){
	        	console.log(data);
	        	var obj = $.parseJSON(data),
	        	//cityList = data.cityList;
	        	town = $("#J_AddrTown");
	        	//清除之前的街道级信息
	        	town.html("<option value='-1'>请选择街道</option>");
	        	$.each(data.townList,function(index,townList){
	        		town.append("<option value="+townList.id+" onclick='chooseCloseAddr("+townList.id+")'>"+townList.name+"</option>");
	        	});
	        }
		});
	});
	
}//


//=========================================================================
/**
 * 取消:取消新增收货地址，返回主页面
 */
function cancelEditAddr(){
	//显示其他信息
	$(".myCom").show();
	
	//隐藏编辑区域
	$(".address_con").hide();
}

/**
 * 确定:新增收货地址
 */
function addEditAddr(){
	//提交后台前的校验
	if($("#J_AddrName").val()==''){
		alert("姓名不能为空，请填写");
		return;
	}
	if($("#J_AddrProvince").val()=='' || $("#J_AddrProvince").val()=='-1'){
		alert("省份不能为空，请选择");
		return;
	}
	if($("#J_AddrCity").val()=='' || $("#J_AddrCity").val()=='-1'){
		alert("城市不能为空，请选择");
		return;
	}
	if($("#J_AddrArea").val()=='' || $("#J_AddrArea").val()=='-1'){
		alert("地区不能为空，请选择");
		return;
	}
	if($("#J_AddrTown").val()=='' || $("#J_AddrTown").val()=='-1'){
		alert("街道不能为空，请选择");
		return;
	}
	if($("#J_AddrMobile").val()==''){
		alert("手机号不能为空，请填写");
		return;
	}
	//手机号规则及邮编规则
	var phone =/^1[358]\d{9}$/,
	post =/^[0-9][0-9]{5}$/;
	if(!phone.test($("#J_AddrMobile").val())){
		alert("手机号格式不正确，请重新填写");
		return;
	}
	if($("#J_AddrCode").val()==''){
		alert("邮编不能为空，请填写");
		return;
	}
	if(!post.test($("#J_AddrCode").val())){
		alert("邮编格式不正确，请重新填写");
		return;
	}
	if($("#J_AddrText").val()==''){
		alert("详细地址不能为空，请填写");
		return;
	}
	
	//ajax添加收货地址
	$.ajax({
		url:getRootPath()+'/cart/add/address',
		data:{
		  "name":$("#J_AddrName").val(),
		  "province":$("#J_AddrProvince").val(),
		  "city":$("#J_AddrCity").val(),
		  "area":$("#J_AddrArea").val(),
		  "town":$("#J_AddrTown").val(),
		  "tel":$("#J_AddrMobile").val(),
		  "postcode":$("#J_AddrCode").val(),
		  "addr":$("#J_AddrText").val(),
		  "isd":$("#J_AddrDefault").is(":checked")?"on":""
		},
		dataType:'json',
	    timeout: 30000,
	    error: function (xmlHttpRequest, error) {			            	
            alert("您的网络异常");
        return;
        },
        success:function(data){
        	console.log(data);
        	var obj = $.parseJSON(data),
        	addr = data.addr;
        	if(data.result){
        		alert("添加成功。");
        		//将新增的收货地址ID放入隐藏域
        		$("#addrid").val(addr.id);
        		//组装省市区地址
        		var address = addr.provname+addr.cityname+addr.areaname+addr.townname+addr.addr;
        		//将新增收货地址放入收货人信息显示区域
        		$("#caname").html(addr.name);
        	    $("#caaddress").html(address);
        	    $("#catel").html(addr.tel);
        	    //拼接到地址列表
        	    var append = '<ul class="address_list">'
			        + '<li class="li_addrList ' + (addr.isd=='1'?'curr"':'"') + 'id="'+addr.id+'" uname="'+addr.name+'" addr="'+addr.provname+addr.cityname+addr.areaname+addr.townname+addr.addr+'" tel="'+addr.tel+'" >'
			        + '<section class="alOrder_addr addr_list" style="background:none">'
			        + '<a href="javascript:void(0);" id="'+addr.id+'" uname="'+addr.name+'" addr="'+addr.provname+addr.cityname+addr.areaname+addr.townname+addr.addr+'" tel="'+addr.tel+'" onclick="setAddress(this);window.history.go(-1);">'
			        + '<dl>'
			        + '<dt>收货人：</dt><dd>'+addr.name+'</dd>'
			        + '</dl>'
			        + '<dl>'
			        + '<dt>地　址：</dt><dd>'+addr.provname+addr.cityname+addr.areaname+addr.townname+addr.addr+'</dd>'
			        + '</dl>'
			        + '<dl>'
			        + '<dt>手机号：</dt><dd>'+addr.tel+'</dd>'
			        + '</dl>'
			        + '</a>'
			        + '</section>'
			        + '</li>'
			        + '</ul>';

			      $("#addrList").append(append);
			      //新增成功后清除输入框内容
			      $("#J_AddrName").val("");
			      $("#J_AddrProvince").val("请选择省份");
			      $("#J_AddrCity").val("请选择城市");
			      $("#J_AddrArea").val("请选择地区");
			      $("#J_AddrTown").val("请选择街道");
			      $("#J_AddrMobile").val("");
			      $("#J_AddrCode").val("");
			      $("#J_AddrText").val("");
			      //如果收货地址列表个数等于10，则隐藏新增区域
			      //alert("地址条数："+data.size);
			      if(data.size>=10){
			    	  $("#addMyAddr").hide();
			      }
			      
        		cancelEditAddr();//返回主页面
        	}else{
        		alert("添加失败，请稍后重试。");
        	}
        }
	});
}

//========================================================================

/**
 * 改变配送方式
 */
function changeDelivery(v){
	//移除之前所选配送方式的样式
	$("ul[class='alOrder_select'] li").attr("class","deliverytag");
	//当前选择配送方式添加样式
	$(v).addClass("deliverytag cur");
	//将配送方式放入隐藏域
	$("#express").val($(v).attr("data-title"));
}


//====================================优惠码start========================================

/**
 * 显示使用优惠码区域
 */
function showCouponcode(){
	//显示优惠码区域
	$("._couponcode").show();
	//隐藏其他区域
	$(".myCom").hide();
}

/**
 * 隐藏使用优惠码区域
 */
function hideCouponcode(){
	//显示其他区域
	$(".myCom").show();
	//隐藏优惠码区域
	$("._couponcode").hide();
}

/**
 * 使用优惠码
 */
function getCouponCodeInfo(){
	//已经使用优惠码，取消使用
	if($("#select_couponcode").attr("useflag")==1){
		$("#couponNum").val("");
		$("#couponNum").removeAttr("readonly");
		$("#select_couponcode").val("使用");
		//标识：0未使用 ，1已使用
		$("#select_couponcode").attr("useflag","0");
		//提交订单主页文字改变
		$("#isCouponcode").text("未使用"); 
		//将隐藏域优惠码类型和值置为空
		$("#couponflag").val("");
		$("#coupon").val("");
		//优惠金额隐藏
		$("#discount").hide();
		//修改应付金额为未优惠前
		$("#factPay").html("&yen;"+$("#CartOrder").val());
		//如果单品大于499则取消运费
		if($("#CartOrder").val()>=499){
			$("#s_carriage").hide();
		}
		
		return;
	}
	//不能为空
	var couponNum = $("#couponNum").val();
	if(couponNum == '' || couponNum == null){
		alert("请输入优惠码");
		return;
	}
	$.ajax({
		url:getRootPath()+'/cart/submit/update',
		data:{
		  "couponFlag":"2",				 //优惠种类：1 优惠劵 ,2优惠码
		  "coupon":$("#couponNum").val(),//优惠码
		  "addressId":$("#addrid").val(),//收货地址id
		  "buyGids":$("#buysIds").val(), //购物车商品id
		},
		dataType:'json',
	    timeout: 30000,
	    error: function (xmlHttpRequest, error) {			            	
            alert("您的网络异常");
        return;
        },
        success:function(data){
        	console.log(data);
        	var code = data.msgcode,
        	    msg = data.msg,
        	    couponVO = data.couponVO;
        	if(code == '0'){
        		alert("使用优惠码成功。");
        		//将优惠类型放入隐藏域
        		$("#couponflag").val("2");
        		$("#coupon").val(couponNum);
        		
        		//优惠金额
        		$("#discount").show();
        		$("#discountamount").html("&yen;"+couponVO.discountamount);//优惠金额
        		
        		//是否有运费，有则显示
        		var carr = couponVO.carriage;
        		if(carr*1>0){
        			$("#s_carriage").show();
            		$("#carriage").html("&yen;"+carr);//运费
        		}
        		
        		//应付金额
        		$("#factPay").html("&yen;"+couponVO.payamount);
        		//应付金额放入隐藏域
        		$("#famount").val(couponVO.payamount);
        		//清空优惠码输入框
        		$("#couponNum").val("");
        		$("#isCouponcode").html("已优惠：&yen;"+couponVO.discountamount); 
        		hideCouponcode();//返回主页面
        		
        		//改变文案字样并禁用优惠码输入框
        		$("#select_couponcode").val("取消使用");
        		$("#couponNum").attr("readonly","readonly");
        		//优惠码标识：0未使用 ，1已使用
        		$("#select_couponcode").attr("useflag","1");
        		
        		//优惠劵和优惠码不可同时使用，判断已经使用优惠劵则置为空 
        		$("#iscoupon").text("未使用"); //主页优惠劵字样
        		//取消已经使用的优惠劵
        		$("div[class='alOrder_couponList'] a").text("使用");
        		//将优惠码劵识设为未使用
        		$("div[class='alOrder_couponList'] a").attr("isUse","0");
        	}else{
        		alert(msg);
        		$("#isCouponcode").text("未使用"); //主页优惠码字样
        	}
        }
	});
	
}

//====================================优惠码end========================================

//====================================优惠券start========================================

/**
 * 显示优惠劵使用区域
 */
function showCoupon(){
	//显示优惠劵区域
	$(".alOrder_couponList").show();
	//隐藏其他区域
	$(".myCom").hide();
}

/**
 * 隐藏优惠劵区域
 */
function hideCoupon(){
	//隐藏优惠劵区域
	$(".alOrder_couponList").hide();
	//显示其他区域
	$(".myCom").show();
}

/**
 * 使用优惠劵
 * @param v 当前对象
 */
function selectCoupon(v){
	//已经使用优惠劵,现在取消使用
	if($(v).attr("isUse")==1){
		$(v).text("使用");
		//标识：0未使用 ，1已使用
		$(v).attr("isUse","0");
		//提交订单主页文字改变
		$("#iscoupon").text("未使用"); 
		//将隐藏域优惠码类型和值置为空
		$("#couponflag").val("");
		$("#coupon").val("");
		//优惠金额隐藏
		$("#discount").hide();
		//修改应付金额为未优惠前
		$("#factPay").html("&yen;"+$("#CartOrder").val());
		//如果单品大于499则取消运费
		if($("#CartOrder").val()>=499){
			$("#s_carriage").hide();
		}
		return;
	}
	//将优惠码劵识设为未使用
	$("div[class='alOrder_couponList'] a").attr("isUse","0");
	//取消已经使用的优惠劵
	$("div[class='alOrder_couponList'] a").text("使用");
	
	$.ajax({
		url:getRootPath()+'/cart/submit/update',
		data:{
		  "couponFlag":"1",				 //优惠种类：1 优惠劵 ,2优惠码
		  "coupon":$(v).attr("couponNo"),//优惠码
		  "addressId":$("#addrid").val(),//收货地址id
		  "buyGids":$("#buysIds").val(), //购物车商品id
		},
		dataType:'json',
	    timeout: 30000,
	    error: function (xmlHttpRequest, error) {			            	
            alert("您的网络异常");
        return;
        },
        success:function(data){
        	console.log(data);
        	var code = data.msgcode,
        	    msg = data.msg,
        	    couponVO = data.couponVO;
        	if(code == '0'){
        		alert("使用优惠劵成功。");
        		//将优惠类型放入隐藏域
        		$("#couponflag").val("1");
        		$("#coupon").val($(v).attr("couponNo"));
        		
        		//优惠金额
        		$("#discount").show();
        		$("#discountamount").html("&yen;"+couponVO.discountamount);//优惠金额
        		
        		//是否有运费，有则显示
        		var carr = couponVO.carriage;
        		if(carr*1>0){
        			$("#s_carriage").show();
            		$("#carriage").html("&yen;"+carr);//运费
        		}
        		
        		//应付金额
        		$("#factPay").html("&yen;"+couponVO.payamount);
        		//应付金额放入隐藏域
        		$("#famount").val(couponVO.payamount);
        		//主页改变优惠劵文案
        		$("#iscoupon").html("已优惠：&yen;"+couponVO.discountamount); 
        		hideCoupon();//返回主页面
        		
        		//改变已经使用的优惠劵文案字样
        		$(v).text("取消");
        		//优惠劵标识：0未使用 ，1已使用
        		$(v).attr("isUse","1");
        		
        		//优惠劵和优惠码不可同时使用，判断已经使用优惠码则置为空 
        		$("#isCouponcode").text("未使用"); //主页优惠码字样
        		//取消已经使用的优惠码
        		$("#couponNum").removeAttr("readonly");
        		$("#select_couponcode").val("使用");
        		//将优惠码标识设为未使用
        		$("#select_couponcode").attr("useflag","0");
        	}else{
        		alert(msg);
        		$("#iscoupon").text("未使用"); 
        	}
        }
	});
}//使用优惠劵结束

