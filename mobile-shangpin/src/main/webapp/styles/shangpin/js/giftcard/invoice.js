/** 新的发票页面invoice_info 的js************/
(function() {
    window.Login = function() {},
        $.extend(window.Login.prototype, {
            init: function() {},
            loginForm: function() {
                $("#J_Login.inovice_form .payment_submit .payment_btn").on("click",
                    function(e) {
                        var re = /^[\u4e00-\u9fa5]+$/,
                            mre = /^1\d{10}$/,
                            mail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,
                            post = /^[1-9][0-9]{5}$/;

                        //发票抬头公司名称
                        if($(".org_name").css('display')!="none"){

                            if ($.trim($("#J_invoiceName").val()) == ""){
                                return jShare('请输入单位名称!',"",""),
                                    //$("#J_invoiceName").addClass("error"),
                                    null,
                                    !1;
                            }else{
                                $("#J_invoiceName").removeClass("error");
                            }
                        }
                        var invoiceType = $("input[name='invoicetype']").val();
                        if(invoiceType=="1"){
                            $("legend.invo").next("p").next("p").children("a").children("em").eq(1).text("个人");
                        }else{
                            $("legend.invo").next("p").next("p").children("a").children("em").eq(1).text($("#J_invoiceName").val());
                        }

                        //发票内容
                        if ($.trim($("#J_invoiceContent").val()) == ""){
                            return jShare('请选择发票内容!',"",""),
                                $("#J_invoiceContent").addClass("error"),
                                !1;
                        }else{
                            $("#J_invoiceContent").removeClass("error");
                        }

                        //var J_invoiceAddr =  $(".select-address");
                        //收票人手机
                        if ($.trim($("#J_invoicePhone").val()) == "" ){
                            return jShare('请输入11位手机号码!',"",""),
                                //$("#J_invoicePhone").addClass("error"),
                                null,
                                !1;
                        }else if (!mre.test($("#J_invoicePhone").val())){
                            return jShare('请输入11位手机号码!',"",""),
                                //$("#J_invoicePhone").addClass("error"),
                                null,
                                !1;
                        }else{
                            $("#J_invoicePhone").removeClass("error");
                        }

                        //收票人邮箱
                        if ($("#J_invoiceMail").val()!=""){
                            if (!mail.test($("#J_invoiceMail").val())){
                                return jShare('邮箱格式不正确，请重新输入!',"",""),
                                    //$("#J_invoiceMail").addClass("error"),
                                    null,
                                    !1;
                            }else{
                                $("#J_invoiceMail").removeClass("error");
                            }
                        }
                        //如果是个人，发票抬头title 就是个人
                        if( $(".invoice_info p em a.personal").hasClass("active_a")){
                            $("#invoicetype").val("1");//个人类型1
                            $("#invoice_type").text("电子发票");
                            $("#J_invoiceName").val("个人");
                        }else{
                            $("#invoicetype").val("2");//公司类型2
                            $("#invoice_type").text("电子发票");
                        }

                        $("#invoice_title").text($("#J_invoiceName").val());
                        $("#invoicetitle").val($("#J_invoiceName").val());

                        var select_value = $("#J_invoiceContent").children('option:selected').text();
                        $("#invoice_content").text(select_value);
                        $("#invoicecontent").val(select_value);

                        var addressId = $("#addrid").val();
                        $("#invoice_addr").val(addressId);
                        $("input[name='invoiceEmail']").val($("#J_invoiceMail").val());
                        $("input[name='invoiceTel']").val($("#J_invoicePhone").val());

                        showPage ('order_detail','order_header');
                    });
            }
        });
})(jQuery);

//登录表单验证
if($("#J_Login").length > 0){
    var Login = new Login();
    Login.loginForm();
}

$(function(){
    //发票类型选择
    $(".invoice_info p em a").click(function(){
        $(this).addClass("active_a").siblings().removeClass("active_a");
        if($(this).hasClass("personal")){
            $(".org_name").hide();
        }else{
            $("#J_invoiceName").val("单位");
            $(".org_name").show();
        }
    });
})

//显示发票页面
function showPageInvoice(content, header){
    scroll(0,0);
    $("." + content).show().siblings().hide();
    $("#" + header).show();
    $("#J_invoiceName").val($("#invoicetitle").val());

    var select_value = $("#invoicetype").val();
    var person$ = $(".invoice_info p em a.personal");
    var company$ = $(".invoice_info p em a.company");
    if(select_value=="1"){
        if(!person$.hasClass("active_a")){
            company$.removeClass("active_a");
            person$.addClass("active_a");
            $(".org_name").hide();
        }
    }else{
        if(!company$.hasClass("active_a")){
            company$.addClass("active_a");
            person$.removeClass("active_a");
            $(".org_name").show();
        }
        if($("#invoicetitle").val()==""){
            $("#J_invoiceName").val("单位");
        }else{
            $("#J_invoiceName").val($("#invoicetitle").val());
        }
    }

    var invoiceCon = $("#invoicecontent").val();
    var select_value = $("#J_invoiceContent").children('option');
    $.each(select_value,function(index,item){
        $(this).attr("selected",false);
        if($(this).text()==invoiceCon){
            $("#J_invoiceContent").val($(this).val());
        }
    })
    $("#J_invoiceMail").val($("input[name='invoiceEmail']").val());
    $("#J_invoicePhone").val($("input[name='invoiceTel']").val());

}
