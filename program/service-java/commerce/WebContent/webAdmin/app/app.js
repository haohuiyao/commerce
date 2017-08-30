/**
 * meeno app 
 * ================================
 * make by meeno soft v4.0
 * 
 */

var app = angular.module('app', [
    'ui.router',
    'oc.lazyLoad',
]);

//自定义repeat完成事件
app.directive('onFinishRenderFilters', function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            if (scope.$last === true) {
                var module = 'ng' + attr.onFinishRenderFilters + 'Finished'
                $timeout(function() {
                    scope.$emit(module);
                });
            }
        }
    };
});

app.directive('dynamic', function ($compile) {
    return {
        restrict: 'A',
        replace: true,
        link: function (scope, ele, attrs) {
            scope.$watch(attrs.dynamic, function(html) {
                ele.html(html);
                $compile(ele.contents())(scope);
            });
        }
    };
});