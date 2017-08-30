/**
 * Created by daipengpeng on 2017/7/27.
 * 产品详情页控制器
 */
app.controller("ExcavatorProductDetailCtrl",function($scope,$state,$stateParams){
    ExcavatorProductDetailController.init($scope,$state,$stateParams)
});

var ExcavatorProductDetailController = {

    scope : null,
    state : null,

    id : null,

    dataModel : {
        productName : "",
        productClassify : "",
        productBrand : "",
        excavatorModelArr : [],
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
        productClassify : {
            formDesc : "产品分类：",
            formType : "text",
            formDisabled : true
        },
        productBrand : {
            formDesc : "产品品牌：",
            formType : "text",
            formDisabled : true
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

            self.dataModel.productBrand = data.brandName;
            self.dataModel.productClassify = data.categoryName;
            self.dataModel.productName = data.name;

            self.dataModel.excavatorModelArr.length = 0;
            for(var i = 0; i < data.skuList.length; i++)
            {
                var itemObj = {};
                itemObj.name = data.skuList[i].skuName;
                itemObj.id = data.skuList[i].id;
                itemObj.statusValue = (data.skuList[i].status == "A") ? "出售中" : "已下架";
                itemObj.stayus = (data.skuList[i].status == "A") ? true : false;
                itemObj.statusBtnVal = (data.skuList[i].status == "A") ? '下架' : '上架';
                self.dataModel.excavatorModelArr.push(itemObj);
            }

            self.scope.dataModel = self.dataModel;
            self.scope.$apply();
            common.hideLoading();
        }

        HttpService.ajaxRequest(params);
    },

}