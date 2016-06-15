<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cdn" uri="http://m.shangpin.com/cdn" %>

<%String path = request.getContextPath();%>
<!doctype html>
<html lang="zh-CN">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>尚品奥莱-触屏版</title>
<link href="${cdn:css(pageContext.request)}/css/base.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/css/page/order.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/css/page/account.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="${cdn:pic(pageContext.request)}/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="${cdn:pic(pageContext.request)}/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="${cdn:pic(pageContext.request)}/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="${cdn:pic(pageContext.request)}/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="${cdn:pic(pageContext.request)}/images/logo/loading.png">

<script src="${cdn:js(pageContext.request)}/js/core.js" type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript" charset="utf-8">
    var ver = "?20121023001";
		loader = SP.core
			.install("${cdn:js(pageContext.request)}/js/zepto.min.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/lazyload.js" + ver)
		    .excute()
		    .using("${cdn:js(pageContext.request)}/js/config.sim.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/comm.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/order.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/account.js" + ver)
			.excute(function(){
				isHome(false);
				window.onhashchange = function(){
					var url = window.location.hash;
					var reg = /#app/ig;
					if(!reg.test(url)){
						hideInvoice();
						hideAddress();
						hideCoupon();
						hideCouponcode();
					}
				};
				<s:if test="platform=='android'||ch=='100001'">
					JSCallback.onPageLoaded();
				</s:if>
			});

	    $(document).ready(function(){
	    	//用户操作发票后，回退，页面刷新
	    	if($("#v").val() >0){
	    		location.reload();
	    	}
	    	
	    	
       $("#payVal").val($($(".paytag").get(0)).attr("data-title"));
       $("#dateVal").val($($(".deliverytag").get(0)).attr("data-title"));

       // 初始化地址
       var tag = false;
       var seladd = null;
       $(".li_addrList").each(function(n,v){
           if(1==$(v).attr("isd")){
        	   tag = true;
        	   seladd = $(v).get(0);
        	   setAddress(seladd);
           }
       });

       if(!tag){
    	   seladd = $(".li_addrList").get(0);
    	   setAddress(seladd);
       }

       cascadeStage($("#J_AddrProvince"),$("#J_AddrCity"),$("#J_AddrArea"),$("#J_AddrTown"));
       cascadeStage($("#INV_AddrProvince"),$("#INV_AddrCity"),$("#INV_AddrArea"),$("#INV_AddrTown"));
       total();
		var gidArr = document.getElementsByName("shopid");
       
       var gidtemp=gidArr[0].value;
  	 	for(var i = 1; i < gidArr.length; i++) {
   		if(gidArr.length==1){
   			gidtemp=gidtemp;
   		}else{
   			gidtemp=gidtemp+"|"+gidArr[i].value;
   		
   		}
   		
   	
   		
   	
   	}
   	$("#buysids").val(gidtemp);
       
   	   // 判断商品、收货人地址是否支持货到付款，不支持，则隐藏。
   	   if(!${orderVO.codincart==1} || $(seladd).attr("cod")==0){
   		 hidehdfk();
   	   }
   	var tmp = "";
   	$(".errorsku").each(function(n,v){
   		if("" != $(v).val()){
   			if("" == tmp){
   				tmp = tmp + $(v).val();
   			}else{
   			    tmp = tmp + "|" + $(v).val();
   			}
   		}
   	});
   	$("#errorskus").val(tmp);
    });

    // 隐藏货到付款
    function hidehdfk(){
      $("#al2").hide();
 	  $(".paytag").removeClass("cur");
 	  $("#al20").addClass("cur");
 	  $("#payVal").val("20");
 	  $("#paytypechildid").val("37");
    }

	//购物袋中商品合计
	function total(){
	    var total = 0;
	    var carriage=$("#_carriage").val();
	   
	    var amount=$("#amount").val();
	    if(carriage*1>0){
	    	$("#s_carriage").show();
	    	$("#carriage").html("&yen;"+carriage);
		}
	   var paymentamount=carriage*1+amount*1;
		$("#em").html("&yen;"+paymentamount);
	}

    // 设置收货人地址
    function setAddress(v){
		if(!${orderVO.codincart==1} || $(v).attr("cod")==0){
			hidehdfk();
		}else{
			$("#al2").show();
			//$("#alnote").html("");
		}

        $("#caname").html($(v).attr("uname"));
        $("#caaddress").html($(v).attr("addr"));
        $("#catel").html($(v).attr("tel"));
        $("#addrid").val($(v).attr("id"));
        if($("#addrVal").val()=="0")
      	  $("#invoiceaddrid").val($(v).attr("id"));
    }

    // 显示收货人地址区域
    function showAddress(){
        // 给选中的地址添加class
        $(".li_addrList").removeClass("curr");
    	var selectAddrid = $("#addrid").val()
    	eval('$("#'+selectAddrid+'").addClass("curr")');
    	
    	$("#J_AddrName").val("");
		$("#J_AddrProvince").val("");
		$("#J_AddrCity").val("");
		$("#J_AddrArea").val("");
		$("#J_AddrMobile").val("");
		$("#J_AddrCode").val("");
		$("#J_AddrText").val("");
		$("#J_AddrDefault").attr("checked",false);

        $(".address_con").show();
        $(".showaddr").show();
        $(".alOrder_invoice").hide();
        $(".alOrder_submitBtn").hide();
        $(".alOrder_list").hide();
        $(".alOrder_select").hide();
        $(".alOrder_title").hide();
        $(".alOrder_addr").hide();
        $(".addr_list").show();
        $(".addr_errorMsg").hide().html("");
    }

    // 隐藏收货人地址区域
    function hideAddress(){
        
        $(".address_con").hide();
        $(".alOrder_invoice").show();
        $(".alOrder_submitBtn").show();
        $(".alOrder_list").show();
        $(".alOrder_select").show();
        $(".alOrder_title").show();
        $(".alOrder_addr").show();

 	    var scrollTop = $(window)[0].scrollTo(0,0);
        $("html, body").animate({ scrollTop: 0 }, 120);
        return false;
    }

    // 新增收货人地址
    function addAddress(){
    	var validate = new Account().validate();
    	if(undefined==validate){
    		$.ajax({
    			  url:'accountaction!ajaxsaveaddress',
    			  data:{"consigneeAddressVO.name":$("#J_AddrName").val(),
    			  "consigneeAddressVO.province":$("#J_AddrProvince").val(),
    			  "consigneeAddressVO.city":$("#J_AddrCity").val(),
    			  "consigneeAddressVO.area":$("#J_AddrArea").val(),
    			  "consigneeAddressVO.town":$("#J_AddrTown").val(),
    			  "consigneeAddressVO.tel":$("#J_AddrMobile").val(),
    			  "consigneeAddressVO.postcode":$("#J_AddrCode").val(),
    			  "consigneeAddressVO.addr":$("#J_AddrText").val(),
    			  "consigneeAddressVO.isd":$("#J_AddrDefault").is(":checked")?"on":""
    			  },
    			  dataType:'json',
    			  timeout: 30000,
    	            error: function (xmlHttpRequest, error) {
    	            	if(error == "parsererror"){
    	            		alert("用户帐号异常，请重新登录");
    	            		window.location.href="<%=path%>/mobileaolai/accountaction!loginui";
    	            	}else{
    	                    alert("您的网络异常");
    	            	}
    	                return;
    	            },
    			  success:function(data){
    			    if(data.code==0){
    			      var obj = data.content.list[0];
    			      
    			      var append = '<ul class="address_list">'
    			        + '<li class="li_addrList ' + (obj.isd=='1'?'curr"':'"') + 'id="'+obj.id+'" uname="'+obj.name+'" addr="'+obj.provname+obj.cityname+obj.areaname+obj.townname+obj.addr+'" tel="'+obj.tel+'" >'
    			        + '<section class="alOrder_addr addr_list" style="background:none">'
    			        + '<a href="javascript:void(0);" id="'+obj.id+'" uname="'+obj.name+'" addr="'+obj.provname+obj.cityname+obj.areaname+obj.townname+obj.addr+'" tel="'+obj.tel+'" onclick="setAddress(this);window.history.go(-1);">'
    			        + '<dl>'
    			        + '<dt>收货人：</dt><dd>'+obj.name+'</dd>'
    			        + '</dl>'
    			        + '<dl>'
    			        + '<dt>地　址：</dt><dd>'+obj.provname+obj.cityname+obj.areaname+obj.townname+obj.addr+'</dd>'
    			        + '</dl>'
    			        + '<dl>'
    			        + '<dt>手机号：</dt><dd>'+obj.tel+'</dd>'
    			        + '</dl>'
    			        + '</a>'
    			        + '</section>'
    			        + '</li>'
    			        + '</ul>';

    			      $("#addrList").append(append);
    			      
    			      $("#caname").html(obj.name);
    		          $("#caaddress").html(obj.provname+obj.cityname+obj.areaname+obj.townname+obj.addr);
    		          $("#catel").html(obj.tel);
    		          $("#addrid").val(obj.id);
    		          if($("#addrVal").val()=="0")
    		        	  $("#invoiceaddrid").val(obj.id);
    		          // 地址只能添加10个

    		          if($(".address_list").size()>=10){
    		        	  $(".add_new").hide();
    		        	  $(".add_cont").hide();
    		          }
    		          $(".noneAddr").hide();
    		          window.history.go(-1);
    			    }else{
    			      $(".addr_errorMsg").html(data.msg).show();
    			    }
    			  }
            });
    	}else{
            return validate;
    	}
	}

    // 显示发票区域
	function showInvoice(){
		$(".addr_errorMsg").hide().html("");
	    // 判断是否开发票，并加入css
	    if($("#_optionVal").val()==0){
    	  $("#invoice_close_a").addClass("cur").siblings("a").removeClass("cur");
    	  $(".invoice_content").hide();
	    }else{
	      $("#invoice_open_a").addClass("cur").siblings("a").removeClass("cur");
	      $(".invoice_content").show();
	    }

    	// 判断个人、单位并加入css
	    if($("#titleVal").val()==0){
    	  $("#invoice_org_a").addClass("cur").siblings("a").removeClass("cur");
    	  //$("#invoice_org").val("");
	    } else{
	      $("#invoice_person_a").addClass("cur").siblings("a").removeClass("cur");
	      //$("#_invoice_person")
	    }

    	// 地址、品类css
	    var tmp = $("#_classVal").val();
		eval('$("#'+tmp+'").addClass("cur").siblings("a").removeClass("cur")');
		$(".invaddr_list_li").removeClass("curr");
		var tmpid = $("#_invoiceaddrid").val();
		eval('$("#'+tmpid+'").addClass("curr")');
		
		$("#invoice").show();
		if($(".invaddr_list").size()>=10 ){
			$("#other_addr_div").hide();
		}
		
        $(".alOrder_invoice").hide();
        $(".alOrder_submitBtn").hide();
        $(".alOrder_list").hide();
        $(".alOrder_select").hide();
        $(".alOrder_title").hide();
        $(".alOrder_addr").hide();
	}

    // 隐藏发票区域
	function hideInvoice(){
	    $("#invoice").hide();
        $(".alOrder_invoice").show();
        $(".alOrder_submitBtn").show();
        $(".alOrder_list").show();
        $(".alOrder_select").show();
        $(".alOrder_title").show();
        $(".alOrder_addr").show();
	}

    // 选择发票地址
    function setInvAddress(id){
    	$("#sle").val("ture");
    	$("#invoiceaddrid").val(id);
    	$(".invaddr_list_li").removeClass("curr");
    	eval('$("#'+id+'").addClass("curr")');
    	$(".big").removeClass("cur");
    }

    // 改变选择发票地址状态
    function changeState(){
    	$("#sle").val("false");
    	$("#invoiceaddrid").val(0);
    	$(".invaddr_list_li").removeClass("curr");
    }

    // 隐藏发票区域，还原上次操作数据
	function hideRevertInvoice(){
		$("#optionVal").val($("#_optionVal").val());
		$("#titleVal").val($("#_titleVal").val());
		
		if($("#_titleVal").val() ==1){
			$("#invoicetitle").val($("#_invoice_person").val());
	        $("#invoice_person").val($("#_invoice_person").val());
		}else{
			$("#invoicetitle").val($("#_invoice_org").val());
   		    $("#invoice_org").val($("#_invoice_org").val());
   		    if($("#_invoice_org").val()!=""){
   		        $("#invoice_org").show();
		    }
		}
		$("#classVal").val($("#_classVal").val());
		
		$("#invoiceaddrid").val($("_#invoiceaddrid").val());

		window.history.go(-1);
		//hideInvoice();
	}

    // 新增发票地址
    function addInvoice(){
    	$("#v").val(1);
    	if(0 == $("#optionVal").val()){
    	  $("#_optionVal").val(0);
    	  $("#isInvoice").html("不开发票");
    	  hideInvoice();
    	  window.history.go(-1);
    	  return;
    	}else{
    		$("#_optionVal").val(1);
    	}

   		if(1 == $("#titleVal").val()){
   			$("#invoicetitle").val($("#invoice_person").val());
   			$("#_invoice_person").val($("#invoice_person").val());
   			$("#_titleVal").val(1);
   		}else{
			$("#_titleVal").val(0);
   			$("#invoicetitle").val($("#invoice_org").val());
   			$("#_invoice_org").val($("#invoice_org").val());
   		}

   		$("#_classVal").val($("#classVal").val());
   		
		$("#_invoiceaddrid").val($("#invoiceaddrid").val());

   		if($("#INV_Addr_size").val() < 10){
   	  		// 1表示点击添加新地址并且没有选择地址
   	   		if(1 == $("#addrVal").val() && $("#sle").val() != "ture"){
   	   			var validate = new Account().ajaxValidate();
   	   	    	if(undefined==validate){
   	   	    	  if(confirm("确认保存该地址吗？")){
   	   	    		$.ajax({
   	   	    			  url:'accountaction!ajaxsaveinvoiceaddress',
   	   	    			  data:{"consigneeAddressVO.name":$("#INV_AddrName").val(),
   	   	    			  "consigneeAddressVO.province":$("#INV_AddrProvince").val(),
   	   	    			  "consigneeAddressVO.city":$("#INV_AddrCity").val(),
   	   	    			  "consigneeAddressVO.area":$("#INV_AddrArea").val(),
   	   	    			  "consigneeAddressVO.tel":$("#INV_AddrMobile").val(),
   	   	    			  "consigneeAddressVO.postcode":$("#INV_AddrCode").val(),
   	   	    			  "consigneeAddressVO.addr":$("#INV_AddrText").val(),
   	   	    			  },
   	   	    			  dataType:'json',
		   	   	          timeout: 30000,
		   	              error: function (xmlHttpRequest, error) {
		   	            	if(error == "parsererror"){
		   	            		alert("用户帐号异常，请重新登录");
		   	            		window.location.href="<%=path%>/mobileaolai/accountaction!loginui";
		   	            	}else{
		   	                    alert("您的网络异常");
		   	            	}
		   	                return;
		   	              },
   	   	    			  success:function(data){
   	   	    			    if(data.code==0){
   	   	    			      var obj = data.content.list[0];
   	   	    			      
   	   	    			      var append = '<ul class="invaddr_list">'
   	   	    			        + '<li class="invaddr_list_li" id="'+obj.id+'" >'
   	   	    			        + '<section class="alOrder_invaddr" style="background:none">'
   	   	    			        + '<a href="javascript:void(0);" onclick="setInvAddress('+obj.id+');">'
   	   	    			        + '<dl>'
   	   	    			        + '<dt>收货人：</dt><dd>'+obj.name+'</dd>'
   	   	    			        + '</dl>'
   	   	    			        + '<dl>'
   	   	    			        + '<dt>地　址：</dt><dd>'+obj.addr+'</dd>'
   	   	    			        + '</dl>'
   	   	    			        + '<dl>'
   	   	    			        + '<dt>手机号：</dt><dd>'+obj.tel+'</dd>'
   	   	    			        + '</dl>'
   	   	    			        + '</a>'
   	   	    			        + '</section>'
   	   	    			        + '</li>'
   	   	    			        + '</ul>';
   	   	    			      $("#invaddrList").append(append);
   	   	    			      $("#_invoiceaddrid").val(obj.id);
   	   	    			    }else{
   	   	    			      $(".addr_errorMsg").html(data.msg).show();
   	   	    			      return;
   	   	    			    }
   	   	    			  }
   	   	            });
   	   	    	  }
   	   	    	}else{
   	   	            return validate;
   	   	    	}
   	   		}
   		}
   	 //hideInvoice();
    $("#isInvoice").html("开发票");
   	window.history.go(-1);
	}

    // 展示优惠券列表
    function showCoupon(){
    	 
    	$(".alOrder_couponList").show();
    	
    	$(".alOrder_invoice").hide();
        $(".alOrder_submitBtn").hide();
        $(".alOrder_list").hide();
        $(".alOrder_select").hide();
        $(".alOrder_title").hide();
        $(".alOrder_addr").hide();
    }
    
    // 隐藏优惠券列表区域
	function hideCoupon(){
	    $(".alOrder_couponList").hide();
		$(".alOrder_invoice").show();
	    $(".alOrder_submitBtn").show();
	    $(".alOrder_list").show();
	    $(".alOrder_select").show();
	    $(".alOrder_title").show();
	}
	
    // 选择优惠券
   // 选择优惠券
    function selectCoupon(v){
    	hideCoupon();
        $(".alOrder_addr").show();
        // 设置事件按钮显示文案为取消，将其他为使用
        // 设置事件按钮为取消状态，将其他的按钮赋值为0使用状态
        var showRule="未使用";//优惠券区域，右侧显示文案
        var buttonName="使用";//优惠券列表，按钮显示文案
        var isUse=0;//优惠券列表，按钮状态，0为使用，1为取消；
        var couponNo=0;
        var amount=$("#amount").val()*1;
        if($(v).attr("isUse")==0  ){//点击使用
        	$(".select_coupon").each(function(n,v){
					$(v).attr("isUse",isUse);
					$(v).html(buttonName);
			});
			buttonName="取消";
			isUse=1;
	        couponNo=$(v).attr("couponNo");
	        $("#coupon").val(couponNo);
        	$.ajax({
    			url:'orderaction!updateConfirmOrderInfo',
  			    data:{
  			    	"orderVO.coupon":$("#coupon").val(),
  			    	"orderVO.buysids":$("#buysids").val(),
  			    	"orderVO.couponflag":1,
  			    	"orderVO.addrid":$("#addrid").val()
  			    },
    			dataType:'json',
    			timeout: 30000,
    			async:false,
    	        error: function (xmlHttpRequest, error) {
    	        if(error == "parsererror"){
    	          	alert("用户帐号异常，请重新登录");
    	          	window.location.href="<%=path%>/mobileaolai/accountaction!loginui";
    	          
    	        }else{
    	            alert("您的网络异常");
    	        }
    	         return;
    	      },
    			success:function(data){
    				if("success" == data.msgcode){
    						 //先判断有没有用礼品卡，在判断有没有用优惠券，计算出金额
					        if(amount<0)
					          amount=0;
					        $("#isUseCouponcode").val("0");
					        $("#isUseCoupon").val("1");
							$("#isCouponcode").text("未使用"); 
							$("#couponcode").val("");
							$("#select_couponcode").val("使用");
							var useflag=0;
					    	$("#select_couponcode").attr("useflag",useflag);
							$("#couponcode").removeAttr("readonly");
							var discountamount=data.discountamount;
							 // 显示所选优惠券规则
					        showRule="已优惠:&yen;"+ discountamount*1;
							var carriage=data.carriage;
						    if(carriage*1>0){
						    	$("#s_carriage").show();
						    	$("#carriage").html("&yen;"+carriage);
							}else{
								$("#s_carriage").hide();
							}
						  if(discountamount*1>0){
							  $("#discount").show();
							  $("#discountamount").html("&yen;"+discountamount);
						  }else{
							  $("#discount").hide();
						  }
    						$("#em").html("&yen;"+data.payamount);
    						window.history.go(-1);
    						return;
    					 }else{
    						alert(data.msg);
    					
    						$("#isCouponcode").text("未使用"); 
    						$("#select_couponcode").val("使用");
    					}
    				}
    			});
        }else{
        	$("#isUseCoupon").val("0");
        	$("#discount").hide();
		    var carriage=$("#_carriage").val();
		    var amount=$("#amount").val();
		    if(carriage*1>0){
		    	$("#s_carriage").show();
		    	$("#carriage").html("&yen;"+carriage);
			}
		   	var paymentamount=carriage*1+amount*1;
			$("#em").html("&yen;"+paymentamount);
        }
        $(v).html(buttonName);
    	$(v).attr("isUse",isUse);
    	
        $("#iscoupon").html(showRule);
        $("#couponAmount").val($(v).attr("couponAmount"));
        $("#coupon").val(couponNo);
    }

    // 保存订单
    function save(){
    	if($("#isSubmit").val()=="1"){
    		alert("订单已提交,请勿重复提交");
    		window.location.href="<%=path%>/orderaction!orderlist";
    		return;
    	}
        if($("#addrid").val()==""){
        	alert("填写收货人地址");
        	return;
        }
        if($("#_optionVal").val() != "0" && $("#_invoiceaddrid").val()==""){
        	alert("请选择发票地址");
        	return;
        }
        if($("#payVal").val()=="20"){
   	        $("#paytypechildid").val("37");
        }
        if($("#payVal").val()=="2"){
   	        $("#paytypechildid").val("41");
        }
        if($("#_optionVal").val() == "1"){//开发票填写个人、单位名称
          if($("#_titleVal").val() == "1"){
        	$("#_invoicetitle").val($("#_invoice_person").val());
          } else if($("#_titleVal").val() == "0" && $("#_invoice_org").val() != ""){
        	$("#_invoicetitle").val($("#_invoice_org").val());
          }else if($("#_titleVal").val() == "0" && $("#_invoice_org").val() == ""){
        	$("#_invoicetitle").val("");
        	$("#_titleVal").val(1);
          }
        }
         // alert($("#_optionVal").val());
		  //alert($("#_titleVal").val());
		  //alert($("#_invoicetitle").val());
		 // alert($("#_classVal").val());
		 // alert($("#_invoiceaddrid").val());
		var couponflag;
		if( $("#isUseCouponcode").val()==1){
			couponflag=2;
		}else if($("#isUseCouponcode").val()==0&&$("#coupon").val()!=0){
			couponflag=1;
		}else{
			couponflag="";
		}

		$("#submit").html("正在提交订单......");
    	$.ajax({
 			  url:'orderaction!saveorder',
 			  data:{"orderVO.addrid":$("#addrid").val(),
 			  "orderVO.express":$("#dateVal").val(),
 		//	  "orderVO.paytypeid":$("#payVal").val(),
 			//  "orderVO.paytypechildid":$("#paytypechildid").val(),

 			  "orderVO.invoiceflag":$("#_optionVal").val(),
 			  "orderVO.invoicetype":$("#_titleVal").val(),
 			  "orderVO.invoicetitle":$("#_invoicetitle").val(),
 			  "orderVO.invoicecontent":$("#_classVal").val(),
 			  "orderVO.invoiceaddrid":$("#_invoiceaddrid").val(),

 			  "orderVO.orderfrom":$("#orderfrom").val(),
 			  "orderVO.ordertype":$("#ordertype").val(),
 			  "orderVO.coupon":$("#coupon").val(),
 			  "orderVO.errorskus":$("#errorskus").val(),
 			 	"orderVO.buysids":$("#buysids").val(),
 			 	"orderVO.couponflag":couponflag
 			  },
 			  dataType:'json',
 			  timeout: 30000,
              error: function (xmlHttpRequest, error) {
              	if(error == "parsererror"){
            		alert("用户帐号异常，请重新登录");
            		window.location.href="<%=path%>/mobileaolai/accountaction!loginui";
            		
            	}else{
                    alert("您的网络异常");
            	}
            	$("#isSubmit").val("0");
            
            	$(".alOrder_buyBtn alOrder_submitBtn").html("提交订单");
                return;
              },
 			  success:function(data){
 				  if("success" == data.msgcode){
 					 $("#isSubmit").val("1");
 					 location.href = "<%=path%>/orderaction!success?orderid="+data.orderid+"&amount="+data.amount+"";
 					 return;  
 				  }
 				 if("notifysuccess" == data.msgcode){
 					 location.href = "<%=path%>/orderaction!success?orderid="+data.orderid+"&amount="+data.amount+"&payresult=payresult";
 					 return;  
 				  }
 				  alert(data.msg);
 				  if("cart" == data.msgcode){
 				    location.href = "<%=path%>/allcartaction!listCart";
 				  }else{
 					 //location.href = "<%=path%>/aolaiindex!index";
 				  }
 			  }
         });
    }
    
    function showCouponcode(){
		$("._couponcode").show();
		if( $("#select_couponcode").attr("useflag")==1){
			 $("#couponcode").attr("readonly","readonly");
			$("#select_couponcode").val("取消使用");
		}else{
			$("#couponcode").val("");
			 $("#couponcode").removeAttr("readonly");
			$("#select_couponcode").val("使用");
		}
		$(".alOrder_submitBtn").hide();
	    $(".alOrder_list").hide();
        $(".alOrder_select").hide();
        $(".alOrder_title").hide();
        $(".alOrder_addr").hide();
	}

    // 隐藏发票区域
	function hideCouponcode(){
	    $("._couponcode").hide();
        $(".alOrder_invoice").show();
        $(".alOrder_submitBtn").show();
        $(".alOrder_list").show();
        $(".alOrder_select").show();
        $(".alOrder_title").show();
        $(".alOrder_addr").show();
	}
	 function getCouponCodeInfo(){
	    	var amount=$("#amount").val()*1;
	    	var couponNo=0;
	    	if( $("#select_couponcode").attr("useflag")==1){
				$("#coupon").val(couponNo);
	    		$("#isUseCouponcode").val("0");
	    		$("#isCouponcode").text("未使用"); 
	    		var useflag=0;
	    		$("#select_couponcode").attr("useflag",useflag);
	    		$("#couponcode").removeAttr("readonly");
	    		$("#select_couponcode").val("使用");
	    		
	    		
	    		$("#discount").hide();
				var carriage=$("#_carriage").val();
			    var amount=$("#amount").val();
			    if(carriage*1>0){
			    	$("#s_carriage").show();
			    	$("#carriage").html("&yen;"+carriage);
				}
			   	var paymentamount=carriage*1+amount*1;
				$("#em").html("&yen;"+paymentamount);
	    		window.history.go(-1);
	    	}else{
	    		var validate = new Account().couponCodeValidate();
	        	if(undefined==validate){
	    		  $.ajax({
	    			  url:'orderaction!updateConfirmOrderInfo',
	  			    data:{
	  			    	"orderVO.coupon":$("#couponcode").val(),
	  			    	"orderVO.buysids":$("#buysids").val(),
	  			    	"orderVO.couponflag":2,
	  			    	"orderVO.addrid":$("#addrid").val()
	  			    },
	    			    dataType:'json',
	    			    timeout: 30000,
	    			    async:false,
	    	          error: function (xmlHttpRequest, error) {
	    	          	if(error == "parsererror"){
	    	          		alert("用户帐号异常，请重新登录");
	    	          		window.location.href="<%=path%>/mobileaolai/accountaction!loginui";
	    	          	}else{
	    	                  alert("您的网络异常");
	    	          	}
	    	              return;
	    	          },
	    			    success:function(data){
	    						  if("success" == data.msgcode){
	    							  $("#isCouponcode").html("已优惠:&yen"+data.discountamount); 
	    							  var discountamount=data.discountamount;
	    							  var carriage=data.carriage;
	    							  $("#couponcode").val(data.couponcode);
	    							  $("#isUseCouponcode").val("1");
	    							  $("#isUseCoupon").val("0");
	    							  $("#select_couponcode").attr("useflag","1");
	    							  $("#iscoupon").text("未使用"); 
	    							  $(".select_coupon").each(function(){
	    								  var isUse=0;
	    								  $(this).attr("isUse",isUse);
	    								  $(this).html("使用");
	    					           	});
	    							  couponNo=data.couponcode;
	    							  $("#coupon").val(couponNo);
	    							  
	    							    if(carriage*1>0){
	    							    	
	    							    	$("#s_carriage").show();
	    							    	$("#carriage").html("&yen;"+carriage);
	    								}else{
	    									$("#s_carriage").hide();
	    								}
	    							  if(discountamount*1>0){
	    								  $("#discount").show();
	    								  $("#discountamount").html("&yen;"+discountamount);
	    							  }else{
	    								  $("#discount").hide();
	    							  }
	    							 
	    							  $("#em").html("&yen;"+data.payamount);
	    							  window.history.go(-1);
	    							  return;
	    						  }else{
	    							  alert(data.msg);
	    							  $("#isCouponcode").text("未使用"); 
	    							  $("#select_couponcode").val("使用");
	    						  }
	    					  }
	    			  });
	    		  
	        	}else{
	        		alert("请输入优惠码");
	        	}
	        	
	    	}
	    	
	} 
	 
	
	 
	 
	 
	 
