/**
 * Created by daipengpeng on 2017/7/20.
 * 产品参数模板
 */

app.controller("ProductParamTemplateCtrl",function($scope,$state){
    ProductParamTemplateController.init($scope,$state)
});

var ProductParamTemplateController = {

    scope : null,
    state : null,


    dataModel : {
        paramTemplateList : [],
    },


    /**
     * 初始化
     * */
    init : function($scope,$state){

        this.scope = $scope;

        this.state = $state;

        this.bindEvent();

        this.getProductParamTemplateList();

    },

    /**
     * 获取产品参数模板列表
     * */
    getProductParamTemplateList : function(){

        var self = this;

        PageController.init(self.scope,ProductApi.GET_PARAM_TEMPLATE_LIST,{}, function (data) {

            self.dataModel.paramTemplateList.length = 0;

            PageController.setTotalPage(data.totalCount);

            self.scope.isEmpty = (data.list == 0) ? true : false;

            var list = data.list;
            for(var i = 0; i < list.length; i++)
            {
                var itemObj = {};
                itemObj.index = (PageController.currentPage - 1) * PageController.num + i + 1;
                itemObj.id = list[i].templateId;
                itemObj.createTime = list[i].createTime;
                itemObj.name = list[i].name;
                itemObj.status = list[i].status;

                self.dataModel.paramTemplateList.push(itemObj);
            }

            self.scope.dataModel = self.dataModel;
            self.scope.$apply();

            common.hideLoading();
        })
    },

    /**
     *
     * */
    bindEvent : function(){

        var self = this;

        self.scope.onClickGoTemplateDetail = function(id){
            self.state.go('productParamTemplateDetail',{id:id});
        }

        self.scope.onClickAdd = function(){
            self.state.go('productParamTemplateDetail');
        }
    },


}

