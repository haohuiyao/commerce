

/*
 * 空列表
 */

app.directive('listEmpty', function() { 
    return { 
        restrict : 'AE', 
        template : '<div class="" ng-class="emptyClass" ng-show="showEmpty">'+
							'<i class="empty-icon">'+
								'<img ng-src="{{emptyIcon}}" />'+
							'</i>'+
							'<div class="empty-title" ng-bind="emptyTitle">您寻找的商品还未上架</div>'+
						'</div>', 
        replace : true,
        scope : {
        	emptyIcon : "@",
        	emptyTitle : "@",
        	limitArr : "=limitArr",
        	emptyClass : "@"
        },
        link : function(scope, element, attrs)
        {
        	scope.$watch('limitArr', function(newValue)
            {
    			if(newValue == undefined || newValue == null || newValue == "")
    			{
    				scope.showEmpty = true;
    			}
    			else
    			{
    				if(newValue.length == 0)
    				{
    					scope.showEmpty = true;
    				}
    				else
    				{
    					scope.showEmpty = false;
    				}
    			}
            });
        }
    }; 
}); 