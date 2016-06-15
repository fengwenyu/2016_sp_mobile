/**
 * @author Macrox
 */

(function(window){

    var SP = (function(){
        var _core, _UseLocalXHR = "UseLocalXHR", _AlwaysPreserveOrder = "AlwaysPreserveOrder", _AllowDuplicates = "AllowDuplicates", _CacheBust = "CacheBust", _Debug = "Debug", _BasePath = "BasePath", root_page = /^[^?#]*\//.exec(location.href)[0], root_domain = /^\w+\:\/\/\/?[^\/]+/.exec(root_page)[0], append_to = document.head || document.getElementsByTagName("head"), opera_or_gecko = (window.opera && Object.prototype.toString.call(window.opera) == "[object Opera]") || ("MozAppearance" in document.documentElement.style), log_msg = function(){
        }, log_error = log_msg, test_script_elem = document.createElement("script"), explicit_preloading = typeof test_script_elem.preload == "boolean", real_preloading = explicit_preloading || (test_script_elem.readyState && test_script_elem.readyState == "uninitialized"), script_ordered_async = !real_preloading && test_script_elem.async === true, xhr_or_cache_preloading = !real_preloading && !script_ordered_async && !opera_or_gecko;
        
        
        if (window.console && window.console.log) {
            if (!window.console.error) 
                window.console.error = window.console.log;
            log_msg = function(msg){
                window.console.log(msg);
            };
            log_error = function(msg, err){
                window.console.error(msg, err);
            };
        }
        
        
        function is_func(func){
            return Object.prototype.toString.call(func) == "[object Function]";
        }
        
        function is_array(arr){
            return Object.prototype.toString.call(arr) == "[object Array]";
        }
        
        function canonical_uri(src, base_path){
            var absolute_regex = /^\w+\:\/\//;
            
            if (/^\/\/\/?/.test(src)) {
                src = location.protocol + src;
            }
            else 
                if (!absolute_regex.test(src) && src.charAt(0) != "/") {
                    src = (base_path || "") + src;
                }
            return absolute_regex.test(src) ? src : ((src.charAt(0) == "/" ? root_domain : root_page) + src);
        }
        
        function merge_objs(source, target){
            for (var k in source) {
                if (source.hasOwnProperty(k)) {
                    target[k] = source[k];
                }
            }
            return target;
        }
        
        function check_chain_group_scripts_ready(chain_group){
            var any_scripts_ready = false;
            for (var i = 0; i < chain_group.scripts.length; i++) {
                if (chain_group.scripts[i].ready && chain_group.scripts[i].exec_trigger) {
                    any_scripts_ready = true;
                    chain_group.scripts[i].exec_trigger();
                    chain_group.scripts[i].exec_trigger = null;
                }
            }
            return any_scripts_ready;
        }
        
        function create_script_load_listener(elem, registry_item, flag, onload){
            elem.onload = elem.onreadystatechange = function(){
                if ((elem.readyState && elem.readyState != "complete" && elem.readyState != "loaded") || registry_item[flag]) 
                    return;
                elem.onload = elem.onreadystatechange = null;
                onload();
            };
        }
        
        function script_executed(registry_item){
            registry_item.ready = registry_item.finished = true;
            for (var i = 0; i < registry_item.finished_listeners.length; i++) {
                registry_item.finished_listeners[i]();
            }
            registry_item.ready_listeners = [];
            registry_item.finished_listeners = [];
        }
        
        function request_script(chain_opts, script_obj, registry_item, onload, preload_this_script){
            setTimeout(function(){
                var script, src = script_obj.real_src, xhr;
                
                if ("item" in append_to) {
                    if (!append_to[0]) {
                        setTimeout(arguments.callee, 25);
                        return;
                    }
                    append_to = append_to[0];
                }
                script = document.createElement("script");
                if (script_obj.type) 
                    script.type = script_obj.type;
                if (script_obj.charset) 
                    script.charset = script_obj.charset;
                
                if (preload_this_script) {
                    if (real_preloading) {
                    
                        if (chain_opts[_Debug]) 
                            log_msg("start script preload: " + src);
                        registry_item.elem = script;
                        if (explicit_preloading) {
                            script.preload = true;
                            script.onpreload = onload;
                        }
                        else {
                            script.onreadystatechange = function(){
                                if (script.readyState == "loaded") 
                                    onload();
                            };
                        }
                        script.src = src;
                    }
                    else 
                        if (preload_this_script && src.indexOf(root_domain) == 0 && chain_opts[_UseLocalXHR]) {
                            xhr = new XMLHttpRequest();
                            if (chain_opts[_Debug]) 
                                log_msg("start script preload (xhr): " + src);
                            xhr.onreadystatechange = function(){
                                if (xhr.readyState == 4) {
                                    xhr.onreadystatechange = function(){
                                    };
                                    registry_item.text = xhr.responseText + "\n//@ sourceURL=" + src;
                                    onload();
                                }
                            };
                            xhr.open("GET", src);
                            xhr.send();
                        }
                        else {
                        
                            if (chain_opts[_Debug]) 
                                log_msg("start script preload (cache): " + src);
                            script.type = "text/cache-script";
                            create_script_load_listener(script, registry_item, "ready", function(){
                                append_to.removeChild(script);
                                onload();
                            });
                            script.src = src;
                            append_to.insertBefore(script, append_to.firstChild);
                        }
                }
                else 
                    if (script_ordered_async) {
                    
                        if (chain_opts[_Debug]) 
                            log_msg("start script load (ordered async): " + src);
                        script.async = false;
                        create_script_load_listener(script, registry_item, "finished", onload);
                        script.src = src;
                        append_to.insertBefore(script, append_to.firstChild);
                    }
                    else {
                    
                        if (chain_opts[_Debug]) 
                            log_msg("start script load: " + src);
                        create_script_load_listener(script, registry_item, "finished", onload);
                        script.src = src;
                        append_to.insertBefore(script, append_to.firstChild);
                    }
            }, 0);
        }
        
        function create_sandbox(){
            var window_defaults = {}, can_use_preloading = real_preloading || xhr_or_cache_preloading, queue = [], registry = {}, instanceAPI;
            
            window_defaults[_UseLocalXHR] = true;
            window_defaults[_AlwaysPreserveOrder] = false;
            window_defaults[_AllowDuplicates] = false;
            window_defaults[_CacheBust] = false;
            
            window_defaults[_Debug] = false;
            window_defaults[_BasePath] = "";
            
            function execute_preloaded_script(chain_opts, script_obj, registry_item){
                var script;
                
                function preload_execute_finished(){
                    if (script != null) {
                        script = null;
                        script_executed(registry_item);
                    }
                }
                
                if (registry[script_obj.src].finished) 
                    return;
                if (!chain_opts[_AllowDuplicates]) 
                    registry[script_obj.src].finished = true;
                
                script = registry_item.elem || document.createElement("script");
                if (script_obj.type) 
                    script.type = script_obj.type;
                if (script_obj.charset) 
                    script.charset = script_obj.charset;
                create_script_load_listener(script, registry_item, "finished", preload_execute_finished);
                
                if (registry_item.elem) {
                    registry_item.elem = null;
                }
                else 
                    if (registry_item.text) {
                        script.onload = script.onreadystatechange = null; // script injection doesn't fire these events
                        script.text = registry_item.text;
                    }
                    else {
                        script.src = script_obj.real_src;
                    }
                append_to.insertBefore(script, append_to.firstChild);
                
                if (registry_item.text) {
                    preload_execute_finished();
                }
            }
            
            function do_script(chain_opts, script_obj, chain_group, preload_this_script){
                var registry_item, registry_items, ready_cb = function(){
                    script_obj.ready_cb(script_obj, function(){
                        execute_preloaded_script(chain_opts, script_obj, registry_item);
                    });
                }, finished_cb = function(){
                    script_obj.finished_cb(script_obj, chain_group);
                };
                
                script_obj.src = canonical_uri(script_obj.src, chain_opts[_BasePath]);
                script_obj.real_src = script_obj.src +
                (chain_opts[_CacheBust] ? ((/\?.*$/.test(script_obj.src) ? "&_" : "?_") + ~ ~ (Math.random() * 1E9) + "=") : "");
                
                if (!registry[script_obj.src]) 
                    registry[script_obj.src] = {
                        items: [],
                        finished: false
                    };
                registry_items = registry[script_obj.src].items;
                
                if (chain_opts[_AllowDuplicates] || registry_items.length == 0) {
                    registry_item = registry_items[registry_items.length] = {
                        ready: false,
                        finished: false,
                        ready_listeners: [ready_cb],
                        finished_listeners: [finished_cb]
                    };
                    
                    request_script(chain_opts, script_obj, registry_item, ((preload_this_script) ? function(){
                        registry_item.ready = true;
                        for (var i = 0; i < registry_item.ready_listeners.length; i++) {
                            registry_item.ready_listeners[i]();
                        }
                        registry_item.ready_listeners = [];
                    }
 : function(){
                        script_executed(registry_item);
                    }), preload_this_script);
                }
                else {
                    registry_item = registry_items[0];
                    if (registry_item.finished) {
                        finished_cb();
                    }
                    else {
                        registry_item.finished_listeners.push(finished_cb);
                    }
                }
            }
            
			function create_chain(){
                var chainedAPI, chain_opts = merge_objs(window_defaults, {}), chain = [], exec_cursor = 0, scripts_currently_loading = false, group;
                
                function chain_script_ready(script_obj, exec_trigger){
                
                    if (chain_opts[_Debug]) 
                        log_msg("script preload finished: " + script_obj.real_src);
                    script_obj.ready = true;
                    script_obj.exec_trigger = exec_trigger;
                    advance_exec_cursor();
                }
                
                function chain_script_executed(script_obj, chain_group){
                
                    if (chain_opts[_Debug]) 
                        log_msg("script execution finished: " + script_obj.real_src);
                    script_obj.ready = script_obj.finished = true;
                    script_obj.exec_trigger = null;
                    for (var i = 0; i < chain_group.scripts.length; i++) {
                        if (!chain_group.scripts[i].finished) 
                            return;
                    }
                    chain_group.finished = true;
                    advance_exec_cursor();
                }
                
                function advance_exec_cursor(){
                    while (exec_cursor < chain.length) {
                        if (is_func(chain[exec_cursor])) {
                        	try {
                                chain[exec_cursor++]();
                            } 
                            catch (err) {}
                            continue;
                        }
                        else 
                            if (!chain[exec_cursor].finished) {
                                if (check_chain_group_scripts_ready(chain[exec_cursor])) 
                                    continue;
                                break;
                            }
                        exec_cursor++;
                    }
                    if (exec_cursor == chain.length) {
                        scripts_currently_loading = false;
                        group = false;
                    }
                }
                
                function init_script_chain_group(){
                    if (!group || !group.scripts) {
                        chain.push(group = {
                            scripts: [],
                            finished: true
                        });
                    }
                }
                
                chainedAPI = {
                    using: function(){
                        for (var i = 0; i < arguments.length; i++) {
                            (function(script_obj, script_list){
                                var splice_args;
                                
                                if (!is_array(script_obj)) {
                                    script_list = [script_obj];
                                }
                                for (var j = 0; j < script_list.length; j++) {
                                    init_script_chain_group();
                                    script_obj = script_list[j];
                                    
                                    if (is_func(script_obj)) 
                                        script_obj = script_obj();
                                    if (!script_obj) 
                                        continue;
                                    if (is_array(script_obj)) {
                                        splice_args = [].slice.call(script_obj);
                                        splice_args.unshift(j, 1);
                                        [].splice.apply(script_list, splice_args);
                                        j--;
                                        continue;
                                    }
                                    if (typeof script_obj == "string") 
                                        script_obj = {
                                            src: script_obj
                                        };
                                    script_obj = merge_objs(script_obj, {
                                        ready: false,
                                        ready_cb: chain_script_ready,
                                        finished: false,
                                        finished_cb: chain_script_executed
                                    });
                                    group.finished = false;
                                    group.scripts.push(script_obj);
                                    
                                    do_script(chain_opts, script_obj, group, (can_use_preloading && scripts_currently_loading));
                                    scripts_currently_loading = true;
                                    
                                    if (chain_opts[_AlwaysPreserveOrder]) 
                                        chainedAPI.excute();
                                }
                            })(arguments[i], arguments[i]);
                        }
                        return chainedAPI;
                    },
                    excute: function(){
                        if (arguments.length > 0) {
                            for (var i = 0; i < arguments.length; i++) {
                                chain.push(arguments[i]);
                            }
                            group = chain[chain.length - 1];
                        }
                        else 
                            group = false;
                        
                        advance_exec_cursor();
                        
                        return chainedAPI;
                    }
                };
                
                return {
                    using: chainedAPI.using,
                    excute: chainedAPI.excute
                };
            }
            
            instanceAPI = {
                using: function(){
                    return create_chain().using.apply(null, arguments);
                },
                excute: function(){
                    return create_chain().excute.apply(null, arguments);
                },
				install: function(){
					if(arguments.length === 1 && /zepto/i.test(arguments[0]) && /msie/i.test( navigator.userAgent ) && !/opera/i.test( navigator.userAgent )){
						return this.using.apply(this, ["/js/jquery.min.js"]).excute();
					}
					return this.using.apply(this, arguments).excute();
				}
            };
            
            return instanceAPI;
        }
        
        _core = create_sandbox();
        (function(addEvent, domLoaded, handler){
            if (document.readyState == null && document[addEvent]) {
                document.readyState = "loading";
                document[addEvent](domLoaded, handler = function(){
                    document.removeEventListener(domLoaded, handler, false);
                    document.readyState = "complete";
                }, false);
            }
        })("addEventListener", "DOMContentLoaded");
		
        return {
            core: _core,
            plug: {}
        }
    })();
    
    window.SP = SP;
    
})(window, undefined);
//获取项目根目录
function getRootPath(){
//	var current_path = window.location.href;
//	console.log("current_path:" + current_path);
//	var pathName = window.location.pathname;
//	console.log("pathName:" + pathName);
//	var index = current_path.indexOf(pathName);
//	console.log("index:" + index);
//	var host = current_path.substring(0,index);
//	var project_name = pathName.substring(0,pathName.substring(1).indexOf("/") + 1);
//	console.log("host:" + host);
//	console.log("project_name:" + project_name);
//	console.log(host.indexOf("m.shangpin.com"));
//	if(host.indexOf("m.shangpin.com") > -1){
//		return host;
//	}
	var ctx = document.getElementById("ctx").value;
	return ctx;
	
//	var contextPath = document.location.pathname;
//	var index = contextPath.substr(1).indexOf("/");
//	contextPath = contextPath.substr(0, index + 1);
//	delete index;
//	console.log(contextPath);
//	return contextPath;

}
function getShareImg(){
	var imgs = document.querySelector('.share-img img');
	
	if(imgs!=null){
		return imgs.src;
	}
	//var imgs = document.getElementsByTagName("img");
	return null;
}
//获取当前日期时间并格式化
Date.prototype.format = function(format){
	var o = {
	"M+" : this.getMonth()+1, //month
	"d+" : this.getDate(), //day
	"h+" : this.getHours(), //hour
	"m+" : this.getMinutes(), //minute
	"s+" : this.getSeconds(), //second
	"q+" : Math.floor((this.getMonth()+3)/3), //quarter
	"S" : this.getMilliseconds() //millisecond
	}

	if(/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}

	for(var k in o) {
		if(new RegExp("("+ k +")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		}
	}
	return format;
} 