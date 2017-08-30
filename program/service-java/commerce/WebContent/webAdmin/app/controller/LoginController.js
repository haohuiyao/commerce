/**
 * Controller
 * ===================================================================================
 * 登录
 * ===================================================================================
 */


var app = angular.module("app",[]);

app.controller('ctrl', function($scope){
	LoginController.init($scope);
})

var LoginController = 
{
	scope : null,

	isRemember : 0,
	
	userObj : {
		userName : "",
		pwd : "",
		adminType : '',
	},
	
	init : function($scope)
	{
		this.scope = $scope;

		this.dataModelInit();

		this.isRemember();
		
		this.bindEvent();
	},
	
	dataModelInit : function()
	{
		this.scope.adminTypeArr = adminTypeArr;
		this.scope.userObj = this.userObj;
	},

	isRemember : function () {
		var self = this;
		self.isRemember = fn.getLocalStorage(BaseKey.getLocalKey().ISREMEMBER);
		if(fn.isEmpty(self.isRemember)){
			self.isRemember = 0;
		}

		if(self.isRemember == 1)
		{
			self.userObj.adminType = fn.getLocalStorage(BaseKey.getLocalKey().USERTYPE);
			self.userObj.userName = fn.getLocalStorage(BaseKey.getLocalKey().USERNAME);
			self.userObj.pwd = fn.getLocalStorage(BaseKey.getLocalKey().USERPWD);
		}

		self.scope.isRemember = self.isRemember;
		self.scope.userObj = self.userObj;
	},
	
	userLogin : function()
	{
		var self = this;

		if(self.userObj.adminType == "")
		{
			common.alert("请选择管理员类型");
			return;
		}
		
		if(self.userObj.userName == "")
		{
			common.alert("请输入用户名");
			return;
		}
		
		if(self.userObj.pwd == "")
		{
			common.alert("请输入密码");
			return;
		}
		
		var requestParams = {};
		requestParams.username = self.userObj.userName;
		requestParams.password = self.userObj.pwd;
		requestParams.userType = self.userObj.adminType;

		var params = {};
		params.type = 'post';
		params.dataApi = BaseApi.LOGIN;
		params.requestParams = requestParams;
		params.callback = function (data) {
			/**
			 * 本地存储值
			 * */
			fn.setLocalStorage(BaseKey.getLocalKey().SESSIONID,data.userInfo.sessionId);
			fn.setLocalStorage(BaseKey.getLocalKey().ISREMEMBER,self.isRemember);
			fn.setLocalStorage(BaseKey.getLocalKey().USERNAME,self.userObj.userName);
			fn.setLocalStorage(BaseKey.getLocalKey().USERPWD,self.userObj.pwd);
			fn.setLocalStorage(BaseKey.getLocalKey().USERTYPE,self.userObj.adminType);

			window.location.href = "index.html";
		}

		HttpService.ajaxRequest(params);
	},
	
	
	bindEvent : function()
	{
		var self = this;
		
		self.scope.onClickLogin = function()
		{
			self.userLogin();
		};
		
		self.scope.onKeyPressLogin = function($event)
		{
			if($event.keyCode == 13)
			{
				self.userLogin();
		    }
		}

		/**
		 * 记住密码点击事件
		 * */
		self.scope.onClickRemember = function () {
			if(self.isRemember == 0){
				self.isRemember = 1;
			}
			else
			{
				self.isRemember = 0;
			}

			self.scope.isRemember = self.isRemember;
		}
	},
	
}
