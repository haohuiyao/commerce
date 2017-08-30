/**
 * Created by Administrator on 2017/7/17.
 * 产品模块配置信息
 */

var ProductApi = (function() {

    //私有变量
    var PRODUCT_APIS  =
    {
        GET_GOODS_LIST : BASE_URL + "productManager/findProductList.do", //获取商品列表
        GET_BRAND_LIST : BASE_URL + "machineBrand/findMachineBrandList.do",//获取品牌列表
        ADD_BRAND : BASE_URL + "machineBrand/addBrand.action",//新增商品品牌
        MOD_BRAND : BASE_URL + "machineBrand/editBrand.action",//修改商品品牌
        DEL_BRAND : BASE_URL + "machineBrand/delBrand.action",//删除品牌
        MOD_BRAND_CLASSIFY : BASE_URL + "machineBrand/addCategoryBrand.action",//编辑品牌分类
        GET_CLASSIFY_LIST : BASE_URL + "category/getAllCategories.do",//获取产品分类
        ADD_CLASSIFY : BASE_URL + "category/addCategory.action",//新增产品分类
        MOD_CLASSIFY : BASE_URL + "category/editCategory.action",//修改产品分类
        DEL_CLASSIFY : BASE_URL + "category/delCategory.action",//删除产品分类
        //GET_PARAM_TEMPLATE_LIST : BASE_URL + "goodsManage/findTemplateList.do",//获取产品参数模板
        //GET_PARAM_TEMPLATE_DETAIL : BASE_URL + "goodsManage/getTemplateDetail.do",//获取参数模板的详情
        GET_PARAM_TEMPLATE_LIST : BASE_URL + "teamplate/findTemplateList.do",//获取产品参数模板
        GET_PARAM_TEMPLATE_DETAIL : BASE_URL + "teamplate/getTemplateDetail.do",//获取参数模板的详情
        ADD_PARAM_TEMPLATE : BASE_URL + "teamplate/addTemplateParam.action",//添加参数模板
        MOD_PARAM_TEMPLATE : BASE_URL + "teamplate/editTemplateParam.action",//修改参数模板
        DEL_PARAM_TEMPLATE : BASE_URL + "teamplate/delTeamplate.action",//删除参数模板
        GET_GOODS_DETAIL : BASE_URL + "goodsManage/getGoodsByIdWaPei.do",//获取产品组详情
        GET_GOODS_SOURCE : BASE_URL + "source/findSourceList.do",//获取产品货源
        GET_DISTRICT_LIST : BASE_URL + "shop/findDistrictList.do",//获取地区列表
        GET_GOODS_SKU_LIST : BASE_URL + "productManager/findSkuList.do",//获取SKU列表
        GET_GOODS_SKU_DETAIL : BASE_URL + "goodsManage/getSkuByIdWaPei.do",//获取全局产品详情
        GET_SOURCE_LIST : BASE_URL + "source/findSourceList.do",//获取货源列表
        ADD_SOURCE : BASE_URL + "source/addSource.action",//添加货源
        MOD_SOURCE : BASE_URL + "source/editSource.action",//修改货源
        DEL_SOURCE : BASE_URL + "source/delSource.do",//删除货源
        GET_FREIGHT_TEMPLATE_LIST : BASE_URL + "franking/findFrankingList.action",//获取运费模板列表
        ADD_FREIGHT_TEMPLATE : BASE_URL + "franking/addFranking.action",//添加运费模板
        DEL_FREIGHT_TEMPLATE : BASE_URL + "franking/delFranking.do",//删除获取运费模板
        MOD_FREIGHT_TEMPLATE : BASE_URL + "franking/editFranking.action",//修改运费模板


    }

    return PRODUCT_APIS;

    //var PRODUCT_APIS  =
    //{
    //    GET_GOODS_LIST :  "goodsManage/findGoodsListWaPei.do", //获取商品列表
    //    GET_BRAND_LIST : "partsBrand/findOriginalBrandList.do",//获取品牌列表
    //}
    //
    //return {
    //
    //    getApiUrl : function(API,apiName)
    //    {
    //        var url = API[apiName.toUpperCase()] ? API[apiName.toUpperCase()] : new Error("需要传入接口名称");
    //        return fn.combine(BASE_URL,url);
    //    },
    //    API : PRODUCT_APIS
    //};

})();

