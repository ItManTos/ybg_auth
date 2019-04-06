package com.uplus.wei.api.weixin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户打标签
 * </p>
 *
 * @author yanyu
 * @since 2019-04-04
 */
@ApiModel(value = "用户打标签")
@TableName("wx_user_tag_")
public class WxUserTag extends Model<WxUserTag> {

	private static final long serialVersionUID = 1L;

	/**
	 * 公众号应用编码列的数据库字段名称
	 */
	public static final String APP_ID = "app_id";
	/**
	 * 用户公众号ID列的数据库字段名称
	 */
	public static final String OPEN_ID = "open_id";
	/**
	 * 标签ID列的数据库字段名称
	 */
	public static final String TAG_ID = "tag_id";
	/**
	 * 创建时间列的数据库字段名称
	 */
	public static final String CREATE_TIME = "create_time";
	/**
	 * 修改时间列的数据库字段名称
	 */
	public static final String MODIFIED_TIME = "modified_time";
	/**
	 * 标签名称（冗余查询）列的数据库字段名称
	 */
	public static final String TAG_NAME = "tag_name";

	/**
	 * 公众号应用编码
	 */
	@ApiModelProperty(value = "公众号应用编码")
	private String appId;
	/**
	 * 用户公众号ID
	 */
	@ApiModelProperty(value = "用户公众号ID")
	private String openId;

	/**
	 * 标签ID
	 */
	@ApiModelProperty(value = "标签ID")
	private Long tagId;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime modifiedTime;
	/**
	 * 标签名称（冗余查询）
	 */
	@ApiModelProperty(value = "标签名称（冗余查询）")
	private String tagName;

	/**
	 * 获取公众号应用编码
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * 获取创建时间
	 */
	public LocalDateTime getCreateTime() {
		return createTime;
	}

	/**
	 * 获取修改时间
	 */
	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	/**
	 * 获取用户公众号ID
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * 获取标签ID
	 */
	public Long getTagId() {
		return tagId;
	}

	/**
	 * 获取标签名称（冗余查询）
	 */
	public String getTagName() {
		return tagName;
	}

	@Override
	protected Serializable pkVal() {
		return this.openId;
	}

	/**
	 * 设置公众号应用编码
	 */

	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * 设置创建时间
	 */

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置修改时间
	 */

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	/**
	 * 设置用户公众号ID
	 */

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * 设置标签ID
	 */

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	/**
	 * 设置标签名称（冗余查询）
	 */

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return "WxUserTag{" + "appId=" + appId + ", openId=" + openId + ", tagId=" + tagId + ", createTime="
				+ createTime + ", modifiedTime=" + modifiedTime + ", tagName=" + tagName + "}";
	}
}
