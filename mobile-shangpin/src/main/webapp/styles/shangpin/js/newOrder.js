$(function(){
	//页面加载执行公共方法，计算价格
//	selectNumAmountFun();
	var prodNumVal;
	//如果商品数量是一，不能再删除
	$(".prodNum").each(function(){
		if($(this).text()==1){
			$(this).parent(".fillNumber").find(".cut").addClass("disabled")
		}
	})
	//删除商品数量
	$(".order_block").delegate(".fillNumber .cut","click",function(e){
		var $this = $(this);
		var prodNum = $(this).parents(".fillNumber").find(".prodNum");
		var maxNumVal = Number($this.parent(".fillNumber").find(".numberVal").val())
		prodNumVal = Number(prodNum.html());
		//修改数量时选中此商品
		if(!$this.parent(".fillNumber").parent("div").parent("dd").hasClass("dd_fail")){
			if(!$this.parent(".fillNumber").parent("div").parent("dd").find(".input").hasClass("input_curr")){
				$this.parent(".fillNumber").parent("div").parent("dd").find(".input").addClass("input_curr").find("input").attr("checked","checked");
				selectNumAmountFun()
			}
		}
		if($this.hasClass("cut_disable")){
			return false;
		}else{
			if(!$this.parent(".fillNumber").parent("div").parent("dd").hasClass("dd_fail")){
				if(prodNumVal > 1){
					$this.parent(".fillNumber").find(".add").removeClass("disabled");
					prodNum.html(--prodNumVal);
					modify(prodNum.html(),$this.attr("id"));
					if(prodNumVal==1){
						$(this).addClass("disabled");
					}
				}
			}
		}
		
	});
	//添加商品数量
	$(".order_block").delegate(".fillNumber .add","click",function(e){
		var $this = $(this);
		var prodNum = $this.parent(".fillNumber").find(".prodNum");
		var maxNumVal = Number($this.parent(".fillNumber").find(".numberVal").val())
		prodNumVal = Number(prodNum.html());
		var commodityPrice = $this.parent(".fillNumber").find(".commodity_price");
		var comprice = commodityPrice.attr("comprice");
		$this.parent(".fillNumber").find(".cut").removeClass("disabled");
		//修改数量时选中此商品
		if(!$this.parent(".fillNumber").parent("div").parent("dd").hasClass("dd_fail")){
			if(!$this.parent(".fillNumber").parent("div").parent("dd").find(".input").hasClass("input_curr")){
				$this.parent(".fillNumber").parent("div").parent("dd").find(".input").addClass("input_curr").find("input").attr("checked","checked");
				selectNumAmountFun();
			}
		}
		if(prodNumVal<=10){
			if($this.hasClass("add_disable")){
				return false;
			}else{
				if(prodNumVal < maxNumVal&&prodNumVal != 10){
					prodNum.html(++prodNumVal);
					modify(prodNum.html(),$this.attr("id"));
					$(".commodity_price").html()
				}else if(prodNumVal == 10){
					$(".errorPrompt").fadeIn("slow").find("span").html(prodNumVal);
					$(this).addClass("disabled");
					setTimeout(function(){
						$(".errorPrompt").fadeOut("slow");
					},5000)
				}else if(prodNumVal == maxNumVal){
					$(".errorPrompt").fadeIn("slow").find("span").html(prodNumVal);
					$(this).addClass("disabled");
					setTimeout(function(){
						$(".errorPrompt").fadeOut("slow");
					},5000)
				}
			}
		}else{
			--prodNumVal;
			$(".errorPrompt").fadeIn("slow").find("span").html(prodNumVal);
			$(this).addClass("disabled");
			setTimeout(function(){
				$(".errorPrompt").fadeOut("slow");
			},5000)
		}	
		
	});

	//删除商品
	$(".order_block").delegate(".close","click",function(e){
	
		var $this = $(this);
		var thisId = $this.attr("id");
		
		jShare("确定从购物车中删除该商品吗？","",function(result){

			if(result===true){
				/*$("#"+thisId).parents("dd").fadeOut("slow");*/
				if($("#"+thisId).parent().parent().find('dd').length>1){
					/*$("#"+thisId).parent("dd").hide();*/
					$("#"+thisId).parent("dd").fadeOut("slow");
					setTimeout(function(){
						$("#"+thisId).parent("dd").remove();
						del(thisId);
						selectNumAmountFun()
					},600);
			    }else{
			    	$("#"+thisId).parent().parent().fadeOut("slow");
			    /*	$("#"+thisId).parent().parent().hide();*/
					setTimeout(function(){
						$("#"+thisId).parent().parent().remove();
						del(thisId);
						selectNumAmountFun()
					},600);
				}
			}else{
				
			}
		});
		return false;
		
	});
	//计算总金额公共方法
	function selectNumAmountFun(){
		var $selectChoice = $('.order_block').find("a.input_curr");
		var selectNum = $selectChoice.length;
		$('#total_number').text(selectNum);
	}

	//模拟checkbox点击效果
	$('.order_block').delegate('a.input','click',function(e){
		var $this = $(this);
		if(!$this.parents("dd").hasClass("dd_fail")){
			if(!$this.hasClass("input_curr")){
				$this.addClass("input_curr");
				$this.find("input").attr("checked","checked")
			}else{
				$this.removeClass("input_curr");
				$this.find("input").removeAttr("checked")
			}
			setTimeout(updateShowCart,100);
			var dl_length=$(".order_block").find('dd').not('.dd_fail').length;
			var input_curr_length=$(".order_block dd").find('.input_curr').length;
			if(input_curr_length==dl_length){
				$("a.input_all").addClass("inputall_curr");
				$("a.input_all").attr("checked","checked");
			}else{
				$("a.input_all").removeClass("inputall_curr");
				$("a.input_all").removeAttr("checked");
			}

			selectNumAmountFun();
		}
		
	});

	//模拟总金额单选选中效果
	
	$("a.input_all").click(function(){
			var $this = $(this);
			if(!$this.hasClass("inputall_curr")){
				$this.addClass("inputall_curr");
				$this.find("input_all").attr("checked","checked");

				$(".order_block").find("dd").each(function(){
					var $this = $(this);
					if(!$this.hasClass("dd_fail")){
						$this.find(".input").addClass("input_curr").find("input").attr("checked","checked");
					}
				})
				
			}else{
				$this.removeClass("inputall_curr");
				$this.find("input_all").removeAttr("checked");
				$(".order_block").find("dd").each(function(){
					var $this = $(this);
					if(!$this.hasClass("dd_fail")){
						$this.find(".input").removeClass("input_curr").find("input").removeAttr("checked");
					}
				})
			}
			
			setTimeout(updateShowCart,100);
			selectNumAmountFun();
			
	});
	
});

