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

package com.uplus.wei.api.rbac.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.uplus.wei.api.rbac.entity.SysRole;
import com.uplus.wei.util.Query;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	@Select("SELECT r.* FROM sys_role r, sys_user_role ur WHERE (r.role_id = ur.role_id AND r.del_flag = 0 AND ur.user_id IN (#{userId}))")
	List<SysRole> findRolesByUserId(Integer userId);

	/**
	 * 查询角色列表含有部门信息
	 *
	 * @param query     查询对象
	 * @param condition 条件
	 * @return List
	 */
	@Select("SELECT * FROM sys_role r  WHERE r.del_flag = 0 ORDER BY r.role_id ASC ")
	List<com.uplus.wei.api.rbac.dto.RoleDTO> selectRolePage(Query<com.uplus.wei.api.rbac.dto.RoleDTO> query,
			Map<String, Object> condition);
}
