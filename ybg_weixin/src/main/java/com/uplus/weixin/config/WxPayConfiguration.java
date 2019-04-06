package com.uplus.weixin.config;
/**
 * 微信支付配置
 * @author Administrator
 *
 */

import java.util.Map;

import com.github.binarywang.wxpay.service.WxPayService;
import com.google.common.collect.Maps;

public class WxPayConfiguration {
	/**
	 * 微信支付公众号
	 */
	private static Map<String, WxPayService> wxpayServices = Maps.newHashMap();

	public static Map<String, WxPayService> getWxpayServices() {
		return wxpayServices;
	}

}
