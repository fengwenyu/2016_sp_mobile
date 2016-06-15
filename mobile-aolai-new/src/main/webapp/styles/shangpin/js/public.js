
/**
* 判断指定的内容是否为空，true为空
* 
* @param value
*/ 
function isEmpty(value){ 
	if(theValue==""){ 
		return true; 
	} 
	return false; 
} 

/**
 * 省市区级联操作
 * 
 * @param province
 *            省jquery对象(select对象)
 * @param city
 *            市jquery对象(select对象)
 * @param area
 *            区jquery对象(select对象)
 */
function cascadeStage(province, city, area,town) {
	// 省级事件
	province.change(function() {
		if ($(this).val() == -1)
			return;
		// 清空其它2个下拉框
		city[0].options.length = 0;
		area[0].options.length = 0;
		town[0].options.length = 0;
		area.append("<option value='-1'>请选择地区</option>");
		town.append("<option value='-1'>请选择街道</option>");
		// ajax获取相应的市级数据
		$.post("city", {
			"id" : $(this).val()
		}, function(data) {
			city.append("<option value='-1'>请选择城市</option>");
			// 迭代返回的json数据
			$.each(data.content, function(index, content) {
				// 动态装载市级数据
				city.append("<option value='" + content.id + "'>"
						+ content.name + "</option>");
			});
		}, "json");
	});

	// 市级事件
	city.change(function() {
		if ($(this).val() == -1)
			return;
		// 清空区级下拉框
		area[0].options.length = 0;
		area.append("<option value='-1'>请选择地区</option>");
		// ajax获取相应的市级数据
		$.post("area", {
			"id" : $(this).val()
		}, function(data) {
			// 迭代返回的json数据
			$.each(data.content, function(index, content) {
				// 动态装载市级数据
				area.append("<option value='" + content.id + "'>"
						+ content.name + "</option>");
			});
		}, "json");
	});
	
	// 地区事件
	area.change(function() {
		if ($(this).val() == -1)
			return;
		// 清空区级下拉框
		town[0].options.length = 0;
		// ajax获取相应的市级数据
		$.post("town", {
			"id" : $(this).val()
		}, function(data) {
			town.append("<option value='-1'>请选择街道</option>");
			// 迭代返回的json数据
			$.each(data.content, function(index, content) {
				// 动态装载市级数据
				town.append("<option value='" + content.id + "'>"
						+ content.name + "</option>");
			});
		}, "json");
	});
}