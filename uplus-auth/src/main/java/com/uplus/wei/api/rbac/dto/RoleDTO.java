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

import com.uplus.wei.api.rbac.entity.SysRole;
import lombok.EqualsAndHashCode;

/**
 * @author lengleng
 * @date 2018/1/20
 * 角色Dto
 */

@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends SysRole {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2611113188328946162L;

	/**
	 * 角色部门Id
	 */
	private Integer roleDeptId;

	/**
	 * 部门名称
	 */
	private String deptName;

	public Integer getRoleDeptId() {
		return roleDeptId;
	}

	public void setRoleDeptId(Integer roleDeptId) {
		this.roleDeptId = roleDeptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
