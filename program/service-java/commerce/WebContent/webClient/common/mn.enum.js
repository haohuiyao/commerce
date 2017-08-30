/* common
/* ==========================================================================
 * 枚举转换
 * make by liangyong
 * 以 "_" 开始
 * ============================================================================ 
 */ 

var enumeration = {
	
	/*
	 * 根据订单的状态返回操作按钮
	 * isFinish:订单是否已完成
	 */
	getActionByOrderStatu:function(isFinish){
		
		if(isFinish)
		{
			return orderFinishBtnArr;
		}
		else
		{
			return orderWaitPayBtnArr;
		}
	},
	
	/*
	 * 
	 * 订单列表根据订单类型返回操作按钮数组
	 * 
	 */
	getMineOrderItemBtnArr : function(receiptMethod,orderType){
		
		var arr = [];
		if(orderType == ORDER_STATU_WAIT_PAY)
		{
			arr = [orderBtnType.btn_cancle,orderBtnType.btn_pay];
		}
		else if(orderType == ORDER_STATU_WAIT_SEND)
		{
			if(receiptMethod == 'Z'){
				arr = [orderBtnType.btn_takeCode]
			}
			else
			{
				arr = [orderBtnType.btn_remindSend]
			}
		}
		else if(orderType == ORDER_STATU_WAIT_RECEIPT)
		{
			if(receiptMethod == 'Z'){
				arr = [orderBtnType.btn_takeCode]
			}
			else
			{
				arr = [orderBtnType.btn_viewLogistics,orderBtnType.btn_confirmReceipt]
			}
		}
		else if(orderType == ORDER_STATU_WAIT_EVALUATE)
		{
			arr = [orderBtnType.btn_evaluate]
		}
		else if(orderType == ORDER_STATU_TRADE_CLOSE)
		{
			arr = [orderBtnType.btn_delete]
		}
		else if(orderType == ORDER_STATU_TRADE_COMPLETE)
		{
			arr = [orderBtnType.btn_delete]
		}
		else if(orderType == ORDER_STATU_WAIT_UPLOAD)
		{
			arr = [orderBtnType.btn_uploadPayProof];
		}
		else if(orderType == ORDER_STATU_HAS_UPLOAD)
		{
			arr = [];
		}
		
		return arr;
	},
	
	/*
	 * 根据订单类型返回操作按钮数组
	 * 
	 */
	getOrderDetailBtnArr : function(receiptMethod,orderType){
		var arr = [];
		if(orderType == ORDER_STATU_WAIT_PAY)
		{
			arr = [orderBtnType.btn_cancle,orderBtnType.btn_pay];
		}
		else if(orderType == ORDER_STATU_WAIT_SEND)
		{
			arr = [orderBtnType.btn_remindSend]
		}
		else if(orderType == ORDER_STATU_WAIT_RECEIPT)
		{
			if(receiptMethod == 'Z'){
				arr = [orderBtnType.btn_takeCode]
			}
			else
			{
				arr = [orderBtnType.btn_viewLogistics,orderBtnType.btn_confirmReceipt]
			}
		}
		else if(orderType == ORDER_STATU_WAIT_EVALUATE)
		{
			arr = [orderBtnType.btn_evaluate]
		}
		else if(orderType == ORDER_STATU_WAIT_UPLOAD)
		{
			arr = [orderBtnType.btn_uploadPayProof];
		}
		
		return arr;
	},
	
	/*
	 *根据订单类型返回订单状态标题
	 */
	getMineOrderItemTitle : function(type,faildReason)
	{
		var title = null;
		var data = {};
		data[ORDER_STATU_WAIT_PAY] = "待付款";
		data[ORDER_STATU_WAIT_SEND] = "待发货";
		data[ORDER_STATU_WAIT_RECEIPT] = "待收货";
		data[ORDER_STATU_WAIT_EVALUATE] = "待评价";
		data[ORDER_STATU_TRADE_CLOSE] = "已关闭";
//		data[ORDER_STATU_REFUND_COMPLETE] = "售后已完成";
		
		if(type == ORDER_STATU_WAIT_UPLOAD)
		{
			data[ORDER_STATU_WAIT_UPLOAD] = utility.isEmpty(faildReason)?"等待上传支付凭证":"审核失败，重新上传";
		}
		
//		data[ORDER_STATU_WAIT_UPLOAD] = "等待上传支付凭证";
		data[ORDER_STATU_HAS_UPLOAD] = "等待审核支付凭证";
		data[ORDER_STATU_TRADE_COMPLETE] = "交易完成";
		
		return data[type];
	},
	
	/*
	 *根据类型获取修改页面的标题
	 *1表示修改昵称；2表示公司名称；3表示手机号
	 */
	getModHeadTitle : function(type){
		var title = null;
		if(type == 1)
		{
			title = "修改昵称";
		}
		else if(type == 2)
		{
			title = "修改公司名称";
		}
		else if(type == 3)
		{
			title = "修改手机号";
		}
		return title;
	},
	
	/*
	 *根据类型获取修改页面的提示
	 *1表示修改昵称；2表示公司名称；3表示手机号
	 */
	getModPageMessage : function(type){
		var title = null;
		if(type == 1)
		{
			title = "请输入昵称";
		}
		else if(type == 2)
		{
			title = "请输入公司名称";
		}
		else if(type == 3)
		{
			title = "请输入手机号";
		}
		return title;
	},
	
	/*
	 *根据类型获取支付方式
	 */
	getPayMethodTxt : function(type){
		var txt = null;
		if(type == PAY_ALI)
		{
			txt = "支付宝支付";
		}
		else if(type == PAY_WX)
		{
			txt = "微信支付";
		}
		else if(type == PAY_OTHER)
		{
			txt = "其他支付";
		}
		else if(type == PAY_PTP)
		{
			txt = "公对公支付";
		}
		return txt;
	},
	
	getRewardText : function(type)
	{
		var data = {
			1 : "订单返现",
			2 : "推荐好友订单返现",
			3 : "后台派发积分",
			4 : "订单扣除",
			5 : "提现扣除",
			6 : "后台扣除积分",
			7 : '取消订单返回'
		};
		return data[type];
	},
	
	getPayAccount : function(type)
	{
		var data = {
			1 : "支付宝账号",
			2 : "微信账号",
			3 : "银行卡账号"
		};
		
		return data[type];
	},
	
	/*
	 * 获取单一商户售后状态按钮
	 */
	getGoodsRefundBtn : function(statu){
		
		var arr = {
			0 : "待客服审核",
			1 : "待财务审核",
			2 : "待客服填写收货地址",
			3 : "审核拒绝",
			4 : "售后已撤销",
			5 : "待买家发货",
			6 : "待买家收货",
			7 : "已退款",
			8 : "等待财务确认退款",
			9 : "退款进行中"
		}
		
		if(statu === '' || statu === null || statu === undefined)
		{
			return '申请售后';
		}
		
		return arr[statu];
	},
	
	/*
	 * 获取星级数组
	 */
	getCommentStarArr : function(commentStars){
		var arr = ['star-item','star-item','star-item','star-item','star-item'];
		
		var integer = parseInt(commentStars);
		var decimal = commentStars-integer;
		for(var i = 0; i < integer; i++)
		{
			arr[i] = 'star-act'
		}
		
		if(decimal >= 0.5)
		{
			arr[integer] = 'star-half';
		}
		return arr;
		
	}
};