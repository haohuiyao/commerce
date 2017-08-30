//API基地址
//const BASE_URL = "http://192.168.0.189:8083/commerce/";
const BASE_URL = "http://140.206.112.170:8083/commerce/";

const BASE_URL_TEST = "http://140.206.112.170:9201/sosoapi-web/pass/mock/3/";


//商品相关
const API_GET_GOODS_LIST = BASE_URL + "product/frontFindSkuList.do"; //获取商品列表
const API_GET_GOODS_DETAIL = BASE_URL + "product/frontFindSkuDetail.do";//获取商品详情
const API_GET_MACHINEBRANDLIST = BASE_URL + "product/frontGetMachineBrand.do";//获取品牌列表
const API_GET_MACHINE_BRAN_BYID_NOCURRENCY = BASE_URL + "machineManager/findProductByBrand.do";//查找相关类别的机型组(不取出通用)
const API_GET_ONE_CATEGORIES = BASE_URL + "product/frontGetPartsCategory.do";//获取一级商品分类
const API_GET_TWO_CATEGORIES = BASE_URL + "category/getAllCategories.do";//获取二级商品分类


const API_GET_MACHINE_GROUP = BASE_URL + "machineModel/findMachineModelList.do";
const API_GET_ORDER_DETAILS_LIST = BASE_URL + "buyerOrder/getOrderDetailListOfComment.action";
const API_SAVE_COMMENTS = BASE_URL + "goodsFront/saveOrderDetailComment.action";
const API_GET_COMMENTS_LABEL = BASE_URL + "goodsFront/getCommentLabel.do";
const API_GET_SOURCE_LIST = BASE_URL + "source/frontFindSourceList.do";//获取货源列表

const API_GET_MACHINE_BRAN_BYID = BASE_URL + "machineBrand/frontFindMachineBrandById.do";//查找相关类别的机型组



const API_GET_SHELVESID_BY_SKUID = BASE_URL +"goodsFront/getShelvesSkuIdBySkuId.do";//根据skuid获取货架skuid
const API_GET_MACHINE_BRAND_LIST = BASE_URL + "machineBrand/frontGroomFindMachineBrandList.do";//推荐产品机型

////搜索历史
//const API_GET_HOTSEARCH = BASE_URL + "searchFront/findMNHotsearch.do";//获取热门搜索
//const API_GET_HISTORYSEARCH = BASE_URL + "searchFront/findMNHistory.action";//获取热门搜索
//const API_DEL_HISTORYSEARCH = BASE_URL + "searchFront/deleteMNHistory.action";//删除搜索历史

//评论相关
//const API_GET_GOODS_COMMENT = BASE_URL + "goodsFront/getGoodComment.do";//获取商品评论
//


//const API_GUESS_GOODS = BASE_URL + 'goodsFront/matchingGoods.do';//商品猜你喜欢
//const API_GUESS_ORDER = BASE_URL + "buyerOrder/matchingGoods.action";//根据订单猜你喜欢
//const API_GUESS_CART = BASE_URL + 'buyerGoods/matchingGoods.action';//购物车猜你喜欢
