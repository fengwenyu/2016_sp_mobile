$(function(){
	var flag=0;
	setTimeout(function(){
		$('.loading-box').remove();
		flag=1;
		if(flag==1){
		  show("dialogue1",0.5);
		  show("dialogue2",1);
		  show("dialogue3",1.5);
		  show("dialogue4",2);
		  show("dialogue5",2.5);
		  
		  
		  setTimeout(function(){
			  
			  var keyboardHeight = $(".keyboard").height();
			 
			  $('.main').css('paddingBottom',keyboardHeight+20);
			  $('html, body').animate({"scrollTop":$('.main').height()},500);
			  $(".keyboard").css('bottom',0);
		  },2500);
		  //setTimeout(setspw, 11000);
		  /*setTimeout(function(){
			  var text= '<div class="text"><img src="http://m.shangpin.com/styles/shangpin/images/star-packet/text1.gif"></div>';
			  $(".keyboard").append(text);
		  },2500);*/
		  
		  setTimeout(function(){
			  $('#myInputText').text('谢谢');
		  },2600);
		  setTimeout(function(){
			  $('#myInputText').text('谢谢幂幂');
		  },2800);
		  setTimeout(function(){
			  $('#myInputText').text('谢谢幂幂大礼，');
		  },3000);
		  setTimeout(function(){
			  $('#myInputText').text('谢谢幂幂大礼，红包');
		  },3200);
		  setTimeout(function(){
			  $('#myInputText').text('谢谢幂幂大礼，红包快点');
		  },3400);
		  setTimeout(function(){
			  $('#myInputText').text('谢谢幂幂大礼，红包快点发给');
		  },3600);
		  setTimeout(function(){
			  $('#myInputText').text('谢谢幂幂大礼，红包快点发给我！');
		  },3800);

		  
		  
		  setTimeout(function(){
			  $(".hand-btn").show();
		  },4000);
		  }
	},2000);
	
	
	//分享
	$('.hand-btn').click(function(){
		
		$(".keyboard").css('bottom','-200%');
		$('.main').css('paddingBottom',50);
		$('html, body').animate({"scrollTop":$('.main').height()},500);
		$('.hand-btn').hide();
		show("dialogue6",0);
		show("dialogue7",0.5);
		setTimeout(function(){
			$('.red-animate').addClass('red-animate-active');
		},500);
		setTimeout(function(){
			$('.red-animate').remove();
		},3500);
		
	});
	
	$('.red-btn').click(function(){
		var batchNo = $("#batchNo").val();
		//window.location="http://localhost:8080/mobile-shangpin/star/index?batchNo=" + batchNo;
		//window.location="http://192.168.20.77/mshangpin/star/index?batchNo=" + batchNo;
		window.location="http://m.shangpin.com/star/index?batchNo=" + batchNo;
	});

	function show(eid, t) {
    	setTimeout(function(){
			//$("#media")[0].play();
			$("."+eid).show();
			window.scroll(0,$('.main').height());
		}, t*1000);
		
	};
  
	function hide(eid) {
	  $("."+eid).hide();
	};
	
	function setspw() {
	  var inputTxtOldWidth = $("#t8").width();
	  var inputTxtNewWidth = inputTxtOldWidth + 30;
	  $("#t8").css('width',inputTxtNewWidth);
	  if(inputTxtNewWidth<200) 
	   {setTimeout(setspw, 500);}
	};
	
	
});