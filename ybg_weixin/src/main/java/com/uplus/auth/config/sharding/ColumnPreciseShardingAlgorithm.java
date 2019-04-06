package com.uplus.auth.config.sharding;

import java.util.Collection;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

/**
 * 根据列精准定位表的路由策略
 *
 * @author Michael
 *
 */
@SuppressWarnings("rawtypes")
public class ColumnPreciseShardingAlgorithm implements PreciseShardingAlgorithm {
	String tableName;

	public ColumnPreciseShardingAlgorithm(String tableName) {
		super();
		this.tableName = tableName;
	}

	@Override
	public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
		return getTableName() + shardingValue.getValue();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
