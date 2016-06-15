$(function(){
	
	//点击领取V码
	$('body').delegate('#code_btn','click',function(){
		
		//判断验证码格式
		var codeVal = $('#code_text').val(),
			codeImg = $('#code_img').val();
		
		if(codeVal == "" || codeVal.length < 8 || codeVal.length > 8){
			
			jShare('请输入正确V码!',"","");
			
		}else{
			
			if(codeImg == ""){
				jShare('请输入验证码',"","");
			}else{
				
				$.ajax({
					type: "GET",
					url: "/data/active.txt?" + new Date().getTime(),
					//data: $('#verify_text').val(),
					success: function(data){
						if(data == "1"){
							jShare('V码激活成功!',"","");
							setTimeout(function(){
								window.location.href="#";
							
							},1000);
							
						}else{
							jShare('V码错误!',"","");
						}
					},
					error : function(){
							jShare('V码激活失败!',"","");
					}
				});
				
			}
			
			
			return false;
			
		}
		
	});
});