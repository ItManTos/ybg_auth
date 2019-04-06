package com.uplus.wei.api.weixin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 微信支付列表
 * </p>
 *
 * @author yanyu
 * @since 2019-03-31
 */
@ApiModel(value = "微信支付列表")
@TableName("wechat_pay_config")
public class WechatPayConfig extends Model<WechatPayConfig> {

	/**
	 * 公众号appid.列的数据库字段名称
	 */
	public static final String APP_ID = "app_id";

	/**
	 * p12证书文件的绝对路径或者以classpath:开头的类路径.列的数据库字段名称
	 */
	public static final String KEY_PATH = "key_path";
	/**
	 * 商户号.列的数据库字段名称
	 */
	public static final String MCH_ID = "mch_id";
	/**
	 * 商户密钥.列的数据库字段名称
	 */
	public static final String MCH_KEY = "mch_key";
	private static final long serialVersionUID = 1L;
	/**
	 * 服务商模式下的子商户号.列的数据库字段名称
	 */
	public static final String SUB_APP_ID = "sub_app_id";
	/**
	 * 服务商模式下的子商户公众账号ID.列的数据库字段名称
	 */
	public static final String SUB_MCH_ID = "sub_mch_id";

	/**
	 * 公众号appid.
	 */
	@ApiModelProperty(value = "公众号appid.")
	private String appId;
	/**
	 * p12证书文件的绝对路径或者以classpath:开头的类路径.
	 */
	@ApiModelProperty(value = "p12证书文件的绝对路径或者以classpath:开头的类路径.")
	private String keyPath;

	/**
	 * 商户号.
	 */
	@ApiModelProperty(value = "商户号.")
	private String mchId;
	/**
	 * 商户密钥.
	 */
	@ApiModelProperty(value = "商户密钥.")
	private String mchKey;

	/**
	 * 服务商模式下的子商户号.
	 */
	@ApiModelProperty(value = "服务商模式下的子商户号.")
	private String subAppId;
	/**
	 * 服务商模式下的子商户公众账号ID.
	 */
	@ApiModelProperty(value = "服务商模式下的子商户公众账号ID.")
	private String subMchId;

	/**
	 * 获取公众号appid.
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * 获取p12证书文件的绝对路径或者以classpath:开头的类路径.
	 */
	public String getKeyPath() {
		return keyPath;
	}

	/**
	 * 获取商户号.
	 */
	public String getMchId() {
		return mchId;
	}

	/**
	 * 获取商户密钥.
	 */
	public String getMchKey() {
		return mchKey;
	}

	/**
	 * 获取服务商模式下的子商户号.
	 */
	public String getSubAppId() {
		return subAppId;
	}

	/**
	 * 获取服务商模式下的子商户公众账号ID.
	 */
	public String getSubMchId() {
		return subMchId;
	}

	@Override
	protected Serializable pkVal() {
		return this.appId;
	}

	/**
	 * 设置公众号appid.
	 */

	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * 设置p12证书文件的绝对路径或者以classpath:开头的类路径.
	 */

	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}

	/**
	 * 设置商户号.
	 */

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	/**
	 * 设置商户密钥.
	 */

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	/**
	 * 设置服务商模式下的子商户号.
	 */

	public void setSubAppId(String subAppId) {
		this.subAppId = subAppId;
	}

	/**
	 * 设置服务商模式下的子商户公众账号ID.
	 */

	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}

	@Override
	public String toString() {
		return "WechatPayConfig{" + "appId=" + appId + ", mchId=" + mchId + ", mchKey=" + mchKey + ", subAppId="
				+ subAppId + ", subMchId=" + subMchId + ", keyPath=" + keyPath + "}";
	}
}
