/**
 * Created by Administrator on 2017/7/12.
 * create by daipengpeng
 * 基础配置信息
 */

//const BASE_URL = "http://120.27.216.38:8080/ebusiness/";
//const BASE_URL = "http://192.168.0.52:9002/ebusiness/";
//const BASE_URL = "http://192.168.0.189:8083/commerce/";
const BASE_URL = "http://140.206.112.170:8083/commerce/"

/**
 * 管理员类型
 **/
var adminTypeArr = [
    {
        id		:	2,
        name	:	'管理员'
    }
];


var ProjectKey = (function(){

    /**
     * 阿里云参数
     * */
    var OSSKEY =
    {
        REGION : "oss-cn-shanghai",
        ACCESS_KEY_ID : "LTAI3aRi4Ql3PzNb",
        ACCESS_KEY_SECRET :"CU3IEkkwAT9BimjjFSHbtfEGpclZoX",
        //BUCKET : "wapei",
        PUBLIC_BUCKET : "wapei",
    };

    return {
        OSSKEY : OSSKEY
    }

})();