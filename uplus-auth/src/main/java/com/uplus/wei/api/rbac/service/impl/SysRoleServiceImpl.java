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

package com.uplus.wei.api.rbac.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uplus.wei.api.rbac.dao.SysRoleMapper;
import com.uplus.wei.api.rbac.dto.RoleDTO;
import com.uplus.wei.api.rbac.entity.SysRole;
import com.uplus.wei.api.rbac.service.SysRoleService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public List<SysRole> findRolesByUserId(Integer userId) {
		return sysRoleMapper.findRolesByUserId(userId);
	}

	/**
	 * 添加角色
	 *
	 * @param roleDto 角色信息
	 * @return 成功、失败
	 */
	@Override
	public Boolean insertRole(RoleDTO roleDto) {
		SysRole sysRole = new SysRole();
		BeanUtils.copyProperties(roleDto, sysRole);
		sysRoleMapper.insert(sysRole);
		return true;
	}

	/**
	 * 更新角色
	 *
	 * @param roleDto 含有部门信息
	 * @return 成功、失败
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean updateRoleById(RoleDTO roleDto) {

		// 更新角色信息
		SysRole sysRole = new SysRole();
		BeanUtils.copyProperties(roleDto, sysRole);
		sysRoleMapper.updateById(sysRole);
		return true;
	}
}
