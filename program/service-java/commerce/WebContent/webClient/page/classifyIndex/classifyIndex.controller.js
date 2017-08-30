/**
 * Created by Administrator on 2016/12/6.
 */
app.controller('ctrl', function($scope){

//  initMN(function(){
    	
//  	ClassifyCtrl.isIndex = utility.getQueryString("isIndex");
//  	ClassifyCtrl.nativeInvokeH5();
        ClassifyCtrl.init($scope);
        
//  })
});

var ClassifyCtrl = {
	scope: null,
	isIndex : 0,

    dataModel: {
    	hotMachineBrandList : [],
    	machineBrandList : [],
    	letterArr : [],
    	machineBrandDetail : {},
    },
	
	
	init: function($scope){
		
		$Loading.start();
		
		this.scope = $scope;
		
		this.initDataModel();
		
//		this.getHotMachineBrandList();
		
		this.getMachineBrandList();

        this.onClickFunction();
        
      	this.ngRepeatFinish();
	},
	
	/*
	 * 初始化数据模型
	 */
	initDataModel : function(){
		var self = this;
		this.scope.defaultObj = defaultObj;
		this.scope.dataModel = this.dataModel;
//		this.scope.$apply();
	},
	
	 /*
     * 原生调用h5
     */
//  nativeInvokeH5 : function(){
//  	
//  	mnWebMain.disableDrag();
//  	
//  	mnWebMain.cbNavTopClicked = function(){
//  		
//  		 var baseParam = {
//              url: URL_SEARCH_INDEX,
//              isHideNavBar: 0,
//              titleType: 1
//          };
//          var leftParam = [{"leftType" : 0,"type" : 1,"param" : "btn_arrow_lf"}];
//          var rightParam = [{"type" : 0,"param" : "搜索"}];
//          mnWebMain.openWebViewController(baseParam,leftParam,[],rightParam);
//  	}
//  	
//  	registerPushEvent.init();
//  },
	
	
	/*
	 * 获取热门机型品牌
	 */
//	getHotMachineBrandList : function(){
//		
//		var self = this;
//		
//		getApiData.getHotMachineBrandList(function(data){
//			self.dataModel.hotMachineBrandList = data;
//			self.scope.dataModel = self.dataModel;
//			self.scope.$apply();
//		})
//	},
//	
	/*
	 * 获取机型列表
	 */
	getMachineBrandList : function(){
		
		var self = this;
		
		getApiData.getMachineBrandList(function(data){
			$Loading.end();
			self.dataModel.machineBrandList = data;
			self.getSortLetter();
			self.scope.dataModel = self.dataModel;
			self.scope.$apply();
		});
	},
	
	
	/*
	 * 获取首页母
	 */
	getSortLetter : function(){
		
		var letterArr = [];
		
		for(var i = 0; i < this.dataModel.machineBrandList.length; i++)
		{
			this.dataModel.letterArr.push(this.dataModel.machineBrandList[i].abbreviation)
		}
		this.scope.dataModel = this.dataModel;
		this.scope.$apply();
	},

	/*
	 * ng-repeat完成执行事件
	 */
	ngRepeatFinish: function(){
		var self = this;
		
		self.scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent){
			
		})
	},

    //绑定事件
    onClickFunction: function(){
        var self = this;
        
        /*
         * 查找相关类别的机型组
         */
        this.scope.onClickGetMachineDetail = function(id){
        	$("body").css("overflow","hidden");
        	$(".machine-detail").show();
        	$(".detail-content").addClass("slideLf");
        	
        	var params = {};
			params.id = id;
			
			$service.httpRequest("post", API_GET_MACHINE_BRAN_BYID_NOCURRENCY, params, function(data){
				console.log(data.brand)
				self.dataModel.machineBrandDetail = data.brand;
        		self.scope.dataModel = self.dataModel;
        		self.scope.$apply();
	    	})
        }
        
        /*
         * 选择
         */
        this.scope.onClickSelMachineBrand = function(id,machineBrandName,machineModelName,type){
        	var params = {};
        	params.machineModelId = id;
        	params.machineBrandId = self.dataModel.machineBrandDetail.id;
        	params.machineBrandName = machineBrandName;
        	params.machineBrandName = machineModelName;
        	console.log(params)
//      	if(self.isIndex == 1)
//      	{
//      		mnWebMain.transferData(params)
//      		mnWebMain.closeSelfViewController();
//      	}
//      	else
//      	{
        		self.hideRightNav();
        		var URL = URL_GOODS_LIST + "?modelId="+params.machineModelId+"&brandId="+params.machineBrandId + "&machineBrandName="+machineBrandName+"&machineModelName="+machineModelName + "&type=" + type;
//      		var baseParam = {
//	                url: URL,
//	                isHideNavBar: 0,
//	                titleType: 1
//	            };
//	            var leftParam = [{"leftType" : 0,"type" : 1,"param" : "btn_arrow_lf"}];
//	            var rightParam = [{"type" : 0,"param" : "搜索"}];
//	            mnWebMain.openWebViewController(baseParam,leftParam,[],rightParam);
	            location.href = URL;
//      	}
        	
        }
        
        this.scope.onClickHideNav = function(){
        	self.hideRightNav();
        }
        
        this.scope.onClickSelAll = function(){
        	var params = {};
        	params.machineModelId = '';
        	params.machineBrandId = '';
        	
        	if(self.isIndex == 1)
        	{
        		mnWebMain.transferData(params);
        		mnWebMain.closeSelfViewController();
        	}
        	else
        	{
        		self.hideRightNav();
        		var URL = URL_GOODS_LIST + "?modelId="+params.machineModelId+"&brandId="+params.machineBrandId;
//      		var baseParam = {
//	                url: URL,
//	                isHideNavBar: 0,
//	                titleType: 1
//	            };
//	            var leftParam = [{"leftType" : 0,"type" : 1,"param" : "btn_arrow_lf"}];
//	            var rightParam = [{"type" : 0,"param" : "搜索"}];
//	            mnWebMain.openWebViewController(baseParam,leftParam,[],rightParam);
	            location.href = URL;
        	}
        }
        this.scope.goBack = function() {
        	history.back();
        }
    },
    
    /*
     * 
     */
    hideRightNav : function(){
    	$(".detail-content").removeClass("slideLf");
		$("body").css("overflow","")
		$(".machine-detail").hide();
    }
};