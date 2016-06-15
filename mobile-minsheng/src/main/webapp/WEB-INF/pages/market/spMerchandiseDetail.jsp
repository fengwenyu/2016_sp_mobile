<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="/tlds/activityName" prefix="spantag"%> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>尚品网触屏版_领先的高端时尚和奢侈品购物网站</title>
<link href="<%=path %>/css/base.css" rel="stylesheet" />
<link href="<%=path %>/css/page/detail.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path %>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path %>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path %>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path %>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path %>/images/logo/loading.png">
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path %>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path %>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/lazyload.js" + ver)
		    .excute()
		    .using("<%=path%>/js/config.sim.js" + ver)
			.using("<%=path%>/js/css3.js" + ver)
			.using("<%=path%>/js/slideLayer.js" + ver)
			.excute()
			.using("<%=path %>/js/comm.js" + ver)
			.using("<%=path %>/js/detail.js" + ver)
			.excute(function(){
				isHome(false);
						
				$('#J_m-slider').slider({wrap:".slider-outer",wrapUl:".slider-wrap",wrapStatus:'.slider-status',isLoop:true,prev:".prev",next:".next"});
				<s:if test="#session['ch'] == '100001'">
					JSCallback.onPageLoaded();
				</s:if>
			});
		
			//调用客户端提示框-start
			var res;
			window.alert=function(msg){
				res=msg;
				if(browser.versions.android==true){
					
					window.MsgJs.setAlertInfo('{"title":"提示","msg":"'+res+'","ok_btn":"false","cancle_text":"确定","cancle_func":null}');
				}else if(browser.versions.iPhone==true||browser.versions.iPad==true||browser.versions.ios==true){
					setWebitEvent("iphonealert()","05");
				}
				
			}
			//iphone提示框
			function iphonealert(){
	
				var s='{"title":"提示","msg":"'+res+'","ok_btn":"false","cancle_text":"确定","cancle_func":null}';
			return s;
			}
		//调用客户端提示框-end
	// 颜色和尺码的级联
	function changedSecondProp(obj) {
		$("#count option").remove();
		var objId = "secondpro_"+obj.id.split("_")[1];
		$("#sku").val("");
		$("#countTemp").val("");
		$("#alProdInfo").html("");
		$(".fillSize > span").each(function(){//多选
    		var s = $(this).attr("id");
    		$(this).removeClass("cur");
			if(s==objId){
				$(this).show();
			}else{
				$(this).hide();
			}
		});
	}
	// 设置购买商品的sku和数量（有尺码的商品）
	function setSkuAndCount(sku,count){
		$("#alProdInfo").html("");
		$("#sku").val(sku);
		// $("#countTemp").val(count);
		$("#count option").remove();
		for(var i=1;i<=parseInt(count);i++){
			var $cOption = $("<option/>");
			$cOption.attr("value",i);
			$cOption.text(i);
			$("#count").append($cOption);
			if(i == 1){
				$cOption.attr("selected","selected");
			}
		}
	}
	// 设置购买商品的sku和数量（无尺码的商品）
	function setCount(obj){
		var countObjId = "firstpro_count_"+obj.id.split("_")[1];
		var count = $("#"+countObjId).val();
		var skuObjId = "firstpro_sku_"+obj.id.split("_")[1];
		var sku = $("#"+skuObjId).val();
		$("#alProdInfo").html("");
		$("#sku").val(sku);
		$("#count option").remove();
		for(var i=1;i<=parseInt(count);i++){
			var $cOption = $("<option/>");
			$cOption.attr("value",i);
			$cOption.text(i);
			$("#count").append($cOption);
			if(i == 1){
				$cOption.attr("selected","selected");
			}
		}
	}
	// 提交表单
	function addCart(){
		if($("#sku").val()==""){
			$("#alProdInfo").html("请选择尺码");
			return;
		}
		$("#submit").html("正在提交......");
		$("#submit").removeAttr("href");
		/*if(parseInt($("#count").val())>parseInt($("#countTemp").val())){
			$("#alProdInfo").html("库存不足所买数量");
			return;
		}*/
		$.ajax({
			url:"${pageContext.request.contextPath }/allcartaction!addCart?time="+new Date(),
			data:{"productNo":$("#productno").val(),"skuNo":$("#sku").val(),"quantity":$("#count").val(),"categoryNo":$("#categoryno").val()},
			dataType:'json',
		    timeout: 30000,
            error: function (xmlHttpRequest, error) {
                alert("您的网络异常");
                return;
            },
		    success:function(entityJson){
		    	// 1：跳转至登录页面，2：商品信息有错误
				if("1"==entityJson.code){
					//window.SysClientJs.toLoginShangPin("<%=basePath%>/minshengbindaction!minshengLogin?&loginFrom="+entityJson.loginFrom);
					window.location = "${pageContext.request.contextPath }/accountaction!loginui?ch=${ch}&loginFrom="+entityJson.loginFrom;
					//window.location = "${pageContext.request.contextPath }/minshengbindaction!minshengLogin?phoneNumber=15000005478&ch=${ch}&loginFrom="+entityJson.loginFrom;
				}else if("2"==entityJson.code){
//					$("#alProdInfo").text("");
//					$("#alBuy_btn").removeClass("alBuy_btn");
//					$("#alBuy_btn").addClass("alBuy_btn alBuy_overbtn");
//					$("#alProdInfo").text(entityJson.msg);
//					$("#submit").text("立即购买");
					alert(entityJson.msg);
					//判断其他尺码
					window.location.reload();
				}else if("3"==entityJson.code){
					window.location = "${pageContext.request.contextPath }/allcartaction!listCart?ch=${ch}";
				}else if("6137612f"==entityJson.code){
					$("#alProdInfo").text("");
					$("#alBuy_btn").removeClass("alBuy_btn");
					$("#alBuy_btn").addClass("alBuy_btn alBuy_overbtn");
					$("#alProdInfo").text(entityJson.msg+"，3秒后将自动跳转到购物车！");
					$("#submit").text("立即购买");
					setTimeout(function(){
						window.location = "${pageContext.request.contextPath }/allcartaction!listCart?ch=${ch}";
					},4000);
				}
		    }
		  });
 		/*$.post("${pageContext.request.contextPath }/cartaction!addcart?time="+new Date(),{
			"sku":$("#sku").val(),
			"typeFlag":'${typeFlag}',
			"count":$("#count").val(),
			"categoryno":$("#categoryno").val()
		},function(entityJson) {
			//alert(entityJson.code);
			// 1：跳转至登录页面，2：商品信息有错误
			if("1"==entityJson.code){
				window.location = "${pageContext.request.contextPath }/accountaction!loginui?loginFrom="+entityJson.loginFrom;
			}else if("2"==entityJson.code){
				$("#alProdInfo").text("");
				$("#alBuy_btn").removeClass("alBuy_btn");
				$("#alBuy_btn").addClass("alBuy_btn alBuy_overbtn");
				$("#alProdInfo").text(entityJson.msg);
				$("#submit").text("立即购买");
			}else if("3"==entityJson.code){
				window.location = "${pageContext.request.contextPath }/cartaction!showcart";
			}else if("6137612f"==entityJson.code){
				$("#alProdInfo").text("");
				$("#alBuy_btn").removeClass("alBuy_btn");
				$("#alBuy_btn").addClass("alBuy_btn alBuy_overbtn");
				$("#alProdInfo").text(entityJson.msg+"，4秒后将自动跳转到购物车！");
				$("#submit").text("立即购买");
				setTimeout(function(){
					window.location = "${pageContext.request.contextPath }/cartaction!showcart";
				},4000);
			}
		},"json");*/
	}
