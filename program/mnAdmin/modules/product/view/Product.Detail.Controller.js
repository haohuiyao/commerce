/**
 * Created by daipengpeng on 2017/7/20.
 * 产品详情页控制器
 */
app.controller("ProductDetailCtrl",function($scope,$state,$stateParams){
    ProductDetailController.init($scope,$state,$stateParams)
});

var ProductDetailController = {

    scope : null,
    state : null,

    skuId : null,

    dataModel : {
        productName : "",
        productPlace : "",
        productCost : "",
        productCommonName : "",
        productNumber : "",
        productClassify : "", //一级分类
        productSecondClassify : "",//二级分类
        productBrand : "",//产品品牌
        productSource : "",//产品货源
        detail : null,//图文详情
        displayImage : null,//产品首图
        imgUrl : [],//产品图
        districtPrice : [],//地区价格
        isAllprice : 1,//是否全国统一价
        allprice : "",//统一价
        paramTemplateId : "",//参数模板
        paramValue : {},//商品参数详情

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
        classifyObj : {
            firstLevel : {
                optDesc : "产品大类：",
                optArr : [],
                fixParams : {},
            },
            secondLevel : {
                optDesc : "产品细类：",
                optArr : [],
                fixParams : {},
            },
            api : ProductApi.GET_CLASSIFY_LIST
        },
        productBrand : {
            formDesc : "产品品牌：",
            optArr : [],
            api: ProductApi.GET_BRAND_LIST,
            optNameKey:"name",
            optValueKey:"id"
        },
        productSource : {
            formDesc : "产品货源：",
            optArr : [],
            api: ProductApi.GET_GOODS_SOURCE,
            optNameKey:"sourceName",
            optValueKey:"id"
        },
        paramTemplate : {
            formDesc : "参数模板：",
            optArr : [],
            api: ProductApi.GET_PARAM_TEMPLATE_LIST,
            optNameKey:"name",
            optValueKey:"templateId"
        }
    },

    priceArr : [],

    /**
     * 初始化
     * */
    init : function($scope,$state,$stateParams){

        common.showLoading();

        if(!fn.isEmpty($stateParams.id)){
            this.skuId = $stateParams.id;
        }

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
     * 获取商品详情
     * */
    getProductDetail : function () {

        var self = this;

        var requestParams = {};
        requestParams.skuId = self.skuId;

        var params = {};
        params.type = "post";
        params.dataApi = ProductApi.GET_GOODS_SKU_DETAIL;
        params.requestParams = requestParams;
        params.callback = function(data){

            self.dataModel.productName = data.name;
            self.dataModel.productPlace = data.place;
            self.dataModel.productCost = data.cost;
            self.dataModel.productCommonName = data.common;
            self.dataModel.productBrand = data.productBrandId;
            self.dataModel.productSource = data.sourceId;
            self.dataModel.productClassify = data.parentCategoryId;
            self.dataModel.productSecondClassify = data.sonCategoryId;
            self.dataModel.productNumber = data.code;
            self.dataModel.detail = data.detail;
            self.dataModel.displayImage = data.displayImg;
            self.dataModel.imgUrl = JSON.parse(data.imgUrl);
            self.dataModel.districtPrice = data.districtEntities;
            self.dataModel.isAllprice = fn.isEmpty(data.currency) ? '0' : '1';
            self.dataModel.allprice = data.currency;
            self.dataModel.paramTemplateId = data.templateId;
            self.dataModel.paramValue = JSON.parse(data.brandInformationEntities[0].paramsValues);//产品参数详情

            self.getDistrictList();

            self.scope.dataModel = self.dataModel;
            self.scope.$apply();
            common.hideLoading();
        }

        HttpService.ajaxRequest(params);
    },


    /*
     * 获取地区
     */
    getDistrictList : function()
    {
        var self = this;

        var params = {};
        params.type = "post";
        params.dataApi = ProductApi.GET_DISTRICT_LIST;
        params.requestParams = {};

        params.callback = function(data){

            /**
             * 创建价格对象
             * {"地区id":"地区信息"}
             * */
            var priceObj = {};
            for(var i = 0; i < self.dataModel.districtPrice.length; i++){
                var item = self.dataModel.districtPrice[i];
                priceObj[item.id] = item;
            }
            /**
             * 给地区赋价格值
             * */
            self.priceArr = [];
            for(var i = 0 ; i < data.length ; i ++)
            {
                var itemObj = {};
                itemObj.district = data[i].district;
                itemObj.id = data[i].id;
                itemObj.price = (self.dataModel.isAllprice == 1) ? self.dataModel.allprice : priceObj[itemObj.id].price;
                self.priceArr.push(itemObj);
            }
            self.scope.priceArr = self.priceArr;
            self.scope.$apply();
        };
        HttpService.ajaxRequest(params);
    },

}