var ProductText = (function(){
    var TEXT_CONFIG  =
    {
        CHLAN : {
            //确认提示语
            CONFIRM_DEL_BRAND : "确认要删除这个品牌吗？",
            CONFIRM_DEL_CLASSIFY : "确认要删除这个分类吗？",
            CONFIRM_DEL_SOURCE : "确认要删除这个货源吗？",
            CONFIRM_DEL_FREIGHT_TEMPLATE : "确认要删除这个运费模板吗？",
            CONFIRM_DEL_PARAM_ITEM : "确认要删除这个参数吗？",

            //成功提示语
            SUCCESS_ADD_BRAND : "添加品牌成功",
            SUCCESS_MOD_BRAND : "修改品牌成功",
            SUCCESS_DEL_BRAND : "删除品牌成功",
            SUCCESS_ADD_CLASSIFY : "添加分类成功",
            SUCCESS_MOD_CLASSIFY : "修改分类成功",
            SUCCESS_DEL_CLASSIFY : "删除分类成功",
            SUCCESS_ADD_SOURCE : "添加货源成功",
            SUCCESS_MOD_SOURCE : "修改货源成功",
            SUCCESS_DEL_SOURCE : "删除货源成功",
            SUCCESS_ADD_FREIGHT_TEMPLATE : "添加运费模板成功",
            SUCCESS_MOD_FREIGHT_TEMPLATE : "修改运费模板成功",
            SUCCESS_DEL_FREIGHT_TEMPLATE : "删除运费模板成功",

            //警告提示语
            WARN_BRAND_NAME_EMPTY 			        : "请输入品牌名称",
            WARN_BRAND_IMAGE_EMPTY  		        : "请上传品牌图片",
            WARN_BRAND_ABBREVIATION_EMPTY 		    : "请输入品牌缩写",
            WARN_CLASSIFY_NAME_EMPTY 			    : "请输入分类名称",
            WARN_CLASSIFY_IMAGE_EMPTY 			    : "请上传分类图片",
            WARN_CLASSIFY_ABBREVIATION_EMPTY 		: "请输入分类缩写",
            WARN_UN_SEL_TEMPLATE 		            : "请选择参数模板",
            WARN_SOURCE_NAME_EMPTY 			        : "请输入货源名称",
            WARN_FREIGHT_TEMPLATE_NAME_EMPTY 	    : "请输入运费模板名称",
            WARN_FREIGHT_TEMPLATE_POSTAGE_EMPTY 	: "请选择是否包邮",
            WARN_FREIGHT_TEMPLATE_PATTERN_EMPTY 	: "请选择计费方式",
            WARN_FREIGHT_TEMPLATE_DISPATCHING_EMPTY : "请输入配送方式",
            WARN_FREIGHT_TEMPLATE_BASE_WEIGHT_EMPTY : "请输入运费首计",
            WARN_FREIGHT_TEMPLATE_BASE_PRICE_EMPTY 	: "请输入运费首价",
            WARN_FREIGHT_TEMPLATE_ADD_WEIGHT_EMPTY  : "请输入运费续计",
            WARN_FREIGHT_TEMPLATE_ADD_PRICE_EMPTY 	: "请输入运费续价",
            WARN_FREIGHT_TEMPLATE_SCOPE_EMPTY 		: "请输入配送区域",
            WARN_PARAM_ITEM_NAME_EMPTY		        : "请填写参数名称",
            WARN_PARAM_ITEM_TYPE_EMPTY		        : "请选择参数类型",
            WARN_PARAM_ITEM_OPTIONAL_EMPTY		    : "请选择参数是否必填",
            WARN_PARAM_ITEM_HINT_EMPTY		        : "请填写参数提示语",
            WARN_PARAM_ITEM_STATUS_EMPTY		    : "请选择默认显示状态",
            WARN_PARAM_ITEM_OPTION_EMPTY		    : "至少填写一个选项",



        }
    }

    return TEXT_CONFIG.CHLAN
})();