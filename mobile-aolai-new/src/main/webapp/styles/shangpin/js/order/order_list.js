//页面加载完成后
$(function($){
})
//点击全部
$('#all').click(function(){
	switchType(this,"1","1");
})
//点击待支付
$('#waitpay').click(function(){
	switchType(this,"2","1");
})
//点击待收货
$('#waitconfirm').click(function(){
	switchType(this,"4","1");
})

//开关
var switchLock=false;
//切换
function switchType(obj,statusType,pageNo){
	if(switchLock){
		return;
	}
	$(obj).addClass("cur").siblings().removeClass("cur");
	switchBefore();
	switchLock=true;
	loadOrder(statusType,pageNo);
}

//切换前
function switchBefore(){
	$("#loadOrder").show();
	$("#content").children().remove();
}
//切换after
function switchAfter(){
	$("#loadOrder").hide();
	switchLock=false;
	SP_plug_lazyload();
}

//加载订单数据
function loadOrder(statusType,pageNo){
	$.ajax({
        url:'list',
        type:'get',
		data:{"statusType":statusType,"pageNo":pageNo,"callbackUrl":"list_cell"},
		dataType:'html',
	    async:true,
		success:function(data){
			$("#content").append(data);
			switchAfter();
		}
  });
}

//加载更多
function addMore(){
	$("#addMore").hide();
	$("#loadOrder").show();
	var statusType = $("#statusType").val();
	var pageNo = $("#pageNo").val();
	loadMore(statusType,pageNo);
}

//加载数据
function loadMore(statusType,pageNo){
	var nextPageNo=parseInt(pageNo)+1;
	$.ajax({
        url:'list',
        type:'get',
		data:{"statusType":statusType,"pageNo":nextPageNo,"callbackUrl":"list_cell_order"},
		dataType:'html',
		async:true,
		success:function(data){
			$("#alOrder_listCell").append(data);
			SP_plug_lazyload();
			var statusType = $("#statusType").val();
			$("#statusType").val(statusType);
			$("#pageNo").val(nextPageNo);
			//是否加载更多
			var isHaveMoreId="#isHaveMore"+$("#pageNo").val();
			var isHaveMore=$(isHaveMoreId).val();
			if(isHaveMore=="true"){
				$("#addMore").show();
			}else{
				$("#addMore").hide();
			}
			$("#loadOrder").hide();
		}
  });
}

//取消订单
function cancelOrder(){
	if(confirm("您确定要取消订单吗？")){
		var orderId=$(".alOrder_cancelBtn").attr("id");
		$.ajax({
	        url:'cancel',
	        type:'get',
			data:{"orderId":orderId},
			dataType:'json',
			success:function(data){
				if("0"==data.code){
					window.location.href="list";
				}else{
					alert("订单取消失败!");
				}
			}
		});
	}
}

function SP_plug_lazyload(){
	//配置图片延迟加载
	SP.plug.lazyload("img", null, {
		start : function(){
			this.css({opacity : 0});
		},
		end : function(){
			this.animate({opacity:1}, 200);
		}
	}).run();
}