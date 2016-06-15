(function() {
    $.fn.slideLayer_v2 = function(b) {
        var c = {
            direction: "X",
            wrapEl: ".wrap",
            slideEl: ".holder",
            childEl: "li",
            prev: ".prev",
            next: ".next",
            disable: "disabled",
            counter: ".counter",
            countStyle: "dot",
            effect: "both",
            current: 1,
            timer: 300,
            autoplay: 1,
            cycle: 1
        };
        if (b) {
            $.extend(c, b)
        }
        return $(this).each(function() {
            var p = $(this);
            var k = c.current;
            var t = 0,
            q = 0,
            g = 0,
            u = 0;
            var w = Math.ceil(p.find(c.childEl).length);
            var m = p.find(c.childEl);
            var n = p.find(c.slideEl);
            g = p.find(c.wrapEl).width();
            u = p.find(c.wrapEl).height();
            if (c.direction == "X") {
                for (var v = 0; v < m.size(); v++) {
                    t += m.eq(v).width()
                }
                n.width(t)
            } else {
                for (var v = 0; v < m.size(); v++) {
                    q += m.eq(v).height()
                }
                n.height(q)
            }
            if (c.direction == "X") {
                p.find(c.slideEl).css("left", -(g * (k - 1)))
            } else {
                p.find(c.slideEl).css("top", -(u * (k - 1)))
            }
            h();
            switch (c.effect) {
            case "slide":
                d();
                break;
            case "scroll":
                f();
                break;
            case "both":
                d();
                f()
            }
            function f() {
                if (c.direction == "X") {
                    var z = -t + g
                } else {
                    var z = -q + u
                }
                var x = 0;
                n[0].onmousedown = n[0].ontouchstart = y;
                function y(F) {
                    var E, C, B;
                    if (c.autoplay == 1) {
                        j.process()
                    }
                    //var H = [n.position().left, n.position().top];
					var H = [parseInt(n.css("left")), parseInt(n.css("top"))];
                    E = A(F);
                    n[0].ontouchmove = n[0].onmousemove = D;
                    function D(K) {
                        C = A(K);
                        if (c.direction == "X") {
                            var I = (C[0] - E[0]) + H[0];
                            if (Math.abs(C[0] - E[0]) - Math.abs(C[1] - E[1]) > 0) {
                                K.preventDefault();
                                J();
                                n[0].ontouchend = document.onmouseup = G
                            } else {
                                return
                            }
                        } else {
                            var I = (C[1] - E[1]) + H[1];
                            K.preventDefault();
                            J();
                            n[0].ontouchend = document.onmouseup = G
                        }
                        function J() {
                            if (I <= x && I >= z) {
                                if (c.direction == "X") {
                                    n[0].style.left = I + "px"
                                } else {
                                    n[0].style.top = I + "px"
                                }
                            } else {}
                        }
                    }
                    function G(K) {
                        var L, I, J = A(K);
                        if (c.autoplay == 1) {
                            j.process()
                        }
                        if (c.direction == "X") {
                            L = J[0] - E[0];
                            M(K)
                        } else {
                            L = J[1] - E[1];
                            M(K)
                        }
                        function M(N) {
							if (L < -5) {
                                e.process(l)
                            } else {
                                if (L > 5) {
									e.process(o)
                                }
                            }
                        }
                        h();
                        n[0].ontouchmove = n[0].ontouchend = n[0].onmousemove = document.onmouseup = null
                    }
                }
                function A(C) {
                    var B = new Array();
                    B[0] = C.changedTouches ? C.changedTouches[0].clientX: C.clientX;
                    B[1] = C.changedTouches ? C.changedTouches[0].clientY: C.clientY;
                    return B
                }
            }
            var o = function() {
                if (c.autoplay == 1) {
                    j.process()
                }
                if (c.cycle == 1) {
					if (k != 1) {
                        r();
                        return false
                    } else {
                        r();
                        m.eq(w - 1).css("left", -(g * w));
                        m.eq(0).css("left", 0);
                        return false
                    }
                } else {
                    if (k != 1) {
                        r();
                        return false
                    }
                }
            };
            var l = function() {
                if (c.autoplay == 1) {
                    j.process()
                }
                if (c.cycle == 1) {
                    if (k != w) {
                        s();
                        return false
                    } else {
                        s();
                        m.eq(0).css("left", g * w);
                        p.find(c.wrapEl).css("left", 0);
                        return false
                    }
                } else {
                    if (k != w) {
                        s();
                        return false
                    }
                }
            };
            var e = {
                timerid: null,
                action: function(x) {
                    x()
                },
                process: function(x) {
					clearTimeout(e.timerid);
                    e.timerid = setTimeout(function() {
                        e.action(x)
                    },
                    c.timer)
                }
            };
            function d() {
                p.find(c.prev).click(function(x) {
                    if (c.cycle == 1) {
						e.process(o)
                    } else {
                        if (k != 1) {
                            e.process(o)
                        }
                    }
                });
                p.find(c.next).click(function(x) {
                    if (c.cycle == 1) {
                        e.process(l)
                    } else {
                        if (k != w) {
                            e.process(l)
                        }
                    }
                })
            }
            function r() {
                if (c.direction == "X") {
                    p.find(c.slideEl).animate({
                        left: -(g * (k - 2))
                    },
                    c.timer,
					"linear",
                    function() {
						h();
                        p.find(c.wrapEl).find("ul").css("left", -(g * (k - 1)));
                        m.eq(w - 1).css("left", 0)
                    })
                } else {
                    p.find(c.slideEl).animate({
                        top: -(u * (k - 2))
                    },
                    c.timer,
                    function() {})
                }
                k == 1 ? k = w: k--
            }
            function s() {
                if (c.direction == "X") {
                    p.find(c.slideEl).animate({
                        left: -(g * k)
                    },
                    c.timer,
					"linear",
                    function() {
						h();
                        p.find(c.wrapEl).find("ul").css("left", -(g * (k - 1)));
                        m.eq(0).css("left", 0)
                    })
                } else {
                    p.find(c.slideEl).animate({
                        top: -(u * k)
                    },
                    c.timer)
                }
                k == w ? k = 1 : k++
            }
            function h() {
                var z = "",
                x = p.find(c.counter);
                if (x.length > 0) {
                    if (c.countStyle == "dot") {
                        var y = 0;
                        for (y = 1; y <= w; y++) {
                            z += "<li>" + y + "</li>"
                        }
                        x.find("ul").html(z);
                        x.find("li").eq(k - 1).addClass("cur").siblings().removeClass("cur")
                    } else {
                        z = '<span class="cur">' + k + '</span> / <span class="total">' + w + "</span>";
                        x.html(z)
                    }
                    m.eq(k - 1).addClass("cur").siblings().removeClass("cur")
                }
                if (c.cycle != 1) {
                    var B = p.find(c.prev),
                    A = p.find(c.next);
                    B.removeClass(c.disable);
                    A.removeClass(c.disable);
                    if (k == 1) {
                        B.addClass(c.disable)
                    } else {
                        if (k == w) {
                            A.addClass(c.disable)
                        }
                    }
                }
            }
            var j = {
                timeoutId: null,
                performProcessing: function() {
                    l()
                },
                process: function() {
                    clearInterval(j.timeoutId);
                    j.timeoutId = setInterval(function() {
                        j.performProcessing()
                    },
                    5000)
                },
                dispose: function() {
                    clearInterval(j.timeoutId);
                    return
                }
            };
            if (c.autoplay == 1) {
                j.process()
            }
        })
    };
})(Zepto);