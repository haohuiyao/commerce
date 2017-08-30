/**
 * directive
 * upload img
 * 
 */

app.directive('uploadSingle', function() {
    return { 
        restrict : 'AE', 
        templateUrl : 'app/component/uploadSingle/uploadSingle.html',
        replace : true,
        scope : {
        	upLoadCover : "@",
        	url : "=url"
        },
        link : function(scope, element, attrs)
        {
        	scope.$watch('url', function(newValue)
            {
            	scope.upLoadCover = fn.isEmpty(newValue) ?  'build/images/public/default.png' : newValue;
            });

			Oss.initOssKey();
			
			scope.imgUpload = function(e){
				
            	var file = e[0];
            	var objKey = Date.parse(new Date()) + file.name;
            	
            	//common.showProgress("图片正在上传中...");
            	
            	/*
            	 * 上传成功之前显示
            	 */
  	      		var reader = new FileReader();
				reader.readAsDataURL(e[0]);
				
				reader.onload = function(evt)
				{
					scope.$apply(function(){
						scope.upLoadCover = evt.target.result;
		            });
				}
            	
            	Oss.uploadFile(objKey,file,function(FixResult){
		            var dataUrl = FixResult.fixUrl;	
		            scope.$apply(function(){
						scope.url = dataUrl;
						//common.hideProgress();
		            });
            	});
            }
        }
    }; 
}); 