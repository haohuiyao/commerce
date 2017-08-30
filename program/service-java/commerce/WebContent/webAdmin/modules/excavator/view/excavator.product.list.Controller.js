/**
 * Created by Administrator on 2017/7/26.
 * 挖掘机列表控制器
 */

app.controller('ExcavatorProductListCtrl', function ($scope,$state) {
    ExcavatorProductListController.init($scope,$state);
})

var ExcavatorProductListController = {

    scope : null,

    state : null,

    dataModel : {
      excavatorList : [],
    },

    init : function($scope,$state){

        this.scope = $scope;

        this.state = $state;

        this.getExcavatorList();

        this.bindEvent();
    },


    /**
     * 获取产品组列表
     * */
    getExcavatorList : function () {

        var self = this;

        var fixedParams = {};

        PageController.init(self.scope,ExcavatorApi.GET_EXCAVATOR_PRODUCT_LIST,fixedParams,function(data){

            self.dataModel.excavatorList = [];
            var totalCount = data.totalCount;
            var dataArr = data.list;
            PageController.setTotalPage(totalCount);

            self.scope.isEmpty = (data.list == 0) ? true : false;

            for(var i = 0 ; i < dataArr.length ; i ++)
            {
                var itemObj = {};
                itemObj.isSel = false;
                itemObj.index = (PageController.currentPage - 1) * PageController.num + i + 1;
                itemObj.id = dataArr[i].id;
                itemObj.brandName = dataArr[i].brandName;
                itemObj.brandId = dataArr[i].brandId;
                itemObj.createTime = dataArr[i].createTime;
                itemObj.code = dataArr[i].code;
                itemObj.detail = dataArr[i].detail;
                itemObj.displayImg = dataArr[i].displayImg;
                itemObj.paramId = dataArr.paramId;
                itemObj.paramName = dataArr[i].paramName;
                itemObj.paramsValue = dataArr[i].paramsValue;
                itemObj.picture = dataArr[i].picture;
                itemObj.productId = dataArr[i].productId;
                itemObj.productName = dataArr[i].name;
                itemObj.skuName = dataArr[i].skuName;
                itemObj.summary = dataArr[i].summary;
                itemObj.volume = dataArr[i].volume;
                itemObj.weight = dataArr[i].weight;
                itemObj.statusValue = (dataArr[i].status == "A") ? "出售中" : "已下架";
                itemObj.status = (dataArr[i].status == "A") ? true : false;
                itemObj.statusBtnVal = (dataArr[i].status == "A") ? '下架' : '上架';
                self.dataModel.excavatorList.push(itemObj);
            }

            self.scope.dataModel = self.dataModel;
            self.scope.$apply();

            common.hideLoading();
        })

    },

    /**
     * 绑定事件
     * */
    bindEvent : function () {

        var self = this;

        self.scope.onClickAdd = function(){
            self.state.go("excavatorAddProduct");
        }

        self.scope.onClickToDetail = function(id){
            self.state.go("excavatorProductDetail",{id:id});
        }

        self.scope.onClickMod = function(id){
            self.state.go("excavatorModProduct",{id:id});
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
        params.dataApi = ExcavatorApi.PUT_UP_EXCAVATOR_PRODUCT;
        params.requestParams = ids;
        params.callback = function(data){
            common.toast(1,ExcavatorText.SUCCESS_PUT_UP);
            PageController.callApi();
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
        params.dataApi = ExcavatorApi.PUT_OUT_EXCAVATOR_PRODUCT;
        params.requestParams = ids;
        params.callback = function(data){
            common.toast(1,ExcavatorText.SUCCESS_PUT_DOWN);
            PageController.callApi();
        }
        HttpService.ajaxRequest(params);
    }
}