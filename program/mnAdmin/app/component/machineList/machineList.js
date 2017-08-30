/**
 * Created by Administrator on 2017/7/27.
 */
app.directive('machineList', function() {
    return {
        restrict : 'AE',
        templateUrl : 'app/component/machineList/machineList.html',
        replace : true,
        scope : {
            machineBrandObj : "@",
            machineBrandId : "=machineBrandId",
            machineDetail : "@",
            relArr : "=relArr",
        },
        link : function(scope, element, attrs)
        {
            scope.machineBrandObj = {
                formDesc : "机型品牌：",
                optArr : [],
                api: PartsApi.GET_BRAND_BY_CLASSIFY,
                optNameKey:"name",
                optValueKey:"id",
                fixParam : {id:144},
            }
            var getMachineByBrand = function(){
                var requestParams = {};
                requestParams.id = scope.machineBrandId;

                var params = {};
                params.type = "post";
                params.dataApi = PartsApi.GET_PRODUCT_BY_BRAND;
                params.requestParams = requestParams;
                params.callback = function(data){
                    scope.machineDetail = [data.brand];
                    for(var i = 0; i < scope.machineDetail.length; i++)
                    {
                        scope.machineDetail[i].isSel = false;
                        for(var n = 0; n < scope.machineDetail[i].childItem.length; n++)
                        {
                            scope.machineDetail[i].childItem[n].isSel = false;
                            for(var m = 0; m < scope.machineDetail[i].childItem[n].childItem.length; m++)
                            {
                                scope.machineDetail[i].childItem[n].childItem[m].isSel = false;
                            }
                        }
                    }

                    for(var i = 0; i < scope.machineDetail.length; i++)
                    {
                        for(var k = 0; k < scope.relArr.length; k++)
                        {
                            if(scope.machineDetail[i].id == scope.relArr[k].id && scope.machineDetail[i].type == scope.relArr[k].objType){

                                scope.machineDetail[i].isSel = true;
                            }
                        }
                        //scope.machineDetail[i].isSel = false;
                        for(var n = 0; n < scope.machineDetail[i].childItem.length; n++)
                        {
                            for(var k = 0; k < scope.relArr.length; k++)
                            {
                                if(scope.machineDetail[i].childItem[n].id == scope.relArr[k].id && scope.machineDetail[i].childItem[n].type == scope.relArr[k].objType){
                                    scope.machineDetail[i].childItem[n].isSel = true;
                                }
                            }
                            //scope.machineDetail[i].childItem[n].isSel = false;
                            for(var m = 0; m < scope.machineDetail[i].childItem[n].childItem.length; m++)
                            {
                                for(var k = 0; k < scope.relArr.length; k++)
                                {
                                    if(scope.machineDetail[i].childItem[n].childItem[m].id == scope.relArr[k].id && scope.machineDetail[i].childItem[n].childItem[m].type == scope.relArr[k].objType){
                                        scope.machineDetail[i].childItem[n].childItem[m].isSel = true;
                                    }
                                }
                                //scope.machineDetail[i].childItem[n].childItem[m].isSel = false;
                            }
                        }
                    }


                    scope.$apply();
                }
                HttpService.ajaxRequest(params);
            }

            scope.$watch('machineBrandId',function(value){

                if(value == undefined || value == null || value == "")
                {
                    return;
                }
                getMachineByBrand();

            })

            /**
             * 选中品牌通用
             * */
            scope.selBrand = function(id)
            {
                for(var i = 0; i < scope.machineDetail.length; i++){
                    if(id == scope.machineDetail[i].id){
                        scope.machineDetail[i].isSel = !scope.machineDetail[i].isSel;//选中品牌
                        //设置品牌下所有机型系列、具体机型不选
                        for(var n = 0; n < scope.machineDetail[i].childItem.length; n++)
                        {
                            scope.machineDetail[i].childItem[n].isSel = false;
                            for(var m = 0; m < scope.machineDetail[i].childItem[n].childItem.length; m++)
                            {
                                scope.machineDetail[i].childItem[n].childItem[m].isSel = false;
                            }
                        }
                    }
                }
                getBrandArr();
            }

            /**
             * 选中系列通用
             * */
            scope.selGroup = function(brandId,groupId){

                for(var i = 0; i < scope.machineDetail.length; i++)
                {
                    /**
                     * 如果选中了系列通用，则下方的机型都不能选
                     * */
                    if(brandId == scope.machineDetail[i].id){
                        if(scope.machineDetail[i].isSel){
                            return;
                        }
                        else
                        {
                            for(var n = 0; n < scope.machineDetail[i].childItem.length; n++)
                            {
                                if(scope.machineDetail[i].childItem[n].id == groupId)
                                {
                                    scope.machineDetail[i].childItem[n].isSel = !scope.machineDetail[i].childItem[n].isSel;
                                    for(var m = 0; m < scope.machineDetail[i].childItem[n].childItem.length; m++)
                                    {
                                        scope.machineDetail[i].childItem[n].childItem[m].isSel = false;
                                    }
                                }
                            }

                        }
                    }
                }
                getBrandArr();
            }

            /**
             * 选中具体机型
             * */
            scope.selModel = function(brandId,groupId,modelId){
                for(var i = 0; i < scope.machineDetail.length; i++)
                {
                    if(brandId == scope.machineDetail[i].id)
                    {
                        if(scope.machineDetail[i].isSel){
                            return;
                        }

                        for(var n = 0; n < scope.machineDetail[i].childItem.length; n++)
                        {
                            if(scope.machineDetail[i].childItem[n].id == groupId)
                            {
                                if(scope.machineDetail[i].childItem[n].isSel){
                                    return;
                                }
                                for(var m = 0; m < scope.machineDetail[i].childItem[n].childItem.length; m++)
                                {
                                    if(scope.machineDetail[i].childItem[n].childItem[m].id == modelId)
                                    {
                                        scope.machineDetail[i].childItem[n].childItem[m].isSel = !scope.machineDetail[i].childItem[n].childItem[m].isSel;
                                    }
                                }
                            }
                        }
                    }
                }
                getBrandArr()
            }


            /**
             * 获取关系数组
             * */
            var getBrandArr = function (){
                var arr = []
                for(var i = 0; i < scope.machineDetail.length; i++)
                {
                    if(scope.machineDetail[i].isSel){
                        var obj = {};
                        obj.objType = scope.machineDetail[i].type;
                        obj.id = scope.machineDetail[i].id;
                        arr.push(obj);
                        continue;
                    }
                    for(var n = 0; n < scope.machineDetail[i].childItem.length; n++)
                    {
                        if(scope.machineDetail[i].childItem[n].isSel){
                            var obj = {};
                            obj.objType = scope.machineDetail[i].childItem[n].type;
                            obj.id = scope.machineDetail[i].childItem[n].id;
                            arr.push(obj);
                            continue;
                        }
                        for(var m = 0; m < scope.machineDetail[i].childItem[n].childItem.length; m++)
                        {
                            if(scope.machineDetail[i].childItem[n].childItem[m].isSel){
                                var obj = {};
                                obj.objType = scope.machineDetail[i].childItem[n].childItem[m].type;
                                obj.id = scope.machineDetail[i].childItem[n].childItem[m].id;
                                arr.push(obj);
                            }
                        }
                    }
                }

                scope.relArr = arr;

            }
        }
    };
});