</script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<nav class="alNav">
  <ul>
  	<li>
        <a href="<%=path%>/spindex!index?gender=0&ch=${ch}">首页</a>
	</li>
	<s:if test="istop==1">
		<li><a href="<%=path %>/merchandiseaction!splist?ch=${ch}&topicid=${topicid}&gender=${gender}&istop=1">${activityName }</a></li>
	</s:if>
	<s:elseif test="brandid!=null&&brandid!=''">
		<li><a href="<%=path%>/brandaction!getSPBrands?gender=${gender}&ch=${ch}">品牌</a></li>
		<li><a href="<%=path%>/brandaction!getSPProductsBrand?brandid=${brandid}&brandName=${merchandise.brandname}&gender=${gender}&ch=${ch}">
		<s:if test="merchandise.brandname.length()>10"><s:property value="merchandise.brandname.substring(0,5)"/>...</s:if>
			<s:else>
				<s:property value="merchandise.brandname"/>
			</s:else>
		</a></li>
	</s:elseif>
	<s:elseif test="categoryid!=null&&categoryid!=''&&categoryid.length()!=3">
	<li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=${categoryid}&categoryname=${categoryname}&gender=${gender}&ch=${ch}">${categoryname}</a></li>			
		<s:if test="childCategoryname!=null&&childCategoryname!=''">
			<li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=${categoryid}&categoryname=${categoryname}&childcategoryid=${childCategoryid}&childCategoryname=${childCategoryname}&gender=${gender}&ch=${ch}">${childCategoryname}</a></li>
		</s:if>
		<s:else>
		</s:else>
  </s:elseif>
  <s:else>
  	<li><a href="<%=path%>/merchandiseaction!getNewlist?gender=0&ch=${ch}">新品</a></li>
  </s:else>
    <li>详情</li>
  </ul>
