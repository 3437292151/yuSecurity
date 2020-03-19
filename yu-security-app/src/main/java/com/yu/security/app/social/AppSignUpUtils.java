package com.yu.security.app.social;

import com.yu.security.app.AppSecurityException;
import com.yu.security.core.support.BuildKyType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Component
public class AppSignUpUtils {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    /**
     * 缓存社交用户信息
     * @param request
     * @param connectionData
     */
    public void saveConnectionData(ServletWebRequest request, ConnectionData connectionData){

        redisTemplate.opsForValue().set(getKey(request, BuildKyType.SAVE_TYPE), connectionData, 30, TimeUnit.MINUTES);
    }

    /**
     * 绑定用户信息
     * @param request
     * @param userId
     */
    public void doPostSignUp(ServletWebRequest request, String userId){
        String key = getKey(request, BuildKyType.NONE);
        if (!redisTemplate.hasKey(key)){
            throw new AppSecurityException("无法找到缓存中的社交用户信息");
        }
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);

        Connection<?> connection = connectionFactoryLocator
                .getConnectionFactory(connectionData.getProviderId())
                .createConnection(connectionData);
        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);

        redisTemplate.delete(key);
    }

    private String getKey(ServletWebRequest request, BuildKyType buildKyType) {
        String deviceId;
        if (buildKyType.equals(BuildKyType.SAVE_TYPE)){
            deviceId = UUID.randomUUID().toString();
            request.getResponse().addHeader("deviceId", deviceId);
        }else {
            deviceId = request.getHeader("deviceId");
            if (StringUtils.isBlank(deviceId)){
                throw new AppSecurityException("请在请求头里携带deviceId参数");
            }
        }
        log.info("deviceId: {}", deviceId);
        return "yu:security:social.connect." + deviceId;
    }
}
