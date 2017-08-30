/**
 * controller
 * ============================================
 * 
 * ============================================
 */

// 2. 调用Controller
app.controller('ctrl',function($scope){
//	initMN(function(){
		
		headPosition.setHeadPosition(function(){
			$('.goods-detail-head').css('height','74px');
			$('.goods-detail-head').css('padding-top','20px');
			$('.banner').css('padding-top','20px');
			$('.circular-back').css('top','30px');
			$('.circular-home').css('top','30px');
			$('.circular-news').css('top','30px');
		})
		
//		mnWebMain.disableDrag();
		
		GoodsDetailController.skuId = utility.getQueryString("skuId");
		GoodsDetailController.cbFunction();
		
		GoodsDetailController.init($scope);
		
//	})
	
});

var GoodsDetailController = {
	
	//全局变量
	scope : null,
	skuId : null,
	bool : null,
	buyerId : "",
	
	//数据模型
	dataModel : {
		goodsInfo : {},
		isCollect : false,
		commentCount : null,
		commentStars : null,
		commentList : [],
		commentLable : [],
		easeMobList : [],
		guessList : [],
		commentStarsArr : [],
	},
	
	/**
	 * 初始化
	 * @param {Object} $scope
	 */
	init : function($scope){
		
		$Loading.start();
		
		this.scope = $scope;
		
		this.initDataModel();

//		this.getUserInfo();
		
//		this.checkIsCollect();
		
		this.getGoodsDetail();
		
		this.onClickFunction();
		
		this.ngRepeatFinish();
		
//		this.getCommentList();
		
//		this.addCartAnimation();
		
//		this.getCartCount();
		
//		this.getGuessList();
	},
	
	/*
	 * 初始化数据模型
	 */
	initDataModel : function(){
		
		this.scope.cartCount = 0;
		this.scope.datamode = this.dataModel;
//		this.scope.$apply();
		
	},


	/**
	 * 获取本地存储的用户信息
	* */
//	getUserInfo : function () {
//		var self = this;
//
//		if(utility.isEmpty(mnWebMain.syncGetLocalStorage(K_EX_USER_INFO)))
//		{
//			self.buyerId = null;
//		}
//		else
//		{
//			self.buyerId = JSON.parse(mnWebMain.syncGetLocalStorage(K_EX_USER_INFO)).buyerId;
//		}
//
//	},
	
	/*
	 * 获取商品详情
	 */
	getGoodsDetail : function(){
		
		var self = this;
		getApiData.getGoodsDetail(self.skuId,function(data){
			console.log(data)
			self.dataModel.goodsInfo = data.goodsInfo;
			self.scope.dataModel = self.dataModel;
			self.scope.$apply();
			$Loading.end();
		})
	},
	
//	/*
//	 * 获取商品评论
//	 */
//	getCommentList : function(){
//		
//		var self = this;
//		
//		var params = {};
//		params.id = self.shelvesId,
//		params.pageIndex = 1;
//		params.pageSize = 5;
//		params.isRecommend = 1;
//		
//		$service.httpRequest('post', API_GET_GOODS_COMMENT, params, function(data){
//
//			
//			
//			self.dataModel.commentCount = data.totalCount;
//			self.dataModel.commentStars = (data.stars/data.totalCount).toFixed(1);
//			self.dataModel.commentLable = data.goodLable;
//			
//			self.dataModel.commentStarsArr = enumeration.getCommentStarArr(self.dataModel.commentStars)
//			
//			self.scope.isCommentEmpty = (data.buyerComment.length == 0)?false:true;
//		
//			for(var n = 0; n < data.buyerComment.length; n++)
//			{
//				//alert(JSON.stringify(data.buyerComment[n]))
//				data.buyerComment[n].label = (utility.isEmpty(data.buyerComment[n].label))?[]:data.buyerComment[n].label.split(',');
//				data.buyerComment[n].imgs = JSON.parse(data.buyerComment[n].imgs);
//				data.buyerComment[n].stars = data.buyerComment[n].stars;
//				data.buyerComment[n].userImg = data.buyerComment[n].userImg;
//				data.buyerComment[n].comment = (utility.isEmpty(data.buyerComment[n].comment))?'该买家没有留下任何留言...':data.buyerComment[n].comment;
//			}
//			self.dataModel.commentList = data.buyerComment;
//			
//			self.scope.dataModel = self.dataModel;
//			self.scope.$apply();
//		})
//	},
	
	/*
//	 * 获取猜你喜欢
//	 */
//	getGuessList : function(){
//		
//		var self = this;
//		
//		var params = {};
//		params.pageIndex = 1;
//		params.pageSize = 4;
//		params.shelvesId = self.shelvesId;
//		
//		$service.httpRequest('post', API_GUESS_GOODS, params, function(data){
//			var list = data.list;
//			for(var i = 0; i < list.length; i++)
//			{
//				var itemObj = {};
//				itemObj.goodsTitle = list[i].title;
//				itemObj.goodsImage = list[i].displayImg;
//				itemObj.goodsPrice = list[i].price;
//				itemObj.goodsShelvesId = list[i].shelvesId;
//				
//				self.dataModel.guessList.push(itemObj)
//			}
//			self.scope.dataModel = self.dataModel;
//			self.scope.$apply();
//		})
//	},
	
	
	/*
	 * 定义原生调用方法
	 */
	cbFunction : function(){
		
		var self = this;
		
	},
	
	/*
	 * 点击事件
	 */
	onClickFunction : function(){
		
		var self = this;
		
		
		/*
		 * 返回列表
		 */
		this.scope.onClickBack = function(){
//			mnWebMain.closeSelfViewController();
			history.back();
		}
		
		/*
		 * 首页
		 */
		this.scope.onClickHome = function(){
//			mnWebMain.openViewController('ui-index');
			location.href = URL_GOODS_LIST;
		}
		
		
		/*
		 * 评论详情
		 */
		this.scope.cbClickCommentDetail = function(){
			var URL = URL_GOODS_COMMENT_DETAIL + '?goodsId=' + self.shelvesId;
            location.href = URL;
		};
		
		
		
		
		/*
		 * 挖配自营介绍
		 */
		this.scope.toGoodsDesc = function(){
			console.log(123)
			$(".goodsExplain").fadeIn();
			
		}
		this.scope.closeModal = function(){
			$(".goodsExplain").fadeOut();
			
		}
		/*
		 * 查看大图
		 */
//		this.scope.showBigImg = function(arr,index){
//			mnWebMain.browserPics(index, arr);
//		}
	},
	
	
	
	
	
	/*
	 * 循环结束之后执行事件
	 * 
	 */
	ngRepeatFinish: function(){
		
		var self = this;
		
		self.scope.$on('ng1Finished', function(ngRepeatFinishedEvent){
			self.swiperInit();
		})
	},
	
	
	/*
	 * swiper初始化
	 */
	swiperInit : function(){
		
		var self = this;
		
        var swiper = new Swiper('.swiper-container', {
            pagination: '.swiper-pagination',
            paginationClickable: true,
            autoplay: 3000,
            autoplayDisableOnInteraction: false,
            resistanceRatio : 0,
        });
	},
	
};


$(window).scroll(function(){
　　	var scrollTop = $(this).scrollTop();
　　	var scrollHeight = $(document).height();
　　	var windowHeight = $(this).height();
	var test = $('.guess-like').height();
	
　　if(scrollTop + windowHeight  == scrollHeight)
	{
		$(".show-more-detail").hide();
　　　　	$(".goods-detail").html(GoodsDetailController.dataModel.goodsInfo.detail)
		$('.guess-like').show();
　　	}
	
	if(scrollTop == 0)
	{
		$(".goods-detail-head").hide();
		$(".goods-detail-head").css('opacity',0);
	}
	else{
		var opacity = scrollTop/48;
		$(".goods-detail-head").show();
		$(".goods-detail-head").css('opacity',opacity);
	}
	
});