var browser={
	versions:function(){
		var u = navigator.userAgent, app = navigator.appVersion;
		return {
			trident: u.indexOf('Trident') > -1, //IE�ں�
			presto: u.indexOf('Presto') > -1, //opera�ں�
			webKit: u.indexOf('AppleWebKit') > -1, //ƻ��ȸ��ں�
			gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //����ں�
			mobile: !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/), //�Ƿ�Ϊ�ƶ��ն�
			ios: !!u.match(/(i[^;]+\;(U;)? CPU.+Mac OS X)/), //ios�ն�
			android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android�ն˻���uc�����
			iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //�Ƿ�ΪiPhone����QQHD�����
			iPad: u.indexOf('iPad') > -1, //�Ƿ�iPad
			webApp: u.indexOf('Safari') == -1 //�Ƿ�webӦ�ó���û��ͷ����ײ�
		}
	}(),
	language:(navigator.browserLanguage || navigator.language).toLowerCase()
}
function JsonToStr(o) {
    var arr = [];
    var fmt = function (s) {
        if (typeof s == 'object' && s != null) return JsonToStr(s);
        return /^(string|number)$/.test(typeof s) ? '"' + s + '"' : s;
    }
    for (var i in o)
        arr.push('"' + i + '":' + fmt(o[i]));
    return "{" + arr.join(',') + "}";
}
var eventName = "";
var eventCode = "0";
var session = {};
var _locked = false;
function lock() {
	if (_locked) {
		return true;
	}
	_locked = true;
	return false;
}
function unlock() {
	_locked = false;
}
function getWebkitEventCode() {
	return eventCode;
}
function getWebkitEvent() {
	return eventName;
}
function getWebkitValues() {
	return "";
}
function setWebkitValues(a) {
}
function clearEvent() {
	eventCode = "0";
	eventName = "";
}
function initWebkitTitleBar() {
	clearEvent();
	return {"title":"\u7cfb\u7edf\u6807\u9898", "leftButton":{"exist":false}, "rightButton":{"exist":true, "name":"", "func":"touchRightButton();"}};
}
function setWebkitSession(a) {
	session = a;
	clearEvent();
}
var _session_timeout = false;
function showTimeOut() {
	if (!_session_timeout) {
		_session_timeout = true;
		setWebitEvent("clearEvent()", "11");
	}
}
var _mevents = new Array();
function setWebitEvent(b, a) {
	if (b == "") {
		return;
	}
	if (browser.versions.ios || browser.versions.iPhone || browser.versions.iPad) {
		_mevents.push(JsonToStr({code:a, name:b}));
	} else if (browser.versions.android) {
		setAndroidWebitEvent(b,a);
	} else {// other client
	}
}
function getWebkitEventCode() {
	return _mevents.length > 0 ? _mevents.shift() : "0";
}
function getWebkitEvent() {
	return "";
}
// ---------------android js����------------------
function setAndroidWebitEvent(evtName, evtCode) {
	alert("evtName:"+evtName+",evtCode"+evtCode);
	if(evtName.indexOf("()")==-1){
		evtName += "()";
	}
	switch (evtCode) {
	  case "02":  //code : "HY01":���ÿͻ��˵�¼�ӿ�,HY03:����9����ӿ�
	  	var param = eval(evtName);
		window.SysClientJs.toLoginHY(param);
		break;
	  case "HY02":// ����֧���ӿ�
		var param = eval(evtName);
		window.SysClientJs.submitOrderHY(param);
		break;
	}
}
