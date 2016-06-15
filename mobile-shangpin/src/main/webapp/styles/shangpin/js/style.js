var getMore = function() {
	var path = getRootPath();
	var pageindex = 0;
	$("#haveMore").hide();
	pageindex = $("#pageIndex").val();
	$.post(path+"/style/getMore", {
		"pageIndex" : pageindex
	}, function(data) {
		if (parseInt(data.haveMore) == 1) {
			$("#haveMore").text("点击查看更多");
			$("#haveMore").attr("href", "javascript:getMore()");
			$("#haveMore").show();
		} else {
			$("#haveMore").remove();
			$(".alList_moreBtn").css("display", "none");
		}
		$("#pageIndex").val(data.pageIndex);
		var looklist = data.look;
		var $div = $(".styleBigIMG ");
		var result = '';
		$.each(looklist, function(i, item) {
			result += '<dl>';
			result += '<dt>';
			var hrefStr;

			hrefStr = path+"/style/list";

			result += "<a href=" + hrefStr + "?id=" + item.id + ">";
			var pic = item.pic;
			pic = pic.replace("-10-10", "-580-670");
			result += "<img  src=" + pic + " width=\"100%\">";
			result += "</a>";
			result += "</dt>";
			result += "<dd>";
			var style = item.style;
			$.each(style, function(i, style) {
				result += "<span>";
				result += style;
				result += "</span>";
			});
			result += "</dd>";
			result += "</dl>";
		});
		$div.append(result);
	}, "json");
};