$(function(){
	
	$(window).scroll(BottomLoading);  //下拉加载
});

 
 
var ready=1;
var num = 0;
//下拉加载
function BottomLoading(){
  var loading = {
      img : '../styles/shangpin/images/201502brand/loading.gif',
      msgText : '正在加载中...',
  };
  var index= $("#tabIndex").val();
  var htmlUrlLength = $("#"+index+'_hasMore').val();
  var addSelector = '.alcontent';
  var loadingMsg = '<div id="loading" style="display:block"><img src=' + loading.img + ' /><p>' +loading.msgText + '</p><div>'
 
  if(num==0&& parseInt($("#"+index+'_start').val())>1){
       $("#start").val("1");
       $("#hasMore").val("1");
  }
  var start=$("#start").val();
  if(htmlUrlLength*1!=0){
      if ($(window).scrollTop() + $(window).height() >= $(document).height()-50){
	
       /*   if($('.run_box .content_list').eq(index).find('#loading').length){
		    $('.run_box .content_list').eq(index).find("#loading").remove();
          */
          //}
      addSelector.append(loadingMsg);
          if(ready==1){
        	  addSelector.find("#loading").show();
                ready=0;
                var path = getRootPath();
                var checkAPP=$('#checkApp').val()
                $.ajax({
                    url : path+"/index/worth/more",
                 
                    dataType:"json",
                    success : function(data) {
                    	addSelector.find("#loading").hide();
	                      var hasMore = data.hasMore;
	                     
	                      ready=1;
	                      if(data.productList != null && data.productList.length > 0){
	                    	  
	                    	  parData(data,index);
	                          $("#start").val(data.start);
	                          $("#hasMore").val(data.hasMore);
	                          num++;
	                         
	                      }
	                     
	                    },
                    error : function(){
                    	addSelector.find("#loading").hide();
                    }
                });
             
         }
    }
 }else{
 
   	addSelector.append(loadingMsg);
	addSelector.find("#loading p").html('没有更多了');
	addSelector.find("#loading").show();
	addSelector.find("#loading img").hide();
 }

}

function parData(data,tabIndex){
	 var checkAPP=$('#checkApp').val();
	 var path = getRootPath();
	  $.each(data.productList,function(index,item){
		 // <div class="list-box"> 
		  $div_lb=$("<div></div>");
		  $div_lb.addClass("list-box");
          var pic = item.pic.substring(0,item.pic.indexOf('-')) + "-78-105.jpg";
          $a = $("<a></a>");
          $ul = $("<ul></ul>");
          $li_img = $("<li></li>");
          $img = $("<img/>");
          $li_text = $("<li></li>");
          $span = $("<span></span>");
          $p= $("<p></p>");
          $div= $("<div></div>");
          $b= $("<b></b>");
          $strong= $("<strong></strong>");
          if(checkAPP){
              hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="+item.productNo;
            }else{
                hrefV =path+ "/product/detail?productNo="+item.productNo;
                var onclick="_smq.push(['custom',"+ item.productNo+","+","+"]);";
              $a.attr({"onclick":onclick});
            }
          $ul.addClass("content_shoes_list");
          $li_img.addClass("list_img");
          $img.attr({"src":pic});
          $li_img.append($img);
          $a.attr({"href":hrefV});
          $li_text.addClass("list_text");
          $span.text(item.brandEnName);
          $p.html(item.productName);
          $div.addClass("pricestyle");
          var userLv=$("#userLv").val();
          $b.text('￥');
          $em=$("<em></em>");
          if(item.isSupportDiscount==1){
              if(userLv=='0002'){
            	  $strong.text(item.sellPrice);
            	  if(item.sellPrice*1<item.marketPrice){
            		  $em.text(item.marketPrice);
            	  }
              }else if(userLv=='0003'){
            	  $strong.text( item.platinumPrice);
            	  if(item.platinumPrice*1<item.marketPrice){
            		  $em.text(item.marketPrice);
            	  }
              }else if(userLv=='0004'){
            	  $strong.text( item.diamondPrice);
            	  if(item.diamondPrice*1<item.marketPrice){
            		  $em.text(item.marketPrice);
            	  }
              }else{
            	  $strong.text( item.limitedPrice);
            	  if(item.limitedPrice*1<item.marketPrice){
            		  $em.text(item.marketPrice);
            	  }
              } 
          }else{
        	  $strong.text(item.limitedPrice);
        	  if(item.limitedPrice*1<item.marketPrice){
        		  $em.text(item.marketPrice);
        	  }
          }
          $b.append($strong);
          $div.append($b);
          $div.append($em);
          $li_text.append($span);
          $li_text.append($p);
          $li_text.append($div);
          $ul.append($li_img);
          $ul.append($li_text);
          $a.append($ul);
          $div_lb.append($a)
      });
}