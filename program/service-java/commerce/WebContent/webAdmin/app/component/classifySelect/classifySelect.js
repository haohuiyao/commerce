/**
 * Created by Administrator on 2017/7/25.
 * 多级
 */

app.directive('classifySelect',function(){
    return{
        restrict : 'AE',
        templateUrl : 'app/component/classifySelect/classifySelect.html',
        replace : true,
        scope : {
            selId : "=selId",
            classifyArr : "@"

        },
        link : function(scope, element, attrs){

            var getClassifyList = function(){
                var requestParams = {};
                requestParams.parentId = 153;

                var params = {};
                params.type = "post";
                params.dataApi = ProductApi.GET_CLASSIFY_CHILD_LIST;
                params.requestParams = requestParams;
                params.callback = function(data){
                    scope.classifyArr = data.list;
                    var html = '';
                    html+='<option value="">==请选择==</option>';
                    for(var i = 0; i < data.list.length; i++)
                    {
                        html+='<option value="'+data.list[i].categoryId+'">'+data.list[i].name+'</option>';
                    }

                    $("#parentSel").html(html);
                    $("#parentSel").change(function(){
                        var val = $("#parentSel").val();
                        var child = getSelfChildren(val,data.list);
                        getChild(val,child,"parentSel");
                        setValue(val)
                    })
                }
                HttpService.ajaxRequest(params);
            }

            var getChild = function(id,optArr,classId){
                $("#"+classId).parent().parent().parent().parent().nextAll().remove();
                if(optArr.length == 0){
                    return;
                }
                var selId = "parentSel_" + id;
                var html = '';
                html+='<div class="form-li">';
                html+='<div class="form-item-full form-item-container">';
                html+='<div class="form-item">';
                html+='<div class="f form-title">';
                html+='<div></div>';
                html+='</div>';
                html+='<div class="form-input-full">';
                html+=' <select class="option" id="'+selId+'">';
                html+='<option value="">==请选择==</option>';
                for(var i = 0; i < optArr.length; i++)
                {
                    html+='<option value="'+optArr[i].categoryId+'">'+optArr[i].name+'</option>';
                }
                html+='</select>';
                html+='</div>';
                html+='</div>';
                html+='</div>';
                html+='</div>';

                $("#selList").append(html);
                $("#"+selId).unbind().change(function(){
                    var val = $("#"+selId).val();
                    var child = getSelfChildren(val,optArr);
                    getChild(val,child,selId);
                    setValue(val);
                })

            };

            var getSelfChildren = function(id,arr){

                for(var i = 0; i < arr.length; i++)
                {
                    if(arr[i].categoryId == id){
                        return arr[i].childs;
                    }
                }
            }

            getClassifyList();

            var setValue = function(id)
            {
                scope.$apply(function(){
                    scope.selId = id;
                });
            };
        }
    }
})