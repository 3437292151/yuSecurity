package com.yu.security.core.authentication.mobile;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Data
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(smsCodeAuthenticationToken.getPrincipal());
        SmsCodeAuthenticationToken smsCodeAuthenticationTokenResult = new SmsCodeAuthenticationToken(smsCodeAuthenticationToken.getPrincipal(),
                userDetails.getAuthorities());
        smsCodeAuthenticationTokenResult.setDetails(smsCodeAuthenticationToken.getDetails());
        return smsCodeAuthenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
