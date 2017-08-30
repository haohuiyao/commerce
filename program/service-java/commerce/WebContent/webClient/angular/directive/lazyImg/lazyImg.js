

app.directive('lazyImg', function() { 
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
        }
    }; 
}); 