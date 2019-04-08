package com.uplus.auth.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.uplus.wei.util.CRequest;

import javax.servlet.http.HttpServletRequest;

/*** 跳转到登陆页（第三方的） **/
@Controller
@SessionAttributes
public class LoginController {
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

    /** 实际上这个只是验证的地址 正式环境不应该开放。 **/
    @GetMapping("/tologin")
    public String login(
            HttpServletRequest request,
            ModelMap map) {
        System.out.println("跳转登录页");

        Object value = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if (value != null) {
            String str = value.toString();
            str = str.substring(21, str.length() - 2);
            map.putAll(CRequest.URLRequest(str));

        }
        map.put("websiteUrl", websiteUrl);
        map.put("websiteTitle", websiteTitle);
        map.put("websiteRecord", websiteRecord);
        map.put("websiteLogoHref", websiteLogoHref);
        map.put("websiteFaviconIco", websiteFaviconIco);
        return "/login";
    }
}
