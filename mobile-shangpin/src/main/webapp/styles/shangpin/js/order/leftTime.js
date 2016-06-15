 $(function(){
	 	var currentTimes=$("#currentTimes").val();
		var aa=$("form").children();
		aa.each(function(){
			 var orderTime = $(this).find("#setTime").text();
			 var place = $(this).find("#leftTime");
			 var dt= new Date(orderTime.replace(/-/g,"/"));
			 var endTime=dt.getTime()+(30*60*1000);
			 var leftTime=endTime-currentTimes;
			 if(dt.getTime()>currentTimes){
				 leftTime=30*59*1000;
			 }
			 place.attr("leftTime",leftTime);
			 window.setInterval(function(){ShowCountDown(place)}, 1000); 
			
		});
		 function ShowCountDown(place) { 
		     var leftTime=place.attr("leftTime");
			 var leftsecond = (leftTime/1000); 
			 var day1=Math.floor(leftsecond/(60*60*24)); 
			 var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
			 var minute= Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
			 var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60);
			 if(second < 10) {
				 second = '0' + second;
			 }
			 place.text(minute +":" +second);
			 var newTime=parseInt(leftTime)-(1*1000);
			 place.attr("leftTime",newTime);
		 }
 });