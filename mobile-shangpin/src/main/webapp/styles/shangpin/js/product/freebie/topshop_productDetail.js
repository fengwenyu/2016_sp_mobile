$(function(){
	//设置全局所有的ajax请求为同步请求
	$.ajaxSetup({
	  async: false
	});
	var secondPropName=$("#secondPropName").val();
	var firstPropName=$("#firstPropName").val();
	var lowestPrice=$("#lowestInfo").attr("lowestPrice");//商品展示价格，之前去的是商品最低价，现在取的是按索引取的价格
	var saleDesc=$("#lowestInfo").attr("saleDesc");
	var marketPrice=$("#lowestInfo").attr("marketPrice");//商品市场价
	var isPromotion=$("#lowestInfo").attr("isPromotion"); //是否促销标识
	//var isSize=$("#isSize").val();
	var isOut=$("#isOut").val();
	var codeSt=$("#codeSt").val();
	//浮层
	$(window).scroll(function(){
		topFixed('.topFix');
	});
	
	showPrice(lowestPrice,marketPrice,saleDesc,isPromotion,"0");
	/********************************/
	if(codeSt=="0"){
		produceState=0;
	}else if(isOut==0){
		produceState=1;
	}else if (isOut==1){
		produceState=2;
	}else{
		produceState=0;
	}
	stateFn(produceState);	
	
	function stateFn(v){
		var stateHtml = '';
		switch(v){
			case 0:
				stateHtml = '<a href="javascript:;" class="get_gift disable">'+$("#msg").val()+'</a>';
				break;
			case 1:
				stateHtml = '<a href="javascript:;" class="get_gift">免费领取同款撞衫</a>';
				break;
			case 2:
				stateHtml = '<a href="javascript:;" class="replace_gift">已售罄  领取其他款式</a>';
				break;
			/*case 3:
				stateHtml = '<a href="javascript:;" class="get_gift disable">该撞衫赠品已抢光</a><a href="javascript:;" class="replace_gift">换领其他款式</a>';
				break;*/
			default:
				break;
		}
		$("#footerHrefBox").html(stateHtml);
	}
	/*换领商品js*/
	if($(".swiper-box").length > 0){
		var swiper = new Swiper('#swiper-container', {
			loop:false,
			//autoplay: 3000,
			//slidesPerView: 'auto',
			//autoplayDisableOnInteraction : false,
			slidesPerView: 'auto',
			spaceBetween: 5, 
		});
		
		$("body").on("click",".replace_gift",function(){
			var $seiperBox = $(".swiper-box");
			/*$.get(getRootPath() + "/product/recommend?timstamp=" + new Date().getTime()+"&p="+$("#pd").val(), function(data) {
				if(data.products==null){
					jShare("没有推荐商品!");
					return;
				}
				var p=data.p;
				var html = "";
				$.each(data.products, function(n, v) {
					html+= "<div class='swiper-slide'>";
					html+= "<a href='../product/freebieDetail?p="+p+"&spu="+v.productId+"'>";
					html+= "<div class='img_box'><img alt='' src='"+v.pic+"' ></div>";
					html+= "<div class='text_box'>";
					html+= "<h3>"+v.brandNameEN+"</h3>";
					html+= "<p>"+v.productName+"</p>";
					html+= " <p class='price'>￥"+v.newLimitedPrice+"</p>";
					html+= "</div>";
					html+= "</a>";
					html+= "</div>"
				});
				$("#swiper-container .swiper-wrapper").append(html);
		   });		*/	
			$seiperBox.hasClass("active") ? $seiperBox.removeClass("active"):$seiperBox.addClass("active");
			return false;
		});
	}
	$("body").on("click",".get_gift",function(e){
		e.preventDefault();
		//touch = 0;
		if($(this).hasClass("disable")){
			return false;	
		}
		$(".alProd").show();
		$("body").attr("style","overflow:hidden");
		$("body").bind("touchmove",function(e){
			if(touch==0){
				e.preventDefault();
			}
		})
		$(".all_info_box").slideDown();
	});
	
	
	//评论筛选tab
	$(".comment_tab").find("li").on("click",function(){
		$(this).addClass("cur").siblings().removeClass("cur");	
	})
	
	var isShoppingCart =false; //公共变量
	var commodityNumber = 1;   //公共变量
	//tab切换
	$(".tab_info").find("li").click(function(){
		var $this = $(this);
		var $thisIndex = $this.index();
		$(this).addClass("curr").siblings().removeClass("curr");
		$(".content_info").find(".content_list").eq($thisIndex).show().siblings().hide();
	})		

	//	$("body").bind("touchmove",function(event){event.preventDefault();});

	//点击大图片显示图片的列表
	$(".photo_details").click(function(){
		$(".simple_information").show();
		$(".product_all").hide();
	});

	//点击图片列表中的图片返回大图片显示
	$(".simple_information").find("img").click(function(){
		$(".product_all").show();
		$(".simple_information").hide();
		$("html, body").animate({ scrollTop: 0 }, 120);
	})

	var touch = 1;
	var mre=/^1[34578]\d{9}$/;
		regMail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; 
	

	//提交尺码颜色电话号码
	var isNewUser = false; //是否是新客户
	$(".submit").on("click",".submit_btn",function(){
		gosunmit(this);
		return false;
	});	
	
	function gosunmit(t){
	    var color_info = $(".color_info").find("li.curr").length;
		var size_info = $(".size_info").find("li.curr").length;
		var sizeName = $("#size_name").val();
		//var maxNum = $(".amount_val").attr("maxValue");
		//var choiceNum = parseInt($(".amount_val").val());
		var issecondprop=$(".color_info").find("li.curr").attr("issecondprop");
		if($(".color_info").length > 0 && color_info==0){			
			$(".color_info").find("h3").eq(0).html(""+firstPropName+"："+"<span class='red'>请选择"+firstPropName+"</span>")
		}else if(color_info==0 && $(".size_info").length > 0 && size_info==0){			
			$(".size_info").find("h3").eq(0).html(""+sizeName+"："+"<span class='red'>请选择"+sizeName+"</span>")
		//}else if(choiceNum>maxNum){ //选择的件数大于库存的件数时，提示"库存不足"
		//	return jShare('库存不足',"","");
		}else{
			var id=$(".color_info").find("li.buySold.curr").attr("id");
			var issecondprop=$(".color_info").find("li.curr").attr("issecondprop");
			if(issecondprop=='1'){
				var size_info = $("#second_"+id).find("li.curr").length;
				if(size_info==0){
					$(".size_info").find("h3").html(secondPropName+"："+"<span class='red'>请选择"+secondPropName+"</span>")
					return;
				}
			}	
			$("body").removeAttr("style");
				var skuId=$("#buySku").val();
				var productId=$("#productNo").val();
				var amount=$("#buyCount").val();
				var activityId=$("#topicId").val();
				if(skuId==null||skuId==''||amount==null||amount*1<0){
					return jShare('商品信息有误',"","");
				}
				
				if($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
						return jShare('请输入正确手机号码',"","");
				}else if($.trim($("#J_yzm").val()) == ""){
						return jShare('请输入验证码',"","");
				}else{
					
					$.ajax({
			            type: "POST",
			            url: getRootPath() + "/acivity/isCode",
			            data : {
			            	"phoneNum":$("#J_mobileNum").val(),
							"verifyCode":$("#J_yzm").val()
			    		},
			            dataType: "json",
			            async:false,//同步 !重要,防止修改结算参数页面没有刷新完提交
			            success: function (data) {
			            	if(data.code == 0){
			            		//------
								var $parsentAddrBox = $(t).closest(".info_box");
								if(isNewUser==1){
									$(".new_user").removeClass("hide").siblings(".info_box").addClass("hide");	
								}else{
									var num="";
									$.get(getRootPath() + "/acivity/ajlist?timstamp=" + new Date().getTime(), function(data) {
										if(data.addresses!=null){
											num=1;
											var html = "";
											$.each(data.addresses, function(n, v) {
												var ad=v.provname + v.cityname + v.areaname + v.townname + v.addr;
												var fir ="<li class='curr' aid='"+v.id+"'>";
												var other = "<li aid='"+v.id+"'>";
												var tig = "<p><span class='detail_name'>"+v.name+"</span> <span class='detail_mobile'>"+v.tel+"</span> </p>";
												var a1 = "<p class='detial_addr'>"+ad+"</p>";
												if(n==0){
													html+= fir + tig + a1 +"</li>";
												}else{
													html+= other + tig+ a1 +"</li>";
												}
											});
											$(".addr_content .addr_list").append(html+"<li class='add_addrList'>新地址</li>");
										}
										$(".addr_content .addr_list").append("<li class='add_addrList'>新地址</li>");
								   });
									$(".old_user").removeClass("hide").siblings(".info_box").addClass("hide");
								 }
							}else{
								return jShare('请输入正确验证码',"","");
							}
			            },
			            error: function (error) {
			                alert("数据错误,请重试!");
			            }
			        })
					
					/*//------
					var $parsentAddrBox = $(t).closest(".info_box");
					if(false){
						$(".new_user").removeClass("hide").siblings(".info_box").addClass("hide");	
					}else{
						$.get(getRootPath() + "/acivity/ajlist?timstamp=" + new Date().getTime(), function(data) {
							if(data.addresses!=null){
								var html = "";
								$.each(data.addresses, function(n, v) {
									var ad=v.provname + v.cityname + v.areaname + v.townname + v.addr;
									var fir ="<li class='curr' aid='"+v.id+"'>";
									var other = "<li aid='"+v.id+"'>";
									var tig = "<p><span class='detail_name'>"+v.name+"</span> <span class='detail_mobile'>"+v.tel+"</span> </p>";
									var a1 = "<p class='detial_addr'>"+ad+"</p>";
									if(n==0){
										html+= fir + tig + a1 +"</li>";
									}else{
										html+= other + tig+ a1 +"</li>";
									}
								});
								$(".addr_content .addr_list").append(html+"<li class='add_addrList'>新地址</li>");
							}
							$(".addr_content .addr_list").append("<li class='add_addrList'>新地址</li>");
					   });
						$(".old_user").removeClass("hide").siblings(".info_box").addClass("hide");
					}*/
					$("body").removeAttr("style");
					touch = 1;
				}
			}
		}		
 	
	//判断手机输入框内容长度
	$("#J_mobileNum").on("keyup", function(){
		var len = $(this).val().length;
		if(len == 11){
			$(this).blur();
		}
	});
	$("#J_yzm").on("keyup", function(){
		var len = $(this).val().length;
		if(len == 6){
			$(this).blur();
		}
	});	
	//获取验证码
	var isclick = true;
	$("body").on("click","#passwordGetCode",function(e){
		e.preventDefault();
		//验证码倒计时		
		if(!isclick) return false;
		//手机号码验证
		if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			return jShare('请输入正确手机号码',"","");
		}
		var that = $(this),timeId;
		var num = 60;
		that.text(num+"秒").addClass("cur");
		$.get(getRootPath() + "/product/sendcode?timstamp=" + new Date().getTime()+ "&phoneNum=" + $("#J_mobileNum").val(), function(data) {
			if (data.code == "0") {
				isNewUser=data.user;
			} else if (data.code == "3") {
				jShare("系统繁忙!");
				return;
			} else{
			  alert(data.msg);
			  isNewUser=data.user;
			   return;
			}
			isclick = false;
			timeId = setInterval(function(){
				that.text(--num+"秒")
				if(num == 0){
					clearInterval(timeId);
					that.text("获取验证码").removeClass("cur");
					isclick = true;					
				}				
			},1000);		
	   });
	});
	//
	//老用户选择地址
	$(".all_info_box").on("click",".addr_list li",function(){
		$(this).addClass("curr").siblings().removeClass("curr");	
	});
	
	//新增地址
	$(".all_info_box").on("click",".add_addrList",function(){
		$(".new_user").removeClass("hide").siblings(".info_box").addClass("hide");
	});
	
	//选择颜色点击
	$(".color_info ul li").click(function(){
		var $this = $(this);
		if(!$(this).hasClass("soldOut")){
			$this.addClass("curr").siblings().removeClass("curr");
			$("#buyCount").val("1");
			$(".amount_val").val("1");
			$this.parents("div").children("h3").html("颜色："+$this.html());
			//$(".choice_product").find("span").eq(0).removeClass("red").html("已选择"+firstPropName+$this.find("p").html())
			var id="second_"+$this.attr("id");
			if($this.attr("issecondprop")=='1'){
				$(".size_info").each(function(){
					var second_id=$(this).attr("id");
					if(id==second_id){
						$("#"+second_id).removeAttr("style"); 
						var select=0;
						$("#"+second_id+" li").each(function(){
							if($(this).hasClass('curr')){
								$("#buySku").val($(this).children("#sku").val());
								select=1;
								$(".choice_product").find("span").eq(1).removeClass("red").html("已选择"+secondPropName+$(this).html())
								var salePrice=$(this).children("#salePrice").val();
								var marketPrice=$(this).children("#marketPrice").val();
								var isPromotion=$(this).children("#isPromotion");
							
								showPrice(salePrice,marketPrice,isPromotion,"1");
							}
						});
						if(select==0){
							$("#buySku").val("");
							$(".choice_product").find("span").eq(1).removeClass("red").html("");
						}
					}else{
						$(this).attr("style","display:none");
					}
				});
			}else{
				$(".size_info").hide();
				var salePrice=$("#"+id).children("#salePrice").val();
				var marketPrice=$("#"+id).children("#marketPrice").val();
				var sku=$("#"+id).children("#sku").val();
				var isPromotion=$("#"+id).children("#isPromotion");
				showPrice(salePrice,marketPrice,isPromotion,"1");
				$(".choice_product").find("span").eq(1).removeClass("red").html("");
				$("#buySku").val($("#"+id).children("#sku").val());
				
			}
		}
	});

	//选择尺码点击
	$(".size_info ul li").click(function(e){
		$("#buyCount").val("1");
		$(".amount_val").val("1");
		if($(this).hasClass('size-sold-out')){
			$('.size-detail-info').hide();
			return false;
		}
		var $this = $(this);
		if($this.hasClass("soldOut")){
			e.preventDefault();
			return;
		}
		e.stopPropagation();
		$('.table-box table').children().remove();
		var count = parseInt($this.attr("count"));
		if(count > 0){
			var trContent="<tr>";
			for(var i = 1; i <= count; i++){
				trContent += "<td>" + $this.attr('data-key' + i) + "</td>";
			}
			trContent += "</tr><tr>";
			for(var i = 1; i <= count; i++){
				trContent += "<td>" + $this.attr('data-val' + i) + "</td>";
			}
			trContent += "</tr>";
		
		
			$('.table-box table').append(trContent);
				
			var offLeft = $this.offset().left + $this.width()/2;
			var sizeOffLeft = offLeft- $('.size-detail-info').width()/2;
			var sizeOffRight = $('.size_info').width() - $('.size-detail-info').width();
			var offTop = $this.position().top;
			
			
			if( sizeOffLeft>0&&sizeOffLeft<sizeOffRight){
				$('.size-detail-info').css({left:sizeOffLeft});
				$('.size-detail-info .size-detail-arrow').css({left:'50%',marginLeft:'-10px'});
			}else if( sizeOffLeft>=sizeOffRight){
				
				$('.size-detail-info').css({left:sizeOffRight});
				$('.size-detail-info .size-detail-arrow').css({left:offLeft-sizeOffRight,marginLeft:0});
				
			}else{
				$('.size-detail-info').css({left:0});
				$('.size-detail-info .size-detail-arrow').css({left:offLeft,marginLeft:0});
			}
			$('.size-detail-info').css({top:offTop-65});
			if(sizeOffRight<=0){
				touch = 1;
			}
			if($this.hasClass('curr')){
				if($('.size-detail-info').is(":hidden")){
				  	$('.size-detail-info').show();
				}else{
					$('.size-detail-info').hide();
				  	touch = 0;
				}
				return;
			}
			$this.addClass("curr").siblings().removeClass("curr");
			$('.size-detail-info').show();
			$this.parents("div").children("h3").html($this.html());
		}
		if(!$(this).hasClass("soldOut")){
			$this.addClass("curr").siblings().removeClass("curr");
			$this.parents("div").children("h3").html(secondPropName+"："+$this.html());
			//$(".choice_product").find("span").eq(1).removeClass("red").html("已选择"+secondPropName+$this.html())
			var salePrice=$this.children("#salePrice").val();
			var marketPrice=$this.children("#marketPrice").val();
			var sku=$this.children("#sku").val();
			var isPromotion=$this.children("#isPromotion").val();
			showPrice(salePrice,marketPrice,isPromotion,"1");
			$("#buySku").val(sku);
		}
	
	});
	
	
	$('.size-close').click(function(){
		$('.size-close').parent().hide();
		touch = 0;
	});
	$(document).click(function(){
		$('.size-detail-info').hide();
	});

	//关闭商品弹出层
	$(".close_btn").click(function(){
		$(".alProd").hide();
		$(".all_info_box").slideUp();
		$("body").removeAttr("style");
		touch = 1;
	})
	
	/*收货地址选择省市区*/
	//选择收货地址
	var addrTxt,thisCon,content,that,obj,title,prev,thatDl;
	$("#select_addr").click(function(){
		$.get(getRootPath() + "/acivity/province?timstamp=" + new Date().getTime(), function(data) {
			if(data.length<0){
				jShare("获取地址失败!");
				return;
			}
			var html = "";
			$.each(data, function(n, v) {
				html+= "<dd><a href='javascript:;' id='"+v.id+"'>" +v.name;
				html+= "</a></dd>";
			});
			$("#area_province").append(html);
			$(".new_user").addClass("hide");
			$("#area_overlay").height($(document).height());
			$("#area_overlay, #area_layer").show();
			addrTxt = [];
			return false;
	    });
	});
	
	//返回上一级	
	$(".prev_btn").click(function(){		
		setTimeout(function(){				
			addrTxt.pop();
			content.hide();
			thatDl.show();
			thatDl.find("a").removeClass("cur");
			title.html(thatDl.attr("title"));
			thatDl = thatDl.prev("dl");
			if(title.html() == "省份"){	
				$(".prev_btn").hide();
			}	
		}, 300);
		return false;
	});

	//收货地址的弹层关闭
	$(".close_selet_btn, #area_overlay").click(function(){
		$(".new_user").removeClass("hide");
		$("#area_layer, #area_overlay").hide();
		$("#area_layer h3").show().html("省份");
		$("#area_layer").find("dl").eq(0).show().siblings("dl").hide();
		$("#area_layer dd a").removeClass("cur");
	});
	var sta="";
	/*领取撞衫装*/
	$(".all_info_box").on("click",".getGift_btn",function(){
		var $parseInfoBox = $(this).closest(".info_box"),	
		    mobileNum = ""
			addrTipHtml="",
			address = "",
			mail = "",
			userName = ""; 
		
		if($parseInfoBox.hasClass("old_user")){
			var $currParent = $(".old_user").find(".addr_list").find(".curr");
			userName = $currParent.find(".detail_name").text();	//老用户信息			
			mobileNum = $currParent.find(".detail_mobile").text();
			address = $currParent.find(".detial_addr").text();
			
			var selectLen = $parseInfoBox.find(".curr").length;
			if(!selectLen){
				return jShare('请选择收货地址',"","");
			}
			//addrTipHtml += '<h3 class="title">您填写的电话号码为：</h3><p>';
			//addrTipHtml += mobileNum;
			//addrTipHtml += '</p>';
			addrTipHtml += '<h3 class="title">您的收货信息为：</h3><p class="userbox"><span>';
			addrTipHtml += userName;
			addrTipHtml += '</span><span>';
			addrTipHtml += mobileNum;
			addrTipHtml += '</span></p><p>';
			addrTipHtml += address;
			addrTipHtml += '</p>';
			show_window_tip(addrTipHtml);
			sta=0;
			return false;
		}else{
			if($.trim($("#J_name").val()) == ""){
				return jShare('请填写您的姓名',"","");	
			}
			if($.trim($("#J_province").val()) == ""){
				return jShare('请选择您所在的省市区',"","");	
			}
			if($.trim($("#J_addr").val()) == ""){
				return jShare('请填写您的详细地址',"","");	
			}
			if($.trim($("#J_mail").val()) == "" || !regMail.test($("#J_mail").val())){
				return jShare('请填写您的邮箱信息',"","");	
			}
			mobileNum = $("#J_mobileNum").val();
			address += $("#J_province").val();
			address += $("#J_addr").val();
			mail = $("#J_mail").val();
			userName = $("#J_name").val();
			addrTipHtml += '<h3 class="title">您的收货信息为：</h3><p class="userbox"><span>';
			addrTipHtml += userName;
			addrTipHtml += '</span><span>';
			addrTipHtml += mobileNum;
			addrTipHtml += '</span></p><p>';
			addrTipHtml += address;
			addrTipHtml += '</p>'
			show_window_tip(addrTipHtml);
			sta=1;
			return false;
		}		
	});
	//领取成功弹出层公共方法
	function show_window_tip(val){
		touch = 0;
		$("body").attr("style","overflow:hidden");
		$("body").bind("touchmove",function(e){
			if(touch==0){ e.preventDefault();}
		})
		$("#show_window_con1").children(".addr_con").html(val);
		$("#select-overlay1").addClass("active");
	}
	function close_window_tip(){
		$("body").removeAttr("style");
		touch = 1;	
		$("#select-overlay1").removeClass("active");
	}
	
	//确定
	$("body").on("touchend click",".sure_addr_btn",function(){
		var p=$("#pd").val();
		var skuId=$("#buySku").val();
		var addrTipHtml="";
		if(sta==0){
			var $currParent = $(".old_user").find(".addr_list").find(".curr");
			var aid=$currParent.attr("aid");
			var path = getRootPath();
			$.ajax({
	            type: "POST",
	            url: path + "/order/receive",
	            data : {
	            	"lskuId":skuId,
					"laddressId":aid,
					"p":p
	    		},
	            dataType: "json",
	            async:false,//同步 !重要,防止修改结算参数页面没有刷新完提交
	            success: function (data) {
	            	if(data.code == 3){
						var id=data.id;
						addrTipHtml += '<p>领取成功！</p>';
						addrTipHtml += '<p>您可以登录尚品网查看订单状态，跟踪物流信息。</p>';
						addrTipHtml += '<p><a href="http://m.shangpin.com/download" class="appDown_link">下载尚品APP</a></p>';
					}else if(data.code == 5){
						addrTipHtml += '<p>手慢无，撞衫同款已被领光</p>';
						addrTipHtml += '<p><a href="../acivity/receive" class="getCoupon_link">领取100元优惠券</a></p>';
						resultsssFn(addrTipHtml);
						return false;
					}else{
						addrTipHtml += '<p>'+data.msg+'</p>';
					}
	            },
	            error: function (error) {
	                addrTipHtml +="提交订单失败!";
	            }
	        })
			
		}else{
		   var path = getRootPath();
			//ajax 提交保存收货地址
		   var address = $("#order_address_form").serialize();
		   $.ajax({
	            type: "POST",
	            url: path + "/acivity/addAddresses",
	            data :address,
	            dataType: "json",
	            async:false,//同步 !重要,防止修改结算参数页面没有刷新完提交
	            success: function (data) {
	            	if(data.code == 0){
	            		$.ajax({
	        	            type: "POST",
	        	            url: path + "/order/receive",
	        	            data : {
	        	            	"mailAddress":$("#J_mail").val(),
								"lskuId":skuId,
								"laddressId":data.id,
								"p":p
	        	    		},
	        	            dataType: "json",
	        	            async:false,//同步 !重要,防止修改结算参数页面没有刷新完提交
	        	            success: function (data) {
	        	            	if(data.code == 3){
									var id=data.id;
									addrTipHtml += '<p>领取成功！</p>';
									addrTipHtml += '<p>您可以登录尚品网查看订单状态，跟踪物流信息。</p>';
									addrTipHtml += '<p><a href="http://m.shangpin.com/download" class="appDown_link">下载尚品APP</a></p>';
								}else if(data.code == 5){
									addrTipHtml += '<p>手慢无，撞衫同款已被领光</p>';
									addrTipHtml += '<p><a href="../acivity/receive" class="getCoupon_link">领取100元优惠券</a></p>';
									resultsssFn(addrTipHtml);
									return false;
								}else{
									addrTipHtml += '<p>'+data.msg+'</p>';
								}
	        	            },
	        	            error: function (error) {
	        	            	addrTipHtml +="提交订单失败!";
	        	            }
	        	        })
	            		
	            		
	            	}else{
	            		addrTipHtml += '<p>添加地址失败!</p>';
	            	}
	            },
	            error: function (error) {
	            	addrTipHtml +="添加地址错误,请重试!";
	            }
	     })
	    }
		resultsFn(addrTipHtml);
		return false;
	});
	//取消
	$("body").on("touchend click",".cancel_addr_btn",function(){
		close_window_tip();
		return false;
	});
	
	/*领取成功或失败*/
	function resultsFn(val){
		$(".alProd").hide();
		$(".all_info_box").slideUp();	
		close_window_tip();
		touch = 0;
		$("body").attr("style","overflow:hidden");
		$("body").bind("touchmove",function(e){
			if(touch==0){ e.preventDefault();}
		})
		$("#show_window_con2").html(val);
		$("#select-overlay2").addClass("active");
	}
	
	/*领取成功或成功*/
	function resultsssFn(val){
		$(".alProd").hide();
		$(".all_info_box").slideUp();	
		close_window_tip();
		touch = 0;
		$("body").attr("style","overflow:hidden");
		$("body").bind("touchmove",function(e){
			if(touch==0){ e.preventDefault();}
		})
		stateFn(0);
		$("#show_window_con2").html(val);
		$("#select-overlay2").addClass("active");
	}
	function resultsFnClose(){
		$("body").removeAttr("style");
		touch = 1;	
		$("#select-overlay2").removeClass("active");
	}
	
	$("body").on("click","#select-overlay2",function(e){
		if(e.target.classList.contains('select-overlay')){
			resultsFnClose();
		  }		
	})
	
   $("#area_layer").delegate("a","click",function(){
	        that = $(this),
	        prev = $(".prev_btn");
	        obj = $("#area_layer dd  a");
	        content = $("#area_layer dl");
	        thisCon = that.closest("dl");
	        title = $("#area_layer h3");
	        obj.removeClass("cur");
	        that.addClass("cur");
	        thatDl = thisCon;

	        var dl_id = thisCon.attr("id");
	        if(dl_id == "area_province"){
	            $("#province").val(that.attr("id"));
	            $("#provincename").val(that.text());
	        }else if(dl_id == "area_city"){
	            $("#city").val(that.attr("id"));
	            $("#cityname").val(that.text());
	        }else if(dl_id == "area_county"){
	            $("#area").val(that.attr("id"));
	            $("#areaname").val(that.text());
	        }else if(dl_id == "area_street"){
	            $("#town").val(that.attr("id"));
	            $("#townname").val(that.text());
	        }
	        //选择结果
	        addrTxt.push(that.text());
	        setTimeout(function(){
	            if(thisCon.next("dl").length > 0){
	                prev.show(); // 返回上一级
	                content.hide();
	                thisCon.next("dl").show();
	                title.html(thisCon.next("dl").attr("title"));
	                if(thisCon.next("dl").attr("id") == "area_city"){
	                    $("#area_city").empty();
	                    $.post("../address/city",{proviceId : that.attr("id")},function(data){
	                        $.each(data,function(index,item){
	                            $("#area_city").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
	                        });
	                    },"json");
	                }else if(thisCon.next("dl").attr("id") == "area_county"){
	                    $("#area_county").empty();
	                    $.post("../address/area",{cityId : that.attr("id")},function(data){
	                        $.each(data,function(index,item){
	                            $("#area_county").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
	                        });
	                    },"json");
	                }else if(thisCon.next("dl").attr("id") == "area_street"){
	                    $("#area_street").empty();
	                    $.post("../address/town",{areaId : that.attr("id")},function(data){
	                        $.each(data,function(index,item){
	                            $("#area_street").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
	                        });
	                    },"json");
	                }
	            }else{
	                //返回初始状态
	            	content.hide();
					$(".new_user").removeClass("hide");
					$("#area_overlay, #area_layer,.prev_btn").hide();
					$("#area_layer dl:first").show();
					title.html($("#area_layer dl:first").attr("title"));
	                var length = addrTxt.length -1;
	                if(addrTxt[length] == '关闭'){
	                    $("#J_province").text("省市区");
	                    $("#province").val("");
	                    $("#provincename").val("");
	                    $("#city").val("");
	                    $("#cityname").val("");
	                    $("#area").val("");
	                    $("#areaname").val("");
	                    $("#town").val("");
	                    $("#townname").val("");
	                }else{
	                	$("#J_province").val(addrTxt.join(" "));
	                }
	            }
	        }, 300);
	        return false;
	    });
	
});

