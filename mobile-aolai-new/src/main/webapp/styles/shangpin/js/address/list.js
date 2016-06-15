$(function() {
	isHome(false);
	// 标记默认地址
	var obj = $(".curr");
	if (obj.size() == 0) {
		$(".address_list li:eq(0)").addClass("curr");
		$($("[class='default_address']")[0]).html("默认地址");
	}
})

// 删除收货人地址
function deleteAddress(obj) {
	if (confirm("确认要删除该地址吗？")) {
		$.ajax({
			type : "get",
			url : "del",
			data : {
				addressId : obj.id
			},
			dataType : 'json',
			success : function(data) {
				if (data) {
					// 删除页面li对象
					$(obj).parent().parent().remove();
					// 重新排序
					$(".sort").each(function(n, v) {
						$(this).html(n + 1);
					});

					// 最多10个地址，少于10个显示添加按钮
					if ($(".sort").size() < 10) {
						$(".alOrder_submitBtn").attr("style", "");
					}
				} else {
					alert("删除失败！");
				}
			}
		});
	}
}
