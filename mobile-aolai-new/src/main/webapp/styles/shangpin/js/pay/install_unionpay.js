function unionPay(accept){
	var ret=rechargeSnoof(accept);
	$("#redirectYL").hide();
	$("#goOn").show();
	if(ret.support=="true"){
		 var userAgent = navigator.userAgent.toLowerCase();
		 var style=plateformSnoof(userAgent);
		
		 if(style=="android"){
			 $("#notpromptandriod").show(); 
			 
		 }
		 if(style=="ios"){
			 $("#notpromptios").show(); 
		 }
	}else{
		 $("#notphone").show(); 
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
	var safari=new RegExp("safari");
	if (ios.test(userAgent)&&safari.test(userAgent)) {
		return "ios";
	}

	if (ipad.test(userAgent)) {
		return "ios";
	}
	if (android.test(userAgent)) {
		return "android";
	}
}