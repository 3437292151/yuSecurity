package com.yu.security.app.config;

import com.yu.security.core.properties.OAuth2ClientProperties;
import com.yu.security.core.properties.OAuth2Properties;
import com.yu.security.core.properties.SecurityProperties;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Author yuchl
 * @Date 2020/3/15 0015
 * @Description 认证中心uaa服务配置
 */
@Configuration
@EnableAuthorizationServer
public class UaaConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private TokenStore redisTokenStore;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService)
        .tokenStore(redisTokenStore);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        OAuth2ClientProperties[] clientProperties = securityProperties.getOauth2().getClient();
        if (ArrayUtils.isNotEmpty(clientProperties)){
            for (OAuth2ClientProperties oAuth2: clientProperties){
                ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder clientBuilder = builder.withClient(oAuth2.getClientId());
                clientBuilder.secret(oAuth2.getClientSecret())
                        .accessTokenValiditySeconds(oAuth2.getAccessTokenValiditySeconds())
                        .refreshTokenValiditySeconds(oAuth2.getAccessTokenValiditySeconds());
                if (StringUtils.isNotEmpty(oAuth2.getAuthorizedGrantTypes())){
                    String[] authorizedGrantTypes = StringUtils.split(oAuth2.getAuthorizedGrantTypes(), ",");
                    clientBuilder.authorizedGrantTypes(authorizedGrantTypes);
                }else {
                    clientBuilder.authorizedGrantTypes("password", "refrest_Token");
                }
                if (StringUtils.isNotEmpty(oAuth2.getScopes())){
                    String[] scopes = StringUtils.split(oAuth2.getScopes(), ",");
                    clientBuilder.scopes(scopes);
                }
            }
        }

    }
}
