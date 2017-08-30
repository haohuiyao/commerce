app.directive("orderDetailHead", function() {
    return {
    	restrict:'AE',
    	replace : true,
    	scope : {
    		orderStatu : "=orderStatu"
    	},
        template:'<div class="order-detail-head"></div>', 
       	link:function(scope,element,attrs)
       	{
       		var html = "";
       		
       		scope.$watch('orderStatu',function(data){
       			
       			html = "";
       			
	       		if(scope.orderStatu == ORDER_STATU_WAIT_PAY)
	       		{
	       			html+='<div class="order-detail-head-1"></div>';
	       			html+='<div class="order-detail-head-2">'
	       			html+='<div class="order-detail-head-top">你已经拍下商品，请尽快支付~</div>';
	       			html+='<div class="order-detail-head-bo"><span class="font-color">2天23小时59分</span>后，订单将自动取消</div>';
	       			html+='</div>'
	       		}
	       		else if(scope.orderStatu == ORDER_STATU_TRADE_CLOSE)
	       		{
	       			html+='<div class="order-detail-head-1"></div>';
	       			html+='<div class="order-detail-head-2">'
	       			html+='<div class="order-detail-head-top">订单已关闭</div>';
	       			html+='</div>'
	       		}
	       		else if(scope.orderStatu == ORDER_STATU_WAIT_SEND)
	       		{
	       			html+='<div class="order-detail-head-1"></div>';
	       			html+='<div class="order-detail-head-2">'
	       			html+='<div class="order-detail-head-top">商家即将发货，请耐心等待~</div>';
	       			html+='</div>'
	       		}
	       		else if(scope.orderStatu == ORDER_STATU_WAIT_RECEIPT)
	       		{
	       			html+='<div class="order-detail-head-1"></div>';
	       			html+='<div class="order-detail-head-2">'
	       			html+='<div class="order-detail-head-top">卖家已经发货，请注意收货~</div>';
	       			html+='<div class="order-detail-head-bo"><span class="font-color">2天23小时59分</span>后，系统将会自动确认收货</div>';
	       			html+='</div>'
	       		}
	       		else if(scope.orderStatu == ORDER_STATU_WAIT_EVALUATE)
	       		{
	       			html+='<div class="order-detail-head-1"></div>';
	       			html+='<div class="order-detail-head-2">'
	       			html+='<div class="order-detail-head-top">交易成功，等您评价</div>';
	       			html+='<div class="order-detail-head-bo"><span class="font-color">2天23小时59分</span>后，系统将自动好评</div>';
	       			html+='</div>'
	       		}
	       		else if(scope.orderStatu == ORDER_STATU_TRADE_COMPLETE){
	       			html+='<div class="order-detail-head-1"></div>';
	       			html+='<div class="order-detail-head-2">'
	       			html+='<div class="order-detail-head-top">交易完成</div>';
	       			html+='</div>'
	       		}
//	       		else if(scope.orderStatu == ORDER_STATU_APPLY_REFUND){
//	       			html+='<div class="order-detail-head-1"></div>';
//	       			html+='<div class="order-detail-head-2">'
//	       			html+='<div class="order-detail-head-top">申请售后中</div>';
//	       			html+='</div>'
//	       		}
//	       		else if(scope.orderStatu == ORDER_STATU_REFUND_COMPLETE){
//	       			html+='<div class="order-detail-head-1"></div>';
//	       			html+='<div class="order-detail-head-2">'
//	       			html+='<div class="order-detail-head-top">售后完成</div>';
//	       			html+='</div>'
//	       		}
	       		else if(scope.orderStatu == ORDER_STATU_WAIT_UPLOAD){
	       			html+='<div class="order-detail-head-1"></div>';
	       			html+='<div class="order-detail-head-2">'
	       			html+='<div class="order-detail-head-top">等待上传支付凭证</div>';
	       			html+='</div>'
	       		}
	       		else if(scope.orderStatu == ORDER_STATU_HAS_UPLOAD){
	       			html+='<div class="order-detail-head-1"></div>';
	       			html+='<div class="order-detail-head-2">'
	       			html+='<div class="order-detail-head-top">等待审核支付凭证</div>';
	       			html+='</div>'
	       		}
	       		
       			element.html(html)
       		})
           	
       	}
    }
});
