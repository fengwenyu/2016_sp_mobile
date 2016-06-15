var isAndroid = (/android/gi).test(navigator.appVersion),
has3d = css3.has3d(),
hasTransform = !isAndroid && css3.hasTransform(),
gp = hasTransform ? 'translateX' : 'left',
gv1 = has3d ? 'translate3d(' : 'translate(',
gv2 = has3d ? ',0)' : ')';


var screenWidth = window.innerWidth;  //获取屏幕宽度
var imgWidth = $('.slider-wrap').find('img').width();   //获取焦点图img宽度
var imgHeight = $('.slider-wrap').find('img').height(); //获取焦点图img高度
var imgScale = imgHeight/imgWidth;
var liHeight = screenWidth*imgScale;
var liLength = $('.slider-wrap').find('li').length;
$('.slider-outer').width(screenWidth);
$('.slider-outer').height(liHeight);
$('.slider-wrap').width(screenWidth*liLength);
$('.slider-wrap').find('li').width(screenWidth);
$('.slider-wrap').find('li').height(liHeight);
$('.slider-status').width(screenWidth);


$.touchSlider = function(options,container){
	this.op = {
		container : ".slider",  //大容器，包含状态，上下页等
		wrap : ".slider-outer",  //滑动显示区域
		wrapUl : ".slider-wrap",  //滑动容器
		wrapStatus : null,  //状态容器
		margin : 0,  //滑动容器内子元素的间距 
		cls : "sel",  //状态容器内子元素选中的样式
		prev : null,  //上一页
		next : null,  //下一页
		lazy : 'dataimg', //延时属性
		anitime : 400, //滑动持续时间
		easeing : "ease-out",  //滑动公式
		isLoop : false,  //循环
		isPlay : false,  //自动播放
		bounce : true,  //边界反弹,安卓默认禁用回弹
		inter : 5000,  //播放间隔时间
		isHide : true,  //prev和next在头尾是否需要隐藏
		ontouchstart : null, //touch开始触发事件
		ontouchend : null, //touch结束后触发事件
		transitionEnd : null //动画结束后触发
	};
	if(container){this.op.container = container}
	this.reset(options);
	this.increaseEvent();
}
$.extend($.touchSlider.prototype,{
	reset : function(options){
		$.extend(this.op,options || {}); //op内的container优先级高
		this.op.left = this.op.left || 0;
		this.findEl() && this.init();
	},
	findEl : function(){
		var op = this.op,
		container = this._container = $(op.container);
		if(container.length == 0){return null;}
		this._wrap = container.find(op.wrap);
		if(this._wrap.length == 0){return null;}
		this._wul = container.find(op.wrapUl);
		if(this._wul.length == 0){return null;}
		this._childs = this._wul.children();
		if(this._childs.length == 0){return null;}
		this._wrapStatus = op.wrapStatus && container.find(op.wrapStatus);
		this._prev = op.prev && container.find(op.prev)[0];
		this._next = op.next && container.find(op.next)[0];
		return this;
	},
	init : function(){
		var that = this,
		op = that.op,
		container = that._container,
		wrap = that._wrap,
		wul = that._wul,
		childs = that._childs,
		wrapStatus = that._wrapStatus,
		prev = that._prev,
		next = that._next;
		wrap.width()
		var step = that._step = op.step || wrap.width(),  //滑动步长，默认wrap的宽度
		len = that._len = childs.length,  //子元素的个数
		single = childs[0].offsetWidth,  //单个子元素的宽度
		margin = op.margin,
		status = that._status = Math.floor(step/single),  //每页显示子元素的个数
		allWidth = single*len;  //滑动容器的宽度
		
		if(status < 1){return null;}  //出错，childs宽度不能大于_wrap宽度
		if(status > 1 || op.step){op.isLoop = false;}  //如果一页显示的子元素超出1个，或设置了步长，则不支持循环；若自动播放，则只支持一次
		
		if(op.isLoop){
			allWidth = single * (len+2);
			that.op.bounce = true;
		}
		if(hasTransform){
			wrap.css({'-webkit-transform-style':'preserve-3d'});
			//wul.css({'-webkit-perspective':1000,'-webkit-backface-visibility':'hidden'});
			childs.css({'-webkit-transform':gv1+'0,0'+gv2});
		}
		if(margin && typeof margin == 'number'){  //如果有margin
			allWidth += (len-1)*margin;  //总宽度增加
			that._step += margin;
		}
		
		//是否初始位置
		var initLeft = op.left;
		initLeft -= (that._current || 0) * that._step;
		hasTransform && wul.css('-webkit-transform',gv1+initLeft+'px,0'+gv2) || wul.css('left',initLeft);
		
		var pages = that._pages = Math.ceil(len/status);  //总页数
		//状态容器添加子元素
		if(wrapStatus && wrapStatus.length > 0){  //如果状态容器存在
			var temp='',
			childstu = wrapStatus.children();
			if(childstu.length == 0){  //子元素不存在
				for(var i=0;i<pages;i++){
					temp += '<span'+(i==0?" class="+op.cls+"":"")+'></span>';
				}
				wrapStatus.html(temp);
			}
		}
		//如果没超出一页，则不需要滑动
		//延时加载的图片
		var selector = 'img['+op.lazy+']';
		this.lazyimg = wul.find(selector);
		
		this.getImg();
		if(pages <= 1){
			if(prev){prev.style.display = 'none';}
			if(next){next.style.display = 'none';}
			wrapStatus && wrapStatus.hide();
			return null;
		}
		wul.css('width',allWidth);
		//复制收尾元素，以便循环
		if(op.isLoop){
			$(childs[0].cloneNode(true)).appendTo(wul);
			$(childs[len-1].cloneNode(true)).css({'position':'relative','left':-step*(len+2)}).appendTo(wul);
			that._childs = wul.children();
			/* 重置lazy图片数组 */
			that.lazyimg.push(1);
			that.lazyimg = $(that.lazyimg.concat($(that._childs[len+1]).find(selector)));
		}
		//初始坐标参数
		that.eventInit();
	},
	eventInit : function(){
		var that = this,
		initLeft = that.op.left;
		that._coord = {};  //存储开始的event坐标
		that._moveCoord = {};  //存储移动过程的event坐标
		that._cmax = initLeft;  //最大滑动坐标
		that._cmin = -(that._pages-1)*that._step;  //最小滑动坐标
		initLeft && (that._cmin += initLeft);
		that._current = that._current || 0;  //当前所在页
		that._left = initLeft + that._current*that._step;  //滑动容器的x坐标
		if(that._wrapStatus && that._wrapStatus.length > 0){
			that._statusChild = that._wrapStatus.children();  //状态元素
			that._selChild = that._statusChild[that._current];  //当前状态元素
		}
		that._minpage = 0;  //最小页
		that._maxpage = that._pages -1;  //最大页
		that._isScroll = false;  //滑动
		that._playTimer = null;
	},
	increaseEvent : function(){
		var that = this,
		op = that.op,
		_wrap = that._wrap[0];
		if(!_wrap){return null};
		
		//原有代码
		if(_wrap.addEventListener){
			_wrap.addEventListener('touchstart', that, false);
			_wrap.addEventListener('touchmove', that, false);
			_wrap.addEventListener('touchend', that, false);
		}
		
		if(op.isPlay){that.begin();}
		
		if(that._prev){
			that._prev['onclick'] = function(e){that.prev.call(that,e)};
		}
		if(that._next){
			that._next['onclick'] = function(e){that.next.call(that,e)};
		}
		if(that._prev && that._next){
			that.updateArrow();
		}
	},
	handleEvent : function(e){
		var that = this;
		switch(e.type){
			case 'touchstart':
				that.start(e);break;
			case 'touchmove':
				that.move(e);break;
			case 'touchend':
			case 'touchcancel':
				that.end(e);break;
		}
	},
	getImg : function(n){  //还原图片
		var that = this,
		isFirst = n === undefined ? true : false,
		loop = that.op.isLoop,
		lazy = that.op.lazy,
		status = that._status,
		childs = that._childs,
		len = childs.length,
		m = n && parseInt(n,10) || 0,
		lazyimg = that.lazyimg,
		imgarr = [];
		if(lazyimg.length == 0){return;}
		if(m < 0){  //循环
			lazyimg.each(function(n,node){
				if(node == 1) return;
				if(n == (len + m) || n == (len + m - 2)){
					imgarr.push(node);
					lazyimg[n] = 1;
				}
			});
		}
		else{
			m += 1;
			var lisignlen,startn,endn;
			if(status > 1){
				startn = (isFirst ? (m - 1) : m)*status;
				endn = (m + 1)*status;
				/*if(start < len - 1){
					var alen = Math.min((m + 1)*status,len);
					for(var i = start + 1;i<alen;i++){
						imgarr = imgarr.add($(childs[i]).find(selector));
					}
				}*/
			}
			else{
				var lisignlen = $(childs[m-1]).children().length,
				startn = (isFirst ? m-1 : m)*lisignlen,
				endn = (m+1)*lisignlen;
			}
			lazyimg.each(function(n,node){
				if(node == 1) return;
				if(n >= startn && n < endn){
					imgarr.push(node);
					lazyimg[n] = 1;
				}
			});
		}
		//console.log(imgarr);
		if(imgarr.length == 0){return;}
		var src,nobj;
		$(imgarr).each(function(n,item){
			nobj = $(item);
			src = nobj.attr(lazy);
			if(src){
				nobj.attr('src',src);
				nobj.removeAttr(lazy);
			}
		});
	},
	getXY : function(e){
		var touchs = e.touches ? e.touches[0] : e;
		return {x:touchs.clientX,y:touchs.clientY};
	},
	start : function(e){  //触摸开始
		var that = this,
		op = that.op;
		if(that._isScroll){return;}  //滑动未停止，则返回
		that._movestart = undefined;
		that._disX = 0;
		that._coord = that.getXY(e);
		op.ontouchstart && op.ontouchstart();
	},
	move : function(e){
		var that = this,
		op = that.op,
		moveCoord = that.getXY(e),
		disX = that._disX = moveCoord.x - that._coord.x,
		initLeft = op.left,
		tmleft;
		if(typeof that._movestart == 'undefined'){  //第一次执行touchmove
			that._movestart = !!(that._movestart || Math.abs(disX) < Math.abs(moveCoord.y - that._coord.y));
		}
		if(!that._movestart){ //不是上下
			e.preventDefault();
			that.stop();
			if(!op.isLoop && op.bounce){  //不循环且开启回弹
				disX = disX / ( (!that._current && disX > 0 || that._current == that._maxpage && disX < 0 ) ? ( Math.abs(disX) / this._step + 1 ) : 1 );  //增加阻力
			}
			tmleft = initLeft - that._current * that._step + disX;
			if(!that.op.bounce){  //未启动回弹
				if(that._left < that._cmin){tmleft = that._cmin;}
				if(that._left > that._cmax){tmleft = that._cmax;}
			}
			if(hasTransform){
				that._wul.css("-webkit-transform",gv1+tmleft+'px,0'+gv2);
			}
			else{
				that._wul.css("left",tmleft);
			}
			that._disX = disX;
			that._left = tmleft;
		}
		/*if(!that._movestart){
			that._scrollx = Math.abs(newdisx) > Math.abs(newdisy);
			if(that._scrollx){
				e.preventDefault();
				//current = (newdisx > 0) && (_current - 1) || (_current + 1);
				//that.getImg(current);
			}
			that._movestart = true;
		}
		else if(that._scrollx){  //如果是左右
			e.preventDefault();
			newdisx = newdisx / ( (!that._current && newdisx > 0 || that._current == that._maxpage && newdisx < 0 ) ? ( Math.abs(newdisx) / this._step + 1 ) : 1 );  //增加阻力
			tmleft = (initLeft || 0) - that._current * that._step + newdisx;
			
			if(!that.op.bounce){
				if(that._left < that._cmin){tmleft = that._cmin;}
				if(that._left > that._cmax){tmleft = that._cmax;}
			}
			//console.log(newdisx,that._left);
			if(hasTransform){
				that._wul.css("-webkit-transform",gv1+tmleft+'px,0'+gv2);
			}
			else{
				that._wul.css("left",tmleft);
			}
			that._left = tmleft;
		}*/
	},
	end : function(e){
		var that = this,
		op = that.op;
		if(!that._movestart){  //如果执行了move
			//e.preventDefault();
			var distance = that._disX;
			if(distance < -10){
				e.preventDefault();
				that.next();
			}else if(distance > 10){
				e.preventDefault();
				that.prev();
			}
			op.ontouchend && op.ontouchend(that._current);
		}
		//that._coord = {};
		distance = null;
	},
	prev : function(e){
		if(e&&e.preventDefault){e.preventDefault()}
		var that = this;
		that._current -= 1;
		if(that._current < that._minpage){
			if(!that.op.isLoop){that._current = that._minpage;}
			else{that._current = that._minpage - 1;}
		}
		this.touchf(-1);
	},
	next : function(e){
		if(e&&e.preventDefault){e.preventDefault()}
		var that = this;
		that._current += 1;
		if(that._current > that._maxpage){
			if(!that.op.isLoop){that._current = that._maxpage;}
			else{that._current = that._maxpage + 1;}
		}
		that.touchf(1);
	},
	touchf : function(str){
		var that = this,
		op = that.op,
		initLeft = op.left,
		leftt = that._left,
		leftend = initLeft - that._current*that._step;
		that._isScroll = true;
		that.stop();
		//console.log(that._current,leftt,leftend);
		if(leftt == leftend){
			that._isScroll = false;
		}
		else{
			that.getImg(that._current);
			var tob = {};
			tob[gp] = leftend + 'px';
			that._wul.animate(tob,op.anitime,op.easeing,function(){
				if(op.isLoop){
					if(that._current >= (that._maxpage+1)){
						that._current = 0;
					}else if(that._current <= (that._minpage-1)){
						that._current = that._maxpage;
					}
				}
				that._left = initLeft - that._current*that._step;  //重新设置slide的x坐标
				if(hasTransform){
					$(this).css('-webkit-transform',gv1 + that._left + 'px,0'+gv2);
				}
				else{
					$(this).css('left',that._left);
				}
				if(!(!op.isLoop && that._current == that._maxpage)){
					that.begin();
				}
				else{
					that.op.isPlay = false;
				}
				that.update();
				that.updateArrow();
				that._isScroll = false;
				op.transitionEnd && op.transitionEnd(that._current,str);
			});
		}
	},
	update : function(){
		var that = this,
		cls = that.op.cls;
		if(that._statusChild && that._statusChild[that._current] && that._selChild){
			$(that._selChild).removeClass(cls);
			$(that._statusChild[that._current]).addClass(cls);
			that._selChild = that._statusChild[that._current];
		}
	},
	updateArrow : function(){  //左右箭头状态
		var that = this,
		op = that.op,
		prev = that._prev,
		next = that._next;
		if(!prev && !next){return;}
		if(op.isLoop){return;}  //如果是循环，则不需要隐藏
		if(op.isHide){
			if(that._current <= 0){
				prev.style.display = 'none';
			}
			else{
				prev.style.display = '';
			} 
			if(that._current >= that._maxpage){
				next.style.display = 'none';
			}
			else{
				next.style.display = '';
			}
		}
	},
	begin : function(){
		var that = this,
		op = that.op;
		if(op.isPlay){  //自动播放
			that.stop();
			that._playTimer = setInterval(function(){
				that.next();
			},op.inter);
		}
	},
	stop : function(){
		var that = this,
		op = that.op;
		if(op.isPlay && that._playTimer){
			clearInterval(that._playTimer);
			that._playTimer = null;
		}
	},
	destroy : function(){
		var that = this;
		that._container[0].removeAttribute('isLoad');
		if(that._pages <= 1){return;}
		//that._wrap[0][this._touchstart] = null;
		var _wrap = that._wrap[0];
		_wrap.removeEventListener('touchstart', that, false);
		_wrap.removeEventListener('touchmove', that, false);
		_wrap.removeEventListener('touchend', that, false);
		if(that._prev){that._prev['onclick'] = null;}
		if(that._next){that._next['onclick'] = null;}
	}
});
$.touchSlider.cache = [];
$.fn.slider = function(options){
	return this.each(function(n,item){
		if(!item.getAttribute('isLoad')){
			item.setAttribute('isLoad',true);
			$.touchSlider.cache.push(new $.touchSlider(options,item));
		}
	});
}
$.touchSlider.destroy = function(){
	var cache = $.touchSlider.cache,
	len = cache.length;
	//console.log($.touchSlider.cache);
	if(len < 1){return;}
	for(var i=0;i<len;i++){
		cache[i].destroy();
	}
	$.touchSlider.cache = [];
	//console.log($.touchSlider.cache);
}
//return $.touchSlider;