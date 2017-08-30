/**
 * Created by daipengpeng on 2017/7/24.
 * 运费模板
 */

app.controller("freightTemplateCtrl",function($scope,$state){
    FreightTemplateController.init($scope,$state)
});


var FreightTemplateController = {


    scope : null,
    state : null,
    freightTemplateId : "",

    dataModel : {
        list : [],
        dispatching : "",
        scope : "",
        baseWeight : "",
        basePrice : "",
        addWeight : "",
        addPrice : "",
        postage : "",
        pattern : "",
        name : "",
        modalTitle : "",
    },

    formObj : {
        name : {
            formDesc : "运费模板名称",
            formType : "text",
            formDisabled : false
        },
        dispatching : {
            formDesc : "配送方式",
            formType : "text",
            formDisabled : false
        },
        scope : {
            formDesc : "配送区域",
            formType : "text",
            formDisabled : false
        },
        baseWeight : {
            formDesc : "首算",
            formType : "text",
            formDisabled : false
        },
        basePrice : {
            formDesc : "首付",
            formType : "text",
            formDisabled : false
        },
        addWeight : {
            formDesc : "续算",
            formType : "text",
            formDisabled : false
        },
        addPrice : {
            formDesc : "续费",
            formType : "text",
            formDisabled : false
        },
        postage : {
            formDesc : "是否包邮",
            optionArr : [{optName:'包邮',optValue:'1'},{optName:'不包邮',optValue:'2'}]
        },
        pattern : {
            formDesc : "计费方式",
            optionArr : [{optName:'按重量',optValue:'1'},{optName:'按体积',optValue:'2'},{optName:'按件数',optValue:'3'}]
        }

    },


    init : function ($scope,$state) {

        var self = this;

        common.showLoading();

        self.scope = $scope;

        self.state = $state;

        self.initDataModel();

        self.bindEvent();

        self.getFreightTemplateList();
    },
    
    initDataModel : function () {

        this.scope.formObj = this.formObj;

    },


    /**
     * 获取运费模板列表
     * */
    getFreightTemplateList : function () {

        var self = this;

        var params = {};

        params.type = "post";
        params.dataApi = ProductApi.GET_FREIGHT_TEMPLATE_LIST;
        params.requestParams = {};
        params.callback = function(data){
            self.dataModel.list.length = 0;

            for(var i = 0; i < data.length; i++)
            {
                var itemObj = {};
                itemObj.index = i+1;
                itemObj.addPrice = data[i].addPrice;
                itemObj.addWeight = data[i].addWeight;
                itemObj.basePrice = data[i].basePrice;
                itemObj.baseWeight = data[i].baseWeight;
                itemObj.dispatching = data[i].dispatching;
                itemObj.fareName = data[i].fareName;
                itemObj.id = data[i].id;
                itemObj.pattern = data[i].pattern;
                itemObj.patternDesc = self.getCostMethodInfo(itemObj.pattern).textDesc;
                itemObj.postage = data[i].postage;
                itemObj.postageDesc = self.getPostageInfo(itemObj.postage).textDesc;
                itemObj.scope = data[i].scope;
                itemObj.updateTime = data[i].updateTime;

                self.dataModel.list.push(itemObj);

            }
            self.scope.dataModel = self.dataModel;
            self.scope.$apply();

            common.hideLoading();
        }

        HttpService.ajaxRequest(params);
    
    },


    /**
     * 绑定事件
     * */
    bindEvent : function () {

        var self = this;

        /**
         * 添加运费模板
         * */
        self.scope.onClickAdd = function () {
            self.dataModel.modalTitle = "添加运费模板";
            self.reSetFormData();
            common.showModal("editProductFreightTemplate");
        }

        /**
         * 修改运费模板
         * */
        self.scope.onClickMod = function(item){
            self.dataModel.modalTitle = "修改运费模板";
            self.reSetFormData();
            self.freightTemplateId = item.id;
            self.dataModel.addPrice = item.addPrice;
            self.dataModel.addWeight = item.addWeight;
            self.dataModel.basePrice = item.basePrice;
            self.dataModel.baseWeight = item.baseWeight;
            self.dataModel.dispatching = item.dispatching;
            self.dataModel.name = item.fareName;
            self.dataModel.scope = item.scope;
            self.dataModel.pattern = item.pattern;
            self.dataModel.postage = item.postage;

            self.scope.dataModel = self.dataModel;

            common.showModal("editProductFreightTemplate");
        };

        /**
         * 删除运费模板
         * */
        self.scope.onClickDel = function(id){
            common.confirm(ProductText.CONFIRM_DEL_FREIGHT_TEMPLATE, function () {
                var params = {};
                var requestParam = {};
                requestParam.id = id;

                params.type = "post";
                params.dataApi = ProductApi.DEL_FREIGHT_TEMPLATE;
                params.requestParams = requestParam;
                params.callback = function(data){
                    common.toast(1,ProductText.SUCCESS_DEL_FREIGHT_TEMPLATE);
                    self.getFreightTemplateList();
                }

                HttpService.ajaxRequest(params);
            })
        };


        /**
         * 提交运费模板编辑
         * */
        self.scope.submitEdit = function(){
            var formData = self.getFormData();
            if(formData){
                if(fn.isEmpty(self.freightTemplateId))
                {
                    var params = {};
                    params.type = "post";
                    params.dataApi = ProductApi.ADD_FREIGHT_TEMPLATE;
                    params.requestParams = formData;
                    params.callback = function(){
                        common.hideModal("editProductFreightTemplate");
                        common.toast(1,ProductText.SUCCESS_ADD_FREIGHT_TEMPLATE);
                        self.getFreightTemplateList();
                    }
                }
                else
                {
                    formData.id = self.freightTemplateId;
                    var params = {};
                    params.type = "post";
                    params.dataApi = ProductApi.MOD_FREIGHT_TEMPLATE;
                    params.requestParams = formData;
                    params.callback = function(){
                        common.hideModal("editProductFreightTemplate");
                        common.toast(1,ProductText.SUCCESS_MOD_FREIGHT_TEMPLATE);
                        self.getFreightTemplateList();
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
        params.name = self.dataModel.name;
        params.postage = self.dataModel.postage;
        params.pattern = self.dataModel.pattern;
        params.dispatching = self.dataModel.dispatching;
        params.baseWeight = self.dataModel.baseWeight;
        params.basePrice = self.dataModel.basePrice;
        params.addWeight = self.dataModel.addWeight;
        params.addPrice = self.dataModel.addPrice;
        params.scope = self.dataModel.scope;

        var dataArr = [
            {data:params.name,errDesc:ProductText.WARN_FREIGHT_TEMPLATE_NAME_EMPTY},
            {data:params.postage,errDesc:ProductText.WARN_FREIGHT_TEMPLATE_POSTAGE_EMPTY},
            {data:params.pattern,errDesc:ProductText.WARN_FREIGHT_TEMPLATE_PATTERN_EMPTY},
            {data:params.dispatching,errDesc:ProductText.WARN_FREIGHT_TEMPLATE_DISPATCHING_EMPTY},
            {data:params.baseWeight,errDesc:ProductText.WARN_FREIGHT_TEMPLATE_BASE_WEIGHT_EMPTY},
            {data:params.basePrice,errDesc:ProductText.WARN_FREIGHT_TEMPLATE_BASE_PRICE_EMPTY},
            {data:params.addWeight,errDesc:ProductText.WARN_FREIGHT_TEMPLATE_ADD_WEIGHT_EMPTY},
            {data:params.addPrice,errDesc:ProductText.WARN_FREIGHT_TEMPLATE_ADD_PRICE_EMPTY},
            {data:params.scope,errDesc:ProductText.WARN_FREIGHT_TEMPLATE_SCOPE_EMPTY},
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

        self.freightTemplateId = "";
        self.dataModel.addPrice = "";
        self.dataModel.addWeight = "";
        self.dataModel.basePrice = "";
        self.dataModel.baseWeight = "";
        self.dataModel.dispatching = "";
        self.dataModel.name = "";
        self.dataModel.scope = "";
        self.dataModel.pattern = "";
        self.dataModel.postage = "";

        self.scope.dataModel = self.dataModel;
    },



    /**
     *获取运费计费方式
     * */
    getCostMethodInfo : function(type){
        var params = {};
        if(type == 1)
        {
            params.textDesc = "按重量(kg)"
        }
        else if(type == 2)
        {
            params.textDesc = "按体积(m^3)"
        }
        else
        {
            params.textDesc = "按件数(个)"
        }

        return params;
    },

    /**
     *获取包邮信息
     * */
    getPostageInfo : function(type){
        var params = {};
        if(type == 1)
        {
            params.textDesc = "包邮"
        }
        else if(type == 2)
        {
            params.textDesc = "不包邮"
        }
        else
        {
            params.textDesc = "不存在"
        }

        return params;
    },


}
