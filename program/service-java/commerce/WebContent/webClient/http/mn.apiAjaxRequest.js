

/*
 * 获取接口数据
 */

var getApiData = {
	
	/*
	 * 获取机型列表
	 */
	getMachineBrandList : function(callback,failback,errback){
		$service.httpRequest("post",API_GET_MACHINEBRANDLIST,{},function(data){
			var list = data,
				itemObj = {},
				arrList = [];

			for(var i = 0; i < list.length; i++)
			{
				var listItem = list[i];
				listItem.isSel = false;
			    if(!itemObj[listItem.abbreviation]){
			        arrList.push({
			            abbreviation: listItem.abbreviation,
			            listArr: [listItem]
			        });
			        itemObj[listItem.abbreviation] = listItem;
			    }
			    else{
			        for(var j = 0; j < arrList.length; j++){
			            var arrItem = arrList[j];
			            if(arrItem.abbreviation == listItem.abbreviation){
			                arrItem.listArr.push(listItem);
			                break;
			            }
			        }
			    }
			}
			callback(arrList);
    	})
	},
	
	/*
	 * 获取货源列表
	 */
	getSourceList : function(callback,failback,errback){
		$service.httpRequest("post",API_GET_SOURCE_LIST,{},function(data){
			callback(data.list);
    	})
	},
	
	/*
	 * 	查找相关类别的机型组
	 */
	getMachineBrandById : function(machineId,callback,failback,errback){
		var params = {};
		params.machineId = machineId;
		
		$service.httpRequest("post", API_GET_MACHINE_BRAN_BYID, params, function(data){
			callback(data);
    	})
	},
	
	
	/*
	 * 获取商品列表
	 */
	getGoodsList : function(pageIndex,pageSize,condition,callback,failback,errback){
		var params = {};
		params.condition = condition;
		params.pageIndex = pageIndex;
		params.pageSize = pageSize;
		
		$service.httpRequest("post", API_GET_GOODS_LIST, params, function(data){
			
			var list = [];
			for(var i = 0; i < data.list.length; i++)
			{
				var itemObj = {};
				itemObj.goodsTitle = data.list[i].title;
				itemObj.goodsImage = data.list[i].displayImg;
//				itemObj.goodsPrice = data.list[i].price;
				itemObj.goodsShelvesId = data.list[i].skuId;
				
				list.push(itemObj)
			}
			data.list = list;
			
			callback(data);
    	})
	},
	
	/*
	 * 获取一级商品分类
	 */
	getOneCategories : function(callback,failback,errback){
		var params = {};
		$service.httpRequest("post", API_GET_ONE_CATEGORIES, params, function(data){
			var obj = {};
			obj.totalCount = data.totalCount;
			obj.list = [];
			for(var i = 0; i < data.list.length; i++)
			{
				var itemObj = data.list[i];
				itemObj.isSel = false;
				obj.list.push(itemObj)
			}
			callback(obj);
    	})
	},
	//获取二级商品分类
	getTwoCategories : function(parentId,callback,failback,errback){
		var params = {};
		params.pageIndex = "";
		params.pageSize = "";
		params.parentId = parentId;
		$service.httpRequest("post", API_GET_TWO_CATEGORIES, params, function(data){
			var obj = {};
			obj.totalCount = data.totalCount;
			obj.list = [];
			for(var i = 0; i < data.list.length; i++)
			{
				var itemObj = data.list[i];
				itemObj.isSel = false;
				obj.list.push(itemObj)
			}
			callback(obj);
    	})
	},
	/*
	 * 获取商品详情
	 */
	getGoodsDetail : function(skuId,callback,failback,errback){
		
//		mnWebMain.getDeviceID(function(udId){
			
//			var devNum = JSON.parse(udId).data.deviceID;
			var params = {};
			params.skuId = skuId;
//			params.devNum = devNum;
			
			$service.httpRequest("post", API_GET_GOODS_DETAIL, params, function(data){

				var goodsInfoObj = {};
				var sku = data.sku
				goodsInfoObj.name = sku.productName;
				goodsInfoObj.code = sku.code;
				goodsInfoObj.detail = sku.summary;
//				goodsInfoObj.machine = data.machine;
                goodsInfoObj.modelBrand = sku.modelBrand;

				goodsInfoObj.paramsValue = [];

				var paramsValObj = JSON.parse(sku.paramsValue);

			  	for(var key in paramsValObj){
					if(paramsValObj[key].isShow != 0)
					{
						goodsInfoObj.paramsValue.push(paramsValObj[key]);
					}
			    }
				goodsInfoObj.partsBrand = sku.partsBrand;
				goodsInfoObj.displayImg = sku.displayImg;
//				goodsInfoObj.sorceName = sku.source;
				goodsInfoObj.place = sku.place;
//				goodsInfoObj.price = sku.price;
				goodsInfoObj.shelvesId = sku.id;
				goodsInfoObj.title = sku.title;
				goodsInfoObj.bannerArr = JSON.parse(sku.picture);
//				goodsInfoObj.sales = (utility.isEmpty(data.sales))?0:data.sales;
				goodsInfoObj.brandInfoList = data.rel.sku;
//				goodsInfoObj.parentCategoryName = data.parentCategoryName;
//				goodsInfoObj.sonCategoryName = data.sonCategoryName;
				console.log(goodsInfoObj.brandInfoList)
				var detailItem = {};
				detailItem.goodsInfo = goodsInfoObj;
				
				callback(detailItem);
	    	})
//		})
		
	},

	
	/*
	 * 获取热门搜索
	 */
	getHotSearch : function(callback,failback,errback){
		
		$service.httpRequest("post", API_GET_HOTSEARCH, {}, function(data){
			var arr = [];
			for(var i = 0; i < data.length; i++)
			{
				var itemObj = {};
				itemObj.id = data[i].hotSearchId;
				itemObj.name = data[i].hotSearchName;
				
				arr.push(itemObj);
			}
			
			callback(arr);
    	})
		
	},
	
	
	
	
	
};

