/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : iblog

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-03-28 22:18:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ib_album
-- ----------------------------
DROP TABLE IF EXISTS `ib_album`;
CREATE TABLE `ib_album` (
  `id` int(11) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `is_view` int(11) DEFAULT NULL,
  `is_show` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_album
-- ----------------------------

-- ----------------------------
-- Table structure for ib_article
-- ----------------------------
DROP TABLE IF EXISTS `ib_article`;
CREATE TABLE `ib_article` (
  `id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `auth` varchar(255) DEFAULT NULL COMMENT '作者',
  `type` int(11) DEFAULT NULL COMMENT '1-文字 2-图文 3-多图文 4-链接 5-视频 6-音频',
  `content` text COMMENT '内容',
  `file_url` varchar(255) DEFAULT NULL COMMENT '文件地址',
  `tags` varchar(255) DEFAULT NULL COMMENT '标签',
  `gid` int(11) DEFAULT NULL COMMENT '栏目ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `read_count` int(255) DEFAULT NULL COMMENT '阅读数量',
  `reply_count` int(11) DEFAULT NULL COMMENT '回复数量',
  `is_view` int(255) DEFAULT NULL COMMENT '是否允许查看',
  `is_reply` int(11) DEFAULT NULL COMMENT '是否允许评论',
  `draft_id` int(11) DEFAULT NULL COMMENT '草稿ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_article
-- ----------------------------

-- ----------------------------
-- Table structure for ib_article_group
-- ----------------------------
DROP TABLE IF EXISTS `ib_article_group`;
CREATE TABLE `ib_article_group` (
  `id` int(11) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_article_group
-- ----------------------------

-- ----------------------------
-- Table structure for ib_article_reply
-- ----------------------------
DROP TABLE IF EXISTS `ib_article_reply`;
CREATE TABLE `ib_article_reply` (
  `id` int(11) NOT NULL,
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `aid` int(11) DEFAULT NULL COMMENT '文章ID',
  `content` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_article_reply
-- ----------------------------

-- ----------------------------
-- Table structure for ib_auth
-- ----------------------------
DROP TABLE IF EXISTS `ib_auth`;
CREATE TABLE `ib_auth` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '权限名字',
  `decs` varchar(255) DEFAULT NULL COMMENT '说明',
  `status` int(11) DEFAULT NULL COMMENT '状态 0-禁用 1-启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_auth
-- ----------------------------

-- ----------------------------
-- Table structure for ib_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `ib_dictionary`;
CREATE TABLE `ib_dictionary` (
  `id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '0-父类菜单',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `describe` varchar(255) DEFAULT NULL COMMENT '说明',
  `is_show` int(11) DEFAULT NULL COMMENT '是否显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for ib_draft
-- ----------------------------
DROP TABLE IF EXISTS `ib_draft`;
CREATE TABLE `ib_draft` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_draft
-- ----------------------------

-- ----------------------------
-- Table structure for ib_menu
-- ----------------------------
DROP TABLE IF EXISTS `ib_menu`;
CREATE TABLE `ib_menu` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `href` varchar(255) DEFAULT NULL COMMENT '链接',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类ID',
  `style` varchar(255) DEFAULT NULL COMMENT '样式',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标链接',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `type` int(11) DEFAULT NULL COMMENT '菜单类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_menu
-- ----------------------------

-- ----------------------------
-- Table structure for ib_msg
-- ----------------------------
DROP TABLE IF EXISTS `ib_msg`;
CREATE TABLE `ib_msg` (
  `id` int(11) NOT NULL,
  `in_uid` int(11) DEFAULT NULL COMMENT '发送人',
  `send_uid` int(11) DEFAULT NULL COMMENT '接收者',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `pub_time` timestamp NULL DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_msg
-- ----------------------------

-- ----------------------------
-- Table structure for ib_photo
-- ----------------------------
DROP TABLE IF EXISTS `ib_photo`;
CREATE TABLE `ib_photo` (
  `id` int(11) NOT NULL,
  `aid` int(11) DEFAULT NULL COMMENT '相册ID',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `hits` int(11) DEFAULT NULL COMMENT '点击次数',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片链接',
  `img_desc` varchar(255) DEFAULT NULL COMMENT '图片描述',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_photo
-- ----------------------------

-- ----------------------------
-- Table structure for ib_photo_reply
-- ----------------------------
DROP TABLE IF EXISTS `ib_photo_reply`;
CREATE TABLE `ib_photo_reply` (
  `id` int(11) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_photo_reply
-- ----------------------------

-- ----------------------------
-- Table structure for ib_role
-- ----------------------------
DROP TABLE IF EXISTS `ib_role`;
CREATE TABLE `ib_role` (
  `id` int(11) NOT NULL,
  `name` varchar(10) DEFAULT NULL COMMENT '角色名',
  `decs` varchar(50) DEFAULT NULL COMMENT '描述',
  `order` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NULL DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '状态 1-启用 0-停用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_role
-- ----------------------------

-- ----------------------------
-- Table structure for ib_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `ib_role_auth`;
CREATE TABLE `ib_role_auth` (
  `id` int(11) NOT NULL,
  `rid` int(11) NOT NULL COMMENT 'role id',
  `aid` int(11) NOT NULL COMMENT 'auth id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_role_auth
-- ----------------------------

-- ----------------------------
-- Table structure for ib_statistics
-- ----------------------------
DROP TABLE IF EXISTS `ib_statistics`;
CREATE TABLE `ib_statistics` (
  `id` int(11) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  `today_access` int(11) DEFAULT NULL COMMENT '当前',
  `sun_access` int(11) DEFAULT NULL COMMENT '今日',
  `integral` int(11) DEFAULT NULL COMMENT '总量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for ib_user
-- ----------------------------
DROP TABLE IF EXISTS `ib_user`;
CREATE TABLE `ib_user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` varchar(13) DEFAULT NULL,
  `role` int(1) DEFAULT NULL COMMENT '角色',
  `is_valid` int(1) DEFAULT NULL COMMENT '是否有效',
  `is_activation` int(1) DEFAULT NULL COMMENT '是否激活',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_user
-- ----------------------------

-- ----------------------------
-- Table structure for ib_user_info
-- ----------------------------
DROP TABLE IF EXISTS `ib_user_info`;
CREATE TABLE `ib_user_info` (
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `sex` int(1) DEFAULT NULL COMMENT ' 0-女 1-男',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `realname` varchar(50) DEFAULT NULL COMMENT '真是姓名',
  `birthday` timestamp NULL DEFAULT NULL COMMENT '生日',
  `constellation` varchar(10) DEFAULT NULL COMMENT '星座',
  `zodiac` int(10) DEFAULT NULL COMMENT '生肖',
  `nation` varchar(50) DEFAULT NULL COMMENT '民族',
  `addr` varchar(50) DEFAULT NULL COMMENT '详细住址',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `province` varchar(50) DEFAULT NULL COMMENT '县',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `county` varchar(50) DEFAULT NULL COMMENT '县',
  `live_country` varchar(50) DEFAULT NULL COMMENT '居住-国',
  `live_province` varchar(50) DEFAULT NULL COMMENT '居住-省',
  `live_city` varchar(50) DEFAULT NULL COMMENT '居住-市',
  `live_county` varchar(50) DEFAULT NULL COMMENT '居住-县',
  `introduction` varchar(255) DEFAULT NULL COMMENT '简介',
  `describe` varchar(255) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_user_info
-- ----------------------------

-- ----------------------------
-- Table structure for ib_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ib_user_role`;
CREATE TABLE `ib_user_role` (
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL COMMENT 'user ID',
  `rid` int(11) NOT NULL COMMENT 'role ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ib_user_role
-- ----------------------------
