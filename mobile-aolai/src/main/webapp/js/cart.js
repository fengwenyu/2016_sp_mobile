$(function(){
	
	//购物车删除商品
	$(".alOrder_handeln").delegate(".alOrder_deletBtn","click",function(){
		
		jConfirm('确认要删除该商品吗？','',function(result){
			if(result==true){
				
			}else{
				
			}
		});
		
		return false;
	});
	//购物车删除商品
});