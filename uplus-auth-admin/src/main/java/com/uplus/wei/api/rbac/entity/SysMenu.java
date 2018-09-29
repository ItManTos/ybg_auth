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

package com.uplus.wei.api.rbac.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author lengleng
 * @since 2017-11-08
 */
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

	private static final long serialVersionUID = 1L;

	/**
	 * VUE页面
	 */
	private String component;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;
	/**
	 * 0--正常 1--删除
	 */
	@TableLogic
	@TableField("del_flag")
	private String delFlag;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 菜单ID
	 */
	@TableId(value = "menu_id", type = IdType.INPUT)
	private Integer menuId;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 父菜单ID
	 */
	@TableField("parent_id")
	private Integer parentId;
	/**
	 * 前端URL
	 */
	private String path;
	/**
	 * 菜单权限标识
	 */
	private String permission;
	/**
	 * 排序值
	 */
	private Integer sort;
	/**
	 * 菜单类型 （0菜单 1按钮）
	 */
	private String type;
	/**
	 * 更新时间
	 */
	@TableField("update_time")
	private Date updateTime;

	public String getComponent() {
		return component;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public String getIcon() {
		return icon;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public String getName() {
		return name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public String getPath() {
		return path;
	}

	public String getPermission() {
		return permission;
	}

	public Integer getSort() {
		return sort;
	}

	public String getType() {
		return type;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.menuId;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "SysMenu{" + ", menuId=" + menuId + ", name=" + name + ", permission=" + permission + ", parentId="
				+ parentId + ", icon=" + icon + ", component=" + component + ", sort=" + sort + ", type=" + type
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", delFlag=" + delFlag + "}";
	}
}
