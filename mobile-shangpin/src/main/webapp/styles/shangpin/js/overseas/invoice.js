/**
 * @author zghw
 */
$(function(){
	
	//选择地址
	$(".select-address").click(function(){
		$(".select-address").toggleClass("cur");
		if($(".select-address").hasClass("cur")){
			$("#invoiceAddressId").val($("#addressId").val());
		}
	});

});



//提交添加发票地址
function submitAddInvoiceAddr(addrFlag){
	var validateAddr = validateAddress(addrFlag);
	if (!validateAddr) {
		return false;
	}
	$.ajax({
		  url:getRootPath()+'/invoice/ajax/add',
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
						  $("#invoice_address_container .paymet_block p").remove();
						  var address_list=$("#invoice_address_container .paymet_block");
						  $.each(content.list,function(i,address){
							  var appendP='<p class="addr_block"  id="addrId'+address.id+'">';
							  appendP+='<span class="click_addr" onclick="clickInvoiceAddr(this);"  addr_id="'+address.id+'"  addr_name="'+address.name+'" addr_tel="'+address.tel+'" addr="'+address.provname+address.cityname+address.areaname+address.townname+address.addr+'">';
							  appendP+='<i>'+address.name+'&nbsp;&nbsp;'+address.tel+'</i>';
							  appendP+=address.provname+address.cityname+address.areaname+address.townname+address.addr;
							  appendP+='</span>';
							  
							  appendP+='<span class="addr_edit">';
							  appendP+='<a href="javascript:;" class="editBtn" onclick="backAddressInvoiceEdit(\''+address.id+'\' ,\'invoice_address_edit_\');">编辑</a> &nbsp;&nbsp;';
							  appendP+='<a href="javascript:;" class="deletBtn" onclick="delInvoiceAddr(\''+address.id+'\')">删除</a>';
							  appendP+='</span>';
							  appendP+='</p>';
							  address_list.append(appendP);
						  });
						//返回上一级
						  alert("添加发票地址成功！");
						  backInvoiceAddress();
					  }
				  }
		  }
	});
}
//删除发票地址
function delInvoiceAddr(addrId){ 
	  var statu = confirm("您确认要删除该发票地址吗？");
      if(!statu){
          return false;
      }
      var url = getRootPath()+"/address/del";
      $.getJSON(url,{"addressId":addrId},function(data){
    	  if(data=="0"){
    		  alert("删除发票地址成功!");
    		  $("#addrId"+addrId).remove();
    		  if(addrId==$("#invoiceAddressId").val()){
    			  //如果删除的是选中的收货地址则清空
    			  $("#invoiceAddressId").val("");
    		  }
    	  }else{
    		  alert("删除发票地址失败!");
    	  }
      });
}

//修改发票地址
function submitUpdateInvoiceAddr(addrFlag){
	var validateAddr = validateAddress(addrFlag);
	if (!validateAddr) {
		return false;
	}
	$.ajax({
		  url:getRootPath()+'/invoice/ajax/edit',
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
						  $("#invoice_address_container .paymet_block p").remove();
						  var address_list=$("#invoice_address_container .paymet_block");
						  $.each(content.list,function(i,address){
							  var appendP='<p class="addr_block"  id="addrId'+address.id+'">';
							  appendP+='<span class="click_addr" onclick="clickInvoiceAddr(this);"  addr_id="'+address.id+'"  addr_name="'+address.name+'" addr_tel="'+address.tel+'" addr="'+address.provname+address.cityname+address.areaname+address.townname+address.addr+'">';
							  appendP+='<i>'+address.name+'&nbsp;&nbsp;'+address.tel+'</i>';
							  appendP+=address.provname+address.cityname+address.areaname+address.townname+address.addr;
							  appendP+='</span>';
							  
							  appendP+='<span class="addr_edit">';
							  appendP+='<a href="javascript:;" class="editBtn" onclick="backAddressInvoiceEdit(\''+address.id+'\' ,\'invoice_address_edit_\');">编辑</a> &nbsp;&nbsp;';
							  appendP+='<a href="javascript:;" class="deletBtn" onclick="delInvoiceAddr(\''+address.id+'\')">删除</a>';
							  appendP+='</span>';
							  appendP+='</p>';
							  address_list.append(appendP);
						  });
						  alert("编辑收货地址成功！");
						  //返回上一级
						  backInvoiceAddress();
					  }
				  }
		  }
	});
}