$(function(){
	
	var useName,
		avatarSrc,
		raiseMoney,
		sendMoney,
		exchange,
		sendListLength,
		raiseListLength;
	
	
	if($('.raise-money').html()==""||$('.exchange-money').html()==""){
		userData();
	}
	if($('.raise').length&&$('.red-bg>h3>em').html()==""){
		userData();
		raiseList();
	};
	if($('.send').length&&$('.red-bg>h3>em').html()==""){
		userData()
		sendList();
	}
	
	//用户数据
	function userData(){
		$.getJSON("/data/wxchat/list.json",function(data){
			avatarSrc = data.user[0].avatarSrc;
			raiseMoney = data.user[0].raiseMoney;
			raiseListLength = data.raiseList.length;
			sendMoney = data.user[0].sendMoney;
			sendListLength = data.sendList.length;
			exchange = data.user[0].exchange;
			$('.avatar>a>img').attr('src',avatarSrc);
			$('.raise-money').html(raiseMoney);
			$('.raise-num').html(raiseListLength);
			$('.send-money').html(sendMoney);
			$('.send-num').html(sendListLength);
			$('.raise-money').html(raiseMoney);
			$('.exchange-money').html(exchange);
			
		});
	};
	
	//送出数据
	function sendList(){
		$.getJSON("/data/wxchat/list.json",function(data){
			var sendList = "";
			$.each(data["sendList"],function(i,info){
					sendList += "<li>";
					sendList += "<span>"+info["userName"]+"</span>";
					sendList += "<p>"+info["time"]+"</p>";
					sendList += "<em>+<strong>"+info["money"]+"</strong></em>";
					sendList += "</li>"

			});
			$('.friends-box').append(sendList);
		});
	};
	
	//获得数据
	function raiseList(){
		$.getJSON("/data/wxchat/list.json",function(data){
			var raiseList = "";
			$.each(data["raiseList"],function(i,info){
					raiseList += "<li>";
					raiseList += "<span>"+info["userName"]+"</span>";
					raiseList += "<p>"+info["time"]+"</p>";
					raiseList += "<em>+<strong>"+info["money"]+"</strong></em>";
					raiseList += "</li>"

			});
			$('.friends-box').append(raiseList);
		});
	};

});