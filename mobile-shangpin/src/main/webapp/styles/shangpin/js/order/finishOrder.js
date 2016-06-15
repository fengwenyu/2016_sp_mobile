function finishOrder(o){
	 if(confirm("确认收货吗")){
		  var path = getRootPath();
		  $.ajax({
			    url:path+'/order/finish',
			    data:{
			    	"mainOrderNo":o,
			    	"deltime":Math.random()
		  		},
			    dataType:'json',
			    async:false,
				error: function (xmlHttpRequest, error) {
					if(error == "parsererror"){
						alert("用户帐号异常，请重新登录");
					// var enurl=escape(shangpinurl+"login");
					window.location.href=path+"/login";
					}else if(error == "error"){
		          		alert("非常抱歉，有点小问题，请联系客服人员！");
		          	}else{
				        alert("您的网络异常");
					}
				    return
				},success:function(data){
					if("0"==data.code){
					 window.location.href=path+"/order/list?statusType="+$("#statusType").val()
					}else if("2"==data.code){
						 window.location.href=path+"/login"
						}
					else{
						alert(data.msg);
					}
			    }
			  });
	 }  
}