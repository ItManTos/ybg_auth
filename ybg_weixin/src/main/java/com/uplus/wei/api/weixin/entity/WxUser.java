package com.uplus.wei.api.weixin.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
 * @since 2019-04-03
 */
@ApiModel(value = "用户表")
@TableName("wx_user_")
public class WxUser extends Model<WxUser> {

	public static final String APP_ID = "app_id";

	/**
	 * 城市列的数据库字段名称
	 */
	public static final String CITY = "city";
	/**
	 * 国家列的数据库字段名称
	 */
	public static final String COUNTRY = "country";
	/**
	 * 分组ID列的数据库字段名称
	 */
	public static final String GROUP_ID = "group_id";
	/**
	 * 头像列的数据库字段名称
	 */
	public static final String HEAD_IMG_URL = "head_img_url";
	/**
	 * 语言列的数据库字段名称
	 */
	public static final String LANGUAGE = "language";
	/**
	 * 昵称列的数据库字段名称
	 */
	public static final String NICKNAME = "nickname";
	/**
	 * 公众号ID列的数据库字段名称
	 */
	public static final String OPEN_ID = "open_id";
	/**
	 * 省份列的数据库字段名称
	 */
	public static final String PROVINCE = "province";
	/**
	 * 二维码扫码场景列的数据库字段名称
	 */
	public static final String QR_SCENE = "qr_scene";
	/**
	 * 二维码扫码场景描述列的数据库字段名称
	 */
	public static final String QR_SCENE_STR = "qr_scene_str";
	/**
	 * 备注列的数据库字段名称
	 */
	public static final String REMARK = "remark";
	private static final long serialVersionUID = 1L;
	/**
	 * 性别列的数据库字段名称
	 */
	public static final String SEX = "sex";
	/**
	 * 性别描述列的数据库字段名称
	 */
	public static final String SEX_DESC = "sex_desc";
	/**
	 * 是否关注列的数据库字段名称
	 */
	public static final String SUBSCRIBE = "subscribe";
	/**
	 * 返回用户关注的渠道来源列的数据库字段名称
	 */
	public static final String SUBSCRIBE_SCENE = "subscribe_scene";
	/**
	 * 关注事件列的数据库字段名称
	 */
	public static final String SUBSCRIBE_TIME = "subscribe_time";
	/**
	 * 开放平台ID列的数据库字段名称
	 */
	public static final String UNION_ID = "union_id";

	@ApiModelProperty(value = "")
	private String appId;

	/**
	 * 城市
	 */
	@ApiModelProperty(value = "城市")
	private String city;

	/**
	 * 国家
	 */
	@ApiModelProperty(value = "国家")
	private String country;
	/**
	 * 分组ID
	 */
	@ApiModelProperty(value = "分组ID")
	private Integer groupId;

	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
	private String headImgUrl;
	/**
	 * 语言
	 */
	@ApiModelProperty(value = "语言")
	private String language;

	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称")
	private String nickname;
	/**
	 * 公众号ID
	 */
	@ApiModelProperty(value = "公众号ID")
	private String openId;

	/**
	 * 省份
	 */
	@ApiModelProperty(value = "省份")
	private String province;
	/**
	 * 二维码扫码场景
	 */
	@ApiModelProperty(value = "二维码扫码场景")
	private String qrScene;

	/**
	 * 二维码扫码场景描述
	 */
	@ApiModelProperty(value = "二维码扫码场景描述")
	private String qrSceneStr;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;

	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别")
	private Integer sex;
	/**
	 * 性别描述
	 */
	@ApiModelProperty(value = "性别描述")
	private String sexDesc;

	/**
	 * 是否关注
	 */
	@ApiModelProperty(value = "是否关注")
	private Integer subscribe;
	/**
	 * 返回用户关注的渠道来源
	 */
	@ApiModelProperty(value = "返回用户关注的渠道来源")
	private String subscribeScene;

	/**
	 * 关注事件
	 */
	@ApiModelProperty(value = "关注事件")
	private BigDecimal subscribeTime;
	/**
	 * 开放平台ID
	 */
	@ApiModelProperty(value = "开放平台ID")
	private Integer unionId;

	public String getAppId() {
		return appId;
	}

	/**
	 * 获取城市
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 获取国家
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 获取分组ID
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * 获取头像
	 */
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	/**
	 * 获取语言
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * 获取昵称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 获取公众号ID
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * 获取省份
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 获取二维码扫码场景
	 */
	public String getQrScene() {
		return qrScene;
	}

	/**
	 * 获取二维码扫码场景描述
	 */
	public String getQrSceneStr() {
		return qrSceneStr;
	}

	/**
	 * 获取备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 获取性别
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * 获取性别描述
	 */
	public String getSexDesc() {
		return sexDesc;
	}

	/**
	 * 获取是否关注
	 */
	public Integer getSubscribe() {
		return subscribe;
	}

	/**
	 * 获取返回用户关注的渠道来源
	 */
	public String getSubscribeScene() {
		return subscribeScene;
	}

	/**
	 * 获取关注事件
	 */
	public BigDecimal getSubscribeTime() {
		return subscribeTime;
	}

	/**
	 * 获取开放平台ID
	 */
	public Integer getUnionId() {
		return unionId;
	}

	@Override
	protected Serializable pkVal() {
		return this.openId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * 设置城市
	 */

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 设置国家
	 */

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 设置分组ID
	 */

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * 设置头像
	 */

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	/**
	 * 设置语言
	 */

	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * 设置昵称
	 */

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 设置公众号ID
	 */

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * 设置省份
	 */

	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 设置二维码扫码场景
	 */

	public void setQrScene(String qrScene) {
		this.qrScene = qrScene;
	}

	/**
	 * 设置二维码扫码场景描述
	 */

	public void setQrSceneStr(String qrSceneStr) {
		this.qrSceneStr = qrSceneStr;
	}

	/**
	 * 设置备注
	 */

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 设置性别
	 */

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 设置性别描述
	 */

	public void setSexDesc(String sexDesc) {
		this.sexDesc = sexDesc;
	}

	/**
	 * 设置是否关注
	 */

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * 设置返回用户关注的渠道来源
	 */

	public void setSubscribeScene(String subscribeScene) {
		this.subscribeScene = subscribeScene;
	}

	/**
	 * 设置关注事件
	 */

	public void setSubscribeTime(BigDecimal subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	/**
	 * 设置开放平台ID
	 */

	public void setUnionId(Integer unionId) {
		this.unionId = unionId;
	}

	@Override
	public String toString() {
		return "WxUser{" + "appId=" + appId + ", subscribe=" + subscribe + ", openId=" + openId + ", nickname="
				+ nickname + ", sexDesc=" + sexDesc + ", sex=" + sex + ", language=" + language + ", city=" + city
				+ ", province=" + province + ", country=" + country + ", headImgUrl=" + headImgUrl + ", subscribeTime="
				+ subscribeTime + ", unionId=" + unionId + ", remark=" + remark + ", groupId=" + groupId
				+ ", subscribeScene=" + subscribeScene + ", qrScene=" + qrScene + ", qrSceneStr=" + qrSceneStr + "}";
	}
}