</script>

</head>

<body>

<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/">首页</a></li>
    <li><a href="<%=path%>/allcartaction!listCart">购物袋</a></li>
    <li>订单</li>
  </ul>
</nav>

<div class="alContent">

<!-- 收货人地址编辑区域 开始 -->
  <div class="address_con" style="display:none;">
    <div id="addrList">
      <s:iterator value="consigneeAddressList" id="consigneeAddress" status="st">
        <ul class="address_list">
          <li id="${consigneeAddress.id }" cod="${consigneeAddress.cod }" isd="${consigneeAddress.isd }" class="li_addrList" uname="${consigneeAddress.name }" addr="${consigneeAddress.provname}${consigneeAddress.cityname }${consigneeAddress.areaname}${consigneeAddress.townname}${consigneeAddress.addr }" tel="${consigneeAddress.tel }">
              <section class="alOrder_addr addr_list" style="background:none">
                <a href="javascript:void(0);" id="${consigneeAddress.id }" cod="${consigneeAddress.cod }" uname="${consigneeAddress.name }" addr="${consigneeAddress.provname}${consigneeAddress.cityname }${consigneeAddress.areaname}${consigneeAddress.townname}${consigneeAddress.addr }" tel="${consigneeAddress.tel }" onclick="setAddress(this);window.history.go(-1);">
                  <dl>
                    <dt>收货人：</dt><dd>${consigneeAddress.name }</dd>
                  </dl>
                  <dl>
                    <dt>地　址：</dt><dd>${consigneeAddress.provname}${consigneeAddress.cityname }${consigneeAddress.areaname}${consigneeAddress.townname}${consigneeAddress.addr }</dd>
                  </dl>
                  <dl>
                    <dt>手机号：</dt><dd>${consigneeAddress.tel }</dd>
                  </dl>
                </a>
              </section>
          </li>
        </ul>
      </s:iterator>
    </div>
      <s:if test='consigneeAddressList == null || consigneeAddressList.size < 10'>
      <h4 class="add_new">新增收货地址</h4>
      <ul class="add_cont">
          <li id="msg" style="padding:0; color:#b51111; text-align:center; line-height:25px;">${msg}</li>
          <li>
              <span>收货人：</span>
              <input type="text" class="ac_txt" id="J_AddrName" maxlength="20">
          </li>
          <li>
              <span>省份：</span>
              <select id="J_AddrProvince">
                  <option value="-1">请选择省份</option>
                  <s:iterator value="provinceList" id="provinceVO">
                    <option value="${provinceVO.id }" >${provinceVO.name }</option>
                  </s:iterator>
              </select>
          </li>
          <li>
              <span>城市：</span>
              <select id="J_AddrCity">
                  <option value="-1">请选择城市</option>
                  <s:iterator value="cityList" id="cityVO">
                    <option value="${cityVO.id }" >${cityVO.name }</option>
                  </s:iterator>
              </select>
          </li>
          <li>
               <span>地区：</span>
               <select id="J_AddrArea">
                  <option value="-1">请选择地区</option>
				  <s:iterator value="areaList" id="areaVO">
                    <option value="${areaVO.id }" >${areaVO.name }</option>
                  </s:iterator>
              </select>
          </li>
            <li>
               <span>街道：</span>
               <select id="J_AddrTown">
                  <option value="-1">请选择地区</option>
				  <s:iterator value="townList" id="townVO">
                    <option value="${townVO.id }" >${townVO.name }</option>
                  </s:iterator>
              </select>
          </li>
          <li>
              <span>手机号码：</span>
              <input type="tel" class="ac_txt" id="J_AddrMobile" maxlength="11">
          </li>
          <li>
              <span>邮政编码：</span>
              <input type="tel" class="ac_txt" id="J_AddrCode" maxlength="6">
          </li>
          <li>
              <span>详细地址：</span>
              <input type="text" class="ac_txt long_txt" id="J_AddrText">
          </li>
            
          <li>
              <span>设为默认：</span>
              <input type="checkbox" id="J_AddrDefault" >
          </li>

          <li class="addr_errorMsg"></li>
          <li>
              <p class="txt_center">
                <input type="button" class="alList_submitBtn" value="确认" onclick="addAddress();$('#msg').hide();"/>
                <!--a href="#" class="alList_submitBtn">确认</a-->
                <a href="javascript:window.history.go(-1);" class="alList_moreBtn">取消</a>
              </p>
          </li>
      </ul>
      </s:if>
  </div>
  <p style="border:none; color:#b51111; padding:0; text-align:center;">${msg }</p>
