// 事件源
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
// 获取EventCode
function getWebkitEventCode() {
    return eventCode;
}

// 获取自定义事件
function getWebkitEvent() {
    return eventName;
}
// 获取数据：接口
function getWebkitValues() {
    return "";
}
// 数据设置：接口
function setWebkitValues(vars) {
}
// 清理自定义事件：接口
function clearEvent() {
    eventCode = "0";
    eventName = "";
}
// 页面初始化:接口
function initWebkitTitleBar() {
    clearEvent();
    return {
        "title" : "系统标题",
        "leftButton" : {
            "exist" : false
        },
        "rightButton" : {
            "exist" : true,
            "name" : '',
            "func" : "touchRightButton();"
        }
    };
}
// 接收客户端会话信息
function setWebkitSession(vars) {
    session = vars;
    clearEvent();
}


var _session_timeout=false;
/**
 * 会话过期
 */
function showTimeOut(){
	if(!_session_timeout){
		_session_timeout=true;
	    setWebitEvent('clearEvent()', '11');		
	}
}

//事件队列
var _mevents = new Array();
// push 增加入队列 
// shift 先进先出

// 设置自定义事件
function setWebitEvent(evtName, evtCode) {
	if (evtName == "") {
		return;
	}
	_mevents.push(JsonToStr({
		code : evtCode,
		name : evtName
	}));
}
// 获取EventCode
function getWebkitEventCode() {
	return _mevents.length > 0 ? _mevents.shift() : "0";
}

// 获取自定义事件
function getWebkitEvent() {
	return "";
}
