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

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uplus.wei.api.rbac.entity.SysMenu;
import com.uplus.wei.api.rbac.vo.MenuVO;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 通过角色编号查询菜单
	 *
	 * @param role 角色编号
	 * @return
	 */
	@Select("SELECT sys_menu.* FROM sys_role LEFT JOIN sys_role_menu ON sys_role_menu.role_id = sys_role.role_id LEFT JOIN sys_menu ON sys_menu.menu_id = sys_role_menu.menu_id WHERE (sys_role.del_flag = 0 AND sys_menu.del_flag = 0 AND sys_role.role_code = #{role}) ORDER BY sys_menu.sort DESC")
	List<MenuVO> findMenuByRoleCode(String role);

	/**
	 * 通过角色ID查询权限
	 *
	 * @param roleIds Ids
	 * @return
	 */
	@Select("SELECT m.permission FROM sys_menu m, sys_role_menu rm WHERE (m.menu_id = rm.menu_id AND m.del_flag = 0 AND rm.role_id IN (#{roleIds}))")
	List<String> findPermissionsByRoleIds(String roleIds);
}
