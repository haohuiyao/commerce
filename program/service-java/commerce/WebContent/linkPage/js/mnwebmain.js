//js 检错工具
//onerror = function(msg, url, l)
//{
//	var errMsg = "Error: " + msg + "\n";
// 	errMsg += "URL: " + url + "\n";
// 	errMsg += "Line: " + l + "\n";
//
// 	alert(errMsg);
// 	return true;
//}

//原生接口
var mnInited = false;

function initMN(cbBridgeInited){
	if (mnInited)
	{
		return;
	}
	
	mnInited = true;
	
	
	//判断是否已经联网
	function checkInternetStatus(callback)
	{
		if(IsPC()){
			callback(JSON.stringify({
				error : 0,
				data : {
					status : 1
				}
			}));
			return ;
		}
		
		var data = {
			"method" : "checkInternetStatus",
			"data" : {
			}
		};
		WebViewJavascriptBridge.callHandler('mnSystemHandler', JSON.stringify(data),callback);
	}
	
	//打开二级弹框
	function popupModal(url,callback)
	{
		if(IsPC()){
			callback(
				JSON.stringify(
					{
						error : 0,
						data : {
							params : {
								data : 1
							}
						}

					}
				)
			);

			return ;
		}
		var data = {
			"method" : "popupModal",
			"data" : {
				"url" : url
			}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data),callback);
	}
	
	//关闭二级弹框
	function  closeModal(params) {
		if(IsPC()){
			
			return ;
		}
		
		var data = {
			"method" : "closeModal",
			"data" : {
				"params" : params
			}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data));
	}
	
	//在APP外打开链接
	function openUrl(url) {
		if(IsPC()){
			return ;
		}
		
		var data = {
			"method" : "openUrl",
			"data" : {
				"url" : url
			}
		};
		WebViewJavascriptBridge.callHandler('mnSystemHandler',JSON.stringify(data));
	}

	//打电话
	function callPhone(phoneNo)
	{
		if(IsPC()){
			return ;
		}

		var data = {
			"method" : "callPhone",
			"data" : {
				"phoneNo" : phoneNo
			}
		};
		WebViewJavascriptBridge.callHandler('mnSystemHandler', JSON.stringify(data));
	}

	/**
	 *
	 * 设置推送别名
	 *
	 */
	function setAlias(phoneNo)
	{
		if(IsPC())
		{
			return ;
		}

		var data = {
			"method" : "setAlias",
			"data" : {
				"telephone" : phoneNo
			}
		};
		WebViewJavascriptBridge.callHandler('mnPushHandler', JSON.stringify(data));

	}

	/**
	 *
	 * 清空别名
	 */
	function emptyAlias()
	{
		if(IsPC())
		{
			return ;
		}

		var data = {
			"method" : "emptyAlias",
			"data" : {
			}
		};
		WebViewJavascriptBridge.callHandler('mnPushHandler', JSON.stringify(data));
	}

	/*
	 * 更新NavBar状态
	 * position 左中右菜单位置 0 1 2
	 * index：菜单下标
	 * isHide：0不隐藏，1隐藏,
	 * type：0文本，1图片,
	 * param：文本名，图片名
	 * param 是相对type而言的 ，如果type 0 则param 要是文本（包括数字和汉字） 如果type 1 则param 是图片的url
	 * */
	function updateNavBar(params)
	{
		if(!IsPC())
		{
			var data = {
				"method" : "updateNavBar",
				"data" : {
					"position" : params.position,
					"index" : params.index,
					"isHide" : params.isHide,
					"type" : params.type,
					"param" : params.param
				}
			};
			WebViewJavascriptBridge.send(JSON.stringify(data));
		}
		else
		{
			return ;
		}
	}

	//baseParam 包含 url ,isHideNavBar,titleType , leftType 。 格式是 ： var baseParam = {"url" : "view/userViews/index.html","isHideNavBar" : 0 , "titleType" : 0}
	// isHideNavBar 有 0 和 1 参数  0 表示  不隐藏头部。 1 表示隐藏头部。
	//titleType 有 0 和 1 参数  0 表示  表示普通的（即是有文字或者图标标题）。 1  表示 有输入框的（比如搜索头部）。
	//leftParam 格式是 var leftParam = [{"leftType":0, "type" : 1 ,"param" : "btn_back_nor"}]; //type  0 是文字  1是图片   //leftType 0 表示返回键  //1 表示自定义
	//centerParam 格式是 var centerParam = [{"type" : 0,"param" : "产品详细"}];//type  0 是文字  1是图片
	//rightParam 格式是 var rightParam = [{"type":1,"param":"btn_collect_nor"},{"type":1,"param":"btn_share_nor"}];}//type  0 是文字  1是图片
	//打开新页面，可设置页面头信息
