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

package com.uplus.wei.api.rbac.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.uplus.wei.api.rbac.entity.SysRole;

/**
 * @author lengleng
 * @date 2017/10/29
 */
public class UserVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 0-正常，1-删除
	 */
	private String delFlag;
	/**
	 * 部门ID
	 */
	private Integer deptId;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 简介
	 */
	private String phone;
	/**
	 * 角色列表
	 */
	private List<SysRole> roleList;
	/**
	 * 随机盐
	 */
	private String salt;

	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 主键ID
	 */
	private Integer userId;

	/**
	 * 用户名
	 */
	private String username;

	public String getAvatar() {
		return avatar;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public String getSalt() {
		return salt;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
