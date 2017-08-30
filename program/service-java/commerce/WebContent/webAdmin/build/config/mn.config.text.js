const LAN = "CHLAN";

var textConfig = (function() {

	//私有变量
	var TEXT_CONFIG  =
	{
		CHLAN : {
			//确认提示语
			CONFIRM_DEL_ADMIN : "确认要删除这个管理员吗？",

			//成功提示语
			SUCCESS_ADD_ADMIN : "添加管理员成功",
			SUCCESS_MOD_ADMIN : "修改管理员成功",
			SUCCESS_DEL_ADMIN : "删除管理员成功",

			//警告提示语
			WARN_OLD_PWD_EMPTY 			: "请输入旧密码",
			WARN_NEW_PWD_EMPTY 			: "请输入新密码",
			WARN_CONFIRM_PWD_EMPTY 		: "请确认密码",
			WARN_PWD_UN_SAME 			: "两次输入的密码不一致",
			WARN_ADMIN_NAME_EMPTY		: "请输入管理员名称",
			WARN_ADMIN_PWD_EMPTY		: "请输入管理员登录密码",
			WARN_ADMIN_TYPE_EMPTY		: "请选择管理员类型",
		}
	}

	return {

		getText : function()
		{
			var params = {};
			for(var key in TEXT_CONFIG[LAN]){
				params[key] = TEXT_CONFIG[LAN][key];
			}
			return params;
		},

	};

})();