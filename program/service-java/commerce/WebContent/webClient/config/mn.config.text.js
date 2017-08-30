//错误信息
const ERR_USERNAME_BLANK = "请输入用户名";
const ERR_MOBILEPHONE_BLANK = "请输入手机号";
const ERR_LOGINACCOUNT_BLANK = "请输入登录账号";
const ERR_CODE_BLANK = "请输入验证码";
const ERR_PWD_BLANK = "请输入密码";
const ERR_DEL_BLANK = "请选择删除选项！";
const LOGIN_SUCCESS = "登录成功";
const ERR_CONFIRMPWD_DIFFERENCE = "两次输入的密码不一致";
const ERR_LOGININFO_BLANK = "请输入登录信息";
const GET_CODE_SUCCESS = "短信验证码发送成功";
const REGISTER_SUCCESS = "注册成功";
const CHANGE_PWD_SUCCESS = "修改密码成功";
const DEL_SEARCH_SUCCESS = "删除搜索记录成功";
const DEL_CART_SUCCESS = "删除购物车成功";
const DEL_RECEIPTADDRESS_SUCCESS = "删除收货地址成功";

//确认页面提示语
const CONFIRM_DEL_ADDRESS = "确认要删除该地址么？";
const CONFIRM_DEL_COLLECTION = "确认要取消对该商品的收藏么？";
const CONFIRM_RELIEVE_BIND = "确认要解除绑定么？";
const CONFIRM_DEL_MESSAGE = "确认要删除该条消息么？";
const CONFIRM_DEL_SEARCH_HISTORY = "确认要删除历史记录？";

//列表为空时的提示语
const EMPTY_TITLE_RECEIPTADDRESS = "还没有添加任何收获地址";
const EMPTY_CONTENT_RECEIPTADDRESS = "可以去添加新的收获地址";
const EMPTY_TITLE_ACCUMULATEPOINT = "还没有任何的积分抵扣记录";
const EMPTY_CONTENT_ACCUMULATEPOINT = "购买商品就能够获取积分哟！";
const EMPTY_TITLE_COLLECTIONLIST = "还没有收藏任何的商品";
const EMPTY_CONTENT_COLLECTIONLIST = "可以去看看有哪些喜欢的";
const EMPTY_TITLE_VIEWRECORD = "还没有留下任何的足迹";
const EMPTY_CONTENT_VIEWRECORD = "快去浏览一些产品吧";
const EMPTY_TITLE_RECOMMENDFRIENDS = "还没有推荐任何的好友";
const EMPTY_CONTENT_RECOMMENDFRIENDS = "快去推荐一些好友吧";
const EMPTY_TITLE_ORDERLIST = "您还没有相关的订单";
const EMPTY_CONTENT_ORDERLIST = "可以去看看有哪些想买的！";
const EMPTY_TITLE_SHOPPINGCART = "购物车空空如也";
const EMPTY_CONTENT_SHOPPINGCART = "去挑几件好货吧！";
const EMPTY_TITLE_FRIENDSCONSUMPTION = "该好友还没有任何的消费记录";
const EMPTY_CONTENT_FRIENDSCONSUMPTION = "快联系好友去选货吧！";
const EMPTY_TITLE_MESSAGELIST = "还没有相关的消息";
const EMPTY_CONTENT_MESSAGELIST = "";

const LAN = "CHLAN";

var txtConfig = {
	CHLAN : {
		
		//确认提示语
		CONFIRM_DEL_ORDER : "确认要删除这个订单么？",
		CONFIRM_DEL_ADDRESS : "确认要删除该地址么？",
		CONFIRM_DEL_COLLECTION : "确认要取消对该商品的收藏么？",
		CONFIRM_DEL_MESSAGE : "确认要删除该条消息么？",
		CONFIRM_DEL_SEARCH_HISTORY : "确认要删除历史记录？",
		CONFIRM_RELIEVE_BIND : "确认要解除绑定么？",
		
		//成功提示语
		SUCCESS_DEL_ORDER : "订单删除成功",
		SUCCESS_CANCLE_ORDER : "订单取消成功",
		SUCCESS_CLEAR_CACHE : "清除缓存成功",
		SUCCESS_COPY : "复制成功",
		
		//提示语
		WARN_UPLOAD_PHOTO_FULL : "已超过数量上限"
	},
}
