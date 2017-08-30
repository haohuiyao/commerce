/* config
/* ==========================================================================
 * 配置项
 * make by daipengpeng
 * ============================================================================
 */

//默认图片
const DEFAULT_HEADICON = "assets/images/public/default_head.png";

//颜色值
const color_head_bg = 'f8f8f8';

const LINK_URL = 'http://120.27.216.38:8080/ebusiness/linkPage/';
//const LINK_URL = 'http://192.168.0.114:8020/linkPage/'

const SHARE_URL = 'http://120.27.216.38:8080/ebusiness/share/';
//const SHARE_URL = 'http://192.168.0.144:8021/share/';


var userAgreement = LINK_URL + 'userAgreement.html';

var shareParams = {
	
	shareAPP : {
		shareUrl : SHARE_URL+"register.html",
		shareTitle : "我的专属链接",
		shareContent : "快来下载APP吧",
		shareImg : "http://andycfm.oss-cn-shanghai.aliyuncs.com/2-3.jpg",
	},
	
	shareGoods : {
		shareUrl : SHARE_URL+"goodsDetail.html",
		shareTitle : "分享APP",
		shareContent : "分享的内容",
		shareImg : "http://andycfm.oss-cn-shanghai.aliyuncs.com/2-3.jpg",
	},
	
	shareMineLink : {
		shareUrl : SHARE_URL+"register.html",
		shareTitle : "我的专属链接",
		shareContent : "快来下载APP吧",
		shareImg : "http://andycfm.oss-cn-shanghai.aliyuncs.com/2-3.jpg",
	},
	
}

var helpCenter = {
	'url' : 'https://www.baidu.com/',
	'title' : '帮助中心'
}

var defaultObj = {
	de_goods_icon 		: '../../../assets/images/default/default-goods-pic.png',//商品默认展示图
	de_goods_banner 	: '../../../assets/images/default/default-goods-banner.png',//商品详情banner图
	de_classify_icon 	: '../../../assets/images/default/default-classify-icon.png',//产品分类图
	de_head_icon 		: '../../../assets/images/default/default-user-icon.png',//用户默认头像
	de_index_recommend 	: '../../../assets/images/default/default-index-recommend.jpg',//首页推荐好友默认图
	de_good_lf 			: '../../../assets/images/default/default-good-lf.png',//挖配优选左侧
	de_good_rg 			: '../../../assets/images/default/default-good-rg.png',//挖配优选右侧
	de_index_zt			: '../../../assets/images/default/default-index-zhuanti.png',//首页专题
}
