$(function(){
	
	//点击置顶按钮，页面滚动到顶部
    $(".nav_top").bind("click touchend", function(){
    	window.scrollTo(0,0);
    });

    //点击浮动按钮显示右侧菜单
    $(".nav_list").bind("click touchend", function(){
		$('.nav_menu_bg').show();
    	$(".nav_menu").show().removeClass("slideOut").addClass("slideIn");
    });

	//关闭弹出层
	$(".pop_close").bind("click touchend", function(){
		setTimeout(function(){
			$('.nav_menu_bg').hide();
			$(".nav_menu").removeClass("slideIn").addClass("slideOut");
		},200);
		
		

	});
	//点击右侧浮动框
	$(".nav_menu li").bind("click touchend", function(){
        var $this = $(this);
        if(!$this.hasClass("top")){
            $this.addClass("curr").siblings().removeClass("curr");
        }
		setTimeout(function(){
			$('.nav_menu_bg').hide();
			$(".nav_menu").removeClass("slideIn").addClass("slideOut");
		},200);
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
	
	
	//预热页面倒计时
    var timedom = $("#curdatetime_108");
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
            starttime = serializetime($("#nowSys").val()),
            endtime = serializetime("2015,05,19,22,00,00"),
            timer = $.MXTimer({start: starttime, stop: endtime}),
            datesdom = $("#leftdays_108"),
            hoursdom = $("#lefthours_108"),
            minutesdom = $("#leftminutes_108"),
            secondsdom = $("#leftseconds_108");

        timer.start(function(){
            var info = this.info();
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