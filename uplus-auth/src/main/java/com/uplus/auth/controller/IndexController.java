package com.uplus.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统首页
 * 
 * @author yanyu
 *
 */
@Controller
public class IndexController {
    /** 实际上这个只是验证的地址 正式环境不应该开放。 **/
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/")

    public String index() {
        return "index";
    }
}
