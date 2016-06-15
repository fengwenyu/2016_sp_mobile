$(function(){
	var ua = window.navigator.userAgent.toLowerCase(); 
	if(ua.match(/MicroMessenger/i) == 'micromessenger'){ 
		$('.share-icon').click(function(){
			$('.share-tip').show();
		});
		$('.share-tip').click(function(){
			$('.share-tip').hide();
		})
	}	
});