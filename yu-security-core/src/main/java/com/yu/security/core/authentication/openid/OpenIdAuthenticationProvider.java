package com.yu.security.core.authentication.openid;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Data
public class OpenIdAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        OpenIdAuthenticationToken openIdAuthenticationToken = (OpenIdAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(openIdAuthenticationToken.getPrincipal());
        OpenIdAuthenticationToken openIdAuthenticationTokenResult = new OpenIdAuthenticationToken(openIdAuthenticationToken.getPrincipal(),
                userDetails.getAuthorities());
        openIdAuthenticationTokenResult.setDetails(openIdAuthenticationToken.getDetails());
        return openIdAuthenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
