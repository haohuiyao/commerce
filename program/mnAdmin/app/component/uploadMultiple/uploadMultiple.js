/**
 * Created by daipengpeng on 2017/7/21.
 * 上传多张图片
 */

app.directive('uploadMultiple', function() {
    return {
        restrict : 'AE',
        templateUrl : 'app/component/uploadMultiple/uploadMultiple.html',
        replace : true,
        scope : {
            arr : "@",
            url : "=url",
            maxnum : "=maxnum"
        },
        link : function(scope, element, attrs)
        {
            var arr = [];

            scope.$watch('url', function(newValue)
            {
                arr = fn.isEmpty(newValue) ?  [] : newValue;
                scope.arr = arr;
            });

            Oss.initOssKey();

            scope.imgUpload = function(e){
                var num = parseInt(scope.maxnum);

                if(num != 0)
                {
                    if(arr.length >= num)
                    {
                        common.toast(0,'最多上传'+num+'张');
                        return;
                    }
                }

                //common.showProgress("图片正在上传中...");


                for(var i = 0 ; i < e.length ; i ++)
                {
                    var file = e[i];
                    var objKey = Date.parse(new Date()) + file.name;
                    arr.push(objKey)

                    Oss.uploadFile(objKey,file,function(FixResult){
                        var dataUrl = FixResult.fixUrl;

                        for(var n = 0; n < arr.length; n++)
                        {
                            if(arr[n] == dataUrl.split('?')[0].split('/')[dataUrl.split('?')[0].split('/').length-1])
                            {
                                arr[n] = dataUrl;
                            }
                        }
                        scope.$apply(function(){
                            scope.arr = arr;
                            scope.url = arr;
                            //common.hideProgress();
                        });
                    });
                }
            }

            scope.deleteItem = function(index)
            {
                arr.splice(index,1);
                scope.arr = arr;
                scope.url = arr;
            }
        }
    };
});
