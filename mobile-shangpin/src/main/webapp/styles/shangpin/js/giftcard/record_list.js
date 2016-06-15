/**
 * @author zhanghongwei
 */
$(function(){
	
	//点击购买记录
	$('#buyChoice').click(function(){
		switchType(this,1,"1");
	})
	//点击充值记录
	$('#rechargeChoice').click(function(){
		switchType(this,2,"1");
	})
	//点击消费记录
	$('#consumeChoice').click(function(){
		switchType(this,3,"1");
	})


	//开关
	var switchLock=false;
	//切换
	function switchType(obj,recordType,pageNo){
		if(switchLock){
			return;
		}
		$(obj).parent().addClass("curr").siblings().removeClass("curr");
		var index=recordType-1;
		switchBefore(index);
		switchLock=true;
		loadOrder(recordType,pageNo);
	}

	//切换前
	function switchBefore(index){
		$(".content_info").find(".content_list").eq(index).show().siblings().hide();
		if(index>0){
			//充值记录 消费记录结构和购买记录结构不一样
			$(".content_info").find(".content_list").eq(index).find("ul").find("li:not(.first)").remove();
		}else{
			$(".content_info").find(".content_list").eq(index).children().remove();
		}
	}
	//切换after
	function switchAfter(recordType,pageNo){
		$("#recordType").val(recordType);
		$("#pageNo").val(pageNo);
		//是否加载更多
		var isHaveMoreId="#isHaveMore"+recordType+pageNo;
		var isHaveMore=$(isHaveMoreId).val();
		if(isHaveMore=="true"){
			$("#addMore").show();
		}else{
			$("#addMore").hide();
		}
		$("#loadOrder").hide();
		switchLock=false;
	}

	/**
	 *设置请求参数
	 */
	function optionParam(recordType){
		var callBackUrl;
		var index=recordType-1;
		//设置传送值
		var $content=$(".content_info").find(".content_list").eq(index);
		if(recordType==1){
			callBackUrl="record_list_buy_cell";
			$content=$(".content_info").find(".content_list").eq("0");
		}else if(recordType==2){
			callBackUrl="record_list_recharge_cell";
			$content=$(".content_info").find(".content_list").eq("1").find("ul");
		}else if(recordType==3){
			callBackUrl="record_list_consume_cell";
			$content=$(".content_info").find(".content_list").eq("2").find("ul");
		}
		var params=new Object();
		params.callBackUrl=callBackUrl;
		params.content=$content;
		return params;
	}
	
	//加载记录数据
	function loadOrder(recordType,pageNo){
		var params=optionParam(recordType);
		$.ajax({
	        url:'recordList',
	        type:'get',
			data:{"recordType":recordType,"pageNo":pageNo,"callbackUrl":params.callBackUrl},
			dataType:'html',
		    async:true,
			success:function(data){
				params.content.append(data);
				switchAfter(recordType,"1");
			}
	  });
	}

	//加载更多事件
	$('#addMore').click(function(){
		$("#addMore").hide();
		$("#loadOrder").show();
		var recordType = $("#recordType").val();
		var pageNo = $("#pageNo").val();
		loadMore(recordType,pageNo);
	});

	//加载更多数据
	function loadMore(recordType,pageNo){
		var nextPageNo=parseInt(pageNo)+1;
		var params=optionParam(recordType);
		$.ajax({
	        url:'recordList',
	        type:'get',
			data:{"recordType":recordType,"pageNo":nextPageNo,"callbackUrl":params.callBackUrl},
			dataType:'html',
			async:true,
			success:function(data){
				params.content.append(data);
				var recordType = $("#recordType").val();
				switchAfter(recordType,nextPageNo);
			}
	  });
	}
	
	//分享按钮事件
	$('.share-btn').click(function(){
		var $this = $(this);
		var path=getRootPath();
		if(!$("#_isapp").val()&&!$("#_iswx").val()){
			alert("请登录尚品网公众号或尚品网app进行赠送");
		}else{
			var childOrderId= $(this).attr("childOrderId");
			var giftCardId= $(this).attr("giftCardId");
			var faceValue= $(this).attr("faceValue");
			window.location.href = path + "/giftCard/skipToGiftCardSend?giftOrder=" + childOrderId+"&giftCardId="+giftCardId+"&faceValue="+faceValue;
		}
		
	});
})
