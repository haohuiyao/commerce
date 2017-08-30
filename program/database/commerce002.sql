/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : commerce

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-07-20 16:58:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mng_shop
-- ----------------------------
DROP TABLE IF EXISTS `mng_shop`;
CREATE TABLE `mng_shop` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(256) DEFAULT NULL,
  `PROVINCE` varchar(16) DEFAULT NULL,
  `CITY` varchar(16) DEFAULT NULL,
  `DISTRICT` varchar(16) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `TRADER_ID` int(11) DEFAULT NULL,
  `STATUS` varchar(4) DEFAULT NULL,
  `IDENTIFYING` varchar(4) DEFAULT NULL,
  `PHONE` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `TRADER_ID` (`TRADER_ID`),
  CONSTRAINT `mng_shop_ibfk_1` FOREIGN KEY (`TRADER_ID`) REFERENCES `mng_trader` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mng_sku_attr_pair
-- ----------------------------
DROP TABLE IF EXISTS `mng_sku_attr_pair`;
CREATE TABLE `mng_sku_attr_pair` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SKU_ID` int(11) DEFAULT NULL,
  `SKU_KEY_ID` int(11) DEFAULT NULL,
  `SKU_VALUE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SKU_ID` (`SKU_ID`) USING BTREE,
  KEY `SKU_KEY_ID` (`SKU_KEY_ID`) USING BTREE,
  KEY `SKU_VALUE_ID` (`SKU_VALUE_ID`) USING BTREE,
  CONSTRAINT `mng_sku_attr_pair_ibfk_2` FOREIGN KEY (`SKU_KEY_ID`) REFERENCES `mng_sku_key` (`SKU_KEY_ID`),
  CONSTRAINT `mng_sku_attr_pair_ibfk_3` FOREIGN KEY (`SKU_VALUE_ID`) REFERENCES `mng_sku_value` (`SKU_VALUE_ID`),
  CONSTRAINT `mng_sku_attr_pair_ibfk_4` FOREIGN KEY (`SKU_ID`) REFERENCES `mn_sku` (`SKU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mng_sku_key
