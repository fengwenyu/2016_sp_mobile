<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%String path = request.getContextPath();%>
<%@ taglib uri="/tlds/activityName" prefix="spantag"%> 
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>尚品网触屏版_领先的高端时尚和奢侈品购物网站</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/account.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%>/images/logo/loading.png">
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />

<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/dialogs.js" + ver)
			.using("<%=path%>/js/comm.js" + ver)
			.excute(function(){
				isHome(false);
				// 标记默认地址
				var obj = $(".curr");
				if(obj.size() == 0){
				    $(".address_list li:eq(0)").addClass("curr");
				    $($("[class='default_address']")[0]).html("默认地址");
				}
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
			
			function confirdel(obj){
				res=obj;
				if(browser.versions.android==true){				
					window.MsgJs.setAlertInfo('{"title":"提示","msg":"确认要删除该地址吗?","ok_btn":"true","ok_text":"确定","ok_func":"deleteAddress(res);","cancle_text":"取消","cancle_func":""}');
				}else if(browser.versions.iPhone==true||browser.versions.iPad==true||browser.versions.ios==true){
					setWebitEvent("iphonealertfir()","05");
				}				
			return s;
			}
			function iphonealertfir(){
				var s='{"title":"提示","msg":"确认要删除该地址吗?","ok_btn":"true","ok_text":"确定","ok_func":"deleteAddress(res)","cancle_text":"取消","cancle_func":""}';
			return s;
			}
		//调用客户端提示框-end
	//删除收货人地址
	function deleteAddress(obj){
		  $.ajax({
		    url:'accountaction!deleteaddress?ch=${ch}',
		    data:{addrid:obj.id},
		    dataType:'json',
		    timeout: 30000,
            error: function (xmlHttpRequest, error) {
            	if(error == "parsererror"){
            		alert("用户帐号异常，请重新登录");
            		window.location.href="<%=path%>/mobileshangpin/accountaction!loginui?ch=${ch}";
            	}else{
                    alert("您的网络异常");
            	}
                return;
            },
		    success:function(data){
		      if(data.success){
		        //删除页面li对象
		        $(obj).parent().parent().remove();
		        //重新排序
		        $(".sort").each(function(n,v){
		          $(this).html(n+1);
		        });

		        // 最多10个地址，少于10个显示添加按钮
		        if($(".sort").size()<10){
		          $(".alOrder_submitBtn").attr("style", "");
		        }
			  }else{
			    alert(data.msg);
			  }
		    }
		  });
	}

</script>

</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/spindex!index?gender=0&ch=${ch}">首页</a></li>
    <li><a href="<%=path%>/accountaction!info?ch=${ch}" title="">我的账户</a></li>
    <li>地址管理</li>
  </ul>
</nav>

<div class="alContent">
  <div class="address_listbox">
    <ul class="address_list">
      <s:iterator value="consigneeAddressList" id="consigneeAddressVO" status="st">
          <li <s:if test='#consigneeAddressVO.isd == "1"'>class="curr"</s:if>>
              <div class="address_infohd">地址<span class="sort"><s:property value='#st.index+1'/></span> 
                  <span class="default_address"><s:if test='#consigneeAddressVO.isd == "1"'>默认地址</s:if></span>
                  <a href="javascript:void(0);" id="<s:property value="#consigneeAddressVO.id" />" class="address_delbtn" onclick="confirdel(this);">[删除]</a>
              </div>
              <section class="alOrder_addr">
                <a href="<%=path%>/accountaction!modifyaddress?addrid=${consigneeAddressVO.id}&ch=${ch}">
                  <dl>
                    <dt>收货人：</dt><dd><s:property value="#consigneeAddressVO.name" /></dd>
                  </dl>
                  <dl>
                    <dt>地　址：</dt><dd><s:property value="#consigneeAddressVO.provname" /><s:property value="#consigneeAddressVO.cityname" /><s:property value="#consigneeAddressVO.areaname" /><s:property value="#consigneeAddressVO.addr" /></dd>
                  </dl>
                  <dl>
                    <dt>手机号：</dt><dd><s:property value="#consigneeAddressVO.tel" /></dd>
                  </dl>
                </a>
              </section>
          </li>
      </s:iterator>
    </ul>

    
      <a href="<%=path%>/accountaction!newaddress?ch=${ch}" class="alOrder_buyBtn alOrder_submitBtn" <s:if test='consigneeAddressList.size >= 10'> style="visibility: hidden;"</s:if>>新增收货地址</a>
    
  </div>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
