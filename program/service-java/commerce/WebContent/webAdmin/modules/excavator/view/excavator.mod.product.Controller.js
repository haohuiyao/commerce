/**
 * Created by daipengpeng on 2017/7/26.
 * 添加机型组控制器
 */
app.controller("ExcavatorModProductCtrl",function($scope,$state,$stateParams){
    ExcavatorModProductController.init($scope,$state,$stateParams)
});

var ExcavatorModProductController = {

    scope : null,
    state : null,

    id : null,

    dataModel : {
        productName : "",
        productClassify : "144", //分类---挖机
        productBrand : "",//产品品牌
        excavatorModelName :"",
        excavatorModelArr : [],//机型数组
        resetBrandSel : true,
    },

    /**
     *form 表单封装
     * */
    formObj : {
        excavatorModelName : {
            formDesc : "机型型号名称：",
            formType : "text",
            formDisabled : false,
        },
        productName : {
            formDesc : "产品名称：",
            formType : "text",
            formDisabled : false,
        },

        productBrand : {
            formDesc : "品牌：",
            optionArr : []
        },
    },

    /**
     * 初始化
     * */
    init : function($scope,$state,$stateParams){

        common.showLoading();

        this.id = $stateParams.id;

        this.scope = $scope;

        this.state = $state;

        this.initDataModel();

        this.bindEvent();

        this.getExcavatorProductDetail();

    },

    /**
     * 初始化数据模型
     * */
    initDataModel : function () {

        this.scope.dataModel = this.dataModel;
        this.scope.formObj = this.formObj;
    },

    /**
     * 获取机型组详情
     * */
    getExcavatorProductDetail : function () {

        var self = this;

        var requestParams = {};
        requestParams.id = self.id;

        var params = {};
        params.type = "post";
        params.dataApi = ExcavatorApi.GET_EXCAVATOR_PRODUCT_DETAIL;
        params.requestParams = requestParams;
        params.callback = function(data){

            self.dataModel.productBrand = data.brandId;
            self.dataModel.productClassify = data.categoryId;
            self.dataModel.productName = data.name;

            self.dataModel.excavatorModelArr.length = 0;
            for(var i = 0; i < data.skuList.length; i++)
            {
                var itemObj = {};
                itemObj.name = data.skuList[i].skuName;
                itemObj.id = data.skuList[i].id;
                itemObj.statusValue = (data.skuList[i].status == "A") ? "出售中" : "已下架";
                itemObj.status = (data.skuList[i].status == "A") ? true : false;
                itemObj.statusBtnVal = (data.skuList[i].status == "A") ? '下架' : '上架';
                self.dataModel.excavatorModelArr.push(itemObj);
            }

            self.scope.dataModel = self.dataModel;
            self.scope.$apply();
            common.hideLoading();
        }

        HttpService.ajaxRequest(params);
    },

    /**
     * 绑定事件
     * */
    bindEvent : function(){

        var self = this;
        
        self.scope.$watch('dataModel.productClassify', function (value) {
            if(fn.isEmpty(value))
            {
                return;
            }
            self.getBrandByClassify();
        })

        self.scope.onClickSave = function(){
            var requestParam = self.getFormData();
            requestParam.id = self.id;
            var params = {};
            params.type = "post";
            params.dataApi = ExcavatorApi.MOD_EXCAVATOR_PRODUCT;
            params.requestParams = requestParam;
            params.callback = function(data){
                common.toast(1,'修改成功');
            }
            HttpService.ajaxRequest(params);
        }

        self.scope.addExcavatorModel = function(){
            self.resetExcavatorModelForm();
            common.showModal("editExcavatorModel");
        }

        self.scope.submitEditExcavatorModel = function(){
            var itemObj = self.getExcavatorModelForm();
            self.addExcavatorModel(itemObj);

        }

        self.scope.delExcavatorModel = function(index){
            self.dataModel.excavatorModelArr.splice(index,1);
            self.scope.dataModel = self.dataModel;
        }

        self.scope.upOrOutProduct = function(status,id){
            if(status){
                //去下架
                common.confirm(ExcavatorText.CONFIRM_PUT_DOWN,function(){
                    self.putDownExcavatorProduct(id);
                })
            }
            else {
                //去上架
                common.confirm(ExcavatorText.CONFIRM_PUT_UP,function(){
                    self.putUpExcavatorProduct(id);
                })
            }
        }
    },

    /**
     * 上架机型组
     * */
    putUpExcavatorProduct : function(id){
        var self = this;

        var ids = [id];

        var params = {};
        params.type = "post";
        params.dataApi = ExcavatorApi.PUT_UP_EXCAVATOR_SKU;
        params.requestParams = ids;
        params.callback = function(data){
            common.toast(1,ExcavatorText.SUCCESS_PUT_UP);
            self.getExcavatorProductDetail();
        }
        HttpService.ajaxRequest(params);
    },

    /**
     * 下架机型组
     * */
    putDownExcavatorProduct : function(id){
        var self = this;

        var ids = [id];

        var params = {};
        params.type = "post";
        params.dataApi = ExcavatorApi.PUT_OUT_EXCAVATOR_SKU;
        params.requestParams = ids;
        params.callback = function(data){
            common.toast(1,ExcavatorText.SUCCESS_PUT_DOWN);
            self.getExcavatorProductDetail();
        }
        HttpService.ajaxRequest(params);
    },

    /**
     * 添加具体机型
     * */
    addExcavatorModel : function(itemObj){

        var self = this;
        var addRequestParam = {};
        addRequestParam.productId = self.id;
        addRequestParam.skus = [itemObj];
        var addParams = {};
        addParams.type = "post";
        addParams.dataApi = ExcavatorApi.ADD_EXCAVATOR_SKU;
        addParams.requestParams = addRequestParam;
        addParams.callback = function(){
            common.hideModal("editExcavatorModel");
            self.getExcavatorProductDetail();
        }
        HttpService.ajaxRequest(addParams);
    },

    /**
     * 获取表单数据
     * */
    getFormData : function(){

        var self = this;
        var params = {};
        params.name = self.dataModel.productName;
        params.categoryId = self.dataModel.productClassify;
        params.brandId = self.dataModel.productBrand;

        return params;
    },

    /**
     * 根据分类获取品牌列表
     * */
    getBrandByClassify : function () {
        var self = this;

        var requestParam = {};
        requestParam.id = self.dataModel.productClassify;

        var params = {};
        params.type = "post";
        params.dataApi = ExcavatorApi.GET_BRAND_BY_CLASSIFY;
        params.requestParams = requestParam;
        params.callback = function(data){
            self.formObj.productBrand.optionArr.length = 0;
            for(var i = 0; i < data.length; i++)
            {
                var itemObj = {};
                itemObj.optValue = data[i].id;
                itemObj.optName = data[i].name;
                self.formObj.productBrand.optionArr.push(itemObj);
            }

            self.dataModel.resetBrandSel = true;

            self.scope.formObj = self.formObj;
            self.scope.$apply();
        }
        HttpService.ajaxRequest(params)
    },

    /**
     * c重置机型form
     * */
    resetExcavatorModelForm : function(){
        var self = this;

        self.dataModel.excavatorModelName = "";

    },

    /**
     * 获取机型form data
     * */
    getExcavatorModelForm : function(){
        var self = this;

        var params = {};
        params.name = self.dataModel.excavatorModelName;

        return params;
    },

}