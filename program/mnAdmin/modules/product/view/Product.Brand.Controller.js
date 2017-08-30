/**
 * Created by daipengpeng on 2017/7/20.
 * 产品品牌控制器
 */


app.controller("ProductBrandCtrl",function($scope,$state){
    ProductBrandController.init($scope,$state)
});

var ProductBrandController = {

    scope : null,
    state : null,

    dataModel : {
        brandList : [],
        brandName : "",
        brandAbbreviation : "",
        brandImage : "",
        modalTitle : "",
        brandId : "",
        classifyArr : [
            {
                name : "全部",
                id : "",
                child : []
            }
        ],
        classifyArr : []
    },

    formObj : {
        brandName : {
            formDesc : "品牌名称：",
            formType : "text",
            formDisabled : false,
        },
        brandAbbreviation : {
            formDesc : "品牌缩写：",
            formType : "text",
            formDisabled : false,
        }
    },

    /**
     * 初始化
     * */
    init : function($scope,$state){

        common.showLoading();

        this.scope = $scope;

        this.state = $state;

        this.initDataModel();

        this.getProductBrandList();

        this.bindEvent();

    },

    initDataModel : function(){

        this.scope.formObj = this.formObj;
    },

    /**
     * 获取产品品牌列表
     * */
    getProductBrandList : function(){

        var self = this;

        PageController.init(self.scope,ProductApi.GET_BRAND_LIST,{}, function (data) {

            self.dataModel.brandList.length = 0;

            PageController.setTotalPage(data.totalCount);

            self.scope.isEmpty = (data.list == 0) ? true : false;

            var list = data.list;
            for(var i = 0; i < list.length; i++)
            {
                var itemObj = {};
                itemObj.index = (PageController.currentPage - 1) * PageController.num + i + 1;
                itemObj.id = list[i].id;
                itemObj.imgUrl = list[i].imgUrl
                itemObj.name = list[i].name;
                itemObj.abbreviation = list[i].abbreviation;
                itemObj.status = list[i].status
                self.dataModel.brandList.push(itemObj);
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

        self.scope.onClickModClassify = function(id){
            self.dataModel.brandId = id;
            common.showModal("editBrandClassify");
        }

        self.scope.submitEditBrandClassify = function(){
            var requestParam = {};
            requestParam.brandId = self.dataModel.brandId;
            requestParam.categoryId = self.dataModel.classifyArr;

            var params = {};
            params.type = "post";
            params.dataApi = ProductApi.MOD_BRAND_CLASSIFY;
            params.requestParams = requestParam;
            params.callback = function(data)
            {
                common.hideModal("editBrandClassify");
            }

            HttpService.ajaxRequest(params);
        }

        /**
         * 添加品牌
         * */
        self.scope.onClickAddBrand = function(){
            self.dataModel.modalTitle = "添加产品品牌";
            self.reSetFormData();
            common.showModal("addProductBrand");
        };

        /**
         * 修改品牌
         * */
        self.scope.onClickModBrand = function(item){

            self.reSetFormData();
            self.dataModel.modalTitle = "修改产品品牌";
            self.dataModel.brandId = item.id;
            self.dataModel.brandName = item.name;
            self.dataModel.brandImage = item.imgUrl;
            self.dataModel.brandAbbreviation = item.abbreviation;
            self.scope.dataModel = self.dataModel;
            common.showModal("addProductBrand");
        };

        /**
         * 删除品牌
         * */
        self.scope.onClickDelBrand = function(id){
            common.confirm(ProductText.CONFIRM_DEL_BRAND, function () {
                var params = {};
                var requestParam = {};
                requestParam.brandId = id;

                params.type = "post";
                params.dataApi = ProductApi.DEL_BRAND;
                params.requestParams = requestParam;
                params.callback = function(data){
                    common.toast(1,ProductText.SUCCESS_DEL_BRAND);
                    PageController.callApi();
                }

                HttpService.ajaxRequest(params);
            })
        };

        /**
         * 提交品牌编辑
         * */
        self.scope.submitEditBrand = function(){
            var formData = self.getFormData();
            if(formData){
                if(fn.isEmpty(self.dataModel.brandId))
                {
                    var params = {};
                    params.type = "post";
                    params.dataApi = ProductApi.ADD_BRAND;
                    params.requestParams = formData;
                    params.callback = function(){
                        common.hideModal("addProductBrand");
                        common.toast(1,ProductText.SUCCESS_ADD_BRAND);
                        PageController.callApi();
                    }
                }
                else
                {
                    formData.brandId = self.dataModel.brandId;
                    var params = {};
                    params.type = "post";
                    params.dataApi = ProductApi.MOD_BRAND;
                    params.requestParams = formData;
                    params.callback = function(){
                        common.hideModal("addProductBrand");
                        common.toast(1,ProductText.SUCCESS_MOD_BRAND);
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
        params.name = self.dataModel.brandName;
        params.imgUrl = self.dataModel.brandImage;
        params.abbreviation = self.dataModel.brandAbbreviation;

        var dataArr = [
                        {data:params.name,errDesc:ProductText.WARN_BRAND_NAME_EMPTY},
                        {data:params.abbreviation,errDesc:ProductText.WARN_BRAND_ABBREVIATION_EMPTY},
                        {data:params.imgUrl,errDesc:ProductText.WARN_BRAND_IMAGE_EMPTY},
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

        self.dataModel.brandName = "";
        self.dataModel.brandAbbreviation = "";
        self.dataModel.brandImage = "";
        self.dataModel.brandId = "";

        self.scope.dataModel = self.dataModel;
    },

}