package com.uplus.wei.api.rbac.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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

	/**
	 * 角色ID列的数据库字段名称
	 */
	public static final String ROLE_ID = "role_id";

	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID列的数据库字段名称
	 */
	public static final String USER_ID = "user_id";

	/**
	 * 角色ID
	 */
	@TableField("role_id")
	@ApiModelProperty(value = "角色ID")
	private Integer roleId;

	/**
	 * 用户ID
	 */
	@TableId("user_id")
	@ApiModelProperty(value = "用户ID")
	private Integer userId;

	/**
	 * 获取角色ID
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * 获取用户ID
	 */
	public Integer getUserId() {
		return userId;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

	/**
	 * 设置角色ID
	 */

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 设置用户ID
	 */

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "SysUserRole{" + "userId=" + userId + ", roleId=" + roleId + "}";
	}
}
