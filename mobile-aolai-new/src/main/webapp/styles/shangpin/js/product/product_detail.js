//初始化一些信息
	$(function(){
		var hasSecond=$("#hasSecond").val();
		var index=$(".fillColor span").eq(0).attr("id").split("_")[1];
		if( hasSecond=='false'){
			setCount(index);
		}else{
			//将第一个默认编码放入隐藏域
			var objId="secondpro_"+index;
			changedSecondPropStr(objId);
		}
	});
	
	/**
	 * 提交购物车
	 */
	function addCart(){
		//改变按钮文案并禁用按钮
		var sub = $("#submit");
		sub.text("正在添加...");
		sub.attr("href","");
		if($("#sku").val()==""){
			$("#alProdInfo").html("请选择尺码");
			sub.text("加入购物车");
			sub.attr("href","javascript:addCart()");
			return;
		} 
		$.ajax({
			url:getRootPath()+"/cart/add",
			type:"get",
			dataType:"json",
			data:{
				"productNo":$("#productno").val(),
				"skuNo":$("#sku").val(),
				"quantity":$("#count").val(),
				"categoryNo":$("#categoryno").val(),
				"typeFlag":$("#typeFlag").val(),
				"pageType":$("#pageType").val()
			},
			timeout: 30000,
			error: function (xmlHttpRequest, error) {
                alert("您的网络异常，请稍后或重试。");
                sub.text("加入购物车");
    			sub.attr("href","javascript:addCart()");
                return;
            },
            success:function(jsonStr,status,xhr){
            	console.log("jsonStr:"+jsonStr);
            	
            	var pNo = $("#productno").val(),
            	sku = $("#sku").val(),
            	qua = "1",
            	cNo = $("#categoryno").val(),
            	tF = $("#typeFlag").val(),
            	pF = $("#pageType").val();
            	
            	var sessionstatus = xhr.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
            	if (sessionstatus == "timeout") {
            		
            		var locationURL = xhr.getResponseHeader("locationURL"); // 通过XMLHttpRequest取得响应头，locationURL，
            		// 如果超时就处理 ，指定要跳转的页面
            	
            		var encodeUrl="/cart/add/redirect?productNo="+pNo+"&skuNo="+sku+"&quantity="+qua+"&categoryNo="+cNo+"&typeFlag="+tF+"&pageType="+pF;
            		//alert("back:"+encodeUrl);
            		//alert(escape(encodeUrl));
            		window.location.href = locationURL+escape(encodeUrl);
            		sub.text("加入购物车");
        			sub.attr("href","javascript:addCart()");
            	}
            	
            	if(jsonStr!=null && jsonStr!=''){
            		var code = jsonStr.code,
                	msg = jsonStr.msg;
//            		console.log(jsonStr.typeFlag+":"+jsonStr.pageType);
                	if(code == 2){//弹出错误信息
                		alert("抱歉，出错了:"+msg);
                		sub.text("加入购物车");
            			sub.attr("href","javascript:addCart()");
                	}
                	if(code == 3){//跳转到购物车列表页
                		$("#typeFlag").val(jsonStr.typeFlag);
                		$("#pageType").val(jsonStr.pageType);
                		//alert("恭喜，已成功加入购车。");
                		//$("#cartListForm").submit();
                		window.location.href = getRootPath()+"/cart/list?pageType="+jsonStr.pageType+"&typeFlag="+jsonStr.typeFlag;
                		sub.text("加入购物车");
            			sub.attr("href","javascript:addCart()");
                	}
            	}
            	
            }
            
		});
		
	}
	
	// 选择颜色时：级联颜色和尺码，一个颜色对应多个尺码
	function changedSecondProp(obj) {
		var hasSecond=$("#hasSecond").val();
		if( hasSecond=='false'){
			setCount(obj.id.split("_")[1]);
		}else{
			//将第一个默认编码放入隐藏域
			var objId = "secondpro_"+obj.id.split("_")[1];
			changedSecondPropStr(objId);
		}
	}
	function changedSecondPropStr(objId) {
		var c=0;
		$(".fillSize > span").each(function(n){//多选
    		var s = $(this).attr("id");
    		$(this).removeClass("cur");
			if(s==objId){
				$(this).show();
				if(c==0){
					//默认第一个被选中
					$(this).toggleClass("cur");
					setSkuAndCount($(this).attr("sku"),$(this).attr("count"));
					c++;
				}
			}else{
				$(this).hide();
			}
		});
	}
	
	
	
	// 设置购买商品的sku和数量（无尺码的商品）
	function setCount(index){
		var count = $("#firstpro_count_"+ index).val();
		var skuObjId = "firstpro_sku_"+index;
		var sku = $("#"+skuObjId).val();
		$("#alProdInfo").html("");
		$("#sku").val(sku);
		$("#count option").remove();
		if(parseInt(count)>10){
			count=10;
		}
		for(var i=1;i<=parseInt(count);i++){
		
			var $cOption = $("<option/>");
			$cOption.attr("value",i);
			$cOption.text(i);
			$("#count").append($cOption);
			if(i == 1){
				$cOption.attr("selected","selected");
			}
		}
	}
	
	// 设置购买商品的sku和数量（有尺码的商品）
	function setSkuAndCount(sku,count){
		$("#alProdInfo").html("");
		$("#sku").val(sku);
		// $("#countTemp").val(count);
		$("#count option").remove();
		if(parseInt(count)>10){
			count=10;
		}
		for(var i=1;i<=parseInt(count);i++){
			var $cOption = $("<option/>");
			$cOption.attr("value",i);
			$cOption.text(i);
			$("#count").append($cOption);
			if(i == 1){
				$cOption.attr("selected","selected");
			}
		}
	}
