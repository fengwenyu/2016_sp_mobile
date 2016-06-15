function unionpay(accept){
		var ret=rechargeSnoof(accept);
		if(ret.support=="true"){
			 if(ret.tag=="embed"){
				$("#embedtag").show();  
				 
			 }else if(ret.tag=="a"){
				$("#atag").show();   	  
					
			 }else {
				 var userAgent = navigator.userAgent.toLowerCase();
				 var style=plateformSnoof(userAgent);
				 if(style=="android"){
					 $("#notpromptandriod").show(); 
					 
				 }
				 if(style=="ios"){
					 $("#notpromptios").show(); 
				 }
			 }
		}
}			 
function rechargeSnoof(accept) {
	
	var ucSupport = ucSnoof(accept);

	if(ucSupport.support == "true"){
		return ucSupport;
	}
	
	// 通用判断
	var userAgent = navigator.userAgent.toLowerCase();
	$.browser = {
		three : /360 aphone browser/.test(userAgent),
		opera : /opera\//.test(userAgent) && /OupengHD/.test(userAgent),
		qq : /qqbrowser/.test(userAgent)
	};
	// 到这里，uc已经不判断了
	if (plateformSnoof(userAgent) == "android") {
		if ($.browser.qq || $.browser.three || $.browser.opera) {
			return {
				'support' : 'true',
				'tag' : 'a'
			};
		}else{
			return {

				'support':	'true',
				
				'tag' : 'notprompt'
			};
		}
	} else if(plateformSnoof(userAgent) == "ios"){
		if ($.browser.qq){
			return {
				'support' : 'true',
				'tag' : 'a'
			};
		}else{
			return {

				'support':	'true',
				
				'tag' : 'notprompt'
			};
		}
	}else {
		return {
			'support' : 'not'
		};
	}
}

function creditSnoof(accept){
	
	var ucSupport = ucSnoof(accept);
	if(ucSupport.support == "true"){
		return ucSupport;
	} else{
		return {
			'support' : 'false'
		};
	}
}

function ucSnoof(accept){
	
	if (!accept) {
		return;
	}

	// uc浏览器判断
	var ucIos = new RegExp("ios_plugin/1");
	var ucAndroid = new RegExp("plugin/1");

	if (ucIos.test(accept)) {
		return {
			'support' : 'true',
			'tag' : 'a'
		};
	} else if (ucAndroid.test(accept)) {
		return {
			'support' : 'true',
			'tag' : 'embed'
		};
	} else{
		return {
			'support' : 'not'
		};
	}
}

function plateformSnoof(userAgent) {
	var ios = new RegExp("iphone os");
	var android = new RegExp("android");
	var ipad=new RegExp("ipad");

	if (ios.test(userAgent)) {
		return "ios";
	}
	if (ipad.test(userAgent)) {
		return "ios";
	}
	if (android.test(userAgent)) {
		return "android";
	}
}



function chagePayMode(payVal,paytypechildid){
	$.ajax({
		  url:'orderaction!chagePayMode',
		  data:{
			 "paytypeid":payVal,
			 "orderid":$("#orderid").val(),
			 "paytypechildid":paytypechildid
		  }, success:function(data){
			  if("success" == data.msgcode&&payVal==20&&paytypechildid==37){
				 location.href = ""+ $("#path").val()+"/alipay/trade?orderId="+$("#orderid").val();
				 return;  
			  }
			  if("success" ==data.msgcode&&payVal==2&&paytypechildid==41){
				  location.href = ""+ $("#path").val()+"/payokaction!result?amount="+$("#amount").val()+"&orderid="+$("#orderid").val();
				  return;
			  }
			 if("success" == data.msgcode&&payVal==19&&paytypechildid==49){
				location.href = "uppay://uppayservice/?style=token&paydata="+$("#embed").attr("paydata");
				return;
			  }
			
			 alert(data.msg);
			
		  }
 });
}  