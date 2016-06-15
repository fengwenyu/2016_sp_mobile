
function timefun(){
	
	$(".new-arrival-box").each(function() {
			var time=$(this).find("span").attr('new-times');
			var dtime = new Date(parseInt(time)); 
			var month = dtime.getMonth() + 1;     //月
	        var day = dtime.getDate();            //日
	        $(this).find("span").find("times").text(month+"."+day);
	        
	});
	
}
timefun();