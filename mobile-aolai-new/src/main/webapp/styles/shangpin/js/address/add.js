$(function($){
	isHome(false);
	//保存收货人地址成功后，清空表单内容
	if('${msg}'=='保存收货人地址成功'){
		$(':input','#J_AddrForm')
		.not(':button, :submit, :reset, :hidden')  
		.val('')
		//.removeAttr('checked')  
		.removeAttr('selected');
	}
	cascadeStage($("#J_AddrProvince"),$("#J_AddrCity"),$("#J_AddrArea"),$("#J_AddrTown"));
})