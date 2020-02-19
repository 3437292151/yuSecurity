package com.yu.security.browser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-17
 * @Description: 自定义用户服务
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username :{}", username);

        return new User(username, "123456",true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