<!-- 收货人地址编辑区域 结束 -->
  
<!-- 收货人地址显示区域 开始 -->
  <section class="alOrder_addr"  id="alOrder_addr">
    <a href="#app" onclick="javascript:showAddress();">
      <dl>
        <dt>收货人：</dt><dd id="caname"></dd>
      </dl>
      <dl>
        <dt>地　址：</dt><dd id="caaddress"></dd>
      </dl>
      <dl>
        <dt>手机号：</dt><dd id="catel"></dd>
      </dl>
      <s:if test="null == consigneeAddressList || 0>=consigneeAddressList.size">
      <div class="noneAddr">点击添加收货人地址</div>
      </s:if>
    </a>
  </section>
<!-- 收货人地址显示区域 结束 -->

  <h2 class="alOrder_title">配送方式</h2>
  <ul class="alOrder_select" id="alOrder_date">
  <s:iterator value="deliveryList" id="deliveryVO" status="st">
    <s:if test="#deliveryVO.enable==1">
      <li class="deliverytag <s:if test='#st.index==0'>cur</s:if>" data-title="${deliveryVO.id }"><a href="#">${deliveryVO.name }<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></a></li>
    </s:if>
  </s:iterator>
  </ul>

<%--  <h2 class="alOrder_title">支付方式</h2>
  <ul class="alOrder_select" id="alOrder_pay">
  <s:iterator value="payList" id="payVO" status="st">
    <s:if test="#payVO.enable==1">
      <li id="al${payVO.id }" data-title="${payVO.id }" class="paytag <s:if test='#st.index==0'>cur</s:if>" ><a href="javascript:void();">${payVO.name }<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></a></li>
    </s:if>
  </s:iterator>
    <p id="alnote">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  </ul>
