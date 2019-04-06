package com.uplus.wei.api.weixin.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uplus.wei.api.weixin.dao.sql.WxUserSql;
import com.uplus.wei.api.weixin.entity.WxUser;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yanyu
 * @since 2019-04-03
 */
public interface WxUserMapper extends BaseMapper<WxUser> {
	/**
	 * 创建表 {@link com.uplus.wei.api.weixin.dao.sql.WxUserSql#createTable(String)}
	 *
	 * @param appid
	 * @return
	 */
	@SelectProvider(method = "createTable", type = WxUserSql.class)
	void createTable(@Param("appid") String appid);

}
