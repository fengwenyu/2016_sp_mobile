$(function(){
	$.ajax({
		type: "GET",
		url: getRootPath()+"/winList?activityId=520",
		success: function(data){
			if(data.winList!=undefined&&data.winList!=null){
				var winList = data.winList;
				var strHtml = "";
				for(var i=0;i<winList.length;i++){
					var win = winList[i];
					strHtml = strHtml + "<li>" +
							win.userName +
							"抽中了" +
							findLevel(win.prizeLevel) +
							"元购物节现金礼包，已领取</li>";
				}
				$('#window_roll').html(strHtml);
			}
		}
	});
});
function findLevel(prizeLevel){
	var prompt= "";
	var prize = parseInt(prizeLevel);
	switch(prize) {
        case 2:
        case 4:
        	prompt = 500;
        	break;
        case 3:
        	prompt = 50;
        	break;
        case 1:
        	prompt = 1500;
        	break;
	}
    return prompt;
}
$(function(){
	//跑马灯开始
	    var AutoRoll = function(){
		  $(".lottery_list").animate({
			  marginTop:"-24px"
		  },500,function(){
			  $(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
		  });
	    };
		var startRoll = setInterval(AutoRoll,3000);
	});