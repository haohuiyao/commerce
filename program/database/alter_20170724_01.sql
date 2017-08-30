/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50520
Source Host           : 127.0.0.1:3306
Source Database       : commerce

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-07-24 16:30:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mn_admin_entry`
-- ----------------------------
DROP TABLE IF EXISTS `mn_admin_entry`;
CREATE TABLE `mn_admin_entry` (
  `ENTRY_ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '后台分页ID',
  `ENTRY_PARENT_ID` int(11) NOT NULL DEFAULT '0' COMMENT '父分页ID',
  `ENTRY_TITLE` varchar(54) NOT NULL DEFAULT '' COMMENT '分页名',
  `ENTRY_ICON` varchar(54) DEFAULT '' COMMENT '页面ICON',
  `ENTRY_ROUTE` varchar(255) NOT NULL DEFAULT '' COMMENT '路由名',
  `ENTRY_URL` varchar(255) DEFAULT '' COMMENT '路由URL',
  `ENTRY_TEMPLATE_URL` varchar(255) DEFAULT '' COMMENT '浏览器页面URL',
  `ENTRY_CONTROLLER` varchar(255) DEFAULT '' COMMENT '控制器',
  `ENTRY_JS_ARR` text COMMENT '页面加载所需JS',
  `ENTRY_PARAMS` text COMMENT '页面跳转参数',
  `ENTRY_IS_NAV` tinyint(4) DEFAULT '0' COMMENT '是否为导航信息 0否 1是',
  PRIMARY KEY (`ENTRY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mn_admin_entry
-- ----------------------------
INSERT INTO `mn_admin_entry` VALUES ('1', '0', '管理员管理', 'icon', 'test', 'test', 'test', 'test', '[1,2,3]', null, '0');
INSERT INTO `mn_admin_entry` VALUES ('2', '0', '用户管理', '', '', '', '', '', null, null, '0');
INSERT INTO `mn_admin_entry` VALUES ('101', '1', '管理员列表', 'icon', 'test', '', '', '', null, null, '0');
INSERT INTO `mn_admin_entry` VALUES ('102', '1', '管理员统计', '', '', '', '', '', null, null, '0');
INSERT INTO `mn_admin_entry` VALUES ('201', '2', '用户列表', '', '', '', '', '', null, null, '0');
INSERT INTO `mn_admin_entry` VALUES ('202', '2', '用户统计', '', '', '', '', '', null, null, '0');

-- ----------------------------
-- Table structure for `mn_admin_group`
-- ----------------------------
DROP TABLE IF EXISTS `mn_admin_group`;
CREATE TABLE `mn_admin_group` (
  `GROUP_ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '分组ID',
  `GROUP_NAME` varchar(54) NOT NULL DEFAULT '' COMMENT '分组名',
  `GROUP_PRIVILEGES` varchar(255) DEFAULT '' COMMENT '分组权限',
  PRIMARY KEY (`GROUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mn_admin_group
-- ----------------------------
INSERT INTO `mn_admin_group` VALUES ('1', '超级管理员', '[{\"parent\" : \"1\", \"childs\":[\"101\",\"102\"]}, {\"parent\" : \"2\", \"childs\":[\"201\",\"202\"]}]');
INSERT INTO `mn_admin_group` VALUES ('2', '1级管理员', '[{\"parent\" : \"1\", \"childs\":[\"101\",\"102\"]}, {\"parent\" : \"2\", \"childs\":[\"201\",\"202\"]}]');


alter table mnu_base_user add column `GROUP_ID` varchar(6) DEFAULT '' COMMENT '用户分组ID' after ACCT_UUID;   
