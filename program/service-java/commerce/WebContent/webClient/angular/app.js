/**
 * Created by Administrator on 2016/12/2.
 */
var app = angular.module("app",[]).run(function(){FastClick.attach(document.body)});

/*
 * ngrepeat循环结束后执行事件
 * this.scope.$on(ng1Finished, function(ngRepeatFinishedEvent){})
 */
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


app.directive('onGetList', function ($timeout) {
	return {
		restrict: 'A',
		link: function(scope, element, attr) {
			var module = attr.onGetList;
		}
	};
});