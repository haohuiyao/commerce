/**
 * Created by daipengpeng on 2017/7/16.
 * 管理后台产品列表Controller
 */
app.controller("ProductListCtrl",function($scope,$state){
    ProductListController.init($scope,$state)
});


var ProductListController = {

    scope : null,
    state : null,

    /**
     * 数据模型
     * */
    dataModel : {
        goodsList : [],
    },

    /**
     * 搜索配置
     * */
    searchObj : {
        inputSearch : [{name:"学号1",key : "number"},{name:"姓名1",key:"name"}],
        apiClickSearch : [{api:ProductApi.GET_BRAND_LIST,name:"品牌",key : "product",optArr:[]}],
        defaultClickSearch : [{optArr:[{optName : "已上架",optValue:1},{optName : "未上架",optValue:2}],name:"品牌",key : "product"}],
    },

    /**
     * 表格数据
     * */
    tableObj : {

    },

    /**
     *初始化
     * */
    init : function ($scope,$state) {

        this.scope = $scope;

        this.state = $state;

        common.showLoading();

        this.initDataModel();

        this.getGoodsList();

        this.bindEvent()
    },

    /**
     * 初始化
     * */
    initDataModel : function(){
        var self = this;

        self.scope.dataModel = self.dataModel;
        self.scope.searchObj = self.searchObj;
    },


    /**
     * 获取产品组列表
     * */
    getGoodsList : function () {

        var self = this;

        var fixedParams = {};
        fixedParams.condition = {};
        fixedParams.status = "A";

        PageController.init(self.scope,ProductApi.GET_GOODS_LIST,fixedParams,function(data){

            self.dataModel.goodsList = [];
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
                itemObj.frinkingName = dataArr[i].frinkingName;
                itemObj.frinkingId = dataArr[i].frinkingId;
                itemObj.categoryId = dataArr[i].categoryId;
                itemObj.categoryName = dataArr[i].categoryName;
                itemObj.code = dataArr[i].code;
                itemObj.common = dataArr[i].common;
                itemObj.cost = dataArr[i].cost;
                itemObj.createTime = dataArr[i].createTime;
                itemObj.currency = dataArr[i].currency;
                itemObj.detail = dataArr[i].detail;
                itemObj.displayImg = dataArr[i].displayImg;
                itemObj.modifyTime = dataArr[i].modifyTime;
                itemObj.name = dataArr[i].name;
                itemObj.paramTemplateId = dataArr.paramTemplateId;
                itemObj.paramName = dataArr[i].paramName;
                itemObj.paramsValue = dataArr[i].paramsValue;
                itemObj.picture = dataArr[i].picture;
                itemObj.place = dataArr[i].place;
                itemObj.sourceId = dataArr[i].sourceId;
                itemObj.sourceName = dataArr[i].sourceName;
                itemObj.summary = dataArr[i].summary;
                itemObj.volume = dataArr[i].volume;
                itemObj.weight = dataArr[i].weight;
                itemObj.statusValue = (dataArr[i].status == "A") ? "出售中" : "已下架";
                itemObj.staClass = (dataArr[i].status == "A") ? true : false;
                itemObj.statuBtnVal = (dataArr[i].status == "A") ? '下架' : '上架';
                self.dataModel.goodsList.push(itemObj);
            }

            self.scope.dataModel = self.dataModel;
            self.scope.$apply();

            common.hideLoading();
        })

    },

    /**
     *点击事件
     * */
    bindEvent : function(){

        var self = this;

        self.scope.onClickToGroupDetail = function(id){

            self.state.go("productDetail",{id: id});

        }

    }

}