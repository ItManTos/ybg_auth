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

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author lengleng
 * @date 2017/12/25 spring boot 的异常对象
 */

public class ErrorPojo implements Serializable {

	@JSONField(name = "error")
	private String error;
	@JSONField(name = "exception")
	private String exception;
	@JSONField(name = "message")
	private String message;
	@JSONField(name = "path")
	private String path;
	@JSONField(name = "status")
	private int status;
	@JSONField(name = "timestamp")
	private long timestamp;

	public String getError() {
		return error;
	}

	public String getException() {
		return exception;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public int getStatus() {
		return status;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
