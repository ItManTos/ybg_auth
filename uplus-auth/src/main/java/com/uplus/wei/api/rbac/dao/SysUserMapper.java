/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package com.uplus.wei.api.rbac.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.uplus.wei.api.rbac.dto.Query;
import com.uplus.wei.api.rbac.entity.SysUser;
import com.uplus.wei.api.rbac.vo.UserVO;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 通过用户名查询用户信息（含有角色信息）
     *
     * @param username
     *            用户名
     * @return userVo
     */
    @Select(" SELECT `user`.user_id, `user`.username, `user`.`password`, `user`.salt, `user`.phone, `user`.avatar, `user`.dept_id, `user`.create_time AS ucreate_time, `user`.update_time AS uupdate_time, `user`.del_flag AS udel_flag, `user`.dept_id AS deptId, r.role_id, r.role_name, r.role_code, r.role_desc, r.create_time AS rcreate_time, r.update_time AS rupdate_time FROM sys_user AS `user` LEFT JOIN sys_user_role AS ur ON ur.user_id = `user`.user_id LEFT JOIN sys_role AS r ON r.role_id = ur.role_id WHERE `user`.username = #{username}")
    UserVO selectUserVoByUsername(
            @Param("username") String username);

    /**
     * 分页查询用户信息（含角色）
     *
     * @param query
     *            查询条件
     * @param username
     *            用户名
     * @return list
     */
    @SelectProvider(method = "selectUserVoPage", type = SysUserDaoImpl.class)
    List<com.uplus.wei.api.rbac.vo.UserVO> selectUserVoPage(
            Query<?> query,
            @Param("username") String username);

    /**
     * 通过ID查询用户信息
     *
     * @param id
     *            用户ID
     * @return userVo
     */
    @Select("SELECT `user`.user_id, `user`.username, `user`.`password`, `user`.salt, `user`.phone, `user`.avatar, `user`.create_time AS ucreate_time, `user`.update_time AS uupdate_time, `user`.del_flag AS udel_flag, r.role_id, r.role_name, r.role_code, r.role_desc, r.create_time AS rcreate_time, r.update_time AS rupdate_time, FROM sys_user AS `user` LEFT JOIN sys_user_role AS ur ON ur.user_id = `user`.user_id LEFT JOIN sys_role AS r ON r.role_id = ur.role_id WHERE `user`.user_id = #{id}")
    UserVO selectUserVoById(
            @Param("id") Integer id);

}
