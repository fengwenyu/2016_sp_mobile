$(function(){
		//图片ZOOM事件
		var k=0,
			drive= function() {
			k++;
			if(k==2){
				k=0;
			}
			$("#page01 .layer:eq("+ k +")").addClass("on").siblings(".layer").removeClass("on");
			
			if(k==2){
				$("#page01 .layer:eq(0)").addClass("zIndex").siblings(".layer").removeClass("zIndex");
			}else{
				$("#page01 .layer:eq(1)").addClass("zIndex").siblings(".layer").removeClass("zIndex");
			}
		},
		t = setInterval(drive,4900);
					
});