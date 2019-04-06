package com.uplus.wei.api.weixin.dao.sql;

import org.apache.ibatis.annotations.Param;

public class WxUserTagSql {
	/**
	 * 根据appid创建分表
	 *
	 * @param appid
	 * @return
	 */
	public String createTable(@Param("appid") String appid) {
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS `wx_user_tag_").append(appid).append("` ( ");
		sql.append("  `app_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '公众号应用编码', ");
		sql.append("  `open_id` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '用户公众号ID', ");
		sql.append("  `tag_id` bigint(20) DEFAULT NULL COMMENT '标签ID', ");
		sql.append("  `create_time` datetime DEFAULT NULL COMMENT '创建时间', ");
		sql.append("  `modified_time` datetime DEFAULT NULL COMMENT '修改时间', ");
		sql.append("  `tag_name` varchar(255) DEFAULT NULL COMMENT '标签名称（冗余查询）', ");
		sql.append("  PRIMARY KEY (`open_id`), ");
		sql.append("  KEY `tag_id` (`tag_id`), ");
		sql.append("  KEY `open_id` (`open_id`) ");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");
		return sql.toString();
	}

}
