(function($) {
	
	$.alerts = {
		
		// These properties can be read/written by accessing $.alerts.propertyName from your scripts at any time
		
		verticalOffset: -35,                // vertical offset of the dialog from center screen, in pixels
		horizontalOffset: 0,                // horizontal offset of the dialog from center screen, in pixels/
		repositionOnResize: true,           // re-centers the dialog on window resize
		overlayOpacity: .5,                // transparency level of overlay
		overlayColor: '#000',               // base color of overlay
		//draggable: true,                    // make the dialogs draggable (requires UI Draggables plugin)
		okButton: '&nbsp;确定&nbsp;',         // text for the OK button
		cancelButton: '&nbsp;取消&nbsp;', // text for the Cancel button
		dialogClass: null,                  // if specified, this class will be applied to all dialogs
		
		// Public methods
		
		share : function(message, title, callback, isShowBtn) {
			$.alerts._show(title, message, null, 'share', function(result) {
				if( callback ) callback(result);
			},isShowBtn);
		},
		
		// Private methods
		
		_show: function(title, msg, value, type, callback, isShowBtn) {
			
			$.alerts._hide();
			$.alerts._overlay('show');
			
			$("BODY").append(
			  '<div id="popup_container" style="display:none;">' +
			  	'<a href="#" class="title_closeBtn">×</a>' +
			    '<h1 id="popup_title"></h1>' +
			    '<div id="popup_content">' +
			      '<div id="popup_message"><p></p></div>' +
				'</div>' +
			  '</div>');
			
			if( $.alerts.dialogClass ) $("#popup_container").addClass($.alerts.dialogClass);
			
			// IE6 Fix
			//var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'fixed'; 
			var pos = ('undefined' == typeof(document.body.style.maxHeight)) ? 'absolute' : 'fixed'; 
			
			setTimeout(function(){
				$("#popup_container").css({
					position: pos,
					zIndex: 99999,
					padding: 0,
					margin: 0,
					display: 'block'
				});
			},10);
			
			if( title == "" ){
				$("#popup_title, .title_closeBtn").hide();
			}else{
				$("#popup_title").text(title).show();
			}
			
			$("#popup_content").addClass(type);
			$("#popup_message p").text(msg);
			$("#popup_message p").html( $("#popup_message p").text().replace(/\n/g, '<br />') );
			

			setTimeout(function(){
				$("#popup_container").css({
					minWidth: $("#popup_container").offset().width,
					maxWidth: $("#popup_container").offset().width
				});
				
				$.alerts._reposition();
				$.alerts._maintainPosition(true);
			},10);
			
			switch( type ) {
				case 'share':
					$("#popup_message").after('<div id="popup_panel"><button type="button" id="popup_cancel" >' + $.alerts.cancelButton + '</button> <button type="button" id="popup_ok" >' + $.alerts.okButton + '</button></div>');
					
					
					if(isShowBtn==false){
						$("#popup_cancel").hide();
					}

					$("#popup_ok").on("click", function() {
						setTimeout(function(){
							$.alerts._hide();
							if( callback ) callback(true);
						},200);
						return false;
					});
					
					$("#popup_cancel").on("click", function() {
						setTimeout(function(){
							$.alerts._hide();
							if( callback ) callback(false);
						},200);
						return false;
					});
					
					$(".title_closeBtn").on("click", function() {
						setTimeout(function(){
							$.alerts._hide();
							callback(true);
						},200);
						return false;
					});
				break;
			}
		},
		
		_hide: function() {
			$("#popup_container").remove();
			$.alerts._overlay('hide');
			$.alerts._maintainPosition(false);
		},
		
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.alerts._overlay('hide');
					$("BODY").append('<div id="popup_overlay"></div>');
					$("#popup_overlay").css({
						position: 'absolute',
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						background: $.alerts.overlayColor,
						opacity: $.alerts.overlayOpacity
					});
				break;
				case 'hide':
					$("#popup_overlay").remove();
				break;
			}
		},
		
		_loadingMask: function(status) {
			switch( status ) {
				case 'show':
					$.alerts._loadingMask('hide');
					$("BODY").append('<div id="loadingMask"></div><div class="x-mask-loading"><div class="x-loading-spinner"><span class="x-loading-top"></span><span class="x-loading-right"></span><span class="x-loading-bottom"></span><span class="x-loading-left"></span></div></div>');
					$("#loadingMask").css({
						position: 'absolute',
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						background: $.alerts.overlayColor,
						opacity: 0//$.alerts.overlayOpacity
					});
				break;
				case 'hide':
					$("#loadingMask").remove();
					$(".x-mask-loading").remove();
				break;
			}
		},
		
		_reposition: function() {
			var top = ($(window).height() - $("#popup_container").outerHeight()) / 2  + $.alerts.verticalOffset;
			var left = ($(window).width() - $("#popup_container").outerWidth()) / 2 + $.alerts.horizontalOffset;
			//console.log(top + "+" + left)
			
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			
			// IE6 fix
			//if( $.browser.msie && parseInt($.browser.version) <= 6 ) top = top + $(window).scrollTop();
			
			$("#popup_container").css({
				top: top + 'px',
				left: left + 'px'
			});
			$("#popup_overlay").height( $(document).height() );
		},
		
		_maintainPosition: function(status) {
			if( $.alerts.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', function() {
							$.alerts._reposition();
						});
					break;
					case false:
						$(window).unbind('resize');
					break;
				}
			}
		}
		
	}
	
	//Shortuct functions	
	jShare = function(message, title, callback, isShowBtn) {
		$.alerts.share(message, title, callback, isShowBtn);
	};
	
})(jQuery);