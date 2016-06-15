// JavaScript Document
$(function() {
	var haveMore = $("#haveMore").val();
	if (haveMore == '0') {
		$(".alList_moreBtn").css("display", "none");
	}
	if (haveMore == 1) {
		var pageIndex = $("#pageIndex").val();
		$("#moreButton").attr("style", "");
		$("#moreButton").attr("pageIndex", pageIndex);
	}

	// 旗舰店轮播图
	if ($("#J_m-slider").length > 0 && $(".slider-wrap li").length > 1) {

		$("#J_m-slider").slider({
			wrap : ".slider-outer",
			wrapUl : ".slider-wrap",
			wrapStatus : '.slider-status',
			isLoop : true,
			isPlay : true
		});

	}

	// 滑动页面
	if ($('a#pageTop').length > 0) {

		var topH = $('a#pageTop').offset().top;
		document.body.scrollTop = parseInt(topH);

	}

});

function getMore(checkAPP) {
	var path = getRootPath();
	var pageIndex = 0;
	$("#moreButton").hide();

	pageIndex = $("#moreButton").attr("pageIndex");

	$.ajax({
			url : path + '/subject/getMore',
			data : {
				"pageIndex" : $("#moreButton").attr("pageIndex"),
				"topicId" : $("#topicid").val()
			},
			type : 'post',
			dataType : "json",
			timeout : 300000,
			async : true,
			error : function(xmlHttpRequest, error) {

				$("#moreButton").show();

				if (error != "") {
					alert("您的网络异常");
				}
				return;
			},
			success : function(entityList) {
				if (entityList.code == 0) {
					$("#moreButton").css("visibility", "hidden");
					var getMore = entityList.haveMore;
					if (getMore != "0") {
						$("#moreButton").attr("style", "");
					} else {
						$("#moreButton").css("visibility", "hidden");

					}
					var pageIndex = entityList.pageIndex;
					$("#moreButton").attr("pageindex", pageIndex);
					if (entityList.length <= 0) {
						$("#moreButton").css("visibility", "hidden");
						return;
					}
					var entity = entityList.topicProduct.list;
					var $ul = $(".alSubject > ul");
					$(entity)
							.each(
									function(n, v) {
										var entityP = v.products;
										$(entityP)
												.each(
														function(n1, v1) {
															var append = '<li>';
															var hrefV;
															if (checkAPP) {
																hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="
																		+ v1.productid
																		+ "&topicid="
																		+ entityList.topicProduct.topicid;
															} else {
																hrefV = path
																		+ "/product/detail?productNo="
																		+ v1.productid
																		+ "&topicId="
																		+ entityList.topicProduct.topicid;
															}
															append += "<a title="
																	+ v1.productname
																	+ " href= "
																	+ hrefV
																	+ ">"
																	+ '<img width="159" height="211" alt="" src="'
																	+ v1.pic
																	+ '" src="'
																	+ v1.pic
																	+ '">'
																	+ '<div class="alBrand_list_info">'
																	+ '<p>'
																	+ '<em>'
																	+ v1.brandname
																	+ '</em>'
																	+ '<em>'
																	+ v1.productname
																	+ '</em>'
																	+ '</p>'

																	+ '<span>'
																	+ '<i>&yen;</i>';
															if (v1.status == '000100'
																	|| v1.status == '0001') {
																append += v1.specialprice[0]
																		+ '<br />';
															} else {
																append += v1.prices[v1.priceindex]
																		+ '<br />';
															}

															if (entityList.flag == true) {
																if (entityList.pricename != '') {
																	var pricename = entityList.topicProduct.pricename;
																	append += '<font style="font-size: 14px;word-wrap:break-word;color:#ca922d">'
																			+ pricename
																			+ '</font>&nbsp;'
																			+ '<i><font style="font-size: 12px;font-weight: 700;color:#ca922d">&yen;</font></i>&nbsp;'
																			+ '<i><font style="font-size: 14px;color:#ca922d">&yen;</font></i><font style="font-size: 14px;color:#ca922d">'
																			+ v1.specialprice[0]
																			+ '</font>';

																} else {
																}
															}

															append += '</em></span>'
																	+ '</div>'
																	+ '</a>'
																	+ '</li>';
															$ul
																	.append(append);
														})

									});

				} else {
					$("#moreButton").css("visibility", "hidden");
					$("#moreButton").show();

				}

			}
		});

}