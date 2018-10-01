package com.uplus.auth.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 认证授权服务端
 *
 * @author leftso
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	//
	// @Value("${resource.id:spring-boot-application}") //
	// 默认值spring-boot-application
	// private String resourceId;

	// @Value("${access_token.validity_period:3600}") // 默认值3600
	// int accessTokenValiditySeconds = 3600;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	RedisConnectionFactory connectionFactory;

	@Autowired
	private DataSource dataSource;

	/**
	 * token converter
	 *
	 * @return
	 */
	// @Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
			/***
			 * 重写增强token方法,用于自定义一些token返回的信息
			 */
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				// String userName = authentication.getUserAuthentication().getName();
				// 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}

				// /** 自定义一些token属性 ***/
				final Map<String, Object> additionalInformation = new HashMap<>();
				//
				// additionalInformation.put("user",
				// authentication.getUserAuthentication().getPrincipal());
				// additionalInformation.put("userName", userName);
				// additionalInformation.put("roles", user.getAuthorities());
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
				OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
				return enhancedToken;
			}

		};
		accessTokenConverter.setSigningKey("123");// 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
		return accessTokenConverter;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(this.authenticationManager);
		// endpoints.accessTokenConverter(accessTokenConverter());
		endpoints.tokenStore(tokenStore());

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')");
		oauthServer.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
		// 如果没有支持allowFormAuthenticationForClients或者有支持但是url中没有client_id和client_secret的，走basic认证保护
		// https://blog.csdn.net/u012040869/article/details/80140515
		oauthServer.allowFormAuthenticationForClients();

	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		// 默认明文，不加密 ，应该改造 加个缓存 不然数据库读取太多！
		ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);

		clients.withClientDetails(clientDetailsService);

	}

	/** 其实默认有一个 但是，需要支持刷新token 非必要 **/
	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		// 如果不配置此项 则没有RefreshToken 返回
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

	/**
	 * token store
	 *
	 * @param accessTokenConverter
	 * @return
	 */
	@Bean
	public TokenStore tokenStore() {

		// TokenStore tokenStore = new JwtTokenStore(accessTokenConverter());
		TokenStore tokenStore = new RedisTokenStore(connectionFactory);
		return tokenStore;
	}
}
