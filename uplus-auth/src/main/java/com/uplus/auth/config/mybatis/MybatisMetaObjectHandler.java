package com.uplus.auth.config.mybatis;

import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
//import com.uplus.wei.SpringContextUtils;
//import com.uplus.wei.api.user.service.ConnectionUserService;

/**
 * 注入公共字段自动填充,任选注入方式即可
 */
// @Component
public class MybatisMetaObjectHandler extends MetaObjectHandler {

    protected final static Logger logger = LoggerFactory.getLogger(MybatisMetaObjectHandler.class);

    @Override
    public void insertFill(
            MetaObject metaObject) {
        // try {
        // ConnectionUserService connectionUserService =
        // SpringContextUtils.getBean(ConnectionUserService.class);
        // connectionUserService.initSystemUtf8Mb4Encode();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // logger.info("新增的时候干点不可描述的事情");
    }

    @Override
    public void updateFill(
            MetaObject metaObject) {
        // try {
        // ConnectionUserService connectionUserService =
        // SpringContextUtils.getBean(ConnectionUserService.class);
        // connectionUserService.initSystemUtf8Mb4Encode();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // logger.info("更新的时候干点不可描述的事情");
    }
}