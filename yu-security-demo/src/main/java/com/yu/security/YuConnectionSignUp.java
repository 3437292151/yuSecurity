package com.yu.security;

import com.yu.security.core.social.qq.api.QQ;
import com.yu.security.core.social.qq.api.QQUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Author yuchl
 * @Date 2020/3/8 0008
 * @Description 注册连接点
 */
@Component
public class YuConnectionSignUp implements ConnectionSignUp {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String execute(Connection<?> connection) {
        log.info("userKey: {}", connection.getKey().getProviderUserId());
        return connection.getKey().getProviderUserId();
    }
}
