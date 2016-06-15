<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
//调用客户端提示框-end
		function goback(){
			var islogin=${islogin};
			if(browser.versions.android==true){					
				
				if(islogin==1){
					window.SysClientJs.goBack();
					<%
					request.getSession().setAttribute("islogin","0");
					%>
				}else{
					window.SysClientJs.toLastPage();
				}
					//
			}else if(browser.versions.iPhone==true||browser.versions.iPad==true||browser.versions.ios==true){
				if(islogin==1){
					setWebitEvent("goback","SPA01");
					<%
						request.getSession().setAttribute("islogin","0");
					%>
				}else{
					window.history.go(-1);
				}
				
			}			
		}
</script>
<div id="minshengtitle" style="height:45px">
<div style="background:url(images/title_bg1.png) no-repeat;position:fixed;background-size:100%;z-index:10;height:45px;margin:0 auto;width:100%;text-align:center;color:#fff;font-size:25px;line-height:45px;top:0px;"><a href="javascript:goback();" style="background:url(images/go_back_btn_n_1.png) no-repeat;background-size:100%;width:60px;height:45px;position:absolute;top: 7px;left: -4px;">&nbsp;</a>尚品网</div>
</div>
</div>
<header>
  <!-- 禁止自动识别5位以上数字为电话 -->
  <meta name="format-detection" content="telephone=no">
  <h1 id="alLogo">
    <a href="<%=path%>/spindex!index?gender=0&ch=${ch}">尚品</a>
  </h1>
  <nav class="alUser_icon">
   <ul>
    <li><a href="<%=path%>/allcartaction!listCart?ch=${ch}"><img src="<%=path%>/images/cart_icon.png" width="31" height="31" alt="购物袋"></a></li>
    <li><a href="<%=path%>/orderaction!orderlist?ch=${ch}"><img src="<%=path%>/images/order_icon.png" width="31" height="31" alt="订单"></a></li>
    <li><a href="<%=path%>/accountaction!info?ch=${ch}"><img src="<%=path%>/images/user_icon.png" width="31" height="31" alt="账户"></a></li>
   </ul>
  </nav>
</header>