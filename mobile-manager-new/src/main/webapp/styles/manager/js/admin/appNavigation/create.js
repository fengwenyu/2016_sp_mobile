$(function(){
	$("#tabP").hide();
	$("#linkP").hide();
	$("#startTimeP").hide();
	$("#endTimeP").hide();
	$("#showType").change(function(){
		if(this.value==1||this.value==2){
			$("#tabP").show();
			$("#tabId").addClass("validate[required,maxSize[50]] required");
			$("#linkP").hide();
			$("#link").val("");
			$("#link").removeClass("validate[required,maxSize[50]] required");
			$("#startTimeP").hide();
			$("#startTime").val("");
			$("#endTimeP").hide();
			$("#endTime").val("");
		}else if(this.value==3){
			$("#tabP").hide();
			$("#tabId").val("");
			$("#tabId").removeClass("validate[required,maxSize[50]] required");
			$("#linkP").show();
			$("#link").addClass("validate[required,maxSize[50]] required");
			$("#startTimeP").show();
			$("#endTimeP").show();
		}
	})
})