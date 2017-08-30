/**
 * controller
 * ============================================
 * 
 * ============================================
 */

// 2. 调用Controller
app.controller('ctrl', function($scope) {
	//	initMN(function(){

	//		GoodsListController.dataModel.condition.name = utility.getQueryString('name');
	GoodsListController.dataModel.condition.machineModel = utility.getQueryString('modelId');
	GoodsListController.dataModel.condition.machineBrand = utility.getQueryString('brandId');
	GoodsListController.dataModel.condition.parentCategroy = utility.getQueryString('parentCategory');
	GoodsListController.dataModel.condition.sonCategory = utility.getQueryString('sonCategory');
	GoodsListController.dataModel.condition.parentCategoryName = utility.getQueryString('parentCategoryName');
	GoodsListController.dataModel.condition.sonCategoryName = utility.getQueryString('sonCategoryName');
	GoodsListController.dataModel.condition.machineBrandName = utility.getQueryString('machineBrandName');
	GoodsListController.dataModel.condition.machineModelName = utility.getQueryString('machineModelName');
	GoodsListController.dataModel.condition.type = utility.getQueryString('type');
	GoodsListController.sorce = utility.getQueryString('isFromeRecommend');
	GoodsListController.init($scope);

});

var GoodsListController = {

	//全局变量
	scope: null,
	pageIndex: 1,
	pageSize: 10,
	totalPage: 0,
	//	isFirst : true,

	sorce: '', //来源  是否是为你推荐 原来判断是否显示机型筛选

	//数据模型
	dataModel: {
		islist: false, //列表形式
		isFilterGoodsType: false, //商品类型筛选
		isFilterGoodsSupply: false, //商品货源筛选
		sourceList: [],
		firCategoriesList: [],
		secCategoriesList: [],
		goodsList: [],
		condition: {
			name: "",
			type : "",
			categoryId : "153",
			parentCategroy: '',
			sonCategory: '',
			machineBrand: '',
			machineModel: '',
			sourceId: '',
			ordRule: '',
			parentCategroyName: '',
			sonCategoryName: '',
			machineBrandName: '',
			machineModelName: '',
		},
		priceIcon: '../../../assets/images/icon/icon-unup-gray.png',
		sortType: null,
	},

	/**
	 * 初始化
	 * @param {Object} $scope
	 */
	init: function($scope) {

		this.scope = $scope;

		tabFixed.init()

		this.initDataModel();

		this.getGoodsList();

		this.getOneCategories("");

//		this.getSourceList();

		this.onClickFunction();

		this.ngRepeatFinish();
	},

	/*
	 * 初始化数据模型
	 */
	initDataModel: function() {
		this.scope.sorce = this.sorce;
		this.scope.dataModel = this.dataModel;
		//		this.scope.$apply();
	},

	/*
	 * 初始化分页
	 */
	initPageModel: function() {

		this.dataModel.goodsList.length = 0;
		this.pageIndex = 1;
		this.pageSize = 10;
	},

	/*
	 * 点击事件
	 */
	onClickFunction: function() {

		var self = this;

		/*
		 * 排序type1表示人气，2表示销量，3表示新品，4表示价格
		 */
		this.scope.onClickSort = function(type) {
			self.dataModel.sortType = type;
			switch(type) {
				case 1:
					self.dataModel.priceIcon = '../../../assets/images/icon/icon-unup-gray.png';
					self.dataModel.condition.ordRule = SORT_RULE_POP_DESC;
					break;
				case 2:
					self.dataModel.priceIcon = '../../../assets/images/icon/icon-unup-gray.png';
					self.dataModel.condition.ordRule = SORT_RULE_SALES_DESC;
					break;
				case 3:
					self.dataModel.priceIcon = '../../../assets/images/icon/icon-unup-gray.png';
					self.dataModel.condition.ordRule = SORT_RULE_PUTIME_DESC;
					break;
				case 4:
					if(self.dataModel.condition.ordRule == SORT_RULE_PRICE_DESC) {
						self.dataModel.priceIcon = '../../../assets/images/icon/icon-unup-unred.png';
						self.dataModel.condition.ordRule = SORT_RULE_PRICE_ASC;
					} else {
						self.dataModel.priceIcon = '../../../assets/images/icon/icon-unup-upred.png';
						self.dataModel.condition.ordRule = SORT_RULE_PRICE_DESC;
					}
					break;
				default:
					break;
			}
			self.initPageModel();
			self.getGoodsList();
		}

		this.scope.onClickSelGoodsType = function() {
			self.dataModel.isFilterGoodsType = !self.dataModel.isFilterGoodsType;
			self.dataModel.isFilterGoodsSupply = false;
			self.filterFixStatu();
		}

		this.scope.onClickSelGoodsSupply = function() {
			self.dataModel.isFilterGoodsType = false;
			self.dataModel.isFilterGoodsSupply = !self.dataModel.isFilterGoodsSupply;
			self.filterFixStatu();
		}

		/*
		 * 选择货源
		 */
		this.scope.selSorceItem = function(id, sourceName) {
			self.scope.sourceName = utility.isEmpty(id) ? null : sourceName
			self.dataModel.sourceList = CommonSelect.selectItem(self.dataModel.sourceList, 'id', id, 'isSel', true);
			self.dataModel.isFilterGoodsSupply = false;
			self.filterFixStatu();

			self.dataModel.condition.sourceId = id;
			self.initPageModel();
			self.getGoodsList();
		}

		/*
		 * 选择机型
		 */
		this.scope.onClickSelMachineBrand = function() {
			var URL = URL_CLASSIFY_INDEX
			location.href = URL;
		};

		/*
		 * 选择一级商品类型
		 */
		this.scope.onClickSelFirCategory = function(id, parentCategoryName) {
			self.dataModel.condition.parentCategroy = id;
			self.dataModel.condition.parentCategoryName = parentCategoryName;
			self.dataModel.condition.sonCategoryName = '';
			self.dataModel.firCategoriesList = CommonSelect.selectItem(self.dataModel.firCategoriesList, 'categoryId', id, 'isSel', true);
			self.scope.oneCategoryId = id;
			self.getTwoCategories(id);
			$(".filter-list-rg").removeClass("hiddenIm");

		};

		/*
		 * 选择所有一级
		 */
		this.scope.onClickSelAllFirCategory = function() {
			self.dataModel.firCategoriesList = CommonSelect.selectItem(self.dataModel.firCategoriesList, 'categoryId', '', 'isSel', true);
			self.dataModel.isFilterGoodsType = false;
			self.filterFixStatu();
			self.dataModel.condition.parentCategroy = '';
			self.dataModel.condition.sonCategory = '';
			self.dataModel.condition.parentCategoryName = '';
			self.dataModel.condition.sonCategoryName = '';
			self.dataModel.condition.categoryId = "153";
			$(".filter-list-rg").addClass("hiddenIm");
			self.initPageModel();
			self.getGoodsList();
		}

		/*
		 * 选择二级商品类型
		 */
		this.scope.onClickSelSecCategory = function(id, sonCategoryName) {
			self.dataModel.isFilterGoodsType = false;
			self.filterFixStatu();
			self.dataModel.secCategoriesList = CommonSelect.selectItem(self.dataModel.secCategoriesList, 'categoryId', id, 'isSel', true);
			self.dataModel.condition.categoryId = id;
//			self.dataModel.condition.sonCategoryName = sonCategoryName;
			self.initPageModel();
			self.getGoodsList();
		}

		this.scope.hideFilter = function() {
			self.dataModel.isFilterGoodsType = false;
			self.dataModel.isFilterGoodsSupply = false;
			self.filterFixStatu();
		}

		this.scope.onclickToTop = function() {
			$('body').scrollTop(0)
		}
		/*
		 * 搜索
		 */
		this.scope.$watch('inputValue', function(newValue) {
			if(newValue == "" || newValue == undefined) {
				$(".search").addClass('search-center');
				$(".search-clear").addClass("hiddenIm")
			} else {
				$(".search").removeClass('search-center')
				$(".search-clear").removeClass("hiddenIm")
			}
		});
		this.scope.search = function() {
			self.dataModel.goodsList = [];
			self.dataModel.condition.keyword = self.scope.inputValue;
			self.getGoodsList();
		}
		this.scope.clearInput = function($event) {
			$event.stopPropagation();
			self.scope.inputValue = "";
			//      		setTimeout(function(){
			//      			self.scope.clearMethod();
			//      		},300)
		}
        this.scope.toLoadMore = function(){
        	self.pageIndex+=1;
        	self.pageSize+=10;
        	self.getGoodsList();
        }
	},

	/*
	 * 筛选条件位置状态
	 */
	filterFixStatu: function() {
		if(this.dataModel.isFilterGoodsType == true || this.dataModel.isFilterGoodsSupply == true) {
			//			$("#float").addClass("list-filter-fixed");
			$("body").css('overflow', 'hidden');
		} else {
			//			$("#float").removeClass("list-filter-fixed");
			$("body").css('overflow', '');
		}
	},

	/*
	 * 获取商品列表
	 */
	getGoodsList: function() {

		$Loading.start();

		var self = this;

		var condition = {};
		condition.keyword = self.dataModel.condition.keyword;
		condition.categoryId = self.dataModel.condition.categoryId;
//		condition.sourceId = self.dataModel.condition.sourceId;

		var showMachineName = utility.isEmpty(self.dataModel.condition.machineModelName) ? self.dataModel.condition.machineBrandName : self.dataModel.condition.machineModelName
		var showCategroyName = utility.isEmpty(self.dataModel.condition.sonCategoryName) ? self.dataModel.condition.parentCategoryName : self.dataModel.condition.sonCategoryName

		self.scope.machineName = utility.isEmpty(showMachineName) ? null : showMachineName;
		self.scope.categroyName = utility.isEmpty(showCategroyName) ? null : showCategroyName;
		
		var obj ={
			objType2 : self.dataModel.condition.type,
			objId2 : self.dataModel.condition.machineModel
		}
		condition.pRelArr  = [];
		condition.pRelArr.push(obj);
		getApiData.getGoodsList(self.pageIndex, self.pageSize, condition, function(data) {

			self.totalPage = Math.ceil(data.totalCount / 10);
			
			self.dataModel.goodsList = self.dataModel.goodsList.concat(data.list);
			if(data.list.length < 10){
				$(".more").hide();
				$(".noMore").show();
			}
			if(self.dataModel.goodsList.length == 0){
				$(".more").hide();
				$(".noMore").hide();
			}
			self.scope.dataModel = self.dataModel;
			self.scope.$apply();
			$Loading.end();
		})
	},

	/*
	 * 获取所有分类列表
	 */
	getOneCategories: function() {

		var self = this;

		getApiData.getOneCategories(function(data) {
			
				self.dataModel.firCategoriesList = data.list;
			
			self.scope.dataModel = self.dataModel;
			self.scope.$apply();
		});

	},
	getTwoCategories: function(id) {

		var self = this;

		getApiData.getTwoCategories(id, function(data) {		
				self.dataModel.secCategoriesList = data.list;
				
			self.scope.dataModel = self.dataModel;
			self.scope.$apply();
		});

	},

	/*
	 * 获取货源
//	 */
//	getSourceList: function() {
//		var self = this;
//		getApiData.getSourceList(function(data) {
//			var obj = {};
//			obj.id = "";
//			obj.sourceName = "全部货源";
//			obj.isSel = true;
//			data.unshift(obj)
//			self.dataModel.sourceList = data;
//			self.scope.dataModel = self.dataModel;
//			self.scope.$apply();
//		});
//	},

	/**
	 * ng-repeat 循环完成的回调函数
	 */
	ngRepeatFinish: function() {
		var self = this;

		this.scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {

		});
	},

}

/*
 * 监听屏幕滚动
 */
var tabFixed = {

	init: function() {
		console.log($(".goods-list").height())
		$("#float").scroll(function() {
			console.log(123)
//			var $this = $(this),
//				viewH = $(this).height(), //可见高度  
//				contentH = $(this).get(0).scrollHeight, //内容高度  
//				scrollTop = $(this).scrollTop(); //滚动高度  
//
//			//if(contentH - viewH - scrollTop <= 100) { //到达底部100px时,加载新内容  
//			if(scrollTop / (contentH - viewH) >= 0.95) { //到达底部100px时,加载新内容  
//
//			}
		});

	}

}