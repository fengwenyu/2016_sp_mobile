(function(e) {
    e.search = function(t) {
        return this.el = e(t),
        this.el.each(this.init);
    },
    e.extend(e.search.prototype, {
        init: function(t) {
            var n = e(this).find("input"),
			str,
			v = e(this).find("span.datalist"),
			vLen = v.length,
			filter = ["qq.com","sina.com","sohu.com","126.com","163.com","139.com"],
            r = e(this).find("button");	! n.val() && (r[0].style.display = "none");
			if(vLen > 0){! n.val() && (v[0].style.display = "none");}
			
            var fn = function() {
                r[0].style.display = "block",
                !n.val() && (r[0].style.display = "none");
				
				if(n.val().indexOf("@") > -1 && vLen > 0){
					
					v[0].style.display = "block",
					! n.val() && (v[0].style.display = "none");
					
					var word = n.val().substr(n.val().indexOf("@")+1), w = "", ret = [];
					if (word === "" && v.find("a").length == 0) {
						
						for (var i = 0, len = filter.length; i < len; i++) {
							v.append("<a href='#'>" + filter[i] + "</a>");
						}
						
						return filter;
					}
					for (var i = 0, len = filter.length; i < len; i++) {
						w = filter[i];
						if (w.indexOf(word) === 0) {
							ret.push(w);
						}
					}
					if(ret.length > 0){
						//console.log(ret);
						v.empty();
						for(var i = 0, len = ret.length; i < len; i++){
							v.append("<a href='#'>" + ret[i] + "</a>");
						}
						
						if(n.val().indexOf(".") > -1){
							v[0].style.display = "none";
						}else{
							v[0].style.display = "block",
							! n.val() && (v[0].style.display = "none");
						}
						
					}else{
						v.hide();
					}
					return ret;
					
				}else{
					v.hide();
				}
				
				str = $.trim(n.val());
				
            };
            n.keyup(fn),
            r.click(function(e) {
                n.val("").focus(),
                r[0].style.display = "none";
				if(vLen > 0){
					v[0].style.display = "none"
				}
                e.preventDefault();
            }),
			v.delegate("a","click",function(e){
				//console.log(str);
				v[0].style.display = "none",
				n.val(str + "@" + $(this).text()),
				e.preventDefault();
			}),
			n.blur(function(){
				if(vLen > 0){
					setTimeout(function(){v[0].style.display = "none"},100);
				}
				setTimeout(function(){r[0].style.display = "none"},100);
			});
        }
    });
})(Zepto),
function() {
    window.Login = function() {},
    $.extend(window.Login.prototype, {
        init: function() {},
        loginForm: function() {
            $("#J_Login").on("submit",
            function(e) {
                var re = /^([\w]+[_|\_|\-|\.]?)*[\w]+@([\w]+[_|\_|\-|\.]?)*[\w]+\.[a-zA-Z]{2,3}$/,
					mre = /^1\d{10}$/;
				if ($.trim($("#J_UserNameTxt").val()) == "" || !re.test($("#J_UserNameTxt").val()) && !mre.test($("#J_UserNameTxt").val())){
					return $(".login_errorMsg").html("请输入正确邮箱地址或手机号码！"),
					$("#J_UserNameTxt").parent("p").addClass("error"),
                	!1;
				}else{
					$(".login_errorMsg").html("");
					$("#J_UserNameTxt").parent("p").removeClass("error");
				}
				window.localStorage.setItem("lastNick",$("#J_UserNameTxt").val());
				
                if ($.trim($("#J_PassWordTxt").val()) == ""){
					return $(".login_errorMsg").html("请输入密码！"),
					$("#J_PassWordTxt").parent("p").addClass("error"),
					!1;
				}else{
					$(".login_errorMsg").html("");
					$("#J_PassWordTxt").parent("p").removeClass("error");
				}
                
                if ($.trim($("#J_Captcha").val()) == ""){
					return $(".login_errorMsg").html("请输入验证码！"),
					$("#J_Captcha").parent("p").addClass("error"),
					!1;
				}else{
					$(".login_errorMsg").html("");
					$("#J_Captcha").parent("p").removeClass("error");
				}
            }),
            new $.search(".c-form-search");
        },
        regForm: function() {
            $("#J_RegForm").on("submit",
            function(e) {
                var re = /^([\w]+[_|\_|\-|\.]?)*[\w]+@([\w]+[_|\_|\-|\.]?)*[\w]+\.[a-zA-Z]{2,3}$/;
				if ($.trim($("#J_UserName").val()) == "" || !re.test($("#J_UserName").val())){
					return $(".login_errorMsg").html("请输入正确账户名！"),
					$("#J_UserName").parent("p").addClass("error"),
                	!1;
				}else{
					$(".login_errorMsg").html("");
					$("#J_UserName").parent("p").removeClass("error");
				}
				
				var pwdLen = $("#J_Pwd").val().length;
				if ($.trim($("#J_Pwd").val()) == "" || pwdLen < 6){
					return $(".login_errorMsg").html("请输入6-20位密码！"),
					$("#J_Pwd").parent("p").addClass("error"),
					!1;
				}else{
					$(".login_errorMsg").html("");
					$("#J_Pwd").parent("p").removeClass("error");
				}
				
				if ($.trim($("#J_rePwd").val()) == "" || $.trim($("#J_Pwd").val()) !== $.trim($("#J_rePwd").val())){
					return $(".login_errorMsg").html("密码校验失败！"),
					$("#J_rePwd").parent("p").addClass("error"),
					!1;
				}else{
					$(".login_errorMsg").html("");
					$("#J_rePwd").parent("p").removeClass("error");
				}
				
                if ($.trim($("#J_Captcha").val()) == ""){
					return $(".login_errorMsg").html("请输入验证码！"),
					$("#J_Captcha").parent("p").addClass("error"),
					!1;
				}else{
					$(".login_errorMsg").html("");
					$("#J_Captcha").parent("p").removeClass("error");
				}
            }),
            new $.search(".c-form-search");
        }
    });
}(Zepto);

$(function(){
	$(".fillGender").delegate("a","click",function(){
		$(this).addClass("cur").siblings("a").removeClass("cur");
		$("#sexVal").val($(this).attr("name"));
		return false;
	});
	
});

//登录表单验证
if($("#J_Login").length > 0){
	var Login = new Login();
	Login.loginForm();
	
	//设置nickValue
	if(window.localStorage && document.getElementById('J_UserNameTxt').value =='')
	{
	 document.getElementById('J_UserNameTxt').value = window.localStorage.getItem('lastNick');
	}
}

//注册表单验证
if($("#J_RegForm").length > 0){
	var Login = new Login();
	Login.regForm();
}