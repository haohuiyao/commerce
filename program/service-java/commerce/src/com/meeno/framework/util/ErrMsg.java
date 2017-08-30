package com.meeno.framework.util;

public interface ErrMsg {
	String SYS_ERR = "系统异常";

	// 订单相关
	String ORDERID_IS_EMPTY = "订单ID为空";
	String REFUND_ID_IS_EMPTY = "售后申请ID为空";
	String REFUND_NOT_EXIST = "售后申请不存在";
	String CANT_DO_REFUND_IN_THIS_STATUS = "当前售后申请状态不允许此操作";
	String JTK_REFUND_DO_NOT_NEED_RCV_ADDRESS = "仅退款申请无需填写收货地址";
	String ORDER_NOT_EXIST = "订单不存在";
	String NO_GOODS_SELECTED = "未选择任何商品";
	String NO_RCVINFO_SELECTED = "未选择收货地址";
	String RCVINFO_NOT_EXIST = "收货地址不存在";
	String PLEASE_SELECT_EXPRESS_COMPANY = "请选择快递公司";
	String LOGISTIC_NUM_CAN_NOT_BE_EMPTY = "快递单号不能为空";
	String CAN_NOT_DELIVERY_ORDER_IN_THIS_STATUS = "当前订单状态不允许发货";
	String CAN_NOT_CONFIRM_RCV_IN_THIS_STATUS = "当前订单状态不允许确认收货";
	String CAN_NOT_CANCEL_ORDER_IN_THIS_STATUS = "当前订单状态不允许取消操作";
	String CAN_NOT_PAY_ORDER_IN_THIS_STATUS = "当前订单状态不允许支付";
	String CAN_NOT_DELETE_ORDER_IN_THIS_STATUS = "当前订单状态不允许删除";
	String CAN_NOT_COMMENT_ORDER_IN_THIS_STATUS = "当前订单状态不允许评论";
	String DEFAULT_EXPRESS_TYPE_NOT_FOUND = "未找到默认快递方式";
	String ORDER_GOODS_INFO_ERR = "商品信息有误";
	String CAN_ONLY_OPERATE_ON_OWN_ORDERS = "只能对自己的订单进行操作";
	String ORDER_INFO_ERR = "订单信息异常";
	String ORDER_PICKUPCODE_NOT = "提货码不存在，您的输入有误";
	String ORDER_REVISE_NOT_EXIST = "订单改价信息不存在";
	String CANT_AUDIT_REVISE_IN_THIS_STATUS = "当前改价申请状态不允许审核";

	// passport
	String ACCOUNT_CANT_BE_NULL = "account不能为空";
	String ACCOUNT_ALREADY_EXIST = "账号已存在";
	String ACCOUNT_NOT_EXIST = "账号不存在";
	String USERNAME_CANT_BE_EMPTY = "用户名不能为空";
	String PWD_CANT_BE_EMPTY = "密码不能为空";
	String PWD_NOT_CORRECT = "密码错误";
	String PHONE_FORMAT_ERR = "手机号格式错误";
	String VCODE_CANT_BE_EMPTY = "验证码不能为空";
	String VCODE_NOT_EXIST = "验证码不存在";
	String VCODE_NOT_CORRECT = "验证码错误";
	String VCODE_NOT_IN_VALID_PERIOD = "验证码已失效";
	String VTYPE_CANT_BE_EMPTY = "验证码类型不能为空";
	String GET_VCODE_INTERVAL_TOO_SHORT = "间隔时间过短，请稍后再试";
	String PHONE_HAS_BIND_WX = "手机号已绑定微信";
	String PHONE_HAS_BIND_QQ = "手机号已绑定QQ";
	String PHONE_VCODE_NULL = "手机号或验证码为空";
	String PHONE_NON_EXISTENT = "手机号码不存在，请先注册";
	String TOKEN_NON_EXISTENT = "TOKEN值为空";
	String PHONE_HAS_BIND_OTHER_ACC = "此手机号已被使用";

