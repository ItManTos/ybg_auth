package com.uplus.weixin.config;

import java.util.Map;

import com.google.common.collect.Maps;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenMessageRouter;

/***
 * 微信配置类
 *
 * @author yanyu
 *
 */
public class WxMpConfig {
	/**
	 * 多微信公众号
	 */
	private static Map<String, WxMpService> mpServices = Maps.newHashMap();

	/**
	 * 第三方接管消息路由
	 */
	private static Map<String, WxOpenMessageRouter> openRouteServices = Maps.newHashMap();
	/**
	 * 第三方接管服务
	 */
	private static Map<String, WxOpenService> openServices = Maps.newHashMap();

	/**
	 * 微信多公众号的路由处理
	 */
	private static Map<String, WxMpMessageRouter> routers = Maps.newHashMap();

	public static Map<String, WxMpService> getMpServices() {
		return mpServices;
	}

	public static Map<String, WxOpenMessageRouter> getOpenRouteServices() {
		return openRouteServices;
	}

	public static Map<String, WxOpenService> getOpenServices() {
		return openServices;
	}

	public static Map<String, WxMpMessageRouter> getRouters() {
		return routers;
	}

}
