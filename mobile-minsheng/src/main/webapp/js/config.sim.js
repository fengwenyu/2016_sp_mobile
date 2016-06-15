/**
 * @author Macrox
 */
$(function(){
	//基础配置
	SP.config = {
		shopcart : null
	};

	//配置图片延迟加载
	SP.config.lazyload = SP.plug.lazyload("img", null, {
		start : function(){
			this.css({opacity : 0});
		},
		end : function(){
			this.animate({opacity:1}, 200);
		}
	});
	
});
