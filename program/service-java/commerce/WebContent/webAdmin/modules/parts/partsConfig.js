/**
 * Created by Administrator on 2017/7/26.
 * 配件管理配置文件
 */
var PartsApi = (function() {

    //私有变量
    var PROJECT_APIS  =
    {
        GET_PARTS_PRODUCT_LIST : BASE_URL + "productManager/getPartsProduct.do",//产品组管理
        GET_PARTS_SKU_LIST : BASE_URL + "productManager/getPartsSku.do",//产品管理
        GET_BRAND_BY_CLASSIFY : BASE_URL + "machineBrand/getBrandByCategory.do",//根据分类获取品牌列表
        GET_PRODUCT_BY_BRAND : BASE_URL + "machineManager/findProductByBrand.do",//根据品牌获取商品
        ADD_PARTS_PRODUCT : BASE_URL + "productManager/addParts.action",//新增产品机型
        GET_PARTS_PRODUCT_DETAIL : BASE_URL + "productManager/getPartsDetailById.do",//获取产品组详情
        MOD_PARTS_PRODUCT : BASE_URL + "productManager/editParts.action",//修改配件组
        GET_PARTS_SKU_DETAIL : BASE_URL + "productManager/getPartsSkuDetail.do",//获取配件sku
        PUT_UP_EXCAVATOR_SKU : BASE_URL + "machineManager/putUpSku.action",//上架sku
        PUT_OUT_EXCAVATOR_SKU : BASE_URL + "machineManager/putOutSku.action",//下架sku
        EDIT_PARTS_SKU : BASE_URL + "productManager/editPartsSku.action",//修改sku

    }

    return PROJECT_APIS;

})();

var PartsText = (function(){
    var TEXT_CONFIG  =
    {
        CHLAN : {
            //确认提示语
            CONFIRM_PUT_DOWN : "确认要下架吗？",
            CONFIRM_PUT_UP : "确认要上架吗？",

            //成功提示语
            SUCCESS_PUT_DOWN : "下架成功！",
            SUCCESS_PUT_UP : "上架成功！",

            //警告提示语
            WARN_BRAND_NAME_EMPTY 			        : "请输入品牌名称",

        }
    }

    return TEXT_CONFIG.CHLAN
})();