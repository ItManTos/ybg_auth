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

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uplus.wei.api.rbac.dto.MenuTree;
import com.uplus.wei.api.rbac.entity.SysMenu;
import com.uplus.wei.api.rbac.service.SysMenuService;
import com.uplus.wei.api.rbac.vo.MenuVO;
import com.uplus.wei.api.rbac.vo.TreeUtil;
import com.uplus.wei.constant.CommonConstant;
import com.uplus.wei.constant.SecurityConstants;
import com.uplus.wei.util.R;
import com.uplus.wei.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author lengleng
 * @date 2017/10/31
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * 通过角色名称查询用户菜单
	 *
	 * @param role 角色名称
	 * @return 菜单列表
	 */
	@GetMapping("/findMenuByRole/{role}")
	public List<MenuVO> findMenuByRole(@PathVariable String role) {
		return sysMenuService.findMenuByRoleCode(role);
	}

	/**
	 * 返回树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/allTree")
	public List<MenuTree> getTree() {
		SysMenu condition = new SysMenu();
		condition.setDelFlag(CommonConstant.STATUS_NORMAL);
		return TreeUtil.bulidTree(sysMenuService.list(new QueryWrapper<>(condition)), -1);
	}

	/**
	 * 通过ID查询菜单的详细信息
	 *
	 * @param id 菜单ID
	 * @return 菜单详细信息
	 */
	@GetMapping("/{id}")
	public SysMenu menu(@PathVariable Integer id) {
		return sysMenuService.getById(id);
	}

	/**
	 * 新增菜单
	 *
	 * @param sysMenu 菜单信息
	 * @return success/false
	 */
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_menu_add')")
	public R<Boolean> menu(@RequestBody SysMenu sysMenu) {
		return new R<>(sysMenuService.save(sysMenu));
	}

	/**
	 * 删除菜单
	 *
	 * @param id 菜单ID
	 * @return success/false TODO 级联删除下级节点
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_menu_del')")
	public R<Boolean> menuDel(@PathVariable Integer id) {
		return new R<>(sysMenuService.deleteMenu(id));
	}

	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_menu_edit')")
	public R<Boolean> menuUpdate(@RequestBody SysMenu sysMenu) {
		return new R<>(sysMenuService.updateMenuById(sysMenu));
	}

	/**
	 * 返回角色的菜单集合
	 *
	 * @param roleName 角色名称
	 * @return 属性集合
	 */
	@GetMapping("/roleTree/{roleName}")
	public List<Integer> roleTree(@PathVariable String roleName) {
		List<MenuVO> menus = sysMenuService.findMenuByRoleCode(roleName);
		List<Integer> menuList = new ArrayList<>();
		for (MenuVO menuVo : menus) {
			menuList.add(menuVo.getMenuId());
		}
		return menuList;
	}

	/**
	 * 返回当前用户的树形菜单集合
	 *
	 * @return 当前用户的树形菜单
	 */
	@GetMapping(value = "/userMenu")
	public List<MenuTree> userMenu() {
		// 获取符合条件得菜单

		Set<MenuVO> all = new HashSet<>();
		SecurityUtils.getRoles().forEach(new Consumer<String>() {
			@Override
			public void accept(String roleName) {
				all.addAll(sysMenuService.findMenuByRoleCode(SecurityConstants.ROLE + roleName));
			}
		});
		List<MenuTree> menuTreeList = new ArrayList<>();
		all.forEach(new Consumer<MenuVO>() {
			@Override
			public void accept(MenuVO menuVo) {
				if (CommonConstant.MENU.equals(menuVo.getType())) {
					menuTreeList.add(new MenuTree(menuVo));
				}
			}
		});
		CollUtil.sort(menuTreeList, Comparator.comparingInt(MenuTree::getSort));
		return TreeUtil.bulid(menuTreeList, -1);
	}

}
