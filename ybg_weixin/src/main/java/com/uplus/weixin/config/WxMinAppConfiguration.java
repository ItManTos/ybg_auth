package com.uplus.weixin.config;

import java.io.File;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import cn.binarywang.wx.miniapp.message.WxMaMessageHandler;
import cn.binarywang.wx.miniapp.message.WxMaMessageRouter;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;

/***
 * 微信小程序
 *
 * @author yanyu
 *
 */
public class WxMinAppConfiguration {
	private final static WxMaMessageHandler logHandler = new WxMaMessageHandler() {
		@Override
		public void handle(WxMaMessage wxMessage, Map<String, Object> context, WxMaService service,
				WxSessionManager sessionManager) throws WxErrorException {
			System.out.println("收到消息：" + wxMessage.toString());
			service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("收到信息为：" + wxMessage.toJson())
					.toUser(wxMessage.getFromUser()).build());
		}
	};
	/**
	 * 多微信小程序
	 */
	private static Map<String, WxMaService> minAppServices = Maps.newHashMap();

	private final static WxMaMessageHandler picHandler = new WxMaMessageHandler() {
		@Override
		public void handle(WxMaMessage wxMessage, Map<String, Object> context, WxMaService service,
				WxSessionManager sessionManager) throws WxErrorException {
			try {
				WxMediaUploadResult uploadResult = service.getMediaService().uploadMedia("image", "png",
						ClassLoader.getSystemResourceAsStream("tmp.png"));
				service.getMsgService().sendKefuMsg(WxMaKefuMessage.newImageBuilder().mediaId(uploadResult.getMediaId())
						.toUser(wxMessage.getFromUser()).build());
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
	};

	private final static WxMaMessageHandler qrcodeHandler = new WxMaMessageHandler() {
		@Override
		public void handle(WxMaMessage wxMessage, Map<String, Object> context, WxMaService service,
				WxSessionManager sessionManager) throws WxErrorException {
			try {
				final File file = service.getQrcodeService().createQrcode("123", 430);
				WxMediaUploadResult uploadResult = service.getMediaService().uploadMedia("image", file);
				service.getMsgService().sendKefuMsg(WxMaKefuMessage.newImageBuilder().mediaId(uploadResult.getMediaId())
						.toUser(wxMessage.getFromUser()).build());
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * 多微信路由
	 */
	private static Map<String, WxMaMessageRouter> routers = Maps.newHashMap();

	/*********/
	private final static WxMaMessageHandler templateMsgHandler = new WxMaMessageHandler() {
		@Override
		public void handle(WxMaMessage wxMessage, Map<String, Object> context, WxMaService service,
				WxSessionManager sessionManager) throws WxErrorException {
			service.getMsgService()
					.sendTemplateMsg(WxMaTemplateMessage.builder().templateId("此处更换为自己的模板id").formId("自己替换可用的formid")
							.data(Lists.newArrayList(new WxMaTemplateData("keyword1", "339208499", "#173177")))
							.toUser(wxMessage.getFromUser()).build());
		}
	};

	private final static WxMaMessageHandler textHandler = new WxMaMessageHandler() {
		@Override
		public void handle(WxMaMessage wxMessage, Map<String, Object> context, WxMaService service,
				WxSessionManager sessionManager) throws WxErrorException {
			service.getMsgService().sendKefuMsg(
					WxMaKefuMessage.newTextBuilder().content("回复文本消息").toUser(wxMessage.getFromUser()).build());
		}
	};

	public static Map<String, WxMaService> getMinAppServices() {
		return minAppServices;
	}

	public static Map<String, WxMaMessageRouter> getRouters() {
		return routers;
	}

	public static WxMaMessageRouter newRouter(WxMaService service) {
		final WxMaMessageRouter router = new WxMaMessageRouter(service);
		router.rule().handler(logHandler).next().rule().async(false).content("模板").handler(templateMsgHandler).end()
				.rule().async(false).content("文本").handler(textHandler).end().rule().async(false).content("图片")
				.handler(picHandler).end().rule().async(false).content("二维码").handler(qrcodeHandler).end();
		return router;
	}
}
