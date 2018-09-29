package com.uplus.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * http://conkeyn.iteye.com/blog/2296406 重新改造原有的授权页面。
 *
 * @author yanyu
 **/
@Controller
@SessionAttributes("authorizationRequest")
public class OAuth2ApprovalController {
	@Value(value = "${website.favicon.ico}")
	String websiteFaviconIco;
	@Value(value = "${website.logoHref}")
	String websiteLogoHref;
	@Value(value = "${website.record}")
	String websiteRecord;
	@Value(value = "${website.name}")
	String websiteTitle;
	@Value(value = "${website.logoUrl}")
	String websiteUrl;

	/** 授权页面，跳到提问用户是否 授权，仅有存在的回掉地址的时候才会有这个链接，是框架内置，不可直接访问！ **/
	@RequestMapping("/oauth/confirm_access")
	public String getAccessConfirmation(ModelMap map, HttpServletRequest request) throws Exception {

		map.put("client_id", request.getParameterMap().get("client_id")[0]);
		map.put("websiteUrl", websiteUrl);
		map.put("websiteTitle", websiteTitle);
		map.put("websiteRecord", websiteRecord);
		map.put("websiteLogoHref", websiteLogoHref);
		map.put("websiteFaviconIco", websiteFaviconIco);
		return "oauth/oauth_approval";
	}

}
