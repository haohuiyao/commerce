/**
 * Created by daipengpeng on 2017/7/26.
 * 挖机管理配置文件
 */
var ExcavatorApi = (function() {

    //私有变量
    var PROJECT_APIS  =
    {
        GET_EXCAVATOR_PRODUCT_LIST : BASE_URL + "machineManager/getMachineProduct.do",//获取机型组列表
        GET_EXCAVATOR_SKU_LIST : BASE_URL + "machineManager/getMachineModel.do",//获取机型型号列表
        GET_BRAND_BY_CLASSIFY : BASE_URL + "machineBrand/getBrandByCategory.do",//根据分类获取品牌列表
        ADD_EXCAVATOR_PRODUCT : BASE_URL + "machineManager/addMachineProduct.action",//添加机型组
        MOD_EXCAVATOR_PRODUCT : BASE_URL + "machineManager/editProduct.action",//修改机型组
        ADD_EXCAVATOR_SKU : BASE_URL + "machineManager/addMachineSku.action",//添加机型型号（SKU)
        GET_EXCAVATOR_PRODUCT_DETAIL : BASE_URL + "machineManager/getMachineDetail.do",//获取机型组详情
        PUT_UP_EXCAVATOR_PRODUCT : BASE_URL + "machineManager/putUpProduct.action",//上架机型组
        PUT_OUT_EXCAVATOR_PRODUCT : BASE_URL + "machineManager/putOutProduct.action",//下架机型组
        PUT_UP_EXCAVATOR_SKU : BASE_URL + "machineManager/putUpSku.action",//上架机型sku
        PUT_OUT_EXCAVATOR_SKU : BASE_URL + "machineManager/putOutSku.action",//下架机型sku

    }

    return PROJECT_APIS;

})();


var ExcavatorText = (function(){
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
