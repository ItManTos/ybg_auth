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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uplus.wei.api.rbac.dto.SecurityUser;
import com.uplus.wei.api.rbac.dto.UserInfo;
import com.uplus.wei.api.rbac.entity.SysUser;
import com.uplus.wei.api.rbac.service.SysUserService;
import com.uplus.wei.constant.CommonConstant;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 用户详细信息
 *
 * @author lengleng
 */
@Service
public class PigUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	SysUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// R<UserInfo> result = remoteUserService.info(username,
		// SecurityConstants.FROM_IN);
		UserInfo info = userService.findUserInfo(username);

		if (info == null) {
			throw new UsernameNotFoundException("用户不存在");
		}

		Set<String> dbAuthsSet = new HashSet<>();
		if (ArrayUtil.isNotEmpty(info.getRoles())) {
			// 获取角色
			Arrays.stream(info.getRoles()).forEach(new Consumer<String>() {
				@Override
				public void accept(String role) {
					dbAuthsSet.add(role);
				}
			});
			// 获取资源
			dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));
		}
		Collection<? extends GrantedAuthority> authorities = AuthorityUtils
				.createAuthorityList(dbAuthsSet.toArray(new String[0]));
		SysUser user = info.getSysUser();
		boolean enabled = StrUtil.equals(user.getDelFlag(), CommonConstant.STATUS_NORMAL);
		// 构造security用户
		return new SecurityUser(user.getUserId(), username, user.getPassword(), enabled, true, true, true, authorities);
	}
}
