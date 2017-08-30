

app.directive('msgCode', function() { 
    return { 
        restrict : 'AE', 
        template : '<button class="msgCode" ng-click="clickGetCode()" value="">获取验证码</button>', 
        replace : true,
        scope : {
        	codeApi : "@",
        	phone : "=phone",
        	type : "=type",
//      	judgeparams : "&judgeParams"
//      	method : "&onSwitch",
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
		 			var inText = ''+codeTime+'s后重新获取';
		 			btnId.attr("disabled",true)
					btnId.text(inText)
					codeTime--;
					
					setTime();
				}
        	}
        	
        	scope.clickGetCode = function(){
        		
        		var bool = utility.judgePhone(scope.phone);
        		
        		if(!bool)
        		{
        			return;
        		}
        		
        		codeWaiteTime();
        		
        		/*
        		 * ajaxRequest
        		 */
        		var params = {};
        		params.phone = scope.phone;
        		params.vType = scope.type;
				$service.httpRequest("post",API_GET_CODE,params,function(data){
//					mnWebMain.showProgressDialog(JSON.stringify(data))
				},function(){
					btnId.attr("disabled",false)
					btnId.text("免费获取")
					codeTime = 60;
				})
				
        	}
        }
    }; 
}); 