//clcik
var loginStatus = false; //判断登录状态
;(function($){
	$.fn.dbRotateClick2D=function(options){
		var opt={
			rotateSpeed:100       
		}
		$.extend(opt,options);
		return this.each(function(){
			var $this=$(this);
			var $img=$this.find('img');
			var imgWidth=$img.width();
			var imgHeight=$img.height();
			var mOver=false;
			init();

			function init(){
				
				setCss();
				setMouseEvent();
			}
			
			function setCss(){	
				$this.css({'width':imgWidth,'height':imgHeight});
				$img.data({'out':$img.attr('src'),'over':$img.attr('alt')});
			}
			
			function setMouseEvent(){
				$this.bind('click',function(){
					
					
					if($(this).hasClass('clciked')){
						return false;
					}
					$(this).addClass('clciked');
					//接受数据
					checkLogin();
					 if(loginStatus){//已登录
					   $.ajax({
						  type: "GET",
						  url: getRootPath()+"/activityLottery?activityId=520&date="+new Date(),
						  data: "",
						  success: function(data){
							  if(data.status == 0 && data.times<0){
								  var html = '<h3 style=" font-size:40px; padding-top:30px;">很遗憾！</h3><p class="win-prize">今天的3次抽奖次数已用完<p/><p class="tip-info">请明天再来 天天有惊喜</p>';
								  jShare(html,"","");
								  $('#popup_ok').html('X');
								  return;
							  }

							  if(data.times >= 0){
								  if(mOver==false){
									  mOver=true;
									  setAnimation();
								  }
								  winResults(data);
								  
								  return false;
							  }
						  }
					  });
				   }
				});
			}
						
			function setAnimation(){
				if(mOver==true){
					$img.stop()
						.animate({'left':imgWidth/2,'width':0},opt.rotateSpeed,function(){
							$(this).attr({'src':$(this).data('over')});
						})
						.animate({'left':0,'width':imgWidth},opt.rotateSpeed);
					
				}else{
					$img.stop()
						.animate({'left':imgWidth/2,'width':0},opt.rotateSpeed,function(){						
							$(this).attr({'src':$(this).data('out')});
						})
						.animate({'left':0,'width':imgWidth},opt.rotateSpeed);
				}
			}
			
			//判断几等奖显示什么
			function thePrize(data){
				var prompt= "";
				var prize = parseInt(data.message);
				switch(prize) {
					case 0:
						prompt = '<h3 style="padding-top:30px;">很遗憾，你未中奖</h3><p class="win-prize"><em>再试试手气</em><p/><p class="remainder-times">今日你还有'+ data.times +'次机会</p>';
		            	break;
			        case 2:
			        case 4:	
			        	prompt = '<h3>恭喜您抽中了二等奖</h3><p class="win-prize">&yen;<em>500</em>元<p/><p class="win-info">箱包购物节代金券</p><p class="remainder-times">今日你还有'+ data.times +'次机会</p><p class="tip-info">已充入您的个人账户，请登录查收</p>';
			        	break;
			        case 3:
			        	prompt = '<h3>恭喜您抽中了三等奖</h3><p class="win-prize">&yen;<em>50</em>元<p/><p class="win-info">箱包购物节代金券</p><p class="remainder-times">今日你还有'+ data.times +'次机会</p><p class="tip-info">已充入您的个人账户，请登录查收</p>';
			        	break;
			        case 1:
			        	prompt = '<h3>恭喜您抽中了一等奖</h3><p class="win-prize">&yen;<em>1500</em>元<p/><p class="win-info">箱包购物节代金券</p><p class="remainder-times">今日你还有'+ data.times +'次机会</p><p class="tip-info">已充入您的个人账户，请登录查收</p>';
			        	break;
		    }
			    return prompt;
			}
			
			
			function winResults(data){
				
				var thisMsg = thePrize(data); //调用公共方法，返回文字提示
				var html = thisMsg;
				setTimeout(function(){ 
					  jShare(html,"","");
					  $('#popup_ok').html('X');
				  },300);
				  
				  return;
				  //判断得奖名次
			}
	
			
		})			
				
	}			
})(jQuery);

$(function(){
	$('.flip-list li').dbRotateClick2D({});

//跑马灯开始
    var AutoRoll = function(){
	  $(".lottery_list").animate({
		  marginTop:"-24px"
	  },500,function(){
		  $(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
	  });
    };
	var startRoll = setInterval(AutoRoll,3000);
	
	
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
	
	
	/*规则弹层*/
	var $overlay = $('#overlay');
	/**/function modalHidden($ele) {
		$ele.removeClass('modal-in');
		$ele.one('webkitTransitionEnd',function(){
		  $ele.css({"display": "none"});
		  $overlay.removeClass('active');
		});
	}
	$('.rule-pop-btn').click(function(e){
		e.preventDefault();
		var $that = $(this);
		var pop_pic = $(this).attr('data-url');
		$('.modal-bd>p>img').attr('src',pop_pic);
		$overlay.addClass('active');
		$('.modal').css({"display": "block"});
		$('.modal').animate(100,function(){
		  
		  $('.modal').addClass('modal-in');
		});
	});
	$('.btn-modal').click(function(e){
	  modalHidden($('.modal'));
	  e.stopPropagation();
	});
		
	$overlay.click(function(e){
	  if(e.target.classList.contains('overlay')){
		modalHidden($('.modal'));
	  }
	});
	
	
});

function checkLogin (){
	$.ajax({
		type: "GET",
		url: getRootPath()+"/isLogin?"+Math.random(),
		async: false,
		success: function(data){
			/*
			data = 0  未登录
			data = 1  已登录
			*/
			//未登录
			if(data==undefined || data=="" || data == "0" ){
				window.location= getRootPath() + "/meet/redirect/app?id=270";
			}
			//已登录
			if(data == "1"){
				loginStatus = true;
			}
			
		}
	});
}