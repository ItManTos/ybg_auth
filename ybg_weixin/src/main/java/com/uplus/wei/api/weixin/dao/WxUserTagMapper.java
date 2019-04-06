package com.uplus.wei.api.weixin.dao;

import org.apache.ibatis.annotations.SelectProvider;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uplus.wei.api.weixin.dao.sql.WxUserTagSql;
import com.uplus.wei.api.weixin.entity.WxUserTag;

/**
 * <p>
 * 用户打标签 Mapper 接口
 * </p>
 *
 * @author yanyu
 * @since 2019-04-04
 */
public interface WxUserTagMapper extends BaseMapper<WxUserTag> {
	/**
	 * 根据应用分表
	 * {@link com.uplus.wei.api.weixin.dao.sql.WxUserTagSql#createTable(String)}
	 *
	 * @param appid
	 */
	@SelectProvider(type = WxUserTagSql.class, method = "createTable")
	void createTable(String appid);

}
