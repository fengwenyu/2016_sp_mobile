/**
 * Created by cuibinqiang on 2015/12/17.
 */
$(function() {
    //点击选择图片  //点击上一页  //点击下一页  //跳转到某页
    $(".jsMsgSenderPopBt,.page_prev, .page_next, .page_go,.tab_img .Js_reply_add").click(function () {

        if ($(this).hasClass("jsMsgSenderPopBt")) {
            $(".dialog_wrp").hide();
            $(".img_dialog_wrp").show();
        }

        //如果点击的下一页 页码自增
        var curPage = 1;
        var count = 10;
        if ($(this).hasClass("page_prev")) {
            curPage = parseInt($("#wxPagebar_currPage").text()) - 1;
        }
        if ($(this).hasClass("page_next")) {
            curPage = parseInt($("#wxPagebar_currPage").text()) + 1;
        }
        if ($(this).hasClass("page_go")) {
            curPage = parseInt($("#wxPagebar_pageGo").val());
        }

        //ajax请求数据
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/autoReplyAction_getMaterialList.action"
            + "?type=image&page=" + curPage + "&count=" + count,
            dataType: "json",
            success: function (data) {

                var _li = "";
                for (var i = 0; i < data.item.length; i++) {
                    var mediaId = data.item[i].media_id;
                    var mediaName = data.item[i].name;
                    var mediaUrl = data.item[i].url;
                    _li += '<li class="img_item js_imageitem" data-id="' + mediaId + '" data-url="' + mediaUrl + '"data-oristatus="0" data-format="jpeg">' +
                        '<label class="frm_checkbox_label img_item_bd">' +
                        '<img src="' + mediaUrl + '" alt="" class="pic"><span class="lbl_content">' + mediaName + '</span>' +
                        '<div class="selected_mask"><div class="selected_mask_inner"></div>' +
                        '<div class="selected_mask_icon"></div></div></label></li>'
                }
                //添加图片
                $(".img_list").empty().append(_li);

                var pageCount = data.total_count / count;
                pageCount = parseInt(pageCount > parseInt(pageCount) ? pageCount + 1 : pageCount);

                //设置页码
                $("#wxPagebar_currPage").text(curPage);
                $("#wxPagebar_totalPage").text(pageCount);

                //根据页码设置上下一页的显示隐藏
                if (curPage == 1) {
                    $(".page_prev").hide();
                    $(".page_next").attr("style", "display: inline-block;");
                } else if (curPage == pageCount) {
                    $(".page_prev").attr("style", "display: inline-block;");
                    $(".page_next").hide();
                }
                else {
                    $(".page_prev").attr("style", "display: inline-block;");
                    $(".page_next").attr("style", "display: inline-block;");
                }

            }
        });
        //$(".ui-draggable").show();
    });

    $(".pop_closed, .js_btn").click(function () {
        $(".ui-draggable").hide();
    });

    //点击图片选中
    $(".frm_checkbox_label").live("click",function(){
        if($(this).hasClass("selected")){
            $(this).removeClass("selected");
            //确定不可点击
            $(".img_dialog_wrp .dialog_ft .btn_primary").addClass("btn_disabled");
        }else{
            $(".frm_checkbox_label").removeClass("selected");
            $(this).addClass("selected");
            //确定可点击
            $(".img_dialog_wrp .dialog_ft .btn_primary").removeClass("btn_disabled");
        }
    });

    //长传图片按钮点击
    $(".upload_file_btn").click(function(){
        $(this).parent("form").submit();
    });



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


})