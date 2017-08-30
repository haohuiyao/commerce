

app.directive('goodsListItem', function() { 
    return { 
        restrict : 'AE', 
        template : '<div ng-click="toGoodsDetail()">'+
					'<!--横向-->'+
					'<div ng-show="showlist" class="ub goodsListItem-li" data-id = "{{showlist}}">'+
						'<div class="ub-f1 goodsListItem-lf">'+
							'<back-img imageurl="goodsInfo.goodsImage" defaulturl="../../../assets/images/default/default-goods-pic.png"></back-img>'+
							
						'</div>'+
						'<div class="ub-f1 goodsListItem-rg">'+
							'<div class="goodsListItem-rg-name" ng-bind="goodsInfo.goodsTitle">小松pc200-7 s6d102 pn291喷漆嘴 杰克赛尔 日本产</div>'+
//							'<div class="goodsListItem-rg-price">'+
//								'<span class="price-lf">¥</span>'+
//								'<span class="price-rg" ng-bind="goodsInfo.goodsPrice">100.00</span>'+
//							'</div>'+
						'</div>'+
//						'<i class="shoppingCart" ng-click="addCart($event)"></i>'+
					'</div>'+
					'<!--纵向-->'+
					'<div ng-show="!showlist" class="goodsListItem-fl">'+
						'<div class="goodsListItem-fl-con">'+
							'<div class="goodsListItem-fl-pic">'+
								'<back-img imageurl="goodsInfo.goodsImage" defaulturl="../../../assets/images/default/default-goods-pic.png"></back-img>'+
							'</div>'+
							'<div>'+
								'<div class="goodsListItem-fl-name" ng-bind="goodsInfo.goodsTitle">小松pc200-7 s6d102 pn291喷漆嘴 杰克赛尔 日本产</div>'+
//								'<div class="goodsListItem-fl-price" ><span>¥</span><span ng-bind="goodsInfo.goodsPrice">100.00</span></div>'+
							'</div>'+
						'</div>'+
					'</div>'+
				'</div>', 
        replace : true,
        scope : {
        	goodsInfo : "=goodsInfo",
        	islist : "=islist",
        	showlist : "@"
        },
        link : function(scope, element, attrs)
        {
        	scope.$watch('islist', function(newValue)
            { 
            	scope.showlist = newValue;
            });
            
            scope.toGoodsDetail = function(){
            	
            	var URL = URL_GOODS_DETAIL + "?skuId=" + scope.goodsInfo.goodsShelvesId;
            	
//          	var baseParam = {
//	                "url" : URL,
//	                "isHideNavBar" : 1,
//	                "titleType" : 0
//	            };
//	            var centerParam = [{"type" : 0,"param" : "商品详情"}];
//	            var leftParam = [{"leftType":0, "type" : 1 ,"param" : "btn_arrow_lf"}];
//	            var rightParam = [{"type" : 0,"param" : "分享"},{"type" : 0,"param" : "消息"}];
//	
//	            mnWebMain.openWebViewController(baseParam,leftParam,centerParam,rightParam);
                location.href = URL;
            }
            
        }
    }; 
}); 