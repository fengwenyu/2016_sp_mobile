/**
 * Created with JetBrains WebStorm.
 * User: MacroXing
 * Date: 13-5-10
 * Time: 下午12:05
 * To change this template use File | Settings | File Templates.
 */
(function (window, $, undefined) {
    var MXTimer = (function () {
        var MXTimer = function (settings) {
                this.init(settings);
            },
            types = ["undefined", "watch", "countdown"];

        MXTimer.prototype = {
            constructor: MXTimer,

            init: function (settings) {
                settings = settings || {};
                this._ = {
                    start: settings.start ? (function() {
                        var _date = new Date(),
                            _start = settings.start;
                        _date.setFullYear(_start.year, _start.month - 1, _start.date);
                        _date.setHours(_start.hour || 0, _start.minute || 0, _start.second || 0, _start.millisecond || 0);

                        return _date.getTime();
                    })() : null,
                    stop: settings.stop ? (function() {
                        var _date = new Date(),
                            _stop = settings.stop;
                        _date.setFullYear(_stop.year, _stop.month - 1, _stop.date);
                        _date.setHours(_stop.hour || 0, _stop.minute || 0, _stop.second || 0, _stop.millisecond || 0);

                        return _date.getTime();
                    })() : null,
                    interval: settings.interval || 1000,
                    timer: null,
                    lefttime: 0
                };
            },

            start: function (fn) {
                var start = this._.start,
                    stop = this._.stop;

                if (stop === null) {
                    if (start === null) {
                        this.type = 1;
                    }
                    else {
                        this.type = 0;
                    }
                }
                else {
                    if (start === null) {
                        this.type = 0;
                    }
                    else {
                        this.type = 2;
                        this._.lefttime = stop - start;
                    }
                }

                if (this.type) {
                    var that = this,
                        execute = function () {
                            var _ = that._;
                            if (that.type === 2) {
                                if (that.lefttime <= 0) {
                                    that.stop();
                                    return false;
                                }
                                _.lefttime -= _.interval;
                                fn && fn.call(that);
                            }
                            else if (that.type === 1) {
                                _.lefttime += _.interval;
                                fn && fn.call(that);
                            }
                        };

                    return this._.timer = window.setInterval(execute, this._.interval);
                }
                else {
                    return false;
                }
            },

            stop: function () {
                if (this._.timer) {
                    window.clearInterval(this._.timer);
                    this._.timer = null;
                }
            },

            info: function () {
                var lefttime = this._.lefttime,
                    ts = lefttime,
                    milliseconds = parseInt(ts % 1000),
                    ts = parseInt(ts / 1000),
                    dates = parseInt(ts / 86400),
                    ts = ts - dates * 86400,
                    hours = parseInt(ts / 3600),
                    ts = ts - hours * 3600,
                    minutes = parseInt(ts / 60),
                    seconds = ts - minutes * 60;

                return {
                    lefttime: lefttime,
                    dates: dates,
                    hours: hours,
                    minutes: minutes,
                    seconds: seconds,
                    milliseconds: milliseconds
                };
            }
        };

        return function (settings) {
            return new MXTimer(settings);
        };
    })();

    $.extend({
        "MXTimer": MXTimer
    });
})(window, jQuery);