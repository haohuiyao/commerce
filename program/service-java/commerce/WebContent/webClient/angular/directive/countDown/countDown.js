
/*
 * 倒计时
 */
app.directive('countDown', function() { 
    return { 
        restrict : 'AE', 
        template : '<span></span>', 
        replace : true,
        scope : {
        	endtime : "=endtime",
        },
        link : function(scope, element, attrs)
        {
        	
        	var html = '';
        	
        	
        	scope.$watch('endtime',function(value){
        		if(value != undefined || value != null)
        		{
					scope.endtime = scope.endtime.replace(/-/g,'\/');
					element.empty();
        			timer();
        			var str = cut();
            		html = str;
            		element.append(html);
        		}
        	})
        	
        	var cut = function()
        	{
        		
        		var startDate = new Date();
        		
	            var endDate = new Date(scope.endtime);
	            
	            var countDown=(endDate.getTime()-startDate.getTime())/1000;
	            
	            var day=parseInt(countDown/(24*60*60));
	            var h=parseInt(countDown/(60*60)%24);
	            var m=parseInt(countDown/60%60);
	            var s=parseInt(countDown%60);
	            
	            if(countDown<=0){
	            	clearInterval(timer)
	              return '<span>'+0+'</span>天<span>'+0+'</span>小时<span>'+0+'</span>分<span>'+0+'</span>秒';
	            }
	            else
	            {
	            	return '<span>'+day+'</span>天<span>'+h+'</span>小时<span>'+m+'</span>分<span>'+s+'</span>秒';
	            }
        	}
        	
        	var p = function(n){
        		 return n<10?'0'+n:n;
        	}
        	
    		var timer = function(){
    			setInterval(function(){
	    			scope.$apply(function(){
	    				element.empty();
	    				var str = cut();
	            		html = str;
	            		element.append(html);
	    			})
	    		},1000);
    		}
    		
        }
    }; 
}); 

//app.directive('countDown', function() { 
//  return { 
//      restrict : 'AE', 
//      template : '<div id="time"><span>1</span>天<span>1</span>小时<span>1</span>分<span>1</span>秒</div>',
//      replace : true,
//      scope : {
//      	lasttime : "=lasttime"
//      },
//      link : function(scope, element, attrs)
//      {
//      	scope.$watch('lasttime', function(newValue)
//          {
//  			leftTimer(2017,11,11,11,11,11);
//          });
//      	
//      	function leftTimer(year,month,day,hour,minute,second){ 
//			 var leftTime = (new Date(year,month-1,day,hour,minute,second)) - (new Date()); //计算剩余的毫秒数 
//			 var days = parseInt(leftTime / 1000 / 60 / 60 / 24 , 10); //计算剩余的天数 
//			 var hours = parseInt(leftTime / 1000 / 60 / 60 % 24 , 10); //计算剩余的小时 
//			 var minutes = parseInt(leftTime / 1000 / 60 % 60, 10);//计算剩余的分钟 
//			 var seconds = parseInt(leftTime / 1000 % 60, 10);//计算剩余的秒数 
//			 days = checkTime(days); 
//			 hours = checkTime(hours); 
//			 minutes = checkTime(minutes); 
//			 seconds = checkTime(seconds); 
//			 setInterval("leftTimer(2017,11,11,11,11,11)",1000); 
//			 document.getElementById("time").innerHTML = days+"天" + hours+"小时" + minutes+"分"+seconds+"秒"; 
//			} 
//			function checkTime(i){ //将0-9的数字前面加上0，例1变为01 
//			 if(i<10) 
//			 { 
//			  i = "0" + i; 
//			 } 
//			 return i; 
//			} 
//      	
//      }
//  }; 
//}); 