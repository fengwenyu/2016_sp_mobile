$(function(){
	var menu_lv = 2;
/* ==================================== 左侧菜单事件 ==================================== */
	//菜单切换
	var preMenu = $("#pre_menu_item").html();
	$("a.js_addL1Btn").click(function(){
	    
	    var $parent = $(this).parent("li");
	    
	    //隐藏现有选中菜单
	    $parent.siblings("li.jsMenu").removeClass("current selected").find(".sub_pre_menu_box").hide();
	    
	    //新增加一个
	    $parent.before(preMenu);
	    
	    //最多三个，超出隐藏新增按钮
	    if($("li.jsMenu").length > 2){
	        $parent.hide();
	    }
	    
	    //显示右侧表单内容
	    $("#js_none").hide();
	    $("#js_rightBox").show();
	    
	});
	//一级菜单点击
	$("#menuList").delegate("li.jsMenu","click",function(e){
	    $(this).addClass("current selected").find(".sub_pre_menu_box").show();
	    $(this).siblings("li.jsMenu").removeClass("current selected").find(".sub_pre_menu_box").hide();
	    var menu_name = $(this).find(".js_l1Title").text();
	    $(".js_menu_name").val(menu_name);
	    
	    //如果有子菜单只能修改菜单名称
	    if($(this).find(".sub_pre_menu_list .jslevel2").length > 0){
	        $(".js_setContentBox, .menu_content_container").hide();
	        $("#js_innerNone").show();
	        
	    }else{
	        $(".js_setContentBox, .menu_content_container").show();
	        $("#js_innerNone").hide();
	        var type = $(this).find("input").attr("name");
		    if(type == "click"){
				$(".js_radio_sendMsg").addClass("selected").siblings().removeClass("selected");
				$("#edit").show().siblings().hide();
				var text = $(this).find("input").val();
				$("#edit").find("#edit_text").text(text);
		    }else if(type == "view"){
		    	$(".js_radio_url").addClass("selected").siblings().removeClass("selected");
				$("#url").show().siblings().hide();
				var url = $(this).find("input").val();
				$("#url").find("input").val(url);
		    }
	    }
	    
	    //取消菜单选中状态
	    $("li.jslevel2").removeClass("current selected");
	    
	    //显示右侧表单内容
	    $("#js_none").hide();
	    $("#js_rightBox").show();
	    
	    //阻止默认事件
	    e.stopPropagation();
	    e.preventDefault();
	    
	});



	//子菜单切换
	var subMenu = $("#subMenu_menu_item").html();
	$("#menuList").delegate("a.js_addL2Btn","click",function(e){
	    var $parent = $(this).parent("li");
	    
	    //隐藏现有选中菜单
	    $parent.siblings("li.jslevel2").removeClass("current selected");
	    $parent.closest("li.jsMenu").removeClass("current selected");
	    
	    //新增加一个
	    $parent.before(subMenu);
	    
	    var current_menu_text = $parent.siblings("li.current").find(".js_l2Title").text();
	    $(".js_menu_name").val(current_menu_text);
	    $(".js_radio_sendMsg").addClass("selected");
	    
	    //每个菜单最多五个子菜单，超出隐藏新增按钮
	    if($parent.siblings("li.jslevel2").length > 4){
	        $parent.hide();
	    }
	    
	    //菜单显示有子菜单图标
	    $parent.closest(".sub_pre_menu_box").siblings(".pre_menu_link").find(".js_icon_menu_dot").show();
	    
	    //恢复子菜单默认编辑
	    $(".js_setContentBox, .menu_content_container").show();
	    $("#js_innerNone").hide();
	    
	    //阻止默认事件
	    e.stopPropagation();
	    e.preventDefault();
	    
	});

	//子菜单点击
	$("#menuList").delegate("li.jslevel2","click",function(e){
	    $(this).addClass("current selected").siblings("li.jslevel2").removeClass("current selected");
	    $(this).closest("li.jsMenu").removeClass("current selected");
	    var sub_menu_name = $(this).find(".js_l2Title").text();
	    $(".js_menu_name").val(sub_menu_name);
	    //
	    var type = $(this).find("input").attr("name");
	    if(type == "click"){
			$(".js_radio_sendMsg").addClass("selected").siblings().removeClass("selected");
			$("#edit").show().siblings().hide();
			var text = $(this).find("input").val();
			$("#edit").find("#edit_text").text(text);
	    }else if(type == "view"){
	    	$(".js_radio_url").addClass("selected").siblings().removeClass("selected");
			$("#url").show().siblings().hide();
			var url = $(this).find("input").val();
			$("#url").find("input").val(url);
	    }
	    //恢复子菜单默认编辑
	    $(".js_setContentBox, .menu_content_container").show();
	    $("#js_innerNone").hide();
	    
	    //阻止默认事件
	    e.stopPropagation();
	    e.preventDefault();
	});
	
	//保存跳转网址input值
	$("#urlText").blur(function(){
		var url = $(this).val();
		var regexp = new RegExp("(http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?", "gi");;
		if(!url.match(regexp)){
			alert("链接格式不正确");
			return;
		}
		var $li = $(body).find("li.current");
		$li.find("input").attr("name","view");
		$li.find("input").val(url);
	});
	
	//保存发送消息的值
	$("#edit_text").blur(function(){
		var text = $(this).text();
		var $li = $(body).find("li.current");
		$li.find("input").attr("name","click");
		$li.find("input").val(text);
	});
	
	//菜单名称
	$(".js_menu_name").blur(function(){
		var val = $(this).val();
		var $li = $(body).find("li.current");
		$li.find("i").eq(0).next("span").text(val);
	});



/* ==================================== 右侧表单事件 ==================================== */

	//菜单内容tab切换事件
	var tabs1 = function(nav,content){
	    $(nav).bind("click",function(){
	        var index = $(this).index();
	        $(this).addClass("selected").siblings().removeClass("selected");
	        $(content).eq(index).show().siblings(content).hide();
	    });
	}
	tabs1(".frm_radio_label", ".jsMain");


	//表单内容tab切换事件
	var tabs2 = function(nav,content){
	    $(nav).find("li").bind("click",function(){
	        var index = $(this).index();
	        $(this).addClass("selected").siblings().removeClass("selected");
	        $(content).eq(index).show().siblings(content).hide();
	    });
	}
	tabs2(".js_tab_navs", ".tab_content");


	//表情调用
	$(".js_switch").click(function(){
	    $(".js_emotionArea").show();
	});
	
	$(".emotions .emotions_item").each(function(index) {
	    $(this).click(function(){
	        $(".js_emotionArea").hide();
	    });
	    
	    $(this).find("i").hover(function(){
	        var emotionPath = $(this).attr("data-gifurl");
	        $(".js_emotionPreviewArea img").attr("src", emotionPath);
	    }, function(){
	        $(".js_emotionPreviewArea img").attr("src", "");
	    });
	});


	//上传图片
	$(".jsMsgSenderPopBt").click(function(){
	    $(".ui-draggable").not(".delete_dialog_wrp").show();
	});
	
	$(".pop_closed, .js_btn").click(function(){
	    $(".ui-draggable").hide();
	});

	$(".img_pick .img_item_bd").each(function(index) {
	    var flag=1;
	    $(this).click(function(){
	        if(flag==1){
	            //执行方法;
	            $(this).addClass("selected");
	            $(".img_dialog_wrp .dialog_ft .btn").removeClass("btn_disabled");
	            flag=0;
	        }else{
	            //执行方法;
	            $(this).removeClass("selected");
	            $(".img_dialog_wrp .dialog_ft .btn").addClass("btn_disabled");
	            flag=1;
	        }
	    });
	});


	$("#delete_btn").click(function(){
	    $("#menuList li.current").remove();
	    $("#menuList .sub_pre_menu_box").hide();
	    
	    $li = $(body).find("li.current");
	    if($li.hasClass("jsMenu")){//删除的是一级菜单
	    	menu_lv = 1;
	    }
	    $li.remove();
	    
	    //判断菜单显示状态
	    if($("li.jsMenu").length < 3){
	        $("a.js_addL1Btn").parent(".js_addMenuBox").show();
	    }
	    
	    //判断子菜单显示状态
	    $("li.jsMenu").each(function(index) {
	        if($(this).find("li.jslevel2").length < 5){
	            $(this).find(".js_addMenuBox").show();
	        }
	        if($(this).find("li.jslevel2").length <= 0){
	            $(this).find(".js_icon_menu_dot").hide();
	        }
	    });
	    
	    //隐藏右侧表单内容
	    $("#js_none").show();
	    $("#js_rightBox").hide();
	    
	});
	
	//创建菜单
	$("#pubBt").click(function(){
		var path = $("#ctx").val();
		if($("li.jsMenu").length > 0){
			//组装json字符串
			var menuJson = "{" + '"button"' + ":[";
			//一级菜单  
			var one_length = $(".pre_menu_list").find("li.jsMenu").length;
			$(".pre_menu_list").find("li.jsMenu").each(function(index, item){
				menuJson += "{";
				var one_name = $(item).find("span.js_l1Title").text();
				menuJson += '"name"' + ":" + '"' + one_name + '",';
				//二级菜单
				if($(item).find("li.jslevel2").length > 0){
					menuJson += '"sub_button"' + ":[";
					var sub_length = $(item).find("li.jslevel2").length;
					$(item).find("li.jslevel2").each(function(subIndex, subItem){
						menuJson += "{";
						var two_name = $(subItem).find("span.js_l2Title").text();
						menuJson += '"name"' + ":" + '"' + two_name + '",';
						var two_type = $(subItem).find("input").attr("name");
						var two_val = $(subItem).find("input").val();
						menuJson += '"type"' + ":" + '"' + two_type + '",';
						if(two_type == "click"){
							menuJson += '"key"' + ":" + '"' + two_val + '",';
						}else if(two_type == "view"){
							menuJson += '"url"' + ":" + '"' + two_val + '",';
						}
						menuJson += '"sub_button"' + ":[]";
						if((subIndex + 1) < sub_length){
							menuJson += "},";
						}else{
							menuJson += "}";
						}
						
					});
				}else{
					var one_type = $(item).find("input").attr("name");
					var one_val = $(item).find("input").val();
					menuJson += '"type"' + ":" + '"' + one_type + '",';
					if(one_type == "click"){
						menuJson += '"key"' + ":" + '"' + one_val + '",';
					}else if(one_type == "view"){
						menuJson += '"url"' + ":" + '"' + one_val + '",';
					}
					menuJson += '"sub_button"' + ":[]";
				}
				
				if((index + 1) < one_length){
					if($(item).find("li.jslevel2").length > 0){
						menuJson += "]},";
					}else{
						menuJson += "},";
					}
				}else{
					if($(item).find("li.jslevel2").length > 0){
						menuJson += "]}";
					}else{
						menuJson += "}";
					}
				}
			});
			menuJson += "]}";
			console.log(menuJson);
			$.post(path + "/menu_create.action",{
				jsonMenu : menuJson
			},function(data){
				if(data.errcode == 0){
					alert("创建成功！");
				}else{
					alert(data.errmsg);
				}
			},"json");
		}else{//删除所有菜单
			$.post(path + "/menu_del.action",function(data){
				if(data.errcode == 0){
					alert("删除成功！");
				}else{
					alert(data.errmsg);
				}
			},"json");
		}
	});
	
	//删除菜单事件
	$("#jsDelBt").click(function(){
	    $(".ui-draggable").not(".img_dialog_wrp").show();
	    
	});
});