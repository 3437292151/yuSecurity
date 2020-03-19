package com.yu.security.app.config;

import com.yu.security.core.social.YuSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author yuchl
 * @Date 2020/3/18 0018
 * @Description SpringSocialConfigurer 实例化后处理
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName, "yuSpringSocialConfigurer")){
            YuSpringSocialConfigurer configurer = (YuSpringSocialConfigurer) bean;
            configurer.signupUrl("social/signUp");
            return configurer;
        }
        return bean;
    }
}
