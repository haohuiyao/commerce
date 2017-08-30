

app.directive('letterSort', function() { 
    return { 
        restrict : 'AE', 
        template : '<div class="initials">'+
						    '<ul class="side-letter">'+
						       ' <li ng-repeat="item in letterArr" ng-bind="item" ng-click="test(item)" style="color: #f05b29 !important;"></li>'+
						    '</ul>'+
						'</div>', 
        replace : true,
        scope : {
        	letterArr : "=letterArr",
        	scrollId : "@"
//      	test : "@"
        },
        link : function(scope, element, attrs)
        {
        	
        	scope.$watch('letterArr', function(newValue)
            { 
            	scope.letterArr = newValue;
            });
        	
        	scope.test = function(item){
        		var $letter = document.getElementById(item);
                var LetterTop = $letter.offsetTop - 44;
//              if(LetterTop > 41)
//              {
                	$("#"+scope.scrollId).scrollTo({toT:LetterTop});
//              }
        	}

        }
    }; 
}); 