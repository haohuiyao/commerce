/**
 * Created by Administrator on 2017/7/14.
 */
/**
 * meeno app
 * ================================
 * make by meeno soft v4.0
 *
 */

//var loadModuleConfig = function(callback){
//	var index = 0;
//	var requestParams = {};
//	var params = {};
//	params.type = 'post';
//	params.dataApi = 'http://140.206.112.170:9201/sosoapi-web/pass/mock/3/user/getModules.do';
//	params.requestParams = requestParams;
//	params.callback = function (data) {
//		for(var n = 0; n <data.list.length; n++)
//		{
//			var test = "./modules/" + data.list[n].modulesName + '/' + data.list[n].modulesName + 'Config.js';
//			$.getScript(test,function(){
//				index = index + 1;
//				if(index == data.list.length)
//				{
//					callback();
//				}
//			});
//		}
//	}
//	HttpService.ajaxRequest(params);
//};

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
		var requestParams = {};
		var params = {};
		params.type = 'post';
		params.dataApi = 'http://140.206.112.170:9201/sosoapi-web/pass/mock/3/user/getRouteInfo.do';
		params.requestParams = requestParams;
		params.callback = function (data) {
			data = route.data;

			for (var i = 0; i < data.list.length; i++) {
				for(var n = 0; n < data.list[i].childs.length; n++)
				{
					var item = data.list[i].childs[n];

					var loader = function($ocLazyLoad)
					{
						return $ocLazyLoad.load({
							files: arguments.callee.controllerUrl
						});
					};

					loader.controllerUrl = item.controllerUrl;

					var tab = function(){
						HomeController.createTab(arguments.callee.tabName, arguments.callee.tabState);
					}
					tab.tabName = item.title;
					tab.tabState =item.state;

					var itemObj = {
						"params" : item.params,
						"name": item.state,
						"url": item.url,
						"templateUrl": item.templateUrl,
						"controller": item.controller,
						"resolve" : {
							deps : [
								"$ocLazyLoad", loader
							],
							routed: tab
						},
					}
					$stateProvider.state(itemObj.name, itemObj);
				}
			}
			$urlRouterProvider.otherwise("/content");
		}

		//loadModuleConfig(function(){
		//	HttpService.ajaxRequest(params);
		//});

		HttpService.ajaxRequest(params);
	}

]);