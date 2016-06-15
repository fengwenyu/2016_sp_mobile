//评论筛选tab
$(".comment_tab").find("li").on("click",function(){
	$(this).addClass("cur").siblings().removeClass("cur");	
	var tagId = $(this).children("#tagId").val();
	var commentCount=$('#commentCount').val();
	getComment("1",commentCount,tagId);
})

function getSize() {
	$("#for_aftersale").hide();
	$(".other_service").hide();
	$(".other_service_ep").hide();
	$(".list-box").hide();
	
	$("#tabs_txt0").children().remove();
	var path = getRootPath();
	$.ajax({
		url : path + "/product/getSize",
		data : {
			"productNo" : $('#productNo').val()
		},
		dataType : "json",
		success : function(data) {
			var len = 0;
			var len2 = 0;
			var groupsLen=0;
			if (data.productSize.productDetailSize != null) {
				len = data.productSize.productDetailSize.table.length;
			}
			if (data.productSize.property != null) {
				len2 = data.productSize.property.length;
			}
			var groups=data.groups;
			if (groups != null) {
				groupsLen = groups.length;
			}
			if(groupsLen>0){
				$.each(groups, function(index0, item0) {
					
					$table = $("<table></table>");
					$thead = $("<thead></thead>");
					$tbody = $("<tbody></tbody>");
					if (len > 0) {
						
						$.each(data.productSize.productDetailSize.table, function(index, item) {
							$tr = $("<tr></tr>");
							$td = $("<td></td>");
							$td.addClass("left");
							$td.text(item.name);
							$tr.append($td)
							var valueLen=0
							$.each(item.value, function(n, v) {
								if(index0 * 4<=n&&n<=(index0 + 3*(index0+1))){
									$td1 = $("<td></td>");
									$td1.text(v);
									$tr.append($td1)
									valueLen++;
								}
							
							});
							if(valueLen==1){
								$tr.append("<td></td><td></td><td></td>")
							}
							if(valueLen==2){
								$tr.append("<td></td><td></td>")
							}
							if(valueLen==3){
								$tr.append("<td></td>")
							}
							if (index == 0) {
								$thead.append($tr);
							} else {
								$tbody.append($tr);
							}

						
						});
						$table.append($thead);
						$table.append($tbody);
						$("#tabs_txt0").append($table);
					}
				});
			}
			
			
			if (len2 > 0) {
				$div = $("<div></div>");
				$div.addClass("commodity_index");
				$h6 = $("<h6></h6>").text("商品指数");
				$div.append($h6);
				
				
				$.each(data.productSize.property, function(index1, item2) {
					$dl = $("<dl></dl>");
				

					$dt = $("<dt></dt>").text(item2.title);
					$dd = $("<dd></dd>");
					$ul = $("<ul></ul>");
					$.each(item2.values, function(n1, v1) {
						$li = $("<li></li>");
						if (n1 == item2.current) {
							$li.addClass("curr");
						}
						$i = $("<i></i>");
						$span = $("<span></span>");
						$span.text(v1);
						$li.append($i);
						$li.append($span);
						$ul.append($li);
					});
					$dd.append($ul);
					$dl.append($dt);
					$dl.append($dd);
					$div.append($dl);
					
				});
				$("#tabs_txt0").append($div);
			}
			$img_clt= $("<img></img>");
			var modelClothPic=null;
			if(data.productSize!=null&&data.productSize.modelClothPic!=null&&data.productSize.modelClothPic!=""){
				modelClothPic=data.productSize.modelClothPic.substring(0,data.productSize.modelClothPic.indexOf('-')) + "-350-280.jpg";
				
				
				$img_clt.attr({
					"src" : modelClothPic,
					"alt":"尺寸测量方法"
				});
				$("#tabs_txt0").append($img_clt);
			}
			
			
			if(len<=0&&len2<=0&&modelClothPic==null){
				$("#tabs_txt0").append("<div class='no_size'> <p>暂无尺码说明</p> </div>");
			}
		
		}
	});
}

//function getTemplate() {
//	$("#for_aftersale").hide();
//	$(".other_service").hide();
//	$(".other_service_ep").hide();
//	$(".list-box").hide();
//	var path = getRootPath();
//	$.ajax({
//		url : path + "/product/getTemplate",
//		data : {
//			"productNo" : $('#productNo').val(),
//			"type" : "5"
//		},
//		dataType : "json",
//		success : function(data) {
//			$("#tabs_txt1").html(data.productTemplate.html);
//		}
//	});
//}