	// buyer
	String BUYER_ID_IS_NULL = "用户ID为空";
	String BUYER_NOT_EXIST = "用户不存在";
	String BUYER_NOT_LOGGED = "用户未登录";
	String BUYER_CONTENT_NOT_EXIST = "意见反馈不能为空";
	String BUYER_TYPE_NULL = "用户类型不能为空";
	String BUYER_INTEGRAL_NOT_ENOUGH = "用户可用积分不足";

	// 提现
	String WITHDRAW_ID_IS_NULL = "提现申请ID为空";
	String WITHDRAW_NOT_EXIST = "提现申请不存在";
	String WITHDRAW_BUYER_INTEGRAL_NOT_ENOUGH = "用户可用积分不足";

	// admin
	String ADMIN_NOT_EXIST = "管理员不存在";
	String ADMIN_ROLE_NULL = "管理员角色不能为空";
	String ADMIN_NOT_LOGIN = "管理员未登录";
	String ADMIN_DISTRIBUTOR_SHOP_NULL = "经销商门店不存在";
	String GROUPID_CANT_BE_EMPTY = "管理员分组不能为空";
	String ADMIN_IDS_NULL = "管理员ID不能为空";

	// 物流相关
	String EXPRESS_COMPANY_NOT_EXIST = "选择的快递公司不存在";
	String EXPRESS_COMPANY_ID_IS_EMPTY = "选择的快递公司不存在";

	// carriage
	String SP_CANT_BE_NULL = "起点省份不能为空";
	String EP_CANT_BE_NULL = "终点省份不能为空";
	String EXPRESS_TYPE_CANT_BE_NULL = "快递方式不能为空";
	String WEIGHT_CANT_BE_NULL = "重量不能为空";
	String CARRIAGE_NOT_FOUND = "未找到相应的运费计算规则";

	// 优惠券
	String COUPON_NO_CAN_NOT_BE_EMPTY = "优惠券编号不允许为空";
	String COUPON_TYPE_CAN_NOT_BE_EMPTY = "优惠券类型不允许为空";
	String COUPON_PRICE_LIMIT_CANT_BE_NULL = "使用价格限制不能为空";
	String COUPON_DISCOUNT_CANT_BE_NULL = "抵扣价格不能为空";
	String COUPON_VALIDTIME_CANT_BE_NULL = "生效时间不能为空";
	String COUPON_INVALIDTIME_CANT_BE_NULL = "失效时间不能为空";
	String COUPON_STIME_CANT_BE_LATER_THAN_ETIME = "生效时间不能晚于失效时间";
	String COUPON_ETIME_CANT_BE_EARLIER_THAN_NOW = "失效时间不能早于当前时间";
	String COUPON_RANGE_TYPE_CANT_BE_NULL = "使用范围类型不能为空";
	String COUPON_RANGE_CANT_BE_NULL = "使用范围不能为空";
	String COUPON_ID_IS_EMPTY = "优惠券ID为空";
	String COUPON_IS_NOT_EXIST = "优惠券不存在";
	String COUPON_NOT_IN_SENDABLE_STATUS = "优惠券当前状态不允许发放";
	String COUPON_IS_INVALID = "优惠券已失效";
	String COUPON_IS_NOT_IN_DELETABLE_STATUS = "优惠券当前状态不允许删除";

	// wechat
	String WECHAT_GET_ACCESS_TOKEN_ERR = "微信授权失败";
	String WECHAT_LOGIN_PARAM_ERR = "微信登录参数错误";
	String WECHAT_LOGIN_ACC_NOT_EXIST = "微信登录账号不存在";

	// goods category
	String CATEGORY_NOT_EXIST = "分类不存在";
	String CATEGORY_ID_IS_NULL = "分类ID为空";
	String CATEGORY_ORDER_CANT_BE_NULL = "分类位置不能为空";
	String CATEGORY_DO_NOT_HAS_PRE_ORDER = "分类原位置不存在";
	String CANT_CHANGE_ORDER_OF_THIS_CATEGORY = "此分类位置不允许修改";
	String CATEGORY_ORDER_ERR = "分类顺序异常";
	String CATEGORY_NEW_ORDER_OUT_OF_BOUNDS = "分类新位置越界";
	String CANT_CHANGE_ORDER_OF_THIS_CATEGORY_TO_FIRST = "禁止将此分类置于首位";
	String CANT_DELETE_THIS_CATEGORY = "此分类禁止删除";
	String CANT_DELETE_CATEGORY_WITH_GOODS = "分类下有商品，禁止删除";
	String DUPLICATED_CATEGORY_NAME = "分类名重复";
	String CANT_CHANGE_NAME_OF_THIS_CATEGORY = "不允许修改此分类名称";
	String CATEGORY_NAME_CANT_BE_EMPTY = "分类名称不能为空";
	String CATEGORY_IMGURL_CANT_BE_EMPTY = "分类图片不能为空";

