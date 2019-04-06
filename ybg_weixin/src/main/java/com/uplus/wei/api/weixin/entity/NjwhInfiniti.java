package com.uplus.wei.api.weixin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 公众号表
 * </p>
 *
 * @author yanyu
 * @since 2019-03-31
 */
@ApiModel(value = "公众号表")
public class NjwhInfiniti extends Model<NjwhInfiniti> {

	/**
	 * 解密的密钥列的数据库字段名称
	 */
	public static final String AES_KEY = "aes_key";

	/**
	 * 公众号appid列的数据库字段名称
	 */
	public static final String APP_ID = "app_id";
	/**
	 * 公众号的名称列的数据库字段名称
	 */
	public static final String APP_NAME = "app_name";
	/**
	 * 非必填！ 如果有第三方接管则要填上此项列的数据库字段名称
	 */
	public static final String COMPONENT_APP_ID = "component_app_id";
	/**
	 * 公众号密钥，如果有第三方托管这个不填也可以列的数据库字段名称
	 */
	public static final String SECRET = "secret";
	private static final long serialVersionUID = 1L;
	/**
	 * 消息通信token列的数据库字段名称
	 */
	public static final String TOKEN = "token";

	/**
	 * 解密的密钥
	 */
	@ApiModelProperty(value = "解密的密钥")
	private String aesKey;
	/**
	 * 公众号appid
	 */
	@ApiModelProperty(value = "公众号appid")
	private String appId;

	/**
	 * 公众号的名称
	 */
	@ApiModelProperty(value = "公众号的名称")
	private String appName;
	/**
	 * 非必填！ 如果有第三方接管则要填上此项
	 */
	@ApiModelProperty(value = "非必填！ 如果有第三方接管则要填上此项")
	private String componentAppId;

	/**
	 * 公众号密钥，如果有第三方托管这个不填也可以
	 */
	@ApiModelProperty(value = "公众号密钥，如果有第三方托管这个不填也可以")
	private String secret;
	/**
	 * 消息通信token
	 */
	@ApiModelProperty(value = "消息通信token")
	private String token;

	/**
	 * 获取解密的密钥
	 */
	public String getAesKey() {
		return aesKey;
	}

	/**
	 * 获取公众号appid
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * 获取公众号的名称
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * 获取非必填！ 如果有第三方接管则要填上此项
	 */
	public String getComponentAppId() {
		return componentAppId;
	}

	/**
	 * 获取公众号密钥，如果有第三方托管这个不填也可以
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * 获取消息通信token
	 */
	public String getToken() {
		return token;
	}

	@Override
	protected Serializable pkVal() {
		return this.appId;
	}

	/**
	 * 设置解密的密钥
	 */

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	/**
	 * 设置公众号appid
	 */

	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * 设置公众号的名称
	 */

	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * 设置非必填！ 如果有第三方接管则要填上此项
	 */

	public void setComponentAppId(String componentAppId) {
		this.componentAppId = componentAppId;
	}

	/**
	 * 设置公众号密钥，如果有第三方托管这个不填也可以
	 */

	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * 设置消息通信token
	 */

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "NjwhInfiniti{" + "appId=" + appId + ", secret=" + secret + ", token=" + token + ", aesKey=" + aesKey
				+ ", appName=" + appName + ", componentAppId=" + componentAppId + "}";
	}
}
