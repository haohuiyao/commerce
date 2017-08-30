/**
 * Created by Administrator on 2017/7/23.
 * 产品二级分类列表
 */

app.controller('ProductSecondClassifyCtrl',function($scope,$state,$stateParams){
    ProductSecondClassifyController.init($scope,$state,$stateParams);
})

var ProductSecondClassifyController = {

    scope : null,
    state : null,

    parentCategoryId : null,

    dataModel : {
        classifyList : [],
        classifyName : "",
        classifyAbbreviation : "",
        classifyImage : "",
        modalTitle : "",
        parentId : "",
        template : "",
        classifyId : "",
    },

    formObj : {
        classifyName : {
            formDesc : "分类名称：",
            formType : "text",
            formDisabled : false,
        },
        classifyAbbreviation : {
            formDesc : "分类缩写：",
            formType : "text",
            formDisabled : false,
        },
        template : {
            formDesc : "参数模板：",
            optArr : [],
            api:ProductApi.GET_PARAM_TEMPLATE_LIST,
            optNameKey:"name",
            optValueKey:"templateId"
        }
    },

    /**
     * 初始化
     * */
    init : function($scope,$state,$stateParams){

        common.showLoading();

        this.scope = $scope;

        this.state = $state;

        if(!fn.isEmpty($stateParams.id)){
            this.parentCategoryId = $stateParams.id;
        }

        this.initDataModel();

        this.bindEvent();

        this.getProductSecondClassifyList();

    },

    /**
     * 初始化
     * */
    initDataModel : function(){
        this.scope.formObj = this.formObj;
    },


    /**
     * 获取产品分类列表
     * */
    getProductSecondClassifyList : function(){

        var self = this;

        var params = {};
        params.parentId = self.parentCategoryId;

        PageController.init(self.scope,ProductApi.GET_CLASSIFY_LIST,params, function (data) {

            self.dataModel.classifyList.length = 0;

            PageController.setTotalPage(data.totalCount);

            self.scope.isEmpty = (data.list == 0) ? true : false;

            var list = data.list;
            for(var i = 0; i < list.length; i++)
            {
                var itemObj = {};
                itemObj.index = (PageController.currentPage - 1) * PageController.num + i + 1;
                itemObj.id = list[i].categoryId;
                itemObj.imgUrl = list[i].imgUrl;
                itemObj.name = list[i].name;
                itemObj.abbreviation = list[i].abbreviation;
                itemObj.type = list[i].type;
                itemObj.template = list[i].templateId;

                self.dataModel.classifyList.push(itemObj);
            }

            self.scope.dataModel = self.dataModel;
            self.scope.$apply();

            common.hideLoading();
        })
    },

    /**
     *绑定事件
     * */
    bindEvent : function(){

        var self = this;

        self.scope.onClickGetDetail = function(id){
            self.state.go('productSecondClassify',{id:id});
        }

        /**
         * 添加分类
         * */
        self.scope.onClickAddClassify = function(){
            self.dataModel.modalTitle = "添加产品分类";
            self.reSetFormData();
            common.showModal("addProductClassify");
        };

        /**
         * 修改分类
         * */
        self.scope.onClickModClassify = function(item){

            self.reSetFormData();
            self.dataModel.modalTitle = "修改产品分类";
            self.dataModel.classifyId = item.id;
            self.dataModel.classifyName = item.name;
            self.dataModel.classifyImage = item.imgUrl;
            self.dataModel.classifyAbbreviation = item.abbreviation;
            self.dataModel.template = item.template;
            self.scope.dataModel = self.dataModel;
            common.showModal("addProductClassify");
        };

        /**
         * 删除分类
         * */
        self.scope.onClickDelClassify = function(id){
            common.confirm(ProductText.CONFIRM_DEL_CLASSIFY, function () {
                var params = {};
                var requestParam = {};
                requestParam.categoryId = id;

                params.type = "post";
                params.dataApi = ProductApi.DEL_CLASSIFY;
                params.requestParams = requestParam;
                params.callback = function(data){
                    common.toast(1,ProductText.SUCCESS_DEL_CLASSIFY);
                    PageController.callApi();
                }

                HttpService.ajaxRequest(params);
            })
        };

        /**
         * 提交分类编辑
         * */
        self.scope.submitEditClassify = function(){
            var formData = self.getFormData();
            if(formData){
                if(fn.isEmpty(self.dataModel.classifyId))
                {
                    formData.parentId = self.parentCategoryId;
                    var params = {};
                    params.type = "post";
                    params.dataApi = ProductApi.ADD_CLASSIFY;
                    params.requestParams = formData;
                    params.callback = function(){
                        common.hideModal("addProductClassify");
                        common.toast(1,ProductText.SUCCESS_ADD_CLASSIFY);
                        PageController.callApi();
                    }
                }
                else
                {
                    formData.categoryId = self.dataModel.classifyId;
                    var params = {};
                    params.type = "post";
                    params.dataApi = ProductApi.MOD_CLASSIFY;
                    params.requestParams = formData;
                    params.callback = function(){
                        common.hideModal("addProductClassify");
                        common.toast(1,ProductText.SUCCESS_MOD_CLASSIFY);
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
        params.name = self.dataModel.classifyName;
        params.imgUrl = self.dataModel.classifyImage;
        params.abbreviation = self.dataModel.classifyAbbreviation;
        params.paramsTempletId = self.dataModel.template;

        var dataArr = [
            {data:params.name,errDesc:ProductText.WARN_CLASSIFY_NAME_EMPTY},
            {data:params.abbreviation,errDesc:ProductText.WARN_CLASSIFY_ABBREVIATION_EMPTY},
            {data:params.imgUrl,errDesc:ProductText.WARN_CLASSIFY_IMAGE_EMPTY},
            {data:params.paramsTempletId,errDesc:ProductText.WARN_UN_SEL_TEMPLATE},
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

        self.dataModel.classifyName = "";
        self.dataModel.classifyAbbreviation = "";
        self.dataModel.classifyImage = "";
        self.dataModel.classifyId = "";
        self.dataModel.template = "";

        self.scope.dataModel = self.dataModel;
    },

}