function getComment(start,commentCount,tagId) {
	/*var start=$('#pageIndex').val();
	var commentCount=$('#commentCount').val();*/
	$(".produce_comment").children().remove();
	if(commentCount*1==0)
	{
		$("#produce_comment").append("<div class='no_comment'> <p>暂无评价</p> </div>");
	}
	//if(start*3<=(commentCount*1+3)){
		var path = getRootPath();
		$.ajax({
			url : path + "/product/getComment?"+Math.random(),
			data : {
				"productNo" : $('#productNo').val(),
				"pageIndex" : start,
				"pageSize" : 20,
				"tagId" : tagId
				},
			dataType : "json",
			success : function(data) {
				var len = 0;
				if (data.productComment.list != null) {
					len = data.productComment.list.length;
				}
				if (len > 0) {
					$.each(data.productComment.list, function(index, item) {
						$div_concom = $("<div></div>");
						$div_concom.addClass("content_comment");
						$div_concom_h = $("<div></div>");
						$div_concom_h.addClass("content_comment hidden");
	
						$div = $("<div></div>");
						$div.addClass("comment_user");
						$div_uc = $("<div></div>");
						$div_uc.addClass("user_cont");
						$div_ucl = $("<div></div>").addClass("user_class");
						$a = $("<a></a>");
						$a.attr({
							"href" : ""
						});
						$img = $("<img></img>");
						var userIcon;
						if(item.userIcon!=null&&item.userIcon!=''){
							userIcon=item.userIcon
						}else{
							userIcon=path+"/styles/shangpin/images/detail/user.jpg";
						}
						$img.attr({
							"src" : userIcon,
							"width":"35px",
							"height":"35px"
						});
						$a.append($img);
						$span = $("<span></span>").addClass("user_name").text(
								item.userName);
						/*$span_st = $("<span></span>").addClass(
								"starlevel_" + item.stars);*/
						$span_ct = $("<span></span>").addClass("comment_time")
								.text(item.strDate);
	
						$div_uc.append($a);
						$div_uc.append($span);
						/*$.each(item.tags, function(n, v) {
							$span_us = $("<span></span>").addClass("user_size");
							$span_us.text(v);
							$div_uc.append($span_us);
						});*/
						/*$div_ucl.append($span_st);*/
						$div_ucl.append($span_ct);
						$div.append($div_uc);
						$div.append($div_ucl);
	
						$div_cmt = $("<div></div>");
						$div_cmt.addClass("comment_cont");
						$p = $("<p></p>").text(item.desc);
						$div_cmt.append($p);
	
						$div_cImg = $("<div></div>");
						$div_cImg.addClass("comment_img");
						$ul = $("<ul></ul>")
						$.each(item.pics, function(n1, v1) {
							$li = $("<li></li>");
							$img = $("<img></img>");
							$img.attr({
								"src" : v1
							});
							$li.append($img);
							$ul.append($li);
						});
						$div_cImg.append($ul);
	
						$p1 = $("<p></p>");
						$span0 = $("<span></span>");
						$span1 = $("<span></span>");
						$span2 = $("<span></span>");
						
						$.each(item.tags, function(n, v) {
							$span0.text(v);
						});
						if(item.productInfo!=null&&item.productInfo!=''){
							$span1.text(item.productInfo);
						}
						if(item.userInfo!=null&&item.userInfo!=''){
							$span2.text(item.userInfo);
						}
						$p1.append($span0);
						$p1.append($span1);
						$p1.append($span2);
						$div_cImg.append($p1);
						
						$div_reply = $("<div></div>");
						$.each(item.reply, function(n2, v2) {
							
							if(v2.from!=null||v2.desc!=null){
								$div_reply.addClass("comment_reply");
		
								$i = $("<i></i>");
								$h6 = $("<h6></h6>").text(v2.from);
								$p = $("<p></p>").text(v2.desc);
								$div_reply.append($i);
								$div_reply.append($h6);
								$div_reply.append($p);
							}
						});
						$div_concom.append($div);
						$div_concom.append($div_cmt);
						$div_concom.append($div_cImg);
						$div_concom.append($div_reply);
						$(".produce_comment").append($div_concom);
	
					});
					
					$("#pageIndex").val(start*1+1);
					
				}else{
					if(start==1){
						$(".produce_comment").append("<div class='no_comment'> <p>暂无评价</p> </div>");
					}
			            
				}
			}
		});
	//}
}

function collect(){
	
	var path = getRootPath();
	$.ajax({
			url : path + "/collect/product",
			data : {
				"skuId" : $("#lowestInfo").attr("sku")
			},
			
			success : function(data,textStatus, XMLHttpRequest) {
				var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
				if(sessionstatus=="timeout"){
					var locationURL = XMLHttpRequest.getResponseHeader("locationURLAll"); // 通过XMLHttpRequest取得响应头，locationURL，
					// 如果超时就处理 ，指定要跳转的页面
					window.location.href  =path+"/login?back=/product/detail_"+$("#productNo").val();
					return;
				}
				if ("0" == data.code) {//成功
					$("#collectId").val(data.collectId);
					   $(".collection_commodity").addClass("already_collection");
			            return jShare('收藏成功',"",""),
			            $("#J_userName").addClass("error"),
			            !1;
				} else if ("2" == data.code) {//未登录
					window.location.href=path+"/login?back=/product/detail_"+$("#productNo").val();
					return;
				} else {//失败
			            return jShare('收藏失败',"",""),
			            $("#J_userName").addClass("error"),
			            !1;
				}

			}
	});
}
function cancleCollect(){
	var path = getRootPath();
	 $.ajax({
			url : path + "/collect/cancle/product",
			data : {
				"collectId" : $("#collectId").val()
			},
			success : function(data,textStatus, XMLHttpRequest) {
				var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
				if(sessionstatus=="timeout"){
					var locationURL = XMLHttpRequest.getResponseHeader("locationURLAll"); // 通过XMLHttpRequest取得响应头，locationURL，
					// 如果超时就处理 ，指定要跳转的页面
					window.location.href  =path+"/login?back=/product/detail_"+$("#productNo").val();
					return;
				}
				if ("0" == data.code) {//成功
		            $(".collection_commodity").removeClass("already_collection");
		            return jShare('取消收藏',"",""),
		            $("#J_userName").addClass("error"),
		            !1;
				} else if ("2" == data.code) {//未登录
					window.location.href=path+"/login?back=/product/detail_"+$("#productNo").val();
					return;
				} else {//失败
		            
		            return jShare('取消收藏失败',"",""),
		            $("#J_userName").addClass("error"),
		            !1;
				}

			}
	});
} 

