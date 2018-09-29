package com.uplus.wei.api.rbac.dao;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

import com.uplus.wei.api.rbac.dto.Query;

/**
 * @author yanyu
 *
 */
public class SysUserDaoImpl {

    public String selectUserVoPage(
            Query<?> query,
            @Param("username") String username) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT `user`.user_id, `user`.username, `user`.`password`, `user`.salt, `user`.phone, `user`.avatar, `user`.create_time AS ucreate_time, `user`.update_time AS uupdate_time, `user`.del_flag AS udel_flag, r.role_id, r.role_name, r.role_code, r.role_desc, r.create_time AS rcreate_time, r.update_time AS rupdate_time, FROM sys_user AS `user` LEFT JOIN sys_user_role AS ur ON ur.user_id = `user`.user_id LEFT JOIN sys_role AS r ON r.role_id = ur.role_id WHERE r.del_flag = 0 ");
        if (StringUtils.isNotEmpty(username)) {
            sql.append(" and `user`.username LIKE CONCAT('%',#{username},'%') ");
        }

        sql.append(" ORDER BY `user`.create_time DESC ");
        return sql.toString();
    }
}
