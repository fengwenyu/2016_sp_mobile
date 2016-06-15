$(function(){
	
	$(".pop_btn").click(function(){
		var html = '<h3>恭喜你中奖了！</h3>';
		html += '<ol>'
		html += '<li>每天下午5:20发售一组超低价时尚奢品；</li>'
		html += '<li>活动时间为：5月12日-5月25日；</li>'
		html += '<li>进入秒杀专区并完成在线支付，售出商品已属超低价，如无质量问题，不支持退换货服务。</li>'
		jShare(html,"");
	})

});