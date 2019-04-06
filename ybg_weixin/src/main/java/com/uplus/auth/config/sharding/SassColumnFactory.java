package com.uplus.auth.config.sharding;

import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.ShardingStrategyConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;

public class SassColumnFactory {

	/**
	 * 创建Sass化配置的封装方法(根据列的值 精准定位表) 如 aid=1,则定位的表是1
	 *
	 * @param logicTableName 逻辑表
	 * @param sassColumn     拆分列名
	 * @return
	 */
	public static TableRuleConfiguration getsassColumnRuleConfiguration(String logicTableName, String sassColumn) {
		// 自定义策略
		TableRuleConfiguration tableRuleConfig = new TableRuleConfiguration();
		tableRuleConfig.setLogicTable(logicTableName);
		// 标准分表策略
		// 精准定位表
		ShardingStrategyConfiguration tableShardingStrategyConfig = new StandardShardingStrategyConfiguration(
				sassColumn, new ColumnPreciseShardingAlgorithm(logicTableName));
		tableRuleConfig.setTableShardingStrategyConfig(tableShardingStrategyConfig);
		return tableRuleConfig;
	}

	private SassColumnFactory() {
	}
}
