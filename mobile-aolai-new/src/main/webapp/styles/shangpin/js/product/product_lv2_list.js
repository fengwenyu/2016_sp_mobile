
/**
 * 加载更多
 */
function getMore(){
	var pageIndex = $("#pageIndex").val();
	$("#haveMore").text("正在加载...");
	$("#haveMore").removeAttr("href");
	$.ajax({
		url:getRootPath()+"/activity/more",
		data:{
			"activityId":$("#activityNum").val(),
			"typeFlag":$("#typeFlag").val(),
			"pageIndex":parseInt(pageIndex)+1,
			"pageType":$("#pageType").val()
		},
		dataType:'html',
	    timeout: 30000,
	    error: function (xmlHttpRequest, error) {			            	
            alert("您的网络异常");
            return;
        },
       success:function(data){
    	 //将返回的html填充
    	 $("#modelLv2").append(data);
    	 $("#pageIndex").val($("#nowPage").val());
    	 var more = $("#isMore").val();
    	 if(more == 0){
			$("#haveMore").remove();
		 }else{
			$("#haveMore").text("加载更多");
			$("#haveMore").attr("href", "javascript:getMore()");
		 } 
    	 
       }
	});
	
}
