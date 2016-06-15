$(function(){

	if($(".content_scroll").length>0){
		var roll1 = new slider(".content_body", ".scroll .bar_list ul", ".scroll .bar_list ul li", ".bar_btn", 0);
	}

	if($(".nav_menu_pop").length > 0){
		$(".nav_menu_pop").height(document.body.scrollHeight);
	}

	//关闭弹出层
	$(".pop_close, .nav_menu_pop").bind("click touchend", function(){
		$(".nav_menu_pop").hide();
		$(".nav_menu").removeClass("slideIn").addClass("slideOut");

	});

	//点击右侧浮动框
	$(".nav_menu li").click(function(){

		var $this = $(this);
		if(!$this.hasClass("top")){
			$this.addClass("curr").siblings().removeClass("curr");
		}
        $(".nav_menu_pop").hide();
        $(".nav_menu").removeClass("slideIn").addClass("slideOut");
	});

    var mao_arr = [];
    $("a.list_position").each(function(index, element) {
        mao_arr.push($(this).offset().top);
    });
     
    //鼠标滚动事件
    $(window).scroll(function(){
        
        for(var i=0; i<mao_arr.length; i++){
            if($(this).scrollTop() >= mao_arr[i]){
                $('.nav_menu li').eq(i+1).addClass('curr').siblings('li').removeClass('curr');
            }
        };

        //页面滚动时，显示置顶的小图标
    	if($(this).scrollTop()>=window.screen.availHeight){
        	$(".nav_top").css("display","block");
        }else{
        	$(".nav_top").css("display","none");
        }
    });

    //点击置顶，页面滚动到顶部
    $(".nav_top").click(function(){
    	window.scrollTo(0,0);
    });

    //点击详情显示滚动条
    $(".nav_list").click(function(){
    	$(".nav_menu_pop").show();
    	$(".nav_menu").removeClass("slideOut").addClass("slideIn");
    });

    //点击收藏
    
    $(".collection").click(function(){

    	if(!$(this).hasClass("curr")){
    		$(this).addClass("curr").html("已收藏");
    		
			return jShare('收藏成功!',"",""),
			$("#J_userName").addClass("error"),
	    	!1;
    	}else{
    		$(this).removeClass("curr").html("收藏");
    		
			return jShare('取消收藏!',"",""),
			$("#J_userName").addClass("error"),
	    	!1;
    	}
    	
    })
	
	//108预热页面倒计时
    var timedom = $("#curdatetime_108");
    var myDate = new Date();
    var month=myDate.getMonth()+1;
    if(timedom.length > 0){
        var serializetime = function (timestr) {
                var timearr = timestr.split(",");
                return {
                    year: parseInt(timearr[0]),
                    month: parseInt(timearr[1]),
                    date: parseInt(timearr[2]),
                    hour: parseInt(timearr[3]),
                    minute: parseInt(timearr[4]),
                    second: parseInt(timearr[5])
                }
            },
            starttime = serializetime(myDate.getFullYear()+","+month+","+myDate.getDate()+","+myDate.getHours()+","+myDate.getMinutes()+","+myDate.getSeconds()),
            endtime = serializetime(timedom.attr("endtime")),
            timer = $.MXTimer({start: starttime, stop: endtime}),
            datesdom = $("#leftdays_108"),
            hoursdom = $("#lefthours_108"),
            minutesdom = $("#leftminutes_108"),
            secondsdom = $("#leftseconds_108");

        timer.start(function(){
            var info = this.info();
            //console.log("剩余" + info.dates + "天" + info.hours + "小时" + info.minutes + "分" + info.seconds + "秒");
            var lefttime = info.lefttime,
				dates = info.dates,
                hours = info.hours,
                minutes = info.minutes,
                seconds = info.seconds;

            if(lefttime >= 0){
				datesdom.text(dates);
				hoursdom.text(hours > 9 ? hours : "0" + hours);
				minutesdom.text(minutes > 9 ? minutes : "0" + minutes);
				secondsdom.text(seconds > 9 ? seconds : "0" + seconds);
			}else{
				$(".sp_top_time").hide();
				clearInterval(writeT);	//停止毫秒事件
			}
			
        });
		
		
	}
	
});