package com.uplus.wei.api.rbac.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotations.TableId;

import com.baomidou.mybatisplus.annotations.TableField;

import com.baomidou.mybatisplus.activerecord.Model;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author yanyu
 * @since 2018-08-18
 */
@ApiModel(value = "用户角色表")
@TableName("sys_user_role")
public class SysUserRole extends Model<SysUserRole> {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId("user_id")
	@ApiModelProperty(value = "用户ID")
	private Integer userId;
	/**
	 * 角色ID
	 */
	@TableField("role_id")
	@ApiModelProperty(value = "角色ID")
	private Integer roleId;

	/**
	 * 获取用户ID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 */

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获取角色ID
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * 设置角色ID
	 */

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 用户ID列的数据库字段名称
	 */
	public static final String USER_ID = "user_id";

	/**
	 * 角色ID列的数据库字段名称
	 */
	public static final String ROLE_ID = "role_id";

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

	@Override
	public String toString() {
		return "SysUserRole{" + "userId=" + userId + ", roleId=" + roleId + "}";
	}
}
