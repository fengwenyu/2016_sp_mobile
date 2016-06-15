$(function(){
	/*计算导航宽度*/
	var filterWidth = 0;
	$('#filter_box ul li').each(function(index, element) {
        filterWidth += $(this).width();
    });
	$('#filter_box ul').css('width',filterWidth);
	
	/*筛选*/
	var $filter_con = $('#filter_con'),
		$filter_hide = $('#filter_hide'),
		$btn_box = $('#btn_box'),
		isComb = false;
		
	$('#filter_box a').click(function(){
		$filter_hide.height($(document).height()).show();
		
		var $id = $(this).attr('id');
		$filter_con.find('h3').html($(this).html() + "筛选");
		$filter_con.attr('class','filter_con_in');
		
		if( $id !== "comb" ){
			var $filter_id = $('#filter_' + $id);
			
			$filter_con.find('dl').hide();
			$filter_id.show().find('dt').hide();
			$filter_id.find('dd').show();
			
			$btn_box.hide();			
			isComb = false;
		}else{
			$filter_con.find('dl').show();
			$filter_con.find('dt').show();
			$filter_con.find('dd').hide();
			$btn_box.show();
			isComb = true;
			alert($filter_con.find('a'));
			$filter_con.find('a').each(function(){
				var onclickv = $(this).attr("onclick");
				var newOnClickV = onclickv.replace("searchkey", "combSearch");
				$(this).attr("onclick", newOnClickV);
			});
		}
	});
	

	var $filter_con_dt = $filter_con.find('dt'),
		$filter_con_dd = $filter_con.find('dd');
	
	$filter_con_dt.click(function(){
		
		if($(this).hasClass('curr')){
			$(this).removeClass('curr').siblings('dd').hide();
		}else{
			$filter_con_dd.hide();
			$filter_con_dt.removeClass('curr');
			$(this).addClass('curr').siblings('dd').show();
		}
		
	});
	
	
	$filter_con_dd.find('a').click(function(){
		var $this = $(this),
			$html = $this.html();
		$this.closest('dd').find('a').removeClass('curr');
		$this.addClass('curr');
		$this.closest('dd').siblings('dt').find('span').html($html ? $html : $this.attr('title'));
		if(!isComb){
			setfn();
		}
		
		return false;
		
	});
	
	/*关闭筛选层*/
	var setfn = function(){
		$filter_con.attr('class','filter_con_out');
		var hidefn = function(){
			$filter_hide.hide();
		}
		setTimeout(hidefn,700);
	};
	$('#submit,#filter_close,#cancel').click(function(){
		setfn();
	});
	
});