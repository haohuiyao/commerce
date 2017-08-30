/**
 * Created by daipengpeng on 2017/7/26.
 * 添加机型组控制器
 */
app.controller("ExcavatorAddProductCtrl",function($scope,$state){
    ExcavatorAddProductController.init($scope,$state)
});

var ExcavatorAddProductController = {

    scope : null,
    state : null,

    dataModel : {
        productName : "",
        productClassify : "144", //分类---挖机
        productBrand : "",//产品品牌
        excavatorModelName :"",
        excavatorModelArr : [],//机型数组

        productPlace : "",
        productCost : "",
        productCommonName : "",
        productNumber : "",
        productSource : "",//产品货源
        productSummary : "",//产品简介
        detail : null,//图文详情
        displayImage : null,//产品首图
        imgUrl : [],//产品图
        paramTemplateId : "",//参数模板
        paramValue : {},//商品参数详情
        templateDetail : "",
    },

    /**
     *form 表单封装
     * */
    formObj : {
        reset : true,
        excavatorModelName : {
            formDesc : "机型名称：",
            formType : "text",
            formDisabled : false,
        },

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
        productCost : {
            formDesc : "成本价：",
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
        productSource : {
            formDesc : "产品货源：",
            optArr : [],
            api: ProductApi.GET_GOODS_SOURCE,
            optNameKey:"sourceName",
            optValueKey:"id"
        },
        productBrand : {
            formDesc : "品牌：",
            optionArr : [

            ]
        },

        paramTemplate : {
            formDesc : "参数模板：",
            optArr : [],
            api: ProductApi.GET_PARAM_TEMPLATE_LIST,
            optNameKey:"name",
            optValueKey:"templateId"
        }
    },

    /**
     * 初始化
     * */
    init : function($scope,$state){

        common.showLoading();

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
            var params = {};
            params.type = "post";
            params.dataApi = ExcavatorApi.ADD_EXCAVATOR_PRODUCT;
            params.requestParams = requestParam;
            params.callback = function(data){
               //添加机型组成功后添加具体机型
               self.addExcavatorModel(data.id);
            }
            HttpService.ajaxRequest(params);
        }

        self.scope.addExcavatorModel = function(){
            self.resetExcavatorModelForm();
            common.showModal("editExcavatorModel");
        }

        self.scope.submitEditExcavatorModel = function(){
            var itemObj = self.getExcavatorModelForm();
            self.dataModel.excavatorModelArr.push(itemObj);
            self.scope.dataModel = self.dataModel;
        }

        self.scope.delExcavatorModel = function(index){
            self.dataModel.excavatorModelArr.splice(index,1);
            self.scope.dataModel = self.dataModel;
        }
        
    },

    /**
     * 添加具体机型
     * */
    addExcavatorModel : function(id){

        var self = this;
        var addRequestParam = {};
        addRequestParam.productId = id;
        addRequestParam.skus = self.dataModel.excavatorModelArr;
        var addParams = {};
        addParams.type = "post";
        addParams.dataApi = ExcavatorApi.ADD_EXCAVATOR_SKU;
        addParams.requestParams = addRequestParam;
        addParams.callback = function(){
            alert("success");
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

        //params.detail = self.dataModel.detail;
        //params.summary = self.dataModel.productSummary;
        //params.place = self.dataModel.productPlace;
        //params.code = self.dataModel.productNumber;
        //params.cost = self.dataModel.productCost;
        //params.displayImg = self.dataModel.displayImage;
        //params.picture = self.dataModel.imgUrl;
        //params.common = self.dataModel.productCommonName;
        //params.currency = 1;
        //params.volume = 100;
        //params.weight = 200;
        //params.carriageId = 4;
        //params.paramTemplateId = self.dataModel.paramTemplateId;
        //params.sourceId = self.dataModel.productSource;
        //for(var i = 0; i < self.dataModel.templateDetail.length; i++)
        //{
        //    var paramObj = {};
        //    paramObj.value = self.dataModel.templateDetail[i].formModel;
        //    paramObj.name = self.dataModel.templateDetail[i].name;
        //
        //    self.dataModel.paramValue[self.dataModel.templateDetail[i].id] = paramObj;
        //}
        //params.paramsValue = self.dataModel.paramValue;

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
            self.formObj.reset = true;
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