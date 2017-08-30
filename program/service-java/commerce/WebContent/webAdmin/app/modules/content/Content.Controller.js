/**
 * Created by daipengpeng on 2017/7/13.
 * 管理后台主页Controller
 */

app.controller("ContentCtrl",function($scope){
    ContentController.init($scope)
});


var ContentController = {
    
    scope : null,
    state : null,
    /**
     *初始化
     * */
    init : function ($scope,$state) {

        this.scope = $scope;

        this.state = $state;
    }
}