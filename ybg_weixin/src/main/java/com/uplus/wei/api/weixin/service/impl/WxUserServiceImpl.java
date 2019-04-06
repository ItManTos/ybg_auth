package com.uplus.wei.api.weixin.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uplus.wei.api.weixin.dao.WxUserMapper;
import com.uplus.wei.api.weixin.entity.WxUser;
import com.uplus.wei.api.weixin.service.WxUserService;

//import org.springframework.stereotype.Service;
/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yanyu
 * @since 2019-04-03
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements WxUserService {

	@Override
	public int creareTable(String appid) {
		baseMapper.createTable(appid);
		return 0;
	}

}
