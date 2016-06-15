function share(e){
	var checkApp = $('#checkAPP').val();
	if(checkApp){
		var url = $(e).parent('.show_text').find(".shareUrl").val();
		var shareTitle = $("#shareTitle").val();
		var shareDesc = $("#shareDesc").val();
		var sharePic = $("#sharePic").val();
		if(url){
			window.location.href="shangpinapp://phone.shangpin/actiongoshare?title="+shareTitle+"&desc="+shareDesc+"&url="+url+"&imgurl="+sharePic;
		}
	}else{
		alert("请在尚品app中打开");
	}
}