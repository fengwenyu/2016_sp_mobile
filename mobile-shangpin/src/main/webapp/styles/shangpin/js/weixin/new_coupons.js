// JavaScript Document


/*	$('.friends-box').hide();*/
    //if($(this).find('.arrow-down')){
		$('.raise-tip').toggle(function(){
			$('.friends-box').slideDown();
			$(this).find('.arrow-down').addClass('arrow-up').removeClass('.arrow-down');
			},
			function(){
				$('.friends-box').slideUp();
			$(this).find('.arrow-down').addClass('arrow-down').removeClass('.arrow-up');
			}
	  );
	/*}else if($(this).find('.arrow-up')){
		$('.raise-tip').click(function(){
			$('.friends-box').slideUp();
			$(this).find('.arrow-down').addClass('arrow-down').removeClass('.arrow-up');
		});
	}*/
	
