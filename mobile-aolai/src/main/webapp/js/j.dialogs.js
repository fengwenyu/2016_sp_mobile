(function($) {
	
	$.alerts = {
		
		// These properties can be read/written by accessing $.alerts.propertyName from your scripts at any time
		
		verticalOffset: -35,                // vertical offset of the dialog from center screen, in pixels
		horizontalOffset: 0,                // horizontal offset of the dialog from center screen, in pixels/
		repositionOnResize: true,           // re-centers the dialog on window resize
		overlayOpacity: .4,                // transparency level of overlay
		overlayColor: '#000',               // base color of overlay
		//draggable: true,                    // make the dialogs draggable (requires UI Draggables plugin)
		okButton: '&nbsp;确　定&nbsp;',         // text for the OK button
		cancelButton: '&nbsp;取　消&nbsp;', // text for the Cancel button
		dialogClass: null,                  // if specified, this class will be applied to all dialogs
		
		// Public methods
		
		massageBox: function(message, title, callback) {
			$.alerts._show(title, message, null, 'massageBox', function(result) {
				if( callback ) callback(result);
			});
		},
		
		alert: function(message, title, callback) {
			$.alerts._show(title, message, null, 'alert', function(result) {
				if( callback ) callback(result);
			});
		},
		
		confirm: function(message, title, callback) {
			$.alerts._show(title, message, null, 'confirm', function(result) {
				if( callback ) callback(result);
			});
		},
			
		prompt: function(message, value, title, callback) {
			$.alerts._show(title, message, value, 'prompt', function(result) {
				if( callback ) callback(result);
			});
		},
		share : function(message, title, callback) {
			$.alerts._show(title, message, null, 'share', function(result) {
				if( callback ) callback(result);
			});
		},
		
		// Private methods
		
		_show: function(title, msg, value, type, callback) {
			
			$.alerts._hide();
			$.alerts._overlay('show');
			
			$("BODY").append(
			  '<div id="popup_container" style="display:none;">' +
			    '<h1 id="popup_title"></h1>' +
			    '<div id="popup_content">' +
			      '<div id="popup_message"></div>' +
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
				$("#popup_title").hide();
			}else{
				$("#popup_title").text(title);
			}
			$("#popup_content").addClass(type);
			$("#popup_message").text(msg);
			$("#popup_message").html( $("#popup_message").text().replace(/\n/g, '<br />') );
			
			setTimeout(function(){
				$("#popup_container").css({
					minWidth: $("#popup_container").offset().width,
					maxWidth: $("#popup_container").offset().width
				});
				
				$.alerts._reposition();
				$.alerts._maintainPosition(true);
			},10);
			
			switch( type ) {
				case 'massageBox':
					$('div#popup_container').addClass('popup_massge');
					//$('#popup_message').css({'height':'40','padding':'40px 0'});
					$("#popup_overlay").css('opacity','0');
					setTimeout(function(){$("#popup_container").remove();$.alerts._overlay('hide');},1500);
				break;
				case 'alert':
					$('div#popup_container').addClass('popup_alert');
					$("#popup_message").after('<div id="popup_panel"><button type="button" id="popup_ok">' + $.alerts.okButton + '</button></div>');
					$("#popup_ok").click( function() {
						setTimeout(function(){
							$.alerts._hide();
							callback(true);
						},200);
					});
					/*$("#popup_ok").focus().keypress( function(e) {
						if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
					});*/
				break;
				case 'confirm':
					$("#popup_message").after('<div id="popup_panel"><button type="button" id="popup_ok" >' + $.alerts.okButton + '</button> <button type="button" id="popup_cancel" >' + $.alerts.cancelButton + '</button></div>');
					$("#popup_ok").click( function() {
						setTimeout(function(){
							$.alerts._hide();
							if( callback ) callback(true);
						},200);
					});
					
					$("#popup_cancel").click(function() {
						setTimeout(function(){
							$.alerts._hide();
							if( callback ) callback(false);
						},200);
					});
					/*$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});*/
				break;
				case 'prompt':
					$("#popup_message").append('<br /><input type="text" size="30" id="popup_prompt" />').after('<div id="popup_panel"><button type="button" id="popup_ok" >' + $.alerts.okButton + '</button>　<button type="button" id="popup_cancel" >' + $.alerts.cancelButton + '</button></div>');
					$("#popup_prompt").width( $("#popup_message").width() );
					$("#popup_ok").click( function() {
						setTimeout(function(){
							var val = $("#popup_prompt").val();
							$.alerts._hide();
							if( callback ) callback( val );
						},200);
					});
					$("#popup_cancel").click( function() {
						setTimeout(function(){
							$.alerts._hide();
							if( callback ) callback( null );
						},200);
					});
					/*$("#popup_prompt, #popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});*/
					if( value ) $("#popup_prompt").val(value);
					$("#popup_prompt").focus().select();
				break;
				case 'share':
					setTimeout(function(){
						$.alerts._hide();
						if( callback ) callback( null );
					},2000);
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
			//var top = (($(window).height() / 2) - ($("#popup_container").offset().height / 2)) + $.alerts.verticalOffset;
			//var left = (($(window).width() / 2) - ($("#popup_container").offset().width / 2)) + $.alerts.horizontalOffset;
			var top = ($(window).height() - $("#popup_container").outerHeight()) / 2  + $.alerts.verticalOffset;
			var left = ($(window).width() - $("#popup_container").outerWidth()) / 2 + $.alerts.horizontalOffset;
			
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
	
	// Shortuct functions
	jMassage = function(message, title, callback) {
		$.alerts.massageBox(message, title, callback);
	}
	
	jAlert = function(message, title, callback) {
		$.alerts.alert(message, title, callback);
	}
	
	jConfirm = function(message, title, callback) {
		$.alerts.confirm(message, title, callback);
	};
		
	jPrompt = function(message, value, title, callback) {
		$.alerts.prompt(message, value, title, callback);
	};
	
	jShare = function(message, title, callback) {
		$.alerts.share(message, title, callback);
	};
	
})(jQuery);