	function changeImage(v){
		var img = "captcha?t="+ new Date().getTime() ;
		$(v).attr("src",img);
	}

	function changeImage(){
		var img = "captcha?t="+ new Date().getTime() ;
		$('span img').attr("src",img);
	}

	function checkCaptchaCode(){
		var flag = true;
		var captcha = $('#J_verCode').val();
		$.ajax({
			type:'get',
			url:'checkCaptchaCode?captcha=' + captcha,
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

	function sendAddress(){
		var username = $('#J_UserNameTxt').val();
		if(username.match('@') != null){
			$('#J_Forget1').attr({action:'findpwdEmail', method: 'post'});
			$('.login_btn').val('发送');
		}else{
			$('#J_Forget1').attr({action:'findpwdPhone', method: 'post'});
		}
	}

	function checkPassword(){
		var password = $('#J_MobiPwd').val();
		var partt = new RegExp(/^[a-zA-Z0-9]{6,20}$/);
		if(!partt.test(password)){
			alert("6-20位英文字母或数字密码");
			return false;
		}
	}

	function thirdLogin(mode){
		var back = $("input[name='back']").val();
		if(back){
			var reg=new RegExp("&","g");
			back =back = decodeURI(back);
			back =back.replace(reg,"9uuuuu9");
		}
		var path = getRootPath();
		if(mode=="qq"){
			window.location.href = path +"/thirdLogin/qqlogin?back="+back;
		}else if(mode=="wx"){
			window.location.href = path +"/thirdLogin/wxlogin?back="+back;
		}
	}

