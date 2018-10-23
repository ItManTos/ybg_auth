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

package com.uplus.wei.api.rbac.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uplus.wei.api.rbac.dto.Query;
import com.uplus.wei.api.rbac.dto.UserDTO;
import com.uplus.wei.api.rbac.dto.UserInfo;
import com.uplus.wei.api.rbac.entity.SysUser;
import com.uplus.wei.api.rbac.vo.UserVO;

/**
 * @author lengleng
 * @date 2017/10/31
 */
public interface SysUserService extends IService<SysUser> {
	/**
	 * 删除用户
	 *
	 * @param sysUser 用户
	 * @return boolean
	 */
	Boolean deleteUserById(SysUser sysUser);

	/**
	 * 查询用户信息
	 *
	 * @param username 用户名
	 * @return userInfo
	 */
	UserInfo findUserInfo(String username);

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	UserVO selectUserVoById(Integer id);

	/**
	 * 分页查询用户信息（含有角色信息）
	 *
	 * @param query    查询条件
	 *
	 * @param userName
	 * @return
	 */
	Page<com.uplus.wei.api.rbac.vo.UserVO> selectWithRolePage(Query<com.uplus.wei.api.rbac.vo.UserVO> query,
			String userName);

	/**
	 * 更新指定用户信息
	 *
	 * @param userDto  用户信息
	 * @param username 用户信息
	 * @return
	 */
	Boolean updateUser(UserDTO userDto, String username);

	/**
	 * 更新当前用户基本信息
	 *
	 * @param userDto  用户信息
	 * @param username 用户名
	 * @return Boolean
	 */
	Boolean updateUserInfo(UserDTO userDto, String username);
}
