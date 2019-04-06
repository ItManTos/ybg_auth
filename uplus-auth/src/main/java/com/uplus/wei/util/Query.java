/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.uplus.wei.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lengleng
 * @date 2017/12/10
 */
public class Query<T> extends Page<T> {
	public static final String IS_ASC = "isAsc";
	public static final String LIMIT = "limit";
	public static final String ORDER_BY_FIELD = "orderByField";
	public static final String PAGE = "page";
	private Map<String, Object> condition;

	@Deprecated
	public Query() {
		// 没有dubbo会报错
	}

	/**
	 * 初始化 分页（推荐）
	 *
	 * @param page         多少页 1开始
	 * @param pageSize     每页多少条
	 * @param orderByField 正序排列的列名称 可null
	 */
	public Query(int page, int pageSize, String orderByField) {
		super(page, pageSize);
		Map<String, Object> params = new LinkedHashMap<>();
		Boolean isAsc = Boolean.parseBoolean(params.getOrDefault(IS_ASC, Boolean.TRUE).toString());
		if (StringUtils.isNotEmpty(orderByField)) {
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
		this.setCondition(params);
	}

	/**
	 * 初始化 分页（不推荐）
	 *
	 * @param params 分页参数
	 */
	public Query(Map<String, Object> params) {
		super(Integer.parseInt(params.getOrDefault(PAGE, 1).toString()),
				Integer.parseInt(params.getOrDefault(LIMIT, 10).toString()));

		String orderByField = params.getOrDefault(ORDER_BY_FIELD, "").toString();
		Boolean isAsc = Boolean.parseBoolean(params.getOrDefault(IS_ASC, Boolean.TRUE).toString());
		if (StringUtils.isNotEmpty(orderByField)) {
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
		this.setCondition(params);
	}

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}
}
