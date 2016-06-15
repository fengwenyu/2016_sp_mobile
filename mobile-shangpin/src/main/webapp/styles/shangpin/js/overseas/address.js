/**
 * @author zghw
 */
$(function() {
	// 收货地址的弹层关闭
	$(".close_btn, #address_area_overlay").click(function() {
		$("#address_area_layer, #address_area_overlay").hide();
		$("#address_area_layer h3").show().html("省份");
		$("#address_area_layer").find("dl").eq(0).show().siblings("dl").hide();
		$("#address_area_layer dd a").removeClass("cur");
	});
	
});

//提交添加收货地址
function submitAddAddr(addrFlag){
	var validateAddr = validateAddress(addrFlag);
	if (!validateAddr) {
		return false;
	}
	$.ajax({
		  url:getRootPath()+'/address/ajax/add',
		  data:$('#'+addrFlag+"form").serialize(),
		  type:"POST",
		  dataType : 'json',
		  success:function(data) {
				  if(data!=null){
					  var code = data.code;
					  var msg = data.msg;
					  var content = data.content;
					  if(code=="1"){
						  alert(msg);
					  }else{
						  //添加成功处理
						  
						  //更新列表数据
						  $("#address_list_container .paymet_block p").remove();
						  var address_list=$("#address_list_container .paymet_block");
						  $.each(content,function(i,address){
							  var appendP='<p class="addr_block"  id="addrId'+address.id+'" area="'+ address.area +'" town="'+ address.town +'">';
							  var cardID=$.trim(address.cardID);
							  var newCardID="";
							  if(cardID!=""){
								  newCardID=cardID.substr(0,6)+"****"+cardID.substr((cardID.length-4),cardID.length); 
							  }
							  appendP+='<span class="click_addr" onclick="clickAddr(this);"  addr_id="'+address.id+'"  addr_name="'+address.name+'" addr_tel="'+address.tel+'" addr="'+address.provname+address.cityname+address.areaname+address.townname+address.addr+'" addr_cardID='+newCardID+'>';
							  appendP+='<i>'+address.name+'&nbsp;&nbsp;'+address.tel+'</i>';
							  appendP+=address.provname+address.cityname+address.areaname+address.townname+address.addr;
							  appendP+='</span>';
							  
							  appendP+='<span class="addr_edit">';
							  appendP+='<a href="javascript:;" class="editBtn" onclick="backAddressEdit(\''+address.id+'\' ,\'address_edit_\');">编辑</a> &nbsp;&nbsp;';
							  appendP+='<a href="javascript:;" class="deletBtn" onclick="delAddr(\''+address.id+'\')">删除</a>';
							  appendP+='</span>';
							  appendP+='</p>';
							  address_list.append(appendP);
						  });
						//返回上一级
						  alert("添加收货地址成功！");
						  if($("#addressId").val()==""){
							  $.each(content,function(i,address){
									$("#addressId").val(address.id);
								 });
								backMain();
							}else{
								backAddress();
							}
					  }
				  }
		  }
	});
}

//删除收货地址
function delAddr(addrId){ 
	  var statu = confirm("您确认要删除该收货地址吗？");
      if(!statu){
          return false;
      }
      var url = getRootPath()+"/address/del";
      $.getJSON(url,{"addressId":addrId},function(data){
    	  if(data=="0"){
    		  alert("删除收货地址成功!");
    		  $("#addrId"+addrId).remove();
    		  if(addrId==$("#addressId").val()){
    			  //如果删除的是选中的收货地址则清空
    			  $("#addressId").val("");
    		  }
    	  }else{
    		  alert("删除收货地址失败!");
    	  }
      });
}

//修改收货地址
function submitUpdateAddr(addrFlag){
	var validateAddr = validateAddress(addrFlag);
	if (!validateAddr) {
		return false;
	}
	$.ajax({
		  url:getRootPath()+'/address/ajax/edit',
		  data:$('#'+addrFlag+"form").serialize(),
		  type:"POST",
		  dataType : 'json',
		  success:function(data) {
				  if(data!=null){
					  var code = data.code;
					  var msg = data.msg;
					  var content = data.content;
					  if(code=="1"){
						  alert(msg);
					  }else{
						  //更新列表数据
						  $("#address_list_container .paymet_block p").remove();
						  var address_list=$("#address_list_container .paymet_block");
						  $.each(content,function(i,address){
							  var appendP='<p class="addr_block"  id="addrId'+address.id+'">';
							  var cardID=$.trim(address.cardID);
							  var newCardID="";
							  if(cardID!=""){
								  newCardID=cardID.substr(0,6)+"****"+cardID.substr((cardID.length-4),cardID.length); 
							  }
							  appendP+='<span class="click_addr" onclick="clickAddr(this);"  addr_id="'+address.id+'"  addr_name="'+address.name+'" addr_tel="'+address.tel+'" addr="'+address.provname+address.cityname+address.areaname+address.townname+address.addr+'" addr_cardID='+newCardID+'>';
							  appendP+='<i>'+address.name+'&nbsp;&nbsp;'+address.tel+'</i>';
							  appendP+=address.provname+address.cityname+address.areaname+address.townname+address.addr;
							  appendP+='</span>';
							  
							  appendP+='<span class="addr_edit">';
							  appendP+='<a href="javascript:;" class="editBtn" onclick="backAddressEdit(\''+address.id+'\' ,\'address_edit_\');">编辑</a> &nbsp;&nbsp;';
							  appendP+='<a href="javascript:;" class="deletBtn" onclick="delAddr(\''+address.id+'\')">删除</a>';
							  appendP+='</span>';
							  appendP+='</p>';
							  address_list.append(appendP);
						  });
						  alert("编辑收货地址成功！");
						  //返回上一级
						  backAddress();
					  }
				  }
		  }
	});
}

