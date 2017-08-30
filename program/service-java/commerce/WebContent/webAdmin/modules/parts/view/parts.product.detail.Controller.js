/**
 * Created by Administrator on 2017/7/27.
 */


app.controller('PartsProductDetailCtrl', function ($scope,$state,$stateParams) {
    PartsProductDetailController.init($scope,$state,$stateParams);
})

var PartsProductDetailController = {

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
            formDesc : "产品品牌：",
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
        requestParams.id = self.id;

        var params = {};
        params.type = "post";
        params.dataApi = PartsApi.GET_PARTS_PRODUCT_DETAIL;
        params.requestParams = requestParams;
        params.callback = function(data){
            var detail = data.entity
            self.dataModel.productName = detail.name;
            self.dataModel.detail = detail.detail;
            self.dataModel.productSummary = detail.summary;
            self.dataModel.productPlace = detail.place;
            self.dataModel.productNumber = detail.code;
            self.dataModel.displayImage = detail.displayImg;
            self.dataModel.imgUrl = JSON.parse(detail.picture);
            self.dataModel.productCommonName = detail.common;
            self.dataModel.productClassify = detail.categoryId;
            self.dataModel.paramTemplateId = detail.paramTemplateId;
            self.dataModel.productBrand = detail.brandId;
            self.dataModel.paramValue = JSON.parse(detail.paramsValue);
            self.dataModel.productBrandName = detail.brandName;
            self.dataModel.productClassifyName = detail.categoryName;
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
            var model= data.productRel;
            for(var i = 0; i < model.length; i++)
            {
                var modelObj = {};
                modelObj.brandName = model[i].brandName;
                modelObj.modelList = [];
                for(var n = 0; n < model[i].productRel.product.length; n++){
                    var name = model[i].productRel.product[n].name + '-系列';
                    var itemObj = {};
                    modelObj.modelList.push(name)
                }

                for(var n = 0; n < model[i].productRel.brand.length; n++){
                    var name = model[i].productRel.brand[n].brandName + '-系列';
                    modelObj.modelList.push(name)
                }

                for(var n = 0; n < model[i].productRel.sku.length; n++){
                    var name = model[i].productRel.sku[n].skuName;
                    modelObj.modelList.push(name)
                }

                modelArr.push(modelObj);
            }
            self.dataModel.productModelArr = modelArr;


            self.scope.dataModel = self.dataModel;
            self.scope.$apply();

            common.hideLoading();
        }

        HttpService.ajaxRequest(params)

    },

}