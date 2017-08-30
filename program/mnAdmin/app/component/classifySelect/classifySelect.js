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
        },
        link : function(scope, element, attrs){

            var getClassifyList = function(parentId,elementId){
                var requestParams = {};
                requestParams.parentId = parentId;

                var params = {};
                params.type = "post";
                params.dataApi = ProductApi.GET_CLASSIFY_LIST;
                params.requestParams = requestParams;
                params.callback = function(data){
                    $("#"+elementId).parent().siblings("ul").empty();
                    var html = '';
                    var list = data.list;
                    for(var i = 0; i < list.length; i++){
                        var id = "classify_li_" + list[i].categoryId;
                        html+='<div>';
                        html+='<li class="classify-li" style="height: 40px;line-height: 40px">';
                        html+='<span class="unSelected mgt-1 selBtn" data-id="'+list[i].categoryId+'"></span>'
                        html+='<span class="classify-li-name" id="'+id+'" data-id="'+list[i].categoryId+'">'+list[i].name+'</span>';
                        html+= '</li>';
                        html+='<ul class="mgl-2"></ul>';
                        html+='</div>';
                    }
                    $("#"+elementId).parent().siblings("ul").append(html);

                    /*获取下一级*/
                    $(".classify-li-name").on('click',function(){
                        getClassifyList($(this).attr("data-id"),$(this).attr("id"));
                    })

                    /*选择框点击事件*/
                    $(".selBtn").unbind().on('click',function(){

                        $(".selBtn").removeClass("hasSelected").addClass("unSelected");
                        $(this).removeClass("unSelected").addClass("hasSelected");

                        getSel();
                    })

                }
                HttpService.ajaxRequest(params);
            }


            /*获取选择的分类*/
            var getSel = function(){

                $(".selBtn").each(function () {
                    if($(this).hasClass("hasSelected"))
                    {
                        var id = $(this).attr("data-id");
                        scope.$apply(function(){
                            scope.selId = id;
                        })
                    }
                })

            }

            /*获取一级分类列表*/
            scope.getClassify = function(elementId){
                getClassifyList('',elementId);
            }
        }
    }
})