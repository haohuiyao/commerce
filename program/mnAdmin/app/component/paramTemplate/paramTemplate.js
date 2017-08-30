/**
 * Created by Administrator on 2017/7/22.
 * 参数模板
 */

app.directive("paramTemplate", function() {
    return {
    	restrict:'AE',
    	replace : true,
    	scope : {
    		paramValue : "=paramValue",
			templateId : "=templateId",
			templateDetail : "=templateDetail",
    	},
        templateUrl:'app/component/paramTemplate/paramTemplate.html',
       	link:function(scope,element,attrs)
       	{
			var paramTemplate = {

				//获取参数模板的详情
				getTemplateDetail : function(){

					var self = this;

					var requestParam = {};
					requestParam.templateId = scope.templateId;

					var params = {};
					params.type = "post";
					params.dataApi = ProductApi.GET_PARAM_TEMPLATE_DETAIL;
					params.requestParams = requestParam;
					params.callback = function(data)
					{

						scope.templateDetail = [];
						var arr = JSON.parse(data.content)
						for(var i = 0; i < arr.length; i++)
						{
							if(arr[i].type == 1)
							{
								var itemObj = {};
								itemObj.formDesc = arr[i].name;
								itemObj.formType = "text";
								itemObj.formDisabled = false;

								var paramItem = {};
								var paramValueItem = scope.paramValue[arr[i].id];

								paramItem.formModel = (paramValueItem == undefined)?'':paramValueItem.value;
								paramItem.formObj = itemObj;
								paramItem.type = 1;
								paramItem.id = arr[i].id
								paramItem.optional = arr[i].optional;
								paramItem.name = arr[i].name;

								scope.templateDetail.push(paramItem);
							}
							else if(arr[i].type == 2)
							{
								var optArr = [];
								for(var n = 0; n < arr[i].options.length; n++)
								{
									var obj = {};
									obj.optValue = arr[i].options[n].name;
									obj.optName = arr[i].options[n].name;
									optArr.push(obj)
								}
								var itemObj = {};
								itemObj.formDesc = arr[i].name;
								itemObj.optionArr = optArr;

								var paramItem = {};
								var paramValueItem = scope.paramValue[arr[i].id];

								paramItem.formModel = (paramValueItem == undefined)?'':paramValueItem.value;
								paramItem.formObj = itemObj;
								paramItem.type = 2;
								paramItem.id = arr[i].id
								paramItem.optional = arr[i].optional
								paramItem.name = arr[i].name;

								scope.templateDetail.push(paramItem);

							}

						}
						scope.$apply();
					}

					HttpService.ajaxRequest(params)

				}
			};

			scope.$watch('templateId',function(value){
				if(!fn.isEmpty(value)){
					paramTemplate.getTemplateDetail();
				}
			});
		}
    }
});