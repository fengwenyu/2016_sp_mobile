
/**
 * 显示发票信息操作区域
 */
function showInvoice(){
	//隐藏其他信息
	$(".myCom").hide();
	//显示发票信息
	$("#invoice").show();
}

/**
 * 是否开发票
 */
function invoiceWant(flag){
	if(flag == 'N'){//不要发票，隐藏
		$(".invoice_content").hide();
		//改变样式
		$("#invoice_close_a").addClass("cur");
		$("#invoice_open_a").removeClass("cur");
		//放入标示隐藏域
		$("#invFlag").val("0");
	}
	if(flag == 'Y'){//要发票，显示
		$(".invoice_content").show();
		//改变样式
		$("#invoice_open_a").addClass("cur");
		$("#invoice_close_a").removeClass("cur");
		//放入标示隐藏域
		$("#invFlag").val("1");
		//主页改变发票文案
		//$("#isInvoice").text("开发票");
	}
}

/**
 * 发票种类：单位/个人
 */
function invoiceKind(flag){
	if(flag == '0'){//单位
		//改变样式
		$("#invoice_org_a").addClass("cur");
		$("#invoice_person_a").removeClass("cur");
		//放入标示隐藏域
		$("#invTit").val("1");
	}
	if(flag == '1'){//个人
		//改变样式
		$("#invoice_person_a").addClass("cur");
		$("#invoice_org_a").removeClass("cur");
		//放入隐藏域
		$("#invTit").val("0");
	}
}

/**
 * 发票内容：
 */
function invoiceContent(flag){
	//清除之前发票内容样式
	$("dd[id='invoiceCon'] a").removeClass("cur");
	$("#"+flag).addClass("cur");
	
	//将发票抬头放入放入隐藏域
	//$("#invoicetitle").val($("#invoice_org").val());
	//将发票内容放入标示隐藏域
	$("#invCon").val($("#"+flag).attr("data-title"));
	
}

/**
 * 发票邮至：与收获地址相同/其他地址
 */
function invoiceDest(flag){
	if(flag == '0'){//与收货地址相同
		$("#otherAddr").hide();
		$(".invoice_addrForm").hide();
		//改变样式
		$("#destY").addClass("cur");
		$("#destN").removeClass("cur");
		//放入标示隐藏域
		$("#invDesFlag").val("0");//与收获地址相同
		cancelAdd();//新增发票地址区域隐藏
	}
	if(flag == '1'){//其他地址
		//$(".invoice_addrForm").css("display","");
		//$(".invoice_addrForm add_cont").show();
		$(".invoice_addrForm").show();
		$("#invaddrList").show();
		$("#otherAddr").hide();
		//改变样式
		$("#destN").addClass("cur");
		$("#destY").removeClass("cur");
		//放入标示隐藏域
		$("#invDesFlag").val("1");//其他地址
		//如果发票地址数量等于10，则隐藏新增按钮
		if($("#INV_Addr_size").val()==10){
			$("#addInvBut").hide();
		}
	}
}

/**
 * 从已有发票列表中选择发票地址
 * @param id
 */
function setInvAddress(id){
	//去除其他未选择的地址样式
	$(".invaddr_list_li").removeClass();
	//给选中的地址添加样式
	$("#"+id).addClass("invaddr_list_li curr");
	//将选中的发票地址id放入标示隐藏域
	$("#invAddressId").val(id);
	
}

/**
 * 取消:添加发票地址区域，返回主页面
 */
function cancelInvoice(){
	//显示其他信息
	$(".myCom").show();
	//隐藏发票信息
	$("#invoice").hide();
}

//新增发票地址区域显示
function showAddInv(){
	//隐藏发票地址列表及按钮
	$("#invaddrList").hide();
	$("#chooseInvButton").hide();//按钮隐藏
	//显示新增发票地址区域及按钮
	$("#otherAddr").show();
	$("#addInvButton").show();//按钮显示
	//隐藏新增按钮
	$("#addInvBut").hide();
	
}

//新增发票地址区域隐藏
function cancelAdd(){
	//显示发票地址列表及按钮
	$("#invaddrList").show();
	$("#chooseInvButton").show();//按钮显示
	//显示新增按钮
	$("#addInvBut").show();
	//隐藏新增发票地址区域及按钮
	$("#otherAddr").hide();
	$("#addInvButton").hide();//按钮隐藏
}

