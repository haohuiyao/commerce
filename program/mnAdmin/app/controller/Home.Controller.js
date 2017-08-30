/**
 * Created by Administrator on 2017/7/13.
 *
 */

app.controller("HomeCtrl",function($scope,$state,$stateParams){
    HomeController.init($scope,$state,$stateParams);
});

/**
 * 首页控制器
 *
 */
var HomeController =
{
    scope: null,
    state: null,
    stateParams : null,

    currentIndex : null,

    tabs: [{name: "首页", sref: "content", className: "tabed"}],

    dataModel : {
        oldPwd : null,
        newPwd : null,
        confirmPwd : null,
    },

    /**
     *form 表单封装
     * */
    formObj : {
        oldPwd : {
            formDesc : "旧密码",
            formType : "password",
            formDisabled : false,
        },
        newPwd : {
            formDesc : "新密码",
            formType : "password",
            formDisabled : false,
        },
        confirmPwd : {
            formDesc : "确认密码",
            formType : "password",
            formDisabled : false,
        },
    },

    init: function ($scope, $state,$stateParams) {
        this.scope = $scope;
        this.state = $state;
        this.stateParams = $stateParams;
        this.initTab();
        this.initClick();
        this.watchRepeatFinish();
    },

    /**
     * 初始化tab model
     */
    initTab: function () {
        this.scope.tabs = this.tabs;
        this.scope.formObj = this.formObj;
        this.scope.dataModel = this.dataModel;
    },

    /**
     * 初始化点击事件
     */
    initClick: function () {

        var self = this;

        self.scope.onClickRemove = function (index) {
            self.closeTabed(index);
        }
        
        self.scope.onClickSubmitModPwd = function () {
            var formData = [
                {data:self.dataModel.oldPwd,errDesc:textConfig.getText().WARN_OLD_PWD_EMPTY},
                {data:self.dataModel.newPwd,errDesc:textConfig.getText().WARN_NEW_PWD_EMPTY},
                {data:self.dataModel.confirmPwd,errDesc:textConfig.getText().WARN_CONFIRM_PWD_EMPTY},
            ];

            if(fn.formNoEmpty(formData)){
                if(!fn.isSame([self.dataModel.newPwd,self.dataModel.confirmPwd])){
                   common.alert(textConfig.getText().WARN_PWD_UN_SAME);
                    return;
                }

                alert("执行修改操作");

            }
        }
    },

    /**
     * 创建tabs
     */
    createTab: function (name, sref) {
        var self = this, index, isExists = false;

        if (self.tabs.length == 0) {
            self.tabs.push({
                name: name,
                sref: sref,
                className: "tabed"
            });
            self.currentIndex = self.tabs.length-1;
        }
        else {
            for (var i = 0; i < self.tabs.length; i++)
            {
                if (self.tabs[i].name == name)
                {
                    isExists = true;
                    index = i;
                    break;
                }
                else
                {
                    isExists = false;
                    continue;
                }
            }

            if (isExists)
            {
                self.cancelTabed();
                self.tabs[index].className = "tabed";
                self.currentIndex = index;
            }
            else
            {
                self.cancelTabed();

                self.tabs.push({
                    name: name,
                    sref: sref,
                    className: "tabed"
                });
                self.currentIndex = self.tabs.length-1;
            }

        }

        self.scope.tabs = self.tabs;
    },

    /**
     * 切换tab状态 为未选中
     */
    cancelTabed: function () {
        var self = this;

        for (var i = 0; i < self.tabs.length; i++) {
            self.tabs[i].className = "un-tabed";
        }
    },

    /**
     * 关闭tab页
     */
    closeTabed : function (index) {
        var self = this;

        self.tabs.splice(index, 1);
        var sref = self.tabs[self.tabs.length - 1].sref;
        self.state.go(sref);

        self.currentIndex = index-1;
    },

    /**
     * 重置修改密码form表单
     */
    reSetModPwdForm : function(){

        var self = this;
        self.dataModel.oldPwd = '';
        self.dataModel.newPwd = '';
        self.dataModel.confirmPwd = '';

        self.scope.dataModel = self.dataModel;
    },


    /**
     * 监听repeat结束事件
     */
    watchRepeatFinish: function () {

        var self = this;
        self.scope.$on('loadingOver', function (ngRepeatFinishedEvent) {
            self.JqMethods();
        });

    },

    /**
     *jquery方法
     */
    JqMethods: function () {
        var index = 0, tabsWidth = $('.ui-tabs').width(), ulWidth = 0;

        $(".ui-tabs>ul>li").each(function () {
            var self = $(this);
            (function (that) {
                ulWidth = ulWidth + that.width();
            })(self);
        });

        showTabBtn();

        $(window).on('resize', function () {

            Throttle(function () {
                ulWidth = 0;
                tabsWidth = $('.ui-tabs').width();

                $(".ui-tabs ul li").each(function () {
                    var self = $(this);
                    (function (that) {
                        ulWidth = ulWidth + that.width();
                    })(self);

                });

                showTabBtn();
            });

        });

        $(".btn-pre").on('click', function () {

            var prePositionX = $(".btn-pre").offset().left,
                firstLi = $(".ui-tabs ul li").first(),
                firstLiWidth = $(".ui-tabs ul li").first().width(),
                firstLiPositionX = firstLi.offset().left;

            if (firstLiPositionX - firstLiWidth < prePositionX) {
                index = index - 100;
                $(".ui-tabs ul").css("left", -index + 'px');
            }

        });

        $(".btn-next").on('click', function () {

            var nextPositionX = $(".btn-next").offset().left,
                lastLi = $(".ui-tabs ul li").last(),
                lastLiWidth = $(".ui-tabs ul li").last().width(),
                lastLiPositionX = lastLi.offset().left;

            if (nextPositionX < lastLiPositionX + lastLiWidth) {
                index = index + 100;
                $(".ui-tabs ul").css("left", -index + 'px');
            }

        });

        function showTabBtn() {

            if (ulWidth >= tabsWidth - 100) {
                $(".ui-tabs ul").css("min-width", "5000px");
                $(".tab-btn").show();
            }
            else {
                $(".ui-tabs ul").css("min-width", "0px");
                $(".tab-btn").hide();

                $(".ui-tabs ul").css({"min-width": "0px", "left": "0"});

            }
        }

    }
}
