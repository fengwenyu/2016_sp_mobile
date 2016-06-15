$(function(){

    //选择收货地址
    var addrTxt,thisCon,content,that,obj,title,prev,thatDl;
    $("#select_area").click(function(){
        $("#forth_id").val("");
        $(".prev_btn").hide();
        $("#address_area_overlay").height($(document).height());
        $("#address_area_overlay, #address_area_layer").show();
        addrTxt = [];
        return false;
    });

    $("#address_area_layer").delegate("a","click",function(){
        var that = $(this),
            prev = $(".prev_btn");
        obj = $("#address_area_layer dd  a");
        content = $("#address_area_layer dl");
        thisCon = that.closest("dl");
        title = $("#address_area_layer h3");
        obj.removeClass("cur");
        that.addClass("cur");
        thatDl = thisCon;

        var dl_id = thisCon.attr("id");
        if(dl_id == "area_province"){
            $("#province").val(that.attr("id"));
            $("#provincename").val(that.text());
        }else if(dl_id == "area_city"){
            $("#city").val(that.attr("id"));
            $("#cityname").val(that.text());
        }else if(dl_id == "area_county"){
            $("#area").val(that.attr("id"));
            $("#areaname").val(that.text());
        }else if(dl_id == "area_street"){
            $("#town").val(that.attr("id"));
            $("#townname").val(that.text());
        }
        //选择结果
        addrTxt.push(that.text());
        setTimeout(function(){
            if(thisCon.next("dl").length > 0){
                prev.show(); // 返回上一级
                content.hide();
                thisCon.next("dl").show();
                title.html(thisCon.next("dl").attr("title"));
                if(thisCon.next("dl").attr("id") == "area_city"){
                    $("#area_city").empty();
                    $.post("../address/city",{proviceId : that.attr("id")},function(data){
                        $.each(data,function(index,item){
                            $("#area_city").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
                        });
                    },"json");
                }else if(thisCon.next("dl").attr("id") == "area_county"){
                    $("#area_county").empty();
                    $.post("../address/area",{cityId : that.attr("id")},function(data){
                        $.each(data,function(index,item){
                            $("#area_county").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
                        });
                    },"json");
                }else if(thisCon.next("dl").attr("id") == "area_street"){
                    $("#area_street").empty();
                    $.post("../address/town",{areaId : that.attr("id")},function(data){
                        $.each(data,function(index,item){
                            $("#area_street").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
                        });
                    },"json");
                }
            }else{
                //返回初始状态
                content.hide();
                $("#address_area_overlay, #address_area_layer").hide();
                $("#address_area_layer dl:first").show();
                title.html($("#address_area_layer dl:first").attr("title"));
                var length = addrTxt.length -1;
                if(addrTxt[length] == '关闭'){
                    $("#select_area").text("省市区");
                    $("#province").val("");
                    $("#provincename").val("");
                    $("#city").val("");
                    $("#cityname").val("");
                    $("#area").val("");
                    $("#areaname").val("");
                    $("#town").val("");
                    $("#townname").val("");
                }else{
                    $("#select_area").text(addrTxt.join(" "));
                }
            }
        }, 10);
        return false;
    });

    //返回上一级
    $(".prev_btn").click(function(){
        setTimeout(function(){
            addrTxt.pop();
            content.hide();
            thatDl.show();
            thatDl.find("a").removeClass("cur");
            title.html(thatDl.attr("title"));
            thatDl = thatDl.prev("dl");
            if(title.html() == "省份"){
                $(".prev_btn").hide();
            }
        }, 300);
        return false;
    });

    //收货地址的弹层关闭
    $(".close_btn, #address_area_overlay").click(function(){
        $("#address_area_layer, #address_area_overlay").hide();
        $("#address_area_layer h3").show().html("省份");
        $("#address_area_layer").find("dl").eq(0).show().siblings("dl").hide();
        $("#address_area_layer dd a").removeClass("cur");
        $("#select_area").text("省市区");
        $("#province").val("");
        $("#provincename").val("");
        $("#city").val("");
        $("#cityname").val("");
        $("#area").val("");
        $("#areaname").val("");
        $("#town").val("");
        $("#townname").val("");
    });
});






