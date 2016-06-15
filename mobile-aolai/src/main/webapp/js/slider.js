function slider(content, ul, lia, promise, numbers) {
    /*新品热卖*/
    var contentLeft = $(content).offset().left;
    var liw = $(lia).outerWidth(true);
    var len = $(lia).length;
    var leftwidth = -liw * len + numbers;
    $(ul).width(liw * len);
    var $gotouch = $(ul);
    var gogo = document.getElementById($gotouch[0].id);
    var left = -1;
    var right = 1;
    var direction = left, startLeft = 0, startX = 0, overX = 0; startY = 0, overY = 0, line = 0, count = 0, metion, speed = 0, time = 0;
    function timeadd() { time++ }
    gogo.ontouchstart = function (event) {
        clearInterval(metion);
        startLeft = $gotouch.offset().left;
        touch = event.touches[0];
        startX = touch.clientX;
        startY = touch.clientY;
        overX = 0;
    };
    gogo.ontouchmove = function (event) {
        moveX = startLeft - contentLeft;
        overX = event.changedTouches[0].clientX;
        overY = event.changedTouches[0].clientY;
        if (Math.abs(overY - startY) > Math.abs(overX - startX)) {
            return;
        };
        event.preventDefault();
        endX = overX - startX;
        if (endX > 0) {
            direction = right;
        }
        else if (endX < 0) {
            direction = left;
        };
		
        //$gotouch.css("left", moveX + endX + 10);
    };
    if (promise != '') {
        for (var linun = 0; linun < len; linun++) {
            $(promise).find("ul").append("<li></li>");
        };
        $(promise).find("ul li:eq(0)").addClass("cur")/*.width(55)*/;
        gogo.ontouchend = function (event) {
            if (overX == 0) { direction = 0 }
            else {
                autoslider('fast');
                direction = left;
                metion = setInterval(function () { return autoslider(500) }, 5000);
            }
        };
        var metion = setInterval(function () { return autoslider(500) }, 5000);
    }
    else {
        gogo.ontouchend = function (event) {
            if (overX == 0) { direction = 0; };
            if (direction == left) {
                if (parseInt($gotouch.css("left")) <= leftwidth) {
                    $gotouch.animate({ left: leftwidth + 'px' }, "slow");
                }
            }
            else if (direction == right) {
                if (parseInt($gotouch.css("left")) >= 0) {
                    $gotouch.animate({ left: "0" }, "slow");
                }
            };
        };
    };
    function autoslider(speed) {
        if (direction == left) {
            count++;
            if (count >= len) { count = 0; $gotouch.animate({ left: '0px' }, 100) }
            else { $gotouch.animate({ left: liw * count * -1 }, speed) }
        }
        else if (direction == right) {
            count--;
            if (count <= -1) { count = len - 1; $gotouch.animate({ left: -liw * (len - 1) }, 100) }
            else { $gotouch.animate({ left: liw * count * -1 }, speed) }
        }
        $(promise).find("ul li").removeClass("cur");
        $(promise).find("ul li").eq(count).addClass("cur");
    };
};