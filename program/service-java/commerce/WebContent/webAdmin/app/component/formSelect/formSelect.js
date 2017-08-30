/**
 * Created by Administrator on 2017/7/17.
 * form 选择框组件
 * formObj : { formDesc : "管理员类型"，optionArr : [{optName:'普通管理员',optValue:'1'}]}
 * formModel : 数据绑定
 */

app.directive('formSelect',function(){
    return{
        restrict : 'AE',
        templateUrl : 'app/component/formSelect/formSelect.html',
        replace : true,
        scope : {
            formObj : "=formObj",
            formModel : "=formModel",
            reset : "=reset"
        },
        link : function(scope, element, attrs){

            var $select = element.find('select');

            //scope.$watch("formObj.optionArr",function(data){
            //    var arr = data ? data : [];
            //    setOptionList(arr);
            //});

            scope.$watch("reset",function(data){
                if(data){
                    scope.reset = false
                    setOptionList(scope.formObj.optionArr);
                }
            })

            var setOptionList = function(arr)
            {
                if(arr.length == 0 )
                {
                    scope.formModel = "";
                }

                $select.empty();

                var html = '';
                html += '<option value="">==请选择==</option>';

                for(var i= 0 ; i < arr.length; i++)
                {
                    html += '<option value="'+arr[i].optValue+'">'+arr[i].optName+'</option>';
                }

                $select.append(html);

                scope.$watch("formModel",function(data){
                    if(data != undefined)
                    {
                        $select.val(data);
                    }
                });

                $select.on("change",function(){
                    var value = $(this).val();
                    setValue(value);
                });

            };

            var setValue = function(id)
            {
                scope.$apply(function(){
                    scope.formModel = id;
                });
            };

        }
    }
})