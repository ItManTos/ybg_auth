package com.uplus.wei.api.weixin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 第三方公众号
 * </p>
 *
 * @author yanyu
 * @since 2019-03-31
 */
@ApiModel(value = "第三方公众号")
@TableName("wechat_open_platform")
public class WechatOpenPlatform extends Model<WechatOpenPlatform> {

	/**
	 * 第三方公众号Token解密密钥列的数据库字段名称
	 */
	public static final String COMPONENT_AES_KEY = "component_aes_key";

	/**
	 * 第三方公众号ID列的数据库字段名称
	 */
	public static final String COMPONENT_APP_ID = "component_app_id";
	/**
	 * 第三方接管名称列的数据库字段名称
	 */
	public static final String COMPONENT_NAME = "component_name";
	/**
	 * 第三方公众号密钥列的数据库字段名称
	 */
	public static final String COMPONENT_SECRET = "component_secret";
	/**
	 * 第三方公众号消息Token列的数据库字段名称
	 */
	public static final String COMPONENT_TOKEN = "component_token";
	private static final long serialVersionUID = 1L;

	/**
	 * 第三方公众号Token解密密钥
	 */
	@ApiModelProperty(value = "第三方公众号Token解密密钥")
	private String componentAesKey;
	/**
	 * 第三方公众号ID
	 */
	@ApiModelProperty(value = "第三方公众号ID")
	private String componentAppId;

	/**
	 * 第三方接管名称
	 */
	@ApiModelProperty(value = "第三方接管名称")
	private String componentName;
	/**
	 * 第三方公众号密钥
	 */
	@ApiModelProperty(value = "第三方公众号密钥")
	private String componentSecret;

	/**
	 * 第三方公众号消息Token
	 */
	@ApiModelProperty(value = "第三方公众号消息Token")
	private String componentToken;

	/**
	 * 获取第三方公众号Token解密密钥
	 */
	public String getComponentAesKey() {
		return componentAesKey;
	}

	/**
	 * 获取第三方公众号ID
	 */
	public String getComponentAppId() {
		return componentAppId;
	}

	/**
	 * 获取第三方接管名称
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * 获取第三方公众号密钥
	 */
	public String getComponentSecret() {
		return componentSecret;
	}

	/**
	 * 获取第三方公众号消息Token
	 */
	public String getComponentToken() {
		return componentToken;
	}

	@Override
	protected Serializable pkVal() {
		return this.componentAppId;
	}

	/**
	 * 设置第三方公众号Token解密密钥
	 */

	public void setComponentAesKey(String componentAesKey) {
		this.componentAesKey = componentAesKey;
	}

	/**
	 * 设置第三方公众号ID
	 */

	public void setComponentAppId(String componentAppId) {
		this.componentAppId = componentAppId;
	}

	/**
	 * 设置第三方接管名称
	 */

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	/**
	 * 设置第三方公众号密钥
	 */

	public void setComponentSecret(String componentSecret) {
		this.componentSecret = componentSecret;
	}

	/**
	 * 设置第三方公众号消息Token
	 */

	public void setComponentToken(String componentToken) {
		this.componentToken = componentToken;
	}

	@Override
	public String toString() {
		return "WechatOpenPlatform{" + "componentAppId=" + componentAppId + ", componentSecret=" + componentSecret
				+ ", componentToken=" + componentToken + ", componentAesKey=" + componentAesKey + ", componentName="
				+ componentName + "}";
	}
}
