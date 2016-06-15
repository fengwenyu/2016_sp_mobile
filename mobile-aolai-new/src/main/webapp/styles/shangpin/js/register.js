
//设置性别
function changeGender(t){
		var value = $(t).attr('name');
		$('input[name=gender]').val(value);
}

 function changeImg(v){
    	var img = getRootPath()+"/captcha?t="+ new Date().getTime() ;
    	$(v).attr("src",img);
 }