// JavaScript Document
$(function(){
	
	//点击获取验证码
	var phoneed = 0; //标识是否验证过手机
	$('body').delegate('#verify_btn','click',function(){
		
		var $this = $(this);
		if($this.hasClass('countdown_ing')){
			return false;
		}
		
		//判断手机格式
		var phoneVal = $('#verify_text').val();
		
		if (!/^\d{11}$/.test(phoneVal) && !phoneed) {
			
			jShare('您输入的手机号有误，请重新输入!',"","");
			
		}else{
			
			
			
			phoneed = 1; //手机已验证
			//手机已验证
			if(phoneed){
				$.ajax({
		  			  url:'accountaction!sendcouponphonecode',
		  			  data:{"phonenum":phoneVal	    			  
		  			  },
		  			  dataType:'json',
		  	          timeout: 30000,
		          
		           
		  			  success:function(data){
		  			    if(data.code==0){	
		  			    	jShare('验证码已发送,查收短信!',"","");
		  			    }else{
		  			    	jShare(data.msg,"","");
		  			      return;
		  			    }
		  			  }
		          });
			}else{
				jShare('您输入的手机号有误，请重新输入!',"","");
			}
			
			
		
			
			
			
			
			$this.addClass('countdown_ing');
			var time = 60;//倒计时时间60秒
			var countDownTime = setInterval(function(){
				if(time != 1){
					time--;
					//$this.text( time + 's后重新获取验证码');
				}else{
					
					$this.removeClass('countdown_ing');
					clearInterval(countDownTime);
					countDownTime = null;
				}
			},1000);					
		}
	});
	
	//点击领取优惠券
	$('body').delegate('#code_btn','click',function(){
		var phoneVal = $('#verify_text').val();
		//判断验证码格式
		var codeVal = $('#code_text').val();
		
		if(codeVal == "" || codeVal.length < 6 || codeVal.length > 6){
			
			jShare('请输入正确验证码!',"","");
			
		}else{
			phoneed = 1;
			var verifycode=$('#code_text').val();
			var couponcode=$('#couponcode').val();
			//手机已验证
			if(phoneed){
				
				$.ajax({
		  			  url:'accountaction!verifycouponphonecode',
		  			  data:{
		  				  "phonenum":phoneVal,	
		  				"verifycode":codeVal,
		  				"couponcode":couponcode
		  			  },
		  			  dataType:'json',
		  	          timeout: 30000,
		            
		  			  success:function(data){
		  			    if(data.code==0){	
		  			    	jShare('恭喜您领取成功!',"","");
		  			    	location.href = ""+ $("#path").val()+"/motherDay/index.html";
		  			    }else{
		  			    	jShare(data.msg,"","");
		  			      return;
		  			    }
		  			  }
		          });
			
				
			
			}else{
				jShare('请先获取验证码!',"","");
			}
			return false;
			
		}
		
	});
	
	
	//点击领取优惠券
	$('body').delegate('#code_btn','click',function(){
		var phoneVal = $('#verify_text').val();
		//判断验证码格式
		var codeVal = $('#code_text').val();
		
		if(codeVal == "" || codeVal.length < 6 || codeVal.length > 6){
			
			jShare('请输入正确验证码!',"","");
			
		}else{
			phoneed = 1;
			var verifycode=$('#code_text').val();
			var couponcode=$('#couponcode').val();
			//手机已验证
			if(phoneed){
				
				$.ajax({
		  			  url:'accountaction!verifycouponphonecode',
		  			  data:{
		  				  "phonenum":phoneVal,	
		  				"verifycode":codeVal,
		  				"couponcode":couponcode
		  			  },
		  			  dataType:'json',
		  	          timeout: 30000,
		            
		  			  success:function(data){
		  			    if(data.code==0){	
		  			    	jShare('恭喜您领取成功!',"","");
		  			    	location.href = ""+ $("#path").val()+"/motherDay/index.html";
		  			    }else{
		  			    	jShare(data.msg,"","");
		  			      return;
		  			    }
		  			  }
		          });
			
				
			
			}else{
				jShare('请先获取验证码!',"","");
			}
			return false;
			
		}
		
	});
});