-- ----------------------------
DROP TABLE IF EXISTS `mng_sku_key`;
CREATE TABLE `mng_sku_key` (
  `SKU_KEY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `KEY_NAME` varchar(255) DEFAULT NULL,
  `GOODS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SKU_KEY_ID`),
  KEY `GOODS_ID` (`GOODS_ID`) USING BTREE,
  CONSTRAINT `mng_sku_key_ibfk_1` FOREIGN KEY (`GOODS_ID`) REFERENCES `mn_product` (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mng_sku_value
-- ----------------------------
DROP TABLE IF EXISTS `mng_sku_value`;
CREATE TABLE `mng_sku_value` (
  `SKU_VALUE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SKU_KEY_ID` int(11) DEFAULT NULL,
  `WORDS` varchar(255) DEFAULT NULL,
  `VALUE_IMG_PATH` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SKU_VALUE_ID`),
  KEY `SKU_KEY_ID` (`SKU_KEY_ID`) USING BTREE,
  CONSTRAINT `mng_sku_value_ibfk_1` FOREIGN KEY (`SKU_KEY_ID`) REFERENCES `mng_sku_key` (`SKU_KEY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mng_trader
-- ----------------------------
DROP TABLE IF EXISTS `mng_trader`;
CREATE TABLE `mng_trader` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IMG_URL` varchar(1024) DEFAULT NULL,
  `NAME` varchar(1024) DEFAULT NULL,
  `CONTACT_NAME` varchar(16) DEFAULT NULL,
  `CONTACT_PHONE` varchar(16) DEFAULT NULL,
  `CONTACT_EMAIL` varchar(256) DEFAULT NULL,
  `REGISTER_TIME` datetime DEFAULT NULL,
  `SETTLE_BY_MONTH` varchar(4) DEFAULT NULL COMMENT '是否可以月结[Y:是；N:否]',
  `STATUS` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mnp_account
-- ----------------------------
DROP TABLE IF EXISTS `mnp_account`;
CREATE TABLE `mnp_account` (
  `UUID` varchar(50) NOT NULL COMMENT 'uuid',
  `STATUS` varchar(8) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`UUID`),
  KEY `mn_acct_uuid` (`UUID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mnp_acc_normal
-- ----------------------------
DROP TABLE IF EXISTS `mnp_acc_normal`;
CREATE TABLE `mnp_acc_normal` (
  `NORMAL_PP_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `USER_NAME` varchar(120) DEFAULT NULL COMMENT '用户名',
  `PWD` varchar(100) NOT NULL COMMENT '密码',
  `ACCT_UUID` varchar(50) DEFAULT NULL COMMENT '关联的ACCOUNT的UUID',
  PRIMARY KEY (`NORMAL_PP_ID`),
  KEY `mnp_acc_normal_ibfk_1` (`ACCT_UUID`) USING BTREE,
  CONSTRAINT `mnp_acc_normal_ibfk_1` FOREIGN KEY (`ACCT_UUID`) REFERENCES `mnp_account` (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mnp_acc_phone
-- ----------------------------
DROP TABLE IF EXISTS `mnp_acc_phone`;
CREATE TABLE `mnp_acc_phone` (
  `PHONE_PP_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PHONE` varchar(16) DEFAULT NULL COMMENT '手机号',
  `ACCT_UUID` varchar(50) DEFAULT NULL COMMENT '关联的ACCOUNT的UUID',
  PRIMARY KEY (`PHONE_PP_ID`),
  KEY `phone_vcode_pp_fk_1` (`ACCT_UUID`) USING BTREE,
  CONSTRAINT `mnp_acc_phone_ibfk_1` FOREIGN KEY (`ACCT_UUID`) REFERENCES `mnp_account` (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mnp_acc_qq
-- ----------------------------
DROP TABLE IF EXISTS `mnp_acc_qq`;
CREATE TABLE `mnp_acc_qq` (
  `QQ_PP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPEN_ID` varchar(60) DEFAULT NULL,
  `ACCT_UUID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`QQ_PP_ID`),
  KEY `ACCT_UUID` (`ACCT_UUID`),
  CONSTRAINT `mnp_acc_qq_ibfk_1` FOREIGN KEY (`ACCT_UUID`) REFERENCES `mnp_account` (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mnp_acc_wx
-- ----------------------------
DROP TABLE IF EXISTS `mnp_acc_wx`;
CREATE TABLE `mnp_acc_wx` (
  `WX_PP_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `OPEN_ID` varchar(60) DEFAULT NULL COMMENT '用户微信登录唯一ID',
  `ACCT_UUID` varchar(50) DEFAULT NULL COMMENT '关联的ACCOUNT的UUID',
  PRIMARY KEY (`WX_PP_ID`),
  KEY `mnp_acc_wx_ibfk_1` (`ACCT_UUID`) USING BTREE,
  CONSTRAINT `mnp_acc_wx_ibfk_1` FOREIGN KEY (`ACCT_UUID`) REFERENCES `mnp_account` (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mnp_login_record
-- ----------------------------
DROP TABLE IF EXISTS `mnp_login_record`;
CREATE TABLE `mnp_login_record` (
  `UUID` varchar(50) NOT NULL,
  `ENTRY_TYPE` varchar(8) NOT NULL,
  `TOKEN` varchar(255) DEFAULT NULL,
  `LAST_LOGIN_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`UUID`,`ENTRY_TYPE`),
  CONSTRAINT `mnp_login_record_ibfk_1` FOREIGN KEY (`UUID`) REFERENCES `mnp_account` (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mnu_base_user
-- ----------------------------
DROP TABLE IF EXISTS `mnu_base_user`;
CREATE TABLE `mnu_base_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `NAME` varchar(32) DEFAULT NULL COMMENT '姓名',
  `GENDER` varchar(8) DEFAULT NULL COMMENT '性别',
  `CREATE_TIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `USER_TYPE` varchar(6) DEFAULT NULL COMMENT '用户类型：1普通用户 2管理员 3经销商',
  `ACCT_UUID` varchar(50) DEFAULT NULL COMMENT '关联的ACCOUNT_UUID',
  PRIMARY KEY (`USER_ID`),
  KEY `ACCT_UUID` (`ACCT_UUID`) USING BTREE,
  CONSTRAINT `mnu_base_user_ibfk_1` FOREIGN KEY (`ACCT_UUID`) REFERENCES `mnp_account` (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=2272 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mn_area
-- ----------------------------
DROP TABLE IF EXISTS `mn_area`;
CREATE TABLE `mn_area` (
  `ID` int(10) NOT NULL,
  `NAME` varchar(64) NOT NULL,
  `PARENT_ID` int(10) DEFAULT '0',
  `LEVEL` tinyint(4) DEFAULT '0' COMMENT '区域等级',
  `INITIALS` varchar(16) DEFAULT 'A' COMMENT '首字母',
  PRIMARY KEY (`ID`),
  KEY `parent_id` (`PARENT_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mn_category
-- ----------------------------
DROP TABLE IF EXISTS `mn_category`;
CREATE TABLE `mn_category` (
  `CATEGORY_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `CATEGORY_NAME` varchar(50) DEFAULT NULL COMMENT '分类名',
  `LEVEL` int(11) DEFAULT NULL COMMENT '菜单级别',
  `STYLE` varchar(40) DEFAULT NULL COMMENT '样式',
  `ABBREVIATION` varchar(20) DEFAULT NULL COMMENT '首字母缩写',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '上级菜单ID',
  `TEMPLATE_ID` int(11) DEFAULT NULL COMMENT '分类参数模板ID',
  `IMG_URL` varchar(255) DEFAULT NULL COMMENT '分类图片地址',
  `STATUS` varchar(6) DEFAULT NULL COMMENT '状态(A:可用 D:禁用)',
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_ID`),
  KEY `TEMPLATE_ID` (`TEMPLATE_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mn_config
-- ----------------------------
DROP TABLE IF EXISTS `mn_config`;
CREATE TABLE `mn_config` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(32) DEFAULT NULL COMMENT '参数名',
  `VALUE` varchar(255) DEFAULT NULL COMMENT '参数值',
  `MODIFY_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mn_franking_templet
-- ----------------------------
DROP TABLE IF EXISTS `mn_franking_templet`;
CREATE TABLE `mn_franking_templet` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FARENAME` varchar(20) DEFAULT NULL COMMENT '模板名称',
  `POSTAGE` varchar(4) DEFAULT NULL COMMENT '是否包邮',
  `PATTERN` int(11) DEFAULT NULL COMMENT '计费方式',
  `DISPATCHING` varchar(11) DEFAULT NULL COMMENT '配送方式',
  `BASEWEIGHT` decimal(11,2) DEFAULT NULL COMMENT '基本重量',
  `BASEPRICE` decimal(11,2) DEFAULT NULL COMMENT '基本价钱',
  `ADDWEIGHT` decimal(11,2) DEFAULT NULL COMMENT '新增重量',
  `ADDPRICE` decimal(11,2) DEFAULT NULL COMMENT '价钱',
  `SCOPE` varchar(10) DEFAULT NULL COMMENT '配送范围 0：全国',
  `UPDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mn_param_template
-- ----------------------------
DROP TABLE IF EXISTS `mn_param_template`;
CREATE TABLE `mn_param_template` (
  `TEMPLATE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` text,
  `CREATE_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(6) NOT NULL COMMENT '状态(A:可用 D:禁用)',
  `NAME` varchar(64) DEFAULT NULL COMMENT '模板名',
  PRIMARY KEY (`TEMPLATE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mn_product
-- ----------------------------
DROP TABLE IF EXISTS `mn_product`;
CREATE TABLE `mn_product` (
  `PRODUCT_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '具体的商品',
  `NAME` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `SUMMARY` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品简介',
  `DETAIL` text CHARACTER SET utf8 COMMENT '商品详情',
  `CATEGORY_ID` int(11) DEFAULT NULL COMMENT '商品所属的分类ID',
  `CREATE_TIME` datetime DEFAULT NULL,
  `TEMPLATE_ID` int(11) DEFAULT NULL COMMENT '参数模板',
  `PARAMS_VALUE` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '模板参数值',
  `STATUS` varchar(6) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态(A:可用 D:禁用)',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改的时间',
  `PRODUCT_BRAND_ID` int(11) DEFAULT NULL COMMENT '商品品牌',
  `SOURCE_ID` int(11) DEFAULT NULL COMMENT '商品货源',
  `PLACE` varchar(40) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品产地',
  `CODE` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `COST` decimal(10,2) DEFAULT NULL COMMENT '商品成本价',
  `PICTURE` text CHARACTER SET utf8 COMMENT '商品图片',
  `COMMON` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品通用名',
  `CURRENCY` varchar(5) CHARACTER SET utf8 DEFAULT NULL COMMENT '标识是否为全国统一价',
  `WEIGHT` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `VOLUME` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `CARRIAGEID` int(11) DEFAULT NULL COMMENT '运费模板Id',
  `DISPLAY_IMG` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '首图',
  PRIMARY KEY (`PRODUCT_ID`),
  KEY `CATEGORY_ID` (`CATEGORY_ID`),
  KEY `PRODUCT_BRAND_ID` (`PRODUCT_BRAND_ID`),
  KEY `TEMPLATE_ID` (`TEMPLATE_ID`),
  KEY `SOURCE_ID` (`SOURCE_ID`),
  CONSTRAINT `mn_product_ibfk_1` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `mn_category` (`CATEGORY_ID`),
  CONSTRAINT `mn_product_ibfk_2` FOREIGN KEY (`PRODUCT_BRAND_ID`) REFERENCES `mn_product_brand` (`ID`),
  CONSTRAINT `mn_product_ibfk_3` FOREIGN KEY (`TEMPLATE_ID`) REFERENCES `mn_param_template` (`TEMPLATE_ID`),
  CONSTRAINT `mn_product_ibfk_4` FOREIGN KEY (`SOURCE_ID`) REFERENCES `mn_source` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mn_product_brand
-- ----------------------------
DROP TABLE IF EXISTS `mn_product_brand`;
CREATE TABLE `mn_product_brand` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BRAND_NAME` varchar(20) DEFAULT NULL,
  `IMG_URL` varchar(1024) DEFAULT NULL,
  `STATUS` varchar(4) DEFAULT NULL,
  `ABBREVIATION` varchar(20) DEFAULT NULL COMMENT '品牌缩写',
  `GROOM` varchar(4) DEFAULT NULL COMMENT '是否推荐 0:否 1:是',
  `GROOM_TIME` datetime DEFAULT NULL COMMENT '设置时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mn_product_model
-- ----------------------------
DROP TABLE IF EXISTS `mn_product_model`;
CREATE TABLE `mn_product_model` (
  `ID` int(11) NOT NULL,
  `BRAND_ID` int(11) DEFAULT NULL COMMENT '品牌ID',
  `MODEL_NAME` varchar(256) DEFAULT NULL COMMENT '机型名称',
  `PARENT` int(11) DEFAULT NULL COMMENT '父级ID',
  PRIMARY KEY (`ID`),
  KEY `BRAND_ID` (`BRAND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mn_product_rel
-- ----------------------------
DROP TABLE IF EXISTS `mn_product_rel`;
CREATE TABLE `mn_product_rel` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `rel_type` int(6) DEFAULT NULL COMMENT ' 用来表示obj1与obj2的关系',
  `obj_type1` int(6) DEFAULT NULL,
  `obj_id1` int(11) DEFAULT NULL,
  `obj_type2` int(6) DEFAULT NULL,
  `obj_id2` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mn_recommend_genre
-- ----------------------------
DROP TABLE IF EXISTS `mn_recommend_genre`;
CREATE TABLE `mn_recommend_genre` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `POSITION` int(5) DEFAULT NULL,
  `IMGURL` varchar(255) DEFAULT NULL,
  `CATEGORY_ID` int(11) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `TYPE` int(5) DEFAULT NULL COMMENT '1：推荐类别  2：类别展示',
  `SIGN` varchar(5) NOT NULL COMMENT '标记添加的是大类还是细类',
  PRIMARY KEY (`ID`),
  KEY `CATEGORY_ID` (`CATEGORY_ID`),
  CONSTRAINT `mn_recommend_genre_ibfk_1` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `mn_category` (`CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mn_shelves
-- ----------------------------
DROP TABLE IF EXISTS `mn_shelves`;
CREATE TABLE `mn_shelves` (
  `SHELVES_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(32) DEFAULT NULL,
  `SHOP_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SHELVES_ID`),
  KEY `SHOP_ID` (`SHOP_ID`),
  CONSTRAINT `mn_shelves_ibfk_1` FOREIGN KEY (`SHOP_ID`) REFERENCES `mng_shop` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mn_shelves_rel_sku
-- ----------------------------
DROP TABLE IF EXISTS `mn_shelves_rel_sku`;
CREATE TABLE `mn_shelves_rel_sku` (
  `ID` int(11) NOT NULL,
  `SKU_ID` int(11) DEFAULT NULL,
  `SHELVES_ID` int(11) DEFAULT NULL,
  `PRICE` decimal(10,2) DEFAULT NULL,
  `STATUS` varchar(6) DEFAULT NULL,
  `STOCK` int(10) DEFAULT NULL COMMENT '库存',
  `SALES` int(11) DEFAULT '0' COMMENT '销量',
  `CREATE_TIME` datetime DEFAULT NULL,
  `GROOM_TIME` datetime DEFAULT NULL COMMENT '设置时间',
  PRIMARY KEY (`ID`),
  KEY `SKU_ID` (`SKU_ID`),
  KEY `SHELVES_ID` (`SHELVES_ID`),
  CONSTRAINT `mn_shelves_rel_sku_ibfk_1` FOREIGN KEY (`SKU_ID`) REFERENCES `mn_sku` (`SKU_ID`),
  CONSTRAINT `mn_shelves_rel_sku_ibfk_2` FOREIGN KEY (`SHELVES_ID`) REFERENCES `mn_shelves` (`SHELVES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mn_sku
-- ----------------------------
DROP TABLE IF EXISTS `mn_sku`;
CREATE TABLE `mn_sku` (
  `SKU_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` int(11) DEFAULT NULL COMMENT '关联商品ID',
  `CREATE_TIME` datetime DEFAULT NULL,
  `TEMPLATE_ID` int(11) DEFAULT NULL,
  `PARAMS_VALUE` varchar(1024) DEFAULT NULL,
  `ADAPTATION` varchar(200) DEFAULT NULL COMMENT '生成的产品标题',
  `PICTURE` varchar(1024) DEFAULT NULL,
  `CODE` varchar(40) DEFAULT NULL,
  `STATUS` varchar(4) DEFAULT NULL,
  `DETAIL` text,
  `BRAND_ID` int(11) DEFAULT NULL COMMENT '品牌ID',
  `SKU_NAME` varchar(256) DEFAULT NULL COMMENT '型号',
  `DISPLAY_IMG` varchar(1024) DEFAULT NULL,
  `SUMMARY` varchar(255) DEFAULT NULL COMMENT '商品简介',
  `WEIGHT` varchar(11) DEFAULT NULL,
  `VOLUME` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`SKU_ID`),
  KEY `GOODS_ID` (`PRODUCT_ID`) USING BTREE,
  KEY `TEMPLATE_ID` (`TEMPLATE_ID`),
  KEY `BRAND_ID` (`BRAND_ID`),
  KEY `MODEL_ID` (`SKU_NAME`(255)),
  CONSTRAINT `mn_sku_ibfk_1` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `mn_product` (`PRODUCT_ID`),
  CONSTRAINT `mn_sku_ibfk_2` FOREIGN KEY (`BRAND_ID`) REFERENCES `mn_product_brand` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mn_source
-- ----------------------------
DROP TABLE IF EXISTS `mn_source`;
CREATE TABLE `mn_source` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SOURCE_NAME` varchar(100) DEFAULT NULL,
  `STATUS` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for phone_vcode
-- ----------------------------
DROP TABLE IF EXISTS `phone_vcode`;
CREATE TABLE `phone_vcode` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PHONE` varchar(16) DEFAULT NULL COMMENT '手机号',
  `VERIFY_CODE` varchar(10) DEFAULT NULL COMMENT '验证码',
  `V_TYPE` varchar(2) DEFAULT NULL COMMENT '验证码类型： 1.登录验证 2.微信绑定手机号验证',
  `V_CODE_CREATE_TIME` datetime DEFAULT NULL COMMENT '当前验证码创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
