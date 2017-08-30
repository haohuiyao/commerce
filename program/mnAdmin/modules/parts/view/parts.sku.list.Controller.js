/**
 * Created by Administrator on 2017/7/26.
 */

app.controller('PartsSkuListCtrl', function ($scope,$state) {
    PartsSkuListController.init($scope,$state);
})

var PartsSkuListController = {

    scope : null,

    state : null,

    dataModel : {
        partsProductList : [],
    },

    init : function($scope,$state){

        this.scope = $scope;

        this.state = $state;

        this.getExcavatorList();
    },


    /**
     * 获取产品组列表
     * */
    getExcavatorList : function () {

        var self = this;

        var fixedParams = {};

        PageController.init(self.scope,PartsApi.GET_PARTS_SKU_LIST,fixedParams,function(data){

            self.dataModel.partsProductList = [];
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
                itemObj.productName = dataArr[i].productName;
                itemObj.skuName = dataArr[i].skuName;
                itemObj.summary = dataArr[i].summary;
                itemObj.volume = dataArr[i].volume;
                itemObj.weight = dataArr[i].weight;
                itemObj.statusValue = (dataArr[i].status == "A") ? "出售中" : "已下架";
                itemObj.staClass = (dataArr[i].status == "A") ? true : false;
                itemObj.statuBtnVal = (dataArr[i].status == "A") ? '下架' : '上架';
                self.dataModel.partsProductList.push(itemObj);
            }

            self.scope.dataModel = self.dataModel;
            self.scope.$apply();

            common.hideLoading();
        })

    },
}