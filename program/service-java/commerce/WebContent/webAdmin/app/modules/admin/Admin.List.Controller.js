/**
 * Created by daipengpeng on 2017/7/13.
 * 管理后台主页Controller
 */

app.controller("AdminListCtrl",function($scope,$state){
    AdminListController.init($scope,$state)
});


var AdminListController = {
    
    scope : null,
    state : null,

    adminId : null,

    /**
     * 数据模型
     * */
    dataModel : {
        adminList : [],  //管理员列表
        modalTitle : "", //模态框标题
        adminName : "", //管理员名称
        loginPwd : "", //登录密码
        confirmPwd : "",//确认密码
        adminType : '', //管理员类型
    },

    /**
     *form 表单封装
     * */
    formObj : {
        adminName : {
            formDesc : "管理员名称",
            formType : "text",
            formDisabled : false,
        },
        loginPwd : {
            formDesc : "登录密码",
            formType : "password",
            formDisabled : false,
        },
        confirmPwd : {
            formDesc : "确认登录密码",
            formType : "password",
            formDisabled : false,
        },
        adminType : {
            formDesc : "管理员类型",
            optionArr : []
        }
    },


    /**
     *初始化
     * */
    init : function ($scope,$state) {

        this.scope = $scope;

        this.state = $state;

        common.showLoading();

        this.initDataModel();

        this.getAdminList();

        this.getAdminRole();

        this.bindEvent();
    },

    /**
     * 初始化
     * */
    initDataModel : function(){
        var self = this;

        self.scope.dataModel = self.dataModel;
        self.scope.formObj = self.formObj;
    },


    /**
     * 获取管理员列表
     * */
    getAdminList : function () {

        var self = this;

        PageController.init(self.scope, BaseApi.GET_ADMIN_LIST, {}, function(data){

            PageController.setTotalPage(data.totalCount);
            self.dataModel.adminList.length = 0;

            var arr = data.list;

            for(var i = 0; i < arr.length; i++)
            {
                var itemObj = {};
                itemObj.id = arr[i].id;
                itemObj.name = arr[i].adminName;
                itemObj.role = arr[i].roleNo;
                itemObj.index = (PageController.currentPage - 1) * PageController.num + i + 1;

                self.dataModel.adminList.push(itemObj);
            }

            self.scope.dataModel = self.dataModel;
            self.scope.$apply();

            common.hideLoading();

        })

    },

    /**
     *绑定事件
     * */
    bindEvent : function(){

        var self = this;

        self.scope.$watch('dataModel.adminType',function(data){

        })


        /**
         * 添加管理员
         * */
        self.scope.onClickAdd = function () {
            self.adminId = null;
            self.reSetAdminFormData();
            self.dataModel.modalTitle = '添加管理员'
            common.showModal("adminDetail");
        }

        /**
         * 修改管理员
         * */
        self.scope.onClickItemMod = function (item) {
            self.adminId = item.id;
            self.dataModel.modalTitle = '修改管理员'
            common.showModal("adminDetail");

            self.dataModel.adminName = item.name;
            self.dataModel.adminType = item.role;
            self.dataModel.loginPwd = "";
            self.dataModel.confirmPwd = "";

            self.scope.dataModel = self.dataModel;
        }

        /**
         * 删除管理员
         * */
        self.scope.onClickItemDel = function(id){
            common.confirm(textConfig.getText().CONFIRM_DEL_ADMIN,function(){

                var delIds = [id];
                var requestParams = {};
                requestParams.ids = delIds;

                var params = {};
                params.type = "post";
                params.dataApi = BaseApi.DEL_ADMIN;
                params.requestParams = requestParams;
                params.callback = function(){
                    common.toast(1,textConfig.getText().SUCCESS_DEL_ADMIN);
                    PageController.callApi();
                }
                HttpService.ajaxRequest(params)
            })
        }

        /**
         * 管理员form提交
         * */
        self.scope.onClickSubmitAdminForm = function () {
            var formData = [
                {data:self.dataModel.adminName,errDesc:textConfig.getText().WARN_ADMIN_NAME_EMPTY},
                {data:self.dataModel.loginPwd,errDesc:textConfig.getText().WARN_ADMIN_PWD_EMPTY},
                {data:self.dataModel.confirmPwd,errDesc:textConfig.getText().WARN_CONFIRM_PWD_EMPTY},
                {data:self.dataModel.adminType,errDesc:textConfig.getText().WARN_ADMIN_TYPE_EMPTY},
            ];

            if(fn.formNoEmpty(formData)){
                if(!fn.isSame([self.dataModel.loginPwd,self.dataModel.confirmPwd])){
                    common.alert(textConfig.getText().WARN_PWD_UN_SAME);
                    return;
                }

                if(self.adminId == null){
                    var params = {};
                    params.type = "post";
                    params.dataApi = BaseApi.ADD_ADMIN;
                    params.requestParams = self.getAdminFormData();
                    params.callback = function(data){
                        common.toast(1,textConfig.getText().SUCCESS_ADD_ADMIN);
                        common.hideModal("adminDetail");
                        PageController.callApi();
                    }
                }
                else
                {
                    var params = {};
                    params.type = "post";
                    params.dataApi = BaseApi.MOD_ADMIN;
                    params.requestParams = self.getAdminFormData();
                    params.requestParams.id = self.adminId;
                    params.callback = function(data){
                        common.toast(1,textConfig.getText().SUCCESS_MOD_ADMIN);
                        common.hideModal("adminDetail");
                        PageController.callApi();
                    }
                }

                HttpService.ajaxRequest(params);

            }
        }

    },

    /**
     * 获取管理员form表单数据
     * */
    getAdminFormData : function () {

        var self = this;

        var params = {};
        params.userName = self.dataModel.adminName;
        params.passWord = self.dataModel.loginPwd;
        params.userType = self.dataModel.adminType;

        return params;
    },

    /**
     * 重置form表单数据
     * */
    reSetAdminFormData : function () {

        var self = this;

        self.dataModel.adminName = "";
        self.dataModel.adminType = "";
        self.dataModel.loginPwd = "";
        self.dataModel.confirmPwd = "";

        self.scope.dataModel = self.dataModel;
    },

    /**
     * 获取管理员角色
     * */
    getAdminRole : function(){

        var self = this;

        var params = {};
        params.type = "post";
        params.dataApi = BaseApi.GET_ADMIN_ROLE;
        params.requestParams = {};
        params.callback = function(data){
            var arr = [];
            for(var i = 0; i < data.length; i++)
            {
                var itemObj = {};
                itemObj.optName = data[i].roleName;
                itemObj.optValue = data[i].roleNo;

                arr.push(itemObj)
            }

            self.formObj.adminType.optionArr = arr;

            self.scope.formObj = self.formObj;
            self.scope.$apply();
        }

        HttpService.ajaxRequest(params);

    },
}