function updateShowCart(){
	var isChecked='';
	$("input[type='checkbox']").each(function(){
		if($(this).attr("checked")){
			isChecked += $(this).val() + "|";
		}
	});

	var path = getRootPath();
	$.ajax({
	    url:path+'/cart/updateShowCart',
	    data:{"isChecked":isChecked},
	    dataType:'json',
	    timeout: 30000,
        error: function (xmlHttpRequest, error) {			            	
                alert("您的网络异常");
            return;
        },
	    success:function(data){
	    	show(data);
	    }
	});
}
function del(id){
	var path = getRootPath();
	var isChecked='';
	$("input[type='checkbox']").each(function(){
		if($(this).attr("checked")){
			isChecked += $(this).val() + "|";
		}
	});
	$.ajax({
	    url:path+'/cart/delete',
	    data:{
	    	"isChecked":isChecked,
	    	"cartDetailId":id
	    },
	    dataType:'json',
	    timeout: 30000,
        error: function (xmlHttpRequest, error) {			            	
                alert("您的网络异常");
            return;
        },
	    success:function(data){
	    	if(data.code=='0'){
	    		show(data);
	    	}else{
	    		 showErr(data.msg);
	    	}
	    }
	});
}
function modify(count,id){
	var path = getRootPath();
	var cartItem=id+"_"+count;
	var isChecked='';
	$("input[type='checkbox']").each(function(){
		if($(this).attr("checked")){
			isChecked += $(this).val() + "|";
		}
	});
	$.ajax({
	    url:path+'/cart/modify',
	    data:{
	    	"isChecked":isChecked,
	    	"cartItem":cartItem
	    },
	    dataType:'json',
	    timeout: 30000,
        error: function (xmlHttpRequest, error) {			            	
                alert("您的网络异常");
            return;
        },
	    success:function(data){
	    	if(data.code=='0'){
	    		show(data);
	    	}else{
	    		showErr(data.msg);
	    	}
	    	
	    }
	});
}
function topay(checkAPP){
	var shopids = "";
	var arr = $("input:checkbox[name='shopid']:checked");
	if(arr.length > 10){
		alert("一次最多只能结算10件商品！");
		return;
	}
	$("input[type='checkbox']").each(function(){
		if($(this).attr("checked")){
			shopids += $(this).val() + "|";
		}
	});
	if(shopids == ""){
		jShare("请选择您购买的商品","",function(result){},false);
		$("#popup_cancel").hide();
		return;
	}
	$("#shopids").val(shopids);
	
	var hrefV='';
	if(checkAPP){
      hrefV = "shangPinApp://phone.shangpin/actiongosettlement?shopDetailId="+$("#shopids").val();
  	}
	if(hrefV!=''){        	
		window.location.href =hrefV;
	}else{        		
		$("#cartForm").submit();
	}
}
function show(data){
	
	var path = getRootPath();
	var len=0
	if(data.shopCartItem.cartList!=null){
		len=data.shopCartItem.cartList.length;
	}
	
	var checkAPP=$('#checkApp').val()
	if(len>0){
		$(".order_block").children().remove();
		$("#total_amount").text(data.shopCartItem.totalAmount);
		if(data.shopCartItem.spareAmount*1>0){
			  $(".total_amount").css("margin-top","5px");
			  $(".save_amount").html("已节省：&yen;<em>"+data.shopCartItem.spareAmount+"</em>")
		}else{
			  $(".total_amount").css("margin-top","15px");
			  $(".save_amount").html("")
		}
		console.log(data.shopCartItem.prompt);
		$(".coupon-tip").html("");
		if(data.shopCartItem.prompt!=null&&data.shopCartItem.prompt!=""){
			$(".coupon-tip").html(data.shopCartItem.prompt);
		} 
		$.each(data.shopCartItem.cartList,function(index,item){
	    	$dl=$("<dl></dl>");
	    	if(item.title!=null&&item.title!=""){
	    		$dt=$("<dt></dt>");
	    		var href;
    			if(checkAPP){
					href = "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid="+item.id;
			  	}else{
			  		href =path+ "/subject/product/list_"+item.id;
			  	}
    	    	$a=$("<a></a>");
    	    	$a.attr({"href":href});
    	    	$span=$("<span></span>").text("满减");
    	    	$i=$("<i></i>").text(item.title);
    	    	$em=$("<em></em>").html("&gt;");
    	    	
    	    	$a.append($span);
    	    	$a.append($i);
    	    	$a.append($em);
    	    	$dt.append($a);
    	    	$dl.append($dt);
	    	}
	    
	    	$.each(item.productList,function(n,v){
	    		$dd=$("<dd></dd>");
	    		if(v.msgType=='1'){
	    			$dd.addClass("clr dd_fail");
	    		}else if(v.msgType=='3'){
	    			$dd.addClass("clr dd_fail");
	    		}else if(v.msgType=='4'){
	    			$dd.addClass("clr dd_fail");
	    		}else{
	    			$dd.addClass("clr");
	    		}
	    		$a1=$("<a></a>");
	    		$a1.attr({"href":"javascript:;","id":v.cartDetailId,"class":"close"}).text("关闭");
	    		$a2=$("<a></a>");
	    		if(v.isChecked=='1'){
	    			$a2.addClass("input input_curr");
	    			$a2.attr({"href":"javascript:;"});
    	    		$input=$("<input/>");
    	    		$input.addClass("choice_commodity");
    	    		$input.attr({"type":"checkbox","sku":v.sku,"name":"shopid","checked":"checked","value":v.cartDetailId});
	    		}else{
	    			$a2.addClass("input");
	    			$a2.attr({"href":"javascript:;"});
    	    		$input=$("<input/>");
    	    		$input.addClass("choice_commodity");
    	    		$input.attr({"type":"checkbox","sku":v.sku,"name":"shopid","value":v.cartDetailId});
	    		}
	    		
	    	
	    		$a2.append($input);
	    		$a3=$("<a></a>");
	    		if(v.msgType != '3' && v.msgType != '4'&& v.msgType != '1'){
	    			var hrefV;
	    			if(checkAPP){
						hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="+v.spu;
				  	}else{
				  		hrefV =path+ "/product/detail?productNo="+v.spu;
				  	}
	    		
	    			$a3.attr({"href":hrefV});
	    		}
	    		$span=$("<span></span>");
	    		$span.addClass("fail");
	    		$img=$("<img/>");
	    		$img.attr({"src":v.pic,"width":"70","height":"94"});
	    		$a3.append($span);
	    		$a3.append($img);
	    		$h2=$("<h2></h2>");
	    		$a4=$("<a></a>");
	    		$a4.attr({"href":hrefV});
	    		$span1=$("<span></span").text(v.brand);
	    		$span2=$("<span></span").text(v.name);
	    		$h2.append($a4);
	    		$a4.append($span1);
	    		$a4.append($span2);
	    		$h2.append($a4);
	    		$p=$("<p></p>");
	    		$.each(v.attribute,function(n1,v1){
	    			$span3=$("<span></span").text(v1.name+":"+v1.value);
	    			if(n1==0){
	    				$span3.addClass("color");
	    			}
	    			$p.append($span3);
	    		})
	    		$div=$("<div></div>");
	    		$div.addClass("clr");
	    		$span4=$("<span></span");
	    		$span4.addClass("price");
	    		$b=$("<b></b>").html("&yen;");
	    		$em=$("<em></em>").text(v.price);
	    		$em.addClass("commodity_price");
	    		$em.attr({"comprice":v.price});
	    		$b.append($em);
	    		$span4.append($b);
	    		$span4.append("×")
	    		$div1=$("<div></div>");
	    		$div1.addClass("fillNumber");
	    		$a5=$("<a></a>").text("-");
	    		$a5.addClass("cut");
	    		$a5.attr({"href":"javascript:;","id":v.cartDetailId});
	    		$span5=$("<span></span>").text(v.quantity);
	    		$span5.addClass("prodNum");
	    		$a6=$("<a></a>").text("+");
	    		$a6.addClass("add");
	    		$a6.attr({"href":"javascript:;","id":v.cartDetailId});
	    		$input1=$("<input/>");
	    		$input1.addClass("numberVal");
	    		$input1.attr({"type":"hidden","value":v.count});
	    		$div1.append($a5);
	    		$div1.append($span5);
	    		$div1.append($a6);
	    		$div1.append($input1);
	    		$div.append($span4);
	    		$div.append($div1);
	    		$dd.append($a1);
	    		$dd.append($a2);
	    		$dd.append($a3);
	    		$dd.append($h2);
	    		$dd.append($p);
	    		$dd.append($div);
	    	
	    		
	    		if(v.priceTag!=null&&v.priceTag!=""){
	    			$em_pt=$("<em></em>");
		    		$em_pt.addClass("premium-price").text(v.priceTag);
		    		$dd.append($em_pt);
	    		}
	    	
	    		$dl.append($dd);
	    		$(".order_block").append($dl);
	    	})
		})
		inputAllCount()
	}else{
		location.href =path+"/cart/list"
	}
}
function showErr(msg){
	$('#pop_error_msg').html(msg);
	$('#pop_error_overlay').show();
	$('#pop_error').show();
	
	setTimeout(function(){
		$('#pop_error_overlay').hide();
		$('#pop_error').hide();
	},3000);
}

function inputAllCount(){
	var dl_length=$(".order_block").find('dd').not('.dd_fail').length;
	var input_curr_length=$(".order_block dd").find('.input_curr').length;
	if(input_curr_length==dl_length){
	$("a.input_all").addClass("inputall_curr");
	$("a.input_all").attr("checked","checked");
	}else{
	$("a.input_all").removeClass("inputall_curr");
	$("a.input_all").removeAttr("checked");
	}
}