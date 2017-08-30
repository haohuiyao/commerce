/**
 * =========================================================================================================================
 * 
 * params{
 * 	type : post || get
 * 	dataApi : 接口地址
 * 	requestParams : 参数JSON.stringify()
 *  callback : 回调
 * 	errorCallback : 错误回调
 *  failCallback : 失败回调
 * }
 * 
 * =========================================================================================================================
 */
var HttpService = {

    /*
    * 数据请求
    * */
    ajaxRequest : function (params) {

        var Sid = fn.getLocalStorage(BaseKey.getLocalKey().SESSIONID);
        if(Sid == undefined)
        {
            Sid = "";
        }

        var requestParams = {};
        requestParams.Data = JSON.stringify(params.requestParams);
        requestParams.Sid = Sid;

        $.ajax({
            url: params.dataApi,
            async:true,
            type: params.type,
            data: requestParams,
            dataType:"json",
            success: function(data){
                var err = data['code'];
                var errMsg = data['message'];

                if (err != 0)
                {
                    if (errMsg != "")
                    {
                        alert(errMsg);
                    }

                    if(params.errorCallback){
                        params.errorCallback();
                    }
                }
                else
                {
                    params.callback(data["data"]);
                }

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("网络出错！");
            }
        });
    }
}

