//页面加载完成后
$(function($){
})
//点击全部
$('#all').click(function(){
	switchType(this,"-1","1");
})
//点击未使用
$('#unused').click(function(){
	switchType(this,"0","1");
})
//点击已使用
$('#used').click(function(){
	switchType(this,"1","1");
})
//点击已过期
$('#expired').click(function(){
	switchType(this,"3","1");
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
}

//加载订单数据
function loadOrder(statusType,pageNo){
	$.ajax({
        url:'list',
        type:'get',
		data:{"couponType":statusType,"pageNo":pageNo,callbackUrl:"list_cell"},
		dataType:'html',
	    async:true,
		success:function(data){
			$("#content").append(data);
			switchAfter();
		}
  });
}

//加载更多
function addMore(obj){
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
		data:{"couponType":statusType,"pageNo":nextPageNo,callbackUrl:"list_cell_coupon"},
		dataType:'html',
		async:true,
		success:function(data){
			$("#alOrder_couponList").append(data);
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