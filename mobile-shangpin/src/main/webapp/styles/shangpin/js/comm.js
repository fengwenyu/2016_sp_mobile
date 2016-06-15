// JavaScript Document
//隐藏地址栏
function isHome(el){
	var $ipad = navigator.userAgent.match(/(iPad).*OS\s([\d_]+)/) ? true: false,
	$iphone = !$ipad && navigator.userAgent.match(/(iPhone\sOS)\s([\d_]+)/) ? true: false;
	
	if($ipad || $iphone){
		if(el){
			document.documentElement.style.height = "5000px";
		}
		window.scrollTo(0, 0);
		document.documentElement.style.height = window.innerHeight - 0 + "px";
	}else{
		setTimeout(scrollTo, 0, 0, 1);
	}
	//setTimeout(scrollTo, 0, 0, 1);
}
 