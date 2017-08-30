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
        partsSkuList : [],
    },

    init : function($scope,$state){

        this.scope = $scope;

        this.state = $state;

        this.getPartsSkuList();
        
        this.bindEvent();
    },
    
    bindEvent : function(){
    	
    	var self = this;
    	
    	self.scope.onClickDetail = function(id){
    		self.state.go("partsSkuDetail",{id:id});
    	}
    	
    	self.scope.onClickMod = function(id){
    		self.state.go('partsSkuMod',{id:id})
    	}
    	
    	self.scope.upOrOutProduct = function(status,id){
            if(status){
                //去下架
                common.confirm(PartsText.CONFIRM_PUT_DOWN,function(){
                    self.putDownPartsSku(id);
                })
            }
            else {
                //去上架
                common.confirm(PartsText.CONFIRM_PUT_UP,function(){
                    self.putUpPartsSku(id);
                })
            }
        }
    },


    /**
     * 获取产品组列表
     * */
    getPartsSkuList : function () {

        var self = this;

        var fixedParams = {};
        var obj = {};
        obj.productId = "";
        fixedParams.condition = obj

        PageController.init(self.scope,PartsApi.GET_PARTS_SKU_LIST,fixedParams,function(data){
			
            self.dataModel.partsSkuList = [];
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
                itemObj.productName = dataArr[i].productName;
                itemObj.skuName = dataArr[i].skuName;
                itemObj.summary = dataArr[i].summary;
                itemObj.volume = dataArr[i].volume;
                itemObj.weight = dataArr[i].weight;
                itemObj.statusValue = (dataArr[i].status == "A") ? "出售中" : "已下架";
                itemObj.status = (dataArr[i].status == "A") ? true : false;
                itemObj.statusBtnVal = (dataArr[i].status == "A") ? '下架' : '上架';
                self.dataModel.partsSkuList.push(itemObj);
            }

            self.scope.dataModel = self.dataModel;
            self.scope.$apply();

            common.hideLoading();
        })

    },
    
    /**
     * 上架机型组
     * */
    putUpPartsSku : function(id){
        var self = this;

        var ids = [id];

        var params = {};
        params.type = "post";
        params.dataApi = PartsApi.PUT_UP_EXCAVATOR_SKU;
        params.requestParams = ids;
        params.callback = function(data){
            common.toast(1,PartsText.SUCCESS_PUT_UP);
            self.getPartsSkuList();
        }
        HttpService.ajaxRequest(params);
    },

    /**
     * 下架机型组
     * */
    putDownPartsSku : function(id){
        var self = this;

        var ids = [id];

        var params = {};
        params.type = "post";
        params.dataApi = PartsApi.PUT_OUT_EXCAVATOR_SKU;
        params.requestParams = ids;
        params.callback = function(data){
            common.toast(1,PartsText.SUCCESS_PUT_DOWN);
            self.getPartsSkuList();
        }
        HttpService.ajaxRequest(params);
    },
}