//	function openWebViewController(baseParam,leftParam,centerParam,rightParam,color){
//		if(!IsPC())
//		{
//			if(color == undefined)
//			{
//				color = "";
//			}
//
//			var data = {
//				"method" : "openWebViewController",
//				"data" : {
//					"url" : baseParam.url,
//					"isHideNavBar" : baseParam.isHideNavBar,
//					"titleType" : baseParam.titleType,//0 表示普通的  1 表示 有输入框的
//					"leftParam" : leftParam ,
//					"centerParam" : centerParam,
//					"rightParam" : rightParam,
//					"navBgColor" : color,
//					"pageId" : baseParam.url
//				}
//			};
//			WebViewJavascriptBridge.send(JSON.stringify(data));
//
//		}
//		else
//		{
//			location.href = 'http://192.168.0.123:8088/wapei/program/wapei/' + baseParam.url;
//		}
//	}

	function openWebViewController(baseParam,leftParam,centerParam,rightParam,color,bottomParam,params){
//		alert(params)
//		if(params == undefined || params == null || params == 'null')
//		{
//			alert(1)
//			params = '';
//		}
		
		if(!IsPC())
		{
			if(color == undefined)
			{
				color = "";
			}
			
			if(bottomParam == undefined)
			{
				bottomParam = {};
				bottomParam.isExistBottomBar = 0;
				bottomParam.bottomLeftParam = [];
				bottomParam.bottomRightParam = [];
			}
			
			if(bottomParam.isExistBottomBar == undefined)
			{
				bottomParam.isExistBottomBar = 0;
			}
			
			if(bottomParam.bottomLeftParam == undefined)
			{
				bottomParam.bottomLeftParam = [];
			}
			
			if(bottomParam.bottomRightParam == undefined)
			{
				bottomParam.bottomRightParam = [];
			}
			
			var pageId = (baseParam.url).split('?')[0];
			
			var data = 
			{
				"method" : "openWebViewController",
				"data" : {
					"url" : baseParam.url,
					"isHideNavBar" : baseParam.isHideNavBar,
					"titleType" : baseParam.titleType,//0 表示普通的  1 表示 有输入框的
					"searchContent" : baseParam.searchContent,
					"leftParam" : leftParam ,
					"centerParam" : centerParam,
					"rightParam" : rightParam,
					"navBgColor" : color,
					"pageId" : pageId,
					//底部参数
					"isExistBottomBar" : bottomParam.isExistBottomBar,
					"bottomLeftParam" : bottomParam.bottomLeftParam,
					"bottomRightParam" : bottomParam.bottomRightParam,
					//携带参数
					"params" : params
				}
			}
			
			WebViewJavascriptBridge.send(JSON.stringify(data));
		}
		else
		{
//			location.href = 'http://127.0.0.1:8020/wapei/' + baseParam.url;
            location.href = 'http://192.168.0.114:8020/wapei/'+ baseParam.url;
		}
	}

	//关闭多个界面
	function closeMoreViewController(pageIds)
	{
		if(IsPC())
		{
			return ;
		}

		var data = {
			"method" : "closeMoreViewController",
			"data" : {
				"ids" : pageIds
			}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data));
		
	}
	
	//更新头部按钮右上角数字或图片
	
	/**
	 *position 左中右菜单位置   0 1 2 左中右
	 *index  需要更新的按钮的下表
	 * isHide 隐藏corner
	 * type 0 文本  1 图片
	 * param  相对于type 而言
	 */
	
	function updateNavBarCorner(updateParam)
	{
		if(IsPC())
		{
			return ;
		}
		
		var data =
		{
			"method" : "updateNavBarCorner",
			"data" : {
				"position" : updateParam.position,
				"index" : updateParam.index,
				"isHide" : updateParam.isHide,//0 表示不隐藏的  1 表示 隐藏的
				"type" : updateParam.type ,
				"param" : updateParam.param
			}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data));
	}
	
	//更新底部状态
	function updateTabBarCorner(index, isHide)
	{
		if(IsPC())
		{
			return ;
		}

		var data =
		{
			"method" : "updateTabBarCorner",
			"data" : {
				"index" : index,
				"isHide" : isHide //0 表示不隐藏的  1 表示 隐藏的
			}
		};
		WebViewJavascriptBridge.callHandler("mnAppHandler",JSON.stringify(data));
	}

    /**
     *shareType
     * 1 微信朋友分享
     * 2 微信朋友圈分享
     * 3 微博分享
     * 4 短信分享
     *
     * shareModel
     * title ： 分享的标题
     * content ： 分享的基础内容
     * icon  分享的图标
     * url ： 分享链接
     *
     */
    function shareInfo(shareType,shareModel)
    {
    	if(IsPC())
    	{
			return ;
		}
    	
    	var data = {
    		
			"method" : "shareInfo",
			"data" : {
				"shareType" : shareType,
				"param" : shareModel
			}
		};
		
		WebViewJavascriptBridge.callHandler('mnShareHandler', JSON.stringify(data));
    	
    }
	
	//判断是PC还是移动端方法       ----新建 By   谢中龙    2016-03-23
	function IsPC()
    {

        if (simulate)
        {
            return true;
        }

        var userAgentInfo = navigator.userAgent;

        var flag = false;
        if(/AppleWebKit.*Mobile/i.test(userAgentInfo))
        {
            if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(userAgentInfo))
            {
                flag = false;
            }
            else
            {
                flag = true;
            }
        }
        else
        {
            flag = true;
        }
        return flag;
       
	}
    
    
    //判断是安卓还是ios
    function isIos(){
    	var ua = navigator.userAgent.toLowerCase();	
		if(/iphone|ipad|ipod/.test(ua)) {
			return true;
		    mnWebMain.showProgressDialog("iphone");		
		} 
		else if (/android/.test(ua)) {
			return false;
			    mnWebMain.showProgressDialog("android");	
		}
    }
	
	//同步缓存本地存储    
	function syncSetLocalStorage(str_Key,source){
		if(!IsPC())
		{
			//Mobile调用的本地存储方法
			setDataToPhoneLocalStorage(str_Key,source);
		}
		else
		{
			//PC调用的本地存储方法
			localStorage.setItem(str_Key,source);
		}
		
	}

	// 同步获得本地缓存数据   
	function syncGetLocalStorage(str_Key){
		if(!IsPC())
		{
			//mobile调用的本地存储方法
			return getDataToPhoneLocalStorage(str_Key);
		}
		else
		{
			//PC调用的本地存储方法
			return localStorage.getItem(str_Key);
		}
	}

	//调用原生存储接口进行存储
	function setDataToPhoneLocalStorage(tagName,data){
		
		SyncManager.syncSetLocalStorage(tagName,data);
		
	}

	//调用原生存储接口获得数据
	function getDataToPhoneLocalStorage(strKey){
		return SyncManager.syncGetLocalStorage(strKey);
	}
	
	//删除本地存储
	function removeLocalStorage(key){
		if(IsPC())
		{
			localStorage.removeItem(key);
			return ;
		}
		
		syncRemoveLocalStorage(key);
	}

	function syncRemoveLocalStorage(strKey)
	{
		SyncManager.syncRemoveLocalStorage(strKey);
	}
	 //在A页面打开B页面，B页面更新A页面数据
    function openWebViewControllerWithDataTransfer(baseParam,leftParam,centerParam,rightParam,color,onResult){
    	if (IsPC())
	    {
//	    	location.href = 'http://192.168.0.114:8020/wapei/' + baseParam.url;
	    	location.href = 'http://192.168.0.123:8088/wapei/program/wapei/' + baseParam.url;
	    	return;
	    }
    	
    	if(color == undefined)
    	{
    		color = "";
    	}
    	
    	var data = {
			"method" : "openWebViewControllerWithDataTransfer",
			"data" : {
				"url" : baseParam.url,
				"isHideNavBar" : baseParam.isHideNavBar,
				"titleType" : baseParam.titleType,//0 表示普通的  1 表示 有输入框的
				"hintContent" : baseParam.hintContent,
				"leftParam" : leftParam ,
				"centerParam" : centerParam,
				"rightParam" : rightParam,
				"navBgColor" : color,
				"pageId" : baseParam.url
			}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data), onResult);
    }
    
    //返回原来页面前、存储当前页面输入的值
    function transferData(params){
    	
    	if(IsPC()){
			return ;
		}
		var data = {
			"method" : "transferData",
			"data" : {
				"params" : params
			}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data));
	}
	
	//关闭当前页面
	function closeSelfViewController(noAnim){
        // 如果是PC那么什么都不做
        if (IsPC())
        {
            return ;
        }
        
		var animInt = 0;
		if (noAnim){
			animInt = 0;//无动画
		}else{
			animInt = 1;//有动画
		}
		
		var data = {
			"method" : "closeSelfViewController",
			"data" : {
				"anim" : animInt
			}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data));
	}
	
	//打开原生页面
    function openViewController(vcId,params){
    	if (IsPC())
        {
//      	vcId = "ui-mine";
			location.href = "http	://192.168.0.114:8020/wapei/modules/homePage/index/index.html";
//          location.href = JUMP_URL + vcId + '.html';
			return;
        }
    	
    	var data = {
			"method" : "openViewController",
			"data" : {
				"VCId":vcId,
				"params" : params
			}
		};
		WebViewJavascriptBridge.callHandler("mnAppHandler",JSON.stringify(data));
    }
    
	//激活对应的TabBar
	function setTabbarSelectedIndex(index)
	{
		var data = {
			"method" : "setTabbarSelectedIndex",
			"data" : {
				"index": index
			}
		};
		WebViewJavascriptBridge.callHandler("mnAppHandler",JSON.stringify(data));
	}
	
	//重新加载界面
	function reloadWebView()
	{
		if(IsPC()){
			return ;
		}
		
		var data = {
			"method" : "reloadWebView",
			"data" : {}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data));
	}
	
	//设置页面可反弹
	function enableDrag(){
		if(IsPC()){
			return ;
		}
		
		var data = {
			"method" : "enableDrag",
			"data" : {}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data));
	}
	
	//禁止页面反弹
	function disableDrag(){
		if (IsPC())
		{
			return;
		}
		
		var data = {
			"method" : "disableDrag",
			"data" : {}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data));
	}
	
	//设置上下拉有加载效果
	function refresh(refreshType){
		
		if(IsPC())
		{
			return;
		}
		//refreshType 0:上拉 1:下拉 2:两者都有
		
		var data = {
			"method" : "refresh",
			"data" : {
				"refreshType": refreshType
			}
		};
		
		WebViewJavascriptBridge.send(JSON.stringify(data));
	}
	
	//下拉刷新
	function downRefresh()
	{
		
		if(IsPC())
		{
			return;
		}
		
		var data = {
			"method" : "downRefresh",
			"data" : {
			}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data));
	}
	//上拉刷新
	function UpRefresh()
	{
		
		if(IsPC())
		{
			return;
		}
		
		var data = {
			"method" : "UpRefresh",
			"data" : {
			}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data));
	}
	
	//关闭上下拉效果
	function refreshFinish(result){
		//result: 0 提示成功 1提示失败
		if(IsPC())
		{
			return;
		}
		var data = {
			"method" : "refreshFinish",
			"data" : {
				"result": result
			}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data));
	}
	
	//相册和拍照
    function photoPicker(isCut, photoType, maxCount, onResult){
    	if(IsPC()){
			onResult(
				JSON.stringify(
					{
						error: 0 ,
						data : {
							localURLs : [
								{
									url: 'test.png',
									size : 1000
								}
							]
						}
					}
				)
			);
			return ;
		}
    	//isCut: 0表示截图  1表示不截图
	   	//photoType: 0：调用照相机 1：相册  2:多图
	   	//maxCount: 最大图片数量
	   	//返回值 imageURLs:["图片地址1","图片地址2","图片地址3".....] 
    	var data = {
			"method" : "photoPicker",
			"data" : {
				"isCut" : isCut,
				"photoType" : photoType,
				"maxCount" : maxCount
			}
		};
		
		WebViewJavascriptBridge.callHandler('mnMediaHandler', JSON.stringify(data), onResult);
    }
    
    //上传图片
    function uploadFiles(filesArr,onResult)
    {
    	if(IsPC()){

			var objectKeys = {};
			for (var i = 0; i < filesArr.length; i++)
			{
				objectKeys[filesArr[0]] = filesArr[0];
			}

			onResult(
				JSON.stringify(
					{
						error : 1,
						data : {
							objectKeys : objectKeys
						}
					}
				)
			);
			return ;
		}
    	
    	var data = {
			"method" : "uploadFiles",
			"data" : {
				"files" : filesArr,
			}
		}
		WebViewJavascriptBridge.callHandler('mnAliOSSHandler', JSON.stringify(data), onResult);
    }
    
    
    //上传图片并获得图片地址
    function uploadFileAndGetURL(filesArr,onResult)
    {
    	if(IsPC()){

			var objectKeys = {};
			for (var i = 0; i < filesArr.length; i++)
			{
				objectKeys[filesArr[0]] = filesArr[0];
			}

			onResult(
				JSON.stringify(
					{
						error : 1,
						data : {
							objectKeys : objectKeys
						}
					}
				)
			);
			return ;
		}
    	
    	var data = {
			"method" : "uploadFileAndGetURL",
			"data" : {
				"files" : filesArr,
			}
		}
		WebViewJavascriptBridge.callHandler('mnAliOSSHandler', JSON.stringify(data), onResult);
    }
    
    //删除阿里云已经上传的图片
    function deleteFiles(objectKeysArr,onResult)
    {
    	if(IsPC()){
			return ;
		}
    	
    	var data = {
			"method" : "deleteFiles",
			"data" : {
				"objectKeys" : objectKeysArr,
			}
		}
		WebViewJavascriptBridge.callHandler('mnAliOSSHandler', JSON.stringify(data), onResult);
    }
    
    //根据阿里object key 获得对应的图片或者文件的url
    function getConstrainedObjectURLs(objectKeys,onResult)
    {
    	if(IsPC()){
			return ;
		}
    	
    	var data = {
			"method" : "getConstrainedObjectURLs",
			"data" : {
				"objectKeys" : objectKeys,
			}
		}
		WebViewJavascriptBridge.callHandler('mnAliOSSHandler', JSON.stringify(data), onResult);
    }
    
    //浏览图片
    function browserPics(index, urls){
    	if(IsPC()){
			return ;
		}
    	//index 需要查看的图片index值
    	//urls 图片数组
    	var data = {
			"method" : "browserPics",
			"data" : {
				"index" : index,
				"urls" : urls
			}
		};
		WebViewJavascriptBridge.callHandler('mnMediaHandler', JSON.stringify(data));
    }
    
    //打开底部bar遮罩
    function popupTabBarShadow()
    {
    	if(IsPC())
    	{
			return ;
		}
    	
    	var data = {
			"method" : "popupTabBarShadow",
			"data" : {}
		};
		WebViewJavascriptBridge.callHandler("mnAppHandler",JSON.stringify(data));
		
    }
    
    //关闭底部bar遮罩
    function closeTabBarShadow()
    {
    	if(IsPC()){
			return ;
		}
    	
    	var data = {
			"method" : "closeTabBarShadow",
			"data" : {}
		};
		WebViewJavascriptBridge.callHandler("mnAppHandler",JSON.stringify(data));
		
    }
    
	
	//获取版本号
	function getBundleVersion(callBack) {
		if(IsPC()) {
			return;
		}
		
		var data = {
			"method" : "getBundleVersion",
			"data" : {}
		};
		
		WebViewJavascriptBridge.callHandler("mnSystemHandler", JSON.stringify(data), callBack);
	}
	
	//弹框
	function showProgressDialog(content){
		if(IsPC()) {
			alert(content);
			return;
		}
		
		var data = {
			"method" : "showProgressDialog",
			"data" : {
				"content" : content
			}
		};
		WebViewJavascriptBridge.send(JSON.stringify(data));
	}
	
    //打开原生的页面
    function openUrlInApp(title,url){
    	if (IsPC())
        {
        	location.href = url;
			return;
        }
    	
    	var data = {
			"method" : "openUrlInApp",
			"data" : {
				"title" : title,
				"url" : url
			}
		};
    	WebViewJavascriptBridge.send(JSON.stringify(data));
    }
    
    /*
     * 支付
     */
    function createPayment(charge,callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "createPayment",
			"data" : {
				"charge" : charge,
			}
		};
		
    	WebViewJavascriptBridge.callHandler("mnPayHandler",JSON.stringify(data), callback);
    }
    
    /*
     * 第三方登录
     */
    function login(loginType,callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "login",
			"data" : {
				"loginType" : loginType,
			}
		};
		
    	WebViewJavascriptBridge.callHandler("mnLoginHandler",JSON.stringify(data), callback);
    }
    
    /*
     * 分享
     */
    function toUMengShare(shareType,shareUrl,shareTitle,shareContent,shareImg,callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "toUMengShare",
			"data" : {
				"shareType" : shareType,
				"shareUrl" : shareUrl,
				"shareTitle" : shareTitle,
				"shareContent" : shareContent,
				"shareImg" : shareImg,
			}
		};
		
    	WebViewJavascriptBridge.callHandler("mnShareHandler",JSON.stringify(data), callback);
    }
    
    /*
     * 复制
     */
    function copyText(copyContent){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "copyText",
			"data" : {
				"copyContent" : copyContent,
			}
		};
		WebViewJavascriptBridge.callHandler("mnSystemHandler", JSON.stringify(data));
    }
    
    /*
     * 定位
     */
    function getUserLocation(callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "getUserLocation",
			"data" : {
				
			}
		};
		WebViewJavascriptBridge.callHandler("mnSystemHandler", JSON.stringify(data), callback);
    }
    
    /*
     * 获取缓存
     */
    function getAppCrash(callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "getAppCrash",
			"data" : {
				
			}
		};
		WebViewJavascriptBridge.callHandler("mnSystemHandler", JSON.stringify(data), callback);
    }
    
     /*
     * 清除换攒
     */
    function cleanAppCrash(callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "cleanAppCrash",
			"data" : {
				
			}
		};
		WebViewJavascriptBridge.callHandler("mnSystemHandler", JSON.stringify(data), callback);
    }
    
    /*
     * 设置数量
     */
    function setTipNum(type,num){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "setTipNum",
			"data" : {
				"type" : type,
				"num" : num,
			}
		};
		WebViewJavascriptBridge.callHandler("mnAppHandler", JSON.stringify(data));
    }
    
    /*
     * 设置极光推送别名
     */
    function setAlias(alias,callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "setAlias",
			"data" : {
				"alias" : alias,
			}
		};
		WebViewJavascriptBridge.callHandler("mnJPushHandler", JSON.stringify(data),callback);
    }
    
    /*
     * 推送接收状态
     */
    function isPushStopped(callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "isPushStopped",
			"data" : {
			}
		};
		WebViewJavascriptBridge.callHandler("mnJPushHandler", JSON.stringify(data),callback);
    }
    
     /*
     * 关闭推送
     */
    function stopPush (callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "stopPush",
			"data" : {
			}
		};
		WebViewJavascriptBridge.callHandler("mnJPushHandler", JSON.stringify(data),callback);
    }
    
     /*
     * 打开推送
     */
    function resumePush (callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "resumePush",
			"data" : {
			}
		};
		WebViewJavascriptBridge.callHandler("mnJPushHandler", JSON.stringify(data),callback);
    }
    
    /*
     * 环信账号退出
     */
    function easeLogOut(callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "easeLogOut",
			"data" : {
			}
		};
		WebViewJavascriptBridge.callHandler("mnEaseHandler", JSON.stringify(data),callback);
    }
    
     /*
     * 环信账号登录
     */
    function easeLogin(account,password,callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "easeLogin",
			"data" : {
				"account" : account,
				"password" : password,
			}
		};
		WebViewJavascriptBridge.callHandler("mnEaseHandler", JSON.stringify(data),callback);
    }
    
    /*
     * 获取设备id
     */
    function getDeviceID(callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "getDeviceID",
			"data" : {
			}
		};
		WebViewJavascriptBridge.callHandler("mnSystemHandler", JSON.stringify(data),callback);
    }
    
    /*
     * 获取客服历史消息
     */
    function getConversation(callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "getConversation",
			"data" : {
			}
		};
		WebViewJavascriptBridge.callHandler("mnEaseHandler", JSON.stringify(data),callback);
    }
    
    /*
     * 获取当前版本号
     */
    function getVersionCode(callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "getVersionCode",
			"data" : {
			}
		};
		WebViewJavascriptBridge.callHandler("mnSystemHandler", JSON.stringify(data),callback);
    }
    
    /*
     * 获取当前版本名称
     */
    function getVersionName(callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "getVersionName",
			"data" : {
			}
		};
		WebViewJavascriptBridge.callHandler("mnSystemHandler", JSON.stringify(data),callback);
    }
    
    /*
     * t跳转appstore
     */
    function skipToAppStore(callback){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "skipToAppStore",
			"data" : {
			}
		};
		WebViewJavascriptBridge.callHandler("mnSystemHandler", JSON.stringify(data),callback);
    }
    
    /*
     * 打开主页面
     */
    function openMainController(index){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "openMainController",
			"data" : {
				'index' : index
			}
		};
		WebViewJavascriptBridge.callHandler("mnAppHandler", JSON.stringify(data));
    }
    
    /*
     * 
     */
    function showLoading(content){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "showLoading",
			"data" : {
				'content' : content
			}
		};
		WebViewJavascriptBridge.callHandler("mnAppHandler", JSON.stringify(data));
    }
    
    function closeLoading(){
    	if (IsPC())
        {
			return;
        }
    	
    	var data = {
			"method" : "closeLoading",
			"data" : {
			}
		};
		WebViewJavascriptBridge.callHandler("mnAppHandler", JSON.stringify(data));
    }
    
	
	window.mnWebMain = {
		//原生调用html5方法
		cbBridgeInited: cbBridgeInited,	//原生和html建立连接
		cbNavRightClicked: undefined,	//点击顶部右按钮
		//顶部左按钮默认点击事件
//		cbNavLeftClicked: function(){
//			mnWebMain.closeSelfViewController(0);
//		},	//点击顶部左按钮
        cbNavLeftClicked : undefined,
        cbNavTopClicked : undefined,
		cbAppWillEnterForground: undefined,
		cbAppDidEnterBackground: undefined,
		cbViewWillAppear: undefined,	//页面显示时触发
		cbViewWillDisappear: undefined,	//页面隐藏时触发
		//我的方法
		cbLoginSuccessUpdate : undefined,	//登录成功更新数据
		cbUpLoadData : undefined,	//上拉加载
		cbDownRefreshData : undefined,	//下拉刷新
		cbInputFocused : undefined,//输入框获得焦点事件，
		cbTabBarOnClicked : undefined,//底部导航栏点击事件
		cbUpdateConversationList : undefined,//获得消息列表
		cbUpdateConorCount : undefined,//更新消息图标上的数字
		cbUpdataTabBarCorner : undefined,//更新底部coner
		cbAppBeForcedLogOut : undefined,//强制退出app
		getParams : undefined,
		cbNavTopRightClicked : undefined,
		
		//html5调用原生方法
		checkInternetStatus : checkInternetStatus,//判断是不是已经联网
		popupTabBarShadow : popupTabBarShadow, //打开底部bar遮罩
		closeTabBarShadow : closeTabBarShadow, //关闭底部bar遮罩
		popupModal: popupModal,//弹出二级弹框
		closeModal : closeModal,//关闭二级弹框
		updateNavBar : updateNavBar,//更新导航栏
		updateNavBarCorner : updateNavBarCorner,//更新头部按钮的右上角数字或者图片
		updateTabBarCorner : updateTabBarCorner,//更新底部状态
		callPhone : callPhone,//打电话方法
		setAlias : setAlias,//设置别名
		emptyAlias : emptyAlias,//清空别名
		setTabbarSelectedIndex : setTabbarSelectedIndex,//激活对应的tabBar
		openUrl : openUrl,//在app外打开链接
		openWebViewController : openWebViewController,	//打开新页面
		closeMoreViewController : closeMoreViewController,//关闭界面
		syncGetLocalStorage : syncGetLocalStorage,//获得本地存储数据
		syncSetLocalStorage : syncSetLocalStorage,//本地存储
		shareInfo : shareInfo,//分享
		openWebViewControllerWithDataTransfer : openWebViewControllerWithDataTransfer,//在A页面打开B页面，B页面更新A页面数据
		closeSelfViewController : closeSelfViewController,	//关闭当前页面
		openViewController : openViewController,	//打开原生页面
		removeLocalStorage : removeLocalStorage,	//删除本地存储
		enableDrag : enableDrag,	//设置反弹
		disableDrag : disableDrag,	//禁止反弹
		refresh: refresh,	//设置上下拉有加载效果
		transferData : transferData,	//返回原来页面前、存储当前开页面输入的值
		photoPicker : photoPicker,	//相册和拍照
		uploadFiles : uploadFiles,
		uploadFileAndGetURL : uploadFileAndGetURL,
		deleteFiles : deleteFiles,
		getConstrainedObjectURLs : getConstrainedObjectURLs,
		browserPics : browserPics, //浏览图片
		refreshFinish : refreshFinish,	//关闭上下拉的刷新效果（回弹）
		downRefresh : downRefresh,//下拉刷新
		UpRefresh : UpRefresh,//上拉刷新
		reloadWebView : reloadWebView,
		getBundleVersion: getBundleVersion,
		showProgressDialog : showProgressDialog,
		openUrlInApp : openUrlInApp,
		createPayment : createPayment,
		login : login,
		toUMengShare : toUMengShare,
		copyText : copyText,
		getUserLocation : getUserLocation,
		getAppCrash : getAppCrash,
		cleanAppCrash : cleanAppCrash,
		setTipNum : setTipNum,
		setAlias : setAlias,
		isPushStopped : isPushStopped,
		stopPush : stopPush,
		resumePush : resumePush,
		easeLogin : easeLogin,
		easeLogOut : easeLogOut,
		getDeviceID : getDeviceID,
		getConversation : getConversation,
		getVersionCode : getVersionCode,
		getVersionName : getVersionName,
		skipToAppStore : skipToAppStore,
		openMainController : openMainController,
		showLoading : showLoading,
		closeLoading : closeLoading,
		showLoading : showLoading,
		
	};
	
	// Connect bridge
	function connectWebViewJavascriptBridge(callback) {
		if (window.WebViewJavascriptBridge) {
			callback(WebViewJavascriptBridge)
		}else {
			document.addEventListener('WebViewJavascriptBridgeReady', function() {
				callback(WebViewJavascriptBridge)
			}, false)
		}
	}
	
	// 为了在PC上调试，有些页面的逻辑是在cbBridgeInited中初始化的
	setTimeout(function(){
		if (IsPC())
		{
			if (mnWebMain.cbBridgeInited != undefined){
				mnWebMain.cbBridgeInited();
			}
		}
	}, 500);
	
	// 连接Bridge
	connectWebViewJavascriptBridge(function(bridge){
		
		if (mnWebMain.cbBridgeInited != undefined){
			mnWebMain.cbBridgeInited();
		}
		else
		{
//			alert("bridge inited but no cbBridgeInited");	
		}

		bridge.init(function(message, responseCallback) 
		{
			if (message.method != undefined)
			{
				switch( message.method ){
					case "pressBack" :
						if (mnWebMain.cbPressBack != undefined){
							mnWebMain.cbPressBack(message.data);
						}
						break;
					case "tapNavRightBtn" :
						if (mnWebMain.cbNavRightClicked != undefined){
							mnWebMain.cbNavRightClicked(message.data);
						}
						break;
					case "tapNavLeftBtn" :
						if (mnWebMain.cbNavLeftClicked != undefined){
							mnWebMain.cbNavLeftClicked(message.data);
						}
						break;
					case "appWillEnterForground" :
						if (mnWebMain.cbAppWillEnterForground != undefined){
							mnWebMain.cbAppWillEnterForground();
						}
						break;
					case "appDidEnterBackground" :
						if (mnWebMain.cbAppDidEnterBackground != undefined){
							mnWebMain.cbAppDidEnterBackground();
						}
						break;
					case "viewWillAppear" :
						if (mnWebMain.cbViewWillAppear != undefined){	//页面显示时触发
							mnWebMain.cbViewWillAppear();
						}
						break;
					case "viewWillDisappear" :
						if (mnWebMain.cbViewWillDisappear != undefined){	//页面显示时触发
							mnWebMain.cbViewWillDisappear();
						}
						break;
					case "cbUpLoadData" :
						if (mnWebMain.cbUpLoadData != undefined){	//上拉加载
							mnWebMain.cbUpLoadData();
						}
						break;
					case "cbDownRefreshData" :
						if (mnWebMain.cbDownRefreshData != undefined){	//下拉刷新
							mnWebMain.cbDownRefreshData();
						}
						break;
					case "cbInputFocused" : 
						if (mnWebMain.cbInputFocused != undefined){	//
							mnWebMain.cbInputFocused(message.data);
						}
						break;
					case "cbTabBarOnClicked" : 
						if (mnWebMain.cbTabBarOnClicked != undefined){	//下拉刷新
							mnWebMain.cbTabBarOnClicked(message.data);
						}
						break;
					case "cbUpdateConversationList"	:
						if (mnWebMain.cbUpdateConversationList != undefined){	//下拉刷新
							mnWebMain.cbUpdateConversationList(message.data);
						}
						break;
					case "cbUpdateConorCount" : 
						if (mnWebMain.cbUpdateConorCount != undefined){	//下拉刷新
							mnWebMain.cbUpdateConorCount(message.data);
						}
						break;
					case "cbUpdataTabBarCorner" : 
						if(mnWebMain.cbUpdataTabBarCorner != undefined)
						{
							mnWebMain.cbUpdataTabBarCorner();
						}
						break;
						
					case "cbAppBeForcedLogOut" :
						if(mnWebMain.cbAppBeForcedLogOut != undefined)
						{
							mnWebMain.cbAppBeForcedLogOut();
						}
						break;
					case "getParams" :
						if(mnWebMain.getParams != undefined)
						{
							mnWebMain.getParams(message.data);
						}
						break;
					case "cbNavTopClicked" :
						if(mnWebMain.cbNavTopClicked != undefined)
						{
							mnWebMain.cbNavTopClicked();
						}
						break;
					case "cbNavTopRightClicked" : 
						if(mnWebMain.cbNavTopRightClicked != undefined)
						{
							mnWebMain.cbNavTopRightClicked();
						}
						break;
				}
			}
			
			if (responseCallback)
			{
				responseCallback("");	
			}
		});
	});
}
