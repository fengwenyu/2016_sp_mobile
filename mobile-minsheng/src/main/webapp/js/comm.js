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
$(function(){
//隐藏地址栏

//判断不同浏览器
function get_scrollTop_of_body(){
	var scrollTop;
	if(typeof window.pageYOffset != 'undefined'){
		scrollTop = window.pageYOffset;
	}else if(typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat'){
		scrollTop = document.documentElement.scrollTop;
	}else if(typeof document.body != 'undefined'){
		scrollTop = document.body.scrollTop;
	}
	return scrollTop;
}
//判断不同浏览器
	
	//返回顶部
	$(".alScrollTop").click(function(){
		var scrollTop = $(window)[0].scrollTo(0,0);
		$("html, body").animate({ scrollTop: 0 }, 120);
		return false;
	});
	//返回顶部
	
	//tab切换事件
	var tabs = function(nav,content){
		$(nav).find("li").bind("click",function(){
			var index = $(this).index();
			$(this).addClass("cur").siblings().removeClass("cur");
			$(content).eq(index).show().siblings(content).hide();
		});
	}
	
	tabs(".alProd_introMenu ul",".alProd_introCell");
	tabs(".alOrder_listMenu ul",".alOrder_listCell");
	
	//tab切换事件
	
	//预售日历TAB切换
	$(".alDate_menu").delegate("a","click",function(){
		var tabIndex = $(this).index();
		$(".alDate_menu a").removeClass("cur");
		$(this).addClass("cur");
		$(this).parent().siblings(".alDate_menu").find("a").eq(tabIndex).addClass("cur");
		$(".alDate_list header em").html($(this).text());
	});
	//预售日历TAB切换
	
	//男士分类切换
	$(".tagNav li span").click(function(){
		var isUp = $(this).find("i").hasClass("up");
		
		if(!isUp){
			$(this).find("i").addClass("up");
			$(this).closest("li").find("div").show();
		}else{
			$(this).find("i").removeClass("up");
			$(this).closest("li").find("div").hide();
		}
	});
	//男士分类切换
	
	//底部日历返回头部
	window.onscroll = function(){
		
		if (get_scrollTop_of_body() > 0){
			$("#aldate_btm a").click(function(){
				var scrollTop = $(window)[0].scrollTo(0,0);
				$("html, body").animate({ scrollTop: 0 }, 120);
				return false;
			});
		}
		
	};
	//底部日历返回头部
	
	//60秒倒计时重新发送手机验证码
	$("#secondCode").click(function(){
		$(this).hide();
		setTimeout(function(){$("#secondCode").show();},60000);
		return false;
	});
	setTimeout(function(){$("#secondCode").show();},60000);
	//60秒倒计时重新发送手机验证码
	
	if($("#tabContent").length > 0){
		loaded();
	}
	
	//
	if($(".alProd_introCell img").length > 0 ){
		var imgW = $(window).width() - 20;
		$(".alProd_introCell img").css("max-width",imgW);
	}
	
});

var boxScroll;
function loaded(){
	
	boxScroll = new iScroll('tabContent',
    {
		hScrollbar:false,
		vScrollbar:true
    });
}
//document.addEventListener('DOMContentLoaded', loaded, false);

//活动倒计时
/*if($(".lxftime").length > 0){
	
	$(".lxftime").each(function() {
		var $this = $(this),
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
			nowTxt = "活动进行中";
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
					overTime = day + "天" + hour + "小时" + minite + "分";
				}else if(hour > 0){
					overTime = hour + "小时" + minite + "分";
				}else if(minite > 0){
					overTime = minite + "分";
				}else{
					overTime = second + "秒";
				}
				
				$this.find("em").html(overTime);
			}
			else{
					//剩余时间小于或等于0的时候，就停止间隔函数
					window.clearInterval(InterValObj);
					InterValObj = null;
					//这里可以添加倒计时时间为0后需要执行的事件
					//$this.hide();
					
					$this.html(nowTxt);
			} 
		}
		
    });
}*/