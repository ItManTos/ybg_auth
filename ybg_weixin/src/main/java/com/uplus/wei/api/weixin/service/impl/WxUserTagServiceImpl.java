package com.uplus.wei.api.weixin.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uplus.wei.api.weixin.dao.WxUserTagMapper;
import com.uplus.wei.api.weixin.entity.WxUserTag;
import com.uplus.wei.api.weixin.service.WxUserTagService;

//import org.springframework.stereotype.Service;
/**
 * <p>
 * 用户打标签 服务实现类
 * </p>
 *
 * @author yanyu
 * @since 2019-04-04
 */
@Service
public class WxUserTagServiceImpl extends ServiceImpl<WxUserTagMapper, WxUserTag> implements WxUserTagService {

	@Override
	public void createTable(String appid) {
		baseMapper.createTable(appid);
	}

}
