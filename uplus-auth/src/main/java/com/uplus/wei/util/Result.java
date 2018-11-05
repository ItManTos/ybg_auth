package com.uplus.wei.util;

import java.io.Serializable;

import com.uplus.wei.enums.ResultEnum;

/**
 * @author yanyu
 *
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 747116930509748789L;

	/**
	 * @Description: 返回失败，返回码参考ResultEnum.SUCCESS
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:12
	 */
	public static Result error() {
		Result result = new Result();
		result.setCode(ResultEnum.ERROR.getCode());
		result.setMsg(ResultEnum.ERROR.getMessage());
		result.setData(null);
		return result;
	}

	/**
	 * @Description: 返回失败，返回码参考ResultEnum.SUCCESS
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:12
	 */
	public static Result error(Integer code) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(ResultEnum.getDescByValue(code));
		result.setData(null);
		return result;
	}

	/**
	 * @Description: 返回失败，返回码参考ResultEnum.SUCCESS
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:12
	 */
	public static Result error(Integer code, String msg) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(null);
		return result;
	}

	// @ApiModelProperty("是否成功")
	// public boolean isSuccess() {
	// return this.code == ResultEnum.SUCCESS.getCode() ? true : false;
	// }

	/**
	 * @Description: 返回失败，返回码参考ResultEnum.SUCCESS
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:12
	 */
	public static Result error(Integer code, String msg, Object data) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}

	/**
	 * @Description: 返回失败，返回码参考ResultEnum.SUCCESS
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:12
	 */
	public static Result error(String msg) {
		Result result = new Result();
		result.setCode(ResultEnum.ERROR.getCode());
		result.setMsg(msg);
		result.setData(null);
		return result;
	}

	/**
	 * @Description: 返回失败，返回码参考ResultEnum.SUCCESS
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:12
	 */
	public static Result errorWithData(Object data) {
		Result result = new Result();
		result.setCode(ResultEnum.ERROR.getCode());
		result.setMsg(ResultEnum.ERROR.getMessage());
		result.setData(data);
		return result;
	}

	/**
	 * @Description: 返回成功，返回码参考ResultEnum.SUCCESS
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:12
	 */
	public static Result success() {
		Result result = new Result();
		result.setCode(ResultEnum.SUCCESS.getCode());
		result.setMsg(ResultEnum.SUCCESS.getMessage());
		result.setData(null);
		return result;
	}

	/**
	 * @Description: 返回成功，返回码参考ResultEnum.SUCCESS
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:12
	 */
	public static Result success(Integer code) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(ResultEnum.getDescByValue(code));
		result.setData(null);
		return result;
	}

	/**
	 * @Description: 返回成功，返回码参考ResultEnum.SUCCESS
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:12
	 */
	public static Result success(Integer code, String msg) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(null);
		return result;
	}

	/**
	 * @Description: 返回失败，返回码参考ResultEnum.SUCCESS
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:12
	 */
	public static Result success(Integer code, String msg, Object data) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}

	/**
	 * @Description: 返回成功，返回码参考ResultEnum.SUCCESS
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:12
	 */
	public static Result success(String msg) {
		Result result = new Result();
		result.setCode(ResultEnum.SUCCESS.getCode());
		result.setMsg(msg);
		result.setData(null);
		return result;
	}

	/**
	 * @Description: 返回成功，返回码参考ResultEnum.SUCCESS
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:12
	 */
	public static Result successWithData(Object data) {
		Result result = new Result();
		result.setCode(ResultEnum.SUCCESS.getCode());
		result.setMsg(ResultEnum.SUCCESS.getMessage());
		result.setData(data);
		return result;
	}

	/**
	 * @Description: 错误码
	 * @Author: chongzi
	 * @Date: 2018/7/17 下午11:59
	 */
	private Integer code;

	/**
	 * @Description: 具体内容
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:00
	 */
	private Object data;

	/**
	 * @Description: 提示信息
	 * @Author: chongzi
	 * @Date: 2018/7/17 下午11:59
	 */
	private String msg;

	public Result() {
	}

	/**
	 * @Description: 返回成功，返回码参考ResultEnum
	 * @Author: chongzi
	 * @Date: 2018/7/18 上午12:12
	 */
	public Result(Integer code) {
		this.code = code;
		this.msg = ResultEnum.getDescByValue(code);
	}

	public Integer getCode() {
		return code;
	}

	public Object getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
