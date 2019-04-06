package com.uplus.wei.api.weixin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uplus.wei.api.weixin.entity.WxUserTag;

/**
 * <p>
 * 用户打标签 服务类
 * </p>
 *
 * @author yanyu
 * @since 2019-04-04
 */
public interface WxUserTagService extends IService<WxUserTag> {
	/**
	 * 根据appid分表
	 *
	 * @param appid
	 */
	void createTable(String appid);

}
