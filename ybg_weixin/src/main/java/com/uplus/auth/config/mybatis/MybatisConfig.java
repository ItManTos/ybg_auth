// package com.uplus.auth.config.mybatis;
//
// import org.apache.ibatis.logging.stdout.StdOutImpl;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import com.baomidou.mybatisplus.spring.boot.starter.ConfigurationCustomizer;
//
/// ** @author yanyu **/
// @Configuration
// public class MybatisConfig {
//
// @Bean
// public ConfigurationCustomizer configurationCustomizer() {
// return new ConfigurationCustomizer() {
//
// @Override
// public void customize(
// org.apache.ibatis.session.Configuration configuration) {
// configuration.setCacheEnabled(false);// 不需要缓存
// configuration.setMapUnderscoreToCamelCase(true);// 设置驼峰命名规则
// configuration.setLogImpl(StdOutImpl.class);// 打印SQL语句
// }
// };
// }
// }
