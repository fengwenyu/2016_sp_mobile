$(window).scroll(function(){
	topFixed('.topFix');
});
var ready=1;
var getOrderList = function(){
	if(ready==1){
		ready=0;
		var path = getRootPath();
		var status = $(".moreButton").attr("statusType");
		var pageIndex = $(".moreButton").attr("pageIndex");
		$.post(path+"/order/getMore",{
			"statusType":status,
			"pageIndex" : pageIndex
		},
		function(entityJson){
			var st = entityJson.statusType;
			if(st==null){
				st="";
			}
			$(".moreButton").remove();
		    if(entityJson.code == 0){
		    	var haveMore=entityJson.result.haveMore;
	    		if(haveMore !="0"){
	        		$(".loading").hide();
	        		$(".moreButton").remove();
	        		$(".moreButton").attr("statusType",st);
	        	}else{
	        		$(".loading").hide();
	        		$(".moreButton").remove();
	        		$(".alList_moreBtn").css("display", "none");
	        	}
	        	if( entityJson.result== null){
	        		$(".moreButton").remove();
	        		$(".order_list").hide();
	        		$(".order_null").show();
	        		return;
	        	}
	        	if(entityJson.result.list.length <= 0){
	        		$(".moreButton").remove();
	        		$(".order_list").hide();
	        		$(".order_null").show();
	        		return;
	        	}
	        	var pageMoreButton=1;
	        	pageMoreButton=entityJson.pageIndex;
	        	
	        	var addSelector = '.paymet_block';
	        	$( entityJson.result.list).each(function(n,v){
	        		$fieldset=$("<fieldset> </fieldset>");
	        		$fieldset.addClass("order-box");
	        		$p=$("<p></p>").addClass("selected order-type");
	        		$a=$("<a></a>")
	        		$a.attr({"href":"#"}).html(v.orderTypeDesc +"<time id='setTime'>"+v.date +"</time>");
	        		$p.append($a);
	        		$fieldset.append($p)
	        		$(v.orderList).each(function(n1,v1){
		        		$p2=$("<p></p>").addClass("selected");
		        		var desc;
		        		if(v1.isSplitOrder!='1'){
		        			desc= "主订单"
		        		}else{
		        			desc= "子订单"
		        			if(v.orderList.length>1){
		        				desc= "子订单"+(n1+1)
		        			}
		        		}
		        		$a2=$("<a></a>").attr({"href":"#"}).html("<strong style='color:#888'>"+desc+"：</strong>"+v1.orderId +"<time style='color:#c62026' >"+v1.statusName +"</time>");
		        		$p2.append($a2);
		        		$fieldset.append($p2);
		        		var flag=0;
		        		$(v1.detail).each(function(n2,v2){
		        			$p3=$("<p></p>").addClass("pord_info");
		        			var href=path+"/order/detail?isSplitOrder="+v1.isSplitOrder+"&orderId="+v1.orderId+"&mainOrderId="+v.mainOrderId;
		        			$a3=$("<a></a>").attr({"href":href}).addClass("clr");
		        			var pic = v2.pic.substring(0,v2.pic.indexOf('-')) + "-112-134.jpg";
		        			$img=$("<img/>").attr({"src":pic,"width":"56","height":"67"}).addClass("clr");
		        			$ins=$("<ins></ins>")
		        			$i=$("<i></i>")
		        			if(v2.postArea=='2'){
		        				if(v2.countryPic!=''){
		        					var countryPic= v2.countryPic.replace('-10-10','-40-26');
			        				$span=$("<span></span>").html("<img src="+countryPic + " width='20' height='13'>");
			        				$i.append($span);
		        				}
		        				
		        			}
		        			if(v2.giftType=='0'){
		        				$i.append(v2.brandNameEN+"<br />"+v2.name);
		        			}else{
		        				$i.append(v2.name);
		        			}
		        			
		        			$ins.append($i);
		        			$(v2.attribute).each(function(n3,v3){
		        				if(v3.value!=null&&v3.value!=''&&v2.giftType=='0'){
		        					$em=$("<em></em>").text(v3.name+"："+v3.value);
			        				$ins.append($em)
		        				}
		        			})
		        			$ins.append(" <br />");
		        			$em1=$("<em></em>").html(v2.priceTitle+"：&yen;"+v2.price);
		        			$em2=$("<em></em>").html(v2.quantityTitle+"：&yen;"+v2.quantity);
		        			$ins.append($em1);
		        			$ins.append($em2);
		        			$a3.append($img).append($ins);
		        			$p3.append($a3);
		        			$fieldset.append($p3);
		        			
		        			
		        			$p4=$("<p></p>").addClass("pay-model-p").html("<a>支付方式：<strong>"+v.payment.desc +"</strong></a><span>支付金额：<strong>&yen;"+v1.orderAmount+"</strong></span>")
			        		
		        			$div=$("<div></div>").addClass("btn-icon clr");
			        		var herfv;
			        		
			        		console.log(v1.canLogistics);
			        		if(v1.canLogistics=="1"){
			        			$a4=$("<a></a>")
			        			flag=1;
			        			herfv=path+"/logistice/list?orderId="+v1.orderId+"&postArea="+v2.postArea;
			        			$a4.attr({"href":herfv}).addClass("cur").text("订单跟踪");
			        			$div.append($a4);
			        		}
			        		if(v.canCancel=="1"){
			        			$a4=$("<a></a>")
			        			flag=1;
			        			$a4.attr({"href":"javascript:cancelOrder("+v1.orderId+"');'"}).addClass("cancel-order-btn").text("取消订单");
			        			$div.append($a4);
			        		}
			        		if(v.canPay=="1"){
			        			$a4=$("<a></a>")
			        			flag=1;
			        			herfv=path+"/order/pay/normal?orderId="+v1.orderId;
			        			if(v1.postArea=='2'){
			        				herfv=path+"/overseas/pay/continue?orderId="+v1.orderId;
			        			}
			        			$a4.attr({"href":herfv}).addClass("red").html("立即支付 <em id='leftTime' leftTime=''></em>");
			        			$div.append($a4);
			        		}
			        		if(v1.canConfirm=="1"){
			        			$a4=$("<a></a>")
			        			flag=1;
			        			$a4.attr({"href":"javascript:finishOrder("+v1.orderId+"');'"}).addClass("red").text("确认收货");
			        			$div.append($a4);
			        		}
		        		})
		        	
		        		
		        		$fieldset.append($p4)
		        		if(flag==1){
		        			$fieldset.append($div);
		        		}
		        		
	        		})
		        	$(addSelector).append($fieldset);
	        	});
	        	ready=1;
			    if(haveMore== "1"){
			    	$(".alContent").append('<a id="haveMore" class="payment_btn moreButton" href="javascript:getOrderList();" pageindex="'+pageMoreButton+'" statustype="'+status+'">点击查看更多</a>');
			    }	
		   }else if(entityJson.code == 2){
				window.location.href=path+"/user/login";
		   }else{
				$(".moreButton").remove();
	    		$(".order_list").hide();
	    		$(".order_null").show();
		   }
		
		},"json");	
	
	}
	
	
};