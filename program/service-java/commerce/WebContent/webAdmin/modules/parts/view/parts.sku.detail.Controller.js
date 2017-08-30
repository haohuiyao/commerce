/**
 * Created by Administrator on 2017/7/27.
 */


app.controller('PartsSkuDetailCtrl', function ($scope,$state,$stateParams) {
    PartsSkuDetailController.init($scope,$state,$stateParams);
})

var PartsSkuDetailController = {

    scope : null,

    state : null,

    id : null,

    dataModel : {
        productName : "",
        productSummary : "",
        productPlace : "",
        productCost : "",
        productCommonName : "",
        productNumber : "",
        productClassify : "", //一级分类
        productBrand : "",//产品品牌
        detail : null,//图文详情
        displayImage : null,//产品首图
        imgUrl : [],//产品图
        paramTemplateId : "",//参数模板
        paramValue : {},//商品参数详情
        resetBrand : true,
        machineList : [],
        templateDetail : "",
        productClassifyName : "",
        productBrandName : "",
        paramTemplateName : "",
        paramTemplateValueFormArr : [],
        productModelArr : [],
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
        productCost : {
            formDesc : "成本价：",
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

        productClassifyName : {
            formDesc : "产品分类：",
            formType : "text",
            formDisabled : true
        },

        productBrandName : {
            formDesc : "产品品牌：",
            formType : "text",
            formDisabled : true
        },

        paramTemplateName : {
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
        }
    },

    init : function($scope,$state,$stateParams){

        this.id = $stateParams.id;

        common.showLoading();

        this.scope = $scope;

        this.state = $state;

        this.initDataModel();

        this.getProductDetail();
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
            self.dataModel.productClassify = detail.categoryId;
            self.dataModel.paramTemplateId = detail.paramTemplateId;
            self.dataModel.paramValue = JSON.parse(detail.paramsValue);
            self.dataModel.productBrandName = detail.partsBrand;
            self.dataModel.productClassifyName = '没有返回分类，找浩辉要';
            self.dataModel.paramTemplateName = detail.paramName;

            /**
             * 参数模板
             * */
            var arr = [];
            for(var key in  self.dataModel.paramValue){
                var formObj = {};
                formObj.formDesc = self.dataModel.paramValue[key].name;
                formObj.formDisabled = true;
                formObj.formType = "text";

                var itemObj = {};
                itemObj.formObj = formObj;
                itemObj.formModel = self.dataModel.paramValue[key].value;

                arr.push(itemObj)
            }
            self.dataModel.paramTemplateValueFormArr = arr;

            /**
             * 适用机型
             * */
            var modelArr = [];
            var model= data.rel;

            var modelObj = {};	
            modelObj.brandName = self.dataModel.productBrandName;
            modelObj.modelList = [];
            for(var n = 0; n < model.product.length; n++){
                var name = model.product[n].name + '-系列';
                modelObj.modelList.push(name)
            }

            for(var n = 0; n < model.brand.length; n++){
                var name = model.brand[n].brandName + '-系列';
                modelObj.modelList.push(name)
            }

            for(var n = 0; n < model.sku.length; n++){
                var name = model.sku[n].skuName;
                modelObj.modelList.push(name)
            }

            modelArr.push(modelObj);
            
            self.dataModel.productModelArr = modelArr;

            self.scope.dataModel = self.dataModel;
            self.scope.$apply();

            common.hideLoading();
        }

        HttpService.ajaxRequest(params)

    },

}