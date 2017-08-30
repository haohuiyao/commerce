/**
 * Created by Administrator on 2017/7/26.
 */

app.controller('PartsProductListCtrl', function ($scope,$state) {
    PartsProductListController.init($scope,$state);
})

var PartsProductListController = {

    scope : null,

    state : null,

    dataModel : {
        partsProductList : [],
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

        PageController.init(self.scope,PartsApi.GET_PARTS_PRODUCT_LIST,fixedParams,function(data){

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
                itemObj.productName = dataArr[i].name;
                itemObj.summary = dataArr[i].summary;
                itemObj.volume = dataArr[i].volume;
                itemObj.weight = dataArr[i].weight;
                itemObj.statusValue = (dataArr[i].status == "A") ? "出售中" : "已下架";
                itemObj.status = (dataArr[i].status == "A") ? true : false;
                itemObj.statusBtnVal = (dataArr[i].status == "A") ? '下架' : '上架';
                self.dataModel.partsProductList.push(itemObj);
            }

            self.scope.dataModel = self.dataModel;
            self.scope.$apply();

            common.hideLoading();
        })

    },

    bindEvent : function(){
        var self = this;

        self.scope.onClickAdd = function(){
            self.state.go("partsProductAdd");
        }

        self.scope.onClickDetail = function(id){
            self.state.go("partsProductDetail",{id:id})
        }

        self.scope.onClickMod = function(id){
            self.state.go("partsProductMod",{id:id})
        }
    }
}