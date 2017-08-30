/**
 * Created by Administrator on 2017/7/27.
 */


app.controller('PartsProductAddCtrl', function ($scope,$state) {
    PartsProductAddController.init($scope,$state);
})

var PartsProductAddController = {

    scope : null,

    state : null,

    dataModel : {
        productName : "",
        productSummary : "",
        productPlace : "",
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
    },

    /**
     *form 表单封装
     * */
    formObj : {
        productName : {
            formDesc : "产品名称：",
            formType : "text",
            formDisabled : false,
        },
        productSummary : {
            formDesc : "产品简介：",
            formType : "text",
            formDisabled : false,
        },
        productPlace : {
            formDesc : "产地：",
            formType : "text",
            formDisabled : false,
        },
        productCommonName : {
            formDesc : "通用名：",
            formType : "text",
            formDisabled : false
        },
        productNumber : {
            formDesc : "产品编号：",
            formType : "text",
            formDisabled : false
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

    init : function($scope,$state){

        common.hideLoading();

        this.scope = $scope;

        this.state = $state;

        this.initDataModel();

        this.bindEvent();
    },


    /**
     * 初始化数据模型
     * */
    initDataModel : function () {

        this.scope.dataModel = this.dataModel;
        this.scope.formObj = this.formObj;
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

        self.scope.onClickAdd = function(){

            var requestParams = self.getFormData();

            var params = {};
            params.type = "post";
            params.dataApi = PartsApi.ADD_PARTS_PRODUCT;
            params.requestParams = requestParams;
            params.callback = function(){
                alert("success")
            }
            HttpService.ajaxRequest(params)
        }
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
        params.name = self.dataModel.productName;
        params.detail = self.dataModel.detail;
        params.summary = self.dataModel.productSummary;
        params.place = self.dataModel.productPlace;
        params.code = self.dataModel.productNumber;
        params.displayImg = self.dataModel.displayImage;
        params.picture = self.dataModel.imgUrl;
        params.common = self.dataModel.productCommonName;
        params.categoryId = self.dataModel.productClassify;
        params.paramTemplateId = self.dataModel.paramTemplateId;
        params.brandId = self.dataModel.productBrand;
        params.volume = 10;
        params.weight = 20;
        params.brand = self.dataModel.machineList;

        for(var i = 0; i < self.dataModel.templateDetail.length; i++)
        {
            var paramObj = {};
            paramObj.value = self.dataModel.templateDetail[i].formModel;
            paramObj.name = self.dataModel.templateDetail[i].name;

            self.dataModel.paramValue[self.dataModel.templateDetail[i].id] = paramObj;
        }
        params.paramsValue = self.dataModel.paramValue;

        return params;

    }


}