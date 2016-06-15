$(function(){
	//页面加载执行公共方法，计算价格
	selectNumAmountFun();
	
	var prodNumVal;

	//如果商品数量是一，不能再删除
	$(".prodNum").each(function(){
		if($(this).text()==1){
			$(this).parent(".fillNumber").find(".cut").addClass("disabled")
		}
	})
	
	//删除商品数量
	$(".alContent").delegate(".fillNumber .cut","click",function(e){
		console.log(1);
		var $this = $(this);
		var prodNum = $(this).parents(".fillNumber").find(".prodNum");
		var maxNumVal = Number($this.parent(".fillNumber").find(".numberVal").val())
		prodNumVal = Number(prodNum.html());
		//修改数量时选中此商品
		if(!$this.parent(".fillNumber").parent("div").parent("dd").hasClass("dd_fail")){
			if(!$this.parent(".fillNumber").parent("div").parent("dd").find(".input").hasClass("input_curr")){
				$this.parent(".fillNumber").parent("div").parent("dd").find(".input").addClass("input_curr").find("input").attr("checked","checked");
				selectNumAmountFun();
			}
		}
		if($this.hasClass("cut_disable")){
			return false;
		}else{
			if(!$this.parent(".fillNumber").parent("div").parent("dd").hasClass("dd_fail")){
				if(prodNumVal > 1){
					$this.parent(".fillNumber").find(".add").removeClass("disabled");
					prodNum.html(--prodNumVal);
					var id = $this.parent(".fillNumber").parent("div").parent("dd").attr("id");
					var region = $this.parent(".fillNumber").attr("region");
					modify(prodNum.html(),id,region,$this);
					if(prodNumVal==1){
						$(this).addClass("disabled");
					}
				}
			}
		}
		allCheck(this);
		selectNumAmountFun(this);
	});
	

	//添加商品数量
	$(".alContent").delegate(".fillNumber .add","click",function(e){
		console.log(1);
		var $this = $(this);
		var prodNum = $this.parent(".fillNumber").find(".prodNum");
		var maxNumVal = Number($this.parent(".fillNumber").find(".numberVal").val())
		prodNumVal = Number(prodNum.html());
		var commodityPrice = $this.parent(".fillNumber").parent("div").find(".commodity_price");//?
		var comprice = commodityPrice.attr("comprice");
		//修改数量时选中此商品
		if(!$this.parent(".fillNumber").parent("div").parent("dd").hasClass("dd_fail")){
			if(!$this.parent(".fillNumber").parent("div").parent("dd").find(".input").hasClass("input_curr")){
				$this.parent(".fillNumber").parent("div").parent("dd").find(".input").addClass("input_curr").find("input").attr("checked","checked");
			}
			//如果是海外商品，弹窗提示
			if($this.closest("dd").hasClass("overseas-goods")){
				prodNum.html(1);
				allCheck(this);
				selectNumAmountFun(this);
				tipLayer();
				return false;	
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
					var region = $this.parent(".fillNumber").attr("region");
					modify(prodNum.html(),id,region,$this);
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
		allCheck(this);
		selectNumAmountFun(this);	
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
						selectNumAmountFun(onthis)
					},600);	
				}				
				if($("#"+thisId).parent().parent().find('dd').length>1){
					$("#"+thisId).parent("dd").fadeOut("slow");
					setTimeout(function(){
						$("#"+thisId).parent("dd").remove();
						del(thisId);
						selectNumAmountFun()
					},600);
					
			    }else{					
			    	$("#"+thisId).parent().parent().fadeOut("slow");
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
	

	//var saveMoneyAll = 0;
	//计算总金额公共方法
	function selectNumAmountFun(ele){
		var that = $(ele).closest(".order_block") || $(ele);		
		var $selectChoice =that.find("a.input_curr");
		var selectNum = $selectChoice.length;
		//that.find('#total_number').text(selectNum);
		
		if(selectNum > 0){
			that.find(".immediately").removeClass("no_submit");
		}else{
			that.find(".immediately").addClass("no_submit");	
		}
		
		var money = (function(){
			var money = 0;
			$selectChoice.each(function() {
				var $this = $(this).parents("dd");
				if(!$this.hasClass("dd_fail") ){
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
				if(!$this.hasClass("dd_fail") ){
					var amountVal = parseInt($this.find('.prodNum').text()), //个数
						amountVal = amountVal ? amountVal : 1;
	            	total_number+=amountVal;
				}
        	});
			return total_number;
		})();

		//优惠金额
		//var saveAll=0;
		var saveMoney = (function(){
			var saveMoney = 0;
			that.find('dl').each(function(){
				var $this = $(this);
				var $thisDdLength = $this.find("dd").length;
				var $thisDdCurrLength = $this.find("dd .input_curr").length;
				if($thisDdLength==$thisDdCurrLength && $this.find("dt").length==1){
					var saveAmount = parseInt($this.find(".saveAmount").text());
					if(isNaN(saveAmount)){
						saveAmount = 0;
					}
					saveMoney = parseInt(saveMoney) + saveAmount;
				}
			});
        	that.find(".save_amount").find("em").html(saveMoney);
			return saveMoney;
		})();
		that.find('#total_amount').text(money-saveMoney);
		that.find('#total_number').text(total_number);
	}

	//模拟checkbox点击效果
	$('.alContent').delegate('a.input','click',function(e){
		var $this = $(this);		
		if(!$this.parents("dd").hasClass("dd_fail")){
			if(!$this.hasClass("input_curr")){
				$this.addClass("input_curr");
				$this.find("input").attr("checked","checked");
			}else{
				$this.removeClass("input_curr");
				$this.find("input").removeAttr("checked")
			}
			updateShowCart();
			allCheck(this);
			selectNumAmountFun(this);
		}
		
	});
	
	//商品是否全部选中
	function allCheck(ele){
		var $thisBlockAll = $(ele).closest(".order_block");
		var dl_length=$thisBlockAll.find('dd').not('.dd_fail').length;
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
				if(!$this.hasClass("dd_fail")){
					$this.find(".input").addClass("input_curr").find("input").attr("checked","checked");
				}
			})
			
		}else{
			$this.removeClass("inputall_curr");
			$this.find("input_all").removeAttr("checked");
			$this.closest(".order_block").children("dl").find("dd").each(function(){
				var $this = $(this);
				if(!$this.hasClass("dd_fail")){
					$this.find(".input").removeClass("input_curr").find("input").removeAttr("checked");
				}
			})
		}
		updateShowCart();
		selectNumAmountFun(this);
		
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
	    dataType:'html',
	    success:function(data){
	    	console.log(data);
	    	$(".alContent").html(data);
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
function modify(count,id,region,ele){
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
	    	"region":region,
	    	"cartSpuNo":cartSpuNo
	    },
	    dataType:'html',
	    success:function(data){
	    	console.log(data);
	    	$(".alContent").html(data);
	    }
	});
}
function topay(el,postArea){
	var checkAPP = $("#checkAPP").val();
	var $this = $(el);
	$("#postArea").val(postArea);
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
		$("#cartForm").submit();
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

//提示层
function tipLayer(){
	$('.tip-overlay').show();
	$('.tip-container').show();
	setTimeout(function(){
		$('.tip-overlay').hide();
		$('.tip-container').hide();
	},2000);
	
}