</nav>

<div class="alContent">
  
  <section class="detail_block">
    <header>
  	  <h3>${merchandise.brandname}<em>${merchandise.productname }</em></h3>
      <span>商品编号：${merchandise.goodscode }</span>
    </header>
    <!-- 图片轮播 Start -->
    <div class="content" id="J_m-slider">
        <div class="slider-outer">
            <ul class="slider-wrap">
                <s:iterator value="merchandise.pics" id="pic">
            		<li><img alt="" width="225" height="300" src="<%=path %>/images/e.gif" lazy="${pic }" ></li>
    		    </s:iterator>
            </ul>
        </div>
        <div class="prev pg-btn"><a>上一页</a></div>
        <div class="slider-status" ></div>
        <div class="next pg-btn"><a>下一页</a></div>
    </div>
    <!-- 图片轮播 End -->
    
    <div class="alProdInfo">
    <s:if test="%{merchandise.totalcount>0}">
	      <dl>
	        <dt>价格：</dt>
	        <dd>
	          <strong>&yen;
	            <s:if test="%{merchandise.status==\"000100\"}">
			      ${merchandise.specialprice[0] }
		        </s:if>
		        <s:elseif test="%{merchandise.status==\"0001\"}">
			      ${merchandise.specialprice[0] }
		        </s:elseif>
		        <s:else>
		          ${merchandise.priceList[merchandise.priceindex] }
		        </s:else>
	          </strong>&nbsp;&nbsp;
