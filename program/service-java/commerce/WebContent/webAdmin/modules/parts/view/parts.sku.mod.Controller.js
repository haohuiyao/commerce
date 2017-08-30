/**
 * Created by Administrator on 2017/7/27.
 */


app.controller('PartsSkuModCtrl', function ($scope,$state,$stateParams) {
    PartsSkuModController.init($scope,$state,$stateParams);
})

var PartsSkuModController = {

    scope : null,

    state : null,

    id : null,

    dataModel : {
        productName : "",
        productSummary : "",
        productPlace : "",
        productCommonName : "",
        productNumber : "",
        productClassify : "", //分类
        productClassifyName : "",//分类名称
        productBrand : "",//产品品牌
        productBrandName : "",
        detail : null,//图文详情
        displayImage : null,//产品首图
        imgUrl : [],//产品图
        paramTemplateId : "",//参数模板
        paramValue : {},//商品参数详情
        resetBrand : true,
        machineList : [],
        templateDetail : "",
        isCommonUse : false,
    },

    /**
     *form 表单封装
     * */
    formObj : {
        productName : {
            formDesc : "产品名称：",
            formType : "text",
            formDisabled : true,
        },
        productSummary : {
            formDesc : "产品简介：",
            formType : "text",
            formDisabled : true,
        },
        productPlace : {
            formDesc : "产地：",
            formType : "text",
            formDisabled : true,
        },
        productCommonName : {
            formDesc : "通用名：",
            formType : "text",
            formDisabled : true
        },
        productNumber : {
            formDesc : "产品编号：",
            formType : "text",
            formDisabled : true
        },
        productBrandName : {
            formDesc : "产品品牌：",
            formType : "text",
            formDisabled : true
        },
        productBrand : {
            formDesc : "产品品牌：",
            optionArr : [],
        },
        paramTemplate : {
            formDesc : "参数模板：",
            optArr : [],
            api: ProductApi.GET_PARAM_TEMPLATE_LIST,
            optNameKey:"name",
            optValueKey:"templateId"
        },
        productClassifyName : {
            formDesc : "产品分类：",
            formType : "text",
            formDisabled : true
        },
    },

    init : function($scope,$state,$stateParams){

        common.hideLoading();

        this.scope = $scope;

        this.state = $state;

        this.id = $stateParams.id

        this.initDataModel();

        this.getProductDetail();

        this.bindEvent();
    },


    /**
     * 初始化数据模型
     * */
    initDataModel : function () {

        this.scope.dataModel = this.dataModel;
        this.scope.formObj = this.formObj;
    },

    /**
     * 获取详情
     * */
    getProductDetail : function () {

        var self = this;

        var requestParams = {};
        requestParams.skuId = self.id;

        var params = {};
        params.type = "post";
        params.dataApi = PartsApi.GET_PARTS_SKU_DETAIL;
        params.requestParams = requestParams;
        params.callback = function(data){

            var detail = data.sku
            self.dataModel.productName = detail.productName;
            self.dataModel.detail = detail.detail;
            self.dataModel.productSummary = detail.summary;
            self.dataModel.productPlace = detail.place;
            self.dataModel.productNumber = detail.code;
            self.dataModel.displayImage = detail.displayImg;
            self.dataModel.imgUrl = JSON.parse(detail.picture);
            self.dataModel.productCommonName = detail.common;
            self.dataModel.paramTemplateId = detail.paramId;
            self.dataModel.productBrand = detail.partsBrandId;
            self.dataModel.paramValue = JSON.parse(detail.paramsValue);
            self.dataModel.productBrandName = detail.partsBrand;
            self.dataModel.productClassify = detail.categoryId;
            self.dataModel.productClassifyName = detail.categoryName;
            self.dataModel.paramTemplateName = detail.paramName;
            self.dataModel.productBrandName = detail.partsBrand;

            /**
             * 适用机型
             * */

            var modelArr = [];
            var model= data.rel;
            
            var modelObj = {};
            modelObj.brandId = "";
            modelObj.rel = [];
            for(var n = 0; n < model.product.length; n++){
                modelObj.brandId = model.product[n].brandId
                var itemObj = {};
                itemObj.objType = 1;
                itemObj.id = model.product[n].id;
                modelObj.rel.push(itemObj)
            }

            for(var n = 0; n < model.brand.length; n++){
                modelObj.brandId = model.brand[n].id
                var itemObj = {};
                itemObj.objType = 3;
                itemObj.id = model.brand[n].id;
                modelObj.rel.push(itemObj)
            }

            for(var n = 0; n < model.sku.length; n++){
                modelObj.brandId = model.sku[n].brandId
                var itemObj = {};
                itemObj.objType = 2;
                itemObj.id = model.sku[n].id;
                modelObj.rel.push(itemObj)
            }

            modelArr.push(modelObj);

            if(model.product.length == 0 && model.brand.length == 0 && model.sku.length == 0)
            {
                self.dataModel.isCommonUse = true
            }
           

            self.dataModel.machineList = modelArr;


            self.scope.dataModel = self.dataModel;
            self.scope.$apply();

            common.hideLoading();
        }

        HttpService.ajaxRequest(params)

    },


    bindEvent : function(){

        var self = this;

        self.scope.$watch('dataModel.productClassify', function (value) {

            if(fn.isEmpty(value))
            {
                return;
            }
            self.getBrandByClassify();
        })

        self.scope.delMachine = function(index){
            self.dataModel.machineList.splice(index,1);
        }

        self.scope.addMachine = function(){
            var itemObj = {};
            itemObj.brandId = "";
            itemObj.rel = [];
            self.dataModel.machineList.push(itemObj);
        }

        self.scope.onClickSave = function(){

            var requestParams = self.getFormData();
            requestParams.skuId = self.id;

            var params = {};
            params.type = "post";
            params.dataApi = PartsApi.EDIT_PARTS_SKU;
            params.requestParams = requestParams;
            params.callback = function(){
                common.toast(1,'修改成功');
                HomeController.closeTabed(HomeController.currentIndex)
            }
            HttpService.ajaxRequest(params)
        }

        /*
         * 是否通用
         */
        //self.scope.isCommonUse = function(){
        //    self.dataModel.isCommonUse = !self.dataModel.isCommonUse;
        //    self.scope.dataModel = self.dataModel;
        //}
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
        params.dataApi = PartsApi.GET_BRAND_BY_CLASSIFY;
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
            self.dataModel.resetBrand = true;
            self.scope.formObj = self.formObj;
            self.scope.dataModel = self.dataModel;
            self.scope.$apply();
        }
        HttpService.ajaxRequest(params)
    },

    /**
     * 获取表单数据
     * */
    getFormData : function(){

        var self = this;
        var params = {};
        params.detail = self.dataModel.detail;
        params.displayImg = self.dataModel.displayImage;
        params.picture = self.dataModel.imgUrl;
        params.paramTemplateId = self.dataModel.paramTemplateId;
        params.rel = self.dataModel.machineList[0].rel;

        for(var i = 0; i < self.dataModel.templateDetail.length; i++)
        {
            var paramObj = {};
            paramObj.value = self.dataModel.templateDetail[i].formModel;
            paramObj.name = self.dataModel.templateDetail[i].name;

            self.dataModel.paramValue[self.dataModel.templateDetail[i].id] = paramObj;
        }
        params.paramsValue = self.dataModel.paramValue;

        return params;

    },


}