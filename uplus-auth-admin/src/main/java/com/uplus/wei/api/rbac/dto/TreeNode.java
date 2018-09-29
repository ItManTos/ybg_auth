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

import java.util.ArrayList;
import java.util.List;

/**
 * @author lengleng
 * @date 2017年11月9日23:33:45
 */
public class TreeNode {
	protected List<TreeNode> children = new ArrayList<TreeNode>();
	protected int id;
	protected int parentId;

	public void add(TreeNode node) {
		children.add(node);
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public int getId() {
		return id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
