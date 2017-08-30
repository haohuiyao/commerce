/**
 * 自定义富文本
 * 
 */
app.directive('uiKindeditor', function ($timeout) {
    return {
        restrict: 'EA',
        require: '?ngModel',
        link: function(scope, element, attrs, ctrl) 
        {
        	var fexUe = 
        	{
        		initContent : null,
        		editor : null,
        		
        		initEditor : function()
        		{
        			fexUe.editor = KindEditor.create(element[0],{
        				resizeType : 1,
						allowPreviewEmoticons : false,
						allowImageUpload : false,
						width: '100%',
						height : "600px",
						items : [
							'fontname',
							'fontsize', 
							'|', 
							'forecolor', 
							'hilitecolor', 
							'bold', 
							'italic', 
							'underline',
							'removeformat', 
							'|', 
							'justifyleft', 
							'justifycenter', 
							'justifyright', 
							'insertorderedlist',
							'insertunorderedlist',
							'uploadImage'
						],
						afterChange : function ()
						{
                            ctrl.$setViewValue(this.html());
                        }
        			});
        			
        			var inputfile = '<input type="file" multiple="multiple" style="opacity:0">';
        			$(".ke-icon-uploadImage").append(inputfile);
        		},
        		
        		setContent : function(content)
        		{
        			if(this.editor)
        			{
        				this.editor.html(content);
        			}
        		}
        	}
        	
        	if (!ctrl) 
        	{
                return;
            }
        	
        	fexUe.initContent = ctrl.$viewValue;
        	
        	ctrl.$render = function () {
                fexUe.initContent = ctrl.$isEmpty(ctrl.$viewValue) ? '' : ctrl.$viewValue;
                fexUe.setContent(fexUe.initContent);
            };
        	
        	fexUe.initEditor();

			Oss.initOssKey();

			var arr = [];//上传的图片数组


        	$(".ke-icon-uploadImage input").off("change").on("change",function(){
        		arr = [];
        		var selectedFile = $(this).get(0).files;

        		var k = 0;
        		for(var i = 0 ; i < selectedFile.length ; i ++)
				{
					var file = selectedFile[i];
	            	var objKey = Date.parse(new Date()) + file.name;
	            	arr.push(objKey);//将选择图片的objKey依次存入数组，用于后面的排序

					//上传图片拿到url
	            	Oss.uploadFile(objKey,file,function(FixResult){

			            var dataUrl = FixResult.fixUrl;
			            //排序
			            for(var n = 0; n < arr.length; n++)
			            {
							//根据数组中的objectkey和图片地址，对图片进行排序
			            	if(arr[n] == dataUrl.split('?')[0].split('/')[dataUrl.split('?')[0].split('/').length-1])
			            	{
			            		k++;
			            		arr[n] = dataUrl;
			            	}
			            }

			            if(k == selectedFile.length)
			            {
			            	var html = '';
	        				for(var n = 0; n < arr.length; n++)
							{
								html += '<img style="width: 100%" src="'+ arr[n] +'" />';
							}
							fexUe.editor.insertHtml(html);
			            }
	            	});
				}
        	});
        }
    };
});