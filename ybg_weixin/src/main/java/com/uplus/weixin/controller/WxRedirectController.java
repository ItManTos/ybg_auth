package com.uplus.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uplus.weixin.config.WxMpConfig;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author Edward
 */
@Controller
@RequestMapping("/wx/redirect/{appid}")
public class WxRedirectController {

	@RequestMapping("/greet")
	public String greetUser(@PathVariable String appid, @RequestParam String code, ModelMap map) {

		WxMpService mpService = WxMpConfig.getMpServices().get(appid);

		try {
			WxMpOAuth2AccessToken accessToken = mpService.oauth2getAccessToken(code);
			WxMpUser user = mpService.oauth2getUserInfo(accessToken, null);
			map.put("user", user);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		return "greet_user";
	}
}
