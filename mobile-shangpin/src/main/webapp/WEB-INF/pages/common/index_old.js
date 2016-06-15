$(function(){
	var url;
	var path = getRootPath();
	var isApp = $("#isAPP").val();
	//导航图轮播
	var mySwiper = new Swiper('#swiper-container1',{
		loop:true,       //循环切换
		autoplay: 2000,  //自动播放
		autoplayDisableOnInteraction : false, //swiper之后自动切换不会停止
		pagination:'.swiper-pagination', //分页
		paginationClickable: true,
	});
	
	/*点击领券*/
	$(".con").delegate(".icon_type","click",function(e){
		e.preventDefault();//阻止默认事件
		var $this = $(this);
		url = $this.siblings("a").attr("href");
		//判断券的状态
		var activeCode = $this.attr("id");
		$.post(path + "/dailyCoupon/get/coupon",{
			"activeCode" : activeCode
		},function(data){
			if(data.code == '5'){//未登录
				if(isApp){
					window.location.href = path + "/accountaction!loginui?callback=" + $("#basePath").val() + "/dailyCoupon/index";
				}else{
					window.location.href = path + "/login?back=/dailyCoupon/index";
				}
			}else if(data.code == '1'){//领取成功
				//show_window_tip();
				touch = 0;
				$(".con").attr("style","overflow:hidden");
				$(".con").bind("touchmove",function(e){
					if(touch==0){ e.preventDefault();}
				})
				$(".select-overlay").addClass("active");
			}else if(data.code == '0'){//已领取
				/*$(".tip-message").text("您已领取成功");
				tipLayer();*/
				if(isApp){
					alert("ShangPinApp://phone.shangpin/actiongowebview?title=天天抢券&url=" + url.replace('&', '8uuuuu8'));
					window.location.href = "ShangPinApp://phone.shangpin/actiongowebview?title=天天抢券&url=" + url.replace('&', '8uuuuu8');
				}else{
					window.location.href = url;
				}
			}else if(data.code == '2'){//已领光
				/*$(".tip-message").text("已领光");
				tipLayer();*/
				if(isApp){
					alert("ShangPinApp://phone.shangpin/actiongowebview?title=天天抢券&url=" + url.replace('&', '8uuuuu8'));
					window.location.href = "ShangPinApp://phone.shangpin/actiongowebview?title=天天抢券&url=" + url.replace('&', '8uuuuu8');
				}else{
					window.location.href = url;
				}
			}else{//领取失败
				$(".tip-message").text("领取失败，再试试~");
				tipLayer();
			}
			
		},"json");
	});
	
	//继续领券
	$(".continue_btn").click(function(e){
		show_window_down();
		window.location.href = path + "/dailyCoupon/index";
		return false;
	});
	
	//立即使用
	$(".use_btn").click(function(e){
		e.preventDefault();
		console.log("url:" + url);
		show_window_down();
		window.location.href = url;
		//跳转到活动页面
		return false;
	});
	
	/*//领取成功弹出层公共方法
	function show_window_tip(){
		touch = 0;
		$(".con").attr("style","overflow:hidden");
		$(".con").bind("touchmove",function(e){
			if(touch==0){ e.preventDefault();}
		})
		$(".select-overlay").addClass("active");
		//继续领券
		$(".con").on("touchend click",".continue_btn",function(){
			show_window_down();
			window.location.href = path + "/dailyCoupon/index";
			return false;
		});
		//立即使用
		$(".con").on("touchend click",".use_btn",function(){
			show_window_down();
			window.location.href = url;
			//跳转到活动页面
			return false;
		});
	}*/
	function show_window_down(){
		$("body").removeAttr("style");
		touch = 1;	
		$(".select-overlay").removeClass("active");
	}
	
	//领取失败提示层
	function tipLayer(){
		$('.tip-overlay').show();
		$('.tip-container').show();
		setTimeout(function(){
			$('.tip-overlay').hide();
			$('.tip-container').hide();
		},2000);		
	}
	
	//M站排序选项
	var scrollTop = 0,thisTop =0,touch =0,
		$selectFixedTop = $(".menu_nav").offset().top;
		thisTop = $selectFixedTop;
	
	$(".filte_btn").on("click",function(e){
		e.stopPropagation();
		var that = this;
		show_box_window(".filte_btn","cur","#filte_list");						
		return false;	
	});
	function show_box_window(ele,ele_class,ele_list){
		if(!$(ele).hasClass(ele_class)){
			$(ele).addClass(ele_class);
			$(ele_list).stop(true,true).slideDown(300,function(){
				$(".Mask").show();
			});						
			touch = 0;
			$("body").bind("touchmove",function(e){
				if(touch==0){ e.preventDefault();}
			})
		}else{
			$(ele).removeClass(ele_class);
			$(ele_list).stop(true,true).slideUp(300,function(){
				$(".Mask").hide();
			});	
			touch = 1;
		}
	}
	
	//根据券的类型筛选列表
	$(".filte_menu").on("click","li",function(){
		
		if(($(this).hasClass("cur"))){
			$(this).removeClass("cur");
			var type = "";
		}else{
			$(this).addClass("cur").siblings().removeClass("cur");
			var type = $(this).attr("id");
		}
		var order = $("#order").val();
		var pageSize = $("#pageSize").val();
		$.post(path + "/dailyCoupon/ajax/list",{
			pageIndex : "0",
			pageSize : pageSize,
			order : order,
			type : type
		},function(data){
			$(".con").empty();
			if(data != null & data.dailyCouponsList.list.length > 0){
				$("#pageIndex").val(data.pageIndex);
				$("#hasMore").val(data.hasMore);
				$("#type").val(type);
				if(data.hasMore == '0'){
					$(".load_btn").remove();
				}
				$.each(data.dailyCouponsList.list,function(index, item){
					$li = $("<li></li>");
					$h3 = $("<h3 class='classes_title'></h3>");
					$h3.text(item.desc);
					if(item.type == '0'){
						$div = $("<div class='list_box yellow'></div>");
					}else if(item.type == '1'){
						$div = $("<div class='list_box red'></div>");
					}else if(item.type == '2'){
						$div = $("<div class='list_box purple'></div>");
					}
					if(item.statusCode == '0'){//已领取
						$div2 = $("<div class='type type3'></div>");
					}else if(item.statusCode == '1'){//未领取
						$div2 = $("<div class='type type1'></div>");
					}else if(item.statusCode == '2'){//已领光
						$div2 = $("<div class='type type2'></div>");
					}
					$a = $("<a></a>")
					$a.attr("href",item.url);
					$img = $("<img/>")
					$img.attr("src",item.pic.substring(0,item.pic.indexOf('-')) + "-116-116.jpg");
					$a.append($img);
					$div3 = $("<div class='info'></div>");
					$p = $("<p></p>");
					$strong = $("<strong class='num'></strong>");
					$strong.text(item.amount);
					$span = $("<span class='type-text'></span>");
					if(item.type == '0'){
						$strong2 = $("<strong>满减券</strong>");
					}else if(item.type == '1'){
						$strong2 = $("<strong>现金券</strong>");
					}else if(item.type == '2'){
						$strong2 = $("<strong>礼券包</strong>&nbsp;");
					}
					//$span.append($strong2);
					$span.append($strong2).append(item.condition);
					$time = $("<time>有效期<br> " + item.expireDate +"</time>");
					$i = $("<i class='icon_type'></i>");
					$i.attr("id",item.activeCode);
					$p.append($strong).append($span);
					$div3.append($p).append($time);
					$div2.append($a).append($div3).append($i);
					$div.append($div2);
					$li.append($h3).append($div);
					$(".con").append($li);
				});
			}
		},"json");
		isHasclass(".filte_btn","click","cur");
		return false;
	});
	
	//金额排序
	$(".price_btn").click(function(e){
		var $this = $(this);
		e.stopPropagation();
		//remove
		//$(this).hasClass("cur") ? $(this).removeClass("cur") : $(this).addClass("cur");
		if($(this).hasClass("disabel")){
			$(this).addClass("up");
			$(this).removeClass("disabel");
		}else{
			if($(this).hasClass("up")){
				$(this).removeClass("up");
				$(this).addClass("down");
			}else if($(this).hasClass("down")){
				$(this).removeClass("down");
				$(this).addClass("up");
			}
		}
		
		if($(this).hasClass("up")){
			var order = "2";
		}else if($(this).hasClass("down")){
			var order = "1";
		}else if($(this).hasClass("disabel")){
			var order="";
		}
		var type = $("#type").val();
		var pageSize = $("#pageSize").val();
		$.post(path + "/dailyCoupon/ajax/list",{
			pageIndex : "0",
			pageSize : pageSize,
			order : order,
			type : type
		},function(data){
			$(".con").empty();
			if(data != null & data.dailyCouponsList.list.length > 0){
				$("#pageIndex").val(data.pageIndex);
				$("#hasMore").val(data.hasMore);
				$("#order").val(order);
				if(data.hasMore == '0'){
					$(".load_btn").remove();
				}
				$.each(data.dailyCouponsList.list,function(index, item){
					$li = $("<li></li>");
					$h3 = $("<h3 class='classes_title'></h3>");
					$h3.text(item.desc);
					if(item.type == '0'){
						$div = $("<div class='list_box yellow'></div>");
					}else if(item.type == '1'){
						$div = $("<div class='list_box red'></div>");
					}else if(item.type == '2'){
						$div = $("<div class='list_box purple'></div>");
					}
					if(item.statusCode == '0'){//已领取
						$div2 = $("<div class='type type3'></div>");
					}else if(item.statusCode == '1'){//未领取
						$div2 = $("<div class='type type1'></div>");
					}else if(item.statusCode == '2'){//已领光
						$div2 = $("<div class='type type2'></div>");
					}
					$a = $("<a></a>")
					$a.attr("href",item.url);
					$img = $("<img/>")
					$img.attr("src",item.pic.substring(0,item.pic.indexOf('-')) + "-116-116.jpg");
					$a.append($img);
					$div3 = $("<div class='info'></div>");
					$p = $("<p></p>");
					$strong = $("<strong class='num'></strong>");
					$strong.text(item.amount);
					$span = $("<span class='type-text'></span>");
					if(item.type == '0'){
						$strong2 = $("<strong>满减券</strong>");
					}else if(item.type == '1'){
						$strong2 = $("<strong>现金券</strong>");
					}else if(item.type == '2'){
						$strong2 = $("<strong>礼券包</strong>&nbsp;");
					}
					$span.append($strong2).append(item.condition);
					$time = $("<time>有效期<br> " + item.expireDate +"</time>");
					$i = $("<i class='icon_type'></i>");
					$i.attr("id",item.activeCode);
					$p.append($strong).append($span);
					$div3.append($p).append($time);
					$div2.append($a).append($div3).append($i);
					$div.append($div2);
					$li.append($h3).append($div);
					$(".con").append($li);
				});
			}
		},"json");
		//关闭排序层
		isHasclass(".filte_btn","click","cur");
			
	});
	
	
	$(".Mask").click(function(){	
		isHasclass(".filte_btn","click","cur");
		return false;
	});	

	function isHasclass(ele,t,c){
		if($(ele).hasClass(c)){
			$(ele).trigger(t);
		}
	}
	
	var ready = 1;
	$(window).scroll(function(){
		topFixed(".topFix");
		topFixed(".menu_nav");
		if($("#hasMore").val() == '1'){
			if ($(window).scrollTop() + $(window).height() >= $(document).height()-50){
				if(ready == 1){
					ready = 0;
					var pageIndex = parseInt($("#pageIndex").val()) + 1;
					var pageSize = $("#pageSize").val();
					var order = $("#order").val();
					var type = $("#type").val();
					$.post(path + "/dailyCoupon/ajax/list",{
						pageIndex : pageIndex,
						pageSize : pageSize,
						order : order,
						type : type
					},function(data){
						if(data != null & data.dailyCouponsList.list.length > 0){
							$("#pageIndex").val(data.pageIndex);
							$("#hasMore").val(data.hasMore);
							if(data.hasMore == '0'){
								$(".load_btn").remove();
							}
							$.each(data.dailyCouponsList.list,function(index, item){
								$li = $("<li></li>");
								$h3 = $("<h3 class='classes_title'></h3>");
								$h3.text(item.desc);
								if(item.type == '0'){
									$div = $("<div class='list_box yellow'></div>");
								}else if(item.type == '1'){
									$div = $("<div class='list_box red'></div>");
								}else if(item.type == '2'){
									$div = $("<div class='list_box purple'></div>");
								}
								if(item.statusCode == '0'){//已领取
									$div2 = $("<div class='type type3'></div>");
								}else if(item.statusCode == '1'){//未领取
									$div2 = $("<div class='type type1'></div>");
								}else if(item.statusCode == '2'){//已领光
									$div2 = $("<div class='type type2'></div>");
								}
								$a = $("<a></a>")
								$a.attr("href",item.url);
								$img = $("<img/>")
								$img.attr("src",item.pic.substring(0,item.pic.indexOf('-')) + "-116-116.jpg");
								$a.append($img);
								$div3 = $("<div class='info'></div>");
								$p = $("<p></p>");
								$strong = $("<strong class='num'></strong>");
								$strong.text(item.amount);
								$span = $("<span class='type-text'></span>");
								if(item.type == '0'){
									$strong2 = $("<strong>满减券</strong>");
								}else if(item.type == '1'){
									$strong2 = $("<strong>现金券</strong>");
								}else if(item.type == '2'){
									$strong2 = $("<strong>礼券包</strong>&nbsp;");
								}
								$span.append($strong2).append(item.condition);
								$time = $("<time>有效期<br> " + item.expireDate +"</time>");
								$i = $("<i class='icon_type'></i>");
								$i.attr("id",item.activeCode);
								$p.append($strong).append($span);
								$div3.append($p).append($time);
								$div2.append($a).append($div3).append($i);
								$div.append($div2);
								$li.append($h3).append($div);
								$(".con").append($li);
							});
						}
						ready = 1;
					},"json");
				}
			}
		}
	});
});