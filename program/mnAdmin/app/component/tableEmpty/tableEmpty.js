/**
 * =========================================================================================================================
 * {demo} 
 * {directive name} table 为空显示
 * {author} liang
 * =========================================================================================================================
 */

app.directive('tableEmpty', function() { 
    return { 
        restrict : 'AE', 
        templateUrl : 'app/component/tableEmpty/tableEmpty.html',
        replace : true,
        scope : {
        	isempty : "=isempty",
        },
        link : function(scope, element, attrs)
        {
        	scope.$watch("isempty",function(data){
        		
        		if(scope.isempty)
        		{
        			element.show();
        		}
        		else
        		{
        			element.hide();
        		}
			});
        }
    }; 
}); 