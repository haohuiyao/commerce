/**
 * Created by Administrator on 2017/7/12.
 * 左侧导航controller
 */

app.controller("NavController",function($scope){
    NavController.init($scope);
})

var NavController =
{
    scope : null,

    currentType : null,
    lfNavArr : [],

    init : function($scope)
    {
        this.scope = $scope;

        this.getLfNav();

    },

    /**
     *获取左侧导航
     * */
    getLfNav : function(){

        var self = this;

        var requestParams = {};

        var params = {};
        params.type = 'post';
        params.dataApi = 'http://140.206.112.170:9201/sosoapi-web/pass/mock/3/user/getRouteInfo.do';
        params.requestParams = requestParams;
        params.callback = function(data)
        {

            for (var i = 0; i < data.list.length; i++) {
                var childArr = [];
                for(var n = 0; n < data.list[i].childs.length; n++)
                {
                    var item = data.list[i].childs[n];

                    if(item.isNav == 1)
                    {
                        childArr.push(item);
                    }
                }
                data.list[i].childs = childArr;
            }
            self.lfNavArr = data.list;

            self.ngRepeatFinish();

            self.scope.lfNavArr = self.lfNavArr;
            self.scope.$apply();
        }

        HttpService.ajaxRequest(params);
    },

    /*
     * 循环结束之后执行事件
     *
     */
    ngRepeatFinish: function(){

        var self = this;
        var finishKey = 'ng' + (self.lfNavArr.length - 1).toString() + 'Finished';

        self.scope.$on(finishKey, function(ngRepeatFinishedEvent){

            self.bindEvent();
        })
    },

    bindEvent : function()
    {
        $(".nav-li-a").on("click",function(){

            if($(this).siblings('ul').find("li").length == 1){

                $(".nav-li-a").removeClass("active navBlock");
                $(this).addClass("active");
                return;
            }

            $(".nav-li-a").removeClass("active navBlock");
            $(this).addClass("active navBlock");
        })

    }

}
