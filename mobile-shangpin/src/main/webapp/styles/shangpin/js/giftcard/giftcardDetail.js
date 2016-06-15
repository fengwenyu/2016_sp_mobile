$(function(){

	//显示导航浮层方法
	$(window).scroll(topFixed);
	var menuTopHeight=0;
	if($('.topFix').length>0){
	  menuTopHeight = $('.topFix').offset().top;
	}
	function topFixed(){	
		var scrolls = document.body.scrollTop;
		if (scrolls > menuTopHeight) {
			$('.topFix').css({
				position: "fixed"
			});
			$('body .container').css('margin-top',45);    
		
		}else {
			$('body .container').css('margin-top',0); 
			$('.topFix').css({
				position: "static"
			});
			   
		}
	};
	
	var tabFlag=1;
	//tab切换
	$(".tab_info").delegate('li','click touchstart',function(){
		
		var $this = $(this);
		var $thisIndex = $this.index();
		$(this).addClass("curr").siblings().removeClass("curr");

		$(".content_info").find(".content_list").eq($thisIndex).show().siblings().hide();

		var id=$this.attr("id");
		if(id=="product_tab_4"){
			$("#for_aftersale").hide();
			$(".other_service").hide();
			$(".list-box").hide();
			if(tabFlag==1){
				getComment(0);
				tabFlag=0;
			}
		
			$(".content_info").find(".content_list").eq(3).show().siblings().hide();
		}
		//滚动屏幕时，发请求获取更多的评论数据
//		if($("#commentList").is(":visible")){
//			var pageNum = 1;  //第几页
//			var pageSize = 5; //一页显示几条信息
//
//						
//			$(window).scroll(function(){
//				var scrollTop = $(this).scrollTop(),
//			　　    scrollHeight = $(document).height(),
//			　　    windowHeight = $(this).height(),
//
//				    comment_list="";  //发请求时添加到页面的内容
//
//				//页面滚动到最底部，并且“查看更多评论”的内容显示出来了
//			　　if(scrollTop + windowHeight == scrollHeight && $(".moreComment").is(":hidden")){
//			　　　　
//					//发请求start
//					var data={};
//					data.pageNum = pageNum;
//					data.pageSize = pageSize;
//
//					$.ajax({  //请求数据整理
//						type: "GET",
//						url: "/data/detail/detail_list.json",
//						data: data,
//						dataType: "json",
//						success: function(data){
//							
//							$.each(data.Data, function(index, item){
//								var userHead = item.userHead, //用户头像
//									userName = item.userName, //用户名
//									userSize = item.userSize, //尺码
//									starLevel = item.starLevel, //星级
//									dataTime = item.dataTime,   //日期
//									userComment = item.userComment, //用户评价
//									commentImg = item.commentImg, //评价图片
//									commodityInformation = item.commodityInformation, //商品信息
//									userInformation = item.userInformation, //用户信息
//									spReply = item.spReply //尚品回复
//						        
//						        /*拼出评价数据 start*/
//						        comment_list+= '<div class="content_comment">';
//				                comment_list+= '<div class="comment_user">';
//				                comment_list+= '<div class="user_cont">';
//				                comment_list+= '<a href="#">';
//				                comment_list+= '<img src="'+userHead+'" width="35px" height="35px" style="opacity: 1;">';
//				                comment_list+= '</a>';
//				                comment_list+= '<span class="user_name">'+userName+'</span>';
//				                comment_list+= '<span class="user_size">尺码：'+userSize+'</span>';
//				                comment_list+= '</div>';
//				                comment_list+= '<div class="user_class">';
//				                comment_list+= '<span class="starlevel_'+starLevel+'"></span>';
//				                comment_list+= '<span class="comment_time">'+dataTime+'</span>';
//				                comment_list+= '</div>';
//				                comment_list+= '</div>';
//
//				                comment_list+= '<div class="comment_cont">';
//				                    
//				                comment_list+= '<p>'+userComment+'</p>';
//				                comment_list+= '</div>';
//				                comment_list+= '<div class="comment_img">';
//
//				                if(commentImg.length>0){ //判断评价用户是否上传了图片
//				                	comment_list+= '<ul>';
//				                	for(i=0; i<commentImg.length; i++){
//				                		comment_list+= ' <li><img src="'+commentImg[i].imgName+'" width="50px" style="opacity: 1;"></li>';
//				                	}
//					                comment_list+= '</ul>';
//				                }
//
//				                comment_list+= '<p>';
//				                comment_list+= '<span>评价商品：'+commodityInformation+'</span>';
//				                comment_list+= '<span>用户信息：'+userInformation+'</span>';
//				                comment_list+= '</p>';
//				                comment_list+= '</div>';
//
//				                if(spReply!=""){ //判断是否有客服回复
//				                	comment_list+= '<div class="comment_reply">';
//					                comment_list+= '<i></i>';
//					                comment_list+= '<h6>尚品客服回复：</h6>';
//					                comment_list+= '<p>'+spReply+'</p>';
//					                comment_list+= '</div>';
//				                }
//
//				                comment_list+= '</div>';
//						        /*拼出评价数据 end*/
//
//							});
//							pageNum++;
//							$(".comment_height").append(comment_list);
//						},
//						error : function(){
//							alert("请求数据失败！");
//						}
//					});
//					//发请求end
//			　　}
//			})
//		}

	});


	//评论--查看更多评论
	$(".moreComment").click(function(){
		var $this = $(this);
		$this.parents(".content_list").find(".content_comment").show();
		
		$this.addClass("hidden");
		$(".putAwayComment").removeClass("hidden");
	});

	//评论--收起评论
	$(".putAwayComment").click(function(){
		var $this = $(this);
		$this.parents(".content_list").find(".comment_height").find(".content_comment").each(function(){
			var thisIndex = $(this).index();
			if(thisIndex<3){
				$(this).show();
			}else{
				$(this).hide();
			}
		});
		$this.addClass("hidden");
		$(".moreComment").removeClass("hidden");
	});
	
	
	//点击收藏效果
	$(".collection_commodity").click(function(){
        if(!$(this).hasClass("already_collection")){
        	collect();
        }else{
        	cancleCollect();
        
        }
        
    })
	$(".alProd, .alProdInfo").hide();	
var touch = 1; //公共变量控制是否滑动屏幕
	
	//点击“加入购物车”,“立即购买”按钮
	$(".add_btn, .buy_btn").click(function(){
//		var path = getRootPath();
//		window.location.href=path+"/buy/now?productId="+productId+"&skuId="+skuId+"&amount="+amount+"&region=1&activityId="+activityId;
		touch = 0;
		$(".alProd").height(document.documentElement.clientWidth).show();
		$("body").attr("style","overflow:hidden");
		$("body").bind("touchmove",function(e){
			if(touch==0){
				e.preventDefault();
			}
		})
		$(".alProdInfo").show();
		$(".alProd").css('height','465px');
	});
	//关闭商品弹出层
	$(".close_btn").click(function(){
		$(".alProd, .alProdInfo").hide();
		$("body").removeAttr("style");
		touch = 1;
	})
	
	//选择商品后点击“确定”购买
	$(".submit_btn").click(function(){
		var skuId=$("#buySku").val();//尺码
		var productId=$("#productNo").val();//商品编号
		var amount=$("#buyCount").val();//购买数量
		var activityId=$("#topicId").val();//活动编号
		var cardType = $('#cardType').val();
		if(amount*1<0){
			return jShare('商品信息有误',"","");
		}
		var path = getRootPath();
		
		window.location.href=path+"/giftCard/giftProcess?productId="+productId+"&skuId="+skuId+"&amount="+amount+"&type="+cardType+"&mrandom="+Math.random();

	});
	//添加商品数量点击
	$(".amount_add").click(function(){
		var cardType = $('#cardType').val();
		var amount_val = $(".amount_val").val();
		if(cardType==2){
			var skuCount=$("#count").val();
			if(skuCount>10){
				if(amount_val ==10){
					$(".amount_val").val(10);
					jShare("抱歉！您最多只能购买10件!","","");
					return;
				}
			   $(".amount_val").val(++amount_val);
			}else{
				$(".amount_val").val(skuCount);
				$("#buyCount").val(skuCount);
				jShare("抱歉！您最多只能购买"+skuCount+"件!","","");
				return;
			}
		}else if(cardType==1){
			return jShare("抱歉！您最多只能购买1件!","","");
		}
		commodityNumber=$(".amount_val").val();
		$("#buyCount").val(commodityNumber);
	});
	$(".amount_cut").click(function(){
		var amount_val = $(".amount_val").val();
		if(amount_val>1){
			$(".amount_val").val(--amount_val);
		}
		commodityNumber=$(".amount_val").val();
		$("#buyCount").val(commodityNumber);
	});
	//输入框中直接修改商品数量
	$(".amount_val").keyup(function(){
		var amount_val = $(".amount_val").val();
		var cardType = $('#cardType').val();
		if(cardType==1){
			$(".amount_val").val(1);
			$("#buyCount").val($(".amount_val").val(1));
		}else{
			var skuCount=$("#count").val();
			if(skuCount>10){
				if(amount_val >10){
					$(".amount_val").val(10);
					commodityNumber=$(".amount_val").val();
					$("#buyCount").val(10);
					jShare("抱歉！您最多只能购买10件!","","");
					return;
				}
			}else{
				$(".amount_val").val(skuCount);
				commodityNumber=$(".amount_val").val();
				$("#buyCount").val(skuCount);
				jShare("抱歉！您最多只能购买"+skuCount+"件!","","");
				return;
			}
			if(amount_val<1){
				$(".amount_val").val(1);
				$("#buyCount").val($(".amount_val").val(1));
			}
		}
		
		commodityNumber=$(".amount_val").val();
		$("#buyCount").val(commodityNumber);
	})

});
