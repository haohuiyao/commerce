
/*基础接口相关*
 * 调用方式 Api.getApiUrl("LOGIN")
 */
var BaseApi = (function() {

	//私有变量
	var BASE_APIS  =
	{
		LOGIN : 			BASE_URL +  "admin/loginNormal.do",//管理员登录
		GET_ADMIN_LIST : 	BASE_URL +  "admin/getAdminList.action",//获取管理员列表
		ADD_ADMIN : 		BASE_URL +  "admin/addAdmin.action",//添加管理员
		MOD_ADMIN : 		BASE_URL +  "admin/modAdmin.action",//修改管理员
		DEL_ADMIN : 		BASE_URL +  "admin/delAdmin.action",//删除管理员
		GET_ADMIN_ROLE : 	BASE_URL +  "user/getAdminRole.action",//获取管理员角色
	}

	return BASE_APIS;

})();


/*
 *key
 * 获取方式是 BaseKey.getOSSKEY()
 * 返回的格式是 ：
 * {
 * 		region : "",
 * 		accessKeyId :"",
 * 		accessKeySecret : "",
 * 		bucket : "",
 * 		pubBucket : ""
 * }
 *
 * */

var BaseKey = (function() {

	var LOCALKRY = {
		ISREMEMBER : "ISREMEMBER",
		USERNAME : "USERNAME",
		USERPWD : "USERPWD",
		USERTYPE : "USERTYPE",
		SESSIONID : "SESSIONID",
	};

	var ERRORCODEKEY = {
		ERROR_UN_LOGIN : "1",
		ERROR_RELOGIN_FAILED : "103"
	};

	return {
		/*
		 * 获得local keys
		 * */
		getLocalKey : function () {
			var params = {};
			for(var key in LOCALKRY){
				params[key] = LOCALKRY[key];
			}
			return params;
		},

		/*
		 * 获得错误码 keys
		 * */
		getErrorCodeKey : function () {
			var params = {};
			for(var key in ERRORCODEKEY){
				params[key] = ERRORCODEKEY[key];
			}
			return params;
		}

	};

})();

/*
 * 默认
 */
const defaultObj = {
	img_head_icon : '../../../assets/images/default/default-classify-icon.png',	//用户默认头像
	txt_user_name : '用户'														//用户默认昵称
}
