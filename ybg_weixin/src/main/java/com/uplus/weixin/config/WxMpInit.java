package com.uplus.weixin.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.uplus.wei.api.weixin.entity.NjwhInfiniti;
import com.uplus.wei.api.weixin.entity.WechatMinappConfig;
import com.uplus.wei.api.weixin.entity.WechatOpenPlatform;
import com.uplus.wei.api.weixin.entity.WechatPayConfig;
import com.uplus.wei.api.weixin.service.NjwhInfinitiService;
import com.uplus.wei.api.weixin.service.WechatMinappConfigService;
import com.uplus.wei.api.weixin.service.WechatOpenPlatformService;
import com.uplus.wei.api.weixin.service.WechatPayConfigService;
import com.uplus.wei.api.weixin.service.WxUserService;
import com.uplus.wei.api.weixin.service.WxUserTagService;
import com.uplus.weixin.handler.KfSessionHandler;
import com.uplus.weixin.handler.LocationHandler;
import com.uplus.weixin.handler.LogHandler;
import com.uplus.weixin.handler.MenuHandler;
import com.uplus.weixin.handler.MsgHandler;
import com.uplus.weixin.handler.NullHandler;
import com.uplus.weixin.handler.StoreCheckNotifyHandler;
import com.uplus.weixin.handler.SubscribeHandler;
import com.uplus.weixin.handler.UnsubscribeHandler;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import me.chanjar.weixin.common.api.WxConsts.EventType;
import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpInRedisConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenComponentServiceImpl;
import me.chanjar.weixin.open.api.impl.WxOpenInRedisConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenMessageRouter;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;
import redis.clients.jedis.JedisPool;

/**
 * 系统初始化加载信息
 *
 * @author Administrator
 *
 */
@Component
public class WxMpInit implements ApplicationListener<ApplicationReadyEvent> {
	private static final Logger logger = LoggerFactory.getLogger(WxMpInit.class);
	@Autowired
	private JedisPool jedisPool;
	// wx
	@Autowired
	private KfSessionHandler kfSessionHandler;
	// wx
	@Autowired
	private LocationHandler locationHandler;
	// wx
	@Autowired
	private LogHandler logHandler;
	// wx
	@Autowired
	private MenuHandler menuHandler;
	// wx
	@Autowired
	private MsgHandler msgHandler;
	// wx
	@Autowired
	private NjwhInfinitiService njwhInfinitiService;
	// wx
	@Autowired

	private NullHandler nullHandler;
	// wx
	@Autowired

	private StoreCheckNotifyHandler storeCheckNotifyHandler;
	// wx
	@Autowired

	private SubscribeHandler subscribeHandler;
	// wx
	@Autowired

	private UnsubscribeHandler unsubscribeHandler;

	@Autowired
	private WechatMinappConfigService wechatMinappConfigService;

	@Autowired
	private WechatOpenPlatformService wechatOpenPlatformService;
	@Autowired
	private WechatPayConfigService wechatPayConfigService;
	@Autowired
	WxUserService wxUserService;
	@Autowired
	WxUserTagService wxUserTagService;

	/**
	 * 小程序
	 */
	private void initWxMinApp() {
		logger.info("正在初始化小程序加载！");
		List<WechatMinappConfig> minapps = wechatMinappConfigService.list();
		if (minapps != null && minapps.size() > 0) {
			for (WechatMinappConfig dbProp : minapps) {
				WxMaInMemoryConfig config = new WxMaInMemoryConfig();
				config.setAppid(dbProp.getAppid());
				config.setSecret(dbProp.getSecret());
				config.setToken(dbProp.getToken());
				config.setAesKey(dbProp.getAesKey());
				config.setMsgDataFormat(dbProp.getMsgDataFormat());
				WxMaService service = new WxMaServiceImpl();
				service.setWxMaConfig(config);
				logger.info("加载小程序：" + dbProp.getAppid());
				WxMinAppConfiguration.getMinAppServices().put(dbProp.getAppid(), service);
				WxMinAppConfiguration.getRouters().put(dbProp.getAppid(), WxMinAppConfiguration.newRouter(service));

			}
		} else {
			logger.info("无小程序加载到系统中");
		}
		logger.info("初始化小程序加载完毕！");
	}

	private void initWxMp() {
		// 微信第三方配置
		List<WechatOpenPlatform> wechatOpenPlatformList = wechatOpenPlatformService.list();
		if (wechatOpenPlatformList != null && wechatOpenPlatformList.size() > 0) {
			for (WechatOpenPlatform dbProp : wechatOpenPlatformList) {
				WxOpenService wxOpenService = new WxOpenServiceImpl();
				WxOpenInRedisConfigStorage inRedisConfigStorage = new WxOpenInRedisConfigStorage(jedisPool);
				inRedisConfigStorage.setComponentAppId(dbProp.getComponentAppId());
				inRedisConfigStorage.setComponentAppSecret(dbProp.getComponentSecret());
				inRedisConfigStorage.setComponentToken(dbProp.getComponentToken());
				inRedisConfigStorage.setComponentAesKey(dbProp.getComponentAesKey());
				wxOpenService.setWxOpenConfigStorage(inRedisConfigStorage);
				WxOpenMessageRouter wxOpenMessageRouter = new WxOpenMessageRouter(wxOpenService);
				wxOpenMessageRouter.rule().handler(new WxMpMessageHandler() {
					@Override
					public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
							WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
						logger.info("\n接收到 {} 公众号请求消息，内容：{}", wxMpService.getWxMpConfigStorage().getAppId(),
								wxMpXmlMessage);
						return null;
					}
				}).next();
				WxMpConfig.getOpenRouteServices().put(dbProp.getComponentAppId(), wxOpenMessageRouter);
				WxMpConfig.getOpenServices().put(dbProp.getComponentAppId(), wxOpenService);
				logger.info("加入第三方平台到系统中：" + dbProp.getComponentAppId());
			}

		} else {
			logger.info("没有第三方平台加载！");
		}
		// -------------------------------------------------------------------------------------------------------
		// 微信公众号配置

