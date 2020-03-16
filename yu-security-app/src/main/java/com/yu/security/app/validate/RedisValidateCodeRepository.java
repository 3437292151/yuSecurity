package com.yu.security.app.validate;

import com.yu.security.core.validate.ValidateCode;
import com.yu.security.core.validate.ValidateCodeException;
import com.yu.security.core.validate.ValidateCodeRepository;
import com.yu.security.core.validate.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author yuchl
 * @Date 2020/3/16 0016
 * @Description session验证码持久化
 */
@Component("validateCodeRepository")
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    private Logger log = LoggerFactory.getLogger(getClass());

    private enum BuildKyType{
        SAVE_TYPE,NONE
    }

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void saveCode(ServletWebRequest request, ValidateCode validateCode, ValidateCodeType validateCodeType) {
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        log.info("code: {}", code);
        redisTemplate.opsForValue().set(buildKy(request, validateCodeType, BuildKyType.SAVE_TYPE), code, 60, TimeUnit.SECONDS);
    }

    @Override
    public ValidateCode getValidate(ServletWebRequest request, ValidateCodeType validateCodeType) {
        Object value = redisTemplate.opsForValue().get(buildKy(request, validateCodeType, BuildKyType.NONE));
        if (value == null){
            return null;
        }
        return (ValidateCode) value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        redisTemplate.delete(buildKy(request, validateCodeType, BuildKyType.NONE));
    }

    private String buildKy(ServletWebRequest request, ValidateCodeType validateCodeType, BuildKyType buildKyType) {
        String deviceId;
        if (buildKyType.equals(BuildKyType.SAVE_TYPE)){
            deviceId = UUID.randomUUID().toString();
            request.getResponse().addHeader("deviceId", deviceId);
        }else {
            deviceId = request.getHeader("deviceId");
            if (StringUtils.isBlank(deviceId)){
                throw new ValidateCodeException("请在请求头里携带deviceId参数");
            }
        }
        log.info("deviceId: {}", deviceId);
        return "code:" + validateCodeType.getParamNameOnValidate() + ":" + deviceId;
    }
}
