

app.directive('iconItem', function() { 
    return { 
        restrict : 'AE', 
        template : '<i style="background: url();background-position: center;background-size: cover;background-repeat: no-repeat;display: block;width: 100%;height: 100%;"></i>',
//      templateUrl : '../../../angular/directive/iconItem/iconItem.html', 
        replace : true,
        scope : {
        	iconurl : "=iconurl",
        },
        link : function(scope, element, attrs)
        {
        	scope.$watch('iconurl', function(newValue)
            { 
            	if(newValue == null || newValue == "")
            	{
            	}
            	else
            	{
            		var img = "url("+scope.iconurl+")";
            		element.css('background-image',img);
            	}
            });
        }
    }; 
}); 