		List<NjwhInfiniti> weixinList = njwhInfinitiService.list();
		if (weixinList != null && weixinList.size() > 0) {
			for (NjwhInfiniti bean : weixinList) {
				WxMpService weixinService = new WxMpServiceImpl();
				WxMpInRedisConfigStorage configStorage = new WxMpInRedisConfigStorage(jedisPool);
				// WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
				configStorage.setAppId(bean.getAppId());
				configStorage.setSecret(bean.getSecret());
				configStorage.setToken(bean.getToken());
				configStorage.setAesKey(bean.getAesKey());

				weixinService.setWxMpConfigStorage(configStorage);
				// 如果有第三方接管
				if (bean.getComponentAppId() != null && bean.getComponentAppId().trim().length() > 0) {
					WxOpenService wxOpenService = WxMpConfig.getOpenServices().get(bean.getComponentAppId());
					if (wxOpenService == null) {
						logger.error("没有找到对应的第三方接管配置,请检查 此公众号appId是：" + bean.getAppId());
						continue;
					}

					WxOpenComponentService wxOpenComponentService = new WxOpenComponentServiceImpl(wxOpenService);

					weixinService = wxOpenComponentService.getWxMpServiceByAppid(bean.getAppId());

					logger.info("加入公众号appId到系统中：" + bean.getAppId() + ",接管的公众号appId是：" + bean.getComponentAppId());
					WxMpConfig.getMpServices().put(bean.getAppId(), weixinService);
					WxMpConfig.getRouters().put(bean.getAppId(), newRouter(weixinService));
				} else {
					// 如果没有第三方接管
					logger.info("加入公众号到系统中：" + bean.getAppId());
					WxMpConfig.getMpServices().put(bean.getAppId(), weixinService);
					WxMpConfig.getRouters().put(bean.getAppId(), newRouter(weixinService));
				}
				// 初始化相关表
				logger.info("根据公众号初始化相关微信表 如果没有创建");
				wxUserService.creareTable(bean.getAppId());
				wxUserTagService.createTable(bean.getAppId());
				logger.info("根据公众号初始化相关微信表 如果没有创建  执行完毕！");
			}
		} else {
			logger.info("没有公众号配置载入平台！");
		}
		logger.info("初始化公众号流程完毕！");
	}

	/**
	 * 初始化微信支付配置
	 */
	private void initWxPay() {
		logger.info("正在初始化微信支付配置");
		List<WechatPayConfig> wxpayconfigs = wechatPayConfigService.list();
		if (wxpayconfigs != null && wxpayconfigs.size() > 0) {
			for (WechatPayConfig dbProp : wxpayconfigs) {
				WxPayConfig payConfig = new WxPayConfig();
				payConfig.setAppId(dbProp.getAppId());
				payConfig.setMchId(dbProp.getMchId());
				payConfig.setMchKey(dbProp.getMchKey());
				payConfig.setSubAppId(dbProp.getSubAppId());
				payConfig.setSubMchId(dbProp.getSubMchId());
				payConfig.setKeyPath(dbProp.getKeyPath());
				WxPayService wxPayService = new WxPayServiceImpl();

				wxPayService.setConfig(payConfig);
				logger.info("加载第三方支付到系统中appId:" + dbProp.getAppId());
				WxPayConfiguration.getWxpayServices().put(dbProp.getAppId(), wxPayService);
			}

		} else {
			logger.info("没有加载第三方支付到系统中");
		}
		logger.info("初始化微信支付配置完毕");
	}

	/**
	 * 微信消息路由
	 *
	 * @param wxMpService
	 * @return
	 */
	private WxMpMessageRouter newRouter(WxMpService wxMpService) {
		final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

		// 记录所有事件的日志 （异步执行）
		newRouter.rule().handler(this.logHandler).next();

		// 接收客服会话管理事件
		newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
				.event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION).handler(this.kfSessionHandler).end();
		newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
				.event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION).handler(this.kfSessionHandler).end();
		newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
				.event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION).handler(this.kfSessionHandler).end();

		// 门店审核事件
		newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(WxMpEventConstants.POI_CHECK_NOTIFY)
				.handler(this.storeCheckNotifyHandler).end();

		// 自定义菜单事件
		newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(MenuButtonType.CLICK).handler(this.menuHandler)
				.end();

		// 点击菜单连接事件
		newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(MenuButtonType.VIEW).handler(this.nullHandler)
				.end();

		// 关注事件
		newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(EventType.SUBSCRIBE)
				.handler(this.subscribeHandler).end();

		// 取消关注事件
		newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(EventType.UNSUBSCRIBE)
				.handler(this.unsubscribeHandler).end();

		// 上报地理位置事件
		newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(EventType.LOCATION).handler(this.locationHandler)
				.end();

		// 接收地理位置消息
		newRouter.rule().async(false).msgType(XmlMsgType.LOCATION).handler(this.locationHandler).end();

		// 扫码事件
		newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(EventType.SCAN).handler(this.nullHandler).end();

		// 默认
		newRouter.rule().async(false).handler(this.msgHandler).end();

		return newRouter;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// 微信公众号
		initWxMp();
		// -------------------------------------------------------------------------------------------------------
		// 微信支付
		initWxPay();
		// -------------------------------------------------------------------------------------------------------
		// 微信小程序
		initWxMinApp();

	}

}