	// goods
	String GOODS_NAME_CANT_BE_EMPTY = "商品名称不允许为空";
	String GOODS_NOT_EXIST = "商品不存在";
	String GOODS_BELONG_CATEGORY_CANT_BE_NULL = "商品所属分类不允许为空";
	String GOODS_TEMPLATE_CANT_BE_NULL = "商品参数模板不能为空";
	String GOODS_TEMPLATE_VALUE_CANT_BE_EMPTY = "商品参数模板值不能为空";
	String GOODS_IMGURLS_CANT_BE_EMPTY = "商品图片不能为空";
	String GOODS_IMGURLS_NUM_ERR = "商品图片数量错误";

	// sku
	String SKU_NOT_EXIST = "SKU不存在";
	String SKU_BELONG_GOODS_CANT_BE_NULL = "所属商品不能为空";
	String SKU_WEIGHT_CANT_BE_NULL = "重量不能为空";
	String SKU_WEIGHT_VALUE_ERR = "重量有误";

	// shelRelSku
	String SHELVES_SKU_ID_IS_NULL = "货架SkuId为空";
	String SHELVES_SKU_NOT_EXIST = "货架Sku不存在";
	String SHELVES_SKU_NOT_ON_SALE = "商品未上架";
	String SHELVES_CANT_BE_NULL = "货架不能为空";
	String SHELVES_NOT_EXIST = "货架不存在";
	String SKU_CANT_BE_NULL = "SKU不能为空";
	String PRICE_CANT_BE_NULL = "价格不能为空";
	String SKU_ALREADY_ON_SHELVES = "SKU已处于上架状态";
	String SKU_ALREADY_NOT_ON_SHELVES = "SKU已处于下架状态";
	String SKU_NOT_ON_SHELVES = "SKU不处于上架状态";

	// 收藏夹
	String SHELVES_SKU_ALREADY_IN_FAVORITES = "商品已在收藏夹中";
	String SHELVES_SKU_NOT_IN_FAVORITES = "收藏夹中无此商品";

	// shopping cart
	String SKU_NOT_FOUND_IN_SP_CART = "未在购物车中找到此商品";

	// rcv info
	String NO_PERMISSION_TO_DELETE_THIS_RCVINFO = "没有删除此消息的权限";

	String MESSAGE_IS_NULL = "消息不能为空";
	String MESSAGE_SEND_FAIL = "消息推送失败";
	String MESSAGE_NOT_EXIST = "消息不存在";
	String MESSAGE_NOT_BELONG_BUYER = "消息不属于本用户";

	//
	String TEMPLATE_ID_IS_NULL = "参数模板ID为空";
	String TEMPLATE_NOT_EXIST = "参数模板不存在";
	String TEMPLATE_NOT_AVA = "参数模板不可用";
	String NEW_PARAM_IS_EMPTY = "新增参数为空";
	String TEMPLATE_NAME_CANT_BE_EMPTY = "参数模板名称不能为空";
	String TEMPLATE_CONTENT_CANT_BE_EMPTY = "参数模板内容不能为空";
	String DUPLICATED_NAME_TEMPLATE_EXIST = "有同名参数模板存在";
	String TEMPLATE_FORMAT_ERR = "参数模板格式有误";
	String TEMPLATE_PARAM_VALUE_FORMAT_ERR = "模板参数格式有误";
	String NEW_PARAM_FORMAT_ERR = "新模板参数格式有误";
	String PARAM_ALREADY_EXIST = "模板参数已存在";
}
