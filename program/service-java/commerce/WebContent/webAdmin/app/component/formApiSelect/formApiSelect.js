/**
 * Created by Administrator on 2017/7/17.
 * form 选择框组件(通过接口获取数据）
 * formObj : { formDesc : "管理员类型"，optArr : [{optName:'普通管理员',optValue:'1'}],api:"接口",optNameKey:"",optValueKey:""}
 * formModel : 数据绑定
 */

app.directive('formApiSelect',function(){
    return{
        restrict : 'AE',
        templateUrl : 'app/component/formApiSelect/formApiSelect.html',
        replace : true,
        scope : {
            formObj : "=formObj",
            formModel : "=formModel",
        },
        link : function(scope, element, attrs){

            var $select = element.find('select');

            scope.$watch("formObj",function(data){
                if(data == undefined)
                {
                    return
                }
                formApiSelect.getOptionArr();
            });

            var formApiSelect = {

                /**
                 * 获取选项列表
                 * */
                getOptionArr : function () {

                    var self = this;

                    if(scope.formObj.fixParam == undefined)
                    {
                        scope.formObj.fixParam = {};
                    }

                    var requestParam = scope.formObj.fixParam;
                    var params = {};
                    params.type = "post";
                    params.dataApi = scope.formObj.api;

                    params.requestParams = requestParam;
                    params.callback = function(data){

                        var arr = [];
                        if(data.list){
                            arr = data.list
                        }
                        else
                        {
                            arr = data;
                        }

                        scope.formObj.optArr.length = 0;

                        for(var i = 0; i < arr.length; i++)
                        {
                            var itemObj = {};
                            itemObj.optName = arr[i][scope.formObj.optNameKey];
                            itemObj.optValue = arr[i][scope.formObj.optValueKey];

                            scope.formObj.optArr.push(itemObj);
                        }

                        self.setOptionList();
                    }

                    HttpService.ajaxRequest(params);
                },

                /**
                 * dom拼接
                 * */
                setOptionList : function()
                {
                    var self = this;
                    var arr = scope.formObj.optArr;

                    if(arr.length == 0 )
                    {
                        scope.formModel = "";
                    }

                    $select.empty();

                    var html = '';
                    html += '<option value="">==请选择==</option>';

                    for(var i= 0 ; i < arr.length; i++)
                    {
                        if(scope.formModel == arr[i].optValue)
                            html += '<option value="'+arr[i].optValue+'" selected="selected">'+arr[i].optName+'</option>';
                        else
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
                        self.setValue(value);
                    });
                },

                /**
                 * 数据绑定
                 * */
                setValue : function(id)
                {
                    scope.$apply(function(){
                        scope.formModel = id;
                    });
                }
            }

            //formApiSelect.getOptionArr();

        }
    }
})