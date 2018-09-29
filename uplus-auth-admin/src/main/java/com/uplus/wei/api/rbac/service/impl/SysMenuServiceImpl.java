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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.uplus.wei.api.rbac.entity.SysMenu;
import com.uplus.wei.api.rbac.mapper.SysMenuMapper;
import com.uplus.wei.api.rbac.service.SysMenuService;
import com.uplus.wei.api.rbac.vo.MenuVO;
import com.uplus.wei.constant.CommonConstant;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	@CacheEvict(value = "menu_details", allEntries = true)
	public Boolean deleteMenu(Integer id) {
		// 删除当前节点
		SysMenu condition1 = new SysMenu();
		condition1.setMenuId(id);
		condition1.setDelFlag(CommonConstant.STATUS_DEL);
		updateById(condition1);

		// 删除父节点为当前节点的节点
		SysMenu conditon2 = new SysMenu();
		conditon2.setParentId(id);
		SysMenu sysMenu = new SysMenu();
		sysMenu.setDelFlag(CommonConstant.STATUS_DEL);
		return update(sysMenu, new EntityWrapper<>(conditon2));
	}

	@Override
	@Cacheable(value = "menu_details", key = "#role  + '_menu'")
	public List<MenuVO> findMenuByRoleCode(String role) {
		return sysMenuMapper.findMenuByRoleCode(role);
	}

	@Override
	@CacheEvict(value = "menu_details", allEntries = true)
	public Boolean updateMenuById(SysMenu sysMenu) {
		return updateById(sysMenu);
	}
}
