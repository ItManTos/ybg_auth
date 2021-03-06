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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uplus.wei.api.rbac.entity.OauthClientDetails;
import com.uplus.wei.util.Query;
import com.uplus.wei.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lengleng
 * @since 2018-05-15
 */
@RestController
@RequestMapping("/client")
public class OauthClientDetailsController {
	@Autowired
	private com.uplus.wei.api.rbac.service.OauthClientDetailsService OauthClientDetailsService;

	/**
	 * 添加
	 *
	 * @param OauthClientDetails 实体
	 * @return success/false
	 */
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_client_add')")
	public R<Boolean> add(@RequestBody OauthClientDetails OauthClientDetails) {
		return new R<>(OauthClientDetailsService.save(OauthClientDetails));
	}

	/**
	 * 删除
	 *
	 * @param id ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_client_del')")
	public R<Boolean> delete(@PathVariable String id) {
		OauthClientDetails OauthClientDetails = new OauthClientDetails();
		OauthClientDetails.setClientId(id);
		return new R<>(OauthClientDetailsService.removeById(OauthClientDetails));
	}

	/**
	 * 编辑
	 *
	 * @param OauthClientDetails 实体
	 * @return success/false
	 */
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_client_edit')")
	public R<Boolean> edit(@RequestBody OauthClientDetails OauthClientDetails) {
		return new R<>(OauthClientDetailsService.updateById(OauthClientDetails));
	}

	/**
	 * 通过ID查询
	 *
	 * @param id ID
	 * @return OauthClientDetails
	 */
	@GetMapping("/{id}")
	public OauthClientDetails get(@PathVariable Integer id) {
		return OauthClientDetailsService.getById(id);
	}

	/**
	 * 分页查询信息
	 *
	 * @param params 分页对象
	 * @return 分页对象
	 */
	@RequestMapping("/page")
	public IPage page(@RequestParam Map<String, Object> params) {
		return OauthClientDetailsService.page(new Query<>(params), new QueryWrapper<>());
	}
}
