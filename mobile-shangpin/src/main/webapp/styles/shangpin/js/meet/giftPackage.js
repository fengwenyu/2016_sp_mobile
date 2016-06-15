$(function(){
	
	$('.receive-btn').click(function() {
		var coupon="gp1";
		// 获取数据
		$.ajax({
			type : "GET",
			url : getRootPath() + "/meet/coupon/activation",
			data : {
				'coupon' : coupon
			},
			success : function(data) {
				if (data == undefined) {
					alert("领取失败！");
					return;
				} else if (data.code == '2') {
					location.href = getRootPath() + "/meet/redirect/app?id=giftPackage";
					return;
				} else if (data.code == "0") {
					var html = "<h3>恭喜您已成功领取礼券包！</h3><p> 领取的礼券包请到<br><a href='"+getRootPath()+"/coupon/list'><span>个人中心\"我的优惠券\"</span></a>中查看！</p>";
					jShare(html,"","");
				}else{
					var html = "<h3>您已领取礼券包！</h3><p> 领取的礼券包请到<br><a href='"+getRootPath()+"/coupon/list'><span>个人中心\"我的优惠券\"</span></a>中查看！</p>";
					jShare(html,"","");
				}
			}
		});
	});	
});