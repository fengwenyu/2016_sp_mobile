
$(function(){	
    		var ccode =["1","2","3"];
    		var ran1=Math.floor(Math.random() * 3);
    		var ran2=Math.floor(Math.random() * 3);
    		var ran3=Math.floor(Math.random() * 3);
    		var ccode1=ccode[ran1];
    		var ccode2=ccode[ran2];
    		var ccode3=ccode[ran3];
    		$(".egg a").eq(0).attr("ccode",ccode1);
    		$(".egg a").eq(1).attr("ccode",ccode2);
    		$(".egg a").eq(2).attr("ccode",ccode3);
    		//砸蛋延时
    		var mesgFn = function(message,title,callback,time){
    			var setFn = function(){
    				jShare(message,title,callback); 
    				
    			}
    			setTimeout(setFn,time);
    		}
    		var locked = 1; //加锁-防止连点
    		var times=3;
    		$('body').on('touchstart click','.egg a',function(e){
    			e.preventDefault();
    			var $this = $(this);
    			if(locked && $this.attr('smashed')=="true"){
    				locked = 0;
    			}else{
    				return false;
    			}
    			var ccode=$this.attr('ccode');
    			//获取数据
    				$.ajax({
    					type: "GET",
    					url: getRootPath()+"/meet/coupon/get",
    					data: {'coupon':ccode},
    					success: function(data){
    						if(data.code==undefined){
    							alert("请先登录！");
    							//mesgFn("<p class='tip'><em class='bg-icon'></em><em class='btn-icon'></em>领取失败！</p>","","",600);
    							//解锁
    							locked = 1;
    							return ;
    						}else if(data.code=='2'){
    							location.href=getRootPath()+"/meet/redirect/app?id="+196;
    							//解锁
								locked = 1;
    							return ;
    						}else{
    							if(data.code=='0'){
    								var couponrmb;
    								if(ccode=='1'){
    									couponrmb="一等奖-1500";
    								}else if(ccode=='2'){
    									couponrmb="二等奖-1000";
    								}else{
    									couponrmb="三等奖-800";
    								}
    								times=times-1;
    								if(times==0){
    									mesgFn("<p class='tip'><em class='bg-icon'></em><em class='btn-icon'></em>恭喜您中奖了！ <br>您获得"+couponrmb+"优惠礼包！<br>优惠券请到您的个人账户中查看！</p>","","",600);
    								}else{
    									mesgFn("<p class='tip'><em class='bg-icon'></em><em class='btn-icon'></em>恭喜您中奖了！<br>您还有"+times+"次抽奖机会，再试试手气！<br>您获得"+couponrmb+"元优惠礼包！<br>优惠券请到您的个人账户中查看！</p>","","",600);
    								}
    								//砸动作动画
    								var num = 1,
    									setInterfun = setInterval(function(){
    										num++;
    										
    										if(num == 4){
    											clearInterval(setInterfun);
    											$this.find('img').css({
    												'margin-top':'-98px'
    											});
    										}
    								},100);
    								
    								//表示已砸
    								$this.attr('smashed','false');
    								//解锁
    								locked = 1;
    							}else{
    								if(data.msg!=undefined){
    									alert(data.msg);
    								}
    								//解锁
    								locked = 1;
    								return ;
    							}
    					  }
    					
    					}
    				});
    			/*砸蛋动画*/		
    			
    		});
    		
    		//提示层
    		
    		$(".setting_btn").click(function(){
    			jShare("<p class='rule'><em class='rule-title'>奖项设置</em><em class='bg-icon'></em><em class='btn-icon'></em>1.每个账号每天仅三次砸蛋机会，砸中后会自动充值到账户中。<br>2. 奖项设置：一等奖1500元礼包、二等奖1000元礼包、三等奖800元礼包。<br>3.优惠礼包适用于全场箱包（“整点愚乐”商品除外）。<br>4.砸蛋活动结束时间：4月2日 24:00。<br>5.活动解释权归尚品所有。</p>","","");
    			return false;
    			
    		});
    		
    		$('body').on('touchstart click','.tip .btn-icon',function(e){
    			e.preventDefault();
    			$("#popup_container").remove();
    			$("#popup_overlay").remove();
    		});
    		
    		$('body').on('touchstart click','.rule .btn-icon',function(e){
    			e.preventDefault();
    			$("#popup_container").remove();
    			$("#popup_overlay").remove();
    		});
    		
    	});
