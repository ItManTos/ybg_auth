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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uplus.wei.api.rbac.dto.UserDTO;
import com.uplus.wei.api.rbac.dto.UserInfo;
import com.uplus.wei.api.rbac.entity.SysRole;
import com.uplus.wei.api.rbac.entity.SysUser;
import com.uplus.wei.api.rbac.entity.SysUserRole;
import com.uplus.wei.api.rbac.mapper.SysUserMapper;
import com.uplus.wei.api.rbac.service.SysMenuService;
import com.uplus.wei.api.rbac.service.SysRoleService;
import com.uplus.wei.api.rbac.service.SysUserRoleService;
import com.uplus.wei.api.rbac.service.SysUserService;
import com.uplus.wei.api.rbac.vo.MenuVO;
import com.uplus.wei.api.rbac.vo.UserVO;
import com.uplus.wei.util.Query;
import com.uplus.wei.util.R;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lengleng
 * @date 2017/10/31
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	/**
	 * 删除用户
	 *
	 * @param sysUser 用户
	 * @return Boolean
	 */
	@Override
	@CacheEvict(value = "user_details", key = "#sysUser.username")
	public Boolean deleteUserById(SysUser sysUser) {
		sysUserRoleService.deleteByUserId(sysUser.getUserId());
		removeById(sysUser.getUserId());
		return Boolean.TRUE;
	}

	/**
	 * 通过用户名查用户的全部信息
	 *
	 * @param username 用户名
	 * @return
	 */
	@Override
	@Cacheable(value = "user_details", key = "#username")
	public UserInfo findUserInfo(String username) {
		SysUser condition = new SysUser();
		condition.setUsername(username);
		SysUser sysUser = getOne(new QueryWrapper<>(condition));
		if (sysUser == null) {
			return null;
		}

		UserInfo userInfo = new UserInfo();
		userInfo.setSysUser(sysUser);
		// 设置角色列表
		List<SysRole> roleList = sysRoleService.findRolesByUserId(sysUser.getUserId());
		List<String> roleCodes = new ArrayList<>();
		if (CollUtil.isNotEmpty(roleList)) {
			roleList.forEach(new Consumer<SysRole>() {
				@Override
				public void accept(SysRole sysRole) {
					roleCodes.add(sysRole.getRoleCode());
				}
			});
		}
		userInfo.setRoles(ArrayUtil.toArray(roleCodes, String.class));

		// 设置权限列表（menu.permission）
		Set<MenuVO> menuVoSet = new HashSet<>();
		for (String role : roleCodes) {
			List<MenuVO> menuVos = sysMenuService.findMenuByRoleCode(role);
			menuVoSet.addAll(menuVos);
		}
		Set<String> permissions = new HashSet<>();
		for (MenuVO menuVo : menuVoSet) {
			if (StringUtils.isNotEmpty(menuVo.getPermission())) {
				String permission = menuVo.getPermission();
				permissions.add(permission);
			}
		}
		userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
		return userInfo;
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	@Override
	public UserVO selectUserVoById(Integer id) {
		return sysUserMapper.selectUserVoById(id);
	}

	@Override
	public Page selectWithRolePage(Query query) {
		Object username = query.getCondition().get("username");
		query.setRecords(sysUserMapper.selectUserVoPage(query, username));
		return query;
	}

	@Override
	@CacheEvict(value = "user_details", key = "#username")
	public Boolean updateUser(UserDTO userDto, String username) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setUpdateTime(new Date());
		updateById(sysUser);

		SysUserRole condition = new SysUserRole();
		condition.setUserId(userDto.getUserId());
		sysUserRoleService.remove(new QueryWrapper<>(condition));
		userDto.getRole().forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer roleId) {
				SysUserRole userRole = new SysUserRole();
				userRole.setUserId(sysUser.getUserId());
				userRole.setRoleId(roleId);
				userRole.insert();
			}
		});
		return Boolean.TRUE;
	}

	@Override
	@CacheEvict(value = "user_details", key = "#username")
	public R<Boolean> updateUserInfo(UserDTO userDto, String username) {
		UserVO userVO = sysUserMapper.selectUserVoByUsername(username);
		SysUser sysUser = new SysUser();
		if (StrUtil.isNotBlank(userDto.getPassword()) && StrUtil.isNotBlank(userDto.getNewpassword1())) {
			if (ENCODER.matches(userDto.getPassword(), userVO.getPassword())) {
				sysUser.setPassword(ENCODER.encode(userDto.getNewpassword1()));
			} else {
				log.warn("原密码错误，修改密码失败:{}", username);
				return new R<>(Boolean.FALSE, "原密码错误，修改失败");
			}
		}
		sysUser.setPhone(userDto.getPhone());
		sysUser.setUserId(userVO.getUserId());
		sysUser.setAvatar(userDto.getAvatar());
		return new R<>(updateById(sysUser));
	}
}
