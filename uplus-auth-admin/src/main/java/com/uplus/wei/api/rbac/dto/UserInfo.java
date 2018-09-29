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

package com.uplus.wei.api.rbac.dto;

import java.io.Serializable;

import com.uplus.wei.api.rbac.entity.SysUser;

/**
 * @author lengleng
 * @date 2017/11/11
 *       <p>
 *       commit('SET_ROLES', data) commit('SET_NAME', data) commit('SET_AVATAR',
 *       data) commit('SET_INTRODUCTION', data) commit('SET_PERMISSIONS', data)
 */
public class UserInfo implements Serializable {
	/**
	 * 权限标识集合
	 */
	private String[] permissions;
	/**
	 * 角色集合
	 */
	private String[] roles;

	/**
	 * 用户基本信息
	 */
	private SysUser sysUser;

	public String[] getPermissions() {
		return permissions;
	}

	public String[] getRoles() {
		return roles;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
}