//添加新的发票地址
function addNewInv(){
	//提交后台前的校验
	if($("#INV_AddrName").val()==''){
		alert("姓名不能为空，请填写");
		return;
	}
	if($("#INV_AddrProvince").val()=='' || $("#INV_AddrProvince").val()=='-1'){
		alert("省份不能为空，请选择");
		return;
	}
	if($("#INV_AddrCity").val()=='' || $("#INV_AddrCity").val()=='-1'){
		alert("城市不能为空，请选择");
		return;
	}
	if($("#INV_AddrArea").val()=='' || $("#INV_AddrArea").val()=='-1'){
		alert("地区不能为空，请选择");
		return;
	}
	if($("#INV_AddrTown").val()=='' || $("#INV_AddrTown").val()=='-1'){
		alert("街道不能为空，请选择");
		return;
	}
	if($("#INV_AddrMobile").val()==''){
		alert("手机号不能为空，请填写");
		return;
	}
	//手机号规则及邮编规则
	var phone =/^1[358]\d{9}$/,
	post =/^[0-9][0-9]{5}$/;
	if(!phone.test($("#INV_AddrMobile").val())){
		alert("手机号格式不正确，请重新填写");
		return;
	}
	if($("#INV_AddrCode").val()==''){
		alert("邮编不能为空，请填写");
		return;
	}
	if(!post.test($("#INV_AddrCode").val())){
		alert("邮编格式不正确，请重新填写");
		return;
	}
	if($("#INV_AddrText").val()==''){
		alert("详细地址不能为空，请填写");
		return;
	}
	
	
	if(confirm("确定增加此地址吗？")){
		//其他地址，但发票地址列表为空
		$.ajax({
			url:getRootPath()+'/cart/add/invoice',
			data:{
			  "consigneeName":$("#INV_AddrName").val(),
			  "province":$("#INV_AddrProvince").val(),
			  "city":$("#INV_AddrCity").val(),
			  "area":$("#INV_AddrArea").val(),
			  "town":$("#INV_AddrTown").val(),
			  "tel":$("#INV_AddrMobile").val(),
			  "postcode":$("#INV_AddrCode").val(),
			  "address":$("#INV_AddrText").val(),
			},
			dataType:'json',
		    timeout: 30000,
		    error: function (xmlHttpRequest, error) {			            	
	            alert("您的网络异常");
	            return;
	        },
	        success:function(data){
	        	console.log(data);
	        	//var obj = $.parseJSON(data),
	        	//cityList = data.cityList;
	        	var obj = $.parseJSON(data),
	        	addr = data.addr;
	        	if(data.result){
	        		alert("添加发票地址成功。");
	        		//将发票地址id放入隐藏域
	        		$("#invoiceaddrid").val(addr.id);
	        		//主页改变发票文案
	        		$("#isInvoice").html("开发票");
	        		invCommon();
	        		cancelInvoice();//返回主页面
	        		//拼接列表
	        		var append = '<ul class="invaddr_list">'
  	    			        + '<li class="invaddr_list_li" id="'+addr.id+'" >'
  	    			        + '<section class="alOrder_invaddr" style="background:none">'
  	    			        + '<a href="javascript:void(0);" onclick="setInvAddress('+addr.id+');">'
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
  	    			      $("#invaddrList").append(append);
  	    			      
	        		//新增成功后清除输入框内容
				      $("#INV_AddrName").val("");
				      $("#INV_AddrProvince").val("请选择省份");
				      $("#INV_AddrCity").val("请选择城市");
				      $("#INV_AddrArea").val("请选择地区");
				      $("#INV_AddrTown").val("请选择街道");
				      $("#INV_AddrMobile").val("");
				      $("#INV_AddrCode").val("");
				      $("#INV_AddrText").val("");
				      //将发票地址数量放入隐藏域
				      $("#INV_Addr_size").val(data.size);
				     
				     //显示列表，隐藏新增区域
				     cancelAdd();
	        		
	        	}else{
	        		alert("添加失败，请稍后重试。");
	        	}
	        }
		});
	}
	
}

//选择现有发票地址和新增发票地址公用方法
function invCommon(){
	//公共部分
	var flag = $("#invFlag").val();//是否开发票
	
	if(flag==1){//开发票
		//改变提交隐藏域为开发票
		$("#invoiceflag").val("1");
		//判断是单位或者个人
		if($("#invTit").val("0")==0){//个人
			$("#invoicetype").val("0");
		}else{
			$("#invoicetype").val("1");//单位
		}
		//发票抬头放入提交隐藏域
		var tit = $("#invoice_org").val();
		if(tit != ''){
			$("#invoicetitle").val(tit);
		}
		//发票内容
		var content = $("#invCon").val();
		if(content != ''){
			$("#invoicecontent").val($("#invCon").val());
		}
		
		//主页改变发票文案
		$("#isInvoice").text("开发票");
	}
	if(flag==0){//不开发票
		$("#invoiceflag").val("0");//默认不开
		$("#isInvoice").text("不开发票");
		cancelInvoice();//返回主页面
		return;
	}
}

/**
 * 确定：与收获地址相同和选择发票地址列表中的
 */
function addInvoice(){
	invCommon();
	var flag = $("#invFlag").val();//是否开发票
	
	if(flag==1 && $("#invDesFlag").val()==0){//与收获地址相同
		//alert("与收获地址相同");
		$("#invoiceaddrid").val($("#addrid").val());
		cancelInvoice();//返回主页面
		//$("#invDesFlag").val("0");//与收获地址相同
		return;
	}
	
	if(flag==1 && $("#invDesFlag").val()==1 && $("#INV_Addr_size").val()>0){//其他地址，发票地址列表中选择
		$("#invoiceaddrid").val($("#invAddressId").val());
		cancelInvoice();//返回主页面
		//$("#invDesFlag").val("0");//与收获地址相同
		return;
	}
	
}

//=========================================新增发票地址省市区级联start===============================================
//初始化省市区级联信息

function cascadeInv(province, city, area,town){
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
	        	city = $("#INV_AddrCity");
	        	//清除之前的市级信息
	        	city.html("<option value='-1'>请选择城市</option>");
	        	$.each(data.cityList,function(index,cityList){
	        		city.append("<option value="+cityList.id+" onclick='chooseInvAreaAddr("+cityList.id+")'>"+cityList.name+"</option>");
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
	        	area = $("#INV_AddrArea");
	        	//清除之前的区级信息
	        	area.html("<option value='-1'>请选择地区</option>");
	        	$.each(data.areaList,function(index,areaList){
	        		area.append("<option value="+areaList.id+" onclick='chooseInvTownAddr("+areaList.id+")'>"+areaList.name+"</option>");
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
	        	town = $("#INV_AddrTown");
	        	//清除之前的街道级信息
	        	town.html("<option value='-1'>请选择街道</option>");
	        	$.each(data.townList,function(index,townList){
	        		town.append("<option value="+townList.id+" onclick='chooseInvCloseAddr("+townList.id+")'>"+townList.name+"</option>");
	        	});
	        }
		});
	});
	
}

//=========================================新增发票地址省市区级联end===============================================