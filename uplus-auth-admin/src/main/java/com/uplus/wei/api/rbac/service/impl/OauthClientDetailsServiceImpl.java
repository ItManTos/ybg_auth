package com.uplus.wei.api.rbac.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.uplus.wei.api.rbac.entity.OauthClientDetails;
import com.uplus.wei.api.rbac.mapper.OauthClientDetailsMapper;
import com.uplus.wei.api.rbac.service.OauthClientDetailsService;

/**
 * <p>
 * 在项目中,主要操作oauth_client_details表的类是JdbcClientDetailsService.java, 更多的细节请参考该类.
 * 也可以根据实际的需要,去扩展或修改该类的实现. 服务实现类
 * </p>
 *
 * @author yanyu
 * @since 2018-08-18
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails>
		implements OauthClientDetailsService {

}
