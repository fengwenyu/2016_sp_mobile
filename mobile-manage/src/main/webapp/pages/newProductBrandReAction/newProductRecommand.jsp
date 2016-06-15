<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>添加推荐品牌新品</title>
<%@ include file="/pages/public/common.jspf"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/script/newproductbrand.js"></script>
<script type="text/javascript">


	</script>
</head>
<body>
	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />&nbsp;添加推荐品牌新品
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--显示表单内容-->

	<div id="QueryArea">
		<div style="height: 30px">

			<table border=0 cellspacing=3 cellpadding=5>
				<tr>
					<td>品类</td>
					<td><select id="categoryIdQuery" name="categoryIdQuery"
						style="width: 150px;">
							<option value="-1">---请选择---</option>
							<option value="A01B02">女士服装</option>
							<option value="A01B03">女士鞋靴</option>
							<option value="A01B01">女士箱包</option>
							<option value="A01B04">女士配饰</option>
							<option value="A02B02">男士服装</option>
							<option value="A02B03">男士鞋靴</option>
							<option value="A02B01">男士箱包</option>
							<option value="A02B04">男士配饰</option>
					</select> </td>
					<td><input type="image"
						src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"
						/ " onclick="reloadgrid();">
					</td>
				</tr>
			</table>

		</div>

	</div>


	<div id=MainArea>
		<div class="Description" style="margin-left: 10px;">

			<div id="newproductDiv">
				<table id="newProduct"></table>
			</div>
		</div>

		<div id="add-window" title="推荐位添加窗口"
			style="width: 850px; height: 500px;">

			<div style="padding: 20px 20px 20px 100px;">
			
				<s:form action="">
				<div id="desc" style="padding: 0px 00px 10px 0px;"></div>
					<table>
						<tr id="uporsave">
							
							<td>品类</td>
							<td><select id="categoryId" name="categoryId"
								style="width: 100px;">
									<option value="-1">---请选择---</option>
									<option value="A01B02">女士服装</option>
									<option value="A01B03">女士鞋靴</option>
									<option value="A01B01">女士箱包</option>
									<option value="A01B04">女士配饰</option>
									<option value="A02B02">男士服装</option>
									<option value="A02B03">男士鞋靴</option>
									<option value="A02B01">男士箱包</option>
									<option value="A02B04">男士配饰</option>
							</select> <font color="red"> *</font></td>
							
						</tr>
						<tr><td>新品推荐</td></tr>
						<tr>
							<td>位置1</td>
							<td><input id="firstNewProductId" name="firstNewProductId"
								type="text" value=""/> <font color="red"> *</font></td>

							<td>位置2</td>
							<td><input id="secondNewProductId" name="secondNewProductId"
								type="text" /> <font color="red"> *</font></td>
						</tr>
						
						<tr>
							<td>品牌推荐</td>
						</tr>
						<tr>
							<td>位置1ID</td>
							<td><input id="firstBrandId" name="firstBrandId" type="text" />
								<font color="red"> *</font></td>

							<td>位置1名称</td>
							<td><input id="firstBrandName" name="firstBrandName"
								type="text" /> <font color="red"> *</font></td>

							<td>位置1商品编号</td>
							<td><input id="firstBrandProductId" name="firstBrandProductId"
								type="text" /> <font color="red"> *</font></td>
						</tr>
						
						<tr>
							<td>位置2ID</td>
							<td><input id="secondBrandId" name="secondBrandId"
								type="text" /> <font color="red"> *</font></td>

							<td>位置2名称</td>
							<td><input id="secondBrandName" name="secondBrandName"
								type="text" /> <font color="red"> *</font></td>
							<tr>
						

						<tr>
							<td>位置3ID</td>
							<td><input id="threeBrandId" name="threeBrandId"
										type="text" /> <font color="red"> *</font></td>

							<td>位置3名称</td>
							<td><input id="threeBrandName" name="threeBrandName"
										type="text" /> <font color="red"> *</font></td>
						<tr>
						
						<tr>
							<td>位置4ID</td>
							<td><input id="fourthBrandId" name="fourthBrandId"
												type="text" /> <font color="red"> *</font></td>

							<td>位置4名称</td>
							<td><input id="fourthBrandName" name="fourthBrandName"
												type="text" /> <font color="red"> *</font></td>
						<tr>
						
						<tr>
							<td>位置5ID</td>
							<td><input id="fifthBrandId" name="fifthBrandId"
														type="text" /> <font color="red"> *</font></td>
							<td>位置5名称</td>
							<td><input id="fifthBrandName" name="fifthBrandName"
														type="text" /> <font color="red"> *</font></td>
						<tr>
						
						<tr>
							<td>位置6ID</td>
							<td><input id="sixthBrandId" name="sixthBrandId"
																type="text" /> <font color="red"> *</font></td>

							<td>位置6名称</td> 
							<td><input id="sixthBrandName" name="sixthBrandName"
																type="text" /> <font color="red"> *</font></td>
						<tr>
						<tr>
							<td>位置7ID</td>
							<td><input id="seventhBrandId" name="seventhBrandId" type="text" /> <font
																		color="red"> *</font></td>
							<td>位置7名称</td>
							<td><input id="seventhBrandName" name="seventhBrandName" type="text" /> <font
																		color="red"> *</font></td>
						<tr>

						</tr>
					</table>
				</s:form>
			</div>
			<div style="text-align: center; padding: 5px;">
				<a href="javascript:void(0)" onclick="saveEntity()" id="btn-save"
					icon="icon-search" class='easyui-linkbutton'>保存</a> <a
					href="javascript:void(0)" onclick="closeWindows()" id="btn-cancel"
					icon="icon-cancel" class='easyui-linkbutton'>取消</a>
			</div>
		</div>

	</div>
</body>
</html>
