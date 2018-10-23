package com.uplus.wei.api.rbac.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yanyu
 * @since 2018-08-18
 */
@ApiModel(value = "用户表")
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

	/**
	 * 头像列的数据库字段名称
	 */
	public static final String AVATAR = "avatar";

	/**
	 * 创建时间列的数据库字段名称
	 */
	public static final String CREATE_TIME = "create_time";
	/**
	 * 0-正常，1-删除列的数据库字段名称
	 */
	public static final String DEL_FLAG = "del_flag";
	/**
	 * 部门ID列的数据库字段名称
	 */
	public static final String DEPT_ID = "dept_id";
	public static final String PASSWORD = "password";
	/**
	 * 简介列的数据库字段名称
	 */
	public static final String PHONE = "phone";
	/**
	 * 随机盐列的数据库字段名称
	 */
	public static final String SALT = "salt";
	private static final long serialVersionUID = 1L;
	/**
	 * 修改时间列的数据库字段名称
	 */
	public static final String UPDATE_TIME = "update_time";
	/**
	 * 主键ID列的数据库字段名称
	 */
	public static final String USER_ID = "user_id";
	/**
	 * 用户名列的数据库字段名称
	 */
	public static final String USERNAME = "username";

	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
	private String avatar;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	/**
	 * 0-正常，1-删除
	 */
	@TableField("del_flag")
	@ApiModelProperty(value = "0-正常，1-删除")
	private String delFlag;

	/**
	 * 部门ID
	 */
	@TableField("dept_id")
	@ApiModelProperty(value = "部门ID")
	private Integer deptId;

	@ApiModelProperty(value = "密码")
	private String password;

	/**
	 * 简介
	 */
	@ApiModelProperty(value = "简介")
	private String phone;

	/**
	 * 随机盐
	 */
	@ApiModelProperty(value = "随机盐")
	private String salt;

	/**
	 * 修改时间
	 */
	@TableField("update_time")
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;

	/**
	 * 主键ID
	 */
	@TableId(value = "user_id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键ID")
	private Integer userId;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;

	/**
	 * 获取头像
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * 获取创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取0-正常，1-删除
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * 获取部门ID
	 */
	public Integer getDeptId() {
		return deptId;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * 获取简介
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 获取随机盐
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * 获取修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 获取主键ID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 获取用户名
	 */
	public String getUsername() {
		return username;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

	/**
	 * 设置头像
	 */

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 设置创建时间
	 */

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置0-正常，1-删除
	 */

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 设置部门ID
	 */

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 设置简介
	 */

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 设置随机盐
	 */

	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * 设置修改时间
	 */

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 设置主键ID
	 */

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 设置用户名
	 */

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "SysUser{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", salt=" + salt
				+ ", phone=" + phone + ", avatar=" + avatar + ", deptId=" + deptId + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", delFlag=" + delFlag + "}";
	}
}
