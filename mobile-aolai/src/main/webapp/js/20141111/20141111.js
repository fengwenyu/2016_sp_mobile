/*下拉加载*/

var num = 0;

$(window).scroll(function(){
	BottomLoading();
});

function BottomLoading(){
	var loading = {
		img : '/images/20141111/loading.gif',
		msgText : '正在给力载入中...',
	};
	var htmlUrl = [
		'/data/20141111/women_shoes.html',
		'/data/20141111/women_clothing.html',
		'/data/20141111/men_clothing.html',
		'/data/20141111/beauty.html',
		'/data/20141111/furniture.html'
	];
	var htmlUrlLength = htmlUrl.length;
	var addSelector = '.category';
	var loadingMsg = '<div id="loading" style="display:none"><img src=' + loading.img + ' /><p>' +loading.msgText + '</p><div>'
	var ready=1;
	if(num < htmlUrlLength){
		
		if ($(window).scrollTop() + $(window).height() >= $(document).height()-50){
			if(!$('body').find('#loading').length){
				$('body').append(loadingMsg);
			}
			if(ready==1){
				  $('#loading').show();
				  ready=0;
				  //setTimeout(setReady,3000);
				  $.ajax({
					  type : "POST",
					  url : htmlUrl[num], 
					  dataType:"html",
					  success : function(data) {
						$('#loading').hide();
						$(addSelector).append(data);
						num++;
						ready=1;
					  },
					  error : function(){
						alert('数据获取失败，请刷新页面');
					  }
				  });
					
		   }
	  }
   }
}
/*function setReady(times){
   ready = 1;
   console.log(123);
}	*/
