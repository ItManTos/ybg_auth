package com.uplus.wei.util;

import org.springframework.security.crypto.password.PasswordEncoder;

import cn.hutool.crypto.SecureUtil;

/**
 * MD5的密码管理器
 * 
 * @author yanyu
 *
 */

public class MD5PasswordEncoder implements PasswordEncoder {
    /** 加密字符串 **/
    @Override
    public String encode(
            CharSequence rawPassword) {

        return SecureUtil.md5(rawPassword.toString());
    }

    /** 匹配加密 **/
    @Override
    public boolean matches(
            CharSequence rawPassword,
            String encodedPassword) {

        return encode(rawPassword.toString()).equals(encodedPassword);
    }

}
