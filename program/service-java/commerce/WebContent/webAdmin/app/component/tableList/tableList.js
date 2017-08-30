/**
 * Created by Administrator on 2017/7/14.
 */
app.directive('tableList', function() {
    return {
        restrict : 'AE',
        templateUrl : 'app/component/tableList/tableList.html',
        replace : true,
        scope : {
            searchObj : "=searchObj",
            pageOption : "=pageOption"
        },
        link : function(scope, element, attrs)
        {
            getApiClick();
            /**
             * 获取筛选条件
             * */
            function  getApiClick(){
                for(var i = 0; i < scope.searchObj.apiClickSearch.length; i++)
                {
                    var item = scope.searchObj.apiClickSearch[i];

                    var requestParams = {};

                    var params = {};
                    params.type = 'post';
                    params.dataApi = item.api;
                    params.requestParams = requestParams;
                    params.callback = function (data) {

                        var arr = [];
                        for(var i = 0; i < data.list.length; i++)
                        {
                            var itemObj = {}
                            itemObj.optName = data.list[i].name;
                            itemObj.optValue = data.list[i].id;
                            arr.push(itemObj);
                        }

                        item.optArr = arr;
                        scope.$apply();

                    }

                    HttpService.ajaxRequest(params)
                }

            }
        }
    };
});