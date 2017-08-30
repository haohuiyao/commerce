

app.directive('msgCode', function() { 
    return { 
        restrict : 'AE', 
        templateUrl : 'angular/directive/msgCode/msgCode.html', 
        replace : true,
        scope : {
        	codeApi : "@",
        	params : "=params",
        	judgeparams : "&judgeParams"
        },
        link : function(scope, element, attrs)
        {
        	var btnId = element;
        	var codeTime = 60;
        	
        	var setTime = function(){
        		setTimeout(function() {
					codeWaiteTime();
				},1000)
        	}
        	
        	var codeWaiteTime = function(){
        		if (codeTime == 0) 
				{
					btnId.attr("disabled",false)
					btnId.text("免费获取")
					codeTime = 60;
				}
				else
				{
					console.log(codeTime)
		 			var inText = '（'+codeTime+'s）';
		 			btnId.attr("disabled",true)
					btnId.text(inText)
					codeTime--;
					
					setTime();
				}
        	}
        	
        	scope.clickGetCode = function(){
        		
        		var bool = scope.judgeparams();
        		
        		if(!bool)
        		{
        			return;
        		}
        		
        		codeWaiteTime();
        		
        		/*
        		 * ajaxRequest
        		 */
        		
//				var data = {};
//				data.Data = JSON.stringify(scope.params);
//				
//				httpRequest("post",scope.codeApi,data,function(data){
//					alert(JSON.stringify(data))
//				})
        	}
        }
    }; 
}); 