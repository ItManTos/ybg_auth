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
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author lengleng
 * @since 2017-11-08
 */
@Data
public class MenuVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * VUE页面
	 */
	private String component;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 0--正常 1--删除
	 */
	private String delFlag;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 菜单ID
	 */
	private Integer menuId;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 父菜单ID
	 */
	private Integer parentId;
	/**
	 * 一个路径
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
	private Date updateTime;

	/**
	 * menuId 相同则相同
	 *
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MenuVO) {
			Integer targetMenuId = ((MenuVO) obj).getMenuId();
			return menuId.equals(targetMenuId);
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return menuId.hashCode();
	}
}
