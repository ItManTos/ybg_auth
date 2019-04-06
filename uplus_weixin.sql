

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for njwh_infiniti
-- ----------------------------
DROP TABLE IF EXISTS `njwh_infiniti`;
CREATE TABLE `njwh_infiniti` (
  `app_id` varchar(64) NOT NULL COMMENT '公众号appid',
  `secret` varchar(64) DEFAULT NULL COMMENT '公众号密钥，如果有第三方托管这个不填也可以',
  `token` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '消息通信token',
  `aes_key` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '解密的密钥',
  `app_name` varchar(100) DEFAULT NULL COMMENT '公众号的名称',
  `component_app_id` varchar(255) DEFAULT NULL COMMENT '非必填！ 如果有第三方接管则要填上此项',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公众号表';

-- ----------------------------
-- Table structure for wechat_minapp_config
-- ----------------------------
DROP TABLE IF EXISTS `wechat_minapp_config`;
CREATE TABLE `wechat_minapp_config` (
  `appid` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '应用编号',
  `secret` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '密钥',
  `token` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '消息Token',
  `aes_key` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '加密',
  `msg_data_format` varchar(255) CHARACTER SET utf8 DEFAULT 'JSON' COMMENT '消息数据格式',
  `wxapp_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '小程序名称',
  PRIMARY KEY (`appid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信小程序配置';

-- ----------------------------
-- Table structure for wechat_open_platform
-- ----------------------------
DROP TABLE IF EXISTS `wechat_open_platform`;
CREATE TABLE `wechat_open_platform` (
  `component_app_id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '第三方公众号ID',
  `component_secret` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '第三方公众号密钥',
  `component_token` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '第三方公众号消息Token',
  `component_aes_key` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '第三方公众号Token解密密钥',
  `component_name` varchar(255) DEFAULT NULL COMMENT '第三方接管名称',
  PRIMARY KEY (`component_app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='第三方公众号';

-- ----------------------------
-- Table structure for wechat_pay_config
-- ----------------------------
DROP TABLE IF EXISTS `wechat_pay_config`;
CREATE TABLE `wechat_pay_config` (
  `app_id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '公众号appid.',
  `mch_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商户号.',
  `mch_key` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商户密钥.',
  `sub_app_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '服务商模式下的子商户号.',
  `sub_mch_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '服务商模式下的子商户公众账号ID.',
  `key_path` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'p12证书文件的绝对路径或者以classpath:开头的类路径.',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信支付列表';

-- ----------------------------
-- Table structure for wx_user_
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_`;
CREATE TABLE `wx_user_` (
  `app_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `subscribe` tinyint(4) DEFAULT NULL COMMENT '是否关注',
  `open_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '公众号ID',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `sex_desc` varchar(10) DEFAULT NULL COMMENT '性别描述',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `language` varchar(60) DEFAULT NULL COMMENT '语言',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `subscribe_time` decimal(10,0) DEFAULT NULL COMMENT '关注事件',
  `union_id` int(11) DEFAULT NULL COMMENT '开放平台ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `group_id` int(11) DEFAULT NULL COMMENT '分组ID',
  `subscribe_scene` varchar(255) DEFAULT NULL COMMENT '返回用户关注的渠道来源',
  `qr_scene` varchar(255) DEFAULT NULL COMMENT '二维码扫码场景',
  `qr_scene_str` varchar(255) DEFAULT NULL COMMENT '二维码扫码场景描述',
  PRIMARY KEY (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Table structure for wx_user_tag_
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_tag_`;
CREATE TABLE `wx_user_tag_` (
  `app_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '公众号应用编码',
  `open_id` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '用户公众号ID',
  `tag_id` bigint(20) DEFAULT NULL COMMENT '标签ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `tag_name` varchar(255) DEFAULT NULL COMMENT '标签名称（冗余查询）',
  PRIMARY KEY (`open_id`),
  KEY `tag_id` (`tag_id`),
  KEY `open_id` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户打标签';

-- ----------------------------
-- Table structure for wx_user_tag_wxa246873de4b351d5
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_tag_wxa246873de4b351d5`;
CREATE TABLE `wx_user_tag_wxa246873de4b351d5` (
  `app_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '公众号应用编码',
  `open_id` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '用户公众号ID',
  `tag_id` bigint(20) DEFAULT NULL COMMENT '标签ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `tag_name` varchar(255) DEFAULT NULL COMMENT '标签名称（冗余查询）',
  PRIMARY KEY (`open_id`),
  KEY `tag_id_wx_user_tag_wxa246873de4b351d5` (`tag_id`),
  KEY `open_id_wx_user_tag_wxa246873de4b351d5` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for wx_user_tag_wxbed172448f475c85
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_tag_wxbed172448f475c85`;
CREATE TABLE `wx_user_tag_wxbed172448f475c85` (
  `app_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '公众号应用编码',
  `open_id` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '用户公众号ID',
  `tag_id` bigint(20) DEFAULT NULL COMMENT '标签ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `tag_name` varchar(255) DEFAULT NULL COMMENT '标签名称（冗余查询）',
  PRIMARY KEY (`open_id`),
  KEY `tag_id_wx_user_tag_wxbed172448f475c85` (`tag_id`),
  KEY `open_id_wx_user_tag_wxbed172448f475c85` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for wx_user_tag_wxc6df6f48e697742c
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_tag_wxc6df6f48e697742c`;
CREATE TABLE `wx_user_tag_wxc6df6f48e697742c` (
  `app_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '公众号应用编码',
  `open_id` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '用户公众号ID',
  `tag_id` bigint(20) DEFAULT NULL COMMENT '标签ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `tag_name` varchar(255) DEFAULT NULL COMMENT '标签名称（冗余查询）',
  PRIMARY KEY (`open_id`),
  KEY `tag_id_wx_user_tag_wxc6df6f48e697742c` (`tag_id`),
  KEY `open_id_wx_user_tag_wxc6df6f48e697742c` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for wx_user_tag_wxee9c2c58eba7d37b
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_tag_wxee9c2c58eba7d37b`;
CREATE TABLE `wx_user_tag_wxee9c2c58eba7d37b` (
  `app_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '公众号应用编码',
  `open_id` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '用户公众号ID',
  `tag_id` bigint(20) DEFAULT NULL COMMENT '标签ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `tag_name` varchar(255) DEFAULT NULL COMMENT '标签名称（冗余查询）',
  PRIMARY KEY (`open_id`),
  KEY `tag_id_wx_user_tag_wxee9c2c58eba7d37b` (`tag_id`),
  KEY `open_id_wx_user_tag_wxee9c2c58eba7d37b` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for wx_user_wxa246873de4b351d5
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_wxa246873de4b351d5`;
CREATE TABLE `wx_user_wxa246873de4b351d5` (
  `app_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `subscribe` tinyint(4) DEFAULT NULL COMMENT '是否关注',
  `open_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '公众号ID',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `sex_desc` varchar(10) DEFAULT NULL COMMENT '性别描述',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `language` varchar(60) DEFAULT NULL COMMENT '语言',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `subscribe_time` decimal(10,0) DEFAULT NULL COMMENT '关注事件',
  `union_id` int(11) DEFAULT NULL COMMENT '开放平台ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `group_id` int(11) DEFAULT NULL COMMENT '分组ID',
  `subscribe_scene` varchar(255) DEFAULT NULL COMMENT '返回用户关注的渠道来源',
  `qr_scene` varchar(255) DEFAULT NULL COMMENT '二维码扫码场景',
  `qr_scene_str` varchar(255) DEFAULT NULL COMMENT '二维码扫码场景描述',
  PRIMARY KEY (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Table structure for wx_user_wxbed172448f475c85
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_wxbed172448f475c85`;
CREATE TABLE `wx_user_wxbed172448f475c85` (
  `app_id` varchar(64) DEFAULT NULL,
  `subscribe` tinyint(4) DEFAULT NULL COMMENT '是否关注',
  `open_id` varchar(64) NOT NULL COMMENT '公众号ID',
  `nickname` varchar(254) DEFAULT NULL COMMENT '昵称',
  `sex_desc` varchar(10) DEFAULT NULL COMMENT '性别描述',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `language` varchar(60) DEFAULT NULL COMMENT '语言',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `subscribe_time` decimal(10,0) DEFAULT NULL COMMENT '关注事件',
  `union_id` int(11) DEFAULT NULL COMMENT '开放平台ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `group_id` int(11) DEFAULT NULL COMMENT '分组ID',
  `subscribe_scene` varchar(255) DEFAULT NULL COMMENT '返回用户关注的渠道来源',
  `qr_scene` varchar(255) DEFAULT NULL COMMENT '二维码扫码场景',
  `qr_scene_str` varchar(255) DEFAULT NULL COMMENT '二维码扫码场景描述',
  PRIMARY KEY (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Table structure for wx_user_wxc6df6f48e697742c
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_wxc6df6f48e697742c`;
CREATE TABLE `wx_user_wxc6df6f48e697742c` (
  `app_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `subscribe` tinyint(4) DEFAULT NULL COMMENT '是否关注',
  `open_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '公众号ID',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `sex_desc` varchar(10) DEFAULT NULL COMMENT '性别描述',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `language` varchar(60) DEFAULT NULL COMMENT '语言',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `subscribe_time` decimal(10,0) DEFAULT NULL COMMENT '关注事件',
  `union_id` int(11) DEFAULT NULL COMMENT '开放平台ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `group_id` int(11) DEFAULT NULL COMMENT '分组ID',
  `subscribe_scene` varchar(255) DEFAULT NULL COMMENT '返回用户关注的渠道来源',
  `qr_scene` varchar(255) DEFAULT NULL COMMENT '二维码扫码场景',
  `qr_scene_str` varchar(255) DEFAULT NULL COMMENT '二维码扫码场景描述',
  PRIMARY KEY (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Table structure for wx_user_wxee9c2c58eba7d37b
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_wxee9c2c58eba7d37b`;
CREATE TABLE `wx_user_wxee9c2c58eba7d37b` (
  `app_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `subscribe` tinyint(4) DEFAULT NULL COMMENT '是否关注',
  `open_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '公众号ID',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `sex_desc` varchar(10) DEFAULT NULL COMMENT '性别描述',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `language` varchar(60) DEFAULT NULL COMMENT '语言',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `subscribe_time` decimal(10,0) DEFAULT NULL COMMENT '关注事件',
  `union_id` int(11) DEFAULT NULL COMMENT '开放平台ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `group_id` int(11) DEFAULT NULL COMMENT '分组ID',
  `subscribe_scene` varchar(255) DEFAULT NULL COMMENT '返回用户关注的渠道来源',
  `qr_scene` varchar(255) DEFAULT NULL COMMENT '二维码扫码场景',
  `qr_scene_str` varchar(255) DEFAULT NULL COMMENT '二维码扫码场景描述',
  PRIMARY KEY (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
