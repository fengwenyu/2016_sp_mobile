//表单验证
(function() {
    window.remainTime = function() {},
    $.extend(window.remainTime.prototype, {
        init: function() {},
        activeTime: function(id,callback) {
            
            //
            var $this = $(id),
			startTimeData = $this.attr("startTime"),
			startTime,// = new Date(Date.parse(startTimeData.replace(/-/g, "/"))).getTime(),
			nowTimeData = $this.attr("nowTime"),
			nowTime = new Date(Date.parse(nowTimeData.replace(/-/g, "/"))).getTime(),
			endTimeData = $this.attr("endTime"),
			endTime,// = new Date(Date.parse(endTimeData.replace(/-/g, "/"))).getTime(),
			SysSecond,// = (startTime - nowTime)/1000,	//这里获取倒计时的起始
			InterValObj = window.setInterval(SetRemainTime, 1000), //间隔函数，1秒执行
			nowTxt;
		
			if(startTimeData != null){
				startTime = new Date(Date.parse(startTimeData.replace(/-/g, "/"))).getTime();
				SysSecond = (startTime - nowTime)/1000;
				nowTxt = "每天上午10点最新特卖";
				console.log(SysSecond);
			}
			if(endTimeData != null){
				endTime = new Date(Date.parse(endTimeData.replace(/-/g, "/"))).getTime();
				SysSecond = (endTime - nowTime)/1000;
				nowTxt = "活动已经结束";
				console.log(SysSecond);
			}
			
			//将时间减去1秒，计算天、时、分、秒 
			function SetRemainTime() {
				if (SysSecond > 0) { 
					SysSecond = SysSecond - 1;
					var second = Math.floor(SysSecond % 60);	// 计算秒
					var minite = Math.floor((SysSecond / 60) % 60);	//计算分
					var hour = Math.floor((SysSecond / 3600) % 24);	//计算小时
					var day = Math.floor((SysSecond / 3600) / 24);	//计算天
					
					var overTime;
					if(day > 0){
						overTime = day + "天" + hour + "小时" + minite + "分" + second + "秒";
					}else if(hour > 0){
						overTime = hour + "小时" + minite + "分" + second + "秒";
					}else if(minite > 0){
						overTime = minite + "分" + second + "秒";
					}else{
						overTime = second + "秒";
					}
					if(startTime){
						overTime = "活动在"+overTime +"后开始";
					}
					if(endTime){
						overTime = "活动在"+overTime +"后结束";
					}
					$this.find("em").html(overTime);
				}
				else{
						//剩余时间小于或等于0的时候，就停止间隔函数
						window.clearInterval(InterValObj);
						InterValObj = null;
						//这里可以添加倒计时时间为0后需要执行的事件
						//$this.hide();
						callback();
						$this.html(nowTxt);
				} 
			}
            //
            
        }
    });
})(Zepto);


var reload = function(){
	window.top.location.reload();
};

var remainTime = new remainTime();
if($(".lxftime1").length > 0){
	remainTime.activeTime(".lxftime1",reload);
}
if($(".lxftime2").length > 0){
	remainTime.activeTime(".lxftime2",reload);
}
if($(".lxftime3").length > 0){
	remainTime.activeTime(".lxftime3",reload);
}
