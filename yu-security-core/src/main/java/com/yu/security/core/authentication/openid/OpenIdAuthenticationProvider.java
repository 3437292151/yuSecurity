package com.yu.security.core.authentication.openid;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.UsersConnectionRepository;

import java.util.HashSet;
import java.util.Set;

@Data
public class OpenIdAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        OpenIdAuthenticationToken openIdAuthenticationToken = (OpenIdAuthenticationToken) authentication;

        Set<String> providerUserIds = new HashSet<>();
        providerUserIds.add(openIdAuthenticationToken.getPrincipal());
        Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(openIdAuthenticationToken.getCredentials(), providerUserIds);

        if (CollectionUtils.isEmpty(userIds) && userIds.size() != 1){
            throw new InternalAuthenticationServiceException("无法获取到用户信息");
        }
        String userId = userIds.iterator().next();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        OpenIdAuthenticationToken openIdAuthenticationTokenResult = new OpenIdAuthenticationToken(openIdAuthenticationToken.getPrincipal(),
                openIdAuthenticationToken.getCredentials(),
                userDetails.getAuthorities());
        openIdAuthenticationTokenResult.setDetails(openIdAuthenticationToken.getDetails());
        return openIdAuthenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
