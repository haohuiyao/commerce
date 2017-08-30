/**
 * Created by daipengpeng on 2017/7/24.
 * 产品货源
 */

app.controller("ProductSourceCtrl",function($scope,$state){
    ProductSourceController.init($scope,$state);
});


var ProductSourceController = {

    scope : null,
    state : null,

    dataModel : {
        sourceList : [],
        sourceName : "",
        sourceId : ""
    },

    formObj : {
        sourceName : {
            formDesc : "货源名称：",
            formType : "text",
            formDisabled : false,
        }
    },

    init : function($scope,$state){

        this.scope = $scope;

        this.state = $state;

        this.initDataModel();

        this.bindEvent();

        this.getProductSourceList();

    },

    initDataModel : function(){
        var self = this;
        self.scope.formObj = self.formObj;
    },

    /**
     * 获取货源列表
     * */
    getProductSourceList : function(){

        var self = this;

        PageController.init(self.scope,ProductApi.GET_SOURCE_LIST,{}, function (data) {

            self.dataModel.sourceList.length = 0;

            PageController.setTotalPage(data.totalCount);

            self.scope.isEmpty = (data.list == 0) ? true : false;

            var list = data.list;
            for(var i = 0; i < list.length; i++)
            {
                var itemObj = {};
                itemObj.index = (PageController.currentPage - 1) * PageController.num + i + 1;
                itemObj.id = list[i].id;
                itemObj.sourceName = list[i].sourceName;
                itemObj.status = list[i].status;

                self.dataModel.sourceList.push(itemObj);
            }

            self.scope.dataModel = self.dataModel;
            self.scope.$apply();

            common.hideLoading();
        })
    },

    /**
     * 绑定事件
     * */
    bindEvent : function(){

        var self = this;

        /**
         * 添加货源
         * */
        self.scope.onClickAddSource = function(){
            self.dataModel.modalTitle = "添加货源";
            self.reSetFormData();
            common.showModal("editProductSource");
        };

        /**
         * 修改货源
         * */
        self.scope.onClickModSource = function(item){

            self.reSetFormData();
            self.dataModel.modalTitle = "修改货源";
            self.dataModel.sourceId = item.id;
            self.dataModel.sourceName = item.sourceName;
            self.scope.dataModel = self.dataModel;
            common.showModal("editProductSource");
        };

        /**
         * 删除货源
         * */
        self.scope.onClickDelSource = function(id){
            common.confirm(ProductText.CONFIRM_DEL_SOURCE, function () {
                var params = {};
                var requestParam = {};
                requestParam.sourceId = id;

                params.type = "post";
                params.dataApi = ProductApi.DEL_SOURCE;
                params.requestParams = requestParam;
                params.callback = function(data){
                    common.toast(1,ProductText.SUCCESS_DEL_SOURCE);
                    PageController.callApi();
                }

                HttpService.ajaxRequest(params);
            })
        };

        /**
         * 提交货源编辑
         * */
        self.scope.submitEditSource = function(){
            var formData = self.getFormData();
            if(formData){
                if(fn.isEmpty(self.dataModel.sourceId))
                {
                    var params = {};
                    params.type = "post";
                    params.dataApi = ProductApi.ADD_SOURCE;
                    params.requestParams = formData;
                    params.callback = function(){
                        common.hideModal("editProductSource");
                        common.toast(1,ProductText.SUCCESS_ADD_SOURCE);
                        PageController.callApi();
                    }
                }
                else
                {
                    formData.id = self.dataModel.sourceId;
                    var params = {};
                    params.type = "post";
                    params.dataApi = ProductApi.MOD_SOURCE;
                    params.requestParams = formData;
                    params.callback = function(){
                        common.hideModal("editProductSource");
                        common.toast(1,ProductText.SUCCESS_MOD_SOURCE);
                        PageController.callApi();
                    }
                }

                HttpService.ajaxRequest(params);
            }
        }
    },

    /**
     * 获取form表单数据
     * */
    getFormData : function () {

        var self = this;

        var params = {};
        params.sourceName = self.dataModel.sourceName;
        var dataArr = [
            {data:params.sourceName,errDesc:ProductText.WARN_SOURCE_NAME_EMPTY},
        ]
        if(fn.formNoEmpty(dataArr))
            return params;
        else
            return false;
    },

    /**
     * 重置form表单数据
     * */
    reSetFormData : function () {

        var self = this;

        self.dataModel.sourceName = "";
        self.dataModel.sourceId = "";

        self.scope.dataModel = self.dataModel;
    },
}