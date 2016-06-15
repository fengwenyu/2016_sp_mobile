/**
 * 根据会场信息添加对应的js
 */
var meetId = $("#mmeetId").val();
var meetStatus = $("#mmeetStatus").val();
if (meetId == '171') {
	if (meetStatus == '0') {
		usejs = "meet/2015YearEndTop/2015YearEndTopPreheat.js";
	}
	if (meetStatus == '1') {
		usejs = "meet/2015YearEndTop/2015YearEndTop.js";
	}
} else if (meetId == '167') {
	usejs = "meet/2015WinterDiscount/2015WinterDiscountMan.js";
} else {
	usejs = "meet/2015WinterDiscount/2015WinterDiscount.js";
}