--%>
<!-- 发票信息 开始 -->
  <section class="alOrder_addr alOrder_invoice">
    <a href="#app" onclick="javascript:showInvoice();">
      <dl>
        <dt>发票信息</dt><dd id="isInvoice">不开发票</dd>
      </dl>
    </a>
  </section>
  
  <div id="invoice" style="display:none;">
  <ul class="invoice_select" id="invoice_option">
    <li>
      <dl>
        <dt>发票信息：</dt>
        <dd><a href="#" class="cur" id="invoice_close_a" data-title="0" '><em>不要发票<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a><a href="#" id="invoice_open_a" data-title="1" ><em>要发票<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a></dd>
      </dl>
    </li>
  </ul>
  
  <div class="invoice_content">
    
    <ul class="invoice_select" id="invoice_title">
      <li>
        <dl>
          <dt>发票抬头：</dt>
          <dd><a href="#" id="invoice_org_a" class="cur" data-title="0"><em>单位<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a><a href="#" id="invoice_person_a" data-title="1"><em>个人<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a></dd>
        </dl>
      </li>
      <span>
        <input type="text" id="invoice_org" name="org" value="" placeholder="请填写单位名称,如不填写默认显示‘个人’" />
        <input type="text" id="invoice_person" name="person" value="" placeholder="请填写个人姓名,如不填写默认显示‘个人’" />
      </span>
        <input type="hidden" id="_invoice_org" value=""  />
        <input type="hidden" id="_invoice_person" value=""  />
    </ul>

    <ul class="invoice_select" id="invoice_class">
      <li>
        <dl>
          <dt>发票内容：</dt>
          <dd>
            <a href="#" class="cur" data-title="商品全称" id="商品全称"><em>商品全称<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a>
            <a href="#" data-title="箱包" id="箱包"><em>箱包<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a>
            <a href="#" data-title="饰品" id="饰品"><em>饰品<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a>
            <a href="#" data-title="化妆品" id="化妆品"><em>化妆品<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a>
            <a href="#" data-title="钟表" id="钟表"><em>钟表<i><img src="<%=path %>/images/e.gif" lazy"<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a>
            <a href="#" data-title="服装" id="服装"><em>服装<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a>
            <a href="#" data-title="鞋帽" id="鞋帽"><em>鞋帽<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a>
            <a href="#" data-title="眼镜" id="眼镜"><em>眼镜<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a>
          </dd>
        </dl>
      </li>
      <p><em>温馨提示:</em>手表类商品只能开具为品牌和型号，其他内容无法开具。</p>
    </ul>

    <ul class="invoice_select" id="invoice_addr">
      <li>
        <dl>
          <dt>发票邮至：</dt>
          <dd><a href="#" class="big cur" data-title="0" onclick='$(".invaddr_list_li").removeClass("curr");'><em>与收货地址相同<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a>
          <s:if test='invoiceaddrsList == null || invoiceaddrsList.size < 10'>
          </s:if>
          <a href="#" data-title="1" id="other_addr"><em>其他地址<i><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/cur_icon.png" width="12" height="12"></i></em></a>
          </dd>
        </dl>
        <input type="hidden" id="addrVal" value="0">
      </li>
    </ul>

    <ul class="invoice_addrForm add_cont">
        <div id="invaddrList">
      <s:iterator value="invoiceaddrsList" id="invoiceaddrs" status="st">
        <ul class="invaddr_list">
          <li id="${invoiceaddrs.id }" class="invaddr_list_li">
              <section class="alOrder_invaddr" style="background:none">
                <a href="javascript:void(0);" onclick="setInvAddress(${invoiceaddrs.id });">
                  <dl>
                    <dt>收货人：</dt><dd>${invoiceaddrs.name }</dd>
                  </dl>
                  <dl>
                    <dt>地　址：</dt><dd>${invoiceaddrs.addr }</dd>
                  </dl>
                  <dl>
                    <dt>手机号：</dt><dd>${invoiceaddrs.tel }</dd>
                  </dl>
                </a>
              </section>
          </li>
        </ul>
      </s:iterator>
    </div>
    
    <div id="other_addr_div">
      <li>
        <label for="userName">姓　 名：</label>
        <input type="text" id="INV_AddrName" name="userName" value="" maxlength="20" onfocus="changeState();"/>
      <li>
        <label>省　 份：</label>
        <select id="INV_AddrProvince" onclick="changeState();">
            <option value="-1">请选择省份</option>
            <s:iterator value="provinceList" id="provinceVO">
              <option value="${provinceVO.id }" >${provinceVO.name }</option>
            </s:iterator>
        </select>
      </li>
      <li>
        <label>城　 市：</label>
        <select id="INV_AddrCity" onclick="changeState();">
            <option value="-1">请选择城市</option>
            <s:iterator value="cityList" id="cityVO">
              <option value="${cityVO.id }" >${cityVO.name }</option>
            </s:iterator>
        </select>
      </li>
      <li>
        <label>地　 区：</label>
        <select id="INV_AddrArea" onclick="changeState();">
            <option value="-1">请选择地区</option>
            <s:iterator value="areaList" id="areaVO">
              <option value="${areaVO.id }" >${areaVO.name }</option>
            </s:iterator>
        </select>
      </li>
       <li>
               <span>街道：</span>
               <select id="INV_AddrTown" onclick="changeState();">
                  <option value="-1">请选择地区</option>
				  <s:iterator value="townList" id="townVO">
                    <option value="${townVO.id }" >${townVO.name }</option>
                  </s:iterator>
              </select>
          </li>
      <li>
        <label for="userPhone" >手　 机：</label>
        <input type="tel" id="INV_AddrMobile" name="userPhone" value="" maxlength="11" onfocus="changeState();"/>
      </li>
      <li>
        <label for="userCode">邮　 编：</label>
        <input type="tel" id="INV_AddrCode" name="userCode" value="" maxlength="6" onfocus="changeState();"/>
      </li>
      <li>
        <label for="userAddr">地　 址：</label>
        <input type="text" id="INV_AddrText" name="userAddr" value="" onfocus="changeState();"/>
      </li>
      </div>
    </ul>

  </div>
  
  <div class="inv_addr_errorMsg addr_errorMsg" style="padding: 0;color: #B51111;text-align: center;line-height: 25px;" ></div>

  <p class="txt_center">
    <input type="button" class="alList_submitBtn invoice_submitBtn" value="确认" onclick="addInvoice();$('#msg').hide();"/>
    <!--a href="#" class="alList_submitBtn">确认</a-->
    <a href="javascript:hideRevertInvoice();" class="alList_moreBtn">取消</a>
  </p>
  </div>
