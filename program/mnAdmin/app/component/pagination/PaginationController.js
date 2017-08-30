/**
 * =========================================================================================================================
 * 
 * {cotroller name} 分页控制器
 * {author} liang
 * =========================================================================================================================
 */
const PAGE_DEFAULT_NUM = 10;

var PageController = {
	
	//全局变量
	scope : null,
	
	callback : null,
	api : null,
	fixedParams : {},
	currentPage : 1,
	totalPage : null,
	num : PAGE_DEFAULT_NUM,
	searchParams : {},
	
	/**
	 * 分页始化
	 * @param $scope
     */
	init : function($scope, api , fixedParams ,callback, num)
	{
		this.scope = $scope;
		
		this.api = api;
		
		this.fixedParams = fixedParams;
		
		this.num = num ? num : PAGE_DEFAULT_NUM;
		
		this.callback = callback;
		
		this.resetParams();

		this.callApi();
	},
	
	/**
	 * 设置默认
	 */
	resetParams : function()
	{
		this.currentPage = 1;
	},
	
	/**
	 * 设置分页页数
	 * @param {Object} count
	 */
	setTotalPage : function(count)
	{
		var self = this;

		self.totalPage = Math.ceil(count / self.num);

		
		self.scope.option = {
	        curr: self.currentPage,  
	        all: self.totalPage, 
	        count: 10,  
	        click: function (page) {
	        	self.currentPage = page;
	        	self.callApi();
	        }
	    }
		
		self.scope.$apply();
	},
	
	/**
	 * 当搜索条件变化的时候调用此方法
	 * @param {Object} searchParam
	 */
	searchChange : function(searchParam)
	{
		this.resetParams();
		
		this.searchParams = searchParam;
		this.callApi();
	},
	
	/**
	 * 请求数据
	 */
	callApi : function()
	{
		var self = this;

		var pageParams = {
			"pageIndex" : this.currentPage,
			"pageSize" : this.num
		};

		var requestParams = $.extend(pageParams,this.fixedParams,this.searchParams);

		var params = {};
		params.type = 'post';
		params.dataApi = this.api;
		params.requestParams = requestParams;
		params.callback = this.callback;


		HttpService.ajaxRequest(params)
	},
	
}
