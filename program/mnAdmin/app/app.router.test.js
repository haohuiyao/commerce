/**
 * Created by Administrator on 2017/7/14.
 */
/**
 * meeno app
 * ================================
 * make by meeno soft v4.0
 *
 */

app.config([

    "$provide",
    "$compileProvider",
    "$controllerProvider",
    "$filterProvider",
    function ($provide, $compileProvider, $controllerProvider, $filterProvider)
    {
        app.controller = $controllerProvider.register;
        app.directive = $compileProvider.directive;
        app.filter = $filterProvider.register;
        app.factory = $provide.factory;
        app.service = $provide.service;
        app.constant = $provide.constant;
    }

]);

app.config([

    "$stateProvider",
    "$urlRouterProvider",
    function($stateProvider, $urlRouterProvider)
    {
        $urlRouterProvider.otherwise("/content");

        $stateProvider

        //首页(学校管理员/系统管理员-->公用部分)
        .state("content",{
        url : "/content",
        templateUrl : "app/view/content.html",
        controller : "ContentController",
        resolve : {
            deps : [
                "$ocLazyLoad", function($ocLazyLoad)
                {
                    return $ocLazyLoad.load("app/controller/ContentController.js");
                }
            ]
        }
    })

    //权限配置
        .state("action",{
            url : "/action",
            templateUrl : "app/view/action.html",
            controller : "ActioinController",
            resolve : {
                deps : [
                    "$ocLazyLoad", function($ocLazyLoad)
                    {
                        return $ocLazyLoad.load("app/controller/ActioinController.js");
                    }
                ]
            }
        })

        //管理员列表
        .state("useradmin",{
            url : "/useradmin",
            templateUrl : "app/view/admin/user.admin.html",
            controller : "UserAdminController",
            resolve : {
                deps : [
                    "$ocLazyLoad", function($ocLazyLoad)
                    {
                        return $ocLazyLoad.load("app/controller/User.adminController.js");
                    }
                ]
            }
        })
    }

]);

