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
	
	/**
	 * 减少商品数量
	 */
	$(".fillNumber .cut").click(function(){		
		var $this = $(this);
		var shopid = $this.attr("id");
		var prodNum = $(this).parents(".fillNumber").find(".prodNum");
		var maxNumVal = '';
		prodNumVal = Number(prodNum.html());
		--prodNumVal;	
		if(prodNumVal>=1){
			$.ajax({
			    url:getRootPath()+'/cart/update',
			    data:{"shopDetailId":shopid,"quantity":prodNumVal},
			    dataType:'json',
			    timeout: 30000,
	            error: function (xmlHttpRequest, error) {			            	
	                    alert("您的网络异常");
	                return;
	            },
			    success:function(data){
		    	  console.log(data);
          	      var code = data.code;
          	      var msg = data.msg;
          	      var conInven = data.conInven;
          	      var conCode = data.conCode;
			      if(code=='0'){
			    	  //var resultData=data.content;
			    	  if(conCode == '1'){
			    		  maxNumVal=$this.parent(".fillNumber").find(".numberVal").val(conInven);
			    		  maxNumVal = Number($this.parent(".fillNumber").find(".numberVal").val());		    		 
			    	  }
			    	  
				  }else{
				    alert(msg);
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

	/**
	 * 添加商品数量
	 */
	$(".fillNumber .add").click(function(){
		var $this = $(this);
		var shopid = $this.attr("id");
		var prodNum = $this.parent(".fillNumber").find(".prodNum");		
		prodNumVal = Number(prodNum.html());
		++prodNumVal;
		var maxNumVal='';
		if(prodNumVal<=10){
			$.ajax({
			    url:getRootPath()+'/cart/update',
			    data:{"shopDetailId":shopid,"quantity":prodNumVal},
			    dataType:'json',
			    timeout: 30000,
	            error: function (xmlHttpRequest, error) {			            	
	                    alert("您的网络异常");
	                return;
	            },
			    success:function(data){
			      console.log(data);
			      var code = data.code;
          	      var msg = data.msg;
          	      var conInven = data.conInven;
          	      var conCode = data.conCode;
			      if(code=='0'){
			    	 // var resultData=data.content;
			    	  if(conCode == '1'){
			    		  prodNumVal--;
			    		  maxNumVal=$this.parent(".fillNumber").find(".numberVal").val(conInven);
			    		  maxNumVal = Number($this.parent(".fillNumber").find(".numberVal").val());		    		 
			    	  }
			    	  
				  }else{
				    alert(msg);
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

	/**
	 * 删除购物车商品
	 */
	$("dd").delegate(".close","click",function(){
		var $this = $(this);
		var thisId = $this.attr("id");
		jShare("确定从购物车中删除该商品吗？","",function(result){
			if(result===true){
					  $.ajax({
					    url:getRootPath()+'/cart/delete',
					    data:{"shopDetailId":thisId},
					    dataType:'json',
					    timeout: 30000,
			            error: function (xmlHttpRequest, error) {			            	
			                alert("您的网络异常");
			                return;
			            },
					    success:function(data){
					    	var code = data.code;
			          	      var msg = data.msg;
					      if(code==0){
					    	  $("#"+thisId).parents("dd").fadeOut("slow");
								setTimeout(function(){
									$("#"+thisId).parents("dd").remove();
									selectNumAmountFun();
								},600)
								goToIndex();
						  }else{
						    alert(msg);
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
				if(!$this.hasClass("dd_fail")&&!$this.hasClass("dd_fail_xj")&&!$this.hasClass("dd_fail_gq")){
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
	
	//选中
	function checkShopId(){
		var $selectElement = $(".order_block").find("dd"),
			$obj,
			$len=$selectElement.length;
			$shopids='';
		$selectElement.each(function(i){
			var $this = $(this);
			if(!$this.hasClass("dd_fail")&&!$this.hasClass("dd_fail_xj")&&!$this.hasClass("dd_fail_gq")){
				$obj = $this.find('a.input_curr').siblings(".close").attr("id");
				if($obj!=undefined){					
					$shopids+=$obj;
					if(i!=($len-1)){
						$shopids+="|";
					}
				}
			}
			//console.log($obj);
		});
		$("#shopids").val($shopids);
	
	}
	//计算总金额公共方法
	function goToIndex(){
		var size=$("dd").length;
		if(size==1){
			self.location.reload();
		}
		//$(this).find(":radio").attr("checked",true); 
		
	}
	//模拟checkbox点击效果
	$("a.input").click(function(){		
		var $this = $(this);
		if(!$this.parents("dd").hasClass("dd_fail")&&!$this.parents("dd").hasClass("dd_fail_xj")&&!$this.parents("dd").hasClass("dd_fail_gq")){
			if(!$this.hasClass("input_curr")){
				if($('#total_number').text()<10){
					$this.addClass("input_curr");
					$this.find("input").attr("checked","checked")
				}else{
					jShare("单次结算的商品不能超过10件，请重新选择商品","",function(result){},false);
				}
			}else{
				$this.removeClass("input_curr");
				$this.find("input").removeAttr("checked")
			}
			checkShopId();
			selectNumAmountFun();
		}
		
	});
	
});

/**
 * 提交购物车商品
 */
function toSubmit(checkAPP){
	var shopids=$("#shopids").val();
	//var form=$("cartForm");
	console.log(shopids);
	if(shopids == ''){
		jShare("请选择您购买的商品","",function(result){},false);
		return;
	}
	var hrefV='';
	if(checkAPP){
	   hrefV = "shangPinApp://phone.aolai/actiongosettlement?shopDetailId="+shopids;
	}
	if(hrefV!=''){        	
		window.location.href =hrefV;
	}else{//选定商品，跳转到提交订单页        		
		$("#cartForm").submit();
	}
}
