package com.uplus.wei.api.weixin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 微信小程序配置
 * </p>
 *
 * @author yanyu
 * @since 2019-03-31
 */
@ApiModel(value = "微信小程序配置")
@TableName("wechat_minapp_config")
public class WechatMinappConfig extends Model<WechatMinappConfig> {

	/**
	 * 加密列的数据库字段名称
	 */
	public static final String AES_KEY = "aes_key";

	/**
	 * 应用编号列的数据库字段名称
	 */
	public static final String APPID = "appid";
	/**
	 * 消息数据格式列的数据库字段名称
	 */
	public static final String MSG_DATA_FORMAT = "msg_data_format";
	/**
	 * 密钥列的数据库字段名称
	 */
	public static final String SECRET = "secret";
	private static final long serialVersionUID = 1L;
	/**
	 * 消息Token列的数据库字段名称
	 */
	public static final String TOKEN = "token";
	/**
	 * 小程序名称列的数据库字段名称
	 */
	public static final String WXAPP_NAME = "wxapp_name";

	/**
	 * 加密
	 */
	@ApiModelProperty(value = "加密")
	private String aesKey;
	/**
	 * 应用编号
	 */
	@ApiModelProperty(value = "应用编号")
	private String appid;

	/**
	 * 消息数据格式
	 */
	@ApiModelProperty(value = "消息数据格式")
	private String msgDataFormat;
	/**
	 * 密钥
	 */
	@ApiModelProperty(value = "密钥")
	private String secret;

	/**
	 * 消息Token
	 */
	@ApiModelProperty(value = "消息Token")
	private String token;
	/**
	 * 小程序名称
	 */
	@ApiModelProperty(value = "小程序名称")
	private String wxappName;

	/**
	 * 获取加密
	 */
	public String getAesKey() {
		return aesKey;
	}

	/**
	 * 获取应用编号
	 */
	public String getAppid() {
		return appid;
	}

	/**
	 * 获取消息数据格式
	 */
	public String getMsgDataFormat() {
		return msgDataFormat;
	}

	/**
	 * 获取密钥
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * 获取消息Token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 获取小程序名称
	 */
	public String getWxappName() {
		return wxappName;
	}

	@Override
	protected Serializable pkVal() {
		return this.appid;
	}

	/**
	 * 设置加密
	 */

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	/**
	 * 设置应用编号
	 */

	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * 设置消息数据格式
	 */

	public void setMsgDataFormat(String msgDataFormat) {
		this.msgDataFormat = msgDataFormat;
	}

	/**
	 * 设置密钥
	 */

	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * 设置消息Token
	 */

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 设置小程序名称
	 */

	public void setWxappName(String wxappName) {
		this.wxappName = wxappName;
	}

	@Override
	public String toString() {
		return "WechatMinappConfig{" + "appid=" + appid + ", secret=" + secret + ", token=" + token + ", aesKey="
				+ aesKey + ", msgDataFormat=" + msgDataFormat + ", wxappName=" + wxappName + "}";
	}
}
