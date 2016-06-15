$(function(){
	var shareimg=getShareImg();
	if(shareimg!=null){
		var href=$(".shang_share a").attr("href")+"&imgurl="+shareimg;
		$(".shang_share a").attr({href:href});
	}
	
})