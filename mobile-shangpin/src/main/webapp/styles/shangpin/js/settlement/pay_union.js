$(function () {

    $(window).bind('beforeunload', function () {
        return "确定要离开订单提交页吗";
    });

    //***************************************************结算页面刷新begin********************************************/
    function freshSettle() {
        var data = getFreshSettleData();
        return postFreshSettle(data)
    }
    //**检测微信 且没有支付方式 **/
    function payOnly(){
        var wx = $("input[name=\"wx\"]").val();
        if(wx && $.trim($("#pay").html())==""){
            alert("您要支付的商品只支持使用支付宝付款，请使用浏览器打开页面或者下载尚品APP下单!");
            $(window).unbind('beforeunload');
            window.location.href = getRootPath() + "/cart/list";
        }
    }
    payOnly();

    function getFreshSettleData() {

        var buyId = $("input[name='buyId']").val();
        var orderSource = $("input[name='orderSource']").val();
        var receivedId = $("input[name='addressId']").val();
        var domesticCouponFlag = $("input[name='innerUseCouponFlag']").val();
        var domesticCoupon  = $("input[name='innerUseCouponNo']").val();
        var abroadCoupon = $("input[name='outerUseCouponNo']").val();
        var abroadCouponflag = 1;//国外暂时只支持优惠券
        var isUseGiftCard = "0";
        var a$ = $("legend.gift + p.giftCard a");
        if (a$.hasClass("cur")) {
            isUseGiftCard = "1";
        }
        var fresh_param = {
            userId: "",
            buyId: buyId, orderSource: orderSource, receivedId: receivedId,
            domesticCouponflag: domesticCouponFlag, domesticCoupon: domesticCoupon,
            abroadCouponflag: abroadCouponflag, abroadCoupon: abroadCoupon,
            isUseGiftCard: isUseGiftCard
        };
        return fresh_param;
    }

    function postFreshSettle(data) {

        var path = getRootPath();
        var result={suc:true,msg:""};
        $.ajax({
            type: "POST",
            url: path + "/settlement/fresh",
            data: data,
            dataType: "json",
            async:false,//同步 !重要,防止修改结算参数页面没有刷新完提交
            success: function (data) {

                if(data.code=="0"){
                    freshFormFill(data);
                    result.suc =  true;
                }else{
                    result.suc = false;
                    result.msg = data.msg;
                }
            },
            error: function (error) {
                alert("数据错误,请重试!");
            }
        })
        return result;
    }

    function freshFormFill(data) {

        var cartUnion = data.cartUnion;
        var payments = data.payments;
        var received = cartUnion.received;
        var domesticProduct = cartUnion.domesticProduct;
        var abroadProduct = cartUnion.abroadProduct;
        var invoice = cartUnion.invoice;
        var giftCard = cartUnion.giftCard;
        var priceShow = cartUnion.priceShow;
        var buyId = cartUnion.buyId;
        var totalSettlement = cartUnion.totalSettlement;//结算数量
        var totalAmount = cartUnion.totalAmount;// 总金额
        var prompt = cartUnion.prompt;//页面提示
        var isCod = cartUnion.isCod;//   是否支持货到付款。0:否，1是。
        var payCategory = cartUnion.payCategory;//  "1:国内，2：国外，3：支付宝分帐
        domestic_view_fresh(cartUnion.domesticProduct);
        abroad_view_fresh(cartUnion.abroadProduct);
        giftcard_view_fresh(cartUnion.giftCard);
        sum_view_fresh(cartUnion.priceShow,cartUnion.totalAmount);//费用刷新
        pay_view_fresh(payments);//支付方式刷新
        payOnly();
    }

    /* 国内商品信息刷新**/
    function domestic_view_fresh(domesticProduct) {
        if(domesticProduct){
            //运费刷新
            var fre_html = domesticProduct.carriage.title + "<i>" + domesticProduct.carriage.desc + "</i>";
            $("p.freight a").html(fre_html);
            //现金券刷新
            if (domesticProduct.coupon) {
                var title =  domesticProduct.coupon.title;
                var desc = domesticProduct.coupon.desc;
                var count =  domesticProduct.coupon.count;
                var ahtml = "<a href=\"javascript:;\" count=\""+count+"\">"+title+"<i><em>"+desc+"</em></i></a>";
                $("p.cash").html(ahtml);

                if(domesticProduct.coupon.data!=""){
                    $("input[name='innerUseCouponFlag']").val("1");
                    $("input[name='innerUseCouponNo']").val(domesticProduct.coupon.data);
                }
            }

            if(domesticProduct.discount){
                //折扣刷新
                var dis_title=  domesticProduct.discount.title;
                var dis_desc=  domesticProduct.discount.desc;
                var dis_html = "<a class=\"discount_btn\">"+dis_title+"<i>"+dis_desc+"</i></a>";
                $("p.cash").next("p").html(dis_html);
                if(domesticProduct.discount.data){
                    $(".dis_box input").val(domesticProduct.discount.data);
                    $("input[name='innerUseCouponFlag']").val("2");
                    $("input[name='innerUseCouponNo']").val(domesticProduct.discount.data);
                }
            }
        }
    }

    /* 海外商品信息刷新**/
    function abroad_view_fresh(outerProduct) {
        //运费刷新
        if(outerProduct){
            if(outerProduct.list){
                $.each(outerProduct.list,function(index,item){
                    var carriage = item.carriage;
                    var price = item.price;
                    var carr_amount = carriage.amount;
                    $(this).children("ins").children(".freight_info").children("b").text(carr_amount);
                    $(this).children("mark").text(price);
                })
            }
            if (outerProduct.coupon) {
                var title =  outerProduct.coupon.title;
                var desc = outerProduct.coupon.desc;
                var count =  outerProduct.coupon.count;
                var ahtml = "<a href=\"javascript:;\" count=\""+count+"\">"+title+"<i><em>"+desc+"</em></i></a>";
                $("#out_coupon").html(ahtml);

                if(outerProduct.coupon.data!=""){
                    $("input[name='outerUseCouponNo']").val(outerProduct.coupon.data);
                }
            }
        }
    }
    /**礼品卡刷新**/
    function giftcard_view_fresh(giftCard) {
        if(giftCard){
           $(".giftCard").attr("valid",giftCard.valid);
           $(".giftCard a i").attr("amount",giftCard.canUse);
           $(".giftCard a").empty();
           var temp = "";
           if($(".giftCard a").hasClass("cur")){
               temp = "使用礼品卡为本次支付 ¥"+giftCard.canUse;
           }
           $(".giftCard a").html("¥"+giftCard.balance+"<i amount="+giftCard.canUse+">"+temp+"</i>");
        }
    }
    /* 支付方式刷新**/
    function pay_view_fresh(payments) {

        var allhtml = ""
        var firstHtml = "";
        var otherHtml = "";

        //获取之前选中的支付方式
        var payname = "";
        $("p.payment-method").each(function(index,item){
            if($(item).children("span").children("a").hasClass("cur")){
                payname = $(item).children("span").attr("class");
            }
        })

        var isshow = false;
        //判断之前其他支付方式是否显示
        if($("div.other-payment-box").is(":visible")){
            isshow = true;
        }

        var selectFlag = false;
        $.each(payments,function (index,pay) {

            var select_temp ="";
            if(payname==pay.clazz){
                select_temp = "class=\"cur\"";
                selectFlag = true;
            }
            if(pay.isSelected=="1"){

                var ahtml =  "<a href=\"javascript:;\" "+select_temp+">";
                firstHtml = "<p id=\"defaultPay\" class=\"payment-method\" payname="+pay.clazz+">" +
                                "<span class="+pay.clazz+" url="+pay.url+" mainid="+pay.id+" subid="+pay.subpayCode+" way="+pay.way+">"+
                                    "<a href=\"javascript:;\" "+select_temp+">"+
                                    "<em>"+pay.name+"</em>"+
                                    "</a>"+
                                " </span>"+
                            "</p>";
            }else{
                otherHtml += "<p class=\"payment-method\">"+
                    "<span class="+pay.clazz+" url="+pay.url+" mainid="+pay.id+" subid="+pay.subpayCode+" way="+pay.way+">"+
                    "<a href=\"javascript:;\" "+select_temp+">"+
                    "<em>"+pay.name+"</em>"+
                    "</a>"+
                    " </span>"+
                    "</p>";
            }
        });


        $("legend#pay_legend").next("p.pay-method").empty().html(firstHtml);
        var prefix = "<p class=\"select other-payment\"><a href=\"javascript:;\">其他支付方式</a></p>"+
                     "<div class=\"other-payment-box\">";
        var endfix = "</div>";
        if(otherHtml!=""){
            otherHtml = prefix + otherHtml + endfix;
        }
        allhtml = firstHtml+otherHtml;
        $("#pay").empty().html(allhtml);
        //如果目前没有默认值
        if(!selectFlag){
            $("#defaultPay span a").addClass("cur");
        }
        if(isshow){
            $("div.other-payment-box").show();
            $("p.other-payment").hide();
        }

    }
    
    /* 金额刷新 **/
    function sum_view_fresh(priceShow,totalAmount) {

        var feeHtml = "";
        $.each(priceShow,function(i,item){
            var amountShow = "";
            if(item.type=="2" ||item.type=="3" ||item.type=="4" || item.type=="6"){
                amountShow = "&thinsp;&minus;&yen;"+item.amount;
            }else{
                amountShow = "&nbsp;&nbsp;&nbsp;&yen;"+item.amount;
            }
            if(item.amount!='0' || item.type=="1" || item.type=="7"){
                feeHtml += item.title +":<i type='"+item.type+"'>&nbsp;&nbsp;"+amountShow+"</i></br>";
            }
        });
        $("p.total").empty().html(feeHtml);
        $("#order_content div.payment_submit_all i").html("&yen;"+totalAmount);
        $("#order_content div.payment_submit_all p i").attr("total",totalAmount);
    }

    /*   可以引起结算页面刷新的源**/
    /**1. 地址id
     * 2.orderSource;//    1：购物车，2：立即购买    String    必须
     receivedId;// 收货地址ID    String    可选
     giftCardAmount;//    用户礼品卡使用金额    String    可选
     domesticCouponflag;
     domesticCoupon;
     abroadCouponflag;
     abroadCoupon;
     */
    /**********************************************结算页面刷新end*********************************************/
    //返回到主页面
    function backMain() {
        scroll(0, 0);
        $("#order_content").show().siblings().hide();
        $("#order_head").show();
    }

    /******************************************地址相关********************************************/
    /** 地址选择回填**/
    $("#order_content .paymet_block p:first").click(function () {
        $("#address_list_content").show().siblings().hide();
        $("#address_list_head").show();
        //绑定地址点击回调
        var pDom$ = $(this);
        $("#address_list_content").delegate("p", "click", function () {
            //地址不可用默认灰色
            if($(this).hasClass("gray_disable")){
                alert("当前地址不可用！")
                return;
            }else{
                var addressId = $(this).attr("id");//地址id
                var receive_name = $(this).find("i").attr("name");//姓名
                var receive_phone = $(this).find("i").attr("tel");//手机号
                var receive_cardID = $(this).find("i").attr("cardid");//身份证号
                //有国外的商品
                if(hasAboradProduct()){
                    cardIdInput(receive_cardID,pDom$);
                }
                var receive_addr = $(this).find("em").text();//地址
                var forth_id = $(this).attr("town");
                var third_id = $(this).attr("area");
                $("a em", pDom$).text(receive_name);
                $("a i", pDom$).text(receive_phone);
                //是否有身份证的id
                if($("a span:eq(1)",pDom$).length>0){
                    $("a span:eq(1)", pDom$).text(receive_addr);
                }else{
                    $("a span:eq(0)", pDom$).text(receive_addr);
                }
                $("#forth_id").val(forth_id);
                $("#third_id").val(third_id);
                $("input[name='addressId']").val(addressId);
                //刷新结算页
                backMain();
                freshSettle();
                /**设置表单提交值*/

            }
        });
    });

    //身份证id 处理
    function cardIdInput(cardId,pDom$){

        if(!!cardId){
            if($("a span:eq(1)",pDom$).length>0){
                $("a span:eq(0)",pDom$).text(cardId);
            }else{
                $("a i",pDom$).after("<br><span>"+cardId+"</span>");
            }
        }else{
            if($("a span:eq(1)",pDom$).length>0){
                $("a span:eq(0)",pDom$).next("br").remove();
                $("a span:eq(0)",pDom$).remove();
            }
        }
    }

    function hasAboradProduct() {
        if($(".abroad_info").length>0){
            return true;
        }else{
            return false;
        }
    }

    //地址添加返回
    $("#address_add_head .backBtn").click(function () {
       backMain();
    })
    //地址列表返回js
    $("#address_list_head .backBtn").click(function () {
        backMain();
    })

    $("#address_add_content .payment_btn").click(function () {
        if(addAddressListValidate()){
            addAddressList();
        }
    })


    $("#address_list_content a.payment_btn").click(function () {
        scroll(0, 0);
        $("#address_add_content").show().siblings().hide();
        $("#address_add_head").show();
        address_add_clear();
    })

    function addAddressListValidate(){

        var re = /^[\u4e00-\u9fa5]$/;
        var mre = /^1[34578]\d{9}$/;
        var post = /^[1-9][0-9]{5}$/;
        //请选择四级地址
        if($.trim($("#province").val()) == "" || $.trim($("#city").val()) == "" || $.trim($("#area").val()) == "" || $.trim($("#town").val()) == ""){
            return jShare('请选择省市地址!', "", ""), null, !1;
        }

        //收货人地址
        if ($.trim($("#J_addr").val()) == "") {
            return jShare('请输入详细地址!', "", ""), null, !1;
        } else {
            $("#J_addr").removeClass("error");
        }

        //收货人邮编
        if ($.trim($("#J_code").val()) == "" || !post.test($("#J_code").val())) {
            return jShare('请输入正确邮编!', "", ""),
                // $("#J_code").addClass("error"),
                null,
                !1;
        } else {
            $("#J_code").removeClass("error");
        }

        //收货人姓名
        if ($.trim($("#J_userName").val()) == "") {
            return jShare('请输入中文名称!', "", ""),
                null,
                !1;
        } else {
            $("#J_userName").removeClass("error");
        }

        //收货人电话
        if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())) {
            return jShare('请输入11位手机号码!', "", ""),
                // $("#J_mobileNum").addClass("error"),
                null,
                !1;
        } else {
            $("#J_mobileNum").removeClass("error");
        }

        if($("#address_add_cardID").val()!=""){
            //身份证号码
            if (!checkCard($("#address_add_cardID").val())) {
                jShare('请输入正确的身份证号码！', "", ""),null,!1;
                return false;
            }
        }
        return true;
    }

    //结算添加地址提交页提交
    function addAddressList() {

        var path = getRootPath();
        //ajax 提交保存收货地址
        var address = $("#order_address_form").serialize();
        var buyId = $("input[name='buyId']").val();
        var orderSource = $("input[name='orderSource']").val();
        address +="&buyId="+buyId +"&orderSource="+orderSource;
        $.post(path + "/settlement/address/add", address, function (data) {
            if (data.code == 0) {
                //更改地址类表数据
                $(".add_block_list").remove("");
                $.each(data.addresses, function (index, item) {
                    var clazz = "addr_block add_block_list";
                    if(item.enabled=="0"){
                        clazz +=" gray_disable";
                    }
                    var $html = "<p class='"+clazz+"' id='" + item.id + "' title='" + item.cod + "' area='" + item.area + "' town='" + item.town + "'><span><i>" + item.name + "&nbsp;&nbsp;" + item.tel + "</i><em>" + item.provName + item.cityName + item.areaName + item.townName + item.addr + "</em></span></p>"
                    $("#append_address div.payment_submit").before($html);
                });
                var obj = data.addresses[0];
                var receive_name = obj.name;
                var receive_tel = obj.tel;
                var receive_addr = obj.provName + obj.cityName + obj.areaName + obj.townName + obj.addr;
                $("input[name='addressId']").val(obj.id);
                $("#forth_id").val(obj.town);//四级地址
                $("#third_id").val(obj.area);
                var adom$ = $("#order_content p:first a");
                adom$.children("em").text(receive_name);
                adom$.children("i").text(receive_tel);

                var cardId = obj.cardID;
                if(hasAboradProduct()){
                    cardIdInput(cardId,$("#order_content p:first"));
                }
                //是否有身份证的id
                if($("span:eq(1)",adom$).length>0){
                    $("span:eq(1)", adom$).text(receive_addr);
                }else{
                    $("span:eq(0)", adom$).text(receive_addr);
                }
                $("#order_content").show().siblings().hide();
                $("#order_head").show();
                address_add_clear();
                freshSettle();
            } else {
                alert("收货地址添加失败！");
                return;
            }
        }, "json");
    }

    /**
     * 初始化页面数据
     */
    function address_add_clear(){
        $("#J_userName").val("");
        $("#J_mobileNum").val("");
        $("#J_addr").val("");
        $("#J_code").val("");
        $("#select_area").text("省市区");
        $("#address_add_cardID").val("");
        $("#province").val("");
        $("#provincename").val("");
        $("#city").val("");
        $("#cityname").val("");
        $("#area").val("");
        $("#areaname").val("");
        $("#town").val("");
        $("#townname").val("");
    }

    /**********收货时间相关******************************************************************************/

    //选择收货时间
    $("#select_time").click(function () {
        $(".select-overlay").height($(document).height());
        $(".select-overlay, #rece_time_layer").show();
        return false;
    });

    $("#rece_time_layer dd  a").click(function () {

        var that = $(this);
        var obj = $("#rece_time_layer dd  a");
        var timeTxt = that.text();
        obj.removeClass("cur");
        that.addClass("cur");
        setTimeout(function () {

            $(".select-overlay, #rece_time_layer").hide();
            $(".delivery_mode").text(timeTxt);
            /**表单数值修改**/
            $("input[name='deliveryFlag']").val(that.attr("id"));

        }, 300);
        return false;
    });

    //弹层关闭
    $(".close_btn, .select-overlay").click(function () {
        $("#rece_time_layer,.select-overlay,#dis_modes_layer,#select_street_layer").hide();
    });
    /****************************************************************************************/
   //运费弹层
    $(".freight_info").click(function () {
        $(".freight_bg").show();
        return false;
    });
    $(".freight_box a").click(function () {
        $(".freight_bg").hide();
        return false;
    });
    /*******************************发票相关begin******************************************************/
    $(".invo").next().next("p").children("a").click(function () {
        scroll(0, 0);
        $("#invoice_content").show().siblings().hide();
        $("#invoice_head").show();
        showPageInvoice();
    })

    //显示发票页面初始化发票页数据
    function showPageInvoice(){

        var invoiceflag = $("input[name='invoiceflag']").val();//是否开发票
        var invoicetype = $("input[name='invoicetype']").val();//发票类型
        var invoicetitle = $("input[name='invoicetitle']").val();//发票名称
        var invoicecontent =$("input[name='invoicecontent']").val();//发票内容 $("#J_invoiceContent option:selected").val()?$("#J_invoiceContent option:selected").val():"";
        var invoiceemail = $("input[name='invoiceemail']").val();//发票邮箱
        var invoicetel = $("input[name='invoicetel']").val();//发票手机号

        //名称
        $("#J_invoiceName").val(invoicetitle);
        //类型
        var person$ = $(".invoice_info p em a.personal");
        var company$ = $(".invoice_info p em a.company");
        if(invoicetype=="1"){
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
            if(invoicetitle==""){
                $("#J_invoiceName").val("单位");
            }else{
                $("#J_invoiceName").val(invoicetitle);
            }
        }
        //内容
        var select_value = $("#J_invoiceContent").children('option');
        $.each(select_value,function(index,item){
            $(this).attr("selected",false);
            if($(this).text()==invoicecontent){
                $("#J_invoiceContent").val($(this).val());
            }
        })
        $("#J_invoiceMail").val(invoiceemail);
        $("#J_invoicePhone").val(invoicetel);
    }


    $('#yes').click(function () {
        var flag = $(".invo").attr("valid");
        if (flag=="0") {
            alert("全部是海外商品不能开发票");
        } else {
            $('.invoice').show();
            $('#no').removeClass("cur");
            $(this).addClass("cur");
            $("input[name='invoiceflag']").val("1");
        }
    });

    $('#no').click(function () {

        $('.invoice').hide();
        $('#yes').removeClass("cur");
        $(this).addClass("cur");
        $("input[name='invoiceflag']").val("0");
    });

    /**********************************end**********************************************************/
    //点击显示国内优惠券页面
    $("#inner_coupon").delegate("a", "click",function () {
        scroll(0, 0);
        $("#coupons_content").attr("type", "inner");//设置国内还是海外标识
        showCouponPageData("inner");
        $("#coupons_content").show().siblings().hide();
        $("#coupons_head").show();


    })

    //点击显示国外优惠券页面
    $("#out_coupon").delegate("a", "click",function () {
        scroll(0, 0);
        $("#coupons_content").attr("type", "outer");
        showCouponPageData("outer");
        $("#coupons_content").show().siblings().hide();
        $("#coupons_head").show();

    });

    //优惠券返回到主页面
    $("#coupons_head .topBack .backBtn").click(function () {
        backMain();
        freshSettle();
    })

    //优惠券点击选中
    $("#coupons_content ul").delegate("li", "click", function () {
        
        var type = $("#coupons_content").attr("type");
        //取消选中
        if($(this).children("b").hasClass("coupons_selected")){
            $(this).children("b").attr("class","coupons_select");
            if (type == "outer") {
                $("input[name='outerUseCouponNo']").val("0");
            }else {
                $("input[name='innerUseCouponNo']").val("0");
                $("input[name='innerUseCouponFlag']").val("");
            }
        }else{
             $(this).siblings("li").children("b[class=\"coupons_selected\"]").attr("class","coupons_select");
             var id = $(this).children("input[name=\"couponId\"]").val();
             var amount = $(this).children("div").children("i").attr("amount");
             var type = $("#coupons_content").attr("type");
             if (type == "outer") {
                $("input[name='outerUseCouponNo']").val(id);
             }else {
                $("input[name='innerUseCouponNo']").val(id);
                 $("input[name='innerUseCouponFlag']").val("1");
             }
             backMain();
             freshSettle();//刷新结算页
        }
    })

    //显示优惠券页面 type 国内 国外标识
    function showCouponPageData(type) {

        var buyId = "";
        var orderSource = $("input[name='orderSource']").val();
        var pageIndex = "0";
        var pageSize = "200";
        var couponNo = "";
        var useCouponNo = "";
        if (type == "inner") {
            useCouponNo = $("input[name='outerUseCouponNo']").val();
            couponNo = $("input[name='innerUseCouponNo']").val();
            buyId= $("input[name='innerUseCouponBuyId']").val();
        } else {
            useCouponNo = $("input[name='innerUseCouponNo']").val();
            couponNo = $("input[name='outerUseCouponNo']").val();
            buyId= $("input[name='outerUseCouponBuyId']").val();
        }
        var tempData = {
            buyId: buyId, orderSource: orderSource,
            pageIndex: pageIndex, pageSize: pageSize, couponNo: couponNo, useCouponNo: useCouponNo
        };
        $.ajax({
            url: getRootPath() + "/settlement/canUseCoupons",
            type: "post",
            dataType: "json",
            data: tempData,
            success: function (backdata) {
                $(".alContent.order_coupons ul").empty()
                fillCouponPage(backdata);
            }, error: function (errerdata) {
                console.info("coupon error");
            }
        });
    }

    //优惠券页面加载
    function fillCouponPage(data) {
        var amount = data.count;
        var list = data.list;

        var allhtml = "";
        $.each(list, function (i, item) {
            var type = ""
            if (item.type == "0") {
                type = "sale";
            } else {
                type = "cash"
            }
            var bHtml = "";
            if(item.isSelected=="1"){
                bHtml = '<b id="coupon_selected" class="coupons_selected"></b>';
            }else{
                bHtml = '<b id="coupon_selected" class="coupons_select"></b>';
            }
            if(type=="cash"){
                var htmlTem = '<li class="cash">' +
                    '<h4><img src= ' + getRootPath() + '/styles/shangpin/images/order/cash_coupon_angle.png width="69" height="145" /></h4>'
                    + '<input type="hidden" name="couponId" value="' + item.couponno + '"/>'
                    + '<div class="cash">'
                    + '<i amount="' + item.amount + '">&yen;' + item.amount + '</i>'
                    + '<em>' + item.name + '</em>'
                    + '<span><strong>' + item.expirydate + '</strong></span>'
                    + '<span>' + item.rule + '</span>'
                    + '</div>'
                    + '<p><img src=' + getRootPath() + '/styles/shangpin/images/order/cash_coupon.png width="69" height="145" /></p>'
                    + bHtml
                    + '</li>';
                allhtml += htmlTem;
            }else{
                var htmlTem = '<li class="sale">' +
                    '<h4><img src= ' + getRootPath() + '/styles/shangpin/images/order/coupon_angle.png width="69" height="145" /></h4>'
                    + '<input type="hidden" name="couponId" value="' + item.couponno + '"/>'
                    + '<div class="sale">'
                    + '<i amount="' + item.amount + '">&yen;' + item.amount + '</i>'
                    + '<em>' + item.name + '</em>'
                    + '<span><strong>' + item.expirydate + '</strong></span>'
                    + '<span>' + item.rule + '</span>'
                    + '</div>'
                    + '<p><img src=' + getRootPath() + '/styles/shangpin/images/order/coupon.png width="69" height="145" /></p>'
                    + bHtml
                    + '</li>';
                allhtml += htmlTem;
            }


        })
        $(".alContent.order_coupons ul").empty().append(allhtml);
    }

    $("#coupons_code + a").click(function () {
        var code = $("#coupons_code").val();
        activeCoupon(code);
    });

    //发送优惠券激活码
    function activeCoupon(code) {

        if ($.trim($("#coupons_code").val()) == "") {
            return jShare('请输入优惠券激活码!', "", ""),
                $("#coupons_code").addClass("error"), !1;
        } else {
            $("#coupons_code").removeClass("error");
        }
        var path = getRootPath();
        $.post(path + "/coupon/order/active", {"code": "coupon:" + code}, function (data) {

            if (data.code == "0") {
                jShare("亲，优惠券充值成功", "", "");
                $("#coupons_code").val("");
                var type = $("#coupons_content").attr("type");
                if (type == "inner") {
                    showCouponPageData("inner");
                } else {
                    showCouponPageData("outer");
                }
            } else {
                jShare(data.msg, "", "");
                $("#coupons_code").val("");
            }
        }, "json");
    }

    /*******************************************end***************************/
    /*****************************折扣码使用****************************************/
    //折扣码弹层
    $("#inner_discount").delegate(".discount_btn","click",function () {
        //如果之前已经使用了折扣码
        if($("input[name='innerUseCouponFlag']").val()=="2"){
            $(".dis_false").text("取消使用");
        }else{
            $(".dis_false").text("取消");
        }
        $(".discount").show();
        return false;
    });
    $(".dis_false").click(function () {
        //如果之前已经使用了折扣码
        if($("input[name='innerUseCouponFlag']").val()=="2"){
            $("input[name='innerUseCouponFlag']").val("");
            $("input[name='innerUseCouponNo']").val("0");
            freshSettle();
        }
        $(".discount").hide();
        $(".dis_box input").val("");
        return false;

    });
    //折扣码点击确认
    $(".dis_true").click(function () {
        if ($(".dis_box input").val() != "") {

            var flag_before = $("input[name='innerUseCouponFlag']").val();
            var no_before = $("input[name='innerUseCouponNo']").val();
            $("input[name='innerUseCouponFlag']").val("2");
            $("input[name='innerUseCouponNo']").val($(".dis_box input").val());
            var result = freshSettle();
            if(result.suc){
                $(".discount").hide();
            }else{
                $("input[name='innerUseCouponFlag']").val(flag_before);
                $("input[name='innerUseCouponNo']").val(no_before);
                if(flag_before=="2"){
                    $(".dis_box input").val(no_before);
                }else{
                    $(".dis_box input").val("");
                }
                alert(result.msg);
                return false;
            }
        }else{
            alert("请输入折扣码！");
        }
        return false;
    });
    /******************************end*********************************************/
    /*****************************礼品卡*********************************************/
    $("legend.gift + p.giftCard").children("a").click(function () {
        //如果点击选中
        if ($(this).attr("class") != "cur") {

            if($(this).parent("p").attr("valid")=="0"){
                alert("全球购商品不支持使用礼品卡支付!");
                return false;
            }
            //todo刷新结算页
            $(this).attr("class", "cur");
            freshSettle();
        } else {
            //todo刷新结算页
            $(this).attr("class", "");
            $(this).children("i").text("");
            freshSettle();
        }
    })
    /*****************************礼品卡end******************************************/
    
    //订单提交
    $("#order_content .payment_btn").click(function () {
        submitOrder();
    });

    function submitOrder(){
        if(area_not_complate()){
            var param = orderSubmitParam();
            var validate = varlidateSubmitParam(param);
            if (validate) {
                //有国外的商品
                if($("#out_coupon").length>0){
                    //身份证校验
                    if(!valiCardId()){
                        return false
                    }
                }
                var url = getRootPath() + "/settlement/submit";
                $("#order_content .payment_btn").text("正在提交订单.......");
                $("#order_content .payment_btn").unbind("click");//防止重复提交
                $.post(url, param, function (data) {
                    submitResult(data);
                }, "json");

            }
        }
    }

    /**
     * 提交表单校验
     * @returns {{flag: boolean, msg: string}}
     */
    function varlidateSubmitParam(param) {

        if (!param.receivedId) {
            alert("请选择用户地址");
            return false;
        }
        // 开发票验证
        if (param.invoiceFlag == "1") {
            if (!param.invoiceTitle ) {
                alert("请输入发票抬头！");
                return false;
            }
            if (!param.invoiceContent ) {
                alert("请输入发票内容！");
                return false;
            }
            if(!param.invoiceType){
                alert("请输入发票类型!")
                return false;
            }
            if(!param.invoiceTel){
                alert("请输入发票手机号码！");
                return false;
            }
        }
        if (param.express == "") {
            alert("请选择配送方式");
            return false;
        }
        return true;
    }

    /**
     * 提交参数获取
     * @returns {{receivedId: (*|jQuery), invoiceFlag: (*|jQuery), invoiceType: (*|jQuery), invoiceTitle: (*|jQuery), invoiceContent: (*|jQuery), invoiceEmail: (*|jQuery), invoiceTel: (*|jQuery), domesticCouponFlag: *, domesticCoupon: *, abroadCoupon: (*|jQuery), abroadCouponflag: number, express: (*|jQuery), orderfrom: string, buyId: (*|jQuery), paytypeid: (*|jQuery), paytypechildid: (*|jQuery), ordertype: string, isUseGiftCardPay: string, orderSource: (*|jQuery), deliveryId: string, channelNo: string, channelId: string}}
     */
    function orderSubmitParam() {

        var receivedId = $("input[name='addressId']").val();
        var invoiceFlag = $("input[name='invoiceflag']").val();//是否开发票
        var invoiceType = $("input[name='invoicetype']").val();//发票类型
        var invoiceTitle = $("input[name='invoicetitle']").val();//发票名称
        var invoiceContent =$("input[name='invoicecontent']").val();//发票名称 $("#J_invoiceContent option:selected").val()?$("#J_invoiceContent option:selected").val():"";
        ;//发票内容
        var invoiceEmail = $("input[name='invoiceemail']").val();//发票邮箱
        var invoiceTel = $("input[name='invoicetel']").val();//发票手机号

        var domesticCouponFlag = $("input[name='innerUseCouponFlag']").val();
        var domesticCoupon = $("input[name='innerUseCouponNo']").val()?$("input[name='innerUseCouponNo']").val():"0";

        if(domesticCoupon=="0"){
            domesticCouponFlag="1";
        }
        var abroadCoupon = $("input[name='outerUseCouponNo']").val()?$("input[name='outerUseCouponNo']").val():"0";//国外使用优惠券类型	int	必须	无：传空；1：优惠券；2：优惠码
        var abroadCouponflag = "";
        if(abroadCoupon=="0"){
            abroadCouponflag = "1";//国外暂时只支持优惠券//国外优惠券或折扣码编号	String
        }else{
            abroadCouponflag = "1";
        }

        var express = $("input[name='deliveryFlag']").val();	//配送发送	String	必须	:1：工作日收货；2：工作日，节假日收货；3：双休，节假日收货
        var orderfrom = "19"; //订单来源	String	必须	9：ios；18：安卓；19：M站订单渠道
        var buyId = $("input[name='buyId']").val();//购物车商品id	String	必须	多个用“|‘分开
        var paytypeid = $(getPayDom()).attr("mainid");	//主支付方式编号	String	必须
        var paytypechildid = $(getPayDom()).attr("subid");
        ;	//子支付方式编号	String	必须
        var ordertype = "1";//订单类型	String	必须	尚品1
        var hasGift = $("p.giftCard a").hasClass("cur");
        var isUseGiftCardPay = hasGift ? "1" : "0";//	是否使用礼品卡 String	必须	0：为不使用；1：使用
        var orderSource = $("input[name='orderSource']").val();//从何处来到提交订单 String	必须1：从购物车2：立即购买
        var deliveryId = "2";//配送方式编号	String	必须	极速配送还是什么
        var channelNo = "";//第三方频道号	String	非必须
        var channelId = "";//三方频道id	String	非必须
        return {
            receivedId: receivedId, invoiceFlag: invoiceFlag, invoiceType: invoiceType,
            invoiceTitle: invoiceTitle, invoiceContent: invoiceContent, invoiceEmail: invoiceEmail,
            invoiceTel: invoiceTel, domesticCouponflag: domesticCouponFlag, domesticCoupon: domesticCoupon,
            abroadCoupon: abroadCoupon, abroadCouponflag: abroadCouponflag, express: express, orderfrom: orderfrom,
            buyId: buyId, paytypeid: paytypeid, paytypechildid: paytypechildid, ordertype: ordertype,
            isUseGiftCardPay: isUseGiftCardPay, orderSource: orderSource, deliveryId: deliveryId, channelNo: channelNo,
            channelId: channelId
        };
    }

    //获取选中支付方式的span
    function getPayDom() {
        return $("#order_content p.payment-method span a[class='cur']").parent();
    }

    //选择支付方式
    $("#order_content").delegate(".paymet_block fieldset p.payment-method span a","click",function(){
        var that = $(this);
        var obj = $(".paymet_block fieldset p.payment-method span a");
        obj.removeClass("cur");
        that.addClass("cur");
    });

    $("#order_content").delegate("p.other-payment","click",function(){
        $('.other-payment').hide();
        $('.other-payment-box').show();
    });

    /**
     * 身份证校验
     */
    function valiCardId(){

        // 如果收货地址中的身份证号码不存在则弹层提示
        var addressId = $("input[name='addressId']").val();
        var result;
        $.ajax({
            type : "get",
            url : getRootPath() + "/address/ajax/hasCardID?"+new Date(),
            data : {
                "addressId" : addressId
            },
            async : false,
            dataType : "json",
            success : function(data) {
                if (!data) {
                    // 收货地址不存在身份证号
                    $('#overlay').show();
                    $('#popup_box_id').show();
                    result = false;
                } else {
                    result = true;
                }
            }
        });
        return result;
    }

    // 身份证保存
    $("#popup_ok_id").click(function () {
        saveCardID();
    })

    // 身份证关闭按钮
    $("#popup_cancel_id,.title_closeBtn").click(function(e) {
        e.preventDefault();
        $('#overlay').hide();
        $('#popup_box_id').hide();
    });
    // 保存身份证号码
    function saveCardID() {
        var cardID = $("#J_identificationNum").val().toUpperCase();
        var addressId = $("input[name='addressId']").val();
        // 验证身份证号码
        if (cardID == "") {
            alert("请输入身份证号码！");
            return false;
        }
        if (!checkCard(cardID)) {
            alert("您输入的身份证号码无效！");
            return false;
        } else {
            // ajax请求修改省份证号码
            $.ajax({
                url : getRootPath() + '/address/ajax/addCardID',
                data : {
                    "addressId" : addressId,
                    "cardID" : cardID
                },
                type : "get",
                dataType : 'json',
                success : function(data) {
                    if (data != null) {
                        var code = data.code;
                        var msg = data.msg;
                        if (code == "1") {
                            alert(msg);
                        } else {
                            // 关闭弹层
                            $('#overlay').hide();
                            $('#popup_box_id').hide();
                            alert("添加身份证信息成功!");
                        }
                    }
                }
            });

        }
    }

    //调用结算返回逻辑处理
    function submitResult(data) {

        var code = data.code;
        if (data.code == "0") {
            var url = $(getPayDom()).attr("url") + "?orderId=" + data.orderInfo.orderid;
            var title = $(getPayDom()).children("a").children("em").text();
            var way = $(getPayDom()).attr("way");//0线上 1线下
            //有礼品卡支付
            if(data.orderInfo.giftcard=="1"){
                url = "/order/gifgcard?orderId=" + data.orderInfo.orderid;
                title = "礼品卡"
            }
            //优惠券,折扣码全额支付
            $("#order_content .payment_btn").val("正在跳转到" + title + "支付页面......");
            window.location.href = getRootPath() + url;

        } else if (data.code == "3005") {//库存不足
            alert("您的订单中有库存不足的商品");
            $("#order_content .payment_btn").text("提交订单");
            $("#order_content .payment_btn").bind("click",submitOrder);
            return;
        } else if (data.code == "3003") {//地址不全
            area_not_complate();
            $("#order_content .payment_btn").bind("click",submitOrder);
            return;
        } else {
            if (data.msg == "库存不足") {
                alert("您的订单中有库存不足的商品");
            } else {
                alert(data.msg);
            }
            $("#order_content .payment_btn").bind("click",submitOrder);
            $("#order_content .payment_btn").text("提交订单");
            return;
        }

    }

    /*******************************支付_begin*******************************************/

    /*******************************支付_end**********************************************/

    //地址提交订单返回数据地址不全
    function area_not_complate(){
        var forthId = $("#forth_id").val();
        if(forthId == '' || forthId == '0') {
            $("#select-area-overlay, #select_street_layer").show();
            var town = $("#third_id").val();
            $.post(getRootPath() + "/address/town", {areaId: town}, function (data) {
                $("#orgin_area_street").empty();
                $.each(data, function (index, item) {
                    $("#orgin_area_street").append("<dd><a href='javascript:;' id=" + item.id + ">" + item.name + "</a></dd>");
                });
            }, "json");
            return false;
        }else{
            return true;
        }
    }

    $("#select-area-overlay, .close_btn").click(function () {
        $("#select-area-overlay, .select-layer").hide();
    });

    //选择四级地址
    $("#select_street_layer").delegate("a", "click", function () {
        $(this).addClass("cur");
        var obj = $("#select_street_layer dd  a");
        obj.removeClass("cur");
        var townId = $(this).attr("id");
        var addrId = $("input[name='addressId']").val();
        $("#forth_id").val(townId);
        setTimeout(function () {
            $("#select-area-overlay, .select-layer").hide();
            //更新收货地址
            var path = getRootPath();
            $.get(path + "/address/ajax/update?addressId=" + addrId, function (data) {
                console.log("update address:" + data);
                $.post(path + "/address/ajax/edit", {
                    id: data.id,
                    province: data.province,
                    city: data.city,
                    area: data.area,
                    town: townId,
                    name: data.name,
                    addr: data.addr,
                    postcode: data.postcode,
                    tel: data.tel
                }, function (data) {
                    if (data.code == "0") {
                        $.each(data.content, function (index, item) {
                            if (addrId == item.id) {
                                var address = item.provname + item.cityname + item.areaname + item.townname + item.addr;
                                if($(".first_leg").next("p").children("a").children("span").length>1){
                                    $(".first_leg").next("p").children("a").children("span").eq(1).text(address);
                                }else{
                                    $(".first_leg").next("p").children("a").children("span").eq(0).text(address);
                                }
                                return;
                            }
                        });
                    } else {
                        alert("四级地址更新失败");
                        return;
                    }
                }, "json");
            }, "json");
        }, 300);
        return false;
    });
});