<!-- 	          <s:elseif test="%{status==\"000100\"}">
	            <del>&yen;${merchandise.priceList[fn:length(merchandise.priceList)-1] }</del>
		      </s:elseif> -->
	          &nbsp;&nbsp;
	          <s:if test="#session['user'].level == '钻石会员'">
					<img src="<%=path %>/images/level0004.png" width="140" >
			  </s:if>
			  <s:if test="#session['user'].level == '白金会员'">
					<img src="<%=path %>/images/level0003.png" width="140"  >
			  </s:if>
			  <s:if test="#session['user'].level == '黄金会员'">
					<img src="<%=path %>/images/level0002.png" width="140" >
			  </s:if>
	        </dd>
	      </dl>
	      <s:if test="%{merchandise.firstpropname!=null&&merchandise.firstpropname!=''}">
			  <dl>
		        <dt>${merchandise.firstpropname }：</dt>
		        <dd class="fillColor">
		          <s:if test="%{merchandise.secondpropname!=null&&merchandise.secondpropname!=''}">
		              <s:iterator value="merchandise.firstprops" status="status">
		              	<s:if test="#status.index==0">
			          		<span class="cur" data-title="${status.index }" id="firstpro_${status.index }" onclick="changedSecondProp(this)"><img src="<%=path %>/images/e.gif" lazy="${icon }" width="34" height="34"><i><img src="<%=path %>/images/cur_icon.png" width="12" height="12"></i></span>
			          	</s:if>
		              	<s:else>
		              		<span data-title="${status.index }" id="firstpro_${status.index }" onclick="changedSecondProp(this)"><img src="<%=path %>/images/e.gif" lazy="${icon }" width="34" height="34"><i><img src="<%=path %>/images/cur_icon.png" width="12" height="12"></i></span>
						</s:else>
		    		  </s:iterator>
	    		  </s:if>
	    		  <s:else>
		   		  	  <s:iterator value="merchandise.firstprops" status="status">
		              	<s:if test="#status.index==0">
			          		<span class="cur" data-title="${status.index }" id="firstpro_${status.index }" onclick="setCount(this)"><img src="<%=path %>/images/e.gif" lazy="${icon }" width="34" height="34"><i><img src="<%=path %>/images/cur_icon.png" width="12" height="12"></i></span>
			          	</s:if>
		              	<s:else>
		              		<span data-title="${status.index }" id="firstpro_${status.index }" onclick="setCount(this)"><img src="<%=path %>/images/e.gif" lazy="${icon }" width="34" height="34"><i><img src="<%=path %>/images/cur_icon.png" width="12" height="12"></i></span>
						</s:else>
					    <s:iterator value="secondprops">
					  		<input type="hidden" value="${count }" id="firstpro_count_${status.index }"/>
					  		<input type="hidden" value="${sku }" id="firstpro_sku_${status.index }"/>
					    </s:iterator>
		    		  </s:iterator>
		   		  </s:else>
		        </dd>
		      </dl>
		   </s:if>
	      <s:if test="%{merchandise.secondpropname!=null&&merchandise.secondpropname!=''}">
		      <dl>
		        <dt>${merchandise.secondpropname }： 
		      <!--   <span style="float:right;">
			      <s:if test="merchandise.sizeinfo!=null&&merchandise.sizeinfo!=''">
			      <a href="<%=path %>/merchandiseaction!sizedesc?productid=${merchandise.goodscode }&ch=${ch}">尺码说明&gt;&gt;</a>
				  </s:if>
		        </span> -->
		        </dt>
		        <dd class="fillSize">
		          <s:iterator value="merchandise.firstprops" status="first">
			          <s:iterator value="secondprops" status="second">
			          	<s:if test="#first.index==0">
			          		<s:if test="#second.index==0">
		              			<span class="cur" data-title="${status.index }" id="secondpro_${first.index}" onclick="setSkuAndCount('${sku }','${count}')"><a href="#">${secondprop }<i><img src="<%=path %>/images/cur_icon.png" width="12" height="12"></i></a></span>
		              		</s:if>
		              		<s:else>
		              			<span data-title="${status.index }" id="secondpro_${first.index}" onclick="setSkuAndCount('${sku }','${count}')"><a href="#">${secondprop }<i><img src="<%=path %>/images/cur_icon.png" width="12" height="12"></i></a></span>
		              		</s:else>
		              	</s:if>
		              	<s:else>
						  	<span data-title="${status.index }" id="secondpro_${first.index}" style="display:none;" onclick="setSkuAndCount('${sku }','${count }')"><a href="#">${secondprop }<i><img src="<%=path %>/images/cur_icon.png" width="12" height="12"></i></a></span>
						</s:else>
		    		  </s:iterator>
	    		  </s:iterator>
		        </dd>
		      </dl>
	      </s:if>
	      <form action="<%=path %>/cartaction!addcart?ch=${ch}" method="post" id="merchandiseInfo">
	      <input type="hidden" name="sku" id="sku" value="${merchandise.defaultSku}"/>
	      <input type="hidden" name="productno" id="productno" value="${merchandise.goodscode }"/>
	      <input type="hidden" name="categoryno" id="categoryno" value="${merchandise.topicno}"/>
	      <input type="hidden" name="countTemp" id="countTemp"/>
	      <input type="hidden" name="url" id="url"/>
	      <dl>
	        <dt>数量：</dt>
	        <dd class="fillNumber">
	          <%--<s:if test="%{merchandise.secondpropname==null||merchandise.secondpropname==''}"> --%>
			      <select name="count" id="count">
					  <s:iterator value="merchandise.firstprops" id="firstprop" status="first">
					  <s:if test="#first.index==0">
				          <s:iterator value="secondprops" id="secondprop" status="second">
			          		  <s:if test="#second.index==0">
						        <s:bean name="org.apache.struts2.util.Counter" id="counter">
							        <s:param name="first" value="0" />
							        <s:param name="last" value="%{#secondprop.count-1}" />
							        <s:iterator>
								        <s:if test="current==1">
								        	<option value="${current }" selected="selected">${current }</option>
								        </s:if>
								       	<s:else>
								       		<option value="${current }">${current }</option>
										</s:else>
							        </s:iterator>
						     	</s:bean>
						     </s:if>
						  </s:iterator>
					  </s:if>
		    		  </s:iterator>
		          </select>
		      <%--</s:if>
	          <s:else>
		          <select name="count" id="count">
		          </select>
		 	  </s:else> --%>
	        </dd>
	      </dl>
	      <dl>
	        <dt>&nbsp;</dt>
	        <dd id="alProdInfo" align="center">${merchandise.msg }</dd>
	      </dl>
	      <s:if test="%{merchandise.msg =='' || merchandise.msg == null}">
	      <dl class="alBuy_btn" id="alBuy_btn">
	        <dd><a href="javascript:addCart();" id="submit">立刻购买</a></dd>
	      </dl>
	      </s:if>
	      <s:else>
		      <dl class="alBuy_btn alBuy_overbtn">
		        <dd><a href="javascript:;">已售罄</a></dd>
		      </dl>
		  </s:else>
	  </s:if>
  	  <s:else>
  	  	  <dl>
	        <dt>&nbsp;</dt>
	        <dd id="alProdInfo" align="center">${merchandise.msg }</dd>
	      </dl>
	      <dl class="alBuy_btn alBuy_overbtn">
	        <dd><a href="javascript:;">已售罄</a></dd>
	      </dl>
	  </s:else>
	  </form>
      <dl class="alShareBtn">
	  	<%--
        <dd>
		<a>分享到：<script type="text/javascript" charset="utf-8">
			(function(){
			  var _w = 72 , _h = 16;
			  var param = {
			    url:location.href,/** location.href */
			    type:"3",
			    count:"0", /**是否显示分享数，1显示(可选)*/
			    appkey:"1456715412", /**您申请的应用appkey,显示分享来源(可选)*/
			    title:"尚品，给力商品推荐：${brand } ${productname }【尚品价${merchandise.now}】（原价${merchandise.past}），100%正品保证，闪电发货！抢购地址：", /**分享的文字内容(可选，默认为所在页面的title)*/
			    pic:"${merchandise.sharepic}", /**分享图片的路径(可选)*/
			    ralateUid:"", /**关联用户的UID，分享微博会@该用户(可选)*/
				language:"zh_cn", /**设置语言，zh_cn|zh_tw(可选)*/
			    rnd:new Date().valueOf()
			  }
			  var temp = [];
			  for( var p in param ){
			    temp.push(p + '=' + encodeURIComponent( param[p] || '' ) )
			  }
			  document.write('<iframe allowTransparency="true" frameborder="0" scrolling="no" src="http://hits.sinajs.cn/A1/weiboshare.html?' + temp.join('&') + '" width="'+ _w+'" height="'+_h+'"></iframe>')
			})()
			</script>
		</a>
		 --%>
        </dd>
      </dl>
    </div>
    
    <div class="alService">
      <ul class="alNotice">
        <li><img src="<%=path%>/images/footer-promise.png" width="320"  ></li>
        <!-- 
        <li><img src="<%=path %>/images/e.gif" lazy="<%=path %>/images/zp_icon.png" width="30" height="30" alt="正品保证"> 正品保证</li>
        <li><img src="<%=path %>/images/e.gif" lazy="<%=path %>/images/my_icon.png" width="30" height="30" alt="免运费"> 免运费　</li>
        <li><img src="<%=path %>/images/e.gif" lazy="<%=path %>/images/sc_icon.png" width="30" height="30" alt="支持试穿"> 支持试穿</li>
         -->
      </ul>
    </div>
    
    <section class="alProd_intro">
      <nav class="alProd_introMenu">
        <ul>
         <li class="cur"><a href="javascript:;">商品详情</a></li>
         <li><a href="javascript:;">售后服务</a></li>
         <li><a href="javascript:;">品牌介绍</a></li>
        </ul>
      </nav>
      
      <div class="alProd_introCell">
        <s:if test="%{merchandise.buyer !='' && merchandise.buyer != null}">
	        <h1>买手推荐</h1>
			<p>${merchandise.buyer }</p>
	    </s:if>
        <h1>基本信息</h1>
        <p>
        	<s:iterator value="merchandise.info" id="str" status="strinfo">

        	${str }<br/>

    		</s:iterator>
        </p>
        <s:if test="%{merchandise.specialinfo !='' && merchandise.specialinfo != null}">
	        <h1>特殊说明</h1>
			<p>${merchandise.specialinfo }</p>
	    </s:if>
      </div>
      
      <div class="alProd_introCell" style="display:none;">
        <s:if test="%{merchandise.aftersale !='' && merchandise.aftersale != null}">
	      
	        <p>${merchandise.aftersale }</p>
	    </s:if>
      </div>
      
      <div class="alProd_introCell" style="display:none;">
        <s:if test="%{merchandise.brandinfo !='' && merchandise.brandinfo != null}">
	       
			<p >${merchandise.brandinfo }</p>
	    </s:if>
      </div>
    </section>
      
  </section>
  
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
