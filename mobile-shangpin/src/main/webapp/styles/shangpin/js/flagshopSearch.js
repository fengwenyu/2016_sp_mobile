function getMore(checkAPP) {
	var path = getRootPath();
	
	var start = parseInt($('#start').val()) + 1;
	var pageNo = parseInt($("#pageNo").val()) + 1;
	$('#start').val(start);
	var searchConditions = $('#J_Search').serialize();
	$.post(
					path + "/search/flagshopProduct/list/more",
					searchConditions,
					function(data) {
						var productList = data.searchResult.productList;
						var len = productList.length;
						var hasMore = data.hasMore;
						if (len > 0) {
							$("a").remove(".list_moreLink");
							$.each(productList,function(i, item) {
												var $list_box = $("#list_box");
												var $a = $("<a/>");
												var hrefV = '';
												if (checkAPP) {
													hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="
															+ item.productId;
												} else {
													hrefV = path
															+ "/product/detail?productNo="
															+ item.productId;
												}

												$a.attr("href", hrefV);
												var pic = item.productPicUrl.substring(0,item.productPicUrl.indexOf('-')) + "-318-422.jpg";
												var $span = $("<span/>");
												$span.addClass("img_box");
												var $img = $("<img/>");
												$img.attr("src",pic);
												$img.attr("width", "80");
												$img.attr("height", "108");
												$span.append($img);
												var $dl = $("<dl/>");
												var $dt = $("<dt/>");
												$dt.text(item.brandNameEN);
												$dl.append($dt);
												var $dd = $("<dd/>");
												$dd.text(item.productName);
												$dl.append($dd);
												var $dd2 = $("<dd/>");
												$em = $("<em/>");
												// 需要判断根据用户级别显示不同的价格
												if (data.userLv == "0002") {
													$em.text("¥"+ item.goldPrice);
												} else if (data.userLv == "0003") {
													$em.text("¥"+ item.platinumPrice);

												} else if (data.userLv == "0004") {
													$em.text("¥"+ item.diamondPrice);
												} else {
													$em.text("¥"+ item.limitedPrice);
												}
												$dd2.append($em);
												$i1 = $("<i/>");
												$i1.text("¥"+ item.marketPrice);
												$dd2.append($i1);
												$dl.append($dd2);
												$a.append($span);
												$a.append($dl);
												$list_box.append($a);
											});
							if (hasMore == 1) {
								$('.alBrand_list').append(
										"<a class='list_moreLink' href='javascript:getMore("
												+ checkAPP + ");'>点击查看更多</a>");
							}
						}
					}, "json");

};