function showPrice(salePrice,marketPrice,saleDesc,isPromotion,isLoad){
	var color_info = $(".color_info").find("li.buySold").length;//颜色未售罄的个数
	var size_info = $(".size_info").find("li.buySold").length;//尺码未售罄的个数
	$(".alProdInfo p b").html("&yen;"+salePrice);
	if(isLoad=='0'){//页面首次加载的默认选中情况
		if(color_info<=1&&size_info<=1){//只有一个sku//颜色和尺码均小于等于1代表只有一个sku
			var color_firsr_count=0;
			var buySoldNun=0;
			var count=0;
			if(size_info==0){ //如果没有尺码
				count=$("#second_first_0").children("#count").val();//sku的库存从这个标签上取值
				buySoldNun=$(".color_info").find("li.buySold").attr("flag");
				if(count==0 && color_info>0){
				  color_firsr_count=$("#second_first_"+buySoldNun+"").children("#count").val();//sku的库存从这个标签上取值	
				}
			}else{
				count=$(".size_info").find("li.buySold").children("#count").val();//如果有尺码sku的库存从这个标签上取值
			}
			if(count*1>0 || buySoldNun*1>0){//如果库存大于0
				$(".color_info").find("li").addClass("curr")//默认选中当前颜色
				$(".size_info").find("li").addClass("curr")//默认选中当前尺码
				$(".choice_product").find("span").eq(0).removeClass("red").html("已选择"+firstPropName+$(".color_info").find("li.buySold").find("p").html())
				if(size_info>0){
					$(".choice_product").find("span").eq(1).removeClass("red").html("已选择"+secondPropName+$(".size_info").find("li.buySold").html())
				}
				if(size_info==0){//默认选中购买的sku的值
					if(count==0 && color_firsr_count>0){
					   $(".color_info").find("li").removeClass("curr")//默认选中当前颜色
					   $(".size_info").find("li").removeClass("curr")//默认选中当前尺码					   
					   $(".color_info").find("li.buySold").addClass("curr")//默认选中当前颜色
					   $(".size_info").find("li.buySold").addClass("curr")//默认选中当前尺码
					   $(".color_info").find("h3").eq(0).html(""+firstPropName+"："+ $(".color_info").find("li.buySold").html());
					   $("#buySku").val($("#second_first_"+buySoldNun+"").children("#sku").val());
					}else{
					   $("#buySku").val($("#second_first_0").children("#sku").val());
					}
				}else{
					$("#buySku").val($(".size_info").find("li.buySold").children("#sku").val());
				}
			}else{
				$(".footerBtmFixed div a.buy_btn").attr("buySoldOut","1")//标记售罄
				$("#buyCount").val("0");
			}
		}else if(color_info<=1){//只有一个颜色，但是尺码有多个的时候
			$(".color_info").find("li").addClass("curr");
			//$(".choice_product").find("span").eq(0).removeClass("red").html("已选择"+firstPropName+$(".color_info").find("li.buySold").find("p").html())
		}
	}
}