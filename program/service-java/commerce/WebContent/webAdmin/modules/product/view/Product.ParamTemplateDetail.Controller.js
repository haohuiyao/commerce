/**
 * Created by Administrator on 2017/7/23.
 * 参数模板详情
 */

app.controller('ProductParamTemplateDetailCtrl',function($scope,$state,$stateParams){
    ProductParamTemplateDetailController.init($scope,$state,$stateParams);
})

var ProductParamTemplateDetailController = {

    scope : null,
    state : null,
    templateId : "",
    paramsItemId : "",

    dataModel : {
        templateContent : [],
        templateName : "",
        paramTitle : "",
        paramHint : "",
        paramType : "",
        paramOptional : "",
        paramStatus : "",
        paramOptionArr : [],
        isShowParamOption : false,//是否显示选项
        modalTitle : "",
    },

    formObj : {
        templateName : {
            formDesc : "模板名称",
            formType : "text",
            formDisabled : false
        },

        paramTitle : {
            formDesc : "参数标题",
            formType : "text",
            formDisabled : false
        },
        paramHint : {
            formDesc : "参数提示",
            formType : "text",
            formDisabled : false
        },
        paramType : {
            formDesc : "参数类型",
            optionArr : [{optName:'输入类型',optValue:'1'},{optName:'选择类型',optValue:'2'}]
        },
        paramOptional : {
            formDesc : "是否必填",
            optionArr : [{optName:'必填',optValue:'0'},{optName:'非必填',optValue:'1'}]
        },
        paramStatus : {
            formDesc : "是否显示",
            optionArr : [{optName:'显示',optValue:'1'},{optName:'隐藏',optValue:'0'}]
        }
    },

    /**
     * 初始化
     * */
    init : function($scope,$state,$stateParams){

        common.showLoading();

        this.scope = $scope;

        this.state = $state;

        this.initDataModel();

        this.bindEvent();

        if(!fn.isEmpty($stateParams.id)){
            this.templateId = $stateParams.id;
            this.getTemplateDetail();
        }

    },

    initDataModel : function(){
        var self = this;

        self.scope.formObj = self.formObj;
        self.scope.dataModel = self.dataModel;

        common.hideLoading();
    },

    /**
     * 获取参数模板详情
     * */
    getTemplateDetail : function(){

        var self = this;

        var requestParam = {};
        requestParam.templateId = self.templateId;

        var params = {};
        params.type = "post";
        params.dataApi = ProductApi.GET_PARAM_TEMPLATE_DETAIL;
        params.requestParams = requestParam;
        params.callback = function(data){

            self.dataModel.templateName = data.name;
            self.dataModel.templateContent.length = 0;
            var arr = JSON.parse(data.content);
            for(var i = 0; i < arr.length; i++)
            {
                var itemObj = {};
                itemObj.id = arr[i].id;
                itemObj.type = arr[i].type;
                itemObj.optional = arr[i].optional;
                itemObj.name = arr[i].name;
                itemObj.hint = arr[i].hint;
                itemObj.isShow = arr[i].isShow;
                itemObj.options = arr[i].options;
                itemObj.revisable = false

                self.dataModel.templateContent.push(itemObj);
            }
            self.scope.dataModel = self.dataModel;
            self.scope.$apply();
        }
        HttpService.ajaxRequest(params);
    },

    /**
     *绑定事件
    * */
    bindEvent : function(){

        var self = this;

        self.scope.$watch('dataModel.paramType', function (value) {
            if(value == 2)
            {
                self.dataModel.isShowParamOption = true;
            }
            else
            {
                self.dataModel.isShowParamOption = false;
            }

            self.scope.dataModel = self.dataModel;
        })

        /**
         * 添加一个参数
         * */
        self.scope.addParamItem = function () {
            self.paramsItemId = "";
            self.dataModel.modalTitle = "添加参数";
            self.reSetFormData();
            common.showModal("editProductParamItem");
        }

        /**
         * 修改一个参数
         */
        this.scope.onClickMod = function(item){

            self.reSetFormData();
            self.paramsItemId = item.id;
            self.dataModel.modalTitle = "修改参数";
            self.dataModel.paramType = item.type;
            self.dataModel.paramOptional = item.optional;
            self.dataModel.paramTitle = item.name;
            self.dataModel.paramHint = item.hint;
            self.dataModel.paramStatus = item.isShow;
            self.dataModel.paramOptionArr = item.options;

            common.showModal("editProductParamItem");
            self.scope.dataModel = self.dataModel;

        };

        /**
         * 删除一个参数
         * */
        self.scope.onClickDel = function(index){
            common.confirm(ProductText.CONFIRM_DEL_PARAM_ITEM,function(){
                self.dataModel.templateContent.splice(index,1);
                self.scope.dataModel = self.dataModel;
                self.scope.$apply();
            })
        }

        /**
         * 添加一个选项
         * */
        self.scope.addOptionItem = function(){
            var item = {};
            item.name = null;
            self.dataModel.paramOptionArr.push(item);
            self.scope.dataModel = self.dataModel;
        }

        /**
         * 删除参数中的选项
         */
        this.scope.delOptionItem = function(index){
            self.dataModel.paramOptionArr.splice(index, 1);
            self.scope.dataModel = self.dataModel;
        }

        /**
         * 提交参数编辑
        * */
        self.scope.submitEditParamItem = function(){
            var paramsItem = self.getFormData();
            if(paramsItem){
                if(fn.isEmpty(self.paramsItemId))
                {
                    //添加
                    var timestampId = Date.parse(new Date());
                    paramsItem.id = timestampId;
                    self.dataModel.templateContent.push(paramsItem);
                    console.log(JSON.stringify(self.dataModel.templateContent))
                    self.scope.dataModel = self.dataModel;
                    common.hideModal("editProductParamItem");
                }
                else
                {
                    //修改
                    for(var n = 0; n < self.dataModel.templateContent.length; n++)
                    {
                        if(self.dataModel.templateContent[n].id == self.paramsItemId)
                        {
                            paramsItem.id = self.paramsItemId;
                            self.dataModel.templateContent.splice(n,1,paramsItem);
                            common.hideModal("editProductParamItem");
                            self.scope.dataModel = self.dataModel;
                        }
                    }
                }
            }
        };

        /**
         * 保存参数模板
         * */
        self.scope.saveTemplate = function(){
            var templateParams = {};
            templateParams.name = self.dataModel.templateName;
            templateParams.content = JSON.stringify(self.dataModel.templateContent);

            if(fn.isEmpty(self.dataModel.templateName))
            {
                common.alert("请填写参数模板的名称");
                return;
            }

            if(self.dataModel.templateContent.length == 0){
                common.alert("请填写参数模板的内容");
                return
            }

            if(fn.isEmpty(self.templateId)){
                //添加
                var params = {};
                params.type = "post";
                params.dataApi = ProductApi.ADD_PARAM_TEMPLATE;
                params.requestParams = templateParams;
                params.callback = function(data){
                    HomeController.closeTabed(HomeController.currentIndex);
                }
                HttpService.ajaxRequest(params)
            }
            else
            {
                //修改
                templateParams.templateId = self.templateId;
                var params = {};
                params.type = "post";
                params.dataApi = ProductApi.MOD_PARAM_TEMPLATE;
                params.requestParams = templateParams;
                params.callback = function(data){
                    HomeController.closeTabed(HomeController.currentIndex)
                }
                HttpService.ajaxRequest(params)
            }
        }
    },

    /**
     * 获取表单数据
     * */
    getFormData : function () {

        var self = this;

        var params = {};
        params.type = self.dataModel.paramType;
        params.optional = self.dataModel.paramOptional;
        params.name = self.dataModel.paramTitle;
        params.hint = self.dataModel.paramHint;
        params.isShow = self.dataModel.paramStatus;
        params.options = self.dataModel.paramOptionArr;
        params.revisable = true;

        var dataArr = [
            {data:params.type,errDesc:ProductText.WARN_PARAM_ITEM_TYPE_EMPTY},
            {data:params.optional,errDesc:ProductText.WARN_PARAM_ITEM_OPTIONAL_EMPTY},
            {data:params.name,errDesc:ProductText.WARN_PARAM_ITEM_NAME_EMPTY},
            {data:params.hint,errDesc:ProductText.WARN_PARAM_ITEM_HINT_EMPTY},
            {data:params.isShow,errDesc:ProductText.WARN_PARAM_ITEM_STATUS_EMPTY},
        ]

        if(fn.formNoEmpty(dataArr))
        {
            if(params.type == 1)
            {
                return params;
            }
            else
            {
                if(params.options.length != 0){
                    return params;
                }
                common.alert(ProductText.WARN_PARAM_ITEM_OPTION_EMPTY);
                return false;
            }
        }

        return false;

    },

    /**
     * 重置form表单数据
     * */
    reSetFormData : function () {

        var self = this;

        self.dataModel.paramType = "";
        self.dataModel.paramOptional = "";
        self.dataModel.paramTitle = "";
        self.dataModel.paramHint = "";
        self.dataModel.paramStatus = "";
        self.dataModel.paramOptionArr = [];
        self.scope.dataModel = self.dataModel;
    },

}