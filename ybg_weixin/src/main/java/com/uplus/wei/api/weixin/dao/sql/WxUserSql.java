package com.uplus.wei.api.weixin.dao.sql;

import org.apache.ibatis.annotations.Param;

public class WxUserSql {
	/**
	 * 根据appid创建分表
	 *
	 * @param appid
	 * @return
	 */
	public String createTable(@Param("appid") String appid) {
		StringBuffer sql = new StringBuffer();
		sql.append(" CREATE TABLE IF NOT EXISTS `wx_user_");
		sql.append(appid);
		sql.append("` ( ");
		sql.append("  `app_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL, ");
		sql.append("  `subscribe` tinyint(4) DEFAULT NULL COMMENT '是否关注', ");
		sql.append("  `open_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '公众号ID', ");
		sql.append("  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称', ");
		sql.append("  `sex_desc` varchar(10) DEFAULT NULL COMMENT '性别描述', ");
		sql.append("  `sex` int(11) DEFAULT NULL COMMENT '性别', ");
		sql.append("  `language` varchar(60) DEFAULT NULL COMMENT '语言', ");
		sql.append("  `city` varchar(255) DEFAULT NULL COMMENT '城市', ");
		sql.append("  `province` varchar(255) DEFAULT NULL COMMENT '省份', ");
		sql.append("  `country` varchar(255) DEFAULT NULL COMMENT '国家', ");
		sql.append("  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像', ");
		sql.append("  `subscribe_time` decimal(10,0) DEFAULT NULL COMMENT '关注事件', ");
		sql.append("  `union_id` int(11) DEFAULT NULL COMMENT '开放平台ID', ");
		sql.append("  `remark` varchar(255) DEFAULT NULL COMMENT '备注', ");
		sql.append("  `group_id` int(11) DEFAULT NULL COMMENT '分组ID', ");
		sql.append("  `subscribe_scene` varchar(255) DEFAULT NULL COMMENT '返回用户关注的渠道来源', ");
		sql.append("  `qr_scene` varchar(255) DEFAULT NULL COMMENT '二维码扫码场景', ");
		sql.append("  `qr_scene_str` varchar(255) DEFAULT NULL COMMENT '二维码扫码场景描述', ");
		sql.append("  PRIMARY KEY (`open_id`) ");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';");
		return sql.toString();
	}

}
