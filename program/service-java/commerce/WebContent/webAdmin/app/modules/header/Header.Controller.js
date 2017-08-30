/**
 * Created by daipengpeng on 2017/7/12.
 * 公用头部控制器
 */

app.controller("HeaderController",function($scope){
    HeaderController.init($scope);
})

var HeaderController =
{
    scope : null,

    init : function($scope)
    {
        this.scope = $scope;

        this.setHeaderInfo();

        this.bindEvent();
    },

    setHeaderInfo : function()
    {
        this.scope.adminName = fn.getLocalStorage(BaseKey.getLocalKey().USERNAME);
    },

    bindEvent : function()
    {
        var self = this;

        self.scope.onClickToModifyPwd = function()
        {
            HomeController.reSetModPwdForm();
            common.showModal("modPwd");
        };

        self.scope.onClickLogout = function()
        {
            location.replace("login.html");
            event.returnvalue = false;
        };
    },
}
