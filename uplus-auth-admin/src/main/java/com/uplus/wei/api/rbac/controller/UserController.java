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

package com.uplus.wei.api.rbac.controller;

import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uplus.wei.api.rbac.dto.UserDTO;
import com.uplus.wei.api.rbac.dto.UserInfo;
import com.uplus.wei.api.rbac.entity.SysUser;
import com.uplus.wei.api.rbac.entity.SysUserRole;
import com.uplus.wei.api.rbac.service.SysUserService;
import com.uplus.wei.api.rbac.vo.UserVO;
import com.uplus.wei.constant.CommonConstant;
import com.uplus.wei.constant.SecurityConstants;
import com.uplus.wei.util.Query;
import com.uplus.wei.util.R;
import com.uplus.wei.util.SecurityUtils;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author lengleng
 * @date 2017/10/28
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
	@Autowired
	private SysUserService userService;

	/**
	 * 修改个人信息
	 *
	 * @param userDto userDto
	 * @return success/false
	 */
	@PutMapping("/editInfo")
	public R<Boolean> editInfo(@RequestBody UserDTO userDto) {
		return userService.updateUserInfo(userDto, SecurityUtils.getUser());
	}

	/**
	 * 通过ID查询当前用户信息
	 *
	 * @param id ID
	 * @return 用户信息
	 */
	@GetMapping("/{id}")
	public UserVO user(@PathVariable Integer id) {
		return userService.selectUserVoById(id);
	}

	/**
	 * 获取当前用户信息（角色、权限） 并且异步初始化用户部门信息
	 *
	 * @param from     请求标志，该接口会被 auth、 前端调用
	 * @param username 用户名
	 * @return 用户名
	 */
	@GetMapping(value = { "/info", "/info/{username}" })
	public R<UserInfo> user(@PathVariable(required = false) String username,
			@RequestHeader(required = false) String from) {
		// 查询用户不为空时判断是不是内部请求
		if (StrUtil.isNotBlank(username) && !StrUtil.equals(SecurityConstants.FROM_IN, from)) {
			return new R<>(null, "error");
		}
		// 为空时查询当前用户
		if (StrUtil.isBlank(username)) {
			username = SecurityUtils.getUser();
		}

		return new R<>(userService.findUserInfo(username));
	}

	/**
	 * 添加用户
	 *
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_user_add')")
	public R<Boolean> user(@RequestBody UserDTO userDto) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setDelFlag(CommonConstant.STATUS_NORMAL);
		sysUser.setPassword(ENCODER.encode(userDto.getNewpassword1()));
		userService.save(sysUser);

		userDto.getRole().forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer roleId) {
				SysUserRole userRole = new SysUserRole();
				userRole.setUserId(sysUser.getUserId());
				userRole.setRoleId(roleId);
				userRole.insert();
			}
		});
		return new R<>(Boolean.TRUE);
	}

	/**
	 * 删除用户信息
	 *
	 * @param id ID
	 * @return R
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_user_del')")
	@ApiOperation(value = "删除用户", notes = "根据ID删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path", example = "1000")
	public R<Boolean> userDel(@PathVariable Integer id) {
		SysUser sysUser = userService.getById(id);
		return new R<>(userService.deleteUserById(sysUser));
	}

	/**
	 * 分页查询用户
	 *
	 * @param params 参数集
	 * @return 用户集合
	 */
	@RequestMapping("/userPage")
	public IPage userPage(@RequestParam Map<String, Object> params) {
		return userService.selectWithRolePage(new Query(params));
	}

	/**
	 * 更新用户信息
	 *
	 * @param userDto 用户信息
	 * @return R
	 */
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	public R<Boolean> userUpdate(@RequestBody UserDTO userDto) {
		SysUser user = userService.getById(userDto.getUserId());
		return new R<>(userService.updateUser(userDto, user.getUsername()));
	}
}
