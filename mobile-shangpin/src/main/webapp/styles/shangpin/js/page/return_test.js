;$(function(){
	$('.js-return-explain-pop .close-btn').click(function(){
		$('.js-return-explain-pop').hide();
	});
	$('.close-btn').click(function(){
		$('#abc').attr("style","display: none;");
	});
});
function show(){
	$('#abc').attr("style","display: block;");
	$('.js-return-explain-pop').show();
}