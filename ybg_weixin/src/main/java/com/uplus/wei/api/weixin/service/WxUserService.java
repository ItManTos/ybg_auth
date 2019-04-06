package com.uplus.wei.api.weixin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uplus.wei.api.weixin.entity.WxUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yanyu
 * @since 2019-04-03
 */
public interface WxUserService extends IService<WxUser> {
	/**
	 * 创建表（根据公众号分表）
	 * 
	 * @param appid
	 * @return
	 */
	int creareTable(String appid);

}
