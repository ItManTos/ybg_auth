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

import com.uplus.wei.api.rbac.vo.MenuVO;

/**
 * @author lengleng
 * @date 2017年11月9日23:33:27
 */
public class MenuTree extends TreeNode {
	private String authority;
	private String code;
	private String component;
	private String icon;
	private String label;
	private String name;
	private String path;
	private String redirect;
	private Integer sort;
	private boolean spread = false;
	private String type;

	public MenuTree() {
	}

	public MenuTree(int id, String name, int parentId) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.label = name;
	}

	public MenuTree(int id, String name, MenuTree parent) {
		this.id = id;
		this.parentId = parent.getId();
		this.name = name;
		this.label = name;
	}

	public MenuTree(MenuVO menuVo) {
		this.id = menuVo.getMenuId();
		this.parentId = menuVo.getParentId();
		this.icon = menuVo.getIcon();
		this.name = menuVo.getName();
		this.path = menuVo.getPath();
		this.component = menuVo.getComponent();
		this.type = menuVo.getType();
		this.label = menuVo.getName();
		this.sort = menuVo.getSort();
	}

	public String getAuthority() {
		return authority;
	}

	public String getCode() {
		return code;
	}

	public String getComponent() {
		return component;
	}

	public String getIcon() {
		return icon;
	}

	public String getLabel() {
		return label;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public String getRedirect() {
		return redirect;
	}

	public Integer getSort() {
		return sort;
	}

	public String getType() {
		return type;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public void setType(String type) {
		this.type = type;
	}

}
