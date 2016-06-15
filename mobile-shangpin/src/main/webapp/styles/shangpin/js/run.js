$(function(){
	if($(".swiper-wrapper").find(".swiper-slide").length>1){
		var mySwiper = new Swiper('#swiper-container1',{
			loop:true,       //循环切换
			autoplay: 2000,  //自动播放
			autoplayDisableOnInteraction : false, //swiper之后自动切换不会停止
			pagination:'.swiper-pagination', //分页
			paginationClickable: true,
		});
	}

	$(".tab_info").find("li").click(function(e){
		e.preventDefault();
		var $this = $(this);
		var $thisIndex = $this.index();
		$(this).children("a").addClass("curr").parent().siblings().find("a").removeClass("curr");
		
		$("#channelCategoryNO").val($this.attr("id"));
		$("#tabIndex").val($thisIndex);
		var $con=$(".content_info").find(".content_list").eq($thisIndex);
		if($con.html()==''){
		
			tabRun($thisIndex);
		}else{
			$(".content_info").find(".content_list").eq($thisIndex).show().siblings().hide();
		}
		
	});
	$(window).scroll(BottomLoading);  //下拉加载
});


 function tabRun(index){
	 var path = getRootPath();
	 var checkAPP=$('#checkApp').val()
	 $("#start").val("1");
	 var addSelector = '.run_box .content_list';
	  $.ajax({
          url : path+"/run/get/channel",
         data:{
              channelCategoryNO:$("#channelCategoryNO").val()
          },
          dataType:"json",
          success : function(data) {
            $('#loading').hide();
            var hasMore = data.hasMore;
            var isNative=$("#isNative").val();
            if(data.secondfirstCategory != null && data.secondfirstCategory.length > 0){
            	$ul = $("<ul></ul>");
            	$ul.addClass("content_shoes");
          	  	$.each(data.secondfirstCategory,function(index,item_index){
                    var pic = item_index.channelCategoryPic.substring(0,item_index.channelCategoryPic.indexOf('-')) + "-128-100.jpg";
                    $a = $("<a></a>");
                    $li = $("<li></li>");
                    $img = $("<img/>");
                    $em = $("<em></em>");
                    if(isNative=="1"){
                        hrefV = "ShangPinApp://phone.shangpin/actiongochannellist?title="+item_index.channelCategoryName+"&channelid="+item_index.channelCategoryNo;
                    }else{
                        hrefV=path+"/channel/product/list?channelCategoryNo="+item_index.channelCategoryNo+"&channelCategoryName="+item_index.channelCategoryName;
                    
                    }
                    $em.text(item_index.channelCategoryName );
                    $a.attr({"href":hrefV});
                    $img.attr({"src":pic});
                    $a.append($img);
                    $a.append($em);
                    $li.append($a);
                    $ul.append($li);
                });
          	var index= $("#tabIndex").val();
            $(".content_info").find(".content_list").eq(index*1).append($ul)
          }
          if(data.productList != null && data.productList.length > 0){
        	  parData(data,index);
          }
          $(".content_info").find(".content_list").eq(index).show().siblings().hide();
          }
     });
 }
 
 
 
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
  var addSelector = '.run_box .content_list';
  var loadingMsg = '<div id="loading" style="display:block"><img src=' + loading.img + ' /><p>' +loading.msgText + '</p><div>'
 
  if(num==0&& parseInt($("#"+index+'_start').val())>1){
       $("#"+index+'_start').val("1");
       $("#"+index+'_hasMore').val("1");
  }
  var start=$("#"+index+'_start').val();
  if(htmlUrlLength*1!=0){
      if ($(window).scrollTop() + $(window).height() >= $(document).height()-50){
	
          if($('.run_box .content_list').eq(index).find('#loading').length){
		    $('.run_box .content_list').eq(index).find("#loading").remove();
          
          }
		    $('.run_box .content_list').eq(index).append(loadingMsg);
          if(ready==1){
                $('.run_box .content_list').eq(index).find("#loading").show();
                ready=0;
                var path = getRootPath();
                var checkAPP=$('#checkApp').val()
                $.ajax({
                    url : path+"/run/product/more",
                    data:{
                    	channelCategoryNO:$("#channelCategoryNO").val(),
                        start:start
                    },
                    dataType:"json",
                    success : function(data) {
                       $('.run_box .content_list').eq(index).find("#loading").hide();
                      var hasMore = data.hasMore;
                     
                      ready=1;
                      if(data.productList != null && data.productList.length > 0){
                    	  
                    	  parData(data,index);
                          $("#"+index+'_start').val(data.start);
                          $("#"+index+'_hasMore').val(data.hasMore);
                          num++;
                         
                      }
                     
                    },
                    error : function(){
					  $('.run_box .content_list').eq(index).find("#loading").hide();
                    }
                });
             
         }
    }
 }else{
   if($('.run_box .content_list').eq(index).find('#loading').length){
		 $('.run_box .content_list').eq(index).find("#loading").remove();
    }
	$('.run_box .content_list').eq(index).append(loadingMsg);
	$('.run_box .content_list').eq(index).find("#loading p").html('没有更多了');
    $('.run_box .content_list').eq(index).find("#loading").show();
    $('.run_box .content_list').eq(index).find("#loading img").hide();
 }

}

function parData(data,tabIndex){
	 var checkAPP=$('#checkApp').val();
	 var path = getRootPath();
	  $.each(data.productList,function(index,item){
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
          $p.html(item.productName+"&nbsp;"+item.adverTitle);
          $div.addClass("pricestyle");
          var userLv=$("#userLv").val();
          $b.text('￥');
          if(item.isSupportDiscount==1){
              if(userLv=='0002'){
            	  $strong.text(item.sellPrice);
              }else if(userLv=='0003'){
            	  $strong.text( item.platinumPrice);
              }else if(userLv=='0004'){
            	  $strong.text( item.diamondPrice);
              }else{
            	  $strong.text( item.limitedPrice);
              } 
          }else{
        	  $strong.text(item.limitedPrice);
          }
          $b.append($strong);
          $div.append($b);
          $li_text.append($span);
          $li_text.append($p);
          $li_text.append($div);
          $ul.append($li_img);
          $ul.append($li_text);
          $a.append($ul);
          console.log($a);
          $(".content_info").find(".content_list").eq(tabIndex).append($a)
      });
}