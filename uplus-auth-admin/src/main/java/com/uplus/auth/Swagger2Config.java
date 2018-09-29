package com.uplus.auth;/**
						* Created by Don
						* on 2018/5/23 0023.
						*/

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Sets;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @program: uplus-web
 *
 * @description: Swagger2配置
 *
 * @author: Don
 *
 * @create: 2018-05-23 16:41
 **/
@Configuration
public class Swagger2Config {
	/*
	 * @Bean public Docket createRestApi() { return new
	 * Docket(DocumentationType.SWAGGER_2) .apiInfo(apiInfo()) .select()
	 * .apis(RequestHandlerSelectors.basePackage("com.uplus.wei.controller"))
	 * .paths(PathSelectors.any()) .build(); }
	 */

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("建行新微银行WEB-API文档").description("新微银行WEB-API文档")
				.termsOfServiceUrl("http://wei.ujia007.com").version("1.0").build();
	}

	@Bean
	public Docket configSpringfoxDocketForAll() {
		return new Docket(DocumentationType.SWAGGER_2)

				.produces(Sets.newHashSet("application/json")).consumes(Sets.newHashSet("application/json"))
				.protocols(Sets.newHashSet("http", "https"/**/)).forCodeGeneration(true).select().paths(regex(".*"))
				// .apis(RequestHandlerSelectors.basePackage("*"))
				.build().apiInfo(apiInfo());
	}
}
