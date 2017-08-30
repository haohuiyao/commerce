

app.directive('searchItem', function() { 
    return { 
        restrict : 'AE', 
        template : '<div class="search" >'+
						'<i class="search-icon" ng-click="search()"></i>'+
					    '<input type="text" placeholder="请输入产品名称、品牌、机型" ng-model="inputValue" >'+
					    '<i class="search-clear hiddenIm" ng-click="clearInput($event)"></i>'+
					'</div>', 
        replace : true,
        scope : {
        	inputValue : "=inputValue",
        	clearMethod : "&"
        },
        link : function(scope, element, attrs)
        {
        	scope.$watch('inputValue', function(newValue)
            {
    			if(newValue == "" || newValue == undefined)
    			{
    				$(".search").addClass('search-center');
    				$(".search-clear").addClass("hiddenIm")
    			}
    			else
    			{
    				$(".search").removeClass('search-center')
    				$(".search-clear").removeClass("hiddenIm")
    			}
            });
        	
//      	scope.toSearchPage = function(){
//      		 var baseParam = {
//	                url: URL_SEARCH_INDEX,
//	                isHideNavBar: 0,
//	                titleType: 1
//	            };
//	             var rightParam = [{"type" : 0,"param" : "取消"}];
//	            mnWebMain.openWebViewController(baseParam,[],[],rightParam);
//      	}
        	scope.search = function(){
        		console.log(123)
        	}
        	scope.clearInput = function($event){
        		$event.stopPropagation();
        		scope.inputValue = "";
        		setTimeout(function(){
        			scope.clearMethod();
        		},300)
        	}
        }
    }; 
}); 