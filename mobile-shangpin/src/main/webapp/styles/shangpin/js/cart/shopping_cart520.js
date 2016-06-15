$(function(){
	
	var prodNumVal;
	var canModify = true;
	//如果商品数量是一，不能再删除
	$(".prodNum").each(function(){
		if($(this).text()==1){
			$(this).parent(".fillNumber").find(".cut").addClass("disabled")
		}
	})
	
	//删除商品数量
	$(".alContent").delegate(".fillNumber .cut","click",function(e){
		
		if(canModify){
			canModify=false;
			var $this = $(this);
			var prodNum = $(this).parents(".fillNumber").find(".prodNum");
			var maxNumVal = Number($this.parent(".fillNumber").find(".numberVal").val())
			prodNumVal = Number(prodNum.html());
			//修改数量时选中此商品
			if((!$this.parent(".fillNumber").parent("div").parent("dd").hasClass("dd_fail"))
					&& (!$this.parent(".fillNumber").parent("div").parent("dd").hasClass("dd_offshelf"))){
				if(!$this.parent(".fillNumber").parent("div").parent("dd").find(".input").hasClass("input_curr")){
					var cutVal = $this.parent(".fillNumber").parent("div").parent("dd").find(".input").find("input");
					if(!cutVal.attr("checked") && prodNumVal>1){
						$this.parent(".fillNumber").parent("div").parent("dd").find(".input").addClass("input_curr").find("input").attr("checked","checked");
					}

				}
			}

			if($this.hasClass("cut_disable")){
				return false;

			}else{
				if((!$this.parent(".fillNumber").parent("div").parent("dd").hasClass("dd_fail"))
						&& (!$this.parent(".fillNumber").parent("div").parent("dd").hasClass("dd_offshelf"))){
					if(prodNumVal > 1){
						$this.parent(".fillNumber").find(".add").removeClass("disabled");
						prodNum.html(--prodNumVal);
						var id = $this.parent(".fillNumber").parent("div").parent("dd").attr("id");
						
						modify(prodNum.html(),id,$this);
						if(prodNumVal==1){
							$(this).addClass("disabled");
						}
					}else{
						canModify = true;
					}
				}
			}
			allCheck(this);
			
		}

	});
	

	//添加商品数量
	$(".alContent").delegate(".fillNumber .add","click",function(e){

		if(canModify){
			canModify = false;
			var $this = $(this);
			var prodNum = $this.parent(".fillNumber").find(".prodNum");
			var maxNumVal = Number($this.parent(".fillNumber").find(".numberVal").val())
			prodNumVal = Number(prodNum.html());
			var commodityPrice = $this.parent(".fillNumber").parent("div").find(".commodity_price");//?
			var comprice = commodityPrice.attr("comprice");
			//修改数量时选中此商品
			if((!$this.parent(".fillNumber").parent("div").parent("dd").hasClass("dd_fail"))
					&& (!$this.parent(".fillNumber").parent("div").parent("dd").hasClass("dd_offshelf"))){
				if(!$this.parent(".fillNumber").parent("div").parent("dd").find(".input").hasClass("input_curr")){
					if($this.hasClass("add_disable")){
						return false;
					}else{
						var addVal=$this.parent(".fillNumber").parent("div").parent("dd").find(".input").find("input");
						if(!addVal.attr("checked") && (prodNumVal<10 && prodNumVal<maxNumVal)){
							$this.parent(".fillNumber").parent("div").parent("dd").find(".input").addClass("input_curr").find("input").attr("checked","checked");
						}

					}

				}
			}
		
			$this.parent(".fillNumber").find(".cut").removeClass("disabled");
			if(prodNumVal<=10){
				if($this.hasClass("add_disable")){
					return false;

				}else{
					if(prodNumVal < maxNumVal&&prodNumVal != 10){
						prodNum.html(++prodNumVal);
						var id = $this.parent(".fillNumber").parent("div").parent("dd").attr("id");
						
						modify(prodNum.html(),id,$this);
						$(".commodity_price").html()
					}else if(prodNumVal == 10){
						canModify =true;
						$(".errorPrompt").fadeIn("slow").find("span").html(prodNumVal);
						$(this).addClass("disabled");
						setTimeout(function(){
							$(".errorPrompt").fadeOut("slow");
						},5000)
					}else if(prodNumVal == maxNumVal){
						canModify =true;
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
		
			allCheck(this);
			
		}
	
	});




	
	//删除商品
	$(".alContent").delegate(".close","click",function(e){
		var $this = $(this);
		var onthis = $this.closest(".order_block");
		var thisId = $this.siblings(".input").find("input").val();
		console.log(thisId);
		jShare("确定从购物车中删除该商品吗？","",function(result){
			if(result===true){
				if($("#"+thisId).closest(".order_block").find('dd').length<=1){
					$("#"+thisId).closest(".order_block").fadeOut("slow");
					setTimeout(function(){
						$("#"+thisId).closest(".order_block").remove();
						
						del(thisId);
					},600);	
				}				
				if($("#"+thisId).parent().parent().find('dd').length>1){
					$("#"+thisId).parent("dd").fadeOut("slow");
					setTimeout(function(){
						$("#"+thisId).parent("dd").remove();
						del(thisId);
						
					},600);
					
			    }else{					
			    	$("#"+thisId).parent().parent().fadeOut("slow");
					setTimeout(function(){
						$("#"+thisId).parent().parent().remove();
						del(thisId);
						
					},600);
					
				}
				
			}else{
			}
			
		});
		return false;
		
	});
	
	
	/*//var saveMoneyAll = 0;
	//计算总金额公共方法
	function selectNumAmountFun(){
		var $selectChoice = $('.order_block').find("a.input_curr");
		var selectNum = $selectChoice.length;

		var money = (function(){
			var money = 0;
			$selectChoice.each(function() {
				var $this = $(this).parents("dd");
				if((!$this.hasClass("dd_fail")) && (!$this.hasClass("dd_offshelf"))){
					var priceNew = parseFloat($this.find('.commodity_price').attr("comprice")), //单价
						amountVal = parseInt($this.find('.prodNum').text()), //个数
						amountVal = amountVal ? amountVal : 1,
						sum = $.Calc.Mul(priceNew,amountVal);
					//计算总金额
	            	money = $.Calc.Add(money,sum); 
				}
        	});

			return money;
		})();

		var total_number = (function(){
			var total_number = 0; 
			$selectChoice.each(function() {
				var $this = $(this).parents("dd");
				if((!$this.hasClass("dd_fail")) && (!$this.hasClass("dd_offshelf"))){
					var amountVal = parseInt($this.find('.prodNum').text()), //个数
						amountVal = amountVal ? amountVal : 1;
	            	total_number+=amountVal;
				}
        	});
			return total_number;
		})();
		
		$('#total_amount').text(money);
		$('#total_number').text(total_number);
	}*/
	

	//模拟checkbox点击效果
	$('.alContent').delegate('a.input','click',function(e){
		var $this = $(this);		
		if((!$this.parents("dd").hasClass("dd_fail"))
				&& (!$this.parents("dd").hasClass("dd_offshelf"))){
			if(!$this.hasClass("input_curr")){
				$this.addClass("input_curr");
				$this.find("input").attr("checked","checked");
			}else{
				$this.removeClass("input_curr");
				$this.find("input").removeAttr("checked")
			}
			
			updateShowCart();
			allCheck(this);
		}
		
	});
	
	//商品是否全部选中(点击所有的单选按钮置为全选，否则不置为全选)
	function allCheck(ele){
		var $thisBlockAll = $(ele).closest(".order_block");
		var dl_length=$thisBlockAll.find('dd').not('.dd_fail'&& '.dd_offshelf').length;
		var input_curr_length=$thisBlockAll.find("dd").find('.input_curr').length;
		if(input_curr_length==dl_length){
			$thisBlockAll.find("a.input_all").addClass("inputall_curr");
			$thisBlockAll.find("a.input_all").attr("checked","checked");
		}else{
			$thisBlockAll.find("a.input_all").removeClass("inputall_curr");
			$thisBlockAll.find("a.input_all").removeAttr("checked");
		}	
	}

	//模拟总金额单选选中效果
	$(".alContent").delegate("a.input_all","click",function(){
		var $this = $(this);
		if(!$this.hasClass("inputall_curr")){
			$this.addClass("inputall_curr");
			$this.find("input_all").attr("checked","checked");

			$this.closest(".order_block").children("dl").find("dd").each(function(){
				var $this = $(this);
				if((!$this.hasClass("dd_fail")) && (!$this.hasClass("dd_offshelf"))){
					$this.find(".input").addClass("input_curr").find("input").attr("checked","checked");
				}
			})
			
		}else{
			$this.removeClass("inputall_curr");
			$this.find("input_all").removeAttr("checked");
			$this.closest(".order_block").children("dl").find("dd").each(function(){
				var $this = $(this);
				if((!$this.hasClass("dd_fail")) && (!$this.hasClass("dd_offshelf"))){
					$this.find(".input").removeClass("input_curr").find("input").removeAttr("checked");
				}
			})
		}
		
		updateShowCart();
		
		
	});
/*	updateShowCart();
	if($(".coupon_tip").text() !=''){
		tipLayer();
	}*/

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
	    dataType:'html',
	    success:function(data){
	    	console.log(data);
	    	$(".alContent").html(data);
	    	var msg = $(".coupon_tip").text();
			if($(".coupon_tip").text() !=''){
				tipLayer();
			}
			
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
	    dataType:'html',
	    success:function(data){
	    	console.log(data);
	    	$(".alContent").html(data);
	    	if(!$(".order_block").length > 0){
	    		window.location.href = path + "/cart/no";
	    	}
	    }
	});
}
function modify(count,id,ele){
	var path = getRootPath();
	var cartItem=id+"_"+count;
	var isChecked='';
	var cartSpuNo = ele.parent(".fillNumber").parent("div").parent("dd").attr("ver");
	$("input[type='checkbox']").each(function(){
		if($(this).attr("checked")){
			isChecked += $(this).val() + "|";
		}
	});
	$.ajax({
	    url:path+'/cart/modify',
	    data:{
	    	"isChecked":isChecked,
	    	"cartItem":cartItem,
	    	"cartSpuNo":cartSpuNo
	    },
	    dataType:'html',
	    success:function(data){
	    	console.log(data);
	    	$(".alContent").html(data);
	    	if($(".coupon_tip").text() !=''){
				tipLayer();
			}
	    	canModify=true;
	    }
	});
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
/*点击结算提醒弹层*/
function modalHidden($ele) {
	$ele.removeClass('modal-in');
	$ele.one('webkitTransitionEnd',function(){
	  $ele.css({"display": "none"});
	  $('#overlay').removeClass('active');
	});
}
$('.btn-close').click(function(e){
  modalHidden($('.modal'));
  e.stopPropagation();
});
	
$('#overlay').click(function(e){
  if(e.target.classList.contains('overlay')){
	  ($('.modal'));
  }
});
//提示层
function tipLayer(){
	$('.coupon_tip').show();
	setTimeout(function(){
		$('.coupon_tip').hide();
	},3000);
}

$(".alContent").delegate("#immediately","click",function () {

	if(canModify){

		var wx = $("input[name=\"wx\"]").val();
		if( wx && $.trim($(".coupon_tip").text())!=''){
			alert("您勾选的商品只支持使用支付宝付款，请使用浏览器打开页面或者下载尚品APP下单!");
			return;
		}
		var checkAPP = $("#checkAPP").val();
		var $this = $(this);
		var shopids = "";
		$this.closest("section").find(".choice_commodity").each(function(){
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
			$("#cartForm").attr("action",getRootPath()+"/settlement/topay");
			$("#cartForm").submit();
		}
	}
})

});

