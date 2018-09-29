package com.uplus.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.uplus.wei.util.MD5PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
				/** formLogin 加上这个就不会弹出登录。变成页面表单登录 **/
				.loginPage("/tologin")
				/*
				 * http://www.cnblogs.com/charlypage/p/9320515.html ;
				 * name="username";name="password",否则无法识别,另外action="/authentication/form"要与.
				 * loginProcessingUrl("/authentication/form")相对应,原因为:
				 * 由于security是由UsernamePasswordAuthenticationFilter这个类定义登录的,里面默认是/login路径,
				 * 我们要让他用我们的/authentication/form路径,
				 * 就需要配置.loginProcessingUrl("/authentication/form")
				 **/
				.loginProcessingUrl("/authentication/form").and().authorizeRequests().antMatchers(HttpMethod.OPTIONS)
				.permitAll().antMatchers("/tologin").permitAll() // 设置所有人都可以访问登录页面
				/***/
				.anyRequest().permitAll()// .anyRequest().authenticated().
				.and().httpBasic().and()
				/** 关闭跨域保护 */
				.csrf().disable();// ;
	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {

		// 配置用户来源于数据库
		auth.userDetailsService(userDetailsService).passwordEncoder(md5());
	}

	@Bean
	public PasswordEncoder md5() {
		// 使用自己写的MD5加密方式
		return new MD5PasswordEncoder();
	}
}
