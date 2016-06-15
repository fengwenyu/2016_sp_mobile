$(function(){
	var obj = $(".search_box"),
		sInput = obj.find("input"),
		sClose = obj.find("button"),
		sBtn = $(".search_btn");
		
	!sInput.val() && (sClose[0].style.display = "none");
	
	var fn = function(){
		sInput.removeAttr("placeholder");
		obj.attr("class", "slideLeft");
		$(".topSearch fieldset").addClass("curr");
		sBtn.show();
	};
	var fn2 = function(){
		sClose[0].style.display = "block";
	};
	sInput.focus(fn),
	sInput.keyup(fn2),
	sClose.click(function(e) {
		sInput.val("").focus(),
		sClose[0].style.display = "none";
		e.preventDefault();
	}),
	sInput.blur(function(){
		setTimeout(function(){sClose[0].style.display = "none"},100);
	});
	
	//取消按钮事件
	sBtn.click(function(e){
		sInput.val("");
		sClose[0].style.display = "none",
		obj.attr("class", "slideRight");
		$(".topSearch fieldset").removeClass("curr");
		sBtn.hide();
		sInput.blur();
		if(sInput.val() == ""){
			sInput.attr("placeholder", "请输入货号进行搜索");
		}
		e.preventDefault();
	});
	
	//如果默认值不为空显示取消按钮
	if(sInput.val().length > 0){
		obj.attr("class", "slideLeft");
		sClose[0].style.display = "block";
		sBtn.show();
	}
});