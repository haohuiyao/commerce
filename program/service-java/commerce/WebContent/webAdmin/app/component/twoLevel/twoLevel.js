/**
 * Created by daipengpeng on 2017/7/21.
 * 封装二级联动
 */

app.directive('twoLevel',function(){
    return{
        restrict : 'AE',
        templateUrl : 'app/component/twoLevel/twoLevel.html',
        replace : true,
        scope : {
            levelObj : "=levelObj",
            firstLevel : "=firstLevel",
            secondLevel : "=secondLevel",
        },
        link : function(scope, element, attrs){

            var twoLevel = {

                /**
                 * 获取一级菜单
                 * */
                getFirstLevel : function () {

                    var self = this;

                    var requestParam = {};
                    var params = {};
                    params.type = "post";
                    params.dataApi = scope.levelObj.api;

                    params.requestParams = $.extend(requestParam,scope.levelObj.firstLevel.fixParams);
                    params.callback = function(data){

                        scope.levelObj.firstLevel.optArr.length = 0;

                        for(var i = 0; i < data.list.length; i++)
                        {
                            var itemObj = {};
                            itemObj.optName = data.list[i].name;
                            itemObj.optValue = data.list[i].categoryId;

                            scope.levelObj.firstLevel.optArr.push(itemObj);
                        }

                        self.setFirstLevelOptionList("firstLevel");
                    }

                    HttpService.ajaxRequest(params);
                },

                /**
                 * 获取二级菜单
                 * */
                getSecondLevel : function (id) {

                    var self = this;

                    //如果没有选择一级，清空二级选项列表
                    if(id == null || id == ""){
                        scope.levelObj.secondLevel.optArr.length = 0;
                        self.setSecondLevelOptionList();
                        return;
                    }

                    var requestParam = {
                        parentId : id
                    };
                    var params = {};
                    params.type = "post";
                    params.dataApi = scope.levelObj.api;
                    params.requestParams = $.extend(requestParam,scope.levelObj.secondLevel.fixParams);
                    params.callback = function(data){

                        scope.levelObj.secondLevel.optArr.length = 0;

                        for(var i = 0; i < data.list.length; i++)
                        {
                            var itemObj = {};
                            itemObj.optName = data.list[i].name;
                            itemObj.optValue = data.list[i].categoryId;

                            scope.levelObj.secondLevel.optArr.push(itemObj);
                        }

                        self.setSecondLevelOptionList();
                    }

                    HttpService.ajaxRequest(params);
                },

                /**
                 * 设置选项
                 * */
                setFirstLevelOptionList : function()
                {
                    var self = this;
                    var $select = element.find(".firstLevel");
                    var arr = scope.levelObj.firstLevel.optArr;

                    if(arr.length == 0 )
                    {
                        scope.firstLevel = "";
                    }

                    $select.empty();

                    var html = '';
                    html += '<option value="">==请选择==</option>';

                    for(var i= 0 ; i < arr.length; i++)
                    {
                        if(scope.firstLevel == arr[i].optValue)
                        {
                            self.getSecondLevel(scope.firstLevel);
                            html += '<option value="'+arr[i].optValue+'" selected="selected">'+arr[i].optName+'</option>';
                        }
                        else
                            html += '<option value="'+arr[i].optValue+'">'+arr[i].optName+'</option>';
                    }

                    $select.append(html);

                    $select.on("change",function(){
                        var value = $(this).val();
                        self.setValue("firstLevel",value)
                    });

                    /**
                     * 监听变化
                     * */
                    scope.$watch("firstLevel",function(data){
                        //设置选中值
                        $select.val(data);
                        //请求二级数据
                        self.getSecondLevel(data);
                    })
                },

                setSecondLevelOptionList : function()
                {
                    var self = this;
                    var $select = element.find(".secondLevel");
                    var arr = scope.levelObj.secondLevel.optArr;

                    if(arr.length == 0 )
                    {
                        scope.secondLevel = "";
                    }

                    $select.empty();

                    var html = '';
                    html += '<option value="">==请选择==</option>';

                    for(var i= 0 ; i < arr.length; i++)
                    {
                        if(scope.secondLevel == arr[i].optValue)
                        {
                            html += '<option value="'+arr[i].optValue+'" selected="selected">'+arr[i].optName+'</option>';
                        }
                        else
                            html += '<option value="'+arr[i].optValue+'">'+arr[i].optName+'</option>';
                    }

                    $select.append(html);

                    $select.on("change",function(){
                        var value = $(this).val();
                        self.setValue("secondLevel",value)
                    });

                    /**
                     * 监听变化
                     * */
                    scope.$watch("secondLevel",function(data){
                        //设置选中值
                        $select.val(data);
                    })
                },

                setValue : function(level,value){
                    scope.$apply(function(){
                        scope[level] = value;
                    });
                },
            }

            twoLevel.getFirstLevel();
        }
    }
})
