

/*
 * 公用上加载更多
 */
var upLoadList = {
	
	scope : null,
	obj : null,
	pageIndex : 1,
	pageSize : 10,
	isFirst : true,
	totalPage : 1,
	
	init : function($scope,obj){
		
		var self = this;
		
		this.scope = $scope;
		
		this.obj = obj;
		
		this.nativeInvokeH5();
	},
	
	/*
	 * h5调用原生
	 */
	nativeInvokeH5 : function(){
		
		var self = this;
		
		mnWebMain.enableDrag();
		mnWebMain.refresh(0);
		
		mnWebMain.cbUpLoadData = function(){

			self.isFirst = false;
			if(self.pageIndex < self.totalPage)
			{
				mnWebMain.refreshFinish(0);
				self.pageIndex = self.pageIndex + 1;
				self.obj.getListPage();
			}
			else
			{
				mnWebMain.refreshFinish(0);
				mnWebMain.showProgressDialog("没有更多数据了...");
			}
		}
	},
	
	/*
	 * 初始化分页
	 */
	initPageModel : function(listKey){
		
		var self = this;
		
		self.pageIndex = 1;
		self.pageSize = 10;
		self.isFirst = true;
	}
	
};


var isLoginController = {
	
	/*
	 * 是否登录
	 */
	isLogin : function(){
		
		var self = this;
		
		if(mnWebMain.syncGetLocalStorage(K_EX_LOGIN_STATUS) == HASLOGIN)
		{
			return true;
		}
		else
		{
			self.jumpToLogin();
			return false;
		}
	},
	
	/*
	 * 跳转登录
	 */
	jumpToLogin : function(){
		var baseParam = {
			"url" : URL_LOGIN_INDEX,
			"isHideNavBar" : 1, 
			"titleType" : 0,
		};
		mnWebMain.openWebViewController(baseParam,[],[],[]);
	},
}

var shopCartNumUpdate = function(){

	getApiData.getCartList(function(data){
		var num = 0;
		
		for(var i = 0; i < data.length; i++)
		{
			num = num + data[i].amount;
			
		}
		num = (num > 99)? 99 : num;
		
		mnWebMain.setTipNum(2,num);
	})
}

/*
 * 第三方登录
 */
var thirdParty = {
	
	WX : 'WX',
	QQ : 'QQ',
	
	userLogin : function(type,jumpCallBack){
		
		var self = this;
		type = self[type];
	
		mnWebMain.login(type,function(data){
			
    		var userName = JSON.parse(data).data.userName;
    		var iconUrl = JSON.parse(data).data.iconUrl;
    		var openid = JSON.parse(data).data.openid;
    		var uid = JSON.parse(data).data.uid;
    		
    		mnWebMain.syncSetLocalStorage(K_EX_OPEN_ID,openid);
    		mnWebMain.syncSetLocalStorage(K_EX_USER_NAME,userName);
    		mnWebMain.syncSetLocalStorage(K_EX_USER_ICON,iconUrl);
    		
    		var params = {};
			params.openId = openid;
			params.type = type;
			
			$service.httpRequest('post',API_THIRD_LOGIN, params, function(loginData){
				/*
				 * 登录成功
				 */
				self.loginCallBack(loginData,jumpCallBack);
			},function(loginData){
				/*
				 * 如果没绑定跳绑定页面
				 */
				if(loginData.code == ERROR_UN_BIND)
				{
					var URL = URL_LOGIN_BIND_PHONE+'?type=' + type;
					var baseParam = {
						"url" : URL,
						"isHideNavBar" : 0, 
						"titleType" : 0,
					};
					  var centerParam = [{"type" : 0,"param" : "账号绑定"}];
            		var leftParam = [{"leftType":0, "type" : 1 ,"param" : "btn_arrow_lf"}];
					mnWebMain.openWebViewController(baseParam,leftParam,centerParam,[]);
				}
				else
				{
					mnWebMain.showProgressDialog("第三方登录失败")
				}
			})
    		
    	})
	},
	
	/*
	 * 登录回调
	 * jumpCallBack  登录成功跳转
	 */
	loginCallBack : function(data,jumpCallBack){
    	mnWebMain.syncSetLocalStorage(K_EX_TOKEN,data.token);
    	mnWebMain.syncSetLocalStorage(K_EX_SESSIONID,data.JSESSIONID);
    	mnWebMain.syncSetLocalStorage(K_EX_LOGIN_STATUS,HASLOGIN);
    	mnWebMain.syncSetLocalStorage(K_EX_USER_INFO,JSON.stringify(data.userInfo));
    	
    	/*
    	 * 设置极光推送别名
    	 */
    	var easeCount = data.userInfo.buyerId;
    	
    	mnWebMain.setAlias(easeCount,function(data){});

		jumpCallBack();
    	//mnWebMain.openViewController('ui-index');
		//mnWebMain.closeSelfViewController()
    	/*
    	 * 环信账号登录
    	 */
    	mnWebMain.easeLogin(data.userInfo.buyerId,data.userInfo.easemobPWD,function(callback){});
    	
    },
}

/*
 * 修改样式--全屏
 */
var headPosition = {
	
	setHeadPosition : function(action){
		if(utility.isIos())
		{
			action();
		}
	}
}

var registerPushEvent = {
	
	init : function(){
		
		mnWebMain.registerPushEvent();
    	
    	mnWebMain.cbPushClick = function(data){
    		
    		var messageType = JSON.parse(data.data).type;//类型 1:订单助手 2:通知 3:积分变化
    		
    		if(isLoginController.isLogin())
    		{
    			if(messageType == 1)
    			{
    				var baseParam = {
						"url" : URL_MESSAGE_ORDER,
						"isHideNavBar" : 0, 
						"titleType" : 0,
					};
					var centerParam = [{"type" : 0,"param" : "订单助手"}];
					var leftParam = [{"leftType":0, "type" : 1 ,"param" : "btn_back_nor"}];
					mnWebMain.openWebViewController(baseParam,leftParam,centerParam,[],color_head_bg);
    			}
    			else if(messageType == 3)
    			{
    				var baseParam = {
						"url" : URL_NOTICE,
						"isHideNavBar" : 0, 
						"titleType" : 0,
					};
					var centerParam = [{"type" : 0,"param" : "通知"}];
					var leftParam = [{"leftType":0, "type" : 1 ,"param" : "btn_back_nor"}];
					mnWebMain.openWebViewController(baseParam,leftParam,centerParam,[],color_head_bg);
    			}
    		}
    	}
	}
}
