package com.uplus.weixin.controller.minapp;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uplus.weixin.config.WxMinAppConfiguration;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/wx/maportal/{appid}")
public class WxMaPortalController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(produces = "text/plain;charset=utf-8")
	public String authGet(@PathVariable String appid,
			@RequestParam(name = "signature", required = false) String signature,
			@RequestParam(name = "timestamp", required = false) String timestamp,
			@RequestParam(name = "nonce", required = false) String nonce,
			@RequestParam(name = "echostr", required = false) String echostr) {
		try {
			this.logger.info("\n接收到来自微信服务器的认证消息：signature = [{}], timestamp = [{}], nonce = [{}], echostr = [{}]",
					signature, timestamp, nonce, echostr);

			if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
				throw new IllegalArgumentException("请求参数非法，请核实!");
			}

			final WxMaService wxService = WxMinAppConfiguration.getMinAppServices().get(appid);

			boolean checkSignature = wxService.checkSignature(timestamp, nonce, signature);
			logger.info("checkSignature=" + checkSignature);
			if (checkSignature) {
				return echostr;
			}

		} catch (Exception e) {
			logger.error("错误", e);
		}
		return "非法请求";
	}

	@PostMapping(produces = "application/xml; charset=UTF-8")
	public String post(@PathVariable String appid, @RequestBody String requestBody,
			@RequestParam("msg_signature") String msgSignature, @RequestParam("encrypt_type") String encryptType,
			@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce) {
		this.logger.info(
				"\n接收微信请求：[msg_signature=[{}], encrypt_type=[{}], signature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				msgSignature, encryptType, signature, timestamp, nonce, requestBody);

		final WxMaService wxService = WxMinAppConfiguration.getMinAppServices().get(appid);

		final boolean isJson = Objects.equals(wxService.getWxMaConfig().getMsgDataFormat(),
				WxMaConstants.MsgDataFormat.JSON);
		if (StringUtils.isBlank(encryptType)) {
			// 明文传输的消息
			WxMaMessage inMessage;
			if (isJson) {
				inMessage = WxMaMessage.fromJson(requestBody);
			} else {// xml
				inMessage = WxMaMessage.fromXml(requestBody);
			}

			route(inMessage, appid);
			return "success";
		}

		if ("aes".equals(encryptType)) {
			// 是aes加密的消息
			WxMaMessage inMessage;
			if (isJson) {
				inMessage = WxMaMessage.fromEncryptedJson(requestBody, wxService.getWxMaConfig());
			} else {// xml
				inMessage = WxMaMessage.fromEncryptedXml(requestBody, wxService.getWxMaConfig(), timestamp, nonce,
						msgSignature);
			}

			route(inMessage, appid);
			return "success";
		}

		throw new RuntimeException("不可识别的加密类型：" + encryptType);
	}

	private void route(WxMaMessage message, String appid) {
		try {
			WxMinAppConfiguration.getRouters().get(appid).route(message);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}
	}

}