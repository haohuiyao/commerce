
/*
 * 背景图片
 */

app.directive('backImg', function() { 
    return { 
        restrict : 'AE', 
        template : '<i class="lazyImg" style="background-image: url({{defaulturl}})"><img ng-src="{{defaulturl}}" /></i>',
        replace : true,
        scope : {
        	imageurl : "=imageurl",
        	defaulturl : "@defaulturl",
        },
        link : function(scope, element, attrs)
        {
        	
        	scope.$watch('imageurl',function(value){
        		
        		if(value == null || value == undefined)
        		{
        			return;
        		}
        		
        		if(localPackage)
		        {
		        	mnWebMain.getPhotoPath(value,function(data){
	        			var img = element.find("img");
	     				var localUrl = JSON.parse(data).data.imgUrl;
	     				
			        	var imageObj = new Image();
			        	imageObj.src = localUrl;
			        	
			        	imageObj.onload = function(){
			        		img.attr("src",localUrl);
			        		element.css("background-image",'url('+localUrl+')');
			        	};
			        	
			        	imageObj.onerror = function(){
			        		img.attr("src",scope.defaulturl);
			        		element.css("background-image",'url('+scope.defaulturl+')');
			        	};
	        		})
		        	return;
		        }
        		
        		var img = element.find("img");
        	
	        	var imageObj = new Image();
	        	imageObj.src = scope.imageurl;
	        	
	        	imageObj.onload = function(){
	        		img.attr("src",scope.imageurl);
	        		element.css("background-image",'url('+scope.imageurl+')');
	        	};
	        	
	        	imageObj.onerror = function(){
	        		img.attr("src",scope.defaulturl);
	        		element.css("background-image",'url('+scope.defaulturl+')');
	        	};
        		
        	})
        }
    }; 
}); 