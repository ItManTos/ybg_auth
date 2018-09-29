/*
Navicat MySQL Data Transfer

Source Server         : 内网微平台
Source Server Version : 50722
Source Host           : 192.168.2.149:3306
Source Database       : uplus_wei

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-08-15 00:36:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '山东农信', null, '2018-01-22 19:00:23', '2018-01-23 12:40:46', '0', '0');
INSERT INTO `sys_dept` VALUES ('2', '沙县国际', null, '2018-01-22 19:00:38', '2018-01-23 12:42:04', '0', '0');
INSERT INTO `sys_dept` VALUES ('3', '潍坊农信', null, '2018-01-22 19:00:44', '2018-01-23 12:40:56', '0', '1');
INSERT INTO `sys_dept` VALUES ('4', '高新农信', null, '2018-01-22 19:00:52', '2018-01-23 12:41:11', '0', '3');
INSERT INTO `sys_dept` VALUES ('5', '院校农信', null, '2018-01-22 19:00:57', '2018-01-23 12:41:22', '0', '4');
INSERT INTO `sys_dept` VALUES ('6', '潍坊学院农信', '115', '2018-01-22 19:01:06', '2018-03-07 16:22:04', '0', '5');
INSERT INTO `sys_dept` VALUES ('7', '山东沙县', null, '2018-01-22 19:01:57', '2018-01-23 12:42:15', '0', '2');
INSERT INTO `sys_dept` VALUES ('8', '潍坊沙县', null, '2018-01-22 19:02:03', '2018-01-23 12:42:23', '0', '7');
INSERT INTO `sys_dept` VALUES ('9', '高新沙县', null, '2018-01-22 19:02:14', '2018-03-07 16:28:14', '0', '8');

-- ----------------------------
-- Table structure for `sys_dept_relation`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation` (
  `ancestor` int(11) NOT NULL COMMENT '祖先节点',
  `descendant` int(11) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`,`descendant`),
  KEY `idx1` (`ancestor`),
  KEY `idx2` (`descendant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
INSERT INTO `sys_dept_relation` VALUES ('1', '1');
INSERT INTO `sys_dept_relation` VALUES ('1', '3');
INSERT INTO `sys_dept_relation` VALUES ('1', '4');
INSERT INTO `sys_dept_relation` VALUES ('1', '5');
INSERT INTO `sys_dept_relation` VALUES ('1', '6');
INSERT INTO `sys_dept_relation` VALUES ('2', '2');
INSERT INTO `sys_dept_relation` VALUES ('2', '7');
INSERT INTO `sys_dept_relation` VALUES ('2', '8');
INSERT INTO `sys_dept_relation` VALUES ('2', '9');
INSERT INTO `sys_dept_relation` VALUES ('3', '3');
INSERT INTO `sys_dept_relation` VALUES ('3', '4');
INSERT INTO `sys_dept_relation` VALUES ('3', '5');
INSERT INTO `sys_dept_relation` VALUES ('3', '6');
INSERT INTO `sys_dept_relation` VALUES ('4', '4');
INSERT INTO `sys_dept_relation` VALUES ('4', '5');
INSERT INTO `sys_dept_relation` VALUES ('4', '6');
INSERT INTO `sys_dept_relation` VALUES ('5', '5');
INSERT INTO `sys_dept_relation` VALUES ('5', '6');
INSERT INTO `sys_dept_relation` VALUES ('6', '6');
INSERT INTO `sys_dept_relation` VALUES ('7', '7');
INSERT INTO `sys_dept_relation` VALUES ('7', '8');
INSERT INTO `sys_dept_relation` VALUES ('7', '9');
INSERT INTO `sys_dept_relation` VALUES ('8', '8');
INSERT INTO `sys_dept_relation` VALUES ('8', '9');
INSERT INTO `sys_dept_relation` VALUES ('9', '9');

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('2', '9', '异常', 'log_type', '日志异常', '1', '2017-12-28 13:06:39', '2018-01-06 10:54:41', null, '0');
INSERT INTO `sys_dict` VALUES ('3', '0', '正常', 'log_type', '正常', '1', '2018-05-11 23:52:57', '2018-05-11 23:52:57', '123', '0');

-- ----------------------------
-- Table structure for `sys_log_0`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_0`;
CREATE TABLE `sys_log_0` (
  `id` bigint(64) NOT NULL COMMENT '编号',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) DEFAULT NULL COMMENT '服务ID',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(1000) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(10) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `time` mediumtext COMMENT '执行时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='日志表';

-- ----------------------------
-- Records of sys_log_0
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_log_1`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_1`;
CREATE TABLE `sys_log_1` (
  `id` bigint(64) NOT NULL COMMENT '编号',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) DEFAULT NULL COMMENT '服务ID',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(1000) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(10) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `time` mediumtext COMMENT '执行时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='日志表';

-- ----------------------------
-- Records of sys_log_1
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
  `permission` varchar(32) DEFAULT NULL COMMENT '菜单权限标识',
  `path` varchar(128) DEFAULT NULL COMMENT '前端URL',
  `url` varchar(128) DEFAULT NULL COMMENT '请求链接',
  `method` varchar(32) DEFAULT NULL COMMENT '请求方法',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `component` varchar(64) DEFAULT NULL COMMENT 'VUE页面',
  `sort` int(11) DEFAULT '1' COMMENT '排序值',
  `type` char(1) DEFAULT NULL COMMENT '菜单类型 （0菜单 1按钮）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '0--正常 1--删除',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', null, '/admin', null, null, '-1', 'icon-xitongguanli', 'Layout', '1', '0', '2017-11-07 20:56:00', '2018-05-14 21:53:22', '0');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', null, 'user', '', null, '1', 'icon-yonghuguanli', 'views/admin/user/index', '2', '0', '2017-11-02 22:24:37', '2018-05-14 22:11:35', '0');
INSERT INTO `sys_menu` VALUES ('3', '菜单管理', null, 'menu', '', null, '1', 'icon-caidanguanli', 'views/admin/menu/index', '3', '0', '2017-11-08 09:57:27', '2018-05-14 22:11:21', '0');
INSERT INTO `sys_menu` VALUES ('4', '角色管理', null, 'role', null, null, '1', 'icon-jiaoseguanli', 'views/admin/role/index', '4', '0', '2017-11-08 10:13:37', '2018-05-14 22:11:19', '0');
INSERT INTO `sys_menu` VALUES ('5', '日志管理', null, 'log', null, null, '1', 'icon-rizhiguanli', 'views/admin/log/index', '5', '0', '2017-11-20 14:06:22', '2018-05-14 22:11:18', '0');
INSERT INTO `sys_menu` VALUES ('6', '字典管理', null, 'dict', null, null, '1', 'icon-zygl', 'views/admin/dict/index', '6', '0', '2017-11-29 11:30:52', '2018-05-14 22:12:48', '0');
INSERT INTO `sys_menu` VALUES ('7', '部门管理', null, 'dept', null, null, '1', 'icon-iconbmgl', 'views/admin/dept/index', '7', '0', '2018-01-20 13:17:19', '2018-05-14 22:11:16', '0');
INSERT INTO `sys_menu` VALUES ('8', '系统监控', null, '', null, null, '-1', 'icon-iconbmgl', null, '8', '0', '2018-01-22 12:30:41', '2018-05-14 20:41:16', '0');
INSERT INTO `sys_menu` VALUES ('9', '服务监控', null, 'http://139.224.200.249:5001', null, null, '8', 'icon-jiankong', null, '9', '0', '2018-01-23 10:53:33', '2018-04-21 03:51:56', '0');
INSERT INTO `sys_menu` VALUES ('10', 'zipkin监控', null, 'http://139.224.200.249:5002', null, null, '8', 'icon-jiankong', null, '11', '0', '2018-01-23 10:55:18', '2018-04-22 07:02:34', '0');
INSERT INTO `sys_menu` VALUES ('11', 'pinpoint监控', null, 'https://pinpoint.pig4cloud.com', null, null, '8', 'icon-xiazaihuancun', null, '10', '0', '2018-01-25 11:08:52', '2018-04-22 07:02:38', '0');
INSERT INTO `sys_menu` VALUES ('12', '缓存状态', null, 'http://139.224.200.249:8585', null, null, '8', 'icon-ecs-status', null, '12', '0', '2018-01-23 10:56:11', '2018-04-21 03:51:47', '0');
INSERT INTO `sys_menu` VALUES ('13', 'ELK状态', null, 'http://139.224.200.249:5601', null, null, '8', 'icon-ecs-status', null, '13', '0', '2018-01-23 10:55:47', '2018-04-21 03:51:40', '0');
INSERT INTO `sys_menu` VALUES ('14', '接口文档', null, 'http://139.224.200.249:9999/swagger-ui.html', null, null, '8', 'icon-wendangdocument72', null, '14', '0', '2018-01-23 10:56:43', '2018-04-21 03:50:47', '0');
INSERT INTO `sys_menu` VALUES ('15', '任务监控', null, 'http://139.224.200.249:8899', null, null, '8', 'icon-jiankong', null, '15', '0', '2018-01-23 10:55:18', '2018-04-21 03:51:34', '0');
INSERT INTO `sys_menu` VALUES ('21', '用户查看', '', null, '/admin/user/**', 'GET', '2', null, null, null, '1', '2017-11-07 20:58:05', '2018-02-04 14:28:49', '0');
INSERT INTO `sys_menu` VALUES ('22', '用户新增', 'sys_user_add', null, '/admin/user/*', 'POST', '2', null, null, null, '1', '2017-11-08 09:52:09', '2017-12-04 16:31:10', '0');
INSERT INTO `sys_menu` VALUES ('23', '用户修改', 'sys_user_upd', null, '/admin/user/**', 'PUT', '2', null, null, null, '1', '2017-11-08 09:52:48', '2018-01-17 17:40:01', '0');
INSERT INTO `sys_menu` VALUES ('24', '用户删除', 'sys_user_del', null, '/admin/user/*', 'DELETE', '2', null, null, null, '1', '2017-11-08 09:54:01', '2017-12-04 16:31:18', '0');
INSERT INTO `sys_menu` VALUES ('31', '菜单查看', null, null, '/admin/menu/**', 'GET', '3', null, null, null, '1', '2017-11-08 09:57:56', '2017-11-14 17:29:17', '0');
INSERT INTO `sys_menu` VALUES ('32', '菜单新增', 'sys_menu_add', null, '/admin/menu/*', 'POST', '3', null, null, null, '1', '2017-11-08 10:15:53', '2018-01-20 14:37:50', '0');
INSERT INTO `sys_menu` VALUES ('33', '菜单修改', 'sys_menu_edit', null, '/admin/menu/*', 'PUT', '3', null, null, null, '1', '2017-11-08 10:16:23', '2018-01-20 14:37:56', '0');
INSERT INTO `sys_menu` VALUES ('34', '菜单删除', 'sys_menu_del', null, '/admin/menu/*', 'DELETE', '3', null, null, null, '1', '2017-11-08 10:16:43', '2018-01-20 14:38:03', '0');
INSERT INTO `sys_menu` VALUES ('41', '角色查看', null, null, '/admin/role/**', 'GET', '4', null, null, null, '1', '2017-11-08 10:14:01', '2018-02-04 13:55:06', '0');
INSERT INTO `sys_menu` VALUES ('42', '角色新增', 'sys_role_add', null, '/admin/role/*', 'POST', '4', null, null, null, '1', '2017-11-08 10:14:18', '2018-04-20 07:21:38', '0');
INSERT INTO `sys_menu` VALUES ('43', '角色修改', 'sys_role_edit', null, '/admin/role/*', 'PUT', '4', null, null, null, '1', '2017-11-08 10:14:41', '2018-04-20 07:21:50', '0');
INSERT INTO `sys_menu` VALUES ('44', '角色删除', 'sys_role_del', null, '/admin/role/*', 'DELETE', '4', null, null, null, '1', '2017-11-08 10:14:59', '2018-04-20 07:22:00', '0');
INSERT INTO `sys_menu` VALUES ('45', '分配权限', 'sys_role_perm', null, '/admin/role/*', 'PUT', '4', null, null, null, '1', '2018-04-20 07:22:55', '2018-04-20 07:24:40', '0');
INSERT INTO `sys_menu` VALUES ('51', '日志查看', null, null, '/admin/log/**', 'GET', '5', null, null, null, '1', '2017-11-20 14:07:25', '2018-02-04 14:28:53', '0');
INSERT INTO `sys_menu` VALUES ('52', '日志删除', 'sys_log_del', null, '/admin/log/*', 'DELETE', '5', null, null, null, '1', '2017-11-20 20:37:37', '2017-11-28 17:36:52', '0');
INSERT INTO `sys_menu` VALUES ('61', '字典查看', null, null, '/admin/dict/**', 'GET', '6', null, null, null, '1', '2017-11-19 22:04:24', '2017-11-29 11:31:24', '0');
INSERT INTO `sys_menu` VALUES ('62', '字典删除', 'sys_dict_del', null, '/admin/dict/**', 'DELETE', '6', null, null, null, '1', '2017-11-29 11:30:11', '2018-01-07 15:40:51', '0');
INSERT INTO `sys_menu` VALUES ('63', '字典新增', 'sys_dict_add', null, '/admin/dict/**', 'POST', '6', null, null, null, '1', '2018-05-11 22:34:55', null, '0');
INSERT INTO `sys_menu` VALUES ('64', '字典修改', 'sys_dict_upd', null, '/admin/dict/**', 'PUT', '6', null, null, null, '1', '2018-05-11 22:36:03', null, '0');
INSERT INTO `sys_menu` VALUES ('71', '部门查看', '', null, '/admin/dept/**', 'GET', '7', null, '', null, '1', '2018-01-20 13:17:19', '2018-01-20 14:55:24', '0');
INSERT INTO `sys_menu` VALUES ('72', '部门新增', 'sys_dept_add', null, '/admin/dept/**', 'POST', '7', null, null, null, '1', '2018-01-20 14:56:16', '2018-01-20 21:17:57', '0');
INSERT INTO `sys_menu` VALUES ('73', '部门修改', 'sys_dept_edit', null, '/admin/dept/**', 'PUT', '7', null, null, null, '1', '2018-01-20 14:56:59', '2018-01-20 21:17:59', '0');
INSERT INTO `sys_menu` VALUES ('74', '部门删除', 'sys_dept_del', null, '/admin/dept/**', 'DELETE', '7', null, null, null, '1', '2018-01-20 14:57:28', '2018-01-20 21:18:05', '0');
INSERT INTO `sys_menu` VALUES ('100', '客户端管理', '', 'client', '', '', '1', 'icon-bangzhushouji', 'views/admin/client/index', '9', '0', '2018-01-20 13:17:19', '2018-05-15 21:28:10', '0');
INSERT INTO `sys_menu` VALUES ('101', '客户端新增', 'sys_client_add', null, '/admin/client/**', 'POST', '100', '1', null, null, '1', '2018-05-15 21:35:18', '2018-05-16 10:35:26', '0');
INSERT INTO `sys_menu` VALUES ('102', '客户端修改', 'sys_client_upd', null, '/admin/client/**', 'PUT', '100', null, null, null, '1', '2018-05-15 21:37:06', '2018-05-15 21:52:30', '0');
INSERT INTO `sys_menu` VALUES ('103', '客户端删除', 'sys_client_del', null, '/admin/client/**', 'DELETE', '100', null, null, null, '1', '2018-05-15 21:39:16', '2018-05-15 21:52:34', '0');
INSERT INTO `sys_menu` VALUES ('104', '客户端查看', null, null, '/admin/client/**', 'GET', '100', null, null, null, '1', '2018-05-15 21:39:57', '2018-05-15 21:52:40', '0');
INSERT INTO `sys_menu` VALUES ('110', '路由管理', null, 'route', null, null, '1', 'icon-luyou', 'views/admin/route/index', '8', '0', '2018-05-15 21:44:51', '2018-05-16 06:58:20', '0');
INSERT INTO `sys_menu` VALUES ('111', '路由查看', null, null, '/admin/route/**', 'GET', '110', null, null, null, '1', '2018-05-15 21:45:59', '2018-05-16 07:23:04', '0');
INSERT INTO `sys_menu` VALUES ('112', '路由新增', 'sys_route_add', null, '/admin/route/**', 'POST', '110', null, null, null, '1', '2018-05-15 21:52:22', '2018-05-15 21:53:46', '0');
INSERT INTO `sys_menu` VALUES ('113', '路由修改', 'sys_route_upd', null, '/admin/route/**', 'PUT', '110', null, null, null, '1', '2018-05-15 21:55:38', null, '0');
INSERT INTO `sys_menu` VALUES ('114', '路由删除', 'sys_route_del', null, '/admin/route/**', 'DELETE', '110', null, null, null, '1', '2018-05-15 21:56:45', null, '0');

-- ----------------------------
-- Table structure for `sys_oauth_client_details`
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details` (
  `client_id` varchar(40) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES ('app', null, 'app', 'server', 'password,refresh_token', null, null, null, null, null, 'true');
INSERT INTO `sys_oauth_client_details` VALUES ('pig', null, 'pig', 'server', 'password,refresh_token', null, null, null, null, null, 'false');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `role_code` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `role_desc` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_idx1_role_code` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', 'ROLE_ADMIN', '超级管理员', '2017-10-29 15:45:51', '2018-04-22 11:40:29', '0');
INSERT INTO `sys_role` VALUES ('2', 'wei_branch_manager', 'wei_branch_manager', '微平台网点管理员', '2018-06-23 14:14:56', '2018-06-23 14:15:06', '0');
INSERT INTO `sys_role` VALUES ('14', 'demo', 'demo', 'demo用户', '2018-04-20 07:14:32', '2018-04-21 23:35:22', '0');

-- ----------------------------
-- Table structure for `sys_role_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` int(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色与部门对应关系';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('11', '1', '1');
INSERT INTO `sys_role_dept` VALUES ('14', '14', '1');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '7');
INSERT INTO `sys_role_menu` VALUES ('1', '8');
INSERT INTO `sys_role_menu` VALUES ('1', '9');
INSERT INTO `sys_role_menu` VALUES ('1', '10');
INSERT INTO `sys_role_menu` VALUES ('1', '11');
INSERT INTO `sys_role_menu` VALUES ('1', '12');
INSERT INTO `sys_role_menu` VALUES ('1', '13');
INSERT INTO `sys_role_menu` VALUES ('1', '14');
INSERT INTO `sys_role_menu` VALUES ('1', '15');
INSERT INTO `sys_role_menu` VALUES ('1', '21');
INSERT INTO `sys_role_menu` VALUES ('1', '22');
INSERT INTO `sys_role_menu` VALUES ('1', '23');
INSERT INTO `sys_role_menu` VALUES ('1', '24');
INSERT INTO `sys_role_menu` VALUES ('1', '31');
INSERT INTO `sys_role_menu` VALUES ('1', '32');
INSERT INTO `sys_role_menu` VALUES ('1', '33');
INSERT INTO `sys_role_menu` VALUES ('1', '34');
INSERT INTO `sys_role_menu` VALUES ('1', '41');
INSERT INTO `sys_role_menu` VALUES ('1', '42');
INSERT INTO `sys_role_menu` VALUES ('1', '43');
INSERT INTO `sys_role_menu` VALUES ('1', '44');
INSERT INTO `sys_role_menu` VALUES ('1', '45');
INSERT INTO `sys_role_menu` VALUES ('1', '51');
INSERT INTO `sys_role_menu` VALUES ('1', '52');
INSERT INTO `sys_role_menu` VALUES ('1', '61');
INSERT INTO `sys_role_menu` VALUES ('1', '62');
INSERT INTO `sys_role_menu` VALUES ('1', '63');
INSERT INTO `sys_role_menu` VALUES ('1', '64');
INSERT INTO `sys_role_menu` VALUES ('1', '71');
INSERT INTO `sys_role_menu` VALUES ('1', '72');
INSERT INTO `sys_role_menu` VALUES ('1', '73');
INSERT INTO `sys_role_menu` VALUES ('1', '74');
INSERT INTO `sys_role_menu` VALUES ('1', '100');
INSERT INTO `sys_role_menu` VALUES ('1', '101');
INSERT INTO `sys_role_menu` VALUES ('1', '102');
INSERT INTO `sys_role_menu` VALUES ('1', '103');
INSERT INTO `sys_role_menu` VALUES ('1', '104');
INSERT INTO `sys_role_menu` VALUES ('1', '110');
INSERT INTO `sys_role_menu` VALUES ('1', '111');
INSERT INTO `sys_role_menu` VALUES ('1', '112');
INSERT INTO `sys_role_menu` VALUES ('1', '113');
INSERT INTO `sys_role_menu` VALUES ('1', '114');
INSERT INTO `sys_role_menu` VALUES ('14', '1');
INSERT INTO `sys_role_menu` VALUES ('14', '2');
INSERT INTO `sys_role_menu` VALUES ('14', '3');
INSERT INTO `sys_role_menu` VALUES ('14', '4');
INSERT INTO `sys_role_menu` VALUES ('14', '5');
INSERT INTO `sys_role_menu` VALUES ('14', '6');
INSERT INTO `sys_role_menu` VALUES ('14', '7');
INSERT INTO `sys_role_menu` VALUES ('14', '8');
INSERT INTO `sys_role_menu` VALUES ('14', '9');
INSERT INTO `sys_role_menu` VALUES ('14', '10');
INSERT INTO `sys_role_menu` VALUES ('14', '11');
INSERT INTO `sys_role_menu` VALUES ('14', '12');
INSERT INTO `sys_role_menu` VALUES ('14', '13');
INSERT INTO `sys_role_menu` VALUES ('14', '14');
INSERT INTO `sys_role_menu` VALUES ('14', '15');
INSERT INTO `sys_role_menu` VALUES ('14', '21');
INSERT INTO `sys_role_menu` VALUES ('14', '31');
INSERT INTO `sys_role_menu` VALUES ('14', '41');
INSERT INTO `sys_role_menu` VALUES ('14', '51');
INSERT INTO `sys_role_menu` VALUES ('14', '61');
INSERT INTO `sys_role_menu` VALUES ('14', '71');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `salt` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '随机盐',
  `phone` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '简介',
  `avatar` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '0-正常，1-删除',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_idx1_username` (`username`),
  UNIQUE KEY `user_idx2_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null, '17034642111', null, '1', '2018-04-20 07:15:18', '2018-06-23 14:51:54', '0');
INSERT INTO `sys_user` VALUES ('4', 'pig', '$2a$10$vg5QNHhCknAqevx9vM2s5esllJEzF/pa8VZXtFYHhhOhUcCw/GWyS', null, '17034642118', null, '1', '2018-04-22 11:39:07', '2018-05-10 18:01:11', '0');
INSERT INTO `sys_user` VALUES ('100', 'test', 'e10adc3949ba59abbe56e057f20f883e', null, '88888888888', null, null, '2018-07-02 17:37:18', '2018-07-02 17:38:00', '0');
INSERT INTO `sys_user` VALUES ('200', '余庆琳', 'f5518e2d3648de2552a003ef407d3cff', null, '15002096821', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/DA/Chim91mT9H2AW7wDAAMxNIJQnIE906.jpg', null, '2018-07-23 16:40:22', '2018-07-23 16:40:22', '0');
INSERT INTO `sys_user` VALUES ('201', '梁越新', '68ace1f676cba8de87de9927c2600242', null, '13823376960', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/0A/tlzDm1bFNj6AMv9MAABMJ5Tat7g850.jpg', null, '2018-07-23 16:40:22', '2018-07-23 16:40:22', '0');
INSERT INTO `sys_user` VALUES ('202', '许丹璇', '6daeca4903af2175474638ebc0912961', null, '13631670710', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/12/tlzDm1cDExyAQhfcAAFY1AWiJ1k761.png', null, '2018-07-23 16:40:22', '2018-07-23 16:40:22', '0');
INSERT INTO `sys_user` VALUES ('203', '饶夏子', '20e95ad3ef36e621fe06f9dbe06aa15a', null, '13590167318', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/1D/tlzDm1d7v6OAeSJLAAA1AqZcZmM072.jpg', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('204', '朱睿东', 'b84a784ae2011503499473946c58d786', null, '15920070119', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/06/tlzDm1Z6AlaAHgODAAR_UzjeYwE308.png', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('205', '郑清玲', '49af13a330861b92d2da2341ff86ee6a', null, '13640978361', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/08/tlzDm1aMzS6AE5ZgAAPbrMXpwcY314.png', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('206', '陕星辰', 'e10adc3949ba59abbe56e057f20f883e', null, '13028808915', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/67/Chim91pufN-AXrnYAADG7Y14cRs401.jpg', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('207', '刘刚', '04c34e6cef8cb5460925dda6211b22d9', null, '13267120272', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/25/Chim91oEB5aADbViAAEwQmNZVw0686.jpg', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('208', '纪燕玲', 'e10adc3949ba59abbe56e057f20f883e', null, '13530705547', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/14/CkJd1FsGGP2AHgFAAAOATZ30fIw77.jpeg', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('209', '赖玉萍', '40824539d8c9938ae241fad6372bec7c', null, '13424190012', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/0E/tlzDm1bhNkuAE-CdAACwbC-2xJs897.png', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('210', '叶来胜', '39eed10456ba857d9ad72d02dbc57e16', null, '13728864042', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/0E/tlzDm1bhN6OAe4EFAADmxD5K1l8537.png', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('211', '陈旭基', 'f53b35972c6c8bfd5e910e59d1acb0b1', null, '13603024962', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/03/Chim91nd1fCAZ7KxAAD1aYJZmLk821.jpg', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('212', '汤晓龙', '6f51590e292ca2d33dd63dd1a48dfdf0', null, '13751155115', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/11/tlzDm1b0w2OAADutAAGUGsEOLdM635.png', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('213', '林旭伟', '21218cca77804d2ba1922c33e0151105', null, '18038078080', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/18/tlzDm1dLnl2AfWtvAAByoaff1dA650.jpg', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('214', '杜琴', 'e10adc3949ba59abbe56e057f20f883e', null, '15915491143', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/20/CkJd1Fsh1v2APjJ0AAcvO-jfG3w946.jpg', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('215', '张煜烨', '39eed10456ba857d9ad72d02dbc57e16', null, '15817474822', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/1B/tlzDm1do_MCAEWe8AADQAgUnxXk309.png', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('216', '林桂坛', '0089ae3989a841b86374518128149250', null, '18126082505', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/1F/tlzDm1eEsC-ALo5PAADET95fU7o439.png', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('217', '林乐珊', 'e10adc3949ba59abbe56e057f20f883e', null, '18923776107', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/1F/tlzDm1eErzeAUxXLAADYN8uHnX8233.png', null, '2018-07-23 16:40:23', '2018-07-23 16:40:23', '0');
INSERT INTO `sys_user` VALUES ('218', '林晓露', 'e10adc3949ba59abbe56e057f20f883e', null, '18312534588', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/21/tlzDm1eMYTeAI09wAADmp4ZFvR8939.png', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('219', '欧诗莹', 'b2948feb4a8d3d30d9ef406b4b4573bf', null, '18926769660', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/2C/tlzDm1fI5EmAPRbhAAC6uCa-y2k379.jpg', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('220', '郑雪芬', '39eed10456ba857d9ad72d02dbc57e16', null, '18018776085', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/2E/tlzDm1fSVGaAUBBvAABav18QxAc729.jpg', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('221', '汪磊', '5e0f236f524ee71937100ff976971578', null, '13510115226', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/DC/CkJd1FqcqCaAJRv7AADh2gzVix4134.jpg', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('222', '罗育芳', 'aadb2aa20e95954c4425cbd337ca1fc6', null, '13714515578', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/62/tlzDm1hGZPSAU1dpAAA_oAgVTCM23.jpeg', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('223', '秦金钰', 'e10adc3949ba59abbe56e057f20f883e', null, '13922850588', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/64/tlzDm1hJMl6AHVWhAAPzbuCSvIE262.png', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('224', '邵雪云', 'ccbe76dbf5003ee0db6af7a32e8b5c09', null, '18126082660', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/90/tlzDm1i-IriAF-MHAAgclk3JJBA618.png', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('225', '陈耿', '5a565ba0823a1c9824af40c59c845ff8', null, '13682684796', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/96/tlzDm1jSPbCAWgT6AACPL4W70eM323.jpg', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('226', '徐凉', 'dbf66a7f1a9e53a4a026c1afa35cc261', null, '17722408316', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/9C/tlzDm1jnBzyAATIjAAFYlXPBoxk379.png', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('227', '廖艳红', 'b5da63e65ddad5acf24e2d23c7238f77', null, '13632760150', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/C2/eEyaMFlKA6iAHgiOAALiMpvNZ6U453.png', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('228', '魏娜', 'e10adc3949ba59abbe56e057f20f883e', null, '13145850088', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/E7/Chim91mvcK2AGIKiAAcyMlkou80650.jpg', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('229', '林宙', 'e10adc3949ba59abbe56e057f20f883e', null, '13826529027', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/65/Chim91pqguqAYMruAAC5RKPaSuM988.jpg', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('230', '李小英', 'e10adc3949ba59abbe56e057f20f883e', null, '13691908313', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/D3/Chim91mCf8eAIflgAALIEpgY6DI724.jpg', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('231', '陈晓茹', 'e10adc3949ba59abbe56e057f20f883e', null, '13538182886', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/D3/Chim91mC3rKAP7EEAABxSOg5KiM823.jpg', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('232', '熊甜子', '1929756b328478455b81d249f68d41aa', null, '15986785867', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/D5/Chim91mJGDmAeMiSAADluROqFZw712.jpg', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('233', '吴宗镇', 'e10adc3949ba59abbe56e057f20f883e', null, '13798248343', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/18/CkJd1FsPZh6AWsPPAA4nt7WL9Cg52.jpeg', null, '2018-07-23 16:40:24', '2018-07-23 16:40:24', '0');
INSERT INTO `sys_user` VALUES ('234', '林霖', 'e10adc3949ba59abbe56e057f20f883e', null, '18617080102', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/DB/Chim91mVEDuAO-qAAABzoXp9nnQ838.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('235', '姚晓虹', 'e10adc3949ba59abbe56e057f20f883e', null, '13590301560', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/FA/Chim91nLUj2Aa85gAAbtOpOrPiI854.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('236', '胡燕翔', 'e10adc3949ba59abbe56e057f20f883e', null, '13751051793', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/DC/Chim91mWp0OAcHqPAAQb2wDQpYA271.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('237', '周玉萍', 'e10adc3949ba59abbe56e057f20f883e', null, '13751003620', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/2E/Chim91oWMx6AZWB4AAKxqq9hD_k705.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('238', '张和铭', 'e10adc3949ba59abbe56e057f20f883e', null, '13760273553', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/DC/Chim91mWyWqAanJUAAB_wwzukE0906.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('239', '杨树萍', 'e10adc3949ba59abbe56e057f20f883e', null, '13632849565', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/DC/Chim91mWkIaAVwG5AACkWSoIhYU097.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('240', '危安', 'a5e7702245122d8e5de605b713bcf268', null, '18898591487', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/DD/Chim91mbnduALd6BAAF5A_2Jxhw204.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('241', '敖琪', 'e10adc3949ba59abbe56e057f20f883e', null, '15818567100', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/11/CkJd1Fr-qT6AW986AAFtVDsft8085.jpeg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('242', '林慧慧', 'e10adc3949ba59abbe56e057f20f883e', null, '13798281592', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/E6/CkJd1FqvdbGAAN0mAAEKsQ7ZW9c332.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('243', '廖雅军', 'e10adc3949ba59abbe56e057f20f883e', null, '13684910489', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/DC/CkJd1FqcqUSAYW1PAAE0LWAoNw0799.png', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('244', '邓丽', 'e10adc3949ba59abbe56e057f20f883e', null, '13267136360', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/15/Chim91nxPQeAfvRWAAAysqn_BVo089.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('245', '罗静文', '0d90e45d5ac6a2fb5441dc3d284392e4', null, '13760245362', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/FD/Chim91nN3sqAM7hWAADNhtljL68193.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('246', '陈显军', 'e10adc3949ba59abbe56e057f20f883e', null, '13760252069', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/0A/tlzDm1bC1jaAL1wcAABFy1VHRcw17.jpeg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('247', '徐慕蓉', 'e10adc3949ba59abbe56e057f20f883e', null, '15919880889', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/DF/CkJd1Fqg4N2ATlWOAAPIbqRhx8Q515.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('248', '张龙', 'e10adc3949ba59abbe56e057f20f883e', null, '15019220293', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/E7/Chim91mvnnaAU82hAABObDW_XbQ155.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('249', '谭宏林', 'e10adc3949ba59abbe56e057f20f883e', null, '15002065905', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/FA/Chim91nLUX2AMHjbAAHAFDAu8RA473.png', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('250', '胡瑜', '5dc0be8deb62f60cd56b31b9b7cb337b', null, '13424311994', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/FD/Chim91nNsEWAD7GIAACsVpp5tZI312.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('251', '吕婉怡', 'e10adc3949ba59abbe56e057f20f883e', null, '13590223393', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/0A/tlzDm1bC1jaAL1wcAABFy1VHRcw17.jpeg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('252', '池丽娟', '2ddce52cd81704d677fae374d145fdf5', null, '13534031812', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/16/Chim91nxtZmAAnDBAAI_DXXrjr8794.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('253', '曾小燕', 'e10adc3949ba59abbe56e057f20f883e', null, '13510799079', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/F3/Chim91nE3ueATThQAAB82nhUe7g976.jpg', null, '2018-07-23 16:40:25', '2018-07-23 16:40:25', '0');
INSERT INTO `sys_user` VALUES ('254', '谢根娣', 'e10adc3949ba59abbe56e057f20f883e', null, '13537757359', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/43/Chim91o3Nb6AU8Q5AADujr1ZQkc769.jpg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');
INSERT INTO `sys_user` VALUES ('255', '刘永柱', 'e10adc3949ba59abbe56e057f20f883e', null, '18823678315', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/FF/Chim91nPaf-AAyP3AACcyqRZ65U786.jpg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');
INSERT INTO `sys_user` VALUES ('256', '叶梅', 'e10adc3949ba59abbe56e057f20f883e', null, '18688965929', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/3A/Chim91ot3ViAQGrWAABkBbt20dQ870.jpg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');
INSERT INTO `sys_user` VALUES ('257', '邹丹娜', 'e10adc3949ba59abbe56e057f20f883e', null, '15768456721', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/03/Chim91ndxgOALfXPAAO325bqCaw798.jpg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');
INSERT INTO `sys_user` VALUES ('258', '黄珑玲', 'f866eaa2d8f163d2b4e99259966427c8', null, '13715221933', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/15/Chim91nxWrqAJgpFAAAdl1-uEck747.jpg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');
INSERT INTO `sys_user` VALUES ('259', '刘宸', 'e10adc3949ba59abbe56e057f20f883e', null, '13723468916', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/23/Chim91oCmqiAU5ziAAAegZtZVPU340.jpg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');
INSERT INTO `sys_user` VALUES ('260', '田扬扬', 'e10adc3949ba59abbe56e057f20f883e', null, '18218811567', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/16/CkJd1FsLqx6ATgOiAAEBUjhT_6k09.jpeg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');
INSERT INTO `sys_user` VALUES ('261', '周珊如', 'e10adc3949ba59abbe56e057f20f883e', null, '18126082361', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/43/Chim91o4a6KAT-0LAAFFfoZq_p8617.jpg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');
INSERT INTO `sys_user` VALUES ('262', '陈有为', 'e10adc3949ba59abbe56e057f20f883e', null, '15818614639', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/D8/CkJd1FqWGtiALU1RAABkiKQMOGw037.jpg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');
INSERT INTO `sys_user` VALUES ('263', '罗深珍', 'e10adc3949ba59abbe56e057f20f883e', null, '18316996968', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/F1/CkJd1FrDJLCATmyoAASAGvJ55Ms113.jpg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');
INSERT INTO `sys_user` VALUES ('264', '范文展', 'e10adc3949ba59abbe56e057f20f883e', null, '13760394055', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/F8/CkJd1FrVsXqAAqdPAAE3Z4V5NJU392.jpg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');
INSERT INTO `sys_user` VALUES ('265', '任婷婷', 'ac9898e898b0be19ff47e35bcbde3e4f', null, '1340000000', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/DF/CkJd1FqhEayABFRxAAAistupmzI915.jpg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');
INSERT INTO `sys_user` VALUES ('266', '曹珺', 'e10adc3949ba59abbe56e057f20f883e', null, '13728680260', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/00/0A/tlzDm1bC1jaAL1wcAABFy1VHRcw17.jpeg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');
INSERT INTO `sys_user` VALUES ('267', '李梦琳', 'e10adc3949ba59abbe56e057f20f883e', null, '13927498294', 'http://ccb.ujia007.com/heroes/api/v1/image/group1/M00/01/12/CkJd1FsCLlmASZ_5AADipIrjAb414.jpeg', null, '2018-07-23 16:40:26', '2018-07-23 16:40:26', '0');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('4', '14');
INSERT INTO `sys_user_role` VALUES ('5', '2');
INSERT INTO `sys_user_role` VALUES ('200', '2');
INSERT INTO `sys_user_role` VALUES ('201', '2');
INSERT INTO `sys_user_role` VALUES ('202', '2');
INSERT INTO `sys_user_role` VALUES ('203', '2');
INSERT INTO `sys_user_role` VALUES ('204', '2');
INSERT INTO `sys_user_role` VALUES ('205', '2');
INSERT INTO `sys_user_role` VALUES ('206', '2');
INSERT INTO `sys_user_role` VALUES ('207', '2');
INSERT INTO `sys_user_role` VALUES ('208', '2');
INSERT INTO `sys_user_role` VALUES ('209', '2');
INSERT INTO `sys_user_role` VALUES ('210', '2');
INSERT INTO `sys_user_role` VALUES ('211', '2');
INSERT INTO `sys_user_role` VALUES ('212', '2');
INSERT INTO `sys_user_role` VALUES ('213', '2');
INSERT INTO `sys_user_role` VALUES ('214', '2');
INSERT INTO `sys_user_role` VALUES ('215', '2');
INSERT INTO `sys_user_role` VALUES ('216', '2');
INSERT INTO `sys_user_role` VALUES ('217', '2');
INSERT INTO `sys_user_role` VALUES ('218', '2');
INSERT INTO `sys_user_role` VALUES ('219', '2');
INSERT INTO `sys_user_role` VALUES ('220', '2');
INSERT INTO `sys_user_role` VALUES ('221', '2');
INSERT INTO `sys_user_role` VALUES ('222', '2');
INSERT INTO `sys_user_role` VALUES ('223', '2');
INSERT INTO `sys_user_role` VALUES ('224', '2');
INSERT INTO `sys_user_role` VALUES ('225', '2');
INSERT INTO `sys_user_role` VALUES ('226', '2');
INSERT INTO `sys_user_role` VALUES ('227', '2');
INSERT INTO `sys_user_role` VALUES ('228', '2');
INSERT INTO `sys_user_role` VALUES ('229', '2');
INSERT INTO `sys_user_role` VALUES ('230', '2');
INSERT INTO `sys_user_role` VALUES ('231', '2');
INSERT INTO `sys_user_role` VALUES ('232', '2');
INSERT INTO `sys_user_role` VALUES ('233', '2');
INSERT INTO `sys_user_role` VALUES ('234', '2');
INSERT INTO `sys_user_role` VALUES ('235', '2');
INSERT INTO `sys_user_role` VALUES ('236', '2');
INSERT INTO `sys_user_role` VALUES ('237', '2');
INSERT INTO `sys_user_role` VALUES ('238', '2');
INSERT INTO `sys_user_role` VALUES ('239', '2');
INSERT INTO `sys_user_role` VALUES ('240', '2');
INSERT INTO `sys_user_role` VALUES ('241', '2');
INSERT INTO `sys_user_role` VALUES ('242', '2');
INSERT INTO `sys_user_role` VALUES ('243', '2');
INSERT INTO `sys_user_role` VALUES ('244', '2');
INSERT INTO `sys_user_role` VALUES ('245', '2');
INSERT INTO `sys_user_role` VALUES ('246', '2');
INSERT INTO `sys_user_role` VALUES ('247', '2');
INSERT INTO `sys_user_role` VALUES ('248', '2');
INSERT INTO `sys_user_role` VALUES ('249', '2');
INSERT INTO `sys_user_role` VALUES ('250', '2');
INSERT INTO `sys_user_role` VALUES ('251', '2');
INSERT INTO `sys_user_role` VALUES ('252', '2');
INSERT INTO `sys_user_role` VALUES ('253', '2');
INSERT INTO `sys_user_role` VALUES ('254', '2');
INSERT INTO `sys_user_role` VALUES ('255', '2');
INSERT INTO `sys_user_role` VALUES ('256', '2');
INSERT INTO `sys_user_role` VALUES ('257', '2');
INSERT INTO `sys_user_role` VALUES ('258', '2');
INSERT INTO `sys_user_role` VALUES ('259', '2');
INSERT INTO `sys_user_role` VALUES ('260', '2');
INSERT INTO `sys_user_role` VALUES ('261', '2');
INSERT INTO `sys_user_role` VALUES ('262', '2');
INSERT INTO `sys_user_role` VALUES ('263', '2');
INSERT INTO `sys_user_role` VALUES ('264', '2');
INSERT INTO `sys_user_role` VALUES ('265', '2');
INSERT INTO `sys_user_role` VALUES ('266', '2');
INSERT INTO `sys_user_role` VALUES ('267', '2');

-- ----------------------------
-- Table structure for `sys_userconnection`
-- ----------------------------
DROP TABLE IF EXISTS `sys_userconnection`;
CREATE TABLE `sys_userconnection` (
  `userid` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `providerid` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `provideruserid` varchar(255) CHARACTER SET utf8 NOT NULL,
  `rank` int(11) DEFAULT NULL,
  `displayname` varchar(255) DEFAULT NULL,
  `profileurl` varchar(512) CHARACTER SET utf8 DEFAULT NULL,
  `imageurl` varchar(512) CHARACTER SET utf8 DEFAULT NULL,
  `accesstoken` varchar(512) CHARACTER SET utf8 DEFAULT NULL,
  `secret` varchar(512) CHARACTER SET utf8 DEFAULT NULL,
  `refreshtoken` varchar(512) CHARACTER SET utf8 DEFAULT NULL,
  `expiretime` bigint(20) DEFAULT NULL,
  `unionid` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '微信公众平台ID',
  PRIMARY KEY (`provideruserid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_userconnection
-- ----------------------------
INSERT INTO `sys_userconnection` VALUES ('812', 'weixin', '023074f5-13ad-4a05-9cff-557fe791d958', '0', '梓枫', 'http://wx.qlogo.cn/mmopen/jlabptXtjrLhfxyhK7kfyxy9tYIRjgnhKDe5dcV2iak2SYwhhA45KviaxjKRk6jEFJibetZCyyPaBjcNBTmlUQS0WHJc8lBF6e3/0', null, null, null, null, null, 'ozsWOwvLinifdXkDtRxwwnQqrL0c');
INSERT INTO `sys_userconnection` VALUES ('801', 'weixin', '0f19131a-7770-4209-abb6-bf4fd267fe53', '0', '小LiLy', 'http://thirdwx.qlogo.cn/mmopen/Js2xbtH9KmSGlcfRtiaxJnuia0vwxW5YtNCIZyCyuicI0wlCjcyy5D8R3YSnTVctgdmqYyKgMqqx6OflpqLTgDY55Ml16DeG4h2/132', null, null, null, null, null, 'o397i0a-5egmqrXwUQgskggLvkGQ');
INSERT INTO `sys_userconnection` VALUES ('808', 'weixin', '22570610-a486-43c6-8948-c0b492a5015a', '0', '莳茪华菽', 'http://thirdwx.qlogo.cn/mmopen/zhULiaUbyj49elSX5au1kgIdpwrcsEQSXzF9MRK0mibr9BE5NJWKrrB8nUENUys5iaRXic5KPKrWxA5vZS0Y1iaXAibwJwUOOa8mmA/132', null, null, null, null, null, 'o397i0a-5SLLdiBnMsE6UP1XfxQo');
INSERT INTO `sys_userconnection` VALUES ('799', 'weixin', '318565b7-c8cb-4def-8733-ffb3c788e9c4', '0', '老虎', 'http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM7Ss1nfBHIUCfZu91jhMOgjfoX4XSfTIMG5Tx4MAIbHtNazPycHg7tpnfFG1KibVicHHfQvs1iaibg8glicKqnLyr68iatYzm43XpwBo/132', null, null, null, null, null, 'o397i0a-0Gpgr0WwIo0_eJx0mHyI');
INSERT INTO `sys_userconnection` VALUES ('807', 'weixin', '363fefc7-41df-4e96-8614-12145cd3fa8d', '0', '五花茶', 'http://wx.qlogo.cn/mmopen/uQt8NJBmtRd0iaKms728SJqzJHbB4jBmwRgwI97M9Ko9NQO1mwZpJjKrnibUVdnj2oEsozcMvsNrxqgIpWSHgaIjvpqFWdCk7h/0', null, null, null, null, null, 'o397i0a-4KJqPyxbKZy3CycsBvnc');
INSERT INTO `sys_userconnection` VALUES ('815', 'weixin', '609de27e-53cc-4e28-b03f-155317dead96', '0', 'cassy 冯', 'http://wx.qlogo.cn/mmopen/V8WJCGfb9k1WpaP1ZpXqic6GblwcyGcOicRH0HtEFkkhpTWIkeGYhH5EA6xics7BNKCRHQg5ZAh8Esy3g2gX6OIJLf3wl0zRUyb/0', null, null, null, null, null, 'ozsWOwthR41r4cPsP0Vm4UZAiY6A');
INSERT INTO `sys_userconnection` VALUES ('814', 'weixin', '673f6b59-f823-4772-b866-6fb02ae1ffa4', '0', '周', 'http://wx.qlogo.cn/mmopen/V8WJCGfb9k3fJmHgiaS0qWwo95VKsSic4NBQia9mtjhWzibTn6uAoiaSwLOdN9T8MMZCLsa50DYsevBWiaN4MaoDgSuWUlcZbERRD0/0', null, null, null, null, null, 'ozsWOwv-QnT5WmoH4REFQxYSf3h4');
INSERT INTO `sys_userconnection` VALUES ('800', 'weixin', '9cd4d4b3-6bf0-4a05-b8b8-9b13f1404867', '0', 'A罗璇子～免费领刷卡机银行房车贷', 'http://thirdwx.qlogo.cn/mmopen/6iangeIPHQ6Vo48EOHGbyM0jRnvWk0215AfhJykxxG3LAxfx0XGZ4y0xphFUhtFoyrblmKrup6Fqz7nXqE9xOlIbT0gfJ1KcG/132', null, null, null, null, null, 'o397i0a-0bNUdIFKMj1820lyRDKY');
INSERT INTO `sys_userconnection` VALUES ('810', 'weixin', 'a7764564-10d5-4b11-bef9-c6b6f50e5236', '0', '浮夸', 'http://thirdwx.qlogo.cn/mmopen/DQ6q4U1jHoctYmW2IlN31MZZNic1k7ZdL8mSgcX3zE4oHpDkWMESxZcV2OaicRwfOb9OsVKxasBbIzsmIbXEIQCPjsr22artrn/132', null, null, null, null, null, 'o397i0a-9xaBXncIqBVMMMJR6Xeo');
INSERT INTO `sys_userconnection` VALUES ('809', 'weixin', 'a80b430c-06dc-447b-9fb2-ec66673b632c', '0', 'CHENLANG.', 'http://thirdwx.qlogo.cn/mmopen/VNWXLjTZiaSG46e8bgEotVl6RF7Wq0Tzicx7anr4Sv1RF5tygicWnfGEpd0XEFoVsCfFiamlD9YeOa9ZvyvKbJy3vRU3EQ9bu9TS/132', null, null, null, null, null, 'o397i0a-7eqpmMxcyqtUgNkSoud4');
INSERT INTO `sys_userconnection` VALUES ('804', 'weixin', 'b3f5d879-5768-4ce7-a63e-a5d29150fd0f', '0', '梨名', 'http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaEKfZdEyUPWXgoZlo4EgBJoBjg9AIFweODm5NK8pyIPeyDyAhYuhUI63vYibVomWQdoib4HYpiagx47uA/132', null, null, null, null, null, 'o397i0a--m57Tytm6xkKsdLBMFSs');
INSERT INTO `sys_userconnection` VALUES ('813', 'weixin', 'b67fe9b3-fe64-4b20-8094-595977f4ae27', '0', '何承轩', 'http://wx.qlogo.cn/mmopen/8icqwjXialuH8KGUdGED3xlnWbfwVz1QOm3zmZBZ6ECE8MOzKTnxkR60icfdUoWs6OiaF87dunSsfFXK9bMqXO7Bc9j3riabmc2yz/0', null, null, null, null, null, 'ozsWOwvHaQarU_BNfa9u8fYxphVo');
INSERT INTO `sys_userconnection` VALUES ('645', 'weixin', 'c23d6055-9013-4700-ac01-11ec1ddc62d9', '0', '小英', 'http://thirdwx.qlogo.cn/mmopen/tCBAzlbichDrQAyZF5C6ODpYf4dv9KibknLAvEOSKaGliaVlJWiaSS88pzcxpAHK242KjBNhPL4jUU4xdWy4icotWyrdONBLm4Wric/132', null, null, null, null, null, 'o397i0QODEDxR4ODHmrrLAXzmFsM');
INSERT INTO `sys_userconnection` VALUES ('811', 'weixin', 'cc95e95d-42cd-4ff2-a16d-e1e32b733dce', '0', '艾妮斯高端美容?美甲?會所', 'http://thirdwx.qlogo.cn/mmopen/9GgqFGhDfiabmic6ImLWfwhqxpib9HAhBjNgW4eRFWxj0j2rnDKVxYwL4c9CicTwpbK2vb0Tib6pO6zyRgQXlQm0hVMOuHjicbHwfG/132', null, null, null, null, null, 'o397i0a-AjSYFkRDZ-egzMiPD-38');
INSERT INTO `sys_userconnection` VALUES ('53', 'weixin', 'oTJR-wsfb6BGhNoAtZ9LyZgiia6b', '0', '任婷婷:two_hearts:', 'http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46', null, null, null, null, null, 'o6_bmasdasdsad6_2sgVt7hMZOPfi');
INSERT INTO `sys_userconnection` VALUES ('1', 'weixin', 'oTJR-wsfb6BGhNoAtZ9LyZgiia6k', '0', null, 'http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/47', null, null, null, null, null, 'oTJR-wsfb6BGhNoAtZ9LyZgiia6k');
INSERT INTO `sys_userconnection` VALUES ('816', 'weixin', 'oux9Ws0JVI1AezDJsE7CZZtQa870', '0', 'Icetea', 'http://thirdwx.qlogo.cn/mmopen/vi_32/icDsjAJad9IbBSgSAvZyfcxjB3pvic1eibITzIa1VJzicWic5PjKH6jHt4MCxns7MxWXTUQbp7QfMh7VYqoSXHKnj2w/132', null, null, null, null, null, 'o5rItwJyEaxSaX968qrE3lo097o8');
INSERT INTO `sys_userconnection` VALUES ('73', 'weixin', 'oux9Ws0zcXsBv3sQjYCIhjS6PPEk', '0', '追明.焕崭?', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoUFrq7bWpDQ4lKoiacflcoyCoa2zuhOiaWr5XqkgXGgWpbJuhucCJre9E66IIPoIaib3XZ5ibEQ860tA/132', null, null, null, null, null, 'o5rItwNLiPTDTnlJ-9sq1xsaf_Ns');
INSERT INTO `sys_userconnection` VALUES ('57', 'weixin', 'oux9Ws105ItIv2Q5NPRgmpL_CXn0', '0', 'Null is Empty', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKia1YEyrljcIKG07zF4tbLXiaCcCMibTLCGbqjxslX0TpPicCziaMen03egic61Gkqribia5errTcfG7y89g/132', null, null, null, null, null, 'o5rItwP0RdKowXwpoKQXWNMWc9f8');
INSERT INTO `sys_userconnection` VALUES ('72', 'weixin', 'oux9Ws1BCXjM331J7aAdaZVt1oik', '0', '虫子', 'http://thirdwx.qlogo.cn/mmopen/vi_32/ATTJC03q4BZKyXxz5Zl3feWh4FkvKMTTLAGcZ2PsHsDtW6gib0rhLycicbMJp5SF6v3GLUjGIKjpgJZRMd9yOFQw/132', null, null, null, null, null, 'o5rItwLfC9iIQt1nGs0BkDSGpcno');
INSERT INTO `sys_userconnection` VALUES ('56', 'weixin', 'oux9Ws2a8SaIdqdx1oqe6ldT-XsE', '0', 'Him郑荣金', 'http://thirdwx.qlogo.cn/mmopen/vi_32/k1TQGao0fjFJGlNOY1fM0dpGdRjfkevxxs9y5YmicuyMLLLewVDDsaicN3SOFYWPnNsESs9qECGibrYJgdc00BN7g/132', null, null, null, null, null, 'o5rItwEeoGN0WpVDoxnoCprrCh6w');
INSERT INTO `sys_userconnection` VALUES ('52', 'weixin', 'oux9Ws5e9RZtrawHDVnRN8s3Iw5c', '0', '火', 'http://thirdwx.qlogo.cn/mmopen/vi_32/ZtNibnN59kGI3uicee4iazQZhSDz1Vy05Ft0cgABDjIOicJmEFXcz9aZ42OJKFV6THumwib7u8HHTxEia1GPTdaYEs3w/132', null, null, null, null, null, 'o5rItwK55RZ37zVrDCYeixH8u_sQ');
INSERT INTO `sys_userconnection` VALUES ('817', 'weixin', 'oux9Ws9ugLxES8a-0dShN8-wqqfc', '0', '愿你我都实现彼此的马斯洛', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLicJG2WGsVFc1m8odVUnztqskQfZzicra6LOI6bKx3icibcMkV0QbaiaNsetF9ZfwM8PKkc9Sjzo2boEQ/132', null, null, null, null, null, 'o5rItwEcI8bWqUU8AG7xEhHEAGaU');

-- ----------------------------
-- Table structure for `sys_zuul_route`
-- ----------------------------
DROP TABLE IF EXISTS `sys_zuul_route`;
CREATE TABLE `sys_zuul_route` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'router Id',
  `path` varchar(255) NOT NULL COMMENT '路由路径',
  `service_id` varchar(255) NOT NULL COMMENT '服务名称',
  `url` varchar(255) DEFAULT NULL COMMENT 'url代理',
  `strip_prefix` char(1) DEFAULT '1' COMMENT '转发去掉前缀',
  `retryable` char(1) DEFAULT '1' COMMENT '是否重试',
  `enabled` char(1) DEFAULT '1' COMMENT '是否启用',
  `sensitiveHeaders_list` varchar(255) DEFAULT NULL COMMENT '敏感请求头',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='动态路由配置表';

-- ----------------------------
-- Records of sys_zuul_route
-- ----------------------------
INSERT INTO `sys_zuul_route` VALUES ('1', 'test', 'test', 'tsest', '1', '1', '1', '0', '2018-05-16 07:28:43', '2018-05-16 07:35:08', '1');
INSERT INTO `sys_zuul_route` VALUES ('2', 'sdfg', 'we', 'jjj', '1', '1', '1', 'jj', '2018-05-16 07:35:43', '2018-05-17 13:56:14', '1');
INSERT INTO `sys_zuul_route` VALUES ('3', '/demo/**', 'pig-demo-service', '', '1', '1', '1', '', '2018-05-17 14:09:06', '2018-05-17 14:32:36', '0');
INSERT INTO `sys_zuul_route` VALUES ('4', '/admin/**', 'pig-upms-service', '', '1', '1', '1', '', '2018-05-21 11:40:38', null, '0');
INSERT INTO `sys_zuul_route` VALUES ('5', '/auth/**', 'pig-auth', '', '1', '1', '1', '', '2018-05-21 11:41:08', null, '0');
