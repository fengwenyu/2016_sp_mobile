<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>尚品无线管理后台</title>
		<%@ include file="/pages/public/common.jspf"%>
		<link type="text/css" rel="stylesheet" href="style/blue/menu.css"charset="GBK" />
		<script type="text/javascript">
			function menuClick( menuDiv ){
				$(".MenuLevel2").not( $(menuDiv).next() ).hide();
				$(menuDiv).next().toggle();
			}
		</script>
	</head>
	<body style="margin: 0; ">
		<div id="Menu" >
			<ul id="MenuUl" style="overflow:hidden; overflow-y:auto; height:100%;">
				<s:iterator value="#application.topPrivielgeList">
				<s:if test="#session.user.hasPrivilegeByName(modulename)">
					<li class="level1">
						<div onClick="menuClick(this);" class="level1Style">
							${modulename}
						</div>
						<ul style="display: none;" class="MenuLevel2">
							<s:iterator value="children">
							<s:if test="#session.user.hasPrivilegeByName(modulename)">
								<li class="level2">
									<div class="level2Style">
										<a target="right" href="${pageContext.request.contextPath}/${url}.action" >${modulename}</a> 
									</div>
								</li>
							</s:if>
							</s:iterator>
						</ul>
					</li>
				</s:if>
				</s:iterator>
			</ul>
		</div>
	</body>
</html>
