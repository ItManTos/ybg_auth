package com.uplus.wei.api.weixin.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uplus.wei.api.weixin.dao.WechatPayConfigMapper;
import com.uplus.wei.api.weixin.entity.WechatPayConfig;
import com.uplus.wei.api.weixin.service.WechatPayConfigService;

/**
 * <p>
 * 微信支付列表 服务实现类
 * </p>
 *
 * @author yanyu
 * @since 2019-03-31
 */
@Service
public class WechatPayConfigServiceImpl extends ServiceImpl<WechatPayConfigMapper, WechatPayConfig>
		implements WechatPayConfigService {

}