<!-- 发票信息 结束 -->

<!-- 优惠券列表开始 -->
<s:if test='couponList != null && 0 < couponList.size '>
  <section class="alOrder_addr alOrder_invoice">
    <a href="#app" onclick="javascript:showCoupon();">
      <dl>
        <dt>优惠券</dt>
        <dd id="iscoupon">未使用 </dd>
      </dl>
    </a>
  </section>

  <div class="alOrder_couponList" style="display:none;">
    <s:iterator value="couponList" id="couponVO" status="st">
  	  <dl>
    	<dd>
        	<p>${couponVO.desc}</p>
            <ul>
            	<li>优惠券面值<br />${couponVO.amount }</li>
                <li>有效期<br />${couponVO.expiredate }</li>
                <li><a href="#" onclick="javascript:selectCoupon(this);" couponNo="${couponVO.couponno}" couponRule="${couponVO.desc}" couponAmount="${couponVO.amount }" class="select_coupon" id ="usecode" isUse="0">使用</a></li>
            </ul>
        </dd>
      </dl>
    </s:iterator>
  </div>
</s:if>
<!-- 优惠券列表结束 -->

  <!-- 优惠码 开始 -->
<section class="alOrder_addr alOrder_couponcode">
    <a href="#app" onclick="javascript:showCouponcode();">
      <dl>
        <dt>使用优惠码</dt><dd id="isCouponcode">未使用</dd>
      </dl>
    </a>
