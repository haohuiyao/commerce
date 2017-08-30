

app.directive('commentHead', function() { 
    return { 
        restrict : 'AE', 
        template : '<div>'+
					'<div class="content-top ub">'+
						'<div class="ub-f1 content-top-lf">'+
							'<div><i ng-class="{\'true\':\'star-act\',\'false\':\'star-item\'}[stars>0]"><i></div>'+
							'<div><i ng-class="{\'true\':\'star-act\',\'false\':\'star-item\'}[stars>1]"><i></div>'+
							'<div><i ng-class="{\'true\':\'star-act\',\'false\':\'star-item\'}[stars>2]"><i></div>'+
							'<div><i ng-class="{\'true\':\'star-act\',\'false\':\'star-item\'}[stars>3]"><i></div>'+
							'<div><i ng-class="{\'true\':\'star-act\',\'false\':\'star-item\'}[stars>4]"><i></div>'+
						'</div>'+
						'<div class="ub-f1 content-top-md"><span ng-bind="stars">5</span>分</div>'+
						'<div class="ub-f1 content-top-rg"><span ng-bind="count">112</span>人评价</div>'+
					'</div>'+
					'<div class="content-tag">'+
						'<span ng-repeat="item in labels"><em ng-bind="item.label">111</em>(<em ng-bind="item.click"></em>)</span>'+
					'</div>'+
				'</div>', 
        replace : true,
        scope : {
        	count : "=count",
        	labels : "=labels",
        	stars : "=stars"
        },
        link : function(scope, element, attrs)
        {
			
        }
    }; 
}); 