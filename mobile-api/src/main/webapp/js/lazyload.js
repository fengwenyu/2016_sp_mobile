/**
 * @author Macrox
 */
;
(function(window, $, SP){
    
	//判断移动设备版本
	var browser={
		versions:function(){ 
			   var u = navigator.userAgent, app = navigator.appVersion; 
			   return {//移动终端浏览器版本信息 
					trident: u.indexOf('Trident') > -1, //IE内核
					presto: u.indexOf('Presto') > -1, //opera内核
					webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
					gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
					mobile: !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/), //是否为移动终端
					ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
					android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
					iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
					iPad: u.indexOf('iPad') > -1, //是否iPad
					webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
					isIOS7: u.indexOf('iPhone OS 7') > -1  //最新的IOS7版本
				};
			 }(),
			 language:(navigator.browserLanguage || navigator.language).toLowerCase()
	};
	//判断移动设备版本
	
	if(!("grep" in $)){
        $.grep = function(array, callback, invert){
            //先验证参数的正确性
            var verifier = Object.prototype.toString,
                el,
                ret = [];
            invert = !!invert;
            if(verifier.call(array) === "[object Array]" && verifier.call(callback) === "[object Function]"){
                for(var i = 0, len = array.length; i < len; i++){
                    el = array[i];
                    (function(_i, _el){
                        var n = !!(callback.apply(array, [_el, _i]));
                        if(!!(n ^ invert)){
                            ret.push(_el);
                        }
                    })(i, el);
                }
                return ret;
            }
            else{
                return false;
            }
        };
    }
    var extension = (function(){
        return {
            isInter: function(rec1, rec2){
                var lp1 = rec1.left + rec1.width / 2, lp2 = rec2.left + rec2.width / 2, tp1 = rec1.top + rec1.height / 2, tp2 = rec2.top + rec2.height / 2, w1 = (rec1.width + rec2.width) / 2, h1 = (rec1.height + rec2.height) / 2;
                return Math.abs(lp1 - lp2) < w1 && Math.abs(tp1 - tp2) < h1;
            },
            area: function(el){
                if (el == document) {
                    return {
                        left: document.documentElement.scrollLeft || document.body.scrollLeft,
                        top: document.documentElement.scrollTop || document.body.scrollTop,
                        width: document.documentElement.clientWidth || document.body.clientWidth,
                        height: document.documentElement.clientHeight || document.body.clientHeight
                    };
                }
                else {
                    return {
                        left: el.offset().left,
                        top: el.offset().top,
                        width: el.width(),
                        height: el.height()
                    };
                }
            }
        };
    })(), lazyIndex = 0, __lazyload__, Lazyload = function(el, fn, option){
        this.__indexer = lazyIndex++;
        this.__uid = new Date().getTime();
        this.lazy = [];
		var group = {};
		
        if (arguments.length > 0) {
            this.add(el, fn, option);
        }
		
		this.grouper = function(id, fn){
            if(typeof id === "undefined"){
                return group;
            }
            id = id || 0;
			if(typeof fn === "undefined"){
				return group[id] || null;
			}
            else{
                group[id] = fn;
                return fn;
            }
		};
    };
    
    Lazyload._defaultFunction = function(index){
        var el = this.elem, lazy = this.lazy, callback = this.callback, option = this.option, ext = extension, oel = ext.area(el), od = ext.area((option && option._container) && $(option._container) || document), that = this;
		
        if(browser.versions.isIOS7){
        	
        	setTimeout(function(){
                if (!that.loaded) {
                    that.loaded = true;
                    var tag = (el.attr("tagName") || el.prop("tagName") || "NT").toLowerCase();
                    if (lazy) {
                        if (tag == "img") {
                            el.bind("load", function(){
                                var o = $(this);
                                if (option && option.end) {
                                    option.end.call(o);
                                }
                            }).attr("src", lazy);
                        }
                        else {
                            $("<img>").bind("load", function(){
                                el.css({
                                    "background-image": "url(" + lazy + ")"
                                });
                                if (option && option.end) {
                                    option.end.call(el);
                                }
                            }).attr("src", lazy);
                        }
                    }
                    if (option && option.start) {
                        option.start.call(el);
                    }
                    if (!lazy) {
                        if (option && option.end) {
                            option.end.call(el);
                        }
                    }
                    callback && callback.call(that);
                }
            }, 0);
        	
    	}else{

    		if (ext.isInter(oel, od)) {
				setTimeout(function(){
	                if (!that.loaded) {
	                    that.loaded = true;
	                    var tag = (el.attr("tagName") || el.prop("tagName") || "NT").toLowerCase();
	                    if (lazy) {
	                        if (tag == "img") {
	                            el.bind("load", function(){
	                                var o = $(this);
	                                if (option && option.end) {
	                                    option.end.call(o);
	                                }
	                            }).attr("src", lazy);
	                        }
	                        else {
	                            $("<img>").bind("load", function(){
	                                el.css({
	                                    "background-image": "url(" + lazy + ")"
	                                });
	                                if (option && option.end) {
	                                    option.end.call(el);
	                                }
	                            }).attr("src", lazy);
	                        }
	                    }
	                    if (option && option.start) {
	                        option.start.call(el);
	                    }
	                    if (!lazy) {
	                        if (option && option.end) {
	                            option.end.call(el);
	                        }
	                    }
	                    callback && callback.call(that);
	                }
	            }, 0);
    		}
    		
    	}
    };
    
    Lazyload.prototype = {
    
        constructor: Lazyload,
        
        give: function(adder){
            this.lazy = this.lazy.concat(adder);
            return this;
        },
        
        add: function(el, fn, option){
            var that = this, container;
            if (option && option.container && $(option.container).length > 0) {
                container = option.container;
                delete option.container;
                option._container = container;
                this.add(container, function(){
                    that.__innerlazy__ = new that.constructor(el, fn, option).run(container);
					return that.__innerlazy__;
                });
                return this;
            }
            $(el).each(function(){
                var elem = $(this), lazy = elem.attr("lazy"), callback = fn;
                (lazy || callback) &&
                that.lazy.push({
                    "elem": elem,
                    "lazy": lazy,
                    "callback": callback,
                    "loaded": false,
                    "option": option
                });
                elem.removeAttr("lazy").data("LAZY_UID", that.__indexer + "_" + (++that.__uid));
            });
            return this;
        },
        
        group: function(container, el, id){
            var lazy = this.lazy, len = lazy.length, adder = [], o, that = this;
            $(el).each(function(){
                var that = $(this);
                for (var i = 0; i < len; i++) {
                    if (!(function(_i, _lazy){
                        var cur = _lazy[_i], sp;
                        if (cur && that.data("LAZY_UID") == cur.elem.data("LAZY_UID")) {
                            sp = _lazy.splice(_i, 1)[0];
                            sp.option = $.extend({}, sp.option, {
                                _container: container
                            });
                            adder.push(sp);
                            return false;
                        }
                        return true;
                    })(i, lazy)) {
                        return;
                    };
                }
            });
            
            this.add(container, function(){
                return that.grouper(id, new that.constructor().give(adder).run(container));
            });
            
            return this;
        },
        
        addFlash: function(el){
            return this.add(el, function(){
                var elem = this.elem, flashsrc = elem.attr("flashsrc");
                elem.append("<embed src=\"" + flashsrc + "\"></embed>");
            });
        },
        
        filter: function(){
        	return this.lazy = $.grep(this.lazy, function(o){
        		return !o.loaded;
            });
        },
        
        each: function(fn){
            var lazy = this.filter(), len = lazy.length, fn = fn || Lazyload._defaultFunction;
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    fn.call(lazy[i], i);
                }
            }
            return this;
        },
        
        run: function(container){
            var that = this;
            container = container || window;
            $(container).bind("scroll resize touchend", function(){
                that.each();
            });
            that.each();
            return this;
        }
        
    };
    
    SP.plug.lazyload = function(el, fn, option){
        if (typeof __lazyload__ == "undefined") {
            __lazyload__ = new Lazyload(el, fn, option);
            setTimeout(function(){
                __lazyload__.run();
            }, 0);
        }
        else {
            __lazyload__.add(el, fn, option);
        }
        return __lazyload__;
    };
	
	SP.plug.lazyload.startNew = function(el, fn, option){
		var _lazyload = new Lazyload(el, fn, option);
        setTimeout(function(){
            _lazyload.run();
        }, 0);
		return _lazyload;
	};
	
})(window, Zepto, SP, undefined);
