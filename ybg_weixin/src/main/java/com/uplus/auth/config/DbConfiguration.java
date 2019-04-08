package com.uplus.auth.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.uplus.auth.config.sharding.SassColumnFactory;

import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.NoneShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

@Configuration
public class DbConfiguration {
	@Bean
	/**
	 * 单库分表
	 *
	 * @return
	 */
	public DataSource dataSource(@Value("${sharding.spring.datasource.url}") String url,
			@Value("${sharding.spring.datasource.username}") String username,
			@Value("${sharding.spring.datasource.password}") String password) {
		DruidDataSource db = new DruidDataSource();
		db.setUrl(url);
		db.setUsername(username);
		db.setPassword(password);
		db.setDriverClassName("com.mysql.cj.jdbc.Driver");
		db.setMaxActive(200);
		db.setMinIdle(8);
		ArrayList<String> connectionInitSqls = new ArrayList<String>();
		connectionInitSqls.add("set names utf8mb4;");

		db.setConnectionInitSqls(connectionInitSqls);
		// TODO 如果不想支持分表，则直接return db；
		// return db;

		// 华丽的分割线------------------------------------------------------------------------
		// 下面支持分表
		Map<String, DataSource> dataSourceMap = new LinkedHashMap<String, DataSource>();
		dataSourceMap.put("ds0", db);
		ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
		// 自定义策略，根据公众号app_id分表
		Collection<TableRuleConfiguration> tableRuleConfigs = new ArrayList<TableRuleConfiguration>();
		TableRuleConfiguration wx_user = SassColumnFactory.getsassColumnRuleConfiguration("wx_user_", "app_id");
		TableRuleConfiguration wx_user_tag = SassColumnFactory.getsassColumnRuleConfiguration("wx_user_tag_", "app_id");
		// 加入user表策略，根据aid拆分如果接口不传aid 则会报错
		tableRuleConfigs.add(wx_user);
		// 加入微信用户标签表策略
		tableRuleConfigs.add(wx_user_tag);
		//
		shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new NoneShardingStrategyConfiguration());
		shardingRuleConfig.setDefaultTableShardingStrategyConfig(new NoneShardingStrategyConfiguration());
		shardingRuleConfig.setTableRuleConfigs(tableRuleConfigs);
		Map<String, Object> configMap = new LinkedHashMap<>();
		Properties props = new Properties();
		try {
			return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, configMap, props);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return db;
	}

}
