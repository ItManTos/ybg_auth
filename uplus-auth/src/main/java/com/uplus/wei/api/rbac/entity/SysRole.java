package com.uplus.wei.api.rbac.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;

import com.baomidou.mybatisplus.annotations.TableField;

import com.baomidou.mybatisplus.activerecord.Model;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author yanyu
 * @since 2018-08-18
 */
@ApiModel(value = "角色表")
@TableName("sys_role")
public class SysRole extends Model<SysRole> {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色主键
	 */
	@TableId(value = "role_id", type = IdType.AUTO)
	@ApiModelProperty(value = "角色主键")
	private Integer roleId;
	/**
	 * 角色名称，如ROLE_ADMIN
	 */
	@TableField("role_name")
	@ApiModelProperty(value = "角色名称，如ROLE_ADMIN")
	private String roleName;
	/**
	 * 角色编码
	 */
	@TableField("role_code")
	@ApiModelProperty(value = "角色编码")
	private String roleCode;
	/**
	 * 角色描述
	 */
	@TableField("role_desc")
	@ApiModelProperty(value = "角色描述")
	private String roleDesc;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@TableField("update_time")
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableField("del_flag")
	@ApiModelProperty(value = "删除标识（0-正常,1-删除）")
	private String delFlag;

	/**
	 * 获取角色主键
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * 设置角色主键
	 */

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取角色名称，如ROLE_ADMIN
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 设置角色名称，如ROLE_ADMIN
	 */

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 获取角色编码
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * 设置角色编码
	 */

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * 获取角色描述
	 */
	public String getRoleDesc() {
		return roleDesc;
	}

	/**
	 * 设置角色描述
	 */

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	/**
	 * 获取创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 */

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 */

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取删除标识（0-正常,1-删除）
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * 设置删除标识（0-正常,1-删除）
	 */

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 角色主键列的数据库字段名称
	 */
	public static final String ROLE_ID = "role_id";

	/**
	 * 角色名称，如ROLE_ADMIN列的数据库字段名称
	 */
	public static final String ROLE_NAME = "role_name";

	/**
	 * 角色编码列的数据库字段名称
	 */
	public static final String ROLE_CODE = "role_code";

	/**
	 * 角色描述列的数据库字段名称
	 */
	public static final String ROLE_DESC = "role_desc";

	/**
	 * 创建时间列的数据库字段名称
	 */
	public static final String CREATE_TIME = "create_time";

	/**
	 * 更新时间列的数据库字段名称
	 */
	public static final String UPDATE_TIME = "update_time";

	/**
	 * 删除标识（0-正常,1-删除）列的数据库字段名称
	 */
	public static final String DEL_FLAG = "del_flag";

	@Override
	protected Serializable pkVal() {
		return this.roleId;
	}

	@Override
	public String toString() {
		return "SysRole{" + "roleId=" + roleId + ", roleName=" + roleName + ", roleCode=" + roleCode + ", roleDesc="
				+ roleDesc + ", createTime=" + createTime + ", updateTime=" + updateTime + ", delFlag=" + delFlag + "}";
	}
}
