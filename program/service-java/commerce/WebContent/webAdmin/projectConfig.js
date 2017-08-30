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


var route = {
    "code": 0,
    "data": {
        "list": [
            {
                "childs": [
                    {
                        "params": {},
                        "isNav": 1,
                        "state": "content",
                        "icon": "",
                        "id": 8,
                        "parentId": 2,
                        "status": "",
                        "title": "首页",
                        "url": "/content",
                        "templateUrl": "app/modules/content/content.html",
                        "controller": "ContentCtrl",
                        "controllerUrl": [
                            "app/modules/content/Content.Controller.js"
                        ]
                    }
                ],
                "state": "content",
                "icon": "ti-home",
                "id": 2,
                "title": "首页"
            },
            {
                "childs": [
                    {
                        "params": {},
                        "isNav": 1,
                        "state": "adminList",
                        "icon": "",
                        "id": 8,
                        "parentId": 2,
                        "status": "",
                        "title": "管理员列表",
                        "url": "/adminList",
                        "templateUrl": "app/modules/admin/admin.list.html",
                        "controller": "AdminListCtrl",
                        "controllerUrl": [
                            "app/modules/admin/Admin.List.Controller.js"
                        ]
                    }
                ],
                "state": "adminList",
                "icon": "ti-user",
                "id": 2,
                "title": "管理员管理"
            },
            {
                "childs": [
                    {
                        "params": {},
                        "isNav": 1,
                        "state": "productClassify",
                        "icon": "",
                        "id": 15,
                        "parentId": 3,
                        "status": "",
                        "title": "产品分类",
                        "url": "/productClassify",
                        "templateUrl": "modules/product/view/product.classify.html",
                        "controller": "ProductClassifyCtrl",
                        "controllerUrl": [
                            "modules/product/productConfig.js",
                            "modules/product/view/Product.Classify.Controller.js"
                        ]
                    },
                    {
                        "params": {},
                        "isNav": 1,
                        "state": "productBrand",
                        "icon": "",
                        "id": 15,
                        "parentId": 3,
                        "status": "",
                        "title": "产品品牌",
                        "url": "/productBrand",
                        "templateUrl": "modules/product/view/product.brand.html",
                        "controller": "ProductBrandCtrl",
                        "controllerUrl": [
                            "modules/product/productConfig.js",
                            "modules/product/view/Product.Brand.Controller.js"
                        ]
                    },
                    {
                        "params": {},
                        "isNav": 1,
                        "state": "productParamTemplate",
                        "icon": "",
                        "id": 15,
                        "parentId": 3,
                        "status": "",
                        "title": "产品参数模板",
                        "url": "/productParamTemplate",
                        "templateUrl": "modules/product/view/product.paramTemplate.html",
                        "controller": "ProductParamTemplateCtrl",
                        "controllerUrl": [
                            "modules/product/productConfig.js",
                            "modules/product/view/Product.ParamTemplate.Controller.js"
                        ]
                    },
                    {
                        "params": {
                            "id": null
                        },
                        "isNav": 0,
                        "state": "productParamTemplateDetail",
                        "icon": "",
                        "id": 15,
                        "parentId": 3,
                        "status": "",
                        "title": "参数模板详情",
                        "url": "/productParamTemplateDetail",
                        "templateUrl": "modules/product/view/product.paramTemplateDetail.html",
                        "controller": "ProductParamTemplateDetailCtrl",
                        "controllerUrl": [
                            "modules/product/productConfig.js",
                            "modules/product/view/Product.ParamTemplateDetail.Controller.js"
                        ]
                    },
                    {
                        "params": {
                            "id": null
                        },
                        "isNav": 1,
                        "state": "productFreightTemplate",
                        "icon": "",
                        "id": 15,
                        "parentId": 3,
                        "status": "",
                        "title": "产品运费模板",
                        "url": "/productFreightTemplate",
                        "templateUrl": "modules/product/view/product.freightTemplate.html",
                        "controller": "freightTemplateCtrl",
                        "controllerUrl": [
                            "modules/product/productConfig.js",
                            "modules/product/view/Product.FreightTemplate.Controller.js"
                        ]
                    }
                ],
                "state": "",
                "icon": "ti-package",
                "id": 3,
                "title": "商品管理"
            },
            {
                "childs": [
                    {
                        "params": {},
                        "isNav": 1,
                        "state": "excavatorProductList",
                        "icon": "",
                        "id": 8,
                        "parentId": 2,
                        "status": "",
                        "title": "机型组管理",
                        "url": "/excavatorProductList",
                        "templateUrl": "modules/excavator/view/excavator.product.list.html",
                        "controller": "ExcavatorProductListCtrl",
                        "controllerUrl": [
                            "modules/excavator/excavatorConfig.js",
                            "modules/product/productConfig.js",
                            "modules/excavator/view/excavator.product.list.Controller.js"
                        ]
                    },
                    {
                        "params": {},
                        "isNav": 0,
                        "state": "excavatorAddProduct",
                        "icon": "",
                        "id": 8,
                        "parentId": 2,
                        "status": "",
                        "title": "机型组添加",
                        "url": "/excavatorAddProduct",
                        "templateUrl": "modules/excavator/view/excavator.add.product.html",
                        "controller": "ExcavatorAddProductCtrl",
                        "controllerUrl": [
                            "modules/excavator/excavatorConfig.js",
                            "modules/product/productConfig.js",
                            "modules/excavator/view/excavator.add.product.Controller.js"
                        ]
                    },
                    {
                        "params": {
                            "id": null
                        },
                        "isNav": 0,
                        "state": "excavatorProductDetail",
                        "icon": "",
                        "id": 8,
                        "parentId": 2,
                        "status": "",
                        "title": "机型组详情",
                        "url": "/excavatorProductDetail",
                        "templateUrl": "modules/excavator/view/excavator.product.detail.html",
                        "controller": "ExcavatorProductDetailCtrl",
                        "controllerUrl": [
                            "modules/excavator/excavatorConfig.js",
                            "modules/product/productConfig.js",
                            "modules/excavator/view/excavator.product.detail.Controller.js"
                        ]
                    },
                    {
                        "params": {
                            "id": null
                        },
                        "isNav": 0,
                        "state": "excavatorModProduct",
                        "icon": "",
                        "id": 8,
                        "parentId": 2,
                        "status": "",
                        "title": "机型组修改",
                        "url": "/excavatorModProduct",
                        "templateUrl": "modules/excavator/view/excavator.mod.product.html",
                        "controller": "ExcavatorModProductCtrl",
                        "controllerUrl": [
                            "modules/excavator/excavatorConfig.js",
                            "modules/product/productConfig.js",
                            "modules/excavator/view/excavator.mod.product.Controller.js"
                        ]
                    }
                ],
                "state": "excavatorProductList",
                "icon": "ti-user",
                "id": 2,
                "title": "挖掘机管理"
            },
            {
                "childs": [
                    {
                        "params": {},
                        "isNav": 1,
                        "state": "partsProductList",
                        "icon": "",
                        "id": 8,
                        "parentId": 2,
                        "status": "",
                        "title": "产品组管理",
                        "url": "/partsProductList",
                        "templateUrl": "modules/parts/view/parts.product.list.html",
                        "controller": "PartsProductListCtrl",
                        "controllerUrl": [
                            "modules/parts/partsConfig.js",
                            "modules/product/productConfig.js",
                            "modules/parts/view/parts.product.list.Controller.js"
                        ]
                    },
                    {
                        "params": {},
                        "isNav": 1,
                        "state": "partsSkuList",
                        "icon": "",
                        "id": 8,
                        "parentId": 2,
                        "status": "",
                        "title": "产品管理",
                        "url": "/partsSkuList",
                        "templateUrl": "modules/parts/view/parts.sku.list.html",
                        "controller": "PartsSkuListCtrl",
                        "controllerUrl": [
                            "modules/parts/partsConfig.js",
                            "modules/product/productConfig.js",
                            "modules/parts/view/parts.sku.list.Controller.js"
                        ]
                    },
                    {
                        "params": {},
                        "isNav": 0,
                        "state": "partsProductAdd",
                        "icon": "",
                        "id": 8,
                        "parentId": 2,
                        "status": "",
                        "title": "添加产品组",
                        "url": "/partsProductAdd",
                        "templateUrl": "modules/parts/view/parts.product.add.html",
                        "controller": "PartsProductAddCtrl",
                        "controllerUrl": [
                            "modules/parts/partsConfig.js",
                            "modules/product/productConfig.js",
                            "modules/parts/view/parts.product.add.Controller.js"
                        ]
                    },
                    {
                        "params": {
                            "id": null
                        },
                        "isNav": 0,
                        "state": "partsProductMod",
                        "icon": "",
                        "id": 8,
                        "parentId": 2,
                        "status": "",
                        "title": "修改产品组",
                        "url": "/partsProductMod",
                        "templateUrl": "modules/parts/view/parts.product.mod.html",
                        "controller": "PartsProductModCtrl",
                        "controllerUrl": [
                            "modules/parts/partsConfig.js",
                            "modules/product/productConfig.js",
                            "modules/parts/view/parts.product.mod.Controller.js"
                        ]
                    },
                    {
                        "params": {
                            "id": null
                        },
                        "isNav": 0,
                        "state": "partsProductDetail",
                        "icon": "",
                        "id": 8,
                        "parentId": 2,
                        "status": "",
                        "title": "产品组详情",
                        "url": "/partsProductDetail",
                        "templateUrl": "modules/parts/view/parts.product.detail.html",
                        "controller": "PartsProductDetailCtrl",
                        "controllerUrl": [
                            "modules/parts/partsConfig.js",
                            "modules/product/productConfig.js",
                            "modules/parts/view/parts.product.detail.Controller.js"
                        ]
                    },
                    {
                        "params": {
                            "id": null
                        },
                        "isNav": 0,
                        "state": "partsSkuDetail",
                        "icon": "",
                        "id": 8,
                        "parentId": 2,
                        "status": "",
                        "title": "产品sku详情",
                        "url": "/partsSkuDetail",
                        "templateUrl": "modules/parts/view/parts.sku.detail.html",
                        "controller": "PartsSkuDetailCtrl",
                        "controllerUrl": [
                            "modules/parts/partsConfig.js",
                            "modules/product/productConfig.js",
                            "modules/parts/view/parts.sku.detail.Controller.js"
                        ]
                    },
                    {
                        "params": {
                            "id": null
                        },
                        "isNav": 0,
                        "state": "partsSkuMod",
                        "icon": "",
                        "id": 8,
                        "parentId": 2,
                        "status": "",
                        "title": "产品sku详情",
                        "url": "/partsSkuMod",
                        "templateUrl": "modules/parts/view/parts.sku.mod.html",
                        "controller": "PartsSkuModCtrl",
                        "controllerUrl": [
                            "modules/parts/partsConfig.js",
                            "modules/product/productConfig.js",
                            "modules/parts/view/parts.sku.mod.Controller.js"
                        ]
                    }
                ],
                "state": "partsProductList",
                "icon": "ti-user",
                "id": 2,
                "title": "配件管理"
            }
        ]
    },
    "message": "ok"
}