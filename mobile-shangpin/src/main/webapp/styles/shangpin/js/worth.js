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

  var htmlUrlLength = $("#hasMore").val();
  var addSelector = '.alContent';
  var loadingMsg = '<div id="loading" style="display:block"><img src=' + loading.img + ' /><p>' +loading.msgText + '</p><div>'
 
  if(num==0&& parseInt($("#start").val())>1){
       $("#start").val("1");
       $("#hasMore").val("1");
  }
  var start=$("#start").val();
  if(htmlUrlLength*1!=0){
      if ($(window).scrollTop() + $(window).height() >= $(document).height()-50){
    	   if($(addSelector).find('#loading').length){
    		   $(addSelector).find("#loading").remove();
    	    }
    	  $(addSelector).append(loadingMsg);
          if(ready==1){
        	  $(addSelector).find("#loading").show();
                ready=0;
                var path = getRootPath();
                var checkAPP=$('#checkApp').val()
                $.ajax({
                    url : path+"/index/worth/more",
                    data:{
                    	pageIndex:start
                    },
                    dataType:"json",
                    success : function(data) {
                    	console.log(data);
                    	$(addSelector).find("#loading").hide();
                    	var hasMore = data.hasMore;
                     
	                    ready=1;
	                    if(data.worthTitle != null && data.worthTitle.list.length>0){
	                    	parData(data);
	                    	$("#start").val(data.start);
	                        $("#hasMore").val(data.hasMore);
	                        num++;
	                         
	                    }
                     
                    },
                    error : function(){
                    	$(addSelector).find("#loading").hide();
                    }
                });
             
         }
    }
 }else{
   if($(addSelector).find('#loading').length){
	   $(addSelector).find("#loading").remove();
    }
   $(addSelector).append(loadingMsg);
   $(addSelector).find("#loading p").html('没有更多了');
   $(addSelector).find("#loading").show();
   $(addSelector).find("#loading img").hide();
 }

}

function parData(data){
	 var checkAPP=$('#checkApp').val();
	 var path = getRootPath();
	  $.each(data.worthTitle.list,function(index,item){
		  $div=$("<div></div").addClass("list-box");
		  $div1=$("<div></div").addClass("content-list");
          var pic = item.pic.substring(0,item.pic.indexOf('-')) + "-138-186.jpg";
          $a = $("<a></a>").addClass("clr");
          var hrefV;
          if(checkAPP){
              hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="+item.productNo;
            }else{
                hrefV =path+ "/product/detail?productNo="+item.productNo;
                var onclick="_smq.push(['custom',"+ item.productNo+","+","+"]);";
              $a.attr({"onclick":onclick});
            }
          $img = $("<img/>");
          $img.attr({"src":pic,"href":hrefV});
          $div2=$("<div></div").addClass("list-img");
          $div2.append($img);
          $div3=$("<div></div").addClass("list-text");
          $h3=$("<h3></h3").text(item.brandNameEN);
          $p=$("<p></p").text(item.productName);
          $div3.append($h3).append($p);
          $div4=$("<div></div").addClass("price-style");
          
          $b=$("<b></b")
          $strong= $("<strong></strong>");
          $em= $("<em></em>");
          $b.text('￥');
          var userLv=data.userLv;
          if(item.isSupportDiscount==1){
              if(userLv=='0002'){
            	  $strong.text(item.sellPrice);
            	  if(item.sellPrice*1<item.marketPrice){
            		  $em.text('￥'+item.marketPrice)
            	  }
              }else if(userLv=='0003'){
            	  $strong.text( item.platinumPrice);
            	  if(item.platinumPrice*1<item.marketPrice){
            		  $em.text('￥'+item.marketPrice)
            	  }
              }else if(userLv=='0004'){
            	  $strong.text( item.diamondPrice);
            	  if(item.diamondPrice*1<item.marketPrice){
            		  $em.text('￥'+tem.marketPrice)
            	  }
              }else{
            	  $strong.text( item.limitedPrice);
            	  if(item.limitedPrice*1<item.marketPrice){
            		  $em.text('￥'+item.marketPrice)
            	  }
              } 
          }else{
        	  $strong.text(item.limitedPrice);
        	  if(item.limitedPrice*1<item.marketPrice){
        		  $em.text('￥'+item.marketPrice)
        	  }
          }
          $b.append($strong);
          $div4.append($b);
          $div4.append($em);
          $div3.append($div4);
          $a.append($div2);
          $a.append($div3);
          $div1.append($a);
          $div.append($div1);
          $(".alContent").append($div)
      });
}