</section>
  
<div class="_couponcode" style="display:none;">
  	
         <div class="prompt" id="prompt">  温馨提示：优惠码和优惠券不能同时使用。</div>
         <s:if test="#session != null && #session['user'] != null && #session['user'].level!='正式会员'">
	        <div class="prompt" id="promptlevel">您是<s:property value="#session['user'].level"/>，如使用优惠码，该商品不能同时享受会员价。</div>
         </s:if>
          <div>   
          	<label >使用优惠码：</label>
            <input type="text" id="couponcode" name="orderVO.couponcode" value=""  readonly="readonly"  "/>
       	</div>
       		
         <div>  <input type="button" onclick="javascript:getCouponCodeInfo()" id="select_couponcode" useflag="0" value="使用" /></div>
  </div>
<!--优惠码信息 结束 -->


   <h2 class="alOrder_title">商品清单</h2>
  <section class="alOrder_list">
    <s:iterator value="productsPromotionVOList" id="productsPromotionVO" >
      <s:iterator value="#productsPromotionVO.productCartVO" id="productCartVO" status="st">
      <input type="hidden" id="shopid" name="shopid" value="<s:property value='#productCartVO.shopDetailId'/>"/>
    <dl class="alOrder_listBlock">
      <dt>
         <span style="width:70px; height:90px;"><img src="<%=path %>/images/e.gif" lazy="<s:property value='#productCartVO.img'/>" width="70" height="90"></span>
         <p><s:property value="#productCartVO.productName"/><br />
          <s:iterator value="#productCartVO.skuAttrText" id="attr" >
			<s:property value="#attr.name"/>：<s:property value="#attr.value"/><br />
          </s:iterator>

            数量：<s:property value='#productCartVO.quantity'/>件<br />
            <em>&yen;<s:property value="#productCartVO.price"/></em>
         </p>
        <input class="subtotal" type="hidden" value="<s:property value="#productCartVO.quantity * #productCartVO.price"/>">
      </dt>
    </dl>
     </s:iterator>
    </s:iterator>
	  <p class="totalPricewrap"  >
     	<span>总金额：</span><em>&yen;${orderVO.amount}</em></br>
     		<span id="discount" style="display: none;"><span >-优惠金额：</span><em id="discountamount"></em></br></span>
      		<span id="s_carriage" style="display: none;text-align:right;"><span>+运费：</span><em id="carriage"></em></br></span>
     		<span id="totalPrice">应付金额：</span><em id="em"></em><p>
  </section>
  <%-- <form name="orderForm" id="orderForm" action="<%=path%>/orderaction!saveorder"> --%>
    <input type="hidden" id="addrid" name="orderVO.addrid">
    <input type="hidden" id="dateVal" value="" name="orderVO.express">
  <%--  <input type="hidden" id="payVal" value="20" name="orderVO.paytypeid">
    <input type="hidden" id="paytypechildid" value="37" name="orderVO.paytypechildid">--%>

    <input type="hidden" id="optionVal" value="0" name="orderVO.invoiceflag">
    <input type="hidden" id="_optionVal" value="0" >
    <input type="hidden" id="titleVal" value="0" name="orderVO.invoicetype">
    <input type="hidden" id="_titleVal" value="0" >
    <input type="hidden" id="invoicetitle" value="" name="orderVO.invoicetitle">
    <input type="hidden" id="_invoicetitle" value="">
    <input type="hidden" id="classVal" value="商品全称" name="orderVO.invoicecontent">
    <input type="hidden" id="_classVal" value="商品全称" >
    <input type="hidden" id="invoiceaddrid" value="0" name="orderVO.invoiceaddrid">
    <input type="hidden" id="_invoiceaddrid" name="orderVO.invoiceaddrid">

    <input type="hidden" id="orderfrom" value="9" name="orderVO.orderfrom">
    <input type="hidden" id="ordertype" value="2" name="orderVO.ordertype">
    <input type="hidden" id="coupon" value="0" name="orderVO.coupon">
    <input type="hidden" id="amount" value="${orderVO.amount }" >
<input type="hidden" id="_carriage" value="${orderVO.carriage }" >
    <input type="hidden" id="errorskus" value="" name="orderVO.errorskus">
<input type="hidden" id="buysids" value="" name="orderVO.buysids">
    <input type="hidden" id="sle" value="" >
    <input type="hidden" id="v" value="0" >
    <input type="hidden" id="isUseCouponcode" value="0" >
    <input type="hidden" id="isSubmit" value="0" >
    <input type="hidden" id="INV_Addr_size" value='<s:property value="invoiceaddrsList.size" />' />
  <%-- </form> --%>
  <a href='javascript:save();' class="alOrder_buyBtn alOrder_submitBtn" id="submit" >提交订单</a>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>