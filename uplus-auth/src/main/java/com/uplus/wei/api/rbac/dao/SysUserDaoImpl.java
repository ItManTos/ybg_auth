package com.uplus.wei.api.rbac.dao;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

import com.uplus.wei.api.rbac.dto.Query;

/**
 * @author yanyu
 *
 */
public class SysUserDaoImpl {

    public String selectUserVoPage(
            @Param("offset") int offset, @Param("limit") int limit,
            @Param("username") String username) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT `user`.user_id, `user`.username, `user`.`password`, `user`.salt, `user`.phone, `user`.avatar, `user`.create_time AS ucreate_time, `user`.update_time AS uupdate_time, `user`.del_flag AS delFlag, r.role_id, r.role_name, r.role_code, r.role_desc, r.create_time AS rcreate_time, r.update_time AS rupdate_time FROM sys_user AS `user` LEFT JOIN sys_user_role AS ur ON ur.user_id = `user`.user_id LEFT JOIN sys_role AS r ON r.role_id = ur.role_id and r.del_flag = 0 ");
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotEmpty(username)) {
            sql.append(" and `user`.username LIKE CONCAT('%',#{username},'%') ");
        }

        sql.append(" ORDER BY `user`.create_time DESC ");
        PagerUtils.limit(sql.toString(), JdbcConstants.MYSQL,offset,limit);
        return sql.toString();
    }
    public String countUserVoPage(

            @Param("username") String username) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT `user`.user_id, `user`.username, `user`.`password`, `user`.salt, `user`.phone, `user`.avatar, `user`.create_time AS ucreate_time, `user`.update_time AS uupdate_time, `user`.del_flag AS udel_flag, r.role_id, r.role_name, r.role_code, r.role_desc, r.create_time AS rcreate_time, r.update_time AS rupdate_time FROM sys_user AS `user` LEFT JOIN sys_user_role AS ur ON ur.user_id = `user`.user_id LEFT JOIN sys_role AS r ON r.role_id = ur.role_id and r.del_flag = 0 ");
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotEmpty(username)) {
            sql.append(" and `user`.username LIKE CONCAT('%',#{username},'%') ");
        }

        sql.append(" ORDER BY `user`.create_time DESC ");
        PagerUtils.count(sql.toString(), JdbcConstants.MYSQL);
        return sql.toString();
    }

}
