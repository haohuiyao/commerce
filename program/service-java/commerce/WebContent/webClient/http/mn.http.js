/* ajax
/* ==========================================================================
 * 封装Ajax
 * make by daipengpeng
 * ============================================================================
 */

var $service = {
	firstReloginFail : true,

	/**
	 * 封装Ajax
	 * @param {Object} type //"post" or "get"
	 * @param {Object} dataApi //api地址
	 * @param {Object} params //参数
	 * @param {Object} callBack //成功回调
	 * @param {Object} errCallBack //失败回调，可不传
	 */

	httpRequest : function(type, dataApi, params, callBack, failCallBack,errCallBack)
	{
		var data = {};
		data.Data = JSON.stringify(params);	
	    $.ajax({
	        url: dataApi,
	        async:true,
	        type: type,
	        data: data,
	        dataType:"json",
	        success: function(data){
//	        	mnWebMain.closeLoading();
	        	var err = data.code;
	        	var errMsg = data.message;
	        	if(err != 0)
	        	{
//	        		
	        			if(failCallBack)
						{
							failCallBack(data)
						}
						else
						{
//							mnWebMain.showProgressDialog(errMsg);
						}
//	        		}
	        	}
	        	else
	        	{
	        		callBack(data.data);
	        	}

		    },
			error: function (data) {
//				mnWebMain.showProgressDialog("网络请求出错")
				if(errCallBack)
				{
					errCallBack(data)
				}
			}
	    });
	},
	
	
};
