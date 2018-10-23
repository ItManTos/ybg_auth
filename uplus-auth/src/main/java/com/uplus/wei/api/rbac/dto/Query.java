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

import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.util.StrUtil;

/**
 * @author lengleng
 * @date 2017/12/10
 */
public class Query<T> extends Page<T> {

	private static final String IS_ASC = "isAsc";
	private static final String LIMIT = "limit";
	private static final String ORDER_BY_FIELD = "orderByField";
	private static final String PAGE = "page";
	private static final long serialVersionUID = 3833267068113440737L;

	public Query(Map<String, Object> params) {
		super(Integer.parseInt(params.getOrDefault(PAGE, 1).toString()),
				Integer.parseInt(params.getOrDefault(LIMIT, 10).toString()));
		Boolean isAsc = Boolean.parseBoolean(params.getOrDefault(IS_ASC, Boolean.TRUE).toString());
		String orderByField = params.getOrDefault(ORDER_BY_FIELD, "").toString();
		if (StrUtil.isNotEmpty(orderByField)) {
			if (isAsc) {
				setAsc(orderByField);
			} else {
				setDesc(orderByField);
			}
		}

		params.remove(PAGE);
		params.remove(LIMIT);
		params.remove(ORDER_BY_FIELD);
		params.remove(IS_ASC);
		// this.setCondition(params);
	}
}
