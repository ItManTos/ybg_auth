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

import java.util.List;

import com.uplus.wei.api.rbac.entity.SysUser;

/**
 * @author lengleng
 * @date 2017/11/5
 */
public class UserDTO extends SysUser {
	private Integer deptId;

	/**
	 * 新密码
	 */
	private String newpassword1;

	/**
	 * 角色ID
	 */
	private List<Integer> role;

	@Override
	public Integer getDeptId() {
		return deptId;
	}

	public String getNewpassword1() {
		return newpassword1;
	}

	public List<Integer> getRole() {
		return role;
	}

	@Override
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public void setNewpassword1(String newpassword1) {
		this.newpassword1 = newpassword1;
	}

	public void setRole(List<Integer> role) {
		this.role = role;
	}

}
