$(function(){
	var msg=$("#msg").val();
	if(msg!=""){
		alert(msg);
		$("#msg").val("");
		return;
	}
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
	$(".fillNumber .cut").click(function(){		
		var $this = $(this);
		var shopid = $this.attr("id");
		var prodNum = $(this).parents(".fillNumber").find(".prodNum");
		var maxNumVal = '';
		prodNumVal = Number(prodNum.html());
		--prodNumVal;	
		if(prodNumVal>=1){
			$.ajax({
			    url:'../cart/update',
			    data:{"shopDetailId":shopid,"quantity":prodNumVal},
			    dataType:'json',
			    timeout: 30000,
	            error: function (xmlHttpRequest, error) {			            	
	                    alert("您的网络异常");
	                return;
	            },
			    success:function(data){
			      if(data.code=='0'){
			    	  if(data.conCode=='1'){
			    		  maxNumVal=$this.parent(".fillNumber").find(".numberVal").val(data.conInven);
			    		  maxNumVal = Number($this.parent(".fillNumber").find(".numberVal").val());		    		 
			    	  }
			    	  
				  }else{
				    alert(data.msg);
				    return;
				  }
			      if($this.hasClass("cut_disable")){
						return false;
			      }else{
			    	  ++prodNumVal;
						if(prodNumVal >1){
							$this.parent(".fillNumber").find(".add").removeClass("disabled");
							prodNum.html(--prodNumVal);
							selectNumAmountFun();	
							if(prodNumVal==1){
								$this.parent(".fillNumber").find(".cut").addClass("disabled");
							}
						}
					}
			    }
			  });
		}
	});

	//添加商品数量
	$(".fillNumber .add").click(function(){
		var $this = $(this);
		var shopid = $this.attr("id");
		var prodNum = $this.parent(".fillNumber").find(".prodNum");		
		prodNumVal = Number(prodNum.html());
		++prodNumVal;
		var maxNumVal='';
		if(prodNumVal<=10){
			$.ajax({
			    url:'../cart/update',
			    data:{"shopDetailId":shopid,"quantity":prodNumVal},
			    dataType:'json',
			    timeout: 30000,
	            error: function (xmlHttpRequest, error) {			            	
	                    alert("您的网络异常");
	                return;
	            },
			    success:function(data){
			      if(data.code=='0'){
			    	  if(data.conCode=='1'){
			    		  prodNumVal--;
			    		  maxNumVal=$this.parent(".fillNumber").find(".numberVal").val(data.conInven);
			    		  maxNumVal = Number($this.parent(".fillNumber").find(".numberVal").val());		    		 
			    	  }
			    	  
				  }else{
				    alert(data.msg);
				    return;
				  }
			      var commodityPrice = $this.parent(".fillNumber").find(".commodity_price");
					var comprice = commodityPrice.attr("comprice");
					$this.parent(".fillNumber").find(".cut").removeClass("disabled");
					if($this.hasClass("add_disable")){
						return false;
					}else{
						if(maxNumVal==''||prodNumVal < maxNumVal){
							prodNum.html(prodNumVal);
							selectNumAmountFun();
							$(".commodity_price").html();
						}else if(prodNumVal == maxNumVal){
							$(".errorPrompt").fadeIn("slow").find("span").html(prodNumVal);
							$this.parent(".fillNumber").find(".add").addClass("disabled");
							if(maxNumVal==1){
								$this.parent(".fillNumber").find(".cut").addClass("disabled");
							}
							setTimeout(function(){
								$(".errorPrompt").fadeOut("slow");
							},5000)
						}
					}
			    }
			  });
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
	$("dd").delegate(".close","click",function(){
		var $this = $(this);
		var thisId = $this.attr("id");
		jShare("确定从购物车中删除该商品吗？","",function(result){
			if(result===true){
					  $.ajax({
					    url:'../cart/del',
					    data:{"shopDetailId":thisId},
					    dataType:'json',
					    timeout: 30000,
			            error: function (xmlHttpRequest, error) {			            	
			                    alert("您的网络异常");
			                return;
			            },
					    success:function(data){
					    	if(data.code == 0){
						    	  $("#"+thisId).parents("dd").fadeOut("slow");
									setTimeout(function(){
										$("#"+thisId).parents("dd").remove();
										selectNumAmountFun();
									},600)
									goToIndex();
							  }else{
							    alert(data.msg);
							  }
						    }
					  });
				
			}else{
				
			}
		});
		return false;
		
	});

	//计算总金额公共方法
	function selectNumAmountFun(){
		
		var $selectChoice = $('.order_block').find("a.input_curr");
		//计算数量
		/*var selectNum = 0
		$selectElement.each(function(){
			var $this = $(this);
			if(!$this.hasClass("dd_fail") && $selectChoice){
				selectNum++;
			}
		})*/
		var selectNum = $selectChoice.length;
		$('#total_number').text(selectNum);

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

		$('#total_amount').text(money);
	}
	
	//模拟checkbox点击效果
	$("a.input").click(function(){
		var $this = $(this);
		if(!$this.parents("dd").hasClass("dd_fail")&&!$this.parents("dd").hasClass("dd_fail_gq")&&!$this.parents("dd").hasClass("dd_fail_xj")){
			if(!$this.hasClass("input_curr")){
				$this.addClass("input_curr");
				$this.find("input").attr("checked","checked");
			}else{
				$this.removeClass("input_curr");
				$this.find("input").removeAttr("checked");
			}

			selectNumAmountFun();
		}
		
	});
	//计算总金额公共方法
	function goToIndex(){
		var size=$("dd").length;
		if(size==1){
			window.location.href="../cart/list";
		}
		//$(this).find(":radio").attr("checked",true); 
		
	}
});

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