// 选择收货地址
var addrTxt,thisCon,content,that,obj,title,prev,thatDl;
// 加锁
var block = true;
// 点击省市区
function selectPCAT(addrFlag) {
	$(".prev_btn").hide();
	block = true;
	$("#address_area_overlay").height($(document).height());
	$("#address_area_overlay, #address_area_layer").show();
	addrTxt = [];
	$("#addrFlag").val("#" + addrFlag);
	return false;
}

// 选择省市区
function choicePCAT(o) {
	var addrFlag = $("#addrFlag").val();
	// 设置id值 辨别隐藏选择的省市区的值
	var province = addrFlag + "province", city = addrFlag + "city", area = addrFlag
			+ "area", town = addrFlag + "town", selectTxt = addrFlag
			+ "select_area";
	// 锁定 防止重复执行
	if (!block) {
		return;
	}
	block = false;
	var that = $(o);// 当前选中的对象
	prev = $(".prev_btn");
	obj = $("#address_area_layer dd  a");
	content = $("#address_area_layer dl");
	thisCon = that.closest("dl");
	title = $("#address_area_layer h3");
	nextDl = thisCon.next("dl");
	obj.removeClass("cur");
	that.addClass("cur");
	// 选择结果
	addrTxt.push(that.text());
	setTimeout(function() {
		var did = that.attr("id");// 当前选择的省市区街ID
		var conId = thisCon.attr("id");
		var typeId = nextDl.attr("id");
		var url = getRootPath();
		if (thisCon.next("dl").length > 0) {
			prev.show(); // 返回上一级
			content.hide();
			nextDl.children("dd").remove();
			// 当前的是省份，下一个为市级
			if (typeId == 'area_city') {
				url += "/address/city";
				$(province).val(did);
			}
			// 当前的是市级，下一个为地区
			if (typeId == 'area_county') {
				url += "/address/area";
				$(city).val(did);
			}
			// 当前的是地区，下一个为街道
			if (typeId == 'area_street') {
				url += "/address/town";
				$(area).val(did);
			}
			// 请求
			$.post(url, {
				"proviceId" : did,
				"cityId" : did,
				"areaId" : did
			}, function(data) {
				// 迭代返回的json数据
				$.each(data, function(index, content) {
					// 动态装载市级数据
					nextDl.append("<dd><a href='javascript:;' id='"
							+ content.id + "' onclick='choicePCAT(this)' >"
							+ content.name + "</a></dd>");
				});
				block = true;
			}, "json");
			nextDl.show();// 显示下一级
			title.html(thisCon.next("dl").attr("title"));//
		} else {
			if (conId == 'area_street') {
				$(town).val(did);
			}
			// 返回初始状态
			content.hide();
			$("#address_area_overlay, #address_area_layer").hide();
			$("#address_area_layer dl:first").show();
			title.html($("#address_area_layer dl:first").attr("title"));
			var length = addrTxt.length -1;
			if(addrTxt[length] == '关闭'){
				$(selectTxt).text("省市区");
			}else{
				$(selectTxt).text(addrTxt.join(" "));
			}
			block = true;
		}
	}, 300);
}

function prevCTA(){
	setTimeout(function(){				
		addrTxt.pop();			
		content.hide();
		thisCon.show();			
		thisCon.find("a").removeClass("cur");
		title.html(thisCon.attr("title"));
		thisCon = thisCon.prev("dl");
		if(title.html() == "省份"){	
			$(".prev_btn").hide();
		}	
	}, 300);
	return false;
}

/**
 * 收货地址验证
 */
var validateAddress = function(flag) {
	var province = $("#" + flag + "province").val();
	var city = $("#" + flag + "city").val();
	var area = $("#" + flag + "area").val();
	var town = $("#" + flag + "town").val();
	var addr = $("#" + flag + "addr").val();
	var postcode = $("#" + flag + "postcode").val();
	var name = $("#" + flag + "name").val();
	var tel = $("#" + flag + "tel").val();
	var cardID = $("#" + flag + "cardID").val();

	var mre = /^1[358]\d{9}$/, post = /^[1-9][0-9]{5}$/;
	// 省市区验证
	if ($.trim(province) == "" || $.trim(city) == "" || $.trim(area) == ""
			|| $.trim(town) == "") {
		alert("请选择省市区地址！");
		return false;
	}
	// 详细地址验证
	if ($.trim(addr) == "") {
		alert("请填写详细地址！");
		return false;
	}
	// 邮编信息验证
	if (postcode == "") {
		alert("请填写邮编信息！");
		return false;
	}
	if (postcode != "" && !post.test(postcode)) {
		alert("请输入正确的邮编信息！");
		return false;
	}
	// 姓名验证
	if ($.trim(name) == "") {
		alert("请填写收货人姓名！");
		return false;
	}

	// 联系电话验证
	if (tel == "") {
		alert("请填写联系电话！");
		return false;
	}
	if (tel != "" && !mre.test(tel)) {
		alert("请输入正确的联系电话！");
		return false;
	}

	// 身份证验证
	/*
	 * if(cardID==""){ alert("请填写身份证号码！"); return false; }
	 */

	if (cardID!=undefined && cardID != "" && !checkCard(cardID)) {
		alert("请输入正确的身份证号码！");
		return false;
	}
	return true;
};