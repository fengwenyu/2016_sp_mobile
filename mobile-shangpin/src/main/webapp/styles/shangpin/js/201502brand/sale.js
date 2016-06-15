

$(window).scroll(function(){
	topFixed('.topFix');
});

var page='pagenavi';
var mslide='slider';
var as=$('#pagenavi a');
var Slider=new TouchSlider(
{
	id:mslide,
	'auto':'-1',
	fx:'ease-out',
	direction:'left',
	speed:600,
	timeout:5000,
	'before':function(index){
			as[this.p].className='';
			as[index].className='active';
		    this.p=index;

	}
});

Slider.page = page;
Slider.p = 0;

for(var i=0;i<as.length;i++){
	(function(){
		var j=i;
		as[j].Slider = Slider;
		as[j].onclick=function(){
			this.Slider.slide(j);
			return false;
		}
	})();

}

