var wait = 60;
$(document).ready(changeShow);
	function changeShow(){
		if(wait == 0){
			$('span a').css('display','block');
			$('.reSend_btn').css('display','none');
			wait = 60;
		}else{
			$('span a').css('display','none');
			$('.reSend_btn').css('display','block');
			$('.reSend_btn').text(wait + '秒后重新发送');
			wait--;
			setTimeout(function(){
				changeShow();
			}, 1000);
		}
	}

		/**
		 * 验证发送到手机上的验证码
		 */
		function checkPhoneCode(){
			var flag = true;
			var mobi = $('#J_phonenum').val();
			var verifycode = $('#J_MobiCode').val();
			$.ajax({
				type:"get",
				url:"checkPhoneCode?mobi=" + mobi + "&" + "verifycode="+verifycode,
				async:false,
				dataType:"json",
				success:function(data){
					var code = data.code;
					if(code == "1"){
						flag = false;
						alert(data.msg);
					}
				}
			});
			return flag;
		}
		
		function sendphonecode(){
			changeShow();
			var phoneNum = $('#J_phonenum').val();
			if(phoneNum == "" || phoneNum == null){
				return;
			}
			$.post("repeatSendRegistPhoneCode",{mobi:phoneNum},function(data){
				if(data.code!="0"){
					alert(data.msg);
					return;
				}
			},"json");
		}

		
		function sendFindpwdcode(){
			changeShow();
			var phoneNum = $('#J_phonenum').val();
			if(phoneNum == "" || phoneNum == null){
				return;
			}
			$.post("repeatSendFindpwdCode",{mobi:phoneNum},function(data){
				if(data.code!="0"){
					alert(data.msg);
					return;
				}
			},"json");
		}
		