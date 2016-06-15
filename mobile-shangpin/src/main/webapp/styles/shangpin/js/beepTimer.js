$(function(){
    $(".select_time").find("a").click(function(){
        $(this).addClass("cur").siblings().removeClass("cur");
        return false;    
    })    
    
    var mre=/^1[34578]\d{9}$/; //手机号码验证规则
    var isclick = true;
    $(".get_yzm_btn").click(function(e){
     //   e.preventDefault();
        //验证码倒计时        
        if(!isclick) return false;
        var phoneNum=$("#J_mobileNum").val();
        //手机号码验证
        if ($.trim($("#J_mobileNum").val()) == "" || !mre.test(phoneNum)){
            tipLayer("请输入正确的手机号码");
            return;
        }
        $.ajax({
    		type: "GET",
    		url: getRootPath()+"/acivity/sendTipPhoneCode?timstamp=" + new Date().getTime() + "&phoneNum="+phoneNum,
    		success: function(data){
    			if (data.code == "1") {
                    alert(data.msg);
                    return;
                }else if (data.code == "3"){
                    popBox(data.star,data.phone);
                    return;
                } 
    		}
    	});
        var that = $(this),timeId;
        var num = 60;
        that.text(num+"秒").addClass("cur");
        isclick = false;
        timeId = setInterval(function(){
            that.text(--num+"秒")
            if(num == 0){
                clearInterval(timeId);
                that.text("获取验证码").removeClass("cur");
                isclick = true;                    
            }                
        },1000);
    });    
    $(".submit_btn").click(function(e){
        var verifyCode=$("#J_yzm").val();
        var nun=$(".select_time .cur").attr("time");
        var phoneNum=$("#J_mobileNum").val();
        var code=$(this).attr("code");
        e.stopPropagation();
        if ($.trim(phoneNum) == "" || !mre.test(phoneNum)){
            tipLayer("请输入正确的手机号码");
            return;
        }
        if ($.trim(verifyCode) == ""){
            tipLayer("请输入正确的验证码");
            return;
        }
        if ($.trim(nun) == ""){
            tipLayer("需要选提醒时间");
            return;
        }
        var path = getRootPath();
        $.get(path + "/acivity/follow/remind?imstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum + "&verifyCode=" + verifyCode +"&time=" + nun +"&code=" + code,function(data){
        	if(data.code == "0"){
                tipLayer("关注成功!");
                javascript:history.go(-1);
                return;
            }if(data.code == "3"){
                tipLayer("请输入正确的验证码");
                return;
            }else{
                tipLayer(data.mag);
                return;
            }
         });
    });
    
    //提示层
    function tipLayer(t){
        if(t){$(".tip-message").html(t);}
        $('.tip-overlay').show();
        $('.tip-container').show();
        setTimeout(function(){
            $('.tip-overlay').hide();
            $('.tip-container').hide();
        },2000);        
    }
    
    //判断手机输入框内容长度
    $("#J_mobileNum").on("keyup", function(){
        var len = $(this).val().length;
        if(len == 11){
            $(this).blur();
        }
    });
    $("#J_yzm").on("keyup", function(){
        var len = $(this).val().length;
        if(len == 6){
            $(this).blur();
        }
    });    
})
