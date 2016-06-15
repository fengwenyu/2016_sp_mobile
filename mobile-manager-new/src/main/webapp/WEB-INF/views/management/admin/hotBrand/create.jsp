<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">
	<form id="hotBrandAdd" action="${contextPath }/management/hotBrand/create" method="post" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
		<div class="pageFormContent" layoutH="58">
		<table>
			<tbody>
				<tr>
					<td width="50px">品牌ID:</td>
					<td><input id="brandId" name="brandId" type="text"
						class="validate[required,maxSize[50]] required" size="20"
						maxlength="50">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>品牌名称:</td>
					<td><input id="brandName" name="brandName" type="text"
						class="validate[required,maxSize[100]] required" size="20"
						maxlength="100"></td>
				</tr>
			</tbody>
		</table>
		<br>
		<table>
			<tbody>
				<tr>
					<td>位置顺序:</td>
					<td><input id="sort" name="sort" type="text"
						class="validate[required,maxSize[11]] required" size="20"
						maxlength="11"></td>
				</tr>
			</tbody>
		</table>
		<br>
		<table>
			<tbody>
				<tr>
					<td>图片链接:</td>
					<td><input id="imgUrl" name="imgUrl" type="text"
						class="validate[required,maxSize[200]] required" size="60"
						maxlength="200"></td>
				</tr>
			</tbody>
		</table>
		<br>
		<table>
			<tbody>
				<tr>
					<td>图片宽度:</td>
					<td><input id="imgWidth" name="imgWidth" type="text"
						class="validate[required,maxSize[50]] required" size="20"
						maxlength="50">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

					<td>图片高度:</td>
					<td><input id="imgHeight" name="imgHeight" type="text"
						class="validate[required,maxSize[50]] required" size="20"
						maxlength="50"></td>
				</tr>
			</tbody>
		</table>
		<br>
		<table>
			<tbody>
				<tr>
					<td>头图链接:</td>
					<td><input id="topImgUrl" name="topImgUrl" type="text"
						class="validate[required,maxSize[200]] required" size="60"
						maxlength="200"></td>
				</tr>
			</tbody>
		</table>
		<br>
		<table>
			<tbody>
				<tr>
					<td>头图宽度:</td>
					<td><input id="topImgWidth" name="topImgWidth" type="text"
						class="validate[required,maxSize[50]] required" size="20"
						maxlength="50">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>头图高度:</td>
					<td><input id="topImgHeight" name="topImgHeight" type="text"
						class="validate[required,maxSize[50]] required" size="20"
						maxlength="50"></td>
				</tr>
			</tbody>
		</table>
		</div>
		<div class="formBar">
		<ul><li>
			<div class="button">
				<div class="buttonContent">
					<button type="submit">确定</button>
				</div>
			</div>
			</li>
			<li>
			<div class="button">
				<div class="buttonContent">
					<button type="button" class="close">关闭</button>
				</div>
			</div>
			</li>
		</div>
	</form>
</div>