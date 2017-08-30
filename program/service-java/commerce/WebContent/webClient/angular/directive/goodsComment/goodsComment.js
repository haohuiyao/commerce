

app.directive('goodsComment', function() { 
    return { 
        restrict : 'AE', 
        template : '<div class="evaluate-item ub">'+
							'<div class="evaluate-item-lf ub-f1">'+
								'<span class="item-head-icon"><back-img imageurl="\'11\'" defaulturl="../../../assets/images/default/default-head-icon.jpeg"></back-img></span>'+
							'</div>'+
							'<div class="evaluate-item-rg ub-f1">'+
								'<div class="item-rg-top clear-fix">'+
									'<div class="f" ng-bind="detail.name">玩那个车</div>'+
									'<div class="r" ng-bind="detail.createTime">2017-07-09</div>'+
								'</div>'+
								'<div class="item-score clear-fix">'+
									'<div class="f"><i ng-class="{\'true\':\'star-act\':\'false\':\'star-item\'}[\'true\']"><i></div>'+
									'<div class="f"><i ng-class="{\'true\':\'star-act\':\'false\':\'star-item\'}[\'true\']"><i></div>'+
									'<div class="f"><i ng-class="{\'true\':\'star-act\':\'false\':\'star-item\'}[\'true\']"><i></div>'+
									'<div class="f"><i ng-class="{\'true\':\'star-act\':\'false\':\'star-item\'}[\'true\']"><i></div>'+
									'<div class="f"><i ng-class="{\'true\':\'star-act\':\'false\':\'star-item\'}[\'true\']"><i></div>'+
								'</div>'+
								'<div class="item-tag clear-fix">'+
									'<span ng-repeat="item in detail.label" ng-bind="item">111</span>'+
								'</div>'+
								'<div class="item-desc" ng-bind="detail.comment">khsdjhfj是开发接口返回咖啡黑看国家级反馈借款人和规格看见泛海国际拿话费</div>'+
								'<div class="item-pic clear-fix">'+
									'<div ng-repeat="item in detail.imgs"><back-img imageurl="item" defaulturl="../../../assets/images/default/default-comment-pic.png"></back-img></div>'+
								'</div>'+
							'</div>'+
						'</div>', 
        replace : true,
        scope : {
        	detail : "=detail",
        },
        link : function(scope, element, attrs)
        {
        }
    }; 
}); 