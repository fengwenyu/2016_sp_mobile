	function checkCaptchaCode(){
		var flag = true;
		var captcha = $('#J_verCode').val();
		var mobi = $('#J_MobiName').val();
		$.ajax({
			type:'get',
			url:'checkCaptchaCode?captcha=' + captcha +'&mobi='+mobi,
			async:false,
			dataType:'json',
			success:function(data){
				if(data != null){
					flag = false;
					alert(data.msg);
				}
			}
		});
		setTimeout(function(){
			changeImage();
		},3000);
		return flag;
	}
	
function changeImage(){
	var img = "captcha?t="+ new Date().getTime() ;
	$('span img').attr("src",img);
}