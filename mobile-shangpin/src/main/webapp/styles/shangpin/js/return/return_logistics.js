;$(function(){

	/*退货原因弹层*/
	var select_region = $("#select_region");
	var provincePicker = $("#provincePicker");
	
	var up,animaed;

	var selectYes = $("#selectYes");
	var selectClose = $("#selectClose");
	var data = [
		{
			"value":"申通快递",
		},
		{
			"value":"韵达快递",
		},
		{
			"value":"圆通快递",
		},
		{
			"value":"中通快递",
		},
		{
			"value":"邮政EMS",
		},
		{
			"value":"顺丰快递",
		},
		{
			"value":"百世汇通",
		},
		{
			"value":"天天快递",
		},
		{
			"value":"宅急送",
		},
		{
			"value":"中国邮政",
		},
		{
			"value":"德邦快递",
		},
		{
			"value":"全峰快递",
		},
		{
			"value":"其他",
		}
	];

	var provinceData,cityData,areaData,regionHtml="";
	
	select_region.on("click",function(){
		touch = 0;
		$("body").attr("style","overflow:hidden");
		$("body").bind("touchmove",function(e){
			if(touch==0){
				e.preventDefault();
			}
		})
		$("#picker-overlay").height($(document).height()).show();
		
		//判断是否已经生成数据，如果没有创建，有直接显示DOM
		var hasData = provincePicker.children().length ? true : false;
		
		if(hasData){
			//判断是否已经显示，已经显示不执行
			if(!animaed.isShowed){	
				animaed.start();
				//console.log(animaed.isShowed);
			}
		}else{
			//创建需要的UIPickerView
			up = UIPickerView.createPickerView({
				dataSource:data,		//数据源
				id:'provincePicker',	//容器id
				constraintsId:'wower',	//约束id
				kUP:{
					kUPCELLHEIGHT:36,	//行高
					kUPFRICTION:0.003	//动画速率
				},
				valueChange:function(data){					//触发valueChange事件回调
					provinceData = data;					//给变量赋值选中项的值
				}
			});
			//console.log(up);
			
			animaed = CAAnimation.createAnimation({id:'region-picker'});
			animaed.start();
			//console.log(animaed);
			
			//用户已选择过修改选项时事件
			var provinceSelect = select_region.attr("data-provinceIndex");
			
			if(provinceSelect != null || citySelect != null){
				//console.log(data[provinceSelect-1]);
				
				//选择中参数对应的值
				up.UPSelectRowIndexPath(provinceSelect).UPThen();

				//删除所传递参数
				select_region.removeAttr("data-provinceIndex");				
			}else{
				//console.log("第一次设置");
			}				
		}
	});
	
	//确定按钮提交事件
	selectYes.on("click",function(){
		layerDown();
		
		//if provinceData or cityData or areaData is empty ,so use UPSelectRowIndexPath and UPThen
		//如果用户打开UIPickerView，然而用户并没有触发valueChange事件，就读取每个选项的第一个默认值
		
		if (!provinceData&& up) {
			up.UPSelectRowIndexPath(1).UPThen(function(indexPath,value){
				//console.log(value);
				regionHtml += value.value;
			})
		}else{
			//console.log(provinceData);
			regionHtml += provinceData.value;
		};
		
		//显示结果
		select_region.html(regionHtml);
		select_region.addClass("f-black");
		regionHtml = "";	//清除数据
		
		//maybe use your datasource is relatively good
		//data[0]  //你的数据默认选择第一行
	});
	
	//关闭事件
	selectClose.on("click",function(){
		layerDown();
	});
	
	/*退货原因弹层关闭方法*/
	function layerDown(){
		animaed.finish();	
		setTimeout(function(){
				$("#picker-overlay").hide();	
			},300);
		$("body").removeAttr("style");
		touch = 1;		
	}
	
	$("#picker-overlay").click(function(e){
		if(e.target.classList.contains('select-overlay')){
			layerDown();
		}
	});
	
	/*提交信息btn*/
	var tN = /^[0-9a-z]*$/;
	$(".payment-btn").click(function(e){
		e.preventDefault();
		//选择物流公司
		if (!$("#select_region").hasClass("f-black")){
			return jShare('请选择物流公司',"",""),
			$("#select_region").addClass("error"),
        	!1;
		}else{
			$("#select_region").removeClass("error");
		}	
		//填写运单编号
		if ($.trim($("#tNumber").val()) == ""){
			return jShare('请填写物流单号',"",""),
			$("#tNumber").addClass("error"),
        	!1;
		}else if(!tN.test($.trim($("#tNumber").val()))){
			return jShare('请填写正确的物流单号',"",""),
			$("#tNumber").addClass("error"),
        	!1;
		}else{
			$("#tNumber").removeClass("error");
		}
		var company = $.trim($("#select_region").text());
		var logisticsNo = $.trim($("#tNumber").val());
		var applyNo = $.trim($("#applyNo").text());
		var path = getRootPath();
		$.post(path + "/returnGoods/logistics/submit",{
			applyNo : applyNo,
			logisticsNo : logisticsNo,
			logisticsCompany : company
		},function(data){
			if(data.code == "0"){
				window.location.href = path + "/returnGoods/logistics/success";
			}else{
				alert(data.msg);
				return;
			}
		},"json");
	});

});