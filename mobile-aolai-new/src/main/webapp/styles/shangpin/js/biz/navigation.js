/**
 * 控制头部切换按钮样式
 */
$(function(){
	var gender = $('#gender').val();
	if(gender == "0"){
	  $("#li1").attr("class","cur");
	  $("#li2").attr("class","");
	  $("#li3").attr("class","");
    }else if(gender == "1"){
	  $("#li1").attr("class","");
	  $("#li2").attr("class","cur");
	  $("#li3").attr("class","");
    }else if(gender == "2"){
    	$("#li1").attr("class","");
		$("#li2").attr("class","");
		$("#li3").attr("class","cur");
    }
});
